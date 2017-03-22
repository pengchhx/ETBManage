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
<link rel="stylesheet" href="<%=path%>/css/common1.css">
<link rel="stylesheet" href="<%=path%>/js/skin/default/layer.css">
<link rel="stylesheet" href="<%=path%>/js/skin/mylayer.css">
<link rel="stylesheet" href="<%=path%>/css/laypage.css">
<style type="text/css">
	#byPage{
		margin-bottom: 30px;
	}

</style>


</head>

<body class="grey">
	<div class="container">
		<div class="login_top">
			<div class="logo"><img src="<%=path%>/images/blueLogo.png"></div>
			<div class="message_box clearfix">
				<span>admin</span>
				<span>(0)</span>
				<span>退出</span>
			</div>
		</div><!--login_top end-->
		<div class="login_left">
			<ul>
				<li class="li1">制作广告</li>
				<li class="li2">发布广告</li>
				<li class="li3">发布审核</li>
				<li class="li4">发布记录</li>
				<li class="li5">广告库</li>
				<li class="li6">设备管理</li>
				<li class="li7 active">权限管理</li>
			</ul>
		</div><!--login_left end-->
		<div class="login_middle">
	 

			<header class="content_header">
			<h3>权限管理</h3>
			<div class="search_wrap clearfix">
				<form action="">
				<input type="hidden" value="totalCount" id="totalPageNo" />
					<strong class="clearfix"><label for="">用户名:</label><input type="text" id="userInfoUsername"></strong>
					<strong class="clearfix"><label for="">姓名:</label><input type="text" id="userInfoName"></strong>
					<strong class="clearfix">
						<label for="">访问权限:</label>
						<select name="powerInfoId" id="select_power">
							<option value="">请选择</option>
							<s:iterator value="powerInfoList" id="power">
								<option value="<s:property value="#power.powerInfoId"/>"><s:property value="#power.powerInfoName"/></option>				
							</s:iterator>
						</select></strong>
					<a onclick="demo()"><strong class="search_btn"><img src="<%=path%>/images/search.png"></strong></a>
				</form>
			</div><!--search_wrap end-->
			<div class="btn_wrap">
				<a href="#" class="add_btn" id='add_btn'>新增用户</a>
			</div>
			<div class="table_wrap" >
				<table  cellpadding="0" cellspacing="0" border="0" width="100%" id="userTable">
					<thead>
							<tr>
								<th>用户名</th>
								<th>姓名</th>
								<th>联系方式</th>
								<th>访问权限</th>
								<th>备注</th>
								<th></th>
							</tr>
					</thead>
					<tbody id="tbodyByPage">
<!-- 						<tr > -->
							
<%-- 							<td><a href="#" class="view"><img src="<%=path%>/images/preview.png"></a><a href="#" class="edit"><img src="<%=path%>/images/edit.png"></a><a href="#" class="delete"><img src="<%=path%>/images/delete.png"></a></td> --%>
<!-- 						</tr> -->
					</tbody>
				</table>
			</div><!--table_wrap end-->
			<div class="pagenation clearfix" id="byPage">

			</div><!--pagenation end-->
		</div><!--login_middle end-->
	</div><!--container end-->
	
	 
	
	
	
	
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/layer.js"></script>
	<script type="text/javascript" src="<%=path%>/js/laypage.js"></script>
	<script type="text/javascript">
		//右侧 高度
		function initPageLayout(){
			var height = $(window).height() - $(".login_top").height();	
			$(".login_middle").css("height",height);
			$(".login_left").css("height",height);
			$(".table_wrap").css({
			 	height: $('.pagenation').offset().top-$(".table_wrap").offset().top + 'px',
			 	overflow: 'auto'

			 });
		}
		 var addUser; 
		 function closeLayer(){
            	layer.close(addUser)
         }
		$(document).ready(function(){

			initPageLayout();
 
            /*添加用户*/
            $("#add_btn").click(function(event) {         
            	$.get('userInfo!gotoAddUser.do', function(result){
            		 addUser = layer.open({
  						type: 1,
  						title: false,
  						closeBtn: 0,
 				 		shadeClose: true,
  					 
  						area: ['720px', '520px'],
  						content: result
						});
            	})
            	
            }); 

            /*编辑用户*/
              $('#userTable').delegate(".edit",'click',function(){
            	  var id=$(this).attr("id");
            	$.get('userInfo!gotoUpdateUser.do?userInfo.userInfoId='+id, function(result){
            		 addUser = layer.open({
  						type: 1,
  						title: false,
  						closeBtn: 0,
 				 		shadeClose: true,
  						 
  						area: ['720px', '520px'],
  						content: result
						});
            	})
              	
              })

         /*删除用户*/
              $('#userTable').delegate(".delete",'click',function(){
            	  var id=$(this).attr("id");
            	$.get('userInfo!gotoDeleteUser.do?userInfo.userInfoId='+id, function(result){
            		 addUser = layer.open({
  						type: 1,
  						title: false,
  						closeBtn: 0,
 				 		shadeClose: true,
  						area: ['400px', '200px'],
  						content: result
						});
            	})
              	
              })


         /*查看用户*/
              $('#userTable').delegate(".view",'click',function(){
            	  
            	  var id=$(this).attr("id");
 					$.get('userInfo!gotoUserView.do?userInfo.userInfoId='+id, function(result){
            		    addUser = layer.open({
  						type: 1,
  						title: false,
  						closeBtn: 0,
 				 		shadeClose: true,						 
  						area: ['720px', '520px'],
  						content: result
						});
            	})
              	
              })
      

			$(window).resize(function(){
				initPageLayout();		 
			});
		
		
		
            //以下将以jquery.ajax为例，演示一个异步分页

		})
		 
		
		
		     function demo(curr){
				$.ajax({
						type:"post",
						url:"userInfo!getAuthorManage.do",
						data:{page: curr || 1,"userInfo.userInfoUsername":$("#userInfoUsername").val(),"userInfo.userInfoName":$("#userInfoName").val(),"powerInfoId":$("#select_power").val()},
						datatype:"json",
						success:function(res){
							
							document.getElementById("tbodyByPage").innerHTML ="";
							var list1=res.list;//得到用户信息
							for(var i=0;i<list1.length;i++){			
								document.getElementById("tbodyByPage").innerHTML += "<tr><td>"+list1[i].userInfoUsername+"</td><td>"+
								list1[i].userInfoName+"</td><td>"+list1[i].userInfoTel+"</td><td>"+list1[i].hasEditPower+
								list1[i].hasCheckPower+list1[i].hasDevicePower+"</td><td>"+list1[i].userInfoTips+
								"</td><td><a href='#' class='view' id="+list1[i].userInfoId+">"+"<img src='<%=path%>/images/preview.png'>"+
								"</a><a href='#' class='edit' id="+list1[i].userInfoId+"><img src='<%=path%>/images/edit.png'></a><a href='#' class='delete' id="+list1[i].userInfoId+">"+"<img src='<%=path%>/images/delete.png'><a/></td></tr>"; 
							
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
              
              
         
	</script>
</body>
</html>