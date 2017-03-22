package com.archermind.etb.device.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.device.domain.EtbClientDeviceConfig;
import com.archermind.etb.device.service.EtbClientDeviceConfigService;
import com.archermind.etb.util.Constant;

/**
 * 终端设备系统配置模块Action层
 * 
 * @author 001667
 * @version 2.0
 * @since 1.0
 */
@Controller
@Scope("prototype")
@Namespace("/device")
@Action(value = "etbClientDeviceConfigAction", results = {
		@Result(name = "clientDeviceConfig", location = "clientDeviceConfig.jsp"),
		@Result(name = "addDeviceConfig", location = "updateDeviceConfig.jsp") })
public class EtbClientDeviceConfigAction extends BaseAction {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 实体
	 */
	private EtbClientDeviceConfig etbClientDeviceConfig;

	/**
	 * Service层
	 */
	@Resource(name = "com.archermind.etb.device.service.EtbClientDeviceConfigService")
	private EtbClientDeviceConfigService etbClientDeviceConfigService;

	/**
	 * 结果集
	 */
	private List<EtbClientDeviceConfig> result;

	/**
	 * 显示配置列表
	 * 
	 * @return
	 */
	public String showClientDeviceConfig() {
		result = etbClientDeviceConfigService.showClientDeviceConfig();
		return "clientDeviceConfig";
	}

	public String addDeviceConfig() {
		return "addDeviceConfig";
	}

	/**
	 * 保存配置信息
	 * 
	 * @return
	 */
	public String saveDeviceConfig() {
		etbClientDeviceConfigService.saveDeviceConfig(etbClientDeviceConfig);
		this.writeDwzResultToResponse(Constant.SAVE_SUCCESS,
				Constant.HTTP_STATUS_OK, "deviceconfig", null, "closeCurrent",
				null);
		return null;
	}

	/**
	 * 删除配置信息
	 * 
	 * @return
	 */
	public String deleteDeviceConfig() {
		etbClientDeviceConfig = etbClientDeviceConfigService
				.findConfigById(etbClientDeviceConfig.getId());
		etbClientDeviceConfig.setStat(Constant.DATA_STAT_OFF);
		etbClientDeviceConfigService.saveDeviceConfig(etbClientDeviceConfig);
		this.writeDwzResultToResponse(Constant.SAVE_SUCCESS,
				Constant.HTTP_STATUS_OK, "deviceconfig", null, null, null);
		return null;
	}

	/**
	 * 修改配置
	 * 
	 * @return
	 */
	public String updateDeviceConfig() {
		etbClientDeviceConfig = etbClientDeviceConfigService
				.findConfigById(etbClientDeviceConfig.getId());
		return "addDeviceConfig";
	}
  
	/**
	 * 推送配置信息
	 * @return
	 */
	public String pushSysInfoToClient(){
		etbClientDeviceConfigService.pushMsgToClient();
		this.writeDwzResultToResponse(Constant.PUSH_SUCCESS,
				Constant.HTTP_STATUS_OK, "deviceconfig", null, null, null);
		return null;
	}
	/**
	 * 
	 * @return
	 */
	public EtbClientDeviceConfig getEtbClientDeviceConfig() {
		return etbClientDeviceConfig;
	}

	/**
	 * 
	 * @param etbClientDeviceConfig
	 */
	public void setEtbClientDeviceConfig(
			EtbClientDeviceConfig etbClientDeviceConfig) {
		this.etbClientDeviceConfig = etbClientDeviceConfig;
	}

	/**
	 * 
	 * @return
	 */
	public List<EtbClientDeviceConfig> getResult() {
		return result;
	}

	/**
	 * 
	 * @param result
	 */
	public void setResult(List<EtbClientDeviceConfig> result) {
		this.result = result;
	}
}
