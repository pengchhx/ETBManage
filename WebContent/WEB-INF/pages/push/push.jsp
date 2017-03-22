<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript">
function showDevice()
{
	 $("#device").show();
}
function hiddenDevice()
{
	$("#device").hide();
}
</script>
<title>PUSH </title>
</head>
<body>
<div class="pageContent">
	<s:form id="pushForm" theme="simple" method="post"
		action="push/pushinfo!pushMessage.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<s:hidden name="otaInfo.id"></s:hidden>
		<div class="pageFormContent" layoutH="56">
		    <p> <label>个推</label><input type="radio" name="msg.type" value="0" checked="checked"  onclick="showDevice();">群推<input type="radio" name="msg.type" value="1" onclick="hiddenDevice();">
		    </p>
		    <p>
				<label>过期时间：</label>
				<s:textfield id="adPackageBegintime" dateFmt="yyyy-MM-dd HH:mm:ss"
							name="msg.excuteDate" class="date"
							cssClass="required date" minDate="%y-%M-%d" />
						<a class="inputDateButton" href="javascript:;">选择</a>
				<!--  <input type="text" name="msg.excuteDate" class="date required"  dateFmt="yyyy-MM-dd HH:mm:ss" />-->
			</p>
			<div id="device">
				<!--<label>Device：</label>
				<s:textfield name="msg.device" cssClass="required characterCheck"  maxlength="100"
				remote="ota/versioninfo!nameIsExist.do"
				></s:textfield>
				<input class="required" id="clientdevice" name="msg.device" type="text" />-->
				<div style="float: left; margin: 0; padding: 5px 0;">
					<div style="float: left;">
						<label>设备编号：</label>
					</div>
					<div class="combox">
						<div class="select">
							<s:textarea id="clientdevice" class="required"  name="msg.device" ></s:textarea>
						</div>
					</div>
			   </div>
				 <a class="add"
						href="push/pushinfo!showEtbClientDevice.do" target="dialog"
						rel="deviceChoice" title="终端设备信息" width="690" height="606"
						mask="true" resizable="false"> <span><img alt="搜索"
							src="themes/azure/images/preview.png"></span></a>
			</div>
			  <p>
				<label>消息内容：</label>
				<s:textarea cols="25" rows="5" class="required" name="msg.message" maxlength="3000"></s:textarea>
			</p>

		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">&nbsp;保存&nbsp;</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">&nbsp;取消&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
</div>
</body>
</html>