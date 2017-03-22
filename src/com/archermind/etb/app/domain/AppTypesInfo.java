package com.archermind.etb.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ETB_APP_TYPES")
public class AppTypesInfo implements Serializable {
	
	private static final long serialVersionUID = -8591567811561118012L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "APP_TYPES_ID")
	private int appTypesId;
	
	@Column(name="APP_TYPES_NAME")
	private String appTypesName;
	
	@Column(name="APP_TYPES_TIPS")
	private String appTypesTips;

	@Column(name="DATA_STAT")
	private int dataStat;
	
	@Column(name="APP_TYPES_EN_NAME")
	private String appTypesEnName;
	
	@Column(name="APP_TYPES_URL")
	private String appTypesUrl;
	
	@Column(name="APP_TYPES_FILENAME")
	private String appTypesFileName;
	
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
