<%@page import="com.archermind.etb.util.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" rel="stylesheet"
	href="js/uploadify-v2.1.4/uploadify.css"></link>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="js/uploadify-v2.1.4/swfobject.js"></script>
<script type="text/javascript"
	src="js/uploadify-v2.1.4/jquery.uploadify.v2.1.4.js"></script>
<style>
<!--
span.error { /* display:block; */
	overflow: hidden;
	display: block;
	width: 165px;
	height: 21px;
	line-height: 21px;
	margin-left: 72px;
	padding: 0 3px;
	background: #F00;
	color: #FFF;
	z-index: 99;
	position: absolute;
	top: 5px;
	left: 322px;
}
-->
</style>
<script type="text/javascript">
	//进度条
	var ajaxbg = $("#background,#progressBar");
	
	//定义变量判断是否要显示进度bar for 上传时进度bar 显示问题,true显示,false隐藏,2013-11-08,RanChen
	var showProcessBarFlag = false;
	
	$(function() {

		//若不能修改，则进入画面提示错误信息
		if ('<s:property value="errMsg"/>' != "") {
			reloadAppTab();
			return;
		}

		//初始化时，判断图片素材的数量，若大于最大数量，则不能继续上传for上传图片数量限定,2013-10-31,RanChen,start
		if(parseInt($("#picNum").val())>='<%=Constant.LIMIT_PIC_NUM%>'){
			alertMsg.error("图片素材已达最大数量<%=Constant.LIMIT_PIC_NUM%>，不可再添加");
			reloadAppTab();
		}
		//初始化时，判断图片素材的数量，若大于最大数量，则不能继续上传for上传图片数量限定,2013-10-31,RanChen,end
		
		$('#fileupload').uploadify({
			'uploader' : 'js/uploadify-v2.1.4/uploadify.swf',
			'script' : 'ad/adPackage!uploadAdPicCommon.do',
			'cancelImg' : 'js/uploadify-v2.1.4/cancel.png',
			'queueID' : 'fileQueue',
			'method' : 'GET',
			'scriptData' : {
				'adPackageId' : '',
				'adResourceTypes' : '',
				'adResourceAction' : '',
				'adResourceTimes' : '',
				'adResourceTips' : '',
				'adStyle' : ''
			},
			'fileDataName' : 'fileupload', //和以下input的name属性一致
			'auto' : false,
			'multi' : false,
			'buttonText' : 'Browser',
			'buttonImg' : 'js/uploadify-v2.1.4/browser.JPG',
			'width' : '60',
			'fileDesc' : 'jpg,bmp,png,JPG,BMP,PNG',
			'fileExt' : '*.jpg;*.bmp;*.png;*.JPG;*.BMP;*.PNG;',
			'sizeLimit' : 86400000000,
			'simUploadLimit' : 10,
			'onSelect' : function(e, queueId, fileObj) {
				$("#text_webApplogo").val(fileObj.name);
				$("#fileuploadFileName").val(fileObj.name);
			},
			'onComplete' : function(event, queueID, fileObj, response, data) {
				ajaxbg.hide();
				var result = eval('(' + response + ')');
				if (result.flag) {
					alertMsg.correct("文件:" + fileObj.name + " 上传成功!");
					reloadAppTab();
				} else {
					alertMsg.error("文件:" + fileObj.name + " 上传失败!");
					$("#text_webApplogo").val('');
					$("#fileuploadFileName").val('');
				}
				//$.pdialog.close("toUpload");
			},
			'onError' : function(event, queueID, fileObj) {
				ajaxbg.hide();
				alertMsg.error("文件:" + fileObj.name + " 上传失败!");
			},
			'onCancel' : function(event, queueID, fileObj) {
				//alert("取消 " + fileObj.name);
			}
		});

		//覆盖document的ajaxStop方法for 上传时进度bar 显示问题,2013-11-08,RanChen
	    $("#appImgUpload").ajaxStop(function(){
	    	ajaxbg.hide();
	    	if(showProcessBarFlag){
	    		ajaxbg.show();
	    	}
	    	
	    });
		
		$("#saveAppBtn")
				.click(
						function() {

							var $form = $("#appImgUpload");
							if (!$form.valid()) {
								return false;
							}

							alertMsg.confirm("确定保存吗?", {
								okCall : function() {
									//添加判断,已审核或已修改不可修改
									$
											.ajax({
												type : "post",
												cache : false,
												url : "ad/adPackage!getAdpackageStatusAndPicNumById.do",
												data : "adPackage.adPackageId=<s:property value="adResource.adPackageId"/>",
												datatype : "json",
												success : function(data) {

													//判断是否可以修改
													if (!checkEditEnable(data.status)) {
														$("#adPackageStatusAddUploadApp").val(data.status);
														reloadAppTab();
														//修改showProcessBarFlag for 上传进度bar 显示问题,2013-11-08,RanChen
														showProcessBarFlag = true;
													} 
													//判断-若新增&&图片素材>最大数量，则不能继续上传for上传图片数量限定,2013-10-31,RanChen,start
													else if(data.picNum >= '<%=Constant.LIMIT_PIC_NUM%>'){
														alertMsg.error("图片素材已达最大数量<%=Constant.LIMIT_PIC_NUM%>，不可再添加");
														reloadAppTab();
														//修改showProcessBarFlag for 上传进度bar 显示问题,2013-11-08,RanChen
														showProcessBarFlag = false;
													} 
													//判断-若新增&&图片素材>最大数量，则不能继续上传for上传图片数量限定,2013-10-31,RanChen,end
													else {
														//修改showProcessBarFlag for 上传进度bar 显示问题,2013-11-08,RanChen
														showProcessBarFlag = true;
														save();
													}
												}
											});
								}
							});
							
							
						});
		//取消
		$("#btnCancelAppAdd").click(function(){
			$.pdialog.closeCurrent();
			//设置页面title
			sysTitleSetting('<%=Constant.PROJECT_TITLE%>');
		});

		$("a.close").click(function(){
			//设置页面title
			sysTitleSetting('<%=Constant.PROJECT_TITLE%>');
		});
	});

	function reloadAppTab() {
		navTab
				.reload(
						"ad/adPackage!toUploadPicAppList.do?adPackage.adPackageId=<s:property value="adResource.adPackageId"/>&adStyle=<s:property value="adStyle"/>&adPackage.adPackageStatus="+$("#adPackageStatusAddUploadApp").val(),
						{
							navTabId : "toAddApp"
						});
		navTab.switchTab("toAddApp");
	}

	function save() {
		//进度条
		ajaxbg.show();
		uploadFile();
	}

	function clearResult() {
		$("#result").html("");
	}

	function uploadFile() {

		$('#fileupload').uploadifySettings('scriptData', {
			'adPackageId' : '<s:property value="adResource.adPackageId"/>',
			'adResourceTypes' : $("#adResourceTypes").val(),
			'adResourceAction' : '',//应用广告不能添加资源链接,故改为空,2013-11-04,RanChen
			'adResourceTimes' : $("#adResourceTimes").val(),
			'adResourceTips' : paramsDeal($("#adResourceTips").val()),
			'adResourceIntent' : paramsDeal($("#adResourceIntent").val()),
			'adResourceProduct' : paramsDeal($("#adResourceProduct").val()),
			'adResourceIntime' : $("#adResourceIntime").val(),
			'adResourceOuttime' : $("#adResourceOuttime").val(),
			'adResourceCount' : $("#adResourceCount").val(),
			'adStyle' : '<s:property value="adStyle"/>'
		}); //传递参数

		jQuery('#fileupload').uploadifyUpload();
	}

	function resourceSetting(obj) {
		//清空上传路径和备注
		$("#text_webApplogo").val('');
		$("#adResourceTips").val('');
		$("#adResourceProduct").val('');

		if (obj.value == 1) {
			$("#imgDiv").show();
			$("#vedioDiv").hide();
			$("#imgFormat").show();
			$("#vedioFormat").hide();
			$("#adResourceCount").removeClass("digits");
			$("#adResourceTimes").addClass("required digits");
		} else {
			$("#imgDiv").hide();
			$("#vedioDiv").show();
			$("#imgFormat").hide();
			$("#vedioFormat").show();
			$("#adResourceCount").addClass("digits");
			$("#adResourceTimes").removeClass("required digits");
		}
	}
	
	//设置页面title
	window.document.title='<%=Constant.PROJECT_TITLE%>';
</script>
<div class="pageContent">
	<s:form id="appImgUpload" theme="simple" method="post"
		action="ad/adPackage!uploadAdPicCommon.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);"
		enctype="multipart/form-data">
		<s:hidden name="fileuploadFileName" id="fileuploadFileName"></s:hidden>
		<s:hidden name="adResource.adPackageId" id="adPackageId"></s:hidden>
		<s:hidden name="adStyle"></s:hidden>
		<s:hidden name="errMsg"></s:hidden>
		<s:hidden name="picNum" id="picNum"></s:hidden>
		<s:hidden name="adPackage.adPackageStatus" id="adPackageStatusAddUploadApp" />
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>类型：</label>
				<s:select list="#{1:'图片'}" listKey="key" listValue="value"
					name="adResource.adResourceTypes" id="adResourceTypes"
					onchange="resourceSetting(this);" cssClass="required"></s:select>
			</p>
			<p>
				<label>广告商：</label>
				<s:textfield id="adResourceProduct" onchange="fieldTrim(this)"
					name="adResource.adResourceProduct" maxlength="40"></s:textfield>
			</p>
			<!-- 应用广告不能添加资源链接,故去掉应用广告的资源链接,2013-11-04,RanChen -->
			<%-- <p>
				<label>资源链接：</label>
				<s:textfield id="adResourceAction" onchange="fieldTrim(this)"
					name="adResource.adResourceAction" maxlength="100"></s:textfield>
			</p> --%>
			<p>
				<label>播放时长：</label>
				<s:textfield id="adResourceTimes" name="adResource.adResourceTimes"
					onchange="fieldTrim(this)" cssClass="required positiveInt"
					maxlength="6" min="1" value=""></s:textfield>
				&nbsp;秒
			</p>
			<p style="height: 40px;">
				<label>路径：</label><input type="text" readonly="readonly"
					id="text_webApplogo" name="app_logo" class="required" value="" />&nbsp;
				<span><input id="fileupload" type="file" name="fileupload" /></span>
				<span id="imgFormat" class="info" style="left: 130px; top: 30px;">（格式：jpg，bmp，png；分辨率：<%=Constant.AD_RATIO_APP %>）</span>
			</p>
			<div style="float: left; margin: 0; padding: 5px 0;">
				<div style="float: left;">
					<label>备注说明：</label>
				</div>
				<div class="combox">
					<div class="select" id="combox_8599556">
						<s:textarea id="adResourceTips" name="adResource.adResourceTips"
							onchange="fieldTrim(this)" width="100px" maxlength="100" rows="2"
							cols="40"></s:textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><a class="button" id="saveAppBtn" width="520" height="430"
					title="保存"><span>保存</span></a></li>
				<li><a class="button" id="btnCancelAppAdd" width="520" height="430"
					title="取消"><span>取消</span></a></li>
			</ul>
		</div>
	</s:form>
</div>
