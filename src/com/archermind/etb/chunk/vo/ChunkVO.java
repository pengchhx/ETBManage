package com.archermind.etb.chunk.vo;

import java.util.Date;

import com.google.gson.annotations.Expose;

/**
 * ChunckServer视图层对象实体
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @since 1.0
 */
public class ChunkVO {
	//chunk服务器ID
	@Expose
	private int chunkId;
	//设备ID
	@Expose
	private int deviceId;
	//chunk服务器名称
	@Expose
	private String chunkName;
	//chunk服务器地址
	@Expose
	private String chunkUrl;
	//chunk服务器备用地址
	@Expose
	private String reserveUrl;
	//chunk服务器删除标识  0 未删除   1 已删除
	@Expose
	private int dataStat;
	//chunk服务器创建时间
	@Expose
	private Date chunkCreateTime;
	//chunk服务器创建人
	@Expose
	private String chunkCreateUser;
	//chunk服务器修改时间
	@Expose
	private Date chunkUpdateTime;
	//chunk服务器修改人
	@Expose
	private String chunkUpdateUser;
	//chunk服务器ID修改时间显示描述
	@Expose
	private String chunkUpdateTimeDsp;
	//chunk服务器ID创建时间显示描述
	@Expose
	private String chunkCreateTimeDsp;
	//状态  1-运行    2-空闲   3-暂停
	@Expose
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

	public String getChunkUpdateTimeDsp() {
		return chunkUpdateTimeDsp;
	}

	public void setChunkUpdateTimeDsp(String chunkUpdateTimeDsp) {
		this.chunkUpdateTimeDsp = chunkUpdateTimeDsp;
	}

	public String getChunkCreateTimeDsp() {
		return chunkCreateTimeDsp;
	}

	public void setChunkCreateTimeDsp(String chunkCreateTimeDsp) {
		this.chunkCreateTimeDsp = chunkCreateTimeDsp;
	}
	
	
}
