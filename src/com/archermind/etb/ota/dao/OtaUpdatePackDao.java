package com.archermind.etb.ota.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.ota.domain.OtaUpdatePack;
/**
 * 
 * 版本升级包实体的dao层
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130711
 * @see 
 * @since 1.0
 */
@Repository("com.archermind.etb.ota.dao.OtaUpdatePackDao")
public class OtaUpdatePackDao extends BaseDao<OtaUpdatePack>
{
	
	/**
     * 根据版本名称查询出升级包信息
     * @param  name   目标版本名称
     * @param  datastat  是否删除：0，存在  1，删除
     * @return  List<OtaUpdatePack>
     * @throws  
     * @see
     */	
	public List<OtaUpdatePack> getVersionUpdatePack(String name,Integer datastat)
	{
		List<OtaUpdatePack> list=new ArrayList<OtaUpdatePack>();
		String hql = " from OtaUpdatePack c where 1=1";
		List<Object> params = new ArrayList<Object>();
		if(null!=name&&!"".equals(name))
		{
			hql += " and c.targetname= ?";
			params.add(name);
		}
		if(null!=datastat)
		{
			hql += " and  c.datastat = ? ";
    		params.add(datastat);
		}
		list = (List<OtaUpdatePack>) super.findListByHql(hql, params,null,null);
		return list;
	}
	/**
     *  根据名称查询当前版本下完整包或差分包的数量
     * 
     * @param  targetname   版本名称
     * @param  packtype   0：完整包 ，1：差分包
     * @param  datastat   是否删除
     * @return  int类型的数目
     * @throws  
     * @see
     */	
	public int getPackCount(String targetname,Integer packtype,Integer datastat)
	{
		String hql="from OtaUpdatePack c where 1=1";
		List<Object> params = new ArrayList<Object>();
		if(null!=targetname&&targetname.length()>0)
		{
			hql += " and  c.targetname = ? ";
    		params.add(targetname);
		}
		if(null!=packtype)
		{
			hql += " and  c.packtype = ? ";
    		params.add(packtype);
		}
		if(null!=datastat)
		{
			hql += " and  c.datastat = ? ";
    		params.add(datastat);
		}
		return this.getRecordNumber(hql, params);
	}
	/**
     *  根据id查询出唯一的升级包实体
     * 
     * @param  id 升级包的唯一标识id
     * @return  OtaUpdatePack实体
     * @throws  
     * @see
     */	
	public OtaUpdatePack getPackById(Integer id)
	{
		OtaUpdatePack info=this.findById(OtaUpdatePack.class, id);
		return info;
	}
	/**
     *  查询升级包的名称是否唯一
     * 
     * @param  packname   名称,必须存在
     * @param  datastat  是否存在的状态值  0，存在   1，删除
     * @return  boolean 存在返回false，不存在返回true
     * @throws  
     * @see
     */	
	public boolean packnameIsExist(String packname,Integer datastat)
	{
		String hql="from OtaUpdatePack c where c.packname = ?";
		List<Object> params = new ArrayList<Object>();
        params.add(packname);
		if(null!=datastat)
		{
			hql += " and  c.datastat = ? ";
    		params.add(datastat);
		}
        int i= this.getRecordNumber(hql, params);
        if(i>0) return false;
        else return true;
	}

}
