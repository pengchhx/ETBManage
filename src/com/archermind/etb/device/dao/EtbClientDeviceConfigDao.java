package com.archermind.etb.device.dao;

/**
 * 终端设备系统配置模块Dao层
 * @author 001667
 * @version 2.0
 * @since 1.0
 */
import java.util.List;

import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.device.domain.EtbClientDeviceConfig;
import com.archermind.etb.util.Constant;

@Repository("com.archermind.etb.device.dao.EtbClientDeviceConfigDao")
public class EtbClientDeviceConfigDao extends BaseDao<EtbClientDeviceConfig> {

	/**
	 * 查找配置列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EtbClientDeviceConfig> showClientDeviceConfig() {
		return (List<EtbClientDeviceConfig>) this
				.find(" from EtbClientDeviceConfig where stat=0");
	}
	
	/**
	 * 获取终端系统配置信息
	 * @return
	 */
	public EtbClientDeviceConfig getClientSystemInfo() {
		String hql = "from EtbClientDeviceConfig t WHERE t.stat = "+Constant.DATA_STAT_ON;
		List<EtbClientDeviceConfig> etbClientDeviceConfig = this.findListByHql(hql, null, null, null);
		if (etbClientDeviceConfig != null && etbClientDeviceConfig.size() > 0) {
			return etbClientDeviceConfig.get(0);
		}
		return new EtbClientDeviceConfig();
	};
}
