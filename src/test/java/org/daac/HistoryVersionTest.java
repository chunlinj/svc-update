package org.daac;

import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.daac.sw.dao.HistoryVersionRepository;
import org.daac.sw.pojo.HistoryVersions;
import org.daac.sw.service.UpdateVersionService;
import org.daac.sw.util.CommonUtil;
import org.daac.sw.util.ZipSearchUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by  HASEE on 2019-01-23
 */
/*@SpringBootTest
@RunWith(SpringRunner.class)*/
public class HistoryVersionTest {

    @Autowired
    private HistoryVersionRepository repository;
    @Autowired
    private UpdateVersionService updateVersionService;

    @Test
    public void test1() {
        List<HistoryVersions> all = repository.findAll();
        System.out.println(all);
    }

    @Test
    public void test2() {
        int page = 1;
        int size = 10;
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        Page<HistoryVersions> all = repository.findAll(pageable);
        System.out.println(all);
    }

    @Test
    public void test3() throws Exception {
        //CommonUtil.uzipDec("C:\\tools\\apache-tomcat-8.5.20\\webapps"+File.separator+"configTool.zip","C:\\tools\\apache-tomcat-8.5.20\\webapps");
        File file = new File("C:\\tools\\apache-tomcat-8.5.20\\webapps" + File.separator + "configTool.zip");
        CommonUtil.unZip(file, "C:\\tools\\apache-tomcat-8.5.20\\webapps");
    }

    @Test
    public void test4() {
        ZipSearchUtil zipSearch = new ZipSearchUtil();
        zipSearch.zipLibSearch("C:\\tools\\apache-tomcat-8.5.20\\webapps" + File.separator + "configTool.zip", 0);
    }

    @Test
    public void test5() throws IOException {
        CommonUtil.getZipFileAllName("C:\\tools\\apache-tomcat-8.5.20\\webapps" + File.separator + "configTool.zip", 0);
    }


    @Test
    public void test6() throws IOException {
        Runtime.getRuntime().exec("C:\\tools\\apache-tomcat-8.5.20\\webapps\\test.bat 1 ss aa cc ss");
    }

    @Test
    public void test7() throws IOException {
        BufferedReader br =null;
        try {
            // 执行ping命令
            Process process = Runtime.getRuntime().exec("cmd /c C:\\tools\\apache-tomcat-8.5.20\\webapps\\test.bat 1 ss aa cc ss");
            br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            br.close();
        }
    }

    @Test
    public void test8() throws IOException {
        String webapps = "C:\\tool\\testtomcat\\webapps";
        String backup = "C:\\tool\\testtomcat\\backup";
        String webappsNew = "C:\\tools\\apache-tomcat-8.5.20\\webapps";
        //CommonUtil.excuteBatFile("C:\\tools\\apache-tomcat-8.5.20\\webapps\\test.bat " + webapps + " " + backup + " "+webappsNew+"", true);
        Runtime.getRuntime().exec("cmd.exe /k C:\\tools\\apache-tomcat-8.5.20\\webapps\\test.bat " + webapps + " " + backup + " "+webappsNew+"");
    }

    @Test
    public void test9() throws IOException {
        String str="1";
        String substring = str.substring(str.length() - 3);

    }


}
