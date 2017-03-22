package com.archermind.etb.device.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.archermind.etb.ad.domain.AdPackage;
import com.archermind.etb.user.domain.ClientUserInfo;
import com.archermind.etb.user.domain.CompanyInfo;
import com.google.gson.annotations.Expose;

/**
 * 终端设备实体
 * 
 * @author 001667
 * @version 1.0
 * @since 1.0
 * 
 */
@Entity
@Table(name = "ETB_CLIENT_DEVICE")
public class EtbClientDevice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Transient
	private int index;

	@Id
	@Column(name = "CLIENT_DEVICE_IMEI")
	@Expose
	private String imei;

	@Column(name = "CLIENT_DEVICE_NAME")
	@Expose
	private String name;

	@Column(name = "CLIENT_DEVICE_MAC_ADDR")
	@Expose
	private String addr;

	@Column(name = "CLIENT_DEVICE_IP")
	@Expose
	private String ip;

	@Column(name = "DATA_STAT")
	@Expose
	private int stat;
	
	@Column(name="CLIENT_DEVICE_TOKEN")
	@Expose
	private String token;
	
	
	@Column(name="CLIENT_DEVICE_APK_TOKEN")
	@Expose
	private String apkToken;

	@Column(name = "CLIENT_DEVICE_TIPS")
	@Expose
	private String tips;
	
	
//	@Column(name = "CLIENT_DEVICE_STATUS")
//	@Expose
//	private int status;

	@Column(name = "CLIENT_DEVICE_OPERATOR")
	@Expose
	private String os;
	
	@Column(name = "CLIENT_DEVICE_CREATER")
	@Expose
	private String cs;
	
	@Column(name = "CREATE_TIME")
	@Expose
	private Date ct;
	
	@Column(name = "UPDATE_TIME")
	@Expose
	private Date ut;
	
	@Column(name = "CLIENT_DEVICE_AREA_ID")
	@Expose
	private Integer areaId;

	@Transient
	private String province;

	@Transient
	private String cityCode;
	
	@Transient
	private String city;
	
	@Transient
	private String country;

	@Column(name = "CLIENT_DEVICE_TEL")
	@Expose
	private String tel;
	
	@Column(name = "CLIENT_DEVICE_ADDRESS")
	@Expose
	private String Address;
	
	/** 建立"终端设备"与"公司"的多对一映射 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="COMPANY_INFO_ID",nullable=true,updatable=false)
	@NotFound(action = NotFoundAction.IGNORE)
	private CompanyInfo company;
	
	/** 建立"终端设备"与"设备属性"的多对一映射 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CLIENT_DEVICE_PROPERTY_ID",nullable=true)
	@NotFound(action = NotFoundAction.IGNORE)
	private EtbClientDeviceProperty deviceProperty;
	
    @OneToMany(mappedBy="clientDevice",fetch=FetchType.EAGER) 
	@Cascade(CascadeType.SAVE_UPDATE)
    private Set<EtbClientDeviceTime> deviceTimeList;  
    
    @OneToMany(mappedBy="clientDeviceByVolume",fetch=FetchType.EAGER) 
	@Cascade(CascadeType.SAVE_UPDATE)
    private Set<EtbClientDeviceVolume> deviceVolumeList;  

	/** 建立"终端设备"与"终端用户"的多对多映射 */
	@ManyToMany(mappedBy = "etbClientDeviceList")
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<ClientUserInfo> clientUserInfoList;
	
	/** 建立"终端设备"与"广告"的多对多映射 */
	@ManyToMany(mappedBy = "etbClientDeviceList")
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<AdPackage> clientAdPackageList;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei.trim();
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

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Set<ClientUserInfo> getClientUserInfoList() {
		return clientUserInfoList;
	}

	public void setClientUserInfoList(Set<ClientUserInfo> clientUserInfoList) {
		this.clientUserInfoList = clientUserInfoList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Date getCt() {
		return ct;
	}

	public void setCt(Date ct) {
		this.ct = ct;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Set<AdPackage> getClientAdPackageList() {
		return clientAdPackageList;
	}

	public void setClientAdPackageList(Set<AdPackage> clientAdPackageList) {
		this.clientAdPackageList = clientAdPackageList;
	}

	public CompanyInfo getCompany() {
		return company;
	}

	public void setCompany(CompanyInfo company) {
		this.company = company;
	}

	public EtbClientDeviceProperty getDeviceProperty() {
		return deviceProperty;
	}

	public void setDeviceProperty(EtbClientDeviceProperty deviceProperty) {
		this.deviceProperty = deviceProperty;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
//
//	public int getStatus() {
//		return status;
//	}
//
//	public void setStatus(int status) {
//		this.status = status;
//	}

	public String getApkToken() {
		return apkToken;
	}

	public void setApkToken(String apkToken) {
		this.apkToken = apkToken;
	}

	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public Date getUt() {
		return ut;
	}

	public void setUt(Date ut) {
		this.ut = ut;
	}

	public Set<EtbClientDeviceTime> getDeviceTimeList() {
		return deviceTimeList;
	}

	public void setDeviceTimeList(Set<EtbClientDeviceTime> deviceTimeList) {
		this.deviceTimeList = deviceTimeList;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Set<EtbClientDeviceVolume> getDeviceVolumeList() {
		return deviceVolumeList;
	}

	public void setDeviceVolumeList(Set<EtbClientDeviceVolume> deviceVolumeList) {
		this.deviceVolumeList = deviceVolumeList;
	}


	
	
	
	
	
}
