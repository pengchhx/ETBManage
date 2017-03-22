package com.archermind.etb.information.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.information.domain.LotteryCollection;

/**
 * 彩票信息采集Dao
 * 
 * @author 003445 张瑞
 * @version 1.0
 * @see com.archermind.etb.common.BaseDao
 * @time 2013-08-22 10:31
 */
@Repository("com.archermind.etb.information.dao.LotteryCollectionDao")
public class LotteryCollectionDao extends BaseDao<LotteryCollection> {

	/**
	 * 根据查询条件，分页查询彩票采集信息
	 * 
	 * @param params
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<LotteryCollection> listLotteryCollectionByPage(
			String clientUserInfoAccount, String lotteryId, int pageSize,
			int pageNum) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append("from LotteryCollection where 1=1");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(clientUserInfoAccount, lotteryId, sbHQL, params);
		sbHQL.append(" order by lotteryCollectionId desc");
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}

	/**
	 * 根据查询条件，设置HQL
	 * 
	 * @param queryCondition
	 * @param sbHQL
	 * @param params
	 */
	private void setQueryCondition(String clientUserInfoAccount,
			String lotteryId, StringBuffer sbHQL, List<Object> params) {
		if (!StringUtils.isEmpty(clientUserInfoAccount)) {
			sbHQL.append(" and clientUserInfoAccount like '%'||?||'%' ");
			params.add(clientUserInfoAccount);
		}

		if (!StringUtils.isEmpty(lotteryId)) {
			sbHQL.append(" and lotteryId like '%'||?||'%' ");
			params.add(lotteryId);
		}

	}

	/**
	 * 根据查询条件,查询彩票采集信息总数
	 * 
	 * @param params
	 * @return
	 */
	public int getLotteryCollectionCount(String clientUserInfoAccount,
			String lotteryId) {
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append("from LotteryCollection where 1=1");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(clientUserInfoAccount, lotteryId, sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}

}
