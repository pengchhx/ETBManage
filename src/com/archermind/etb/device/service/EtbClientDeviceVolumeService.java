package com.archermind.etb.device.service;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.archermind.etb.device.dao.EtbClientDeviceVolumeDao;

import com.archermind.etb.device.domain.EtbClientDeviceVolume;


@Service("com.archermind.etb.device.service.EtbClientDeviceVolumeService")
public class EtbClientDeviceVolumeService {
	
	
	
	
	
	@Resource(name = "com.archermind.etb.device.dao.EtbClientDeviceVolumeDao")
	private EtbClientDeviceVolumeDao etbClientDeviceVolumeDao;

	
	

	public void deleteDeviceVolumeList(Set<EtbClientDeviceVolume> deviceVolumeList ){
		etbClientDeviceVolumeDao.deleteCollection(deviceVolumeList);
		
		
	}
	
	
	

}
