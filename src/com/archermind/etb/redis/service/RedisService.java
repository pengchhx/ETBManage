package com.archermind.etb.redis.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import com.archermind.etb.push.domain.PushMsg;
import com.archermind.etb.redis.dao.RedisBackupDao;
import com.archermind.etb.redis.dao.RedisDao;
import com.archermind.etb.util.PushConstant;
/**
 * Redis逻辑服务层
 * @author	zc 002538 2013/08/28
 * @since	1.0
 */
@Service("com.archermind.etb.redis.service.RedisService")
public class RedisService {

	@Resource(name = "com.archermind.etb.redis.dao.RedisDao")
	private RedisDao redisDao;
	
	@Resource(name = "com.archermind.etb.redis.dao.RedisBackupDao")
	private RedisBackupDao redisBackupDao;
	
	/**
	 * 在Redis中查找TOKEN
	 * @param key 根据deviceId在Redis中查找对应的TOKEN
	 * @return
	 */
	public String getToken(String key){
		return redisDao.getAsString(key);
	}
	
	/**
	 * 存入一组TOKEN键值对
	 * @param key
	 * @param token
	 */
	public void addToken(String key, String token){
		redisDao.setString(key, token);
	}
	
	/**
	 * 根据流水号查询目标设备列表
	 * @param	key
	 * @return	成功：Map<String, String> | 失败：NULL
	 */
	public Set<String> getTargetDevices(String key){
		if(key!=null && key.indexOf(PushConstant.FLOWER_USER_KEY_PREFIX)==0) {
			//传入的key必须非空并且以"Flower_"开头
			return redisDao.getAsSet(key);
		} else {
			//如果格式不符合要求，直接返回NULL
			return null;
		}
	}
	
	/**
	 * 向Redis中设置目标设备列表
	 * @param key
	 * @param targets
	 * @return	成功：true| KEY格式不合法：false
	 */
	public boolean addTargetDevices(String key, Set<String> targets){
		if(key!=null && key.indexOf(PushConstant.FLOWER_USER_KEY_PREFIX)==0) {
			//传入的key必须非空并且以"Flower_"开头
			redisDao.addSet(key, targets);
			return true;
		} else {
			//如果格式不符合要求，返回FALSE
			return false;
		}
	}
	
	/**
	 * 根据流水号查询PUSH执行时间列表
	 * @param	key
	 * @return	成功：Map<String, String> | 失败：NULL
	 */
	public Map<String, String> getExecuteTimes(String key){
		if(key!=null && key.indexOf(PushConstant.CHUNKSERVER_FLOWER_EXCUTETIME__PREFIX)==0) {
			//传入的key必须非空并且以"Excutetime_"开头
			return redisDao.getAsMap(key);
		} else {
			//如果格式不符合要求，直接返回NULL
			return null;
		}
	}
	
	/**
	 * 向Redis中设置PUSH执行时间列表
	 * @param key
	 * @param targets
	 * @return	成功：true| KEY格式不合法：false
	 */
	public boolean addExecuteTimes(String key, Map<String, String> targets){
		if(key!=null && key.indexOf(PushConstant.CHUNKSERVER_FLOWER_EXCUTETIME__PREFIX)==0) {
			//传入的key必须非空并且以"Excutetime_"开头
			redisDao.addMap(key, targets);
			return true;
		} else {
			//如果格式不符合要求，返回FALSE
			return false;
		}
	}
	
	/**
	 * 查询PUSH流水信息列表
	 * @param	key
	 * @return	成功：Map<String, String> | 失败：NULL
	 */
	public Map<String, String> getFlowers(){
		return redisDao.getAsMap(PushConstant.FLOWER_STORE_KEY_NAME);
	}
	
	/**
	 * 向Redis中批量设置PUSH流水信息
	 * @param key
	 * @param targets
	 * @return	成功：true| KEY格式不合法：false
	 */
	public boolean addFlowers(Map<String, String> targets){
		// save to redis cache
		redisDao.addMap(PushConstant.FLOWER_STORE_KEY_NAME, targets);
		return true;
	}
	
	/**
	 * 添加PUSH流水信息
	 * @param pmsg
	 */
	public void addFlower(PushMsg pmsg){
		redisDao.addToMap(PushConstant.FLOWER_STORE_KEY_NAME, String.valueOf(pmsg.getId()), pmsg.getMsgContent());
	}
	
	/**
	 * 获取在线设备列表
	 * @param	key	- Online_ChunkServerIP
	 * @return	成功：Map<String, String> | 失败：NULL
	 */
	public Set<String> getOnlineDevices(String key){
		if(key!=null && key.indexOf(PushConstant.ONLINE_USER_KEY_PREFIX)==0) {
			//传入的key必须非空并且以"Online_"开头
			return redisDao.getAsSet(key);
		} else {
			//如果格式不符合要求，直接返回NULL
			return null;
		}
	}
	/**
	 * 获取在线离线状态的设备
	 */
	
	/**
	 * 向在线设备列表中添加新的在线设备（建立长连接时使用）
	 * @param	key	- Online_ChunkServerIP
	 * @param	deviceId
	 * @return	成功：true| KEY格式不合法：false
	 */
	public boolean addOnlineDevice(String key, String deviceId){
		if(key!=null && key.indexOf(PushConstant.ONLINE_USER_KEY_PREFIX)==0) {
			//传入的key必须非空并且以"Online_"开头
			return redisDao.addToSet(key, deviceId);
		} else {
			//如果格式不符合要求，返回FALSE
			return false;
		}
	}
	
	/**
	 * 定时备份
	 * @param list 以PushMsg实例为元数据的List
	 * @throws DataAccessResourceFailureException
	 * @throws HibernateException
	 * @throws IllegalStateException
	 * @throws SQLException
	 */
	public void backupPushMsg(List<PushMsg> list) throws DataAccessResourceFailureException, HibernateException, IllegalStateException, SQLException{
		redisBackupDao.backupPushMsg(list);
	}
}
