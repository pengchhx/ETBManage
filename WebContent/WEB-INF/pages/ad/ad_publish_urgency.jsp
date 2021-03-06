<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.archermind.etb.util.Constant"%>
<style>
<!--
#adPublishUrgencyForm td {
	padding-top: 10px;
}

.pageFormContent label.textLabel {
	padding-left: 0px;
	text-align: left;
	width: 250px;
}
-->
</style>

<script type="text/javascript">
	var adPublishUrgencyForm = $("#adPublishUrgencyForm");
	var confirmMsg = "确定要发布吗？";
</script>

<div class="pageContent">

	<s:form id="adPublishUrgencyForm" theme="simple" method="post"
		action="ad/adPackage!publish.do" cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone,confirmMsg);">

		<s:hidden name="adPackage.adPackageId"></s:hidden>
		<s:hidden name="adPackage.adTemplateId"></s:hidden>
		<s:hidden name="adPackage.adPackageName"></s:hidden>
		<s:hidden name="adPackage.adPackagePath"></s:hidden>
		<s:hidden name="adPackage.adPackageCreater"></s:hidden>
		<s:hidden name="adPackage.adPackageCreaterTime"></s:hidden>
		<s:hidden name="adPackage.adPackageStatus"></s:hidden>
		<s:hidden name="adPackage.adPackageChecker"></s:hidden>
		<s:hidden name="adPackage.adPackageCheckTime"></s:hidden>
		<s:hidden name="adPackage.adPackagePublisher"></s:hidden>
		<%-- 		<s:hidden name="adPackage.adPackagePublishTime"></s:hidden> --%>
		<s:hidden name="adPackage.adPackageBegintime"></s:hidden>
		<s:hidden name="adPackage.adPackageEndtime"></s:hidden>
		<%-- 		<s:hidden name="adPackage.adPackageTips"></s:hidden> --%>
		<s:hidden name="adPackage.dataStat"></s:hidden>
		<s:hidden name="adPackage.adPackageBatchNo"></s:hidden>
		<s:hidden name="adPackage.adPackageCheckSuggest"></s:hidden>

		<div class="pageFormContent" layoutH="56">
			<div class="adPackageTable" id="adPackageTableUrgencyPublish">
				<div class="titleBar">
					<div class="titleTd">
						<b>紧急广告包发布</b>
					</div>
				</div>
				<p style="width: 100%;">
					<label>广告类型：</label><label class="textLabel"
						id="adTypeUrgencyPublish"></label>
				</p>
				<p>
					<label>批次号：</label>
					<s:textfield name="adPackage.adPackageBatchNo" width="180px"
						readonly="true"></s:textfield>
				</p>
				<p>
					<label>广告包名称：</label>
					<s:textfield name="adPackage.adPackageName" readonly="true"
						width="180px"></s:textfield>
				</p>
				<p>
					<label>开始时间：</label>
					<s:textfield name="adPackage.adPackageBegintime1" readonly="true" />
				</p>
				<p>
					<label>截止时间：</label>
					<s:textfield name="adPackage.adPackageEndtime1" readonly="true" />
				</p>
				<p style="width: 100%; height: 65px" id="messageNoteUrgencyPublish">
					<label>文字通知：</label>
					<s:textarea class="editor" id="msgNoteUrgencyPublish" readonly="true"
						name="adPackage.adPackageTips"></s:textarea>
				</p>
			</div>
			<table id="adResourceUrgencyPublish" style="width: 98%">
				<tr valign="top">
					<td><div class="titleBar">
							<div class="titleTd">
								<b>广告包素材发布</b>
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
													id="operaUrgency<s:property value="#st.index+1" />">
													<div class="btnDivUrgencyUpd"></div>
												</div>
												<div class="updPicUrgency"
													style="position: relative; z-index: 1;">
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
													id="operaParamUrgencyPublish<s:property value="#st.index+1" />">
													<font>播放时长：<s:property value="adResourceTimes" /> 秒
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
			<table style="width: 98%;" id="adPackageTableSinglePublish2">
				<tr valign="top">
					<td>
						<div class="titleBar">
							<div class="titleTd">
								<b>选择需要发布的设备</b>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 20px;">
						<div class="titleTd">
						<b>筛选设备：</b>
						</div>
						<select id="province">
							<option value="0">--全部--</option>
							<s:iterator value="provinces" id="province" status="st">
							<option value="<s:property value="areaCode" />"><s:property value="areaName" /></option>
							</s:iterator>
						</select>
						<select id="city">
							<option value="0">--全部--</option>
							<!-- <s:iterator value="citys" id="city" status="st">
							<option value="<s:property value="areaCode" />"><s:property value="areaName" /></option>
							</s:iterator> -->
						</select>
						<select id="county">
							<option value="0">--全部--</option>
							<!-- <s:iterator value="countys" id="county" status="st">
							<option value="<s:property value="areaId" />"><s:property value="areaName" /></option>
							</s:iterator> -->
						</select>
					</td>
				</tr>
				<tr>
					<td style="padding-left: 20px"><input type="checkbox" name="selectAll" id="selectAll">全选</td>
				</tr>
				<tr>
					<td style="padding-left: 20px;">
						<ul id="device_list">
							<s:iterator value="etbClientDeviceList" id="etbClientDevice" status="st">
								<li>
									<input type="checkbox" name="selectDevice" value="<s:property value="imei" />">
									<s:property value="name" />
								</li>
							</s:iterator>
						</ul>
					</td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">&nbsp;发布&nbsp;</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">&nbsp;取消&nbsp;</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</s:form>
	<script type="text/javascript">
		$(document).ready(
				function() {
					//文字广告和图片广告UI控制
					if ('<s:property value="adPackage.adTemplateId"/>' == '<%=Constant.AD_TEMPLATE_URGENCY_PIC%>') {//图片广告
						$("#messageNoteUrgencyPublish").hide();
						$("#adResourceUrgencyPublish").show();
						$("#adTypeUrgencyPublish").text("图片广告");
						
						$("#adResourceUrgencyPublish .updPicUrgency img").each(
								function(i) {

									//设置播放时长和播放次数显示
									var divPamObj = $('#operaParamUrgencyPublish'
											+ $(this).attr("idNo"));
									divPamObj.css({
										position : "absolute",
										left : $(this).position().left,
										top : $(this).position().top + 161
									});
									$(this).parent().append(divPamObj);
								});
					} else {
						$("#messageNoteUrgencyPublish").show();
						$("#adResourceUrgencyPublish").hide();
						$("#adTypeUrgencyPublish").text("文字广告");

						$("#adPackageTableUrgencyPublish p").css("width", "100%");
						$("#msgNoteUrgencyPublish").css("width", "250px");
						$("#checkSuggestUrgencyPublish").css("width", "250px");
					}
					
					//选中的资源图片高亮显示
					$("#adResourceUrgencyPublish .updPicUrgency").click(function(){
						$("#adResourceUrgencyPublish .updPicUrgency").each(
								function(i) {
									$(this).find(".resourceImgUrgency").removeClass(
											"resourceImgSelected");
								});
						$(this).find(".resourceImgUrgency").addClass("resourceImgSelected");
					});
					
					//选中的资源图片高亮显示
					$("#adPackageTableSinglePublish .updPic").click(function(){
						$("#adPackageTableSinglePublish .updPic").each(
								function(i) {
									$(this).find(".resourceImgSingle").removeClass(
											"resourceImgSelected");
								});
						$(this).find(".resourceImgSingle").addClass("resourceImgSelected");
					});
					
					//根据省份获取城市
					$("#province").change(function(){
						var areaCode = $("#province").val();
						$.ajax({
							type : "post",
							cache : false,
							url : "ad/adPackage!getCityByProvince.do",
							data : "areaCode=" + areaCode,
							success : function(data) {
								data = eval(data);
								var city_list = data.city;
								var county_list = data.county;
								var device_list = data.device;
								city_list = eval(city_list);
								county_list = eval(county_list);
								device_list = eval(device_list);
								
								var html1 = "";
								var html2 = "";
								var html3 = "";
								for(var i = 0;i < city_list.length;i ++){
									html1 += "<option value='" + city_list[i].areaCode + "'>" + city_list[i].areaName + "</option>";
								}
								for(var i = 0;i < county_list.length;i ++){
									html2 += "<option value='" + county_list[i].areaId + "'>" + county_list[i].areaName + "</option>";
								}
								for(var i = 0;i < device_list.length;i ++){
									html3 += "<li><input type='checkbox' name='selectDevice' value='" + device_list[i].imei + "'>" + device_list[i].name + "</li>"
								}

								$("#city").html(html1);
								$("#county").html(html2);
								$("#device_list").html(html3);
							}
						});
					});

					//根据城市获取地区
					$("#city").change(function(){
						var areaCode = $("#city").val();
						$.ajax({
							type : "post",
							cache : false,
							url : "ad/adPackage!getCountyByCity.do",
							data : "areaCode=" + areaCode,
							success : function(data) {
								data = eval(data);
								var county_list = data.county;
								var device_list = data.device;
								county_list = eval(county_list);
								device_list = eval(device_list);
								
								var html1 = "";
								var html2 = "";
								for(var i = 0;i < county_list.length;i ++){
									html1 += "<option value='" + county_list[i].areaId + "'>" + county_list[i].areaName + "</option>";
								}
								for(var i = 0;i < device_list.length;i ++){
									html2 += "<li><input type='checkbox' name='selectDevice' value='" + device_list[i].imei + "'>" + device_list[i].name + "</li>"
								}

								$("#county").html(html1);
								$("#device_list").html(html2);
								
								/* var html = "";
								for(var i = 0;i < data.length;i ++){
									html += "<option value='" + data[i].areaId + "'>" + data[i].areaName + "</option>";
								}

								$("#county").html(html); */
							}
						});
					});
					
					//根据地区获取设备
					$("#county").change(function(){
						var areaId = $("#county").val();
						$.ajax({
							type : "post",
							cache : false,
							url : "ad/adPackage!getDeviceByArea.do",
							data : "areaId=" + areaId,
							success : function(data) {
								var html = "";
								for(var i = 0;i < data.length;i ++){
									html += "<li><input type='checkbox' name='selectDevice' value='" + data[i].imei + "'>" + data[i].name + "</li>"
								}

								$("#device_list").html(html);
							}
						});
					});
					
					//选择全部设备
					$("#selectAll").change(function(){
						if($("#selectAll").is(":checked")){
							for(var i = 0;i < $("input[type='checkbox']").length;i ++){
								if($("input[type='checkbox']")[i] != this){
									$("input[type='checkbox']")[i].checked = true;
								}
							}
						}else{
							for(var i = 0;i < $("input[type='checkbox']").length;i ++){
								if($("input[type='checkbox']")[i] != this){
									$("input[type='checkbox']")[i].checked = false;
								}
							}
						}

					});
				});
	</script>
</div>