package com.archermind.etb.device.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.device.domain.EtbServerDevice;
import com.archermind.etb.device.service.EtbServerDeviceService;
import com.archermind.etb.util.Constant;

/**
 * 平台设备管理控制层
 * 
 * @author 001667 梁伟
 * @version 1.0 2013-07-11
 * @see com.archermind.etb.common.BaseAction
 * @since 1.0
 * 
 * 
 */
@Controller
@Scope("prototype")
@Namespace("/device")
@Action(value = "etbServerDeviceAction", results = {
		@Result(name = "listDeviceInfo", location = "server_device_list.jsp"),
		@Result(name = "addDeviceInfo", location = "server_device_add.jsp"),
		@Result(name = "updateDeviceInfo", location = "server_device_update.jsp") })
public class EtbServerDeviceAction extends BaseAction {
	/**
	 * 声明serialVersionUID并初始化
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 声明平台设备实体
	 */
	private EtbServerDevice etbServerDevice;

	/**
	 * 声明平台设备结果集
	 */
	private List<EtbServerDevice> listResult;

	/**
	 * 声明etbServerDeviceService
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbServerDeviceService")
	private EtbServerDeviceService etbServerDeviceService;

	/**
	 * 平台设备管理列表控制层方法入口
	 * 
	 * @return String 调用列表界面
	 * @see com.archermind.etb.device.service.EtbServerDeviceService
	 */
	public String listDeviceInfo() {
		String nameString = "";
		if (etbServerDevice == null) {
			etbServerDevice = new EtbServerDevice();
		} else {
			if (etbServerDevice.getName() != null) {
				nameString = etbServerDevice.getName().trim();
			}

		}
		this.totalCount = etbServerDeviceService.getDeviceInfoCount(nameString);
		this.listResult = etbServerDeviceService.listUserInfoByPage(nameString,
				this.numPerPage, this.getPageNum());
		return "listDeviceInfo";
	}

	/**
	 * 添加平台设备控制层方法入口
	 * 
	 * @return String 调用增加界面
	 */
	public String addDeviceInfo() {
		return "addDeviceInfo";
	}

	/**
	 * 更新平台设备控制层方法入口
	 * 
	 * @return String 调用修改界面
	 * @see com.archermind.etb.device.service.EtbServerDeviceService
	 */
	public String updateDeviceInfo() {
		etbServerDevice = etbServerDeviceService
				.updateDeviceInfo(etbServerDevice.getId());
		return "updateDeviceInfo";
	}

	/**
	 * 更新和保存平台设备
	 * 
	 * @return null
	 */
	public String saveDeviceInfo() {
		String message = Constant.SAVE_SUCCESS;
		etbServerDevice.setStat(Constant.DATA_STAT_ON);
		etbServerDeviceService.saveDeviceInfo(etbServerDevice);
		this.writeDwzResultToResponse(message, Constant.HTTP_STATUS_OK,
				"serverid", null, "closeCurrent", null);
		return null;
	}

	/**
	 * 逻辑删除平台设备
	 * 
	 * @return null
	 */
	public String deleteDeviceInfo() {
		etbServerDevice = etbServerDeviceService
				.updateDeviceInfo(etbServerDevice.getId());
		etbServerDevice.setStat(Constant.DATA_STAT_OFF);
		etbServerDeviceService.saveDeviceInfo(etbServerDevice);
		this.writeDwzResultToResponse(Constant.DELETE_SUCCESS,
				Constant.HTTP_STATUS_OK, "serverid", null, null, null);

		return null;
	}

	/**
	 * 
	 * @return 平台设备实体对象
	 * @see com.archermind.etb.device.domain.EtbServerDevice
	 */
	public EtbServerDevice getEtbServerDevice() {
		return etbServerDevice;
	}

	/**
	 * 
	 * @param etbServerDevice
	 * @see com.archermind.etb.device.domain.EtbServerDevice
	 */
	public void setEtbServerDevice(EtbServerDevice etbServerDevice) {
		this.etbServerDevice = etbServerDevice;
	}

	/**
	 * 
	 * @return 实体结果集
	 */
	public List<EtbServerDevice> getListResult() {
		return listResult;
	}

	/**
	 * 
	 * @param listResult
	 */
	public void setListResult(List<EtbServerDevice> listResult) {
		this.listResult = listResult;
	}
}
