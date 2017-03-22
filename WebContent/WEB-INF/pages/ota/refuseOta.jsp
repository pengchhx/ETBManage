<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent">
	<s:form id="refuseForm" theme="simple" method="post"
		action="ota/versioninfo!auditUnpassOta.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<s:hidden name="otaInfo.id"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			  <p>
				<label>驳回意见：</label>
				<s:textarea cols="25" rows="5" name="otaInfo.checksuggest" cssClass="required" maxlength="1000"></s:textarea>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">&nbsp;确定&nbsp;</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">&nbsp;取消&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
</div>
