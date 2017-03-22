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

/**
 * 
 * ota升级包的Action
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130711
 * @see 
 * @since 1.0
 */
@Controller
@Scope("prototype")
@Namespace("/ota")
@Action(value = "updatepack", results = {
		@Result(name = "preaddupdatepack", location = "addupdatepack.jsp"),
		@Result(name = "preaddpack", location = "addpack.jsp"),
		@Result(name = "packlist", location = "otaupdatepacklist.jsp")
})
public class OtaUpdatepackAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private static final Logger log = Logger.getLogger(OtaUpdatepackAction.class);

	@Resource(name = "com.archermind.etb.ota.service.OtaUpdatepackService")
	private OtaUpdatepackService otaUpdatePackService;
	@Resource(name = "com.archermind.etb.ota.service.OtaversionService")
	private OtaVersionService otaVersionService;
	
	/**
	 * 完整包状态标示
	 */
	private static final int PACKAGE_TYPE_COMPLETE = 0;
	/**
	 * 差分包标示
	 */
	private static final int PACKAGE_TYPE_UPDATE = 1 ;
	
	/**
	 * 数据存在状态值
	 */
	private static final int ISEXIST = 0;
	/**
	 * 已删除状态值
	 */
	private static final int  ISDELETE = 1;
	/**
	 * 升级包的实体参数
	 */
	private OtaUpdatePack updatePack;
	/**
	 * 版本名称
	 */
	private String name;
	/**
	 * 版本id
	 */
	private String otaId;
	/**
	 * 返回OtaUpdatePack类型的集合
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
	 * 升级包的唯一标示id
	 */
	private int packId;
	/**
	 * 判断是否审核通过或者是已经发布，0是，1否
	 */
	private int auditReleased;
	/**
	 * 向下4个差分包的名称
	 */
	private List<String> listName;
	
	public List<String> getListName() 
	{
		return listName;
	}

	public void setListName(List<String> listName) 
	{
		this.listName = listName;
	}
	public int getIsMinVersion() 
	{
		return isMinVersion;
	}

	public void setIsMinVersion(int isMinVersion) 
	{
		this.isMinVersion = isMinVersion;
	}

	/**
	 * 查询出版本下所有升级包的信息
	 * @return
	 */
	public String getVersionUpdatePack()
	{
		OtaVersionInfo ota = otaVersionService.getOtaInfoById(Integer.parseInt(otaId));
		String otaname= ota.getName();
		if(ota.getInfostat()==Constant.OTA_VER_INFO_STATUS_CHECKED||ota.getInfostat()==Constant.OTA_VER_INFO_STATUS_PUBLISHED)
		{
			auditReleased=1;
		}else{
			auditReleased=0;
		}
		isOne=otaUpdatePackService.getPackCount(otaname, PACKAGE_TYPE_COMPLETE, ISEXIST);
		log.debug("Complete pack number is"+isOne);
		isFour=otaUpdatePackService.getPackCount(otaname, PACKAGE_TYPE_UPDATE, ISEXIST);
		log.debug("Update pack number is"+isFour);
		listName = this.otaVersionService.getUpdateVersionName(ota.getBuildversion());
		if(listName.size()==0)
		{
			isMinVersion = 0;
		}else
		{
			isMinVersion = 1;
		}
		name=otaname;
		packList = otaUpdatePackService.getVersionUpdatePack(otaname,ISEXIST);
		return "packlist";
	}
	
	/**
	 * 欲添加完整包页面
	 * @return
	 */
	public String preAddPack()
	{
		OtaVersionInfo ota = otaVersionService.getOtaInfoById(Integer.parseInt(otaId));
		//审核通过或已发布的版本不允许再添加完整包，这里做后台提示
		if(ota.getInfostat()==Constant.OTA_VER_INFO_STATUS_CHECKED||ota.getInfostat()==Constant.OTA_VER_INFO_STATUS_PUBLISHED)
		{
			this.writeDwzResultToResponse(Constant.HAVE_AUDIT_RELEASE, Constant.HTTP_STATUS_FAILED,
					"otaupdatepack", null, null, null);
			return null;
		}
		//已经存在完整包的版本不允许再添加完整包，之力做后台提示
		isOne=otaUpdatePackService.getPackCount(ota.getName(), PACKAGE_TYPE_COMPLETE, ISEXIST);
		if(isOne>0)
		{
			this.writeDwzResultToResponse(Constant.HAVE_COMPLETE_PACK, Constant.HTTP_STATUS_FAILED,
					"otaupdatepack", null, null, null);
			return null;
		}
		updatePack = null==updatePack?new OtaUpdatePack():updatePack;
		updatePack.setTargetname(ota.getName());
		updatePack.setOtaid(Integer.parseInt(otaId));
		return "preaddpack";
	}
	/**
	 * 欲添加差分包页面
	 * @return
	 */
	public String preAddUpdatePack()
	{
		OtaVersionInfo ota = otaVersionService.getOtaInfoById(Integer.parseInt(otaId));
		//审核通过或已发布的版本不允许再添加差分包，这里做后台提示
		if(ota.getInfostat()==Constant.OTA_VER_INFO_STATUS_CHECKED||ota.getInfostat()==Constant.OTA_VER_INFO_STATUS_PUBLISHED)
		{
			this.writeDwzResultToResponse(Constant.HAVE_AUDIT_RELEASE, Constant.HTTP_STATUS_FAILED,
					"otaupdatepack", null, null, null);
			return null;
		}
		//已经向下差分了4个版本不允许再添加差分包，只做后台提示
		isFour=otaUpdatePackService.getPackCount(ota.getName(), PACKAGE_TYPE_UPDATE, ISEXIST);
		if(isFour==4)
		{
			this.writeDwzResultToResponse(Constant.HAVE_FOUR_PACK, Constant.HTTP_STATUS_FAILED,
					"otaupdatepack", null, null, null);
			return null;
		}
		//只有一个版本的时候不允许添加差分包
		listName = this.otaVersionService.getUpdateVersionName(ota.getBuildversion());
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
	 * 新增ota版本的完整包
	 * @return
	 * @throws Exception 
	 */
	public String  addCompletepack()   
	{
		updatePack.setPacktype(PACKAGE_TYPE_COMPLETE);
//		String url = PropertyUtil.readData("ftpUrl");
//		String strport = PropertyUtil.readData("ftpPort");
//		String username = PropertyUtil.readData("ftpUserName");
//		String password = PropertyUtil.readData("ftpPassword");
		String path = PropertyUtil.readData("zipUploadPath");
		String filename = "";
		File file = updatePack.getPackfile();
		//int port = Integer.parseInt(strport);
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
			this.writeTextResultToResponse(Constant.UPLOAD_FAILED_ERR_SUFFIX, Constant.HTTP_STATUS_FAILED,
					"otaupdatepack", null, null, null);
			return null;
		}catch (Exception e)
		{
			log.error("Complete pack upload fail",e);
			this.writeTextResultToResponse(Constant.UPLOAD_FAILED_ERR_SUFFIX, Constant.HTTP_STATUS_FAILED,
					"otaupdatepack", null, null, null);
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
	 * 新增ota版本的差分包
	 * @return
	 * @throws Exception 
	 */
	public String  addUpdatepack()  
	{
		updatePack.setPacktype(PACKAGE_TYPE_UPDATE);
//		String url = PropertyUtil.readData("ftpUrl");
//		String strport = PropertyUtil.readData("ftpPort");
//		String username = PropertyUtil.readData("ftpUserName");
//		String password = PropertyUtil.readData("ftpPassword");
		String path =PropertyUtil.readData("zipUploadPath");
		String filename = "";
		File file = updatePack.getPackfile();
		//int port = Integer.parseInt(strport);
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
		} catch (Exception e) {
			log.error("FTP upload fail",e);
			return null;
		}
		updatePack.setPackaddr(path + File.separator + filename);
		updatePack.setDatastat(ISEXIST);
		//OtaVersionInfo ota = otaVersionService.getOtaInfoById(updatePack.getOtaid());
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
		if(ota.getInfostat()==Constant.OTA_VER_INFO_STATUS_CHECKED||ota.getInfostat()==Constant.OTA_VER_INFO_STATUS_PUBLISHED)
		{
			this.writeDwzResultToResponse(Constant.HAVE_AUDIT_RELEASE, Constant.HTTP_STATUS_FAILED,
					"otaupdatepack", null, null, null);
			return null;
		}
		updatePack = otaUpdatePackService.getPackById(packId);
		updatePack.setDatastat(ISDELETE);
		otaUpdatePackService.deletePack(updatePack);
		//这里还没有做FTP上对应文件删除
		this.writeDwzResultToResponse(Constant.DELETE_SUCCESS, Constant.HTTP_STATUS_OK,
				"add_ota_info", null, null, null);
		return null;
	}
	
	public OtaUpdatepackService getOtaUpdatePackService() {
		return otaUpdatePackService;
	}

	public void setOtaUpdatePackService(OtaUpdatepackService otaUpdatePackService) {
		this.otaUpdatePackService = otaUpdatePackService;
	}

	public OtaVersionService getOtaVersionService() {
		return otaVersionService;
	}

	public void setOtaVersionService(OtaVersionService otaVersionService) {
		this.otaVersionService = otaVersionService;
	}

	/**
	 * 查询升级包名称是否已经被占用
	 * @return
	 */
	public String packnameIsExist()
	{
		boolean result = otaUpdatePackService.packnameIsExist(updatePack.getPackname(), ISEXIST);
		if(result){
			this.writeResultToResponse("true");
		}else{
			this.writeResultToResponse("false");
		}
		return null;
	}
	
	
	public String getOtaId()
	{
		return otaId;
	}

	public void setOtaId(String otaId)
	{
		this.otaId = otaId;
	}
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	public int getPackId() 
	{
		return packId;
	}

	public void setPackId(int packId)
	{
		this.packId = packId;
	}
	public List<OtaUpdatePack> getPackList() 
	{
		return packList;
	}

	public void setPackList(List<OtaUpdatePack> packList) 
	{
		this.packList = packList;
	}

	public int getAuditReleased() 
	{
		return auditReleased;
	}

	public void setAuditReleased(int auditReleased) 
	{
		this.auditReleased = auditReleased;
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
	public OtaUpdatePack getUpdatePack()
	{
		return updatePack;
	}

	public void setUpdatePack(OtaUpdatePack updatePack)
	{
		this.updatePack = updatePack;
	}
}
