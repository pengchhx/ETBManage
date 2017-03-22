package com.archermind.etb.chunk.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.archermind.etb.chunk.domain.ChunkInfo;
import com.archermind.etb.chunk.vo.ChunkVO;
import com.archermind.etb.common.BaseDao;
import com.archermind.etb.util.Constant;

/**
 * ChunkServer管理DAO
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @see com.archermind.etb.chunk.dao.BaseDao
 * @since 1.0
 */
@Repository("com.archermind.etb.app.dao.ChunkDao")
public class ChunkDao extends BaseDao<ChunkInfo>{
	/**
	 * 分页方法
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @param  pageSize     页数
	 * @param  pageNum      每页记录数
	 * @return  [chunk服务器分页集合]
	 * @throws  
	 * @see 
	 */
	public List<ChunkInfo> listChunkByPage(ChunkVO vo, int pageSize,
			int pageNum){

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from ChunkInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getChunkName(), sbHQL, params);
		sbHQL.append(" order by chunkId desc");
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}
	
	/**
	 * 空闲chunkServer分页方法
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @param  pageSize     页数
	 * @param  pageNum      每页记录数
	 * @return  [chunk服务器分页集合]
	 * @throws  
	 * @see 
	 */
	public List<ChunkInfo> listFreeChunkByPage(ChunkVO vo, int pageSize,
			int pageNum){

		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from ChunkInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON+" and status = "+Constant.CHUNK_STATUS_FREE);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getChunkName(), sbHQL, params);
		sbHQL.append(" order by chunkId desc");
		return this.findListByHql(sbHQL.toString(), params, pageNum, pageSize);
	}

	/**
	 * 根据查询条件，设置HQL
	 * 
	 * @param queryCondition
	 * @param sbHQL
	 * @param params
	 */
	private void setQueryCondition(String chunkName, StringBuffer sbHQL, List<Object> params) {
		if (!StringUtils.isEmpty(chunkName)) {
			sbHQL.append(" and chunkName like '%' || ? || '%' ");
			params.add(chunkName);
		}

	}

	/**
	 * 分页总数
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  [chunk服务器分页总数]
	 * @throws  
	 * @see 
	 */
	public int getTypesInfoCount(ChunkVO vo) {
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from ChunkInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getChunkName(), sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}
	
	/**
	 * 空闲chunkServer分页总数
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  [chunk服务器分页总数]
	 * @throws  
	 * @see 
	 */
	public int getFreeChunkCount(ChunkVO vo) {
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from ChunkInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON+" and status = "+Constant.CHUNK_STATUS_FREE);
		List<Object> params = new ArrayList<Object>();
		setQueryCondition(vo.getChunkName(), sbHQL, params);
		return this.getRecordNumber(sbHQL.toString(), params);
	}
	
	/**
	 * 取ID对应实例
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  [chunk服务器实例]
	 * @throws  
	 * @see 
	 */
	public ChunkInfo getChunkInfo(ChunkVO vo){
		return this.findById(ChunkInfo.class, vo.getChunkId());
	}
	/**
	 * 添加
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void addChunkInfo(ChunkVO vo){
		ChunkInfo model = new ChunkInfo();
		BeanUtils.copyProperties(vo, model);
		model.setDataStat(Constant.DATA_STAT_ON);
		this.save(model);
	}
	/**
	 * 修改
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void updateChunkInfo(ChunkVO vo){
		ChunkInfo model = getChunkInfo(vo);
		model.setChunkName(vo.getChunkName());
		model.setChunkUrl(vo.getChunkUrl());
		model.setStatus(vo.getStatus());
		model.setChunkUpdateUser(vo.getChunkUpdateUser());
		model.setChunkUpdateTime(vo.getChunkUpdateTime());
		this.saveOrUpdate(model);
	}
	
	/**
	 * 备份URL
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void reserveChunkInfo(ChunkVO vo){
		ChunkInfo model = getChunkInfo(vo);
		model.setReserveUrl(vo.getReserveUrl());
		model.setChunkUpdateUser(vo.getChunkUpdateUser());
		model.setChunkUpdateTime(vo.getChunkUpdateTime());
		this.saveOrUpdate(model);
	}
	
	
	/**
	 * 删除
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  删除成功标识     flag:1 成功  0 未成功
	 * @throws  
	 * @see 
	 */
	public void deleteChunkInfo(ChunkVO vo){
		ChunkInfo model = getChunkInfo(vo);
		model.setDataStat(Constant.DATA_STAT_OFF);
		this.saveOrUpdate(model);
	}
	/**
	 * 查找所有chunk服务器
	 * 
	 * @param
	 * @return  实体集合
	 * @throws  
	 * @see 
	 */
	public List findAllChunk(){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from ChunkInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON);
		return this.find(sbHQL.toString());
		
	}
	
	/**
	 * 根据名称查找chunk服务器
	 * 
	 * @param  name chunk服务器名称
	 * @return  chunk服务器集合
	 * @throws  
	 * @see 
	 */
	public List<ChunkInfo> findChunkByName(String name){
		StringBuffer sbHQL = new StringBuffer(256);
		sbHQL.append(" from ChunkInfo where 1 = 1 and dataStat = "+Constant.DATA_STAT_ON);
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(name)) {
			sbHQL.append(" and chunkName = ? ");
			params.add(name);
		}
		
		return this.find(sbHQL.toString(),params.toArray());
	}
	
}
