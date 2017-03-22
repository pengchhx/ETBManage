package com.archermind.etb.ota.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.ota.dao.OtaUpdatePackDao;
import com.archermind.etb.ota.dao.OtaVersionDao;
import com.archermind.etb.ota.domain.OtaUpdatePack;
import com.archermind.etb.ota.domain.OtaVersionInfo;
import com.archermind.etb.util.Constant;


/**
 * 
 * ota升级包service的增删改查
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130711
 * @see 
 * @since 1.0
 */
@Service("com.archermind.etb.ota.service.OtaUpdatepackService")
public class OtaUpdatepackService {

	@Resource(name = "com.archermind.etb.ota.dao.OtaUpdatePackDao")
	private OtaUpdatePackDao otaUpdatePackDao;
	@Resource(name = "com.archermind.etb.ota.dao.OtaversionDao")
	private OtaVersionDao otaVersionDao;

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
		return otaUpdatePackDao.getVersionUpdatePack(name,datastat);
	}
	/**
     *  根据名称查询当前版本下完整包或差分包的数量
     * 
     * @param  targetname   版本名称
     * @param  packtype   0：完整包 ，1：差分包， null：完整包+差分包
     * @param  datastat   是否删除
     * @return  int类型的数目
     * @throws  
     * @see
     */	
	public int getPackCount(String targetname,Integer packtype,Integer datastat)
	{
		return otaUpdatePackDao.getPackCount(targetname, packtype, datastat);
	}
	/**
     *  添加ota版本的升级包
     * @param  pack hibernate映射实体
     * @throws  
     * @see
     */	
	public void addUpdatepack(OtaUpdatePack pack)
	{
		this.otaUpdatePackDao.save(pack);
	}
	/**
     *  删除升级包
     * @param  pack hibernate映射实体,只做逻辑删除，改datastat为1
     * @throws  
     * @see
     */	
	public void deletePack(OtaUpdatePack pack)
	{
		otaUpdatePackDao.update(pack);
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
		return otaUpdatePackDao.getPackById(id);
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
		return otaUpdatePackDao.packnameIsExist(packname, datastat);
	}
	public OtaUpdatePackDao getOtaUpdatePackDao() {
		return otaUpdatePackDao;
	}
	public void setOtaUpdatePackDao(OtaUpdatePackDao otaUpdatePackDao) {
		this.otaUpdatePackDao = otaUpdatePackDao;
	}
	public OtaVersionDao getOtaVersionDao() {
		return otaVersionDao;
	}
	public void setOtaVersionDao(OtaVersionDao otaVersionDao) {
		this.otaVersionDao = otaVersionDao;
	}
	
}
