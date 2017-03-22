<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
%>

<s:form id="pagerForm" action="ad/adPackage!listAdPublish.do"
	method="post" theme="simple">

	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>

	<s:hidden name="adPackage.adPackageId" />
	<s:hidden name="adPackage.adPackageName"></s:hidden>
</s:form>


<div class="pageHeader">
	<s:form id="adForm" name="adForm"
		action="ad/adPackage!listAdPublish.do" method="post" theme="simple"
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
							<th width="70px">批次号</th>
							<th width="80px">模板名称</th>
							<th>广告包名称</th>
							<th width="60px">广告包状态</th>
							<th width="70px">创建人</th>
							<th width="80px">创建时间</th>
							<th width="70px">审核人</th>
							<th width="80px">审核时间</th>
							<th width="70px">发布人</th>
							<th width="80px">发布时间</th>
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
								<td>
									<!-- 1-审核通过-显示状态为“下线”；4-已发布 -显示状态为“上线” --> <s:if
										test="#ad[0].adPackageStatus==1">未发布</s:if> <s:elseif
										test="#ad[0].adPackageStatus==4">已发布</s:elseif>
								</td>
								<td><s:property value="#ad[0].adPackageCreater" /></td>
								<td><s:property value="#ad[0].adPackageCreaterTimeDsp" /></td>
								<td><s:property value="#ad[0].adPackageChecker" /></td>
								<td><s:property value="#ad[0].adPackageCheckTimeDsp" /></td>
								<td><s:property value="#ad[0].adPackagePublisher" /></td>
								<td><s:property value="#ad[0].adPackagePublishTimeDsp" /></td>
								<td valign="middle">
									<!-- 1-审核通过-显示button为“发布” --> <s:if
										test="#ad[0].adPackageStatus==1">
										<a mask="true" class="inLine" width="600" height="360"
											href="ad/adPackage!toPublish.do?adPackage.adPackageId=<s:property value='#ad[0].adPackageId'/>"
											target="navTab" rel="toPublish" title="广告发布"><span
											class="btnPublish"></span><span>&nbsp;发布&nbsp;</span></a>
									</s:if>
									<s:elseif
										test="#ad[0].adPackageStatus==4">
										<a mask="true" class="inLine" width="600" height="360"
											href="ad/adPackage!toAdView.do?adPackage.adPackageId=<s:property value='#ad[0].adPackageId'/>"
											target="navTab" rel="toPublish" title="广告查看"><span
											class="btnView"></span><span>&nbsp;查看&nbsp;</span></a></s:elseif>
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