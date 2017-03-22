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
	<!--删除-->
<div class="alert_wrap alert_delete" id="alert_delete">
		<div class="alert_content">
			<h3><a href="#"><img src="<%=path%>/images/close.png"  onclick="closeLayer()"></a></h3>
			<p>确定删除该用户？</p>
			<div class="btns clearfix"><a href='userInfo!deleteUser.do?userInfo.userInfoId=<s:property value="userInfo.userInfoId"/>' class="save_btn">保存</a>
			<a href="#"  onclick="closeLayer()">取消</a></div>
		</div> 
</div> <!--alert_wrap end-->



<script type="text/javascript">
// 	$(".save_btn").click(function(){
// 		var id=$(this).attr("id");
// 		alert(id);
// 		$.ajax({
// 			type:"post",
// 			url:"userInfo!deleteUser.do?userInfo.userInfoId="+id,
// 			data:"",
// 			datatype:"json",
// 			success:function(data){
// 				if(data=="yes"){
// 					alert("删除成功");
// 				}
				
				
// 			}
// 		})
		
// 	})





</script>





</body>
</html>