package com.archermind.etb.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.user.dao.PowerInfoDao;
import com.archermind.etb.user.dao.UserInfoDao;
import com.archermind.etb.user.domain.PowerInfo;
import com.archermind.etb.user.domain.UserInfo;


/**
 * 权限Service
 * @author 
 * @version 
 * @see 
 * @Time 
 */
@Service("com.archermind.etb.user.service.PowerInfoService")
public class PowerInfoService {

	/**权限Dao*/
	@Resource(name = "com.archermind.etb.user.dao.PowerInfoDao")
	private PowerInfoDao powerInfoDao;
	
	
	/**
	 * 查询所有权限信息
	 * 
	 * @param userInfo
	 */
	public List<PowerInfo> getPowerInfoList() {
		return powerInfoDao.getPowerInfoList();
	}

}
