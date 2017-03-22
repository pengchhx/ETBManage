<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form id="pagerForm" action="app/appAction!checkedAppInfoList.do" method="post" theme="simple">
	
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>
	<s:hidden name="vo.appInfoName"></s:hidden>
	<s:hidden name="vo.appTypesId"></s:hidden>
</s:form>


<div class="pageHeader">
	<s:form id="appInfoCheckedListForm" name="appInfoCheckedListForm" action="app/appAction!checkedAppInfoList.do" method="post" theme="simple" onsubmit="return navTabSearch(this);">
		<s:hidden name="pageNum"></s:hidden>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						应用名称：<s:textfield name="vo.appInfoName"></s:textfield>
					</td>
					<td>
						应用分类：<s:select list="appTypesList" listKey="appTypesId" listValue="appTypesName" name="vo.appTypesId" id="appTypesId"  headerKey="0" headerValue="请选择"></s:select>
					</td>
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button>
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

	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr align="center">
				<th>应用分类</th>
				<th>应用名称</th>
				<th>应用版本</th>
				<th>应用包名</th>
				<th>应用状态</th>
				<th>创建人</th>
				<th>创建时间</th>
				<th>审核人</th>
				<th>审核时间</th>
				<th>发布人</th>
				<th>发布时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" id="appInfo">
				<tr target="appInfoId" rel="<s:property value='#appInfo.appInfoId'/>">
					<td><s:property value="#appInfo.appTypesName"/></td>
					<td><s:property value="#appInfo.appInfoName"/></td>
					<td><s:property value="#appInfo.appInfoVersion"/></td>
					<td><s:property value="#appInfo.appInfoPackage"/></td>
					<td>
						<s:if test="#appInfo.appInfoStatus==0">待审核</s:if>
						<s:elseif test="#appInfo.appInfoStatus==1">审核通过</s:elseif>
						<s:elseif test="#appInfo.appInfoStatus==2">审核驳回</s:elseif>
						<s:elseif test="#appInfo.appInfoStatus==3">草稿</s:elseif>
						<s:elseif test="#appInfo.appInfoStatus==4">已上线</s:elseif>
						<s:elseif test="#appInfo.appInfoStatus==5">已下线</s:elseif>
					</td>
					<td><s:property value="#appInfo.appInfoCreater"/> </td>
					<td><s:property value="#appInfo.appInfoCreateTimeDsp"/></td>
					<td><s:property value="#appInfo.appInfoChecher"/></td>
					<td><s:property value="#appInfo.appInfoCheckTimeDsp"/></td>
					<td><s:property value="#appInfo.appInfoPublisher"/></td>
					<td><s:property value="#appInfo.appInfoPublishTimeDsp"/></td>
					<td>
					<s:if test="#appInfo.appInfoStatus==4">
						<a  class="inLine" href="app/appAction!unpublishAppInfo.do?vo.appInfoId=<s:property value="#appInfo.appInfoId"/>" target="ajaxTodo" title="确定下线该应用?"  width="600" height="360" mask="true"><span class="btnUninstall"></span><span>&nbsp;下线&nbsp;</span></a>
					</s:if>
					<s:else>
						<a  class="inLine" href="app/appAction!publishAppInfo.do?vo.appInfoId=<s:property value="#appInfo.appInfoId"/>" target="ajaxTodo" title="确定上线该应用?"  width="600" height="360" mask="true"><span class="btnPublish"></span><span>&nbsp;上线&nbsp;</span></a>
					</s:else>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
		</div>
		<div style="height: 300px; left: 57px; display: none;"
			class="resizeMarker"></div>
		<div style="height: 300px; left: 377px; display: none;"
			class="resizeProxy"></div>
	</div>
	
	<div class="panelBar">
		<div class="pages">
			<span>共<s:property value="totalCount"/>条</span>
		</div>
		
		<div class="pagination" targetType="<s:property value='targetType'/>" totalCount="<s:property value='totalCount'/>" numPerPage="<s:property value='numPerPage'/>" pageNumShown="<s:property value='pageNumShown'/>" currentPage="<s:property value='pageNum'/>"></div>

	</div>
</div>