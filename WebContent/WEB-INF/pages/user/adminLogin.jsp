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
<title>管理员登录</title>
<link rel="stylesheet" href="<%=path%>/css/common.css">
</head>

<body>
	<div class="container">
<!-- 		<div class="login_top"> -->
<!-- 			<div class="logo"><img src="../images/blueLogo.png"></div> -->
<!-- 			<div class="message_box clearfix"> -->
<%-- 				<span><s:property value="#session.user.userInfoUsername"/></span> --%>
<%-- 				<span>(0)</span> --%>
<%-- 				<span>退出</span> --%>
<!-- 			</div> -->
<!-- 		</div>login_top end -->
	<%@include file="/top.jsp" %>
		
		<div class="middle">
			<h3>欢迎进入南京步锐捷数字标牌系统</h3>
			<div class="middle_lists middle1300">
				<ul>
					<s:iterator value="#session.user.powerInfoList" id="power">
						<s:if test="#power.powerInfoId==3||#power.powerInfoId==2">
							<li><a href="<%=path%>/ad/adPackage!toHbTemplateChoose.do"><img src="<%=path%>/images/zzgg.png">制作广告</a></li>
							<li><a href="<%=path%>/ad/adPackage!toAdList.do"><img src="<%=path%>/images/ggk.png">广告库</a></li>
							<li><a href="<%=path%>/ad/adPackage!toReleaseAd.do"><img src="<%=path%>/images/fbgg.png">发布广告</a></li>
							<li><a href="<%=path%>/ad/adPackage!toReleaseRecord.do"><img src="<%=path%>/images/fbjl.png">发布记录</a></li>
						</s:if>
						<s:if test="#power.powerInfoId==4||#power.powerInfoId==2">
							<li><a href="<%=path%>/ad/adPackage!toCheckAdList.do"><img src="<%=path%>/images/fbsh.png">发布审核</a></li>
						</s:if>
						<s:if test="#power.powerInfoId==5||#power.powerInfoId==2">
							<li><a href="/ETBManage/device/etbClientDeviceAction!gotoDeviceList.do"><img src="<%=path%>/images/sbgl.png">设备管理</a></li>
						</s:if>		
					</s:iterator>
					<s:if test="#power.powerInfoId==2">
							<li><a href="userInfo!gotoAuthorManage.do"><img src="<%=path%>/images/qxgl.png">权限管理</a></li>
					</s:if>
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
