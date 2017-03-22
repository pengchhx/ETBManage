package com.archermind.etb.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.user.domain.CompanyInfo;
import com.archermind.etb.util.Constant;


@Repository("com.archermind.etb.user.dao.CompanyInfoDao")
public class CompanyInfoDao extends BaseDao<CompanyInfo>{
	
	
	/**
	 * 查询所有公司
	 * 
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CompanyInfo> getCompanyList(String companyInfoName,int pageSize,int pageNum) {
		StringBuffer sbHQL = new StringBuffer(128);
		sbHQL.append(" from CompanyInfo where dataStat=").append(Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(companyInfoName,sbHQL,params);
		sbHQL.append(" order by companyInfoId asc");
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
		
	}

	/**
	 * 根据查询条件，设置HQL
	 * 
	 * @param queryCondition
	 * @param sbHQL
	 * @param params
	 */
	private void setQueryCondition(String companyInfoName,StringBuffer sbHQL, List<Object> params) {
					if(companyInfoName!=null){
						sbHQL.append(" and companyInfoName like '%'||?||'%' ");
					}
					params.add(companyInfoName);
	}
	
	
	
	/**
	 * 查询所有公司的数量
	 * 
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getCountByCompanyList(String companyInfoName) {
		StringBuffer sbHQL = new StringBuffer(128);
		sbHQL.append(" from CompanyInfo where dataStat=").append(Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(companyInfoName,sbHQL,params);
		return this.getRecordNumber(sbHQL.toString(), params);
		
	}
	


}
