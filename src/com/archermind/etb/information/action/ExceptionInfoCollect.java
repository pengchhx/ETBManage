package com.archermind.etb.information.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.common.ChartLable;
import com.archermind.etb.common.ChartValue;
import com.archermind.etb.information.domain.ExceptionInfo;
import com.archermind.etb.information.domain.ExceptionReport;
import com.archermind.etb.information.service.ExceptionService;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.DateUtil;
import com.archermind.etb.util.JsonHelper;

/**
 * @description : 异常信息统计信息
 * @author 003468 wenlong.xiao
 * @version v1.0
 * @date 2013年9月12日 下午4:52:13
 */

@Controller
@Scope("prototype")
@Namespace("/information")
@Action(value = "exceptionCollect", results = {
		@Result(name = "listExceptionInformation", location = "exceptionReport.jsp"),
		@Result(name = "listException", location = "exceptionInfo.jsp"),
		@Result(name = "lineChart", location = "lineChart.jsp") })
public class ExceptionInfoCollect extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 注入ExceptionService */
	@Resource(name = "com.archermind.etb.information.service.ExceptionService")
	private ExceptionService exceptionService;

	/**
	 * 柱状图及饼状图x轴值
	 */
	private String chartDataLabel;

	/**
	 * 柱状图及饼状图y轴值
	 */
	private String chartDataValue;

	/**
	 * 饼图value
	 */
	private String pieDataValue;

	/**
	 * 饼图label
	 */
	private String pieDataLabel;

	private String lineDataLabel;

	private String lineDataValue;

	/**
	 * 开始时间
	 */
	private String startTime;

	/**
	 * 结束时间
	 */
	private String endTime;

	/**
	 * 异常信息集合
	 */
	private List<ExceptionInfo> exceptionInfoList;

	private ExceptionInfo exceptionInfo;

	/**
	 * 异常类型
	 */
	private int type;

	/**
	 * 年份
	 */
	private String yearLineChart;
	/**
	 * 异常信息分类统计信息
	 */
	private List<ExceptionReport> exceptionReport;

	private String thisStartTime;

	private String thisEndTime;

	private String val;

	/**
	 * 获取图表数据
	 * 
	 * @return
	 */
	public String findExceptionInfoCollect() {
		getTime();
		// 异常明细报表记录
		exceptionReport = exceptionService.getExceptionReport(startTime,
				endTime);
		List<ChartLable> listLable = exceptionService.findExceptionTypeItem();
		List<ChartValue> listValue = exceptionService.findExceptionTypeValue(
				startTime, endTime);
		// 柱状图
		this.chartDataLabel = JsonHelper.getGson().toJson(listLable);
		this.chartDataValue = JsonHelper.getGson().toJson(listValue);
		// 饼图
		Map<String, List<Object>> map = exceptionService.getPieChartData(
				startTime, endTime);
		this.pieDataLabel = JsonHelper.getGson().toJson(map.get("key"));
		this.pieDataValue = JsonHelper.getGson().toJson(map.get("value"));
		if (StringUtils.isEmpty(yearLineChart)) {
			// 默认为当年
			Calendar cal = Calendar.getInstance();
			yearLineChart = cal.get(Calendar.YEAR) + "";
		}
		return "listExceptionInformation";
	}

	/**
	 * 异常明细列表
	 * 
	 * @return
	 */
	public String findExceptionInfo() {
		getTime();
		exceptionInfo = exceptionInfo == null ? new ExceptionInfo()
				: exceptionInfo;
		this.targetType = Constant.TARGET_TYPE_DIALOG;
		// 获取查询异常信息集合
		exceptionInfoList = exceptionService.getExceptionInfo(this.startTime,
				this.endTime, this.type, this.numPerPage, this.getPageNum());
		return "listException";
	}

	/**
	 * 折线图
	 */

	public String getlineChartData() {
		List<List<ChartValue>> listLineValue;
		if (StringUtils.isEmpty(yearLineChart)) {
			// 默认为当年
			Calendar cal = Calendar.getInstance();
			yearLineChart = cal.get(Calendar.YEAR) + "";
			listLineValue = exceptionService
					.getExceptionInfoByYear(yearLineChart);
		} else {
			listLineValue = exceptionService
					.getExceptionInfoByYear(yearLineChart);
		}
		this.lineDataValue = JsonHelper.getGson().toJson(listLineValue);
		return "lineChart";
	}

	/**
	 * 时间处理
	 */
	public void getTime() {
		if (StringUtils.isEmpty(startTime) && StringUtils.isEmpty(endTime)) {
			// 默认为时间为当天
			Date date = Calendar.getInstance().getTime();
			String time = DateUtil.getDateYYYY_MM_DD(date);
			this.startTime = time + " 00:00:00";
			this.endTime = time + " 23:59:59";
			this.thisStartTime = time;
			this.thisEndTime = time;
		} else if (!StringUtils.isEmpty(startTime)
				&& !StringUtils.isEmpty(endTime)) {
			System.out.println("start:" + startTime);
			this.thisStartTime = startTime;
			this.thisEndTime = endTime;
			this.startTime = startTime + " 00:00:00";
			this.endTime = endTime + " 23:59:59";
		} else if (!StringUtils.isEmpty(startTime)) {
			this.thisStartTime = startTime;
			this.startTime = startTime + " 00:00:00";
		} else {
			this.thisEndTime = endTime;
			this.endTime = endTime + " 23:59:59";
		}
	}

	public List<ExceptionInfo> getExceptionInfoList() {
		return exceptionInfoList;
	}

	public void setExceptionInfoList(List<ExceptionInfo> exceptionInfoList) {
		this.exceptionInfoList = exceptionInfoList;
	}

	public ExceptionInfo getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(ExceptionInfo exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	public String getChartDataLabel() {
		return chartDataLabel;
	}

	public void setChartDataLabel(String chartDataLabel) {
		this.chartDataLabel = chartDataLabel;
	}

	public String getChartDataValue() {
		return chartDataValue;
	}

	public void setChartDataValue(String chartDataValue) {
		this.chartDataValue = chartDataValue;
	}

	public List<ExceptionReport> getExceptionReport() {
		return exceptionReport;
	}

	public void setExceptionReport(List<ExceptionReport> exceptionReport) {
		this.exceptionReport = exceptionReport;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLineDataLabel() {
		return lineDataLabel;
	}

	public void setLineDataLabel(String lineDataLabel) {
		this.lineDataLabel = lineDataLabel;
	}

	public String getYearLineChart() {
		return yearLineChart;
	}

	public void setYearLineChart(String yearLineChart) {
		this.yearLineChart = yearLineChart;
	}

	public String getLineDataValue() {
		return lineDataValue;
	}

	public void setLineDataValue(String lineDataValue) {
		this.lineDataValue = lineDataValue;
	}

	public String getPieDataValue() {
		return pieDataValue;
	}

	public void setPieDataValue(String pieDataValue) {
		this.pieDataValue = pieDataValue;
	}

	public String getPieDataLabel() {
		return pieDataLabel;
	}

	public void setPieDataLabel(String pieDataLabel) {
		this.pieDataLabel = pieDataLabel;
	}

	public String getThisStartTime() {
		return thisStartTime;
	}

	public void setThisStartTime(String thisStartTime) {
		this.thisStartTime = thisStartTime;
	}

	public String getThisEndTime() {
		return thisEndTime;
	}

	public void setThisEndTime(String thisEndTime) {
		this.thisEndTime = thisEndTime;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

}
