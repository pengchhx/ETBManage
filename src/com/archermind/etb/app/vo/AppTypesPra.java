package com.archermind.etb.app.vo;


import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 应用分类模块接口参数实体类
 * 
 * @author 002611  王巍
 * @version 1.0, 2013-8-8 下午6:14:33
 * @since 1.0
 */
@XmlRootElement
public class AppTypesPra {
	//应用集合
	private List<AppTypesItem> appTypes;
	//验证令牌
	private String token;
	//应用升级类型
	private int type;
	//升级通知批次
	private int action;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<AppTypesItem> getAppTypes() {
		return appTypes;
	}

	public void setAppTypes(List<AppTypesItem> appTypes) {
		this.appTypes = appTypes;
	}


	
}
