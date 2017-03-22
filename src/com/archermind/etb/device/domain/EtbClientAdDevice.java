package com.archermind.etb.device.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ETB_CLIENT_AD_DEVICE")
public class EtbClientAdDevice implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "AD_DEVICE_ID")
	private Integer adDeviceId;
	
	@Column(name = "AD_PACKAGE_ID")
	private String adPackageId;
	
	@Column(name = "CLIENT_DEVICE_IMEI")
	private String clientDeviceImei;
	
	@Column(name = "AD_PACKAGE_BEGINTIME")
	private Date adPackageBegintime;

	@Column(name = "AD_PACKAGE_ENDTIME")
	private Date adPackageEndtime;

	public Integer getAdDeviceId() {
		return adDeviceId;
	}

	public void setAdDeviceId(Integer adDeviceId) {
		this.adDeviceId = adDeviceId;
	}

	public String getAdPackageId() {
		return adPackageId;
	}

	public void setAdPackageId(String adPackageId) {
		this.adPackageId = adPackageId;
	}

	public String getClientDeviceImei() {
		return clientDeviceImei;
	}

	public void setClientDeviceImei(String clientDeviceImei) {
		this.clientDeviceImei = clientDeviceImei;
	}

	public Date getAdPackageBegintime() {
		return adPackageBegintime;
	}

	public void setAdPackageBegintime(Date adPackageBegintime) {
		this.adPackageBegintime = adPackageBegintime;
	}

	public Date getAdPackageEndtime() {
		return adPackageEndtime;
	}

	public void setAdPackageEndtime(Date adPackageEndtime) {
		this.adPackageEndtime = adPackageEndtime;
	}
	
}
