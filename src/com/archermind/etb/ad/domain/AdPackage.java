package com.archermind.etb.ad.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.archermind.etb.device.domain.EtbClientDevice;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.DateUtil;

@Entity
@Table(name = "ETB_AD_PACKAGE")
public class AdPackage implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "AD_PACKAGE_ID")
	private String adPackageId;

	@Column(name = "AD_PACKAGE_BATCH_NO")
	private String adPackageBatchNo;

	@Column(name = "AD_TEMPLATE_ID")
	private long adTemplateId;

	@Column(name = "AD_PACKAGE_NAME", length = 100)
	private String adPackageName;

	@Column(name = "AD_PACKAGE_PATH", length = 200)
	private String adPackagePath;

	@Column(name = "AD_PACKAGE_CREATER", length = 20)
	private String adPackageCreater;

	@Column(name = "AD_PACKAGE_CREATER_TIME")
	private Date adPackageCreaterTime;

	/** 广告包状态 - 0 新增;1-审核通过；2-审核不通过；3-已修改待审核；4-已发布； */
	@Column(name = "AD_PACKAGE_STATUS")
	private int adPackageStatus;

	@Column(name = "AD_PACKAGE_CHECKER", length = 20)
	private String adPackageChecker;

	@Column(name = "AD_PACKAGE_CHECK_TIME")
	private Date adPackageCheckTime;

	@Column(name = "AD_PACKAGE_CHECK_SUGGEST")
	private String adPackageCheckSuggest;

	@Column(name = "AD_PACKAGE_PUBLISHER", length = 20)
	private String adPackagePublisher;

	@Column(name = "AD_PACKAGE_PUBLISH_TIME")
	private Date adPackagePublishTime;
	
	/*播放方式：0：计划播放；立即播放：1：覆盖已有广告；2：原有广告顺延*/
	@Column(name = "AD_PACKAGE_PLAY_TYPE")
	private int adPackagePlayType;
	
	/*循环播放：0：不；1： 是*/
	@Column(name = "AD_PACKAGE_LOOP_TYPE")
	private int adPackageLoopType;

	@Column(name = "AD_PACKAGE_BEGINTIME")
	private Date adPackageBegintime;

	@Column(name = "AD_PACKAGE_ENDTIME")
	private Date adPackageEndtime;

	/** 1:已删除；0：未删除 */
	@Column(name = "DATA_STAT")
	private int dataStat = 0;

	@Column(name = "AD_PACKAGE_TIPS", length = 200)
	private String adPackageTips;
	
	/**广告包类型：B-普通广告；U-紧急广告；E-应用广告*/
	@Column(name = "AD_PACKAGE_TYPE", length = 20)
	private String adPackageType;

	@Transient
	private String adTemplateName;

	@Transient
	private String adPackageBegintime1;

	@Transient
	private String adPackageEndtime1;

	// 批次号-首字母
	@Transient
	private String adPackageBatchNoInitial;

	// 批次号-输入内容
	@Transient
	private String adPackageBatchNoInput;

	// 紧急广告广告类型 5：文字广告 6：图片广告
	@Transient
	private String adPackageTypeUrgency;
	
	/**  建立"广告"与"终端设备"的多对多映射   */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ETB_CLIENT_AD_DEVICE",
			   joinColumns={@JoinColumn(name="AD_PACKAGE_ID")},
			   inverseJoinColumns={@JoinColumn(name="CLIENT_DEVICE_IMEI")})
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<EtbClientDevice> etbClientDeviceList;
	
	/** 建立"终端设备"与"广告"的多对多映射 */
	@ManyToMany(mappedBy = "etbClientDeviceList")
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<AdPackage> clientAdPackageList;

	public String getAdPackageId() {
		return adPackageId;
	}

	public void setAdPackageId(String adPackageId) {
		this.adPackageId = adPackageId;
	}

	public long getAdTemplateId() {
		return adTemplateId;
	}

	public void setAdTemplateId(long adTemplateId) {
		this.adTemplateId = adTemplateId;
	}

	public String getAdPackageName() {
		return adPackageName;
	}

	public void setAdPackageName(String adPackageName) {
		this.adPackageName = adPackageName;
	}

	public String getAdPackagePath() {
		return adPackagePath;
	}

	public void setAdPackagePath(String adPackagePath) {
		this.adPackagePath = adPackagePath;
	}

	public String getAdPackageCreater() {
		return adPackageCreater;
	}

	public void setAdPackageCreater(String adPackageCreater) {
		this.adPackageCreater = adPackageCreater;
	}

	public Date getAdPackageCreaterTime() {
		return adPackageCreaterTime;
	}

	public void setAdPackageCreaterTime(Date adPackageCreaterTime) {
		this.adPackageCreaterTime = adPackageCreaterTime;
	}

	public String getAdPackageChecker() {
		return adPackageChecker;
	}

	public void setAdPackageChecker(String adPackageChecker) {
		this.adPackageChecker = adPackageChecker;
	}

	public Date getAdPackageCheckTime() {
		return adPackageCheckTime;
	}

	public void setAdPackageCheckTime(Date adPackageCheckTime) {
		this.adPackageCheckTime = adPackageCheckTime;
	}

	public String getAdPackagePublisher() {
		return adPackagePublisher;
	}

	public void setAdPackagePublisher(String adPackagePublisher) {
		this.adPackagePublisher = adPackagePublisher;
	}

	public Date getAdPackagePublishTime() {
		return adPackagePublishTime;
	}

	public void setAdPackagePublishTime(Date adPackagePublishTime) {
		this.adPackagePublishTime = adPackagePublishTime;
	}

	public int getAdPackagePlayType() {
		return adPackagePlayType;
	}

	public void setAdPackagePlayType(int adPackagePlayType) {
		this.adPackagePlayType = adPackagePlayType;
	}

	public int getAdPackageLoopType() {
		return adPackageLoopType;
	}

	public void setAdPackageLoopType(int adPackageLoopType) {
		this.adPackageLoopType = adPackageLoopType;
	}

	public Date getAdPackageBegintime() {
		return adPackageBegintime;
	}

	public void setAdPackageBegintime(Date adPackageBegintime) {
		this.adPackageBegintime = adPackageBegintime;
	}

	public Date getAdPackageEndtime() {
		return adPackageEndtime;
	}

	public void setAdPackageEndtime(Date adPackageEndtime) {
		this.adPackageEndtime = adPackageEndtime;
	}

	public String getAdPackageTips() {
		return adPackageTips;
	}

	public void setAdPackageTips(String adPackageTips) {
		this.adPackageTips = adPackageTips;
	}

	public String getAdPackageBatchNo() {
		return adPackageBatchNo;
	}

	public void setAdPackageBatchNo(String adPackageBatchNo) {
		this.adPackageBatchNo = adPackageBatchNo;
	}

	public int getAdPackageStatus() {
		return adPackageStatus;
	}

	public void setAdPackageStatus(int adPackageStatus) {
		this.adPackageStatus = adPackageStatus;
	}

	public int getDataStat() {
		return dataStat;
	}

	public void setDataStat(int dataStat) {
		this.dataStat = dataStat;
	}
	
	public String getAdPackageType() {
		return adPackageType;
	}

	public void setAdPackageType(String adPackageType) {
		this.adPackageType = adPackageType;
	}

	@Transient
	public String getAdPackageBegintimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAdPackageBegintime());
	}
	
	@Transient
	public String getAdPackageEndtimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAdPackageEndtime());
	}

	@Transient
	public String getAdPackageEndtimeDsp2() {
		return DateUtil
				.getDateYYYY_MM_DD_HH_MM_SS(this.getAdPackageEndtime());
	}
	
	@Transient
	public String getAdPackagePublishTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAdPackagePublishTime());
	}

	@Transient
	public String getAdPackageBegintimeDsp2() {
		return DateUtil
				.getDateYYYY_MM_DD_HH_MM_SS(this.getAdPackageBegintime());
	}

	@Transient
	public String getAdPackageCheckTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAdPackageCheckTime());
	}

	@Transient
	public String getAdPackageCreaterTimeDsp() {
		return DateUtil.getDateYYYY_MM_DD(this.getAdPackageCreaterTime());
	}

	public String getAdTemplateName() {
		return adTemplateName;
	}

	public void setAdTemplateName(String adTemplateName) {
		this.adTemplateName = adTemplateName;
	}

	public String getAdPackageCheckSuggest() {
		return adPackageCheckSuggest;
	}

	public void setAdPackageCheckSuggest(String adPackageCheckSuggest) {
		this.adPackageCheckSuggest = adPackageCheckSuggest;
	}

	@Transient
	public String getAdPackageBegintime1() {
		return adPackageBegintime1;
	}

	@Transient
	public void setAdPackageBegintime1(String adPackageBegintime1) {
		this.adPackageBegintime1 = adPackageBegintime1;
	}

	@Transient
	public String getAdPackageEndtime1() {
		return adPackageEndtime1;
	}

	@Transient
	public void setAdPackageEndtime1(String adPackageEndtime1) {
		this.adPackageEndtime1 = adPackageEndtime1;
	}

	@Transient
	public String getAdPackageBatchNoInitial() {
		return adPackageBatchNoInitial;
	}

	@Transient
	public void setAdPackageBatchNoInitial(String adPackageBatchNoInitial) {
		this.adPackageBatchNoInitial = adPackageBatchNoInitial;
	}

	@Transient
	public String getAdPackageBatchNoInput() {
		return adPackageBatchNoInput;
	}

	@Transient
	public void setAdPackageBatchNoInput(String adPackageBatchNoInput) {
		this.adPackageBatchNoInput = adPackageBatchNoInput;
	}

	public String getAdPackageTypeUrgency() {
		return adPackageTypeUrgency;
	}

	public void setAdPackageTypeUrgency(String adPackageTypeUrgency) {
		this.adPackageTypeUrgency = adPackageTypeUrgency;
	}
	
	@Transient
	public String getAdPackageBegintimeList() {
		return DateUtil
				.getDateYYYY_MM_DD_HH_MM_SS(this.getAdPackageBegintime());
	}
	
	@Transient
	public String getAdPackageEndtimeList() {
		return DateUtil
				.getDateYYYY_MM_DD_HH_MM_SS(this.getAdPackageEndtime());
	}
	

	/**
	 * 广告包状态 - 0 新增;1-审核通过；2-审核不通过；3-已修改待审核；4-已发布；
	 * 
	 * @return
	 */
	@Transient
	public String getAdPackageStatusDsp() {

		switch (this.getAdPackageStatus()) {

		case Constant.AD_PACKAGE_STATUS_ADD:// 0 新增
			return Constant.AD_PACKAGE_STATUS_ADD_CHINESE;

		case Constant.AD_PACKAGE_STATUS_CHECK_PASSED:// 1-审核通过
			return Constant.AD_PACKAGE_STATUS_CHECK_PASSED_CHINESE;

		case Constant.AD_PACKAGE_STATUS_CHECK_UNPASSED:// 2-审核不通过
			return Constant.AD_PACKAGE_STATUS_CHECK_UNPASSED_CHINESE;

		case Constant.AD_PACKAGE_STATUS_COMMITTED:// 3-待审核
			return Constant.AD_PACKAGE_STATUS_COMMITTED_CHINESE;

		case Constant.AD_PACKAGE_STATUS_PUBLISHED:// 4-已发布
			return Constant.AD_PACKAGE_STATUS_PUBLISHED_CHINESE;

		default:
			return "";
		}
	}

	public Set<EtbClientDevice> getEtbClientDeviceList() {
		return etbClientDeviceList;
	}

	public void setEtbClientDeviceList(Set<EtbClientDevice> etbClientDeviceList) {
		this.etbClientDeviceList = etbClientDeviceList;
	}

	public Set<AdPackage> getClientAdPackageList() {
		return clientAdPackageList;
	}

	public void setClientAdPackageList(Set<AdPackage> clientAdPackageList) {
		this.clientAdPackageList = clientAdPackageList;
	}
	
}
