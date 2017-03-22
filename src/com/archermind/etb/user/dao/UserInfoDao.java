package com.archermind.etb.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.user.domain.CompanyInfo;
import com.archermind.etb.user.domain.PowerInfo;
import com.archermind.etb.user.domain.UserInfo;
import com.archermind.etb.util.Constant;

/**
 * 用户信息Dao
 * @author 003468 wenlong.xiao
 * @version 1.0
 * @see com.archermind.etb.common.BaseDao
 * @Time 2013-8-2-10:31:00
 */
@Repository("com.archermind.etb.user.dao.UserInfoDao")
public class UserInfoDao extends BaseDao<UserInfo> {

	/**
	 * 根据查询条件分页查询用户信息
	 * 
	 * @param params
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<UserInfo> listUserInfoByPage(String userInfoUsername,
			String userInfoName, String powerInfoId,long companyInfoId,int pageSize, int pageNum) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append("from UserInfo u   where u.dataStat = ").append(
				Constant.DATA_STAT_ON );
		sbHQL.append(" and u.companyInfo.companyInfoId = ").append(companyInfoId);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(userInfoUsername, userInfoName,powerInfoId, sbHQL, params);
		sbHQL.append(" order by u.userInfoId desc");
		
		System.out.println(sbHQL.toString());
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}

	
	/**
	 * 根据查询条件，设置HQL
	 * 
	 * @param queryCondition
	 * @param sbHQL
	 * @param params
	 */
	private void setQueryCondition(String userInfoUserName,
			String userInfoName, String powerInfoId,StringBuffer sbHQL, List<Object> params) {
		if (!StringUtils.isEmpty(userInfoUserName)) {
			sbHQL.append(" and u.userInfoUsername like '%'||?||'%' ");
			params.add(userInfoUserName);
		}

		if (!StringUtils.isEmpty(userInfoName)) {
			sbHQL.append(" and u.userInfoName like '%'||?||'%' ");
			params.add(userInfoName);
		}

//		if (!StringUtils.isEmpty(powerInfoId)) {
//			sbHQL.append(" and exits(select * from ETB_USER_POWER where ?= )" );
//			params.add(powerInfoId);
//
//		}
	
	}

	/**
	 * 根据查询条件查询用户信息总数
	 * 
	 * @param params
	 * @return
	 */
	public int getUserInfoCount(String userInfoUsername, String userInfoName, String powerInfoId,long companyInfoId) {
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append("from UserInfo u   where u.dataStat = ").append(
				Constant.DATA_STAT_ON);
		sbHQL.append(" and u.companyInfo.companyInfoId = ").append(companyInfoId);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(userInfoUsername, userInfoName, powerInfoId,sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}

	/**
	 * 根据用户名 ,获取用户信息集合
	 * 
	 * @param userInfoUsername
	 * @return
	 */
	public List<UserInfo> getUserInfoByUsername(String userInfoUsername) {

		StringBuffer sbHQL = new StringBuffer(256);
		//通过用户名及逻辑状态dataStat 来查询存在的用户
		sbHQL.append("from UserInfo where userInfoUsername = ? and dataStat =").append(Constant.DATA_STAT_ON);
		List<UserInfo> result = this.find(sbHQL.toString(), userInfoUsername);
		return result;
	}
   
	/**
	 * 根据该用户名、用户编号，获取该用户实例
	 * 
	 * @param userInfoUsername
	 * @param userInfoId
	 * @return
	 */
	public UserInfo findUserInfoByName(String userInfoUsername, long userInfoId) {
		List<UserInfo> result = null;

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append("from UserInfo where dataStat = ")
				.append(Constant.DATA_STAT_ON)
				.append(" and userInfoUsername = ? ");

		// 数据库中存在该用户
		if (userInfoId > 0) {
			sbHQL.append("and userInfoId != ? ");
			result = this.find(sbHQL.toString(), userInfoUsername, userInfoId);
		} else {
			result = this.find(sbHQL.toString(), userInfoUsername);
		}

		UserInfo userInfo = (result.size() == 0) ? null : (result.get(0));
		return userInfo;

	}

	/**
	 * 根据登录账号获取真实姓名
	 * 
	 * @param loginCount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getUserNameByLoginID(String loginCount) {
		StringBuffer sbHQL = new StringBuffer(128);
		sbHQL.append("select userInfoName from UserInfo where userInfoUsername = ? and dataStat = ").append(Constant.DATA_STAT_ON);
		List<String> userNameList = this.getHibernateTemplate().find(
				sbHQL.toString(), loginCount);
		return (userNameList.size() == 0) ? null : (userNameList.get(0));
	}
	
	
	//---------------------------------------------------------------------------------------------------------------------------------
	/**
	 * 获取用户信息
	 * 
	 * @param loginCount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public UserInfo getUserByLoginID(String loginCount) {
		StringBuffer sbHQL = new StringBuffer(128);
		sbHQL.append(" from UserInfo where userInfoUsername = ? and dataStat = ").append(Constant.DATA_STAT_ON);
		List<UserInfo> userList = this.getHibernateTemplate().find(
				sbHQL.toString(), loginCount);
		return (userList.size() == 0) ? null : (userList.get(0));
	}
	
	/**
	 * 查询所有公司用户
	 * 
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<UserInfo> getCompanyUser() {
		StringBuffer sbHQL = new StringBuffer(128);
		sbHQL.append(" from UserInfo u where u.roleInfo.roleInfoId =7 and dataStat = ").append(Constant.DATA_STAT_ON);
		List<UserInfo> userList = this.getHibernateTemplate().find(
				sbHQL.toString());
		return userList;
	}

	
	
	
}
