package com.archermind.etb.device.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.archermind.etb.device.dao.EtbClientDeviceDao;
import com.archermind.etb.device.dao.EtbClientDevicePropertyDao;
import com.archermind.etb.device.domain.EtbClientDeviceProperty;




@Service("com.archermind.etb.device.service.EtbClientDevicePropertyService")
public class EtbClientDevicePropertyService {

	
	@Resource(name = "com.archermind.etb.device.dao.EtbClientDevicePropertyDao")
	private EtbClientDevicePropertyDao etbClientDevicePropertyDao;

	
	
	public void saveOrUpdate(EtbClientDeviceProperty deviceProperty){
		
		etbClientDevicePropertyDao.saveOrUpdate(deviceProperty);
		
	}
	
	
	
	
}
