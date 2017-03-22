<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑人员登录</title>
<link rel="stylesheet" href="css/common.css">
</head>


<body>
	<div class="container">
		<div class="login_top">
			<div class="logo"><img src="images/blueLogo.png"></div>
			<div class="message_box clearfix">
				<span>admin</span>
				<span>(0)</span>
				<span>退出</span>
			</div>
		</div><!--login_top end-->
		<div class="middle">
			<h3>欢迎进入南京步锐捷数字标牌系统</h3>
			<div class="middle_lists middle600">
				<ul>
					<li><a href="#"><img src="images/zzgg.png">制作广告</a></li>
					<li><a href="#"><img src="images/fbgg.png">发布广告</a></li>
					<li><a href="#"><img src="images/fbjl.png">发布记录<span class="orange clearfix"><b>审核未通过:</b><em>10</em></span></a></li>
					<li><a href="#"><img src="images/ggk.png">广告库<span class="blue clearfix"><b>编辑中:</b><em>6</em></span></a></li>
				</ul>
			</div>
		</div><!--middle end-->
	</div>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//中心内容高度
			var height = $(".middle").height($(window).height() - $(".login_top").height()) + "px";	
			$(".middle").css("height",height);
		})
	</script>
</body>
</html>
