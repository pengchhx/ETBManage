package com.archermind.etb.app.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.archermind.etb.util.DateUtil;

@Entity
@Table(name="ETB_APP_INFO")
public class AppInfo implements Serializable {
	
	private static final long serialVersionUID = -8591567811561118012L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "APP_INFO_ID")
	private int appInfoId;
	
	@Column(name = "APP_TYPES_ID")
	private int appTypesId;
	
	@Column(name="APP_INFO_NAME")
	private String appInfoName;
	
	@Column(name="APP_INFO_UPDATE_TIME")
	private Date appInfoUpdateTime;
	
//	@Column(name="APP_INFO_OPERATER")
//	private String appInfoOperater;

	@Column(name="APP_INFO_PATH")
	private String appInfoPath;
	
	@Column(name="APP_INFO_VERSION")
	private String appInfoVersion;
	
	@Column(name="APP_INFO_VERSION_CODE")
	private String appInfoVersionCode;
	
	@Column(name = "APP_INFO_STATUS")
	private int appInfoStatus;
	
	@Column(name="APP_INFO_CHECKER")
	private String appInfoChecher;
	
	@Column(name="APP_INFO_PUBLISHER")
	private String appInfoPublisher;
	
	@Column(name="APP_INFO_BEGINtiME")
	private Date appInfoBeginTime;
	
	@Column(name="APP_INFO_ENDTIME")
	private Date appInfoEndTime;
	
	@Column(name="APP_INFO_TIPS")
	private String appInfoTips;

	@Column(name="DATA_STAT")
	private int dataStat;
	
	@Column(name="APP_INFO_PACKAGE")
	private String appInfoPackage;
	
	
	@Column(name="APP_INFO_CREATER")
	private String appInfoCreater;
	
	@Column(name="APP_INFO_CREATE_TIME")
	private Date appInfoCreateTime;
	
	@Column(name="APP_INFO_CHECK_TIME")
	private Date appInfoCheckTime;
	
	@Column(name="APP_INFO_PUBLISH_TIME")
	private Date appInfoPublishTime;
	
	@Column(name="APP_INFO_CHECK_SUGGEST")
	private String appInfoCheckSuggest;
	
	@Column(name="APP_INFO_FILENAME")
	private String appInfoFilename;
	
	
	
	public String getAppInfoFilename() {
		return appInfoFilename;
	}

	public void setAppInfoFilename(String appInfoFilename) {
		this.appInfoFilename = appInfoFilename;
	}

	@Transient
	public String getAppInfoUpdateTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAppInfoUpdateTime());
	}
	
	@Transient
	public String getAppInfoBeginTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAppInfoBeginTime());
	}
	
	@Transient
	public String getAppInfoEndTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAppInfoEndTime());
	}
	
	@Transient
	public String getAppInfoCreateTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAppInfoCreateTime());
	}
	
	@Transient
	public String getAppInfoCheckTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAppInfoCheckTime());
	}
	
	@Transient
	public String getAppInfoPublishTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAppInfoPublishTime());
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

	public int getAppInfoId() {
		return appInfoId;
	}

	public void setAppInfoId(int appInfoId) {
		this.appInfoId = appInfoId;
	}

	public String getAppInfoVersionCode() {
		return appInfoVersionCode;
	}

	public void setAppInfoVersionCode(String appInfoVersionCode) {
		this.appInfoVersionCode = appInfoVersionCode;
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
//
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
}
