<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.archermind.etb.util.Constant" %>
<s:form id="pagerForm"
	action="information/payment!findPaymentList.do" method="post"
	theme="simple">
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>
	<s:hidden name="payment.clientUserInfoAccount"></s:hidden>
	<s:hidden name="payment.paymentId"></s:hidden>
</s:form>
<div class="pageHeader">
	<s:form id="adInformationForm" name="adInformationForm"
		action="information/payment!findPaymentList.do" method="post"
		theme="simple" onsubmit="return navTabSearch(this);">
		<s:hidden name="pageNum"></s:hidden>
		<s:hidden name="numPerPage"></s:hidden>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>终端用户账号：<s:textfield
							name="payment.clientUserInfoAccount"></s:textfield>
					</td>
					<td>缴费流水号：<s:textfield name="payment.paymentId"></s:textfield>
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

<script type="text/javascript">
	
</script>

<div class="pageContent">
<div class="grid">
		<div class="gridHeader">
			<div class="gridThead">
	<table class="table" width="100%" layoutH="120">
		<thead>
			<tr style="text-align: center">
				<th>终端用户账号</th>
				<th>缴费流水号</th>
				<th>缴费类型</th>
				<th>缴费原因</th>
				<th>缴费时间</th>
				<th>备注说明</th>
			</tr>
		</thead>
		<tbody>

			<s:iterator value="paymentList" id="payment">
				<tr target="paymentId"
					rel="<s:property value='#payment.paymentCollectionId'/>">
					<td><s:property value="#payment.clientUserInfoAccount" /></td>
					<td><s:property value="#payment.paymentId" /></td>
					<td>
					<s:if test="#payment.paymentType == 1"><%=Constant.PAYMENT_TYPE_1%></s:if>
					<s:elseif test="#payment.paymentType == 2"><%=Constant.PAYMENT_TYPE_2 %></s:elseif>
					<s:else><%=Constant.PAYMENT_TYPE_3 %></s:else>
					</td>
					<td><s:property value="#payment.paymentReason" /></td>
					<td><s:date name="#payment.paymentTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value="#payment.paymentCollectionTips" /></td>
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