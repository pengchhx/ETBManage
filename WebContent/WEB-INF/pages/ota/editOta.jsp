<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
<!--
#editOta p {
	width: 500px;
}
-->
</style>

<div class="pageContent">
	<s:form id="editOtaForm" theme="simple" method="post"
		action="ota/otaedit!submitOta.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone,confirmMsg);">
		<s:hidden name="otaInfo.infostat" id="edit_stat"></s:hidden>
		<s:hidden name="otaInfo.datastat"></s:hidden>
		<s:hidden name="otaInfo.id"></s:hidden>
		<s:hidden name="otaInfo.name"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<div style="width: 98%;" id="editOta">
				<div class="titleBar">
					<div class="titleTd">
						<b>版本修改</b>
					</div>
				</div>
				<p>
					<label>OTA版本名称：</label> <input alt="请输入字母,数字,下划线或中文"
						name="otaInfo.name" id="otaInfo.name" type="text"
						class="required characterCheck" readonly="readonly"
						disabled="disabled"
						remote="ota/versioninfo!nameIsExist.do?otaInfo.id=<s:property value='otaInfo.id'/>"
						value="<s:property value='otaInfo.name'/>" maxlength="100" />
				</p>
				<s:if test="otaInfo.infostat==3">
					<p>
						<label>审核人：</label>
						<s:textfield name="otaInfo.checker" readonly="true"
							disabled="disabled"></s:textfield>
					</p>
				</s:if>
				<p style="height: 90px;">
					<label>备注说明：</label>
					<s:textarea id="bbbb" class="editor" name="otaInfo.tips"
						maxlength="200" rows="2" cols="40"
						style="width: 250px;height:80px"></s:textarea>
				</p>
				<s:if test="otaInfo.infostat==3">
					<p style="color: red; height: 90px;">
						<label>驳回意见：</label>
						<s:textarea name="otaInfo.checksuggest" readonly="true"
							style="width: 250px;color: red;height:80px" />
					</p>
				</s:if>
			</div>
			<div id="edit_packlist" style="float:left;width:100%;"></div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" onclick="saveOta();">&nbsp;保存草稿&nbsp;</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" onclick="submitOta();">&nbsp;提交&nbsp;</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close" onclick="reloadList">&nbsp;取消&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
	<script type="text/javascript">
		var confirmMsg = "";
		$(function() {
			loadPackList();
		});
		function loadPackList() {
			$("#edit_packlist")
					.loadUrl(
							'ota/otaedit!getEditPackList.do?otaInfo.id=<s:property value="otaInfo.id"/>');
		}
		function saveOta() {
			confirmMsg = "确定保存吗？";
			//保存状态为0
			$("#edit_stat").val("0");
			$("#editOtaForm").action = "ota/otaedit!submitOta.do";
			$("#editOtaForm").submit();
		}
		function submitOta() {
			confirmMsg = "确定提交吗？";
			//提交状态为1
			$("#edit_stat").val("1");
			$("#editOtaForm").action = "ota/otaedit!submitOta.do";
			$("#editOtaForm").submit();
		}
	</script>
</div>