<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=path%>/css/common.css">
</head>

<body class="light">
	<div class="container">
	<%@include file="/top.jsp" %>
		<div class="search_wrapper">
			<h2>
				客户详情
				<a href="#" class="sure_btn">确定</a>
			</h2>
		</div><!--search_wrapper end-->
		<div class="super_admin_middle clearfix">
			<div class="super_admin_left">
				<h3>客户基本信息</h3>
				<div class="table_wrapper">
					<form action="userInfo!editCompany.do" id="editForm">
						<input type="hidden"  id="city" value="<s:property value='companyInfo.companyInfoAreaId'/>"/>
						<input type="hidden" name="id" id="id" value="0"/>
						<input type="hidden" name="companyInfo.companyInfoId" value="<s:property value='companyInfo.companyInfoId'/>" />
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th><label for=""><span>*</span><em>公司名称</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoName" value="<s:property value='companyInfo.companyInfoName'/>"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>密码</em></label></th>
								<td><input type="password" class="txt" name="companyInfo.companyInfoPassword" value="<s:property value='companyInfo.companyInfoPassword'/>"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>确认密码</em></label></th>
								<td><input type="password" class="txt"  value="<s:property value='companyInfo.companyInfoPassword'/>"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>管理员姓名</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoAdminName" value="<s:property value='companyInfo.companyInfoAdminName'/>"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>电话</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoTel" value="<s:property value='companyInfo.companyInfoTel'/>"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>E-mail</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoEmail" value="<s:property value='companyInfo.companyInfoEmail'/>"></td>
							</tr>
							<tr>
								<th><label for=""><em>所属地区</em></label></th>
								<td class="clearfix">
									<select  id="select_province">
										<s:iterator value="provinces" id="province" >
											<s:if test="#province.areaName==companyInfo.province">
												<option selected="selected" value="<s:property value='#province.areaCode'/>"><s:property value='#province.areaName'/></option>
											</s:if>
											<s:else>
												<option value="<s:property value='#province.areaCode'/>"><s:property value='#province.areaName'/></option>
											</s:else>					
										</s:iterator>
									</select>
									
									<select name="companyInfo.companyInfoAreaId" id="select_city">
				
									</select>
								</td>
							</tr>
							<tr>
								<th><label for=""><em>详细地址</em></label></th>
								<td><input type="text" name="companyInfo.companyInfoAddress" class="txt" value="<s:property value='companyInfo.companyInfoAddress'/>"></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="btns clearfix"><a onclick="document.getElementById('editForm').submit()" class="save_btn">保存</a><a href="userInfo!gotoSuperProfile.do?companyInfo.companyInfoId=<s:property value='companyInfo.companyInfoId'/>">取消</a></div>
			</div>
			<div class="super_admin_right">
				<h3>终端设备信息</h3>
				<div class="search_wrap">
					<form action="">
						<div class="form_group form_group1">
							<input type="text" class="txt" placeholder="设备ID查询" id="imei">
							<a onclick="demo()" class="form_link"><img src="<%=path%>/images/search.png"></a>		
						</div>
						<div class="form_group form_group2">
							<input type="text" class="txt" placeholder="设备ID" id="deviceImei">
							 <a onclick="addEtbClientDevice()" class="form_link">新增</a>		
						</div>
						<div class="form_group form_group3">
							 <a onclick="deleteDeviceBatch()" class="delet_btn">删除</a>	
							 <span><input type="checkbox" id="allcheck">全选</span>	
						</div>
					</form>
				</div>
				<div class="super_admin_lists">
					<ul id="tbodyByPage">

					</ul>
				</div>
			</div>
		</div><!--super_middle end-->
	</div><!--container end-->
	
	<div id="byPage"></div>
	
	<!--删除 -->
	<div class="alert_wrap alert_delete super_alert" id="alert_delete" style="display:none">
		<div class="alert_content">
			<h3><a href="#" id="p_cancel"><img src="<%=path%>/images/close.png"></a></h3>
			<p>消息未保存确定离开？</p>
			<div class="btns clearfix"><a href="#" class="save_btn" id="save_btn">保存并离开</a><a href="userInfo!gotoCompanyList.do" class="cancel_btn" >放弃保存</a><a href="#" id="a_cancel">取消</a></div>
		</div>
	</div>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/laypage.js"></script>
	<script type="text/javascript">

		   function demo(){
				$.ajax({
						type:"post",
						url:"userInfo!getDeviceListByCompany.do?companyInfo.companyInfoId="+<s:property value='companyInfo.companyInfoId'/>,
						data:{"imei":$("#imei").val()},
						datatype:"json",
						success:function(res){
						
							document.getElementById("tbodyByPage").innerHTML ="";
							var list1=res.list;//得到用户信息
							var length=list1.length;
							for(var i=0;i<list1.length;i++){			
							document.getElementById("tbodyByPage").innerHTML+="<li class='clearfix'>" +
																			 "<em>" + (length-i) + "</em>" +
																			 "<span>" + list1[i].imei + "</span>" +
																			 "<strong><a class='ondelete' id='"+list1[i].imei+"'><img src='<%=path%>/images/deleteBtn.png'></a></strong>"+"<input type='checkbox' id='"+list1[i].imei+"'>" +"</li>";
							
							}

						}
					})
              };
         demo();

         
		$(document).ready(function(){
			//中心内容高度
			var height = $(".super_admin_middle").height($(window).height() - $(".login_top").height() - $(".search_wrapper").height()) + "px";	
			$(".super_admin_middle").css("height",height);	
		})
		
			//单个删除
			$(document).on("click",".ondelete",function(){

				$.ajax({
					url:"userInfo!deleteEtbClientDevice.do",
					type:"post",
					data:{"imei":$(this).attr("id")},
					datatype:"json",
					success:function(data){
						if(data=="success"){
							demo();
						}
					}				
				})	
			})
			
		
			var arr=[];	
			//批量删除
			function deleteDeviceBatch(){
				arr=[];
				$("li input:checkbox:checked").each(function(){
					arr.push($(this).attr("id"));
				})
				var imei=arr.join("-");				
				$.ajax({
					url:"userInfo!deleteEtbClientDeviceBatch.do",
					type:"post",
					data:{"imei":imei},
					datatype:"json",
					success:function(data){
						if(data=="success"){
							demo();
						}
					}				
				})		
			}
			
			//全选反全选
			$("#allcheck").click(function(){
				if($(this).prop("checked")){
					$("li input:checkbox").prop("checked","true");	
				}
				if(!$(this).prop("checked")){
					$("li input:checkbox").removeAttr("checked");	
				}
			})	
			
			//新增设备
			function addEtbClientDevice(){
				$.ajax({
					url:"userInfo!addEtbClientDevice.do",
					type:"post",
					data:{"imei":$("#deviceImei").val(),"companyInfo.companyInfoId":<s:property value='companyInfo.companyInfoId'/>},
					datatype:"json",
					success:function(data){
						if(data=="success"){
							demo();
						}
					}				
				})		
			}

			//改变添加按钮样式
			$("#deviceImei").focus(function(){
				$(this).next().addClass("form_link blue");					
			})
			
			//点击复选框 删除按钮样式改变
			$(document).on("click","input[type=checkbox]",function(){
					$(".delet_btn").addClass("delet_btn blue");					
			})
			
			
			//返回按钮处理
			$(".sure_btn").click(function(){
				$("#alert_delete").show();				
			})
			
			//取消
			$("#a_cancel").click(function(){
				$("#alert_delete").hide();				
			})
			
			//取消
			$("#p_cancel").click(function(){
				$("#alert_delete").hide();				
			})
			
			//保存
			$("#save_btn").click(function(){
				$("#id").val(1);
				$("#editForm").submit();				
			})

			
		$("#select_province").change(function(){
			$("#select_city option").remove();
			$.ajax({
				url:"userInfo!getCityByProvince.do",
				type:"post",
				data:{"areaCode":$(this).val()},
				datatype:"json",
				success:function(data){			
					var list=data.cityList;		
					for(var i=0;i<list.length;i++){
						$("#select_city").append("<option value="+list[i].areaId+">"+list[i].areaName+"</option>");
					}			
		
					$("#select_city option").each(function(){
						if($(this).val()==$("#city").val()){
							$(this).prop("selected","true");
						}
					})
					
					}
				})				
			})
		
			
		
		$("#select_province").change();
			
	</script>
</body>
</html>
