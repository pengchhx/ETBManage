package com.archermind.etb.information.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;

import com.archermind.etb.information.domain.Payment;

/**
 * 缴费DAO
 * @author 003468 wenlong.xiao
 * @version 1.0
 * @see com.archermind.etb.common.BaseDao
 * @Time 2013-8-12 15:13:48
 */
@Repository("com.archermind.etb.information.dao.PaymentDao")
public class PaymentDao extends BaseDao<Payment> {

	/**
	 * 根据用户账号,缴费流水号查询
	 * 
	 * @param clientUserInfoAccount
	 *            用户账号
	 * @param paymentId
	 *            广告编号
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Payment> findPaymentListByPage(String clientUserInfoAccount,
			String paymentId, int pageSize, int pageNum) {
		StringBuffer hql = new StringBuffer(256);
		hql.append("from Payment where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(clientUserInfoAccount, paymentId, hql, params);
		hql.append(" order by paymentCollectionId desc");
		return this.findListByHql(hql.toString(), params, pageNum, pageSize);

	}

	/**
	 * 根据查询条件，设置HQL
	 * 
	 * @param queryCondition
	 * @param sbHQL
	 * @param params
	 */
	private void setQueryCondition(String clientUserInfoAccount, String paymentId,
			StringBuffer sbHQL, List<Object> params) {
		if (StringUtils.isNotEmpty(clientUserInfoAccount)) {
			sbHQL.append(" and clientUserInfoAccount like '%'||?||'%'");
			params.add(clientUserInfoAccount);
		} 
		if (StringUtils.isNotEmpty(paymentId)) {
			sbHQL.append(" and paymentId like '%'||?||'%'");
			params.add(paymentId);
		}
       
	}

	/**
	 * 根据查询条件查询缴费采集信息总数
	 * 
	 * @param params
	 * @return
	 */
	public int getPaymentCount(String userName, String paymentId) {
		StringBuffer hql = new StringBuffer(256);
		hql.append("from Payment where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(userName, paymentId, hql, params);
		return this.getRecordNumber(hql.toString(), params);
	}

}
