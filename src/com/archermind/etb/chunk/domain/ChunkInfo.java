package com.archermind.etb.chunk.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.archermind.etb.util.DateUtil;

/**
 * ChunkServer信息实体
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @since 1.0
 */
@Entity
@Table(name="ETB_CHUNK")
public class ChunkInfo implements Serializable {
	
	private static final long serialVersionUID = -8591567811561118012L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "CHUNK_ID")
	private int chunkId;
	
	@Column(name = "DEVICE_ID")
	private int deviceId;
	
	@Column(name="CHUNK_NAME")
	private String chunkName;
	
	@Column(name="CHUNK_URL")
	private String chunkUrl;
	
	@Column(name="RESERVE_URL")
	private String reserveUrl;

	@Column(name="DATA_STAT")
	private int dataStat;
	
	@Column(name="CHUNK_CREATE_TIME")
	private Date chunkCreateTime;
	
	@Column(name="CHUNK_CREATE_USER")
	private String chunkCreateUser;
	
	@Column(name="CHUNK_UPDATE_TIME")
	private Date chunkUpdateTime;
	
	@Column(name="CHUNK_UPDATE_USER")
	private String chunkUpdateUser;
	
	@Column(name="CHUNK_PUSH_TIME")
	private Date chunkPushTime;
	
	@Column(name="STATUS")
	private int status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getReserveUrl() {
		return reserveUrl;
	}

	public void setReserveUrl(String reserveUrl) {
		this.reserveUrl = reserveUrl;
	}

	public int getChunkId() {
		return chunkId;
	}

	public void setChunkId(int chunkId) {
		this.chunkId = chunkId;
	}

	public String getChunkName() {
		return chunkName;
	}

	public void setChunkName(String chunkName) {
		this.chunkName = chunkName;
	}


	public String getChunkUrl() {
		return chunkUrl;
	}

	public void setChunkUrl(String chunkUrl) {
		this.chunkUrl = chunkUrl;
	}

	public int getDataStat() {
		return dataStat;
	}

	public void setDataStat(int dataStat) {
		this.dataStat = dataStat;
	}

	public Date getChunkCreateTime() {
		return chunkCreateTime;
	}

	public void setChunkCreateTime(Date chunkCreateTime) {
		this.chunkCreateTime = chunkCreateTime;
	}

	public String getChunkCreateUser() {
		return chunkCreateUser;
	}

	public void setChunkCreateUser(String chunkCreateUser) {
		this.chunkCreateUser = chunkCreateUser;
	}

	public Date getChunkUpdateTime() {
		return chunkUpdateTime;
	}

	public void setChunkUpdateTime(Date chunkUpdateTime) {
		this.chunkUpdateTime = chunkUpdateTime;
	}

	public String getChunkUpdateUser() {
		return chunkUpdateUser;
	}

	public void setChunkUpdateUser(String chunkUpdateUser) {
		this.chunkUpdateUser = chunkUpdateUser;
	}
	
	public Date getChunkPushTime() {
		return chunkPushTime;
	}

	public void setChunkPushTime(Date chunkPushTime) {
		this.chunkPushTime = chunkPushTime;
	}

	@Transient
	public String getChunkCreateTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getChunkCreateTime());
	}
	
	@Transient
	public String getChunkUpdateTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getChunkUpdateTime());
	}
	
	@Transient
	public String getChunkPushTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getChunkPushTime());
	}
	
}
