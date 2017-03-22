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
<title>超级管理员登录</title>
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/laypage.css">
<style type="text/css">
	#byPage{

		margin-bottom: 150px;
	}

</style>
</head>

<body class="light">
	<div class="container">
		<%@include file="/top.jsp" %>
		<div class="search_wrapper">
			<a href="#"><input type="text" placeholder="通过关键词查找" id="companyInfoName"><img src="<%=path%>/images/search.png" onclick="demo()"></a>
		</div><!--search_wrapper end-->
		<div class="super_middle">
			<div class="btn_wrap">
				<a href="userInfo!gotoSuperAdd.do" class="add_btn">新增客户</a>
			</div>
			<div class="super_lists">
				<ul class="clearfix" id="tbodyByPage">

		

				</ul>
			</div>
			<div class="pagenation clearfix" id="byPage">
			</div><!--pagenation end-->
		</div><!--super_middle end-->
	</div>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/laypage.js"></script>
	<script type="text/javascript">
	
    function demo(curr){
		$.ajax({
				type:"post",
				url:"userInfo!getCompanyList.do",
				data:{page: curr || 1,"companyInfo.companyInfoName":$("#companyInfoName").val()},
				datatype:"json",
				success:function(res){
					document.getElementById("tbodyByPage").innerHTML ="";
					var list1=res.list;//得到用户信息
					for(var i=0;i<list1.length;i++){	
						document.getElementById("tbodyByPage").innerHTML += "<li id='"+list1[i].companyInfoId+"'><h3>"+list1[i].companyInfoName+
						"</h3><p><span>"+6+"</span>"+0+"</p><div class='position_wrap'><span>"+list1[i].province+list1[i].city+"</span><a href='userInfo!gotoSuperEdit.do?companyInfo.companyInfoId="+list1[i].companyInfoId+"'><img src='<%=path%>/images/edit.png'></a></div></li>";
					}
	                  laypage({
	                    cont: 'byPage', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
	                    pages: res.totalPage, //通过后台拿到的总页数
	                    curr: curr || 1, //当前页
	                    jump: function(obj, first){ //触发分页后的回调
	                      if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
	                        demo(obj.curr);
	                      }
	                    }
	                  });
				}
			})
     };
     demo();
     
     //进入
		$(document).on("click","li",function(){
				window.location.href="userInfo!gotoSuperProfile.do?companyInfo.companyInfoId="+$(this).attr("id");
		})
     
     
     
     
	</script>
</body>
</html>
