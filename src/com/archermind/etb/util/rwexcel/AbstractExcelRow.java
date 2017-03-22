package com.archermind.etb.util.rwexcel;

/**
 * Excel文件转换为list接口
 * 
 * @author 001667
 * @version 2.0
 * @since 1.0
 */
public abstract class AbstractExcelRow {
	/**
	 * 设置Cell
	 * 
	 * @param cell
	 */
	public abstract void set(String[] cell);

	/**
	 * 返回对象
	 * 
	 * @return
	 */
	public abstract Object get();
}
