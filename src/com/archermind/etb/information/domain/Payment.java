package com.archermind.etb.information.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description : 缴费记录实体类
 * @author 003468 wenlong.xiao
 * @version v1.0
 * @date 2013年8月16日 下午3:03:00
 */
@Entity
@Table(name = "ETB_PAYMENT_COLLECTION")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PAYMENT_COLLECTION_ID")
	private Integer paymentCollectionId;

	/** 安全令牌 */
	@Column(name = "CLIENT_USER_INFO_TOKEN")
	private String clientUserInfoToken;

	/** 用户名 */
	@Column(name = "CLIENT_USER_INFO_ACCOUNT")
	private String clientUserInfoAccount;

	/** 缴费的流水ID号 */
	@Column(name = "PAYMENT_ID")
	private String paymentId;

	/** 缴费类型 1=水，2=电，3=煤气 */
	@Column(name = "PAYMENT_TYPE")
	private int paymentType;

	/** 缴费原因 */
	@Column(name = "PAYMENT_REASON")
	private String paymentReason;

	/** 缴费时间 */
	@Column(name = "PAYMENT_TIME")
	private Date paymentTime;

	/** 备注说明 */
	@Column(name = "PAYMENT_COLLECTION_TIPS")
	private String paymentCollectionTips;

	public Integer getPaymentCollectionId() {
		return paymentCollectionId;
	}

	public void setPaymentCollectionId(Integer paymentCollectionId) {
		this.paymentCollectionId = paymentCollectionId;
	}

	public String getClientUserInfoToken() {
		return clientUserInfoToken;
	}

	public void setClientUserInfoToken(String clientUserInfoToken) {
		this.clientUserInfoToken = clientUserInfoToken;
	}

	public String getClientUserInfoAccount() {
		return clientUserInfoAccount;
	}

	public void setClientUserInfoAccount(String clientUserInfoAccount) {
		this.clientUserInfoAccount = clientUserInfoAccount;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentReason() {
		return paymentReason;
	}

	public void setPaymentReason(String paymentReason) {
		this.paymentReason = paymentReason;
	}


	public String getPaymentCollectionTips() {
		return paymentCollectionTips;
	}

	public void setPaymentCollectionTips(String paymentCollectionTips) {
		this.paymentCollectionTips = paymentCollectionTips;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}



}
