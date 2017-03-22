package com.archermind.etb.common;

import java.util.UUID;

/**
 * 为数据库获取一个唯一的主键id的代码
 * 
 * @author 003382 禇俊毅
 * @version v1.0,2013.07.16
 * @see
 * @since v1.0
 */
public class UUIDGenerator {

	public UUIDGenerator() {
	}

	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {

		String s = UUID.randomUUID().toString();
		return s.replaceAll("-", "");

	}

	/**
	 * 获得指定数目的UUID
	 * 
	 * @param number需要获得的UUID数量
	 * @return String[] UUID数组
	 */
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}
}