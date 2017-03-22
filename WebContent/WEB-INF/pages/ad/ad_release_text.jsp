<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%
	String ctx = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发布广告</title>
<link rel="stylesheet" href="<%=ctx%>/css/common.css">
<link rel="stylesheet" href="<%=ctx%>/css/calendar.css">
</head>

<body class="light">
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
				<li class="li1"><a href="hbAds.html">制作广告</a></li>
				<li class="li2 active"><a href="releaseAd.html">发布广告</a></li>
				<!--<li class="li3 active"><a href="#">发布审核</a></li>-->
				<li class="li4"><a href="#">发布记录<span>未通过审核10</span></a></li>
				<li class="li5"><a href="adLib.html">广告库</a></li>
				<!--<li class="li6"><a href="#">设备管理</a></li>-->
				<!--<li class="li7"><a href="#">权限管理</a></li>-->
			</ul>
		</div><!--login_left end-->
		<div class="login_middle">
			<h3 class="title">广告列表</h3>
			<div class="step_con active" id="step_one">
				<div class="steps_wrap clearfix">
					<div class="steps_li">
						<span id="step_li_one" class="step_li_one active">广告列表</span>
						<span id="step_li_two" class="step_li_two">选择设备</span>
						<span id="step_li_three" class="step_li_three">发布成功</span>
					</div>
					<div class="steps_btn">
						<span class="blue_btn active" onClick="stepOne()">下一步</span>
					</div>
				</div>
				<div class="tabs_wrap making_wrap release_wrap text_wrap no_border clearfix">
					<div class="edit_wrap2 templet_wrap zzbj_left">
						<h3>发布属性</h3>
						<div class="edit_content">
							<form action="">
								<div class="form_group">
									<h4>开始:</h4>
									<strong class="strong1 clearfix"><input type="radio" checked="true"><label for="">立即播放</label></strong>
									<strong class="strong2 clearfix">
										<em><input type="radio" checked="true"><label for="">覆盖已有广告</label></em>
										<em><input type="radio"><label for="">原有广告顺延</label></em>
									</strong>
									<strong class="strong3 clearfix"><input type="radio"><input id="datepicker-start" type="text" class="data_input"><select name="" id=""><option value="14">14</option></select><select name="" id=""><option value="30">30</option></select></strong>
								</div>
								<div class="form_group">
									<h4>失效:</h4>
									<strong class="strong4 clearfix"><input id="datepicker-end" type="text" class="data_input"><select name="" id=""><option value="14">14</option></select><select name="" id=""><option value="30">30</option></select></strong>
								</div>
							</form>
						</div><!--edit_content end-->
					</div><!--左侧-->
					<div class="edit_wrap sc_wrap zzbj_right">
						<h3>广告列表</h3>
						<div class="edit_content">
							<div class="dirc_btns">
								<div class="dirc_btns_con clearfix">
									<span><input type="checkbox" checked>循环播放</span>
									<span class="clearfix"><img src="<%=ctx%>/images/down.png" width="20" height="20"><em>前移</em></span>
									<span class="clearfix"><img src="<%=ctx%>/images/up.png" width="20" height="20"><em>后移</em></span>
									<span class="clearfix"><img src="<%=ctx%>/images/del.png" width="18" height="20"></span>
								</div>
							</div>
							<div class="add_icon"><img src="<%=ctx%>/images/addIcon.png" width="30" height="30"></div>
							<div class="sc_content tabs_lis hori_li scroll_li">
								<ul class="clearfix">
									<!--<li>
										<p class="scroll_content">北国风光，千里冰封，万里雪飘。望长城内外，惟余莽莽，大河上下，顿失滔滔。山舞银蛇，原驰蜡象，欲与天公试比高。须晴日，看红装素裹，分外妖娆.江山如此多娇，引无数英雄竞折腰。惜秦皇汉武，略输文采；唐宗宋祖，稍逊风骚。一代天骄，成吉思汗，只识弯弓射大雕。俱往矣，数风流人物，还看今朝。</p>
										<div class="time_wrap">
										<span>开始:</span><strong><b>00:00:50</b><em class="time_h"></em><em class="time_m"></em><em class="time_s"></em></strong>
										</div>
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
						</div><!--edit_content end-->
					</div><!--右侧-->
				</div><!--tabs_wrap end-->
			</div><!--广告列表 end-->
			<div class="step_con active" id="step_two">
				<div class="steps_wrap clearfix">
					<div class="steps_li">
						<span id="step_li_one" class="step_li_one">广告列表</span>
						<span id="step_li_two" class="step_li_two active">选择设备</span>
						<span id="step_li_three" class="step_li_three">发布成功</span>
					</div>
					<div class="steps_btn">
						<span class="blue_btn active" onClick="restepOne()">上一步</span>
						<span class="blue_btn active" onClick="stepTwo()">发布</span>
					</div>
				</div>
				<div class="tabs_wrap making_wrap release_wrap equip_wrap clearfix">
					<div class="tabs_btn clearfix">
						<a href="#">选择设备</a>
					</div><!--tabs_btn end-->
					<div class="tabs_content active">
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
						<div class="all_select_wrap">
							<span><input type="checkbox">全选</span>
						</div>
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
					</div>
				</div><!--tabs_wrap end-->
			</div><!--选择设备 end-->
			<div class="step_con active" id="step_three">
				<div class="tabs_wrap making_wrap release_wrap finish_wrap no_border clearfix">	
					<div class="finish_content">
						<div class="success_contetn">发布成功</div>
						<p>可到发布记录里查看审核状态</p>
					</div>
				</div><!--tabs_wrap end-->
			</div><!--发布成功 end-->
		</div><!--login_middle end-->
	</div><!--container end-->
	
	<!--横版广告详情弹出层-->
	<div class="alert_wrap alert_profile_wrap hori_alert">
		<div class="alert_content">
			<h3>广告详情<a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
			<div class="alert_profile clearfix">
				<div class="alert_profile_left">
					<div class="alert_profile_img clearfix">
						<img src="<%=ctx%>/images/profileImg1.png"> 
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
			
			var tabs_height = $(".login_middle").height() - $("h3").height() - $(".steps_wrap").outerHeight() - 36 + "px";
			$(".making_wrap").css("height",tabs_height);
			
			var tabs_con_height = $(".tabs_wrap").outerHeight() - $(".tabs_btn").outerHeight();
			$(".tabs_content").css("height",tabs_con_height);
			
			var tabs_li_height = $(".tabs_content").height() - $(".search_wrap").outerHeight() - $(".all_select_wrap").outerHeight() - 90 + "px";
			$(".equipment_lists").css("height",tabs_li_height);
			
			//广告列表鼠标移上移除的效果
			$(".tabs_lis li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});
			
			//添加广告--滚动字幕列表
			var textLi = [{
				"content":"北国风光，千里冰封，万里雪飘",
				"name":"未命名-01", 
				"align":"顶部对齐", 
				"family":"黑体",
				"size":"24px",
				"path":"<%=ctx%>/images/scrollImg1.png",
				"speed":"快",
				"startTime":"00:00:50",
				"endTime":"00:00:50",
			},{"content":"北国风光，千里冰封，万里雪飘。",
				"name":"未命名-01", 
				"align":"顶部对齐", 
				"family":"黑体",
				"size":"24px",
				"path":"<%=ctx%>/images/scrollImg1.png",
				"speed":"快",
				"startTime":"00:00:50",
				"endTime":"00:00:50",
			}];
			var text_li_html = "";
			for(var i = 0; i < textLi.length; i ++){
				text_li_html += "<li>" +
								 "<div class='li_img'><p>" + textLi[i].content + "</p></div>" +
								 "<div class='time_wrap'>" +
								 "<div class='time_wrap1 clearfix'><span>开始:</span><strong class='clearfix'><b>" + textLi[i].startTime + "</b><em class='time_h'></em><em class='time_m'></em><em class='time_s'></em></strong></div>" +
								 "<div class='time_wrap1 time_wrap2 clearfix'><span>结束:</span><strong class='clearfix'><b>" + textLi[i].endTime + "</b><em class='time_h'></em><em class='time_m'></em><em class='time_s'></em></strong></div></div>" +
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
			
			//弹出层广告详情右侧li
			var profileLi = [{
				"time":"00:00:50", 
				"number":"01", 
				"path":"<%=ctx%>/images/profileImg1.png"
			},{
				"time":"00:00:50", 
				"number":"02", 
				"path":"<%=ctx%>/images/profileImg1.png"
			},{
				"time":"00:00:50", 
				"number":"03", 
				"path":"<%=ctx%>/images/profileImg1.png"
			},{
				"time":"00:00:50", 
				"number":"04", 
				"path":"<%=ctx%>/images/profileImg1.png"
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
			
			//选择设备li
			var li = [{
				"title":"G45",
				"position":"江苏省栖霞区马群街道",
				"url":"#"
			},{
				"title":"G45",
				"position":"江苏省栖霞区马群街道",
				"url":"#"
			},{
				"title":"G45",
				"position":"江苏省栖霞区马群街道",
				"url":"#"
			},{
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
								 "<h2 class='clearfix'><strong>" + li[i].title + "</strong><span><input type='checkbox'></span></h2>" +
								 "<div>" + li[i].position + "</div>" +
								 "<div><a href='" + li[i].url + "'><img src='<%=ctx%>/images/more.png'></a></div>" +
						   "</li>";
			}
			$(".equipment_lists ul").html(li_html);
			
			//鼠标移上移除的效果
			$(".hori_li li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});
			
			//点击查看更多详情按钮，显示详情弹出层--横版广告
			$(".hori_li .more_white").click(function(e) {
				$(".hori_alert").fadeIn(500);
			});
			
			//点击关闭按钮，隐藏详情弹出层--横版广告
			$(".hori_alert .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
			
			//全选
			$(".all_select_wrap input").click(function(e){
				if($(".all_select_wrap input").is(":checked")){
					$(".equipment_lists input").attr("checked",true);
				}else{
					$(".equipment_lists input").removeAttr("checked");
				}
			});
		})
		//步骤
			function stepOne(){
				$("#step_one").hide();
				$("#step_two").show();
				$(".title").html("选择设备");
				$(".equip_wrap .pagenation").css("display","block");
			}
			function stepTwo(){
				$('#step_two').hide();
				$('#step_three').show();
				$(".title").css("display","none");
				$(".equip_wrap .pagenation").css("display","none");
			}
			function restepOne(){
				$('#step_one') .show();
				$('#step_two').hide();
				$(".title").html("广告列表");
				$(".equip_wrap .pagenation").css("display","none");
			}


	</script>
</body>
</html>
