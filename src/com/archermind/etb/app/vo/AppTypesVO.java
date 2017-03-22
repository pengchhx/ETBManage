package com.archermind.etb.app.vo;

import java.io.File;

import com.archermind.etb.util.Constant;

/**
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @since 1.0
 */
public class AppTypesVO {
	/*应用分类ID*/
	private int appTypesId;
	/*应用分类名称*/
	private String appTypesName;
	/*应用分类备注*/
	private String appTypesTips;
	/*应用分类删除状态*/
	private int dataStat;
	/*应用分类英文名字*/
	private String appTypesEnName;
	/*应用分类ICON路径*/
	private String appTypesUrl;
	/*应用分类ICON文件名*/
	private String appTypesFileName;
	/*应用分类ICON文件*/
	private File appTypesFile;
	
	public String getAppTypesFilePathDsp() {
		String path = Constant.AD_FILE_UPLOAD_FOLDER + "/";
		String s = this.getAppTypesUrl();
		if(s.indexOf("\\")!=-1){
			String[] ss = s.split("apps");
			path+="apps"+"/"+ss[1].substring(1);
		}else{
			path+=s;
		}
		
		return path;
	}
	public File getAppTypesFile() {
		return appTypesFile;
	}
	public void setAppTypesFile(File appTypesFile) {
		this.appTypesFile = appTypesFile;
	}
	public String getAppTypesFileName() {
		return appTypesFileName;
	}
	public void setAppTypesFileName(String appTypesFileName) {
		this.appTypesFileName = appTypesFileName;
	}
	public String getAppTypesEnName() {
		return appTypesEnName;
	}
	public void setAppTypesEnName(String appTypesEnName) {
		this.appTypesEnName = appTypesEnName;
	}
	public String getAppTypesUrl() {
		return appTypesUrl;
	}
	public void setAppTypesUrl(String appTypesUrl) {
		this.appTypesUrl = appTypesUrl;
	}
	public int getDataStat() {
		return dataStat;
	}
	public void setDataStat(int dataStat) {
		this.dataStat = dataStat;
	}
	public int getAppTypesId() {
		return appTypesId;
	}
	public void setAppTypesId(int appTypesId) {
		this.appTypesId = appTypesId;
	}
	public String getAppTypesName() {
		return appTypesName;
	}
	public void setAppTypesName(String appTypesName) {
		this.appTypesName = appTypesName;
	}
	public String getAppTypesTips() {
		return appTypesTips;
	}
	public void setAppTypesTips(String appTypesTips) {
		this.appTypesTips = appTypesTips;
	}
	
	
}
