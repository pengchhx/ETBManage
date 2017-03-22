<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" rel="stylesheet"
	href="js/uploadify-v2.1.4/uploadify.css"></link>
<%@ page import="com.archermind.etb.util.Constant"%>

<div class="pageContent">

	<s:form id="adViewAppForm" theme="simple" method="post"
		action="ad/adPackage!modify.do" cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<div class="adPackageTable">
				<div class="titleBar">
					<div class="titleTd">
						<b>应用广告包查看</b>
					</div>
				</div>
				<p>
					<label>批次号：</label> <label class="textLabel"> <s:textfield
							name="adPackage.adPackageBatchNo" width="180px" readonly="true"></s:textfield>
					</label>
				</p>
				<p>
					<label>广告包名称：</label> <label class="textLabel"><s:textfield
							name="adPackage.adPackageName" width="180px" readonly="true"></s:textfield></label>
				</p>
				<p>
					<label>开始时间：</label> <label class="textLabel"> <s:textfield
							name="adPackage.adPackageBegintime1" class="date"
							dateFmt="yyyy-MM-dd" readonly="true" />
					</label>
				</p>
				<p>
					<label>截止时间：</label> <label class="textLabel"> <s:textfield
							name="adPackage.adPackageEndtime1" class="date"
							dateFmt="yyyy-MM-dd" readonly="true" />
					</label>
				</p>
				<s:if
					test="adPackage.adPackageCheckSuggest!=null && adPackage.adPackageCheckSuggest!=''">
					<p style="height: 70px;">
						<label>文字通知：</label> <label class="textLabel"><s:textarea
								class="editor" name="adPackage.adPackageTips"
								style="width: 250px;" readonly="true"></s:textarea></label>
					</p>
					<p style="height: 70px;">
						<label>审核意见：</label> <label class="textLabel"><s:textarea
								name="adPackage.adPackageCheckSuggest" readonly="true"
								style="width: 250px;" /></label>
					</p>
				</s:if>
				<s:else>
					<p style="width: 760px; height: 70px;" id="wordMsg">
						<label>文字通知：</label> <label class="textLabel"><s:textarea
								class="editor" name="adPackage.adPackageTips"
								style="width: 630px;" readonly="true"></s:textarea></label>
					</p>
				</s:else>
			</div>
			<table style="width: 98%">
				<tr valign="top">
					<td><div class="titleBar">
							<div class="titleTd">
								<b>广告包素材查看</b>
							</div>
						</div></td>
				</tr>
				<tr>
					<td style="padding-top: 0px;">
						<div class="navApp" id="navAppView">
							<ul>
								<s:iterator value="adResourceList" id="adResource" status="st">
									<li>
										<div class="picDiv" align="center">
											<s:if test='#adResource.adResourceTypes=="1"'>
												<div class="updPic" style="position: relative; z-index: 1;">
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
													id="operaParamView<s:property value="#st.index+1" />">
													<font>播放时长： <s:property value="adResourceTimes" />
														秒
													</font>
												</div>
											</s:if>
										</div>
									</li>
								</s:iterator>
							</ul>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" class="close">&nbsp;返回&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#navAppView .updPic img").each(function(i) {
			//设置播放时长和播放次数显示
			var divPamObj = $('#operaParamView' + $(this).attr("idNo"));
			divPamObj.css({
				position : "absolute",
				left : $(this).position().left,
				top : $(this).position().top + 79
			});
			$(this).parent().append(divPamObj);
		});

		$("#navAppView a").each(function() {
			$(this).attr("hidefocus", true);
		});
		
		//选中的资源图片高亮显示
		$("#navAppView .updPic").click(function(){
			$("#navAppView .updPic").each(
					function(i) {
						$(this).find(".resourceImgApp").removeClass(
								"resourceImgSelected");
					});
			$(this).find(".resourceImgApp").addClass("resourceImgSelected");
		});
	});

</script>
