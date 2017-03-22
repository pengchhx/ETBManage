package com.archermind.etb.app.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.app.service.AppTypesService;
import com.archermind.etb.app.vo.AppTypesItem;
import com.archermind.etb.app.vo.AppTypesVO;
import com.archermind.etb.common.BaseAction;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.FileUpload;
import com.archermind.etb.util.ImageCompressUtil;
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
@Action(value = "appTypesAction", results ={
		  @Result(name = "list", location = "appTypesInfo_list.jsp"),
		  @Result(name = "add", location = "appTypesInfo_add.jsp"),
		  @Result(name = "update", location = "appTypesInfo_update.jsp")})

public class AppTypesAction extends BaseAction {

	private static final long serialVersionUID = -8162458812138734645L;

	private static final Logger log = Logger.getLogger(AppTypesAction.class);
	/** 平台应用分类信息 */
	private AppTypesVO vo;

	/** 平台应用分类信息集合 */
	private List<AppTypesVO> list;
//
//	@Resource
//	private IAppTypesService appTypesService;

	@Resource(name = "com.archermind.etb.app.service.AppTypesService")
	private AppTypesService appTypesService;
	
	/**
	 * 列表展现
	 * 
	 * @return
	 */
	public String listAppTypesInfo() {
		revo();
		this.totalCount = appTypesService.getTypesInfoCount(vo);
		this.list = appTypesService.listAppTypesInfoByPage(vo,this.numPerPage, this.getPageNum());
		return "list";
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
	 * 修改页面
	 * 
	 * @return
	 */
	public String toUpdate() {
		vo = appTypesService.getAppTypesInfo(vo);
		return "update";
	}

	/**
	 * 修改信息
	 * 
	 * @return
	 */
	public String updateAppTypesInfo() {
		revo();
		
		String message = Constant.UPDATE_SUCCESS;
		if (vo.getAppTypesId() > 0) {
			message = Constant.UPDATE_SUCCESS;
		}
		
		String path = PropertyUtil.readData("appUploadPath");
		File file = vo.getAppTypesFile();
		try{
			if(file!=null){
				String savePath = ServletActionContext.getServletContext().getRealPath(
						File.separator)+ Constant.AD_FILE_UPLOAD_FOLDER+ File.separator;
				
				String filename = UUID.randomUUID().toString()+"_"+vo.getAppTypesFileName();
				String realPath = savePath+path + File.separator + filename;
				
				File tfile = null;
				tfile = new File(realPath);
				FileUtils.copyFile(file, tfile);
				
				boolean updFlag = true;
				updFlag = ImageCompressUtil.compressImgNoWhite(tfile, realPath,
						Constant.APPTYPES_IMG_WIDTH, Constant.APPTYPES_IMG_HEIGHT,
						Constant.COMPRESSION_RATIO);
				
//				InputStream input = new FileInputStream(file);
//				FileUpload.upToFileServer(input, path, filename);
				
				if(updFlag){
					vo.setAppTypesUrl(path + File.separator + filename);
					appTypesService.updateAppTypesInfo(vo);
					
					AppTypesVO tempvo = appTypesService.getAppTypesInfo(vo);
					pushAppTypesInfo(tempvo, Constant.APPTYPES_ACTION_UPDATE);
					
					this.writeTextResultToResponse(message, Constant.HTTP_STATUS_OK,
							"listAppTypes", null, "closeCurrent", null);
				}else{
					if(file!=null){
						file.delete();
					}
					this.writeTextResultToResponse(Constant.UPDATE_FAILED, Constant.HTTP_STATUS_FAILED,
							"listAppTypes", null, null, null);
				}
				
				
			}else{
				appTypesService.updateAppTypesInfo(vo);
				
				AppTypesVO tempvo = appTypesService.getAppTypesInfo(vo);
				pushAppTypesInfo(tempvo, Constant.APPTYPES_ACTION_UPDATE);
				
				this.writeTextResultToResponse(message, Constant.HTTP_STATUS_OK,
						"listAppTypes", null, "closeCurrent", null);
			}
			
		}catch (Exception e) {
			log.error("update appTypes failed.", e);
			this.writeTextResultToResponse(Constant.UPDATE_FAILED, Constant.HTTP_STATUS_FAILED,
					"listAppTypes", null, null, null);
		}
		return null;
	}

	/**
	 * 保存信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveAppTypesInfo() {

		String message = Constant.SAVE_SUCCESS;
		if (vo.getAppTypesId() > 0) {
			message = Constant.SAVE_SUCCESS;
		}

		String path = PropertyUtil.readData("appUploadPath");
		File file = vo.getAppTypesFile();
		String filename = UUID.randomUUID().toString()+"_"+vo.getAppTypesFileName();
		
		try{
			String savePath = ServletActionContext.getServletContext().getRealPath(
					File.separator)+ Constant.AD_FILE_UPLOAD_FOLDER+ File.separator;
			
			String realPath = savePath+path + File.separator + filename;
			
			File tfile = null;
			tfile = new File(realPath);
			FileUtils.copyFile(file, tfile);
			
			boolean updFlag = true;
			updFlag = ImageCompressUtil.compressImgNoWhite(tfile, realPath,
					Constant.APPTYPES_IMG_WIDTH, Constant.APPTYPES_IMG_HEIGHT,
					Constant.COMPRESSION_RATIO);
			
//			InputStream input = new FileInputStream(vo.getAppTypesFile());
//			FileUpload.upToFileServer(input, path, filename);
			
			if(updFlag){
				vo.setAppTypesUrl(path + File.separator + filename);
				
				appTypesService.addAppTypesInfo(vo);
				
				AppTypesVO tempvo = appTypesService.getAppTypesInfo(vo);
				pushAppTypesInfo(tempvo, Constant.APPTYPES_ACTION_ADD);
				
				this.writeTextResultToResponse(message, Constant.HTTP_STATUS_OK,
						"listAppTypes", null, "closeCurrent", null);
			}else{
				if(file!=null){
					file.delete();
				}
				this.writeTextResultToResponse(Constant.SAVE_FAILED, Constant.HTTP_STATUS_FAILED,
						"listAppTypes", null, null, null);
			}
		}catch (Exception e) {
			log.error("save appTypes failed.", e);
			this.writeTextResultToResponse(Constant.SAVE_FAILED, Constant.HTTP_STATUS_FAILED,
					"listAppTypes", null, null, null);
		}
		return null;
	}

	/**
	 * 删除信息
	 * 
	 * @return
	 */
	public String deleteAppTypesInfo() {
		try{
//			AppTypesVO tempvo = appTypesService.getAppTypesInfo(vo);
			AppTypesVO tempvo = new AppTypesVO();
			tempvo.setAppTypesId(vo.getAppTypesId());
			int flag = appTypesService.deleteAppTypesInfo(vo);
			if(flag==1){
				pushAppTypesInfo(tempvo, Constant.APPTYPES_ACTION_DEL);
				this.writeDwzResultToResponse(Constant.DELETE_SUCCESS,
						Constant.HTTP_STATUS_OK, "listAppTypes", null, null, null);
			}else{
				this.writeDwzResultToResponse(Constant.HAVE_APPS,
						Constant.HTTP_STATUS_FAILED, "listAppTypes", null, null, null);
			}
		}catch (Exception e) {
			log.error("delete appTypes failed.", e);
			this.writeDwzResultToResponse(Constant.DELETE_FAILED,
					Constant.HTTP_STATUS_FAILED, "listAppTypes", null, null, null);
		}

		return null;
	}

	/**
	 * 判断是否应用分类是否重名
	 * @return
	 */
	public String findAppTypesByName(){
		boolean result = appTypesService.findAppTypesByName(vo.getAppTypesName(),vo.getAppTypesId());
		if(result){
			this.writeResultToResponse("true");
		}else{
			this.writeResultToResponse("false");
		}
		return null;
	}
	
	/**
	 * 判断是否应用分类英文是否重名
	 * @return
	 */
	public String findAppTypesByEnName(){
		boolean result = appTypesService.findAppTypesByEnName(vo.getAppTypesEnName(),vo.getAppTypesId());
		if(result){
			this.writeResultToResponse("true");
		}else{
			this.writeResultToResponse("false");
		}
		return null;
	}
	
	public AppTypesVO getVo() {
		return vo;
	}

	public void setVo(AppTypesVO vo) {
		this.vo = vo;
	}

	public List<AppTypesVO> getList() {
		return list;
	}

	public void setList(List<AppTypesVO> list) {
		this.list = list;
	}

	public AppTypesService getAppTypesService() {
		return appTypesService;
	}

	public void setAppTypesService(AppTypesService appTypesService) {
		this.appTypesService = appTypesService;
	}

	/**
	 * 防止VO为空的容错方法
	 * @return
	 */
	private void revo(){
		if(null==vo){
			vo = new AppTypesVO();
		}
	}
	
	private void pushAppTypesInfo(AppTypesVO tempvo,int action){
//		AppTypesPra ai = new AppTypesPra();
		AppTypesItem ai1 = new AppTypesItem();
		ai1.setAppTypesId(tempvo.getAppTypesId());
		ai1.setCnName(tempvo.getAppTypesName());
		ai1.setEnName(tempvo.getAppTypesEnName());
		ai1.setAction(action);
		ai1.setIcon(tempvo.getAppTypesUrl()!=null?URLEncoder.encode(tempvo.getAppTypesUrl()):null);
		List<AppTypesItem> inlist = new ArrayList<AppTypesItem>();
		inlist.add(ai1);
		
		Map remap = new HashMap();
		remap.put("type", Constant.APPTYPS_TYPE);
		remap.put("apptypes", inlist);
		String json = JsonHelper.getGson().toJson(remap);
		
		String wsdl = PropertyUtil.readData("webservice_wsdl");
		WsClientUtil.callCXFWservice(wsdl,Constant.NOTIFY_NEW_MESSAGE,new Object[]{UUID.randomUUID().toString(),json,true});
	}
	
}
