package com.archermind.etb.common;

import java.io.Serializable;
import java.util.List;

/**
 * DWZ ajax请求返回json数据
 * @author 002525
 *
 */
public class DwzJsonData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8279135279804998761L;
	
	
	/** 数据返回状态 */
	private int statusCode;
	
	/** 数据返回信息 */
	private String message;
	
	/** 服务器转回navTabId可以把那个navTab标记为reloadFlag=1, 下次切换到那个navTab时会重新载入内容. */
	private String navTabId;
	
	/** */
	private String rel;
	
	/** callbackType如果是closeCurrent就会关闭当前tab
	 *只有callbackType="forward"时需要forwardUrl值，将进行页面跳转
	 */
	private String callbackType;
	
	/** 跳转页面的URL */
	private String forwardUrl;
	
	/** 需要关闭的页签ID */
	private String closeNavTabId;
	
	
	
	private List list;
	
	private int totalPage;
	
	

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getCloseNavTabId() {
		return closeNavTabId;
	}

	public void setCloseNavTabId(String closeNavTabId) {
		this.closeNavTabId = closeNavTabId;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	
	
}
