package com.archermind.etb.redis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Redis数据访问层
 * @author	zc 002538 2013/08/28
 * @since	1.0
 */
@Repository("com.archermind.etb.redis.dao.RedisDao")
public class RedisDao {
	private static Logger log = Logger.getLogger(RedisDao.class);
	@Resource(name="redisTemplate")
	private StringRedisTemplate redisTemplate;

	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	/**
	 * 清除所有数据
	 * @param key
	 */
	public void deleteAll(final String key) {
		try {
			redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection conn)
						throws DataAccessException {
					conn.del(key.getBytes());
					return true;
				}
			});
		} catch (Exception e) {
			log.error("redis deleteAllByKey occur error, key is:" + key,e);
		}
	}

	/**
	 * 根据KEY获取VALUE
	 * @param key
	 * @return
	 */
	public String getAsString(final String key) {
		String strRes = "";
		try {
			strRes = redisTemplate.execute(new RedisCallback<String>() {

				@Override
				public String doInRedis(RedisConnection conn)
						throws DataAccessException {
					
					byte[] res = conn.get(key.getBytes());
					return res == null ? "" : new String(res); 
				}
			});
		} catch (Exception e) {
			log.error("redis string_getValueByKey occur error, key is:" + key,e);
		}
		return strRes;
	}

	/**
	 * 添加MAP类型数据
	 * @param key
	 * @param map
	 */
	public void addMap(final String key, final Map<String, String> map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
		} catch (Exception e) {
			log.error("redis map_setHashMap occur error, key is:" + key,e);
		}
	}

	/**
	 * 获取MAP类型数据
	 * @param key
	 * @return
	 */
	public Map<String, String> getAsMap(final String key) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			Map<byte[], byte[]> temp = redisTemplate
					.execute(new RedisCallback<Map<byte[], byte[]>>() {

						@Override
						public Map<byte[], byte[]> doInRedis(
								RedisConnection conn)
								throws DataAccessException {
							return conn.hGetAll(key.getBytes());
						}
					});

			// 转换redis返回map类型
			for (Map.Entry<byte[], byte[]> entry : temp.entrySet()) {
				map.put(new String(entry.getKey()),
						new String(entry.getValue()));
			}
		} catch (Exception e) {
			log.error("redis map_getHashMapByKey occur error, key is:" + key,e);
		}
		return map;
	}

	/**
	 * 获取Map类型数据
	 * @param key
	 * @param set
	 * @return
	 */
	public Map<String, String> getAsMap(final String key, final Set<String> set) {
		final Map<String, String> mapTemp = new HashMap<String, String>();

		try {

			this.redisTemplate.execute(new RedisCallback<String>() {
				@Override
				public String doInRedis(RedisConnection conn)
						throws DataAccessException {
					for (String str : set) {
						byte[] res = conn.hGet(key.getBytes(), str.getBytes());
						if (null == res) {
							continue;
						}
						mapTemp.put(str, new String(res));
					}
					return "";
				}
			}, false, true);
		} catch (Exception e) {
			log.error("redis map_getHValues occur error, key is:" + key,e);
		}
		return mapTemp;
	}
	
	/**
	 * 获取Set类型数据
	 * @param key
	 * @return
	 */
	public Set<String> getAsSet(final String key){
		return redisTemplate.opsForSet().members(key);
	}
	
	/**
	 * 新增Set类型数据
	 * @param key
	 * @param set
	 */
	public void addSet(final String key, final Set<String> set) {
		try {
			
			redisTemplate.execute(new RedisCallback<Long>() {
				@Override
				public Long doInRedis(RedisConnection conn)
						throws DataAccessException {
					
					if (set != null && !set.isEmpty()) {
						for (final String value : set) {
							conn.sAdd(key.getBytes(), value.getBytes());
						}
					}
					return 0l;
				}
			}, false, true);
		} catch (Exception e) {
			log.error("redis set_addSet occur error, key is:" + key,e);
		}
	}

	/**
	 * 获取Set数据
	 * @param key
	 * @param set
	 * @return
	 */
	public Set<String> getAsSet(String key, Set<String> set) {
		Set<String> sRes = new HashSet<String>();
		final Map<String, Boolean> map = new HashMap<String, Boolean>();
		try {
			boolean r1 = false;
			if (set != null && !set.isEmpty()) {
				for (final String str : set) {
					r1 = redisTemplate.opsForSet().isMember(key, str);
					map.put(str, r1);
				}
			}
		} catch (Exception e) {
			log.error("redis set_getSetBySet occur error, key is:" + key,e);
		}
		// 判断map中是否存在这个值
		for (Map.Entry<String, Boolean> entry : map.entrySet()) {
			if (entry.getValue()) {
				sRes.add(entry.getKey());
			}
		}
		
		return sRes;
	}

	/**
	 * 清除K-V数据
	 * @param key
	 * @param value
	 * @return
	 */
	public Long remove(final String key, final String value) {
		Long result = null;
		try {
			result = this.redisTemplate.execute(new RedisCallback<Long>() {
				@Override
				public Long doInRedis(RedisConnection conn)
						throws DataAccessException {
					conn.sRem(key.getBytes(), value.getBytes());
					return 0l;
				}
			});
		} catch (Exception e) {
			log.error("redis set_delSetByValue occur error, key is:" + key,e);
		}
		return result;
	}

	/**
	 * 清除Set数据
	 * @param key
	 * @param set
	 */
	public void remove(final String key, final Set<String> set) {
		try {

			this.redisTemplate.execute(new RedisCallback<Long>() {
				@Override
				public Long doInRedis(RedisConnection conn)
						throws DataAccessException {

					for (final String str : set) {
						conn.sRem(key.getBytes(), str.getBytes());
					}
					return 0l;
				}
			}, false, true);

		} catch (Exception e) {
			log.error("redis set_delSetByValues occur error, key is:" + key,e);
		}
		
	}

	/**
	 * 获取Set类型列表数据
	 * @param set
	 * @param _PREFIX
	 * @param filed
	 * @return
	 */
	public Set<String> getAsSet(final Set<String> set, final String _PREFIX,
			final String filed) {

		Set<String> sRes = new HashSet<String>();
		final Map<String, Boolean> map = new HashMap<String, Boolean>();

		try {
			this.redisTemplate.execute(new RedisCallback<Boolean>() {
				@Override
				public Boolean doInRedis(RedisConnection conn)
						throws DataAccessException {

					boolean r = false;

					for (String str : set) {
						r = redisTemplate.opsForSet().isMember(_PREFIX+ str, filed);
						map.put(str, r);
					}
					return true;
				}
			}, false, true);

		} catch (Exception e) {
			log.error("redis set_getSetByFiled occur error, key is:" + (_PREFIX+filed),e);
		}
		// 判断map中是否存在这个值
		for (Map.Entry<String, Boolean> entry : map.entrySet()) {

			if (entry.getValue()) {
				sRes.add(entry.getKey());
			}
		}
		return sRes;
	}

	/**
	 * 插入string类型的K-V数据
	 * @param key
	 * @param value
	 */
	public void setString(final String key, final String value) {
		// Put into redis
		redisTemplate.execute(new RedisCallback<Object>() {  
	        @Override  
	        public Object doInRedis(RedisConnection conn)  
	                throws DataAccessException {  
	        	conn.set(redisTemplate.getStringSerializer().serialize(key), 
	        				redisTemplate.getStringSerializer().serialize(value));  
	            return null;  
	        }  
	    });
	}

	/**
	 * 在链表左端插入数据
	 * @param listName
	 * @param value
	 */
	public void lPush2List(final String listName, final String value){
		ListOperations<String, String> listOper = redisTemplate.opsForList();
		listOper.leftPush(listName, value);
	}
	
	/**
	 * 在链表右端插入数据
	 * @param listName
	 * @param value
	 */
	public void rPush2List(final String listName, final String value){
		ListOperations<String, String> listOper = redisTemplate.opsForList();
		listOper.rightPush(listName, value);
	}

	/**
	 * 获取List数据
	 * @param listName
	 * @param start	
	 * @param size
	 * @return
	 */
	public List<String> getList(final String listName, final long start, final long size) {
		List<String> relt = redisTemplate.execute(new RedisCallback<List<String>>() {  
	        @Override  
	        public List<String> doInRedis(RedisConnection conn)  
	                throws DataAccessException {  
	        	
	        	List<byte[]> rlist = conn.lRange(redisTemplate.getStringSerializer().serialize(listName), start, size);
	        	List<String> tlist = new ArrayList<String>();
	        	for(byte[] tItem : rlist){
	        		tlist.add(new String(tItem));
	        		System.out.println(tItem);
	        	}
	            return tlist;  
	        }
	    });
		return relt;
	}

	/**
	 * 添加value到Set
	 * @param setName	目标Set
	 * @param value		要插入的值
	 */
	public boolean addToSet(String setName, String value) {
		return redisTemplate.opsForSet().add(setName, value);
	}
	
	/**
	 * 添加entry到Map
	 * @param mapName
	 * @param key
	 * @param value
	 */
	public void addToMap(String mapName, String key, String value){
		redisTemplate.opsForHash().put(key, mapName, value);
	}
}
