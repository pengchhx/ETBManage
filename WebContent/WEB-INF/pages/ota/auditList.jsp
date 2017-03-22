<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>OTA release list</title>
</head>
<body>
<form id="pagerForm" method="post" action="<%=path%>/ota/versioninfo!getOtaAuditList.do">
	<input type="hidden" name="name" value="${name}">
	<input type="hidden" name="pageNum" value="${pageNum }" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>
<div class="pageHeader">
	<form id="form" onsubmit="return navTabSearch(this);" action="<%=path%>/ota/versioninfo!getOtaAuditList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					OTA版本名称：<input type="text" name="name" value="${name }" />
				</td>
				<td>
					<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>

<div class="pageContent j-resizeGrid">
	<!--  <div class="panelBar">
		<ul class="toolBar">
		<li><a class="add" href="ota/versioninfo!preAuditOta.do?otaId={otaid}"  fresh="false" target="navTab" rel="preaudit_ota_info"  ><span>审核</span></a></li>
		<li><a class="add" href="ota/versioninfo!auditPassOta.do?otaId={otaid}"  fresh="false" target="ajaxTodo"  title="确认审核通过该版本?"><span>通过</span></a></li>
		<li><a class="delete" href="ota/versioninfo!preUnpassOta.do?otaId={otaid}" target="dialog" mask=true width="600" rel="audit_ota_info"><span>驳回</span></a></li>
		<li><a class="icon"   href="ota/versioninfo!queryOta.do?otaInfo.id={otaid}"  target="dialog" mask=true  height="450" width="500" rel="see_ota_info"><span>查看</span></a></li>
	</ul>
	</div>-->
	<div class="grid">
		<div class="gridHeader">
			<div class="gridThead">
				<table style="width: 100%;" class="table" layoutH="138">
					<thead>
						<tr>
							<th width="20%">OTA版本名称</th>
							<th width="10%">版本状态</th>
							<th width="15%">创建人</th>
							<th width="15%">创建时间</th>
							<th width="15%">审核人</th>
							<th width="15%">审核时间</th>
							<th width="10%">操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="list" var="arr" status="status"  id="arr" >
						<tr target="otaid" rel="<s:property value="#arr.id" />">
						<td title="${arr.name }">${arr.name}</td>
						<td>
						<s:if test="%{#arr.infostat==0}">未提交</s:if>
						<s:if test="%{#arr.infostat==1}">待审核</s:if>
						<s:if test="%{#arr.infostat==2}">未发布</s:if>
						<s:if test="%{#arr.infostat==3}">审核驳回</s:if>
						<s:if test="%{#arr.infostat==4}">已发布</s:if>
						</td>
						<td>${arr.creater }</td>
						<td>${arr.createtimedsp }</td>
						<td>${arr.checker }</td>
						<td>${arr.checktimedsp }</td>
						<!--  <td title="${arr.tips }">${arr.tips }</td>-->
						<td><a class="inLine"
									href="ota/versioninfo!preAuditOta.do?otaId=${arr.id }"
									target="navTab" 
									rel="preaudit_ota_info" title="OTA审核"><span class="btnCheck"></span><span>&nbsp;审核&nbsp;</span></a>
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
</body>
</html>