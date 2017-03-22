<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" rel="stylesheet"
	href="js/uploadify-v2.1.4/uploadify.css"></link>
<%@ page import="com.archermind.etb.util.Constant"%>
<table style="width: 100%" id="adResourceUrgencyModify">
	<tr valign="top">
		<td><div class="titleBar">
				<div class="titleTd">
					<b>广告包素材修改</b><font color="red" size="10">（图片素材最多可上传<%=Constant.LIMIT_PIC_NUM%>张）
					</font>
				</div>
			</div></td>
	</tr>
	<tr>
		<td style="padding-top: 0px;">
			<div class="navUrgency">
				<ul>
					<s:iterator value="adResourceList" id="adResource" status="st">
						<li>
							<div class="picDivUrgency" align="center">
								<s:if test='#adResource.adResourceTypes=="1"'>
									<div class="deleteImgDivUrgencyUpd"
										id="operaUrgencyModify<s:property value="#st.index+1" />">
										<div class="btnDivUrgencyUpd"
											onmouseover="divOnmouseover('<s:property value="#st.index+1" />')"
											onmouseout="divOnmouseout('<s:property value="#st.index+1" />')">
											<a title="素材修改" rel="toAdResourceModifyUrgency"
												target="dialog" width="700" height="400" mask="true"
												onclick="updBtnShow(this);"
												href='ad/adPackage!toUrgencyAdResourceModify.do?adResource.adResourceId=<s:property value="#adResource.adResourceId"/>&adResource.adPackageId=<s:property value='adPackage.adPackageId' />&adType=<s:property value="adType"/>&adPackage.adTemplateId=<s:property value="adPackage.adTemplateId"/>'>
												<img class="deleteImg" src="themes/default/images/upd.png" />&nbsp;修改
											</a> <a target="ajaxTodo" title="确定删除该图片?"
												callback="navTabAjaxDoneWithInFailUpd"
												href="ad/adPackage!deleteAdResource.do?adResourceId=<s:property value='#adResource.adResourceId' />&adResource.adPackageId=<s:property value='adPackage.adPackageId' />">
												<img class="deleteImg" src="themes/default/images/del.png" />&nbsp;删除
											</a>
										</div>
									</div>
									<div class="updPicUrgency"
										style="position: relative; z-index: 1;"
										onmouseover="showDeleteDiv('<s:property value="#st.index+1" />','operaUrgencyModify');"
										onmouseout="imgOnmouseout(),hideDeleteDiv('<s:property value="#st.index+1" />','operaUrgencyModify');">
										<a srcImg="<s:property value="adResourcePathDsp"/>"
											resourcePro='<s:property value="adResourceProduct" />'
											resourceAct='<s:property value="adResourceAction"/>'
											resourceTips='<s:property value="adResourceTips"/>'
											onclick="openDiv(this,'<%=Constant.AD_PACKAGE_BATCH_U%>');">
											<img src="<s:property value='adResourcePathDsp'/>"
											idNo="<s:property value="#st.index+1" />" alt="查看原图"
											class="resourceImgUrgency">
										</a>
									</div>

									<div class="opreaDivUrgencyUpdSetting"
										id="operaParamUrgencyModify<s:property value="#st.index+1" />">
										<font>播放时长：<s:property value="adResourceTimes" /> 秒
										</font>
									</div>
								</s:if>
							</div>
						</li>
					</s:iterator>

					<li><div class="picDivUrgency" align="center">
							<div class="deleteImgDivUrgencyUpd">
								<div class="btnDivUrgencyUpd"></div>
							</div>
							<div style="position: relative; z-index: 1;">
								<a class="add"
									href="ad/adPackage!toUrgencyAdResourceModify.do?adResource.adPackageId=<s:property value="adPackage.adPackageId"/>&areaFlag=<%=Constant.AD_RESOURCE_POSITION_SIGN_SINGLE%>&adPackage.adTemplateId=<s:property value="adPackage.adTemplateId"/>&adType=<s:property value="adType"/>"
									target="dialog" rel="toUpload" title="素材添加" width="700"
									height="400" fresh="true" mask="true"><img id="img1"
									src="images/addCommon.gif" class="resourceImgUrgency" /> </a>
							</div>
						</div></li>
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

	$(document).ready(
			function() {

				$("#adResourceUrgencyModify a").each(function() {
					$(this).attr("hidefocus", true);
				});

				$("#adResourceUrgencyModify .updPicUrgency img").each(
						function(i) {
							//设置修改和删除按钮的显示
							var divObj = $('#operaUrgencyModify'
									+ $(this).attr("idNo"));
							divObj.css({
								position : "absolute",
								left : $(this).position().left,
								top : $(this).position().top
							});
							$(this).parent().append(divObj);

							//设置播放时长和播放次数显示
							var divPamObj = $('#operaParamUrgencyModify'
									+ $(this).attr("idNo"));
							divPamObj.css({
								position : "absolute",
								left : $(this).position().left,
								top : $(this).position().top + 161
							});
							$(this).parent().append(divPamObj);
						});
				
				//选中的资源图片高亮显示
				$("#adResourceUrgencyModify .updPicUrgency").click(function(){
					$("#adResourceUrgencyModify .updPicUrgency").each(
							function(i) {
								$(this).find(".resourceImgUrgency").removeClass(
										"resourceImgSelected");
							});
					$(this).find(".resourceImgUrgency").addClass("resourceImgSelected");
				});
				
				//点击新增按钮，列表中高亮显示取消
				$(".add").click(function(){
					$("#adResourceUrgencyModify .updPicUrgency").each(
							function(i) {
								$(this).find(".resourceImgUrgency").removeClass(
										"resourceImgSelected");
							});
				});
				
				//设置页面title
				window.document.title='<%=Constant.PROJECT_TITLE%>';
			});

	/**点击修改按钮，高亮显示*/
	function updBtnShow(obj){
		$("#adResourceUrgencyModify .updPicUrgency").each(
				function(i) {
					$(this).find(".resourceImgUrgency").removeClass(
							"resourceImgSelected");
				});
		$(obj).parents(".updPicUrgency").find(".resourceImgUrgency").addClass("resourceImgSelected");
	}
//-->
</script>
