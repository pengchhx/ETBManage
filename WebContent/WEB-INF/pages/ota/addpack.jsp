<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
  function reloadParent()
  {
	  var url = "";
	  url = "ota/updatepack!getVersionUpdatePack.do?otaId=<s:property value="updatePack.otaid"/>";
		navTab.reload(url, {
			navTabId : "add_ota_info"
		});
		$.pdialog.close("add_compelet_pack");
		//navTab.switchTab("toAddCommon");
  }
</script>
<div class="pageContent">
	<s:form id="packInfoForm" theme="simple" method="post"
		action="ota/updatepack!addCompletepack.do"
		cssClass="pageForm required-validate"
		onsubmit="return iframeCallback(this,reloadParent);"
		enctype="multipart/form-data">
		<s:hidden name="updatePack.otaid"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>完整包名称：</label>
				<s:textfield name="updatePack.packname" alt="请输入字母,数字,下划线或中文"
					cssClass="required characterCheck" maxlength="100"
					remote="ota/updatepack!packnameIsExist.do"></s:textfield>
			</p>
			<p>
				<label>版本名称：</label>
				<s:textfield name="updatePack.targetname" readonly="true"
					disabled="disabled" cssClass="required"></s:textfield>
			</p>
			<p>
				<label>升级包文件：</label>
				<s:file name="updatePack.packfile" cssClass="required checkZipFile"></s:file>
			</p>
			<div style="float: left; margin: 0; padding: 5px 0;">
				<div style="float: left;">
					<label>备注说明：</label>
				</div>
				<div class="combox">
					<div class="select">
						<s:textarea name="updatePack.tips" maxlength="200"></s:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">&nbsp;保存&nbsp;</button>
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