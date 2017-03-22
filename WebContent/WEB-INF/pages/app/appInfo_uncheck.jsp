<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="pageContent">
	<s:form id="appInfoUncheckForm" theme="simple" method="post"
		action="app/appAction!uncheckAppInfo.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<s:hidden name="vo.appInfoId"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<div style="float: left; margin: 0; padding: 5px 0;">
				<%-- <div style="float: left;">
					<label>驳回意见：</label>
				</div>
				<div class="combox">
					<div class="select">
						<s:textarea cssClass="required" name="vo.appInfoCheckSuggest"
							maxlength="1000" required=""></s:textarea>
					</div>
				</div> --%>

				<p>
					<label>驳回意见：</label>
					<s:textarea class="editor" rows="2" cssClass="required" cols="40"
						name="vo.appInfoCheckSuggest" maxlength="1000" />
				</p>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">&nbsp;确定&nbsp;</button>
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
	</s:form>
</div>