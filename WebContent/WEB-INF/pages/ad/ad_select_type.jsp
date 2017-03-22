<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$(function() {
		$(".button").click(function() {
			//关闭当前的模态窗口
			$.pdialog.closeCurrent();
		});
	});
</script>
<div class="pageContent">
	<s:form id="adForm" theme="simple" method="post"
		action="ad/adPackage!addAdd.do" cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone)">
		<div class=typePanelBar layoutH="37" style="border-top: 1px solid; border-color: #B8D0D6;border-bottom: 1px solid; border-color: #B8D0D6;">
			<div style="font-size: 12px; margin-top: 35px; text-align: center;"><b>请选择新增广告类型</b></div>
			<ul class="toolBar">
				<li><a class="button" href="ad/adPackage!toTemplateChoose.do"
					width="520" height="430" rel="toTemplateChoose" title="普通广告模板选择"
					target="dialog" fresh="false" resizable="false" maxable=false
					mask="true"><span>普通广告</span></a></li>
				<li><a class="button"
					href="ad/adPackage!toTemplateChooseApp.do" width="520" height="430"
					rel="toTemplateChoose" title="应用广告模板选择" target="dialog"
					fresh="false" resizable="false" maxable=false mask="true"><span>应用广告</span></a></li>
				<li><a class="button" href="ad/adPackage!toAddUrgency.do"
					target="navTab" rel="toAddUrgency" title="广告新增"><span>紧急广告</span></a></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close" style="padding-top: 5px;">
								<font>&nbsp;&nbsp;&nbsp;&nbsp;取消&nbsp;&nbsp;&nbsp;&nbsp;</font>
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</s:form>
</div>