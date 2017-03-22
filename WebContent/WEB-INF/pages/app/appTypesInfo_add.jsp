<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	var adCommonForm = $("#appTypesInfoAddForm");
	function save() {
		var appTypesFile = $("#appTypesFile").val();
		if(null!=appTypesFile && appTypesFile!=''){
			if(appTypesFile.indexOf('\\')!=-1){
				var temp = appTypesFile.split('\\');
				appTypesFile = temp[temp.length-1];
			}else if(appTypesFile.indexOf('/')!=-1){
				var temp = appTypesFile.split('/');
				appTypesFile = temp[temp.length-1];
			}
			$("#appTypesFileName").val(appTypesFile);
		}
		
		//$("#appInfoStatus").val(flag);
		adCommonForm.action = "app/appTypesAction!saveAppTypesInfo.do";

	}
</script>
<div class="pageContent">
	<s:form id="appTypesInfoAddForm" theme="simple" method="post"
		action="app/appTypesAction!saveAppTypesInfo.do"
		cssClass="pageForm required-validate"
		onsubmit="return iframeCallback(this,dialogAjaxDone);" enctype="multipart/form-data">
		<s:hidden name="vo.appTypesId"></s:hidden>
		<s:hidden name="vo.appTypesFileName" id="appTypesFileName"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>应用分类名称：</label>
				<input type="text" name="vo.appTypesName" alt="请输入字母,数字,下划线或中文"
					class="required characterCheck" maxlength="40"
					remote="app/appTypesAction!findAppTypesByName.do">
					
			</p>
			<p>
				<label>应用分类英文名称：</label>
				<input type="text" name="vo.appTypesEnName" alt="请输入字母,数字,下划线"
					class="required characterEnCheck" maxlength="40" remote="app/appTypesAction!findAppTypesByEnName.do">
					
			</p>
			<p style="height:40px;">
				<label>应用分类图标：</label>
				<s:file  name="vo.appTypesFile" id="appTypesFile" cssClass="required checkImageFile"></s:file><br><span class="info" style="left:130px;top:30px;" >（格式：jpg，png，jpeg；分辨率：120*120）</span>
			</p>
			<div style="float: left; margin: 0; padding: 5px 0;">
				<div style="float: left;">
					<label>备注说明：</label>
				</div>
				<div class="combox">
					<div class="select">
						<s:textarea class="editor" name="vo.appTypesTips" maxlength="200"></s:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="return save();">&nbsp;保存&nbsp;</button>
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