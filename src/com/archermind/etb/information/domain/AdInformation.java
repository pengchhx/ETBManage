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
 * @description : 广告日志实体类
 * @author  003468 wenlong.xiao
 * @version v1.0
 * @date 2013年8月16日 下午3:02:22
 */
@Entity
@Table(name = "ETB_AD_COLLECTION")
public class AdInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "AD_COLLECTION_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adCollectionId;

	/** 安全令牌 */
	@Column(name = "CLIENT_USER_INFO_TOKEN")
	private String clientUserInfoToken;

	/** 终端用户 */
	@Column(name = "CLIENT_USER_INFO_ACCOUNT")
	private String clientUserInfoAccount;

	/** 广告编号 */
	@Column(name = "AD_ID")
	private String adId;

	/** 广告内容描述 */
	@Column(name = "AD_COLLECTION_CONTENT")
	private String adCollectionContent;

	/** 广告点击时间 */
	@Column(name = "AD_COLLECTION_CLICKTIME")
	private Date adCollectionClickTime;

	public Integer getAdCollectionId() {
		return adCollectionId;
	}

	public void setAdCollectionId(Integer adCollectionId) {
		this.adCollectionId = adCollectionId;
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

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getAdCollectionContent() {
		return adCollectionContent;
	}

	public void setAdCollectionContent(String adCollectionContent) {
		this.adCollectionContent = adCollectionContent;
	}

	public Date getAdCollectionClickTime() {
		return adCollectionClickTime;
	}

	public void setAdCollectionClickTime(Date adCollectionClickTime) {
		this.adCollectionClickTime = adCollectionClickTime;
	}


}
