<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>

<link rel="stylesheet" href="css/common.css">
</head>
<body>
	<div class="login_wrap">
		<div class="login_content clearfix">
			<img src="images/loginbg.jpg" class="loginbg">
			<div class="logo"><img src="images/logo.png"></div>
			<div class="form_wrap">
				<h3>步锐捷广告标牌系统</h3>
				<form action="user/Login!checkLogin.do">
					<input type="hidden" name="pageNum" value="2">
					<div class="form_group">
						<input type="text" placeholder="账号" class="txt" name="userInfo.userInfoUsername">
					</div>
					<div class="form_group">
						<input type="password" placeholder="密码" class="password" name="userInfo.userInfoPwd">
					</div>
					<div class="form_group clearfix">
						<input type="text" placeholder="验证码" class="code"><span class="code_box"><img src="images/code.png"></span>
					</div>
					<div class="form_group clearfix">
						<input  type="submit" value="登录" class="login_btn btn"><input type="reset" value="重置" class="reset_btn btn">
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".login_wrap").height($(".login_content").height());
			var height = -$(".login_wrap").height()/2 + "px";
			$(".login_wrap").css("margin-top",height);
		})
	</script>
</body>
</html>