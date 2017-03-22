package com.archermind.etb.user.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.device.domain.EtbClientDevice;
import com.archermind.etb.device.service.EtbClientDeviceService;
import com.archermind.etb.user.domain.ClientUserInfo;
import com.archermind.etb.user.service.ClientUserInfoService;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.JsonHelper;
import com.archermind.etb.util.Md5Util;

/**
 * 终端用户管理Action
 * 
 * @author 003445 张瑞
 * @version v1.0,2013.07.11
 * @see com.archermind.etb.common.BaseAction
 * @since v1.0
 */
@Controller
@Scope("prototype")
@Namespace("/user")
@Action(value = "clientUserInfo", results = {
		@Result(name = "listClientUserInfo", location = "clientuserinfo_list.jsp"),
		@Result(name = "addClientUserInfo", location = "clientuserinfo_editor.jsp"),
		@Result(name = "updateClientUserInfo", location = "clientuserinfo_editor.jsp"),
		@Result(name = "showEtbClientDevice", location = "show_clientdevice.jsp") })
public class ClientUserInfoAction extends BaseAction {

	private static final long serialVersionUID = 8426961382281338131L;

	/** 日志 */
	private static final Logger log = Logger
			.getLogger(ClientUserInfoAction.class);

	/** 终端用户信息 */
	private ClientUserInfo clientUserInfo;

	/** 终端用户信息集合 */
	private List<ClientUserInfo> clientUserInfoList;

	/** 设备选择页面页码个数 */
	private final static int PAGE_NUM_SHOWN = 5;
	/**
	 * 声明ClientUserInfoService
	 */
	@Resource(name = "com.archermind.etb.user.service.ClientUserInfoService")
	private ClientUserInfoService clientUserInfoService;

	/**
	 * 声明EtbClientDeviceService
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbClientDeviceService")
	private EtbClientDeviceService etbClientDeviceService;

	/** 终端设备信息集合 */
	private List<EtbClientDevice> etbClientDeviceList;

	/** 终端设备信息 */
	private EtbClientDevice etbClientDevice;

	/**
	 * 列表展现终端用户信息
	 * 
	 * @return
	 */
	public String listClientUserInfo() {

		clientUserInfo = clientUserInfo == null ? new ClientUserInfo()
				: clientUserInfo;
		this.totalCount = clientUserInfoService.getClientUserInfoCount(
				clientUserInfo.getClientUserInfoName(),
				clientUserInfo.getClientUserInfoAccount(),
				clientUserInfo.getClientUserInfoIdentity());
		this.clientUserInfoList = clientUserInfoService
				.listClientUserInfoByPage(
						clientUserInfo.getClientUserInfoName(),
						clientUserInfo.getClientUserInfoAccount(),
						clientUserInfo.getClientUserInfoIdentity(),
						this.numPerPage, this.getPageNum());
		return "listClientUserInfo";
	}

	/**
	 * 终端用户信息新增页面
	 * 
	 * @return
	 */
	public String addClientUserInfo() {
		return "addClientUserInfo";
	}

	/**
	 * 修改终端用户信息
	 * 
	 * @return
	 */
	public String updateClientUserInfo() {

		clientUserInfo = clientUserInfoService
				.getClientUserInfoById(clientUserInfo.getClientUserInfoId());
		clientUserInfo.setReClientUserInfoPwd(clientUserInfo
				.getClientUserInfoPwd());
		clientUserInfo.setIsEditPassWord(clientUserInfo.getClientUserInfoPwd());
		// 设置设备编号值，使之在编辑页面显示
		Set<EtbClientDevice> deviceList = clientUserInfo
				.getEtbClientDeviceList();
		Iterator<EtbClientDevice> deviceIterator = deviceList.iterator();
		EtbClientDevice device = null;
		if (deviceIterator.hasNext()) {
			device = deviceIterator.next();
			clientUserInfo.setClientUserInfoDeviceImei(device.getImei());
		}
		return "updateClientUserInfo";
	}

	/**
	 * 保存终端用户信息
	 * 
	 * @return
	 */
	public String saveClientUserInfo() {

		try {

			String message = Constant.SAVE_SUCCESS;
			if (clientUserInfo.getClientUserInfoId() > 0) {
				message = Constant.UPDATE_SUCCESS;
			}

			// 根据设备编号临时值，查找该设备，并添加至用户关联的设备集合中
			String imei = clientUserInfo.getClientUserInfoDeviceImei();
			EtbClientDevice clientDevice = etbClientDeviceService
					.findDeviceInfoById(imei);
			Set<EtbClientDevice> etbClientDeviceSet = new HashSet<EtbClientDevice>();
			etbClientDeviceSet.add(clientDevice);
			clientUserInfo.setEtbClientDeviceList(etbClientDeviceSet);

			if (clientUserInfo.getClientUserInfoId() > 0
					&& clientUserInfo.getIsEditPassWord().equals(
							clientUserInfo.getClientUserInfoPwd())) {
				clientUserInfoService.saveClientUserInfo(clientUserInfo);
			} else {
				// 使用Md5值，对用户密码进行加密
				clientUserInfo.setClientUserInfoPwd(Md5Util.Md5(clientUserInfo
						.getClientUserInfoPwd()));
//				clientUserInfoService.saveClientUserInfo(clientUserInfo);
				clientUserInfoService.addClientUserInfo(clientUserInfo);
			}

			this.writeDwzResultToResponse(message, Constant.HTTP_STATUS_OK,
					"listClientUserInfo", null, "closeCurrent", null);

		} catch (Exception e) {
			log.error(" save ClientUserInfo failure！", e);
			this.writeDwzResultToResponse(Constant.SAVE_FAILED,
					Constant.HTTP_STATUS_FAILED, "listClientUserInfo", null,
					"closeCurrent", null);
			return null;
		}
		return null;
	}

	/**
	 * 判断用户与设备是否关联
	 * 
	 * @return
	 */
	public String vertify() {
		clientUserInfo = clientUserInfoService
				.getClientUserInfoById(clientUserInfo.getClientUserInfoId());
		// 若该用户关联了设备，则须进一步提示
		if (clientUserInfo.getEtbClientDeviceList() != null) {
			if (clientUserInfo.getEtbClientDeviceList().size() > 0) {
				// 返回json解析的字符串,传给ajax;
				this.writeTextToResponse("true");
			}
		}
		return null;
	}

	/**
	 * 删除用户信息
	 * 
	 * @return
	 */
	public String delClientUserInfo() {

		try {
			clientUserInfoService.delClientUserInfoById(clientUserInfo
					.getClientUserInfoId());

			this.writeDwzResultToResponse(Constant.DELETE_SUCCESS,
					Constant.HTTP_STATUS_OK, "listClientUserInfo", null, null,
					null);

		} catch (Exception e) {
			log.error("delete ClientUserInfo failure", e);
			this.writeDwzResultToResponse(Constant.DELETE_FAILED,
					Constant.HTTP_STATUS_FAILED, "listClientUserInfo", null,
					null, null);
		}
		return null;
	}

	/**
	 * 显示终端设备信息
	 * 
	 * @return
	 */
	public String showEtbClientDevice() {

		// 将targetType属性设置为弹窗模式
		this.pageNumShown = PAGE_NUM_SHOWN;
		this.targetType = Constant.TARGET_TYPE_DIALOG;
		etbClientDevice = etbClientDevice == null ? new EtbClientDevice()
				: etbClientDevice;
		this.totalCount = etbClientDeviceService
				.getDeviceInfoCount(etbClientDevice.getImei());
		this.etbClientDeviceList = etbClientDeviceService.listUserInfoByPage(
				etbClientDevice.getImei(), this.numPerPage, this.getPageNum());
		return "showEtbClientDevice";
	}

	/**
	 * 判断该设备是否存在
	 */
	public String checkDevice() {

		// 根据设备编号临时值，查找该设备
		String deviceImei = clientUserInfo.getClientUserInfoDeviceImei();
		EtbClientDevice clientDevice = etbClientDeviceService
				.findDeviceInfoById(deviceImei);
		if (clientDevice != null) {
			this.writeResultToResponse("true");
		} else {
			String errorMsg = Constant.NO_DEVICE;
			this.writeResultToResponse(errorMsg);
		}

		return null;
	}

	/**
	 * 根据该终端用户账号、用户编号，查询该用户实例
	 * 
	 * @return
	 */
	public String findClientUserInfoByAccount() {
		// 转码处理
		String clientUserAccount = "";
		clientUserAccount = clientUserInfo.getClientUserInfoAccount();

		boolean result = clientUserInfoService.findClientUserInfoByAccount(
				clientUserAccount, clientUserInfo.getClientUserInfoId());
		if (result) {
			this.writeResultToResponse("true");
		} else {
			this.writeResultToResponse("false");
		}
		return null;
	}

	/**
	 * 根据imei，查询终端设备信息
	 * 
	 * @return
	 * @author 003445
	 */
	public String findDevice() {
		etbClientDevice = etbClientDevice == null ? new EtbClientDevice()
				: etbClientDevice;
		etbClientDeviceList = etbClientDeviceService.listUserInfoByPage(
				etbClientDevice.getImei(), Constant.DEVICE_SHOW_COLUMN,
				Constant.INIT_PAGENUM);
		// domain中，注解为expose的字段，可通过json解析
		String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(
				etbClientDeviceList);
		this.writeResultToResponse(json);
		return null;
	}

	public ClientUserInfo getClientUserInfo() {
		return clientUserInfo;
	}

	public void setClientUserInfo(ClientUserInfo clientUserInfo) {
		this.clientUserInfo = clientUserInfo;
	}

	public List<ClientUserInfo> getClientUserInfoList() {
		return clientUserInfoList;
	}

	public void setClientUserInfoList(List<ClientUserInfo> clientUserInfoList) {
		this.clientUserInfoList = clientUserInfoList;
	}

	public List<EtbClientDevice> getEtbClientDeviceList() {
		return etbClientDeviceList;
	}

	public void setEtbClientDeviceList(List<EtbClientDevice> etbClientDeviceList) {
		this.etbClientDeviceList = etbClientDeviceList;
	}

	public EtbClientDevice getEtbClientDevice() {
		return etbClientDevice;
	}

	public void setEtbClientDevice(EtbClientDevice etbClientDevice) {
		this.etbClientDevice = etbClientDevice;
	}

}
