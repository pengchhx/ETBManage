package com.archermind.etb.redis.dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.push.domain.PushMsg;

/**
 * Redis定时同步备份Dao
 * @author 002538 chi.zhang
 * @version 1.0
 * @see com.archermind.etb.common.BaseDao
 * @Time 2013-9-13
 */
@Repository("com.archermind.etb.redis.dao.RedisBackupDao")
public class RedisBackupDao extends BaseDao<PushMsg>{
	
	/**
	 * 定时备份
	 * @param list 以PushMsg实例为元数据的List
	 * @throws DataAccessResourceFailureException
	 * @throws HibernateException
	 * @throws IllegalStateException
	 * @throws SQLException
	 */
	public void backupPushMsg(List<PushMsg> list) throws DataAccessResourceFailureException, HibernateException, IllegalStateException, SQLException{
		String command = "{call backup_push_msg(?,?,?,?,?,?)}";
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		session.beginTransaction();
		
		int times = 0;
		for(PushMsg msg : list) {
			CallableStatement stm = session.connection().prepareCall(command);
			
			stm.setString(1,msg.getId());
			stm.setString(2, msg.getMsgContent());
			if(msg.getDeadLine()!=null){
				stm.setDate(3, new Date(msg.getDeadLine().getTime()));
			} else {
				stm.setDate(3, null);
			}
			stm.setString(4, msg.getMsgCreater());
			if(msg.getMsgCreateTime()!=null){
				stm.setDate(5, new Date(msg.getMsgCreateTime().getTime()));
			} else {
				stm.setDate(5, null);
			}
			
			stm.setInt(6, msg.getStat());
			
			//stm.registerOutParameter("out_code", Types.VARCHAR);
			stm.executeUpdate();
	        //String keycode = stm.getString("out_code");
	        
	        if (times % 50 == 0) {
				session.flush();
				session.clear();
			}
		}
		
		session.getTransaction().commit();
		session.close();
	}
}
