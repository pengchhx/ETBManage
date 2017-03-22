package com.archermind.etb.ad.domain;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.PropertyUtil;

/**
 * 广告资源 Entity
 * 
 * @author 003383 陈然
 * @version v1.0,2013-07-15
 * @see
 * @since v1.0
 */

@Entity
@Table(name = "ETB_AD_RESOURCE")
public class AdResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AD_RESOURCE_ID")
	private long adResourceId;

	@Column(name = "AD_PACKAGE_ID", length = 100)
	private String adPackageId;

	@Column(name = "AD_RESOURCE_PATH", length = 255)
	private String adResourcePath;

	/** 1:图片；2：视频 */
	@Column(name = "AD_RESOURCE_TYPES", length = 40)
	private String adResourceTypes;

	@Column(name = "AD_RESOURCE_TIME_H")
	private String adResourceTimeH;
	
	@Column(name = "AD_RESOURCE_TIME_M")
	private String adResourceTimeM;
	
	@Column(name = "AD_RESOURCE_TIME_S")
	private String adResourceTimeS;

	/** 转场动画:1,2,3..*/
	@Column(name = "AD_RESOURCE_TRANSITIONS", length = 8)
	private String adResourceTransitions;
	
	/** 背景音乐*/
	@Column(name = "AD_RESOURCE_MUSIC", length = 255)
	private String adResourceMusic;
	
	/** 位置序号*/
	@Column(name = "AD_RESOURCE_NUMBER", length = 255)
	private String adResourceNumber;
	
	/** 区块编号*/
	@Column(name = "AD_RESOURCE_AREA_PLACE")
	private int adResourceAreaPlace;
	
	@Column(name = "AD_RESOURCE_OUTTIME", length = 100)
	private String adResourceOuttime;
	
	@Column(name = "AD_RESOURCE_COUNT")
	private long adResourceCount;

	/** 0:单屏; 1:上屏；2：下屏 */
	@Column(name = "AD_RESOURCE_POSITION_SIGN", length = 5)
	private String adResourcePositionSign;
	
	@Column(name = "AD_RESOURCE_PLACE_X")
	private int adResourcePlaceX;
	
	@Column(name = "AD_RESOURCE_PLACE_Y")
	private int adResourcePlaceY;
	
	@Column(name = "AD_RESOURCE_PLACE_W")
	private int adResourcePlaceW;
	
	@Column(name = "AD_RESOURCE_PLACE_H")
	private int adResourcePlaceH;

	/** 1:已删除；0：未删除 */
	@Column(name = "DATA_STAT")
	private long dataStat = 0;

	@Column(name = "AD_RESOURCE_TIPS", length = 200)
	private String adResourceTips;
	
	/**缩略图路径*/
	@Column(name = "AD_RESOURCE_THUMBNAIL_PATH", length = 200)
	private String adResourceThumbnailPath;

	@Column(name = "AD_RESOURCE_ACTION", length = 200)
	private String adResourceAction;

	@Column(name = "AD_RESOURCE_INTENT", length = 100)
	private String adResourceIntent;

	@Column(name = "AD_RESOURCE_PRODUCT", length = 100)
	private String adResourceProduct;

	@Column(name = "AD_RESOURCE_INTIME", length = 100)
	private String adResourceIntime;

	public long getAdResourceId() {
		return adResourceId;
	}

	public void setAdResourceId(long adResourceId) {
		this.adResourceId = adResourceId;
	}

	public String getAdPackageId() {
		return adPackageId;
	}

	public void setAdPackageId(String adPackageId) {
		this.adPackageId = adPackageId;
	}

	public String getAdResourcePath() {
		return adResourcePath;
	}

	public void setAdResourcePath(String adResourcePath) {
		this.adResourcePath = adResourcePath;
	}

	public String getAdResourceTypes() {
		return adResourceTypes;
	}

	public void setAdResourceTypes(String adResourceTypes) {
		this.adResourceTypes = adResourceTypes;
	}

	public String getAdResourceTime() {
		Integer time = 0;
		if(Integer.parseInt(adResourceTimeH) > 0){
			time += Integer.parseInt(adResourceTimeH) * 60 * 60;
		}
		if(Integer.parseInt(adResourceTimeM) > 0){
			time += Integer.parseInt(adResourceTimeM) * 60;
		}
		if(Integer.parseInt(adResourceTimeS) > 0){
			time += Integer.parseInt(adResourceTimeS);
		}
		
		return time.toString();
	}
	public String getAdResourceTimeH() {
		return adResourceTimeH;
	}

	public void setAdResourceTimeH(String adResourceTimeH) {
		this.adResourceTimeH = adResourceTimeH;
	}

	public String getAdResourceTimeM() {
		return adResourceTimeM;
	}

	public void setAdResourceTimeM(String adResourceTimeM) {
		this.adResourceTimeM = adResourceTimeM;
	}

	public String getAdResourceTimeS() {
		return adResourceTimeS;
	}

	public void setAdResourceTimeS(String adResourceTimeS) {
		this.adResourceTimeS = adResourceTimeS;
	}

	public String getAdResourceOuttime() {
		return adResourceOuttime;
	}

	public void setAdResourceOuttime(String adResourceOuttime) {
		this.adResourceOuttime = adResourceOuttime;
	}

	public long getAdResourceCount() {
		return adResourceCount;
	}

	public void setAdResourceCount(long adResourceCount) {
		this.adResourceCount = adResourceCount;
	}

	public String getAdResourcePositionSign() {
		return adResourcePositionSign;
	}

	public void setAdResourcePositionSign(String adResourcePositionSign) {
		this.adResourcePositionSign = adResourcePositionSign;
	}

	public String getAdResourceTransitions() {
		return adResourceTransitions;
	}

	public void setAdResourceTransitions(String adResourceTransitions) {
		this.adResourceTransitions = adResourceTransitions;
	}

	public String getAdResourceMusic() {
		return adResourceMusic;
	}

	public void setAdResourceMusic(String adResourceMusic) {
		this.adResourceMusic = adResourceMusic;
	}

	public String getAdResourceNumber() {
		return adResourceNumber;
	}

	public void setAdResourceNumber(String adResourceNumber) {
		this.adResourceNumber = adResourceNumber;
	}

	public int getAdResourceAreaPlace() {
		return adResourceAreaPlace;
	}

	public void setAdResourceAreaPlace(int adResourceAreaPlace) {
		this.adResourceAreaPlace = adResourceAreaPlace;
	}

	public int getAdResourcePlaceX() {
		return adResourcePlaceX;
	}

	public void setAdResourcePlaceX(int adResourcePlaceX) {
		this.adResourcePlaceX = adResourcePlaceX;
	}

	public int getAdResourcePlaceY() {
		return adResourcePlaceY;
	}

	public void setAdResourcePlaceY(int adResourcePlaceY) {
		this.adResourcePlaceY = adResourcePlaceY;
	}

	public int getAdResourcePlaceW() {
		return adResourcePlaceW;
	}

	public void setAdResourcePlaceW(int adResourcePlaceW) {
		this.adResourcePlaceW = adResourcePlaceW;
	}

	public int getAdResourcePlaceH() {
		return adResourcePlaceH;
	}

	public void setAdResourcePlaceH(int adResourcePlaceH) {
		this.adResourcePlaceH = adResourcePlaceH;
	}

	public long getDataStat() {
		return dataStat;
	}

	public void setDataStat(long dataStat) {
		this.dataStat = dataStat;
	}

	public String getAdResourceTips() {
		return adResourceTips;
	}

	public void setAdResourceTips(String adResourceTips) {
		this.adResourceTips = adResourceTips;
	}
	
	public String getAdResourceAction() {
		return adResourceAction;
	}
	
	public void setAdResourceAction(String adResourceAction) {
		this.adResourceAction = adResourceAction;
	}
	
	public String getAdResourceIntent() {
		return adResourceIntent;
	}
	
	public void setAdResourceIntent(String adResourceIntent) {
		this.adResourceIntent = adResourceIntent;
	}
	
	public String getAdResourceProduct() {
		return adResourceProduct;
	}
	
	public void setAdResourceProduct(String adResourceProduct) {
		this.adResourceProduct = adResourceProduct;
	}
	
	public String getAdResourceIntime() {
		return adResourceIntime;
	}
	
	public void setAdResourceIntime(String adResourceIntime) {
		this.adResourceIntime = adResourceIntime;
	}

	/**
	 * 文件路径组装
	 * 
	 * @return
	 */
	public String getAdResourcePathDsp() {
		String path = Constant.AD_FILE_UPLOAD_FOLDER + File.separator
				+ PropertyUtil.readData("advertiseUploadPath") + File.separator
				+ this.getAdPackageId() + File.separator
				+ this.getAdResourcePath();
		return path;
	}
	
	/**
	 * 缩略图路径组装
	 * 
	 * @return
	 */
	public String getAdResourceThumbnailPathDsp() {
		String path = Constant.AD_FILE_UPLOAD_FOLDER + File.separator
				+ Constant.AD_FILE_THUMBNAIL_FOLDER + File.separator
				+ this.getAdPackageId() + File.separator
				+ this.getAdResourceThumbnailPath();
		return path;
	}

	public String getAdResourceThumbnailPath() {
		return adResourceThumbnailPath;
	}

	public void setAdResourceThumbnailPath(String adResourceThumbnailPath) {
		this.adResourceThumbnailPath = adResourceThumbnailPath;
	}

	
}
