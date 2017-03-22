package com.archermind.etb.ota.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.ota.domain.OtaVersionInfo;
import com.archermind.etb.util.Constant;


/**
 * 
 * ota模块dao层的增删改查
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130711
 * @see 
 * @since 1.0
 */
@Repository("com.archermind.etb.ota.dao.OtaversionDao")
public class OtaVersionDao extends BaseDao<OtaVersionInfo>{
	
	/**
     *  条件查询出所有ota
     * 
     * @param  name   ota的版本名称
     * @param  infostat 0：新添加待审核 ，1：审核通过，2：审核不通过，3：已修改待审核，4：已发布
     * @param  datastat 是否已经删除    0否，1是
     * @param  start   分页参数当前页
     * @param  size    分页参数每页条数
     * @return  List<OtaVersionInfo>的泛型
     * @throws  
     * @see
     */
	public List<OtaVersionInfo> getOtaVersionInfoList(String name,Integer infostat,Integer datastat,Integer start, Integer size)
	{
		List<OtaVersionInfo> list=new ArrayList<OtaVersionInfo>();

		String hql = " from OtaVersionInfo c where 1=1";
		List<Object> params = new ArrayList<Object>();
		if(null!=name&&name.length()>0)
		{
			hql += " and  c.name like '%' || ? || '%' ";
			params.add(name);
		}
		if(null!=infostat)
		{
			hql += " and  c.infostat= ? ";
			params.add(infostat);
		}
		if(null!=datastat)
		{
			hql += " and  c.datastat= ? ";
			params.add(datastat);
		}
		hql += " order by c.id desc ";
		list = (List<OtaVersionInfo>) this.findListByHql(hql, params, start, size);
		return list;
	}
	/**
     *  删除ota
     * 
     * @param  ota hibernate映射实体,之力只做逻辑删除，改datastat未1
     * @return  boolean，true表示删除成功，false失败
     * @throws  
     * @see
     */	
	public boolean deleteOtaversioninfo(OtaVersionInfo ota)
	{
		try
		{
			super.update(ota);
			return true;
		}catch(Exception ex)
		{
			return false;
		}
		
	}
	/**
     *  根据id查询出唯一的ota实体
     * 
     * @param  id ota的唯一标识id
     * @return  OtaVersionInfo实体
     * @throws  
     * @see
     */	
	public OtaVersionInfo getOtaInfoById(Integer id)
	{
		OtaVersionInfo otainfo=this.findById(OtaVersionInfo.class, id);
		return otainfo;
	}
	/**
     *  条件查询出所有ota的数目
     * 
     * @param  name   ota名称
     * @param  infostat   0：新添加待审核 ，1：审核通过，2：审核不通过，3：已修改待审核，4：已发布
     * @param  datastat   是否删除
     * @param  tip   ota备注
     * @return  int类型的数目
     * @throws  
     * @see
     */	
	public int getOtaCount(String name,Integer infostat,Integer datastat,String tip)
	{
		String hql="from OtaVersionInfo c where 1=1";
		List<Object> params = new ArrayList<Object>();
		if(null!=name&&name.length()>0)
		{
			hql += " and  c.name like '%' || ? || '%' ";
    		params.add(name);
		}
		if(null!=infostat)
		{
			hql += " and  c.infostat = ? ";
    		params.add(infostat);
		}
		if(null!=datastat)
		{
			hql += " and  c.datastat = ? ";
    		params.add(datastat);
		}
		if(null!=tip&&tip.length()>0)
		{
			hql += " and  c.tips like '%' || ? || '%' ";
    		params.add(tip);
		}
		return this.getRecordNumber(hql, params);
	}
	/**
     *  查询ota的名称查询列表
     * 
     * @param  name   名称,必须存在
     * @param  datastat  是否存在的状态值  0，存在   1，删除
     * @return   List<OtaVersionInfo>泛型
     * @throws  
     * @see
     */	
	public List<OtaVersionInfo> getOtaListByName(String name,Integer datastat)
	{
		String hql="from OtaVersionInfo c where c.name = ?";
		List<Object> params = new ArrayList<Object>();
        params.add(name);
		if(null!=datastat)
		{
			hql += " and  c.datastat = ? ";
    		params.add(datastat);
		}
        return this.findListByHql(hql, params, null, null);
	}
	
	/**
     *  条件查询出所有待审核和修改待审核的ota
     * 
     * @param  name   ota的版本名称
     * @param  datastat 是否已经删除    0否，1是
     * @param  start   分页参数当前页
     * @param  size    分页参数每页条数
     * @return  List<OtaVersionInfo>的泛型
     * @throws  
     * @see
     */
	public List<OtaVersionInfo> getPreAuditInfoList(String name,Integer datastat,Integer start, Integer size)
	{
		List<OtaVersionInfo> list=new ArrayList<OtaVersionInfo>();

		String hql = " from OtaVersionInfo c where 1=1 and c.infostat="+Constant.OTA_VER_INFO_STATUS_SUBMIT+" ";
		List<Object> params = new ArrayList<Object>();
		if(null!=name&&name.length()>0)
		{
			hql += " and  c.name like '%' || ? || '%' ";
			params.add(name);
		}
		if(null!=datastat)
		{
			hql += " and  c.datastat= ? ";
			params.add(datastat);
		}
		hql += " order by c.id desc ";
		list = (List<OtaVersionInfo>) this.findListByHql(hql, params, start, size);
		return list;
	}
	/**
     *  条件查询出所有待审核ota的数目
     * 
     * @param  name   ota名称
     * @param  datastat   是否删除
     * @param  tip   ota备注
     * @return  int类型的数目
     * @throws  
     * @see
     */	
	public int getPreAuditOtaCount(String name,Integer datastat,String tip)
	{ 
		String hql="from OtaVersionInfo c where 1=1 and (c.infostat="+Constant.OTA_VER_INFO_STATUS_CREATE+" or c.infostat="+Constant.OTA_VER_INFO_STATUS_UPDATED+")";
		List<Object> params = new ArrayList<Object>();
		if(null!=name&&name.length()>0)
		{
			hql += " and  c.name like '%' || ? || '%' ";
    		params.add(name);
		}

		if(null!=datastat)
		{
			hql += " and  c.datastat = ? ";
    		params.add(datastat);
		}
		if(null!=tip&&tip.length()>0)
		{
			hql += " and  c.tips like '%' || ? || '%' ";
    		params.add(tip);
		}
		return this.getRecordNumber(hql, params);
	}
	/**
     *  条件查询出所有审核通过或者已发布的ota的数目
     *  发布列表
     * 
     * @param  name   ota名称
     * @param  datastat   是否删除
     * @param  tip   ota备注
     * @return  int类型的数目
     * @throws  
     * @see
     */	
	public int getReleaseOtaCount(String name,Integer datastat,String tip)
	{ 
		String hql="from OtaVersionInfo c where 1=1 and (c.infostat="+Constant.OTA_VER_INFO_STATUS_CHECKED+" or c.infostat="+Constant.OTA_VER_INFO_STATUS_PUBLISHED+")";
		List<Object> params = new ArrayList<Object>();
		if(null!=name&&name.length()>0)
		{
			hql += " and  c.name like '%' || ? || '%' ";
    		params.add(name);
		}

		if(null!=datastat)
		{
			hql += " and  c.datastat = ? ";
    		params.add(datastat);
		}
		if(null!=tip&&tip.length()>0)
		{
			hql += " and  c.tips like '%' || ? || '%' ";
    		params.add(tip);
		}
		return this.getRecordNumber(hql, params);
	}
	/**
     *  条件查询出所有审核通过或者已发布的ota
     * 
     * @param  name   ota的版本名称
     * @param  datastat 是否已经删除    0否，1是
     * @param  start   分页参数当前页
     * @param  size    分页参数每页条数
     * @return  List<OtaVersionInfo>的泛型
     * @throws  
     * @see
     */
	public List<OtaVersionInfo> getReleaseInfoList(String name,Integer datastat,Integer start, Integer size)
	{
		List<OtaVersionInfo> list=new ArrayList<OtaVersionInfo>();

		String hql = " from OtaVersionInfo c where 1=1 and (c.infostat="+Constant.OTA_VER_INFO_STATUS_CHECKED+" or c.infostat="+Constant.OTA_VER_INFO_STATUS_PUBLISHED+")";
		List<Object> params = new ArrayList<Object>();
		if(null!=name&&name.length()>0)
		{
			hql += " and  c.name like '%' || ? || '%' ";
			params.add(name);
		}
		if(null!=datastat)
		{
			hql += " and  c.datastat= ? ";
			params.add(datastat);
		}
		hql += " order by c.id desc ";
		list = (List<OtaVersionInfo>) this.findListByHql(hql, params, start, size);
		return list;
	}
	/**
     *  查询出当前系统中最大的build版本号
     * @return  int 版本号
     * @see
     */
	public int getMaxBuildVersion()
	{
		String hql = "select max(buildversion)  from OtaVersionInfo where infostat="+Constant.OTA_VER_INFO_STATUS_PUBLISHED+"  and datastat="+Constant.DATA_STAT_ON;
		List list = this.find(hql, null);
		if(null != list && list.size() > 0){
			if(null == list.get(0))
			{
				//没有最大值，那么当前版本build编号为0
				return 0;
			}else
			{
				return (Integer)list.get(0);
			}
		}else
		{
			//没有最大值，那么当前版本build编号为0
			return 0;
		}
		
	}
	
	/**
     *  查询出当前版本向下4个差分的初始版本
     * @param maxVersionId  当前版本的id
     * @return  List<OtaVersionInfo>的泛型
     */
	public List<OtaVersionInfo> getFourUpdateVersion(Integer minBuildVersion,Integer maxBuildVersion)
	{
		List<OtaVersionInfo> list=new ArrayList<OtaVersionInfo>();
		String hql = " from OtaVersionInfo c where 1=1 and c.datastat=0 and c.buildversion > "+minBuildVersion+" and c.buildversion < "+maxBuildVersion+"";
		
		list = (List<OtaVersionInfo>) this.findListByHql(hql, null, null, null);
		return list;
	}
	/**
	 * 查询出所有没有发布的ota版本
	 * @return List<OtaVersionInfo>的泛型
	 */
	public List<OtaVersionInfo> getUnReleaseOta()
	{
		List<OtaVersionInfo> list=new ArrayList<OtaVersionInfo>();
		String hql = " from OtaVersionInfo c where c.infostat!="+Constant.OTA_VER_INFO_STATUS_PUBLISHED+" and c.datastat="+Constant.DATA_STAT_ON+" ";
		
		list = (List<OtaVersionInfo>) this.findListByHql(hql, null, null, null);
		return list;
	}
	
}
