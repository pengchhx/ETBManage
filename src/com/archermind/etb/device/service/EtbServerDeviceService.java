package com.archermind.etb.device.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.device.dao.EtbServerDeviceDao;
import com.archermind.etb.device.domain.EtbServerDevice;

/**
 * 平台设备service类
 * 
 * @author 001667 梁伟
 * @version 1.0 2013-07-11
 * @since 1.0
 */
@Service("com.archermind.etb.device.service.EtbServerDeviceService")
public class EtbServerDeviceService
{
	/**
	 * 声明etbServerDeviceDao
	 */
	@Resource(name = "com.archermind.etb.device.dao.EtbServerDeviceDao")
	private EtbServerDeviceDao etbServerDeviceDao;

	/**
	 * 
	 * @param etbServerDevice
	 */
	public void saveDeviceInfo(EtbServerDevice etbServerDevice)
	{
		etbServerDeviceDao.saveOrUpdate(etbServerDevice);
	}

	/**
	 * 根据ID查找要修改的平台设备
	 * 
	 * @param id
	 * @return
	 */
	public EtbServerDevice updateDeviceInfo(int id)
	{
		return etbServerDeviceDao.findById(EtbServerDevice.class, id);
	}

	/**
	 * 获取设备信息Count
	 * @param name
	 * @return
	 */
	public int getDeviceInfoCount(String name)
	{
		return etbServerDeviceDao.getDeviceInfoCount(name);
	}

	/**
	 * 根据查询条件分页查询用户信息
	 * 
	 * @param params
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<EtbServerDevice> listUserInfoByPage(String name, int pageSize,
			int pageNum)
	{

		return etbServerDeviceDao.listDeviceInfoByPage(name, pageSize, pageNum);
	}
}
