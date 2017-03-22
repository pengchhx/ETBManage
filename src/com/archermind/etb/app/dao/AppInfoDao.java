package com.archermind.etb.app.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.archermind.etb.app.domain.AppInfo;
import com.archermind.etb.app.vo.AppVO;
import com.archermind.etb.common.BaseDao;
import com.archermind.etb.util.Constant;

@Repository("com.archermind.etb.app.dao.AppInfoDao")
public class AppInfoDao extends BaseDao<AppInfo>{
	/**
	 * 分页方法
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @param  pageSize     页数
	 * @param  pageNum      每页记录数
	 * @return  [应用分类分页集合]
	 * @throws  
	 * @see 
	 */
	public List<Object[]> listAppInfoByPage(AppVO vo, int pageSize,
			int pageNum){

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" select a,b.appTypesName from AppInfo a,AppTypesInfo b where 1 = 1 and a.appTypesId = b.appTypesId and a.dataStat= "+Constant.DATA_STAT_ON+" and b.dataStat="+Constant.DATA_STAT_ON );
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getAppInfoName(),vo.getAppTypesId(), sbHQL, params);
		sbHQL.append(" order by a.appInfoId desc");
		List  relist =  this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
		return relist;
	}

	/**
	 * 根据查询条件，设置HQL
	 * 
	 * @param queryCondition
	 * @param sbHQL
	 * @param params
	 */
	private void setQueryCondition(String appName,int appTypesId, StringBuffer sbHQL, List<Object> params) {
		if (!StringUtils.isEmpty(appName)) {
			sbHQL.append(" and a.appInfoName like '%' || ? || '%' ");
			params.add(appName);
		}
		if (0!=appTypesId) {
			sbHQL.append(" and a.appTypesId = ? ");
			params.add(appTypesId);
		}
		
	}

	/**
	 * 分页总数
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  [应用分类分页总数]
	 * @throws  
	 * @see 
	 */
	public int getInfoCount(AppVO vo) {
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" select a,b.appTypesName from AppInfo a,AppTypesInfo b where 1 = 1 and a.appTypesId = b.appTypesId and a.dataStat="+Constant.DATA_STAT_ON+" and b.dataStat="+Constant.DATA_STAT_ON );
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getAppInfoName(),vo.getAppTypesId(), sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}
	
	/**
	 * 取ID对应实例
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  [应用分类实例]
	 * @throws  
	 * @see 
	 */
	public AppInfo getAppInfo(AppVO vo){
		return this.findById(AppInfo.class, vo.getAppInfoId());
	}
	/**
	 * 添加
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void addAppInfo(AppVO vo){
		AppInfo model = new AppInfo();
		BeanUtils.copyProperties(vo, model);
//		model.setAppInfoStatus(Constant.APP_INFO_STATUS_CREATE);
		model.setDataStat(Constant.DATA_STAT_ON);
		model.setAppInfoVersionCode(vo.getAppInfoVersionCode());
		model.setAppInfoCreateTime(new Date());
		this.save(model);
	}
	/**
	 * 修改
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void updateAppInfo(AppVO vo){
		AppInfo model = getAppInfo(vo);
//		BeanUtils.copyProperties(vo, model);
		model.setAppInfoName(vo.getAppInfoName());
		if(null!=vo.getAppInfoPath()){
			model.setAppInfoPath(vo.getAppInfoPath());
			model.setAppInfoVersion(vo.getAppInfoVersion());
			model.setAppInfoVersionCode(vo.getAppInfoVersionCode());
			model.setAppInfoPackage(vo.getAppInfoPackage());
		}
//		if(model.getAppInfoStatus()==Constant.APP_INFO_STATUS_REFUSED){
//			model.setAppInfoStatus(Constant.APP_INFO_STATUS_MANUSCRIPT);
//		}
		model.setAppInfoStatus(vo.getAppInfoStatus());
		model.setAppInfoUpdateTime(new Date());
		model.setAppTypesId(vo.getAppTypesId());
		model.setAppInfoTips(vo.getAppInfoTips());
		this.saveOrUpdate(model);
	}
	/**
	 * 删除
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  删除成功标识     flag:1 成功  0 未成功
	 * @throws  
	 * @see 
	 */
	public int deleteAppInfo(AppVO vo){
		int flag = 1;
		AppInfo model = getAppInfo(vo);
		if(model.getAppInfoStatus()==Constant.APP_INFO_STATUS_PUBLISHED){
			flag = 0;
		}else{
			model.setDataStat(Constant.DATA_STAT_OFF);
			this.saveOrUpdate(model);
		}
		return flag;
	}
	/**
	 * 审核分页总数
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  [审核分页总数]
	 * @throws  
	 * @see 
	 */
	public int getCheckInfoCount(AppVO vo){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" select a,b.appTypesName from AppInfo a,AppTypesInfo b where 1 = 1 and a.appTypesId = b.appTypesId and a.appInfoStatus = "+Constant.APP_INFO_STATUS_CREATE+"  and a.dataStat="+Constant.DATA_STAT_ON+" and b.dataStat="+Constant.DATA_STAT_ON );
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getAppInfoName(),vo.getAppTypesId(), sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}
	/**
	 * 审核分页方法
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @param  pageSize     页数
	 * @param  pageNum      每页记录数
	 * @return  [审核分页集合]
	 * @throws  
	 * @see 
	 */
	public List<Object[]> checkAppInfoByPage(AppVO vo, int pageSize, int pageNum){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" select a,b.appTypesName from AppInfo a,AppTypesInfo b where 1 = 1 and a.appTypesId = b.appTypesId and a.appInfoStatus = "+Constant.APP_INFO_STATUS_CREATE+"  and a.dataStat="+Constant.DATA_STAT_ON+" and b.dataStat="+Constant.DATA_STAT_ON );
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getAppInfoName(),vo.getAppTypesId(), sbHQL, params);
		sbHQL.append(" order by a.appInfoId desc");
		List  relist =  this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
		return relist;
	}
	/**
	 * 审核通过
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void checkAppInfo(AppVO vo){
		AppInfo model = getAppInfo(vo);
		model.setAppInfoStatus(Constant.APP_INFO_STATUS_CHECKED);
		model.setAppInfoChecher(vo.getAppInfoChecher());
		model.setAppInfoCheckTime(new Date());
		this.saveOrUpdate(model);
	}
	
	
	/**
	 * 审核不通过
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void uncheckAppInfo(AppVO vo){
		AppInfo model = getAppInfo(vo);
		model.setAppInfoStatus(Constant.APP_INFO_STATUS_REFUSED);
		model.setAppInfoChecher(vo.getAppInfoChecher());
		model.setAppInfoCheckTime(new Date());
		model.setAppInfoCheckSuggest(vo.getAppInfoCheckSuggest());
		this.saveOrUpdate(model);
	}
	
	/**
	 * 审核通过的分页总数  上线，下线页面
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  [审核通过分页总数]
	 * @throws  
	 * @see 
	 */
	public int getCheckedInfoCount(AppVO vo){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" select a,b.appTypesName from AppInfo a,AppTypesInfo b where 1 = 1 and a.appTypesId = b.appTypesId and (a.appInfoStatus = "+Constant.APP_INFO_STATUS_CHECKED+" or a.appInfoStatus = "+Constant.APP_INFO_STATUS_PUBLISHED+" or a.appInfoStatus = "+Constant.APP_INFO_STATUS_UNPUBLISHED+") and a.dataStat="+Constant.DATA_STAT_ON+" and b.dataStat="+Constant.DATA_STAT_ON );
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getAppInfoName(),vo.getAppTypesId(), sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}
	/**
	 * 审核通过列表分页方法   上线，下线页面
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @param  pageSize     页数
	 * @param  pageNum      每页记录数
	 * @return  [审核通过分页集合]
	 * @throws  
	 * @see 
	 */
	public List<Object[]> checkedAppInfoByPage(AppVO vo, int pageSize, int pageNum){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" select a,b.appTypesName from AppInfo a,AppTypesInfo b where 1 = 1 and a.appTypesId = b.appTypesId and (a.appInfoStatus = "+Constant.APP_INFO_STATUS_CHECKED+" or a.appInfoStatus = "+Constant.APP_INFO_STATUS_PUBLISHED+" or a.appInfoStatus = "+Constant.APP_INFO_STATUS_UNPUBLISHED+") and a.dataStat="+Constant.DATA_STAT_ON+" and b.dataStat="+Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getAppInfoName(),vo.getAppTypesId(), sbHQL, params);
		sbHQL.append(" order by a.appInfoId desc");
		List  relist =  this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
		return relist;
	}
	/**
	 * 上线
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void publishAppInfo(AppVO vo){
		AppInfo model = getAppInfo(vo);
		model.setAppInfoPublisher(vo.getAppInfoPublisher());
		model.setAppInfoPublishTime(new Date());
		model.setAppInfoStatus(Constant.APP_INFO_STATUS_PUBLISHED);
		this.saveOrUpdate(model);
	}
	/**
	 * 下线
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void unpublishAppInfo(AppVO vo){
		AppInfo model = getAppInfo(vo);
//		model.setAppInfoPublisher(vo.getAppInfoPublisher());
		model.setAppInfoStatus(Constant.APP_INFO_STATUS_UNPUBLISHED);
		this.saveOrUpdate(model);
	}
	/**
	 * 根据应用分类ID查找对应的应用集合
	 * 
	 * @param  appTypesId   应用分类ID
	 * @return  [应用集合]
	 * @throws  
	 * @see 
	 */
	public List<AppInfo> findAppByAppTypes(int appTypesId){
		String hql = " from AppInfo a where 1 = 1 and a.dataStat="+Constant.DATA_STAT_ON+" and a.appTypesId = "+appTypesId;
		List relist = this.find(hql);
		return relist;
	}
	/**
	 * 根据应用包名和版本查找对应的应用集合
	 * 
	 * @param  vo   应用信息对象
	 * @return  [应用集合]
	 * @throws  
	 * @see 
	 */
	public List<AppInfo> findAppByPack(AppVO vo){
		String hql = " from AppInfo a where 1 = 1 and a.dataStat="+Constant.DATA_STAT_ON+" and a.appInfoPackage = '"+vo.getAppInfoPackage()+"' and a.appInfoVersionCode = '"+vo.getAppInfoVersionCode()+"'";
		List relist = this.find(hql);
		return relist;
	}
	/**
	 * 根据应用包名查找对应的应用集合
	 * 
	 * @param  appInfoPackage   应用包名
	 * @return  [应用集合]
	 * @throws  
	 * @see 
	 */
	public List<AppInfo> findAppByAppInfoPackage(String appInfoPackage){
		String hql = " from AppInfo a where 1 = 1 and a.appInfoStatus="+Constant.APP_INFO_STATUS_PUBLISHED+" and a.dataStat="+Constant.DATA_STAT_ON+" and a.appInfoPackage = '"+appInfoPackage+"'";
		List relist = this.find(hql);
		return relist;
	}
}
