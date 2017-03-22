package com.archermind.etb.device.domain;

/**
 * @description : 系统配置推送
 * @author  003468 wenlong.xiao
 * @version v1.0
 * @date 2013年9月11日 上午9:04:38
 */
public class ClientSysInfo {

    private int type;
	
	private int action;
	
	/**
	 * 系统时间
	 */
	private long systime;
	
	/**
	 * 系统待机时间(单位秒)
	 */
	private long locktimes;
	
	/**
	 * 系统使用时间(时间段)
	 */
	private String usetime;

	public long getSystime() {
		return systime;
	}

	public void setSystime(long systime) {
		this.systime = systime;
	}

	public long getLocktimes() {
		return locktimes;
	}

	public void setLocktimes(long locktimes) {
		this.locktimes = locktimes;
	}

	public String getUsetime() {
		return usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
	
	
}
