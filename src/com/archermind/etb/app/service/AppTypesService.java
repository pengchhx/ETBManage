package com.archermind.etb.app.service;

/**
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @since 1.0
 */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.archermind.etb.app.dao.AppInfoDao;
import com.archermind.etb.app.dao.AppTypesInfoDao;
import com.archermind.etb.app.domain.AppTypesInfo;
import com.archermind.etb.app.vo.AppTypesVO;

@Service("com.archermind.etb.app.service.AppTypesService")
public class AppTypesService{
	@Resource(name = "com.archermind.etb.app.dao.AppTypesInfoDao")
	private AppTypesInfoDao appTypesInfoDao;
	
	@Resource(name = "com.archermind.etb.app.dao.AppInfoDao")
	private AppInfoDao appInfoDao;
	
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
	public List<AppTypesVO> listAppTypesInfoByPage(AppTypesVO vo, int pageSize,
			int pageNum) {
		List list = new ArrayList();
		List<AppTypesInfo> relist = appTypesInfoDao.listAppTypesInfoByPage(vo,pageSize, pageNum);
		if(null!=relist && relist.size()>0){
			for(int i=0;i<relist.size();i++){
				AppTypesVO vos = new AppTypesVO();
				AppTypesInfo inf = relist.get(i);
				BeanUtils.copyProperties(inf, vos);
				list.add(vos);
			}
		}
		return list;
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
		return appTypesInfoDao.getTypesInfoCount(vo);
	}

	
	/**
	 * 取ID对应实例
	 * 
	 * @param  AppTypesVO   应用分类BEAN
	 * @return  [应用分类实例]
	 * @throws  
	 * @see 
	 */
	public AppTypesVO getAppTypesInfo(AppTypesVO vo) {
		AppTypesInfo model = appTypesInfoDao.getAppTypesInfo(vo);
		AppTypesVO revo = new AppTypesVO();
		BeanUtils.copyProperties(model, revo);
		return revo;
	}

	/**
	 * 添加
	 * 
	 * @param  AppTypesVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void addAppTypesInfo(AppTypesVO vo) {
		appTypesInfoDao.addAppTypesInfo(vo);
		
	}

	
	/**
	 * 修改
	 * 
	 * @param  AppTypesVO   应用分类BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void updateAppTypesInfo(AppTypesVO vo) {
		appTypesInfoDao.updateAppTypesInfo(vo);
		
	}

	/**
	 * 删除
	 * 
	 * @param  AppTypesVO   应用分类BEAN
	 * @return  删除成功标识     flag:1 成功  0 未成功
	 * @throws  
	 * @see 
	 */
	public int deleteAppTypesInfo(AppTypesVO vo) {
		int flag = 1;
		List relist = appInfoDao.findAppByAppTypes(vo.getAppTypesId());
		if(null!=relist && relist.size()>0){
			flag = 0;
		}else{
			appTypesInfoDao.deleteAppTypesInfo(vo);		
		}
		return flag;
	}
	
	/**
	 * 查找所有应用分类
	 * 
	 * @param 
	 * @return  实体集合
	 * @throws  
	 * @see 
	 */
	public List<AppTypesVO> findAllAppTypes(){
		List list = appTypesInfoDao.findAllAppTypes();
		List<AppTypesVO>  relist = new ArrayList();
		if(null!=list && list.size()>0){
			for(int i=0;i<list.size();i++){
				AppTypesInfo model = (AppTypesInfo) list.get(i);
				AppTypesVO vos = new AppTypesVO();
				BeanUtils.copyProperties(model, vos);
				relist.add(vos);
			}
		}
		return relist;
	}
	/**
	 * 根据名称查找应用分类
	 * 
	 * @param  	name   		应用分类名称
	 * @param 	appTypesId	应用分类ID[修改操作时会用到]	
	 * @return	boolean		[是否重名]
	 * @throws  
	 * @see 
	 */
	public boolean findAppTypesByName(String name,int appTypesId){
		List list = appTypesInfoDao.findAppTypesByName(name);
		if(null!=list && list.size()>0){
			if(appTypesId!=0){
				if(list.size()==1){
					AppTypesInfo at = (AppTypesInfo) list.get(0);
					if(at.getAppTypesId()==appTypesId){
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}
	
	/**
	 * 根据英名称查找应用分类
	 * 
	 * @param  	name   		应用分类英名名称
	 * @param 	appTypesId	应用分类ID[修改操作时会用到]	
	 * @return	boolean		[是否重名]
	 * @throws  
	 * @see 
	 */
	public boolean findAppTypesByEnName(String name,int appTypesId){
		List list = appTypesInfoDao.findAppTypesByEnName(name);
		if(null!=list && list.size()>0){
			if(appTypesId!=0){
				if(list.size()==1){
					AppTypesInfo at = (AppTypesInfo) list.get(0);
					if(at.getAppTypesId()==appTypesId){
						return true;
					}
				}
			}
			return false;
		}
		return true;
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
