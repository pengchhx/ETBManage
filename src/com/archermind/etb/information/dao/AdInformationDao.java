package com.archermind.etb.information.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;
import com.archermind.etb.common.BaseDao;
import com.archermind.etb.information.domain.AdInformation;

/**
 * 广告采集信息记录DAO
 * @author 003468 wenlong.xiao
 * @version 1.0
 * @see com.archermind.etb.common.BaseDao
 * @Time2013-8-12 14:15:03
 */
@Repository("com.archermind.etb.information.dao.AdInformationDao")
public class AdInformationDao extends BaseDao<AdInformation> {

	/**
	 * 根据用户账号,广告内容查询
	 * @param clientUserInfoAccount 用户账号
	 * @param adId     广告编号
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<AdInformation> findInformationListByPage(String clientUserInfoAccount,
			String adCollectionContent, int pageSize, int pageNum) {
		StringBuffer hql = new StringBuffer(256);
		hql.append("from AdInformation where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(clientUserInfoAccount, adCollectionContent, hql, params);
		hql.append(" order by adCollectionId desc");
		return this.findListByHql(hql.toString(), params, pageNum, pageSize);

	}

	/**
	 * 根据查询条件，设置HQL
	 * @param queryCondition
	 * @param sbHQL
	 * @param params
	 */
	private void setQueryCondition(String clientUserInfoAccount, String adCollectionContent,
			StringBuffer sbHQL, List<Object> params) {
		if (StringUtils.isNotEmpty(clientUserInfoAccount)) {
			sbHQL.append(" and clientUserInfoAccount like '%'||?||'%'");
			params.add(clientUserInfoAccount);
		}if(StringUtils.isNotEmpty(adCollectionContent)){
			sbHQL.append(" and adCollectionContent like '%'||?||'%'");
			params.add(adCollectionContent);
		}
	}

	/**
	 * 根据查询条件查询广告采集信息总数
	 * @param params
	 * @return
	 */
	public int getAdInformationCount(String clientUserInfoAccount, String adCollectionContent) {
		StringBuffer hql = new StringBuffer(256);
		hql.append("from AdInformation where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(clientUserInfoAccount, adCollectionContent, hql, params);
		return this.getRecordNumber(hql.toString(), params);
	}

}
