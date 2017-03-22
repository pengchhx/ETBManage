package com.archermind.etb.app.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 应用分类模块接口返回类型实体
 * 
 * @author 002611  王巍
 * @version 1.0, 2013-8-8 下午6:14:33
 * @since 1.0
 */
@XmlRootElement
public class AppTypesItem implements Serializable{
	
	private static final long serialVersionUID = -8591567811561118012L;
	//应用分类ID
	private int appTypesId; 
	//应用分类图标地址
	private String icon;
	
	//应用分类名称
	private String cnName;
	//应用分类英文名称
	private String enName;
	//操作类型 （0.新增   1.删除    2.修改）
	private int action;
	
	public int getAppTypesId() {
		return appTypesId;
	}
	public void setAppTypesId(int appTypesId) {
		this.appTypesId = appTypesId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	
	
}
