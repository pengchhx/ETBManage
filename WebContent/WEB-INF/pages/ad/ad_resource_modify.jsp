<%@page import="com.archermind.etb.util.Constant"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link type="text/css" rel="stylesheet"
	href="js/uploadify-v2.1.4/uploadify.css"></link>
<%
	String path = request.getContextPath();
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
	var adResourceModifyForm = $("#adResourceModifyForm");
	//进度条
	var ajaxbg = $("#background,#progressBar");
	var fileDesc = "jpg,bmp,png,JPG,BMP,PNG";//素材格式
	var fileExt = "*.jpg;*.bmp;*.png;*.JPG;*.BMP;*.PNG";//素材类型
	
	//定义变量判断是否要显示进度bar for 上传时进度bar 显示问题,true显示,false隐藏,2013-11-08,RanChen
	var showProcessBarFlag = false;
	
	$(function() {
		
		//若不能修改，则进入画面提示错误信息
		if ($("#errMsg").val() != "") {
			navTab
					.reload('ad/adPackage!toAdModify.do?adPackage.adPackageId=<s:property value="adResource.adPackageId"/>');
		}

		//修改时默认为之前上传的名字
		var fileuploadFileName = '<s:property value="fileuploadFileName"/>';
		if (fileuploadFileName != "") {
			$("#fileuploadFileName").val(fileuploadFileName);
			$("#text_webApplogo").val(fileuploadFileName);
		}else{
			$("#fileuploadFileName").val('');
		}

		//默认为图片，设置播放时长样式
		if ('<s:property value="adResource.adResourceTypes"/>' == 2) {//视频
			$("#adResourceTimes").val('');
			imgHide();
		} else {
			$("#adResourceCount").val('');
			vedioHide();
		}

		//初始化时，若为新增，则判断图片素材的数量，若大于最大数量，则只能选择视频for上传图片数量限定,2013-10-31,RanChen,start
		if($("#adResourceId").val() == 0 && parseInt($("#adResourceModifyForm #picNum").val())>='<%=Constant.LIMIT_PIC_NUM%>'){
			$("#adResourceTypes").val(2);
			$("#adResourceTypes").attr("disabled",true);
			$("#adResourceTimes").val('');
			imgHide();
			alertMsg.warn("图片素材已达最大数量<%=Constant.LIMIT_PIC_NUM%>,只能上传视频素材");
		}
		//初始化时，若为新增，则判断图片素材的数量，若大于最大数量，则只能选择视频for上传图片数量限定,2013-10-31,RanChen,end
		//初始化时，若为新增，则判断图片素材的数量，若大于最大数量，则只能选择视频for上传图片数量限定,2013-10-31,RanChen,start
		else if($("#adResourceId").val() != 0 && '<s:property value="adResource.adResourceTypes"/>' == 2 && parseInt($("#adResourceModifyForm #picNum").val())>='<%=Constant.LIMIT_PIC_NUM%>'){
			$("#adResourceTypes").attr("disabled",true);
			alertMsg.warn("图片素材已达最大数量<%=Constant.LIMIT_PIC_NUM%>,只能上传视频素材");
		}
		//初始化时，若为新增，则判断图片素材的数量，若大于最大数量，则只能选择视频for上传图片数量限定,2013-10-31,RanChen,end
		
		//判断-若修改且素材状态为已删除则不可修改,2013-10-31,RanChen,start
		if ('<s:property value="adResource.dataStat"/>' == '<%=Constant.DATA_STAT_OFF%>') {
				alertMsg.error("<%=Constant.MATERIAL_DELETED_MESSAGE%>");
				window.parent.loadResourceList(true);
		} 
		//判断-若修改且素材状态为已删除则不可修改,2013-10-31,RanChen,end
														
														
	$('#fileupload').uploadify({
			'uploader' : 'js/uploadify-v2.1.4/uploadify.swf',
			'script' : 'ad/adPackage!modifyAdResource.do',
			'cancelImg' : 'js/uploadify-v2.1.4/cancel.png',
			'queueID' : 'fileQueue',
			'method' : 'GET',
			'scriptData' : {
				'adPackageId' : '',
				'adResourceTypes' : '',
				'adResourceAction' : '',
				'adResourceTimes' : '',
				'adResourceTipsEN' : '',
				'adResourceId' : '',
				'adResourcePath' : '',
				'adResourceThumbnailPath' : '',
				'dataStat' : '',
				'adTemplateId' : '',
				'adType' : ''
			},
			'fileDataName' : 'fileupload', //和以下input的name属性一致
			'auto' : false,
			'multi' : false,
			'buttonText' : 'Browser',
			'buttonImg' : 'js/uploadify-v2.1.4/browser.JPG',
			'width' : '60',
			'fileDesc' : fileDesc,
			'fileExt' : fileExt,
			'sizeLimit' : 86400000000,
			'simUploadLimit' : 10,
			'onSelect' : function(e, queueId, fileObj) {
				$("#text_webApplogo").val(fileObj.name);
				$("#fileuploadFileName").val(fileObj.name);
			},
			'onComplete' : function(event, queueID, fileObj, response, data) {
				
				var result = eval('(' + response + ')');
				if (result.flag) {
					//视频上传后延迟3s,以免缩略图还未加载完成
					if($("#adResourceTypes").val()=='<%=Constant.AD_RESOURCE_VEDIO%>'){
						setTimeout(function(){
							saveSuccessCallBack(fileObj.name);
						},3000); //设置一个超时对象
					}else{
						saveSuccessCallBack(fileObj.name);
					}
					
				} else {
					ajaxbg.hide();
					alertMsg.error("文件:" + fileObj.name + " 上传失败!");
					$("#text_webApplogo").val('');
					$("#fileuploadFileName").val('');
				}
				// navTab
				// 		.reload('ad/adPackage!toAdModifyResourceList.do?adPackage.adPackageId=<s:property value="adResource.adPackageId"/>');

				//$.pdialog.closeCurrent();
			},
			'onError' : function(event, queueID, fileObj, errorObj) {
				ajaxbg.hide();
				alertMsg.error("文件:" + fileObj.name + " 上传失败!");

			},
			'onCancel' : function(event, queueID, fileObj) {
			}

		});

		//覆盖document的ajaxStop方法for 上传时进度bar 显示问题,2013-11-08,RanChen
	    $("#adResourceModifyForm").ajaxStop(function(){
	    	ajaxbg.hide();
	    	if(showProcessBarFlag){
	    		ajaxbg.show();
	    	}
	    	
	    });
		
		$("#saveBtn")
				.click(
						function() {
							
							var $form = $("#adResourceModifyForm");
							if (!$form.valid()) {
								return false;

							} else {
								alertMsg.confirm("确定保存吗?", {
									okCall : function() {
										
										//添加判断,已审核或已修改不可修改
										$
												.ajax({
													type : "post",
													cache : false,
													//url : "ad/adPackage!getAdpackageStatusById.do",
													url : "ad/adPackage!getAdpackageStatusAndPicNumById.do",
													data : "adPackage.adPackageId=<s:property value="adResource.adPackageId"/>&adResource.adResourceId="+$("#adResourceId").val(),
													datatype : "json",
													success : function(data) {
														//判断是否可以修改
														if (!checkEditEnable(data.status)) {
															navTab
																	.reload('ad/adPackage!toAdModify.do?adPackage.adPackageId=<s:property value="adResource.adPackageId"/>');
															//修改showProcessBarFlag for 上传进度bar 显示问题,2013-11-08,RanChen
															showProcessBarFlag = true;
														}
														//判断-若素材状态为已删除则不可修改,2013-10-31,RanChen,start
														else if (data.dataStat == '<%=Constant.DATA_STAT_OFF%>') {
															//修改showProcessBarFlag for 上传进度bar 显示问题,2013-11-08,RanChen
															showProcessBarFlag = false;
															alertMsg.error("<%=Constant.MATERIAL_DELETED_MESSAGE%>");
															window.parent.loadResourceList(true);
														} 
														//判断-若素材状态为已删除则不可修改,2013-10-31,RanChen,end
														//判断-若原素材类型!=图片&& 图片素材>=最大数量 && 选择的是图片素材，则不允许修改for上传图片数量限定,2013-10-31,RanChen,start
														else if($("#adResourceTypes").val() == 1 && data.resourceTypeOld != '<%=Constant.AD_RESOURCE_IMG%>' && data.picNum >= '<%=Constant.LIMIT_PIC_NUM%>'){
															alertMsg.warn("图片素材已达最大数量<%=Constant.LIMIT_PIC_NUM%>,只能上传视频素材");
															$("#adResourceTypes").val(2);
															$("#adResourceTypes").attr("disabled",true);
															$("#adResourceTimes").val('');
															imgHide();
															//修改showProcessBarFlag for 上传进度bar 显示问题,2013-11-08,RanChen
															showProcessBarFlag = false;
															$("#text_webApplogo").trigger("blur");
															$('#fileupload').uploadifySettings('fileDesc', fileDesc);
															$('#fileupload').uploadifySettings('fileExt', fileExt);
															
															window.parent.loadResourceList(false);
															
														} 
														//判断-若原素材类型!=图片 && 图片素材>=最大数量 && 选择的是图片素材，则不允许修改for上传图片数量限定,2013-10-31,RanChen,end
														else {
															//修改showProcessBarFlag for 上传进度bar 显示问题,2013-11-08,RanChen
															showProcessBarFlag = true;
															save();
														}
													}
												});
									}
								});
							}
							
						});
		//取消
		$("#btnCancelCommonModify").click(function(){
			$.pdialog.closeCurrent();
			//设置页面title
			sysTitleSetting('<%=Constant.PROJECT_TITLE%>');
		});
		
		$("a.close").click(function(){
			//设置页面title
			sysTitleSetting('<%=Constant.PROJECT_TITLE%>');
		});
	});

	//图片上传成功事件
	function saveSuccessCallBack(name){
		alertMsg.correct("文件:" + name + " 上传成功!");
		ajaxbg.hide();
		resourceModifyCallBack();
	}
	//保存操作
	function save(){
		//大文件上传，背景置灰
		ajaxbg.show();

		//视频，清空播放时长
		if ($("#adResourceTypes").val() == 2) {
			$("#adResourceTimes").val('');
			$("#adResourceAction").val('');
		} else {
			//图片，清空播放次数
			$("#adResourceCount").val('');
		}
		//若没有修改素材，则直接提交
		var adResourcePath = $("#adResourcePath").val().trim();
		if (adResourcePath != ""
				&& adResourcePath == $("#text_webApplogo").val()
						.trim()) {
			adResourceModifyForm.submit();
		} else {
			uploadFile();
		}
	}

	function clearResult() {
		$("#result").html("");
	}

	function uploadFile() {
		$('#fileupload')
				.uploadifySettings(
						'scriptData',
						{
							'adPackageId' : '<s:property value="adResource.adPackageId"/>',
							'adResourceTypes' : $("#adResourceTypes").val(),
							'adResourceAction' : paramsDeal($(
									"#adResourceAction").val()),
							'adResourceTimes' : $("#adResourceTimes").val(),
							'adResourceTips' : paramsDeal($("#adResourceTips")
									.val()),
							'adResourceIntent' : paramsDeal($(
									"#adResourceIntent").val()),
							'adResourceProduct' : paramsDeal($(
									"#adResourceProduct").val()),
							'adResourceIntime' : $("#adResourceIntime").val(),
							'adResourceOuttime' : $("#adResourceOuttime").val(),
							'adResourceCount' : $("#adResourceCount").val(),
							'adResourceId' : '<s:property value="adResource.adResourceId"/>',
							'adResourcePath' : paramsDeal($("#adResourcePath")
									.val()),
							'adResourceThumbnailPath' : paramsDeal($(
									"#adResourceThumbnailPath").val()),
							'adResourcePositionSign' : $(
									"#adResourcePositionSign").val(),
							'dataStat' : $("#dataStat").val(),
							'adTemplateId' : '<s:property	value="adPackage.adTemplateId" />',
							'adType' : '<s:property value="adType" />'
						}); //传递参数
		jQuery('#fileupload').uploadifyUpload();
	}

	function resourceSetting(obj) {
		//新增清空上传路径和备注
		if ('<s:property value="adResource.adResourceId"/>' == 0) {
			$("#text_webApplogo").val('');
		}

		if (obj.value == 1) {//图片

			vedioHide();

		} else {

			imgHide();

		}

		$("#text_webApplogo").trigger("blur");

		$('#fileupload').uploadifySettings('fileDesc', fileDesc);
		$('#fileupload').uploadifySettings('fileExt', fileExt);

	}

	//选择视频
	function imgHide() {
		$("#imgDiv").hide();
		$("#vedioDiv").show();
		$("#imgFormat").hide();
		$("#vedioFormat").show();
		$("#adResourceCount").addClass("positiveInt");
		$("#adResourceTimes").removeClass("required positiveInt");
		$("#adResourceCount").trigger("blur");
		//视频上传格式
		fileDesc = "3gp,mp4,3GP,MP4";
		fileExt = "*.mp4;*.3gp;*.MP4;*.3GP;";

		var fileNameUpper = $("#fileuploadFileName").val().trim().toUpperCase();
		//非视频，则清空文件上传信息
		if (fileNameUpper.indexOf(".MP4") == -1
				&& fileNameUpper.indexOf(".3GP") == -1) {
			$("#text_webApplogo").val("");
		} else {
			$("#text_webApplogo").val($("#fileuploadFileName").val().trim());
		}

	}

	//选择图片
	function vedioHide() {
		$("#imgDiv").show();
		$("#vedioDiv").hide();
		$("#imgFormat").show();
		$("#vedioFormat").hide();
		$("#adResourceCount").removeClass("positiveInt");
		$("#adResourceTimes").addClass("required positiveInt");
		$("#adResourceTimes").trigger("blur");

		//图片上传格式
		fileDesc = "jpg,bmp,png,JPG,BMP,PNG";
		fileExt = "*.jpg;*.bmp;*.png;*.JPG;*.BMP;*.PNG";

		var fileNameUpper = $("#fileuploadFileName").val().trim().toUpperCase();
		//非图片，则清空文件上传信息
		if (fileNameUpper.indexOf(".JPG") == -1
				&& fileNameUpper.indexOf(".BMP") == -1
				&& fileNameUpper.indexOf(".PNG") == -1) {
			$("#text_webApplogo").val("");
		} else {
			$("#text_webApplogo").val($("#fileuploadFileName").val().trim());
		}
	}
	
	//设置页面title
	window.document.title='<%=Constant.PROJECT_TITLE%>';
	
</script>
<div class="pageContent">
	<s:form id="adResourceModifyForm" theme="simple" method="post"
		action="ad/adPackage!modifyAdResourceInfo.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this, resourceModifyCallBack)"
		enctype="multipart/form-data">
		<s:hidden name="fileuploadFileName" id="fileuploadFileName"></s:hidden>
		<s:hidden name="adResource.adPackageId" id="adPackageId"></s:hidden>
		<s:hidden name="adResource.adResourceId" id="adResourceId" />
		<s:hidden name="adResource.adResourcePath" id="adResourcePath" />
		<s:hidden name="adResource.adResourceThumbnailPath" id="adResourceThumbnailPath" />
		<s:hidden name="adResource.dataStat" id="dataStat" />
		<s:hidden name="adResource.adResourcePositionSign"
			id="adResourcePositionSign" />
		<s:hidden name="errMsg" id="errMsg"></s:hidden>
		<s:hidden name="picNum" id="picNum"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>类型：</label>
				<s:select list="#{1:'图片',2:'视频'}" listKey="key" listValue="value"
					name="adResource.adResourceTypes" id="adResourceTypes"
					cssClass="required" onchange="resourceSetting(this);"></s:select>
			</p>
			<p>
				<label>广告商：</label>
				<s:textfield id="adResourceProduct" onchange="fieldTrim(this)"
					name="adResource.adResourceProduct" maxlength="40"></s:textfield>
			</p>
			<div id="imgDiv">
				<p>
					<label>资源链接：</label>
					<s:textfield id="adResourceAction" onchange="fieldTrim(this)"
						name="adResource.adResourceAction" maxlength="100"></s:textfield>
				</p>
				<%-- <p>
				<label>资源意图：</label>
				<s:textfield id="adResourceIntent"
					name="adResource.adResourceIntent" cssClass="required"
					maxlength="100"></s:textfield>
			</p> --%>
				<p>
					<label>播放时长：</label>
					<s:if test="adResource.adResourceId==0">
						<s:textfield id="adResourceTimes" onchange="fieldTrim(this)"
							name="adResource.adResourceTimes" maxlength="6" value=""></s:textfield>
					</s:if>
					<s:else>
						<s:textfield id="adResourceTimes" onchange="fieldTrim(this)"
							name="adResource.adResourceTimes" maxlength="6"></s:textfield>
					</s:else>
					&nbsp;秒
				</p>
			</div>
			<div id="vedioDiv" style="display: none;">
				<%--<p>
					<label>资源链接：</label>
					<s:textfield id="adResourceAction"
						name="adResource.adResourceAction"
						maxlength="200"></s:textfield>
				</p>
				 <p>
				<label>资源意图：</label>
				<s:textfield id="adResourceIntent"
					name="adResource.adResourceIntent" cssClass="required"
					maxlength="100"></s:textfield>
			</p> --%>
				<%-- <p>
				<label>开播时段：</label>
				<s:textfield id="adResourceIntime"
					name="adResource.adResourceIntime" cssClass="required"
					maxlength="100"></s:textfield>
			</p>
			<p>
				<label>禁播时段：</label>
				<s:textfield id="adResourceOuttime"
					name="adResource.adResourceOuttime" cssClass="required"
					maxlength="100"></s:textfield>
			</p> --%>
				<p>
					<label>播放次数：</label>
					<s:if test="adResource.adResourceId==0">
						<s:textfield id="adResourceCount" onchange="fieldTrim(this)"
							name="adResource.adResourceCount" maxlength="6" value=""></s:textfield>
					</s:if>
					<s:else>
						<s:textfield id="adResourceCount" onchange="fieldTrim(this)"
							name="adResource.adResourceCount" maxlength="6"></s:textfield>
					</s:else>

				</p>
			</div>
			<p style="height: 40px;">
				<label>路径：</label><input type="text" readonly="readonly"
					id="text_webApplogo" name="app_logo" class="required" value="" />&nbsp;
				<span><input id="fileupload" type="file" name="fileupload" /></span>
				<span id="imgFormat" class="info" style="left: 130px; top: 30px;">（格式：jpg，bmp，png；分辨率：
					<!-- 单屏 --> <s:if test="adResource.adResourcePositionSign==0"><%=Constant.AD_RATIO_COMMON_SINGLE%></s:if>
					<!-- 双屏上屏 -->
					<s:elseif test="adResource.adResourcePositionSign==1"><%=Constant.AD_RATIO_COMMON_DOUBLE_UPPER%></s:elseif>
					<!-- 双屏下屏 -->
					<s:elseif test="adResource.adResourcePositionSign==2"><%=Constant.AD_RATIO_COMMON_DOUBLE_LOWER%></s:elseif>）
				</span> <span id="vedioFormat" class="info"
					style="display: none; left: 130px; top: 30px;">（请选择无声视频，格式：3gp，mp4；分辨率：
					<!-- 单屏 --> <s:if test="adResource.adResourcePositionSign==0"><%=Constant.AD_RATIO_COMMON_SINGLE%></s:if>
					<!-- 双屏上屏 -->
					<s:elseif test="adResource.adResourcePositionSign==1"><%=Constant.AD_RATIO_COMMON_DOUBLE_UPPER%></s:elseif>
					<!-- 双屏下屏 -->
					<s:elseif test="adResource.adResourcePositionSign==2"><%=Constant.AD_RATIO_COMMON_DOUBLE_LOWER%></s:elseif>）
				</span>
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
				<li><a class="button" id="saveBtn" title="保存"><span>保存</span></a></li>
				<li><a class="button" id="btnCancelCommonModify" title="取消"><span>取消</span></a></li>
			</ul>
		</div>
	</s:form>
</div>
