<%@page import="com.archermind.etb.util.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style type="text/css" media="screen">
.my-uploadify-button {
	background: none;
	border: none;
	text-shadow: none;
	border-radius: 0;
}

.uploadify:hover .my-uploadify-button {
	background: none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 50px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>
<script type="text/javascript">
	var adAppForm = $("#adAppForm");
	var confirmMsg = "";
	
	function save() {
		var curDate = getCurDate(); //获取当前日期
		var adPackageBegintimeApp1 = $("#adPackageBegintimeApp1").val();
		var adPackageEndtimeApp1 = $("#adPackageEndtimeApp1").val();
		var adPackageBatchNoApp=$("#adPackageBatchNoApp").val();
		var adStyle=$("#adStyle").val();
		
		//批次号重复判断
		if (!batchNoIsRepeat(adStyle,adPackageBatchNoApp)) {
			alertMsg.error("批次号不能重复，请重新输入！");
			return false;
		}

		if (adPackageBegintimeApp1 != null && adPackageBegintimeApp1 != ""
				&& adPackageEndtimeApp1 != null && adPackageEndtimeApp1 != "") {

			if (!dateCompare(curDate, adPackageBegintimeApp1)) {
				alertMsg.error("开始时间不能小于当前时间");
				return false;
			}

			if (!dateCompare(curDate, adPackageEndtimeApp1)) {
				alertMsg.error("截止时间不能小于当前时间");
				return false;
			}

			if (!dateCompare(adPackageBegintimeApp1, adPackageEndtimeApp1)) {
				alertMsg.error("截止时间不能小于开始时间");
				return false;
			}

			//时间是否重叠
			var type = '<%=Constant.AD_PACKAGE_BATCH_E%>';
			if(timeIsValid(adPackageBegintimeApp1,adPackageEndtimeApp1,"",type)>0){
				alertMsg.error("广告播放时间有冲突，请重新设定播放时间！");
				return false;
			}
		}
		
		$("#adPackageStatusAppAdd").val("<%=Constant.AD_PACKAGE_STATUS_ADD%>");
		confirmMsg = "确定执行下一步吗？";
		adAppForm.action = "ad/adPackage!addAdApp.do";

	}

</script>
<div class="pageContent">
	<s:form id="adAppForm" theme="simple" method="post"
		action="ad/adPackage!addAdApp.do"
		cssClass="pageForm required-validate"
		onsubmit="return navTabSearchSkip(this,confirmMsg,false,DWZ.ajaxDone,'toAddApp');">
		<s:hidden name="adPackage.adTemplateId"></s:hidden>
		<s:hidden name="adPackage.adPackageStatus" id="adPackageStatusAppAdd"></s:hidden>
		<s:hidden name="adStyle" id="adStyle"></s:hidden>
		<div>
			<div style="margin: 20px 10px 2px 15px;">
				<b>应用广告</b>
			</div>
			<div class="pageFormContentAd nowrap" layoutH="450"
				style="margin: 0 10px 0 10px;">
				<dl>
					<dt>模板类型：</dt>
					<dd>
						<s:property value="adPackage.adTemplateName" />
					</dd>
				</dl>
				<dl>
					<dt>批次号：</dt>
					<dd>
						<input type="text" name="adPackage.adPackageBatchNo"
							id="adPackageBatchNoApp"
							class="required alphanumericonly" maxlength="7" validlength="7"
							width="180px" alt="请输入字母或数字"
							remote="ad/adPackage!findAdPackageInfoByBatchNo.do?adStyle=<s:property value='adStyle'/>" />
					</dd>
				</dl>
				<dl>
					<dt>广告包名称：</dt>
					<dd>
						<s:textfield name="adPackage.adPackageName" alt="请输入字母,数字,下划线或中文"
							cssClass="required characterCheck" maxlength="100" width="180px"></s:textfield>
					</dd>
				</dl>
				<dl>
					<dt>文字通知：</dt>
					<dd>
						<s:textarea class="editor" name="adPackage.adPackageTips"
							maxlength="200" rows="2" cols="40"></s:textarea>
					</dd>
				</dl>
				<dl>
					<dt>开始时间：</dt>
					<dd>
						<s:textfield id="adPackageBegintimeApp1" dateFmt="yyyy-MM-dd"
							name="adPackage.adPackageBegintime1" class="date"
							cssClass="required date" minDate="%y-%M-%d" />
						<a class="inputDateButton" href="javascript:;">选择</a>
					</dd>
				</dl>
				<dl>
					<dt>截止时间：</dt>
					<dd>
						<s:textfield id="adPackageEndtimeApp1" dateFmt="yyyy-MM-dd"
							name="adPackage.adPackageEndtime1" class="date"
							cssClass="required date" minDate="%y-%M-%d" />
						<a class="inputDateButton" href="javascript:;">选择</a>
					</dd>
				</dl>
			</div>
			<div class="btnArea">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="return save();">&nbsp;下一步&nbsp;</button>
							</div>
						</div></li>
					<li><div class="button">
							<div class="buttonContent">
								<button type="button" class="close">&nbsp;取消&nbsp;</button>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
		<div class="formBar" style="height: 400px;"></div>
	</s:form>

</div>