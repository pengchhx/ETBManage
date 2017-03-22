<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.archermind.etb.util.Constant"%>
<style>
<!--
#adCheckUrgencyForm td {
	padding-top: 10px;
}

.pageFormContent label.textLabel {
	padding-left: 0px;
	text-align: left;
	width: 250px;
}
-->
</style>

<script type="text/javascript">
	var adCheckUrgencyForm = $("#adCheckUrgencyForm");
	var confirmMsg = "";

	function check() {

		adCheckUrgencyForm.action = "ad/adPackage!check.do";
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

	<s:form id="adCheckUrgencyForm" theme="simple" method="post"
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
			<div class="adPackageTable" id="adPackageTableUrgencyCheck">
				<div class="titleBar">
					<div class="titleTd">
						<b>紧急广告包审核</b>
					</div>
				</div>
				<p style="width: 100%;">
					<label>广告类型：</label><label class="textLabel"
						id="adTypeUrgencyCheck"></label>
				</p>
				<p>
					<label>批次号：</label>
					<s:textfield name="adPackage.adPackageBatchNo" width="180px"
						readonly="true"></s:textfield>
				</p>
				<p>
					<label>广告包名称：</label>
					<s:textfield name="adPackage.adPackageName" readonly="true"
						width="180px"></s:textfield>
				</p>
				<p>
					<label>开始时间：</label>
					<s:textfield name="adPackage.adPackageBegintime1" readonly="true" />
				</p>
				<p>
					<label>截止时间：</label>
					<s:textfield name="adPackage.adPackageEndtime1" readonly="true" />
				</p>
				<p style="width: 100%; height: 65px" id="messageNoteUrgencyCheck">
					<label>文字通知：</label>
					<s:textarea class="editor" id="msgNoteUrgencyCheck" readonly="true"
						name="adPackage.adPackageTips"></s:textarea>
				</p>
				<s:if
					test="adPackage.adPackageCheckSuggest!=null && adPackage.adPackageCheckSuggest!=''">
					<p id="suggestNoteUrgencyCheck" >
						<label>审核意见：</label>
						<s:textarea name="adPackage.adPackageCheckSuggest"
							id="checkSuggestUrgencyCheck" readonly="true"
							style="width: 630px;" />
					</p>

				</s:if>
			</div>
			<table id="adResourceUrgencyCheck" style="width: 98%">
				<tr valign="top">
					<td><div class="titleBar">
							<div class="titleTd">
								<b>广告包素材审核</b>
							</div>
						</div></td>
				</tr>
				<tr>
					<td style="padding-top: 0px;">
						<div class="navUrgency">
							<ul>
								<s:iterator value="adResourceList" id="adResource" status="st">
									<li>
										<div class="picDivUrgency" align="center">
											<s:if test='#adResource.adResourceTypes=="1"'>
												<div class="deleteImgDivUrgencyUpd"
													id="operaUrgency<s:property value="#st.index+1" />">
													<div class="btnDivUrgencyUpd"></div>
												</div>
												<div class="updPicUrgency"
													style="position: relative; z-index: 1;">
													<a srcImg="<s:property value="adResourcePathDsp"/>"
														resourcePro='<s:property value="adResourceProduct" />'
														resourceAct='<s:property value="adResourceAction"/>'
														resourceTips='<s:property value="adResourceTips"/>'
														onclick="openDiv(this,'<%=Constant.AD_PACKAGE_BATCH_U%>');">
														<img src="<s:property value='adResourcePathDsp'/>"
														idNo="<s:property value="#st.index+1" />" alt="查看原图"
														class="resourceImgUrgency">
													</a>
												</div>

												<div class="opreaDivUrgencyUpdSetting"
													id="operaParamUrgencyCheck<s:property value="#st.index+1" />">
													<font>播放时长：<s:property value="adResourceTimes" /> 秒
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
	<script type="text/javascript">
		$(function() {
			//文字广告和图片广告UI控制
// 			if ('<s:property value="adPackage.adPackageTypeUrgency"/>' == "6") {//图片广告
			if ('<s:property value="adPackage.adTemplateId"/>' == '<%=Constant.AD_TEMPLATE_URGENCY_PIC%>') {//图片广告
				$("#messageNoteUrgencyCheck").hide();
				$("#adResourceUrgencyCheck").show();
				$("#adTypeUrgencyCheck").text("图片广告");

				$("#suggestNoteUrgencyCheck").css("width", "760px");
				$("#suggestNoteUrgencyCheck").css("height", "60px");
				
				/* if ('<s:property value="adPackage.adPackageCheckSuggest"/>' != ''
						&& '<s:property value="adPackage.adPackageCheckSuggest"/>' != null) {
					$("#adPackageTableUrgencyCheck").css("height", "33%");
				}else{
					$("#adPackageTableUrgencyCheck").css("height", "20%");
				} */

			} else {
				$("#messageNoteUrgencyCheck").show();
				$("#adResourceUrgencyCheck").hide();
				$("#adTypeUrgencyCheck").text("文字广告");

				$("#adPackageTableUrgencyCheck p").css("width", "100%");
				$("#msgNoteUrgencyCheck").css("width", "250px");
				$("#checkSuggestUrgencyCheck").css("width", "250px");

			}
		});

		//到审核意见输入画面
		function toUnCheck() {
			$("#unCheckBtn").click();
		}

		$(document).ready(
				function() {
					$("#adResourceUrgencyCheck .updPicUrgency img").each(
							function(i) {

								//设置播放时长和播放次数显示
								var divPamObj = $('#operaParamUrgencyCheck'
										+ $(this).attr("idNo"));
								divPamObj.css({
									position : "absolute",
									left : $(this).position().left,
									top : $(this).position().top + 161
								});
								$(this).parent().append(divPamObj);
							});
					
					//选中的资源图片高亮显示
					$("#adResourceUrgencyCheck .updPicUrgency").click(function(){
						$("#adResourceUrgencyCheck .updPicUrgency").each(
								function(i) {
									$(this).find(".resourceImgUrgency").removeClass(
											"resourceImgSelected");
								});
						$(this).find(".resourceImgUrgency").addClass("resourceImgSelected");
					});
				});
	</script>
</div>