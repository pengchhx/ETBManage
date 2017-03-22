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
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
  function deleteOta(id){
	       if(confirm("是否确定删除该记录")){
	    	$.ajax({
	    			type:"post",
	    			cache:false,
	    			url:"<%=path%>/ota/versioninfo!deleteOta.do",
						data : "otaId=" + id,
						datatype : "json",
						success : function(data) {	
							alert("11111");
							var code = data.statusCode;	
							alert(code);
							if (code == 0) {
								var trid = document.getElementById("ota_"
										+ id);
								$(trid).remove();
								alert(data.message);
							}else{
								alert(data.message);
							}
						}
					});
		}
	}
  function preadd(){
	  var form =document.getElementById("form");
	  form.action="ota/versioninfo!preAddOta.do";
	  form.submit();
  }
  /**
  *审核通过和发布了不能修改,发布了不能删除
  */
  function rowclick(op)
  {
	 
	  if(op==0){
		  $("#rowedit").hide();
		  $("#rowedit1").show();
	  }
	  if(op==1){
		  $("#rowedit").show();
		  $("#rowedit1").hide();
	  }
	  if(op==2){
		  $("#rowedit").show();
		  $("#rowedit1").hide();
	  }
	  if(op==3){
		  $("#rowedit").hide();
		  $("#rowedit1").show();
	  }
	  if(op==4){
		  $("#rowedit").show();
		  $("#rowedit1").hide();
	  }
	  //$("#rowedit").attr('style', 'display: none'); 
  }
  function closeTab(json)
  {
	  navTab.closeTab("add_ota_info");
	  navTabAjaxDone(json);
  }
</script>
<title>OTA list</title>
</head>
<body>
<form id="pagerForm" method="post" action="<%=path%>/ota/versioninfo!getOtaVersionInfoList.do">
	<input type="hidden" name="name" value="${name}">
	<input type="hidden" name="pageNum" value="${pageNum }" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>
<div class="pageHeader">
	<form id="form" onsubmit="return navTabSearch(this);" action="<%=path%>/ota/versioninfo!getOtaVersionInfoList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					OTA版本名称：<input type="text" name="name" value="${name }" />
				</td>
				<td>
				<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					<!--  <input type="submit" target="navTab" rel="otamanger" value="检索" />-->
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>


<div class="pageContent j-resizeGrid">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
			<s:if test="canBeAdd==true">
			<a class="add" href="<%=path%>/ota/versioninfo!preAddOta.do"  target="navTab" mask=true rel="add_ota_info" title="新增"  fresh="false"><span>添加</span></a>
			</s:if>
			<s:else>
			<a class="add" href="<%=path%>/ota/versioninfo!preAddOta.do?canAdd=no"  target="ajaxTodo" ><span>添加</span></a>
			</s:else>
			</li>
			<li><a id="rowdelete" callback="closeTab" class="delete" href="ota/versioninfo!deleteOta.do?otaId={otaid}" target="ajaxTodo" title="确定删除该记录?"><span>删除</span></a></li>
			<li>
			<a  id="rowedit" class="edit" style="display: none;" href="ota/otaedit!preEditOta.do?otaInfo.id={otaid}"  target="ajaxTodo"><span>修改</span></a>
			<a  id="rowedit1" class="edit"   href="ota/otaedit!preEditOta.do?otaInfo.id={otaid}"  target="navTab" title="修改" rel="edit_ota_info"><span>修改</span></a>
			</li>
			 <li><a class="icon" href="ota/versioninfo!queryOta.do?otaInfo.id={otaid}"  target="navTab"  rel="query_ota" title="查看OTA信息"><span>查看</span></a></li>
		</ul>
	</div>
	<div class="grid">
		<div class="gridHeader">
			<div class="gridThead">
				<table style="width: 100%;" class="table" layoutH="138">
					<thead>
						<tr>
							<th width="15%">OTA版本名称</th>
							<th width="10%">版本状态</th>
							<th width="10%">创建人</th>
							<th width="15%">创建时间</th>
							<th width="50%">备注说明</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="list" var="arr" status="status"  id="arr" >
						 <tr target="otaid" rel="<s:property value="#arr.id" />" onclick="rowclick('<s:property value="#arr.infostat" />');">
						<td title="${arr.name }">${arr.name}</td>
						<td>
						<s:if test="%{#arr.infostat==0}">未提交</s:if>
						<s:if test="%{#arr.infostat==1}">待审核</s:if>
						<s:if test="%{#arr.infostat==2}">未发布</s:if>
						<s:if test="%{#arr.infostat==3}">审核驳回</s:if>
						<s:if test="%{#arr.infostat==4}">已发布</s:if>
						</td>
						<td>${arr.creater }</td>
						<td>${arr.createtimedsp }</td>
						<td title="${arr.tips }">${arr.tips }</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
		<div style="height: 300px; left: 57px; display: none;"
			class="resizeMarker"></div>
		<div style="height: 300px; left: 377px; display: none;"
			class="resizeProxy"></div>
	</div>
	<div class="panelBar">
		<div class="pages">
			<span>共<s:property value="totalCount"/>条</span>
		</div>
		
		<div class="pagination" targetType="<s:property value='targetType'/>" totalCount="<s:property value='totalCount'/>" numPerPage="<s:property value='numPerPage'/>" pageNumShown="<s:property value='pageNumShown'/>" currentPage="<s:property value='pageNum'/>"></div>

	</div>
</div>
</body>
</html>