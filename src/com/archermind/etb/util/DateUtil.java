/**
 * 时间处理公共类
 * 
 * @author 003383 陈然
 * @version v1.0,2013.08.01
 * @see
 * @since v1.0
 */
package com.archermind.etb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.xwork.StringUtils;

public class DateUtil {

	/**
	 * 时间格式定义
	 */
	public static String DATE_YYYY_MM_DD = "yyyy-MM-dd";
	public static String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:SS";

	/**
	 * 获取时间格式YYYY-MM-DD
	 * 
	 * @author 陈然
	 * @param date
	 * @return
	 */
	public static String getDateYYYY_MM_DD(Date date) {

		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_FORMAT_YYYY_MM_DD);
			return sdf.format(date);
		}

		return "";
	}

	/**
	 * 获取时间格式YYYY-MM-DD HH:MM:SS
	 * 
	 * @author 陈然
	 * @param date
	 * @return
	 */
	public static String getDateYYYY_MM_DD_HH_MM_SS(Date date) {

		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(
					Constant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
			return sdf.format(date);
		}

		return "";

	}
	
	/**
	 * 获取时间格式YYYY-MM-DD HH:MM:SS
	 * 
	 * @author 禇俊毅
	 * @param date
	 * @return
	 */
	public static Date formatDateYYYY_MM_DD_HH_MM_SS (Date date) throws ParseException{

		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(
					Constant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
			return sdf.parse(sdf.format(date));
		}

		return null;

	}

	/**
	 * String转日期格式化为yyyy-MM-dd
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDateYYYY_MM_DD(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constant.DATE_FORMAT_YYYY_MM_DD);
		if (!StringUtils.isEmpty(date)) {
		
			return sdf.parse(date);
		}

		return null;
	}
	
	/**
	 * String转日期格式化为yyyy-MM-dd HH:mm:SS
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDateYYYY_MM_DD_HH_MM_SS (String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(
				Constant.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
		if (!StringUtils.isEmpty(date)) {
		
			return sdf.parse(date);
		}

		return null;
	}
	
	/**
	 * 日期格式化为yyyyMMddHHmmss
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String parseDateYYYYMMDDHHMMSS(Date date) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				Constant.DATE_FORMAT_YYYYMMDDHHMMSS); // 时间格式化的格式

		if (date != null) {
			return sDateFormat.format(date);
		}

		return "";
	}

	/**
	 * 日期格式化为yyyy-MM
	 * @param date
	 * @return
	 */
	public static String getDateYYYY_MM(Date date) {

		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			return sdf.format(date);
		}
		return "";
	}
}
