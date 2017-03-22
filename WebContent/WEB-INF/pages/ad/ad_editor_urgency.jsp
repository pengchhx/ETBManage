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
	
</script>
<div class="pageContent">
	<s:form id="adUrgencyForm" theme="simple" method="post"
		action="ad/adPackage!addAdUrgency.do"
		cssClass="pageForm required-validate"
		onsubmit="return navTabSearchSkip(this,confirmMsg,flag,navTabAjaxDone,'toAddUrgency');">
		<s:hidden name="adPackage.adTemplateId"></s:hidden>
		<s:hidden name="adPackage.adPackageStatus"
			id="adPackageStatusUrgencyAdd"></s:hidden>
		<s:hidden name="adStyle" id="adStyle"></s:hidden>
		<div>
			<div style="margin: 20px 10px 2px 15px;">
				<b>紧急广告</b>
			</div>
			<div class="pageFormContentAd nowrap" layoutH="450"
				style="margin: 0 10px 0 10px;">
				<dl>
					<dt>类型：</dt>
					<dd>
						<s:select list="#{5:'文字广告',6:'图片广告'}" listKey="key"
							listValue="value" name="adPackage.adPackageTypeUrgency"
							id="adPackageTypeUrgency" onchange="resourceSetting(this);"></s:select>
					</dd>
				</dl>
				<dl>
					<dt>批次号：</dt>
					<dd class="adj">
						<input type="text" name="adPackage.adPackageBatchNo"
							alt="请输入字母或数字" id="adPackageBatchNo"
							class="required alphanumericonly" maxlength="7" validlength="7"
							width="180px"
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
					<dt>开始时间：</dt>
					<dd>
						<s:textfield id="adPackageBegintimeUrgency1" dateFmt="yyyy-MM-dd HH:mm:ss"
							name="adPackage.adPackageBegintime1" class="date"
							cssClass="required date" minDate="%y-%M-%d" />
						<a class="inputDateButton" href="javascript:;">选择</a>
					</dd>
				</dl>
				<dl>
					<dt>截止时间：</dt>
					<dd>
						<s:textfield id="adPackageEndtimeUrgency1" dateFmt="yyyy-MM-dd HH:mm:ss"
							name="adPackage.adPackageEndtime1" class="date"
							cssClass="required date" minDate="%y-%M-%d" />
						<a class="inputDateButton" href="javascript:;">选择</a>
					</dd>
				</dl>
				<dl id="messageNote">
					<dt>文字通知：</dt>
					<dd>
						<s:textarea id="msgNote" class="editor"
							name="adPackage.adPackageTips" maxlength="200" rows="2" cols="40"></s:textarea>
					</dd>
				</dl>
			</div>
			<div id="btnAreaUrgency" class="btnAreaUrgency">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" id="nextStep"
									onclick="return saveUrgencyAdd1('save');">&nbsp;下一步&nbsp;</button>
								<button type="submit" id="saveStep"
									onclick="return saveUrgencyAdd1('save');">&nbsp;保存草稿&nbsp;</button>
							</div>
						</div></li>
					<li id="commitStep"><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit"
									onclick="return saveUrgencyAdd1('commit');">&nbsp;提交&nbsp;</button>
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
		<script type="text/javascript">
			var adUrgencyForm = $("#adUrgencyForm");
			var confirmMsg = "";
			var maxEndTime="";//DB中该类型的最大截止时间
			var flag = true;
			$(function() {
				$("#nextStep").hide();
				$("#msgNote").addClass("required");
			});

			function resourceSetting(obj) {
				if (obj.value == 5) {//文字广告
					flag = true;
					$("#messageNote").show();
					$("#nextStep").hide();
					$("#saveStep").show();
					$("#commitStep").show();
					$("#msgNote").addClass("required");
					$("#btnAreaUrgency").removeClass("btnArea");
					$("#btnAreaUrgency").addClass("btnAreaUrgency");
					confirmMsg = "确定保存吗？";

				} else {//图片广告
					flag = false;
					$("#msgNote").val('');
					$("#messageNote").hide();
					$("#nextStep").show();
					$("#saveStep").hide();
					$("#commitStep").hide();
					$("#msgNote").removeClass("required");
					$("#btnAreaUrgency").removeClass("btnAreaUrgency");
					$("#btnAreaUrgency").addClass("btnArea");
					confirmMsg = "确定执行下一步吗？";
				}
				$("#msgNote").trigger("blur");
			}

			function saveUrgencyAdd1(saveFlag) {
				var curTime = getCurentTime();
				var adPackageBegintimeUrgency1 = $("#adPackageBegintimeUrgency1").val();
				var adPackageEndtimeUrgency1 = $("#adPackageEndtimeUrgency1").val();
				var adPackageBatchNo = $("#adPackageBatchNo").val();
				var adStyle = $("#adStyle").val();

				
				//批次号重复判断
				if (!batchNoIsRepeat(adStyle, adPackageBatchNo)) {
					alertMsg.error("批次号不能重复，请重新输入！");
					return false;
				}
				if (adPackageBegintimeUrgency1 != null
						&& adPackageBegintimeUrgency1 != ""
						&& adPackageEndtimeUrgency1 != null
						&& adPackageEndtimeUrgency1 != "") {

					if (!timeCompare(curTime, adPackageBegintimeUrgency1)) {
						alertMsg.error("开始时间不能小于当前时间");
						return false;
					}

					if (!timeCompare(curTime, adPackageEndtimeUrgency1)) {
						alertMsg.error("截止时间不能小于当前时间");
						return false;
					}
					if (!timeCompare(adPackageBegintimeUrgency1,
							adPackageEndtimeUrgency1)) {
						alertMsg.error("截止时间不能小于等于开始时间");
						return false;
					}

					//时间是否重叠	
					var type = '<%=Constant.AD_PACKAGE_BATCH_U%>';
					if(timeIsValid(adPackageBegintimeUrgency1,adPackageEndtimeUrgency1,"",type)>0){
						alertMsg.error("广告播放时间有冲突，请重新设定播放时间！");
						return false;
					}
				}

				var confrimMsg = "";
				
				if(saveFlag == "save"){
					confirmMsg = "确定保存吗？";
					$("#adPackageStatusUrgencyAdd").val("<%=Constant.AD_PACKAGE_STATUS_ADD%>");
				}else if(saveFlag == "commit"){
					confirmMsg = "确定提交吗？";
					$("#adPackageStatusUrgencyAdd").val("<%=Constant.AD_PACKAGE_STATUS_COMMITTED%>");
				}
			}
		</script>
	</s:form>
</div>