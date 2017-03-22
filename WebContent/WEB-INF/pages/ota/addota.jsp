<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent">
	<s:form id="addotaForm" theme="simple" method="post"
		action="ota/versioninfo!addOta.do"
		cssClass="pageForm required-validate"
		onsubmit="return navTabSearchSkip(this,'确认执行下一步吗?',false,DWZ.ajaxDone,'add_ota_info');">
		<s:hidden name="otaInfo.id"></s:hidden>
		<div class="pageFormContent" layoutH="500">
			<p>
				<label>OTA版本名称：</label>
				<s:textfield name="otaInfo.name" cssClass="required characterCheck"
					maxlength="100" remote="ota/versioninfo!nameIsExist.do"
					alt="请输入字母,数字,下划线或中文"></s:textfield>
			</p>
			<p>
				<label>备注说明：</label>
				<s:textarea cols="25" rows="5" name="otaInfo.tips" maxlength="200"></s:textarea>
			</p>
		</div>
		<div class="formBar" style="border-width:0px;">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">&nbsp;下一步&nbsp;</button>
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
		<div class="formBar" style="height: 500px;"></div>
	</s:form>
</div>
