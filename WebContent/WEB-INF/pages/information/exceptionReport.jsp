<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<s:form id="pagerForm"
	action="information/exceptionCollect!findExceptionInfoCollect.do"
	method="post" theme="simple">
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>
	<s:hidden name="startTime"></s:hidden>
	<s:hidden name="endTime"></s:hidden>
</s:form>
<div class="pageHeader">
	<s:form id="exceptionReportForms" name="exceptionReportForm"
		action="information/exceptionCollect!findExceptionInfoCollect.do"
		method="post" theme="simple" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent searchBar"
			style="padding-top: 0px; padding-bottom: 0px">
			<table class="searchContent">
				<tr>
					<td style="padding-right: 0px; text-align: left;"><label
						style="width: 48px">日期：从</label> <input type="text" id="startTime"
						name="startTime" class="date" dateFmt="yyyy-MM-dd" readonly="true"
						value="<s:property value='thisStartTime'/>" /><a
						class="inputDateButton" href="javascript:;">选择</a></td>
					<td><label style="width: 10px; padding-left: 0px">至</label> <input
						type="text" id="endTime" name="endTime" class="date"
						dateFmt="yyyy-MM-dd" readonly="true"
						value="<s:property value='thisEndTime'/>" /><a
						class="inputDateButton" href="javascript:;">选择</a></td>

					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="validate()">统计</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</div>
<div class="pageContent" style="width: 100%; height: 700px">

	<div style="width: 840px; height: 260px">
		<div id="barchartsss" class="barChart"
			valueData="<s:property value="chartDataValue"/>"
			keyData="<s:property value="chartDataLabel"/>" axisystep="5"
			xLength="250" yLength="200"
			style="height: 230px; width: 470px; float: left;"></div>
		<div id="chartHolderss" class="pieChart"
			valueData='<s:property value="pieDataValue"/>'
			keyData="<s:property value='pieDataLabel'/>" pieRadius="80"
			title="异常信息统计" yPosition="135" xPosition="95"
			style="height: 230px; width: 320px; float: right;"></div>
		<div id="hha"
			style="height: 230px; width: 180px; display: none; line-height: 230px; margin: auto; text-align: center; font-size: 20px;">无异常数据显示</div>
	</div>

	<div class="pageHeader" style="line-height: 30px; height: 30px">
		<div style="width: 330px;">
			<div style="float: left; width: 280px; margin-top: 5px">
				<s:select name="yearLineChart" id="yearLineChart"
					list="#{'2013':'2013','2014':'2014','2015':'2015','2016':'2016','2017':'2017','2018':'2018','2019':'2019','2020':'2020','2021':'2021','2022':'2022','2023':'2023','2024':'2024'}"
					listKey="key" listValue="value" label="  年份" />
			</div>

			<div class="buttonActive"
				style="float: right; width: 40px; margin-top: 4px">
				<div class="buttonContent">
					<button type="button" onclick="compute();">统计</button>
				</div>
			</div>
		</div>
	</div>
	<div style="width: 880px; height: 260px; margin-top: 55px"
		id="linecharts"></div>
</div>

<script type="text/javascript">
	
	$(function() {

		$("#linecharts").load(
				"information/exceptionCollect!getlineChartData.do?yearLineChart="
						+ $("#yearLineChart").val());

		var valueDatas = $("#chartHolderss").attr("valueData");
		var vals = jQuery.parseJSON(valueDatas);
		if (!vals.length) {
			$("#chartHolderss").css("display", 'none');
			$("#barchartsss").css("display", 'none');
			$("#hha").css("display", "");
		}
	});

	function timeCompares(beginTime, endTime) {
		var b = true;
		if (Date.parse(beginTime.replace(/-/g, '/ ')) > Date.parse(endTime
				.replace(/-/g, '/ '))) {
			b = false;
		}
		return b;
	};

	function validate() {
		if (!$("#startTime").val() && !$("#endTime").val()) {
			alertMsg.error("开始时间和结束时间不能为空!");
			return;

		} else if (!$("#startTime").val()) {
			alertMsg.error("开始时间不能为空!");
			return;
		} else if (!$("#endTime").val()) {
			alertMsg.error("结束时间不能为空!");
			return;
		} else {
			if (timeCompares($("#startTime").val(), $("#endTime").val())) {
				$("#exceptionReportForms").submit();
			} else {
				alertMsg.error("开始时间不能大于结束时间!");
				return;
			}
		}
	};

	function compute() {
		$("#linecharts").load(
				"information/exceptionCollect!getlineChartData.do?yearLineChart="
						+ $("#yearLineChart").val());
	};
</script>