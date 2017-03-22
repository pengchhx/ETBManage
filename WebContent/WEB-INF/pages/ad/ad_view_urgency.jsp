<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" rel="stylesheet"
	href="js/uploadify-v2.1.4/uploadify.css"></link>
<%@ page import="com.archermind.etb.util.Constant"%>
<style>
<!--
.pageFormContent #nav li {
	margin: 10px;
	height: 100%;
	line-height: 90px;
	float: left;
	padding-left: 40px;
}

td {
	padding-top: 10px;
}

.pageFormContent label.textLabel {
	padding-left: 0px;
	text-align: left;
	width: 250px;
}
-->
</style>

<div class="pageContent">

	<s:form id="adViewUrgencyForm" theme="simple" method="post"
		action="ad/adPackage!modify.do" cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone);">

		<div class="pageFormContent" layoutH="56">
			<div class="adPackageTable" id="adPackageTableUrgencyView">
				<div class="titleBar">
					<div class="titleTd">
						<b>紧急广告包查看</b>
					</div>
				</div>
				<p style="width: 100%;">
					<label>广告类型：</label><label class="textLabel" id="adTypeUrgencyView"></label>
				</p>
				<p>
					<label>批次号：</label><label class="textLabel"> <s:textfield
							name="adPackage.adPackageBatchNo" width="180px" readonly="true"></s:textfield></label>
				</p>
				<p>
					<label>广告包名称：</label>
					<s:textfield name="adPackage.adPackageName" readonly="true"
						width="180px"></s:textfield>
				</p>
				<p>
					<label>开始时间：</label><label class="textLabel"> <s:textfield
							dateFmt="yyyy-MM-dd HH:mm:ss"
							name="adPackage.adPackageBegintime1" class="date" readonly="true" /></label>
				</p>
				<p>
					<label>截止时间：</label><label class="textLabel"> <s:textfield
							dateFmt="yyyy-MM-dd HH:mm:ss" name="adPackage.adPackageEndtime1"
							class="date" readonly="true" />
					</label>
				</p>
				<p style="width: 100%; height: 65px" id="messageNoteUrgencyView">
					<label>文字通知：</label><label class="textLabel"> <s:textarea
							class="editor" id="msgNoteUrgencyView" readonly="true"
							name="adPackage.adPackageTips"></s:textarea></label>
				</p>
				<s:if
					test="adPackage.adPackageCheckSuggest!=null && adPackage.adPackageCheckSuggest!=''">
					<p id="suggestNoteUrgencyView">
						<label>审核意见：</label><label class="textLabel"> <s:textarea
								name="adPackage.adPackageCheckSuggest"
								id="checkSuggestUrgencyView" readonly="true"
								style="width: 630px;" /></label>
					</p>

				</s:if>
			</div>
			<table id="adResourceUrgencyView" style="width: 98%">
				<tr valign="top">
					<td><div class="titleBar">
							<div class="titleTd">
								<b>广告包素材查看</b>
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
													id="operaParamUrgencyView<s:property value="#st.index+1" />">
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
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" class="close">&nbsp;返回&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
	<script type="text/javascript">
		$(function() {
			
			//文字广告和图片广告UI控制
			if ('<s:property value="adPackage.adTemplateId"/>' == '<%=Constant.AD_TEMPLATE_URGENCY_PIC%>') {//图片广告
				$("#messageNoteUrgencyView").hide();
				$("#adResourceUrgencyView").show();
				$("#adTypeUrgencyView").text("图片广告");

				$("#suggestNoteUrgencyView").css("width", "760px");
				$("#suggestNoteUrgencyView").css("height", "60px");
			} else {
				$("#messageNoteUrgencyView").show();
				$("#adResourceUrgencyView").hide();
				$("#adTypeUrgencyView").text("文字广告");

				$("#adPackageTableUrgencyView p").css("width", "100%");
				$("#msgNoteUrgencyView").css("width", "250px");
				$("#checkSuggestUrgencyView").css("width", "250px");
			}
		});

		$(document).ready(
				function() {
					$("#adResourceUrgencyView .updPicUrgency img").each(
							function(i) {

								//设置播放时长和播放次数显示
								var divPamObj = $('#operaParamUrgencyView'
										+ $(this).attr("idNo"));
								divPamObj.css({
									position : "absolute",
									left : $(this).position().left,
									top : $(this).position().top + 161
								});
								$(this).parent().append(divPamObj);
							});
					
					//选中的资源图片高亮显示
					$("#adResourceUrgencyView .updPicUrgency").click(function(){
						$("#adResourceUrgencyView .updPicUrgency").each(
								function(i) {
									$(this).find(".resourceImgUrgency").removeClass(
											"resourceImgSelected");
								});
						$(this).find(".resourceImgUrgency").addClass("resourceImgSelected");
					});
				});
	</script>
</div>