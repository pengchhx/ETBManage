package com.archermind.etb.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.user.domain.PowerInfo;
import com.archermind.etb.user.domain.UserInfo;
import com.archermind.etb.util.Constant;



/**
 * 权限Dao
 * @author 
 * @version 
 * @see 
 * @Time 
 */
@Repository("com.archermind.etb.user.dao.PowerInfoDao")
public class PowerInfoDao extends BaseDao<PowerInfo>{
	
	
	/**
	 * 查询所有权限信息
	 * 
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PowerInfo> getPowerInfoList() {
		StringBuffer sbHQL = new StringBuffer(128);
		sbHQL.append(" from PowerInfo  where  dataStat = ").append(Constant.DATA_STAT_ON);
		List<PowerInfo> powerList = this.getHibernateTemplate().find(
				sbHQL.toString());
		return powerList;
	}
	
	
}
