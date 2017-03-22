package com.archermind.etb.util;

/**
 * 
 * APK信息包装类
 * 
 * @author  002538   chi.zhang
 * @version 1.0, 2013.07.19
 * @see 
 * @since 1.0
 * 
 */
public class ApkInfo {
	private String versionCode;
	private String packageName;
	private String versionName;
	
	public ApkInfo(){}
	public ApkInfo(String versionName, String versionCode, String packageName){
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.packageName = packageName;
	}
	
	
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
}
