package com.archermind.etb.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.archermind.etb.app.domain.AppTypesInfo;
import com.archermind.etb.app.vo.AppTypesVO;
import com.archermind.etb.common.BaseDao;
import com.archermind.etb.user.domain.UserInfo;
import com.archermind.etb.util.Constant;

@Repository("com.archermind.etb.app.dao.AppTypesInfoDao")
public class AppTypesInfoDao extends BaseDao<AppTypesInfo>{
	/**
	 * 分页方法
	 * 
	 * @param  AppTypesVO   应用分类BEAN
	 * @param  pageSize     页数
	 * @param  pageNum      每页记录数
	 * @return  [应用分类分页集合]
	 * @throws  
	 * @see 
	 */
	public List<AppTypesInfo> listAppTypesInfoByPage(AppTypesVO vo, int pageSize,
			int pageNum){

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AppTypesInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getAppTypesName(), sbHQL, params);
		sbHQL.append(" order by appTypesId desc");
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}

	/**
	 * 根据查询条件，设置HQL
	 * 
	 * @param queryCondition
	 * @param sbHQL
	 * @param params
	 */
	private void setQueryCondition(String appTypesName, StringBuffer sbHQL, List<Object> params) {
		if (!StringUtils.isEmpty(appTypesName)) {
			sbHQL.append(" and appTypesName like '%' || ? || '%' ");
			params.add(appTypesName);
		}

	}

	/**
	 * 分页总数
	 * 
	 * @param  AppTypesVO   应用分类BEAN
	 * @return  [应用分类分页总数]
	 * @throws  
	 * @see 
	 */
	public int getTypesInfoCount(AppTypesVO vo) {
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AppTypesInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getAppTypesName(), sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}
	
	/**
	 * 取ID对应实例
	 * 
	 * @param  AppTypesVO   应用分类BEAN
	 * @return  [应用分类实例]
	 * @throws  
	 * @see 
	 */
	public AppTypesInfo getAppTypesInfo(AppTypesVO vo){
		return this.findById(AppTypesInfo.class, vo.getAppTypesId());
	}
	/**
	 * 添加
	 * 
	 * @param  AppTypesVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void addAppTypesInfo(AppTypesVO vo){
		AppTypesInfo model = new AppTypesInfo();
		BeanUtils.copyProperties(vo, model);
		model.setDataStat(Constant.DATA_STAT_ON);
		this.save(model);
		vo.setAppTypesId(model.getAppTypesId());
	}
	/**
	 * 修改
	 * 
	 * @param  AppTypesVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void updateAppTypesInfo(AppTypesVO vo){
		AppTypesInfo model = getAppTypesInfo(vo);
		model.setAppTypesName(vo.getAppTypesName());
		model.setAppTypesTips(vo.getAppTypesTips());
		model.setAppTypesEnName(vo.getAppTypesEnName());
		model.setAppTypesFileName(vo.getAppTypesFileName());
		if(null!=vo.getAppTypesUrl()){
			model.setAppTypesUrl(vo.getAppTypesUrl());
		}
		this.saveOrUpdate(model);
	}
	/**
	 * 删除
	 * 
	 * @param  AppTypesVO   应用分类BEAN
	 * @return  删除成功标识     flag:1 成功  0 未成功
	 * @throws  
	 * @see 
	 */
	public void deleteAppTypesInfo(AppTypesVO vo){
		AppTypesInfo model = getAppTypesInfo(vo);
		model.setDataStat(Constant.DATA_STAT_OFF);
		this.saveOrUpdate(model);
	}
	/**
	 * 查找所有应用分类
	 * 
	 * @param
	 * @return  实体集合
	 * @throws  
	 * @see 
	 */
	public List findAllAppTypes(){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AppTypesInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON);
		return this.find(sbHQL.toString());
		
	}
	
	/**
	 * 根据名称查找应用分类
	 * 
	 * @param  name 应用分类名称
	 * @return  实体集合
	 * @throws  
	 * @see 
	 */
	public List<AppTypesInfo> findAppTypesByName(String name){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AppTypesInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(name)) {
			sbHQL.append(" and appTypesName = ? ");
			params.add(name);
		}
		
		return this.find(sbHQL.toString(),params.toArray());
	}
	
	/**
	 * 根据英文名称查找应用分类
	 * 
	 * @param  name 应用分类英文名称
	 * @return  实体集合
	 * @throws  
	 * @see 
	 */
	public List<AppTypesInfo> findAppTypesByEnName(String name){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from AppTypesInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(name)) {
			sbHQL.append(" and appTypesEnName = ? ");
			params.add(name);
		}
		
		return this.find(sbHQL.toString(),params.toArray());
	}
}
