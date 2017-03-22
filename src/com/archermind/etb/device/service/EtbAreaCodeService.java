package com.archermind.etb.device.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.device.dao.EtbAreaCodeDao;
import com.archermind.etb.device.domain.EtbAreaCode;

/**
 * 地址管理 Service
 * 
 * @author huchengyi
 * @version v1.0,2013.07.11
 * @see
 * @since v1.0
 */

@Service("com.archermind.etb.device.service.EtbAreaCodeService")
public class EtbAreaCodeService {

	/**
	 * 地区DAO
	 */
	@Resource(name = "com.archermind.etb.device.dao.EtbAreaCodeDao")
	private EtbAreaCodeDao etbAreaCodeDao;
	
	public List<EtbAreaCode> getAllProvince(){
		List<EtbAreaCode> province = etbAreaCodeDao.showAllProvince();
		
		return province;
	}
	
	public List<EtbAreaCode> getCityByAreaCode(String areaCode){
		List<EtbAreaCode> city = etbAreaCodeDao.findAreaByCodeAndDeep(areaCode, 2);
		
		return city;
	}
	
	public List<EtbAreaCode> getCountyByAreaCode(String areaCode){
		List<EtbAreaCode> county = etbAreaCodeDao.findAreaByCodeAndDeep(areaCode, 3);
		
		return county;
	}
	
	public EtbAreaCode getAreaById(int areaId){
		return etbAreaCodeDao.findAreaById(areaId);
	}
	
	public EtbAreaCode getAreaByCode(String areaCode){
		return etbAreaCodeDao.findAreaByCode(areaCode);
	}
	
	public EtbAreaCode getAreaByParentCode(String parentCode){
		return etbAreaCodeDao.findAreaByParentCode(parentCode);
	}
}
