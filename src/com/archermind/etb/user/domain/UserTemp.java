package com.archermind.etb.user.domain;

import java.util.List;
import java.util.Set;


public class UserTemp {

	private long userInfoId;

	private String userInfoUsername;

	private String userInfoTel;

	private String userInfoName;

	private String userInfoTips;
	
	private List<String> powerList;

	
	private String hasEditPower="";
	
	private String hasCheckPower="";
	
	private String hasDevicePower="";
	
	

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
		this.userInfoUsername = userInfoUsername;
	}



	public String getUserInfoTel() {
		return userInfoTel;
	}


	public void setUserInfoTel(String userInfoTel) {
		this.userInfoTel = userInfoTel;
	}


	public String getUserInfoName() {
		return userInfoName;
	}


	public void setUserInfoName(String userInfoName) {
		this.userInfoName = userInfoName;
	}


	public String getUserInfoTips() {
		return userInfoTips;
	}


	public void setUserInfoTips(String userInfoTips) {
		this.userInfoTips = userInfoTips;
	}


	public List<String> getPowerList() {
		return powerList;
	}


	public void setPowerList(List<String> powerList) {
		this.powerList = powerList;
	}


	public String getHasEditPower() {
		return hasEditPower;
	}


	public void setHasEditPower(String hasEditPower) {
		this.hasEditPower = hasEditPower;
	}


	public String getHasCheckPower() {
		return hasCheckPower;
	}


	public void setHasCheckPower(String hasCheckPower) {
		this.hasCheckPower = hasCheckPower;
	}


	public String getHasDevicePower() {
		return hasDevicePower;
	}


	public void setHasDevicePower(String hasDevicePower) {
		this.hasDevicePower = hasDevicePower;
	}




	
	
	
	
}
