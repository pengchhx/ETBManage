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
	<s:form id="commonPicUploadDouble" theme="simple" method="post"
		action="ad/adPackage!closeUploadPage.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone);">
		<s:hidden name="fileuploadFileName" id="fileuploadFileName"></s:hidden>
		<s:hidden name="adResource.adPackageId" id="adResource.adPackageId"></s:hidden>
		<s:hidden name="adPackage.adPackageStatus" id="adPackageStatusDoubleAdd"/>
		
		<div class="pageFormContent" layoutH="56">
			<div style="margin: 20px 10px 2px 15px;">
				<b>广告包素材新增</b><font color="red" size="10">（图片素材最多可上传<%=Constant.LIMIT_PIC_NUM%>张）
				</font>
			</div>
			<table style="width: 98%;" id="doubleAddTable">
				<tr>
					<td>
						<div class="titleBar">
							<div class="titleTd">
								<b>上半区域</b>
							</div>
						</div>
						<div class="navCommonDoubleList" style="padding-bottom: 10px;">
							<ul>
								<s:iterator value="adResourceDoubleUpperList"
									id="adResourceUpper" status="st">
									<li>
										<div class="picDiv" align="center">
											<s:if test='#adResourceUpper.adResourceTypes=="1"'>
												<div class="deleteImgDivDoubleUpd"
													id="operaUpper<s:property value="#st.index+1" />">
													<div class="btnDivDoubleList"
														onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
														onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
														<a target="ajaxTodo" title="确定删除该图片?"
															callback="navTabAjaxDoneWithInFail"
															href="ad/adPackage!deleteAdResourceUpload.do?adResourceId=<s:property value='#adResourceUpper.adResourceId' />&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER%>&adStyle=<s:property value="adStyle"/>&adResource.adPackageId=<s:property value='adResource.adPackageId' />">
															<img class="deleteImg"
															src="themes/default/images/del.png" />&nbsp;<font>删除</font>
														</a>
													</div>
												</div>
												<div class="listPicUpper"
													style="position: relative; z-index: 1;"
													onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','operaUpper');"
													onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','operaUpper');">
													<a srcImg="<s:property value="adResourcePathDsp"/>"
														resourcePro='<s:property value="adResourceProduct" />'
														resourceAct='<s:property value="adResourceAction"/>'
														resourceTips='<s:property value="adResourceTips"/>'
														onclick="openDiv(this,'<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER%>');">
														<img src="<s:property value='adResourcePathDsp'/>"
														idNo="<s:property value="#st.index+1" />" alt="查看原图"
														class="resourceImgDouble" />
													</a>
												</div>
												<div class="opreaDivDoubleUpdSetting"
													id="operaParamUpper<s:property value="#st.index+1" />">
													<font>播放时长：<s:property value="adResourceTimes" /> 秒
													</font>
												</div>
											</s:if>
											<s:else>
												<div class="deleteImgDivDoubleUpd"
													id="operaUpper<s:property value="#st.index+1" />">
													<div class="btnDivDoubleList"
														onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
														onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
														<a target="ajaxTodo" title="确定删除该视频?"
															callback="navTabAjaxDoneWithInFail"
															href="ad/adPackage!deleteAdResourceUpload.do?adResourceId=<s:property value='#adResourceUpper.adResourceId' />&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER%>&adStyle=<s:property value="adStyle"/>&adResource.adPackageId=<s:property value='adResource.adPackageId' />">
															<img class="deleteImg"
															src="themes/default/images/del.png" />&nbsp;<font>删除</font>
														</a>
													</div>
												</div>

												<div class="listPicUpper"
													style="position: relative; z-index: 1;"
													onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','operaUpper');"
													onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','operaUpper');">
													<a href="javascript:void(0);"
														srcImg="<s:property value="adResourcePathDsp"/>"
														resourcePro='<s:property value="adResourceProduct" />'
														resourceTips='<s:property value="adResourceTips"/>'
														onclick="openVedioDiv(this,'<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER%>');return false;">
														<!-- "下载"图片 --> <img
														idNo="<s:property value="#st.index+1" />" alt="点击播放"
														class="resourceImgDouble"
														src='<s:property value="adResourceThumbnailPathDsp"/>'>
													</a>
												</div>
												<div class="playButtonDivSingleUpdSetting"
													id="playButtonParamUpperAdd<s:property value="#st.index+1" />">
													<img src="images/images_Pause_Scgedyke.png"
														style="cursor: pointer" onmouseover="playButtonOn(this)"
														onmouseout="playButtonOff(this)">
												</div>
												<div class="opreaDivDoubleUpdSetting"
													id="operaParamUpper<s:property value="#st.index+1" />">
													<font>播放次数：<s:property value="adResourceCount" /> 次
													</font>
												</div>
											</s:else>
										</div>
									</li>
								</s:iterator>
								<li>
									<div class="picDiv" align="center">
										<div class="deleteImgDivDoubleUpd">
											<div class="btnDivDoubleList"></div>
										</div>
										<div style="position: relative; z-index: 1;">
											<a class="add"
												href="ad/adPackage!toUpload.do?adResource.adPackageId=<s:property value="adResource.adPackageId"/>&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER%>&adStyle=<s:property value="adStyle"/>"
												target="dialog" rel="toUpload" title="素材添加" width="700"
												height="400" fresh="true" mask="true"><img id="img1"
												src="images/addCommon_double.gif" class="resourceImgDouble" />
											</a>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="titleBar">
							<div class="titleTd">
								<b>下半区域</b>
							</div>
						</div>
						<div class="navCommonDoubleList">
							<ul>
								<s:iterator value="adResourceDoubleLowerList"
									id="adResourceLower" status="st">
									<li>
										<div class="picDiv" align="center">
											<s:if test='#adResourceLower.adResourceTypes=="1"'>
												<div class="deleteImgDivDoubleUpd"
													id="operaLower<s:property value="#st.index+1" />">
													<div class="btnDivDoubleList"
														onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
														onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">

														<a target="ajaxTodo" title="确定删除该图片?"
															callback="navTabAjaxDoneWithInFail"
															href="ad/adPackage!deleteAdResourceUpload.do?adResourceId=<s:property value='#adResourceLower.adResourceId' />&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER%>&adStyle=<s:property value="adStyle"/>&adResource.adPackageId=<s:property value='adResource.adPackageId' />">
															<img class="deleteImg"
															src="themes/default/images/del.png" />&nbsp;<font>删除</font>
														</a>
													</div>
												</div>
												<div class="listPicLower"
													style="position: relative; z-index: 1;"
													onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','operaLower');"
													onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','operaLower');">
													<a srcImg="<s:property value="adResourcePathDsp"/>"
														resourcePro='<s:property value="adResourceProduct" />'
														resourceAct='<s:property value="adResourceAction"/>'
														resourceTips='<s:property value="adResourceTips"/>'
														onclick="openDiv(this,'<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER%>');">
														<img src="<s:property value='adResourcePathDsp'/>"
														idNo="<s:property value="#st.index+1" />" alt="查看原图"
														class="resourceImgDouble" />
													</a>
												</div>
												<div class="opreaDivDoubleUpdSetting"
													id="operaParamLower<s:property value="#st.index+1" />">
													<font>播放时长：<s:property value="adResourceTimes" /> 秒
													</font>
												</div>
											</s:if>
											<s:else>
												<div class="deleteImgDivDoubleUpd"
													id="operaLower<s:property value="#st.index+1" />">
													<div class="btnDivDoubleList"
														onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
														onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
														<a target="ajaxTodo" title="确定删除该视频?"
															callback="navTabAjaxDoneWithInFail"
															href="ad/adPackage!deleteAdResourceUpload.do?adResourceId=<s:property value='#adResourceLower.adResourceId' />&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER%>&adStyle=<s:property value="adStyle"/>&adResource.adPackageId=<s:property value='adResource.adPackageId' />">
															<img class="deleteImg"
															src="themes/default/images/del.png" />&nbsp;<font>删除</font>
														</a>
													</div>
												</div>

												<div class="listPicLower"
													style="position: relative; z-index: 1;"
													onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','operaLower');"
													onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','operaLower');">
													<a href="javascript:void(0);"
														srcImg="<s:property value="adResourcePathDsp"/>"
														resourcePro='<s:property value="adResourceProduct" />'
														resourceTips='<s:property value="adResourceTips"/>'
														onclick="openVedioDiv(this,'<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER%>');return false;">
														<!-- "下载"图片 --> <img
														idNo="<s:property value="#st.index+1" />" alt="点击播放"
														class="resourceImgDouble"
														src='<s:property value="adResourceThumbnailPathDsp"/>'>
													</a>
												</div>
												<div class="playButtonDivSingleUpdSetting"
													id="playButtonParamLowerAdd<s:property value="#st.index+1" />">
													<img src="images/images_Pause_Scgedyke.png"
														style="cursor: pointer" onmouseover="playButtonOn(this)"
														onmouseout="playButtonOff(this)">
												</div>
												<div class="opreaDivDoubleUpdSetting"
													id="operaParamLower<s:property value="#st.index+1" />">
													<font>播放次数：<s:property value="adResourceCount" /> 次
													</font>
												</div>
											</s:else>
										</div>
									</li>
								</s:iterator>
								<li>
									<div class="picDiv" align="center">
										<div class="deleteImgDivDoubleUpd">
											<div class="btnDivDoubleList"></div>
										</div>
										<div style="position: relative; z-index: 1;">
											<a class="add"
												href="ad/adPackage!toUpload.do?adResource.adPackageId=<s:property value="adResource.adPackageId"/>&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER%>&adStyle=<s:property value="adStyle"/>"
												target="dialog" rel="toUpload" title="素材添加" width="700"
												height="400" fresh="true" mask="true"><img id="img1"
												src="images/addCommon_double.gif" class="resourceImgDouble" />
											</a>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" onclick="saveDoubleAdd('save');">&nbsp;保存草稿&nbsp;</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="saveDoubleAdd('commit');">&nbsp;提交&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
	<script type="text/javascript">
	
		var commonPicUploadDouble = $("#commonPicUploadDouble");
	
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

		$(document).ready(
				function() {

					$(".listPicUpper img").each(
							function(i) {
								//设置修改和删除按钮的显示：下屏
								var divObjUpper = $('#operaUpper'
										+ $(this).attr("idNo"));
								divObjUpper.css({
									position : "absolute",
									left : $(this).position().left,
									top : $(this).position().top
								});
								$(this).parent().append(divObjUpper);

								//设置播放时长和播放次数显示：下屏
								var divPamObjUpper = $('#operaParamUpper'
										+ $(this).attr("idNo"));
								divPamObjUpper.css({
									position : "absolute",
									left : $(this).position().left,
									top : $(this).position().top + 101
								});
								$(this).parent().append(divPamObjUpper);
								
								//播放button显示
								var divPlayButtonObj = $('#playButtonParamUpperAdd'
										+ $(this).attr("idNo"));
								if (divPlayButtonObj != null
										&& divPlayButtonObj != undefined) {
									divPlayButtonObj
											.css({
												position : "absolute",
												left : $(this)
														.position().left + 50,
												top : $(this)
														.position().top + 25
											});
									$(this).parent().append(
											divPlayButtonObj);
								}
							});

					$(".listPicLower img").each(
							function(i) {
								//设置修改和删除按钮的显示：下屏
								var divObjLower = $('#operaLower'
										+ $(this).attr("idNo"));
								divObjLower.css({
									position : "absolute",
									left : $(this).position().left,
									top : $(this).position().top
								});
								$(this).parent().append(divObjLower);

								//设置播放时长和播放次数显示：下屏
								var divPamObjLower = $('#operaParamLower'
										+ $(this).attr("idNo"));
								divPamObjLower.css({
									position : "absolute",
									left : $(this).position().left,
									top : $(this).position().top + 101
								});
								$(this).parent().append(divPamObjLower);
								
								//播放button显示
								var divPlayButtonObj = $('#playButtonParamLowerAdd'
										+ $(this).attr("idNo"));
								if (divPlayButtonObj != null
										&& divPlayButtonObj != undefined) {
									divPlayButtonObj
											.css({
												position : "absolute",
												left : $(this)
														.position().left + 50,
												top : $(this)
														.position().top + 25
											});
									$(this).parent().append(
											divPlayButtonObj);
								}
							});

					$("#doubleAddTable a").each(function() {
						$(this).attr("hidefocus", true);
					});
					
					//选中的资源图片高亮显示
					$("#commonPicUploadDouble .listPicUpper,.listPicLower").click(function(){
						$("#commonPicUploadDouble .listPicUpper").each(
								function(i) {
									$(this).find(".resourceImgDouble").removeClass(
											"resourceImgSelected");
								});
						
						$("#commonPicUploadDouble .listPicLower").each(
								function(i) {
									$(this).find(".resourceImgDouble").removeClass(
											"resourceImgSelected");
								});
						$(this).find(".resourceImgDouble").addClass("resourceImgSelected");
					});
					
					//点击新增按钮，列表中高亮显示取消
					$(".add").click(function(){
						$("#commonPicUploadDouble .listPicUpper").each(
								function(i) {
									$(this).find(".resourceImgDouble").removeClass(
											"resourceImgSelected");
								});
						
						$("#commonPicUploadDouble .listPicLower").each(
								function(i) {
									$(this).find(".resourceImgDouble").removeClass(
											"resourceImgSelected");
								});
					});
				});
		
		function saveDoubleAdd(saveFlag) {
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
									$("#adPackageStatusDoubleAdd").val("<%=Constant.AD_PACKAGE_STATUS_COMMITTED%>");
								}
								commonPicUploadDouble.action = "ad/adPackage!closeUploadPage.do";
								commonPicUploadDouble.submit();
							}
							return false;
						}
					});
				}
				
			});
		}
		
	</script>
</div>
