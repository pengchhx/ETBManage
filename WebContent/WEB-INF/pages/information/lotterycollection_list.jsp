<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form id="pagerForm"
	action="information/lotteryCollection!listLotteryCollection.do"
	method="post" theme="simple">
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>
	<s:hidden name="lotteryCollection.clientUserInfoAccount"></s:hidden>
	<s:hidden name="lotteryCollection.lotteryId"></s:hidden>
</s:form>

<div class="pageHeader">
	<s:form id="lotteryCollectionForm" name="lotteryCollectionForm"
		action="information/lotteryCollection!listLotteryCollection.do"
		method="post" theme="simple" onsubmit="return navTabSearch(this);">
		<s:hidden name="pageNum"></s:hidden>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>终端用户账号： <s:textfield
							name="lotteryCollection.clientUserInfoAccount"></s:textfield>
					</td>
					<td>彩票流水号：<s:textfield name="lotteryCollection.lotteryId"></s:textfield>
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
							<th>彩票流水号</th>
							<th>彩票类型</th>
							<th>购买时间</th>
							<th>备注说明</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="lotteryCollectionList" id="lotteryCollection">
							<tr target="lotteryCollectionId"
								rel="<s:property value='#lotteryCollection.lotteryCollectionId'/>">
								<td><s:property
										value="#lotteryCollection.clientUserInfoAccount" /></td>
								<td><s:property value="#lotteryCollection.lotteryId" /></td>
								<td><s:if test="#lotteryCollection.lotteryType==\"1\"">双色球</s:if>
									<s:if test="#lotteryCollection.lotteryType==\"2\"">3D</s:if> <s:if
										test="#lotteryCollection.lotteryType==\"3\"">七彩乐</s:if>
								<td><s:date name="#lotteryCollection.lotteryBuyingTime"
										format="yyyy-MM-dd HH:mm:ss" />
								<td><s:property
										value="#lotteryCollection.lotteryCollectionTips" /></td>
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
			条
		</div>

		<div class="pagination" targetType="<s:property value='targetType'/>"
			totalCount="<s:property value='totalCount'/>"
			numPerPage="<s:property value='numPerPage'/>"
			pageNumShown="<s:property value='pageNumShown'/>"
			currentPage="<s:property value='pageNum'/>"></div>

	</div>
</div>
