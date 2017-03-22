<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公共模块</title>


</head>
<body>
		<div class="login_top">
			<div class="logo"><img src="../images/blueLogo.png"></div>
			<div class="message_box clearfix">
				<span><s:property value="#session.user.userInfoUsername"/></span>
				<span>(0)</span>
				<span><a href="/ETBManage/user/Login!logout.do">退出</a></span>
			</div>
		</div>
</body>
</html>