package com.archermind.etb.user.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.archermind.etb.device.domain.EtbClientDevice;

/**
 * 
 * @author 
 * @version 
 * @see 
 * @Time 
 */
@Entity
@Table(name = "ETB_COMPANY_INFO")
public class CompanyInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMPANY_INFO_ID")
	private long companyInfoId;

	@Column(name = "COMPANY_INFO_NAME")
	private String companyInfoName;

	@Column(name = "COMPANY_INFO_ADMINNAME")
	private String companyInfoAdminName;

	@Column(name = "COMPANY_INFO_PASSWORD")
	private String companyInfoPassword;

	@Column(name = "COMPANY_INFO_AREA_ID")
	private int companyInfoAreaId;

	@Column(name = "COMPANY_INFO_ADDRESS")
	private String companyInfoAddress;

	@Column(name = "COMPANY_INFO_EMAIL")
	private String companyInfoEmail;
	
	@Column(name = "COMPANY_INFO_TEL")
	private String companyInfoTel;
	
	@Column(name = "DATA_STAT")
	private int dataStat;
	
	@Transient
	private String province;
	
	@Transient
	private String city;
	
	
	
	@OneToMany(mappedBy="companyInfo",fetch = FetchType.EAGER)
	private Set<UserInfo> userInfoList;
	
	
	@OneToMany(mappedBy="company",fetch = FetchType.EAGER)
	private Set<EtbClientDevice> deviceList;


	public long getCompanyInfoId() {
		return companyInfoId;
	}

	public void setCompanyInfoId(long companyInfoId) {
		this.companyInfoId = companyInfoId;
	}

	public String getCompanyInfoName() {
		return companyInfoName;
	}

	public void setCompanyInfoName(String companyInfoName) {
		this.companyInfoName = companyInfoName;
	}

	public String getCompanyInfoAdminName() {
		return companyInfoAdminName;
	}

	public void setCompanyInfoAdminName(String companyInfoAdminName) {
		this.companyInfoAdminName = companyInfoAdminName;
	}

	public String getCompanyInfoPassword() {
		return companyInfoPassword;
	}

	public void setCompanyInfoPassword(String companyInfoPassword) {
		this.companyInfoPassword = companyInfoPassword;
	}

	public int getCompanyInfoAreaId() {
		return companyInfoAreaId;
	}

	public void setCompanyInfoAreaId(int companyInfoAreaId) {
		this.companyInfoAreaId = companyInfoAreaId;
	}

	public String getCompanyInfoEmail() {
		return companyInfoEmail;
	}

	public void setCompanyInfoEmail(String companyInfoEmail) {
		this.companyInfoEmail = companyInfoEmail;
	}

	public String getCompanyInfoAddress() {
		return companyInfoAddress;
	}

	public void setCompanyInfoAddress(String companyInfoAddress) {
		this.companyInfoAddress = companyInfoAddress;
	}
	public int getDataStat() {
		return dataStat;
	}

	public void setDataStat(int dataStat) {
		this.dataStat = dataStat;
	}

	public String getCompanyInfoTel() {
		return companyInfoTel;
	}

	public void setCompanyInfoTel(String companyInfoTel) {
		this.companyInfoTel = companyInfoTel;
	}

	public Set<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(Set<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public Set<EtbClientDevice> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(Set<EtbClientDevice> deviceList) {
		this.deviceList = deviceList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	
	
	
	
	

}
