package com.archermind.etb.device.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "ETB_CLIENT_DEVICE_VOLUME")
public class EtbClientDeviceVolume {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CLIENT_DEVICE_VOLUME_ID")
	private long id;



	//音量
	@Column(name = "CLIENT_DEVICE_VOLUME_VALUE")
	private int value;


	@Column(name = "DATA_STAT")
	private int stat;
	
	
	@Column(name = "CLIENT_DEVICE_VOLUME_OPEN_HOUR")
	private String openHour;
	
	@Column(name = "CLIENT_DEVICE_VOLUME_OPEN_MIN")
	private String openMin;

	@Column(name = "CLIENT_DEVICE_VOLUME_CLOSE_HOUR")
	private String closeHour;

	@Column(name = "CLIENT_DEVICE_VOLUME_CLOSE_MIN")
	private String closeMin;

	
	
	
	/** 建立"设备音量"与"设备"的多对一映射 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CLIENT_DEVICE_ID",nullable=true)
	@NotFound(action = NotFoundAction.IGNORE)
	private EtbClientDevice clientDeviceByVolume;



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}




	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public int getStat() {
		return stat;
	}


	public void setStat(int stat) {
		this.stat = stat;
	}


	public EtbClientDevice getClientDeviceByVolume() {
		return clientDeviceByVolume;
	}


	public void setClientDeviceByVolume(EtbClientDevice clientDeviceByVolume) {
		this.clientDeviceByVolume = clientDeviceByVolume;
	}


	public String getOpenHour() {
		return openHour;
	}


	public void setOpenHour(String openHour) {
		this.openHour = openHour;
	}


	public String getOpenMin() {
		return openMin;
	}


	public void setOpenMin(String openMin) {
		this.openMin = openMin;
	}


	public String getCloseHour() {
		return closeHour;
	}


	public void setCloseHour(String closeHour) {
		this.closeHour = closeHour;
	}


	public String getCloseMin() {
		return closeMin;
	}


	public void setCloseMin(String closeMin) {
		this.closeMin = closeMin;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
