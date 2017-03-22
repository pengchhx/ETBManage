<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>

 <div class="alert_wrap alert_add" id="alert_add">
		<div class="alert_content alert_user_manager_content">
			<h3>添加用户<a href="#" onclick="closeLayer()"><img src="<%=path%>/images/close.png"></a></h3>
			<form action="userInfo!addUser.do" method="post" id="addForm">
			<div class="table_wrapper">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th><label for=""><span>*</span><em>用户名</em></label></th>
						<td><input type="text" class="txt" name="userInfo.userInfoUsername"></td>
					</tr>
					<tr>
						<th><label for=""><span>*</span><em>姓名</em></label></th>
						<td><input type="text" class="txt" name="userInfo.userInfoName"></td>
					</tr>
					<tr>
						<th><label for=""><span>*</span><em>密码</em></label></th>
						<td><input type="password" class="txt" name="userInfo.userInfoPwd"></td>
					</tr>
					<tr>
						<th><label for=""><span>*</span><em>确认密码</em></label></th>
						<td><input type="password" class="txt"></td>
					</tr>
					<tr>
						<th><label for=""><span>*</span><em>电话</em></label></th>
						<td><input type="text" class="txt" name="userInfo.userInfoTel"></td>
					</tr>
					<tr>
						<th><label for=""><span>*</span><em>访问权限</em></label></th>
						<td>
							<span><input type="checkbox" name="tempList" value="3">编辑权限</span>
							<span><input type="checkbox" name="tempList" value="4">审核权限</span>
							<span><input type="checkbox" name="tempList" value="5">维护权限</span>
						</td>
					</tr>
					<tr>
						<th><label for=""><em>性别</em></label></th>
						<td><input type="radio" class="" name="userInfo.userInfoSex" value="1">  男  <input type="radio" class="" name="userInfo.userInfoSex" value="0">  女 </td>
					</tr>
					<tr>
						<th><label for=""><em>备注</em></label></th>
						<td><textarea name="userInfo.userInfoTips" id="" cols="30" rows="10"></textarea></td>
					</tr>
				</table>
			</div>
			<div class="btns clearfix"><a href="#" class="save_btn" id="save_btn" onclick="document.getElementById('addForm').submit()">保存</a><a href="#" onclick="closeLayer()">取消</a></div>
			</form>
		</div>
</div>

<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript">
	
	
	
</script>



</body>
</html>