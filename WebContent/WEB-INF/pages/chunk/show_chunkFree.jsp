<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form id="pagerForm"
	action="chunk/chunkAction!showFreeChunk.do" method="post"
	theme="simple">
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>
	<s:hidden name="vo.chunkId"></s:hidden>
</s:form>

<div class="pageHeader">
	<s:form id="chunkForm" name="chunkForm"
		action="chunk/chunkAction!showFreeChunk.do" method="post"
		onsubmit="return dwzSearch(this, 'dialog');" theme="simple">
		<s:hidden name="pageNum"></s:hidden>
		<div class="searchBar">
			<table class="searchContent" style="table-layout: fixed">
				<tr>
					<td>设备名称：<s:textfield name="vo.chunkName"></s:textfield>
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
				<table class="table" style="width: 100%;" layoutH="120">
					<thead>
						<tr>
							<th>设备ID</th>
							<th>地址</th>
							<th>备用地址</th>
							<th>状态</th>
							<th>创建人</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="list" var="vo">
							<tr rel="<s:property value='#vo.chunkUrl'/>" target="reserveUrl">
								<td><s:property value='#vo.deviceId'/></td>
								<td><s:property value='#vo.chunkUrl'/></td>
								<td><s:property value='#vo.reserveUrl'/></td>
								<td>
									<s:if test="#vo.status==1">运行</s:if>
									<s:elseif test="#vo.status==2">空闲</s:elseif>
									<s:elseif test="#vo.status==3">暂停</s:elseif>
								</td>
								<td><s:property value='#vo.chunkCreateUser'/></td>
								<td><s:property value='#vo.chunkCreateTimeDsp'/></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="panelBar">
		<div class="pages">
			<span>共<s:property value="totalCount" />条
			</span>
		</div>
		<div class="pagination" targetType="<s:property value='targetType'/>"
			totalCount="<s:property value='totalCount'/>"
			numPerPage="<s:property value='numPerPage'/>"
			pageNumShown="<s:property value='pageNumShown'/>"
			currentPage="<s:property value='pageNum'/>"></div>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive">
					<div class="buttonContent">
						<button onclick="setImei();" value="关闭">确定</button>
					</div>
				</div></li>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">&nbsp;取消&nbsp;</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>
<script type="text/javascript">
	function setImei() {
		var reserveUrl = $("#reserveUrl").val();
		if (!reserveUrl) {
			alertMsg.warn("请选择设备");
			return;
		}
		//将选中值，带回父级页面input输入框中
		$("#inputReserveUrl").val(reserveUrl);
		$.pdialog.close("chunkSeleter");
	}
</script>