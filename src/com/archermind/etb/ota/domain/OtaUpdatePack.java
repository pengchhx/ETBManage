package com.archermind.etb.ota.domain;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.archermind.etb.util.Constant;

/**
 * ota升级包的数据库对应实体
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130711
 * @see 
 * @since 1.0
 */
@Entity
@Table(name = "ETB_OTA_UPDATE_PACK_INFO")
public class OtaUpdatePack implements Serializable
{

	private static final long serialVersionUID = -638425839442519621L;
	
	@Id
	@Column(name="OTA_UPDATE_PACK_INFO_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="OTA_UPDATE_PACK_INFO_TO",length = 100)
	private String targetname;
	
	@Column(name="OTA_UPDATE_PACK_INFO_FROM",length = 100)
	private String initname;
	
	@Column(name="OTA_UPDATE_PACK_INFO_NAME",length = 100)
	private String packname;
	
	@Column(name="OTA_UPDATE_PACK_INFO_URL",length = 200)
	private String packaddr;
	
	@Column(name="OTA_UPDATE_PACK_INFO_TYPE",length = 11)
	private Integer packtype;
	
	@Column(name="DATA_STAT",length = 11)
	private Integer datastat;
	
	@Column(name="OTA_UPDATE_PACK_INFO_TIPS",length = 200)
	private String tips;
	
	@Transient
	private Integer otaid;

	@Transient
	private File packfile;
	
	@Transient
	private String packfileFileName;
	
	@Transient
	private String packfileContentType;
	
	@Transient
	private String packUploadPath;
	
	@Transient
	private String packShowPath;
	
	public String getPackShowPath() {
		String s = this.getPackaddr();
		return s.substring(4, s.length());
	}

	public String getPackUploadPath() {
		String path = Constant.AD_FILE_UPLOAD_FOLDER + "/";
		String s = this.getPackaddr();
		if(s.indexOf("\\")!=-1){
			String[] ss = s.split("ota");
			path+="ota"+"/"+ss[1].substring(1);
		}else{
			path+=s;
		}
		
		return path;
	}

	public String getPackfileContentType()
	{
		return packfileContentType;
	}

	public void setPackfileContentType(String packfileContentType)
	{
		this.packfileContentType = packfileContentType;
	}

	public String getPackfileFileName()
	{
		return packfileFileName;
	}

	public void setPackfileFileName(String packfileFileName) 
	{
		this.packfileFileName = packfileFileName;
	}

	public File getPackfile()
	{
		return packfile;
	}

	public void setPackfile(File packfile)
	{
		this.packfile = packfile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTargetname() {
		return targetname;
	}

	public void setTargetname(String targetname) {
		this.targetname = targetname;
	}

	public String getInitname() {
		return initname;
	}

	public void setInitname(String initname) {
		this.initname = initname;
	}

	public String getPackname() {
		return packname;
	}

	public void setPackname(String packname) {
		this.packname = packname;
	}

	public String getPackaddr() {
		return packaddr;
	}

	public void setPackaddr(String packaddr) {
		this.packaddr = packaddr;
	}

	public Integer getPacktype() {
		return packtype;
	}

	public void setPacktype(Integer packtype) {
		this.packtype = packtype;
	}

	public Integer getDatastat() {
		return datastat;
	}

	public void setDatastat(Integer datastat) {
		this.datastat = datastat;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
	
	public Integer getOtaid() 
	{
		return otaid;
	}

	public void setOtaid(Integer otaid) 
	{
		this.otaid = otaid;
	}
}
