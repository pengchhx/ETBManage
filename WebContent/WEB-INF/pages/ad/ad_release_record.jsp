<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%
	String ctx = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发布记录</title>
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
				<!--<li class="li3">发布审核</li>-->
				<li class="li4 active">发布记录</li>
				<!--<li class="li5">广告库</li>
				<li class="li6">设备管理</li>
				<li class="li7">权限管理</li>-->
			</ul>
		</div><!--login_left end-->
		<div class="login_middle">
			<h3>发布记录</h3>
			<div class="search_wrap clearfix">
				<form action="">
					<strong class="clearfix"><label for="">发布单号:</label><input type="text"></strong>
					<strong class="clearfix"><label for="">提交发布时间:</label><input id="datepicker-start" type="text" placeholder="起始时间"><em>-</em><input id="datepicker-end" type="text" placeholder="结束时间"></strong>
					<strong class="search_btn"><img src="<%=ctx%>/images/search.png"></strong>
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
							<th>审核状态</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<!--<tr>
							<td>LSJ42157</td>
							<td>周韵</td>
							<td>2016-2-10 12:20:24</td>
							<td>2016-3-10 12:20:24</td>
							<td>审核未通过</td>
							<td class="pre_td"><a href="#"><img src="<%=ctx%>/images/preview.png"></a></td>
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
	
	<!--横版广告发布详情弹出层-->
	<div class="alert_wrap scroll_alert record_alert record_hb_alert">
		<div class="alert_content">
			<div class="audit_wrap">
				<h3>发布详情<a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
				<div class="profile_tabs hb_profile_tabs clearfix">
					<span class="active">详细信息</span><span>终端列表</span><span>审核历史</span>
				</div>
				<div class="tabs_wrap tabs_wrap1 active no_border">
					<a href="releaseAdhb.html" class="blue_btn">重新编辑发布</a>
					<div class="prop_con li_prop_con">
						<h4 class="clearfix"><b></b><span>广告列表</span><strong><input type="checkbox" checked>循环播放</strong></h4>
					</div>
					<div class="tabs_content tabs_content1">
						<div class="tabs_lis hori_li">
							<ul class="clearfix">
								<!--<li>
									<div class="li_img"><img src="<%=ctx%>/images/sbImg1.png"></div>
									<div class="time_con"><span>13:30:00</span><em>-</em><span>14:30:00</span></div>
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
										<a href="#" class="more_white"><img src="<%=ctx%>/images/moreWhite.png"></a>
									</div>
								</li>-->
							</ul>
						</div>
					</div><!-- 详细信息 tabs_content end-->
					<div class="prop_con sta_prop_con">
						<h4 class="clearfix"><b></b><span>发布属性</span></h4>
						<div class="time_con clearfix"><span>开始时间</span><em>2016-12-30 14:20</em><span>失效时间</span><em>2017-12-30 14:20</em></div>
					</div>
				</div><!--tabs_wrap end-->
				<div class="tabs_wrap tabs_wrap1 equip_wrap no_border">
					<a href="releaseAdhb.html" class="blue_btn">重新编辑发布</a>
					<div class="tabs_content">
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
					</div><!--tabs_content end-->
				</div><!-- 终端列表 tabs_wrap end-->
				<div class="tabs_wrap tabs_wrap1 no_border">
					<div class="tabs_content">
						<div class="hosi_li hosi_li1">
							<h4 class="clearfix"><span></span><em>2016.05.06</em></h4>
							<p>审核通过</p>
							<div>审核人:周韵</div>
						</div>
						<div class="hosi_li hosi_li2">
							<h4 class="clearfix"><span></span><em>2016.02.10</em></h4>
							<p>审核未通过</p>
							<div>审核人:周韵</div>
							<div>原因:终端设备选择错误</div>
						</div>
					</div><!--tabs_content end-->
				</div><!-- 审核历史 tabs_wrap end-->
			</div><!--audit_wrap end-->
		</div><!--alert_content end-->
	</div><!--alert_wrap 横版广告 end-->
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
					<div class="alert_profile_content halert_profile_content">
						<ul>
							<!--<li>
								<div class="profile_img"><img src="<%=ctx%>/images/profileImg1.png"></div>
								<span><em>00:00:50</em>后切换</span>
								<strong>1</strong>
							</li>-->
						</ul>
					</div>
				</div>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	
	<!--竖版广告发布详情弹出层-->
	<div class="alert_wrap scroll_alert record_alert record_sb_alert">
		<div class="alert_content">
			<div class="audit_wrap">
				<h3>发布详情<a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
				<div class="profile_tabs sb_profile_tabs clearfix">
					<span class="active">详细信息</span><span>终端列表</span><span>审核历史</span>
				</div>
				<div class="tabs_wrap tabs_wrap2 active no_border">
					<a href="releaseAdsb.html" class="blue_btn">重新编辑发布</a>
					<div class="prop_con li_prop_con">
						<h4 class="clearfix"><b></b><span>广告列表</span><strong><input type="checkbox" checked>循环播放</strong></h4>
					</div>
					<div class="tabs_content tabs_content1">
						<div class="tabs_lis vert_li">
							<ul class="clearfix">
								<!--<li>
									<div class="li_img"><img src="images/sbImg1.png"></div>
									<div class="time_con"><span>13:30:00</span><em>-</em><span>14:30:00</span></div>
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
					<div class="prop_con sta_prop_con">
						<h4 class="clearfix"><b></b><span>发布属性</span></h4>
						<div class="time_con clearfix"><span>开始时间</span><em>2016-12-30 14:20</em><span>失效时间</span><em>2017-12-30 14:20</em></div>
					</div>
				</div><!--tabs_wrap end-->
				<div class="tabs_wrap tabs_wrap2 equip_wrap no_border">
					<a href="releaseAdsb.html" class="blue_btn">重新编辑发布</a>
					<div class="tabs_content">
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
					</div><!--tabs_content end-->
				</div><!-- 终端列表 tabs_wrap end-->
				<div class="tabs_wrap tabs_wrap2 no_border">
					<div class="tabs_content">
						<div class="hosi_li hosi_li1">
							<h4 class="clearfix"><span></span><em>2016.05.06</em></h4>
							<p>审核通过</p>
							<div>审核人:周韵</div>
						</div>
						<div class="hosi_li hosi_li2">
							<h4 class="clearfix"><span></span><em>2016.02.10</em></h4>
							<p>审核未通过</p>
							<div>审核人:周韵</div>
							<div>原因:终端设备选择错误</div>
						</div>
					</div><!--tabs_content end-->
				</div><!-- 审核历史 tabs_wrap end-->
			</div><!--audit_wrap end-->
		</div><!--alert_content end-->
	</div><!--alert_wrap 竖版广告 end-->
	<!--竖版广告详情弹出层-->
	<div class="alert_wrap alert_profile_wrap vert_alert">
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
					<div class="alert_profile_content salert_profile_content">
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
	
	<!--滚动字幕发布详情弹出层-->
	<div class="alert_wrap scroll_alert record_alert record_text_alert">
		<div class="alert_content">
			<div class="audit_wrap">
				<h3>发布详情<a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
				<div class="profile_tabs text_profile_tabs clearfix">
					<span class="active">详细信息</span><span>终端列表</span><span>审核历史</span>
				</div>
				<div class="tabs_wrap tabs_wrap3 active no_border">
					<a href="releaseAdsb.html" class="blue_btn">重新编辑发布</a>
					<div class="prop_con li_prop_con">
						<h4 class="clearfix"><b></b><span>广告列表</span><strong><input type="checkbox" checked>循环播放</strong></h4>
					</div>
					<div class="tabs_content tabs_content1">
						<div class="tabs_lis scroll_li">
							<ul class="clearfix">
								<!--<li>
									<div class="li_img"><img src="images/sbImg1.png"></div>
									<div class="time_con"><span>13:30:00</span><em>-</em><span>14:30:00</span></div>
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
									<a href="#" class="more_white"><img src="<%=ctx%>/images/moreWhite.png"></a>
								</div>
								</li>-->
							</ul>
						</div>
					</div><!-- 详细信息 tabs_content end-->
					<div class="prop_con sta_prop_con">
						<h4 class="clearfix"><b></b><span>发布属性</span></h4>
						<div class="time_con clearfix"><span>开始时间</span><em>2016-12-30 14:20</em><span>失效时间</span><em>2017-12-30 14:20</em></div>
					</div>
				</div><!--tabs_wrap end-->
				<div class="tabs_wrap tabs_wrap3 equip_wrap no_border">
					<a href="releaseAdsb.html" class="blue_btn">重新编辑发布</a>
					<div class="tabs_content">
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
					</div><!--tabs_content end-->
				</div><!-- 终端列表 tabs_wrap end-->
				<div class="tabs_wrap tabs_wrap3 no_border">
					<div class="tabs_content">
						<div class="hosi_li hosi_li1">
							<h4 class="clearfix"><span></span><em>2016.05.06</em></h4>
							<p>审核通过</p>
							<div>审核人:周韵</div>
						</div>
						<div class="hosi_li hosi_li2">
							<h4 class="clearfix"><span></span><em>2016.02.10</em></h4>
							<p>审核未通过</p>
							<div>审核人:周韵</div>
							<div>原因:终端设备选择错误</div>
						</div>
					</div><!--tabs_content end-->
				</div><!-- 审核历史 tabs_wrap end-->
			</div><!--audit_wrap end-->
		</div><!--alert_content end-->
	</div><!--alert_wrap 滚动字幕 end-->
	
	<div class="alert_wrap remove_alert_wrap">
		<div class="alert_content">
			<div class="close_btn_wrap"><a href="#" class="close_btn"><img src="images/close.png"></a></div>
			<p>确定撤销该广告</p>
			<div class="sure_btn">确定</div>
		</div>
	</div>
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
			//右侧高度
			var height = $(".login_middle").height($(window).height() - $(".login_top").height()) + "px";	
			$(".login_middle").css("height",height);
			
			var table_height = $(".login_middle").height() - $("h3").outerHeight() - $(".search_wrap").outerHeight() -108 + "px";
			$(".table_wrap").css("height",table_height);

			//td
			var tr = [{
				"numbers":"LSJ42157", 
				"state":"<%=ctx%>/images/recordNo.png", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
			    "url":"#",
				"path":"<%=ctx%>/images/preview.png"
			},{
				"numbers":"LSJ42157", 
				"state":"<%=ctx%>/images/recordYes.png", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
				"url":"#",
				"path":"<%=ctx%>/images/preview.png"
			},{
				"numbers":"LSJ42157", 
				"state":"<%=ctx%>/images/recording.png", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
				"url":"#",
				"path":"<%=ctx%>/images/preview.png"
			},{
				"numbers":"LSJ42157", 
				"state":"<%=ctx%>/images/unrecord.png", 
				"user":"周韵", 
				"startTime":"2016-2-10 12:20:24",
				"failureTime":"2016-3-10",
				"url":"#",
				"path":"<%=ctx%>/images/preview.png"
			}];
			var tr_html = "";
			for(var i = 0; i < tr.length; i ++){
				tr_html += "<tr>" +
								 "<td>" + tr[i].numbers + "</td>" +
								 "<td>" + tr[i].user + "</td>" +
								 "<td>" + tr[i].startTime + "</td>" +
								 "<td>" + tr[i].failureTime + "</td>" +
								 "<td><img src='" + tr[i].state + "'></td>" +
								 "<td><a href='" + tr[i].url + "'><img  class='remove_btn' src='images/remove.png'><img class='pre_td' src='" + tr[i].path + "'></a></td>" +
						   "</tr>";
			}
			$("#table tbody").html(tr_html);
			
			//横版广告库li
			var horiLi = [{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx%>/images/hbImg1.png",
				"startTime":"13:30:00",
				"endTime":"14:30:00",
				"number":"01"
			},{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx%>/images/hbImg2.png",
				"startTime":"13:30:00",
				"endTime":"14:30:00",
				"number":"02"
			}];
			var hori_li_html = "";
			for(var i = 0; i < horiLi.length; i ++){
				hori_li_html += "<li>" +
								"<span class='num'>" + horiLi[i].number + "</span>" +
								 "<div class='li_img'><img src='" + horiLi[i].path + "'></div>" +
								 "<div class='time_con'><span>" + horiLi[i].startTime + "</span><span>-</span><span>" + horiLi[i].endTime + "</span></div>" +
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
								 		"<span>提交发布时间:</span>" +
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
			$(".hori_li ul").html(hori_li_html);
			
			//弹出层广告详情右侧li
			var hprofileLi = [{
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
			var hprofile_li_html = "";
			for(var i = 0; i < hprofileLi.length; i ++){
				hprofile_li_html += "<li>" +
								 		"<div class='profile_img'><img src='" + hprofileLi[i].path + "'></div>" +
										"<span><em>" + hprofileLi[i].time + "</em>后切换</span>" +
										"<strong>" + hprofileLi[i].number + "</strong>" +
						           "</li>";
			}
			$(".halert_profile_content ul").html(hprofile_li_html);
			
			//终端列表li
			var hli = [{
				"title":"G45",
				"position":"江苏省栖霞区马群街道",
				"url":"#"
			},{
				"title":"G45",
				"position":"江苏省栖霞区马群街道",
				"url":"#"
			}];
			var hli_html = "";
			for(var i = 0; i < hli.length; i ++){
				hli_html += "<li>" +
								 "<h2 class='clearfix'><strong>" + hli[i].title + "</strong></h2>" +
								 "<div>" + hli[i].position + "</div>" +
								 "<div><a href='" + hli[i].url + "'><img src='<%=ctx%>/images/more.png'></a></div>" +
						   "</li>";
			}
			$(".equipment_lists ul").html(hli_html);
			
			//详细信息 终端列表 审核历史的tab切换
			$(".hb_profile_tabs span").click(function(e) {
				$(this).addClass("active").siblings().removeClass("active");
				$(".tabs_wrap1").eq($(this).index()).addClass("active").siblings().removeClass("active");
			});
			
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
/*==============================================横版广告end======================================*/			
			//竖版广告库li
			var vertLi = [{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx%>/images/sbImg1.png",
				"startTime":"13:30:00",
				"endTime":"14:30:00",
				"number":"01"
			},{
				"name":"未命名-01", 
				"RP":"1000*600px", 
				"time":"2017.01.03 12:30",
				"user":"周韵",
			    "url":"#",
				"path":"<%=ctx%>/images/sbImg2.png",
				"startTime":"13:30:00",
				"endTime":"14:30:00",
				"number":"02"
			}];
			var vert_li_html = "";
			for(var i = 0; i < vertLi.length; i ++){
				vert_li_html += "<li>" +
								"<span class='num'>" + vertLi[i].number + "</span>" +
								 "<div class='li_img'><img src='" + vertLi[i].path + "'></div>" +
								 "<div class='time_con'><span>" + vertLi[i].startTime + "</span><span>-</span><span>" + vertLi[i].endTime + "</span></div>" +
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
								 		"<span>提交发布时间:</span>" +
										"<span>" + vertLi[i].time + "</span>" +
									"</div>" +
									"<div class='shade_li'>" +
								 		"<span>编辑人:</span>" +
										"<span>" + vertLi[i].user + "</span>" +
									"</div>" +
									 "<a class='more_white' href='" + vertLi[i].url + "'><img src='<%=ctx%>/images/moreWhite.png'></a>" +
								 "</div>" +
						   "</li>";
			}
			$(".vert_li ul").html(vert_li_html);
			
			//弹出层广告详情右侧li
			var sprofileLi = [{
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
			var sprofile_li_html = "";
			for(var i = 0; i < sprofileLi.length; i ++){
				sprofile_li_html += "<li>" +
								 		"<div class='profile_img'><img src='" + sprofileLi[i].path + "'></div>" +
										"<span><em>" + sprofileLi[i].time + "</em>后切换</span>" +
										"<strong>" + sprofileLi[i].number + "</strong>" +
						           "</li>";
			}
			$(".salert_profile_content ul").html(sprofile_li_html);			
			
			//详细信息 终端列表 审核历史的tab切换
			$(".sb_profile_tabs span").click(function(e) {
				$(this).addClass("active").siblings().removeClass("active");
				$(".tabs_wrap2").eq($(this).index()).addClass("active").siblings().removeClass("active");
			});
			
			//鼠标移上移除的效果
			$(".vert_li li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});
			
			//点击查看更多详情按钮，显示详情弹出层--横版广告
			$(".vert_li .more_white").click(function(e) {
				$(".vert_alert").fadeIn(500);
			});
			
			//点击关闭按钮，隐藏详情弹出层--横版广告
			$(".vert_alert .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
/*==============================================竖版广告end======================================*/
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
			
			//详细信息 终端列表 审核历史的tab切换
			$(".text_profile_tabs span").click(function(e) {
				$(this).addClass("active").siblings().removeClass("active");
				$(".tabs_wrap3").eq($(this).index()).addClass("active").siblings().removeClass("active");
			});
			
			//鼠标移上移除的效果
			$(".scroll_li li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});
/*==============================================滚动字幕end======================================*/
			$(".pre_td").click(function(e) {
				$(".record_hb_alert").fadeIn(500);
			});
			$(".audit_wrap .close_btn").click(function(e) {
				$(this).parent().parent().parent().parent().fadeOut(500);
			});


			$(".remove_btn").click(function(e) {
				$(".remove_alert_wrap").fadeIn(500);
			});
			$(".remove_alert_wrap .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
			
		});
	</script>
</body>
</html>
