package com.archermind.etb.app.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 应用模块接口返回类型实体
 * 
 * @author 002611  王巍
 * @version 1.0, 2013-8-8 下午6:14:33
 * @since 1.0
 */
@XmlRootElement
public class AppItem implements Serializable{
	
	private static final long serialVersionUID = -8591567811561118012L;
	//应用下载地址
	private String url;
	//应用包名
	private String packagename;
	//操作类型 （0-无操作、1-安装、2-卸载 . 3-更新）
	private int action;
	//版本号
	private int version;
	//应用类别
	private int type;
		
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPackagename() {
		return packagename;
	}
	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	
	
}
