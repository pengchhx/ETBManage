package com.archermind.etb.device.domain;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 终端设备系统配置实体层
 * 
 * @author 001667
 * @version 2.0
 * @since 1.0
 */
@Entity
@Table(name = "ETB_DEVICE_SYSTEM_CONFIG")
public class EtbClientDeviceConfig {
	@Id
	@Column(name = "DEVICE_SYSTEM_ID")
	private int id;

	@Column(name = "DEVICE_SYSTEM_LOCKTIME")
	private int lockTime;

	@Column(name = "DEVICE_SYSTEM_STARTTIME")
	private Time startTime;

	@Column(name = "DEVICE_SYSTEM_ENDTIME")
	private Time endTime;

	@Column(name = "DATA_STAT")
	private int stat;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLockTime() {
		return lockTime;
	}

	public void setLockTime(int lockTime) {
		this.lockTime = lockTime;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}
}
