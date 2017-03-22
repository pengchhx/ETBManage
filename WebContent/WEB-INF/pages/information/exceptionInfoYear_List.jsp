<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:form id="pagerForm"
	action="information/exception!findExceptionInfoListTotal.do" method="post"
	theme="simple">
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>
	<s:hidden name="exceptionInfo.clientUserAccount"></s:hidden>
	<s:hidden name="exceptionInfo.exceptionInfoType"></s:hidden>
	<s:hidden name="exceptionInfo.time"></s:hidden>
    <s:hidden name="exceptionInfo.type"></s:hidden>
    <s:hidden name="startTime"></s:hidden>
	<s:hidden name="endTime"></s:hidden>
	
</s:form>
<div class="pageHeader">
	<s:form id="exceptionFormss" name="exceptionForm"
		action="information/exception!findExceptionInfoListTotal.do" method="post"
		theme="simple" onsubmit="return dwzSearch(this, 'dialog');">
		<div class="pageFormContent searchBar" style="padding-top: 0px; padding-bottom: 0px;border-style: none;" id="pageform">
			<table class="searchContent">
				<tr>
					<td style="padding-right: 0px; text-align: left;"><label
						style="width: 27px">日期：</label> <input type="text" id="startTimes"
						name="startTime" class="date" dateFmt="yyyy-MM-dd" readonly="true"
						value="<s:property value='startTime'/>" /><a
						class="inputDateButton" href="javascript:;">选择</a></td>
					<td><label style="width: 10px; padding-left: 0px">至</label> <input
						type="text" id="endTimes" name="endTime" class="date"
						dateFmt="yyyy-MM-dd" readonly="true"
						value="<s:property value='endTime'/>" /><a
						class="inputDateButton" href="javascript:;">选择</a></td>

					<td>
					<s:hidden name="exceptionInfo.exceptionInfoType"></s:hidden>
					  <s:hidden name="exceptionInfo.type"></s:hidden>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="validate()">检索</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</div>
<div class="pageContent">
<div class="grid">
		<div class="gridHeader">
			<div class="gridThead">

	<table class="table" width="100%" layoutH="100">
		<thead>
			<tr style="text-align: center">
				<th>终端用户账号</th>
				<th>异常信息编号</th>
				<th>异常名称</th>
				<th>异常版本</th>
				<th>操作类型</th>
				<th>异常原因</th>
				<th>操作时间</th>
			</tr>
		</thead>
		
		<tbody>
			<s:iterator value="exceptionInfoList" id="exceptionInfos">
			<tr>
					<td><s:property value="#exceptionInfos.clientUserAccount" />
					<td><s:property value="#exceptionInfos.exceptionId" /></td>
					<td><s:property value="#exceptionInfos.exceptionName" /></td>
					<td><s:property value="#exceptionInfos.exceptionVersion" /></td>
					<td>
					             <s:if test="#exceptionInfos.exceptionType == 1">
				                                              广告异常
				                 </s:if>
				                 <s:elseif test="#exceptionInfos.exceptionType == 2">
				                                                   应用异常
				                 </s:elseif>
				                 <s:elseif test="#exceptionInfos.exceptionType == 3">
				                  OTA异常
				                 </s:elseif>
				                 <s:elseif test="#exceptionInfos.exceptionType == 4">
				                                                  缴费异常 
				                 </s:elseif>
				                 <s:elseif test="#exceptionInfos.exceptionType == 5">
				                                                  彩票异常
				                 </s:elseif>
				                 <s:elseif test="#exceptionInfos.exceptionType == 6">
				                                               应用分类异常
				                 </s:elseif>
				                 </td>
					<td><s:property value="#exceptionInfos.exceptionReason" /></td>
				    <td><s:date name="#exceptionInfos.exceptionTime" format="yyyy-MM-dd HH:mm:ss" ></s:date></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
	</div>
	</div>
	<div class="panelBar">
		<div class="pages">
			共
			<s:property value="totalCount" />
			条</span>
		</div>

		<div class="pagination" targetType="<s:property value='targetType'/>"
			totalCount="<s:property value='totalCount'/>"
			numPerPage="<s:property value='numPerPage'/>"
			pageNumShown="<s:property value='pageNumShown'/>"
			currentPage="<s:property value='pageNum'/>"></div>

	</div>
</div>
<script type="text/javascript">

function timeComparess(beginTime, endTime) {
	var b = true;
	if (Date.parse(beginTime.replace(/-/g, '/ ')) > Date.parse(endTime
			.replace(/-/g, '/ '))) {
		b = false;
	}
	return b;
}
function validate() {
	if (timeComparess($("#startTimes").val(), $("#endTimes").val())) {
		$("#exceptionFormss").submit();
	} else {
		alertMsg.error("开始时间不能大于结束时间!");
		return;
	}
}
</script>