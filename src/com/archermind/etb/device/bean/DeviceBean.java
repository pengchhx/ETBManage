package com.archermind.etb.device.bean;

public class DeviceBean {
	private String imei;
	private String serverIp;
	/**
	 * 0 未激活
	 * 1 在线
	 * 2 离线
	 */
	private int deviceStatus;
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public int getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(int deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
}
