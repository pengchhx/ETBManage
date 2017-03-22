<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%
	String ctx = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>广告库</title>
<link rel="stylesheet" href="<%=ctx %>/css/common.css">
<link rel="stylesheet" href="<%=ctx %>/css/calendar.css">
</head>

<body class="light">
	<div class="container">
		<div class="login_top">
			<div class="logo"><img src="<%=ctx %>/images/blueLogo.png"></div>
			<div class="message_box clearfix">
				<span>admin</span>
				<span>(0)</span>
				<span>退出</span>
			</div>
		</div><!--login_top end-->
		<div class="login_left">
			<ul>
				<li class="li1"><a href="<%=ctx%>/ad/adPackage!toHbTemplateChoose.do">制作广告</a></li>
				<li class="li2"><a href="<%=ctx%>/ad/adPackage!toReleaseAd.do">发布广告</a></li>
				<!--<li class="li3 active"><a href="#">发布审核</a></li>-->
				<li class="li4"><a href="<%=ctx%>/ad/adPackage!toReleaseRecord.do">发布记录<span>未通过审核10</span></a></li>
				<li class="li5 active"><a href="<%=ctx%>/ad/adPackage!toAdList.do">广告库</a></li>
				<!--<li class="li6"><a href="#">设备管理</a></li>-->
				<!--<li class="li7"><a href="#">权限管理</a></li>-->
			</ul>
		</div><!--login_left end-->
		<div class="login_middle">
			<h3>广告库</h3>
			<div class="tabs_wrap">
				<div class="tabs_btn clearfix">
					<a href="#" class="active">横板广告</a>
					<a href="#">竖版广告</a>
					<a href="#">滚动字幕</a>
				</div><!--tabs_btn end-->
				<div class="tabs_content active">
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix">
								<label for="">分辨率:</label>
								<select name="" id="">
									<option value=""></option>
								</select>
							</strong>
							<strong class="clearfix">
								<label for="">编辑时间:</label>
								<input id="datepicker-start" type="text" placeholder="起始时间"><em>-</em><input id="datepicker-end" type="text" placeholder="结束时间">
							</strong>
							<strong class="search_btn"><img src="<%=ctx %>/images/search.png"></strong>
							<strong class="editt_btn"><img src="<%=ctx %>/images/editt.png" width="20" height="22"></strong>
						</form>
					</div><!--search_wrap end-->
					<div class="all_select_wrap">
					    <span><input type="checkbox">全选</span>
						<a href="#" class="delet_btn">删除</a>	
					</div>
					<div class="tabs_lis hori_li">
						<ul class="clearfix">
							<!--<li>
								<img src="images/hbImg1.png">
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
				</div><!-- 横版广告 tabs_content end-->
				<div class="tabs_content">
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix">
								<label for="">分辨率:</label>
								<select name="" id="">
									<option value=""></option>
								</select>
							</strong>
							<strong class="clearfix">
								<label for="">编辑时间:</label>
								<input id="datepicker-start1" type="text" placeholder="起始时间"><em>-</em><input id="datepicker-end1" type="text" placeholder="结束时间">
							</strong>
							<strong class="search_btn"><img src="<%=ctx %>/images/search.png"></strong>
							<strong class="editt_btn"><img src="<%=ctx %>/images/editt.png" width="20" height="22"></strong>
						</form>
					</div><!--search_wrap end-->
					<div class="all_select_wrap">
					    <span><input type="checkbox">全选</span>
						<a href="#" class="delet_btn">删除</a>	
					</div>
					<div class="tabs_lis vert_li">
						<ul class="clearfix">
							<!--<li>
								<img src="images/sbImg1.png">
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
				</div><!-- 竖版广告 tabs_content end-->
				<div class="tabs_content">
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix">
								<label for="">对齐方式:</label>
								<select name="" id="">
									<option value="">顶部对齐</option>
								</select>
							</strong>
							<strong class="clearfix">
								<label for="">编辑时间:</label>
								<input id="datepicker-start2" type="text" placeholder="起始时间"><em>-</em><input id="datepicker-end2" type="text" placeholder="结束时间">
							</strong>
							<strong class="search_btn"><img src="<%=ctx %>/images/search.png"></strong>
							<strong class="editt_btn"><img src="<%=ctx %>/images/editt.png" width="20" height="22"></strong>
						</form>
					</div><!--search_wrap end-->
					<div class="all_select_wrap">
					    <span><input type="checkbox">全选</span>
						<a href="#" class="delet_btn">删除</a>	
					</div>
					<div class="tabs_lis scroll_li">
						<ul class="clearfix">
							<!--<li>
								<p class="scroll_content">北国风光，千里冰封，万里雪飘。望长城内外，惟余莽莽，大河上下，顿失滔滔。山舞银蛇，原驰蜡象，欲与天公试比高。须晴日，看红装素裹，分外妖娆.江山如此多娇，引无数英雄竞折腰。惜秦皇汉武，略输文采；唐宗宋祖，稍逊风骚。一代天骄，成吉思汗，只识弯弓射大雕。俱往矣，数风流人物，还看今朝。</p>
								<div class="shade_btn clearfix">
									<input type="checkbox">
									<img src="images/delett.png" class="delett"> 
								</div>
								<div class="shade_content">
									<div class="shade_li clearfix">
										<span>广告名称:</span>
										<span>未命名-01</span>
									</div>
									<div class="shade_li clearfix">
										<span>对齐方式:</span>
										<span>顶部对齐</span>
									</div> 
									<div class="shade_li clearfix">
										<span>字体:</span>
										<span>黑体</span>
									</div>
									<div class="shade_li clearfix">
										<span>字号:</span>
										<span>24px</span>
									</div>
									<div class="shade_li clearfix">
										<span>颜色</span>
										<img class="scroll_img" src="images/scrollImg1.png">
									</div>
									<div class="shade_li clearfix">
										<span>滚动速度:</span>
										<span>快</span>
									</div>
								</div>
							</li>-->
						</ul>
					</div>
				</div><!-- 滚动字幕 tabs_content end-->
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
			</div><!--tabs_wrap end-->
		</div><!--login_middle end-->
	</div><!--container end-->
	<!--横版广告详情弹出层-->
	<div class="alert_wrap alert_profile_wrap hori_alert">
		<div class="alert_content">
			<h3>广告详情<a href="#" class="close_btn"><img src="<%=ctx %>/images/close.png"></a></h3>
			<div class="alert_profile clearfix">
				<div class="alert_profile_left">
					<div class="alert_profile_img clearfix">
						<img src="<%=ctx %>/images/profileImg1.png"> 
					</div>
				</div>
				<div class="alert_profile_right">
					<h4>广告素材</h4>
					<div class="alert_profile_content">
						<ul>
							<!--<li>
								<div class="profile_img"><img src="images/profileImg1.png"></div>
								<span><em>00:00:50</em>后切换</span>
								<strong>1</strong>
							</li>-->
						</ul>
					</div>
				</div>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	<!--竖版广告详情弹出层-->
	<div class="alert_wrap alert_profile_wrap vert_alert">
		<div class="alert_content">
			<h3>广告详情<a href="#" class="close_btn"><img src="<%=ctx %>/images/close.png"></a></h3>
			<div class="alert_profile clearfix">
				<div class="alert_profile_left">
					<div class="alert_profile_img clearfix">
						<img src="<%=ctx %>/images/profileImg1.png"> 
					</div>
				</div>
				<div class="alert_profile_right">
					<h4>广告素材</h4>
					<div class="alert_profile_content">
						<ul>
							<!--<li>
								<div class="profile_img"><img src="images/profileImg1.png"></div>
								<span><em>00:00:50</em>后切换</span>
								<strong>1</strong>
							</li>-->
						</ul>
					</div>
				</div>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/zebra_datepicker.js"></script>
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
			$('#datepicker-start1').Zebra_DatePicker({
			direction: true,
			pair: $('#datepicker-end1')
			});
			$('#datepicker-end1').Zebra_DatePicker({
				direction: 1
			});	
			$('#datepicker-start2').Zebra_DatePicker({
			direction: true,
			pair: $('#datepicker-end2')
			});
			$('#datepicker-end2').Zebra_DatePicker({
				direction: 1
			});	
			//右侧高度
			var height = $(".login_middle").height($(window).height() - $(".login_top").height()) + "px";	
			$(".login_middle").css("height",height);
			
			var tabs_height = $(".login_middle").height() - $("h3").height() - 36 + "px";
			$(".tabs_wrap").css("height",tabs_height);
			
			var tabs_con_height = $(".tabs_wrap").outerHeight() - $(".tabs_btn").outerHeight();
			$(".tabs_content").css("height",tabs_con_height);
			
			var tabs_li_height = $(".tabs_content").height() - $(".search_wrap").height() - 118 + "px";
			$(".tabs_lis").css("height",tabs_li_height);
			
			//横版广告库li
			var horiLi = [{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx %>/images/hbImg1.png"
			},{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx %>/images/hbImg2.png"
			}];
			var hori_li_html = "";
			for(var i = 0; i < horiLi.length; i ++){
				hori_li_html += "<li>" +
								 "<img src='" + horiLi[i].path + "'>" +
								 "<div class='shade_btn clearfix'>" +
									"<input type='checkbox'>" +
									"<img src='<%=ctx %>/images/delett.png' class='delett'>" + 
								 "</div>" +
								 "<em>编辑中</em>" +
								 "<div class='shade_content clearfix'>" +
								 	"<div class='shade_li'>" +
								 		"<span>广告名称:</span>" +
										"<span>" + horiLi[i].name + "</span>" +
									"</div>" +
									"<div class='shade_li'>" +
								 		"<span>分辨率:</span>" +
										"<span>" + horiLi[i].RP + "</span>" +
									"</div>" +
									"<div class='shade_li'>" +
								 		"<span>上次编辑时间:</span>" +
										"<span>" + horiLi[i].time + "</span>" +
									"</div>" +
									"<div class='shade_li'>" +
								 		"<span>编辑人:</span>" +
										"<span>" + horiLi[i].user + "</span>" +
									"</div>" +
									 "<a class='more_white' href='" + horiLi[i].url + "'><img src='<%=ctx %>/images/moreWhite.png'></a>" +
								 "</div>" +
						   "</li>";
			}
			$(".hori_li ul").html(hori_li_html);
			
			//竖版广告库li
			var vertLi = [{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx %>/images/sbImg1.png"
			},{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx %>/images/sbImg2.png"
			}];
			var vert_li_html = "";
			for(var i = 0; i < vertLi.length; i ++){
				vert_li_html += "<li>" +
								 "<img src='" + vertLi[i].path + "'>" +
								 "<div class='shade_btn clearfix'>" +
									"<input type='checkbox'>" +
									"<img src='<%=ctx %>/images/delett.png' class='delett'>" + 
								 "</div>" +
								 "<em>编辑中</em>" +
								 "<div class='shade_content clearfix'>" +
								 	"<div class='shade_li'>" +
								 		"<span>广告名称:</span>" +
										"<span>" + vertLi[i].name + "</span>" +
									"</div>" +
									"<div class='shade_li'>" +
								 		"<span>分辨率:</span>" +
										"<span>" + vertLi[i].RP + "</span>" +
									"</div>" +
									"<div class='shade_li'>" +
								 		"<span>上次编辑时间:</span>" +
										"<span>" + vertLi[i].time + "</span>" +
									"</div>" +
									"<div class='shade_li'>" +
								 		"<span>编辑人:</span>" +
										"<span>" + vertLi[i].user + "</span>" +
									"</div>" +
									 "<a class='more_white' href='" + vertLi[i].url + "'><img src='<%=ctx %>/images/moreWhite.png'></a>" +
								 "</div>" +
						   "</li>";
			}
			$(".vert_li ul").html(vert_li_html);
			
			//滚动字幕li
			var scrollLi = [{
				"content":"北国风光，千里冰封，万里雪飘",
				"name":"未命名-01", 
				"align":"顶部对齐", 
				"family":"黑体",
				"size":"24px",
				"path":"<%=ctx %>/images/scrollImg1.png",
				"speed":"快"
			},{
				"content":"北国风光，千里冰封，万里雪飘。",
				"name":"未命名-01", 
				"align":"顶部对齐", 
				"family":"黑体",
				"size":"24px",
				"path":"<%=ctx %>/images/scrollImg1.png",
				"speed":"快"
			}];
			var scroll_li_html = "";
			for(var i = 0; i < scrollLi.length; i ++){
				scroll_li_html += "<li>" +
								 "<p>" + scrollLi[i].content + "</p>" +
								 "<div class='shade_btn clearfix'>" +
									"<input type='checkbox'>" +
									"<img src='<%=ctx %>/images/delett.png' class='delett'>" + 
								 "</div>" +
								 "<div class='shade_content'>" +
								 	"<div class='shade_li clearfix'>" +
								 		"<span>广告名称:</span>" +
										"<span>" + scrollLi[i].name + "</span>" +
									"</div>" +
									"<div class='shade_li clearfix'>" +
								 		"<span>对齐方式:</span>" +
										"<span>" + scrollLi[i].align + "</span>" +
									"</div>" +
									"<div class='shade_li clearfix'>" +
								 		"<span>字体:</span>" +
										"<span>" + scrollLi[i].family + "</span>" +
									"</div>" +
									"<div class='shade_li clearfix'>" +
								 		"<span>字号:</span>" +
										"<span>" + scrollLi[i].size + "</span>" +
									"</div>" +
									"<div class='shade_li clearfix'>" +
								 		"<span>颜色:</span>" +
										"<img class='scroll_img' src='" + scrollLi[i].path + "'>" +
									"</div>" +
									 "<div class='shade_li clearfix'>" +
								 		"<span>滚动速度:</span>" +
										"<span>" + scrollLi[i].speed + "</span>" +
									"</div>" +
								 "</div>" +
						   "</li>";
			}
			$(".scroll_li ul").html(scroll_li_html);
			
			//tab切换
			$(".tabs_btn a").click(function(e){
				$(this).addClass("active").siblings().removeClass("active");
				$(".tabs_content").eq($(this).index()).addClass("active").siblings().removeClass("active");
			});
			
			//广告列表鼠标移上移除的效果
			$(".tabs_lis li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});
			
			//点击编辑按钮，出现checkbox和删除按钮
			$(".editt_btn").click(function(e) {
				if($(".all_select_wrap").css("display")=="none"){
					$(".shade_btn").show(500);
					$(".all_select_wrap").show(500);
					var tabs_li_height = $(".tabs_content").height() - $(".search_wrap").outerHeight() - 160 + "px";
					$(".tabs_lis").css("height",tabs_li_height);
				}else{
					
					$(".shade_btn").hide(500);
					$(".all_select_wrap").hide(500);
					var tabs_li_height = $(".tabs_content").height() - $(".search_wrap").outerHeight() - 118 + "px";
					$(".tabs_lis").css("height",tabs_li_height);
				}
			});
			
			//全选
			$(".all_select_wrap input").click(function(e){
				if($(".all_select_wrap input").is(":checked")){
					$(".shade_btn input").attr("checked",true);
				}else{
					$(".shade_btn input").removeAttr("checked");
				}
			});
			
			//点击当前删除按钮，删除当前li
			$(".delett").click(function(e) {
				$(this).parent().parent().hide(500);
			});
			
			//弹出层广告详情右侧li
			var profileLi = [{
				"time":"00:00:50", 
				"number":"01", 
				"path":"<%=ctx %>/images/profileImg1.png"
			},{
				"time":"00:00:50", 
				"number":"02", 
				"path":"<%=ctx %>/images/profileImg1.png"
			},{
				"time":"00:00:50", 
				"number":"03", 
				"path":"<%=ctx %>/images/profileImg1.png"
			},{
				"time":"00:00:50", 
				"number":"04", 
				"path":"<%=ctx %>/images/profileImg1.png"
			}];
			var profile_li_html = "";
			for(var i = 0; i < profileLi.length; i ++){
				profile_li_html += "<li>" +
								 		"<div class='profile_img'><img src='" + profileLi[i].path + "'></div>" +
										"<span><em>" + profileLi[i].time + "</em>后切换</span>" +
										"<strong>" + profileLi[i].number + "</strong>" +
						           "</li>";
			}
			$(".alert_profile_content ul").html(profile_li_html);
			
			//点击查看更多详情按钮，显示详情弹出层--横版广告
			$(".hori_li .more_white").click(function(e) {
				$(".hori_alert").fadeIn(500);
			});
			
			//点击关闭按钮，隐藏详情弹出层--横版广告
			$(".hori_alert .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
			
			//点击查看更多详情按钮，显示详情弹出层--竖版广告
			$(".vert_li .more_white").click(function(e) {
				$(".vert_alert").fadeIn(500);
			});
			
			//点击关闭按钮，隐藏详情弹出层--竖版广告
			$(".vert_alert .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
			
			
		})
	</script>
</body>
</html>
