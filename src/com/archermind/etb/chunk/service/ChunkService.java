package com.archermind.etb.chunk.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.archermind.etb.chunk.dao.ChunkDao;
import com.archermind.etb.chunk.domain.ChunkInfo;
import com.archermind.etb.chunk.vo.ChunkVO;
/**
 * ChunckServer管理业务逻辑
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @since 1.0
 */
@Service("com.archermind.etb.app.service.ChunkService")
public class ChunkService{
	@Resource(name = "com.archermind.etb.app.dao.ChunkDao")
	private ChunkDao chunkDao;
	
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
	public List<ChunkVO> listChunkByPage(ChunkVO vo, int pageSize,
			int pageNum) {
		List list = new ArrayList();
		List<ChunkInfo> relist = chunkDao.listChunkByPage(vo,pageSize, pageNum);
		if(null!=relist && relist.size()>0){
			for(int i=0;i<relist.size();i++){
				ChunkVO vos = new ChunkVO();
				ChunkInfo inf = relist.get(i);
				BeanUtils.copyProperties(inf, vos);
				list.add(vos);
			}
		}
		return list;
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
	public List<ChunkVO> listFreeChunkByPage(ChunkVO vo, int pageSize,
			int pageNum) {
		List list = new ArrayList();
		List<ChunkInfo> relist = chunkDao.listFreeChunkByPage(vo,pageSize, pageNum);
		if(null!=relist && relist.size()>0){
			for(int i=0;i<relist.size();i++){
				ChunkVO vos = new ChunkVO();
				ChunkInfo inf = relist.get(i);
				BeanUtils.copyProperties(inf, vos);
				list.add(vos);
			}
		}
		return list;
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
		return chunkDao.getTypesInfoCount(vo);
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
		return chunkDao.getFreeChunkCount(vo);
	}
	
	/**
	 * 取ID对应实例
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  [chunk服务器实例]
	 * @throws  
	 * @see 
	 */
	public ChunkVO getChunkInfo(ChunkVO vo) {
		ChunkInfo model = chunkDao.getChunkInfo(vo);
		ChunkVO revo = new ChunkVO();
		BeanUtils.copyProperties(model, revo);
		return revo;
	}

	/**
	 * 添加
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void addChunkInfo(ChunkVO vo) {
		chunkDao.addChunkInfo(vo);
		
	}

	
	/**
	 * 修改
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void updateChunkInfo(ChunkVO vo) {
		chunkDao.updateChunkInfo(vo);
		
	}
	
	/**
	 * 备份URL
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  
	 * @throws  
	 * @see 
	 */
	public void updateReserveChunkInfo(ChunkVO vo) {
		chunkDao.reserveChunkInfo(vo);
		
	}

	/**
	 * 删除
	 * 
	 * @param  ChunkVO   chunk服务器BEAN
	 * @return  删除成功标识     flag:1 成功  0 未成功
	 * @throws  
	 * @see 
	 */
	public int deleteChunkInfo(ChunkVO vo) {
		int flag = 1;
//		List relist = chunkDao.findAppByChunk(vo.getChunkId());
//		if(null!=relist && relist.size()>0){
//			flag = 0;
//		}else{
			chunkDao.deleteChunkInfo(vo);		
//		}
		return flag;
	}
	
	/**
	 * 查找所有chunk服务器
	 * 
	 * @param 
	 * @return  实体集合
	 * @throws  
	 * @see 
	 */
	public List<ChunkVO> findAllChunk(){
		List list = chunkDao.findAllChunk();
		List<ChunkVO>  relist = new ArrayList();
		if(null!=list && list.size()>0){
			for(int i=0;i<list.size();i++){
				ChunkInfo model = (ChunkInfo) list.get(i);
				ChunkVO vos = new ChunkVO();
				BeanUtils.copyProperties(model, vos);
				relist.add(vos);
			}
		}
		return relist;
	}
	/**
	 * 根据名称查找chunk服务器
	 * 
	 * @param  	name   		chunk服务器名称
	 * @param 	chunkId	chunk服务器ID[修改操作时会用到]	
	 * @return	boolean		[是否重名]
	 * @throws  
	 * @see 
	 */
	public boolean findChunkByName(String name,int chunkId){
		List list = chunkDao.findChunkByName(name);
		if(null!=list && list.size()>0){
			if(chunkId!=0){
				if(list.size()==1){
					ChunkInfo at = (ChunkInfo) list.get(0);
					if(at.getChunkId()==chunkId){
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}

	public ChunkDao getChunkDao() {
		return chunkDao;
	}

	public void setChunkDao(ChunkDao chunkDao) {
		this.chunkDao = chunkDao;
	}
	
}
