package com.archermind.etb.information.domain;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @description : 异常信息采集
 * @author  003468 wenlong.xiao
 * @version v1.0
 * @date 2013年9月12日 上午9:57:28
 */
@Entity
@Table(name="ETB_EXCEPTION_COLLECTION")
public class ExceptionInfo {

	@Id
	@Column(name="EXCEPTION_COLLECTION_ID")
	private int exceptionCollectionId;
	
	/**
	 * 安全令牌
	 */
	@Column(name="CLIENT_USER_INFO_TOKEN")
	private String clientUserToken;
	
	/**
	 * 用户名
	 */
	@Column(name="CLIENT_USER_INFO_ACCOUNT")
	private String clientUserAccount;
	
	/**
	 * 异常编号
	 */
	@Column(name="EXCEPTION_ID")
	private String exceptionId;
	
	/**
	 * 异常版本
	 */
	@Column(name="EXCEPTION_VERSION")
	private String exceptionVersion;
	
	/**
	 * 失败操作时间
	 */
	@Column(name="EXCEPTION_TIME")
	private  Date  exceptionTime;
	
	/**
	 * 操作类型(缴费，彩票)
	 */
	@Column(name="EXCEPTION_TYPE")
	private int exceptionType;
	
	/**
	 * 操作类型
	 */
	@Transient
	private String exceptionInfoType;
	/**
	 * 异常原因
	 */
	@Column(name="EXCEPTION_REASON")
	private String exceptionReason;
	
	/**
	 * 异常名称
	 */
	@Column(name="EXCEPTION_NAME")
	private String exceptionName;
     
	@Transient
	private String time;
	
	@Transient
	private int type;
	
	public int getExceptionCollectionId() {
		return exceptionCollectionId;
	}

	public void setExceptionCollectionId(int exceptionCollectionId) {
		this.exceptionCollectionId = exceptionCollectionId;
	}

	public String getClientUserToken() {
		return clientUserToken;
	}

	public void setClientUserToken(String clientUserToken) {
		this.clientUserToken = clientUserToken;
	}

	public String getClientUserAccount() {
		return clientUserAccount;
	}

	public void setClientUserAccount(String clientUserAccount) {
		this.clientUserAccount = clientUserAccount;
	}

	public String getExceptionId() {
		return exceptionId;
	}

	public void setExceptionId(String exceptionId) {
		this.exceptionId = exceptionId;
	}

	public String getExceptionVersion() {
		return exceptionVersion;
	}

	public void setExceptionVersion(String exceptionVersion) {
		this.exceptionVersion = exceptionVersion;
	}

	public Date getExceptionTime() {
		return exceptionTime;
	}

	public void setExceptionTime(Date exceptionTime) {
		this.exceptionTime = exceptionTime;
	}

	public int getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(int exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getExceptionReason() {
		return exceptionReason;
	}

	public void setExceptionReason(String exceptionReason) {
		this.exceptionReason = exceptionReason;
	}

	public String getExceptionName() {
		return exceptionName;
	}

	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	public String getExceptionInfoType() {
		return exceptionInfoType;
	}

	public void setExceptionInfoType(String exceptionInfoType) {
		this.exceptionInfoType = exceptionInfoType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
