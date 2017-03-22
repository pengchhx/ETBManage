<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%
	String ctx = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>制作广告</title>
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
				<li class="li1 active"><a href="<%=ctx%>/ad/adPackage!toHbTemplateChoose.do">制作广告</a></li>
				<li class="li2"><a href="<%=ctx%>/ad/adPackage!toReleaseAd.do">发布广告</a></li>
				<!--<li class="li3 active"><a href="#">发布审核</a></li>-->
				<li class="li4"><a href="<%=ctx%>/ad/adPackage!toReleaseRecord.do">发布记录<span>未通过审核10</span></a></li>
				<li class="li5"><a href="<%=ctx%>/ad/adPackage!toAdList.do">广告库</a></li>
				<!--<li class="li6"><a href="#">设备管理</a></li>-->
				<!--<li class="li7"><a href="#">权限管理</a></li>-->
			</ul>
		</div><!--login_left end-->
		<div class="login_middle">
			<h3 class="title">选择类型</h3>
			<div class="step_con active" id="step_one">
				<div class="steps_wrap clearfix">
					<div class="steps_li">
						<span id="step_li_one" class="step_li_one active">选择类型</span>
						<span id="step_li_two" class="step_li_two">制作字幕</span>
						<span id="step_li_three" class="step_li_three">编辑完成</span>
					</div>
					<div class="steps_btn">
						<span class="blue_btn active" onClick="stepOne()">下一步</span>
					</div>
				</div>
				<div class="tabs_wrap making_wrap">
					<div class="tabs_btn clearfix">
						<a href="<%=ctx%>/ad/adPackage!toHbTemplateChoose.do">横板广告</a>
						<a href="<%=ctx%>/ad/adPackage!toSbTemplateChoose.do">竖版广告</a>
						<a href="<%=ctx%>/ad/adPackage!toScrollTemplateChoose.do" class="active">滚动字幕</a>
					</div><!--tabs_btn end-->
					<div class="tabs_content active">
						<div class="search_wrap clearfix">
							<a href="#" class="ggk_btn">广告库</a>
						</div><!--search_wrap end-->
						<div class="tabs_lis vert_li scro_li">
							<ul class="clearfix">
								<!--<li>
										<div class="tabs_con"><span>读万卷书，行万里路</span></div>
										<p class="align_tit">底部对齐</p>
										<div class="shade_content">
											<img class="ok_icon" src="images/ok.png">
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
			</div><!--选择类型 end-->
			<div class="step_con" id="step_two">
				<div class="steps_wrap clearfix">
					<div class="steps_li">
						<span id="step_li_one" class="step_li_one">选择类型</span>
						<span id="step_li_two" class="step_li_two active">制作字幕</span>
						<span id="step_li_three" class="step_li_three">编辑完成</span>
					</div>
					<div class="steps_btn">
						<span class="blue_btn active">保存</span>
						<span class="blue_btn active" onClick="restepOne()">上一步</span>
						<span class="blue_btn active" onClick="stepTwo()">下一步</span>
					</div>
				</div>
				<div class="tabs_wrap making_wrap no_border clearfix">
					<div class="zj_wrap">
						<h3>组件信息</h3>
						<div class="zj_content">
							<h4>组件参数</h4>
							<form action="">
								<div class="form_group clearfix">
									<label for="">字体:</label>
									<select name="" id="">
										<option value="黑体">黑体</option>
									</select>
								</div>
								<div class="form_group clearfix">
									<label for="">字号:</label>
									<select name="" id="">
										<option value="24">24</option>
									</select>
								</div>
								<div class="form_group clearfix">
									<label for="">颜色:</label>
									<span></span>
									<em><img src="/colors.png" width="30" height="30"></em>
								</div>
								<div class="form_group clearfix">
									<label for="">滚动速度:</label>
									<select name="" id="">
										<option value="快">快</option>
									</select>
								</div>
							</form>
						</div>
					</div>
					<div class="preview_wrap">
						<h3>预览</h3>
						<div class="preview_content">
							<div class="preview_con">
								<span>2017元旦放假1-3号</span>
							</div>
						</div>
					</div>
					<div class="edit_wrap editt_wrap">
						<h3>编辑</h3>
						<div class="edit_content">
							<h4>输入滚动字幕内容:</h4>
							<div class="edit_con">
								<textarea name="" id="" cols="30" rows="10"></textarea>
							</div>
							<span class="blue_btn">预览</span>
						</div>
					</div>
				</div><!--tabs_wrap end-->
			</div><!--制作字幕 end-->
			<div class="step_con" id="step_three">
				<div class="steps_wrap clearfix">
					<div class="steps_li">
						<span id="step_li_one" class="step_li_one">选择类型</span>
						<span id="step_li_two" class="step_li_two">制作字幕</span>
						<span id="step_li_three" class="step_li_three active">编辑完成</span>
					</div>
				</div>
				<div class="tabs_wrap making_wrap text_end_wrap no_border clearfix">
					<div class="edit_left">
						<div class="preview_con">
							<span>2017元旦放假1-3号</span>
						</div>
						<div class="form_group">
							<span>广告名称:</span>
							<input type="text" value="未命名-01">
						</div>
					</div>
					<div class="edit_right">
						<ul>
							<li class="clearfix"><a href="releaseAdtext.html"><span></span><em>发布这个广告</em></a></li>
							<li class="clearfix"><span></span><em>保存，但不发布</em></li>
							<li class="clearfix"><span></span><em>放弃这个广告</em></li>
						</ul>
					</div>
				</div><!--tabs_wrap end-->
			</div><!--编辑完成 end-->
		</div><!--login_middle end-->
	</div><!--container end-->
	<!--滚动字幕广告库弹出层-->
	<div class="alert_wrap alert_profile_wrap scroll_alert text_alert">
		<div class="alert_content">
			<h3>滚动字幕<a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
			<div class="scroll_content">
				<div class="search_wrap clearfix">
					<form action="">
						<strong class="clearfix">
							<select name="" id="">
								<option value="">对齐方式</option>
							</select>
						</strong>
						<strong class="clearfix">
							<label for="">编辑完成时间:</label>
							<input id="datepicker-start" type="text"><em>-</em><input id="datepicker-end" type="text">
						</strong>
					</form>
				</div><!--search_wrap end-->
				<div class="scroll_lis">
					<div class="first_li">
						<h4>2017-02-08</h4>
						<div class="first_con">
							<ul class="clearfix">
								<li class="second_li">
									<div class="second_con">北国风光，千里冰封，万里雪飘。</div>
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
											<img class="scroll_img" src="<%=ctx%>/images/scrollImg1.png">
										</div>
										<div class="shade_li clearfix">
											<span>滚动速度:</span>
											<span>快</span>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div class="first_li">
						<h4>2017-01-18</h4>
						<div class="first_con">
							<ul class="clearfix">
							</ul>
						</div>
					</div>
					<div class="first_li">
						<h4>2017-01-12</h4>
						<div class="first_con">
							<ul class="clearfix">
							</ul>
						</div>
					</div>
				</div>
			</div><!--scroll_content end-->
			<div class="scroll_bottom clearfix">
				<span class="blue_btn" onClick="stepNext()">下一步</span>
				<span class="grey_btn">取消</span>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
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
			$('#datepicker-start1').Zebra_DatePicker({
			direction: true,
			pair: $('#datepicker-end1')
			});
			$('#datepicker-end1').Zebra_DatePicker({
				direction: 1
			});	
			
			//右侧高度
			var height = $(".login_middle").height($(window).height() - $(".login_top").height()) + "px";	
			$(".login_middle").css("height",height);
			
			var tabs_height = $(".login_middle").height() - $("h3").height() - $(".steps_wrap").outerHeight() - 36 + "px";
			$(".making_wrap").css("height",tabs_height);
			
			var tabs_con_height = $(".tabs_wrap").outerHeight() - $(".tabs_btn").outerHeight();
			$(".tabs_content").css("height",tabs_con_height);
			
			var tabs_li_height = $(".tabs_content").height() - $(".search_wrap").outerHeight() - 108 + "px";
			$(".tabs_lis").css("height",tabs_li_height);

			var preview_content_height = $(".making_wrap").outerHeight() - $(".preview_wrap h3").outerHeight() - 90 + "px";
			$(".preview_content").css("height",preview_content_height);
			
			//滚动字幕li
			var vertLi = [{
				"content":"读万卷书，行万里路",
				"align":"底部对齐"
			},{
				"content":"读万卷书，行万里路",
				"align":"顶部对齐"
			},{
				"content":"读万卷书，行万里路",
				"align":"居中对齐"
			}];
			var vert_li_html = "";
			for(var i = 0; i < vertLi.length; i ++){
				vert_li_html += "<li>" +
									 "<div class='tabs_con'><span>" + vertLi[i].content + "</span></div>" +
								     "<div class='shade_content'>" +
									 	"<img class='ok_icon' src='<%=ctx%>/images/ok.png'>" +
									 "</div>" +
									 "<p class='align_tit'>" + vertLi[i].align + "</p>" +
						   		"</li>";
			}
			$(".vert_li ul").html(vert_li_html);
			
			//广告列表鼠标移上移除的效果
			$(".tabs_lis li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});
			
						
			//弹出层广告库详情
			var scrollLi = [{
				"content":"北国风光，千里冰封，万里雪飘",
				"name":"未命名-01", 
				"align":"顶部对齐", 
				"family":"黑体",
				"size":"24px",
				"path":"<%=ctx%>/images/scrollImg1.png",
				"speed":"快"
			},{
				"content":"北国风光，千里冰封，万里雪飘。",
				"name":"未命名-01", 
				"align":"顶部对齐", 
				"family":"黑体",
				"size":"24px",
				"path":"<%=ctx%>/images/scrollImg1.png",
				"speed":"快"
			}];
			var scroll_li_html = "";
			for(var i = 0; i < scrollLi.length; i ++){
				scroll_li_html += "<li class='second_li'>" +
										"<div class='second_con'>" + scrollLi[i].content + "</div>" +
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
			$(".first_con ul").html(scroll_li_html);
			
			//点击广告库显示滚动字幕弹出层
			$(".ggk_btn").click(function(e) {
				$(".scroll_alert").fadeIn(500);
			});
			
			//点击关闭按钮隐藏滚动字幕弹出层
			$(".scroll_alert .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
			
			//广告列表鼠标移上移除的效果
			$(".second_li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});
			
			//点击白色OK变成绿色
			$(".shade_content").click(function(e) {
				$(this).children("img").attr("src","images/greenOk.png");
			});
			
		});
		//步骤
			function stepOne(){
				var isSelected = false;
				for(var i = 0;i < $(".shade_content .ok_icon").length;i ++){
					if(!$($(".shade_content .ok_icon")[i]).is(":hidden")){
						isSelected = true;
					}
				}
				if(isSelected){
					$("#step_one").hide();
					$("#step_two").show();
					$(".title").html("制作布局");
				}else{
					alert("请选择一个类型");
				}
			}
			function stepTwo(){
				$('#step_two').hide();
				$('#step_three').show();
				$(".title").html("编辑完成");
			}
			function stepNext(){
				$("#step_one").hide();
				$("#step_two").show();
				$(".scroll_alert").hide();
				$(".title").html("制作字幕");
			}
			function restepOne(){
				$('#step_one') .show();
				$('#step_two').hide();
				$(".title").html("选择类型");
			}

	</script>
</body>
</html>
