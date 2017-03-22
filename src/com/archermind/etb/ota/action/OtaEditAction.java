package com.archermind.etb.ota.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.ota.domain.OtaUpdatePack;
import com.archermind.etb.ota.domain.OtaVersionInfo;
import com.archermind.etb.ota.service.OtaUpdatepackService;
import com.archermind.etb.ota.service.OtaVersionService;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.FileUpload;
import com.archermind.etb.util.PropertyUtil;

@Controller
@Scope("prototype")
@Namespace("/ota")
@Action(value = "otaedit", results = {
		@Result(name = "preeditota", location = "editOta.jsp"),
		@Result(name = "preaddpack", location = "edit_addpack.jsp"),
		@Result(name = "edit_packlist", location = "edit_pdatepacklist.jsp"),
		@Result(name = "preaddupdatepack", location = "edit_addupdatepack.jsp")
})
public class OtaEditAction extends BaseAction{

	private static final long serialVersionUID = -1L;
	
	private static final Logger log = Logger.getLogger(OtaEditAction.class);
	
	@Resource(name = "com.archermind.etb.ota.service.OtaversionService")
	private OtaVersionService otaVersionService;
	@Resource(name = "com.archermind.etb.ota.service.OtaUpdatepackService")
	private OtaUpdatepackService otaUpdatePackService;
	
	/**
	 *存在
	 */
	private static final int  ISEXIST = 0;
	/**
	 * 已删除状态值
	 */
	private static final int  ISDELETE = 1;
	/**
	 * 完整包状态标示
	 */
	private static final int PACKAGE_TYPE_COMPLETE = 0;
	/**
	 * 差分包标示
	 */
	private static final int PACKAGE_TYPE_UPDATE = 1 ;

	/**
	 * OTA信息
	 */
	private OtaVersionInfo otaInfo;
	/**
	 * 删除，修改，添加升级包的唯一标识
	 */
	private String otaId;
	/**
	 * 版本下所有升级包
	 */
	private List<OtaUpdatePack> packList;
	/**
	 * 完整包的数量
	 */
	private int isOne;
	/**
	 * 差分包的数量
	 */
	private int isFour;
	/**
	 * 是否是最低版本
	 * 0标示最低，1否
	 */
	private int isMinVersion;
	/**
	 * 升级包的实体参数
	 */
	private OtaUpdatePack updatePack;
	/**
	 * 向下4个差分包的名称
	 */
	private List<String> listName;
	/**
	 * 升级包的唯一标示id
	 */
	private int packId;
	/**
	 * 欲修改页面，审核通过,已提交和已发布不允许修改
	 * @return
	 */
	public String preEditOta()
	{
		
		otaInfo = otaVersionService.getOtaInfoById(otaInfo.getId());
		if(otaInfo==null)
		{
			return "500";
		}else
		{
			if(otaInfo.getInfostat()==Constant.OTA_VER_INFO_STATUS_CHECKED
					||otaInfo.getInfostat()==Constant.OTA_VER_INFO_STATUS_PUBLISHED
					||otaInfo.getInfostat()==Constant.OTA_VER_INFO_STATUS_SUBMIT)
			{
				this.writeDwzResultToResponse(Constant.HAVE_AUDIT_RELEASE, Constant.HTTP_STATUS_FAILED,
							"otamanger", null, null, null);
				return null;
			}
//			isOne=otaUpdatePackService.getPackCount(otaInfo.getName(), PACKAGE_TYPE_COMPLETE, ISEXIST);
//			log.debug("Complete pack number is"+isOne);
//			isFour=otaUpdatePackService.getPackCount(otaInfo.getName(), PACKAGE_TYPE_UPDATE, ISEXIST);
//			log.debug("Update pack number is"+isFour);
//			List list = this.otaVersionService.getUpdateVersionName(otaInfo.getBuildversion());
//			if(list.size()==0)
//			{
//				isMinVersion = 0;
//			}else
//			{
//				isMinVersion = 1;
//			}
//			packList = this.otaUpdatePackService.getVersionUpdatePack(otaInfo.getName(), ISEXIST);
			otaId = String.valueOf(otaInfo.getId());
			return "preeditota";
		}
	}
	/**
	 * 
	 * @return
	 */
	public String getEditPackList()
	{
		otaInfo = otaVersionService.getOtaInfoById(otaInfo.getId());
		isOne=otaUpdatePackService.getPackCount(otaInfo.getName(), PACKAGE_TYPE_COMPLETE, ISEXIST);
		log.debug("Complete pack number is"+isOne);
		isFour=otaUpdatePackService.getPackCount(otaInfo.getName(), PACKAGE_TYPE_UPDATE, ISEXIST);
		log.debug("Update pack number is"+isFour);
		List list = this.otaVersionService.getUpdateVersionName(otaInfo.getBuildversion());
		if(list.size()==0)
		{
			isMinVersion = 0;
		}else
		{
			isMinVersion = 1;
		}
		packList = this.otaUpdatePackService.getVersionUpdatePack(otaInfo.getName(), ISEXIST);
		otaId = String.valueOf(otaInfo.getId());
		return "edit_packlist";
	}
	
	/**
	 * 欲添加完整包页面
	 * @return
	 */
	public String preAddPack() {
		OtaVersionInfo ota = otaVersionService.getOtaInfoById(Integer
				.parseInt(otaId));
		// 已经存在完整包的版本不允许再添加完整包，之力做后台提示
		isOne = otaUpdatePackService.getPackCount(ota.getName(),
				PACKAGE_TYPE_COMPLETE, ISEXIST);
		if (isOne > 0) {
			this.writeDwzResultToResponse(Constant.HAVE_COMPLETE_PACK,
					Constant.HTTP_STATUS_FAILED, "otaupdatepack", null, null,
					null);
			return null;
		}
		updatePack = null == updatePack ? new OtaUpdatePack() : updatePack;
		updatePack.setTargetname(ota.getName());
		updatePack.setOtaid(Integer.parseInt(otaId));
		return "preaddpack";
	}
	/**
	 * 新增ota版本的完整包
	 * @return
	 * @throws Exception 
	 */
	public String  addCompletepack()   
	{
		updatePack.setPacktype(PACKAGE_TYPE_COMPLETE);
		String path = PropertyUtil.readData("zipUploadPath");
		String filename = "";
		File file = updatePack.getPackfile();
		InputStream input;
		try {
			String oldFileName = updatePack.getPackfileFileName();   
			if(oldFileName.lastIndexOf(".zip")<0)
			{
				this.writeTextResultToResponse(Constant.UPLOAD_FAILED_ERR_SUFFIX, Constant.HTTP_STATUS_FAILED,
						"otaupdatepack", null, null, null);
				return null;
			}
			filename = UUID.randomUUID().toString()+".zip";
			input = new FileInputStream(file);
			FileUpload.upToFileServer(input,path, filename);
		} catch (FileNotFoundException e) {
			log.error("Complete pack upload fail",e);
			return null;
		}
		updatePack.setPackaddr(path + File.separator + filename);
		updatePack.setDatastat(ISEXIST);
		//OtaVersionInfo ota = otaVersionService.getOtaInfoById(updatePack.getOtaid());
		otaUpdatePackService.addUpdatepack(updatePack);
		log.debug("Complete pack upload successfully");
		this.writeTextResultToResponse(Constant.SAVE_SUCCESS, Constant.HTTP_STATUS_OK,
				"otaupdatepack", null, "closeCurrent", null);
		return null;
		
	}
	
	/**
	 * 欲添加差分包页面
	 * @return
	 */
	public String preAddUpdatePack()
	{
		OtaVersionInfo ota = otaVersionService.getOtaInfoById(Integer.parseInt(otaId));
		//已经向下差分了4个版本不允许再添加差分包，只做后台提示
		isFour=otaUpdatePackService.getPackCount(ota.getName(), PACKAGE_TYPE_UPDATE, ISEXIST);
		if(isFour==4)
		{
			this.writeDwzResultToResponse(Constant.HAVE_FOUR_PACK, Constant.HTTP_STATUS_FAILED,
					"otaupdatepack", null, null, null);
			return null;
		}
		//只有一个版本的时候不允许添加差分包
		listName  = this.otaVersionService.getUpdateVersionName(ota.getBuildversion());
		if(listName.size()==0)
		{
			this.writeDwzResultToResponse(Constant.MIN_PACK, Constant.HTTP_STATUS_FAILED,
					"otaupdatepack", null, null, null);
			return null;
		}
		updatePack = null==updatePack?new OtaUpdatePack():updatePack;
		updatePack.setTargetname(ota.getName());
		updatePack.setOtaid(Integer.parseInt(otaId));
		return "preaddupdatepack";
	}
	
	/**
	 * 新增ota版本的差分包
	 * @return
	 * @throws Exception 
	 */
	public String  addUpdatepack()  
	{
		updatePack.setPacktype(PACKAGE_TYPE_UPDATE);
		String path =PropertyUtil.readData("zipUploadPath");
		String filename = "";
		File file = updatePack.getPackfile();
		InputStream input;
		try {
			String oldFileName = updatePack.getPackfileFileName();   
			if(oldFileName.lastIndexOf(".zip")<0)
			{
				this.writeTextResultToResponse(Constant.UPLOAD_FAILED_ERR_SUFFIX, Constant.HTTP_STATUS_FAILED,
						"otaupdatepack", null, null, null);
				return null;
			}
			filename = UUID.randomUUID().toString()+".zip";
			input = new FileInputStream(file);
			FileUpload.upToFileServer(input,path,filename);
		} catch (FileNotFoundException e) {
			log.error("Update pack upload fail",e);
			return null;
		} 
		updatePack.setPackaddr(path + File.separator + filename);
		updatePack.setDatastat(ISEXIST);
		otaUpdatePackService.addUpdatepack(updatePack);
		log.debug("Update pack upload successfully");
		this.writeTextResultToResponse(Constant.SAVE_SUCCESS, Constant.HTTP_STATUS_OK,
				"otaupdatepack", null, "closeCurrent", null);
		return null;
	}
	
	/**
	 * 删除升级包，这里做逻辑删除，即改变datastat状态为1
	 * @return
	 */
	public String deletePack()
	{
		OtaVersionInfo ota = otaVersionService.getOtaInfoById(Integer.parseInt(otaId));
		updatePack = otaUpdatePackService.getPackById(packId);
		updatePack.setDatastat(ISDELETE);
		otaUpdatePackService.deletePack(updatePack);
		//这里还没有做FTP上对应文件删除
		this.writeDwzResultToResponse(Constant.DELETE_SUCCESS, Constant.HTTP_STATUS_OK,
				"add_ota_info", null, null, null);
		return null;
	}
	
	/**
	 * 提交ota
	 * @return
	 */
	public String submitOta()
	{
		String tip = otaInfo.getTips();
		int stat = otaInfo.getInfostat();
		otaInfo=otaVersionService.getOtaInfoById(otaInfo.getId());
		//保存草稿不判断是否有完整包
		if(stat==Constant.OTA_VER_INFO_STATUS_CREATE)
		{
			otaInfo.setInfostat(stat);
			otaInfo.setTips(tip);
			otaVersionService.updateOta(otaInfo);
			this.writeDwzResultToResponse(Constant.SAVE_SUCCESS, Constant.HTTP_STATUS_OK,
					"otamanger", null, "closeCurrent", null);
		}else
		{
			int packnum=otaUpdatePackService.getPackCount(otaInfo.getName(), PACKAGE_TYPE_COMPLETE, ISEXIST);
			if(packnum==0)
			{
				//没有完整包，提交不成功
				this.writeDwzResultToResponse(Constant.CHECKED_FAILED_NOPACK, Constant.HTTP_STATUS_FAILED,
						null, null, null, null);
			}else
			{
			otaInfo.setInfostat(stat);
			otaInfo.setTips(tip);
			otaVersionService.updateOta(otaInfo);
			this.writeDwzResultToResponse(Constant.SAVE_SUCCESS, Constant.HTTP_STATUS_OK,
					"otamanger", null, "closeCurrent", null);
			}
		}
		return null;
	}
	
	public OtaVersionInfo getOtaInfo()
	{
		return otaInfo;
	}

	public void setOtaInfo(OtaVersionInfo otaInfo)
	{
		this.otaInfo = otaInfo;
	}
	
	public OtaVersionService getOtaVersionService()
	{
		return otaVersionService;
	}

	public void setOtaVersionService(OtaVersionService otaVersionService) 
	{
		this.otaVersionService = otaVersionService;
	}
	public OtaUpdatepackService getOtaUpdatePackService()
	{
		return otaUpdatePackService;
	}

	public void setOtaUpdatePackService(OtaUpdatepackService otaUpdatePackService)
	{
		this.otaUpdatePackService = otaUpdatePackService;
	}
	

	public List<OtaUpdatePack> getPackList()
	{
		return packList;
	}

	public void setPackList(List<OtaUpdatePack> packList)
	{
		this.packList = packList;
	}
	public String getOtaId() 
	{
		return otaId;
	}

	public void setOtaId(String otaId)
	{
		this.otaId = otaId;
	}
	public int getIsOne()
	{
		return isOne;
	}

	public void setIsOne(int isOne)
	{
		this.isOne = isOne;
	}
	

	public int getIsFour() 
	{
		return isFour;
	}

	public void setIsFour(int isFour)
	{
		this.isFour = isFour;
	}

	public int getIsMinVersion() 
	{
		return isMinVersion;
	}

	public void setIsMinVersion(int isMinVersion)
	{
		this.isMinVersion = isMinVersion;
	}
	public OtaUpdatePack getUpdatePack() 
	{
		return updatePack;
	}

	public void setUpdatePack(OtaUpdatePack updatePack) 
	{
		this.updatePack = updatePack;
	}

	public List<String> getListName()
	{
		return listName;
	}
	public void setListName(List<String> listName)
	{
		this.listName = listName;
	}

	public int getPackId()
	{
		return packId;
	}
	public void setPackId(int packId) 
	{
		this.packId = packId;
	}
}
