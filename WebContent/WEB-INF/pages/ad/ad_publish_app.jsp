<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.archermind.etb.util.Constant"%>
<style>
<!--
#adPublishAppForm td {
	padding-top: 10px;
}
-->
</style>

<script type="text/javascript">
	var adPublishAppForm = $("#adPublishAppForm");
	var confirmMsg = "确定要发布吗？";

</script>

<div class="pageContent">

	<s:form id="adPublishAppForm" theme="simple" method="post"
		action="ad/adPackage!publish.do" cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone,confirmMsg);">

		<s:hidden name="adPackage.adPackageId"></s:hidden>
		<s:hidden name="adPackage.adTemplateId"></s:hidden>
		<s:hidden name="adPackage.adPackageName"></s:hidden>
		<s:hidden name="adPackage.adPackagePath"></s:hidden>
		<s:hidden name="adPackage.adPackageCreater"></s:hidden>
		<s:hidden name="adPackage.adPackageCreaterTime"></s:hidden>
		<s:hidden name="adPackage.adPackageStatus"></s:hidden>
		<s:hidden name="adPackage.adPackageChecker"></s:hidden>
		<s:hidden name="adPackage.adPackageCheckTime"></s:hidden>
		<s:hidden name="adPackage.adPackagePublisher"></s:hidden>
		<%-- 		<s:hidden name="adPackage.adPackagePublishTime"></s:hidden> --%>
		<s:hidden name="adPackage.adPackageBegintime"></s:hidden>
		<s:hidden name="adPackage.adPackageEndtime"></s:hidden>
		<%-- 		<s:hidden name="adPackage.adPackageTips"></s:hidden> --%>
		<s:hidden name="adPackage.dataStat"></s:hidden>
		<s:hidden name="adPackage.adPackageBatchNo"></s:hidden>
		<s:hidden name="adPackage.adPackageCheckSuggest"></s:hidden>

		<div class="pageFormContent" layoutH="56">
			<div class="adPackageTable" style="width: 98%;">
				<div class="titleBar">
					<div class="titleTd">
						<b>应用广告包发布</b>
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
				<p style="width: 760px; height: 60px;" id="wordMsg">
					<label>文字通知：</label>
					<s:textarea class="editor" name="adPackage.adPackageTips"
						style="width: 630px;" readonly="true"></s:textarea>
				</p>
			</div>
			<table style="width: 98%" id="adPackageTableAppPublish">
				<tr valign="top">
					<td><div class="titleBar">
							<div class="titleTd">
								<b>广告包素材发布</b>
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
													id="operaParamPublishList<s:property value="#st.index+1" />">
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
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">&nbsp;发布&nbsp;</button>
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
<script type="text/javascript">
	$(document).ready(function() {
		$("#adPackageTableAppPublish .updPic img").each(function(i) {
			//设置播放时长和播放次数显示
			var divPamObj = $('#adPackageTableAppPublish #operaParamPublishList' + $(this).attr("idNo"));
			divPamObj.css({
				position : "absolute",
				left : $(this).position().left,
				top : $(this).position().top + 79
			});
			$(this).parent().append(divPamObj);
			
		});

		$("#adPackageTableAppPublish a").each(function() {
			$(this).attr("hidefocus", true);
		});
		
		//选中的资源图片高亮显示
		$("#adPackageTableAppPublish .updPic").click(function(){
			$("#adPackageTableAppPublish .updPic").each(
					function(i) {
						$(this).find(".resourceImgApp").removeClass(
								"resourceImgSelected");
					});
			$(this).find(".resourceImgApp").addClass("resourceImgSelected");
		});
	});
</script>
