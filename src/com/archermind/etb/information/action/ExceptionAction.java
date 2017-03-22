package com.archermind.etb.information.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.archermind.etb.common.BaseAction;
import com.archermind.etb.information.domain.ExceptionInfo;
import com.archermind.etb.information.domain.ExceptionReport;
import com.archermind.etb.information.service.ExceptionService;
import com.archermind.etb.util.Constant;
import com.archermind.etb.util.DateUtil;

/**
 * @description : 异常信息采集
 * @author 003468 wenlong.xiao
 * @version v1.0
 */
@Controller
@Scope("prototype")
@Namespace("/information")
@Action(value = "exception", results = {
		@Result(name = "listExceptionInformation", location = "exceptionInfo_list.jsp"),
		@Result(name = "exceptionInfoALL", location = "exceptionAllInfo.jsp"),
		@Result(name = "listExceptionInformationYear", location = "exceptionInfoYear_List.jsp"),
		@Result(name = "listExceptionInformationMonth", location = "exceptionMonth_List.jsp") })
public class ExceptionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	/** 注入ExceptionService */
	@Resource(name = "com.archermind.etb.information.service.ExceptionService")
	private ExceptionService exceptionService;

	private List<ExceptionInfo> exceptionInfoList;

	private ExceptionInfo exceptionInfo;

	private List<ExceptionReport> exceptionReport;

	private String startTime;

	private String endTime;

	/**
	 * 异常信息列表
	 * 
	 * @return
	 */
	public String findExceptionInfoList() {

		this.targetType = Constant.TARGET_TYPE_DIALOG;
		exceptionInfo = exceptionInfo == null ? new ExceptionInfo()
				: exceptionInfo;
		if (exceptionInfo.getType() != 0) {
			this.exceptionInfo.setExceptionType(exceptionInfo.getType());
		}
		this.exceptionInfo.setType(exceptionInfo.getExceptionType());
		this.totalCount = (int) exceptionService
				.findExceptionTypeCurrentDayTotalByDate(
						exceptionInfo.getTime(),
						exceptionInfo.getExceptionType());

		exceptionInfoList = exceptionService
				.findExceptionTypeItemCurrentDayTotalByDate(
						exceptionInfo.getTime(),
						exceptionInfo.getExceptionType(), this.numPerPage,
						this.getPageNum());
		return "listExceptionInformation";
	}

	/**
	 * 异常信息汇总
	 * 
	 * @return
	 */
	public String getExceptionInfoAll() {
		exceptionReport = exceptionService.getExceptionReport(null, null);
		return "exceptionInfoALL";
	}

	/**
	 * 月异常信息列表
	 * 
	 * @return
	 */
	public String findExceptionInfoListByYear() {
		String month = DateUtil.getDateYYYY_MM(new Date());
		this.targetType = Constant.TARGET_TYPE_DIALOG;
		exceptionInfo = exceptionInfo == null ? new ExceptionInfo()
				: exceptionInfo;
		if (exceptionInfo.getType() != 0) {
			this.exceptionInfo.setExceptionType(exceptionInfo.getType());
		}
		this.exceptionInfo.setType(exceptionInfo.getExceptionType());
		this.totalCount = (int) exceptionService
				.findExceptionTypeCurrentYearTotalByYear(month,
						exceptionInfo.getExceptionType());
		exceptionInfoList = exceptionService
				.findExceptionTypeItemCurrentDayTotalByYear(month,
						exceptionInfo.getExceptionType(), this.numPerPage,
						this.getPageNum(), this.startTime, this.endTime);
		return "listExceptionInformationMonth";
	}

	/**
	 * 异常分类信息明细
	 * 
	 * @return
	 */
	public String findExceptionInfoListTotal() {
		this.targetType = Constant.TARGET_TYPE_DIALOG;
		exceptionInfo = exceptionInfo == null ? new ExceptionInfo()
				: exceptionInfo;
		if (exceptionInfo.getType() != 0) {
			this.exceptionInfo.setExceptionType(exceptionInfo.getType());
		}
		this.exceptionInfo.setType(exceptionInfo.getExceptionType());
		this.totalCount = (int) exceptionService.getExceptionPayItem(startTime,
				endTime, exceptionInfo.getExceptionType());
		this.exceptionInfoList = exceptionService.getExceptionInfo(startTime,
				endTime, exceptionInfo.getExceptionType(),this.numPerPage, this.getPageNum()
				);
		return "listExceptionInformationYear";
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

}
