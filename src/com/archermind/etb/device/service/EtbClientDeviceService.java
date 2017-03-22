package com.archermind.etb.device.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.device.dao.EtbClientDeviceDao;
import com.archermind.etb.device.domain.EtbClientDevice;
import com.archermind.etb.user.domain.ClientUserInfo;
import com.archermind.etb.util.Constant;

/**
 * 终端设备管理service类
 * 
 * @author 001667 梁伟
 * @version 1.0 2013-07-11
 * @since 1.0
 * 
 */
@Service("com.archermind.etb.device.service.EtbClientDeviceService")
public class EtbClientDeviceService {
	/**
	 * 声明etbClientDeviceDao
	 */
	@Resource(name = "com.archermind.etb.device.dao.EtbClientDeviceDao")
	private EtbClientDeviceDao etbClientDeviceDao;

	/**
	 * 新增设备信息
	 * 
	 * @param etbClientDevice
	 */
	public void saveDeviceInfo(EtbClientDevice etbClientDevice) {
		etbClientDeviceDao.saveOrUpdate(etbClientDevice);
	}

	/**
	 * 根据ID查询要修改的终端设备
	 * 
	 * @param id
	 * @return
	 */
	public EtbClientDevice updateDeviceInfo(String imei) {
		return etbClientDeviceDao.findById(EtbClientDevice.class, imei);
	}

	/**
	 * 获取设备信息Count
	 * 
	 * @param name
	 * @return
	 */
	public int getDeviceInfoCount(String imei) {
		return etbClientDeviceDao.getDeviceInfoCount(imei);
	}

	/**
	 * 根据查询条件分页查询用户信息
	 * 
	 * @param params
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<EtbClientDevice> listUserInfoByPage(String imei, int pageSize,
			int pageNum) {

		return etbClientDeviceDao.listDeviceInfoByPage(imei, pageSize, pageNum);
	}
	/**
	 * 终端设备监控 listMonitorDeviceInfo
	 */
	public List<EtbClientDevice> listMonitorDeviceInfo(String imei, int pageSize,
			int pageNum) {

		return etbClientDeviceDao.listMonitorDeviceInfo(imei, pageSize, pageNum);
	}
	/**
	 * 根据设备编号查询设备信息
	 * 
	 * @param imei
	 * @return
	 */
	public EtbClientDevice findDeviceInfoById(String imei) {
		return etbClientDeviceDao.findById(EtbClientDevice.class, imei);
	}

	/**
	 * 通过imei查找
	 * 
	 * @param imei
	 * @return
	 */
	public int findDeviceInfoByIdForUser(String imei) {
		EtbClientDevice etbClientDevice = etbClientDeviceDao.findById(
				EtbClientDevice.class, imei);
		if (null == etbClientDevice) {
			return 0;
		} else {
			Set<ClientUserInfo> set = etbClientDevice.getClientUserInfoList();
			if (null != set && set.size() > 0) {
				return set.size();
			} else {
				return 0;
			}
		}
	}

	/**
	 * 查询所有终端设备
	 */
	public List<EtbClientDevice> exportDeviceInfo() {
		return etbClientDeviceDao.exportDeviceInfo();
	}

	/**
	 * 批量导入终端设备
	 */
	public String[] saveImportDeviceInfo(List<Object> list) {
		return etbClientDeviceDao.saveImportDeviceInfo(list);
	}
	
	public List<EtbClientDevice> findClientDeviceByAreaId(int areaId){
		return etbClientDeviceDao.getClientDeviceByArea(areaId);
	}
	
	//-----------------------------------------------------------------------------------------

	
	/**
	 *根据公司查询其所有设备列表
	 */
	
	public List<EtbClientDevice> findClientDeviceByCompanyId(String imei,long companyId){
		return etbClientDeviceDao.getClientDeviceListByCompanyId(imei, companyId);
	}
	
	
	
	
	/**
	 *根据公司查询其设备列表 (分页)
	 */
	
	public List<EtbClientDevice> findClientDeviceByCompanyIdByPage(long companyId,String resolution,Integer areaId,int status, int pageSize,int pageNum){
		return etbClientDeviceDao.getClientDeviceListByCompanyIdByPage( companyId,resolution,areaId,status, pageSize, pageNum);
	}
	
	/**
	 *根据公司查询其设备列表的数量
	 */
	
	public int findClientDeviceCountByCompanyId(long companyId,String resolution,Integer areaId,int status){
		return etbClientDeviceDao.getClientDeviceCountByCompanyId(companyId,resolution,areaId,status);
	}
	
	
	/**
	 *删除某个设备
	 */
	
	public void deleteEtbClientDevice(String imei){
		EtbClientDevice etbClinetDevice=etbClientDeviceDao.findById(EtbClientDevice.class, imei);
		if(etbClinetDevice!=null){
			etbClinetDevice.setStat(Constant.DATA_STAT_OFF);
		}
		
	}
	
	/**
	 *批量删除设备
	 */
	
	public void deleteEtbClientDeviceBatch(String imei){
		String[] arr=imei.split("-");
		for(int i=0;i<arr.length;i++){
			EtbClientDevice etbClinetDevice=etbClientDeviceDao.findById(EtbClientDevice.class, arr[i]);
			if(etbClinetDevice!=null){
				etbClinetDevice.setStat(Constant.DATA_STAT_OFF);
			}
		}
	}
	
	
	/**
	 * 终端设备监控 listMonitorDeviceInfo
	 */
	public List<Object> getDeviceListByAreaOrResolution(int areaId, String resolution, int pageSize, int pageNum) {

		return etbClientDeviceDao.findDeviceBySomeAndPage(areaId, resolution, pageSize, pageNum);
	}
}
