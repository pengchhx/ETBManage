package com.archermind.etb.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.user.dao.UserInfoDao;
import com.archermind.etb.user.domain.CompanyInfo;
import com.archermind.etb.user.domain.UserInfo;
import com.archermind.etb.util.Constant;

/**
 * 用户Service
 * @author 003445 yang.chen
 * @version 1.0
 * @see 
 * @Time 2013-8-2-10:15:10
 */
@Service("com.archermind.etb.user.service.UserInfoService")
public class UserInfoService {
    
	/**用户信息Dao*/
	@Resource(name = "com.archermind.etb.user.dao.UserInfoDao")
	private UserInfoDao userInfoDao;

	/**
	 * 添加用户信息
	 * 
	 * @param userInfo
	 */
	public void addUserInfo(UserInfo userInfo) {
		userInfoDao.save(userInfo);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param userInfo
	 */
	public void saveUserInfo(UserInfo userInfo) {
		//设置数据状态
		userInfo.setDataStat(Constant.DATA_STAT_ON);
		userInfoDao.saveOrUpdate(userInfo);
	}

	/**
	 * 根据主键id删除用户信息
	 * 
	 * @param userInfoId
	 */
	public void delUserInfoById(long userInfoId) {

		UserInfo userInfo = userInfoDao.findById(UserInfo.class, userInfoId);
		if (null != userInfo) {
			userInfo.setDataStat(Constant.DATA_STAT_OFF);
		}
	}

	/**
	 * 根据用户id获取用户信息
	 * 
	 * @param userInfoId
	 * @return
	 */
	public UserInfo getUserInfoById(long userInfoId) {
		return userInfoDao.findById(UserInfo.class, userInfoId);
	}

	/**
	 * 根据查询条件，分页查询用户信息
	 * 
	 * @param params
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<UserInfo> listUserInfoByPage(String userInfoUsername,
			String userInfoName,String powerInfoId,long companyInfoId, int pageSize, int pageNum) {

		return userInfoDao.listUserInfoByPage(userInfoUsername, userInfoName,powerInfoId,companyInfoId,
				pageSize, pageNum);
	}

	/**
	 * 根据查询条件，查询用户信息总数
	 * 
	 * @param params
	 * @return
	 */
	public int getUserInfoCount(String userInfoUsername, String userInfoName, String powerInfoId,long companyInfoId) {

		return userInfoDao.getUserInfoCount(userInfoUsername, userInfoName, powerInfoId,companyInfoId);
	}
	
	/**
	 * 根据用户名，获取用户信息集合
	 * @param userInfoUsername
	 * @return
	 */
	public List<UserInfo> getUserInfoByUsername(String userInfoUsername) {
		return userInfoDao.getUserInfoByUsername(userInfoUsername);
	}
	
	/**
	 * 根据该用户名，获取该用户实例
	 * @return
	 */
	public boolean findUserInfoByName(String userInfoUsername,long userInfoId) {
		UserInfo userInfo = userInfoDao.findUserInfoByName(userInfoUsername,userInfoId);
		if(userInfo == null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据登录账号获取真实姓名
	 */
	public String getUserNameByLoginID(String loginCount) {
		
		return userInfoDao.getUserNameByLoginID(loginCount);
	}
	/**
	 * 根据登录账号获取用户
	 */
	public UserInfo getUserByLoginID(String loginCount) {
		
		return userInfoDao.getUserByLoginID(loginCount);
	}
	
	/**
	 * 查询所有公司用户
	 */
	public List<UserInfo> getCompanyUser(){
		
		return userInfoDao.getCompanyUser();
	}
	

	
}
