package com.archermind.etb.ad.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.archermind.etb.ad.domain.AdResource;
import com.archermind.etb.common.BaseDao;
import com.archermind.etb.util.Constant;

/**
 * 广告资源 Dao
 * 
 * @author 003383 陈然
 * @version v1.0,2013.07.15
 * @see
 * @since v1.0
 */

@Repository("com.archermind.etb.ad.dao.AdResourceDao")
public class AdResourceDao extends BaseDao<AdResource> {

	/**
	 * 普通广告：根据广告包ID查询广告资料列表
	 * 
	 * @param adPackageId
	 * @return
	 */
	public List<AdResource> listAdResourceByAdPackageId(String adPackageId) {

		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdResource ");
		sbHQL.append(" where dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据
		sbHQL.append(" and adPackageId = ? ");
		sbHQL.append(" and adResourcePositionSign = ? ");
		return this.find(sbHQL.toString(), adPackageId,
				Constant.AD_RESOURCE_POSITION_SIGN_SINGLE);

	}

	/**
	 * 根据广告包ID查询双屏广告资料列表
	 * 
	 * @param adPackageId
	 * @return
	 */
	public List<AdResource> listAdResourceDoubleByAdPackageId(
			String adPackageId, String flag) {

		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdResource ");
		sbHQL.append(" where dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据
		sbHQL.append(" and adPackageId = ? ");
		sbHQL.append(" and adResourcePositionSign = ? ");// 选择不同区域的数据

		return this.find(sbHQL.toString(), adPackageId, flag);

	}

	/**
	 * 根据广告包ID及屏显位置 查询广告资料列表
	 * 
	 * @param adPackageId
	 * @param adResourcePositionSign
	 * @return
	 */
	public List<AdResource> listAdResourceByAdPackageIdAndPosition(
			String adPackageId, String adResourcePositionSign) {

		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdResource ");
		sbHQL.append(" where dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据
		sbHQL.append(" and adPackageId = ? ");
		sbHQL.append(" and adResourcePositionSign = ? ");

		return this.find(sbHQL.toString(), adPackageId, adResourcePositionSign);

	}

	/**
	 * 紧急广告：根据广告包ID查询广告资料列表
	 * 
	 * @param adPackageId
	 * @return
	 */
	public List<AdResource> listAdResourceByAdPackageIdUrgency(
			String adPackageId) {

		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdResource ");
		sbHQL.append(" where dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据
		sbHQL.append(" and adPackageId = ? ");
		return this.find(sbHQL.toString(), adPackageId);

	}

	/**
	 * 应用广告：根据广告包ID查询广告资料列表
	 * 
	 * @param adPackageId
	 * @return
	 */
	public List<AdResource> listAdResourceByAdPackageIdApp(String adPackageId) {

		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdResource ");
		sbHQL.append(" where dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据
		sbHQL.append(" and adPackageId = ? ");
		return this.find(sbHQL.toString(), adPackageId);

	}

	/**
	 * 根据广告包ID和资源类型,查找资源数量,2013-10-30,RanChen
	 * 
	 * @param adPackageId
	 * @param adResourceType
	 * @return
	 */
	public int getAdResourceCountByAdpackageIdAndAdResourceType(
			String adPackageId, String adResourceType) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AdResource ");
		sbHQL.append(" where dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据
		sbHQL.append(" and adPackageId = ? ");
		sbHQL.append(" and adResourceTypes = ? ");

		List<Object> params = new ArrayList<Object>();
		params.add(adPackageId.trim());
		params.add(adResourceType.trim());

		return this.getRecordNumber(sbHQL.toString(), params);
	}

	/**
	 * 广告包资源删除：根据广告包ID变更资源状态
	 * 
	 * @param adPackageId
	 * @return
	 */
	public boolean delResourceByAdpackageId(String adPackageId) {

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" update AdResource ");
		sbHQL.append(" set dataStat = " + Constant.DATA_STAT_OFF);// 数据失效设置
		sbHQL.append(" where dataStat = " + Constant.DATA_STAT_ON);// 选择0-有效数据
		sbHQL.append(" and adPackageId =  '" + adPackageId + "'");

		return this.update(sbHQL.toString());
	}

}
