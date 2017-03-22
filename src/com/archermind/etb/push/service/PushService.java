package com.archermind.etb.push.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.archermind.etb.push.dao.PushDao;
import com.archermind.etb.push.domain.PushMsg;

/**
 * 
 * 推送后台Service
 * 
 * @author 002582,keli.peng
 * @version 1.0 20130822
 * @see 
 * @since 1.0
 */
@Service("com.archermind.etb.push.service.PushService")
public class PushService {
	
	@Resource(name = "com.archermind.etb.push.dao.PushDao")
	private PushDao pushDao;

	/**
     *  条件查询出所有push消息
     * 
     * @param  msgContent   消息的内容
     * @param  datastat 是否已经删除    0否，1是
     * @param  start   分页参数当前页
     * @param  size    分页参数每页条数
     * @return  List<PushMsg>的泛型
     * @throws  
     * @see
     */
	public List<PushMsg> getPushInfoList(String msgContent,Integer datastat,Integer start, Integer size)
	{
		return this.pushDao.getPushInfoList(msgContent, datastat, start, size);
	}
	
	/**
     *  条件查询出所有push消息的数目
     * 
     * @param  msgContent   消息的内容
     * @param  datastat   是否删除
     * @return  int类型的数目
     * @throws  
     * @see
     */	
	public int getOtaCount(String msgContent,Integer datastat)
	{
		return this.pushDao.getPushCount(msgContent, datastat);
	}
	/**
     *  添加msg到数据库
     * 
     * @param  pushmsg   消息的内容
     * @see
     */	
	public  void addPush(PushMsg pushmsg)
	{
		this.pushDao.save(pushmsg);
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
