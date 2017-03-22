<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page import="com.archermind.etb.util.Constant"%>
<script type="text/javascript">
	/** 广告包状态 - 0 新增;1-审核通过；2-审核不通过；3-已修改待审核；4-已发布； */
	$("#modifyAd").click(
			function(event) {
				//var $this = $(this);
				//var href = "ad/adPackage!adModify.do?adPackage.adPackageId={adPackageId}";
				var url = unescape($("#modifyAdAction").attr("href")).replaceTmById(
						$(event.target).parents(".unitBox:first"));
				var adPackageId = unescape("{adPackageId}").replaceTmById(
						$(event.target).parents(".unitBox:first"));
				if (!url.isFinishedTm()) {
					alertMsg.error(DWZ.msg("alertSelectMsg"));
					return false;
				} else {
					$.ajax({
						type : "post",
						cache : false,
						url : "ad/adPackage!getAdpackageStatusById.do",
						data : "adPackage.adPackageId=" + adPackageId,
						datatype : "json",
						success : function(data) {
							
							//判断是否可以修改，可修改则进行修改操作
							if (checkEditEnable(data.status)) {
								$("#modifyAdAction").click();
							}
						}

					});
				}

			});
	
	$("#deleteAd").click(function(event){
		var url = unescape($("#deleteAdAction").attr("href")).replaceTmById(
				$(event.target).parents(".unitBox:first"));
		
		var adPackageId = unescape("{adPackageId}").replaceTmById(
				$(event.target).parents(".unitBox:first"));
		
		if (!url.isFinishedTm()) {
			alertMsg.error(DWZ.msg("alertSelectMsg"));
			return false;
		} else {
		$.ajax({
			type:"post",
			cache:false,
			url:"ad/adPackage!getAdpackageStatusById.do",
			data:"adPackage.adPackageId="+adPackageId,
			datatype:"json",
			success:function(data){
				//只有已发布且截止日期已过期的广告包才可以删除
				var curTime = getCurentTime();
				if (data.status == '<%=Constant.AD_PACKAGE_STATUS_PUBLISHED%>'
						&& (!timeCompare(curTime, data.endTime))) {// 已发布
					$("#deleteAdAction").click();
				}else{
					alertMsg.error('该广告包还未过期，不能删除！');
					return false;
				} 
			}
		});
		}
	});
</script>
<s:form id="pagerForm" action="ad/adPackage!listAd.do" method="post"
	theme="simple">
	<s:hidden name="pageNum"></s:hidden>
	<s:hidden name="numPerPage"></s:hidden>

	<s:hidden name="showSightFlag"></s:hidden>
	<s:hidden name="adPackage.adPackageId" />
	<s:hidden name="adPackage.adPackageName"></s:hidden>
</s:form>

<div class="pageHeader">
	<s:form id="adForm" name="adForm" action="ad/adPackage!listAd.do"
		method="post" theme="simple" onsubmit="return navTabSearch(this);">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>广告包名称：<s:textfield name="adPackage.adPackageName"
							width="200px"></s:textfield></td>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="ad/adPackage!toTypeSelect.do"
				target="dialog" rel="toTypeSelect" title="广告类型选择" width="520"
				height="200" fresh="false" resizable="false" maxable=false
				mask="true"><span>添加</span></a></li>
			<li><a class="delete" id="deleteAd"><span>删除</span></a>
			<a class="delete" id="deleteAdAction"  style="display: none;"
				href="ad/adPackage!toAdDelete.do?adPackage.adPackageId={adPackageId}"
			    target="ajaxTodo" title="确定删除该广告"><span>删除</span></a>
			</li>
			<li><a class="edit" id="modifyAd"><span>修改</span></a> <a
				class="edit" id="modifyAdAction" style="display: none;"
				href="ad/adPackage!toAdModify.do?adPackage.adPackageId={adPackageId}"
				target="navTab" rel="adModify" title="广告修改" fresh="false"><span>修改</span></a>
			</li>
			<li><a class="icon"
				href="ad/adPackage!toAdView.do?adPackage.adPackageId={adPackageId}"
				target="navTab" rel="adView" title="广告查看" width="520" height="300"
				fresh="false"><span>查看</span></a></li>
		</ul>
	</div>
	<div class="grid">
		<div class="gridHeader">
			<div class="gridThead">
				<table class="table" style="width: 100%;" layoutH="138">
					<thead>
						<tr align="center">
							<th width="80px">批次号</th>
							<th width="80px">模板名称</th>
							<th>广告包名称</th>
							<th width="125px">开始时间</th>
							<th width="125px">截止时间</th>
							<th width="85px">广告包状态</th>
							<th width="80px">创建人</th>
							<th width="80px">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="adList" id="ad">
							<tr target="adPackageId"
								rel="<s:property value='#ad[0].adPackageId'/>">
								<td><s:property value="#ad[0].adPackageBatchNo" /></td>
								<td><s:property value="#ad[1].adTemplateName" /></td>
								<td title="${ad[0].adPackageName}"><s:property
										value="#ad[0].adPackageName" /></td>
								<td><s:property value="#ad[0].adPackageBegintimeList" /></td>
								<td><s:property value="#ad[0].adPackageEndtimeList" /></td>
								<td><s:property value="#ad[0].adPackageStatusDsp" /></td>
								<td><s:property value="#ad[0].adPackageCreater" /></td>
								<td><s:property value="#ad[0].adPackageCreaterTimeDsp" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="panelBar">
		<div class="pages">
			<%-- 			<span>显示</span> --%>
			<%-- 			<s:select name="numPerPage" cssClass="combox" --%>
			<%-- 				list="#{30:'30',50:'50',100:'100',200:'200'}" --%>
			<%-- 				onchange="navTabPageBreak({numPerPage:this.value})"></s:select> --%>
			<span>共<s:property value="totalCount" />条
			</span>
		</div>

		<div class="pagination" targetType="<s:property value='targetType'/>"
			totalCount="<s:property value='totalCount'/>"
			numPerPage="<s:property value='numPerPage'/>"
			pageNumShown="<s:property value='pageNumShown'/>"
			currentPage="<s:property value='pageNum'/>"></div>

	</div>
</div>