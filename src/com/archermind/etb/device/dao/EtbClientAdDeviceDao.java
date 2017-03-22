package com.archermind.etb.device.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.device.domain.EtbClientAdDevice;


@Repository("com.archermind.etb.device.dao.EtbClientAdDeviceDao")
public class EtbClientAdDeviceDao extends BaseDao<EtbClientAdDevice> {

//	private static final Logger LOGGER = Logger.getLogger(EtbClientAdDeviceDao.class);
	
	public EtbClientAdDevice findAdDeviceByAdPackageIdAndImei(String adPackageId, String imei){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbClientAdDevice as e ");
		sbHQL.append(" where e.adPackageId = ? ");
		sbHQL.append(" and e.clientDeviceImei = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(adPackageId);
		params.add(imei);
		
		List<EtbClientAdDevice> adDevice_list = this.findListByHql(sbHQL.toString(), params, null, null);
		
		if(null != adDevice_list && adDevice_list.size() > 0){
			return adDevice_list.get(0);
		}
		
		return null;
	}
	
	public List<EtbClientAdDevice> findAdDeviceByAdPackageId(String adPackageId){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbClientAdDevice as e ");
		sbHQL.append(" where e.adPackageId = ? ");
		
		List<Object> params = new ArrayList<Object>();
		params.add(adPackageId);
		
		
		return this.findListByHql(sbHQL.toString(), params, null, null);
	}
}
