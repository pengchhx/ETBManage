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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.archermind.etb.device.service.ClientDeviceExcelRowService;
import com.archermind.etb.user.domain.CompanyInfo;

@Entity
@Table(name = "ETB_CLIENT_DEVICE_TIME")
public class EtbClientDeviceTime {

	
	
	/**
	 * 
	 * @author 
	 * @version 
	 * @see 
	 * @Time 
	 */

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "CLIENT_DEVICE_TIME_ID")
		private long id;


		@Column(name = "CLIENT_DEVICE_OPEN_HOUR")
		private String openHour;
		
		@Column(name = "CLIENT_DEVICE_OPEN_MIN")
		
		private String openMin;
		
		@Column(name = "CLIENT_DEVICE_CLOSE_HOUR")
		private String closeHour;
		
		@Column(name = "CLIENT_DEVICE_CLOSE_MIN")
		private String closeMin;
		
		
		
		//星期  
		@Column(name = "CLIENT_DEVICE_WEEK_TIME")
		private int weekTime;
	
		//重复 0 为 每日重复  1为每周重复
		@Column(name = "CLIENT_DEVICE_TIME_TYPE")
		private int timeType;
		
		
		@Column(name = "DATA_STAT")
		private int stat;
		
	
		
		/** 建立"设备时间"与"设备"的多对一映射 */
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name="CLIENT_DEVICE_ID",nullable=true)
		@NotFound(action = NotFoundAction.IGNORE)
		private EtbClientDevice clientDevice;



		public long getId() {
			return id;
		}



		public void setId(long id) {
			this.id = id;
		}






		public int getWeekTime() {
			return weekTime;
		}



		public void setWeekTime(int weekTime) {
			this.weekTime = weekTime;
		}



		public int getTimeType() {
			return timeType;
		}



		public void setTimeType(int timeType) {
			this.timeType = timeType;
		}



		public int getStat() {
			return stat;
		}



		public void setStat(int stat) {
			this.stat = stat;
		}



		public EtbClientDevice getClientDevice() {
			return clientDevice;
		}



		public void setClientDevice(EtbClientDevice clientDevice) {
			this.clientDevice = clientDevice;
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
