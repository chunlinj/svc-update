package org.daac.sw.pojo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by  HASEE on 2019-01-25
 */
@Component
@ConfigurationProperties(prefix = "local")
@PropertySource("classpath:localservice.properties")
public class LocalConfig {
    /**
     * 本地tomcat webapp目录位置
     */
    private static String webAppPath;
    /**
     * 本地tomcat 新建webapp目录位置
     */
    private static String webAppPathNew;
    /**
     * 本地tomcat webapp目录下version.txt位置
     */
    private static String webAppPathVersion;

    public static String getWebAppPathNew() {
        return webAppPathNew;
    }

    public static void setWebAppPathNew(String webAppPathNew) {
        LocalConfig.webAppPathNew = webAppPathNew;
    }

    public static String getWebAppPathVersion() {
        return webAppPathVersion;
    }

    public static void setWebAppPathVersion(String webAppPathVersion) {
        LocalConfig.webAppPathVersion = webAppPathVersion;
    }

    public static String getWebAppPath() {
        return webAppPath;
    }

    public static void setWebAppPath(String webAppPath) {
        LocalConfig.webAppPath = webAppPath;
    }
}
