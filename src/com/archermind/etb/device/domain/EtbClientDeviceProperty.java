package com.archermind.etb.device.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ETB_CLIENT_DEVICE_PROPERTY")
public class EtbClientDeviceProperty {

	/**
	 * 
	 * @author 
	 * @version 
	 * @see 
	 * @Time 
	 */

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "CLIENT_DEVICE_PROPERTY_ID")
		private long id;
		
		//分辨率
		@Column(name = "CLIENT_DEVICE_RESOLUTION",updatable=false)
		private String resolution;
		
		
//		//状态 1 正常开启 2当天未登录 3 多天未登录
//		@Column(name = "CLIENT_DEVICE_STATUS")
//		private int deviceStatus;
//	
		//音量
//		@Column(name = "CLIENT_DEVICE_VOLUME_VALUE")
//		private int volumeValue;
		
		//语言  cn 中文  en 英文
		@Column(name = "CLIENT_DEVICE_LANGUAGE")
		private String language;
	
		//时间显示方式  top  down left right close
		@Column(name = "CLIENT_DEVICE_TIME_SHOW")
		private String timeShow;
	
		//色彩
		@Column(name = "CLIENT_DEVICE_COLOR")
		private int color;
		
		//亮度
		@Column(name = "CLIENT_DEVICE_BRIGHTNESS")
		private int brightness;
		
		//对比度
		@Column(name = "CLIENT_DEVICE_CONTRAST")
		private int contrast;
		
		//色调
		@Column(name = "CLIENT_DEVICE_TONE")
		private int tone;
		
		//锐度
		@Column(name = "CLIENT_DEVICE_SHARPNESS")
		private int sharpness;

		//色温
		@Column(name = "CLIENT_DEVICE_COLOR_TEMPERATURE")
		private String colorTemperature;

		
		@Column(name = "CREATE_TIME")
		private Date ct;
		
		@Column(name = "UPDATE_TIME")
		private Date ut;
	
		@Column(name = "DATA_STAT")
		private String stat;
		

	    @OneToMany(mappedBy="deviceProperty",fetch=FetchType.EAGER)  
	    private Set<EtbClientDevice> deviceList;  



		public Set<EtbClientDevice> getDeviceList() {
			return deviceList;
		}

		public void setDeviceList(Set<EtbClientDevice> deviceList) {
			this.deviceList = deviceList;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}



		public String getTimeShow() {
			return timeShow;
		}

		public void setTimeShow(String timeShow) {
			this.timeShow = timeShow;
		}


		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}

		public int getBrightness() {
			return brightness;
		}

		public void setBrightness(int brightness) {
			this.brightness = brightness;
		}

		public int getContrast() {
			return contrast;
		}

		public void setContrast(int contrast) {
			this.contrast = contrast;
		}

		public int getTone() {
			return tone;
		}

		public void setTone(int tone) {
			this.tone = tone;
		}

		public int getSharpness() {
			return sharpness;
		}

		public void setSharpness(int sharpness) {
			this.sharpness = sharpness;
		}

		public String getColorTemperature() {
			return colorTemperature;
		}

		public void setColorTemperature(String colorTemperature) {
			this.colorTemperature = colorTemperature;
		}

		public Date getCt() {
			return ct;
		}

		public void setCt(Date ct) {
			this.ct = ct;
		}

		public Date getUt() {
			return ut;
		}

		public void setUt(Date ut) {
			this.ut = ut;
		}

		public String getStat() {
			return stat;
		}

		public void setStat(String stat) {
			this.stat = stat;
		}

		public String getResolution() {
			return resolution;
		}

		public void setResolution(String resolution) {
			this.resolution = resolution;
		}



		public String getLanguage() {
			return language;
		}

		public void setLanguage(String language) {
			this.language = language;
		}

//		public int getVolumeValue() {
//			return volumeValue;
//		}
//
//		public void setVolumeValue(int volumeValue) {
//			this.volumeValue = volumeValue;
//		}






}
