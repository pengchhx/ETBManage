<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form id="pagerForm" action="ad/adPackage!listAdCheck.do"
	method="post" theme="simple">

	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>

	<s:hidden name="showSightFlag"></s:hidden>
	<s:hidden name="adPackage.adPackageName" />
</s:form>


<div class="pageHeader">
	<s:form id="adCheckListForm" name="adCheckListForm"
		action="ad/adPackage!listAdCheck.do" method="post" theme="simple"
		onsubmit="return navTabSearch(this);">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>广告包名称：<s:textfield name="adPackage.adPackageName"
							width="200px"></s:textfield></td>
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
	</s:form>
</div>
<div class="pageContent">
	<div class="grid">
		<div class="gridHeader">
			<div class="gridThead">
				<table class="table" width="100%" layoutH="138">
					<thead>
						<tr align="center">
							<th width="80px">批次号</th>
							<th width="80px">模板名称</th>
							<th>广告包名称</th>
							<th width="85px">广告包状态</th>
							<th width="80px">创建人</th>
							<th width="80px">创建时间</th>
							<th width="80px">审核人</th>
							<th width="80px">审核时间</th>
							<th width="58px">操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="adList" id="ad">
							<tr>
								<td><s:property value="#ad[0].adPackageBatchNo" /></td>
								<td><s:property value="#ad[1].adTemplateName" /></td>
								<td title="${ad[0].adPackageName}"><s:property
										value="#ad[0].adPackageName" /></td>
								<td><s:property value="#ad[0].adPackageStatusDsp" /></td>
								<td><s:property value="#ad[0].adPackageCreater" /></td>
								<td><s:property value="#ad[0].adPackageCreaterTimeDsp" /></td>
								<td><s:property value="#ad[0].adPackageChecker" /></td>
								<td><s:property value="#ad[0].adPackageCheckTimeDsp" /></td>
								<td><a class="inLine"
									href="ad/adPackage!toCheck.do?adPackage.adPackageId=<s:property value='#ad[0].adPackageId'/>"
									target="navTab" fresh="false" resizable="false" mixable=true
									rel="toCheck" title="广告审核"><span class="btnCheck"></span><span>&nbsp;审核&nbsp;</span></a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="panelBar">
		<div class="pages">
			<%-- 			<span>显示</span> --%>
			<%-- 			<s:select name="numPerPage" cssClass="combox" --%>
			<%-- 				list="#{30:'30',50:'50',100:'100',200:'200'}" --%>
			<%-- 				onchange="navTabPageBreak({numPerPage:this.value})"></s:select> --%>
			<span>共<s:property value="totalCount" />条
			</span>
		</div>

		<div class="pagination" targetType="<s:property value='targetType'/>"
			totalCount="<s:property value='totalCount'/>"
			numPerPage="<s:property value='numPerPage'/>"
			pageNumShown="<s:property value='pageNumShown'/>"
			currentPage="<s:property value='pageNum'/>"></div>

	</div>
</div>