<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	var appInfoReadForm = $("#appInfoReadForm");
	var confirmMsg = "";

	//到审核意见输入画面
	function unCheckBtnApp() {
		$("#unCheckBtnApp").click();
	}

	function appcheck() {
		confirmMsg = "确定审核通过吗？";
		appInfoReadForm.action = "app/appAction!checkAppInfo.do";

	}

	//刷新画面时,若为审核不通过，则关闭窗口；审核通过时，会直接关闭
	if ("<s:property value="vo.appInfoStatus" />" == "3" || "<s:property value="vo.appInfoStatus" />" == "2") {
		navTab.reloadFlag("listAppCheck");
		//navTab.closeCurrentTab("listAdCheck");
		navTab.closeNavTabId("toCheck", "listAppCheck");
	}
</script>

<div class="pageContent">
	<s:form id="appInfoReadForm" theme="simple" method="post"
		action="app/appAction!checkAppInfo.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone,confirmMsg);"
		enctype="multipart/form-data">
		<s:hidden name="vo.appInfoId"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>应用名称：</label>
				<s:textfield name="vo.appInfoName" readonly="true" ></s:textfield>
			</p>
			<p>
				<label>应用分类：</label>
				<s:textfield name="vo.appTypesName" readonly="true" ></s:textfield>
			</p>
			<p>
				<label>应用APK文件：</label>
				<a href="<s:property value="vo.resourcePathDsp"/>" target="blank"><span  class="appinfoReadSpan"><s:property value="vo.appInfoFilename"/></span></a>
			</p>
			<p>
				<label>应用版本：</label>
				<s:textfield name="vo.appInfoVersion" readonly="true" ></s:textfield>
			</p>
			<p>
				<label>创建人：</label>
				<s:textfield name="vo.appInfoCreater" readonly="true" ></s:textfield>
			</p>
			<p>
				<label>创建时间：</label>
				<s:textfield name="vo.appInfoCreateTimeDsp" readonly="true" ></s:textfield>
			</p>
			<div style="float: left; margin: 0; padding: 5px 0;">
				<div style="float: left;">
					<label>备注说明：</label>
				</div>
				<div class="combox">
					<div class="select">
						<s:textarea  name="vo.appInfoTips" maxlength="400" readonly="true"></s:textarea>
					</div>
				</div>
			</div>
			<s:if test="vo.appInfoCheckSuggest!=null">
			 <p style="margin: -15px;">
			 <div style="float: left; margin: 0; padding: 5px 0;">
				<div style="float: left;">
					<label>驳回意见：</label>
				</div>
				<div class="combox">
					<div class="select">
						<s:textarea  name="vo.appInfoCheckSuggest" maxlength="1000" readonly="true"></s:textarea>
					</div>
				</div>
			</div>
			</p>
			</s:if>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="submit" onclick="appcheck();" style="width: 60px">&nbsp;通过&nbsp;</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="unCheckBtnApp();" style="width: 60px">&nbsp;不通过&nbsp;</button>
						</div>
					</div> <a id="unCheckBtnApp" class="button" style="display: none;"
					href="app/appAction!toUncheck.do?vo.appInfoId=<s:property value="vo.appInfoId"/>"
					width="600" height="230" rel="toUncheck" title="审核意见"
					target="dialog" fresh="false" resizable="false" mixable=true
					mask="true"><span>&nbsp;不通过&nbsp;</span></a></li>
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