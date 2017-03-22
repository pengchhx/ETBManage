<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" rel="stylesheet"
	href="js/uploadify-v2.1.4/uploadify.css"></link>
<%@ page import="com.archermind.etb.util.Constant"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
<!--
td {
	padding-top: 10px;
}
-->
</style>
<div class="pageContent">
	<s:form id="appPicUpload" theme="simple" method="post"
		action="ad/adPackage!closeUploadPage.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone);">
		<s:hidden name="fileuploadFileName" id="fileuploadFileName"></s:hidden>
		<s:hidden name="adResource.adPackageId" id="adResource.adPackageId"></s:hidden>
		<s:hidden name="adStyle"></s:hidden>
		<s:hidden name="adPackage.adPackageStatus" id="adPackageStatusAppAdd" />
		<div class="pageFormContent" layoutH="56">
			<div style="margin: 20px 10px 2px 15px;">
				<b>广告包素材新增</b><font color="red" size="10">（图片素材最多可上传<%=Constant.LIMIT_PIC_NUM%>张）
				</font>
			</div>
			<div class="navAppList" id="navAppAdd">
				<ul>
					<s:iterator value="adResourceList" id="adResource" status="st">
						<li>
							<div class="picDiv" align="center">
								<s:if test='#adResource.adResourceTypes=="1"'>
									<div class="deleteImgDivAppUpd"
										id="operaApp<s:property value="#st.index+1" />">
										<div class="btnDivAppList"
											onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
											 onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
											<a target="ajaxTodo" title="确定删除该图片?"
												callback="navTabAjaxDoneWithInFail"
												href="ad/adPackage!deleteAdResourceUploadApp.do?adResourceId=<s:property value='#adResource.adResourceId' />&adStyle=<s:property value="adStyle"/>&adResource.adPackageId=<s:property value='adResource.adPackageId' />">
												<img class="deleteImg" src="themes/default/images/del.png" />&nbsp;<font
												style="vertical-align: top;">删除</font>
											</a>
										</div>
									</div>
									<div class="listPic" style="position: relative; z-index: 1;"
										onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','operaApp');"
										onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','operaApp');">
										<a srcImg="<s:property value="adResourcePathDsp"/>"
											resourcePro='<s:property value="adResourceProduct" />'
											resourceAct='<s:property value="adResourceAction"/>'
											resourceTips='<s:property value="adResourceTips"/>'
											onclick="openDiv(this,'<%=Constant.AD_PACKAGE_BATCH_E%>');">
											<img src="<s:property value='adResourcePathDsp'/>"
											idNo="<s:property value="#st.index+1" />" alt="查看原图"
											class="resourceImgApp">
										</a>
									</div>
									<div class="opreaDivAppUpdSetting"
										id="operaParamApp<s:property value="#st.index+1" />">
										<font>播放时长：<s:property value="adResourceTimes" />秒
										</font>
									</div>
								</s:if>
							</div>
						</li>
					</s:iterator>
					<li><div class="picDiv" align="center">
							<div class="deleteImgDivAppUpd">
								<div class="btnDivAppList"></div>
							</div>
							<div
								style="position: relative; z-index: 1; text-align: center; vertical-align: middle;">
								<a class="add"
									href="ad/adPackage!toUploadApp.do?adResource.adPackageId=<s:property value="adResource.adPackageId"/>&&adStyle=<s:property value="adStyle"/>"
									target="dialog" rel="toUploadApp" title="素材添加" width="700"
									height="400" fresh="true" mask="true"><img id="img1"
									src="images/addAppSma.gif" class="resourceImgApp" /> </a>
							</div>
						</div></li>
				</ul>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" onclick="saveAppAdd('save');">&nbsp;保存草稿&nbsp;</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="saveAppAdd('commit');">&nbsp;提交&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
	<script type="text/javascript">
	
		var appPicUpload = $("#appPicUpload");
		
		$(function() {
			//双传完成后关闭上传页面模态子窗口
			if ($("body").data("toUploadApp") != undefined
					&& $("body").data("toUploadApp") != null
					&& $("body").data("toUploadApp") != '') {
				$.pdialog.close("toUploadApp");
			}
			
			//判断审核状态，不可修改，则关闭窗口，刷新查询列表
			if(!checkEditEnable('<s:property value="adPackage.adPackageStatus"/>')){
				closeCurrentToTabId("listAd");
			}
			
			//设置页面title
			window.document.title='<%=Constant.PROJECT_TITLE%>';
		});

		$(document).ready(function() {
			$("#navAppAdd .listPic img").each(function(i) {
				//设置修改和删除按钮的显示
				var divObj = $('#operaApp' + $(this).attr("idNo"));
				divObj.css({
					position : "absolute",
					left : $(this).position().left,
					top : $(this).position().top
				});
				$(this).parent().append(divObj);

				//设置播放时长和播放次数显示
				var divPamObj = $('#operaParamApp' + $(this).attr("idNo"));
				divPamObj.css({
					position : "absolute",
					left : $(this).position().left,
					top : $(this).position().top + 79
				});
				$(this).parent().append(divPamObj);
			});
			$("#navAppAdd a").each(function() {
				$(this).attr("hidefocus", true);
			});
			
			//选中的资源图片高亮显示
			$("#navAppAdd .listPic").click(function(){
				$("#navAppAdd .listPic").each(
						function(i) {
							$(this).find(".resourceImgApp").removeClass(
									"resourceImgSelected");
						});
				$(this).find(".resourceImgApp").addClass("resourceImgSelected");
			});
			
			//点击新增按钮，列表中高亮显示取消
			$(".add").click(function(){
				$("#navAppAdd .listPic").each(
						function(i) {
							$(this).find(".resourceImgApp").removeClass(
									"resourceImgSelected");
						});
			});
		});
		
		function saveAppAdd(saveFlag) {
			
			//提示信息
			var confirmMsg = '';
			
			if(saveFlag == 'save'){
				confirmMsg = '确定保存吗?';
			}else if(saveFlag == 'commit'){
				confirmMsg = '确定提交吗?';
			}
			
			alertMsg.confirm(confirmMsg, {
				okCall : function() {
					//添加判断,已审核或已发布不可修改
					$.ajax({
						type : "post",
						cache : false,
						url : "ad/adPackage!getAdpackageStatusById.do",
						data : "adPackage.adPackageId=<s:property value='adResource.adPackageId' />",
						datatype : "json",
						success : function(data) {
							if (!checkEditEnable(data.status)) {
								closeCurrentToTabId("listAd");
							} else {
								if(saveFlag == 'commit'){
									$("#adPackageStatusAppAdd").val("<%=Constant.AD_PACKAGE_STATUS_COMMITTED%>");
								}
								appPicUpload.action = "ad/adPackage!closeUploadPage.do";
								appPicUpload.submit();
							}
							return false;
						}
					});
				}
				
			});
		}
	</script>
</div>
