<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" rel="stylesheet"
	href="js/uploadify-v2.1.4/uploadify.css"></link>
<%
	String path = request.getContextPath();
%>
<%@ page import="com.archermind.etb.util.Constant"%>
<style>
<!--
td {
	padding-top: 10px;
}
-->
</style>
<div class="pageContent">

	<s:form id="adModifyUrgencyForm" theme="simple" method="post"
		action="ad/adPackage!modifyAdPackage.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone,confirmMsg);">

		<s:hidden name="adPackage.adPackageId" id="adPackageId" />
		<s:hidden name="adPackage.adTemplateId" />
		<s:hidden name="adPackage.adPackagePath" />
		<s:hidden name="adPackage.adPackageCreater" />
		<s:hidden name="adPackage.adPackageCreaterTime" />
		<s:hidden name="adPackage.adPackageStatus"
			id="adPackageStatusUrgencyModify" />
		<s:hidden name="adPackage.adPackageChecker" />
		<s:hidden name="adPackage.adPackageCheckTime" />
		<s:hidden name="adPackage.adPackagePublisher" />
		<s:hidden name="adPackage.adPackagePublishTime" />
		<s:hidden name="adPackage.dataStat" />
		<s:hidden name="adPackage.adPackageBatchNo" />
		<s:hidden name="adPackage.adPackageBatchNoInitial" />
		<s:hidden name="adPackage.adPackageType" />
		<%-- 		<s:hidden name="adPackage.adPackageCheckSuggest" /> --%>
		<s:hidden name="adType" id="adType" />

		<div class="pageFormContent" layoutH="56">
			<div class="adPackageTable" id="adPackageTableUrgencyModify">
				<div class="titleBar">
					<div class="titleTd">
						<b>紧急广告包修改</b>
					</div>
				</div>
				<p style="width: 100%;">
					<label>广告类型：</label><label id="adTypeUrgencyModify"
						style="padding-left: 0px; text-align: left"></label> <span></span>
				</p>
				<p>
					<label>批次号：</label><input name="adPackage.adPackageBatchNoInput"
						id="adPackageBatchNoInput" type="text" alt="请输入字母或数字"
						class="required alphanumericonly" maxlength="7" validlength="7"
						width="180px"
						remote="ad/adPackage!findAdPackageInfoByBatchNoUpd.do?adType=<s:property value='adType'/>&adPackage.adPackageId=<s:property value='adPackage.adPackageId'/>"
						value="<s:property value='adPackage.adPackageBatchNoInput'/>" />
				</p>
				<p>
					<label>广告包名称：</label>
					<s:textfield name="adPackage.adPackageName"
						cssClass="required characterCheck" maxlength="100" width="180px"
						alt="请输入字母,数字,下划线或中文"></s:textfield>
				</p>
				<p>
					<label>开始时间：</label>
					<s:textfield id="adPackageBegintime" dateFmt="yyyy-MM-dd HH:mm:ss"
						name="adPackage.adPackageBegintime1" class="date"
						minDate="%y-%M-%d" cssClass="required date" />
					<a class="inputDateButton" href="javascript:;">选择</a>
				</p>
				<p>
					<label>截止时间：</label>
					<s:textfield id="adPackageEndtime" dateFmt="yyyy-MM-dd HH:mm:ss"
						name="adPackage.adPackageEndtime1" class="date" minDate="%y-%M-%d"
						cssClass="required date" />
					<a class="inputDateButton" href="javascript:;">选择</a>
				</p>
				<p style="width: 100%; height: 65px" id="messageNoteUrgencyModify">
					<label>文字通知：</label>
					<s:textarea class="editor" id="msgNoteUrgencyModify"
						name="adPackage.adPackageTips" maxlength="200" rows="2" cols="120"></s:textarea>
				</p>
				<s:if
					test="adPackage.adPackageCheckSuggest!=null && adPackage.adPackageCheckSuggest!=''">
					<p id="suggestNoteUrgencyModify" style="color: red;">
						<label>审核意见：</label>
						<s:textarea name="adPackage.adPackageCheckSuggest"
							id="checkSuggestUrgencyModify" readonly="true"
							style="width: 630px;color: red;" />
					</p>
				</s:if>
			</div>

			<div id="adResourceListUrgency" style="float: left; width: 98%"></div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="complete('save');">&nbsp;保存草稿&nbsp;</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="complete('commit');">&nbsp;提交&nbsp;</button>
						</div>
					</div></li>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" class="close">&nbsp;取消&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
	<script type="text/javascript">
		var adModifyUrgencyForm = $("#adModifyUrgencyForm");
		var confirmMsg = "";

		$(function() {

			closeResourceDialog();

			//判断审核状态，不可修改，则关闭窗口，刷新查询列表
			if(!checkEditEnable($("#adPackageStatusUrgencyModify").val())){
				closeCurrentToTabId("listAd");
			}else{
				//文字广告和图片广告UI控制
				if ('<s:property value="adPackage.adTemplateId"/>' == '<%=Constant.AD_TEMPLATE_URGENCY_PIC%>') {//图片广告
					$("#msgNoteUrgencyModify").removeClass("required");
					$("#messageNoteUrgencyModify").hide();
					$("#adResourceListUrgency").show();
					$("#adTypeUrgencyModify").text("图片广告");
					
					$("#suggestNoteUrgencyModify").css("width","760px");
					$("#suggestNoteUrgencyModify").css("height","60px");

					loadResourceList(true);

				} else {
					$("#msgNoteUrgencyModify").addClass("required");
					$("#messageNoteUrgencyModify").show();
					$("#adResourceListUrgency").hide();
					$("#adTypeUrgencyModify").text("文字广告");

					$("#adPackageTableUrgencyModify p").css("width", "100%");
					$("#msgNoteUrgencyModify").css("width", "250px");
					$("#checkSuggestUrgencyModify").css("width", "250px");
				}
			}

		});

		function closeResourceDialog() {

			//上传完成后关闭上传页面模态子窗口：新增
			if ($("body").data("toUpload") != undefined
					&& $("body").data("toUpload") != null
					&& $("body").data("toUpload") != '') {
				$.pdialog.close("toUpload");
			}
			//修改
			if ($("body").data("toAdResourceModifyUrgency") != undefined
					&& $("body").data("toAdResourceModifyUrgency") != null
					&& $("body").data("toAdResourceModifyUrgency") != '') {
				$.pdialog.close("toAdResourceModifyUrgency");
			}

		}

		//单独控制-删除后的操作
		function navTabAjaxDoneWithInFailUpd(json) {

			DWZ.ajaxDone(json);
			//控制弹出信息
			if (json.statusCode == DWZ.statusCode.ok) {
				alertMsg.correct(json.message);
				loadResourceList(true);
			} else {
				alertMsg.error(json.message);

				//控制其他跳转操作
				navTabDone(json);
			}

		}

		function loadResourceList(closeDialogFlag) {

			navTab.switchTab("adModify");

			//增加是否关闭窗口的参数closeDialogFlag for上传图片数量限定,2013-10-31,RanChen
			$("#adResourceListUrgency")
					.loadUrl(
							'ad/adPackage!toAdModifyResourceList.do?adPackage.adPackageId=<s:property value="adPackage.adPackageId"/>&adType=<s:property value="adType"/>&adPackage.adTemplateId=<s:property value="adPackage.adTemplateId"/>&closeDialogFlag='+closeDialogFlag);
		}

		function complete(saveFlag) {
			//添加判断,已审核或已发布不可修改
			$.ajax({
				type : "post",
				cache : false,
				url : "ad/adPackage!getAdpackageStatusById.do",
				data : "adPackage.adPackageId=" + $("#adPackageId").val(),
				datatype : "json",
				success : function(data) {

					//判断审核状态，不可修改，则关闭窗口，刷新查询列表
					if (!checkEditEnable(data.status)) {
						closeCurrentToTabId("listAd");
					} else {
						modifyAdPackage(saveFlag);
					}
					return false;
				}

			});
		}

		function modifyAdPackage(saveFlag) {

			if (adModifyUrgencyForm.valid()) {
				var adPackageBegintime = $("#adPackageBegintime").val();
				var adPackageEndtime = $("#adPackageEndtime").val();
				var curTime = getCurentTime();

				var adPackageBatchNo = $("#adPackageBatchNoInput").val();
				var adType = $("#adType").val();
				var adPackageId=$("#adPackageId").val();

				//批次号重复判断
				if (!batchNoIsRepeatUpd(adType, adPackageBatchNo,adPackageId)) {
					alertMsg.error("批次号不能重复，请重新输入！");
					return false;
				}
				
				if (!timeCompare(curTime, adPackageBegintime)) {
					alertMsg.error("开始时间不能小于当前时间");
					return false;
				}

				if (!timeCompare(curTime, adPackageEndtime)) {
					alertMsg.error("截止时间不能小于当前时间");
					return false;
				}

				if (!timeCompare(adPackageBegintime, adPackageEndtime)) {
					alertMsg.error("截止时间不能小于等于开始时间");
					return false;
				}
				
				//时间是否重叠
				var type='<%=Constant.AD_PACKAGE_BATCH_U%>';
				if(timeIsValid(adPackageBegintime,adPackageEndtime,$("#adPackageId").val(),type)>0){
					alertMsg.error("广告播放时间有冲突，请重新设定播放时间！");
					return false;
				}

				//保存草稿
				if(saveFlag=='save'){
					confirmMsg = "确定保存吗？";
					$("#adPackageStatusUrgencyModify").val("<%=Constant.AD_PACKAGE_STATUS_ADD%>");
					adModifyUrgencyForm.action = "ad/adPackage!modifyAdPackage.do";
				} else if(saveFlag=='commit'){//提交
					confirmMsg = "确定提交吗？";
					$("#adPackageStatusUrgencyModify").val("<%=Constant.AD_PACKAGE_STATUS_COMMITTED%>");
					adModifyUrgencyForm.action = "ad/adPackage!modifyAdPackage.do";
				}

				adModifyUrgencyForm.submit();

			}
		}
	</script>
</div>