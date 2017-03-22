<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
<!--
span.error { /* display:block; */
	overflow: hidden;
	display: block;
	width: 165px;
	height: 21px;
	line-height: 21px;
	margin-left: 2px;
	padding: 0 5px;
	background: #F00;
	top: 5px;
	color: #FFF;
	z-index: 99;
	position: absolute;
	left: 322px;
}
-->
</style>

<div class="pageContent">
	<s:form id="adCheckSuggestForm" theme="simple" method="post"
		action="ad/adPackage!unCheck.do" cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">

		<s:hidden name="adPackage.adPackageId" id="adPackageId" />
		<s:hidden name="adPackage.adTemplateId" />
		<s:hidden name="adPackage.adPackageName" />
		<s:hidden name="adPackage.adPackagePath" />
		<s:hidden name="adPackage.adPackageCreater" />
		<s:hidden name="adPackage.adPackageCreaterTime" />
		<s:hidden name="adPackage.adPackageStatus" id="adPackageStatus" />
		<s:hidden name="adPackage.adPackageChecker" />
		<s:hidden name="adPackage.adPackageCheckTime" />
		<s:hidden name="adPackage.adPackagePublisher" />
		<s:hidden name="adPackage.adPackagePublishTime" />
		<s:hidden name="adPackage.adPackageBegintime" />
		<s:hidden name="adPackage.adPackageEndtime" />
		<s:hidden name="adPackage.adPackageTips" />
		<s:hidden name="adPackage.dataStat" />
		<s:hidden name="adPackage.adPackageBatchNo" />

		<div class="pageFormContent" layoutH="56">
			<p>
				<label>审核意见：</label>
				<s:textarea class="editor" rows="2" cssClass="required" cols="40"
					name="adPackage.adPackageCheckSuggest" maxlength="1000" />
			</p>
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