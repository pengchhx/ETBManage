package com.archermind.etb.device.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.device.dao.EtbClientDeviceTimeDao;
import com.archermind.etb.device.domain.EtbClientDeviceTime;


@Service("com.archermind.etb.device.service.EtbClientDeviceTimeService")
public class EtbClientDeviceTimeService {

	
	
	@Resource(name = "com.archermind.etb.device.dao.EtbClientDeviceTimeDao")
	private EtbClientDeviceTimeDao etbClientDeviceTimeDao;

	
	

	public void deleteDeviceTimeList(Set<EtbClientDeviceTime> deviceTimeList ){
		etbClientDeviceTimeDao.deleteCollection(deviceTimeList);
		
		
	}
	
	
	
}
