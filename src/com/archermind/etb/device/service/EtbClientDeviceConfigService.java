package com.archermind.etb.device.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;





import com.archermind.etb.device.dao.EtbClientDeviceConfigDao;
import com.archermind.etb.device.domain.ClientSysInfo;
import com.archermind.etb.device.domain.EtbClientDeviceConfig;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.JsonHelper;
import com.archermind.etb.util.PropertyUtil;
import com.archermind.etb.util.WsClientUtil;

/**
 * 终端设备系统配置模块Service层
 * 
 * @author 001667
 * @version 2.0
 * @since 1.0
 */
@Service("com.archermind.etb.device.service.EtbClientDeviceConfigService")
public class EtbClientDeviceConfigService {
	/**
	 * 声明Dao
	 */
	@Resource(name = "com.archermind.etb.device.dao.EtbClientDeviceConfigDao")
	private EtbClientDeviceConfigDao etbClientDeviceConfigDao;

	/**
	 * 显示列表
	 * 
	 * @return
	 */
	public List<EtbClientDeviceConfig> showClientDeviceConfig() {
		return etbClientDeviceConfigDao.showClientDeviceConfig();
	}

	/**
	 * 保存配置
	 * 
	 * @param etbClientDeviceConfig
	 */
	public void saveDeviceConfig(EtbClientDeviceConfig etbClientDeviceConfig) {
		etbClientDeviceConfigDao.saveOrUpdate(etbClientDeviceConfig);
	}

	/**
	 * 通过ID查找配置
	 * 
	 * @param id
	 * @return
	 */
	public EtbClientDeviceConfig findConfigById(int id) {
		return etbClientDeviceConfigDao.findById(EtbClientDeviceConfig.class,
				id);
	}
	
	/**
	 * 获取推送的系统配置信息
	 * @return
	 */
	public ClientSysInfo pushSystemConfig(){
		ClientSysInfo clientSystemInfo = new ClientSysInfo();
		EtbClientDeviceConfig etbClientDeviceConfig=etbClientDeviceConfigDao.getClientSystemInfo();
		clientSystemInfo.setType(1);
		clientSystemInfo.setAction(1);
		clientSystemInfo.setLocktimes((long)etbClientDeviceConfig.getLockTime());
		clientSystemInfo.setSystime(System.currentTimeMillis());
		clientSystemInfo.setUsetime(etbClientDeviceConfig.getStartTime()+","+etbClientDeviceConfig.getEndTime());
		return clientSystemInfo;
	}
	
	/**
	 * push系统配置信息
	 * @param  null
	 */
	public void pushMsgToClient(){
		ClientSysInfo clientSystemInfo = pushSystemConfig();
		String systemInfo = JsonHelper.getGson().toJson(clientSystemInfo);
		String wsdl = PropertyUtil.readData("webservice_wsdl");
		WsClientUtil.callCXFWservice(wsdl,Constant.NOTIFY_NEW_MESSAGE,new Object[]{UUID.randomUUID().toString(),systemInfo,true});
	}
}
