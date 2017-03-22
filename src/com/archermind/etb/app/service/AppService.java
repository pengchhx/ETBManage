package com.archermind.etb.app.service;

/**
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @since 1.0
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.archermind.etb.app.dao.AppInfoDao;
import com.archermind.etb.app.dao.AppTypesInfoDao;
import com.archermind.etb.app.domain.AppInfo;
import com.archermind.etb.app.domain.AppTypesInfo;
import com.archermind.etb.app.vo.AppTypesVO;
import com.archermind.etb.app.vo.AppVO;
import com.archermind.etb.util.Constant;

@Service("com.archermind.etb.app.service.AppService")
public class AppService{
	@Resource(name = "com.archermind.etb.app.dao.AppInfoDao")
	private AppInfoDao appInfoDao;
	
	@Resource(name = "com.archermind.etb.app.dao.AppTypesInfoDao")
	private AppTypesInfoDao appTypesInfoDao;
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
	public List<AppVO> listAppInfoByPage(AppVO vo, int pageSize,
			int pageNum) {
		List list = new ArrayList();
		List<Object[]> relist = appInfoDao.listAppInfoByPage(vo,pageSize, pageNum);
		if(null!=relist && relist.size()>0){
			for(int i=0;i<relist.size();i++){
				AppVO vos = new AppVO();
				Object[] o = relist.get(i);
				AppInfo inf = (AppInfo) o[0];
				String appTypesName = o[1]+"";
				BeanUtils.copyProperties(inf, vos);
				vos.setAppTypesName(appTypesName);
				list.add(vos);
			}
		}
		return list;
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
		return appInfoDao.getInfoCount(vo);
	}

	
	/**
	 * 取ID对应实例
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  [应用分类实例]
	 * @throws  
	 * @see 
	 */
	public AppVO getAppInfo(AppVO vo) {
		AppInfo model = appInfoDao.getAppInfo(vo);
		AppVO revo = new AppVO();
		BeanUtils.copyProperties(model, revo);
		return revo;
	}

	
	/**
	 * 取ID对应实例与分类名称
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  [应用分类实例]
	 * @throws  
	 * @see 
	 */
	public AppVO getAppInfoAndType(AppVO vo) {
		AppInfo model = appInfoDao.getAppInfo(vo);
		AppTypesVO avo = new AppTypesVO();
		avo.setAppTypesId(model.getAppTypesId());
		AppTypesInfo at = appTypesInfoDao.getAppTypesInfo(avo);
		AppVO revo = new AppVO();
		BeanUtils.copyProperties(model, revo);
		revo.setAppTypesName(at.getAppTypesName());
		return revo;
	}
	
	/**
	 * 添加
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  
	 * @throws FileNotFoundException 
	 * @throws  
	 * @see 
	 */
	public void addAppInfo(AppVO vo){
		appInfoDao.addAppInfo(vo);
		
	}

	/**
	 * 修改
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void updateAppInfo(AppVO vo) {
		appInfoDao.updateAppInfo(vo);
		
	}

	/**
	 * 删除
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  删除成功标识     flag:1 成功  0 未成功
	 * @throws  
	 * @see 
	 */
	public int deleteAppInfo(AppVO vo) {
		return appInfoDao.deleteAppInfo(vo);
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
		return appInfoDao.getCheckInfoCount(vo);
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
	public List<AppVO> checkAppInfoByPage(AppVO vo, int pageSize, int pageNum){

		List list = new ArrayList();
		List<Object[]> relist = appInfoDao.checkAppInfoByPage(vo,pageSize, pageNum);
		if(null!=relist && relist.size()>0){
			for(int i=0;i<relist.size();i++){
				AppVO vos = new AppVO();
				Object[] o = relist.get(i);
				AppInfo inf = (AppInfo) o[0];
				String appTypesName = o[1]+"";
				BeanUtils.copyProperties(inf, vos);
				vos.setAppTypesName(appTypesName);
				list.add(vos);
			}
		}
		return list;
	
	}
	
	/**
	 * 审核
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void checkAppInfo(AppVO vo){
		appInfoDao.checkAppInfo(vo);
	}
	
	/**
	 * 驳回审核
	 * 
	 * @param  AppVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void uncheckAppInfo(AppVO vo){
		appInfoDao.uncheckAppInfo(vo);
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
		return appInfoDao.getCheckedInfoCount(vo);
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
	public List<AppVO> checkedAppInfoByPage(AppVO vo, int pageSize, int pageNum){
		List list = new ArrayList();
		List<Object[]> relist = appInfoDao.checkedAppInfoByPage(vo,pageSize, pageNum);
		if(null!=relist && relist.size()>0){
			for(int i=0;i<relist.size();i++){
				AppVO vos = new AppVO();
				Object[] o = relist.get(i);
				AppInfo inf = (AppInfo) o[0];
				String appTypesName = o[1]+"";
				BeanUtils.copyProperties(inf, vos);
				vos.setAppTypesName(appTypesName);
				list.add(vos);
			}
		}
		return list;
	
	
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
		AppInfo model = appInfoDao.getAppInfo(vo);
		List<AppInfo> relist = appInfoDao.findAppByAppInfoPackage(model.getAppInfoPackage());
		if(null!=relist && relist.size()>0){
			int len = relist.size();
			for(int i=0;i<len;i++){
				AppInfo ap = relist.get(i);
				ap.setAppInfoStatus(Constant.APP_INFO_STATUS_UNPUBLISHED);
			}
		}
		appInfoDao.publishAppInfo(vo);
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
		appInfoDao.unpublishAppInfo(vo);
	}
	
	public boolean findAppByPack(AppVO vo){
		AppInfo ai = appInfoDao.getAppInfo(vo);
		
		List list = appInfoDao.findAppByPack(vo);
		if(null!=list && list.size()>0){
			if(vo.getAppInfoId()!=0){
				if(list.size()==1){
					AppInfo at = (AppInfo) list.get(0);
					if(at.getAppInfoId()==vo.getAppInfoId()){
						return true;
					}
				}
			}
			return false;
		}else{
			return true;
		}
	}
	
	public AppInfoDao getAppInfoDao() {
		return appInfoDao;
	}

	public void setAppInfoDao(AppInfoDao appInfoDao) {
		this.appInfoDao = appInfoDao;
	}


	public AppTypesInfoDao getAppTypesInfoDao() {
		return appTypesInfoDao;
	}


	public void setAppTypesInfoDao(AppTypesInfoDao appTypesInfoDao) {
		this.appTypesInfoDao = appTypesInfoDao;
	}
	
	
}
