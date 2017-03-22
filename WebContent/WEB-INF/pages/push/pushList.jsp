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
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
</script>
<title>PUSH list</title>
</head>
<body>
<form id="pagerForm" method="post" action="<%=path%>/push/pushinfo!getPushList.do">
	<input type="hidden" name="msgContent" value="${msgContent}">
	<input type="hidden" name="pageNum" value="${pageNum }" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>
<div class="pageHeader">
	<form id="form" onsubmit="return navTabSearch(this);" action="<%=path%>/push/pushinfo!getPushList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					消息内容：<input type="text" name="msgContent" value="${msgContent }" />
				</td>
				<td>
				<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					<!--  <input type="submit" target="navTab" rel="otamanger" value="检索" />-->
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>


<div class="pageContent j-resizeGrid">
	<div class="grid">
		<div class="gridHeader">
			<div class="gridThead">
				<table style="width: 100%;" class="table" layoutH="138">
					<thead>
						<tr>
							<th width="25%">消息流水号</th>
							<th width="35%">消息内容</th>
							<th width="15%">过期时间</th>
							<th width="10%">创建人</th>
							<th width="15%">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="list" var="arr" status="status"  id="arr" >
						<tr target="otaid" rel="<s:property value="#arr.id" />">
						<td>${arr.id }</td>
						<td title="${arr.msgContent }">${arr.msgContent}</td>
						<td>${arr.deadLineDsp }</td>
						<td>${arr.msgCreater }</td>
						<td>${arr.msgCreateTimeDsp }</td>
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