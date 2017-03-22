<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	var adCommonForm = $("#appInfoAddForm");
	function save(flag) {
		var appInfoFile = $("#appInfoFile").val();
		if(null!=appInfoFile && appInfoFile!=''){
			if(appInfoFile.indexOf('\\')!=-1){
				var temp = appInfoFile.split('\\');
				appInfoFile = temp[temp.length-1];
			}else if(appInfoFile.indexOf('/')!=-1){
				var temp = appInfoFile.split('/');
				appInfoFile = temp[temp.length-1];
			}
			$("#appInfoFilename").val(appInfoFile);
		}
		
		$("#appInfoStatus").val(flag);
		adCommonForm.action = "app/appAction!saveAppInfo.do";

	}
</script>
<div class="pageContent">
	<s:form id="appInfoAddForm" theme="simple" method="post"
		action="app/appAction!saveAppInfo.do"
		cssClass="pageForm required-validate"
		onsubmit="return iframeCallback(this,dialogAjaxDone);"
		enctype="multipart/form-data">
		<s:hidden name="vo.appInfoId"></s:hidden>
		<s:hidden name="vo.appInfoFilename" id="appInfoFilename"></s:hidden>
		<s:hidden name="vo.appInfoStatus" id="appInfoStatus"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>应用名称：</label>
				<input type="text" name="vo.appInfoName" alt="请输入字母,数字,下划线或中文" 
					class="required characterCheck"  maxlength="40">
			</p>
			<p>
				<label>应用分类：</label>
				<s:select list="appTypesList" listKey="appTypesId" listValue="appTypesName" name="vo.appTypesId" id="appTypesId"  headerKey="0" headerValue="请选择" cssClass="required"></s:select>
			</p>
			<p style="height:40px;">
				<label>应用文件：</label>
				<s:file  name="vo.appInfoFile" id="appInfoFile" cssClass="required checkApkFile"></s:file><br><span class="info" style="left:130px;top:30px;" >（要求后缀名必须为apk）</span>
			</p>
			<div style="float: left; margin: 0; padding: 5px 0;">
				<div style="float: left;">
					<label>备注说明：</label>
				</div>
				<div class="combox">
					<div class="select">
						<s:textarea class="editor" name="vo.appInfoTips" maxlength="200"></s:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="return save('3');">&nbsp;保存草稿&nbsp;</button>
						</div>
					</div>
				</li>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="return save('0');">&nbsp;提交&nbsp;</button>
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