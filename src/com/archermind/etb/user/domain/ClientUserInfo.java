package com.archermind.etb.user.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.archermind.etb.device.domain.EtbClientDevice;


/**
 * 
 * @author 002525 陈洋
 * @version 1.0
 * @see java.io.Serializable
 * @Time 2013-8-2-10:31:00
 */
@Entity
@Table(name = "ETB_CLIENT_USER_INFO")
public class ClientUserInfo implements Serializable {

	private static final long serialVersionUID = 5289482955356407708L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_user_info_id")
	private long clientUserInfoId;
	
	//临时字段
	@Transient
	private String clientUserInfoDeviceImei;
	
	@Column(name = "client_user_info_name")
	private String clientUserInfoName;
	
	@Column(name = "client_user_info_sex")
	private String clientUserInfoSex;
	
	@Column(name = "client_user_info_tel")
	private String clientUserInfoTel;
	
	@Column(name = "client_user_info_identity")
	private String clientUserInfoIdentity;
	
	@Column(name = "client_user_info_pro")
	private String clientUserInfoPro;
	
	@Column(name = "client_user_info_city")
	private String clientUserInfoCity;
	
	@Column(name = "client_user_info_address")
	private String clientUserInfoAddress;
	
	@Column(name = "client_user_info_account")
	private String clientUserInfoAccount;
	
	@Column(name = "client_user_info_pwd")
	private String clientUserInfoPwd;
	
	@Transient
	private String reClientUserInfoPwd;
	
	@Column(name = "client_user_info_create_time")
	private String clientUserInfoCreateTime;
	
	@Column(name = "data_stat")
	private int dataStat;
	
	@Column(name = "client_user_info_tips")
	private String clientUserInfoTips;

	@Transient
	private String isEditPassWord;
	
	/**  建立"终端用户"与"终端设备"的多对多映射   */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ETB_CLIENT_USER_DEVICE",
			   joinColumns={@JoinColumn(name="CLIENT_USER_INFO_ID")},
			   inverseJoinColumns={@JoinColumn(name="CLIENT_DEVICE_IMEI")})
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<EtbClientDevice> etbClientDeviceList;
	
	public long getClientUserInfoId() {
		return clientUserInfoId;
	}

	public void setClientUserInfoId(long clientUserInfoId) {
		this.clientUserInfoId = clientUserInfoId;
	}

	public String getClientUserInfoDeviceImei() {
		return clientUserInfoDeviceImei;
	}

	public void setClientUserInfoDeviceImei(String clientUserInfoDeviceImei) {
		this.clientUserInfoDeviceImei = clientUserInfoDeviceImei.trim();
	}
    
	public String getClientUserInfoName() {
		return clientUserInfoName;
	}

	public void setClientUserInfoName(String clientUserInfoName) {
		this.clientUserInfoName = clientUserInfoName.trim();
	}

	public String getClientUserInfoSex() {
		return clientUserInfoSex;
	}

	public void setClientUserInfoSex(String clientUserInfoSex) {
		this.clientUserInfoSex = clientUserInfoSex.trim();
	}

	public String getClientUserInfoTel() {
		return clientUserInfoTel;
	}

	public void setClientUserInfoTel(String clientUserInfoTel) {
		this.clientUserInfoTel = clientUserInfoTel.trim();
	}

	public String getClientUserInfoIdentity() {
		return clientUserInfoIdentity;
	}

	public void setClientUserInfoIdentity(String clientUserInfoIdentity) {
		this.clientUserInfoIdentity = clientUserInfoIdentity.trim();
	}

	public String getClientUserInfoPro() {
		return clientUserInfoPro;
	}

	public void setClientUserInfoPro(String clientUserInfoPro) {
		this.clientUserInfoPro = clientUserInfoPro.trim();
	}

	public String getClientUserInfoCity() {
		return clientUserInfoCity;
	}

	public void setClientUserInfoCity(String clientUserInfoCity) {
		this.clientUserInfoCity = clientUserInfoCity.trim();
	}

	public String getIsEditPassWord() {
		return isEditPassWord;
	}

	public void setIsEditPassWord(String isEditPassWord) {
		this.isEditPassWord = isEditPassWord.trim();
	}

	public String getClientUserInfoAddress() {
		return clientUserInfoAddress;
	}

	public void setClientUserInfoAddress(String clientUserInfoAddress) {
		this.clientUserInfoAddress = clientUserInfoAddress.trim();
	}

	public String getClientUserInfoAccount() {
		return clientUserInfoAccount;
	}

	public void setClientUserInfoAccount(String clientUserInfoAccount) {
		this.clientUserInfoAccount = clientUserInfoAccount.trim();
	}

	public String getClientUserInfoPwd() {
		return clientUserInfoPwd;
	}

	public void setClientUserInfoPwd(String clientUserInfoPwd) {
		this.clientUserInfoPwd = clientUserInfoPwd.trim();
	}

	public String getReClientUserInfoPwd() {
		return reClientUserInfoPwd;
	}

	public void setReClientUserInfoPwd(String reClientUserInfoPwd) {
		this.reClientUserInfoPwd = reClientUserInfoPwd.trim();
	}

	public String getClientUserInfoCreateTime() {
		return clientUserInfoCreateTime;
	}

	public void setClientUserInfoCreateTime(String clientUserInfoCreateTime) {
		this.clientUserInfoCreateTime = clientUserInfoCreateTime.trim();
	}

	public int getDataStat() {
		return dataStat;
	}

	public void setDataStat(int dataStat) {
		this.dataStat = dataStat;
	}

	public String getClientUserInfoTips() {
		return clientUserInfoTips;
	}

	public void setClientUserInfoTips(String clientUserInfoTips) {
		this.clientUserInfoTips = clientUserInfoTips.trim();
	}

	public Set<EtbClientDevice> getEtbClientDeviceList() {
		return etbClientDeviceList;
	}

	public void setEtbClientDeviceList(Set<EtbClientDevice> etbClientDeviceList) {
		this.etbClientDeviceList = etbClientDeviceList;
	}

	@Override
	public String toString() {
		return "ClientUserInfo [clientUserInfoId=" + clientUserInfoId
				+ ", clientUserInfoName=" + clientUserInfoName
				+ ", clientUserInfoSex=" + clientUserInfoSex
				+ ", clientUserInfoTel=" + clientUserInfoTel
				+ ", clientUserInfoIdentity=" + clientUserInfoIdentity
				+ ", clientUserInfoPro=" + clientUserInfoPro
				+ ", clientUserInfoCity=" + clientUserInfoCity
				+ ", clientUserInfoAddress=" + clientUserInfoAddress
				+ ", clientUserInfoAccount=" + clientUserInfoAccount
				+ ", clientUserInfoPwd=" + clientUserInfoPwd
				+ ", reClientUserInfoPwd=" + reClientUserInfoPwd
				+ ", clientUserInfoCreateTime=" + clientUserInfoCreateTime
				+ ", etbClientDeviceList=" + etbClientDeviceList
				+ ", dataStat=" + dataStat + ", clientUserInfoTips="
				+ clientUserInfoTips + "]";
	}

	
}
