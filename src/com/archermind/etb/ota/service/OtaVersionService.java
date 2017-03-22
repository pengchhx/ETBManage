package com.archermind.etb.ota.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.ota.dao.OtaVersionDao;
import com.archermind.etb.ota.domain.OtaVersionInfo;
import com.archermind.etb.util.Constant;


@Service("com.archermind.etb.ota.service.OtaversionService")
/**
 * 
 * ota模块service的增删改查
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130711
 * @see 
 * @since 1.0
 */
public class OtaVersionService {
	
	@Resource(name = "com.archermind.etb.ota.dao.OtaversionDao")
	private OtaVersionDao otaVersionDao;

	/**
     *  条件查询出所有ota
     * 
     * @param  name   ota的版本名称
     * @param  infostat 0：新添加待审核 ，1：审核通过，2：审核不通过，3：已修改待审核，4：已发布
     * @param  datastat 是否已经删除   0否，1是
     * @param  start   分页参数当前页
     * @param  size    分页参数每页条数
     * @return  List<Etbotaversioninfo>的泛型
     * @see
     */
	public List<OtaVersionInfo> getOtaVersionInfoList(String name,Integer infostat,Integer datastat,Integer start, Integer size)
	{
		return otaVersionDao.getOtaVersionInfoList(name,infostat,datastat,start,size);
	}
	/**
     *  条件查询出所有ota的数目
     * 
     * @param  name   ota名称
     * @param  infostat  0：新添加待审核 ，1：审核通过，2：审核不通过，3：已修改待审核，4：已发布
     * @param  datastat   是否删除
     * @param  tip   ota备注
     * @return  int类型的数目
     * @see
     */	
	public int getOtaCount(String name,Integer infostat,Integer datastat,String tip)
	{
		return otaVersionDao.getOtaCount(name,infostat,datastat,tip);
	}
	/**
     *  删除ota
     * 
     * @param  ota hibernate映射实体,只做逻辑删除，改datastat为1
     * @return  boolean，true表示删除成功，false失败
     * @see
     */	
	public boolean deleteOtaversioninfo(OtaVersionInfo ota)
	{
		ota.setDatastat(Constant.DATA_STAT_OFF);	//逻辑删除
		return otaVersionDao.deleteOtaversioninfo(ota);
	}
	/**
     *  根据id查询出唯一的ota实体
     * 
     * @param  id ota的唯一标识id
     * @return  Etbotaversioninfo实体
     * @see
     */	
	public OtaVersionInfo getOtaInfoById(Integer id)
	{
		return otaVersionDao.getOtaInfoById(id);
	}
	/**
     *  根据名称查询出唯一的ota实体
     * 
     * @param  name ota的唯一标识名称
     * @return  Etbotaversioninfo实体
     * @see
     */	
	public OtaVersionInfo getOtaInfoByName(String name)
	{
		List<OtaVersionInfo> list = otaVersionDao.getOtaListByName(name, Constant.DATA_STAT_ON);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}else
		{
			return null;
		}
	}
	/**
     *  添加ota
     * 
     * @param  ota hibernate映射实体
     * @see
     */		
	public void saveOta(OtaVersionInfo ota)
	{
		otaVersionDao.save(ota);
	}

	/**
     *  修改OTA信息
     * 
     * @param  ota hibernate映射实体
     * @see
     */	
	public void updateOta(OtaVersionInfo ota)
	{
		otaVersionDao.update(ota);
	}
	/**
     *  条件查询出所有待审核和修改待审核的ota的数目
     * 
     * @param  name   ota名称
     * @param  datastat   是否删除
     * @param  tip   ota备注
     * @return  int类型的数目
     * @see
     */	
	public int getPreAuditOtaCount(String name,Integer datastat,String tip)
	{
		return otaVersionDao.getPreAuditOtaCount(name, datastat, tip);
	}
	/**
     *  条件查询出所有待审核和修改待审核的ota
     * 
     * @param  name   ota的版本名称
     * @param  datastat 是否已经删除    0否，1是
     * @param  start   分页参数当前页
     * @param  size    分页参数每页条数
     * @return  List<Etbotaversioninfo>的泛型
     * @see
     */
	public List<OtaVersionInfo> getPreAuditInfoList(String name,Integer datastat,Integer start, Integer size)
	{
		return otaVersionDao.getPreAuditInfoList(name, datastat, start, size);
	}
	
	/**
     *  查询ota的名称是否唯一
     * 
     * @param  name   名称,必须存在
     * @param  datastat  是否存在的状态值  0，存在   1，删除
     * @param  otaId  ota的id,进行比对
     * @return  boolean 存在返回false，不存在返回true
     * @see
     */	
	public boolean nameIsExist(String name,Integer datastat,int otaId){
		List<OtaVersionInfo> list = otaVersionDao.getOtaListByName(name, datastat);
		if(null!=list && list.size()>0){
			if(otaId!=0){
				if(list.size()==1){
					OtaVersionInfo ota = list.get(0);
					if(ota.getId()==otaId){
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}
	/**
     *  条件查询出所有审核通过或者已发布的ota的数目
     *  发布列表
     * @param  name   ota名称
     * @param  datastat   是否删除
     * @param  tip   ota备注
     * @return  int类型的数目
     * @see
     */	
	public int getReleaseOtaCount(String name,Integer datastat,String tip)
	{
		return otaVersionDao.getReleaseOtaCount(name, datastat, tip);
	}
	/**
     *  条件查询出所有审核通过或者已发布的ota
     * 
     * @param  name   ota的版本名称
     * @param  datastat 是否已经删除    0否，1是
     * @param  start   分页参数当前页
     * @param  size    分页参数每页条数
     * @return  List<Etbotaversioninfo>的泛型
     * @see
     */
	public List<OtaVersionInfo> getReleaseInfoList(String name,Integer datastat,Integer start, Integer size)
	{
		return otaVersionDao.getReleaseInfoList(name, datastat, start, size);
	}
	
	/**
     *  查询出当前系统中最大的build版本号
     * @return  List<OtaVersionInfo>的泛型
     * @see
     */
	public int getMaxBuildVersion(){
		return this.otaVersionDao.getMaxBuildVersion();
	}
	/**
     *  查询出当前版本向下4个差分的初始版本名称
     * @param maxBuildVersion  当前版本的build版本
     * @return  List<String>的泛型
     */
	public List<String> getUpdateVersionName(Integer maxBuildVersion)
	{
		int minBuildVersion = maxBuildVersion-5;
		List<OtaVersionInfo> list = this.otaVersionDao.getFourUpdateVersion(minBuildVersion, maxBuildVersion);
		List<String> listName=new ArrayList<String>();
		if(null!=list&&list.size()>0)
		{
			for (OtaVersionInfo ota : list) {
				listName.add(ota.getName());
			}
		}
		return listName;
	}
	/**
	 * 是否存在没有发布的OTA版本
	 * @return true存在，false不存在
	 */
	public boolean havaUnReleaseOta()
	{
		List<OtaVersionInfo> list =this.otaVersionDao.getUnReleaseOta();
		if(null!=list&&list.size()>0)return true;
		else return false;
	}
	
	public OtaVersionDao getOtaVersionDao() {
		return otaVersionDao;
	}
	public void setOtaVersionDao(OtaVersionDao otaVersionDao) {
		this.otaVersionDao = otaVersionDao;
	}
}
