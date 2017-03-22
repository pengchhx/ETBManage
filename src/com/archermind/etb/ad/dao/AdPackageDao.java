package com.archermind.etb.ad.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;

import com.archermind.etb.ad.domain.AdPackage;
import com.archermind.etb.common.BaseDao;
import com.archermind.etb.util.Constant;

/**
 * 广告管理 Dao
 * 
 * @author 003383 陈然
 * @version v1.0,2013-07-11
 * @see
 * @since v1.0
 */

@Repository("com.archermind.etb.ad.dao.AdPackageDao")
public class AdPackageDao extends BaseDao<AdPackage> {

	/**
	 * 根据查询条件（广告包名）查询广告包总数
	 * 
	 * @param adPackageName
	 * @return
	 */
	public int getAllAdCountByName(String adPackageName) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AdPackage as P ");
		sbHQL.append(" ,AdTemplate as T ");
		sbHQL.append(" where P.adTemplateId = T.adTemplateId ");
		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

		List<Object> params = new ArrayList<Object>();
		setQueryCondition(adPackageName, sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}

	/**
	 * 根据查询条件（广告包名）查询审核广告包总数
	 * 
	 * @param adPackageName
	 * @return
	 */
	public int getAuditAdCountByName(String adPackageName) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AdPackage as P ");
		sbHQL.append(" ,AdTemplate as T ");
		sbHQL.append(" where P.adTemplateId = T.adTemplateId ");
		// 选择已提交待审核
		sbHQL.append(" and P.adPackageStatus = "
				+ Constant.AD_PACKAGE_STATUS_COMMITTED);
		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

		List<Object> params = new ArrayList<Object>();
		setQueryCondition(adPackageName, sbHQL, params);

		return this.getRecordNumber(sbHQL.toString(), params);
	}

	/**
	 * 根据查询条件分页查询广告包信息-广告包管理
	 * 
	 * @param adPackageName
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Map<String, Object>> listAllAdByPage(String adPackageName,
			int pageSize, int pageNum) {

		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdPackage as P ");
		sbHQL.append(" ,AdTemplate as T ");
		sbHQL.append(" where P.adTemplateId = T.adTemplateId ");
		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

		List<Object> params = new ArrayList<Object>();
		setQueryCondition(adPackageName, sbHQL, params);
		sbHQL.append(" order by P.adPackageCreaterTime desc");

		return this.findList(sbHQL.toString(), params, pageNum, pageSize);
	}

	/**
	 * 根据查询条件分页查询广告包信息-审核
	 * 
	 * @param adPackageName
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Map<String, Object>> listAuditAdByPage(String adPackageName,
			int pageSize, int pageNum) {

		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdPackage as P ");
		sbHQL.append(" ,AdTemplate as T ");
		sbHQL.append(" where P.adTemplateId = T.adTemplateId ");
		// 选择已提交待审核
		sbHQL.append(" and P.adPackageStatus = "
				+ Constant.AD_PACKAGE_STATUS_COMMITTED);

		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

		List<Object> params = new ArrayList<Object>();
		setQueryCondition(adPackageName, sbHQL, params);
		sbHQL.append(" order by P.adPackageCreaterTime desc");

		return this.findList(sbHQL.toString(), params, pageNum, pageSize);
	}

	/**
	 * 设置广告包管理模块查询条件 20130826 查询”_“处理
	 * 
	 * @param adPackageName
	 * @param sbHQL
	 * @param params
	 */
	private void setQueryCondition(String adPackageName, StringBuffer sbHQL,
			List<Object> params) {
		if (!StringUtils.isEmpty(adPackageName)) {
			sbHQL.append(" and P.adPackageName like '%' || ? || '%'  ");
			params.add(adPackageName.trim());

		}
	}

	/**
	 * 根据查询条件（广告包名）查询发布广告包总数
	 * 
	 * @param adPackageName
	 * @return
	 */
	public int getAdPublishCountByName(String adPackageName) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AdPackage as P ");
		sbHQL.append(" ,AdTemplate as T ");
		sbHQL.append(" where P.adTemplateId = T.adTemplateId ");
		// 状态为：1-已审核通过或者4-已发布
		sbHQL.append(" and (P.adPackageStatus = "
				+ Constant.AD_PACKAGE_STATUS_CHECK_PASSED);
		sbHQL.append("  or P.adPackageStatus = "
				+ Constant.AD_PACKAGE_STATUS_PUBLISHED);
		sbHQL.append(" )");
		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

		List<Object> params = new ArrayList<Object>();
		setPublishQueryCondition(adPackageName, sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}

	/**
	 * 根据查询条件分页查询广告发布列表-广告发布查询
	 * 
	 * @param adPackageName
	 * @param status
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<Map<String, Object>> listAdPublishByPage(String adPackageName,
			int pageSize, int pageNum) {

		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdPackage as P ");
		sbHQL.append(" ,AdTemplate as T ");
		sbHQL.append(" where P.adTemplateId = T.adTemplateId ");
		// 状态为：1-已审核通过或者4-已发布
		sbHQL.append(" and (P.adPackageStatus = "
				+ Constant.AD_PACKAGE_STATUS_CHECK_PASSED);
		sbHQL.append("  or P.adPackageStatus = "
				+ Constant.AD_PACKAGE_STATUS_PUBLISHED);
		sbHQL.append(" )");
		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

		List<Object> params = new ArrayList<Object>();
		setPublishQueryCondition(adPackageName, sbHQL, params);
		sbHQL.append(" order by P.adPackageCheckTime desc");

		return this.findList(sbHQL.toString(), params, pageNum, pageSize);
	}

	/**
	 * 条件设置-广告发布查询
	 * 
	 * @param adPackageName
	 * @param sbHQL
	 * @param params
	 */
	private void setPublishQueryCondition(String adPackageName,
			StringBuffer sbHQL, List<Object> params) {
		if (!StringUtils.isEmpty(adPackageName)) {
			sbHQL.append(" and P.adPackageName like '%' || ? || '%'  ");
			params.add(adPackageName.trim());
		}
	}

	/**
	 * 查询批次号是否存在
	 * 
	 * @param adPackageBatchNo
	 * @return
	 */
	public int findAdPackageInfoByBatchNo(String adPackageBatchNo) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AdPackage as P ");
		sbHQL.append(" where P.adPackageBatchNo = ? ");
		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

		List<Object> params = new ArrayList<Object>();
		params.add(adPackageBatchNo);
		return this.getRecordNumber(sbHQL.toString(), params);

	}

	/**
	 * 广告包开始时间判断：同一种类型的广告包时间不能重叠
	 * 
	 * @param adPackageType
	 * @return
	 */
	public int judgeAdTimeIsOverlap(AdPackage adPackage) {

		StringBuffer sql = new StringBuffer(256);
		List list = null;

		sql.append("select adPackageBegintime,adPackageEndtime from AdPackage  ");
		sql.append(" where adPackageType =? and dataStat= "
				+ Constant.DATA_STAT_ON);// 数据有效
		// 1、开始时间跟DB数据中有重叠；
		sql.append(" and (( ? between adPackageBegintime and adPackageEndtime ) ");
		// 2、结束时间跟DB数据中有重叠；
		sql.append(" or ( ? between adPackageBegintime and adPackageEndtime ) ");
		// 3、DB中有数据的开始时间在设定时间范围内；
		sql.append(" or ( adPackageBegintime  between ? and ?");
		// 4、DB中有数据的结束时间在设定时间范围内；
		sql.append(") or ( adPackageEndtime  between ? and ? ");
		sql.append(" ) ) ");

		// 修改
		if (StringUtils.isNotEmpty(adPackage.getAdPackageId())) {
			sql.append(" and adPackageId != ?");
			list = this.getHibernateTemplate()
					.find(sql.toString(), adPackage.getAdPackageType(),
							adPackage.getAdPackageBegintime(),
							adPackage.getAdPackageEndtime(),
							adPackage.getAdPackageBegintime(),
							adPackage.getAdPackageEndtime(),
							adPackage.getAdPackageBegintime(),
							adPackage.getAdPackageEndtime(),
							adPackage.getAdPackageId());
		} else {// 新增
			list = this.getHibernateTemplate().find(sql.toString(),
					adPackage.getAdPackageType(),
					adPackage.getAdPackageBegintime(),
					adPackage.getAdPackageEndtime(),
					adPackage.getAdPackageBegintime(),
					adPackage.getAdPackageEndtime(),
					adPackage.getAdPackageBegintime(),
					adPackage.getAdPackageEndtime());
		}

		if (null != list && list.size() > 0) {
			if (null == list.get(0)) {
				// 没有查询到数据，则日期无重叠
				return 0;
			} else {
				return 1;
			}
		} else {
			// 没有查询到数据，则日期无重叠
			return 0;
		}

	}

	
	/**
	 * 根据查询条件分页查询广告包信息
	 * 
	 * @param adPackageName
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<AdPackage> listAdByStatusAndPage(int adPackageStatus, int pageNum, int pageSize) {
		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdPackage as P ");
		//sbHQL.append(" ,AdTemplate as T ");
		//sbHQL.append(" where P.adTemplateId = T.adTemplateId ");
		// 选择已提交待审核
		sbHQL.append(" where P.adPackageStatus = ? ");

		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

		List<Object> params = new ArrayList<Object>();
		params.add(adPackageStatus);
		//setQueryCondition(adPackageName, sbHQL, params);
		sbHQL.append(" order by P.adPackageCreaterTime desc");

		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}
	
	/**
	 * 根据查询条件（广告包名）查询审核广告包总数
	 * 
	 * @param adPackageName
	 * @return
	 */
	public int getAdCountByStatus(int adPackageStatus) {
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AdPackage as P ");
		sbHQL.append(" ,AdTemplate as T ");
		sbHQL.append(" where P.adTemplateId = T.adTemplateId ");
		// 选择已提交待审核
		sbHQL.append(" and P.adPackageStatus = " + adPackageStatus);
		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

		List<Object> params = new ArrayList<Object>();
//		setQueryCondition(adPackageName, sbHQL, params);

		return this.getRecordNumber(sbHQL.toString(), params);
	}
	
	/**
	 * 根据查询条件查询广告包信息
	 * 
	 * @param adPackageName
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> listAdByStatus(int adPackageStatus) {
		
		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdPackage as P ");
		sbHQL.append(" ,AdTemplate as T ");
		sbHQL.append(" where P.adTemplateId = T.adTemplateId ");
		// 选择已提交待审核
		sbHQL.append(" and P.adPackageStatus = " + adPackageStatus);

		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

//		List<Object> params = new ArrayList<Object>();
		//setQueryCondition(adPackageName, sbHQL, params);
		sbHQL.append(" order by P.adPackageCreaterTime desc");

		return (List<Object>)this.find(sbHQL.toString());
	}
	
	public List<AdPackage> findConflictAdPackageByTime(Date start, Date end){
		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdPackage as P ");
		// 选择已提交待审核
		sbHQL.append(" where (P.adPackageBegintime > ? and  P.adPackageBegintime < ? ) or (P.adPackageEndtime > ? and P.adPackageEndtime < ?)");

		sbHQL.append(" and P.dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据

		List<Object> params = new ArrayList<Object>();
		params.add(start);
		params.add(end);
		params.add(start);
		params.add(end);
		sbHQL.append(" order by P.adPackageBegintime desc");

		return this.findListByHql(sbHQL.toString(), params, null, null);
	}
}
