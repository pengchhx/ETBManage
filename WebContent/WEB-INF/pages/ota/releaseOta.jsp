<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" rel="stylesheet"
	href="js/uploadify-v2.1.4/uploadify.css"></link>
<%@ page import="com.archermind.etb.util.Constant"%>
<style>
<!--
td {
	padding-top: 10px;
}
-->
</style>

<script type="text/javascript">
	var otaCheckForm = $("#otaCheckForm");
	var confirmMsg = "";

	//审核
	function check() {

		otaCheckForm.action = "ad/adPackage!check.do";
		confirmMsg = "确定审核通过吗？";

	}

	//到审核意见输入画面
	function toUnCheck() {
		$("#unCheckBtn").click();
	}

	//刷新画面时，判断OTA审核状态，若为审核不通过，则关闭窗口；审核通过时，会直接关闭
	if ("<s:property value="otaInfo.infostat" />" == 3) {
		navTab.reloadFlag("auditOta");
		//navTab.closeCurrentTab("preaudit_ota_info");
		navTab.closeNavTabId("preaudit_ota_info", "auditOta");
	}
</script>

<div class="pageContent">

	<s:form id="otaCheckForm" theme="simple" method="post"
		action="ota/versioninfo!releaseOta.do" cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone,confirmMsg);">
		<s:hidden name="otaInfo.id"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<div class="adPackageTable" style="width: 98%; height: 35%;">
				<div class="titleBar">
					<div class="titleTd">
						<b>版本信息</b>
					</div>
				</div>
				<p>
					<label>版本名称：</label>
					<s:textfield name="otaInfo.name" width="180px"
						readonly="true"></s:textfield>
				</p>
				<p>
					<label>版本状态：</label>
					<s:if test="otaInfo.infostat==0"><input type="text" readonly="true" value="未提交"></s:if>
					<s:if test="otaInfo.infostat==1"><input type="text" readonly="true" value="待审核"></s:if>
					<s:if test="otaInfo.infostat==2"><input type="text" readonly="true" value="未发布"></s:if>
					<s:if test="otaInfo.infostat==3"><input type="text" readonly="true" value="审核驳回"></s:if>
					<s:if test="otaInfo.infostat==4"><input type="text" readonly="true" value="已发布"></s:if>
				</p>
				<p>
					<label>创建人：</label>
					<s:textfield name="otaInfo.creater" readonly="true" />
				</p>
				<p>
					<label>创建时间：</label>
					<s:textfield name="otaInfo.createtimedsp" readonly="true" />
				</p>
					<p>
						<label>审核人：</label>
						<s:textfield name="otaInfo.checker" readonly="true" />
					</p>
					<p>
						<label>审核时间：</label>
						<s:textfield name="otaInfo.checktimedsp" readonly="true" />
					</p>
					<p style="width: 100%;">
						<label>备注：</label>
						<s:textarea name="otaInfo.tips" readonly="true"
							style="width: 630px;" />
					</p>
			</div>
			<table style="width: 98%;">
				<tr valign="top">
					<td><div class="titleBar">
							<div class="titleTd">
								<b>升级包信息</b>
							</div>
						</div></td>
				</tr>
				<tr>
					<td>
						<table class="table" width="100%">
							<thead>
								<tr>
									<th width="120">升级包名称</th>
									<th width="100">目标版本名称</th>
									<th width="100">初始版本名称</th>
									<th width="100">下载地址</th>
									<th width="100">类型</th>
									<th width="100">备注说明</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="packList" var="arr" status="status" id="arr">
									<tr target="packid" rel="<s:property value="#arr.id" />">
										<td title="${arr.packname }">${arr.packname}</td>
										<td title="${arr.targetname }">${arr.targetname}</td>
										<td title="${arr.initname }">${arr.initname}</td>
										<td><a href="${arr.packUploadPath}" target="blank"><span  class="appinfoReadSpan">${arr.packShowPath}</span></a></td>
										<td><s:if test="%{#arr.packtype==0}">完整包</s:if> <s:if
												test="%{#arr.packtype==1}">差分包</s:if></td>
										<td title="${arr.tips }">${arr.tips}</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="submit"  style="width: 60px">&nbsp;发布&nbsp;</button>
						</div>
					</div>
				</li>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" class="close" style="width: 60px">&nbsp;取消&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
</div>
<script type="text/javascript">

</script>