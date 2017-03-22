package com.archermind.etb.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.archermind.etb.util.Constant;
import com.archermind.etb.util.JsonHelper;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 基础Action
 * 
 * @author 000578 wei.liang
 * @version 1.0, 2013.07.01
 * @see com.opensymphony.xwork2.ActionSupport
 * @since 1.0
 * 
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(BaseAction.class);

	/** 分页组件属性 begin */

	protected String targetType = Constant.TARGET_TYPE_NAVTAB;

	protected int totalCount;

	protected int numPerPage = Constant.PAGESIZE;

	protected int pageNumShown = Constant.PAGENUM_SHOWN;

	protected int pageNum = Constant.INIT_PAGENUM;

	/** 分页组件属性 end */

	/**
	 * 
	 * @param message
	 * @param statusCode
	 * @param navTabId
	 * @param rel
	 * @param callbackType
	 * @param forwardUrl
	 * @return
	 */
	protected String setResultData(String message, int statusCode,
			String navTabId, String rel, String callbackType, String forwardUrl) {
		DwzJsonData data = new DwzJsonData();
		data.setStatusCode(statusCode);
		data.setNavTabId(navTabId);
		data.setMessage(message);
		data.setRel(rel);
		data.setCallbackType(callbackType);
		data.setForwardUrl(forwardUrl);
		return JsonHelper.getGson().toJson(data);
	}

	/**
	 * 封装json数据
	 * 
	 * @author 陈然
	 * @param message
	 * @param statusCode
	 * @param closeNavTabId
	 *            需要关闭的tabid
	 * @param navTabId
	 *            需要刷新的tabid
	 * @param rel
	 * @param callbackType
	 * @param forwardUrl
	 * @return
	 */
	protected String setResultData(String message, int statusCode,
			String closeNavTabId, String navTabId, String rel,
			String callbackType, String forwardUrl) {
		DwzJsonData data = new DwzJsonData();
		data.setStatusCode(statusCode);
		data.setCloseNavTabId(closeNavTabId);
		data.setNavTabId(navTabId);
		data.setMessage(message);
		data.setRel(rel);
		data.setCallbackType(callbackType);
		data.setForwardUrl(forwardUrl);
		return JsonHelper.getGson().toJson(data);
	}

	protected String setResultData(List list,int totalCount) {
		DwzJsonData data = new DwzJsonData();
		data.setList(list);
		data.setTotalPage(totalCount);
		return JsonHelper.getGson().toJson(data);
	}
	
	protected String setResultData(List list) {
		DwzJsonData data = new DwzJsonData();
		data.setList(list);
		return JsonHelper.getGson().toJson(data);
	}

	/**
	 * 将DWZ返回结果写到HttpServletResponse里
	 * 
	 * @param message
	 * @param statusCode
	 * @param navTabId
	 * @param rel
	 * @param callbackType
	 * @param forwardUrl
	 */
	protected void writeDwzResultToResponse(String message, int statusCode,
			String navTabId, String rel, String callbackType, String forwardUrl) {

		String json = this.setResultData(message, statusCode, navTabId, rel,
				callbackType, forwardUrl);

		this.writeResultToResponse(json);
	}

	
	protected void writeDwzResultToResponse(List list,int totalPage) {

		String json = this.setResultData(list,totalPage);

		this.writeResultToResponse(json);
	}
	
	
	
	protected void writeDwzResultToResponse(List list) {

		String json = this.setResultData(list);

		this.writeResultToResponse(json);
	}
	/**
	 * 将DWZ返回结果写到HttpServletResponse里
	 * 
	 * @author 陈然
	 * @param message
	 * @param statusCode
	 * @param closeNavTabId
	 * @param navTabId
	 * @param rel
	 * @param callbackType
	 * @param forwardUrl
	 */
	protected void writeDwzResultToResponse(String message, int statusCode,
			String closeNavTabId, String navTabId, String rel,
			String callbackType, String forwardUrl) {

		String json = this.setResultData(message, statusCode, closeNavTabId,
				navTabId, rel, callbackType, forwardUrl);

		this.writeResultToResponse(json);
	}

	/**
	 * 将普通字符型返回结果写到HttpServletResponse里
	 * 
	 * @param message
	 * @param statusCode
	 * @param navTabId
	 * @param rel
	 * @param callbackType
	 * @param forwardUrl
	 */
	protected void writeTextResultToResponse(String message, int statusCode,
			String navTabId, String rel, String callbackType, String forwardUrl) {

		String json = this.setResultData(message, statusCode, navTabId, rel,
				callbackType, forwardUrl);

		this.writeTextToResponse(json);
	}

	/**
	 * 将message以application/json的类型写到response输出流中
	 * 
	 * @param message
	 */
	protected void writeResultToResponse(String message) {

		ServletActionContext.getResponse().setContentType(
				"application/json;charset=utf-8");

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = ServletActionContext.getResponse().getWriter();
			pw.write(message);
			pw.flush();
		} catch (IOException e) {
			log.error("write message to response error.", e);
		} finally {
			if (null != pw) {
				pw.close();
			}
		}
	}

	/**
	 * 将message以text/plain类型写到response输出流
	 * 
	 * @param message
	 */
	protected void writeTextToResponse(String message) {
		ServletActionContext.getResponse().setContentType(
				"text/plain;charset=utf-8");

		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = ServletActionContext.getResponse().getWriter();
			pw.write(message);
			pw.flush();
		} catch (IOException e) {
			log.error("write message to response error.", e);
		} finally {
			if (null != pw) {
				pw.close();
			}
		}
	}

	/**
	 * 文件下载
	 * 
	 * @author 001667
	 * @version 2.0
	 * @since 1.0
	 * @param filePath
	 *            文件绝对路径 fileName 下载后在浏览器显示的文件名 fileType 文件类型
	 * @return
	 * @throws IOException
	 */
	protected void downloadFile(String filePath, String fileName,
			String contentType) throws Exception {
		InputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;
		OutputStream outputStream = null;
		try {
			// 打开指定文件的流信息
			inputStream = new FileInputStream(filePath);

			bufferedInputStream = new BufferedInputStream(inputStream);

			// 写出流信息
			HttpServletResponse response = ServletActionContext.getResponse();
			outputStream = response.getOutputStream();

			// 清空输出流
			response.reset();
			// 设置响应头和下载保存的文件名
			fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
			response.setHeader("content-disposition", "attachment;filename="
					+ fileName);
			// 定义输出类型
			response.setContentType(contentType);

			byte[] buffer = new byte[1024];
			while (bufferedInputStream.read(buffer, 0, 1024) != -1) {
				outputStream.write(buffer, 0, 1024);
			}
			response.flushBuffer();
		} catch (Exception e) {
			throw new SystemException("下载文件异常", e);
		} finally {
			try {
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				throw new SystemException("下载文件异常", e);
			}

		}
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getPageNumShown() {
		return pageNumShown;
	}

	public void setPageNumShown(int pageNumShown) {
		this.pageNumShown = pageNumShown;
	}

	public int getPageNum() {

		//2013-09-12,RanChen,添加判断:若页码>总页数 则改为最大页数,start
		int pageNums = (int) Math.ceil((double)this.totalCount/this.numPerPage);
		if(pageNum > pageNums){
			pageNum = pageNums;
		} 
		//2013-09-12,RanChen,添加判断:若页码>总页数 则改为最大页数,end

		return pageNum;
	}

	public int getPageNum(int page) {


		int pageNums = (int) Math.ceil((double)this.totalCount/this.numPerPage);
		if(page > pageNums){
			page = pageNums;
		} 


		return page;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
