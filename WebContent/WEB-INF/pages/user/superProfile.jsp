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
				<a class="sure_btn">确定</a>
			</h2>
		</div><!--search_wrapper end-->
		<div class="super_admin_middle clearfix">
			<div class="super_admin_left" id="edit_left_box">
				<h3>客户基本信息<a id="edit_left" class="edit_link"><img src="<%=path%>/images/editt.png" width="20" height="22"></a></h3>
				<div class="table_wrapper">
					<form action="">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th><label for=""><em>公司名称</em></label></th>
								<td><input type="text" class="txt no_border" value="<s:property value='companyInfo.companyInfoName'/>" readOnly="true"></td>
							</tr>
							<tr>
								<th><label for=""><em>密码</em></label></th>
								<td><input type="password" class="txt no_border"  value="<s:property value='companyInfo.companyInfoPassword'/>" readOnly="true"></td>
							</tr>
							<tr>
								<th><label for=""><em>管理员姓名</em></label></th>
								<td><input type="text" class="txt no_border" value="<s:property value='companyInfo.companyInfoAdminName'/>" readOnly="true"></td>
							</tr>
							<tr>
								<th><label for=""><em>电话</em></label></th>
								<td><input type="text" class="txt no_border" value="<s:property value='companyInfo.companyInfoTel'/>" readOnly="true"></td>
							</tr>
							<tr>
								<th><label for=""><em>E-mail</em></label></th>
								<td><input type="text" class="txt no_border" value="<s:property value='companyInfo.companyInfoEmail'/>" readOnly="true"></td>
							</tr>
							<tr>
								<th><label for=""><em>所属地区</em></label></th>
								<td><input type="text" class="txt no_border" value="<s:property value='companyInfo.province'/><s:property value='companyInfo.city'/>" readOnly="true"></td>
							</tr>
							<tr>
								<th><label for=""><em>详细地址</em></label></th>
								<td><input type="text" class="txt no_border" value="<s:property value='companyInfo.companyInfoAddress'/>" readOnly="true"></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="super_admin_right" id="edit_right_box">
				<h3>终端设备信息<a  id="edit_right" class="edit_link"><img src="<%=path%>/images/editt.png" width="20" height="22"></a></h3>
				<div class="search_wrap">
					<form action="">
						<div class="form_group form_group1">
							<input type="text" class="txt" placeholder="设备ID查询" id="imei">
							<a onclick="demo()" class="form_link"><img src="<%=path%>/images/search.png"></a>		
						</div>
						<div class="form_group form_group2">
							<input type="text" class="txt" placeholder="设备ID"  id="deviceImei">
							 <a onclick="addEtbClientDevice()" class="form_link">新增</a>		
						</div>
<!-- 						<div class="form_group form_group3"> -->
<!-- 							 <a href="#" class="delet_btn">删除</a>	 -->
<!-- 						</div> -->
					</form>
				</div>
				<div class="super_admin_lists">
					<ul id="tbodyByPage">

					</ul>
				</div>
			</div>
		</div><!--super_middle end-->
	</div>
	
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
		var flag=true;
	
		$(document).ready(function(){
			//中心内容高度
			var height = $(".super_admin_middle").height($(window).height() - $(".login_top").height() - $(".search_wrapper").height()) + "px";	
			$(".super_admin_middle").css("height",height);
			
		})
		
		
		
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
																			 "<em>" + (length-i)  + "</em>" +
																			 "<span>" + list1[i].imei + "</span>"+"</li>";
							
							}
	
						}
					})
              };
         demo();
         
         
         
         
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

			
			
			/*左边编辑*/
			$("#edit_left").click(function(){
				flag=false;
				var url = "userInfo!gotoLeft.do?companyInfo.companyInfoId="+<s:property value='companyInfo.companyInfoId'/>;
				$("#edit_left_box").load(url);

			});

			/*右边边编辑*/
			$("#edit_right").click(function(){
				var url = "userInfo!gotoRight.do?companyInfo.companyInfoId="+<s:property value='companyInfo.companyInfoId'/>;
				$("#edit_right_box").load(url);

			})
			
			
			//确定按钮的判断
			$(".sure_btn").click(function(){
				if(flag){
					window.location.href="userInfo!gotoCompanyList.do";
				}else{
					$("#alert_delete").show();
					
				}	
			})
			
			
			//取消
			$("#a_cancel").click(function(){
				$("#alert_delete").hide();				
			})
			
			//取消
			$("#p_cancel").click(function(){
				$("#alert_delete").hide();				
			})
			


	</script>
</body>
</html>
