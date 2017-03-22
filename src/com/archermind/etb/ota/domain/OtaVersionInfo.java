package com.archermind.etb.ota.domain;

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

/**
 * ota模块版本的数据库对应实体
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130711
 * @see 
 * @since 1.0
 */
@Entity
@Table(name = "ETB_OTA_VERSION_INFO")
public class OtaVersionInfo implements Serializable{
	
	private static final long serialVersionUID = -638425839442519621L;
	
	@Id
	@Column(name="OTA_VERSION_INFO_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="OTA_VERSION_INFO_NAME",length = 100)
	private String name;
	
	@Column(name="OTA_VERSION_INFO_STAT",length = 11)
	private int infostat;
	
	@Column(name="DATA_STAT",length = 11)
	private int datastat;

	@Column(name="OTA_VERSION_INFO_TIPS",length = 200)
	private String tips;
	
	@Column(name="OTA_VERSION_INFO_CHECK_SUGGEST",length = 1000)
	private String checksuggest;
	
	@Column(name="OTA_VERSION_INFO_CREATER",length = 20)
	private String creater;
	
	@Column(name="OTA_VERSION_INFO_CREATE_TIME")
	private Date createtime;
	
	@Column(name="OTA_VERSION_INFO_CHECKER",length = 20)
	private String checker;
	
	@Column(name="OTA_VERSION_INFO_CHECK_TIME")
	private Date checktime;
	
	@Column(name="OTA_VERSION_INFO_PUBLISHER",length = 20)
	private String publisher;
	
	@Column(name="OTA_VERSION_INFO_PUBLISH_TIME")
	private Date publishtime;
	
	@Column(name="OTA_VERSION_INFO_BUILD_VERSION",length = 10)
	private Integer buildversion;
	
	@Transient
	private String createtimedsp;
	
	@Transient
	private String checktimedsp;
	
	@Transient
	private String publishtimedsp;
	
	
	public String getCreatetimedsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getCreatetime());
	}

	public String getChecktimedsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getChecktime());
	}

	public String getPublishtimedsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getPublishtime());
	}

	public Integer getBuildversion() {
		return buildversion;
	}


	public void setBuildversion(Integer buildversion) {
		this.buildversion = buildversion;
	}


	public Date getPublishtime() {
		return publishtime;
	}


	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}


	public String getPublisher() {
		return publisher;
	}


	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public Date getChecktime() {
		return checktime;
	}


	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}


	public String getChecker() {
		return checker;
	}


	public void setChecker(String checker) {
		this.checker = checker;
	}


	public Date getCreatetime() {
		return createtime;
	}
	

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getChecksuggest() {
		return checksuggest;
	}

	public void setChecksuggest(String checksuggest) {
		this.checksuggest = checksuggest;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public int getInfostat() {
		return infostat;
	}

	public void setInfostat(int infostat) {
		this.infostat = infostat;
	}

	public int getDatastat() {
		return datastat;
	}

	public void setDatastat(int datastat) {
		this.datastat = datastat;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}


}
