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
		$.pdialog.close("add_update_pack");
		//navTab.switchTab("toAddCommon");
  }
</script>
<div class="pageContent">
	<s:form id="addpackForm" theme="simple" method="post"
		action="ota/updatepack!addUpdatepack.do"
		cssClass="pageForm required-validate"
		onsubmit="return iframeCallback(this,reloadParent);"
		enctype="multipart/form-data">
		<s:hidden name="updatePack.otaid"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>升级包名称：</label>
				<s:textfield name="updatePack.packname"
					cssClass="required characterCheck" maxlength="100"
					alt="请输入字母,数字,下划线或中文" remote="ota/updatepack!packnameIsExist.do"></s:textfield>
			</p>
			<p>
				<label>目标版本名称：</label>
				<s:textfield name="updatePack.targetname" readonly="true"
					disabled="disabled" cssClass="required"></s:textfield>
			</p>
			<!--  <p>
				<label>初始版本名称：</label>									
				<s:textfield alt="请输入字母,数字,下划线或中文" name="updatePack.initname" cssClass="required characterCheck"  maxlength="40"></s:textfield>
			</p>-->
			<p>
				<label>初始版本名称：</label> <select name="updatePack.initname"
					cssClass="required">
					<s:iterator value="listName" var="list">
						<option value="${list }">${list }</option>
					</s:iterator>
				</select>
			</p>
			<p>
				<label>升级包文件：</label>
				<s:file name="updatePack.packfile" cssClass="required checkZipFile"></s:file>
			</p>
			<p>
				<label>备注说明：</label>
				<s:textarea cols="25" rows="5" name="updatePack.tips"
					maxlength="200"></s:textarea>
			</p>
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
