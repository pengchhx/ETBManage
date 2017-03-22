<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.archermind.etb.util.Constant"%>
<style>
<!--
.pageFormContent #templateList ul,li {
	list-style: none;
}

.pageFormContent #templateList {
	width: 90%;
	margin: 7% 0 0 6%;
}

.pageFormContent #templateList li {
	margin: 10px;
	height: 100%;
	line-height: 90px;
	float: left;
}
-->
</style>
<script type="text/javascript">
	$(function() {

		$(".tmpSelect").click(function() {
			//关闭当前的模态窗口
			$.pdialog.closeCurrent();
		});

	});

	$(function() {
		$('#templateList ul li div').addClass('templateListdiv');

		$('#templateList ul li div').hover(function() {
			$(this).addClass('templateListHover');
			$(this).removeClass('templateListdiv');
		}, function() {
			$(this).addClass('templateListdiv');
			$(this).removeClass('templateListHover');
		});
	});
</script>
<div class="pageContent">
	<s:form id="adForm" theme="simple" method="post"
		action="ad/adPackage!toAdd.do" cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<s:hidden name="adStyle"></s:hidden>
		<div class="pageFormContent" layoutH="22" style="border-bottom: 1px solid; border-color: #B8D0D6;">
			<div id="templateList" style="text-align: center;">
				<ul>
					<s:iterator value="adTemplateList" id="adTemplate" status="c">
						<li><s:if test="adStyle==1">
								<%-- 普通广告--%>
								<div>
									<a target="navTab" title="广告新增" class="tmpSelect"
										rel="toAddCommon"
										href="ad/adPackage!toAddCommon.do?adPackage.adTemplateId=<s:property value='#adTemplate.adTemplateId'/> && adStyle=<s:property value='adStyle'/>">
										<img id='tempList<s:property value="#c.index+1"/>'
										src="<s:property value='#adTemplate.adTemplatePath'/>"
										style="height: 254px; width: 171px;" /> <br> <font><s:property
												value="#adTemplate.adTemplateName" /></font>
									</a>
								</div>
								<%-- 应用广告--%>
							</s:if> <s:elseif test="adStyle==2">
								<div style="margin-left: 25%;">
									<a target="navTab" title="广告新增" class="tmpSelect"
										rel="toAddApp"
										href="ad/adPackage!toAddApp.do?adPackage.adTemplateId=<s:property value='#adTemplate.adTemplateId'/> && adStyle=<s:property value='adStyle'/>">
										<img id='tempList<s:property value="#c.index+1"/>'
										src="<s:property value='#adTemplate.adTemplatePath'/>"
										style="height: 254px; width: 171px;" /> <br> <font><s:property
												value="#adTemplate.adTemplateName" /></font>
									</a>
								</div>
							</s:elseif></li>
					</s:iterator>
				</ul>
			</div>
		</div>
	</s:form>
</div>