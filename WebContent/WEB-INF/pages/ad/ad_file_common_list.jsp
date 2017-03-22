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
<div class="pageContent">
	<s:form id="commonPicUpload" theme="simple" method="post"
		action="ad/adPackage!closeUploadPage.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone);">
		<s:hidden name="fileuploadFileName" id="fileuploadFileName"></s:hidden>
		<s:hidden name="adResource.adPackageId" id="adResource.adPackageId"></s:hidden>
		<s:hidden name="adPackage.adPackageStatus" id="adPackageStatusSingleAdd"/>
		<div class="pageFormContent" layoutH="56">
			<div style="margin: 20px 10px 2px 15px;">
				<b>广告包素材新增</b><font color="red" size="10">（图片素材最多可上传<%=Constant.LIMIT_PIC_NUM%>张）
				</font>
			</div>
			<div class="nav" id="navSingleAdd">
				<ul>
					<s:iterator value="adResourceList" id="adResource" status="st">
						<li>
							<div class="picDiv" align="center">
								<s:if test='#adResource.adResourceTypes=="1"'>
									<div class="deleteImgDivSingleUpd"
										id="opera<s:property value="#st.index+1" />">
										<div class="btnDivSingleList"
											onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
											onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
											<a target="ajaxTodo" title="确定删除该图片?"
												callback="navTabAjaxDoneWithInFail"
												href="ad/adPackage!deleteAdResourceUpload.do?adResourceId=<s:property value='#adResource.adResourceId' />&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_SINGLE%>&&adStyle=<s:property value="adStyle"/>&adResource.adPackageId=<s:property value='adResource.adPackageId' />">
												<img class="deleteImg" src="themes/default/images/del.png" />&nbsp;<font
												style="vertical-align: top;">删除</font>
											</a>
										</div>
									</div>
									<div class="listPic" style="position: relative; z-index: 1;"
										onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','opera');"
										onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','opera');">
										<a srcImg="<s:property value="adResourcePathDsp"/>"
											resourcePro='<s:property value="adResourceProduct" />'
											resourceAct='<s:property value="adResourceAction"/>'
											resourceTips='<s:property value="adResourceTips"/>'
											onclick="openDiv(this,'<%=Constant.AD_RESOURCE_POSITION_SIGN_SINGLE%>');">
											<img src="<s:property value='adResourcePathDsp'/>"
											idNo="<s:property value="#st.index+1" />" alt="查看原图"
											class="resourceImgSingle">
										</a>
									</div>
									<div class="opreaDivSingleUpdSetting"
										id="operaParam<s:property value="#st.index+1" />">
										<font>播放时长：<s:property value="adResourceTimes" /> 秒
										</font>
									</div>
								</s:if>
								<s:else>
									<div class="deleteImgDivSingleUpd"
										id="opera<s:property value="#st.index+1" />">
										<div class="btnDivSingleList"
											onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
											onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
											 <a target="ajaxTodo" title="确定删除该视频?"
												callback="navTabAjaxDoneWithInFail"
												href="ad/adPackage!deleteAdResourceUpload.do?adResourceId=<s:property value='#adResource.adResourceId' />&&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_SINGLE%>&& adStyle=<s:property value="adStyle"/>&adResource.adPackageId=<s:property value='adResource.adPackageId' />">
												<img class="deleteImg" src="themes/default/images/del.png" />&nbsp;<font
												style="vertical-align: top;">删除</font>
											</a>
										</div>
									</div>
									<div class="listPic" style="position: relative; z-index: 1;"
										onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','opera');"
										onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','opera');">
										<a href="javascript:void(0);" srcImg="<s:property value="adResourcePathDsp"/>"
											resourcePro='<s:property value="adResourceProduct" />'
											resourceTips='<s:property value="adResourceTips"/>'
											onclick="openVedioDiv(this,'<%=Constant.AD_RESOURCE_POSITION_SIGN_SINGLE%>');return false;">
											<!-- "下载"图片 --> <img
											idNo="<s:property value="#st.index+1" />" alt="点击播放"
											src='<s:property value="adResourceThumbnailPathDsp"/>' class="resourceImgSingle">
										</a>
									</div>
									<div class="playButtonDivSingleUpdSetting"
										id="playButtonParamSingleAdd<s:property value="#st.index+1" />">
										<img src="images/images_Pause_Scgedyke.png"
											style="cursor: pointer" onmouseover="playButtonOn(this)"
											onmouseout="playButtonOff(this)">
									</div>
									<div class="opreaDivSingleUpdSetting"
										id="operaParam<s:property value="#st.index+1" />">
										<font>播放次数：<s:property value="adResourceCount" /> 次
										</font>
									</div>
								</s:else>
							</div>
						</li>
					</s:iterator>
					<li><div class="picDiv" align="center">
							<div class="deleteImgDivSingleUpd">
								<div class="btnDivSingleList"></div>
							</div>
							<div
								style="position: relative; z-index: 1; text-align: center; vertical-align: middle;">
								<a class="add"
									href="ad/adPackage!toUpload.do?adResource.adPackageId=<s:property value="adResource.adPackageId"/>&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_SINGLE%>&adStyle=<s:property value="adStyle"/>"
									target="dialog" rel="toUpload" title="素材添加" width="700"
									height="400" fresh="true" mask="true"><img id="img1"
									src="images/addCommon.gif" class="resourceImgSingle" /> </a>
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
							<button type="button" onclick="saveSingleAdd('save');">&nbsp;保存草稿&nbsp;</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="saveSingleAdd('commit');">&nbsp;提交&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
	<script type="text/javascript">
		var commonPicUpload = $("#commonPicUpload");
		$(function() {
			//增加是否关闭窗口的判断for上传图片数量限定,2013-10-31,RanChen
			if('<s:property value="closeDialogFlag" />' == 'true'){
				//上传完成后关闭上传页面模态子窗口
				if ($("body").data("toUpload") != undefined
						&& $("body").data("toUpload") != null
						&& $("body").data("toUpload") != '') {
					$.pdialog.close("toUpload");
				}
			}
			
			//判断审核状态，不可修改，则关闭窗口，刷新查询列表
			if(!checkEditEnable('<s:property value="adPackage.adPackageStatus"/>')){
				closeCurrentToTabId("listAd");
			}
			
			//设置页面title
			window.document.title='<%=Constant.PROJECT_TITLE%>';
		});

		$(document).ready(function() {
			$("#navSingleAdd .listPic img").each(function(i) {
				//设置修改和删除按钮的显示
				var divObj = $('#opera' + $(this).attr("idNo"));
				divObj.css({
					position : "absolute",
					left : $(this).position().left,
					top : $(this).position().top
				});
				$(this).parent().append(divObj);

				//设置播放时长和播放次数显示
				var divPamObj = $('#operaParam' + $(this).attr("idNo"));
				divPamObj.css({
					position : "absolute",
					left : $(this).position().left,
					top : $(this).position().top + 161
				});
				$(this).parent().append(divPamObj);
				
				//播放button显示
				var divPlayButtonObj = $('#playButtonParamSingleAdd'
						+ $(this).attr("idNo"));
				if(divPlayButtonObj!=null && divPlayButtonObj!=undefined){
					divPlayButtonObj.css({
						position : "absolute",
						left : $(this).position().left+30,
						top : $(this).position().top + 55
					});
					$(this).parent().append(divPlayButtonObj);
				}
			});
			$("#navSingleAdd a").each(function() {
				$(this).attr("hidefocus", true);
			});
			
			//选中的资源图片高亮显示
			$("#navSingleAdd .listPic").click(function(){
				$("#navSingleAdd .listPic").each(
						function(i) {
							$(this).find(".resourceImgSingle").removeClass(
									"resourceImgSelected");
						});
				$(this).find(".resourceImgSingle").addClass("resourceImgSelected");
			});
			
			//点击新增按钮，列表中高亮显示取消
			$(".add").click(function(){
				$("#navSingleAdd .listPic").each(
						function(i) {
							$(this).find(".resourceImgSingle").removeClass(
									"resourceImgSelected");
						});
			});
		});
		
		function saveSingleAdd(saveFlag) {
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
									$("#adPackageStatusSingleAdd").val("<%=Constant.AD_PACKAGE_STATUS_COMMITTED%>");
								}
								commonPicUpload.action = "ad/adPackage!closeUploadPage.do";
								commonPicUpload.submit();
							}
							return false;
						}
					});
				}
				
			});
		}
		
	</script>
</div>
