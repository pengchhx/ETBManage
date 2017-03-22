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
 * 彩票信息采集Domain
 * 
 * @author 003445 张瑞
 * @version 1.0
 * @see java.io.Serializable
 * @time 2013-08-22 10:28
 */
@Entity
@Table(name = "ETB_LOTTERY_COLLECTION")
public class LotteryCollection implements Serializable {

	private static final long serialVersionUID = -4587921340299433288L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "lottery_collection_id")
	private long lotteryCollectionId;

	/** 安全令牌  */
	@Column(name = "client_user_info_token")
	private String clientUserInfoToken;

	/** 终端用户账号  */
	@Column(name = "client_user_info_account")
	private String clientUserInfoAccount;

	/** 彩票流水号ID */
	@Column(name = "lottery_id")
	private String lotteryId;

	/** 彩票类型  */
	@Column(name = "lottery_type")
	private int lotteryType;

	/** 彩票购买时间 */
	@Column(name = "lottery_buying_time")
	private Date lotteryBuyingTime;

	/** 备注说明 */
	@Column(name = "lottery_collection_tips")
	private String lotteryCollectionTips;

	public long getLotteryCollectionId() {
		return lotteryCollectionId;
	}

	public void setLotteryCollectionId(long lotteryCollectionId) {
		this.lotteryCollectionId = lotteryCollectionId;
	}

	public String getClientUserInfoToken() {
		return clientUserInfoToken;
	}

	public void setClientUserInfoToken(String clientUserInfoToken) {
		this.clientUserInfoToken = clientUserInfoToken.trim();
	}

	public String getClientUserInfoAccount() {
		return clientUserInfoAccount;
	}

	public void setClientUserInfoAccount(String clientUserInfoAccount) {
		this.clientUserInfoAccount = clientUserInfoAccount.trim();
	}

	public String getLotteryId() {
		return lotteryId;
	}

	public void setLotteryId(String lotteryId) {
		this.lotteryId = lotteryId.trim();
	}

	public int getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(int lotteryType) {
		this.lotteryType = lotteryType;
	}

	public Date getLotteryBuyingTime() {
		return lotteryBuyingTime;
	}

	public void setLotteryBuyingTime(Date lotteryBuyingTime) {
		this.lotteryBuyingTime = lotteryBuyingTime;
	}

	public String getLotteryCollectionTips() {
		return lotteryCollectionTips;
	}

	public void setLotteryCollectionTips(String lotteryCollectionTips) {
		this.lotteryCollectionTips = lotteryCollectionTips.trim();
	}

	@Override
	public String toString() {
		return "LotteryCollection [lotteryCollectionId=" + lotteryCollectionId
				+ ", clientUserInfoToken=" + clientUserInfoToken
				+ ", clientUserInfoAccount=" + clientUserInfoAccount
				+ ", lotteryId=" + lotteryId + ", lotteryType=" + lotteryType
				+ ", lotteryBuyingTime=" + lotteryBuyingTime
				+ ", lotteryCollectionTips=" + lotteryCollectionTips + "]";
	}

}
