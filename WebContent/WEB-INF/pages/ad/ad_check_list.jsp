<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%
	String ctx = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发布审核</title>
<link rel="stylesheet" href="<%=ctx %>/css/common.css">
<link rel="stylesheet" href="<%=ctx %>/css/calendar.css">
</head>

<body class="grey">
	<div class="container">
		<!-- <div class="login_top">
			<div class="logo"><img src="<%=ctx %>/images/blueLogo.png"></div>
			<div class="message_box clearfix">
				<span>admin</span>
				<span>(0)</span>
				<span>退出</span>
			</div>
		</div><!--login_top end-->
		<%@include file="/top.jsp" %>
		<div class="login_left">
			<ul>
				<!--<li class="li1">制作广告</li>
				<li class="li2">发布广告</li>-->
				<li class="li3 active">发布审核</li>
				<!--<li class="li4">发布记录</li>
				<li class="li5">广告库</li>
				<li class="li6">设备管理</li>
				<li class="li7">权限管理</li>-->
			</ul>
		</div><!--login_left end-->
		<div class="login_middle">
			<h3>发布审核</h3>
			<div class="search_wrap clearfix">
				<form action="">
					<strong class="clearfix"><label for="">发布单号:</label><input type="text"></strong>
					<strong class="clearfix"><label for="">提交发布时间:</label><input id="datepicker-start" type="text" placeholder=""><em>-</em><input id="datepicker-end" type="text" placeholder=""></strong>
					<strong class="search_btn"><img src="<%=ctx %>/images/search.png"></strong>
				</form>
			</div><!--search_wrap end-->
			<div class="table_wrap">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" id="table">
					<thead>
						<tr>
							<th>发布单号</th>
							<th>发布人</th>
							<th>提交发布时间</th>
							<th>开始播放时间</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<!--<tr>
							<td>LSJ42157</td>
							<td>周韵</td>
							<td>2016-2-10 12:20:24</td>
							<td>2016-3-10</td>
							<td><a class="blue_btn audit_btn" href="#">审核</a></td>
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
	<script type="text/javascript" src="<%=ctx %>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=ctx %>/js/zebra_datepicker.js"></script>
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
			
			var table_height = $(".login_middle").height() - $("h3").outerHeight() - $(".search_wrap").outerHeight() -108 + "px";
			$(".table_wrap").css("height",table_height);
			
			//td
			/*var tr = [{
				"numbers":"LSJ42157", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
			    "url":"auditProfilehb.html"
			},{
				"numbers":"LSJ42157", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
				"url":"auditProfilesb.html"
			},{
				"numbers":"LSJ42157", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
				"url":"auditProfiletext.html"
			},{
				"numbers":"LSJ42157", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
				"url":"#"
			}];
			var tr_html = "";
			for(var i = 0; i < tr.length; i ++){
				tr_html += "<tr>" +
								 "<td>" + tr[i].numbers + "</td>" +
								 "<td>" + tr[i].user + "</td>" +
								 "<td>" + tr[i].startTime + "</td>" +
								 "<td>" + tr[i].failureTime + "</td>" +
								 "<td><a class='blue_btn audit_btn' href='" + tr[i].url + "'>审核</a></td>" +
						   "</tr>";
			}
			$("#table tbody").html(tr_html);*/

			$.ajax({
				url: "adPackage!getCheckAdList.do",
				type: "POST",
				async: false,
				success: function(data){
					data = eval(data);
					var tr = data.ad_list;
					var tr_html = "";
					for(var i = 0; i < tr.length; i ++){
						tr_html += "<tr>" +
										 "<td>" + tr[i].adPackageBatchNo + "</td>" +
										 "<td>" + tr[i].adPackagePublisher + "</td>" +
										 "<td>" + tr[i].adPackagePublishTime + "</td>" +
										 "<td>" + tr[i].adPackageBeginTime + "</td>" +
										 "<td>" +
										 "<input type='hidden' id='adPakageId' value='" + tr[i].adPackageId + "'>";
										 if(tr[i].adPackageType == "H"){
										 	tr_html += "<a class='blue_btn audit_btn' href='adPackage!toCheckHB.do?adPackageId=" + tr[i].adPackageId + "'>审核</a></td>";
										 }
										 if(tr[i].adPackageType == "S"){
										 	tr_html += "<a class='blue_btn audit_btn' href='adPackage!toCheckSB.do?adPakacgeId=" + tr[i].adPackageId + "'>审核</a></td>";
										 }
										 if(tr[i].adPackageType == "G"){
										 	tr_html += "<a class='blue_btn audit_btn' href='adPackage!toCheckText.do?adPakacgeId=" + tr[i].adPackageId + "'>审核</a></td>";
										 }
						tr_html += 	"</tr>";
					}
					$("#table tbody").html(tr_html);
				}
			});
		});
	</script>
</body>
</html>
