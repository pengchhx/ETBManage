package com.archermind.etb.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.user.dao.CompanyInfoDao;
import com.archermind.etb.user.domain.CompanyInfo;


@Service("com.archermind.etb.user.service.CompanyInfoService")
public class CompanyInfoService {

	
	/**用户信息Dao*/
	@Resource(name = "com.archermind.etb.user.dao.CompanyInfoDao")
	private CompanyInfoDao companyInfoDao;
	
	
	
	
	/**
	 * 查询公司
	 */
	public List<CompanyInfo> getCompanyList(String companyInfoName,int pageSize,int pageNum){
		
		return companyInfoDao.getCompanyList(companyInfoName,pageSize,pageNum);
	}
	
	
	
	/**
	 * 查询公司的数量
	 */
	public int getCountByCompanyList(String companyInfoName){
		
		return companyInfoDao.getCountByCompanyList(companyInfoName);
	}
	
	/**
	 * 添加公司or修改公司
	 */
	public void addCompany(CompanyInfo companyInfo){
		companyInfoDao.saveOrUpdate(companyInfo);
		 
	}
	
	
	/**
	 * 根据公司id查询公司对象
	 * 
	 *
	 */
	public CompanyInfo getCompanyInfoById(long companyId){
		return companyInfoDao.findById(CompanyInfo.class,companyId);
		 
	}
	
	
	
	
	
	
	
	
	public CompanyInfoDao getCompanyInfoDao() {
		return companyInfoDao;
	}

	public void setCompanyInfoDao(CompanyInfoDao companyInfoDao) {
		this.companyInfoDao = companyInfoDao;
	}
	
}
