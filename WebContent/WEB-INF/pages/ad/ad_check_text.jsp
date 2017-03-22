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
<link rel="stylesheet" href="<%=ctx%>/css/common.css">
<link rel="stylesheet" href="<%=ctx%>/css/calendar.css">
</head>

<body class="grey">
	<div class="container">
		<div class="login_top">
			<div class="logo"><img src="<%=ctx%>/images/blueLogo.png"></div>
			<div class="message_box clearfix">
				<span>admin</span>
				<span>(0)</span>
				<span>退出</span>
			</div>
		</div><!--login_top end-->
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
		<div class="login_middle audit_wrap">
			<h3>发布详情<div class="btns"><span class="green_btn">通过</span><span class="orange_btn">不通过</span></div></h3>
			<div class="profile_tabs clearfix">
				<span class="active">详细信息</span><span>终端列表</span><span>审核历史</span>
			</div>
			<div class="tabs_wrap active no_border">
				<div class="prop_con">
					<h4 class="clearfix"><b></b><span>发布属性</span></h4>
					<div class="time_con clearfix"><span>开始时间</span><em>2016-12-30 14:20</em><span>失效时间</span><em>2017-12-30 14:20</em></div>
					<h4 class="clearfix"><b></b><span>广告列表</span><strong><input type="checkbox" checked>循环播放</strong></h4>
				</div>
				<div class="tabs_content tabs_content1">
					<div class="tabs_lis hori_li scroll_li">
						<ul class="clearfix">
							<!--<li>
								<div class="li_img"><img src="images/sbImg1.png"></div>
								<div class="time_con"><span>13:30:00</span><em>-</em><span>14:30:00</span></div>
								<div class="shade_btn clearfix">
									<input type="checkbox">
									<img src="images/delett.png" class="delett"> 
								</div>
								<em>编辑中</em>
								<div class="shade_content">
									<div class="shade_li">
										<span>广告名称:</span>
										<span>未命名-01</span>
									</div>
									<div class="shade_li">
										<span>分辨率:</span>
										<span>1000*600px</span>
									</div>
									<div class="shade_li">
										<span>上次编辑时间:</span>
										<span>2017.01.03 12:30</span>
									</div>
									<div class="shade_li">
										<span>编辑人:</span>
										<span>周韵</span>
									</div>
									<a href="#" class="more_white"><img src="images/moreWhite.png"></a>
								</div>
							</li>-->
						</ul>
					</div>
				</div><!-- 详细信息 tabs_content end-->
			</div><!--tabs_wrap end-->
			<div class="tabs_wrap equip_wrap no_border">
				<div class="tabs_content tabs_content2">
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix search_strong">
								<input placeholder="搜索素材" type="search">
								<img src="<%=ctx%>/images/search.png" width="20" height="20">
							</strong>
							<strong class="clearfix">
								<label for="">筛选:</label>
								<select name="" id="">
									<option value="江苏省">江苏省</option>
								</select>
								<select name="" id="">
									<option value="栖霞区">栖霞区</option>
								</select>
								<select name="" id="">
									<option value="马群街道">马群街道</option>
								</select>
							</strong>
						</form>
					</div><!--search_wrap end-->
					<div class="equipment_lists">
						<ul class="clearfix">
							<!--<li>
								<h2 class="clearfix"><strong>G45</strong><span><em class="green"></em></span></h2>
								<div>江苏省栖霞区马群街道</div>
								<div><a href="#"><img src="images/more.png"></a></div>
							</li>-->
						</ul>
					</div>
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
				</div><!--tabs_content end-->
			</div><!-- 终端列表 tabs_wrap end-->
			<div class="tabs_wrap no_border">
				<div class="tabs_content">
					<div class="hosi_li hosi_li1">
						<h4 class="clearfix"><span></span><em>2016.05.06</em></h4>
						<p>审核通过</p>
						<p>审核人:周韵</p>
					</div>
					<div class="hosi_li hosi_li2">
						<h4 class="clearfix"><span></span><em>2016.02.10</em></h4>
						<p>审核未通过</p>
						<p>审核人:周韵</p>
						<p>原因:终端设备选择错误</p>
					</div>
				</div><!--tabs_content end-->
			</div><!-- 审核历史 tabs_wrap end-->
		</div><!--login_middle end-->
	</div><!--container end-->
	
	<!--横版广告详情-不通过原因弹出层-->
	<div class="alert_wrap scroll_alert out_alert">
		<div class="alert_content">
			<h3>未通过原因<a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
			<div class="scroll_content">
				<textarea name="" id=""></textarea>
			</div>
			<div class="scroll_bottom clearfix">
				<span class="blue_btn">确定</span>
				<span class="grey_btn">取消</span>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	
	<script type="text/javascript" src="<%=ctx%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=ctx%>/js/zebra_datepicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//右侧高度
			var height = $(".login_middle").height($(window).height() - $(".login_top").outerHeight()) + "px";	
			$(".login_middle").css("height",height);
			
			var tabs_height = $(".login_middle").height() - $("h3").outerHeight() - $(".profile_tabs").outerHeight() - 36 + "px";
			$(".tabs_wrap").css("height",tabs_height);
			$(".tabs_content").css("height",$(".tabs_wrap").height());
			
			var con_height1 = $(".tabs_wrap").outerHeight() - $(".prop_con").outerHeight();
			$(".tabs_content1").css("height",con_height1);
			
			var tabs_li_height = $(".tabs_content").height() - $(".search_wrap").outerHeight() - 20 + "px";
			$(".equipment_lists").css("height",tabs_li_height);
			
			//滚动字幕li
			var textLi = [{
				"content":"北国风光，千里冰封，万里雪飘。",
				"name":"未命名-01", 
				"align":"顶部对齐", 
				"family":"黑体",
				"size":"24px",
				"path":"<%=ctx%>/images/scrollImg1.png",
				"speed":"快",
				"startTime":"13:30:00",
				"endTime":"14:30:00",
				"number":"01"
			},{
				"content":"北国风光，千里冰封，万里雪飘。",
				"name":"未命名-01", 
				"align":"顶部对齐", 
				"family":"黑体",
				"size":"24px",
				"path":"<%=ctx%>/images/scrollImg1.png",
				"speed":"快",
				"startTime":"13:30:00",
				"endTime":"14:30:00",
				"number":"02"
			}];
			var text_li_html = "";
			for(var i = 0; i < textLi.length; i ++){
				text_li_html += "<li>" +
								"<span class='num'>" + textLi[i].number + "</span>" +
								 "<div class='li_img'><p>" + textLi[i].content + "</p></div>" +
								 "<div class='time_con'><span>" + textLi[i].startTime + "</span><span>-</span><span>" + textLi[i].endTime + "</span></div>" +
								 "<div class='shade_content'>" +
								 	"<div class='shade_li clearfix'>" +
								 		"<span>广告名称:</span>" +
										"<span>" + textLi[i].name + "</span>" +
									"</div>" +
									"<div class='shade_li clearfix'>" +
								 		"<span>对齐方式:</span>" +
										"<span>" + textLi[i].align + "</span>" +
									"</div>" +
									"<div class='shade_li clearfix'>" +
								 		"<span>字体:</span>" +
										"<span>" + textLi[i].family + "</span>" +
									"</div>" +
									"<div class='shade_li clearfix'>" +
								 		"<span>字号:</span>" +
										"<span>" + textLi[i].size + "</span>" +
									"</div>" +
									"<div class='shade_li clearfix'>" +
								 		"<span>颜色:</span>" +
										"<img class='scroll_img' src='" + textLi[i].path + "'>" +
									"</div>" +
									 "<div class='shade_li clearfix'>" +
								 		"<span>滚动速度:</span>" +
										"<span>" + textLi[i].speed + "</span>" +
									"</div>" +
								 "</div>" +
						   "</li>";
			}
			$(".scroll_li ul").html(text_li_html);
			
			//终端列表li
			var li = [{
				"title":"G45",
				"position":"江苏省栖霞区马群街道",
				"url":"#"
			},{
				"title":"G45",
				"position":"江苏省栖霞区马群街道",
				"url":"#"
			}];
			var li_html = "";
			for(var i = 0; i < li.length; i ++){
				li_html += "<li>" +
								 "<h2 class='clearfix'><strong>" + li[i].title + "</strong></h2>" +
								 "<div>" + li[i].position + "</div>" +
								 "<div><a href='" + li[i].url + "'><img src='<%=ctx%>/images/more.png'></a></div>" +
						   "</li>";
			}
			$(".equipment_lists ul").html(li_html);
			
			//详细信息 终端列表 审核历史的tab切换
			$(".profile_tabs span").click(function(e) {
				$(this).addClass("active").siblings().removeClass("active");
				$(".tabs_wrap").eq($(this).index()).addClass("active").siblings().removeClass("active");
			});
			
			//鼠标移上移除的效果
			$(".hori_li li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});
			
			//点击查看更多详情按钮，显示详情弹出层--横版广告
			$(".hori_li .more_white").click(function(e) {
				$(".hori_alert").fadeIn(500);
			});

			//未通过弹出层
			$(".orange_btn").click(function(e) {
				$(".out_alert").fadeIn(500);
			});
			
			//关闭未通过弹出层
			$(".out_alert .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
		})
	</script>
</body>
</html>
