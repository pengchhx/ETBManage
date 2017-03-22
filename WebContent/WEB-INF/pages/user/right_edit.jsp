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
<body>
	<h3>终端设备信息</h3>
				<div class="search_wrap">
					<form action="">
						<div class="form_group form_group1">
							<input type="text" class="txt" placeholder="设备ID查询" id="imei">
							<a  class="form_link"><img src="<%=path%>/images/search.png"></a>		
						</div>
						<div class="form_group form_group2" >
							<input type="text" class="txt" placeholder="设备ID" id="deviceImei">
							 <a  onclick="addEtbClientDevice()" class="form_link">新增</a>		
						</div>
						<div class="form_group form_group3">
							 <a onclick="deleteDeviceBatch()" class="delet_btn">删除</a>	
							 <span><input type="checkbox"  id="allcheck">全选</span>	
						</div>
					</form>
				</div>
			<div class="super_admin_lists" >
				<ul id="tbodyByPage">
				
				</ul>
			</div>
			
			<div id="byPage">

			</div>
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
			</script>
</body>
</html>