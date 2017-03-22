<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:form id="pagerForm"
	action="information/exception!findExceptionInfoListByYear.do" method="post"
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

<div class="pageContent">
<div class="grid">
		<div class="gridHeader">
			<div class="gridThead">

	<table class="table" width="100%" layoutH="60">
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
			<s:iterator value="exceptionInfoList" id="exceptionInfo">
			<tr>
					<td><s:property value="#exceptionInfo.clientUserAccount" />
					<td><s:property value="#exceptionInfo.exceptionId" /></td>
					<td><s:property value="#exceptionInfo.exceptionName" /></td>
					<td><s:property value="#exceptionInfo.exceptionVersion" /></td>
					<td>
					             <s:if test="#exceptionInfo.exceptionType == 1">
				                                              广告异常
				                 </s:if>
				                 <s:elseif test="#exceptionInfo.exceptionType == 2">
				                                                   应用异常
				                 </s:elseif>
				                 <s:elseif test="#exceptionInfo.exceptionType == 3">
				                  OTA异常
				                 </s:elseif>
				                 <s:elseif test="#exceptionInfo.exceptionType == 4">
				                                                  缴费异常 
				                 </s:elseif>
				                 <s:elseif test="#exceptionInfo.exceptionType == 5">
				                                                  彩票异常
				                 </s:elseif>
				                 </td>
					<td><s:property value="#exceptionInfo.exceptionReason" /></td>
				    <td><s:date name="#exceptionInfo.exceptionTime" format="yyyy-MM-dd HH:mm:ss" ></s:date></td>
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