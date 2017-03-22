package com.archermind.etb.device.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.device.domain.EtbAreaCode;

@Repository("com.archermind.etb.device.dao.EtbAreaCodeDao")
public class EtbAreaCodeDao extends BaseDao<EtbAreaCode> {

	@SuppressWarnings("unchecked")
	public List<EtbAreaCode> showAllProvince(){
		return (List<EtbAreaCode>) this.find(" from EtbAreaCode where areaDeep = '1' ");
	}
	
	public List<EtbAreaCode> findAreaByCodeAndDeep(String areaCode, Integer deep){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbAreaCode where areaParentCode = ? and areaDeep = ?");
		
		return this.find(sbHQL.toString(), areaCode, deep);
	}
	
	public EtbAreaCode findAreaById(int areaId){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbAreaCode where areaId = ?");
		
		return this.findById(EtbAreaCode.class, areaId);
	}
	
	public EtbAreaCode findAreaByCode(String areaCode){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbAreaCode where areaCode = ?");
		
		return this.find(sbHQL.toString(), areaCode).get(0);
	}
	
	public EtbAreaCode findAreaByParentCode(String parentCode){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbAreaCode where areaCode = ?");
		
		return this.find(sbHQL.toString(), parentCode).get(0);
	}
}
