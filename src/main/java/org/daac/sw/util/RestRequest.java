package org.daac.sw.util;

import com.alibaba.fastjson.JSON;
import com.zen.parking.bootstrap.exception.BootStrapBizException;
import com.zen.parking.bootstrap.http.ServiceRequestExecutor;
import lombok.extern.slf4j.Slf4j;
import org.daac.sw.pojo.config.LocalConfig;
import org.daac.sw.pojo.config.RestConfig;
import org.daac.sw.pojo.response.BranchResponse;
import org.daac.sw.pojo.response.VersionsRespose;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by  HASEE on 2019-01-25
 */
@Slf4j
public class RestRequest {

    public static BranchResponse getVersionAll(HttpServletRequest request) throws Exception {
        BranchResponse responseEntity = null;
        try{
            log.info("org.daac.sw.util.RestRequest.getVersionAll.port:",request.getServerPort());
            int serverPort = request.getServerPort();
            String baseUrl = RestConfig.getBaseUrl();
            String restUrl = baseUrl+serverPort+RestConfig.getFindCloudBranchAll();
            String result = ServiceRequestExecutor.getInstance().get(restUrl,"");
            responseEntity = JSON.parseObject(result, BranchResponse.class);
            Assert.notNull(responseEntity, "responseEntity must not be null!");
            if (!"000000".equals(responseEntity.getReturnCode())){
                log.error("org.daac.sw.util.RestRequest.getVersionAll","请求码为:"+responseEntity.getReturnCode());
                throw new BootStrapBizException("localrepo is exception");
            }
        }catch (Exception e){
            log.error("RestRequest.BranchResponse:",e);
            throw new Exception("localrepo is exception");
        }
        return responseEntity;
    }

    public static VersionsRespose getInformationByVersion(String version, HttpServletRequest request) throws Exception {
        VersionsRespose responseEntity =null;
        try{
            log.info("org.daac.sw.util.RestRequest.getInformationByVersion.port:",request.getServerPort());
            int serverPort = request.getServerPort();
            String baseUrl = RestConfig.getBaseUrl();
            String restUrl = baseUrl+serverPort+RestConfig.getBranchDown();
            String result = ServiceRequestExecutor.getInstance().get(restUrl+"/"+version,"");
            responseEntity = JSON.parseObject(result, VersionsRespose.class);
            Assert.notNull(responseEntity, "responseEntity must not be null!");
            if (!"000000".equals(responseEntity.getReturnCode())){
                log.error("org.daac.sw.util.RestRequest.getInformationByVersion","请求码为:"+responseEntity.getReturnCode());
                throw new BootStrapBizException("localrepo is exception");
            }
        }catch (Exception e){
            log.error("org.daac.sw.util.RestRequest.getInformationByVersion",e);
            throw new BootStrapBizException("localrepo is exception");
        }

        return responseEntity;
    }

    public static String getLocalVersion() throws Exception {
        log.info("org.daac.sw.util.RestRequest.getLocalVersion");
        String webAppPath = LocalConfig.getWebAppPathVersion();
        return HttpRequest.readTxt(webAppPath);
    }



}
