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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


/**
 * 
 * 权限表
 * @author 
 * @version 
 * @see 
 * @Time 
 */

@Entity
@Table(name = "ETB_POWER_INFO")
public class PowerInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "POWER_INFO_ID")
	private long powerInfoId;
	
	@Column(name = "POWER_INFO_NAME")
	private String powerInfoName;
	
	@Column(name = "DATA_STAT")
	private int dataStat;
	
	@Column(name = "POWER_INFO_TIPS")
	private String powerInfoTips;

	@ManyToMany(mappedBy="powerInfoList",fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<UserInfo> userInfoList;
	
	
	
	
	
	
	public Set<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(Set<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public long getPowerInfoId() {
		return powerInfoId;
	}

	public void setPowerInfoId(long powerInfoId) {
		this.powerInfoId = powerInfoId;
	}

	public String getPowerInfoName() {
		return powerInfoName;
	}

	public void setPowerInfoName(String powerInfoName) {
		this.powerInfoName = powerInfoName;
	}

	public int getDataStat() {
		return dataStat;
	}

	public void setDataStat(int dataStat) {
		this.dataStat = dataStat;
	}

	
	
	
	
	
	
	

}
