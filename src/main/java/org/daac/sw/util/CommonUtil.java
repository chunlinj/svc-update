package org.daac.sw.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.*;

/**
 * Created by  HASEE on 2019-01-25
 */
@Slf4j
public class CommonUtil {

    private static List list = new ArrayList();

    /**
     * 将文件夹更名
     * @param src
     * @param dist
     * @return
     */
    public static Boolean renameDir(String src,String dist){
        File file=new File(src);
        if (!file.isDirectory()){
            return true;
        }
        file.renameTo(new File(dist));
        return true;
    }
    /**
     *
     * @param fromPath 要移动的文件或文件夹的绝对路径包含文件名
     * @param toPath 目标目录路径
     * @return
     * @throws IOException
     */
    public static Boolean  moveFile(String fromPath,String toPath) throws IOException {
        log.info("移动文件：从路径 " + fromPath + " 移动到路径 " + toPath);
        File file = new File(fromPath);
        if (!file.exists()){
            return true;
        }
        if (file.isFile()){
            File toFile=new File(toPath+"\\"+file.getName());
            if (toFile.exists()){
                System.out.println("文件已存在");
                return false;
            }
            else{
                file.renameTo(toFile);
                System.out.println("移动文件成功");
                return true;
            }
        }else{
            // 移动目录
            File src = new File(fromPath);
            File dest = new File(toPath);
            copyFile(src,dest);
            return true;
        }
    }

    /**
     *将指定目录(包含内容)复制到另一个目录中
     * @param src 源文件目录
     * @param dest 木标目录
     * @throws IOException
     */
    public static void copyFile(File src,File dest) throws IOException {
        // 在目的地创建文件夹  c:\\Test\\day09\\avi
        File newFile = new File(dest,src.getName());
        // 判断拼接成的路径是否存在
        if(!newFile.exists()){
            newFile.mkdirs();
        }
        // 获取源目录中的所有的文件和文件夹
        File[] files = src.listFiles();
        for (File file : files) {
            if(file.isFile()){
                // 开始复制文件
                FileInputStream fis = new FileInputStream(file);
                // c:\\Test\\day09\\ppt
                FileOutputStream fos = new FileOutputStream(new File(newFile,file.getName()));
                byte[] b = new byte[1024];
                int len;
                while((len  = fis.read(b)) !=-1){
                    fos.write(b, 0, len);
                }

                fos.close();
                fis.close();

            }else if(file.isDirectory()){
                copyFile(file, newFile);
            }
        }
        delFolder(src.getPath());
    }

    /**
     * 绝对路径删除
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除指定文件夹下所有文件
//param path 文件夹完整绝对路径
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     2
     * zip解压
     3
     * @param srcFile        zip源文件
    4
     * @param destDirPath     解压后的目标文件夹
    5
     * @throws RuntimeException 解压失败会抛出运行时异常
    6
     */
    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile,Charset.forName("GBK"));
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println("解压" + entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if(!targetFile.getParentFile().exists()){
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("解压完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if(zipFile != null){
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解压zip
     * @param src 源zip路径，包含文件名
     * @param dest 解压目标路径
     * @throws IOException
     */
    public static void uzipDec(String src,String dest) throws IOException{
            ZipInputStream Zin=new ZipInputStream(new FileInputStream(
                    src));//输入源zip路径
            BufferedInputStream Bin=new BufferedInputStream(Zin);
            String Parent=dest; //输出路径（文件夹目录）
            File Fout=null;
            ZipEntry entry;
            try {
                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
                    Fout=new File(Parent,entry.getName());
                    if(!Fout.exists()){
                        (new File(Fout.getParent())).mkdirs();
                    }
                    FileOutputStream out=new FileOutputStream(Fout);
                    BufferedOutputStream Bout=new BufferedOutputStream(out);
                    int b;
                    while((b=Bin.read())!=-1){
                        Bout.write(b);
                    }
                    Bout.close();
                    out.close();
                    System.out.println(Fout+"解压成功");
                }
                Bin.close();
                Zin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    /**
     * 获取zip包中所有文件名
     * @param zipPath  zip压缩文件路径
     * @return zip包所有文件名
     * @throws IOException
     */
    public static List<String> getZipFileAllName(String zipPath,int searchlibNum) throws IOException {
        List<String> zipList=new ArrayList<>();
        File file=new File(zipPath);
        InputStream input = new FileInputStream(file);
        ZipInputStream zipInputStream = new ZipInputStream(input, Charset.forName("GBK"));
        ZipEntry zipEntry = null;
        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            //判断是否是目录，是的话取指定目录
            getFirstDir(searchlibNum, zipEntry);

        }
        zipList.addAll(list);
        return zipList;
    }

    private static void getFirstDir(int searchlibNum, ZipEntry zipEntry) {
        StringTokenizer st = new StringTokenizer(zipEntry.getName(),
                "/");

        int k = st.countTokens();

        if (k >= 1) {
            List slf_temp = new ArrayList();
            while (st.hasMoreTokens()) {

                slf_temp.add(st.nextToken());
            }

            if (slf_temp.size() > searchlibNum) {
                if (list.contains(slf_temp.get(searchlibNum)) == false) {
                    list.add(slf_temp.get(searchlibNum));
                }
            }

        }
    }

    /**
     * 执行bat文件，
     * @param file bat文件路径
     * @param isCloseWindow 执行完毕后是否关闭cmd窗口
     * @return bat文件输出log
     */
    public static String  excuteBatFile(String file, boolean isCloseWindow) throws IOException {
        String cmdCommand = null;
        if(isCloseWindow)
        {
            cmdCommand = "cmd.exe /c "+file;
        }else
        {
            cmdCommand = "cmd.exe /k "+file;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Process process = null;
        BufferedReader bufferedReader =null;
        try {
            process = Runtime.getRuntime().exec(cmdCommand);
            bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "GBK"));
            String line = null;
            while((line=bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line+"\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            bufferedReader.close();
        }
    }



}
