package com.archermind.etb.push.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import com.archermind.etb.common.UUIDGenerator;
import com.archermind.etb.push.dao.PushDao;
import com.archermind.etb.push.domain.PushMsg;
import com.archermind.etb.redis.service.RedisService;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.DateUtil;
import com.archermind.etb.util.JsonHelper;
import com.archermind.etb.util.PropertyUtil;
import com.archermind.etb.util.WsClientUtil;


@Service("com.archermind.etb.push.service.PushToClient")
@SuppressWarnings("all")
public class PushToClient {
	
	@Resource(name = "com.archermind.etb.redis.service.RedisService")
	private RedisService redisService;
	
	@Resource(name = "com.archermind.etb.push.dao.PushDao")
	private PushDao pushDao;
	
	/**
     *  消息推送接口,推送给指定用户
     * @param  msg   消息的内容
     * @param  isToAll  是否推送给所有人，true是全体推送，false推送部分
     * @param  users    用户列表
     * @throws  
     * @see
     */	
	public  void addToClientUser(String msg,Date time,boolean isToAll, Set<String> users)
	{
		if(null!=users&&users.size()>0)
		{
			HashMap map =new HashMap();
			map.put("type", 5);
			map.put("action", 0);
			map.put("msg", msg);
			String pushMsg =JsonHelper.getGson().toJson(map);
			HashMap<String,String> pushMap = new HashMap<String,String>();
			String key = UUIDGenerator.getUUID();
			pushMap.put(key, pushMsg);
			//保存redis
			this.redisService.addFlowers(pushMap);
			this.redisService.addTargetDevices(key, users);
			String executetime = DateUtil.getDateYYYY_MM_DD(time);
			
			//保存mysql
			PushMsg sqlMsg = new PushMsg();
			sqlMsg.setId(key);
			sqlMsg.setMsgContent(msg);
			Subject subject = SecurityUtils.getSubject();
			//设置创建人和创建时间
			sqlMsg.setMsgCreater(String.valueOf(subject.getSession()
					.getAttribute(Constant.SESSION_USER_NAME)));
			sqlMsg.setDeadLine(time);
			sqlMsg.setMsgCreateTime(new Date());
			this.pushDao.save(sqlMsg);
			
			String wsdl = PropertyUtil.readData("webservice_wsdl");
			WsClientUtil.callCXFWservice(wsdl,Constant.NOTIFY_NEW_MESSAGE_FOR_TIME,new Object[]{key,
					msg,isToAll,executetime});
		}
	}
	/**
     *  消息推送接口,推送给全体用户
     * @param  msg   消息的内容
     * @throws  
     * @see
     */	
	public void pushToClient(String msg,Date time)
	{
		HashMap map =new HashMap();
		map.put("type", 5);
		map.put("action", 0);
		map.put("msg", msg);
		String pushMsg =JsonHelper.getGson().toJson(map);
		String key = UUIDGenerator.getUUID();
		String executetime = DateUtil.getDateYYYY_MM_DD(time);
		
		//保存redis
		HashMap<String,String> pushMap = new HashMap<String,String>();
		pushMap.put(key, pushMsg);
		this.redisService.addFlowers(pushMap);
		//保存mysql
		PushMsg sqlMsg = new PushMsg();
		sqlMsg.setId(key);
		sqlMsg.setMsgContent(msg);
		Subject subject = SecurityUtils.getSubject();
		//设置创建人和创建时间
		sqlMsg.setMsgCreater(String.valueOf(subject.getSession()
				.getAttribute(Constant.SESSION_USER_NAME)));
		sqlMsg.setDeadLine(time);
		sqlMsg.setMsgCreateTime(new Date());
		this.pushDao.save(sqlMsg);
		
		String wsdl = PropertyUtil.readData("webservice_wsdl");
		WsClientUtil.callCXFWservice(wsdl,Constant.NOTIFY_NEW_MESSAGE_FOR_TIME,new Object[]{key,
				pushMsg,true,executetime});
	}
	
	

	public RedisService getRedisService() 
	{
		return redisService;
	}
	public void setRedisService(RedisService redisService) 
	{
		this.redisService = redisService;
	}
	
	public PushDao getPushDao() 
	{
		return pushDao;
	}
	public void setPushDao(PushDao pushDao) 
	{
		this.pushDao = pushDao;
	}
}
