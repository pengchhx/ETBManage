<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form id="pagerForm" action="app/appTypesAction!listAppTypesInfo.do" method="post" theme="simple">
	
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>
	<s:hidden name="vo.appTypesName"></s:hidden>
</s:form>


<div class="pageHeader">
	<s:form id="appTypesInfoListForm" name="appTypesInfoListForm" action="app/appTypesAction!listAppTypesInfo.do" method="post" theme="simple" onsubmit="return navTabSearch(this);">
		<s:hidden name="pageNum"></s:hidden>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						应用分类名称：<s:textfield name="vo.appTypesName"></s:textfield>
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
			<li><a class="add" href="app/appTypesAction!toAdd.do" target="dialog"  title="添加应用分类信息" width="600" height="480" fresh="false" mask="true"><span>添加</span></a></li>
			<li><a class="delete" href="app/appTypesAction!deleteAppTypesInfo.do?vo.appTypesId={appTypesId}" target="ajaxTodo" title="确定删除该应用分类?"><span>删除</span></a></li>
			<li><a class="edit" href="app/appTypesAction!toUpdate.do?vo.appTypesId={appTypesId}" target="dialog"  title="修改应用分类信息" width="600" height="480" fresh="false" mask="true"><span>修改</span></a></li>
		</ul>
	</div>
	<div class="grid">
		<div class="gridHeader">
			<div class="gridThead">
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr align="center">
				<th width="10%">应用分类名称</th>
				<th width="15%">应用分类英文名称</th>
				<th width="10%">图标</th>
				<th width="40%">备注说明</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" id="appTypesInfo">
				<tr target="appTypesId" rel="<s:property value='#appTypesInfo.appTypesId'/>">
					<td><s:property value="#appTypesInfo.appTypesName"/></td>
					<td><s:property value="#appTypesInfo.appTypesEnName"/></td>
					<td style="height: 21px;vertical-align: bottom;text-align:center;padding: 0px 0 0 0;"><div style="height: 21px;"><img src="<s:property value="#appTypesInfo.appTypesFilePathDsp"/>" style="border:solid 0px red; width: 21px;height:21px;margin: 0px 0 0 0px;padding: 0px;position:relative; text-align: center;" ></div></td>
					<td><s:property value="#appTypesInfo.appTypesTips"/></td>
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