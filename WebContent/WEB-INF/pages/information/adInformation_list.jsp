<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<s:form id="pagerForm"
	action="information/adInformation!findAdInformationList.do" method="post"
	theme="simple">
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>
	<s:hidden name="adInformation.clientUserInfoAccount"></s:hidden>
	<s:hidden name="adInformation.adCollectionContent"></s:hidden>
</s:form>
<div class="pageHeader">
	<s:form id="adInformationForm" name="adInformationForm"
		action="information/adInformation!findAdInformationList.do" method="post"
		theme="simple" onsubmit="return navTabSearch(this);">
		<s:hidden name="pageNum"></s:hidden>
        <s:hidden name="numPerPage"></s:hidden>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>终端用户账号：<s:textfield
							name="adInformation.clientUserInfoAccount"></s:textfield>
					</td>
					<td>广告内容：<s:textfield name="adInformation.adCollectionContent"></s:textfield>
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
	</s:form>
</div>
<div class="pageContent">
<div class="grid">
		<div class="gridHeader">
			<div class="gridThead">

	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr style="text-align: center">
				<th>终端用户账号</th>
				<th>广告编号</th>
				<th>广告内容</th>
				<th>广告点击时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="adInformationList" id="adInformation">
				<tr target="adInformationId"
					rel="<s:property value='#adInformation.adCollectionId'/>">
					<td><s:property value="#adInformation.clientUserInfoAccount" /></td>
					<td><s:property value="#adInformation.adId" /></td>
					<td><s:property value="#adInformation.adCollectionContent" /></td>
				    <td><s:date name="#adInformation.adCollectionClickTime" format="yyyy-MM-dd HH:mm:ss" ></s:date></td>
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