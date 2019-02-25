package org.daac.sw.pojo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rest")
@PropertySource("classpath:microservice.properties")
public class RestConfig {

	/**
	 * 查找所有分支
	 */
	private static String findCloudBranchAll;
	/**
	 * 根据版本号下载压缩包
	 */
	private static String branchDown;

	/**
	 * 域名
	 */
	private static String baseUrl;

	public static String getBaseUrl() {
		return baseUrl;
	}

	public static void setBaseUrl(String baseUrl) {
		RestConfig.baseUrl = baseUrl;
	}

	public static String getFindCloudBranchAll() {
		return findCloudBranchAll;
	}

	public static void setFindCloudBranchAll(String findCloudBranchAll) {
		RestConfig.findCloudBranchAll = findCloudBranchAll;
	}

	public static String getBranchDown() {
		return branchDown;
	}

	public static void setBranchDown(String branchDown) {
		RestConfig.branchDown = branchDown;
	}
}
