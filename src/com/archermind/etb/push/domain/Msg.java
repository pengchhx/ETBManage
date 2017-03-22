package com.archermind.etb.push.domain;

import java.util.Date;

public class Msg {
	
	private int type;
	
	private String message;
	
	private String device;
	
	private Date excuteDate;

	public Date getExcuteDate() 
	{
		return excuteDate;
	}

	public void setExcuteDate(Date excuteDate)
	{
		this.excuteDate = excuteDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

}
