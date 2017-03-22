package com.archermind.etb.user.action;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.cxf.binding.corba.wsdl.Array;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.device.domain.EtbAreaCode;
import com.archermind.etb.device.domain.EtbClientDevice;
import com.archermind.etb.device.domain.EtbClientDeviceTemp;
import com.archermind.etb.device.service.EtbAreaCodeService;
import com.archermind.etb.device.service.EtbClientDeviceService;
import com.archermind.etb.user.domain.CompanyInfo;
import com.archermind.etb.user.domain.CompanyTemp;
import com.archermind.etb.user.domain.PowerInfo;
import com.archermind.etb.user.domain.UserInfo;
import com.archermind.etb.user.domain.UserTemp;
import com.archermind.etb.user.service.CompanyInfoService;
import com.archermind.etb.user.service.PowerInfoService;
import com.archermind.etb.user.service.UserInfoService;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.JsonHelper;
import com.archermind.etb.util.Md5Util;

/**
 * 平台用户管理Action
 * 
 * @author 003445 张瑞
 * @version v1.0,2013.07.11
 * @see com.archermind.etb.common.BaseAction
 * @since v1.0
 */

@Controller
@Scope("prototype")
@Namespace("/user")
@Action(value = "userInfo", results = {
		@Result(name = "listUserInfo", location = "userinfo_list.jsp"),
		@Result(name = "addUserInfo", location = "userinfo_editor.jsp"),
		@Result(name = "updateUserInfo", location = "userinfo_editor.jsp"),
		
//-----------------------------------------------------------------------------------

	
		@Result(name = "authorManage", location = "authorManagement.jsp"),
		@Result(name = "viewInfo", location = "viewInfo.jsp"),
		@Result(name = "gotoDeleteUser", location = "deleteUser.jsp"),	
		@Result(name = "gotoAddUser", location = "addUser.jsp"),
		@Result(name = "addUser",type="redirectAction", location ="userInfo!gotoAuthorManage.do"),
		@Result(name = "deleteUser",type="redirectAction", location ="userInfo!gotoAuthorManage.do"),
		@Result(name = "updateUser",type="redirectAction", location ="userInfo!gotoAuthorManage.do"),
		@Result(name = "gotoUpdateUser",location ="editUser.jsp"),
		@Result(name = "gotoSuperAdd",location ="superAdd.jsp"),
		@Result(name = "gotoSuperProfile",location ="superProfile.jsp"),
		@Result(name = "gotoSuperEdit",location ="superEdit.jsp"),
		@Result(name = "gotoLeft",location ="left_edit.jsp"),
		@Result(name = "gotoRight",location ="right_edit.jsp"),
		@Result(name = "gotoCompanyList",location ="superLogin.jsp")
})
public class UserInfoAction extends BaseAction {

	private static final long serialVersionUID = -8162458812138734645L;

	/** 日志 */
	private static final Logger log = Logger.getLogger(UserInfoAction.class);

	/** 平台用户信息 */
	private UserInfo userInfo;

	/** 平台用户信息集合 */
	private List<UserInfo> userInfoList;

	/** 平台权限信息集合 */
	private List<PowerInfo> powerInfoList;
	
	/** 权限id */
	private String powerInfoId;
	
	
	/** 临时权限集合 */
	private List<String> tempList =new ArrayList<>();
	
	/** 当前页数 */
	private int page;
	
	private CompanyInfo companyInfo;


	private int id;
	
	private String imei;
	
	private List<EtbAreaCode> provinces;
	
	private String areaCode;
	
	/**
	 * 声明UserInfoService
	 */
	@Resource(name = "com.archermind.etb.user.service.UserInfoService")
	private UserInfoService userInfoService;

	/**
	 * 声明PowerInfoService
	 */
	@Resource(name = "com.archermind.etb.user.service.PowerInfoService")
	private PowerInfoService powerInfoService;
	
	/**
	 * 声明companyInfoService
	 */
	@Resource(name = "com.archermind.etb.user.service.CompanyInfoService")
	private CompanyInfoService companyInfoService;
	
	/**
	 * 声明EtbClientDeviceService
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbClientDeviceService")
	private EtbClientDeviceService etbClientDeviceService;
	
	/**
	 * 声明EtbAreaCodeService
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbAreaCodeService")
	private EtbAreaCodeService etbAreaCodeService;
	

	/**
	 * 列表展现平台用户信息
	 * 
	 * @return
//	 */
//	public String listUserInfo() {
//
//		userInfo = userInfo == null ? new UserInfo() : userInfo;
//		this.totalCount = userInfoService.getUserInfoCount(
//				userInfo.getUserInfoUsername(), userInfo.getUserInfoName());
//		this.userInfoList = userInfoService.listUserInfoByPage(
//				userInfo.getUserInfoUsername(), userInfo.getUserInfoName(),
//				this.numPerPage, this.getPageNum());
//		return "listUserInfo";
//	}

	/**
	 * 平台用户信息新增页面
	 * 
	 * @return
	 */
//	public String addUserInfo() {
//		roleInfoList = roleInfoService.getRoleInfoList();
//		return "addUserInfo";
//	}
//
//	/**
//	 * 修改平台用户信息
//	 * 
//	 * @return
//	 */
//	public String updateUserInfo() {
//
//		roleInfoList = roleInfoService.getRoleInfoList();
//		userInfo = userInfoService.getUserInfoById(userInfo.getUserInfoId());
//		userInfo.setReUserInfoPwd(userInfo.getUserInfoPwd());
//		userInfo.setIsEditPassWord(userInfo.getUserInfoPwd());
//		return "updateUserInfo";
//	}

	/**
	 * 保存平台用户信息
	 * 
	 * @return
	 */
//	public String saveUserInfo() {
//
//		String message = Constant.SAVE_SUCCESS;
//		if (userInfo.getUserInfoId() > 0) {
//			message = Constant.UPDATE_SUCCESS;
//		}
//
//		try {
//			if (userInfo.getUserInfoId() == 0
//					|| !userInfo.getIsEditPassWord().equals(
//							userInfo.getUserInfoPwd())) {
//				// 使用Md5值，加密密码
//				userInfo.setUserInfoPwd(Md5Util.Md5(userInfo.getReUserInfoPwd()));
//			}
//			
//			// 若在页面设定了角色
//			if (userInfo.getRoleInfo().getRoleInfoId() != 0) {
//
//				RoleInfo roleInfo = roleInfoService.getRoleInfoById(userInfo
//						.getRoleInfo().getRoleInfoId());
//				Set<UserInfo> userInfoSet = new HashSet<UserInfo>();
//				// 将该用户添加至角色关联的平台用户集合中
//				userInfoSet.add(userInfo);
//				roleInfo.setUserInfoList(userInfoSet);
//				// 将选中的角色，设置为与平台用户关联的角色
//				userInfo.setRoleInfo(roleInfo);
//
//			} else {
//				// 没有在页面设定角色
//				userInfo.setRoleInfo(null);
//			}
//			userInfoService.saveUserInfo(userInfo);
//
//			this.writeDwzResultToResponse(message, Constant.HTTP_STATUS_OK,
//					"listUserInfo", null, "closeCurrent", null);
//		} catch (Exception e) {
//			log.error("Md5 password Failure ",e);
//			this.writeDwzResultToResponse(Constant.SAVE_FAILED,
//					Constant.HTTP_STATUS_FAILED, "listUserInfo", null,
//					"closeCurrent", null);
//			return null;
//		}
//
//		
//		return null;
//	}

	/**
	 * 删除用户信息
	 * 
	 * @return
	 */
	public String delUserInfo() {

		userInfoService.delUserInfoById(userInfo.getUserInfoId());

		this.writeDwzResultToResponse(Constant.DELETE_SUCCESS,
				Constant.HTTP_STATUS_OK, "listUserInfo", null, null, null);

		return null;
	}

	/**
	 * 根据该用户名、用户编号，查询该用户实例
	 * 
	 * @return
	 */
	public String findUserInfoByName() {
		// 转码处理
		String userName = userInfo.getUserInfoUsername();

		boolean result = userInfoService.findUserInfoByName(userName,
				userInfo.getUserInfoId());
		if (result) {
			this.writeResultToResponse("true");
		} else {
			this.writeResultToResponse("false");
		}
		return null;
	}
	
//	用户模块-------------------------------------------------------------------------------------------------------------------------------------------

	
	/**
	 * 进入发布审核界面
	 * 
	 * @return
	 */

	public String gotoAudit(){
		
		//查询登录者所属公司的所有广告包
		
		
		return  "";
	}
	
	/**
	 * 进入权限管理界面
	 * 
	 * @return
	 */

	public String gotoAuthorManage(){
		
		//查询权限集合
		this.powerInfoList=powerInfoService.getPowerInfoList();
		
		return  "authorManage";
	}
	
	/**
	 * 查询用户信息
	 * 
	 * @return
	 */

	public String getAuthorManage(){
		
		Subject subject = SecurityUtils.getSubject();
		//取登录者信息
		UserInfo adminUser= (UserInfo) subject.getSession().getAttribute(Constant.SESSION_USER);
		
		
		//查询权限集合

		
		userInfo = userInfo == null ? new UserInfo() : userInfo;

		
		this.totalCount = userInfoService.getUserInfoCount(
				userInfo.getUserInfoUsername(), userInfo.getUserInfoName(),powerInfoId,adminUser.getCompanyInfo().getCompanyInfoId());
		
		
		//模糊查询 用户名 姓名 权限
		this.userInfoList = userInfoService.listUserInfoByPage(
				userInfo.getUserInfoUsername(), userInfo.getUserInfoName(),powerInfoId,adminUser.getCompanyInfo().getCompanyInfoId(),
				this.numPerPage, this.getPageNum(this.page));
		

		//由于对象的关联关系较多，导致json报错，所以如下处理
		List userListTemp=new ArrayList();
		for(UserInfo user:this.userInfoList){
			UserTemp userTemp=new UserTemp();
			userTemp.setUserInfoId(user.getUserInfoId());
			userTemp.setUserInfoUsername(user.getUserInfoUsername());
			userTemp.setUserInfoName(user.getUserInfoName());
			userTemp.setUserInfoTel(user.getUserInfoTel());
			userTemp.setUserInfoTips(user.getUserInfoTips());
			for(PowerInfo power: user.getPowerInfoList()){
				if(power.getPowerInfoId()==3){
					userTemp.setHasEditPower("编辑权限");
				}
				if(power.getPowerInfoId()==4){
					userTemp.setHasCheckPower("审核权限");
				}
				if(power.getPowerInfoId()==5){
					userTemp.setHasDevicePower("设备管理权限");
				}			
			}
			userListTemp.add(userTemp);
		}
		
		//返回用户信息以及总页数
		this.writeDwzResultToResponse(userListTemp,(int) Math.ceil((double)this.totalCount/this.numPerPage));


		return  null;
	}
	
	/**
	 * 进入新增用户页面
	 * 
	 * @return
	 */

	public String gotoAddUser(){
			
		return  "gotoAddUser";
	}
	
	
	/**
	 * 新增用户
	 * 
	 * @return
	 */

	public String addUser(){
		try {
		Subject subject = SecurityUtils.getSubject();
			//取登录者信息
			UserInfo user= (UserInfo) subject.getSession().getAttribute(Constant.SESSION_USER);
			this.userInfo.setCompanyInfo(user.getCompanyInfo());
			
			//将权限临时集合添加到用户权限集合中
			Set<PowerInfo> powerSet=new HashSet<PowerInfo>();
			for(int i=0;i<this.tempList.size();i++){
				PowerInfo powerInfo=new PowerInfo();
				powerInfo.setPowerInfoId(Long.parseLong(tempList.get(i)));
				powerSet.add(powerInfo);
			}
			this.userInfo.setPowerInfoList(powerSet);
			//密码md5加密
			this.userInfo.setUserInfoPwd(Md5Util.Md5(this.userInfo.getUserInfoPwd()));
			
			userInfoService.saveUserInfo(this.userInfo);
			System.out.println("----"+this.getUserInfo().getUserInfoId());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		return  "addUser";
	}
	
	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String gotoUpdateUser(){
		userInfo = userInfo == null ? new UserInfo() : userInfo;
		//根据id查询用户对象
		this.userInfo=userInfoService.getUserInfoById(userInfo.getUserInfoId());
		return  "gotoUpdateUser";
	}
	
	
	/**
	 * 修改用户信息
	 * 
	 * @return
	 */

	public String updateUser(){
		userInfo = userInfo == null ? new UserInfo() : userInfo;
		try {
			
			//将权限临时集合添加到用户权限集合中
			Set<PowerInfo> powerSet=new HashSet<PowerInfo>();
			for(int i=0;i<this.tempList.size();i++){
				PowerInfo powerInfo=new PowerInfo();
				powerInfo.setPowerInfoId(Long.parseLong(tempList.get(i)));
				powerSet.add(powerInfo);
			}
			this.userInfo.setPowerInfoList(powerSet);
			//密码md5加密
			this.userInfo.setUserInfoPwd(Md5Util.Md5(this.userInfo.getUserInfoPwd()));	
			userInfoService.saveUserInfo(this.userInfo);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  "updateUser";
	}
	
	
	/**
	 * 进入详情页面
	 * 
	 * @return
	 */
	public String gotoUserView(){
		userInfo = userInfo == null ? new UserInfo() : userInfo;
		//根据id查询用户对象
		this.userInfo=userInfoService.getUserInfoById(userInfo.getUserInfoId());
		return  "viewInfo";
	}
	
	
	
	/**
	 * 进入删除用户页面
	 * 
	 * @return
	 */

	public String gotoDeleteUser(){
		
		return  "gotoDeleteUser";
	}
	
	
	
	
	/**
	 * 删除用户
	 * 
	 * @return
	 */

	public String deleteUser(){
		userInfo = userInfo == null ? new UserInfo() : userInfo;
		userInfoService.delUserInfoById(userInfo.getUserInfoId());//将用户status设为1	
		return "deleteUser";
	}
	
	
	//超级管理员模块----------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * 超级管理员查询所有公司
	 * 
	 * @return
	 */
	
	public String getCompanyList(){
		
		this.totalCount=companyInfoService.getCountByCompanyList(this.companyInfo.getCompanyInfoName());

		List<CompanyInfo> companyInfoList=companyInfoService.getCompanyList(
				this.companyInfo.getCompanyInfoName(), this.numPerPage, this.getPageNum(this.page));

		List<CompanyTemp> companyTempList=new ArrayList<CompanyTemp>();
		for(CompanyInfo company:companyInfoList){
			CompanyTemp companyTemp=new CompanyTemp();
			//查询公司所在的地区
			EtbAreaCode area=etbAreaCodeService.getAreaById(company.getCompanyInfoAreaId());		
			companyTemp.setCity(area.getAreaName());
			EtbAreaCode	parentArea=etbAreaCodeService.getAreaByParentCode(area.getAreaParentCode());
			companyTemp.setProvince(parentArea.getAreaName());
			
			
			companyTemp.setCompanyInfoId(company.getCompanyInfoId());
			companyTemp.setCompanyInfoName(company.getCompanyInfoName());
			companyTemp.setCompanyInfoAddress(company.getCompanyInfoAddress());
			companyTempList.add(companyTemp);
		}

		
		this.writeDwzResultToResponse(companyTempList,(int) Math.ceil((double)this.totalCount/this.numPerPage));
		
		
		return null;	
	}
	
	
	
	
	/**
	 * 进入超级管理员添加公司页面
	 * 
	 * @return
	 */

	public String gotoSuperAdd(){
		//获取省级列表
		provinces = etbAreaCodeService.getAllProvince();
		return "gotoSuperAdd";
	}
	
	
	
	/**
	 * 省 市 级联查询
	 * 
	 * @return
	 */

	public void getCityByProvince(){

		List<EtbAreaCode> cityList = etbAreaCodeService.getCityByAreaCode(areaCode);

		Map map=new HashMap<>();
		map.put("cityList", cityList);
		
		this.writeResultToResponse(JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(map));
	}
	

	/**
	 * 超级管理员添加公司
	 * 
	 * @return
	 */

	public String addCompany(){
		try {
			//添加公司
			this.companyInfoService.addCompany(this.companyInfo);
			
			this.userInfo=new UserInfo();
			//同时添加公司管理员
			this.userInfo.setCompanyInfo(this.companyInfo);
			
			//为公司管理员添加权限
			this.powerInfoList=new ArrayList<PowerInfo>();
			PowerInfo power=new PowerInfo();
			power.setPowerInfoId(2);
			this.powerInfoList.add(power);
			this.userInfo.setPowerInfoList(new HashSet<PowerInfo>(this.powerInfoList));
			
			this.userInfo.setUserInfoTel(this.companyInfo.getCompanyInfoTel());
			this.userInfo.setUserInfoUsername(this.companyInfo.getCompanyInfoName());
			this.userInfo.setUserInfoName(this.companyInfo.getCompanyInfoAdminName());
			this.userInfo.setUserInfoPwd(Md5Util.Md5(this.companyInfo.getCompanyInfoPassword()));	
			this.userInfo.setUserInfoTips("");
			this.userInfoService.saveUserInfo(this.userInfo);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "gotoSuperProfile";
	}
	
	
	
	/**
	 * 进入详情页面
	 * 
	 * @return
	 */

	public String gotoSuperProfile(){

		this.companyInfo=this.companyInfoService.getCompanyInfoById(this.companyInfo.getCompanyInfoId());
		
		EtbAreaCode area=etbAreaCodeService.getAreaById(this.companyInfo.getCompanyInfoAreaId());		
		this.companyInfo.setCity(area.getAreaName());
		EtbAreaCode	parentArea=etbAreaCodeService.getAreaByParentCode(area.getAreaParentCode());
		this.companyInfo.setProvince(parentArea.getAreaName());
		
		return "gotoSuperProfile";
	}
	
	
	/**
	 * 进入修改页面
	 * 
	 * @return
	 */

	public String gotoSuperEdit(){
		
		provinces = etbAreaCodeService.getAllProvince();
		
		this.companyInfo=this.companyInfoService.getCompanyInfoById(this.companyInfo.getCompanyInfoId());
		
		//查询公司所在地区
		EtbAreaCode area=etbAreaCodeService.getAreaById(this.companyInfo.getCompanyInfoAreaId());		
		this.companyInfo.setCity(area.getAreaName());
		EtbAreaCode	parentArea=etbAreaCodeService.getAreaByParentCode(area.getAreaParentCode());
		this.companyInfo.setProvince(parentArea.getAreaName());
		
		return "gotoSuperEdit";
	}
	
	
	/**
	 * 查询设备列表集合
	 * 
	 * @return
	 */

	public String getDeviceListByCompany(){

		
	
		List<EtbClientDevice> clientDeviceList=etbClientDeviceService.findClientDeviceByCompanyId(imei, this.companyInfo.getCompanyInfoId());
	

		List<EtbClientDeviceTemp> clientDeviceListTemp=new ArrayList<EtbClientDeviceTemp>();
		
		for(EtbClientDevice device:clientDeviceList){
			EtbClientDeviceTemp deviceTemp=new EtbClientDeviceTemp();
			deviceTemp.setImei(device.getImei());
			clientDeviceListTemp.add(deviceTemp);
			}
	
	

		
		this.writeDwzResultToResponse(clientDeviceListTemp);
		
		
		return null;
	}
	
	
	/**
	 * 删除某个设备ajax
	 * 
	 * 
	 * @return
	 */
	public String deleteEtbClientDevice(){

		etbClientDeviceService.deleteEtbClientDevice(this.imei);
		this.writeTextToResponse("success");
		
		
		return null;
	}
	
	
	
	/**
	 *批量删除设备 ajax
	 * 
	 * 
	 * @return
	 */
	public String deleteEtbClientDeviceBatch(){

		etbClientDeviceService.deleteEtbClientDeviceBatch(this.imei);
		this.writeTextToResponse("success");
		
		
		return null;
	}

	
	/**
	 *超级管理员新增设备 ajax
	 * 
	 * 
	 * @return
	 */
	public String addEtbClientDevice(){
	
		EtbClientDevice etbClientDevice=new EtbClientDevice();
		etbClientDevice.setImei(this.imei);
		etbClientDevice.setCompany(this.companyInfo);
		etbClientDevice.setCt(new Date());
		this.etbClientDeviceService.saveDeviceInfo(etbClientDevice);
		this.writeTextToResponse("success");
		
		return null;
	}
	
	
	/**
	 *进入左边的修改页面
	 * 
	 * 
	 * @return
	 */
	
	public String gotoLeft(){
		
		provinces = etbAreaCodeService.getAllProvince();
		
		this.companyInfo=this.companyInfoService.getCompanyInfoById(this.companyInfo.getCompanyInfoId());	
		EtbAreaCode area=etbAreaCodeService.getAreaById(this.companyInfo.getCompanyInfoAreaId());		
		this.companyInfo.setCity(area.getAreaName());
		EtbAreaCode	parentArea=etbAreaCodeService.getAreaByParentCode(area.getAreaParentCode());
		this.companyInfo.setProvince(parentArea.getAreaName());
		return "gotoLeft";
	}
	
	
	/**
	 *进入右边的修改页面
	 * 
	 * 
	 * @return
	 */
	public String gotoRight(){
		
		this.companyInfo=this.companyInfoService.getCompanyInfoById(this.companyInfo.getCompanyInfoId());
		EtbAreaCode area=etbAreaCodeService.getAreaById(this.companyInfo.getCompanyInfoAreaId());		
		this.companyInfo.setCity(area.getAreaName());
		EtbAreaCode	parentArea=etbAreaCodeService.getAreaByParentCode(area.getAreaParentCode());
		this.companyInfo.setProvince(parentArea.getAreaName());
		return "gotoRight";
	}
	
	
	/**
	 *修改公司
	 * 
	 * 
	 * @return
	 */
	public String editCompany(){
		
		this.companyInfoService.addCompany(this.companyInfo);
		
		EtbAreaCode area=etbAreaCodeService.getAreaById(this.companyInfo.getCompanyInfoAreaId());		
		this.companyInfo.setCity(area.getAreaName());
		EtbAreaCode	parentArea=etbAreaCodeService.getAreaByParentCode(area.getAreaParentCode());
		this.companyInfo.setProvince(parentArea.getAreaName());
		
		//id用来判断是保存按钮还是确定按钮 1为确定按钮
		if(id==1){
			return "gotoCompanyList";
		}else{
			return "gotoSuperProfile";
		}

	}
	
	
	
	/**
	 *进入公司列表
	 * 
	 * 
	 * @return
	 */
	public String gotoCompanyList(){
		
		return "gotoCompanyList";
	}
	


	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public List<PowerInfo> getPowerInfoList() {
		return powerInfoList;
	}

	public void setPowerInfoList(List<PowerInfo> powerInfoList) {
		this.powerInfoList = powerInfoList;
	}

	public String getPowerInfoId() {
		return powerInfoId;
	}

	public void setPowerInfoId(String powerInfoId) {
		this.powerInfoId = powerInfoId;
	}

	public List<String> getTempList() {
		return tempList;
	}

	public void setTempList(List<String> tempList) {
		this.tempList = tempList;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public List<EtbAreaCode> getProvinces() {
		return provinces;
	}

	public void setProvinces(List<EtbAreaCode> provinces) {
		this.provinces = provinces;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	
	
	
	
	

}
