package com.archermind.etb.ad.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.archermind.etb.ad.domain.AdTemplate;
import com.archermind.etb.common.BaseDao;

/**
 * 广告模板 Dao
 * 
 * @author 003383 陈然
 * @version v1.0,2013.07.12
 * @see
 * @since v1.0
 */

@Repository("com.archermind.etb.ad.dao.AdTemplateDao")
public class AdTemplateDao extends BaseDao<AdTemplate> {

	/**
	 * 查询审核广告模板
	 * 
	 * @return
	 */
	public List<AdTemplate> listAllAdTemplate(int adTemplateType) {

		StringBuffer sbHQL = new StringBuffer(256);

		sbHQL.append(" from AdTemplate as AT");
		sbHQL.append(" where AT.dataStat = 0 ");// 选择0-有效数据
		sbHQL.append(" and AT.adTemplateType = ?");// 模板类型
		sbHQL.append(" order by AT.adTemplateId desc");
		List<AdTemplate> tmlist = this.find(sbHQL.toString(), adTemplateType);
		return tmlist;
	}

}
