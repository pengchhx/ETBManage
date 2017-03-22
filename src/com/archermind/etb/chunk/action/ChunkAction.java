package com.archermind.etb.chunk.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.chunk.service.ChunkService;
import com.archermind.etb.chunk.vo.ChunkVO;
import com.archermind.etb.common.BaseAction;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.JsonHelper;

/**
 * ChunkServer管理Action
 * @author 002611  王巍
 * @version 1.0, 2013.07.11
 * @see com.archermind.etb.common.BaseAction
 * @since 1.0
 */

@Controller
@Scope("prototype")
@Namespace("/chunk")
@Action(value = "chunkAction", results ={
		  @Result(name = "list", location = "chunk_list.jsp"),
		  @Result(name = "add", location = "chunk_add.jsp"),
		  @Result(name = "reserve", location = "chunk_reserve.jsp"),
		  @Result(name = "showChunkFree", location = "show_chunkFree.jsp"),
		  @Result(name = "update", location = "chunk_update.jsp")})

public class ChunkAction extends BaseAction {

	private static final long serialVersionUID = -8162458812138734645L;

	private static final Logger log = Logger.getLogger(ChunkAction.class);
	/** chunk服务器信息 */
	private ChunkVO vo;

	/** chunk服务器信息集合 */
	private List<ChunkVO> list;

	@Resource(name = "com.archermind.etb.app.service.ChunkService")
	private ChunkService chunkService;
	
	/**
	 * 列表展现
	 * 
	 * @return
	 */
	public String listChunkInfo() {
		revo();
		this.totalCount = chunkService.getTypesInfoCount(vo);
		this.list = chunkService.listChunkByPage(vo,this.numPerPage, this.pageNum);
		return "list";
	}

	/**
	 * 新增页面
	 * 
	 * @return
	 */
	public String toAdd() {
		return "add";
	}
	
	/**
	 * 修改页面
	 * 
	 * @return
	 */
	public String toUpdate() {
		vo = chunkService.getChunkInfo(vo);
		return "update";
	}
	
	/**
	 * 修改页面
	 * 
	 * @return
	 */
	public String toReserve() {
		vo = chunkService.getChunkInfo(vo);
		return "reserve";
	}

	/**
	 * 修改信息
	 * 
	 * @return
	 */
	public String updateChunkInfo() {
		revo();
		String message = Constant.UPDATE_SUCCESS;
		if (vo.getChunkId() > 0) {
			message = Constant.UPDATE_SUCCESS;
		}
		try{
			vo.setChunkUpdateUser(reUser());
			vo.setChunkUpdateTime(new Date());
			chunkService.updateChunkInfo(vo);
			
			this.writeDwzResultToResponse(message, Constant.HTTP_STATUS_OK,
					"listChunk", null, "closeCurrent", null);
		}catch (Exception e) {
			log.error("update Chunk failed.", e);
			this.writeDwzResultToResponse(Constant.UPDATE_FAILED, Constant.HTTP_STATUS_FAILED,
					"listChunk", null, null, null);
		}
		return null;
	}
	
	/**
	 * 备份URL信息
	 * 
	 * @return
	 */
	public String reserveChunkInfo() {
		revo();
		String message = Constant.SAVE_SUCCESS;
		if (vo.getChunkId() > 0) {
			message = Constant.SAVE_SUCCESS;
		}
		try{
			vo.setChunkUpdateUser(reUser());
			vo.setChunkUpdateTime(new Date());
			chunkService.updateReserveChunkInfo(vo);
			
			this.writeDwzResultToResponse(message, Constant.HTTP_STATUS_OK,
					"listChunk", null, "closeCurrent", null);
		}catch (Exception e) {
			log.error("reserve Chunk failed.", e);
			this.writeDwzResultToResponse(Constant.SAVE_FAILED, Constant.HTTP_STATUS_FAILED,
					"listChunk", null, null, null);
		}
		return null;
	}
	
	

	/**
	 * 保存信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveChunkInfo() {

		String message = Constant.SAVE_SUCCESS;
		if (vo.getChunkId() > 0) {
			message = Constant.SAVE_SUCCESS;
		}

		try{
			vo.setChunkCreateUser(reUser());
			vo.setChunkCreateTime(new Date());
			chunkService.addChunkInfo(vo);
			
			this.writeDwzResultToResponse(message, Constant.HTTP_STATUS_OK,
					"listChunk", null, "closeCurrent", null);
		}catch (Exception e) {
			log.error("save Chunk failed.", e);
			this.writeDwzResultToResponse(Constant.SAVE_FAILED, Constant.HTTP_STATUS_FAILED,
					"listChunk", null, null, null);
		}
		return null;
	}

	/**
	 * 删除信息
	 * 
	 * @return
	 */
	public String deleteChunkInfo() {
		try{
			int flag = chunkService.deleteChunkInfo(vo);
			if(flag==1){
				this.writeDwzResultToResponse(Constant.DELETE_SUCCESS,
						Constant.HTTP_STATUS_OK, "listChunk", null, null, null);
			}else{
				this.writeDwzResultToResponse(Constant.HAVE_APPS,
						Constant.HTTP_STATUS_FAILED, "listChunk", null, null, null);
			}
		}catch (Exception e) {
			log.error("delete Chunk failed.", e);
			this.writeDwzResultToResponse(Constant.DELETE_FAILED,
					Constant.HTTP_STATUS_FAILED, "listChunk", null, null, null);
		}

		return null;
	}

	/**
	 * 根据ID获取chunk的状态
	 */
	public void getChunkStatusById() {
			ChunkVO vot = chunkService.getChunkInfo(vo);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status",
					vot == null ? "" : vot.getStatus());

			String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE)
					.toJson(map);
			this.writeResultToResponse(json);

	}
	
	
	/**
	 * 判断chunk服务器是否重名
	 * @return
	 */
	public String findChunkByName(){
		boolean result = chunkService.findChunkByName(vo.getChunkName(),vo.getChunkId());
		if(result){
			this.writeResultToResponse("true");
		}else{
			this.writeResultToResponse("false");
		}
		return null;
	}
	
	
	/**
	 * 查询chunkServer信息
	 * 
	 * @return
	 */
	public String findFreeChunk() {
		revo();
		List<ChunkVO> list = chunkService.listFreeChunkByPage(vo,Constant.DEVICE_SHOW_COLUMN,Constant.INIT_PAGENUM);
		String json = JsonHelper.getGson(JsonHelper.TYPE_EXPOSE).toJson(list);
		this.writeResultToResponse(json);
		return null;
	}
	
	/**
	 * 查找带回可用于备份的设备信息列表
	 * 
	 * @return
	 */
	public String showFreeChunk() {
		revo();
		//将targetType属性设置为弹窗模式
		this.targetType = Constant.TARGET_TYPE_DIALOG;
		this.totalCount = chunkService.getFreeChunkCount(vo);
		this.list = chunkService.listFreeChunkByPage(vo,this.numPerPage, this.pageNum);
		return "showChunkFree";
	}
	
	
	public ChunkVO getVo() {
		return vo;
	}

	public void setVo(ChunkVO vo) {
		this.vo = vo;
	}

	public List<ChunkVO> getList() {
		return list;
	}

	public void setList(List<ChunkVO> list) {
		this.list = list;
	}

	public ChunkService getChunkService() {
		return chunkService;
	}

	public void setChunkService(ChunkService chunkService) {
		this.chunkService = chunkService;
	}

	/**
	 * 防止VO为空的容错方法
	 * @return
	 */
	private void revo(){
		if(null==vo){
			vo = new ChunkVO();
		}
	}
	
	/**
	 * 得到当前操作人
	 */
	private String reUser(){
		Subject subject = SecurityUtils.getSubject();
		return subject.getSession().getAttribute(Constant.SESSION_USER_NAME)+"";
	}
	
}
