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
</script>
<table style="width: 98%;">
				<tr valign="top">
					<td><div class="titleBar">
							<div class="titleTd">
								<b>升级包修改</b>
							</div>
						</div></td>
				</tr>
				<tr>
				<td>
				<div class="panelBar">
					<ul class="toolBar">
						<li>
						<s:if test="isOne==0">
						<a class="add" href="ota/otaedit!preAddPack.do?otaId=${otaId}"  fresh="false"  target="dialog" mask=true rel="edit_compelet_pack" height=380 width=600  ><span>新增完整包</span></a>
						</s:if>
						<s:else>
						<a class="add" href="ota/otaedit!preAddPack.do?otaId=${otaId}" fresh="false"  target="ajaxTodo"><span>新增完整包</span></a>
						</s:else>
						</li>
						<li>
						<s:if test="isFour<4&&isMinVersion==1">
						<a class="add" href="ota/otaedit!preAddUpdatePack.do?otaId=${otaId}" fresh="false"  target="dialog" rel="edit_update_pack" mask=true height=380 width=600  ><span>新增差分包</span></a>
						</s:if>
						<s:else>
						<a class="add" href="ota/otaedit!preAddUpdatePack.do?otaId=${otaId}" fresh="false"  target="ajaxTodo"><span>新增差分包</span></a>
						</s:else>
						</li>
						<li><a class="delete" callback="loadPackList" href="ota/otaedit!deletePack.do?packId={packid}&otaId=${otaId}" target="ajaxTodo" title="确定删除该记录?"><span>删除</span></a></li>
					</ul>
				</div>
				</td>
				</tr>
				<tr>
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
				</tr>
			</table>