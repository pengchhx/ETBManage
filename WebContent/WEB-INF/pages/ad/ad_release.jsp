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
<style type="text/css">
.zzbj_left .edit_content,.sc_content{
	display:none;
	}
</style>
</head>

<body class="light">
	<div class="container">
		<!-- <div class="login_top">
			<div class="logo"><img src="<%=ctx%>/images/blueLogo.png"></div>
			<div class="message_box clearfix">
				<span>admin</span>
				<span>(0)</span>
				<span>退出</span>
			</div>
		</div><!--login_top end-->
		<%@include file="/top.jsp" %>
		<div class="login_left">
			<ul>
				<li class="li1"><a href="<%=ctx%>/ad/adPackage!toHbTemplateChoose.do">制作广告</a></li>
				<li class="li2 active"><a href="<%=ctx%>/ad/adPackage!toReleaseAd.do">发布广告</a></li>
				<!--<li class="li3 active"><a href="#">发布审核</a></li>-->
				<li class="li4"><a href="<%=ctx%>/ad/adPackage!toReleaseRecord.do">发布记录<span>未通过审核10</span></a></li>
				<li class="li5"><a href="<%=ctx%>/ad/adPackage!toAdList.do">广告库</a></li>
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
						<a href="javascript:void(0)" class="blue_btn active" onclick="stepOne()">下一步</a>
					</div>
				</div>
				<div class="tabs_wrap making_wrap release_wrap no_border clearfix">
					<div class="edit_wrap2 templet_wrap zzbj_left">
						<h3>发布属性</h3>
						<p id="set_alert_info" style="color: #adbdde;font-size: 18px;margin-left: 75px;margin-top: 200px;">添加广告后才可设置发布属性</p>
						<div class="edit_content">
							<form action="">
								<div class="form_group">
									<h4>开始:</h4>
									<strong class="strong1 clearfix"><input type="radio" name="startPlay" checked="true"><label for="">立即播放</label></strong>
									<strong class="strong2 clearfix">
										<em><input type="radio" name="playType" value="1" checked="true"><label for="">覆盖已有广告</label></em>
										<em><input type="radio" value="2" name="playType"><label for="">原有广告顺延</label></em>
									</strong>
									<strong class="strong3 clearfix">
										<input type="radio" name="startPlay">
										<input id="datepicker-start" type="text" class="data_input">
										<select id="startTimeH">
										</select>
										<select id="startTimeM">
										</select>
									</strong>
								</div>
								<div class="form_group">
									<h4>失效:</h4>
									<strong class="strong4 clearfix">
										<input id="datepicker-end" type="text" class="data_input">
										<select id="endTimeH">
										</select>
										<select id="endTimeM">
										</select>
									</strong>
								</div>
							</form>
						</div><!--edit_content end-->
					</div><!--左侧-->
					<div class="edit_wrap sc_wrap zzbj_right">
						<h3>广告列表</h3>
						<div class="edit_content">
							<div class="dirc_btns">
								<div class="dirc_btns_con clearfix">
									<span><input type="checkbox" id="loopType" checked>循环播放</span>
									<span class="down_btn clearfix"><img src="<%=ctx%>/images/down.png" width="20" height="20"><em>前移</em></span>
									<span class="up_btn clearfix"><img src="<%=ctx%>/images/up.png" width="20" height="20"><em>后移</em></span>
									<span class="del_btn clearfix"><img src="<%=ctx%>/images/del.png" width="18" height="20"></span>
								</div>
							</div>
							<div class="add_icon"><img src="<%=ctx%>/images/addIcon.png" width="30" height="30"></div>
							<div class="sc_content tabs_lis hori_li">
								<ul class="clearfix">

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
									<select id="provinceList">
										<option value="0">--全部--</option>
										<s:iterator value="provinces" id="province" status="st">
											<option value="<s:property value='areaCode' />">
												<s:property value="areaName" />
											</option>
										</s:iterator>
									</select>
									<select id="cityList">
										<option value="0">--全部--</option>
									</select>
									<select id="countyList">
										<option value="0">--全部--</option>
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
	
	<!--添加广告弹出层-->
	<div class="alert_wrap alert_profile_wrap scroll_alert add_ad_alert">
		<div class="alert_content">
			<h3>添加广告<a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
			<div class="tab_btn"><span class="active">横版广告</span><span>竖版广告</span><span>滚动字幕</span></div>
			<div class="tabs_scroll_content active">
				<div class="scroll_content">
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix">
								<select name="" id="">
									<option value="">分辨率</option>
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
							<div class="first_con hb_first_con">
								<ul class="clearfix">
									<!--<li class="second_li">
										<img src="images/sbImg1.png">
										<div class="shade_content">
											<div class="shade_li clearfix">
												<span>广告名称:</span>
												<span>未命名-01</span>
											</div>
											<div class="shade_li clearfix">
												<span>分辨率:</span>
												<span>1000*600px</span>
											</div> 
											<div class="shade_li clearfix">
												<span>上次编辑时间:</span>
												<span>2017.01.03 12:30</span>
											</div>
											<div class="shade_li clearfix">
												<span>编辑人:</span>
												<span>周韵</span>
											</div>
											<a href="#" class="more_white"><img src="images/moreWhite.png"></a>
										</div>
									</li>-->
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
					<!-- <a href="releaseAdhb.html" class="blue_btn">下一步</a> -->
					<a href="javascript:void(0)" class="blue_btn" onclick="selectPushHbAd()">确定</a>
					<span class="grey_btn" onclick="$('.scroll_alert .close_btn').click()">取消</span>
				</div>
			</div><!--横版广告-->
			
			<div class="tabs_scroll_content">
				<div class="scroll_content sb_scroll_content">
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix">
								<select name="" id="">
									<option value="">分辨率</option>
								</select>
							</strong>
							<strong class="clearfix">
								<label for="">编辑完成时间:</label>
								<input id="datepicker-start1" type="text"><em>-</em><input id="datepicker-end1" type="text">
							</strong>
						</form>
					</div><!--search_wrap end-->
					<div class="scroll_lis">
						<div class="first_li">
							<h4>2017-02-08</h4>
							<div class="first_con sb_first_con">
								<ul class="clearfix">
									<!--<li class="second_li">
										<img src="images/sbImg1.png">
										<div class="shade_content">
											<div class="shade_li clearfix">
												<span>广告名称:</span>
												<span>未命名-01</span>
											</div>
											<div class="shade_li clearfix">
												<span>分辨率:</span>
												<span>1000*600px</span>
											</div> 
											<div class="shade_li clearfix">
												<span>上次编辑时间:</span>
												<span>2017.01.03 12:30</span>
											</div>
											<div class="shade_li clearfix">
												<span>编辑人:</span>
												<span>周韵</span>
											</div>
											<a href="#" class="more_white"><img src="images/moreWhite.png"></a>
										</div>
									</li>-->
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
					<!-- <a href="releaseAdsb.html" class="blue_btn">下一步</a> -->
					<a href="#" class="blue_btn">确定</a>
					<span class="grey_btn">取消</span>
				</div>
			</div><!--竖版广告-->

			<div class="tabs_scroll_content">
				<div class="scroll_content text_scroll_content">
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix">
								<select name="" id="">
									<option value="">对齐方式</option>
								</select>
							</strong>
							<strong class="clearfix">
								<label for="">编辑完成时间:</label>
								<input id="datepicker-start2" type="text"><em>-</em><input id="datepicker-end2" type="text">
							</strong>
						</form>
					</div><!--search_wrap end-->
					<div class="scroll_lis">
						<div class="first_li">
							<h4>2017-02-08</h4>
							<div class="first_con text_first_con">
								<ul class="clearfix">
									<!--<li class="second_li">
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
					<!-- <a href="releaseAdtext.html" class="blue_btn">下一步</a> -->
					<a href="#" class="blue_btn">确定</a>
					<span class="grey_btn">取消</span>
				</div>
			</div><!--滚动字幕-->
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	
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

	<script type="text/javascript" src="<%=ctx%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=ctx%>/js/zebra_datepicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var adPackageResolution;
			$('#step_two').hide();
			$('#step_three').hide();

			$("input[name='startPlay']").change(function(){
				if($($("input[name='startPlay']")[0]).is(":checked")){
					$("input[name='playType']")[0].checked = true;
					$("input[name='playType']")[1].checked = false;
				}else{
					$("input[name='playType']")[0].checked = false;
					$("input[name='playType']")[1].checked = false;
				}
			});

			//设置时间选项值
			var time_h_html = "";
			var time_m_html = "";
			for(var i = 0;i < 24;i ++){
				if(i < 10){
					time_h_html += "<option value='" + "0" + i + "'>" + "0" + i + "</option>";
				}else{
					time_h_html += "<option value='" + i + "'>" + i + "</option>";
				}
			}
			for(var i = 0;i < 60;i ++){
				if(i < 10){
					time_m_html += "<option value='" + "0" + i + "'>" + "0" + i + "</option>";
				}else{
					time_m_html += "<option value='" + i + "'>" + i + "</option>";
				}
			}
			$("#startTimeH").html(time_h_html);
			$("#startTimeM").html(time_m_html);
			$("#endTimeH").html(time_h_html);
			$("#endTimeM").html(time_m_html);

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

			//添加广告--横版广告列表
			/*var horiLi = [{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"startTime":"00:00:50",
				"endTime":"00:00:50",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx%>/images/hbImg1.png"
			},{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"startTime":"00:00:50",
				"endTime":"00:00:50",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx%>/images/hbImg2.png"
			}];
			var hori_li_html = "";
			for(var i = 0; i < horiLi.length; i ++){
				hori_li_html += "<li>" +
								 "<div class='li_img'><img src='" + horiLi[i].path + "'></div>" +
								 "<div class='time_wrap'>" +
								 "<div class='time_wrap1 clearfix'><span>开始:</span><strong class='clearfix'><b>" + horiLi[i].startTime + "</b><em class='time_h'></em><em class='time_m'></em><em class='time_s'></em></strong></div>" +
								 "<div class='time_wrap1 time_wrap2 clearfix'><span>结束:</span><strong class='clearfix'><b>" + horiLi[i].endTime + "</b><em class='time_h'></em><em class='time_m'></em><em class='time_s'></em></strong></div></div>" +
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
									 "<a class='more_white' href='" + horiLi[i].url + "'><img src='<%=ctx%>/images/moreWhite.png'></a>" +
								 "</div>" +
						   "</li>";
			}
			$(".hori_li ul").html(hori_li_html);*/
			
			//广告列表鼠标移上移除的效果
			// $(".tabs_lis li").hover(function(e){
			// 	$(this).children(".shade_content").stop().toggle(500);
			// });
			
			//添加广告--横版广告
			/*var hbLi = [{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx%>/images/img1.png"
			},{
				
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx%>/images/img1.png"
			}];
			var hb_li_html = "";
			for(var i = 0; i < hbLi.length; i ++){
				hb_li_html += "<li class='second_li'>" +
										"<img src='" + hbLi[i].path + "'>" +
										 "<div class='shade_content'>" +
											"<div class='shade_li clearfix'>" +
												"<span>广告名称:</span>" +
												"<span>" + hbLi[i].name + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>分辨率:</span>" +
												"<span>" + hbLi[i].RP + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>上次编辑时间:</span>" +
												"<span>" + hbLi[i].time + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>编辑人:</span>" +
												"<span>" + hbLi[i].user + "</span>" +
											"</div>" +
											"<a class='more_white' href='" + hbLi[i].url + "'><img src='images/moreWhite.png'></a>" +
											 "</div>" +
											 "<input type='checkbox'>" +
						   		  "</li>";
			}
			$(".hb_first_con ul").html(hb_li_html);*/
			
			//添加广告--竖版广告
			var sbLi = [{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx%>/images/img1.png"
			},{
				
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx%>/images/img1.png"
			}];
			var sb_li_html = "";
			for(var i = 0; i < sbLi.length; i ++){
				sb_li_html += "<li class='second_li'>" +
										"<img src='" + sbLi[i].path + "'>" +
										 "<div class='shade_content'>" +
											"<div class='shade_li clearfix'>" +
												"<span>广告名称:</span>" +
												"<span>" + sbLi[i].name + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>分辨率:</span>" +
												"<span>" + sbLi[i].RP + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>上次编辑时间:</span>" +
												"<span>" + sbLi[i].time + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>编辑人:</span>" +
												"<span>" + sbLi[i].user + "</span>" +
											"</div>" +
											"<a class='more_white' href='" + sbLi[i].url + "'><img src='<%=ctx%>/images/moreWhite.png'></a>" +
											 "</div>" +
											 "<input type='checkbox'>" +
						   		  "</li>";
			}
			$(".sb_first_con ul").html(sb_li_html);
			
			//添加广--滚动字幕
			var textLi = [{
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
			var text_li_html = "";
			for(var i = 0; i < textLi.length; i ++){
				text_li_html += "<li class='second_li'>" +
										"<div class='second_con'>" + textLi[i].content + "</div>" +
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
										 "<input type='checkbox'>" +
						   		  "</li>";
			}
			$(".text_first_con ul").html(text_li_html);

			//弹出层广告详情右侧li
			var profileLi = [{
				"time":"00:00:50", 
				"number":"01", 
				"path":"<%=ctx%>/images/profileImg1.png"
			},{
				"time":"00:00:50", 
				"number":"02", 
				"path":"<%=ctx%>/images/profileImg2.png"
			},{
				"time":"00:00:50", 
				"number":"03", 
				"path":"<%=ctx%>/images/profileImg1.png"
			},{
				"time":"00:00:50", 
				"number":"04", 
				"path":"<%=ctx%>/images/profileImg2.png"
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
			
			//点击添加--添加广告弹出层
			$(".add_icon img").click(function(e) {
				$.ajax({
					url: "<%=ctx%>/ad/adPackage!getReleaseAdList.do",
					type: "POST",
					async: false,
					success: function(data){
						data = eval(data);
						
						var ad_list = data.ad_list;
						var hb_li_html = "";
						for(var i = 0;i < ad_list.length;i ++){
							var ad_map = ad_list[i];
							hb_li_html += "<div class='first_li'>";
							hb_li_html += "<h4>" + ad_map.time + "</h4>";
							hb_li_html += "<div class='first_con'><ul class='clearfix'>";

							var ad_info_list = ad_map.data_list;
							for(var j = 0;j < ad_info_list.length;j ++){
								hb_li_html += "<li class='second_li'>" +
										"<img src='" + ad_info_list[j].adPackageImage + "'>" +
										 "<div class='shade_content'>" +
											"<div class='shade_li clearfix'>" +
												"<span>广告名称:</span>" +
												"<span>" + ad_info_list[j].adPackageName + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>分辨率:</span>" +
												"<span>" + ad_info_list[j].adPackageRp + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>上次编辑时间:</span>" +
												"<span>" + ad_info_list[j].adPackageCreaterTime + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>编辑人:</span>" +
												"<span>" + ad_info_list[j].adPackageCreater + "</span>" +
											"</div>" +
											"<a class='more_white' href='javascript:void(0)'><img src='<%=ctx%>/images/moreWhite.png'></a>" + 
										 "</div>" +
										 "<input type='checkbox'>" +
										 "<input type='hidden' id='adPackageId' value='" + ad_info_list[j].adPackageId + "'>" +
										 "<input type='hidden' id='adPackageImage' value='" + ad_info_list[j].adPackageImage + "'>" +
										 "<input type='hidden' id='adPackageName' value='" + ad_info_list[j].adPackageName + "'>" +
										 "<input type='hidden' id='adPackageRp' value='" + ad_info_list[j].adPackageRp + "'>" +
										 "<input type='hidden' id='adPackageCreaterTime' value='" + ad_info_list[j].adPackageCreaterTime + "'>" +
										 "<input type='hidden' id='adPackageCreater' value='" + ad_info_list[j].adPackageCreater + "'>" +
						   		  "</li>";
							}

							hb_li_html += "</ul></div></div>";
						}

						$($(".scroll_lis")[0]).html(hb_li_html);

						//鼠标移上移除的效果
						$(".second_li").hover(function(e){
							$(this).children(".shade_content").stop().toggle(500);
						});
					}
				});
				
				$(".add_ad_alert").fadeIn(500);
			});
			
			//点击关闭按钮隐藏添加广告弹出层
			$(".scroll_alert .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
			
			//横板广告 竖版广告 滚动字幕 的tab切换
			$(".tab_btn span").click(function(e) {
				$(this).addClass("active").siblings().removeClass("active");
				$(".tabs_scroll_content").eq($(this).index()).addClass("active").siblings().removeClass("active");
			});
			
			//鼠标移上移除的效果
			/*$(".second_li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});*/
			
			//点击查看更多详情按钮，显示详情弹出层--横版广告
			// $(".hori_li .more_white").click(function(e) {
			// 	$(".hori_alert").fadeIn(500);
			// });
			
			//点击关闭按钮，隐藏详情弹出层--横版广告
			$(".hori_alert .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
			
			//添加广告后点击确定显示发布属性和广告列表
			// $(".blue_btn").click(function(e) {
			// 	$(".add_ad_alert").hide();
			// 	$(".zzbj_left .edit_content").show();
			// 	$(".sc_content").show();
			// });

			
			//广告详情右侧li点击切换左侧图片路径
			$(".profile_img").click(function(e) {
				var imgPath = $(this).children("img").attr("src");
				$(".alert_profile_img img").attr("src",imgPath);
			});
			
			//选中
			$(".sc_content .shade_content").click(function(e) {
				$(this).parent().addClass("active").siblings().removeClass("active");
			});

			//删除
			$(".del_btn").click(function(e) {
				if($(".sc_content li").hasClass("active")){
					$(".sc_content li.active").hide(500);
				}
			});
			
			//前移
			$(".down_btn").click(function(e) {
				if($(".sc_content li").hasClass("active")){
					$(".sc_content li.active").prev().before($(".sc_content li.active"));
				}
			});
			
			//后移
			$(".up_btn").click(function(e) {
				if($(".sc_content li").hasClass("active")){
					$(".sc_content li.active").next().after($(".sc_content li.active"));
				}
			});
			
		});
	
		function selectPushHbAd(){
			var hori_li_html = $(".hori_li ul").html();
			var isSelect = false;
			for(var i = 0;i < $(".scroll_lis li input[type='checkbox']").length;i ++){
				if($($(".scroll_lis li input[type='checkbox']")[i]).is(":checked")){
					isSelect = true;
					var adPackageId= $($(".scroll_lis li input[type='checkbox']")[i]).parent().find("#adPackageId").val();
					var adPackageImage = $($(".scroll_lis li input[type='checkbox']")[i]).parent().find("#adPackageImage").val();
					var adPackageName = $($(".scroll_lis li input[type='checkbox']")[i]).parent().find("#adPackageName").val();
					var adPackageRp = $($(".scroll_lis li input[type='checkbox']")[i]).parent().find("#adPackageRp").val();
					var adPackageCreaterTime = $($(".scroll_lis li input[type='checkbox']")[i]).parent().find("#adPackageCreaterTime").val();
					var adPackageCreater = $($(".scroll_lis li input[type='checkbox']")[i]).parent().find("#adPackageCreater").val();

					adPackageResolution = adPackageRp;
					hori_li_html += "<li>" +
								 "<div class='li_img'><img src='" + adPackageImage + "'></div>" +
								 //"<div class='time_wrap'>" +
								 //"<div class='time_wrap1 clearfix'><span>开始:</span><strong class='clearfix'><b>" + horiLi[i].startTime + "</b><em class='time_h'></em><em class='time_m'></em><em class='time_s'></em></strong></div>" +
								 //"<div class='time_wrap1 time_wrap2 clearfix'><span>结束:</span><strong class='clearfix'><b>" + horiLi[i].endTime + "</b><em class='time_h'></em><em class='time_m'></em><em class='time_s'></em></strong></div></div>" +
								 "<div class='shade_content clearfix'>" +
								 	"<div class='shade_li'>" +
								 		"<span>广告名称:</span>" +
										"<span>" + adPackageName + "</span>" +
									"</div>" +
									"<div class='shade_li'>" +
								 		"<span>分辨率:</span>" +
										"<span>" + adPackageRp + "</span>" +
									"</div>" +
									"<div class='shade_li'>" +
								 		"<span>上次编辑时间:</span>" +
										"<span>" + adPackageCreaterTime + "</span>" +
									"</div>" +
									"<div class='shade_li'>" +
								 		"<span>编辑人:</span>" +
										"<span>" + adPackageCreater + "</span>" +
									"</div>" +
									 "<a class='more_white' href='javascript:void(0)'><img src='<%=ctx%>/images/moreWhite.png'></a>" +
									 "<input type='hidden' id='adPackageId' value='" + adPackageId + "' />"
								 "</div>" +
						   "</li>";
				}
			}
			if(isSelect){
				$(".hori_li ul").html(hori_li_html);

				//广告列表鼠标移上移除的效果
				$(".tabs_lis li").hover(function(e){
					$(this).children(".shade_content").stop().toggle(500);
				});
				//点击查看更多详情按钮，显示详情弹出层--横版广告
				$(".hori_li .more_white").click(function(e) {
					$.ajax({
							url: "<%=ctx%>/ad/adPackage!getAdPackageResourceList.do?adPackageId=" + $(this).parent().parent().find("#adPackageId").val(),
							type: "POST",
							async: false,
							success: function(data){
								var profileLi = eval(data);
								var profile_li_html = "";
								for(var i = 0; i < profileLi.length; i ++){
									profile_li_html += "<li>" +
													 		"<div class='profile_img'><img src='" + profileLi[i].res_path + "'></div>" +
															"<span><em>" + profileLi[i].res_time + "</em>后切换</span>" +
															"<strong>" + profileLi[i].res_number + "</strong>" +
											           "</li>";
								}
								$(".alert_profile_content ul").html(profile_li_html);

								//广告详情右侧li点击切换左侧图片路径
								$(".profile_img").click(function(e) {
									var imgPath = $(this).children("img").attr("src");
									$(".alert_profile_img img").attr("src",imgPath);
								});
							}
						});

					$(".hori_alert").fadeIn(500);
				});
			}else{
				alert("请选择需要发布的广告");
				return;
			}

			$("#set_alert_info").hide();
			$(".add_ad_alert").hide();
			$(".zzbj_left .edit_content").show();
			$(".sc_content").show();
		}

		function stepOne(){
			var goToStepTwo = false;
			if($(".sc_content li").length == 0){
				alert("请选择需要发布的广告!");
				return false;
			}
			if($($("input[name='startPlay']")[1]).is(":checked")){
				if($("#datepicker-start").val().length == 0){
				alert("请设置广告的开始时间!");
				return false;
			}
			}
			if($("#datepicker-end").val().length == 0){
				alert("请设置广告的结束时间!");
				return false;
			}
			if(!$($("input[name='startPlay']")[0]).is(":checked")){
				var startTime = $("#datepicker-start").val() + " " + $("#startTimeH").val() + ":" + $("#startTimeM").val();
				var endTime = $("#datepicker-end").val() + " " + $("#endTimeH").val() + ":" + $("#endTimeM").val();
				$.ajax({
					url: "adPackage!checkTimeConflict.do?startTime=" + startTime + "&endTime=" + endTime,
					type: "PSOT",
					async: false,
					success: function(data){
						if(data.status == "true"){
							alert("你设置的广告时间有冲突，请重新设置，或选择立即播放");
							return false;
						}else{
							goToStepTwo = true;
						}
					}
				});
			}
			if(goToStepTwo){
				$.ajax({
					url: "<%=ctx%>/ad/adPackage!getDeviceList.do?pageNum=1&resolution=" + adPackageResolution,
					type: "POST",
					async: false,
					success: function(data){
						data = eval(data);
						var li = data.device;
						li = eval(li);

						var li_html = "";
						for(var i = 0; i < li.length; i ++){
							li_html += "<li>" +
											 "<h2 class='clearfix'><strong>" + li[i].device_name + "</strong><span><input type='checkbox'></span>" + 
											 "<input type='hidden' id='device_imei' value='" + li[i].device_imei + "'>" +
											 "</h2>" +
											 "<div>" + li[i].device_address + "</div>" +
											 "<div><a href='javascript:void(0)'><img src='<%=ctx%>/images/more.png'></a></div>" +
									   "</li>";
						}
						$(".equipment_lists ul").html(li_html);
					}
				});

				$("#step_one").hide();
				$("#step_two").show();
				$(".title").html("选择设备");
				$(".equip_wrap .pagenation").css("display","block");
			}
		}

		function restepOne(){


			$('#step_one') .show();
			$('#step_two').hide();
			$(".title").html("广告列表");
			$(".equip_wrap .pagenation").css("display","none");
		}

		function stepTwo(){
			var playType = "";
			var loopType = "";
			var startTime = "";
			var endTime = "";
			var device_imei_list = "";
			var adPackageId = "";

			if(!$(".equipment_lists input[type='checkbox']").is(":checked")){
				alert("请选择需要发布的设备！");
				return false;
			}
			for(var i = 0;i < $(".equipment_lists input[type='checkbox']").length; i ++){
				if($($(".equipment_lists input[type='checkbox']")[i]).is(":checked")){
					device_imei_list += $($(".equipment_lists input[type='checkbox']")[i]).parent().parent().find("#device_imei").val() + ",";
				}
			}
			for(var i = 0;i < $(".hori_li li #adPackageId").length;i ++){
				adPackageId += $($(".hori_li li #adPackageId")[i]).val() + ",";
			}

			if($($("input[name='startPlay']")[0]).is(":checked")){
				if($($("input[name='playType']")[0]).is(":checked")){
					playType = 1;
				}else{
					playType = 2;
				}
			}else{
				playType = 0;
				startTime = $("#datepicker-start").val() + " " + $("#startTimeH").val() + ":" + $("#startTimeM").val();
			}
			if($("#loopType").is(":checked")){
				loopType = 1;
			}else{
				loopType = 0;
			}
			endTime = $("#datepicker-end").val() + " " + $("#endTimeH").val() + ":" + $("#endTimeM").val();

			$.ajax({
				url: "adPackage!putPushAdInfo.do?adPackageId="+ adPackageId + "&playType=" + playType + "&loopType=" + loopType + "&startTime=" + startTime + "&endTime=" + endTime + "&device_imei_list=" + device_imei_list,
				type: "POST",
				async: false,
				success: function(data){
					if(data.status == "true"){
						$('#step_two').hide();
						$('#step_three').show();
						$(".title").css("display","none");
						$(".equip_wrap .pagenation").css("display","none");
					}
				}
			});
		}

		//根据省份获取城市
			$("#provinceList").change(function(){
				var areaCode = $("#provinceList").val();
				$.ajax({
					type : "post",
					cache : false,
					async: false,
					url : "adPackage!getDeviceList.do?pageNum=1&resolution=" + adPackageResolution,
					data : "areaCode=" + areaCode,
					success : function(data) {
						if(areaCode != "0"){
							data = eval(data);
							var city_list = data.city;
							var county_list = data.county;
							var device_list = data.device;
							city_list = eval(city_list);
							county_list = eval(county_list);
							device_list = eval(device_list);
							
							var html1 = "";
							var html2 = "";
							for(var i = 0;i < city_list.length;i ++){
								html1 += "<option value='" + city_list[i].areaCode + "'>" + city_list[i].areaName + "</option>";
							}
							for(var i = 0;i < county_list.length;i ++){
								html2 += "<option value='" + county_list[i].areaId + "'>" + county_list[i].areaName + "</option>";
							}
							var li_html = "";
							for(var i = 0; i < device_list.length; i ++){
								li_html += "<li>" +
												 "<h2 class='clearfix'><strong>" + device_list[i].device_name + "</strong><span><input type='checkbox'></span>" + 
												 "<input type='hidden' id='device_imei' value='" + device_list[i].device_imei + "'>" +
												 "</h2>" +
												 "<div>" + device_list[i].device_address + "</div>" +
												 "<div><a href='javascript:void(0)'><img src='<%=ctx%>/images/more.png'></a></div>" +
										   "</li>";
							}
							$(".equipment_lists ul").html(li_html);

							$("#cityList").html(html1);
							$("#countyList").html(html2);
						}else{
							data = eval(data);
							device_list = eval(device_list);

							var li_html = "";
							for(var i = 0; i < device_list.length; i ++){
								li_html += "<li>" +
												 "<h2 class='clearfix'><strong>" + device_list[i].device_name + "</strong><span><input type='checkbox'></span>" + 
												 "<input type='hidden' id='device_imei' value='" + device_list[i].device_imei + "'>" +
												 "</h2>" +
												 "<div>" + device_list[i].device_address + "</div>" +
												 "<div><a href='javascript:void(0)'><img src='<%=ctx%>/images/more.png'></a></div>" +
										   "</li>";
							}
							$(".equipment_lists ul").html(li_html);

							$("#cityList").html("<option value='0'>--全部--</option>");
							$("#countyList").html("<option value='0'>--全部--</option>");
						}
					}
				});
			});

			//根据城市获取地区
			$("#cityList").change(function(){
				var areaCode = $("#cityList").val();
				$.ajax({
					type : "post",
					cache : false,
					url : "adPackage!getDeviceList.do?pageNum=1&resolution=" + adPackageResolution,
					data : "areaCode=" + areaCode,
					success : function(data) {
						data = eval(data);
						var county_list = data.county;
						var device_list = data.device;
						county_list = eval(county_list);
						device_list = eval(device_list);
						
						var html1 = "";
						for(var i = 0;i < county_list.length;i ++){
							html1 += "<option value='" + county_list[i].areaId + "'>" + county_list[i].areaName + "</option>";
						}					

						$("#countyList").html(html1);
						var li_html = "";
						for(var i = 0; i < device_list.length; i ++){
							li_html += "<li>" +
											 "<h2 class='clearfix'><strong>" + device_list[i].device_name + "</strong><span><input type='checkbox'></span>" + 
											 "<input type='hidden' id='device_imei' value='" + device_list[i].device_imei + "'>" +
											 "</h2>" +
											 "<div>" + device_list[i].device_address + "</div>" +
											 "<div><a href='javascript:void(0)'><img src='<%=ctx%>/images/more.png'></a></div>" +
									   "</li>";
						}
						$(".equipment_lists ul").html(li_html);
					}
				});
			});
		
			//根据地区获取设备
			$("#countyList").change(function(){
				var areaId = $("#countyList").val();
				$.ajax({
					type : "post",
					cache : false,
					url : "adPackage!getDeviceList.do?pageNum=1&resolution=" + adPackageResolution,
					data : "area_Id=" + areaId,
					success : function(data) {
						data = eval(data);
						var device_list = data.device;
						device_list = eval(device_list);
						var li_html = "";
						for(var i = 0; i < device_list.length; i ++){
							li_html += "<li>" +
											 "<h2 class='clearfix'><strong>" + device_list[i].device_name + "</strong><span><input type='checkbox'></span>" + 
											 "<input type='hidden' id='device_imei' value='" + device_list[i].device_imei + "'>" +
											 "</h2>" +
											 "<div>" + device_list[i].device_address + "</div>" +
											 "<div><a href='javascript:void(0)'><img src='<%=ctx%>/images/more.png'></a></div>" +
									   "</li>";
						}
						$(".equipment_lists ul").html(li_html);
					}
				});
			});
	</script>
</body>
</html>
