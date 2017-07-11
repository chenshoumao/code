package com.ibm.entity;

/**
 * 
 * 功能描述：此类是描述应用链接的一个实体类
 *
 * 类名：AppLink
 *
 * Version info 版本号：V1.0 
 * © Copyright 续日科技 2016年7月8日 版权所有
 */
public class AppLink {
	public static final String APPNAME = "appName";
	//应用链接的key
	private String appKey;
	//应用链接的名称
	private String appName;
	//应用链接的地址
	private String appURL;
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppURL() {
		return appURL;
	}
	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}
	@Override
	public String toString() {
		return "AppLink [appKey=" + appKey + ", appName=" + appName
				+ ", appURL=" + appURL + "]";
	}
}
