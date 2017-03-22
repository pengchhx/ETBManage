package com.archermind.etb.user.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.user.domain.ClientUserInfo;
import com.archermind.etb.util.Constant;

/**
 * 
 * @author yang.chen
 * @version 1.0
 * @see com.archermind.etb.common.BaseDao
 * @Time 2013年8月2日下午2:46:53
 */
@Repository("com.archermind.etb.user.dao.ClientUserInfoDao")
public class ClientUserInfoDao extends BaseDao<ClientUserInfo> {

	/**
	 * 根据查询条件分页查询终端用户信息
	 * 
	 * @param params
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<ClientUserInfo> listClientUserInfoByPage(
			String clientUserInfoName, String clientUserInfoAccount,
			String clientUserInfoIdentity, int pageSize, int pageNum) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append("from ClientUserInfo where dataStat = ").append(
				Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(clientUserInfoName, clientUserInfoAccount,
				clientUserInfoIdentity, sbHQL, params);
		sbHQL.append(" order by clientUserInfoId desc");
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}

	/**
	 * 根据查询条件，设置HQL
	 * 
	 * @param queryCondition
	 * @param sbHQL
	 * @param params
	 */
	private void setQueryCondition(String clientUserInfoName,
			String clientUserInfoAccount, String clientUserInfoIdentity,
			StringBuffer sbHQL, List<Object> params) {

		if (!StringUtils.isEmpty(clientUserInfoName)) {
			sbHQL.append(" and clientUserInfoName like '%'||?||'%' ");
			params.add(clientUserInfoName);
		}

		if (!StringUtils.isEmpty(clientUserInfoAccount)) {
			sbHQL.append(" and clientUserInfoAccount like '%'||?||'%' ");
			params.add(clientUserInfoAccount);
		}

		if (!StringUtils.isEmpty(clientUserInfoIdentity)) {
			sbHQL.append(" and clientUserInfoIdentity like '%'||?||'%' ");
			params.add(clientUserInfoIdentity);
		}

	}

	/**
	 * 根据查询条件查询终端用户信息总数
	 * 
	 * @param params
	 * @return
	 */
	public int getClientUserInfoCount(String clientUserInfoName,
			String clientUserInfoAccount, String clientUserInfoIdentity) {
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append("from ClientUserInfo where dataStat = ").append(
				Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(clientUserInfoName, clientUserInfoAccount,
				clientUserInfoIdentity, sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}

	/**
	 * 根据用户名获取终端用户信息集合
	 * 
	 * @param clientUserInfoName
	 * @return
	 */
	public List<ClientUserInfo> getClientUserInfoByName(
			String clientUserInfoName) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append("from ClientUserInfo where clientUserInfoName = ?");
		List<ClientUserInfo> result = this.find(sbHQL.toString(),
				clientUserInfoName);
		return result;
	}

	/**
	 * 根据该终端用户账号、用户编号，获取该用户实例
	 * 
	 * @param clientUserInfoAccount
	 * @param clientUserInfoId
	 * @return
	 */
	public ClientUserInfo findClientUserInfoByAccount(
			String clientUserInfoAccount, long clientUserInfoId) {
		List<ClientUserInfo> result = null;

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append("from ClientUserInfo where dataStat = ")
				.append(Constant.DATA_STAT_ON)
				.append(" and clientUserInfoAccount = ? ");

		if (clientUserInfoId > 0) {
			sbHQL.append("and clientUserInfoId != ? ");
			result = this.find(sbHQL.toString(), clientUserInfoAccount,
					clientUserInfoId);
		} else {
			result = this.find(sbHQL.toString(), clientUserInfoAccount);
		}

		ClientUserInfo clientUserInfo = (result.size() == 0) ? null : (result
				.get(0));
		return clientUserInfo;
	}

	/**
	 * 删除用户与设备之间的关联表记录
	 * 
	 * @param clientUserInfoId
	 */
	public void delClientDeviceById(final long clientUserInfoId) {

		this.getHibernateTemplate().execute(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql = "delete from ETB_CLIENT_USER_DEVICE where CLIENT_USER_INFO_ID = ?";
				Query query = session.createSQLQuery(sql);
				query.setLong(0, clientUserInfoId);
				query.executeUpdate();
				session.flush();
				return null;
			}
		});

	}

}
