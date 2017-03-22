package com.archermind.etb.information.service;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.archermind.etb.common.ChartLable;
import com.archermind.etb.common.ChartValue;
import com.archermind.etb.information.dao.ExceptionDao;
import com.archermind.etb.information.domain.ExceptionInfo;
import com.archermind.etb.information.domain.ExceptionReport;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.DateUtil;

/**
 * @description : 异常信息service
 * @author  003468 wenlong.xiao
 * @version v1.0
 * @date 2013年9月13日 上午9:49:29
 */
@Service(value="com.archermind.etb.information.service.ExceptionService")
public class ExceptionService {

	@Resource(name="com.archermind.etb.information.dao.ExceptionDao")
	private  ExceptionDao exceptionDao;
	
	/**
	 * 返回总记录数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public long getExceptionInfoReport(String startTime , String endTime){
		return exceptionDao.getExceptionInfoReport(startTime, endTime);
	}
	
	/**
	 * 异常记录数
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public long getExceptionPayItem(String startTime, String endTime,int type) {
		return exceptionDao.getExceptionPayItem(startTime, endTime,type);
	}
	
	
	
	/**
	 * 异常记录明细
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ExceptionInfo> getExceptionInfo(String startTime, String endTime,int type,int pageSize, int pageNum){
		
		if(type == 1){
			return exceptionDao.getExceptionPayInfo(startTime, endTime,pageSize,pageNum,1);
		}else if(type == 2){
			return exceptionDao.getExceptionPayInfo(startTime, endTime ,pageSize,pageNum,2);
		}else if(type == 3){
			return exceptionDao.getExceptionPayInfo(startTime, endTime,pageSize,pageNum,3);
		}else if(type == 4){
			return exceptionDao.getExceptionPayInfo(startTime, endTime,pageSize,pageNum,4);
		}else if(type == 5){
			return exceptionDao.getExceptionPayInfo(startTime, endTime,pageSize,pageNum,5);
		}if(type == 6){
			return exceptionDao.getExceptionPayInfo(startTime, endTime,pageSize,pageNum,6);
		}
		return null;
	}
	
	/**
	 * 按年份查询获得每月的异常信息统计
	 * @param year
	 * @return 
	 */
	public List<List<ChartValue>> getExceptionInfoByYear(String year){
		List<Object[]> list = exceptionDao.getExceptionInfoByYear(year);
		List<List<ChartValue>> json = new ArrayList<List<ChartValue>>();
		Map<Integer,List<ChartValue>> math = new HashMap<Integer,List<ChartValue>>();
		for(int i = 0;i<list.size();i++){
			Object[] o = list.get(i);
			List<ChartValue> listValue = new ArrayList<ChartValue>();
			for(int m = 1; m<o.length ; m++){
				ChartValue chartValue = new ChartValue();
				chartValue.setValue(o[m].toString());
				listValue.add(chartValue);
			}
			math.put((Integer)o[0], listValue);
		}
		//排序 （每种颜色代表一种异常）
		for(int n=1;n<7;n++ ){
			List<ChartValue> listValue = new ArrayList<ChartValue>();
			for(int x=0;x<12;x++){
				ChartValue chartValue = new ChartValue();
				chartValue.setValue("0");
				listValue.add(chartValue);
			}
			if(math.get(n)!=null){
				json.add(math.get(n));
			}else{
				json.add(listValue);
			}
		}
		return json;
	}
	
	/**
	 * 图表label
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ChartLable> findExceptionTypeItem(){
		    List<ChartLable> listLable = new ArrayList<ChartLable>();
		//List<Integer> exceptionType = exceptionDao.findExceptionTypeItem(startTime, endTime);
		//由于目前定义6中异常
			   ChartLable chartLable1 = new ChartLable();
			   ChartLable chartLable2 = new ChartLable();
			   ChartLable chartLable3 = new ChartLable();
			   ChartLable chartLable4 = new ChartLable();
			   ChartLable chartLable5 = new ChartLable();
			   ChartLable chartLable6 = new ChartLable();
			   chartLable1.setLabel(Constant.EXCEPTION_01);
			   chartLable2.setLabel(Constant.EXCEPTION_02);
			   chartLable3.setLabel(Constant.EXCEPTION_03);
			   chartLable4.setLabel(Constant.EXCEPTION_04);
			   chartLable5.setLabel(Constant.EXCEPTION_05);
			   chartLable6.setLabel(Constant.EXCEPTION_06);
		       listLable.add(chartLable1);
		       listLable.add(chartLable2);
		       listLable.add(chartLable3);
		       listLable.add(chartLable4);
		       listLable.add(chartLable5);
		       listLable.add(chartLable6);
		       return listLable;
	}
	
	/**
	 * 图表value
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ChartValue> findExceptionTypeValue(String startTime, String endTime){
		//List<Integer> exceptionType = exceptionDao.findExceptionTypeItem(startTime, endTime);
		List<ChartValue> listValue = new ArrayList<ChartValue>();
		ChartValue chartValue1 = new ChartValue();
		ChartValue chartValue2 = new ChartValue();
		ChartValue chartValue3 = new ChartValue();
		ChartValue chartValue4 = new ChartValue();
		ChartValue chartValue5 = new ChartValue();
		ChartValue chartValue6 = new ChartValue();
		chartValue1.setValue(String.valueOf(exceptionDao.getExceptionPayItem(startTime, endTime, 1)));
		chartValue2.setValue(String.valueOf(exceptionDao.getExceptionPayItem(startTime, endTime, 2)));
		chartValue3.setValue(String.valueOf(exceptionDao.getExceptionPayItem(startTime, endTime, 3)));
		chartValue4.setValue(String.valueOf(exceptionDao.getExceptionPayItem(startTime, endTime, 4)));
		chartValue5.setValue(String.valueOf(exceptionDao.getExceptionPayItem(startTime, endTime, 5)));
		chartValue6.setValue(String.valueOf(exceptionDao.getExceptionPayItem(startTime, endTime, 6)));
			listValue.add(chartValue1);
			listValue.add(chartValue2);
			listValue.add(chartValue3);
			listValue.add(chartValue4);
			listValue.add(chartValue5);
			listValue.add(chartValue6);
		return listValue;
	}
	
	/**
	 * 异常信息采集类型
	 * 1     表示  广告异常    
	 * 2     表示  应用异常  
	 * 3     表示   OTA异常   
	 * 4     表示   缴费异常   
	 * 5     表示   彩票异常
	 * 6     表示   应用分类
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ExceptionReport> getExceptionReport(String startTime,
			String endTime) {
		List<ExceptionReport> list = new ArrayList<ExceptionReport>();
		NumberFormat nt = NumberFormat.getPercentInstance();
		DecimalFormat df = new DecimalFormat("0.00");
		String month= DateUtil.getDateYYYY_MM(new Date());
		String day = DateUtil.getDateYYYY_MM_DD(new Date());
		// 当月异常总量
		int currentYearTotal = (int) exceptionDao
				.findExceptionCurrentYearTotalByDate(month);
		// 当天异常总量
		int currentDay = (int) exceptionDao
				.findExceptionCurrentDayTotalByDate(day);
		for (int i = 1; i < 7; i++) {
			ExceptionReport exceptionReport = new ExceptionReport();
			exceptionReport.setType(i);
			exceptionReport.setTime(day);
			exceptionReport.setYear(month);
			exceptionReport.setExceptionTypeCount((int) exceptionDao
					.findExceptionTypeCurrentDayTotalByDate(day, i));
			exceptionReport.setData((int) exceptionDao
					.findExceptionTypeCurrentYearTotalByDate(month, i));
			if (currentYearTotal > 0) {
				String yearProportion = df.format((float)exceptionReport.getData()
						/ currentYearTotal);
				exceptionReport.setProportion(nt.format(Double
						.valueOf(yearProportion)));
			}
			if (currentDay > 0) {
				String dayProportion = df.format((float)exceptionReport
						.getExceptionTypeCount() / currentDay);
				exceptionReport.setExceptionTypeProportion(nt.format(Double
						.valueOf(dayProportion)));
			}
			list.add(exceptionReport);
		}
		return list;
	}
	
	/**
	 * 返回饼图数据
	 * @param startTime
	 * @param endTime
	 */
	public Map<String, List<Object>> getPieChartData(String startTime,String endTime){
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		List<Object> listValue = new ArrayList<Object>();
		List<Object> listLable = new ArrayList<Object>();
	   for(int i = 1;i<7;i++){
		  int val = (int) exceptionDao.getExceptionPayItem(startTime, endTime, i);
		  if( val!=0){
			  ChartValue chartValue = new ChartValue();
			  chartValue.setValue(String.valueOf(val));
			  ChartLable chartLable = new ChartLable();
			  if(i==1){
				  chartLable.setLabel(Constant.EXCEPTION_01);
			  }else if(i==2){
				  chartLable.setLabel(Constant.EXCEPTION_02);
			  }else if(i==3){
				  chartLable.setLabel(Constant.EXCEPTION_03);
			  }else if(i==4){
				  chartLable.setLabel(Constant.EXCEPTION_04);
			  }else if(i==5){
				  chartLable.setLabel(Constant.EXCEPTION_05);
			  }else if(i==6){
				  chartLable.setLabel(Constant.EXCEPTION_06);
			  }
			  listValue.add(chartValue);
			  listLable.add(chartLable);
		  }
	  } 
	  map.put("key", listLable);
	  map.put("value", listValue);
	   return map;
	}
	
	/**
	 * 根据类型返回当天异常数量
	 * @param dayTime
	 * @param type
	 * @return
	 */
	public long findExceptionTypeCurrentDayTotalByDate(String dayTime ,int type){
		return exceptionDao.findExceptionTypeCurrentDayTotalByDate(dayTime,type);
	}
	
	/**
	 * 根据类型返回当天异常
	 * @param dayTime
	 * @param type
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<ExceptionInfo> findExceptionTypeItemCurrentDayTotalByDate(String dayTime,int type,int pageSize,int pageNum){
		return exceptionDao.findExceptionTypeItemCurrentDayTotalByDate(dayTime, type, pageSize, pageNum);
	}
	
	/**
	 * 根据类型返回当月异常数量
	 * @param dayTime
	 * @param type
	 * @return
	 */
	public long findExceptionTypeCurrentYearTotalByYear(String year ,int type){
		return exceptionDao.findExceptionTypeCurrentYearTotalByDate(year, type);
	}
	
	/**
	 * 根据类型返回当月异常
	 * @param dayTime
	 * @param type
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	public List<ExceptionInfo> findExceptionTypeItemCurrentDayTotalByYear(String year,int type,int pageSize,int pageNum,String startTime,String endTime){
		return exceptionDao.findExceptionTypeItemCurrentDayTotalByYear(year, type, pageSize, pageNum,startTime,endTime);
	}
 }
