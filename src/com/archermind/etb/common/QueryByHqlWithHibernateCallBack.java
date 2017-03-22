package com.archermind.etb.common;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
/**
 * 
 * Hibernate回调方法通用类（针对hql语句）
 * 
 * @author  000578   wei.liang
 * @version 1.0, 2013.07.01
 * @see org.springframework.orm.hibernate3.HibernateCallback<Object>
 * @since 1.0
 * 
 */
public class QueryByHqlWithHibernateCallBack implements
		HibernateCallback<Object> {
	/**
	 * hql语句
	 */
    private String hql;   
    /**
     * 参数列表集合
     */
    private  List<Object>  params;
    /**
     * 查询记录的开始位置
     */
    private  Integer start;
    /**
     * 查询的记录条数
     */
    private  Integer size;

	public QueryByHqlWithHibernateCallBack(String hql, List<Object> params) {
		super();
		this.hql = hql;
		this.params = params;
	}

	public QueryByHqlWithHibernateCallBack(String hql, List<Object> params,
			Integer start, Integer number) {
		super();
		this.hql = hql;
		this.params = params;
		this.start = start;
		this.size = number;
	}

	/**
	 * 回调方法
	 * 
	 * @param  session    Hibernate的Session
	 */
	@Override
	public List<?> doInHibernate(Session session) throws HibernateException,
			SQLException {
           Query query = session.createQuery(hql);
           if(params!= null && params.size()>0){
        	    for(int i = 0; i< params.size();i++){
        	    	 query.setParameter(i, params.get(i));
        	    }
           }
           if(start != null && size != null){
        	   if (start <= 0) {
					start = 0;
				} else {
					start = (start - 1) * size;
				}
        	   query.setFirstResult(start);
        	   query.setMaxResults(size);
           }
		   return  query.list();
	}

}
