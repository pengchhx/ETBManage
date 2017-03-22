package com.archermind.etb.ad.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.ad.domain.AdPackage;
import com.archermind.etb.ad.domain.AdResource;
import com.archermind.etb.ad.domain.AdTemplate;
import com.archermind.etb.ad.service.AdService;
import com.archermind.etb.common.BaseAction;
import com.archermind.etb.common.UUIDGenerator;
import com.archermind.etb.device.domain.EtbAreaCode;
import com.archermind.etb.device.domain.EtbClientAdDevice;
import com.archermind.etb.device.domain.EtbClientDevice;
import com.archermind.etb.device.service.EtbAreaCodeService;
import com.archermind.etb.device.service.EtbClientDeviceService;
import com.archermind.etb.user.domain.UserInfo;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.DateUtil;
import com.archermind.etb.util.ImageCompressUtil;
import com.archermind.etb.util.JsonHelper;
import com.archermind.etb.util.PropertyUtil;
import com.archermind.etb.util.StringUtil;
import com.archermind.etb.util.VedioConvert;
import com.google.gson.Gson;

/**
 * 广告管理Action
 * 
 * @author 003383 陈然
 * @version v1.0,2013.07.11
 * @see
 * @since v1.0
 */

@Controller
@Scope("prototype")
@Namespace("/ad")
@Action(value = "adPackage", results = {
		@Result(name = "listAd", location = "ad_list.jsp"),
		@Result(name = "listAdCheck", location = "ad_check_list.jsp"),
		@Result(name = "listAdPublish", location = "ad_publish_list.jsp"),
		@Result(name = "adTypeSelect", location = "ad_select_type.jsp"),
		@Result(name = "adTemplateList", location = "ad_template_list.jsp"),
		@Result(name = "addAdCommon", location = "ad_editor_common.jsp"),
		@Result(name = "toAddUrgency", location = "ad_editor_urgency.jsp"),
		@Result(name = "addAdApp", location = "ad_editor_app.jsp"),
		@Result(name = "toUploadPicCommonList", location = "ad_file_common_list.jsp"),
		@Result(name = "toUploadPicCommonDoubleList", location = "ad_file_common_double_list.jsp"),
		@Result(name = "toUploadPicUrgencyList", location = "ad_file_urgency_list.jsp"),
		@Result(name = "toUploadPicAppList", location = "ad_file_app_list.jsp"),
		@Result(name = "toUpload", location = "ad_upload_common.jsp"),
		@Result(name = "toUploadUrgency", location = "ad_upload_urgency.jsp"),
		@Result(name = "toUploadApp", location = "ad_upload_app.jsp"),
		@Result(name = "adView", location = "ad_view.jsp"),
		@Result(name = "adViewDouble", location = "ad_view_double.jsp"),
		@Result(name = "adViewUrgency", location = "ad_view_urgency.jsp"),
		@Result(name = "adViewApp", location = "ad_view_app.jsp"),
		@Result(name = "adModify", location = "ad_modify.jsp"),
		@Result(name = "adModifyResourceList", location = "ad_modify_resource_list.jsp"),
		@Result(name = "adModifyDouble", location = "ad_modify_double.jsp"),
		@Result(name = "adModifyResourceListDouble", location = "ad_modify_resource_list_double.jsp"),
		@Result(name = "adModifyUrgency", location = "ad_modify_urgency.jsp"),
		@Result(name = "adModifyResourceListUrgency", location = "ad_modify_resource_list_urgency.jsp"),
		@Result(name = "adModifyApp", location = "ad_modify_app.jsp"),
		@Result(name = "adModifyResourceListApp", location = "ad_modify_resource_list_app.jsp"),
		@Result(name = "toAdResourceModify", location = "ad_resource_modify.jsp"),
		@Result(name = "toUrgencyAdResourceModify", location = "ad_resource_modify_urgency.jsp"),
		@Result(name = "toAppAdResourceModify", location = "ad_resource_modify_app.jsp"),
		@Result(name = "checkAd", location = "ad_check.jsp"),
		@Result(name = "checkAdDouble", location = "ad_check_double.jsp"),
		@Result(name = "checkAdUrgency", location = "ad_check_urgency.jsp"),
		@Result(name = "checkAdApp", location = "ad_check_app.jsp"),
		@Result(name = "adCheckSuggest", location = "ad_check_suggest.jsp"),
		@Result(name = "adPublish", location = "ad_publish.jsp"),
		@Result(name = "adPublishDouble", location = "ad_publish_double.jsp"),
		@Result(name = "adPublishUrgency", location = "ad_publish_urgency.jsp"),
		@Result(name = "adPublishApp", location = "ad_publish_app.jsp"),
		
		@Result(name = "adHbTemplateList", location = "ad_hb_template.jsp"),
		@Result(name = "adSbTemplateList", location = "ad_sb_template.jsp"),
		@Result(name = "adScrollTemplateList", location = "ad_scroll_template.jsp"),
		@Result(name = "adCheckList", location = "ad_check_list.jsp"),
		@Result(name = "adCheckHb", location = "ad_check_hb.jsp"),
		@Result(name = "adCheckSb", location = "ad_check_sb.jsp"),
		@Result(name = "adCheckText", location = "ad_check_text.jsp"),
		@Result(name = "adReleaseRecord", location = "ad_release_record.jsp"),
		@Result(name = "adRelease", location = "ad_release.jsp"),
		@Result(name = "adReleaseHb", location = "ad_release_hb.jsp"),
		@Result(name = "adReleaseSb", location = "ad_release_sb.jsp"),
		@Result(name = "adReleaseText", location = "ad_release_text.jsp"),
		@Result(name = "adList", location = "ad_list.jsp")
	})
public class AdAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志
	 */
	private static final Logger log = Logger.getLogger(AdAction.class);

	/**
	 * 上传资源存放路径
	 */

	private static final String adUploadPath = PropertyUtil
			.readData("advertiseUploadPath");

	/**
	 * 上传资源缩略图存放路径
	 */

	private static final String adUploadThumbnailPath = Constant.AD_FILE_THUMBNAIL_FOLDER;

	/**
	 * 广告包信息
	 */
	private AdPackage adPackage;

	/**
	 * 广告包资源信息
	 */
	private AdResource adResource;

	/**
	 * 广告包模板
	 */
	private AdTemplate adTemplate;

	/**
	 * 广告包信息集合
	 */
	private List<Map<String, Object>> adList;

	/**
	 * 广告模板集合
	 */
	private List<AdTemplate> adTemplateList;

	/**
	 * 上传文件，和JSP中input标记name同名
	 */
	private File fileupload;

	/**
	 * 广告资源集合：单屏
	 */
	private List<AdResource> adResourceList;

	/**
	 * 广告资源集合：双屏->上半区域
	 */
	private List<AdResource> adResourceDoubleUpperList;

	/**
	 * 广告资源集合:双屏->下半区域
	 */
	private List<AdResource> adResourceDoubleLowerList;

	/**
	 * 选中资源Id
	 */
	private long adResourceId;

	/**
	 * 双屏，区域标志
	 */
	private String areaFlag;

	/**
	 * 广告类型 ：1-普通广告；2-应用广告；3-紧急广告；
	 */
	private int adStyle;

	/**
	 * 广告类型
	 */
	private String adType;

	/**
	 * 上传来的文件的名字 Struts2拦截器获得的文件名,命名规则，File的名字+FileName 如此处为 'fileupload' +
	 * 'FileName' = 'fileuploadFileName'
	 */
	private String fileuploadFileName;

	/**
	 * 保存上传文件新的文件名
	 */
	String newFileName = "";

	/**
	 * 保存上传文件缩略图的文件名
	 */
	String newThumbnailName = "";

	/**
	 * 保存当前时间
	 */
	String nowTimeStr = "";
	
	String resourceType = "";

	/**
	 * 错误提示信息
	 */
	private String errMsg = "";

	/**
	 * 图片素材的数量for上传图片数量限定,2013-10-31,RanChen,start
	 */
	private int picNum = 0;

	/**
	 * 是否关闭素材上传窗口标记for上传图片数量限定,2013-10-31,RanChen,start
	 */
	private boolean closeDialogFlag = false;

	/**
	 * 声明AdService
	 */
	@Resource(name = "com.archermind.etb.ad.service.AdService")
	private AdService adService;
	
	/**
	 * 声明EtbAreaCodeService
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbAreaCodeService")
	private EtbAreaCodeService etbAreaCodeService;
	
	/**
	 * 声明EtbClientDeviceService
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbClientDeviceService")
	private EtbClientDeviceService etbClientDeviceService;
	
	/**
	 * 所有省份
	 */
	private List<EtbAreaCode> provinces;
//	private List<EtbAreaCode> citys;
//	private List<EtbAreaCode> countys;
	
	private List<EtbClientDevice> etbClientDeviceList;
	
	private List<String> selectDevice;
	
	private String areaCode;
	
	private String areaId;
	
	private String templateType;

	/**
	 * 查询广告包列表-广告包管理
	 * 
	 * @return
	 */
	public String listAd() {

		reAdPackage();

		this.totalCount = adService.getAllAdCountByAdName(adPackage
				.getAdPackageName());
		this.adList = adService.listAllAdByPage(adPackage.getAdPackageName(),
				this.numPerPage, this.getPageNum());

		return "listAd";
	}

	/**
	 * 查询广告包列表-广告包审核
	 * 
	 * @return
	 */
	public String listAdCheck() {

		reAdPackage();

		this.totalCount = adService.getAuditAdCountByAdName(adPackage.getAdPackageName());
		this.adList = adService.listAuditAdByPage(adPackage.getAdPackageName(), this.numPerPage, this.getPageNum());

		return "listAdCheck";
	}

	/**
	 * 查询广告包列表-广告包发布
	 * 
	 * @return
	 */
	public String listAdPublish() {

		reAdPackage();

		// 查询总笔数
		this.totalCount = adService.getAdPublishCountByAdName(adPackage.getAdPackageName());
		// 查询列表
		this.adList = adService.listAdPublishByPage(
				adPackage.getAdPackageName(), this.numPerPage,
				this.getPageNum());

		return "listAdPublish";
	}

	/**
	 * 进入新增画面：普通广告
	 * 
	 * @return
	 */
	public String toAddCommon() {

		reAdPackage();

		// 根据模板id查询模板名称
		if (adPackage.getAdTemplateId() > 0) {
			adTemplate = adService.getAdTemplateById(adPackage.getAdTemplateId());
			adPackage.setAdTemplateName(adTemplate.getAdTemplateName());
		}
		this.setAdStyle(this.getAdStyle());
		return "addAdCommon";
	}

	/**
	 * 进入新增画面：紧急广告
	 * 
	 * @return
	 */
	public String toAddUrgency() {

		reAdPackage();

		this.setAdStyle(Constant.AD_TEMPLATE_TYPE_URGENCY);

		return "toAddUrgency";
	}

	/**
	 * 进入新增画面：应用广告
	 * 
	 * @return
	 */
	public String toAddApp() {

		reAdPackage();

		// 根据模板id查询模板名称
		if (adPackage.getAdTemplateId() > 0) {
			adTemplate = adService.getAdTemplateById(adPackage.getAdTemplateId());
			adPackage.setAdTemplateName(adTemplate.getAdTemplateName());
		}
		this.setAdStyle(this.getAdStyle());
		return "addAdApp";
	}

	/**
	 * 进入新增类型选择画面
	 * 
	 * @return
	 */
	public String toTypeSelect() {
		reAdPackage();

		return "adTypeSelect";
	}

	/**
	 * 普通广告：进入广告包模板页面
	 * 
	 * @return
	 */
	public String toTemplateChoose() {

		reAdPackage();

		this.setAdStyle(Constant.AD_TEMPLATE_TYPE_COMMON);

		// 查询广告模板列表
		adTemplateList = adService.listAllAdTemplate(Constant.AD_TEMPLATE_TYPE_COMMON);

		return "adTemplateList";

	}

	/**
	 * 应用广告：进入广告包模板页面
	 * 
	 * @return
	 */
	public String toTemplateChooseApp() {

		reAdPackage();

		this.setAdStyle(Constant.AD_TEMPLATE_TYPE_APPLICATION);

		// 查询广告模板列表
		adTemplateList = adService.listAllAdTemplate(Constant.AD_TEMPLATE_TYPE_APPLICATION);

		return "adTemplateList";

	}

	/**
	 * 查看广告包明细
	 */
	public String toAdView() {

		return getInfo("adView", "adViewDouble", "adViewUrgency", "adViewApp");

	}

	/**
	 * 修改广告包明细
	 */
	public String toAdModify() {

		reAdPackage();
		if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {

			// 查询广告包基本信息
			adPackage = adService.getAdPackageById(adPackage.getAdPackageId());

			String adPackageBatchNo = adPackage.getAdPackageBatchNo();
			if (StringUtils.isNotEmpty(adPackageBatchNo)) {
				adPackage.setAdPackageBatchNoInitial(adPackageBatchNo.substring(0, 1));// 截取首字母
				adPackage.setAdPackageBatchNoInput(adPackageBatchNo.substring(1));// 截取输入栏位
			}

			String adType = getAdType(adPackage);
			this.setAdType(adType);

			// 普通广告
			if (Constant.AD_PACKAGE_BATCH_B.equals(adType)) {

				// 设置时间
				adPackage.setAdPackageBegintime1(DateUtil.getDateYYYY_MM_DD(adPackage.getAdPackageBegintime()));
				adPackage.setAdPackageEndtime1(DateUtil.getDateYYYY_MM_DD(adPackage.getAdPackageEndtime()));

				// 获取普通画面信息，并返回到普通画面，"adModify"为单屏；"adModifyDouble"为双屏
				return toAdCommon(adPackage, "adModify", "adModifyDouble");

			}
			// 紧急广告:根据文字通知adPackageTips是否为空来判断是文字广告还是图片广告
			else if (Constant.AD_PACKAGE_BATCH_U.equals(adType)) {

				adPackage.setAdPackageBegintime1(DateUtil.getDateYYYY_MM_DD_HH_MM_SS(adPackage.getAdPackageBegintime()));
				adPackage.setAdPackageEndtime1(DateUtil
						.getDateYYYY_MM_DD_HH_MM_SS(adPackage
								.getAdPackageEndtime()));
				return "adModifyUrgency";
			}
			// 应用广告
			else if (Constant.AD_PACKAGE_BATCH_E.equals(adType)) {

				adPackage.setAdPackageBegintime1(DateUtil
						.getDateYYYY_MM_DD(adPackage.getAdPackageBegintime()));
				adPackage.setAdPackageEndtime1(DateUtil
						.getDateYYYY_MM_DD(adPackage.getAdPackageEndtime()));

				return "adModifyApp";
			}
		}

		return "adModify";
	}
	
	/**
	 * 删除广告
	 */
	public String toAdDelete(){
		reAdPackage();
		String adPackageId = adPackage.getAdPackageId();
		String operMsg="";
		int statusCode=Constant.HTTP_STATUS_OK;
		if(StringUtils.isNotEmpty(adPackageId)){
			adPackage = adService.getAdPackageById(adPackageId);
			
			if(adPackage !=null){
				
				adPackage.setDataStat(Constant.DATA_STAT_OFF);	
				
				try {
					//Db变更
					boolean flag = adService.delAdPackage(adPackage);
					
					//DB变更成功
					if(flag){
						//删除目录结构中的资源信息
						 fileFolderRemove(adPackageId, adUploadPath);
						
						// 删除目录结构中视频缩略图素材
						fileFolderRemove(adPackageId, adUploadThumbnailPath);
						
						//删除目录结构中的广告压缩包
						String adFileZipPath = Constant.AD_FILE_UPLOAD_FOLDER
								+ File.separator+ adUploadPath
								+ File.separator+ adPackage.getAdPackageBatchNo() + ".zip";

						File zipfile = new File(ServletActionContext.getServletContext()
								.getRealPath(File.separator) + adFileZipPath);

						StringUtil.deleteAllFiles(zipfile);
					}
					
					operMsg=Constant.DELETE_SUCCESS;
					statusCode=Constant.HTTP_STATUS_OK;
				} catch (Exception e) {
					log.error("delete resource file failed", e);
					operMsg=Constant.DELETE_FAILED;
					statusCode=Constant.HTTP_STATUS_FAILED;
				}
				
				this.writeDwzResultToResponse(operMsg,
						statusCode, "listAd", null, null,null);
			}
		}
		
		return null;
	}

	/**
	 * 修改模块，加载资源列表部分
	 * 
	 * @return
	 */
	public String toAdModifyResourceList() {
		reAdPackage();
		String adTypeTmp = this.getAdType();
		String adTemplateIdTmp = String.valueOf(adPackage.getAdTemplateId());
		if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {
			// 普通广告
			if (Constant.AD_PACKAGE_BATCH_B.equals(adTypeTmp)) {

				// 单屏
				if (Constant.AD_TEMPLATE_COMMON_SINGLE.equals(adTemplateIdTmp)) {
					if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {
						adResourceList = adService
								.listAdResourceByAdPackageId(adPackage
										.getAdPackageId());
					}

					return "adModifyResourceList";
				}

				// 双屏
				else if (Constant.AD_TEMPLATE_COMMON_DOUBLE
						.equals(adTemplateIdTmp)) {
					if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {

						// 查询广告资源信息列表-上屏
						adResourceDoubleUpperList = adService
								.listAdResourceByAdPackageIdAndPosition(
										adPackage.getAdPackageId(),
										Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER);

						// 查询广告资源信息列表-下屏
						adResourceDoubleLowerList = adService
								.listAdResourceByAdPackageIdAndPosition(
										adPackage.getAdPackageId(),
										Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER);
					}

					return "adModifyResourceListDouble";

				}

				// 紧急广告
			} else if (Constant.AD_PACKAGE_BATCH_U.equals(adTypeTmp)) {
				if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {
					// 查询广告资源信息列表
					adResourceList = adService
							.listAdResourceByAdPackageIdUrgency(adPackage
									.getAdPackageId());
				}

				return "adModifyResourceListUrgency";
				// 应用广告
			} else if (Constant.AD_PACKAGE_BATCH_E.equals(adTypeTmp)) {
				if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {

					adResourceList = adService
							.listAdResourceByAdPackageIdApp(adPackage
									.getAdPackageId());
				}

				return "adModifyResourceListApp";
			}
		}
		return null;
	}

	/**
	 * 普通广告：文档上传页面-查看页面点选“修改”按钮
	 * 
	 * @return
	 */
	public String toAdResourceModify() {

		return initAdResourceModify("toAdResourceModify", true);

	}

	/**
	 * 紧急广告：文档上传页面-查看页面点选“修改”按钮
	 * 
	 * @return
	 */
	public String toUrgencyAdResourceModify() {

		return initAdResourceModify("toUrgencyAdResourceModify", false);

	}

	/**
	 * 应用广告：文档上传页面-查看页面点选“修改”按钮
	 * 
	 * @return
	 */
	public String toAppAdResourceModify() {

		return initAdResourceModify("toAppAdResourceModify", false);

	}

	/**
	 * 初始化修改功能-素材修改新增画面
	 * 
	 * @param sight
	 *            返回画面
	 * @param setPositionFlag
	 *            是否设置素材位置标记
	 * @return
	 */
	public String initAdResourceModify(String sight, boolean setPositionFlag) {

		reAdPackage();
		adResource = (adResource == null) ? new AdResource() : adResource;

		// 判断是否可以修改
		String errMsgTmp = checkModifyEnable(adResource.getAdPackageId());

		if (!"".equals(errMsgTmp)) {
			this.errMsg = errMsgTmp;
			return sight;
		}

		// 获取图片素材的数量,2013-10-30,RanChen,start
		picNum = adService.getAdResourceCountByAdpackageIdAndAdResourceType(
				adResource.getAdPackageId(), Constant.AD_RESOURCE_IMG);
		// 获取图片素材的数量,2013-10-30,RanChen,end

		// 获取广告资源信息-画面显示
		if (adResource.getAdResourceId() > 0) {// 修改功能
			adResource = adService.getAdResourceById(adResource
					.getAdResourceId());

			String resourcePath = adResource.getAdResourcePath();
			if (StringUtils.isNotEmpty(resourcePath)) {
				this.setFileuploadFileName(resourcePath);
			}
		} else if (setPositionFlag
				&& StringUtils.isNotEmpty(adResource.getAdPackageId())) {// 新增功能
			// 点击添加button时 有加入条件（0-全屏；1-上屏；2-下屏）
			adResource.setAdResourcePositionSign(areaFlag);
			this.setFileuploadFileName("");
		}

		return sight;
	}

	/**
	 * 修改广告包信息
	 * 
	 * @return
	 */
	public String modifyAdPackage() {

		reAdPackage();

		if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {

			// 设置批次号
			adPackage.setAdPackageBatchNo(adPackage
					.getAdPackageBatchNoInitial()
					+ adPackage.getAdPackageBatchNoInput());

			// 日期类型转换
			dateTypeConvert(this.getAdType());

			adService.saveAdPackage(adPackage);
		}

		// this.writeDwzResultToResponse(Constant.UPDATE_SUCCESS,
		// Constant.HTTP_STATUS_OK, "listAd", null, "closeCurrent", null);
		this.writeDwzResultToResponse(Constant.UPDATE_SUCCESS,
				Constant.HTTP_STATUS_OK, "adModify", "listAd", null,
				"closeNavTabId", null);

		return null;
	}

	/**
	 * 修改素材设置信息
	 * 
	 * @return
	 */
	public String modifyAdResourceInfo() {

		adResource = (adResource == null) ? new AdResource() : adResource;

		try {
			if (adResource.getAdResourceId() > 0) {
				// 修改广告资源信息,广告包信息
				adService.updateAdPackageAndAdResource(adResource);
				this.writeDwzResultToResponse(Constant.UPDATE_SUCCESS,
						Constant.HTTP_STATUS_OK, "adModify", null,
						"closeCurrent", null);
			} else {
				this.writeDwzResultToResponse(Constant.UPDATE_FAILED,
						Constant.HTTP_STATUS_FAILED, null, null, null, null);
			}
		} catch (Exception e) {
			log.error("update DB resource failure", e);
			this.writeDwzResultToResponse(Constant.UPDATE_FAILED,
					Constant.HTTP_STATUS_FAILED, null, null, null, null);
		}
		return null;
	}

	/**
	 * 修改素材信息
	 * 
	 * @return
	 */
	public String modifyAdResource() {
		reAdPackage();
		adResource = (adResource == null) ? new AdResource() : adResource;
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean updFlag = false;
		Map<String, Object> map = new HashMap<String, Object>();

		String adTemplateIdTmp = request.getParameter("adTemplateId");
		String adTypeTmp = request.getParameter("adType");

		// 获取文件后缀名
		String postfix = fileuploadFileName.substring(fileuploadFileName
				.lastIndexOf("."));
		String uuid = UUIDGenerator.getUUID();
		// 新文件名
		newFileName = uuid + postfix;

		// 缩略图名称
		newThumbnailName = uuid + Constant.AD_FILE_THUMBNAIL_POSTFIX;

		String addOrUpdateFlag = Constant.ADD_MODIFY_FLAG_ADD;// 0-新增

		// 设置广告资源信息
		String adResourceId = request.getParameter("adResourceId");
		// 若不为空， 则表示修改
		if (Integer.valueOf(adResourceId) > 0) {
			adResource.setAdResourceId(Integer.valueOf(adResourceId));
			addOrUpdateFlag = Constant.ADD_MODIFY_FLAG_MODIFY;// 1-修改
		}
		try {
			String adPackageId = request.getParameter("adPackageId");

			this.setAdResource(adResource, request);

			// 数据状态
			String dataStat = request.getParameter("dataStat");
			if (StringUtils.isNotEmpty(dataStat)) {
				adResource.setDataStat(Integer.valueOf(dataStat));
			} else {
				adResource.setDataStat(Constant.DATA_STAT_ON);
			}

			// 屏幕位置
			adResource.setAdResourcePositionSign(request
					.getParameter("adResourcePositionSign"));

			// 图片上传
			updFlag = uploadPic(adResource.getAdPackageId(), newFileName,
					adTemplateIdTmp, adTypeTmp,
					adResource.getAdResourcePositionSign(),
					adResource.getAdResourceTypes(), newThumbnailName);

			// 视频缩略图路径存入DB
			if (adResource.getAdResourceTypes()
					.equals(Constant.AD_RESOURCE_IMG)) {
				adResource.setAdResourceThumbnailPath("");
			} else {
				adResource.setAdResourceThumbnailPath(newThumbnailName);
			}

			// 保存广告资源信息
			if (updFlag) {
				try {
					adService.updateAdPackageAndAdResource(adResource);
					// 若为修改，则之前图片的地址，应该要删除掉
					if (Constant.ADD_MODIFY_FLAG_MODIFY.equals(addOrUpdateFlag)) {
						if (request.getParameter("adResourcePath") != null) {
							// 原文件
							fileRemove(request.getParameter("adResourcePath"),
									adPackageId, adUploadPath);
						}

						if (request.getParameter("adResourceThumbnailPath") != null) {
							// 原文件缩略图
							fileRemove(
									request.getParameter("adResourceThumbnailPath"),
									adPackageId, adUploadThumbnailPath);
						}
					}
				} catch (Exception e) {
					updFlag = false;
					// 保存广告资源信息异常，则要删除已上传的资源和资源缩略图
					fileRemove(newFileName, adPackageId, adUploadPath);

					fileRemove(newThumbnailName, adPackageId,
							adUploadThumbnailPath);

					log.error("update DB resource failure", e);
				}

			}

		} catch (Exception e) {
			updFlag = false;
			log.error("resource setting failure", e);
		}
		// 返回结果
		map.put("flag", updFlag);
		String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(map);
		this.writeResultToResponse(json);

		return null;
	}

	/**
	 * 普通广告：点图片右上角的“删除”按钮
	 */
	public String deleteAdResourceUpload() {

		// 单屏
		if (Constant.AD_RESOURCE_POSITION_SIGN_SINGLE.equals(areaFlag)) {
			deleteAdResourceUploadFileList("toUploadPicCommonList",
					String.valueOf(this.getAdStyle()), "toAddCommon");
		} else {// 双屏
			deleteAdResourceUploadFileList("toUploadPicCommonDoubleList",
					String.valueOf(this.getAdStyle()), "toAddCommon");
		}

		return null;

	}

	/**
	 * 紧急广告：点图片右上角的“删除”按钮
	 */
	public String deleteAdResourceUploadUrgency() {

		deleteAdResourceUploadFileList("toUploadPicUrgencyList",
				String.valueOf(this.getAdStyle()), "toAddUrgency");

		return null;

	}

	/**
	 * 应用广告：点图片右上角的“删除”按钮
	 */
	public String deleteAdResourceUploadApp() {

		deleteAdResourceUploadFileList("toUploadPicAppList",
				String.valueOf(this.getAdStyle()), "toAddApp");

		return null;

	}

	/**
	 * 删除新增素材列表的素材资源
	 * 
	 * @param method
	 *            跳转时调用的方法名
	 * @param adStyle
	 *            广告类型-上下屏
	 * @param closeTabId
	 *            不能操作时，需要关闭的页签id
	 * @return
	 */
	public String deleteAdResourceUploadFileList(String method, String adStyle,
			String closeTabId) {

		reAdPackage();

		// 判断是否可以修改
		String errMsg = checkModifyEnable(adResource.getAdPackageId());
		if (!"".equals(errMsg)) {

			adPackage.setAdPackageId(adResource.getAdPackageId());
			// this.writeDwzResultToResponse(errMsg,
			// Constant.HTTP_STATUS_FAILED,
			// "listAd", null, "closeCurrent", null);
			this.writeDwzResultToResponse(errMsg, Constant.HTTP_STATUS_FAILED,
					closeTabId, "listAd", null, "closeNavTabId", null);

			return null;
		}

		if (adResourceId > 0) {

			// 根据adResourceId获取该笔资料
			AdResource adResource = adService.getAdResourceById(adResourceId);

			// 设定 “数据状态”为“1-无效”
			adResource.setDataStat(Constant.DATA_STAT_OFF);

			try {
				adService.saveOrUpdateAdResource(adResource);

				// 删除素材
				if (StringUtils.isNotEmpty(adResource.getAdResourcePath())) {
					fileRemove(adResource.getAdResourcePath(),
							adResource.getAdPackageId(), adUploadPath);
				}

				// 删除视频缩略图素材
				if (StringUtils.isNotEmpty(adResource
						.getAdResourceThumbnailPath())) {
					fileRemove(adResource.getAdResourceThumbnailPath(),
							adResource.getAdPackageId(), adUploadThumbnailPath);
				}

				this.writeDwzResultToResponse(Constant.DELETE_SUCCESS,
						Constant.HTTP_STATUS_OK, null, null, "forward",
						"ad/adPackage!" + method + ".do?adPackage.adPackageId="
								+ adResource.getAdPackageId() + "&adStyle="
								+ adStyle);

			} catch (Exception e) {
				log.error("delete file failed", e);
				this.writeTextResultToResponse(Constant.DELETE_FAILED,
						Constant.HTTP_STATUS_FAILED, null, null, null, null);
			}

		}

		return null;

	}

	/**
	 * 点图片右上角的“删除”按钮
	 */
	public String deleteAdResource() {

		adResource = adResource == null ? new AdResource() : adResource;
		reAdPackage();

		// 判断是否可以修改
		String errMsg = checkModifyEnable(adResource.getAdPackageId());
		if (!"".equals(errMsg)) {

			adPackage.setAdPackageId(adResource.getAdPackageId());
			// this.writeDwzResultToResponse(errMsg,
			// Constant.HTTP_STATUS_FAILED,
			// "listAd", null, "closeCurrent", null);
			this.writeDwzResultToResponse(errMsg, Constant.HTTP_STATUS_FAILED,
					"adModify", "listAd", null, "closeNavTabId", null);

			return null;
		}

		// 删除操作
		if (adResourceId > 0) {
			try {
				// 根据adResourceId获取该笔资料
				AdResource adResource = adService
						.getAdResourceById(adResourceId);

				// 设定 “数据状态”为“1-无效”
				adResource.setDataStat(Constant.DATA_STAT_OFF);

				adService.saveOrUpdateAdResource(adResource);

				// 删除素材
				if (StringUtils.isNotEmpty(adResource.getAdResourcePath())) {
					fileRemove(adResource.getAdResourcePath(),
							adResource.getAdPackageId(), adUploadPath);
				}

				// 删除素材缩略图
				if (StringUtils.isNotEmpty(adResource
						.getAdResourceThumbnailPath())) {
					fileRemove(adResource.getAdResourceThumbnailPath(),
							adResource.getAdPackageId(), adUploadThumbnailPath);
				}

				this.writeDwzResultToResponse(Constant.DELETE_SUCCESS,
						Constant.HTTP_STATUS_OK, "adModify", null, null, null);
			} catch (Exception e) {
				log.error("delete file failed", e);
				this.writeTextResultToResponse(Constant.DELETE_FAILED,
						Constant.HTTP_STATUS_FAILED, null, null, null, null);
			}
		}
		return null;
	}

	/**
	 * 检查是否可以修改
	 * 
	 * @param adPackageId
	 * @return 返回错误信息
	 */
	public String checkModifyEnable(String adPackageId) {

		if (StringUtils.isNotEmpty(adPackageId)) {
			// 查询广告包基本信息
			AdPackage adPackageTmp = adService.getAdPackageById(adPackageId);
			adPackage.setAdPackageStatus(adPackageTmp.getAdPackageStatus());

			return getErrMsg(adPackageTmp.getAdPackageStatus());
		}
		return "";
	}

	/**
	 * 获取错误信息
	 * 
	 * @param adpackageStatus
	 * @return
	 */
	private String getErrMsg(int adpackageStatus) {

		switch (adpackageStatus) {
		case Constant.AD_PACKAGE_STATUS_CHECK_PASSED:
			return Constant.AD_PACKAGE_CHECKED;
		case Constant.AD_PACKAGE_STATUS_COMMITTED:
			return Constant.AD_PACKAGE_COMMITTED;
		case Constant.AD_PACKAGE_STATUS_PUBLISHED:
			return Constant.AD_PACKAGE_PUBLISHED;
		}
		return "";
	}

	/**
	 * 列表点选“审核”按钮，进入审核画面
	 * 
	 * @return
	 */
	public String toCheck() {

		return getInfo("checkAd", "checkAdDouble", "checkAdUrgency",
				"checkAdApp");
	}

	/**
	 * 列表点选“发布”按钮，进入发布画面
	 * 
	 * @return
	 */
	public String toPublish() {

		return getInfo("adPublish", "adPublishDouble", "adPublishUrgency","adPublishApp");
	}

	/**
	 * 获取画面资料
	 * 
	 * @param singleSight
	 *            全屏广告画面
	 * @param doubleSight
	 *            双屏广告画面
	 * @param urgencySight
	 *            紧急广告画面
	 * @param appSight
	 *            应用广告画面
	 * @return
	 */
	public String getInfo(String singleSight, String doubleSight,
			String urgencySight, String appSight) {

		reAdPackage();
		if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {

			// 查询广告包基本信息
			adPackage = adService.getAdPackageById(adPackage.getAdPackageId());

			// 获取批次号首字母，用以判断广告类型
			adType = getAdType(adPackage);

			// 普通广告
			if (Constant.AD_PACKAGE_BATCH_B.equals(adType)) {
				// 设置时间
				adPackage.setAdPackageBegintime1(DateUtil
						.getDateYYYY_MM_DD(adPackage.getAdPackageBegintime()));
				adPackage.setAdPackageEndtime1(DateUtil
						.getDateYYYY_MM_DD(adPackage.getAdPackageEndtime()));

				return toAdCommon(adPackage, singleSight, doubleSight);

			}
			// 紧急广告
			else if (Constant.AD_PACKAGE_BATCH_U.equals(adType)) {

				adPackage.setAdPackageBegintime1(DateUtil
						.getDateYYYY_MM_DD_HH_MM_SS(adPackage
								.getAdPackageBegintime()));
				adPackage.setAdPackageEndtime1(DateUtil
						.getDateYYYY_MM_DD_HH_MM_SS(adPackage
								.getAdPackageEndtime()));

				// 图片广告 查询素材资料
				if (Constant.AD_TEMPLATE_URGENCY_PIC.equals(String
						.valueOf(adPackage.getAdTemplateId()))) {
					// 查询广告资源信息列表
					adResourceList = adService
							.listAdResourceByAdPackageIdUrgency(adPackage
									.getAdPackageId());
				}

				return urgencySight;
			}
			// 应用广告
			else if (Constant.AD_PACKAGE_BATCH_E.equals(adType)) {

				adPackage.setAdPackageBegintime1(DateUtil
						.getDateYYYY_MM_DD(adPackage.getAdPackageBegintime()));
				adPackage.setAdPackageEndtime1(DateUtil
						.getDateYYYY_MM_DD(adPackage.getAdPackageEndtime()));

				// 查询广告资源信息列表
				adResourceList = adService
						.listAdResourceByAdPackageIdUrgency(adPackage
								.getAdPackageId());

				return appSight;
			}
		}

		return singleSight;
	}

	/**
	 * 返回普通广告的画面
	 * 
	 * @param adPackgeTmp
	 * @param oneSight
	 *            单屏返回的画面
	 * @param doubleSight
	 *            双屏返回的画面
	 * @return
	 */
	private String toAdCommon(AdPackage adPackgeTmp, String oneSight,
			String doubleSight) {

		String adTemplateId = String.valueOf(adPackgeTmp.getAdTemplateId());

		provinces = etbAreaCodeService.getAllProvince();
		etbClientDeviceList = etbClientDeviceService.exportDeviceInfo();
//		if(null != provinces && provinces.size() > 0){
//			citys = etbAreaCodeService.getCityByAreaCode(provinces.get(0).getAreaCode());
//			if(null != citys && citys.size() > 0){
//				countys = etbAreaCodeService.getCountyByAreaCode(citys.get(0).getAreaCode());
//			}
//		}
		// 单屏
		if (Constant.AD_TEMPLATE_COMMON_SINGLE.equals(adTemplateId)) {
			// 查询广告资源信息列表
			adResourceList = adService.listAdResourceByAdPackageId(adPackgeTmp.getAdPackageId());

			return oneSight;
		}

		// 双屏
		else if (Constant.AD_TEMPLATE_COMMON_DOUBLE.equals(adTemplateId)) {
			// 查询广告资源信息列表-上屏
			adResourceDoubleUpperList = adService
					.listAdResourceByAdPackageIdAndPosition(
							adPackgeTmp.getAdPackageId(),
							Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER);

			// 查询广告资源信息列表-下屏
			adResourceDoubleLowerList = adService
					.listAdResourceByAdPackageIdAndPosition(
							adPackgeTmp.getAdPackageId(),
							Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER);

			return doubleSight;
		}

		return oneSight;
	}

	/**
	 * 根据ID获取adPackage的状态
	 */
	public void getAdpackageStatusById() {
		reAdPackage();

		if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {
			// 查询广告包基本信息
			adPackage = adService.getAdPackageById(adPackage.getAdPackageId());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", adPackage == null ? "" : adPackage.getAdPackageStatus());
			map.put("endTime", adPackage == null ? "" : adPackage.getAdPackageEndtimeDsp2());
			String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(map);
			this.writeResultToResponse(json);
		}

	}
	
	/**
	 * 根据ID获取adPackage的状态,以及图片素材的数量,2013-10-30,RanChen
	 */
	public void getAdpackageStatusAndPicNumById() {
		reAdPackage();
		adResource = adResource == null ? new AdResource() : adResource;

		if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {
			// 查询广告包基本信息
			adPackage = adService.getAdPackageById(adPackage.getAdPackageId());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", adPackage == null ? "" : adPackage.getAdPackageStatus());
			map.put("dataStatus", adPackage == null ? "" : adPackage.getDataStat());

			int picNumTmp = adService
					.getAdResourceCountByAdpackageIdAndAdResourceType(
							adPackage.getAdPackageId(),
							Constant.AD_RESOURCE_IMG);
			map.put("picNum", picNumTmp);

			if (adResource.getAdResourceId() != 0) {
				AdResource adResourceTmp = adService
						.getAdResourceById(adResource.getAdResourceId());
				map.put("dataStat", adResourceTmp.getDataStat());
				map.put("resourceTypeOld", adResourceTmp.getAdResourceTypes());// 原素材类型
			} else {
				map.put("dataStat", Constant.DATA_STAT_ON);
				map.put("resourceTypeOld", "");
			}

			String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE)
					.toJson(map);
			this.writeResultToResponse(json);
		}

	}

	/**
	 * 审核画面点击“通过”按钮
	 * 
	 * @return
	 */
	public String check() {

		adPackage = (adPackage == null) ? new AdPackage() : adPackage;

		// 审核操作
		String errMsg = adService.check(adPackage);

		if (errMsg != null) {

			this.writeDwzResultToResponse(Constant.CHECKED_FAILED + ":"
					+ errMsg, Constant.HTTP_STATUS_FAILED, "listAdCheck", null,
					"closeCurrent", null);

		} else {

			this.writeDwzResultToResponse(Constant.CHECKED_SUCCESS,
					Constant.HTTP_STATUS_OK, "toCheck", "listAdCheck", null,
					"closeNavTabId", null);

		}

		return null;

	}

	/**
	 * 进入审核意见画面
	 * 
	 * @return
	 */
	public String toAdCheckSuggest() {

		reAdPackage();

		// 获取DB资料
		if (StringUtils.isNotEmpty(adPackage.getAdPackageId().trim())) {
			adPackage = adService.getAdPackageById(adPackage.getAdPackageId());
		}

		return "adCheckSuggest";
	}

	/**
	 * 审核画面点击“不通过”按钮
	 * 
	 * @return
	 */
	public String unCheck() {

		reAdPackage();

		try {

			// 设置审核基本信息
			adService.setAdPackageCheckInfo(adPackage);

			// 状态栏位：2-审核不通过
			adPackage
					.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_CHECK_UNPASSED);

			// 保存
			adService.saveAdPackage(adPackage);

			this.writeDwzResultToResponse(Constant.UNCHECKED_SUCCESS,
					Constant.HTTP_STATUS_OK, "toCheck", null, "closeCurrent",
					null);

		} catch (Exception e) {
			log.error("update data error", e);
			this.writeDwzResultToResponse(Constant.UNCHECKED_FAILED,
					Constant.HTTP_STATUS_FAILED, "toCheck", null,
					"closeCurrent", null);
		}

		return null;

	}

	/**
	 * 发布
	 * 
	 * @return
	 */
	public String publish() {

		reAdPackage();

		try {

			// 获取DB资料
			if (StringUtils.isNotEmpty(adPackage.getAdPackageId().trim())) {
				adPackage = adService.getAdPackageById(adPackage.getAdPackageId());
			}

			Set<EtbClientDevice> device_list = new HashSet<EtbClientDevice>();
			if(null != selectDevice && selectDevice.size() > 0){
				for(int i = 0;i < selectDevice.size();i ++){
					EtbClientDevice device = etbClientDeviceService.findDeviceInfoById(selectDevice.get(i));
					device_list.add(device);
				}
			}
			adPackage.setEtbClientDeviceList(device_list);
			// 推送广告信息
//			boolean pushResult = adService.push(adPackage);
			// 推送失败
//			if (!pushResult) {
//				this.writeDwzResultToResponse(Constant.PUBLISH_ADVERTISE_FAILED
//						+ ":广告推送失败", Constant.HTTP_STATUS_FAILED,
//						"listAdPublish", null, "closeCurrent", null);
//
//				return null;
//			}

			// 发布 DB操作
			adService.publishAd(adPackage);

			this.writeDwzResultToResponse(Constant.PUBLISH_ADVERTISE_SUCCESS,
					Constant.HTTP_STATUS_OK, "listAdPublish", null,
					"closeCurrent", null);
		} catch (Exception e) {
			log.error("update data error", e);
			this.writeDwzResultToResponse(Constant.PUBLISH_ADVERTISE_FAILED,
					Constant.HTTP_STATUS_FAILED, "listAdPublish", null,
					"closeCurrent", null);
		}

		return null;
	}

	/**
	 * 广告包新增:普通广告
	 * 
	 * @return
	 */
	public String addAdCommon() {

		reAdPackage();

		// 广告包批次号
		adPackage.setAdPackageBatchNo(Constant.AD_PACKAGE_BATCH_B
				+ adPackage.getAdPackageBatchNo().trim());
		// 广告类型
		adPackage.setAdPackageType(Constant.AD_PACKAGE_BATCH_B);
		// 设置广告包信息
		setAdPackageInfo(Constant.AD_PACKAGE_BATCH_B);

		// 保存信息
		try {
			adService.saveAdPackage(adPackage);

			// 1：单屏 2：双屏
			if (Constant.AD_TEMPLATE_COMMON_DOUBLE.equals(String
					.valueOf(adPackage.getAdTemplateId()))) {
				return toUploadPicCommonDoubleList();
			} else {
				return toUploadPicCommonList();
			}

		} catch (Exception e) {
			log.error("Save ad package failed", e);
			this.writeTextResultToResponse(Constant.SAVE_FAILED,
					Constant.HTTP_STATUS_FAILED, "toAddCommon", null, null,
					null);

		}
		return null;
	}

	/**
	 * 设置广告包基本信息 - 新增
	 * 
	 * @param adTypeTmp
	 *            广告类型
	 * @param subject
	 */
	private void setAdPackageInfo(String adTypeTmp) {

		adPackage.setAdPackageId(UUIDGenerator.getUUID());// 广告包ID-UUID

		// 创建人，系统登录人员
		Subject subject = SecurityUtils.getSubject();
		adPackage.setAdPackageCreater(String.valueOf(subject.getSession()
				.getAttribute(Constant.SESSION_USER_NAME)));
		adPackage.setAdPackageCreaterTime(new Date());// 创建时间-当前时间
		adPackage.setDataStat(Constant.DATA_STAT_ON);// 数据状态-默认有效

		// 日期类型转换
		dateTypeConvert(adTypeTmp);

	}

	/**
	 * 普通广告：下一步，进入上传图片显示页面
	 * 
	 * @return
	 */
	public String toUploadPicCommonList() {

		return initUploadPicList("toUploadPicCommonList", false);

	}

	/**
	 * 双屏：下一步，进入上传图片显示页面
	 * 
	 * @return
	 */
	public String toUploadPicCommonDoubleList() {

		return initUploadPicList("toUploadPicCommonDoubleList", true);

	}

	/**
	 * 广告包新增:紧急广告
	 * 
	 * @return
	 */
	public String addAdUrgency() {

		reAdPackage();

		String adTypeUrgency = adPackage.getAdPackageTypeUrgency();
		// 广告包信息保存
		adPackage.setAdPackageBatchNo(Constant.AD_PACKAGE_BATCH_U
				+ adPackage.getAdPackageBatchNo().trim());
		// 广告类型
		adPackage.setAdPackageType(Constant.AD_PACKAGE_BATCH_U);

		// 模板ID显示普通广告单屏的ID
		adPackage.setAdTemplateId(Integer.valueOf(adTypeUrgency));
		// 设置广告包基本信息
		setAdPackageInfo(Constant.AD_PACKAGE_BATCH_U);

		try {
			// 保存信息
			adService.saveAdPackage(adPackage);

			if (Constant.AD_URGENCY_WORD.equals(adTypeUrgency)) {// 文字广告
				this.writeDwzResultToResponse(Constant.SAVE_SUCCESS,
						Constant.HTTP_STATUS_OK, "toAddUrgency", "listAd",
						null, "closeNavTabId", null);
			} else {// 图片广告
				return toUploadPicUrgencyList();
			}

		} catch (Exception e) {
			log.error("Save urgency ad failed", e);
			this.writeDwzResultToResponse(Constant.SAVE_FAILED,
					Constant.HTTP_STATUS_FAILED, "toAddUrgency", null, null,
					null);
		}

		return null;
	}

	/**
	 * 紧急广告： 下一步，进入上传图片显示页面
	 * 
	 * @return
	 */
	public String toUploadPicUrgencyList() {

		return initUploadPicList("toUploadPicUrgencyList", false);

	}

	/**
	 * 初始化素材上传列表画面
	 * 
	 * @param sight
	 *            返回的画面
	 * @param isDoubleFlag
	 *            是否为双屏
	 * @return
	 */
	public String initUploadPicList(String sight, boolean isDoubleFlag) {

		reAdPackage();
		adResource = (adResource == null) ? new AdResource() : adResource;

		String errMsg = getErrMsg(adPackage.getAdPackageStatus());
		// 不可修改，则直接返回画面
		if (!"".equals(errMsg)) {
			return sight;
		}

		String packageId = adPackage.getAdPackageId();
		// 从DB中查询该广告包中的资源文件
		if (StringUtils.isNotEmpty(packageId)) {

			// 双屏
			if (isDoubleFlag) {
				// 上半区域列表
				adResourceDoubleUpperList = adService
						.listAdResourceDoubleByAdPackageId(packageId.trim(),
								Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER);
				// 下半区域列表
				adResourceDoubleLowerList = adService
						.listAdResourceDoubleByAdPackageId(packageId.trim(),
								Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER);
			} else {// 单屏
				adResourceList = adService
						.listAdResourceByAdPackageIdUrgency(packageId.trim());
			}
		}
		adResource.setAdPackageId(packageId);

		return sight;
	}

	/**
	 * 广告包新增:应用广告
	 * 
	 * @return
	 */
	public String addAdApp() {

		reAdPackage();

		// 广告包信息保存
		adPackage.setAdPackageBatchNo(Constant.AD_PACKAGE_BATCH_E
				+ adPackage.getAdPackageBatchNo().trim());
		// 广告类型
		adPackage.setAdPackageType(Constant.AD_PACKAGE_BATCH_E);
		// 广告包信息设定
		setAdPackageInfo(Constant.AD_PACKAGE_BATCH_E);

		// 保存信息
		try {
			adService.saveAdPackage(adPackage);

			return toUploadPicAppList();

		} catch (Exception e) {
			log.error("Save ad package failed", e);
			this.writeTextResultToResponse(Constant.SAVE_FAILED,
					Constant.HTTP_STATUS_FAILED, "toAddApp", null, null, null);

		}
		return null;
	}

	/**
	 * 应用广告： 下一步，进入上传图片显示页面
	 * 
	 * @return
	 */
	public String toUploadPicAppList() {

		return initUploadPicList("toUploadPicAppList", false);

	}

	/**
	 * 普通广告：文档上传页面
	 * 
	 * @return
	 */
	public String toUpload() {
		reAdPackage();
		adResource = (adResource == null) ? new AdResource() : adResource;

		// 设置错误信息
		this.setUpdErrMsg();

		return "toUpload";

	}

	/**
	 * 判断是否可以修改，设置错误信息
	 */
	public void setUpdErrMsg() {

		this.errMsg = "";

		// 判断是否可以修改
		String errMsgTmp = checkModifyEnable(adResource.getAdPackageId());

		if (!"".equals(errMsgTmp)) {
			this.errMsg = errMsgTmp;
		}

		// 获取图片素材的数量,2013-10-30,RanChen,start
		picNum = adService.getAdResourceCountByAdpackageIdAndAdResourceType(
				adResource.getAdPackageId(), Constant.AD_RESOURCE_IMG);
		// 获取图片素材的数量,2013-10-30,RanChen,end

	}

	/**
	 * 紧急广告：文档上传页面
	 * 
	 * @return
	 */
	public String toUploadUrgency() {
		reAdPackage();
		adResource = (adResource == null) ? new AdResource() : adResource;

		// 设置错误信息
		this.setUpdErrMsg();

		return "toUploadUrgency";
	}

	/**
	 * 应用广告：文档上传页面
	 * 
	 * @return
	 */
	public String toUploadApp() {
		reAdPackage();
		adResource = (adResource == null) ? new AdResource() : adResource;

		// 设置错误信息
		this.setUpdErrMsg();

		return "toUploadApp";
	}

	/**
	 * 上传图片，并新增图片信息暂存
	 * 
	 * @return
	 */
	public String uploadAdPicCommon() {
		adResource = (adResource == null) ? new AdResource() : adResource;
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean updFlag = true;
		String adTypeTmp = "";
		Map<String, Object> map = new HashMap<String, Object>();
		String areaFlagTmp = request.getParameter("areaFlag");
		String adTemplateTypeIdTmp = "";// 模板类型ID，区分单屏和双屏
		// 获取广告类型:1-普通广告；2-应用广告；3-紧急广告
		String adStyleTmp = request.getParameter("adStyle");

		// 获取文件后缀名
		String postfix = fileuploadFileName.substring(fileuploadFileName
				.lastIndexOf("."));
		String uuid = UUIDGenerator.getUUID();
		// 新文件名
		newFileName = uuid + postfix;

		String newThumbnailName = uuid + "thu.jpg";
		try {
			// 从前台获取页面参数
			this.setAdResource(adResource, request);

			// 屏幕位置:上屏与下屏
			adResource.setAdResourcePositionSign(areaFlagTmp);

			// 单屏
			if (Constant.AD_RESOURCE_POSITION_SIGN_SINGLE.equals(areaFlagTmp)) {
				adTemplateTypeIdTmp = Constant.AD_TEMPLATE_COMMON_SINGLE;
			} else {// 双屏
				adTemplateTypeIdTmp = Constant.AD_TEMPLATE_COMMON_DOUBLE;
			}

			if (StringUtils.isNotEmpty(adStyleTmp)) {
				adTypeTmp = styToType(Integer.valueOf(adStyleTmp));
			}

			// 图片上传
			updFlag = uploadPic(adResource.getAdPackageId(), newFileName,
					adTemplateTypeIdTmp, adTypeTmp,
					adResource.getAdResourcePositionSign(),
					adResource.getAdResourceTypes(), newThumbnailName);

			// 广告缩略图
			adResource.setAdResourceThumbnailPath(newThumbnailName);

			if (updFlag) {
				try {
					// 新增资源信息之广告资源表
					adService.saveOrUpdateAdResource(adResource);
				} catch (Exception e) {
					updFlag = false;
					// 保存广告资源信息异常，则要删除已上传的资源及缩略图
					fileRemove(newFileName, adResource.getAdPackageId(),
							adUploadPath);

					fileRemove(newThumbnailName, adResource.getAdPackageId(),
							adUploadThumbnailPath);

					log.error("update DB resource failure", e);
				}
			}

		} catch (Exception e) {
			updFlag = false;
			log.error("resource setting failure", e);
		}

		map.put("flag", updFlag);
		String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(map);
		this.writeResultToResponse(json);

		return null;
	}

	/**
	 * 设置素材资料信息
	 * 
	 * @param adResourceTmp
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void setAdResource(AdResource adResourceTmp,
			HttpServletRequest request) {
		String adResourceTimes = "0";
		String adResourceCount = "1";
		adResource.setAdPackageId(request.getParameter("adPackageId").trim());
		adResource.setAdResourcePath(newFileName);
		adResource.setAdResourceTypes(request.getParameter("adResourceTypes"));

		if (request.getParameter("adResourceTimes") != null
				&& !"".equals(request.getParameter("adResourceTimes").trim())) {
			adResourceTimes = request.getParameter("adResourceTimes");
		}
		adResource.setAdResourceTimeS(adResourceTimes);

//		adResource.setAdResourceAction(request.getParameter("adResourceAction"));
		adResource.setDataStat(Constant.DATA_STAT_ON);// 数据状态-默认有效
		adResource.setAdResourceTips(request.getParameter("adResourceTips"));
//		adResource.setAdResourceIntent(request.getParameter("adResourceIntent"));
//		adResource.setAdResourceProduct(request.getParameter("adResourceProduct"));

//		adResource.setAdResourceIntime(request.getParameter("adResourceIntime"));
		adResource.setAdResourceOuttime(request.getParameter("adResourceOuttime"));

		if (request.getParameter("adResourceCount") != null
				&& !"".equals(request.getParameter("adResourceCount").trim())) {
			adResourceCount = request.getParameter("adResourceCount");
		}
		adResource.setAdResourceCount(Integer.valueOf(adResourceCount));

	}

	/**
	 * 图片上传
	 * 
	 * @param adPackageId
	 * @param newFileName
	 *            文件重命名后的名字
	 * @param adTemplateTypeIdTmp
	 * @param adTypeTmp
	 *            广告类型
	 * @param adResourcePositionSign
	 *            区分上下屏
	 * @param resourceType
	 *            资源类型
	 * @return
	 */
	public boolean uploadPic(String adPackageId, String newFileName,
			String adTemplateTypeIdTmp, String adTypeTmp,
			String adResourcePositionSign, String resourceType,
			String newThumbnailName) {

		// 上传素材是否成功标记
		boolean updFlag = true;

		// 视频生成缩略图是否成功标记
		boolean convertFlag = true;
		File file = null;

		HttpServletResponse response = ServletActionContext.getResponse();
		// 务必，防止返回文件名是乱码
		response.setCharacterEncoding("utf-8");
		// 获取项目根路径
		String savePath = ServletActionContext.getServletContext().getRealPath(
				File.separator);

		/* 广告视频缩略图放在uploads下的thumbnail目录下 */
//		String thumbnailPath = savePath + Constant.AD_FILE_UPLOAD_FOLDER
//				+ File.separator + Constant.AD_FILE_THUMBNAIL_FOLDER
//				+ File.separator + adPackageId + File.separator;
		/* 广告存放在uploads下的advertise目录下 */
//		savePath = savePath + Constant.AD_FILE_UPLOAD_FOLDER + File.separator
//				+ PropertyUtil.readData("advertiseUploadPath") + File.separator
//				+ adPackageId + File.separator;
		
		//新的文件路径，来自胡的修改
		savePath = savePath + File.separator + adPackageId + File.separator;
		String thumbnailPath = savePath + Constant.AD_FILE_THUMBNAIL_FOLDER + File.separator;

		// advertise目录创建
		fileCreat(savePath);

		// thumbnail目录创建
		fileCreat(thumbnailPath);

		try {
			String realPath = savePath + newFileName;
			String thumbnailRealPath = thumbnailPath + newThumbnailName;// 缩略图完整路径

			file = new File(realPath);

			// 文件上传
			// updFlag = fileupload.renameTo(file); //modified by wei2.liang
			FileUtils.copyFile(fileupload, file);

			// 上传成功后图片压缩:只有图片进行压缩处理
			// if (updFlag) {
			if (file.exists() && file.isFile()) {

				if (resourceType.equals(Constant.AD_RESOURCE_IMG)) {
					updFlag = compressImgDeal(file, realPath,
							adTemplateTypeIdTmp, adTypeTmp,
							adResourcePositionSign);
					// 如果是视频，则获取视频的缩略图
				} else {
					// 生成缩略图失败资源是否还要上传待判断
					convertFlag = VedioConvert.processThumbnail(realPath,
							thumbnailRealPath);
					if (!convertFlag) {// 生成失败失败，指定一张默认图片
						newThumbnailName = Constant.AD_FILE_THUMBNAIL_DEFAULT;
					}
				}
			} else {// 文件不存在，则表示上传失败
				updFlag = false;
			}

		} catch (Exception e) {
			updFlag = false;
			log.error("file upload failed.", e);
		}

		if (!updFlag && file != null) {
			file.delete();
		}
		return updFlag;
	}

	/**
	 * 图片压缩处理
	 * 
	 * @param file
	 * @param realPath
	 * @param adTemplateIdTmp
	 *            ：区分普通广告的单屏和双屏
	 * @param adTypeTmp
	 *            ：区分广告类型
	 * @param adResourcePositionSign
	 *            ：区分上下屏
	 */
	public boolean compressImgDeal(File file, String realPath,
			String adTemplateTypeIdTmp, String adTypeTmp,
			String adResourcePositionSign) {

		int width = Constant.AD_SIZE_WIDTH;
		int height = Constant.AD_SIZE_COMMON_SINGLE_HEIGHT;

		// 图片压缩处理：一屏：1280*800 二屏：640*800 ，640*800 应用广告：426*800
		// 紧急广告
		if (Constant.AD_PACKAGE_BATCH_U.equals(adTypeTmp)) {
			height = Constant.AD_SIZE_COMMON_SINGLE_HEIGHT;
		}
		// 应用广告
		else if (Constant.AD_PACKAGE_BATCH_E.equals(adTypeTmp)) {
			height = Constant.AD_SIZE_COMMON_APP_HEIGHT;
			// 普通广告
		} else {
			// 单屏
			if (Constant.AD_TEMPLATE_COMMON_SINGLE.equals(adTemplateTypeIdTmp)) {
				height = Constant.AD_SIZE_COMMON_SINGLE_HEIGHT;
			}
			// 双屏
			else if (Constant.AD_TEMPLATE_COMMON_DOUBLE
					.equals(adTemplateTypeIdTmp)) {
				// 上屏
				if (Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER
						.equals(adResourcePositionSign)) {
					height = Constant.AD_SIZE_COMMON_DOUBLE_HEIGHT_UPPER;
				} else {// 下屏
					height = Constant.AD_SIZE_COMMON_DOUBLE_HEIGHT_LOWER;
				}
			}
		}
		return ImageCompressUtil.compressImg(file, realPath, width, height,
				Constant.COMPRESSION_RATIO);

	}

	/**
	 * 上传完成，关闭当前navTb，刷新list
	 * 
	 * @return
	 */
	public String closeUploadPage() {

		reAdPackage();
		adResource = (adResource == null) ? new AdResource() : adResource;

		// adPackage.getAdPackageStatus()!=0表示点击提交按钮
		if (StringUtils.isNotEmpty(adResource.getAdPackageId())
				&& adPackage.getAdPackageStatus() != 0) {
			modifyAdPackageStatus(adResource.getAdPackageId(),
					Integer.valueOf(adPackage.getAdPackageStatus()));
		}

		this.writeDwzResultToResponse(Constant.AD_PACKAGE_ADD_FINISH,
				Constant.HTTP_STATUS_OK, "listAd", null, "closeCurrent", null);

		return null;
	}

	/**
	 * 修改广告包的广告包状态
	 * 
	 * @param adPackageId
	 */
	private void modifyAdPackageStatus(String adPackageId, int adPackageStatus) {
		AdPackage adPackageTmp = adService.getAdPackageById(adResource
				.getAdPackageId());
		// 状态为改待审核
		adPackageTmp.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_COMMITTED);
		adService.saveAdPackage(adPackageTmp);
	}

	/**
	 * 判断批次号是否已存在
	 * 
	 * @return
	 */
	public String findAdPackageInfoByBatchNo() {

		reAdPackage();
		boolean result = true;
		String adType = "";
		if (adPackage.getAdPackageBatchNo() != null
				&& !"".equals(adPackage.getAdPackageBatchNo().trim())) {
			int adsty = this.getAdStyle();

			adType = styToType(adsty);

			result = adService.findAdPackageInfoByBatchNo(adType
					+ adPackage.getAdPackageBatchNo().trim());
		}

		if (result) {//不存在
			this.writeResultToResponse("true");
		} else {//存在
			this.writeResultToResponse("false");
		}
		return null;

	}
	
	/**
	 * 广告类型两种格式转换
	 * 
	 * @param adsty
	 * @return
	 */
	public String styToType(int adsty) {
		String adTypeTmp = "";
		switch (adsty) {
		// 普通广告
		case Constant.AD_TEMPLATE_TYPE_COMMON:
			adTypeTmp = Constant.AD_PACKAGE_BATCH_B;
			break;
		// 紧急广告
		case Constant.AD_TEMPLATE_TYPE_URGENCY:
			adTypeTmp = Constant.AD_PACKAGE_BATCH_U;
			break;
		// 应用广告
		default:
			adTypeTmp = Constant.AD_PACKAGE_BATCH_E;
		}

		return adTypeTmp;
	}

	/**
	 * 修改：判断批次号是否已存在
	 * 
	 * @return
	 */
	public String findAdPackageInfoByBatchNoUpd() {

		reAdPackage();
		boolean result = true;
		String adPackageIdTmp = "";

		if (StringUtils.isNotEmpty(adPackage.getAdPackageId())
				&& StringUtils.isNotEmpty(adPackage.getAdPackageBatchNoInput())) {

			// 查询广告包基本信息
			AdPackage adPackageTmp = adService.getAdPackageById(adPackage
					.getAdPackageId());
			adPackageIdTmp = adPackageTmp.getAdPackageBatchNo().trim()
					.substring(1);
			if (adPackageIdTmp.equals(adPackage.getAdPackageBatchNoInput())) {
				this.writeResultToResponse("true");
			} else {
				result = adService.findAdPackageInfoByBatchNo(this.getAdType()
						+ adPackage.getAdPackageBatchNoInput().trim());
				if (result) {
					this.writeResultToResponse("true");
				} else {
					this.writeResultToResponse("false");
				}
			}

		}

		return null;

	}

	/**
	 * 资源文件删除后，从本地目录中清除
	 * 
	 * @param resourcePath
	 */
	public void fileRemove(String resourcePath, String adPackageId,
			String fileFolder) {
		// 删除素材
		/*
		 * String adFilePath = Constant.AD_FILE_UPLOAD_FOLDER + File.separator +
		 * adPackageId + File.separator + resourcePath;
		 */

		// "/work/EtbFileClient/"
		String adFilePath = Constant.AD_FILE_UPLOAD_FOLDER + File.separator
				+ fileFolder + File.separator + adPackageId + File.separator
				+ resourcePath;

		File file = new File(ServletActionContext.getServletContext()
				.getRealPath(File.separator) + adFilePath);

		if (file.exists()) {
			file.delete();
		}
	}
	
	/**
	 * 资源文件删除后，从本地目录中清除
	 * 
	 * @param resourcePath
	 */
	public void fileFolderRemove(String adPackageId,
			String fileFolder) {
		
		String adFilePath = Constant.AD_FILE_UPLOAD_FOLDER + File.separator
				+ fileFolder + File.separator + adPackageId ;

		File file = new File(ServletActionContext.getServletContext()
				.getRealPath(File.separator) + adFilePath);

		StringUtil.deleteAllFiles(file);
	}

	/**
	 * 广告包开始时间判断：同一种类型的广告包时间不能重叠
	 * 
	 * @date 20131016
	 */
	public void judgeAdTimeIsOverlap() {

		reAdPackage();

		// 日期类型转换
		dateTypeConvert(adPackage.getAdPackageType());

		int rFlag = adService.judgeAdTimeIsOverlap(adPackage);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rFlag", rFlag);

		String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(map);
		this.writeResultToResponse(json);
	}

	/**
	 * 时间类型转换：String类型转为Date
	 * 
	 * @param adType
	 */
	public void dateTypeConvert(String adType) {
		try {
			// 普通广告 or 应用广告
			if (Constant.AD_PACKAGE_BATCH_B.equals(adType) || Constant.AD_PACKAGE_BATCH_E.equals(adType)) {
				adPackage.setAdPackageBegintime(DateUtil.parseDateYYYY_MM_DD(adPackage.getAdPackageBegintime1()));

				// 截止日期后加当天的最后一秒的时间
				adPackage.setAdPackageEndtime(DateUtil
						.parseDateYYYY_MM_DD_HH_MM_SS(adPackage.getAdPackageEndtime1() + Constant.DATE_MAX_HH_MM_SS));
				// 紧急广告
			} else if (Constant.AD_PACKAGE_BATCH_U.equals(adType)) {

				adPackage.setAdPackageBegintime(DateUtil.parseDateYYYY_MM_DD_HH_MM_SS(adPackage.getAdPackageBegintime1()));
				adPackage.setAdPackageEndtime(DateUtil.parseDateYYYY_MM_DD_HH_MM_SS(adPackage.getAdPackageEndtime1()));
			}

		} catch (ParseException e) {
			log.error("parseDate Failure", e);
			this.writeDwzResultToResponse(Constant.UPDATE_FAILED + ":时间转换出错",
					Constant.HTTP_STATUS_FAILED, "listAd", null,
					"closeCurrent", null);
		}
	}

	/**
	 * 文件夹创建
	 * 
	 * @param path
	 */
	public void fileCreat(String path) {

		File f = new File(path);

		if (!f.exists()) {
			f.mkdirs();
		}

	}

	public AdPackage getAdPackage() {
		return adPackage;
	}

	public void setAdPackage(AdPackage adPackage) {
		this.adPackage = adPackage;
	}

	public List<Map<String, Object>> getAdList() {
		return adList;
	}

	public void setAdList(List<Map<String, Object>> adList) {
		this.adList = adList;
	}

	public List<AdTemplate> getAdTemplateList() {
		return adTemplateList;
	}

	public void setAdTemplateList(List<AdTemplate> adTemplateList) {
		this.adTemplateList = adTemplateList;
	}

	public List<AdResource> getAdResourceList() {
		return adResourceList;
	}

	public void setAdResourceList(List<AdResource> adResourceList) {
		this.adResourceList = adResourceList;
	}

	public File getFileupload() {
		return fileupload;
	}

	public void setFileupload(File fileupload) {
		this.fileupload = fileupload;
	}

	public String getFileuploadFileName() {
		return fileuploadFileName;
	}

	public void setFileuploadFileName(String fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public AdService getAdService() {
		return adService;
	}

	public void setAdService(AdService adService) {
		this.adService = adService;
	}

	public EtbAreaCodeService getEtbAreaCodeService() {
		return etbAreaCodeService;
	}

	public void setEtbAreaCodeService(EtbAreaCodeService etbAreaCodeService) {
		this.etbAreaCodeService = etbAreaCodeService;
	}

	public List<EtbAreaCode> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<EtbAreaCode> provinces) {
		this.provinces = provinces;
	}

//	public List<EtbAreaCode> getCitys() {
//		return citys;
//	}
//
//	public void setCitys(List<EtbAreaCode> citys) {
//		this.citys = citys;
//	}
//
//	public List<EtbAreaCode> getCountys() {
//		return countys;
//	}
//
//	public void setCountys(List<EtbAreaCode> countys) {
//		this.countys = countys;
//	}

	public List<String> getSelectDevice() {
		return selectDevice;
	}

	public void setSelectDevice(List<String> selectDevice) {
		this.selectDevice = selectDevice;
	}

	public List<EtbClientDevice> getEtbClientDeviceList() {
		return etbClientDeviceList;
	}

	public void setEtbClientDeviceList(List<EtbClientDevice> etbClientDeviceList) {
		this.etbClientDeviceList = etbClientDeviceList;
	}

	public AdResource getAdResource() {
		return adResource;
	}

	public void setAdResource(AdResource adResource) {
		this.adResource = adResource;
	}

	public long getAdResourceId() {
		return adResourceId;
	}

	public void setAdResourceId(long adResourceId) {
		this.adResourceId = adResourceId;
	}

	public List<AdResource> getAdResourceDoubleUpperList() {
		return adResourceDoubleUpperList;
	}

	public void setAdResourceDoubleUpperList(
			List<AdResource> adResourceDoubleUpperList) {
		this.adResourceDoubleUpperList = adResourceDoubleUpperList;
	}

	public List<AdResource> getAdResourceDoubleLowerList() {
		return adResourceDoubleLowerList;
	}

	public void setAdResourceDoubleLowerList(
			List<AdResource> adResourceDoubleLowerList) {
		this.adResourceDoubleLowerList = adResourceDoubleLowerList;
	}

	public String getAreaFlag() {
		return areaFlag;
	}

	public void setAreaFlag(String areaFlag) {
		this.areaFlag = areaFlag;
	}

	public AdTemplate getAdTemplate() {
		return adTemplate;
	}

	public void setAdTemplate(AdTemplate adTemplate) {
		this.adTemplate = adTemplate;
	}

	/**
	 * 获取类型
	 * 
	 * @param adPacakge
	 * @return 返回首字母
	 */
	private String getAdType(AdPackage adPacakgeTmp) {

		String adPackageBatchNo = adPacakgeTmp.getAdPackageBatchNo();
		if (StringUtils.isNotEmpty(adPackageBatchNo)) {
			adPacakgeTmp.setAdPackageBatchNoInitial(adPackageBatchNo.substring(0, 1));// 截取首字母
			adPacakgeTmp.setAdPackageBatchNoInput(adPackageBatchNo.substring(1));// 截取输入内容
		} else {
			adPacakgeTmp.setAdPackageBatchNoInitial("");
		}

		return adPacakgeTmp.getAdPackageBatchNoInitial();
	}
	
	public int getAdStyle() {
		return adStyle;
	}

	public void setAdStyle(int adStyle) {
		this.adStyle = adStyle;
	}

	private void reAdPackage() {
		if (null == adPackage) {
			adPackage = new AdPackage();
		}
	}

	public String getAdType() {
		return adType;
	}

	public void setAdType(String adType) {
		this.adType = adType;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public int getPicNum() {
		return picNum;
	}

	public void setPicNum(int picNum) {
		this.picNum = picNum;
	}

	public boolean isCloseDialogFlag() {
		return closeDialogFlag;
	}

	public void setCloseDialogFlag(boolean closeDialogFlag) {
		this.closeDialogFlag = closeDialogFlag;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public void getCityByProvince(){
		List<EtbAreaCode> city_list = etbAreaCodeService.getCityByAreaCode(areaCode);
		List<EtbAreaCode> county_list = null;
		List<EtbClientDevice> device = null;
		
		if(null != city_list && city_list.size() > 0){
			county_list = etbAreaCodeService.getCountyByAreaCode(city_list.get(0).getAreaCode());
		}
		if(null != county_list && county_list.size() > 0){
			device = etbClientDeviceService.findClientDeviceByAreaId(county_list.get(0).getAreaId());
		}
		String city_json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(city_list);
		String county_json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(county_list);
		String device_json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(device);
		
		Map<String,String> obj = new HashMap<String,String>();
		obj.put("city", city_json);
		obj.put("county", county_json);
		obj.put("device", device_json);
		String area = JsonHelper.getGson().toJson(obj);
		
		this.writeResultToResponse(area);
	}
	
	public void getCountyByCity(){
		List<EtbAreaCode> county_list = etbAreaCodeService.getCountyByAreaCode(areaCode);
		List<EtbClientDevice> device = null;
		if(null != county_list && county_list.size() > 0){
			device = etbClientDeviceService.findClientDeviceByAreaId(county_list.get(0).getAreaId());
		}
		
		String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(county_list);
		String device_json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(device);
		
		Map<String,String> obj = new HashMap<String,String>();
		obj.put("county", json);
		obj.put("device", device_json);
		String area = JsonHelper.getGson().toJson(obj);
		this.writeResultToResponse(area);
	}
	
	public void getDeviceByArea(){
		List<EtbClientDevice> device = etbClientDeviceService.findClientDeviceByAreaId(Integer.parseInt(areaId));
		
		String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(device);
		this.writeResultToResponse(json);
	}
	
	
	/**
	 * 进入横版新增画面
	 * 
	 * @return
	 */
	public String toHbTemplateChoose() {
		reAdPackage();
		this.setAdStyle(Constant.AD_TEMPLATE_TYPE_HENG);

		return "adHbTemplateList";
	}
	
	/**
	 * 进入竖版新增画面
	 * 
	 * @return
	 */
	public String toSbTemplateChoose() {
		reAdPackage();
		this.setAdStyle(Constant.AD_TEMPLATE_TYPE_SHU);

		return "adSbTemplateList";
	}
	
	/**
	 * 进入竖版新增画面
	 * 
	 * @return
	 */
	public String toScrollTemplateChoose() {
		reAdPackage();
		this.setAdStyle(Constant.AD_TEMPLATE_TYPE_SHU);

		return "adScrollTemplateList";
	}
	
	/**
	 * 审核横版广告
	 */
	public String toCheckAdList(){
		
		return "adCheckList";
	}
	
	/**
	 * 审核横版广告
	 */
	public String toCheckHB(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String adPackageId = request.getParameter("adPackageId");
		
		request.setAttribute("adPackageId", adPackageId);
		return "adCheckHb";
	}
	
	/**
	 * 审核竖版广告
	 */
	public String toCheckSB(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String adPackageId = request.getParameter("adPackageId");
		
		request.setAttribute("adPackageId", adPackageId);
		return "adCheckSb";
	}
	
	/**
	 * 审核滚动广告
	 */
	public String toCheckText(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String adPackageId = request.getParameter("adPackageId");
		
		request.setAttribute("adPackageId", adPackageId);
		return "adCheckText";
	}
	
	/**
	 * 发布记录
	 * @return
	 */
	public String toReleaseRecord(){
		reAdPackage();
		
		return "adReleaseRecord";
	}
	
	/**
	 * 发布广告
	 * @return
	 */
	public String toReleaseAd(){
		provinces = etbAreaCodeService.getAllProvince();
		
		return "adRelease";
	}
	
	/**
	 * 制作后直接发布横版广告
	 * @return
	 */
	public String toReleaseHbAd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String adPackageId = request.getParameter("adPackageId");
		
		provinces = etbAreaCodeService.getAllProvince();
		request.setAttribute("adPackageId", adPackageId);
		return "adReleaseHb";
	}
	
	/**
	 * 制作后直接发布竖版广告
	 * @return
	 */
	public String toReleaseSbAd(){
		reAdPackage();
		
		return "adReleaseSb";
	}
	
	/**
	 * 制作后直接发布滚动广告
	 * @return
	 */
	public String toReleaseTextAd(){
		reAdPackage();
		
		return "adReleaseText";
	}
	
	/**
	 * 发布滚动广告
	 * @return
	 */
	public String toAdList(){
		reAdPackage();
		
		return "adList";
	}
	
	public void getTemplateList(){
		adTemplateList = adService.listAllAdTemplate(Integer.parseInt(templateType));
		List<Map<String, Object>> arr = new ArrayList<Map<String, Object>>();
		for(int i = 0;i < adTemplateList.size();i ++){
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("tempId", adTemplateList.get(i).getAdTemplateId());
			obj.put("path", adTemplateList.get(i).getAdTemplatePath());
			obj.put("areaCount", String.valueOf(adTemplateList.get(i).getAdTemplateAreaCount()));
			
			arr.add(obj);
		}
		String arrstr = JsonHelper.getGson().toJson(arr);
		this.writeResultToResponse(arrstr);
	}
	
	@SuppressWarnings("deprecation")
	public void getLocalResource(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String imagePath = "sc_image";
		String videoPath = "sc_video";
		List<Map<String, Object>> image_list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> video_list = new ArrayList<Map<String, Object>>();
		
		File image_file = new File(request.getRealPath(imagePath));
		if(image_file.exists()){
			File[] files = image_file.listFiles();
			if (files.length > 0) {
				for (File image : files) {
					//不是文件夹
					if (!image.isDirectory()) {
						if(image.getName().indexOf("DS_Store") < 0){
							Map<String, Object> obj = new HashMap<String, Object>();
							obj.put("path", request.getContextPath() + File.separator + imagePath + File.separator + image.getName());
							obj.put("title", image.getName());
							
							image_list.add(obj);
						}
					}
				}
			}
		}
		
		File video_file = new File(request.getRealPath(videoPath));
		if(video_file.exists()){
			File[] files = video_file.listFiles();
			if (files.length > 0) {
				for (File video : files) {
					//不是文件夹
					if (!video.isDirectory()) {
						if(video.getName().indexOf("DS_Store") < 0){
							Map<String, Object> obj = new HashMap<String, Object>();
							obj.put("path", request.getContextPath() + File.separator + videoPath + File.separator + video.getName());
//							obj.put("path", video.getPath());
							obj.put("title", video.getName());
							
							video_list.add(obj);
						}
					}
				}
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("image_list", image_list);
		map.put("video_list", video_list);
		
		String mapstr = JsonHelper.getGson().toJson(map);
		this.writeResultToResponse(mapstr);
	}
	
	@SuppressWarnings("deprecation")
	public void getLocalMusicResource(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String imagePath = "sc_music";
		List<Map<String, Object>> music_list = new ArrayList<Map<String, Object>>();
		
		File image_file = new File(request.getRealPath(imagePath));
		if(image_file.exists()){
			File[] files = image_file.listFiles();
			if (files.length > 0) {
				for (File music : files) {
					//不是文件夹
					if (!music.isDirectory()) {
						if(music.getName().indexOf("DS_Store") < 0){
							Map<String, Object> obj = new HashMap<String, Object>();
							obj.put("path", request.getContextPath() + File.separator + imagePath + File.separator + music.getName());
							obj.put("title", music.getName());
							
							music_list.add(obj);
						}
					}
				}
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("music_list", music_list);
		
		String mapstr = JsonHelper.getGson().toJson(map);
		this.writeResultToResponse(mapstr);
	}
	
	public void uploadResource(){
		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response  = ServletActionContext.getResponse();
		boolean updFlag = false;
		String adTemplateIdTmp = request.getParameter("setAdTemplateId");
		String adTypeTmp = request.getParameter("adType");
		String filePath = "";
		if(resourceType.equals("1")){
			filePath = "sc_image";
		}
		if(resourceType.equals("2")){
			filePath = "sc_video";
		}
		if(resourceType.equals("3")){
			filePath = "sc_music";
		}
		// 获取文件后缀名
		String postfix = fileuploadFileName.substring(fileuploadFileName.lastIndexOf("."));
		String uuid = UUIDGenerator.getUUID();
		// 新文件名
		newFileName = uuid + postfix;

		// 缩略图名称
		newThumbnailName = uuid + Constant.AD_FILE_THUMBNAIL_POSTFIX;
		
		updFlag = uploadPic(filePath, newFileName, adTemplateIdTmp, adTypeTmp, "", resourceType, newThumbnailName);
		Map<String, Object> map = new HashMap<String, Object>();
		if(updFlag){
			map.put("path", request.getContextPath() + File.separator + filePath + File.separator);
			map.put("name", newFileName);
//			File im = new File(ServletActionContext.getServletContext().getRealPath(File.separator) + filePath + File.separator + newFileName);
//			try {
//				BufferedImage bi = ImageIO.read(im);
//				long time = im.lastModified();//返回文件最后修改时间，是以个long型毫秒数
//				System.out.print("width:" + bi.getWidth() + "\nheight:" + bi.getHeight());
//				String ctime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
//				System.out.println("\ntime:" + ctime);
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
			map.put("thumName", newThumbnailName);
			map.put("status", "true");
		}
		
		String mapstr = JsonHelper.getGson().toJson(map);
		this.writeResultToResponse(mapstr);
	}
	
	/**
	 * 提交所有广告信息
	 */
	public void putAllAdInfo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String parm = request.getParameter("parm");
		String adName = request.getParameter("adPackageName");
		String adBatchNo = request.getParameter("adPackageBatchNo");
		String adTemplateId = request.getParameter("adTemplateId");
		String adType = request.getParameter("adType");
		String adStatus = request.getParameter("adStatus");
		
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		//创建新的广告包
		reAdPackage();
		adPackage.setAdPackageId(UUIDGenerator.getUUID());// 广告包ID-UUID
		if(adType.equals("H") || adType.equals("S")){
			adType = "B";
		}else{
			adType = "M";
		}
		adPackage.setAdPackageBatchNo(adType + adBatchNo);
		adPackage.setAdTemplateId(Long.parseLong(adTemplateId));
		adPackage.setAdPackageType(adType);
		adPackage.setAdPackageName(adName);
		adPackage.setAdPackageCreater(user.getUserInfoName());
		adPackage.setAdPackageCreaterTime(new Date());
		if(adStatus.equals("0")){
			adPackage.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_ADD);
		}
		if(adStatus.equals("1")){
			adPackage.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_COMMITTED);
		}
		adPackage.setAdPackageLoopType(0);
		adPackage.setAdPackagePlayType(0);
		adPackage.setDataStat(0);
		adPackage.setAdPackageTips("");
		adService.saveAdPackage(adPackage);
		
		Gson gson = JsonHelper.getGson();
		if(parm != null && parm.length() > 0){
			String[] arr = null;
			if(parm.indexOf("}") > -1){
				arr = parm.split("}");
			}
			for(int i = 0;i < arr.length;i ++){
				String str = arr[i];
				if(str.indexOf("}") == -1){
					str = str + "}";
				}
				if(str.indexOf(",{") > -1){
					str = str.substring(1);
				}
				AdResource adResource_info = gson.fromJson(str, AdResource.class);
				if(adResource_info != null){
					adResource_info.setAdPackageId(adPackage.getAdPackageId());
					
					adResource_info.setAdResourceCount(1);
					adResource_info.setAdResourcePositionSign("0");
					adResource_info.setDataStat(0);
					adResource_info.setAdResourceTips("");
					
					adService.saveOrUpdateAdResource(adResource_info);
				}
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "true");
		map.put("adPackageId", adPackage.getAdPackageId());
		
		String mapstr = JsonHelper.getGson().toJson(map);
		this.writeResultToResponse(mapstr);
	}
	
	/**
	 * 获取广告包的信息
	 */
	public void getAdPackageInfo(){
		reAdPackage();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String adPackageId = request.getParameter("adPackageId");
		
		adPackage = adService.getAdPackageById(adPackageId);
		AdTemplate adTemp = adService.getAdTemplateById(adPackage.getAdTemplateId());
		
		Map<String, Object> map = new HashMap<String, Object>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		map.put("adPackageId", adPackage.getAdPackageId());
		map.put("adPackageBatchNo", adPackage.getAdPackageBatchNo());
		map.put("adPackageName", adPackage.getAdPackageName());
		map.put("adPackageCreaterTime", sdf2.format(adPackage.getAdPackageCreaterTime()));
		map.put("adPackageCreater", adPackage.getAdPackageCreater());
		if(null != adPackage.getAdPackageBegintime()){
			map.put("adPackageBeginTime", sdf2.format(adPackage.getAdPackageBegintime()));
		}else{
			map.put("adPackageBeginTime", "");
		}
		if(null != adPackage.getAdPackageEndtime()){
			map.put("adPackageEndTime", sdf2.format(adPackage.getAdPackageEndtime()));
		}else{
			map.put("adPackageEndTime", "");
		}
		if(null != adPackage.getAdPackagePublishTime()){
			map.put("adPackagePublishTime", sdf2.format(adPackage.getAdPackagePublishTime()));
		}else{
			map.put("adPackagePublishTime", "");
		}
		
		map.put("adPackageLoopType", adPackage.getAdPackageLoopType());
		map.put("adPackageResolution", adTemp.getAdTemplateResolution());
		map.put("adPackageImage", adTemp.getAdTemplatePath());
		map.put("adPackageUrl", "");
		
		String mapstr = JsonHelper.getGson().toJson(map);
		this.writeResultToResponse(mapstr);
	}
	
	/**
	 * 获取广告包资料列表
	 */
	public void getAdPackageResourceList(){
		reAdPackage();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String adPackageId = request.getParameter("adPackageId");
		
		adPackage = adService.getAdPackageById(adPackageId);
		
		List<AdResource> resource_list = adService.listAdResourceByAdPackageId(adPackageId);
		List<Map<String, Object>> res_list = new ArrayList<Map<String, Object>>();
		for(int i = 0;i < resource_list.size();i ++){
			Map<String, Object> res_map = new HashMap<String, Object>();
			res_map.put("res_id", resource_list.get(i).getAdResourceId());
			
			res_map.put("res_path", resource_list.get(i).getAdResourcePath());
			res_map.put("res_time", resource_list.get(i).getAdResourceTimeH() + ":" + resource_list.get(i).getAdResourceTimeM() + ":" + resource_list.get(i).getAdResourceTimeS());
			res_map.put("res_number", resource_list.get(i).getAdResourceNumber());
			
			res_list.add(res_map);
		}
		
		String mapstr = JsonHelper.getGson().toJson(res_list);
		this.writeResultToResponse(mapstr);
	}
	
	/**
	 * 获取广告包的所有设备
	 */
	public void getAdPackageDeviceList(){
		reAdPackage();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String adPackageId = request.getParameter("adPackageId");
		
		adPackage = adService.getAdPackageById(adPackageId);
		List<Map<String, Object>> device = new ArrayList<Map<String, Object>>();
		for(EtbClientDevice client : adPackage.getEtbClientDeviceList()){
			Map<String, Object> device_info = new HashMap<String, Object>();
			device_info.put("device_imei", client.getImei());
			device_info.put("device_name", client.getName());
			EtbAreaCode area = etbAreaCodeService.getAreaById(client.getAreaId());
			EtbAreaCode city = etbAreaCodeService.getAreaByParentCode(area.getAreaParentCode());
			EtbAreaCode province = etbAreaCodeService.getAreaByParentCode(city.getAreaParentCode());
			
			String address = "";
			if(null != client.getAddress()){
				address = client.getAddress();
			}
			device_info.put("device_address", province.getAreaName() + city.getAreaName() + area.getAreaName() + address);
			
			device.add(device_info);
		}
		
		String mapstr = JsonHelper.getGson().toJson(device);
		this.writeResultToResponse(mapstr);
	}
	
	/**
	 * 根据条件分页获取设备
	 */
	public void getDeviceList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String pageNum = request.getParameter("pageNum");
		int pageSize = 10;
		String area_Id = request.getParameter("area_Id");
		int areaId = 0;
		String areaCode = request.getParameter("areaCode");
		String resolution = request.getParameter("resolution");
		if(pageNum == null){
			pageNum = "1";
		}
		if(null != area_Id && area_Id.length() > 0){
			areaId = Integer.parseInt(area_Id);
		}
		if(null != areaCode &&  areaCode.equals("0")){
			areaCode = null;
		}
		
		Map<String,String> obj = new HashMap<String,String>();
		//获取地区信息
		if(areaCode != null && areaCode.length() > 0){
			EtbAreaCode area = etbAreaCodeService.getAreaByCode(areaCode);
			List<EtbAreaCode> city_list = null;
			List<EtbAreaCode> county_list = null;
			
			if(area.getAreaDeep() == 1){
				city_list = etbAreaCodeService.getCityByAreaCode(areaCode);
				
				if(null != city_list && city_list.size() > 0){
					county_list = etbAreaCodeService.getCountyByAreaCode(city_list.get(0).getAreaCode());
				}
				if(null != county_list && county_list.size() > 0){
					areaId = county_list.get(0).getAreaId();
				}
			}
			if(area.getAreaDeep() == 2){
				if(null != city_list && city_list.size() > 0){
					county_list = etbAreaCodeService.getCountyByAreaCode(areaCode);
				}
				if(null != county_list && county_list.size() > 0){
					areaId = county_list.get(0).getAreaId();
				}
			}
			
			if(area.getAreaDeep() != 3){
				if(null != city_list){
					String city_json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(city_list);
					String county_json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(county_list);
					
					obj.put("city", city_json);
					obj.put("county", county_json);
				}else{
					String county_json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(county_list);
					
					obj.put("county", county_json);
				}
			}
		}
		
		List<Object> device_list = etbClientDeviceService.getDeviceListByAreaOrResolution(areaId, resolution, pageSize, Integer.parseInt(pageNum));
		List<Map<String, Object>> device = new ArrayList<Map<String, Object>>();
		for(int i = 0;i < device_list.size();i ++){
			Map<String, Object> device_info = new HashMap<String, Object>();
			Object[] dev = (Object[])device_list.get(i);
			EtbClientDevice client = (EtbClientDevice)dev[0];
			
			device_info.put("device_imei", client.getImei());
			device_info.put("device_name", client.getName());
			EtbAreaCode area = etbAreaCodeService.getAreaById(client.getAreaId());
			EtbAreaCode city = etbAreaCodeService.getAreaByParentCode(area.getAreaParentCode());
			EtbAreaCode province = etbAreaCodeService.getAreaByParentCode(city.getAreaParentCode());
			
			String address = "";
			if(null != client.getAddress()){
				address = client.getAddress();
			}
			device_info.put("device_address", province.getAreaName() + city.getAreaName() + area.getAreaName() + address);
			
			device.add(device_info);
		}
		
		String device_json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(device);
		obj.put("device", device_json);
		
		String mapstr = JsonHelper.getGson().toJson(obj);
		this.writeResultToResponse(mapstr);
	}
	
	/**
	 * 分页获取需要审核的广告
	 */
	public void getCheckAdList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String pageNum = request.getParameter("pageNum");
		int pageSize = 10;
		if(pageNum == null){
			pageNum = "1";
		}
		List<AdPackage> ad_list = adService.listAdByStatusAndPage(2, Integer.parseInt(pageNum), pageSize);
		int totalNum = adService.getAdCountByAdStatus(2);
		int totalPage = 0;
		if(totalNum % 10 == 0){
			totalPage = totalPage / 10;
		}else{
			totalPage = (totalPage / 10) + 1; 
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Map<String, Object>> ad_map_list = new ArrayList<Map<String, Object>>();
		for(int i = 0;i < ad_list.size();i ++){
			Map<String, Object> ad_map = new HashMap<String, Object>();
			ad_map.put("adPackageId", ad_list.get(i).getAdPackageId());
			ad_map.put("adPackageBatchNo", ad_list.get(i).getAdPackageBatchNo());
			ad_map.put("adPackagePublisher", ad_list.get(i).getAdPackagePublisher());
			ad_map.put("adPackagePublishTime", sdf2.format(ad_list.get(i).getAdPackagePublishTime()));
			ad_map.put("adPackageBeginTime", sdf.format(ad_list.get(i).getAdPackageBegintime()));
			ad_map.put("adPackageType", ad_list.get(i).getAdPackageType());
			
			ad_map_list.add(ad_map);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ad_list", ad_map_list);
		map.put("totalNum", totalNum);
		map.put("totalPage", totalPage);
		
		String mapstr = JsonHelper.getGson().toJson(map);
		this.writeResultToResponse(mapstr);
	}
	
	/**
	 * 获取需要发布的横版广告
	 */
	public void getReleaseAdList(){
		List<Object> ad_list = adService.listAdByStatus(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date_str = "";
		List<Object> ad_date_list = new ArrayList<Object>();
		List<Object> ad_info_list = new ArrayList<Object>();
		for(int i = 0;i < ad_list.size();i ++){
			Map<String, Object> ad_map = new HashMap<String, Object>();
			
			Object[] ad = (Object[])ad_list.get(i);
			AdPackage ad_package = (AdPackage)ad[0];
			AdTemplate adTemp = (AdTemplate)ad[1];
			String ad_date = sdf.format(ad_package.getAdPackageCreaterTime());
			Map<String, String> ad_info = new HashMap<String, String>();
			
			//添加需要的广告包属性
			ad_info.put("adPackageId", ad_package.getAdPackageId());
			ad_info.put("adPackageBatchNo", ad_package.getAdPackageBatchNo());
			ad_info.put("adPackageName", ad_package.getAdPackageName());
			ad_info.put("adPackageCreater", ad_package.getAdPackageChecker());
			ad_info.put("adPackageCreaterTime", sdf2.format(ad_package.getAdPackageCreaterTime()));
			ad_info.put("adPackageImage", adTemp.getAdTemplatePath());
			ad_info.put("adPackageRp", adTemp.getAdTemplateResolution());
			ad_info.put("adPackageUrl", "");
			if(date_str.length() > 0 && !date_str.equals(ad_date)){
				ad_map.put("time", date_str);
				ad_map.put("data_list", ad_date_list);
				ad_info_list.add(ad_map);
				
				date_str = ad_date;
				ad_date_list = new ArrayList<Object>();
				
				ad_date_list.add(ad_info);
			}else{
				date_str = ad_date;
				ad_date_list.add(ad_info);
			}
			if(i == ad_list.size() - 1){
				ad_map = new HashMap<String, Object>();
				ad_map.put("time", date_str);
				ad_map.put("data_list", ad_date_list);
				
				ad_info_list.add(ad_map);
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ad_list", ad_info_list);
		
		String mapstr = JsonHelper.getGson().toJson(map);
		this.writeResultToResponse(mapstr);
	}
	
	/**
	 * 判断设置时间是否冲突
	 */
	public void checkTimeConflict(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date start = null;
		Date end = null;
		
		try {
			start = sdf.parse(startTime);
			end = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<AdPackage> conflict = adService.getConflictAdPackageByTime(start, end);
		Map<String, Object> map = new HashMap<String, Object>();
		if(null != conflict && conflict.size() > 0){
			map.put("status", "true");
		}else{
			map.put("status", "false");
		}
		
		String mapstr = JsonHelper.getGson().toJson(map);
		this.writeResultToResponse(mapstr);
	}
	
	/**
	 * 推送需要发布的广告信息
	 */
	public void putPushAdInfo(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String adPackageId_list = request.getParameter("adPackageId");
		String adPackagePlayType = request.getParameter("playType");
		String adPackageLoopType = request.getParameter("loopType");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String device_imei_list = request.getParameter("device_imei_list");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		
		String[] ad_id = adPackageId_list.split(",");
		for(int o = 0;o < ad_id.length;o ++){
			AdPackage ad = adService.getAdPackageById(ad_id[o]);
			Set<EtbClientDevice> device_list = new HashSet<EtbClientDevice>();
			ad.setAdPackagePlayType(Integer.parseInt(adPackagePlayType));
			ad.setAdPackageLoopType(Integer.parseInt(adPackageLoopType));
			ad.setAdPackagePublisher(user.getUserInfoName());
			ad.setAdPackagePublishTime(new Date());
			ad.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_PUBLISHED);
			if(Integer.parseInt(adPackagePlayType) > 0){
				ad.setAdPackageBegintime(null);
			}else{
				try {
					ad.setAdPackageBegintime(sdf.parse(startTime));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			try {
				ad.setAdPackageEndtime(sdf.parse(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String[] device_imei = device_imei_list.split(",");
			for(int i = 0;i < device_imei.length;i ++){
				EtbClientDevice device = etbClientDeviceService.findDeviceInfoById(device_imei[i]);
				device_list.add(device);
			}
			ad.setEtbClientDeviceList(device_list);
			adService.saveAdPackage(ad);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "true");
		
		String mapstr = JsonHelper.getGson().toJson(map);
		this.writeResultToResponse(mapstr);
	}
	
	/**
	 * 审核广告
	 */
	@SuppressWarnings("static-access")
	public void checkPassAdPackage(){
		reAdPackage();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String adPackageId = request.getParameter("adPackageId");
		String adPackageTips = request.getParameter("adPackageTips");
		String Status = request.getParameter("adStatus");
		
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		adPackage = adService.getAdPackageById(adPackageId);
		if(Status.equals("3")){
			adPackage.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_CHECK_PASSED);
		}
		if(Status.equals("4")){
			adPackage.setAdPackageStatus(Constant.AD_PACKAGE_STATUS_CHECK_UNPASSED);
		}
		adPackage.setAdPackageTips(adPackageTips);
		adPackage.setAdPackageChecker(user.getUserInfoName());
		adPackage.setAdPackageCheckTime(new Date());
		
		String filename = adPackage.getAdPackageBatchNo() + ".zip";
		adPackage.setAdPackagePath(filename);
		
		adService.saveAdPackage(adPackage);
		
		//创建zip包和XML播放文件
		String flag = null;
		if(Status.equals("3")){
			List<EtbClientAdDevice> adDevice = adService.getAdDeviceByAdPackageId(adPackage.getAdPackageId());
			for(int i = 0;i < adDevice.size();i ++){
				adDevice.get(i).setAdPackageBegintime(adPackage.getAdPackageBegintime());
				adDevice.get(i).setAdPackageBegintime(adPackage.getAdPackageBegintime());
				
				adService.saveOrUpdateAdDevice(adDevice.get(i));
			}
			
			flag = adService.createZipAndXmlFile(adPackage);
		}
		
		//广告播放时间为立即播放，处理时间冲突的广告：
		if(flag == null && adPackage.getAdPackageLoopType() > 0){
			Date start = new Date();
			Date end = adPackage.getAdPackageEndtime();
			List<AdPackage> conflict = adService.getConflictAdPackageByTime(start, end);
			//覆盖原有广告
			if(adPackage.getAdPackageLoopType() == 1){
				for(int i = 0;i < conflict.size();i ++){
					conflict.get(i).setDataStat(1);
					adService.saveAdPackage(conflict.get(i));
				}
			}
			//原有广告顺延
			if(adPackage.getAdPackageLoopType() == 2){
				Calendar calendar = new GregorianCalendar();
				for(int i = 0;i < conflict.size();i ++){
					if(conflict.get(i).getAdPackageBegintime().after(start)){
						//没有开始的广告，还剩余时间
						long time = conflict.get(i).getAdPackageEndtime().getTime() - conflict.get(i).getAdPackageBegintime().getTime();
						Date newStart = null;
						Date newEnd = null;
						if(i == 0){
							newStart = end;
							calendar.setTime(newStart);
							calendar.add(calendar.SECOND,1);//把日期往后增加一秒.整数往后推,负数往前移动
							newStart = calendar.getTime();
							newEnd = new Date(newStart.getTime() + time);
							conflict.get(i).setAdPackageBegintime(newStart);
							conflict.get(i).setAdPackageEndtime(newEnd);
						}else{
							newStart = conflict.get(i - 1).getAdPackageEndtime();
							calendar.setTime(newStart);
							calendar.add(calendar.SECOND,1);//把日期往后增加一秒.整数往后推,负数往前移动
							newStart = calendar.getTime();
							newEnd = new Date(newStart.getTime() + time);
							conflict.get(i).setAdPackageBegintime(newStart);
							conflict.get(i).setAdPackageEndtime(newEnd);
						}
					}else{
						//已经开始的光，还剩余时间
						long time = conflict.get(i).getAdPackageEndtime().getTime() - start.getTime();
						Date newStart = null;
						Date newEnd = null;
						if(i == 0){
							newStart = end;
							calendar.setTime(newStart);
							calendar.add(calendar.SECOND,1);//把日期往后增加一秒.整数往后推,负数往前移动
							newStart = calendar.getTime();
							newEnd = new Date(newStart.getTime() + time);
							conflict.get(i).setAdPackageBegintime(newStart);
							conflict.get(i).setAdPackageEndtime(newEnd);
						}else{
							newStart = conflict.get(i - 1).getAdPackageEndtime();
							calendar.setTime(newStart);
							calendar.add(calendar.SECOND,1);//把日期往后增加一秒.整数往后推,负数往前移动
							newStart = calendar.getTime();
							newEnd = new Date(newStart.getTime() + time);
							conflict.get(i).setAdPackageBegintime(newStart);
							conflict.get(i).setAdPackageEndtime(newEnd);
						}
					}
					adService.saveAdPackage(conflict.get(i));
				}
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(flag != null){
			map.put("status", "false");
		}else{
			map.put("status", "true");
		}
		
		String mapstr = JsonHelper.getGson().toJson(map);
		this.writeResultToResponse(mapstr);
	}
}
