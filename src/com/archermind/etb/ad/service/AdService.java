package com.archermind.etb.ad.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import com.archermind.etb.ad.dao.AdPackageDao;
import com.archermind.etb.ad.dao.AdResourceDao;
import com.archermind.etb.ad.dao.AdTemplateDao;
import com.archermind.etb.ad.domain.AdPackage;
import com.archermind.etb.ad.domain.AdResource;
import com.archermind.etb.ad.domain.AdTemplate;
import com.archermind.etb.common.UUIDGenerator;
import com.archermind.etb.device.dao.EtbClientAdDeviceDao;
import com.archermind.etb.device.domain.EtbClientAdDevice;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.FileUpload;
import com.archermind.etb.util.JsonHelper;
import com.archermind.etb.util.PropertyUtil;
import com.archermind.etb.util.WsClientUtil;
import com.archermind.etb.util.ZipCompressor;

/**
 * 广告管理 Service
 * 
 * @author 003383 陈然
 * @version v1.0,2013.07.11
 * @see
 * @since v1.0
 */

@Service("com.archermind.etb.ad.service.AdService")
public class AdService {

	/**
	 * 广告包DAO
	 */
	@Resource(name = "com.archermind.etb.ad.dao.AdPackageDao")
	private AdPackageDao adPackageDao;

	/**
	 * 广告包模板DAO
	 */
	@Resource(name = "com.archermind.etb.ad.dao.AdTemplateDao")
	private AdTemplateDao adTemplateDao;

	/**
	 * 广告包资源DAO
	 */
	@Resource(name = "com.archermind.etb.ad.dao.AdResourceDao")
	private AdResourceDao adResourceDao;
	
	/**
	 * 广告设备DAO
	 */
	@Resource(name = "com.archermind.etb.device.dao.EtbClientAdDeviceDao")
	private EtbClientAdDeviceDao etbClientAdDeviceDao;

	/**
	 * 日志
	 */
	private static final Logger log = Logger.getLogger(AdService.class);

	/**
	 * 根据查询条件查询广告包总数
	 * 
	 * @param adPackageName
	 * @param status
	 * @return
	 */
	public int getAllAdCountByAdName(String adPackageName) {

		return adPackageDao.getAllAdCountByName(adPackageName);

	}

	/**
	 * 根据查询条件查询待审核广告包总数
	 * 
	 * @param adPackageName
	 * @param status
	 * @return
	 */
	public int getAuditAdCountByAdName(String adPackageName) {

		return adPackageDao.getAuditAdCountByName(adPackageName);

	}

	/**
	 * 根据查询条件分页查询广告包信息
	 * 
	 * @param adPackageName
	 * @param status
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Map<String, Object>> listAllAdByPage(String adPackageName,
			int pageSize, int pageNum) {

		return adPackageDao.listAllAdByPage(adPackageName, pageSize, pageNum);

	}

	/**
	 * 根据查询条件分页查询广告包信息-审核
	 * 
	 * @param adPackageName
	 * @param status
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Map<String, Object>> listAuditAdByPage(String adPackageName,
			int pageSize, int pageNum) {

		return adPackageDao.listAuditAdByPage(adPackageName, pageSize, pageNum);

	}

	/**
	 * 根据查询条件查询广告发布信息总数-广告发布查询
	 * 
	 * @param adPackageName
	 * @return
	 */
	public int getAdPublishCountByAdName(String adPackageName) {

		return adPackageDao.getAdPublishCountByName(adPackageName);

	}

	/**
	 * 根据查询条件分页查询广告发布信息-广告发布查询
	 * 
	 * @param adPackageName
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Map<String, Object>> listAdPublishByPage(String adPackageName,
			int pageSize, int pageNum) {

		return adPackageDao.listAdPublishByPage(adPackageName, pageSize,
				pageNum);

	}

	/**
	 * 添加广告包信息
	 * 
	 * @param adPackage
	 */
	public void addAd(AdPackage adPackage) {

		adPackageDao.save(adPackage);

	}

	/**
	 * 根据广告包的ID获取广告包信息
	 * 
	 * @param adPackageId
	 * @return
	 */
	public AdPackage getAdPackageById(String adPackageId) {

		return adPackageDao.findById(AdPackage.class, adPackageId);

	}

	/**
	 * 根据广告包ID和资源类型,查找资源数量,2013-10-30,RanChen
	 * 
	 * @param adPackageId
	 * @param adResourceType
	 * @return
	 */
	public int getAdResourceCountByAdpackageIdAndAdResourceType(
			String adPackageId, String adResourceType) {
		return adResourceDao.getAdResourceCountByAdpackageIdAndAdResourceType(
				adPackageId, adResourceType);
	}

	/**
	 * 更新广告包信息
	 * 
	 * @param adPackage
	 */
	public void saveAdPackage(AdPackage adPackage) {

		adPackageDao.saveOrUpdate(adPackage);

	}

	/**
	 * 发布广告包
	 * 
	 * @param adPackage
	 */
	public void publishAd(AdPackage adPackage) {

		// 获取登录用户真实姓名
		Subject subject = SecurityUtils.getSubject();
		String userNm = String.valueOf(subject.getSession().getAttribute(
				Constant.SESSION_USER_NAME));
		// 设置发布人，登录用户真实姓名
		adPackage.setAdPackagePublisher(userNm);
		adPackage.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_PUBLISHED);// 状态栏位：4-已发布
		adPackage.setAdPackagePublishTime(new Date());// 发布时间-系统当前时间

		adPackageDao.saveOrUpdate(adPackage);

	}

	/**
	 * 设置审核信息
	 * 
	 * @param adPackage
	 */
	public void setAdPackageCheckInfo(AdPackage adPackageTmp) {
		// 获取登录用户真实姓名
		Subject subject = SecurityUtils.getSubject();
		String userNm = String.valueOf(subject.getSession().getAttribute(
				Constant.SESSION_USER_NAME));
		// 设置审核人
		adPackageTmp.setAdPackageChecker(userNm);
		// 审核时间
		adPackageTmp.setAdPackageCheckTime(new Date());

	}

	/**
	 * 根据资源ID获取资源信息
	 * 
	 * @param adPackageId
	 * @return
	 */
	public AdResource getAdResourceById(long adResourceId) {

		return adResourceDao.findById(AdResource.class, adResourceId);

	}

	/**
	 * 更新广告资源信息
	 * 
	 * @param adResource
	 */
	public void saveOrUpdateAdResource(AdResource adResource) {

		adResourceDao.saveOrUpdate(adResource);

	}

	/**
	 * 获取广告模板列表
	 * 
	 * @return
	 */
	public List<AdTemplate> listAllAdTemplate(int adTemplateType) {

		return adTemplateDao.listAllAdTemplate(adTemplateType);

	}

	/**
	 * 根据广告包ID查询广告资料列表
	 * 
	 * @param adPackageId
	 * @return
	 */
	public List<AdResource> listAdResourceByAdPackageId(String adPackageId) {

		return adResourceDao.listAdResourceByAdPackageId(adPackageId);

	}

	/**
	 * 根据广告包ID查询双屏广告资料列表
	 * 
	 * @param adPackageId
	 * @param flag
	 *            :标志是上屏还是下屏
	 * @return
	 */
	public List<AdResource> listAdResourceDoubleByAdPackageId(
			String adPackageId, String flag) {

		return adResourceDao.listAdResourceDoubleByAdPackageId(adPackageId,
				flag);

	}

	/**
	 * 根据广告包ID和广告位置标识查询广告资料列表
	 * 
	 * @param adPackageId
	 * @param adResourcePositionSign
	 * @return
	 */
	public List<AdResource> listAdResourceByAdPackageIdAndPosition(
			String adPackageId, String adResourcePositionSign) {
		return adResourceDao.listAdResourceByAdPackageIdAndPosition(
				adPackageId, adResourcePositionSign);
	}

	/**
	 * 根据模板ID获取模板信息
	 * 
	 * @param adPackageId
	 * @return
	 */
	public AdTemplate getAdTemplateById(long adTemplateId) {

		return adTemplateDao.findById(AdTemplate.class, adTemplateId);

	}

	/**
	 * 更新广告资源和广告包
	 * 
	 * @param adResource
	 */
	public void updateAdPackageAndAdResource(AdResource adResource) {

		// 更新资源文件
		adResourceDao.saveOrUpdate(adResource);

		String adPackageId = adResource.getAdPackageId();

		if (StringUtils.isNotEmpty(adPackageId)) {

			AdPackage adPackage = this.getAdPackageById(adPackageId);

			// 判断adPackageStatus是否为"2-审核不通过",若是,则将状态改为"0-保存草稿"
			if (adPackage.getAdPackageStatus() == Constant.AD_PACKAGE_STATUS_CHECK_UNPASSED) {
				adPackage.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_ADD);
				adPackageDao.saveOrUpdate(adPackage);
			}
		}

	}

	/**
	 * 审核流程
	 * 
	 * @param adPackage
	 * @return
	 */
	public String check(AdPackage adPacakgeTmp) {

		// 获取广告包路径
		String srcPathName = ServletActionContext.getServletContext()
				.getRealPath(File.separator)
				+ Constant.AD_FILE_UPLOAD_FOLDER
				+ File.separator
				+ PropertyUtil.readData("advertiseUploadPath")
				+ File.separator + adPacakgeTmp.getAdPackageId();

		String zipPathName = ServletActionContext.getServletContext()
				.getRealPath(File.separator)
				+ Constant.AD_FILE_UPLOAD_FOLDER
				+ File.separator
				+ PropertyUtil.readData("advertiseUploadPath")
				+ File.separator + adPacakgeTmp.getAdPackageBatchNo();

		boolean successFlag = true;

		// 创建advertisement.xml文档
		try {
			// 创建xml的Document
			Document document = this.createAdDocument(adPacakgeTmp);

			// 创建xml文档
			successFlag = this.createAdxml(srcPathName, document);

		} catch (Exception e) {
			log.error("create xml error", e);
			return "创建XML文档失败";
		}
		if (!successFlag) {
			return "创建XML文档失败";
		}

		// 先判断文件是否存在，若不存在则提示错误信息
		File zipFile = new File(srcPathName);
		if (!zipFile.exists()) {
			return "广告包文件不存在";
		}
		// 打包广告包
		ZipCompressor zipCompressor = new ZipCompressor(zipPathName + ".zip");
		zipCompressor.compress(srcPathName);

		// FTP上传
		String filename = adPacakgeTmp.getAdPackageBatchNo() + ".zip";

		// 2013-09-29,RanChen,去掉文件上传的逻辑
		// try {
		// // this.ftpUpload(filename, zipPathName);
		// this.fileUpload(filename, zipPathName);
		// } catch (Exception e1) {
		// log.error("ftp upload error", e1);
		// return "FTP上传失败";
		// }

		// 修改审核信息
		try {

			if (StringUtils.isNotEmpty(adPacakgeTmp.getAdPackageId())) {
				// 查询广告包基本信息
				adPacakgeTmp = this.getAdPackageById(adPacakgeTmp.getAdPackageId());
			}

			setAdPackageCheckInfo(adPacakgeTmp);

			// 状态栏位：1-已审核
			adPacakgeTmp.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_CHECK_PASSED);

			// 广告包路径-ftp路径,只存放广告包名
			adPacakgeTmp.setAdPackagePath(filename);

			this.saveAdPackage(adPacakgeTmp);

		} catch (Exception e) {
			log.error("update data error", e);

			return "";
		}

		return null;

	}
	
	/**
	 * 审核流程
	 * 
	 * @param adPackage
	 * @return
	 */
	public String createZipAndXmlFile(AdPackage adPacakgeTmp) {

		// 获取广告包路径
		String srcPathName = ServletActionContext.getServletContext()
				.getRealPath(File.separator)
				+ Constant.AD_FILE_UPLOAD_FOLDER
				+ File.separator
				+ PropertyUtil.readData("advertiseUploadPath")
				+ File.separator + adPacakgeTmp.getAdPackageId();

		String zipPathName = ServletActionContext.getServletContext()
				.getRealPath(File.separator)
				+ Constant.AD_FILE_UPLOAD_FOLDER
				+ File.separator
				+ PropertyUtil.readData("advertiseUploadPath")
				+ File.separator + adPacakgeTmp.getAdPackageBatchNo();

		boolean successFlag = true;

		// 创建advertisement.xml文档
		try {
			// 创建xml的Document
			Document document = this.createAdDocument(adPacakgeTmp);

			// 创建xml文档
			successFlag = this.createAdxml(srcPathName, document);

		} catch (Exception e) {
			log.error("create xml error", e);
			return "创建XML文档失败";
		}
		if (!successFlag) {
			return "创建XML文档失败";
		}

		// 先判断文件是否存在，若不存在则提示错误信息
		File zipFile = new File(srcPathName);
		if (!zipFile.exists()) {
			zipFile.mkdirs();
			List<AdResource> adResourceListAll = this.listAdResourceByAdPackageId(adPacakgeTmp.getAdPackageId());
			for(int i = 0;i < adResourceListAll.size();i ++){
				String fileName = adResourceListAll.get(i).getAdResourcePath();
				fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
				File copyFrom = new File(ServletActionContext.getServletContext().getRealPath(fileName));
				File copyTo = new File(srcPathName + fileName);
				
				try {
					FileUtils.copyFile(copyFrom, copyTo);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			List<AdResource> adResourceListAll = this.listAdResourceByAdPackageId(adPacakgeTmp.getAdPackageId());
			for(int i = 0;i < adResourceListAll.size();i ++){
				String fileName = adResourceListAll.get(i).getAdResourcePath();
				fileName = fileName.substring(fileName.indexOf("ETBManage/") + "ETBManage/".length());
				String toName = fileName.substring(fileName.indexOf("/") + 1);
				File copyFrom = new File(ServletActionContext.getServletContext().getRealPath(fileName));
				File copyTo = new File(srcPathName + File.separator + toName);
				
				try {
					if(!copyTo.exists()){
						FileUtils.copyFile(copyFrom, copyTo);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 打包广告包
		ZipCompressor zipCompressor = new ZipCompressor(zipPathName + ".zip");
		zipCompressor.compress(srcPathName);

		// FTP上传
		//String filename = adPacakgeTmp.getAdPackageBatchNo() + ".zip";

		// 修改审核信息
		//try {

		//	if (StringUtils.isNotEmpty(adPacakgeTmp.getAdPackageId())) {
				// 查询广告包基本信息
		//		adPacakgeTmp = this.getAdPackageById(adPacakgeTmp.getAdPackageId());
		//	}

			//setAdPackageCheckInfo(adPacakgeTmp);

			// 状态栏位：3-已审核
		//	adPacakgeTmp.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_CHECK_PASSED);

			// 广告包路径-ftp路径,只存放广告包名
		//	adPacakgeTmp.setAdPackagePath(filename);

		//	this.saveAdPackage(adPacakgeTmp);

		//} catch (Exception e) {
		//	log.error("update data error", e);

		//	return "";
		//}

		return null;

	}

	/**
	 * 创建xml的Document
	 * 
	 * @author 陈然
	 * @param adPacakgeTmp
	 * @return
	 */
	private Document createAdDocument(AdPackage adPacakgeTmp) {

		// 创建文档
		Document document = DocumentHelper.createDocument();
		// 创建根元素
		Element batch = document.addElement("Batch");
		batch.addAttribute("No", adPacakgeTmp.getAdPackageBatchNo());

		// 批次号首字母，用以判断广告类型
		String batchNoPre = "";
		if (StringUtils.isNotEmpty(adPacakgeTmp.getAdPackageBatchNo())) {
			batchNoPre = adPacakgeTmp.getAdPackageBatchNo().substring(0, 1);
		}

		// 普通广告 - EndTime不要时分秒
		if (Constant.AD_PACKAGE_BATCH_B.equals(batchNoPre)) {
			batch.addAttribute("EndDate", adPacakgeTmp.getAdPackageEndtimeDsp());
		} else {// 其他广告- EndTime需要时分秒
			batch.addAttribute("EndDate", adPacakgeTmp.getAdPackageEndtimeDsp2());
		}

		batch.addAttribute("PublicDate", adPacakgeTmp.getAdPackageBegintimeDsp2());// 开始日期
		
		//循环播放
		if(adPacakgeTmp.getAdPackageLoopType() == 0){
			batch.addAttribute("Loop", "false");
		}else{
			batch.addAttribute("Loop", "true");
		}

		// 添加子节点Page界面,需要判断区块数
		Element Page = batch.addElement("Page");

		// 添加广告素材信息
		this.addPicMovieElement(Page, adPacakgeTmp);

		// 加上文字广告信息(文字信息非空&&不是应用广告)
		if (StringUtils.isNotEmpty(adPacakgeTmp.getAdPackageTips()) && !Constant.AD_TEMPLATE_APP.equals(String.valueOf(adPacakgeTmp.getAdTemplateId()))) {
			this.addTipsElement(Page, adPacakgeTmp);
		}

		// Batch添加子节点LandLayout
//		Element landLayout = batch.addElement("LandLayout");

		// 应用广告 -不存在<PlayArea>和<Advertisement>节点，替换 为 <EmbedAdvertisement>节点
//		if (Constant.AD_TEMPLATE_APP.equals(String.valueOf(adPacakgeTmp.getAdTemplateId()))) {
//			landLayout.addElement("EmbedAdvertisement");
//		} else {
//			Element playArea = landLayout.addElement("PlayArea");
//			playArea.addElement("Advertisement");
//		}

		return document;

	}

	/**
	 * 添加广告文字信息
	 * 
	 * @author 陈然
	 * @param layOut
	 * @param adPacakgeTmp
	 */
	private void addTipsElement(Element layOut, AdPackage adPacakgeTmp) {

		Element playArea_word = layOut.addElement("PlayArea");

		playArea_word.addAttribute("Location", Constant.AD_PLAYAREA_LOCATION_1);
		playArea_word.addAttribute("Height", Constant.AD_TIPS_HEIGHT);
		playArea_word.addAttribute("Width", Constant.AD_TIPS_WIDTH);

		Element advertisement_word = playArea_word.addElement("Advertisement");
		Element id = advertisement_word.addElement("Id");
		id.setText("0");

		advertisement_word.addElement("Product");

		Element contentType = advertisement_word.addElement("ContentType");
		contentType.setText("0");

		Element duration = advertisement_word.addElement("Duration");
		duration.setText("0");

		Element dayPlayTime = advertisement_word.addElement("DayPlayTime");
		dayPlayTime.setText("0");

		advertisement_word.addElement("InTime");

		advertisement_word.addElement("OutTime");

//		advertisement_word.addElement("IntentAction");

//		advertisement_word.addElement("IntentData");

		Element content = advertisement_word.addElement("Content");
		content.setText(getString(adPacakgeTmp.getAdPackageTips()));

		advertisement_word.addElement("File");
	}

	/**
	 * 添加广告素材信息
	 * 
	 * @author 陈然
	 * @param layOut
	 * @param adPacakgeTmp
	 */
	private void addPicMovieElement(Element layOut, AdPackage adPacakgeTmp) {
		
		//查询多媒体资源列表
		//addWidgetElement()
		
		// 查询广告资源信息列表
		List<AdResource> adResourceListAll = this.listAdResourceByAdPackageId(adPacakgeTmp.getAdPackageId());
		if (adResourceListAll != null && adResourceListAll.size() > 0) {
			addPlayAreaElement(layOut, Constant.AD_PLAYAREA_LOCATION_1,
					String.valueOf(Constant.AD_SIZE_COMMON_SINGLE_HEIGHT),
					String.valueOf(Constant.AD_SIZE_WIDTH),
					adResourceListAll);
		}
		

		String adTemplateId = String.valueOf(adPacakgeTmp.getAdTemplateId());

		// 6-紧急广告-图片广告
		if (Constant.AD_TEMPLATE_URGENCY_PIC.equals(adTemplateId)) {

			// 查询广告资源信息列表
			List<AdResource> adResourceListUrgency = this
					.listAdResourceByAdPackageIdUrgency(adPacakgeTmp
							.getAdPackageId());

			if (adResourceListUrgency != null
					&& adResourceListUrgency.size() > 0) {

				addPlayAreaElement(layOut, Constant.AD_PLAYAREA_LOCATION_1,
						String.valueOf(Constant.AD_SIZE_COMMON_SINGLE_HEIGHT),
						String.valueOf(Constant.AD_SIZE_WIDTH),
						adResourceListUrgency);

			}
		}
	}

	/**
	 * 添加PlayArea标签
	 * 
	 * @param layOut
	 *            父标签
	 * @param location
	 *            playArea的开始位置
	 * @param height
	 *            素材高度
	 * @param width
	 *            素材宽度
	 * @param adResourceListTmp
	 *            可处理的List
	 */
	private void addPlayAreaElement(Element layOut, String location,
			String height, String width, List<AdResource> adResourceListTmp) {

		Element playArea = layOut.addElement("PlayArea");
		playArea.addAttribute("Location", location);
		playArea.addAttribute("Height", height);
		playArea.addAttribute("Width", width);

		addAdvertisementElement(playArea, adResourceListTmp, false);
	}
	
	@SuppressWarnings("unused")
	private void addWidgetElement(Element layOut, String location,
			String height, String width, List<AdResource> adResourceListTmp) {
		Element widget = layOut.addElement("Widget");

		Element date = widget.addElement("Date");
		widget.addAttribute("Location", "");
		widget.addAttribute("Heigth", "");
		widget.addAttribute("Width", "");
		widget.addAttribute("Style", "");
		
		Element time = widget.addElement("Time");
	}

	/**
	 * 将列表信息添加到playArea中
	 * 
	 * @param parentTag
	 *            父标签
	 * @param adResourceListTmp
	 *            可处理的List
	 * @param appFlag
	 *            是否为APP应用广告标记
	 */
	private void addAdvertisementElement(Element parentTag,
			List<AdResource> adResourceListTmp, boolean appFlag) {
		int listLen = adResourceListTmp.size();
		for (int i = 0; i < listLen; i++) {
			AdResource adResourceTmp = adResourceListTmp.get(i);

			Element advertisement = null;

			// 应用广告，标签不同
			if (appFlag) {
				advertisement = parentTag.addElement("EmbedAdvertisement");
			} else {
				advertisement = parentTag.addElement("Advertisement");

				Element contentType = advertisement.addElement("ContentType");
				contentType.setText(getString(adResourceTmp.getAdResourceTypes()));

				Element intentAction = advertisement.addElement("IntentAction");
				intentAction.setText(getString(adResourceTmp.getAdResourceIntent()));

				Element intentData = advertisement.addElement("IntentData");
				intentData.setText(getString(adResourceTmp.getAdResourceAction()));

				Element content = advertisement.addElement("Content");
				content.setText(getString(adResourceTmp.getAdResourceTips()));

			}

			Element id = advertisement.addElement("Id");
			id.setText(getString(adResourceTmp.getAdResourceId()));

			Element product = advertisement.addElement("Product");
			product.setText(getString(adResourceTmp.getAdResourceProduct()));

			Element duration = advertisement.addElement("Duration");
			duration.setText(getString(adResourceTmp.getAdResourceTime()));

			Element dayPlayTime = advertisement.addElement("DayPlayTime");
			dayPlayTime.setText(getString(adResourceTmp.getAdResourceCount()));

//			Element inTime = advertisement.addElement("InTime");
//			inTime.setText(getString(adResourceTmp.getAdResourceIntime()));
//
//			Element outTime = advertisement.addElement("OutTime");
//			outTime.setText(getString(adResourceTmp.getAdResourceOuttime()));

			String adResourceBgMusic = adResourceTmp.getAdResourceMusic() == null ? "" : adResourceTmp.getAdResourceMusic();
			adResourceBgMusic = adResourceBgMusic.substring(adResourceBgMusic.lastIndexOf(File.separator) + 1);
			Element bgMusic = advertisement.addElement("BgMusic");
			bgMusic.setText(adResourceBgMusic);
			
			String adResourceSlideAnim = adResourceTmp.getAdResourceTransitions() == null ? "-1" : adResourceTmp.getAdResourceTransitions();
			Element slideAnim = advertisement.addElement("SlideAnim");
			slideAnim.setText(adResourceSlideAnim);
			
			String adResourceFile = adResourceTmp.getAdResourcePath() == null ? "" : adResourceTmp.getAdResourcePath();
			adResourceFile = adResourceFile.substring(adResourceFile.lastIndexOf(File.separator) + 1);
			Element file = advertisement.addElement("File");
			file.setText(adResourceFile);

		}
	}

	/**
	 * 创建并写入xml文档
	 * 
	 * @param srcPathName
	 * @param document
	 * @return
	 */
	private boolean createAdxml(String srcPathName, Document document) {
		// 先判断文件夹是否存在，若不存在则新建
		File f1 = new File(srcPathName);
		if (!f1.exists()) {
			f1.mkdirs();
		}

		// 写入XML文档
		OutputFormat format = OutputFormat.createPrettyPrint();

		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		XMLWriter xmlWriter = null;
		try {
			fileOutputStream = new FileOutputStream(srcPathName + File.separator + "advertisements.xml");

			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");

			xmlWriter = new XMLWriter(outputStreamWriter, format);
			xmlWriter.write(document); // 写入文件中
		} catch (IOException ie) {
			log.error("create xml error", ie);
			return false;
		} finally {
			if (xmlWriter != null) {
				try {
					xmlWriter.close();
				} catch (IOException e) {
					log.error("close XMLWriter error", e);
				}
			}
			if (outputStreamWriter != null) {
				try {
					outputStreamWriter.close();
				} catch (IOException e) {
					log.error("close OutputStreamWriter error", e);
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					log.error("close FileOutputStream error", e);
				}
			}
		}
		return true;
	}

	/**
	 * ftp上传
	 * 
	 * @author 陈然
	 * @param filename
	 * @param zipPathName
	 * @return
	 * @throws Exception
	 */
	/*
	 * public void ftpUpload(String filename, String zipPathName) throws
	 * Exception {
	 * 
	 * // ftp上传信息设置 String url = PropertyUtil.readData("ftpUrl"); String strport
	 * = PropertyUtil.readData("ftpPort"); String username =
	 * PropertyUtil.readData("ftpUserName"); String password =
	 * PropertyUtil.readData("ftpPassword"); String path =
	 * PropertyUtil.readData("advertiseUploadPath");// 广告包上传路径 int port =
	 * Integer.parseInt(strport); File file = new File(zipPathName + ".zip");
	 * InputStream input = null;
	 * 
	 * input = new FileInputStream(file);
	 * 
	 * // ftp上传 FtpUtil.uploadFile(url, port, username, password, path,
	 * filename, input);
	 * 
	 * // 上传完毕 删除本地压缩包 if (file.exists()) { file.delete(); }
	 * 
	 * }
	 */

	/**
	 * 文件上传
	 * 
	 * @param filename
	 * @param zipPathName
	 * @throws Exception
	 */
	public void fileUpload(String filename, String zipPathName)
			throws Exception {

		File file = new File(zipPathName + ".zip");
		String path = PropertyUtil.readData("advertiseUploadPath");// 广告包上传路径
		InputStream input = null;
		input = new FileInputStream(file);
		// 文件上传
		FileUpload.upToFileServer(input, path, filename);

	}

	/**
	 * 推送
	 * 
	 * @param adPackage
	 * @return
	 */
	public boolean push(AdPackage adPackage) {

		boolean pushResultFlag = true;

		try {
			// 获取推送数据
			String json = getPushJson(adPackage);
			// 推送
			String address = PropertyUtil.readData("webservice_wsdl");

			// 有执行时间
			// String now = DateUtil.getDateYYYY_MM_DD_HH_MM_SS(new Date());
			// Object result = WsClientUtil.callCXFWservice(address,
			// Constant.NOTIFY_NEW_MESSAGE_FOR_TIME, new Object[] {
			// UUIDGenerator.getUUID(), json, true,now });

			// 无执行时间
			Object result = WsClientUtil.callCXFWservice(address,
					Constant.NOTIFY_NEW_MESSAGE,
					new Object[] { UUIDGenerator.getUUID(), json, true });

			// 若有返回非空值，则表示推送失败
			if (result != null) {
				log.error("push error");
				pushResultFlag = false;

			}
		} catch (Exception e) {
			log.error("push error", e);
			pushResultFlag = false;
		}

		return pushResultFlag;
	}

	/**
	 * 设置要推送的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getPushJson(AdPackage adPackage) throws Exception {
		// 设置push信息
		// 广告包路径
		String uri = PropertyUtil.readData("advertiseUploadPath")
				+ File.separator + adPackage.getAdPackageBatchNo() + ".zip";

		// ftp信息设置
		// String url = PropertyUtil.readData("ftpUrl");
		// String strport = PropertyUtil.readData("ftpPort");
		// String username = PropertyUtil.readData("ftpUserName");
		// String password = PropertyUtil.readData("ftpPassword");
		// String path = PropertyUtil.readData("advertiseUploadPath");// 广告包上传路径
		// int port = Integer.parseInt(strport);
		//
		// // 从ftp获取文件大小
		// long fileSize = FtpUtil.getFileSize(url, port, username, password,
		// path, adPackage.getAdPackageBatchNo() + ".zip");

		// 从文件服务器获取fileSize
		String fileName = adPackage.getAdPackageBatchNo() + ".zip";

		// long fileSize = FileUpload.getFileSize(path, fileName);
		// 直接从工程中获取
		String zipFilePath = ServletActionContext.getServletContext()
				.getRealPath(File.separator)
				+ Constant.AD_FILE_UPLOAD_FOLDER
				+ File.separator
				+ PropertyUtil.readData("advertiseUploadPath")
				+ File.separator + fileName;
		File zipFile = new File(zipFilePath);
		long fileSize = zipFile.length();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", Constant.CLIENT_TYPE_AD);
		map.put("action", Constant.CLIENT_ACTION_AD_ADD);
		map.put("batchNo", adPackage.getAdPackageBatchNo());
		map.put("fileSize", String.valueOf(fileSize));
		map.put("uri", URLEncoder.encode(uri, "UTF-8"));

		String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(map);

		return json;
	}

	/**
	 * 返回字符串
	 * 
	 * @param obj
	 * @return
	 */
	private String getString(Object obj) {
		if (obj != null) {
			return obj.toString();
		}
		return "";
	}

	/**
	 * 判断批次号是否存在
	 * 
	 * @param imei
	 * @return
	 */
	public boolean findAdPackageInfoByBatchNo(String adPackageBatchNo) {
		int num = adPackageDao.findAdPackageInfoByBatchNo(adPackageBatchNo);
		if (num < 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 紧急广告：根据广告包ID查询广告资料列表
	 * 
	 * @param adPackageId
	 * @return
	 */
	public List<AdResource> listAdResourceByAdPackageIdUrgency(
			String adPackageId) {

		return adResourceDao.listAdResourceByAdPackageIdUrgency(adPackageId);

	}

	/**
	 * 应用广告：根据广告包ID查询广告资料列表
	 * 
	 * @param adPackageId
	 * @return
	 */
	public List<AdResource> listAdResourceByAdPackageIdApp(String adPackageId) {

		return adResourceDao.listAdResourceByAdPackageIdApp(adPackageId);

	}

	/**
	 * 查询某一类型的广告发布结束时间的最大值
	 * 
	 * @param adPackageType
	 * @return
	 */
	public int judgeAdTimeIsOverlap(AdPackage adPackage) {
		return adPackageDao.judgeAdTimeIsOverlap(adPackage);
	}
	
	/**
	 * 删除广告包
	 * 
	 * @param adPackageId
	 * @return
	 */
	public boolean delAdPackage(AdPackage adPackage) {
		//改变PACKAGE中数据状态为“1-无效”
		this.saveAdPackage(adPackage);
		//改变RESOURCE表中的数据状态
		return this.delResourceByAdpackageId(adPackage.getAdPackageId());

	}

	/**
	 * 根据广告包ID删除广告包
	 * 
	 * @param adPackageId
	 * @return
	 */
	public boolean delResourceByAdpackageId(String adPackageId) {
		return adResourceDao.delResourceByAdpackageId(adPackageId);

	}
	
	/**
	 * 根据查询条件分页查询广告包信息
	 * 
	 * @param status
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<AdPackage> listAdByStatusAndPage(int adPackageStatus, int pageNum, int pageSize) {
		return adPackageDao.listAdByStatusAndPage(adPackageStatus, pageNum, pageSize);
	}
	
	/**
	 * 根据查询条件查询待审核广告包总数
	 * 
	 * @param status
	 * @return
	 */
	public int getAdCountByAdStatus(int adPackageStatus) {

		return adPackageDao.getAdCountByStatus(adPackageStatus);
	}
	
	/**
	 * 根据查询条件查询广告包信息
	 * 
	 * @param status
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Object> listAdByStatus(int adPackageStatus) {
		return adPackageDao.listAdByStatus(adPackageStatus);
	}
	
	/**
	 * 根据查询条件查询广告包信息
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<AdPackage> getConflictAdPackageByTime(Date startTime,Date endTime){
		return adPackageDao.findConflictAdPackageByTime(startTime, endTime);
	}
	
	/**
	 * 
	 * @param adPackageId
	 * @param imei
	 * @return
	 */
	public EtbClientAdDevice getAdDeviceByAdPackageIdAndImei(String adPackageId, String imei){
		return etbClientAdDeviceDao.findAdDeviceByAdPackageIdAndImei(adPackageId, imei);
	}
	
	/**
	 * 
	 * @param adPackageId
	 * @return
	 */
	public List<EtbClientAdDevice> getAdDeviceByAdPackageId(String adPackageId){
		return etbClientAdDeviceDao.findAdDeviceByAdPackageId(adPackageId);
	}
	
	public void saveOrUpdateAdDevice(EtbClientAdDevice adDevice){
		etbClientAdDeviceDao.saveOrUpdate(adDevice);
	}
}
