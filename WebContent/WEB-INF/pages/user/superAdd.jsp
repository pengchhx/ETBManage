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
				新增客户
				<a href="userInfo!gotoCompanyList.do" class="sure_btn">确定</a>
			</h2>
		</div><!--search_wrapper end-->
		<div class="super_admin_middle clearfix">
			<div class="super_admin_left">
				<h3>客户基本信息</h3>
				<div class="table_wrapper">
					<form action="userInfo!addCompany.do" id="addForm">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th><label for=""><span>*</span><em>公司名称</em></label></th>
								<td><input type="text" class="txt"   name="companyInfo.companyInfoName"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>密码</em></label></th>
								<td><input type="password" class="txt"  name="companyInfo.companyInfoPassword"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>确认密码</em></label></th>
								<td><input type="password" class="txt"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>管理员姓名</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoAdminName"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>电话</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoTel"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>E-mail</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoEmail"></td>
							</tr>
							<tr>
								<th><label for=""><em>所属地区</em></label></th>
								<td class="clearfix">
									<select name="" id="select_province">
										<option>请选择</option>
										<s:iterator value="provinces" id="province">
											<option value="<s:property value='#province.areaCode'/>"><s:property value='#province.areaName'/></option>
										</s:iterator>
									</select>
									<select name="companyInfo.companyInfoAreaId" id="select_city">
									</select>
								</td>
							</tr>
							<tr>
								<th><label for=""><em>详细地址</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoAddress"></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="btns clearfix"><a href="#" class="save_btn" onclick="document.getElementById('addForm').submit();">保存</a><a href="#">取消</a></div>
			</div>
			<div class="super_admin_right">
				<h3>终端设备信息</h3>
				<div class="search_wrap">
					<form action="">
						<div class="form_group form_group1">
							<input type="text" class="txt" placeholder="设备ID查询">
							<a href="#" class="form_link"><img src="<%=path%>/images/search.png"></a>		
						</div>
						<div class="form_group form_group2">
							<input type="text" class="txt" placeholder="设备ID">
							 <a href="#" class="form_link">新增</a>		
						</div>
					</form>
				</div>
			</div>
		</div><!--super_middle end-->
	</div>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//中心内容高度
			var height = $(".super_admin_middle").height($(window).height() - $(".login_top").height() - $(".search_wrapper").height()) + "px";	
			$(".super_admin_middle").css("height",height);
			
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
				}				
			})
		})

		
	</script>
</body>
</html>