package org.daac.sw.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.daac.sw.pojo.config.LocalConfig;
import org.daac.sw.pojo.response.BranchResponse;
import org.daac.sw.pojo.response.VersionsRespose;
import org.daac.sw.service.UpdateVersionService;
import org.daac.sw.util.CommonUtil;
import org.daac.sw.util.HttpRequest;
import org.daac.sw.util.RestRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by  HASEE on 2019-01-24
 */
@Slf4j
@Service
public class UpdateVersionServiceImpl implements UpdateVersionService {

    @Override
    public List<String> findALLVersion(HttpServletRequest request) throws Exception {
        BranchResponse versionAll = RestRequest.getVersionAll(request);
        return versionAll.getBranch();
    }

    /**
     * 从配置文件中获取tomcat webapp位置
     * @return
     */
    @Override
    public Map<String,String> findCurrent() throws Exception {
        Map<String,String> map= new HashMap<>();
        map.put("version",RestRequest.getLocalVersion());
        return map;
    }

    @Override
    public Boolean updateVersion(String version,HttpServletRequest request) throws Exception {
        //1.远程获取下载路径
        VersionsRespose informationByVersion = RestRequest.getInformationByVersion(version, request);
        String versionStr = informationByVersion.getFileUrl().getVersion();
        if(StringUtils.isBlank(versionStr)){
            log.error("org.daac.sw.service.impl.UpdateVersionServiceImpl.updateVersion.updateVersion");
            throw new Exception("没有可下载版本");
        }
        //String downUrl = versionList.get(0).getFileUrl().get(LocalConfig.getFileType());
        if (informationByVersion.getFileUrl().getFileUrl()==null){
            throw new Exception("请先在localrepo中下载最新版本再进行跟新");
        }
        String downUrl = informationByVersion.getFileUrl().getFileUrl().getService();
       // String downUrl = "http://127.0.0.1:8443/repo/file/"+version+"/config.zip";
        //2.根据url进行下载，先将原先zip包进行删除
        int i = downUrl.lastIndexOf("/");
        String fileName = downUrl.substring(i+1);
        File file = new File(LocalConfig.getWebAppPath() , fileName);
        if (file.exists()){
            file.delete();
        }
        String webAppPath = LocalConfig.getWebAppPath();
        HttpRequest.downLoadFromUrl(downUrl,fileName,webAppPath);
        //3.将tomcat下webapp下的war包和cfgtool文件夹及version.txt移动到backup+time民命的目录
        int rootDirIndex = webAppPath.lastIndexOf("\\");
        String rootDir = webAppPath.substring(0, rootDirIndex);
        Date date =new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(date);
        String backupDirStr = rootDir + File.separator+ "backup"+format;
        File backupDir = new File(backupDirStr);
        if(!backupDir.exists()){
            backupDir.mkdirs();
        }
        //这里在不知道压缩文件中有多少文件或文件夹，遍历zip包所有文件将name与webapp匹配的文件进行移动
        //fileName = "configTool.zip";
        List<String> zipList = CommonUtil.getZipFileAllName(webAppPath + File.separator + fileName,0);
        if (zipList!=null && zipList.size()>0){
            //后移工程文件
            String contextPath = request.getContextPath().substring(1);
            //先移war包
            for (String zfn:zipList){
                int index = zfn.lastIndexOf(".");
                if (index>0){
                    String lastName=zfn.substring(index+1);
                    if ("war".equals(lastName)){
                        int war = zfn.lastIndexOf("war");
                        String projectName = zfn.substring(0, war-1);
                        if (contextPath.equals(projectName)) continue;
                    }
                }
                CommonUtil.moveFile(webAppPath+File.separator+zfn,backupDirStr);
            }

            /*for (int j=0;j<zipList.size(); j++){
                //将原工程改名
                String pj = zipList.get(j);
                int index = pj.lastIndexOf(".");
                if (index<=0){
                    continue;
                }
                String lastName=pj.substring(index+1);
                if ("war".equals(lastName)){
                    int war = pj.lastIndexOf("war");
                    String projectName = pj.substring(0, war-1);
                    if (contextPath.equals(projectName)) continue;
                    CommonUtil.moveFile(webAppPath+File.separator+projectName,backupDirStr);
                }
            }*/

            //4.将下载好的zip包进行解压，调用bat脚本进行解压,
            CommonUtil.unZip(new File(webAppPath+File.separator+fileName),webAppPath);
            //最后替换掉自己
            //CommonUtil.moveFile(webAppPath+File.separator+contextPath,backupDirStr);
        }
        return true;
    }
    @Override
    public Boolean updateVersionBat(String version,HttpServletRequest request) throws Exception {
        //1.远程获取下载路径
        VersionsRespose informationByVersion = RestRequest.getInformationByVersion(version,request);
        String versionStr = informationByVersion.getFileUrl().getVersion();
        if(StringUtils.isBlank(versionStr)){
            log.error("org.daac.sw.service.impl.UpdateVersionServiceImpl.updateVersion.updateVersion");
            throw new Exception("没有可下载版本");
        }
        //String downUrl = versionList.get(0).getFileUrl().get(LocalConfig.getFileType());
        if (informationByVersion.getFileUrl().getFileUrl()==null){
            throw new Exception("请先在localrepo中下载最新版本再进行跟新");
        }
        String downUrl = informationByVersion.getFileUrl().getFileUrl().getService();
        //2.根据url进行下载，先将原先webappsnew进行删除
        String webAppPathNew = LocalConfig.getWebAppPathNew();
        // 删除webapps
        File webappNew=new File(webAppPathNew);
        if (webappNew.exists()&&webappNew.isDirectory()){
            CommonUtil.delFolder(webAppPathNew);
        }
        if (!webappNew.exists()){
            webappNew.mkdirs();
        }
        int i = downUrl.lastIndexOf("/");
        String fileName = downUrl.substring(i+1);

        HttpRequest.downLoadFromUrl(downUrl,fileName,webAppPathNew);
        //3.将zip包进行解压
        CommonUtil.unZip(new File(webAppPathNew+File.separator+fileName),webAppPathNew);
        String webAppPath = LocalConfig.getWebAppPath();
        //4.更新通知
        File file = new File(webAppPath+"//updater.txt");
        if (file.exists()){
            file.delete();
        }
        file.createNewFile();
        return true;
    }




}
