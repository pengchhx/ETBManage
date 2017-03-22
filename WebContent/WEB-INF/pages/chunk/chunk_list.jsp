<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
$("#reserve").click(
		function(event) {
			//var $this = $(this);
			//var href = "ad/adPackage!adModify.do?adPackage.adPackageId={adPackageId}";
			var url = unescape($("#reserve1").attr("href")).replaceTmById(
					$(event.target).parents(".unitBox:first"));
			var chunkId = unescape("{chunkId}").replaceTmById(
					$(event.target).parents(".unitBox:first"));
			if (!url.isFinishedTm()) {
				alertMsg.error(DWZ.msg("alertSelectMsg"));
				return false;
			} else {
				$.ajax({
					type : "GET",
					cache : false,
					url : "chunk/chunkAction!getChunkStatusById.do",
					data : "vo.chunkId="+chunkId,
					datatype : "json",
					success : function(data) {
						if (data.status == "2") {
							alertMsg.error("设备空闲状态，不可增加备份！");
						} else if (data.status == "3") {//已上线
							alertMsg.error("设备暂停状态，不可增加备份！");
						} else {
							$("#reserve1").click();
						}
					}

				});
			}

		});
		
$("#update").click(
		function(event) {
			//var $this = $(this);
			//var href = "ad/adPackage!adModify.do?adPackage.adPackageId={adPackageId}";
			var url = unescape($("#update1").attr("href")).replaceTmById(
					$(event.target).parents(".unitBox:first"));
			var chunkId = unescape("{chunkId}").replaceTmById(
					$(event.target).parents(".unitBox:first"));
			if (!url.isFinishedTm()) {
				alertMsg.error(DWZ.msg("alertSelectMsg"));
				return false;
			} else {
				$.ajax({
					type : "GET",
					cache : false,
					url : "chunk/chunkAction!getChunkStatusById.do",
					data : "vo.chunkId="+chunkId,
					datatype : "json",
					success : function(data) {
						if (data.status == "1") {
							alertMsg.error("运行状态下的设备，不可修改！");
						}  else {
							$("#update1").click();
						}
					}

				});
			}

		});
		
$("#delete").click(
		function(event) {
			//var $this = $(this);
			//var href = "ad/adPackage!adModify.do?adPackage.adPackageId={adPackageId}";
			var url = unescape($("#delete1").attr("href")).replaceTmById(
					$(event.target).parents(".unitBox:first"));
			var chunkId = unescape("{chunkId}").replaceTmById(
					$(event.target).parents(".unitBox:first"));
			if (!url.isFinishedTm()) {
				alertMsg.error(DWZ.msg("alertSelectMsg"));
				return false;
			} else {
				$.ajax({
					type : "GET",
					cache : false,
					url : "chunk/chunkAction!getChunkStatusById.do",
					data : "vo.chunkId="+chunkId,
					datatype : "json",
					success : function(data) {
						if (data.status == "1") {
							alertMsg.error("运行状态下的设备，不可删除！");
						}  else {
							$("#delete1").click();
						}
					}

				});
			}

		});
</script>

<s:form id="pagerForm" action="chunk/chunkAction!listChunkInfo.do" method="post" theme="simple">
	
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>
	<s:hidden name="vo.chunkName"></s:hidden>
</s:form>


<div class="pageHeader">
	<s:form id="chunkForm" name="chunkForm" action="chunk/chunkAction!listChunkInfo.do" method="post" theme="simple" onsubmit="return navTabSearch(this);">
		<s:hidden name="pageNum"></s:hidden>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						名称：<s:textfield name="vo.chunkName"></s:textfield>
					</td>
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="chunk/chunkAction!toAdd.do" target="dialog"  title="添加应用分类信息" width="600" height="350" fresh="false" mask="true"><span>添加</span></a></li>
			<li><a class="delete" id="delete"><span>删除</span></a><a class="delete" id="delete1" style="display: none;" href="chunk/chunkAction!deleteChunkInfo.do?vo.chunkId={chunkId}" target="ajaxTodo" title="确定删除该应用分类?"><span>删除</span></a></li>
			<li><a class="edit" id="update"><span>修改</span></a><a class="edit" id="update1" style="display: none;" href="chunk/chunkAction!toUpdate.do?vo.chunkId={chunkId}" target="dialog"  title="修改应用分类信息" width="600" height="350" fresh="false" mask="true"><span>修改</span></a></li>
			<li><a class="icon" id="reserve"><span>备份</span></a><a class="icon" id="reserve1" style="display: none;" href="chunk/chunkAction!toReserve.do?vo.chunkId={chunkId}" target="dialog"  title="备份地址" width="600" height="400" fresh="false" mask="true"><span>备份</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr align="center">
				<th width="10%">设备ID</th>
				<th width="20%">地址</th>
				<th width="20%">备用地址</th>
				<th width="20%">状态</th>
				<th width="10%">创建人</th>
				<th width="20%">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" id="chunkInfo">
				<tr target="chunkId" rel="<s:property value='#chunkInfo.chunkId'/>">
					<td><s:property value="#chunkInfo.deviceId"/></td>
					<td><s:property value="#chunkInfo.chunkUrl"/></td>
					<td><s:property value="#chunkInfo.reserveUrl"/></td>
					<td>
						<s:if test="#chunkInfo.status==1">运行</s:if>
						<s:elseif test="#chunkInfo.status==2">空闲</s:elseif>
						<s:elseif test="#chunkInfo.status==3">暂停</s:elseif>
					</td>
					<td><s:property value="#chunkInfo.chunkCreateUser"/></td>
					<td><s:property value="#chunkInfo.chunkCreateTimeDsp"/></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>共<s:property value="totalCount"/>条</span>
		</div>
		
		<div class="pagination" targetType="<s:property value='targetType'/>" totalCount="<s:property value='totalCount'/>" numPerPage="<s:property value='numPerPage'/>" pageNumShown="<s:property value='pageNumShown'/>" currentPage="<s:property value='pageNum'/>"></div>

	</div>
</div>