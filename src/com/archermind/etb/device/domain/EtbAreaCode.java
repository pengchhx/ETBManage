package com.archermind.etb.device.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "ETB_AREA_CODE")
public class EtbAreaCode implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Expose
	@Column(name = "AREA_ID")
	private Integer areaId;
	
	@Expose
	@Column(name = "AREA_CODE")
	private String areaCode;
	
	@Expose
	@Column(name = "AREA_FIRST_LETTER")
	private String areaFirstLetter;
	
	@Expose
	@Column(name = "AREA_NAME")
	private String areaName;
	
	@Expose
	@Column(name = "AREA_PARENT_CODE")
	private String areaParentCode;
	
	@Expose
	@Column(name = "AREA_DEEP")
	private Integer areaDeep;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaFirstLetter() {
		return areaFirstLetter;
	}

	public void setAreaFirstLetter(String areaFirstLetter) {
		this.areaFirstLetter = areaFirstLetter;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaParentCode() {
		return areaParentCode;
	}

	public void setAreaParentCode(String areaParentCode) {
		this.areaParentCode = areaParentCode;
	}

	public Integer getAreaDeep() {
		return areaDeep;
	}

	public void setAreaDeep(Integer areaDeep) {
		this.areaDeep = areaDeep;
	}
	
}
