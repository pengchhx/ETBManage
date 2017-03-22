package com.archermind.etb.information.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.archermind.etb.common.BaseDao;
import com.archermind.etb.information.domain.ExceptionInfo;
/**
 * @description : 异常信息Dao
 * @author  003468 wenlong.xiao
 * @version v1.0
 * @date 2013年9月16日 上午10:09:41
 */

@Repository(value = "com.archermind.etb.information.dao.ExceptionDao")
public class ExceptionDao extends BaseDao<ExceptionInfo> {


	/**
	 * 返回总数
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public long getExceptionInfoReport(String startTime, String endTime) {
		final StringBuffer hql = new StringBuffer(256);
		hql.append("select count(*) from ExceptionInfo where 1=1 ");
		if (StringUtils.isNotEmpty(startTime)
				&& StringUtils.isNotEmpty(endTime)) {
			hql.append(" and exceptionTime ").append("between '")
					.append(startTime).append("' and '").append(endTime).append("'");
		} else {
			if (StringUtils.isNotEmpty(startTime)) {
				hql.append(" and exceptionTime >= '").append(startTime).append("'");
			} else if (StringUtils.isNotEmpty(endTime)) {
				hql.append(" and exceptionTime <= '").append(endTime).append("'");
			}
		}
		return  (Long)this.getHibernateTemplate().find(hql.toString())
				.get(0);
	}
    
	/**
	 * 合计记录明细
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ExceptionInfo> getExceptionInfoReportAll(String startTime, String endTime,int pageSize,int pageNum) {
		final StringBuffer hql = new StringBuffer(256);
		hql.append("from ExceptionInfo where 1=1 ");
		if (StringUtils.isNotEmpty(startTime)
				&& StringUtils.isNotEmpty(endTime)) {
			hql.append(" and exceptionTime ").append("between '")
					.append(startTime).append("' and '").append(endTime).append("'");
		} else {
			if (StringUtils.isNotEmpty(startTime)) {
				hql.append(" and exceptionTime >= '").append(startTime).append("'");
			} else if (StringUtils.isNotEmpty(endTime)) {
				hql.append(" and exceptionTime <= '").append(endTime).append("'");
			}
		}
		hql.append(" order by exceptionTime desc");
		  return  this.findListByHql(hql.toString(), null, pageNum, pageSize);
	}
	/**
	 * 返回异常记录数
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public long getExceptionPayItem(String startTime, String endTime ,int type) {
		final StringBuffer hql = new StringBuffer(256);
		hql.append("select count(*) from ExceptionInfo where exceptionType= "+type);
		if (StringUtils.isNotEmpty(startTime)
				&& StringUtils.isNotEmpty(endTime)) {
			hql.append(" and exceptionTime ").append("between '")
					.append(startTime).append("' and '").append(endTime).append("'");
		} else {
			if (StringUtils.isNotEmpty(startTime)) {
				hql.append(" and exceptionTime >= '").append(startTime).append("'");
			} else if (StringUtils.isNotEmpty(endTime)) {
				hql.append(" and exceptionTime <= '").append(endTime).append("'");
			}
		}
		
		return (Long) this.getHibernateTemplate().find(hql.toString())
				.get(0);

	}
  
	/**
	 * 返回异常明细
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ExceptionInfo> getExceptionPayInfo(String startTime, String endTime,int pageSize,int pageNum,int type){
		final StringBuffer hql = new StringBuffer(256);
		hql.append("from ExceptionInfo where exceptionType= "+type);
		if (StringUtils.isNotEmpty(startTime)
				&& StringUtils.isNotEmpty(endTime)) {
			hql.append(" and exceptionTime ").append("between '")
					.append(startTime).append("' and '").append(endTime).append("'");
		} else {
			if (StringUtils.isNotEmpty(startTime)) {
				hql.append(" and exceptionTime >= '").append(startTime).append("'");
			} else if (StringUtils.isNotEmpty(endTime)) {
				hql.append(" and exceptionTime <= '").append(endTime).append("'");
			}
		}
		hql.append(" order by exceptionTime desc");
		return  this.findListByHql(hql.toString(), null, pageNum, pageSize);
	}
	
	/**
	 * 按年份查询获得每月的异常信息统计
	 * @param year
	 * @return
	 */
	public List<Object[]> getExceptionInfoByYear(String year){
		final StringBuffer hql = new StringBuffer(300);
		hql.append("SELECT exceptionType ,");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-01-01 00:00:00' and '").append(year).append("-02-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-02-01 00:00:00' and '").append(year).append("-03-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-03-01 00:00:00' and '").append(year).append("-04-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-04-01 00:00:00' and '").append(year).append("-05-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-05-01 00:00:00' and '").append(year).append("-06-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-06-01 00:00:00' and '").append(year).append("-07-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-07-01 00:00:00' and '").append(year).append("-08-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-08-01 00:00:00' and '").append(year).append("-09-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-09-01 00:00:00' and '").append(year).append("-10-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-10-01 00:00:00' and '").append(year).append("-11-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-11-01 00:00:00' and '").append(year).append("-12-01 00:00:00' then 1 else 0 end),");
		hql.append("sum(case when  exceptionTime   between '").append(year).append("-12-01 00:00:00' and '").append(year).append("-01-01 00:00:00' then 1 else 0 end) ");
		hql.append("FROM ExceptionInfo group by exceptionType");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Object[]> list=this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(hql.toString()).list();
			}
		});
		return list;
	}
	
	/**
	 * 返回异常类型
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Integer> findExceptionTypeItem(String startTime, String endTime){
		 final StringBuffer sql = new StringBuffer(300);
		sql.append("select exceptionType from ExceptionInfo where 1=1");
		if (StringUtils.isNotEmpty(startTime)
				&& StringUtils.isNotEmpty(endTime)) {
			sql.append(" and exceptionTime ").append("between '")
					.append(startTime).append("' and '").append(endTime).append("'");
		} else {
			if (StringUtils.isNotEmpty(startTime)) {
				sql.append(" and exceptionTime >= '").append(startTime).append("'");
			} else if (StringUtils.isNotEmpty(endTime)) {
				sql.append(" and exceptionTime <= '").append(endTime).append("'");
			}
		}
		sql.append("group by exceptionType");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Integer> list=this.getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery(sql.toString()).list();
			}
		});
		return list;
	}
	
	/**
	 * 返回当月异常总量
	 * @param year
	 * @return
	 */
	public long findExceptionCurrentYearTotalByDate(String month){
		final StringBuffer hql = new StringBuffer(256);
		hql.append("select count(*) from ExceptionInfo where 1=1 ");
		hql.append(" and SUBSTRING(exceptionTime,1,7)='").append(month+"'");
		return (Long) this.getHibernateTemplate().find(hql.toString())
				.get(0);
	}
	
	/**
	 * 返回当天异常总量
	 * @param dayTime
	 * @return
	 */
	public long findExceptionCurrentDayTotalByDate(String dayTime){
		final StringBuffer hql = new StringBuffer(256);
		hql.append("select count(*) from ExceptionInfo where 1=1 ");
		hql.append(" and exceptionTime ").append("between '").append(dayTime).append(" 00:00:00' and  '").append(dayTime).append(" 23:59:59'");
		return (Long) this.getHibernateTemplate().find(hql.toString())
				.get(0);
	}
	
	/**
	 * 返回当月某种异常总量
	 * @param year
	 * @return
	 */
	public long findExceptionTypeCurrentYearTotalByDate(String month,int type){
		final StringBuffer hql = new StringBuffer(256);
		hql.append("select count(*) from ExceptionInfo where 1=1 ");
		hql.append("  and  exceptionType =").append(type);
		hql.append(" and SUBSTRING(exceptionTime,1,7)='").append(month+"'");
		
		return (Long) this.getHibernateTemplate().find(hql.toString())
				.get(0);
	}
	
	/**
	 * 返回当天某种异常总量
	 * @param dayTime
	 * @return
	 */
	public long findExceptionTypeCurrentDayTotalByDate(String dayTime,int type){
		final StringBuffer hql = new StringBuffer(256);
		hql.append("select count(*) from ExceptionInfo where 1=1 ");
		hql.append("  and  exceptionType =").append(type);
		hql.append(" and exceptionTime ").append("between '").append(dayTime).append(" 00:00:00' and  '").append(dayTime).append(" 23:59:59'");
		return (Long) this.getHibernateTemplate().find(hql.toString())
				.get(0);
	}
	

	/**
	 * 返回当天某种异常集合
	 * @param dayTime
	 * @return
	 */
	public List<ExceptionInfo> findExceptionTypeItemCurrentDayTotalByDate(String dayTime,int type,int pageSize,int pageNum){
		final StringBuffer hql = new StringBuffer(256);
		hql.append("from ExceptionInfo where 1=1 ");
		hql.append("  and  exceptionType =").append(type);
		hql.append(" and exceptionTime ").append("between '").append(dayTime).append(" 00:00:00' and  '").append(dayTime).append(" 23:59:59'");
		hql.append(" order by exceptionTime desc");
		return  this.findListByHql(hql.toString(), null, pageNum, pageSize);
	}
	
	/**
	 * 返回当月某种异常集合
	 * @param dayTime
	 * @return
	 */
	public List<ExceptionInfo> findExceptionTypeItemCurrentDayTotalByYear(String month,int type,int pageSize,int pageNum,String startTime ,String endTime){
		final StringBuffer hql = new StringBuffer(256);
		hql.append("from ExceptionInfo where 1=1 ");
		hql.append("  and  exceptionType =").append(type);
		hql.append(" and SUBSTRING(exceptionTime,1,7)='").append(month+"'");
		if (StringUtils.isNotEmpty(startTime)
				&& StringUtils.isNotEmpty(endTime)) {
			hql.append(" and exceptionTime ").append("between '")
					.append(startTime).append(" 00:00:00' and '").append(endTime).append(" 23:59:59'");
		} else {
			if (StringUtils.isNotEmpty(startTime)) {
				hql.append(" and exceptionTime >= '").append(startTime).append(" 00:00:00'");
			} else if (StringUtils.isNotEmpty(endTime)) {
				hql.append(" and exceptionTime <= '").append(endTime).append(" 23:59:59'");
			}
		}
		hql.append(" order by exceptionTime desc");
		return  this.findListByHql(hql.toString(), null, pageNum, pageSize);
	}
}
