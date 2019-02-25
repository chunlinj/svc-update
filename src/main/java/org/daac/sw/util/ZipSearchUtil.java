package org.daac.sw.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by  HASEE on 2019-01-27
 */
public class ZipSearchUtil {
    private List list = new ArrayList();

    public List zipLibSearch(String rootName, int searchlibNum) {

        ZipFile slf_file = null;
        try {
            slf_file = new ZipFile(rootName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        Enumeration e = slf_file.entries();
        while (e.hasMoreElements()) {
            ZipEntry slf_zipEntry = (ZipEntry) e.nextElement();
            if (slf_zipEntry.isDirectory()) {
                StringTokenizer st = new StringTokenizer(slf_zipEntry.getName(),
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
            } else {
                if (slf_zipEntry.getSize() < 1) {
                    System.out.println("压缩包内的文件大小不符合规范");
                    try {
                        slf_file.close();
                    } catch (IOException e1) {
                        // TODO 自动生成 catch 块
                        e1.printStackTrace();
                    }
                    return null;
                }
            }

        }
        try {
            slf_file.close();
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            e1.printStackTrace();
        }
        return list;
    }

    public List getZipFile(String rootName)
    {
        ZipFile slf_zipFile=null;
        try {
            slf_zipFile=new ZipFile(rootName);
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        Enumeration e=slf_zipFile.entries();
        while(e.hasMoreElements())
        {
            ZipEntry slf_zipEntry=(ZipEntry)e.nextElement();
            if(!slf_zipEntry.isDirectory())
            {
                String filename=slf_zipEntry.getName().substring(slf_zipEntry.getName().lastIndexOf("/")+1, slf_zipEntry.getName().length());
                list.add(filename);
            }
        }
        return list;
    }
}

