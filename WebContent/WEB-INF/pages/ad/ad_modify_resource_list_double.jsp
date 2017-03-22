<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" rel="stylesheet"
	href="js/uploadify-v2.1.4/uploadify.css"></link>
<%@page import="com.archermind.etb.util.Constant"%>
<table style="width: 100%;" id="adResourceDoubleModify">
	<tr valign="top">
		<td><div class="titleBar">
				<div class="titleTd">
					<b>广告包素材修改</b><font color="red" size="10">（图片素材最多可上传<%=Constant.LIMIT_PIC_NUM%>张）
					</font>
				</div>
			</div></td>
	</tr>
	<tr>
		<td style="padding-top: 0px; padding-bottom: 15px;">
			<div class="navCommonDouble">
				<ul>
					<s:iterator value="adResourceDoubleUpperList" id="adResourceUpper"
						status="st">
						<li>
							<div class="picDiv" align="center">
								<s:if test='#adResourceUpper.adResourceTypes=="1"'>
									<div class="deleteImgDivDoubleUpd"
										id="operaDoubleModifyUpper<s:property value="#st.index+1" />">
										<div class="btnDivDoubleUpd"
											onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
											 onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
											<a title="素材修改" rel="toAdResourceModify" target="dialog"
												onclick="updBtnShow(this);"
												width="700" height="400" mask="true"
												href='ad/adPackage!toAdResourceModify.do?adResource.adResourceId=<s:property value="#adResourceUpper.adResourceId"/>&adResource.adPackageId=<s:property value='adPackage.adPackageId' />&adPackage.adTemplateId=<s:property value="adPackage.adTemplateId"/>&adType=<s:property value="adType"/>'>
												<img class="deleteImg" src="themes/default/images/upd.png" />&nbsp;<font>修改</font>
											</a> <a target="ajaxTodo" title="确定删除该图片?"
												callback="navTabAjaxDoneWithInFailUpd"
												href="ad/adPackage!deleteAdResource.do?adResourceId=<s:property value='#adResourceUpper.adResourceId' />&adResource.adPackageId=<s:property value='adPackage.adPackageId' />">
												<img class="deleteImg" src="themes/default/images/del.png" />&nbsp;<font>删除</font>
											</a>
										</div>
									</div>
									<div class="updPicUpper"
										style="position: relative; z-index: 1;"
										onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','operaDoubleModifyUpper');"
										onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','operaDoubleModifyUpper');">
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
										id="operaParamDoubleModifyUpper<s:property value="#st.index+1" />">
										<font>播放时长：<s:property value="adResourceTimes" /> 秒
										</font>
									</div>
								</s:if>
								<s:elseif test='#adResourceUpper.adResourceTypes=="2"'>
									<div class="deleteImgDivDoubleUpd"
										id="operaDoubleModifyUpper<s:property value="#st.index+1" />">
										<div class="btnDivDoubleUpd" onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
										onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
											<a title="素材修改" rel="toAdResourceModify" target="dialog"
												onclick="updBtnShow(this);"
												width="700" height="400" mask="true"
												href='ad/adPackage!toAdResourceModify.do?adResource.adResourceId=<s:property value="#adResourceUpper.adResourceId"/>&adResource.adPackageId=<s:property value='adPackage.adPackageId' />&adPackage.adTemplateId=<s:property value="adPackage.adTemplateId"/>&adType=<s:property value="adType"/>'>
												<img class="deleteImg" src="themes/default/images/upd.png" />&nbsp;<font>修改</font>
											</a> <a target="ajaxTodo" title="确定删除该视频?"
												callback="navTabAjaxDoneWithInFailUpd"
												href="ad/adPackage!deleteAdResource.do?adResourceId=<s:property value='#adResourceUpper.adResourceId' />&adResource.adPackageId=<s:property value='adPackage.adPackageId' />">
												<img class="deleteImg" src="themes/default/images/del.png" />&nbsp;<font>删除</font>
											</a>
										</div>
									</div>
									<div class="updPicUpper"
										style="position: relative; z-index: 1;"
										onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','operaDoubleModifyUpper');"
										onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','operaDoubleModifyUpper');">
										<a  srcImg="<s:property value="adResourcePathDsp"/>"
											resourcePro='<s:property value="adResourceProduct" />'
											resourceTips='<s:property value="adResourceTips"/>'
											onclick="openVedioDiv(this,'<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER%>');">
											<!-- "下载"图片 --> <img
											idNo="<s:property value="#st.index+1" />" alt="点击播放"
											class="resourceImgDouble" src='<s:property value="adResourceThumbnailPathDsp"/>'>
										</a>
									</div>
									<div class="playButtonDivSingleUpdSetting"
										id="playButtonParamUpperModify<s:property value="#st.index+1" />">
										<img src="images/images_Pause_Scgedyke.png"
											style="cursor: pointer" onmouseover="playButtonOn(this)"
											onmouseout="playButtonOff(this)">
									</div>
									<div class="opreaDivDoubleUpdSetting"
										id="operaParamDoubleModifyUpper<s:property value="#st.index+1" />">
										<font>播放次数：<s:property value="adResourceCount" /> 次
										</font>
									</div>
								</s:elseif>
							</div>
						</li>
					</s:iterator>
					<li>
						<div class="picDiv" align="center">
							<div class="deleteImgDivSingleUpd">
								<div class="btnDivSingleUpd"></div>
							</div>
							<div style="position: relative; z-index: 1;">
								<a class="add"
									href="ad/adPackage!toAdResourceModify.do?adResource.adPackageId=<s:property value="adPackage.adPackageId"/>&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_UPPER%>&adPackage.adTemplateId=<s:property value="adPackage.adTemplateId"/>&adType=<s:property value="adType"/>"
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
			<div class="splitBorder"></div>
		</td>
	</tr>
	<tr>
		<td style="padding-top: 0px;">
			<div class="navCommonDouble">
				<ul>
					<s:iterator value="adResourceDoubleLowerList" id="adResourceLower"
						status="st">
						<li>
							<div class="picDiv" align="center">
								<s:if test='#adResourceLower.adResourceTypes=="1"'>
									<div class="deleteImgDivDoubleUpd"
										id="operaDoubleModifyLower<s:property value="#st.index+1" />">
										<div class="btnDivDoubleUpd"
											onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
											 onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
											<a title="素材修改" rel="toAdResourceModify" target="dialog"
												onclick="updBtnShow(this);"
												width="700" height="400" mask="true"
												href='ad/adPackage!toAdResourceModify.do?adResource.adResourceId=<s:property value="#adResourceLower.adResourceId"/>&adResource.adPackageId=<s:property value='adPackage.adPackageId' />&adPackage.adTemplateId=<s:property value="adPackage.adTemplateId"/>&adType=<s:property value="adType"/>'>
												<img class="deleteImg" src="themes/default/images/upd.png" />&nbsp;<font>修改</font>
											</a> <a target="ajaxTodo" title="确定删除该图片?"
												callback="navTabAjaxDoneWithInFailUpd"
												href="ad/adPackage!deleteAdResource.do?adResourceId=<s:property value='#adResourceLower.adResourceId' />&adResource.adPackageId=<s:property value='adPackage.adPackageId' />">
												<img class="deleteImg" src="themes/default/images/del.png" />&nbsp;<font>删除</font>
											</a>
										</div>
									</div>
									<div class="updPicLower"
										style="position: relative; z-index: 1;"
										onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','operaDoubleModifyLower');"
										onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','operaDoubleModifyLower');">
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
										id="operaParamDoubleModifyLower<s:property value="#st.index+1" />">
										<font>播放时长： <s:property value="adResourceTimes" /> 秒
										</font>
									</div>
								</s:if>
								<s:elseif test='#adResourceLower.adResourceTypes=="2"'>
									<div class="deleteImgDivDoubleUpd"
										id="operaDoubleModifyLower<s:property value="#st.index+1" />">
										<div class="btnDivDoubleUpd" onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
										onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
											<a title="素材修改" rel="toAdResourceModify" target="dialog"
												onclick="updBtnShow(this);"
												width="700" height="400" mask="true"
												href='ad/adPackage!toAdResourceModify.do?adResource.adResourceId=<s:property value="#adResourceLower.adResourceId"/>&adResource.adPackageId=<s:property value='adPackage.adPackageId' />&adPackage.adTemplateId=<s:property value="adPackage.adTemplateId"/>&adType=<s:property value="adType"/>'>
												<img class="deleteImg" src="themes/default/images/upd.png" />&nbsp;<font>修改</font>
											</a> <a target="ajaxTodo" title="确定删除该视频?"
												callback="navTabAjaxDoneWithInFailUpd"
												href="ad/adPackage!deleteAdResource.do?adResourceId=<s:property value='#adResourceLower.adResourceId' />&adResource.adPackageId=<s:property value='adPackage.adPackageId' />">
												<img class="deleteImg" src="themes/default/images/del.png" />&nbsp;<font>删除</font>
											</a>
										</div>
									</div>
									<div class="updPicLower"
										style="position: relative; z-index: 1;"
										onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','operaDoubleModifyLower');"
										onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','operaDoubleModifyLower');">
										<a  srcImg="<s:property value="adResourcePathDsp"/>"
											resourcePro='<s:property value="adResourceProduct" />'
											resourceTips='<s:property value="adResourceTips"/>'
											onclick="openVedioDiv(this,'<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER%>');">
											<!-- "下载"图片 --> <img
											idNo="<s:property value="#st.index+1" />" alt="点击播放"
											class="resourceImgDouble" src='<s:property value="adResourceThumbnailPathDsp"/>'>
										</a>
									</div>
									<div class="playButtonDivSingleUpdSetting"
										id="playButtonParamLowerModify<s:property value="#st.index+1" />">
										<img src="images/images_Pause_Scgedyke.png"
											style="cursor: pointer" onmouseover="playButtonOn(this)"
											onmouseout="playButtonOff(this)">
									</div>
									<div class="opreaDivDoubleUpdSetting"
										id="operaParamDoubleModifyLower<s:property value="#st.index+1" />">
										<font>播放次数： <s:property value="adResourceCount" /> 次
										</font>
									</div>
								</s:elseif>
							</div>
						</li>
					</s:iterator>
					<li>
						<div class="picDiv" align="center">
							<div class="deleteImgDivDoubleUpd">
								<div class="btnDivDoubleUpd"></div>
							</div>
							<div style="position: relative; z-index: 1;">
								<a class="add"
									href="ad/adPackage!toAdResourceModify.do?adResource.adPackageId=<s:property value="adPackage.adPackageId"/>&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_DOUBLE_LOWER%>&adPackage.adTemplateId=<s:property value="adPackage.adTemplateId"/>&adType=<s:property value="adType"/>"
									target="dialog" rel="toUpload" title="素材添加" width="700"
									height="400" fresh="true" mask="true"><img id="img2"
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
<script type="text/javascript">
<!--
	//增加是否关闭窗口的判断for上传图片数量限定,2013-10-31,RanChen
	if('<s:property value="closeDialogFlag" />' == 'true'){
		closeResourceDialog();
	}
	
	$(document)
			.ready(
					function() {
						$("#adResourceDoubleModify .updPicUpper img")
								.each(
										function(i) {
											//设置修改和删除按钮的显示：上屏
											var divObjUpper = $('#operaDoubleModifyUpper'
													+ $(this).attr("idNo"));
											divObjUpper.css({
												position : "absolute",
												left : $(this).position().left,
												top : $(this).position().top
											});
											$(this).parent()
													.append(divObjUpper);

											//设置播放时长和播放次数显示：上屏
											var divPamObjUpper = $('#operaParamDoubleModifyUpper'
													+ $(this).attr("idNo"));
											divPamObjUpper
													.css({
														position : "absolute",
														left : $(this)
																.position().left,
														top : $(this)
																.position().top + 101
													});
											$(this).parent().append(
													divPamObjUpper);
											
											//播放button显示
											var divPlayButtonObj = $('#playButtonParamUpperModify'
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

						$("#adResourceDoubleModify .updPicLower img")
								.each(
										function(i) {
											//设置修改和删除按钮的显示：下屏
											var divObjLower = $('#operaDoubleModifyLower'
													+ $(this).attr("idNo"));
											divObjLower.css({
												position : "absolute",
												left : $(this).position().left,
												top : $(this).position().top
											});
											$(this).parent()
													.append(divObjLower);

											//设置播放时长和播放次数显示：下屏
											var divPamObjLower = $('#operaParamDoubleModifyLower'
													+ $(this).attr("idNo"));
											divPamObjLower
													.css({
														position : "absolute",
														left : $(this)
																.position().left,
														top : $(this)
																.position().top + 101
													});
											$(this).parent().append(
													divPamObjLower);

											//播放button显示
											var divPlayButtonObj = $('#playButtonParamLowerModify'
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

						$("#adResourceDoubleModify a").each(function() {
							$(this).attr("hidefocus", true);
						});
						
						//选中的资源图片高亮显示
						$("#adResourceDoubleModify .updPicUpper,.updPicLower").click(function(){
							$("#adResourceDoubleModify .updPicUpper").each(
									function(i) {
										$(this).find(".resourceImgDouble").removeClass(
												"resourceImgSelected");
									});
							
							$("#adResourceDoubleModify .updPicLower").each(
									function(i) {
										$(this).find(".resourceImgDouble").removeClass(
												"resourceImgSelected");
									});
							$(this).find(".resourceImgDouble").addClass("resourceImgSelected");
						});
						
						//点击新增按钮，列表中高亮显示取消
						$(".add").click(function(){
							$("#adResourceDoubleModify .updPicUpper").each(
									function(i) {
										$(this).find(".resourceImgDouble").removeClass(
												"resourceImgSelected");
									});
							
							$("#adResourceDoubleModify .updPicLower").each(
									function(i) {
										$(this).find(".resourceImgDouble").removeClass(
												"resourceImgSelected");
									});
						});
						
						//设置页面title
						window.document.title='<%=Constant.PROJECT_TITLE%>';
					});
//-->

	/**点击修改按钮，高亮显示*/
	function updBtnShow(obj){
		$("#adResourceDoubleModify .updPicUpper").each(
				function(i) {
					$(this).find(".resourceImgDouble").removeClass(
							"resourceImgSelected");
				});
		
		$("#adResourceDoubleModify .updPicLower").each(
				function(i) {
					$(this).find(".resourceImgDouble").removeClass(
							"resourceImgSelected");
				});
		$(obj).parents(".updPicUpper").find(".resourceImgDouble").addClass("resourceImgSelected");
		$(obj).parents(".updPicLower").find(".resourceImgDouble").addClass("resourceImgSelected");
	}
	
</script>