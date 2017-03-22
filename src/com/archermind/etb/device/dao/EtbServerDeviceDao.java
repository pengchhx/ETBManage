package com.archermind.etb.device.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.device.domain.EtbServerDevice;
import com.archermind.etb.util.Constant;

/**
 * 平台设备service类
 * 
 * @author 001667 梁伟
 * @version 1.0 2013-07-11
 * @see com.archermind.etb.common.BaseDao
 * @since 1.0
 */
@Repository("com.archermind.etb.device.dao.EtbServerDeviceDao")
public class EtbServerDeviceDao extends BaseDao<EtbServerDevice>
{
	/**
	 * 获取设备信息Count
	 * @param name
	 * @return
	 */
	public int getDeviceInfoCount(String name)
	{
		StringBuffer sbhql = new StringBuffer(256);
		sbhql.append(" from EtbServerDevice where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(name, sbhql, params);
		return this.getRecordNumber(sbhql.toString(), params);
	}

	/**
	 * 设置查询条件
	 * @param name
	 * @param sbhql
	 * @param params
	 */
	private void setQueryCondition(String name, StringBuffer sbhql,
			List<Object> params)
	{
		sbhql.append(" and stat = ? ");
		params.add(Constant.DATA_STAT_ON);
		if (!StringUtils.isEmpty(name)) {
			sbhql.append(" and name like '%' || ? || '%' ");
			params.add(name);
		}
	}

	/**
	 * 分页查询设备信息
	 * @param name
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<EtbServerDevice> listDeviceInfoByPage(String name,
			int pageSize, int pageNum)
	{

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from EtbServerDevice where 1 = 1 ");
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(name, sbHQL, params);
		sbHQL.append(" order by id desc");
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}
}
