package com.archermind.etb.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * 
 * 基础Dao
 * 
 * @author wei.liang
 * @version 1.0, 2013.07.01
 * @see org.springframework.orm.hibernate3.support.HibernateDaoSupport
 * @since 1.0
 * 
 */
@Repository("com.archermind.etb.common.BaseDao")
public class BaseDao<T> extends HibernateDaoSupport {

	/**
	 * 为父类HibernateDaoSupport注入sessionFactory的值
	 * 
	 * @param sessionFactory
	 * 
	 */
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 
	 * 对象的保存
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 * 
	 */
	public Serializable save(T entity) {
		return this.getHibernateTemplate().save(entity);
	}

	/**
	 * 对象的删除
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 */
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	/**
	 * 对象集合的删除
	 * 
	 * @param entities
	 *            实体对象的集合
	 * 
	 */
	public void deleteCollection(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	/**
	 * 对象的更新
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 * 
	 */
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	/**
	 * 对象的保存或者更新
	 * 
	 * @param entity
	 *            实体对象
	 * @return 
	 * 
	 * 
	 */
	public  void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 通过Id查找对象
	 * 
	 * @param entityClass
	 * 
	 * @param id
	 *            主键Id
	 * 
	 * @return
	 */
	public T findById(Class<T> entityClass, Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 通过hql查找实体集合
	 * 
	 * @param hql
	 * @return List<?>
	 */
	public List<?> find(String hql) {
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 查找所有的实体集合
	 * 
	 * @param entityClass
	 * 
	 * @return List<T>
	 */
	public List<T> getAllEntity(Class<T> entityClass) {
		return this.getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 通过hql查找带条件的实体集合
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            可变参数
	 * 
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String hql, Object... values) {
		return this.getHibernateTemplate().find(hql, values);
	}

	/**
	 * 通过hql分页查找带条件的实体集合
	 * 
	 * @param hql
	 *            hql语句
	 * @param params
	 *            参数列表集合
	 * @param start
	 *            查询记录的开始位置
	 * @param size
	 *            查询的记录条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findListByHql(String hql, List<Object> params,
			Integer start, Integer size) {
		params = this.toMysqlParams(params,hql);
		return getHibernateTemplate().executeFind(
				new QueryByHqlWithHibernateCallBack(hql, params, start, size));

	}
	
	/**
	 * 通过hql分页查找带条件的实体集合，返回Map
	 * 
	 * @param hql
	 *            ：hql语句
	 * @param params
	 *            ：参数列表集合
	 * @param start
	 *            ：查询记录的开始位置
	 * @param size
	 *            ： 查询的记录条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findList(String hql, List<Object> params,
			Integer start, Integer size) {
		params = this.toMysqlParams(params, hql);
		return getHibernateTemplate().executeFind(
				new QueryByHqlWithHibernateCallBack(hql, params, start, size));

	}

	/**
	 * 通过sql分页查找带条件的实体集合
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            参数列表集合
	 * @param start
	 *            查询记录的开始位置
	 * @param size
	 *            查询的记录条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findListBySql(String sql, List<Object> params,
			Integer start, Integer size) {
		params = this.toMysqlParams(params,sql);
		return getHibernateTemplate().executeFind(
				new QueryBySqlWithHibernateCallBack(sql, params, start, size));

	}

	/**
	 * 获取查询记录的条数
	 * 
	 * @param queryString
	 *            查询的hql语句
	 * @param params
	 *            参数列表集合
	 * @return
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public int getRecordNumber(String queryString, List<Object> params)
			throws DataAccessException {
		int index = queryString.indexOf("from");
		queryString = queryString.substring(index);
		queryString = "select count(*) " + queryString;
		params = this.toMysqlParams(params,queryString);
		List<Object> list = null;
		if (params != null && params.size() > 0) {
			list = getHibernateTemplate().find(queryString, params.toArray());
		} else {
			list = getHibernateTemplate().find(queryString);
		}
		Long recordNumber;
		if (list != null && list.size() > 0) {
			recordNumber = (Long) list.get(0);
		} else {
			recordNumber = 0l;
		}
		return recordNumber.intValue();
	}
	/**
	 * 转义mysql匹配符
	 * 注意：使用like关键字时，必须把%写在HQL或者sql中，而不能设置在params中
	 * 例如：hql += " and  c.name like '%' || ? || '%' ";
	 * @param hql
	 *            查询的hql语句
	 * @param params
	 *            参数列表集合
	 * @return 修改后的查询条件
	 */
	private  List<Object> toMysqlParams(List<Object> params,String hql)
	{
		if(null!=params && params.size() > 0)
		{
			String[] decomposeHql = hql.split("\\?");
 			Object object;
			for(int i = 0, size = params.size(); i < size; i++) {
				object = params.get(i);
				String str = object.toString();
				if(str.indexOf("_")>-1 || str.indexOf("%")>-1)
				{
					if(decomposeHql[i].contains(" like "))
					{
						str = str.replace("_", "\\_");
						str = str.replace("%", "\\%");
						params.set(i, str);
					}else
					{
						params.set(i, object);
					}
					
				}else
				{
					params.set(i, object);
				}
				
			}
		}
		return params;
	}
	
	/**
	 * 更新操作
	 * 
	 * @param sql
	 *            sql语句
	 */
	public boolean update(String sql) {
		Query query = this.getSession().createQuery(sql);
		int rows = query.executeUpdate();
		return rows == 0 ? false : true;
	}

}
