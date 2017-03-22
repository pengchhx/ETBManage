<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
$("#modify").click(
		function(event) {
			//var $this = $(this);
			//var href = "ad/adPackage!adModify.do?adPackage.adPackageId={adPackageId}";
			var url = unescape($("#modify1").attr("href")).replaceTmById(
					$(event.target).parents(".unitBox:first"));
			var appInfoId = unescape("{appInfoId}").replaceTmById(
					$(event.target).parents(".unitBox:first"));
			if (!url.isFinishedTm()) {
				alertMsg.error(DWZ.msg("alertSelectMsg"));
				return false;
			} else {
				$.ajax({
					type : "post",
					cache : false,
					url : "app/appAction!getAppStatusById.do",
					data : "vo.appInfoId="+appInfoId,
					datatype : "json",
					success : function(data) {
						if (data.status == "1") {//已审核
							alertMsg.error("资料已审核通过，不可修改！");
						} else if (data.status == "4") {//已上线
							alertMsg.error("资料已上线，不可修改！");
						} else if (data.status == "5") {//已上线
							alertMsg.error("资料已下线，不可修改！");
						}else if (data.status == "0") {//已上线
							alertMsg.error("资料已提交待审核，不可修改！");
						} else {
							$("#modify1").click();
						}
					}

				});
			}

		});
</script>
<s:form id="pagerForm" action="app/appAction!listAppInfo.do" method="post" theme="simple">
	
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>
	<s:hidden name="vo.appInfoName"></s:hidden>
	<s:hidden name="vo.appTypesId"></s:hidden>
</s:form>


<div class="pageHeader">
	<s:form id="appInfoListForm" name="appInfoListForm" action="app/appAction!listAppInfo.do" method="post" theme="simple" onsubmit="return navTabSearch(this);">
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="app/appAction!toAdd.do" target="dialog"  title="添加应用信息" width="600" height="400" fresh="false" mask="true"><span>添加</span></a></li>
			<li><a class="delete" href="app/appAction!deleteAppInfo.do?vo.appInfoId={appInfoId}" target="ajaxTodo" title="确定删除该应用?"><span>删除</span></a></li>
			<li><a class="edit" id="modify"><span>修改</span></a><a class="edit" id="modify1" style="display: none;" href="app/appAction!toUpdate.do?vo.appInfoId={appInfoId}" target="dialog"  title="修改应用信息" width="600" height="450" fresh="false" mask="true"><span>修改</span></a></li>
		</ul>
	</div>
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
						<s:elseif test="#appInfo.appInfoStatus==3">未提交</s:elseif>
						<s:elseif test="#appInfo.appInfoStatus==4">已上线</s:elseif>
						<s:elseif test="#appInfo.appInfoStatus==5">已下线</s:elseif>
					</td>
					<td><s:property value="#appInfo.appInfoCreater"/> </td>
					<td><s:property value="#appInfo.appInfoCreateTimeDsp"/> </td>
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