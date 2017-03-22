package com.archermind.etb.device.service;

import org.springframework.stereotype.Service;

import com.archermind.etb.device.domain.EtbClientDevice;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.rwexcel.AbstractExcelRow;

/**
 * 将excel每行数据转换为实体，并放入到list中
 * 
 * @author 001667
 * @version 2.0
 * @see com.archermind.etb.util.rwexcel.ChangeRowToList
 * @since 1.0
 */
@Service("com.archermind.etb.device.service.ClientDeviceExcelRowService")
public class ClientDeviceExcelRowService extends AbstractExcelRow {
	private EtbClientDevice row = null;

	/**
	 * 字符长度100
	 */
	private final static int STR_LEN_100 = 100;
	
	/**
	 * 字符长度200
	 */
	private final static int STR_LEN_200 = 200;

	/**
	 * 设置行数据到实体
	 */
	@Override
	public void set(String[] cellValue) {
		
		String[] deveiceValue = new String[7];
		deveiceValue[6] = cellValue[cellValue.length-1];
		for(int i=0 ;i < cellValue.length-1 & i < deveiceValue.length-1; i++) {
			deveiceValue[i] = cellValue[i];
		}
		
		row = new EtbClientDevice();
		row.setIndex(Integer.parseInt(deveiceValue[6]));
		row.setImei(formatString(deveiceValue[0],STR_LEN_100));// excel第一列终端设备编号
		row.setName(formatString(deveiceValue[1],STR_LEN_100));// 第二列终端设备型号
		row.setAddr(formatString(deveiceValue[2],STR_LEN_100));// 第三列终端设备MAC地址
		row.setIp(formatString(deveiceValue[3],STR_LEN_100));// 第四列终端设备IP地址
		row.setOs(formatString(deveiceValue[4],STR_LEN_100));// 第五列终端设备系统版本
		row.setTips(formatString(deveiceValue[5],STR_LEN_200));// 第六列终端设备备注说明
		row.setStat(Constant.DATA_STAT_ON);
	}

	/**
	 * 把行作为实体返回
	 */
	@Override
	public Object get() {
		return row;
	}

	/**
	 * 格式化字符串
	 * 
	 * @param value
	 * @return
	 */
	private String formatString(String value,int maxLength) {
		if(value == null){
			return "";
		}
		int size = value.length() > maxLength ? maxLength:value.length();
		value = value.substring(0, size);
		return value;
	}

}
