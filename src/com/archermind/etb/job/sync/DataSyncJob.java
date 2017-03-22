package com.archermind.etb.job.sync;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessResourceFailureException;

import com.archermind.etb.push.domain.PushMsg;
import com.archermind.etb.redis.service.RedisService;


public class DataSyncJob {

	@Resource(name = "com.archermind.etb.redis.service.RedisService")
	private RedisService redisService;
	
	public void sync() {  
	    System.out.println("******************** START REDIS DATA SYNC ********************");
	    // Count
	    long count = 0;
	    long begin = new Date().getTime();
	    
	    List<PushMsg> list = new ArrayList<PushMsg>();
		// Read data from redis
		Map<String,String> flowerMap = redisService.getFlowers();
		for(Entry<String,String> en : flowerMap.entrySet()){
			PushMsg msg = new PushMsg();
			msg.setId(en.getKey());
			msg.setDeadLine(null);
			msg.setMsgContent(en.getValue());
			msg.setMsgCreater(null);
			msg.setMsgCreateTime(null);
			msg.setStat(0);
			
			list.add(msg);
			count++;
		}
		
		// Write to MySQL DB
		try {
			redisService.backupPushMsg(list);
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Count
		long end = new Date().getTime();
		System.out.println("TOTAL COUNT: " + count + " ITEMS");
		long costMils = end - begin;
		long cost; 
		String timeUnit;
		if(costMils > 1000){
			cost = costMils/1000;
			timeUnit = "seconds";
		} else {
			cost = costMils;
			timeUnit = "milliseconds";
		}
		
		System.out.println("COST TIME: " + cost + " " + timeUnit);
		System.out.println("******************** FINISH REDIS DATA SYNC ********************");
	}  
}
