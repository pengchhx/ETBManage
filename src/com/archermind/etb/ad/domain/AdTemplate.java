package com.archermind.etb.ad.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ETB_AD_TEMPLATE")
public class AdTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AD_TEMPLATE_ID")
	private long adTemplateId;

	@Column(name = "AD_TEMPLATE_NAME", length = 64)
	private String adTemplateName;

	@Column(name = "AD_TEMPLATE_THUMB", length = 200)
	private String adTemplateThumb;

	@Column(name = "AD_TEMPLATE_PATH", length = 200)
	private String adTemplatePath;
	
	/** 广告模板类型：1-普通广告；2-应用广告；3-紧急广告； */
	/** 广告模板类型：1-横版广告；2-竖版广告；3-滚动文字；*/
	@Column(name = "AD_TEMPLATE_TYPE")
	private int adTemplateType;
	
	@Column(name = "AD_TEMPLATE_RESOLUTION")
	private String adTemplateResolution;
	
	@Column(name = "AD_TEMPLATE_AREA_COUNT")
	private int adTemplateAreaCount;

	@Column(name = "AD_TEMPLATE_TIME")
	private Date adTemplateTime;

	@Column(name = "AD_TEMPLATE_CREATER", length = 20)
	private String adTemplateCreater;

	/** 1:已删除；0：未删除 */
	@Column(name = "DATA_STAT")
	private long dataStat = 0;

	@Column(name = "AD_TEMPLATE_TIPS", length = 200)
	private String adTemplateTips;

	public long getAdTemplateId() {
		return adTemplateId;
	}

	public void setAdTemplateId(long adTemplateId) {
		this.adTemplateId = adTemplateId;
	}

	public String getAdTemplateName() {
		return adTemplateName;
	}

	public void setAdTemplateName(String adTemplateName) {
		this.adTemplateName = adTemplateName;
	}

	public String getAdTemplateThumb() {
		return adTemplateThumb;
	}

	public void setAdTemplateThumb(String adTemplateThumb) {
		this.adTemplateThumb = adTemplateThumb;
	}

	public Date getAdTemplateTime() {
		return adTemplateTime;
	}

	public void setAdTemplateTime(Date adTemplateTime) {
		this.adTemplateTime = adTemplateTime;
	}

	public String getAdTemplateCreater() {
		return adTemplateCreater;
	}

	public void setAdTemplateCreater(String adTemplateCreater) {
		this.adTemplateCreater = adTemplateCreater;
	}

	public String getAdTemplateTips() {
		return adTemplateTips;
	}

	public void setAdTemplateTips(String adTemplateTips) {
		this.adTemplateTips = adTemplateTips;
	}

	public String getAdTemplatePath() {
		return adTemplatePath;
	}

	public void setAdTemplatePath(String adTemplatePath) {
		this.adTemplatePath = adTemplatePath;
	}

	public String getAdTemplateResolution() {
		return adTemplateResolution;
	}

	public void setAdTemplateResolution(String adTemplateResolution) {
		this.adTemplateResolution = adTemplateResolution;
	}

	public int getAdTemplateAreaCount() {
		return adTemplateAreaCount;
	}

	public void setAdTemplateAreaCount(int adTemplateAreaCount) {
		this.adTemplateAreaCount = adTemplateAreaCount;
	}

	public long getDataStat() {
		return dataStat;
	}

	public void setDataStat(long dataStat) {
		this.dataStat = dataStat;
	}

	public int getAdTemplateType() {
		return adTemplateType;
	}

	public void setAdTemplateType(int adTemplateType) {
		this.adTemplateType = adTemplateType;
	}

}
