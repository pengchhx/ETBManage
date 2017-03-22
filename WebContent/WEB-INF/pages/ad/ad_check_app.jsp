<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.archermind.etb.util.Constant"%>
<style>
<!--
#adCheckAppForm td {
	padding-top: 10px;
}
-->
</style>

<script type="text/javascript">
	var adCheckAppForm = $("#adCheckAppForm");
	var confirmMsg = "";

	//到审核意见输入画面
	function toUnCheck() {
		$("#unCheckBtn").click();
	}

	function check() {

		adCheckAppForm.action = "ad/adPackage!check.do";
		confirmMsg = "确定审核通过吗？";

	}

	//刷新画面时，判断广告包状态，若为审核不通过，则关闭窗口；审核通过时，会直接关闭
	if ("<s:property value="adPackage.adPackageStatus" />" == "<%=Constant.AD_PACKAGE_STATUS_CHECK_UNPASSED%>") {
		navTab.reloadFlag("listAdCheck");
		//navTab.closeCurrentTab("listAdCheck");
		navTab.closeNavTabId("toCheck", "listAdCheck");
	}
</script>

<div class="pageContent">

	<s:form id="adCheckAppForm" theme="simple" method="post"
		action="ad/adPackage!check.do" cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone,confirmMsg);">

		<s:hidden name="adPackage.adPackageId" id="adPackageId" />
		<s:hidden name="adPackage.adTemplateId" />
		<%-- 		<s:hidden name="adPackage.adPackageName" /> --%>
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
		<%-- 		<s:hidden name="adPackage.adPackageTips" /> --%>
		<s:hidden name="adPackage.dataStat" />
		<%-- 		<s:hidden name="adPackage.adPackageBatchNo" /> --%>
		<%-- 		<s:hidden name="adPackage.adPackageCheckSuggest" /> --%>

		<div class="pageFormContent" layoutH="56">
			<div class="adPackageTable" style="width: 98%;">
				<div class="titleBar">
					<div class="titleTd">
						<b>应用广告包审核</b>
					</div>
				</div>
				<p>
					<label>批次号：</label>
					<s:textfield name="adPackage.adPackageBatchNo" width="180px"
						readonly="true"></s:textfield>
				</p>
				<p>
					<label>广告包名称：</label>
					<s:textfield name="adPackage.adPackageName" width="180px"
						readonly="true"></s:textfield>
				</p>
				<p>
					<label>开始时间：</label>
					<s:textfield name="adPackage.adPackageBegintime1" readonly="true" />
				</p>
				<p>
					<label>截止时间：</label>
					<s:textfield name="adPackage.adPackageEndtime1" readonly="true" />
				</p>
				<s:if
					test="adPackage.adPackageCheckSuggest!=null && adPackage.adPackageCheckSuggest!=''">
					<p style="height: 70px;">
						<label>文字通知：</label>
						<s:textarea class="editor" name="adPackage.adPackageTips"
							style="width: 250px;" readonly="true"></s:textarea>
					</p>
					<p style="height: 70px;">
						<label>审核意见：</label>
						<s:textarea name="adPackage.adPackageCheckSuggest" readonly="true"
							style="width: 250px;" />
					</p>
				</s:if>
				<s:else>
					<p style="width: 760px; height: 70px;" id="wordMsg">
						<label>文字通知：</label>
						<s:textarea class="editor" name="adPackage.adPackageTips"
							style="width: 630px;" readonly="true"></s:textarea>
					</p>
				</s:else>
			</div>
			<table style="width: 98%" id="adPackageTableAppCheck">
				<tr valign="top">
					<td><div class="titleBar">
							<div class="titleTd">
								<b>广告包素材审核</b>
							</div>
						</div></td>
				</tr>
				<tr>
					<td style="padding-top: 0px;">
						<div class="navApp">
							<ul>
								<s:iterator value="adResourceList" id="adResource" status="st">
									<li>
										<div class="picDiv" align="center">
											<s:if test='#adResource.adResourceTypes=="1"'>
												<div class="updPic" style="position: relative; z-index: 1;">
													<a srcImg="<s:property value="adResourcePathDsp"/>"
														resourcePro='<s:property value="adResourceProduct" />'
														resourceAct='<s:property value="adResourceAction"/>'
														resourceTips='<s:property value="adResourceTips"/>'
														onclick="openDiv(this,'<%=Constant.AD_PACKAGE_BATCH_E%>');">
														<img src="<s:property value='adResourcePathDsp'/>"
														idNo="<s:property value="#st.index+1" />" alt="查看原图"
														class="resourceImgApp">
													</a>
												</div>
												<div class="opreaDivAppUpdSetting"
													id="operaParamList<s:property value="#st.index+1" />">
													<font>播放时长： <s:property value="adResourceTimes" />
														秒
													</font>
												</div>
											</s:if>
										</div>
									</li>
								</s:iterator>
							</ul>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="submit" onclick="check();" style="width: 60px">&nbsp;通过&nbsp;</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="toUnCheck();" style="width: 60px">&nbsp;不通过&nbsp;</button>
						</div>
					</div> <a id="unCheckBtn" class="button" style="display: none;"
					href="ad/adPackage!toAdCheckSuggest.do?adPackage.adPackageId=<s:property value="adPackage.adPackageId"/>"
					width="600" height="230" rel="toAdCheckSuggest" title="审核意见"
					target="dialog" fresh="false" resizable="false" mixable=true
					mask="true"><span>&nbsp;不通过&nbsp;</span></a></li>
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
	$(document).ready(function() {
		$("#adPackageTableAppCheck .updPic img").each(function(i) {
			//设置播放时长和播放次数显示
			var divPamObj = $('#adPackageTableAppCheck #operaParamList' + $(this).attr("idNo"));
			divPamObj.css({
				position : "absolute",
				left : $(this).position().left,
				top : $(this).position().top + 79
			});
			$(this).parent().append(divPamObj);
		});

		$("#adPackageTableAppCheck a").each(function() {
			$(this).attr("hidefocus", true);
		});
		
		//选中的资源图片高亮显示
		$("#adPackageTableAppCheck .updPic").click(function(){
			$("#adPackageTableAppCheck .updPic").each(
					function(i) {
						$(this).find(".resourceImgApp").removeClass(
								"resourceImgSelected");
					});
			$(this).find(".resourceImgApp").addClass("resourceImgSelected");
		});
	});
	
</script>
