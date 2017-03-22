package com.archermind.etb.device.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 服务器端设备实体层
 * 
 * @author 001667
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "ETB_SERVER_DEVICE")
public class EtbServerDevice {
	@Id
	@Column(name = "SERVER_DEVICE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "SERVER_DEVICE_NAME")
	private String name;

	@Column(name = "SERVER_DEVICE_TYPE")
	private String type;

	@Column(name = "SERVER_DEVICE_MAC_ADDR")
	private String addr;

	@Column(name = "SERVER_DEVICE_IP")
	private String ip;

	@Column(name = "SERVER_DEVICE_STATUS")
	private Integer status;

	@Column(name = "SERVER_DEVICE_TIPS")
	private String tips;

	@Column(name = "DATA_STAT")
	private int stat;

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

}
