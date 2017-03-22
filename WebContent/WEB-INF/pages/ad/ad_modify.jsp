<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" rel="stylesheet"
	href="js/uploadify-v2.1.4/uploadify.css"></link>
<%@ page import="com.archermind.etb.util.Constant"%>

<div class="pageContent">
	<s:form id="adModifyForm" theme="simple" method="post"
		action="ad/adPackage!modifyAdPackage.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone,confirmMsg);">

		<s:hidden name="adPackage.adPackageId" id="adPackageId" />
		<s:hidden name="adPackage.adTemplateId" />
		<s:hidden name="adPackage.adPackagePath" />
		<s:hidden name="adPackage.adPackageCreater" />
		<s:hidden name="adPackage.adPackageCreaterTime" />
		<s:hidden name="adPackage.adPackageStatus" id="adPackageStatusModify" />
		<s:hidden name="adPackage.adPackageChecker" />
		<s:hidden name="adPackage.adPackageCheckTime" />
		<s:hidden name="adPackage.adPackagePublisher" />
		<s:hidden name="adPackage.adPackagePublishTime" />
		<s:hidden name="adPackage.dataStat" />
		<s:hidden name="adPackage.adPackageBatchNo" />
		<s:hidden name="adPackage.adPackageBatchNoInitial" />
		<s:hidden name="adPackage.adPackageType" />
		<%-- 		<s:hidden name="adPackage.adPackageCheckSuggest" /> --%>
		<s:hidden name="adType" id="adType"/>
		<div class="pageFormContent" layoutH="56">
			<div class="adPackageTable" id="adPackageTableModify">
				<div class="titleBar">
					<div class="titleTd">
						<b>全屏广告包修改</b>
					</div>
				</div>
				<p>
					<label>批次号：</label> <input name="adPackage.adPackageBatchNoInput"
						alt="请输入字母或数字" id="adPackageBatchNoInput" type="text"
						class="required alphanumericonly" maxlength="7" validlength="7"
						width="180px"
						remote="ad/adPackage!findAdPackageInfoByBatchNoUpd.do?adType=<s:property value='adType'/>&adPackage.adPackageId=<s:property value='adPackage.adPackageId'/>"
						value="<s:property value='adPackage.adPackageBatchNoInput'/>" />
				</p>
				<p>
					<label>广告包名称：</label>
					<s:textfield name="adPackage.adPackageName" alt="请输入字母,数字,下划线或中文"
						cssClass="required characterCheck" maxlength="100" width="180px"></s:textfield>
				</p>
				<p>
					<label>开始时间：</label>
					<s:textfield id="adPackageBegintime1" dateFmt="yyyy-MM-dd"
						name="adPackage.adPackageBegintime1" class="date"
						minDate="%y-%M-%d" cssClass="required date" />
					<a class="inputDateButton" href="javascript:;">选择</a>
				</p>
				<p>
					<label>截止时间：</label>
					<s:textfield id="adPackageEndtime1" dateFmt="yyyy-MM-dd"
						name="adPackage.adPackageEndtime1" class="date" minDate="%y-%M-%d"
						cssClass="required date" />
					<a class="inputDateButton" href="javascript:;">选择</a>
				</p>
				<s:if
					test="adPackage.adPackageCheckSuggest!=null && adPackage.adPackageCheckSuggest!=''">
					<p style="height: 70px;" class="tipsHalfP">
						<label>文字通知：</label>
						<s:textarea class="editor" name="adPackage.adPackageTips"
							maxlength="200" rows="2" cols="40" style="width: 250px;"></s:textarea>
					</p>
					<p style="height: 70px; color: red">
						<label>审核意见：</label>
						<%-- <s:property value="adPackage.adPackageCheckSuggest" /> --%>
						<s:textarea name="adPackage.adPackageCheckSuggest" readonly="true"
							style="width: 250px;color: red;" />
					</p>
				</s:if>
				<s:else>
					<p style="width: 760px; height: 70px" class="wordMsg">
						<label>文字通知：</label>
						<s:textarea class="editor" name="adPackage.adPackageTips"
							maxlength="200" rows="2" cols="120" style="width: 630px;"></s:textarea>
					</p>
				</s:else>
			</div>
			<div id="adResourceList" style="float: left; width: 98%;"></div>
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
		var adModifyForm = $("#adModifyForm");
		var confirmMsg = "";

		$(function() {

			closeResourceDialog();

			//判断审核状态，不可修改，则关闭窗口，刷新查询列表
			if (!checkEditEnable( $("#adPackageStatusModify").val())) {
				closeCurrentToTabId("listAd");
			} else {
				loadResourceList(false);
			}

		});

		//单独控制
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
			$("#adResourceList")
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

			if (adModifyForm.valid()) {
				var adPackageBegintime = $("#adPackageBegintime1").val();
				var adPackageEndtime = $("#adPackageEndtime1").val();
				var curDate = getCurDate(); //获取当前日期
				
				var adPackageBatchNo = $("#adPackageBatchNoInput").val();
				var adType = $("#adType").val();
				var adPackageId=$("#adPackageId").val();

				//批次号重复判断
				if (!batchNoIsRepeatUpd(adType, adPackageBatchNo,adPackageId)) {
					alertMsg.error("批次号不能重复，请重新输入！");
					return false;
				}
				if (!dateCompare(curDate, adPackageBegintime)) {
					alertMsg.error("开始时间不能小于当前时间");
					return false;
				}

				if (!dateCompare(curDate, adPackageEndtime)) {
					alertMsg.error("截止时间不能小于当前时间");
					return false;
				}

				if (!dateCompare(adPackageBegintime, adPackageEndtime)) {
					alertMsg.error("截止时间不能小于开始时间");
					return false;
				}
				
				//时间是否重叠
				var type='<%=Constant.AD_PACKAGE_BATCH_B%>';
				if(timeIsValid(adPackageBegintime,adPackageEndtime,$("#adPackageId").val(),type)>0){
					alertMsg.error("广告播放时间有冲突，请重新设定播放时间！");
					return false;
				}
				

				//保存草稿
				if(saveFlag=='save'){
					confirmMsg = "确定保存吗？";
					$("#adPackageStatusModify").val("<%=Constant.AD_PACKAGE_STATUS_ADD%>");
					adModifyForm.action = "ad/adPackage!modifyAdPackage.do?adPackageStatus=<%=Constant.AD_PACKAGE_STATUS_ADD%>";
				} else if(saveFlag=='commit'){//提交
					confirmMsg = "确定提交吗？";
					$("#adPackageStatusModify").val("<%=Constant.AD_PACKAGE_STATUS_COMMITTED%>");
					adModifyForm.action = "ad/adPackage!modifyAdPackage.do?adPackageStatus=<%=Constant.AD_PACKAGE_STATUS_COMMITTED%>";
				}
				adModifyForm.submit();

			}
		}

		function closeResourceDialog() {

			//上传完成后关闭上传页面模态子窗口：新增
			if ($("body").data("toUpload") != undefined
					&& $("body").data("toUpload") != null
					&& $("body").data("toUpload") != '') {
				$.pdialog.close("toUpload");
			}
			//修改
			if ($("body").data("toAdResourceModify") != undefined
					&& $("body").data("toAdResourceModify") != null
					&& $("body").data("toAdResourceModify") != '') {
				$.pdialog.close("toAdResourceModify");
			}

		}
	</script>
</div>