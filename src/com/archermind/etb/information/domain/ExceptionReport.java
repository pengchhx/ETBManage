package com.archermind.etb.information.domain;

/**
 * @description : 异常信息统计
 * @author  003468 wenlong.xiao
 * @version v1.0
 * @date 2013年9月12日 下午4:58:50
 */
public class ExceptionReport {

	/**
	 * 数量
	 */
	private int Data;
	
	/**
	 * 占比
	 */
	private String proportion;
	
	/**
	 * 合计
	 */
	private int total;
	
	/**
	 * 异常类型
	 */
	private int type;
	
	/**
	 * 每种异常在当天的总数
	 */
	private int exceptionTypeCount ;
   
	/**
	 * 占当天的比例
	 */
	private String exceptionTypeProportion;
	
	/**
	 * 时间
	 * @return
	 */
	private String time;
	
	/**
	 * 年份
	 * @return
	 */
	private String year;
	
	public int getData() {
		return Data;
	}

	public void setData(int data) {
		Data = data;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getExceptionTypeCount() {
		return exceptionTypeCount;
	}

	public void setExceptionTypeCount(int exceptionTypeCount) {
		this.exceptionTypeCount = exceptionTypeCount;
	}

	public String getExceptionTypeProportion() {
		return exceptionTypeProportion;
	}

	public void setExceptionTypeProportion(String exceptionTypeProportion) {
		this.exceptionTypeProportion = exceptionTypeProportion;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}
