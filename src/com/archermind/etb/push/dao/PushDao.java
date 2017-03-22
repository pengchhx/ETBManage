package com.archermind.etb.push.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.ota.domain.OtaVersionInfo;
import com.archermind.etb.push.domain.PushMsg;
import com.archermind.etb.util.Constant;


/**
 * 
 * push模块dao层的增删改查
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130827
 * @see 
 * @since 1.0
 */
@Repository("com.archermind.etb.push.dao.PushDao")
public class PushDao extends BaseDao<PushMsg>{
	
	/**
     *  条件查询出所有push消息
     * 
     * @param  msgContent   消息的内容
     * @param  datastat 是否已经删除    0否，1是
     * @param  start   分页参数当前页
     * @param  size    分页参数每页条数
     * @return  List<PushMsg>的泛型
     * @throws  
     * @see
     */
	public List<PushMsg> getPushInfoList(String msgContent,Integer datastat,Integer start, Integer size)
	{
		List<PushMsg> list=new ArrayList<PushMsg>();

		String hql = " from PushMsg c where 1=1";
		List<Object> params = new ArrayList<Object>();
		if(null!=msgContent&&msgContent.length()>0)
		{
			hql += " and  c.msgContent like '%' || ? || '%' ";
			params.add(msgContent);
		}
		if(null!=datastat)
		{
			hql += " and  c.datastat= ? ";
			params.add(datastat);
		}
		hql += " order by c.msgCreateTime desc ";
		list = (List<PushMsg>) this.findListByHql(hql, params, start, size);
		return list;
	}
	/**
     *  条件查询出所有push消息的数目
     * 
     * @param  msgContent   消息的内容
     * @param  datastat   是否删除
     * @return  int类型的数目
     * @throws  
     * @see
     */	
	public int getPushCount(String msgContent,Integer datastat)
	{
		String hql="from PushMsg c where 1=1";
		List<Object> params = new ArrayList<Object>();
		if(null!=msgContent&&msgContent.length()>0)
		{
			hql += " and  c.msgContent like '%' || ? || '%' ";
    		params.add(msgContent);
		}
		if(null!=datastat)
		{
			hql += " and  c.datastat = ? ";
    		params.add(datastat);
		}
		return this.getRecordNumber(hql, params);
	}


	
	
	
}
