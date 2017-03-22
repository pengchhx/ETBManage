package com.archermind.etb.ota.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.common.UUIDGenerator;
import com.archermind.etb.ota.domain.OtaUpdatePack;
import com.archermind.etb.ota.domain.OtaVersionInfo;
import com.archermind.etb.ota.service.OtaUpdatepackService;
import com.archermind.etb.ota.service.OtaVersionService;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.JsonHelper;
import com.archermind.etb.util.PropertyUtil;
import com.archermind.etb.util.WsClientUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * ota模块action的增删改查
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130711
 * @see com.archermind.etb.common.BaseAction
 * @since 1.0
 */
@Controller
@Scope("prototype")
@Namespace("/ota")
@Action(value = "versioninfo", results = {
		@Result(name = "list", location = "otalist.jsp"),		
		@Result(name = "preadd", location = "addota.jsp"),
		@Result(name = "relist", location = "releaseList.jsp"),
		@Result(name = "auditlist", location = "auditList.jsp"),
		@Result(name = "preUnpassOta", location = "refuseOta.jsp"),
		@Result(name = "queryota", location = "queryOta.jsp"),
		@Result(name = "preAuditOta", location = "auditOta.jsp"),
		@Result(name = "preReleaseOta", location = "releaseOta.jsp"),
		@Result(name = "packlist",type="redirect",location = "updatepack!getVersionUpdatePack.do?otaId=${otaId}")
})
public class OtaVersionAction extends BaseAction{

	private static final long serialVersionUID = -3204885835055181844L;
	
	private static final Logger log = Logger.getLogger(OtaVersionAction.class);

	/**
	 *存在
	 */
	private static final int  ISEXIST = 0;
	/**
	 * 已删除
	 */
	private static final int  ISDELETE = 1;
	/**
	 * 完整包状态标示
	 */
	private static final int PACKAGE_TYPE_COMPLETE = 0;
	
	@Resource(name = "com.archermind.etb.ota.service.OtaversionService")
	private OtaVersionService otaVersionService;
	@Resource(name = "com.archermind.etb.ota.service.OtaUpdatepackService")
	private OtaUpdatepackService otaUpdatePackService;

	/**
	 * 返回OtaVersionInfo类型的集合
	 */
	private List<OtaVersionInfo> list;
	/**
	 * 返回单一ota
	 */
	private OtaVersionInfo otaInfo;
	/**
     * 接收页面查询条件name,查询升级包的条件
     */
	private String name;
	/**
	 * 删除，修改的唯一标识
	 */
	private String otaId;
	/**
	 * 返回状态信息
	 */
	private String msg;
	/**
	 * 版本下所有升级包
	 */
	private List<OtaUpdatePack> packList;
	/**
	 * 是否可以添加的标示符
	 */
	private boolean canBeAdd;
	/**
	 * ota的名称
	 */
	private String otaName;
	/**
	 * 分页查询出ota列表
	 * @return
	 */
	public String getOtaVersionInfoList()
	{
		boolean havaUnReleaseOta = this.otaVersionService.havaUnReleaseOta();
		//如果有没有发布的ota，是不允许添加新的ota版本的
		if(havaUnReleaseOta)
		{
			canBeAdd=false;
		}else
		{
			canBeAdd=true;
		}
		this.totalCount = otaVersionService.getOtaCount(name,null,ISEXIST,null);
		list = otaVersionService.getOtaVersionInfoList(name,null,ISEXIST,this.getPageNum(),this.numPerPage);
		return "list";
	}
	
	/**
	 * 欲添加ota页面
	 * @return
	 */
	public String preAddOta()
	{
		String canAdd = ServletActionContext.getRequest().getParameter("canAdd");
		if(null!=canAdd&&canAdd.equals("no"))
		{
			this.writeDwzResultToResponse(Constant.HAVE_OTA_NOT_RELEASE, Constant.HTTP_STATUS_FAILED,
					"otamanger", null, null, null);
		return null;
		}
		return "preadd";
	}
	
	/**
	 * 添加ota
	 * @return
	 */
	public String addOta()
	{
		Subject subject = SecurityUtils.getSubject();
		//设置创建人和创建时间
		otaInfo.setCreater(String.valueOf(subject.getSession()
				.getAttribute(Constant.SESSION_USER_NAME)));
		otaInfo.setCreatetime(new Date());
		int buildversion = this.otaVersionService.getMaxBuildVersion()+1;
		otaInfo.setBuildversion(buildversion);
		otaVersionService.saveOta(otaInfo);
		log.debug("Ota Saved successfully");
//		this.writeDwzResultToResponse(Constant.SAVE_SUCCESS, Constant.HTTP_STATUS_OK,
//				"otamanger", null, "closeCurrent", null);
		//otaName=otaInfo.getName();
		otaInfo = this.otaVersionService.getOtaInfoByName(otaInfo.getName());
		otaId=String.valueOf(otaInfo.getId());
		return "packlist";
	}
	
	/**
	 * 删除ota
	 * @return
	 */
	public String deleteOta()
	{
		if(otaId == null){
			return "500";
			
		} else {
			OtaVersionInfo info = otaVersionService.getOtaInfoById(Integer.parseInt(otaId));
			//版本下有升级包的不允许删除
			int packnum = otaUpdatePackService.getPackCount(info.getName(), null, ISEXIST);
			if(packnum>0)
			{
				this.writeDwzResultToResponse(Constant.HAVE_PACK, Constant.HTTP_STATUS_FAILED,
						"otamanger", null, null, null);
				return null;
			}
			boolean isdel = otaVersionService.deleteOtaversioninfo(info);
			if(isdel)
			{
				log.debug("Ota Deleted successfully");
				this.writeDwzResultToResponse(Constant.DELETE_SUCCESS, Constant.HTTP_STATUS_OK,
						"otamanger", null, null, null);
			}else
			{
				log.debug("Ota Deleted failed");
				this.writeDwzResultToResponse(Constant.DELETE_FAILED, Constant.HTTP_STATUS_OK,
						"otamanger", null, null, null);
			}	
			return null;	
		}
		
			
	}
	/**
	 * 分页查询出待发布的ota列表
	 * @return
	 */
	public String getOtaReleaseList()
	{
		this.totalCount = otaVersionService.getReleaseOtaCount(name,ISEXIST,null);
//		this.totalPage = this.initTotalPage(this.totalCount);
//		if(this.getPageNum() > this.totalPage)
//		{
//			this.getPageNum() = this.totalPage;
//		}
		list = otaVersionService.getReleaseInfoList(name,ISEXIST,this.getPageNum(),this.numPerPage);
		return "relist";
	}
	/**
	 * 分页查询出待审核的ota列表
	 * @return
	 */
	public String getOtaAuditList()
	{
		this.totalCount = otaVersionService.getPreAuditOtaCount(name, ISEXIST, null);
//		this.totalPage = this.initTotalPage(this.totalCount);
//		if(this.getPageNum() > this.totalPage)
//		{
//			this.getPageNum() = this.totalPage;
//		}
		list = otaVersionService.getPreAuditInfoList(name, ISEXIST, this.getPageNum(),this.numPerPage);
		return "auditlist";
	}	
//	/**
//	 * 欲修改页面，审核通过,已提交和已发布不允许修改
//	 * @return
//	 */
//	public String preEditOta()
//	{
//		
//		otaInfo = otaVersionService.getOtaInfoById(otaInfo.getId());
//		if(otaInfo==null)
//		{
//			return "500";
//		}else
//		{
//			if(otaInfo.getInfostat()==Constant.OTA_VER_INFO_STATUS_CHECKED
//					||otaInfo.getInfostat()==Constant.OTA_VER_INFO_STATUS_PUBLISHED
//					||otaInfo.getInfostat()==Constant.OTA_VER_INFO_STATUS_SUBMIT)
//			{
//				this.writeDwzResultToResponse(Constant.HAVE_AUDIT_RELEASE, Constant.HTTP_STATUS_FAILED,
//							"otamanger", null, null, null);
//				return null;
//			}
//			packList = this.otaUpdatePackService.getVersionUpdatePack(otaInfo.getName(), ISEXIST);
//			return "preeditota";
//		}
//	}
	/**
	 * 修改ota
	 * @return
	 */
	public String editOta()
	{
		if(null!=otaInfo)
		{
			OtaVersionInfo info = otaVersionService.getOtaInfoById(otaInfo.getId());
//			if(info.getInfostat()==Constant.OTA_VER_INFO_STATUS_CHECKED||info.getInfostat()==Constant.OTA_VER_INFO_STATUS_PUBLISHED)
//			{
//				this.writeDwzResultToResponse(Constant.HAVE_AUDIT_RELEASE, Constant.HTTP_STATUS_FAILED,
//							"otamanger", null, "closeCurrent", null);
//				return null;
//			}
			info.setName(otaInfo.getName());
			info.setTips(otaInfo.getTips());
			//审核不通过的再修改，设置infostat状态为3
//			if(info.getInfostat()==Constant.OTA_VER_INFO_STATUS_REFUSED)
//			{
//				info.setInfostat(Constant.OTA_VER_INFO_STATUS_UPDATED);
//			}
			otaVersionService.updateOta(info);
			log.debug("Ota edit successfully");
//			this.writeDwzResultToResponse(Constant.UPDATE_SUCCESS, Constant.HTTP_STATUS_OK,
//					"otamanger", null, "closeCurrent", null);
			//otaInfo = this.otaVersionService.getOtaInfoByName(otaInfo.getName());
			otaId=String.valueOf(otaInfo.getId());
			return "packlist";
		}
		return null;
	}
	/**
	 * 发布ota，修改infostat的值为4
	 * @return
	 */
	public String releaseOta()
	{
		OtaVersionInfo info = otaVersionService.getOtaInfoById(otaInfo.getId());
		if(info.getInfostat()==Constant.OTA_VER_INFO_STATUS_PUBLISHED)
		{
			this.writeDwzResultToResponse(Constant.PUBLISH_ADVERTISE_FAILED, Constant.HTTP_STATUS_FAILED,
					"releaseOta", null, null, null);
			return null;
		}
		info.setInfostat(Constant.OTA_VER_INFO_STATUS_PUBLISHED);
		//设置审核人和审核时间
		Subject subject = SecurityUtils.getSubject();
		info.setPublisher(String.valueOf(subject.getSession()
						.getAttribute(Constant.SESSION_USER_NAME)));
		info.setPublishtime(new Date());
		otaVersionService.updateOta(info);
		//HashMap jsonMap = new HashMap();
		HashMap map = new HashMap();
		map.put("type", Constant.OTA_TYPE);
		map.put("action", Constant.OTA_ACTION);
		//jsonMap.put("param", map);
		String json = JsonHelper.getGson().toJson(map);
		String wsdl = PropertyUtil.readData("webservice_wsdl");
		WsClientUtil.callCXFWservice(wsdl,Constant.NOTIFY_NEW_MESSAGE,new Object[]{UUIDGenerator.getUUID(),json,true});
		log.debug("Ota Released successfully");
		this.writeDwzResultToResponse(Constant.PUBLISH_ADVERTISE_SUCCESS, Constant.HTTP_STATUS_OK,
				"releaseOta", null, "closeCurrent", null);
		return null;
	}
	/**
	 * 审核通过ota，修改infostat的值为1
	 * @return
	 */
	public String auditPassOta()
	{
		int id = otaInfo.getId();
		//补全ota的信息
		otaInfo=otaVersionService.getOtaInfoById(id);
		//设置审核人和审核时间
		Subject subject = SecurityUtils.getSubject();
		otaInfo.setChecker(String.valueOf(subject.getSession()
				.getAttribute(Constant.SESSION_USER_NAME)));
		otaInfo.setChecktime(new Date());
		//检查该版本下是否有升级包
		int packnum=otaUpdatePackService.getPackCount(otaInfo.getName(), PACKAGE_TYPE_COMPLETE, ISEXIST);
		if(packnum==0)
		{
			//没有完整包，审核不通过
//			otaInfo.setInfostat(Constant.OTA_VER_INFO_STATUS_REFUSED);
//			otaInfo.setChecksuggest(Constant.CHECKED_FAILED_NOPACK+"\r\n"+new Date());
//			otaVersionService.updateOta(otaInfo);
			this.writeDwzResultToResponse(Constant.CHECKED_FAILED_NOPACK, Constant.HTTP_STATUS_FAILED,
					"auditOta", null, null, null);
			
		} else {
			otaInfo.setInfostat(Constant.OTA_VER_INFO_STATUS_CHECKED);
			otaVersionService.updateOta(otaInfo);
			log.debug("Ota Audites successfully");
			this.writeDwzResultToResponse(Constant.CHECKED_SUCCESS, Constant.HTTP_STATUS_OK,
					"auditOta", null, "closeCurrent", null);
		}
		return null;
	}
	/**
	 * 审核不通过,驳回理由填写
	 * @return
	 */
	public String preUnpassOta(){
		otaInfo = otaInfo==null? otaVersionService.getOtaInfoById(Integer.parseInt(otaId)):otaInfo;
		otaInfo.setId(Integer.parseInt(otaId));
		return "preUnpassOta";
	}
	/**
	 * 审核不通过ota，修改infostat的值为2
	 * @return
	 */
	public String auditUnpassOta()
	{
		OtaVersionInfo info = otaVersionService.getOtaInfoById(otaInfo.getId());
		info.setInfostat(Constant.OTA_VER_INFO_STATUS_UPDATED);
		info.setChecksuggest(otaInfo.getChecksuggest());
		//设置审核人和审核时间
		Subject subject = SecurityUtils.getSubject();
		info.setChecker(String.valueOf(subject.getSession()
				.getAttribute(Constant.SESSION_USER_NAME)));
		info.setChecktime(new Date());
		otaVersionService.updateOta(info);
		this.writeDwzResultToResponse(Constant.UNCHECKED_SUCCESS, Constant.HTTP_STATUS_OK,
				"preaudit_ota_info", null, "closeCurrent", null);
		return null;
	}
	/**
	 * 查询ota名称是否已经被占用
	 * @return
	 */
	public String nameIsExist()
	{
		if(null==otaInfo.getId())
		{
			otaInfo.setId(0);
		}
		boolean result = otaVersionService.nameIsExist(otaInfo.getName(), ISEXIST,otaInfo.getId());
		if(result){
			this.writeResultToResponse("true");
		}else{
			this.writeResultToResponse("false");
		}
		return null;
	}
	
	/**
	 * 查看ota
	 * @return
	 */
	public String queryOta()
	{
		if(null != otaInfo.getId())
		{
			otaInfo = otaVersionService.getOtaInfoById(otaInfo.getId());
			if(otaInfo == null)
			{
				return "500";
			}else
			{
				packList = this.otaUpdatePackService.getVersionUpdatePack(otaInfo.getName(), ISEXIST);
				return "queryota";
			}
		}
		return "500";
	}
	
	/**
	 * 欲审核ota
	 * @return
	 */
	public String preAuditOta()
	{
		if(null==otaInfo)otaInfo=otaVersionService.getOtaInfoById(Integer.parseInt(otaId));
		packList = this.otaUpdatePackService.getVersionUpdatePack(otaInfo.getName(), ISEXIST);
		return "preAuditOta";
	}
	/**
	 * 提交ota
	 * @return
	 */
	public String submitOta()
	{
		otaInfo=otaVersionService.getOtaInfoById(Integer.parseInt(otaId));
		int packnum=otaUpdatePackService.getPackCount(otaInfo.getName(), PACKAGE_TYPE_COMPLETE, ISEXIST);
		if(packnum==0)
		{
			//没有完整包，提交不成功
			this.writeDwzResultToResponse(Constant.CHECKED_FAILED_NOPACK, Constant.HTTP_STATUS_FAILED,
					null, null, null, null);
		}else
		{
		otaInfo.setInfostat(Constant.OTA_VER_INFO_STATUS_SUBMIT);
		otaVersionService.updateOta(otaInfo);
		this.writeDwzResultToResponse(Constant.AD_PACKAGE_ADD_FINISH, Constant.HTTP_STATUS_OK,
				"otamanger", null, "closeCurrent", null);
		}
		return null;
	}
	/**
	 * 保存至草稿
	 * @return
	 */
	public String saveToDraft()
	{
		this.writeDwzResultToResponse(Constant.AD_PACKAGE_ADD_FINISH, Constant.HTTP_STATUS_OK,
				"otamanger", null, "closeCurrent", null);
		return null;
	}
	
	/**
	 * 欲发布ota
	 * @return
	 */
	public String preReleaseOta()
	{
		if(null==otaInfo)otaInfo=otaVersionService.getOtaInfoById(Integer.parseInt(otaId));
		packList = this.otaUpdatePackService.getVersionUpdatePack(otaInfo.getName(), ISEXIST);
		return "preReleaseOta";
	}
	
	
	public List<OtaVersionInfo> getList() {
		return list;
	}

	public void setList(List<OtaVersionInfo> list) {
		this.list = list;
	}	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getOtaId() {
		return otaId;
	}

	public void setOtaId(String otaId) {
		this.otaId = otaId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public OtaVersionInfo getOtaInfo() {
		return otaInfo;
	}

	public void setOtaInfo(OtaVersionInfo otaInfo) {
		this.otaInfo = otaInfo;
	}

	public boolean isCanBeAdd()
	{
		return canBeAdd;
	}

	public void setCanBeAdd(boolean canBeAdd)
	{
		this.canBeAdd = canBeAdd;
	}

	
	public List<OtaUpdatePack> getPackList()
	{
		return packList;
	}

	public void setPackList(List<OtaUpdatePack> packList)
	{
		this.packList = packList;
	}

	public String getOtaName()
	{
		return otaName;
	}

	public void setOtaName(String otaName) 
	{
		this.otaName = otaName;
	}


	public OtaVersionService getOtaversionservice() {
		return otaVersionService;
	}

	public void setOtaversionservice(OtaVersionService otaVersionService) {
		this.otaVersionService = otaVersionService;
	}
	public OtaUpdatepackService getOtaupdatepackservice() {
		return otaUpdatePackService;
	}

	public void setOtaupdatepackservice(OtaUpdatepackService otaUpdatePackService) {
		this.otaUpdatePackService = otaUpdatePackService;
	}
}
