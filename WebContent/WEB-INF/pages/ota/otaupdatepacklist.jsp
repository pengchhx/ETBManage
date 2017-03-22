<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
  function reloadList()
  {
	  var url = "";
	  url = "ota/updatepack!getVersionUpdatePack.do?otaId=<s:property value="otaId"/>";
		navTab.reload(url, {
			navTabId : "add_ota_info"
		});
		//$.pdialog.close("add_compelet_pack");
		//navTab.switchTab("toAddCommon");
  }
  function submitOta()
  {
	  $("#submitBtn").click();
  }
  function saveOta()
  {
	  $("#saveBtn").click();
  }
</script>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
			<s:if test="isOne==0&&auditReleased==0">
			<a class="add" href="ota/updatepack!preAddPack.do?otaId=${otaId}" fresh="false"  target="dialog" mask=true rel="add_compelet_pack" height=380 width=600  ><span>新增完整包</span></a>
			</s:if>
			<s:else>
			<a class="add" href="ota/updatepack!preAddPack.do?otaId=${otaId}" fresh="false"  target="ajaxTodo"><span>新增完整包</span></a>
			</s:else>
			</li>
			<li>
			<s:if test="isFour<4&&auditReleased==0&&isMinVersion==1">
			<a class="add" href="ota/updatepack!preAddUpdatePack.do?otaId=${otaId}" fresh="false"  target="dialog" rel="add_update_pack" mask=true height=380 width=600  ><span>新增差分包</span></a>
			</s:if>
			<s:else>
			<a class="add" href="ota/updatepack!preAddUpdatePack.do?otaId=${otaId}" fresh="false"  target="ajaxTodo"><span>新增差分包</span></a>
			</s:else>
			</li>
			<li><a class="delete" callback="reloadList" href="ota/updatepack!deletePack.do?packId={packid}&otaId=${otaId}" target="ajaxTodo" title="确定删除该记录?"><span>删除</span></a></li>
		</ul>
	</div>
<p style="font-size:small;">&nbsp;&nbsp;&nbsp;&nbsp;1.完整包：即该版本独立的、完整的升级包</p>
<p style="font-size:small;">&nbsp;&nbsp;&nbsp;&nbsp;2.差分包：低版本向高版本升级的升级包，目前高版本只向低于其4个版本内的版本提供差分升级。例如：5-4,5-3,5-2,5-1这样的差分升级包</p>
<p>&nbsp;&nbsp;&nbsp;&nbsp;</p>
<table class="table" width="100%" layoutH="138">
<thead>
			<tr>
				<th width="120">升级包名称</th>
				<th width="100">目标版本名称</th>
				<th width="100">初始版本名称</th>
				<th width="100">下载地址</th>
				<th width="100">类型</th>
				<th width="100">备注说明</th>
			</tr>
</thead>
<tbody>
<s:iterator value="packList" var="arr" status="status"  id="arr" >
<tr target="packid" rel="<s:property value="#arr.id" />">
<td title="${arr.packname }">${arr.packname}</td>
<td title="${arr.targetname }">${arr.targetname}</td>
<td title="${arr.initname }">${arr.initname}</td>
<td><a href="${arr.packUploadPath}" target="blank"><span  class="appinfoReadSpan">${arr.packShowPath}</span></a></td>
<td>
<s:if test="%{#arr.packtype==0}">完整包</s:if>
<s:if test="%{#arr.packtype==1}">差分包</s:if>
</td>
<td title="${arr.tips }">${arr.tips}</td>
</tr>
</s:iterator>
</tbody>
</table>
<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" onclick="saveOta();">&nbsp;保存草稿&nbsp;</button>
						</div>
					</div> <a id="saveBtn" class="button" style="display: none;"
					href="ota/versioninfo!saveToDraft.do" target="ajaxTodo"><span>&nbsp;保存草稿&nbsp;</span></a>
					
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" onclick="submitOta();">&nbsp;提交&nbsp;</button>
						</div>
					</div>
					<a id="submitBtn" class="button" style="display: none;"
					href="ota/versioninfo!submitOta.do?otaId=<s:property value="otaId"/>"
					target="ajaxTodo"><span>&nbsp;提交&nbsp;</span></a>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close" onclick="reloadList">&nbsp;取消&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
</div>
