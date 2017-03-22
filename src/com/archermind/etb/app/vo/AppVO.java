package com.archermind.etb.app.vo;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;

import com.archermind.etb.util.Constant;
import com.archermind.etb.util.PropertyUtil;
/**
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @since 1.0
 */
public class AppVO {
private int appInfoId;
	/*应用分类ID*/
	private int appTypesId;
	/*应用名称*/
	private String appInfoName;
	/*应用修改时间*/
	private Date appInfoUpdateTime;
	/*应用操作*/
//	private String appInfoOperater;
	/*应用APK路径*/
	private String appInfoPath;
	/*应用版本VersionName*/
	private String appInfoVersion;
	/*应用版本VersionName*/
	private String appInfoVersionCode;
	
	/*应用状态*/
	private int appInfoStatus;
	/*应用审核人*/
	private String appInfoChecher;
	/*应用上线人*/
	private String appInfoPublisher;
	/*应用上线时间*/
	private Date appInfoBeginTime;
	/*应用下线时间*/
	private Date appInfoEndTime;
	/*应用备注*/
	private String appInfoTips;
	/*应用分类名称*/
	private String appTypesName;
	/*应用APK*/
	private File appInfoFile;
	/*应用删除状态*/
	private int dataStat;
	
	/*应用包名*/
	private String appInfoPackage;
	/*应用创建人*/
	private String appInfoCreater;
	/*应用创建时间*/
	private Date appInfoCreateTime;
	/*应用审核人*/
	private Date appInfoCheckTime;
	/*应用审核时间*/
	private Date appInfoPublishTime;
	/*应用审核驳回意见*/
	private String appInfoCheckSuggest;
	//修改时间格式转换
	private String appInfoUpdateTimeDsp;
	//开始时间格式转换
	private String appInfoBeginTimeDsp;
	//结束时间格式转换
	private String appInfoEndTimeDsp;
	//创建时间格式转换
	private String appInfoCreateTimeDsp;
	//审核时间格式转换
	private String appInfoCheckTimeDsp;
	//发布时间格式转换
	private String appInfoPublishTimeDsp;
	
	//上传应用的原文件名
	private String appInfoFilename;
	
	
	
	public String getAppInfoFilename() {
		return appInfoFilename;
	}
	public void setAppInfoFilename(String appInfoFilename) {
		this.appInfoFilename = appInfoFilename;
	}
	public String getAppInfoUpdateTimeDsp() {
		return appInfoUpdateTimeDsp;
	}
	public void setAppInfoUpdateTimeDsp(String appInfoUpdateTimeDsp) {
		this.appInfoUpdateTimeDsp = appInfoUpdateTimeDsp;
	}
	public String getAppInfoBeginTimeDsp() {
		return appInfoBeginTimeDsp;
	}
	public void setAppInfoBeginTimeDsp(String appInfoBeginTimeDsp) {
		this.appInfoBeginTimeDsp = appInfoBeginTimeDsp;
	}
	public String getAppInfoEndTimeDsp() {
		return appInfoEndTimeDsp;
	}
	public void setAppInfoEndTimeDsp(String appInfoEndTimeDsp) {
		this.appInfoEndTimeDsp = appInfoEndTimeDsp;
	}
	public String getAppInfoCreateTimeDsp() {
		return appInfoCreateTimeDsp;
	}
	public void setAppInfoCreateTimeDsp(String appInfoCreateTimeDsp) {
		this.appInfoCreateTimeDsp = appInfoCreateTimeDsp;
	}
	public String getAppInfoCheckTimeDsp() {
		return appInfoCheckTimeDsp;
	}
	public void setAppInfoCheckTimeDsp(String appInfoCheckTimeDsp) {
		this.appInfoCheckTimeDsp = appInfoCheckTimeDsp;
	}
	public String getAppInfoPublishTimeDsp() {
		return appInfoPublishTimeDsp;
	}
	public void setAppInfoPublishTimeDsp(String appInfoPublishTimeDsp) {
		this.appInfoPublishTimeDsp = appInfoPublishTimeDsp;
	}
	public String getAppInfoCheckSuggest() {
		return appInfoCheckSuggest;
	}
	public void setAppInfoCheckSuggest(String appInfoCheckSuggest) {
		this.appInfoCheckSuggest = appInfoCheckSuggest;
	}
	public String getAppInfoCreater() {
		return appInfoCreater;
	}
	public void setAppInfoCreater(String appInfoCreater) {
		this.appInfoCreater = appInfoCreater;
	}
	public Date getAppInfoCreateTime() {
		return appInfoCreateTime;
	}
	public void setAppInfoCreateTime(Date appInfoCreateTime) {
		this.appInfoCreateTime = appInfoCreateTime;
	}
	public Date getAppInfoCheckTime() {
		return appInfoCheckTime;
	}
	public void setAppInfoCheckTime(Date appInfoCheckTime) {
		this.appInfoCheckTime = appInfoCheckTime;
	}
	public Date getAppInfoPublishTime() {
		return appInfoPublishTime;
	}
	public void setAppInfoPublishTime(Date appInfoPublishTime) {
		this.appInfoPublishTime = appInfoPublishTime;
	}
	public String getAppInfoPackage() {
		return appInfoPackage;
	}
	public void setAppInfoPackage(String appInfoPackage) {
		this.appInfoPackage = appInfoPackage;
	}
	public int getDataStat() {
		return dataStat;
	}
	public void setDataStat(int dataStat) {
		this.dataStat = dataStat;
	}
	public File getAppInfoFile() {
		return appInfoFile;
	}
	public void setAppInfoFile(File appInfoFile) {
		this.appInfoFile = appInfoFile;
	}
	public String getAppTypesName() {
		return appTypesName;
	}
	public void setAppTypesName(String appTypesName) {
		this.appTypesName = appTypesName;
	}
	public int getAppInfoId() {
		return appInfoId;
	}
	public void setAppInfoId(int appInfoId) {
		this.appInfoId = appInfoId;
	}
	public int getAppTypesId() {
		return appTypesId;
	}
	public void setAppTypesId(int appTypesId) {
		this.appTypesId = appTypesId;
	}
	public String getAppInfoName() {
		return appInfoName;
	}
	public void setAppInfoName(String appInfoName) {
		this.appInfoName = appInfoName;
	}
	public Date getAppInfoUpdateTime() {
		return appInfoUpdateTime;
	}
	public void setAppInfoUpdateTime(Date appInfoUpdateTime) {
		this.appInfoUpdateTime = appInfoUpdateTime;
	}
//	public String getAppInfoOperater() {
//		return appInfoOperater;
//	}
//	public void setAppInfoOperater(String appInfoOperater) {
//		this.appInfoOperater = appInfoOperater;
//	}
	public String getAppInfoPath() {
		return appInfoPath;
	}
	public void setAppInfoPath(String appInfoPath) {
		this.appInfoPath = appInfoPath;
	}
	
	public String getAppInfoVersion() {
		return appInfoVersion;
	}
	public void setAppInfoVersion(String appInfoVersion) {
		this.appInfoVersion = appInfoVersion;
	}
	public String getAppInfoVersionCode() {
		return appInfoVersionCode;
	}
	public void setAppInfoVersionCode(String appInfoVersionCode) {
		this.appInfoVersionCode = appInfoVersionCode;
	}
	public int getAppInfoStatus() {
		return appInfoStatus;
	}
	public void setAppInfoStatus(int appInfoStatus) {
		this.appInfoStatus = appInfoStatus;
	}
	public String getAppInfoChecher() {
		return appInfoChecher;
	}
	public void setAppInfoChecher(String appInfoChecher) {
		this.appInfoChecher = appInfoChecher;
	}
	public String getAppInfoPublisher() {
		return appInfoPublisher;
	}
	public void setAppInfoPublisher(String appInfoPublisher) {
		this.appInfoPublisher = appInfoPublisher;
	}
	public Date getAppInfoBeginTime() {
		return appInfoBeginTime;
	}
	public void setAppInfoBeginTime(Date appInfoBeginTime) {
		this.appInfoBeginTime = appInfoBeginTime;
	}
	public Date getAppInfoEndTime() {
		return appInfoEndTime;
	}
	public void setAppInfoEndTime(Date appInfoEndTime) {
		this.appInfoEndTime = appInfoEndTime;
	}
	public String getAppInfoTips() {
		return appInfoTips;
	}
	public void setAppInfoTips(String appInfoTips) {
		this.appInfoTips = appInfoTips;
	}
	
	public String getResourcePathDsp(){
		String path = Constant.AD_FILE_UPLOAD_FOLDER + "/";
		String s = this.getAppInfoPath();
		if(s.indexOf("\\")!=-1){
			String[] ss = s.split("apps");
			path+="apps"+"/"+ss[1].substring(1);
		}else{
			path+=s;
		}
		
		return path;
	}
}
