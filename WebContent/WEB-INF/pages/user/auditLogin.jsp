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
<title>审核人员登录</title>
<link rel="stylesheet" href="<%=path%>/css/common.css">
<link rel="stylesheet" href="<%=path%>/css/calendar.css">
</head>


<body class="grey">
	<div class="container">
		<%@include file="/top.jsp" %>
		<%@include file="/left.jsp" %>
		<div class="login_middle">
			<h3>发布审核</h3>
			<div class="search_wrap clearfix">
				<form action="">
					<strong class="clearfix"><label for="">发布单号:</label><input type="text"></strong>
					<strong class="clearfix"><label for="">节目名称:</label><input type="text"></strong>
					<strong class="clearfix"><label for="">发布时间:</label><input id="datepicker-start" type="text" placeholder="起始时间"><em>-</em><input id="datepicker-end" type="text" placeholder="结束时间"></strong>
					<strong class="search_btn"><img src="<%=path%>/images/search.png"></strong>
				</form>
			</div><!--search_wrap end-->
			<div class="table_wrap">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" id="table">
					<thead>
						<tr>
							<th>发布单号</th>
							<th>节目名称</th>
							<th>发布人</th>
							<th>发布时间</th>
							<th>失效时间</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<!--<tr>
							<td>LSJ42157</td>
							<td>ceshijiemu</td>
							<td>周韵</td>
							<td>2016-2-10 12:20:24</td>
							<td>2016-3-10</td>
							<td><a href="#"><img src="images/preview.png"></a></td>
						</tr>-->
					</tbody>
				</table>
			</div><!--table_wrap end-->
			<div class="pagenation clearfix">
				 <span class="first">上一页</span>
				 <span>1</span>
				 <span class="active">2</span>
				 <span>3</span>
				 <span>......</span>
				 <span>10</span>
				 <span class="select">8</span>
				 <span class="last">下一页</span>
			</div><!--pagenation end-->
		</div><!--login_middle end-->
	</div>
	<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/zebra_datepicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//日期选择
			$('#datepicker-start').Zebra_DatePicker({
			direction: true,
			pair: $('#datepicker-end')
			});
			$('#datepicker-end').Zebra_DatePicker({
				direction: 1
			});	
			//右侧高度
			var height = $(".login_middle").height($(window).height() - $(".login_top").height()) + "px";	
			$(".login_middle").css("height",height);
			
			//td
			var tr = [{
				"numbers":"LSJ42157", 
				"name":"ceshijiemu", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
			    "url":"#",
				"path":"<%=path%>/images/preview.png"
			},{
				"numbers":"LSJ42157", 
				"name":"ceshijiemu", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
				"url":"#",
				"path":"<%=path%>/images/preview.png"
			},{
				"numbers":"LSJ42157", 
				"name":"ceshijiemu", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
				"url":"#",
				"path":"<%=path%>/images/preview.png"
			},{
				"numbers":"LSJ42157", 
				"name":"ceshijiemu", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
				"url":"#",
				"path":"<%=path%>/images/preview.png"
			}];
			var tr_html = "";
			for(var i = 0; i < tr.length; i ++){
				tr_html += "<tr>" +
								 "<td>" + tr[i].numbers + "</td>" +
								 "<td>" + tr[i].name + "</td>" +
								 "<td>" + tr[i].user + "</td>" +
								 "<td>" + tr[i].startTime + "</td>" +
								 "<td>" + tr[i].failureTime + "</td>" +
								 "<td><a href='" + tr[i].url + "'><img src='" + tr[i].path + "'></a></td>" +
						   "</tr>";
			}
			$("#table tbody").html(tr_html);
		})
	</script>
</body>
</html>
