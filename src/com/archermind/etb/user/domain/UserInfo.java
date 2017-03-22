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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



/**
 * 
 * @author 
 * @version 
 * @see 
 * @Time 
 */
@Entity
@Table(name = "ETB_USER_INFO")
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = -8591567811561118012L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_INFO_ID")
	private long userInfoId;

	@Column(name = "USER_INFO_USERNAME", nullable = false,unique = true)
	private String userInfoUsername;

	@Column(name = "USER_INFO_PWD", nullable = false)
	private String userInfoPwd;

	@Transient
	private String reUserInfoPwd;

	@Column(name = "USER_INFO_TEL")
	private String userInfoTel;

	@Column(name = "USER_INFO_NAME", nullable = false)
	private String userInfoName;

	@Column(name = "USER_INFO_TIPS")
	private String userInfoTips;
	
	@Column(name = "DATA_STAT")
	private int dataStat;

	@Column(name = "USER_INFO_SEX")
	private int userInfoSex;
	
	
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="USER_INFO_COMPANYID",nullable=true,updatable=false)
	@NotFound(action = NotFoundAction.IGNORE)
	private CompanyInfo companyInfo;
	
	

	@ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
	@JoinTable(name = "ETB_USER_POWER",
	joinColumns={@JoinColumn(name="USER_INFO_ID")},
	inverseJoinColumns={@JoinColumn(name="POWER_INFO_ID")})
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<PowerInfo> powerInfoList;
	
	@Transient
	private String isEditPassWord;
	
	public String getIsEditPassWord() {
		return isEditPassWord;
	}

	public void setIsEditPassWord(String isEditPassWord) {
		this.isEditPassWord = isEditPassWord.trim();
	}

	public long getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(long userInfoId) {
		this.userInfoId = userInfoId;
	}

	public String getUserInfoUsername() {
		return userInfoUsername;
	}

	public void setUserInfoUsername(String userInfoUsername) {
		this.userInfoUsername = userInfoUsername.trim();
	}

	public String getUserInfoPwd() {
		return userInfoPwd;
	}

	public void setUserInfoPwd(String userInfoPwd) {
		this.userInfoPwd = userInfoPwd.trim();
	}

	public String getReUserInfoPwd() {
		return reUserInfoPwd;
	}

	public void setReUserInfoPwd(String reUserInfoPwd) {
		this.reUserInfoPwd = reUserInfoPwd.trim();
	}

	public String getUserInfoTel() {
		return userInfoTel;
	}

	public void setUserInfoTel(String userInfoTel) {
		this.userInfoTel = userInfoTel.trim();
	}

	public String getUserInfoName() {
		return userInfoName;
	}

	public void setUserInfoName(String userInfoName) {
		this.userInfoName = userInfoName.trim();
	}

	public String getUserInfoTips() {
		return userInfoTips;
	}

	public void setUserInfoTips(String userInfoTips) {
		this.userInfoTips = userInfoTips.trim();
	}

	public int getDataStat() {
		return dataStat;
	}

	public void setDataStat(int dataStat) {
		this.dataStat = dataStat;
	}
	
	public Set<PowerInfo> getPowerInfoList() {
		return powerInfoList;
	}

	public void setPowerInfoList(Set<PowerInfo> powerInfoList) {
		this.powerInfoList = powerInfoList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getUserInfoSex() {
		return userInfoSex;
	}

	public void setUserInfoSex(int userInfoSex) {
		this.userInfoSex = userInfoSex;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	@Override
	public String toString() {
		return "UserInfo [userInfoId=" + userInfoId + ", userInfoUsername="
				+ userInfoUsername + ", userInfoPwd=" + userInfoPwd
				+ ", reUserInfoPwd=" + reUserInfoPwd + ", userInfoTel="
				+ userInfoTel + ", userInfoName=" + userInfoName
				+ ", userInfoTips=" + userInfoTips + "]";
	}

//----------------------------------------------------------------------------------------------------------


}
