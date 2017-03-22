package com.archermind.etb.app.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.app.service.AppService;
import com.archermind.etb.app.service.AppTypesService;
import com.archermind.etb.app.vo.AppItem;
import com.archermind.etb.app.vo.AppItemPra;
import com.archermind.etb.app.vo.AppTypesVO;
import com.archermind.etb.app.vo.AppVO;
import com.archermind.etb.common.BaseAction;
import com.archermind.etb.util.AndroidManifestParser;
import com.archermind.etb.util.ApkInfo;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.FileUpload;
import com.archermind.etb.util.JsonHelper;
import com.archermind.etb.util.PropertyUtil;
import com.archermind.etb.util.WsClientUtil;




/**
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @see com.archermind.etb.common.BaseAction
 * @since 1.0
 */

@Controller
@Scope("prototype")
@Namespace("/app")
@Action(value = "appAction", results ={
		  @Result(name = "list", location = "appInfo_list.jsp"),
		  @Result(name = "add", location = "appInfo_add.jsp"),
		  @Result(name = "update", location = "appInfo_update.jsp"),
		  @Result(name = "checklist", location = "appInfo_checklist.jsp"),
		  @Result(name = "uncheck", location = "appInfo_uncheck.jsp"),
		  @Result(name = "read", location = "appInfo_read.jsp"),
		  @Result(name = "checkedlist", location = "appInfo_checkedlist.jsp")})

public class AppAction extends BaseAction {

	private static final long serialVersionUID = -8162458812138734645L;

	private static final Logger log = Logger.getLogger(AppAction.class);
	
	/** 平台应用信息 */
	private AppVO vo;

	/** 平台应用信息集合 */
	private List<AppVO> list;


	@Resource(name = "com.archermind.etb.app.service.AppService")
	private AppService appService;
	
	@Resource(name = "com.archermind.etb.app.service.AppTypesService")
	private AppTypesService appTypesService;
	
	/** 应用分类集合*/
	private List<AppTypesVO> appTypesList;

	/**
	 * 列表展现
	 * 
	 * @return
	 */
	public String listAppInfo() {
		revo();
		this.totalCount = appService.getInfoCount(vo);
		this.list = appService.listAppInfoByPage(vo,this.numPerPage, this.getPageNum());
		return "list";
	}
	/**
	 * 未审核列表展现
	 * 
	 * @return
	 */
	public String checkAppInfoList() {
		revo();
		this.totalCount = appService.getCheckInfoCount(vo);
		this.list = appService.checkAppInfoByPage(vo,this.numPerPage, this.getPageNum());
		return "checklist";
	}
	
	/**
	 * 审核列表展现
	 * 
	 * @return
	 */
	public String checkedAppInfoList() {
		revo();
		this.totalCount = appService.getCheckedInfoCount(vo);
		this.list = appService.checkedAppInfoByPage(vo,this.numPerPage, this.getPageNum());
		return "checkedlist";
	}
	
	/**
	 *审核应用
	 * 
	 * @return
	 */
	public String checkAppInfo() {
		try{
			vo.setAppInfoChecher(reUser());
			appService.checkAppInfo(vo);
			this.writeDwzResultToResponse(Constant.CHECKED_SUCCESS,
					Constant.HTTP_STATUS_OK, "toCheck", "listAppCheck", null,
					"closeNavTabId", null);
		}catch (Exception e) {
			log.error("check app failed.", e);
			this.writeDwzResultToResponse(Constant.CHECKED_FAILED,
					Constant.HTTP_STATUS_FAILED, "listAppCheck", null, "closeCurrent", null);
		}

		return null;
	}
	
	/**
	 * 驳回审核页面
	 * 
	 * @return
	 */
	public String toUncheck() {
		vo = appService.getAppInfo(vo);
		return "uncheck";
	}
	
	/**
	 *驳回审核
	 * 
	 * @return
	 */
	public String uncheckAppInfo() {
		try{
			vo.setAppInfoChecher(reUser());
			appService.uncheckAppInfo(vo);
			this.writeDwzResultToResponse(Constant.UNCHECKED_SUCCESS,
					Constant.HTTP_STATUS_OK, "toCheck", null, "closeCurrent", null);
		}catch (Exception e) {
			log.error("uncheck app failed.", e);
			this.writeDwzResultToResponse(Constant.UNCHECKED_FAILED,
					Constant.HTTP_STATUS_FAILED, "toCheck", null, "closeCurrent", null);
		}

		return null;
	}
	
	/**
	 *上线应用
	 * 
	 * @return
	 */
	public String publishAppInfo() {
		try{  
			vo.setAppInfoPublisher(reUser());
			appService.publishAppInfo(vo);
			
			AppVO tempvo = appService.getAppInfo(vo);
			AppItemPra ai = new AppItemPra();
			AppItem ai1 = new AppItem();
			ai1.setPackagename(tempvo.getAppInfoPackage());
			ai1.setVersion(tempvo.getAppInfoVersionCode()!=null?Integer.parseInt(tempvo.getAppInfoVersionCode()):0);
			ai1.setUrl(URLEncoder.encode(tempvo.getAppInfoPath()));
			ai1.setAction(Constant.APP_INSTALL);
			ai1.setType(tempvo.getAppTypesId());
			List<AppItem> inlist = new ArrayList<AppItem>();
			inlist.add(ai1);
			ai.setAppinfos(inlist);
			ai.setType(Constant.APP_TYPE);
			ai.setAction(Constant.APP_ACTION);
			String json = JsonHelper.getGson().toJson(ai);
			
			String wsdl = PropertyUtil.readData("webservice_wsdl");
			WsClientUtil.callCXFWservice(wsdl,Constant.NOTIFY_NEW_MESSAGE,new Object[]{UUID.randomUUID().toString(),json,true});
			
			this.writeDwzResultToResponse(Constant.PUBLISH_SUCCESS,
					Constant.HTTP_STATUS_OK, "listAppUp", null, null, null);
		}catch (Exception e) {
			log.error("publish app failed.", e);
			this.writeDwzResultToResponse(Constant.PUBLISH_FAILED,
					Constant.HTTP_STATUS_FAILED, "listAppUp", null, null, null);
		}

		return null;
	}
	
	/**
	 *下线应用
	 * 
	 * @return
	 */
	public String unpublishAppInfo() {
		try{
			
			appService.unpublishAppInfo(vo);
			
			AppVO tempvo = appService.getAppInfo(vo);
			AppItemPra ai = new AppItemPra();
			AppItem ai1 = new AppItem();
			ai1.setPackagename(tempvo.getAppInfoPackage());
			ai1.setVersion(tempvo.getAppInfoVersionCode()!=null?Integer.parseInt(tempvo.getAppInfoVersionCode()):0);
			ai1.setUrl(URLEncoder.encode(tempvo.getAppInfoPath()));
			ai1.setAction(Constant.APP_UNINSTALL);
			ai1.setType(tempvo.getAppTypesId());
			List<AppItem> inlist = new ArrayList<AppItem>();
			inlist.add(ai1);
			ai.setAppinfos(inlist);
			ai.setType(Constant.APP_TYPE);
			ai.setAction(Constant.APP_ACTION);
			String json = JsonHelper.getGson().toJson(ai);
			
			String wsdl = PropertyUtil.readData("webservice_wsdl");
			WsClientUtil.callCXFWservice(wsdl,Constant.NOTIFY_NEW_MESSAGE,new Object[]{UUID.randomUUID().toString(),json,true});
			
			this.writeDwzResultToResponse(Constant.UNPUBLISH_SUCCESS,
					Constant.HTTP_STATUS_OK, "listAppUp", null, null, null);
		}catch (Exception e) {
			log.error("unpublish app failed.", e);
			this.writeDwzResultToResponse(Constant.UNPUBLISH_FAILED,
					Constant.HTTP_STATUS_FAILED, "listAppUp", null, null, null);
		}

		return null;
	}
	
	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String toAdd() {
		return "add";
	}
	
	
	/**
	 * 根据ID获取app的状态
	 */
	public void getAppStatusById() {
			AppVO vot = appService.getAppInfo(vo);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status",
					vot == null ? "" : vot.getAppInfoStatus());

			String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE)
					.toJson(map);
			this.writeResultToResponse(json);

	}
	
	/**
	 * 修改页面
	 * 
	 * @return
	 */
	public String toUpdate() {
		vo = appService.getAppInfo(vo);
		return "update";
	}
	
	/**
	 * 查看应用审核明细
	 * 
	 * @return
	 */
	public String toRead() {
		vo = appService.getAppInfoAndType(vo);
		return "read";
	}

	/**
	 * 修改信息
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String updateAppInfo() {
		revo();
		
		String message = Constant.UPDATE_SUCCESS;
		if (vo.getAppInfoId() > 0) {
			message = Constant.UPDATE_SUCCESS;
		}
		try{
//			String url = PropertyUtil.readData("ftpUrl");
//			String strport = PropertyUtil.readData("ftpPort");
//			String username = PropertyUtil.readData("ftpUserName");
//			String password = PropertyUtil.readData("ftpPassword");
			String path = PropertyUtil.readData("appUploadPath");
			String filename = UUID.randomUUID().toString()+".apk";
			File file = vo.getAppInfoFile();
//		File file = new File("d:/a.apk");
//			int port = Integer.parseInt(strport);
			
			if(null!=file){
				InputStream input = new FileInputStream(file);
				
				ApkInfo ai = new AndroidManifestParser().getApkInfo(file);
				vo.setAppInfoPackage(ai.getPackageName());
				vo.setAppInfoVersion(ai.getVersionName());
				vo.setAppInfoVersionCode(ai.getVersionCode());
				
				boolean result = appService.findAppByPack(vo);
				if(result){
					FileUpload.upToFileServer(input, path, filename);
//					FtpUtil.uploadFile(url, port, username, password, path, filename, input);
					vo.setAppInfoPath(path + File.separator + filename);
					appService.updateAppInfo(vo);
					this.writeTextResultToResponse(message, Constant.HTTP_STATUS_OK,
							"listApp", null, "closeCurrent", null);
				}else{
					this.writeTextResultToResponse(Constant.APP_INFO_PACK_HAVED, Constant.HTTP_STATUS_FAILED,
							"listApp", null, "closeCurrent", null);
				}
			}else{
				appService.updateAppInfo(vo);
				this.writeTextResultToResponse(message, Constant.HTTP_STATUS_OK,
						"listApp", null, "closeCurrent", null);
			}
			
		}catch (Exception e) {
			log.error("update app failed.", e);
			message = Constant.UPDATE_FAILED;
			this.writeTextResultToResponse(message, Constant.HTTP_STATUS_FAILED,
					"listApp", null, null, null);
		}
		return null;
	}

	/**
	 * 保存信息
	 * 
	 * @return
	 * @throws FileNotFoundException 
	 * @throws Exception
	 */
	public String saveAppInfo() {

		String message = Constant.SAVE_SUCCESS;
		if (vo.getAppInfoId() > 0) {
			message = Constant.SAVE_SUCCESS;
		}

		try{
//			String url = PropertyUtil.readData("ftpUrl");
//			String strport = PropertyUtil.readData("ftpPort");
//			String username = PropertyUtil.readData("ftpUserName");
//			String password = PropertyUtil.readData("ftpPassword");
			String path = PropertyUtil.readData("appUploadPath");
			String filename = UUID.randomUUID().toString()+".apk";
			File file = vo.getAppInfoFile();
//			int port = Integer.parseInt(strport);
			
			ApkInfo ai = new AndroidManifestParser().getApkInfo(file);
			vo.setAppInfoPackage(ai.getPackageName());
			vo.setAppInfoVersion(ai.getVersionName());
			vo.setAppInfoVersionCode(ai.getVersionCode());
			
			boolean result = appService.findAppByPack(vo);
			if(result){
				InputStream input = new FileInputStream(file);
//				FtpUtil.uploadFile(url, port, username, password, path, filename, input);
				FileUpload.upToFileServer(input, path, filename);
				vo.setAppInfoPath(path + File.separator + filename);
				vo.setAppInfoCreater(reUser());
				appService.addAppInfo(vo);
				this.writeTextResultToResponse(message, Constant.HTTP_STATUS_OK,
						"listApp", null, "closeCurrent", null);
			}else{
				this.writeTextResultToResponse(Constant.APP_INFO_PACK_HAVED, Constant.HTTP_STATUS_FAILED,
						"listApp", null, null, null);
			}
			
		}catch (Exception e) {
			log.error("save app failed.", e);
			message = Constant.SAVE_FAILED;
			this.writeTextResultToResponse(message, Constant.HTTP_STATUS_FAILED,
					"listApp", null, null, null);
		}
		return null;
	}

	/**
	 * 删除信息
	 * 
	 * @return
	 */
	public String deleteAppInfo() {
		try{
			
			int flag = appService.deleteAppInfo(vo);
			
			if(flag==1){
				this.writeDwzResultToResponse(Constant.DELETE_SUCCESS,
						Constant.HTTP_STATUS_OK, "listApp", null, null, null);
			}else{
				this.writeDwzResultToResponse(Constant.HAVE_PUBLISHED,
						Constant.HTTP_STATUS_FAILED, "listApp", null, null, null);
			}
		}catch (Exception e) {
			log.error("delete app failed.", e);
			this.writeDwzResultToResponse(Constant.PUBLISH_FAILED,
					Constant.HTTP_STATUS_FAILED, "listApp", null, null, null);
		}

		return null;
	}

	
	public String checkPack(){
		File file = vo.getAppInfoFile();
		ApkInfo ai = null;
		try {
			ai = new AndroidManifestParser().getApkInfo(file);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Error parse AndroidManifest", e);
		}
		vo.setAppInfoPackage(ai.getPackageName());
		vo.setAppInfoVersion(ai.getVersionName());
		vo.setAppInfoVersionCode(ai.getVersionCode());
		
		boolean result = appService.findAppByPack(vo);
		if(result){
			this.writeResultToResponse("true");
		}else{
			this.writeResultToResponse("false");
		}
		return null;
	}
	
	/**
	 * 得到当前操作人
	 */
	private String reUser(){
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession().getAttribute(Constant.SESSION_USER_NAME)+"";
	}
	
	public AppVO getVo() {
		return vo;
	}

	public void setVo(AppVO vo) {
		this.vo = vo;
	}

	public List<AppVO> getList() {
		return list;
	}

	public void setList(List<AppVO> list) {
		this.list = list;
	}

	public AppService getAppService() {
		return appService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	private void revo(){
		if(null==vo){
			vo = new AppVO();
		}
	}

	public List<AppTypesVO> getAppTypesList() {
		if(appTypesList == null){
			appTypesList = appTypesService.findAllAppTypes();
		}
		return appTypesList;
	}

	public void setAppTypesList(List<AppTypesVO> appTypesList) {
		this.appTypesList = appTypesList;
	}

	public AppTypesService getAppTypesService() {
		return appTypesService;
	}

	public void setAppTypesService(AppTypesService appTypesService) {
		this.appTypesService = appTypesService;
	}
	
}
