<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<form id="pagerForm" method="post" action="push/pushinfo!showEtbClientDevice.do" theme="simple">
	<input type="hidden" name="imei" value="${imei}">
	<input type="hidden" name="pageNum" value="${pageNum }" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>
<div class="pageHeader">
	<s:form id="deviceListForm" name="deviceListForm"
		action="push/pushinfo!showEtbClientDevice.do" method="post"
		onsubmit="return dwzSearch(this, 'dialog');" theme="simple">
		<s:hidden name="pageNum"></s:hidden>
		<div class="searchBar">
			<table class="searchContent" style="table-layout: fixed">
				<tr>
					<td>终端设备编号：<s:textfield name="imei"></s:textfield>
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
							<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
							<th>终端设备编号</th>
							<th>终端设备型号</th>
							<th>终端设备MAC地址</th>
							<th>终端设备IP地址</th>
							<th>终端设备系统版本</th>
							<th>备注说明</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="etbClientDeviceList" var="list">
							<tr rel="${list.imei}" target="deviceId">
								<td><input name="ids" value="${list.imei}" type="checkbox"></td>
								<td class="cimei">${list.imei}</td>
								<td>${list.name}</td>
								<td>${list.addr}</td>
								<td>${list.ip}</td>
								<td>${list.os}</td>
								<td>${list.tips}</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>

		<div class="panelBar">
		<div class="pages">
			<span>共<s:property value="totalCount"/>条</span>
		</div>
		<div class="pagination" targetType="<s:property value='targetType'/>" totalCount="<s:property value='totalCount'/>" numPerPage="<s:property value='numPerPage'/>" pageNumShown="<s:property value='pageNumShown'/>" currentPage="<s:property value='pageNum'/>"></div>

	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive">
					<div class="buttonContent">
						<button onclick="setPushDevice();" value="关闭">确定</button>
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
	function setPushDevice() {
		var checkedDevices = $('input[name="ids"]:checked');
		if(checkedDevices.length==0)
		{
			alertMsg.warn("请至少选择一个设备");
			return;
		}
		var returnDevices="";
		for(var i=0;i<checkedDevices.length;i++){
			var tmpDevice = $(checkedDevices[i]);
			var isCheck = checkIsSelected(tmpDevice.val()); 
			if(i==0)
			{
				if(isCheck)
				{
					returnDevices+=tmpDevice.val();
				}
			}else
			{
				if(isCheck)
				{
					returnDevices+=","+tmpDevice.val();
				}
			}
		}
		if(returnDevices.substring(0, 1)==",")
		{
			returnDevices = returnDevices.substring(1, returnDevices.length);
		}
		//将选中值，带回父级页面input输入框中
		var curDevices =$("#clientdevice").val();
		if(!curDevices)
		{
			$("#clientdevice").val(returnDevices);
		}else
		{
			if(returnDevices)
			{
				$("#clientdevice").val(curDevices+","+returnDevices);
			}
		}
		$.pdialog.close("deviceChoice"); 
	}
	//判断已经选择的设备中是否包含当前选择的device
	function checkIsSelected(str)
	{
		//当前选择的设备
		var checkedDevices =$("#clientdevice").val();
		if(!checkedDevices)
		{
			return true;
		}else
		{
			var devices = checkedDevices.split(",");
			for(var i=0;i<devices.length;i++)
			{
				var tmp=devices[i];
				if(tmp==str)
				{
					return false;
					break;
				}
			}
			return true;
		}
		
	}
</script>