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
<link rel="stylesheet" href="<%=ctx%>/css/jquery-ui.min.css">
<style type="text/css">
.sb_making_wrap .edit_wrap2 .edit_content .zzbj_content .zzbj_con .block_content .block_right{
	width:378px;
	height:235px;
	border:1px solid #bfbfbf;
	}
.sb_making_wrap .edit_wrap2 .edit_content .zzbj_content .zzbj_con .block_content .block_left .time_con{
	width:378px;
	height:113px;
	border:1px solid #bfbfbf;
	}
.sb_making_wrap .edit_wrap2 .edit_content .zzbj_content .zzbj_con .block_content .block_left .weather_con{
	width:338px;
	height:185px;
	border:1px solid #bfbfbf;
	}
.ui-widget-content{
	background-color:rgba(255,255,255,0);
	border:0;
	}
</style>
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
						<span id="step_li_two" class="step_li_two">制作布局</span>
						<span id="step_li_three" class="step_li_three">添加素材</span>
						<span id="step_li_four" class="step_li_four">编辑完成</span>
					</div>
					<div class="steps_btn">
						<span class="blue_btn active" onClick="stepOne()">下一步</span>
					</div>
				</div>
				<div class="tabs_wrap making_wrap">
					<div class="tabs_btn clearfix">
						<a href="<%=ctx%>/ad/adPackage!toHbTemplateChoose.do">横板广告</a>
						<a href="<%=ctx%>/ad/adPackage!toSbTemplateChoose.do" class="active">竖版广告</a>
						<a href="<%=ctx%>/ad/adPackage!toScrollTemplateChoose.do">滚动字幕</a>
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
							</form>
							<a href="#" class="have_link">从已有广告库选取</a>
						</div><!--search_wrap end-->
						<div class="tabs_lis vert_li">
							<ul class="clearfix">
								<!--<li>
									<img src="images/sbImg1.png">
									<div class="shade_content">
										<img class="ok_icon" src="images/ok.png">
									</div>
								</li>-->
							</ul>
						</div>
					</div><!-- 竖版广告 tabs_content end-->
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
						<span id="step_li_two" class="step_li_two active">制作布局</span>
						<span id="step_li_three" class="step_li_three">添加素材</span>
						<span id="step_li_four" class="step_li_four">编辑完成</span>
					</div>
					<div class="steps_btn">
						<span class="blue_btn active">保存</span>
						<span class="blue_btn active" onClick="restepOne()">上一步</span>
						<span class="blue_btn active" onClick="stepTwo()">下一步</span>
					</div>
				</div>
				<div class="tabs_wrap making_wrap sb_making_wrap no_border clearfix">
					<div class="zj_wrap zzbj_left">
						<div class="zj_wrapper">
							<h3>组件</h3>
							<ul>
								<li class="zj_li1 active">多媒体区块</li>
								<li class="zj_li2">日期</li>
								<li class="zj_li3">时间</li>
								<li class="zj_li4">天气</li>
								<li class="zj_li5">音乐</li>
							</ul>
						</div><!--组件-->
						<div class="zjxx_wrapper">
							<h3>组件信息</h3>
							<div class="zj_content">
								<h4>组件名称</h4>
								<div class="form_group">
									<input type="text" value="区块01" class="txt_name">
								</div>
								<h4>组件参数</h4>
								<form action="">
									<div class="form_group clearfix">
										<label for="">X:</label>
										<input type="text" class="txt_x">
										<b>Px</b>
									</div>
									<div class="form_group clearfix">
										<label for="">Y:</label>
										<input type="text" class="txt_y">
										<b>Px</b>
									</div>
									<div class="form_group clearfix">
										<label for="">W:</label>
										<input type="text" class="txt_w">
										<b>Px</b>
									</div>
									<div class="form_group clearfix">
										<label for="">H:</label>
										<input type="text" class="txt_h">
										<b>Px</b>
									</div>
								</form>
							</div>
						</div><!--组件信息-->
					</div><!--左侧-->
					<div class="edit_wrap edit_wrap2 zzbj_right block_box zzbj_edit">
						<h3>编辑</h3>
						<div class="edit_content">
							<div class="direction_btns clearfix">
								<span class="direc_btn1"></span>
								<span class="direc_btn2"></span>
								<span class="direc_btn3"></span>
								<span class="direc_btn4"></span>
								<span class="direc_btn5"></span>
								<span class="direc_btn6"></span>
								<span class="direc_btn7"></span>
								<span class="direc_btn8"></span>
								<span class="direc_btn9"></span>
								<span class="direc_btn10"></span>
								<span class="direc_btn11"></span>
								<span class="direc_btn12"></span>
							</div><!--方向按钮-->
							<div class="zzbj_content clearfix">
								<div class="zzbj_con">
									<!--<div class="music_btn"><img src="images/musicIcon.png" width="36" height="36"></div>-->
									<div class="block_content zzbj_block_content">
										<div class="block_right ui-widget-top ui-widget-content resizable1">区块01</div>
										<div class="block_left">
											<div class="time_con clearfix ui-widget-middle ui-widget-content resizable2">
												<span>2017-01-17</span>
												<em>14:20:45</em>
											</div>
											<div class="weather_con ui-widget-bottom ui-widget-content resizable3">
												<ul class="clearfix">
													<li>
														<span>今天</span>
														<span>8&deg;C/15&deg;C</span>
														<img src="<%=ctx%>/images/weather1.png" width="38" height="38"> 
													</li>
													<li>
														<span>明天</span>
														<span>4&deg;C/10&deg;C</span>
														<img src="<%=ctx%>/images/weather2.png" width="38" height="38"> 
													</li>
													<li>
														<span>后天</span>
														<span>6&deg;C/10&deg;C</span>
														<img src="<%=ctx%>/images/weather3.png" width="38" height="38"> 
													</li>
												</ul>
											</div>
										</div>
									</div>
								</div>
								<!-- <div class="zzbj_btns">
									<h4>已添加组件</h4>
									<div class="zzbj_btns_con">
										<span class="active">区块1</span>
										<span>日期</span>
										<span>时间</span>
										<span>天气</span>
										<span>音乐</span>
										<span>区块2</span>
									</div>
								</div> -->
							</div><!--内容-->
						</div>
					</div><!--右侧-->
					<div class="edit_wrap added_compon">
						<h3>已添加组件</h3>
						<div class="zzbj_btns_con">
							<span class="active">区块1</span>
							<span>日期</span>
							<span>时间</span>
							<span>天气</span>
							<span>音乐</span>
							<span>区块2</span>
						</div>
					</div><!--已添加组件 end-->
				</div><!--tabs_wrap end-->
			</div><!--制作布局 end-->
			<div class="step_con" id="step_three">
				<div class="steps_wrap clearfix">
					<div class="steps_li">
						<span id="step_li_one" class="step_li_one">选择类型</span>
						<span id="step_li_two" class="step_li_two">制作布局</span>
						<span id="step_li_three" class="step_li_three active">添加素材</span>
						<span id="step_li_four" class="step_li_four">编辑完成</span>
					</div>
					<div class="steps_btn">
						<span class="blue_btn active">保存</span>
						<span class="blue_btn active" onClick="restepTwo()">上一步</span>
						<span class="blue_btn active" onClick="stepThree()">下一步</span>
					</div>
				</div>
				<div class="tabs_wrap making_wrap sb_making_wrap no_border clearfix">
					<div class="edit_wrap2 templet_wrap zzbj_left">
						<h3>模板信息</h3>
						<div class="edit_content">
							<div class="preview_icon"><img src="<%=ctx%>/images/previewIcon.png" width="26" height="16"></div>
							<div class="zzbj_content clearfix">
								<div class="zzbj_con clearfix">
									<!--<div class="music_btn"><img src="images/musicIcon.png" width="36" height="36"></div>-->
									<div class="block_content">
										<div class="block_right"><img src="<%=ctx%>/images/simg1.png" height="237" width="380"></div>
										<div class="block_left">
											<div class="box1"></div>
											<div class="box2"></div>
											<div class="box3"></div>
										</div>
									</div>
								</div>
								<div class="zzbj_btns clearfix">
									<h4>已选择组件</h4>
									<div class="zzbj_btns_con clearfix">
										<span class="active">区块1</span>
										<span>日期</span>
										<span>时间</span>
										<span>区块2</span>
										<span>天气</span>
										<span>音乐</span>
										<span>区块3</span>
										<span>区块4</span>
										<span>区块5</span>
										<span>区块6</span>
										<span>区块7</span>
										<span>区块8</span>
									</div>
								</div>
							</div><!--内容-->
						</div><!--edit_content end-->
					</div><!--左侧-->
					<div class="edit_wrap sc_wrap zzbj_right">
						<h3>添加素材</h3>
						<div class="edit_content">
							<div class="dirc_btns">
								<div class="dirc_btns_con clearfix">
									<span class="down_btn clearfix"><img src="<%=ctx%>/images/down.png" width="20" height="20"><em>前移</em></span>
									<span class="up_btn clearfix"><img src="<%=ctx%>/images/up.png" width="20" height="20"><em>后移</em></span>
									<span class="del_btn clearfix"><img src="<%=ctx%>/images/del.png" width="18" height="20"></span>
								</div>
							</div>
							<div class="add_icon"><img src="<%=ctx%>/images/addIcon.png" width="30" height="30"></div>
							<div class="sc_content">
								<ul class="clearfix">
									<li>
										<div class="sc_con">
											<div class="set_con clearfix"><span>01</span><img src="<%=ctx%>/images/set.png" width="16" height="16"></div>
										</div>
										<div class="time_wrap">
											<b>00:11:50</b>
											<em class="time_h"></em><em class="time_m"></em><em class="time_s"></em>
										</div>
									</li>
								</ul>
							</div>
						</div><!--edit_content end-->
					</div><!--右侧-->
				</div><!--tabs_wrap end-->
			</div><!--添加素材 end-->
			<div class="step_con" id="step_four">
				<div class="steps_wrap clearfix">
					<div class="steps_li">
						<span id="step_li_one" class="step_li_one">选择类型</span>
						<span id="step_li_two" class="step_li_two">制作布局</span>
						<span id="step_li_three" class="step_li_three">添加素材</span>
						<span id="step_li_four" class="step_li_four active">编辑完成</span>
					</div>
				</div>
				<div class="tabs_wrap making_wrap sb_making_wrap sb_end_wrap no_border clearfix">
					<div class="edit_left editt_left">
						<div class="preview_con editt_con">
							<div class="block_content">
								<div class="block_right"><img src="<%=ctx%>/images/simg1.png" width="237" height="380"></div>
								<div class="block_left">
									<div class="box1"></div>
									<div class="box2"></div>
									<div class="box3"></div>
								</div>
							</div>
						</div>
						<div class="form_group">
							<span>广告名称:</span>
							<input type="text" value="未命名-01">
						</div>
					</div>
					<div class="edit_right editt_right">
						<ul>
							<li class="clearfix"><a href="releaseAdsb.html"><span></span><em>发布这个广告</em></a></li>
							<li class="clearfix"><span></span><em>保存，但不发布</em></li>
							<li class="clearfix"><span></span><em>放弃这个广告</em></li>
						</ul>
					</div>
				</div><!--tabs_wrap end-->
			</div><!--编辑完成 end-->
		</div><!--login_middle end-->
	</div><!--container end-->
	
	<!--竖版广告广告库弹出层-->
	<div class="alert_wrap alert_profile_wrap scroll_alert sb_alert">
		<div class="alert_content">
			<h3>竖版广告<a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
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
						<div class="first_con">
							<ul class="clearfix">
								<li class="second_li">
									<img src="<%=ctx%>/images/sbImg1.png">
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
										<a href="#" class="more_white"><img src="<%=ctx%>/images/moreWhite.png"></a>
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
				<span class="next_btn" onClick="stepNext()">下一步</span>
				<span class="grey_btn">取消</span>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	
	<!--预览弹出层-->
	<div class="alert_wrap alert_profile_wrap pre_wrap sb_pre_wrap">
		<div class="alert_content">
			<h3>预览<a href="#" class="close_btn" onClick="closed()"><img src="<%=ctx%>/images/close.png"></a></h3>
			<div class="block_content sb_block_content">
				<ul>
					<!--<li><img src="images/sbigimg1.png"></li>-->
				</ul>
				<!--<div class="block_right"><img src="images/sbigimg1.png" width="498" height="305"></div>
				<div class="block_left"></div>-->
			</div><!--block_content end-->
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	
	<!--添加素材--素材空间和本地上传的tab切换弹出层-->
	<div class="alert_wrap alert_profile_wrap scroll_alert sc_alert space_alert">
		<div class="alert_content">
			<h3 class="sc_title"><span class="active">素材空间</span><span>本地上传</span><a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
			<div class="scroll_content sucai_content">
				<div class="sckj_con active">
					<div class="pic_tabs active"><span class="active">图片</span><span>视频</span></div>
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix">
								<input type="search" placeholder="搜索素材">
								<img src="<%=ctx%>/images/search.png" width="20" height="20"> 
							</strong>
							<strong class="clearfix">
								<label for="">上传时间:</label>
								<input id="datepicker-start1" type="text"><em>-</em><input id="datepicker-end1" type="text">
							</strong>
						</form>
					</div><!--search_wrap end-->
					<div class="scroll_lis sucai_lis active">
						<ul class="clearfix">
							<!--<li>
								<img src="images/sucaiImg1.png">
								<input type="checkbox">
								<span>夏日饮料.jpg</span>
							</li>-->
						</ul>
					</div>
					<div class="scroll_lis video_lis">
						<ul class="clearfix">
							<!--<li>
								<img src="images/sucaiImg1.png">
								<input type="checkbox">
								<span>夏日饮料.jpg</span>
							</li>-->
						</ul>
					</div>
				</div>
				<div class="sckj_con local">
					<div class="btns clearfix">
						<span class="add_files">
							<img src="images/addFiles.png">
							<input type="file">添加文件</span>  
						<span class="up_files"><img src="images/greyUpFiles.png">全部开始上传</span>
						<span class="pause_files"><img src="images/greyPaused.png">全部暂停上传</span>
						<span class="del_files"><img src="images/greyDelete.png">全部删除</span>
					</div>
					<div class="table_box">
						<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table">
							<!--<tr>
								<th>1</th>
								<td>夏日冷饮.jpg</td>
								<td><em></em><em>1.2M</em></td>
								<td>等待上传</td>
								<td><span><img src="images/upBtn.png"></span><span class="delete_td"><img src="images/deleBtn.png"></span></td>
							</tr>-->
						</table>
					</div>
				</div>
			</div><!--scroll_content end-->
			<div class="scroll_bottom clearfix">
				<span class="blue_btn">确定</span>
				<span class="grey_btn">取消</span>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	
	<!--添加素材--转场动画和背景音乐的tab切换弹出层-->
	<div class="alert_wrap alert_profile_wrap scroll_alert edit_alert">
		<div class="alert_content">
			<h3 class="edit_title"><span class="active">转场动画</span><span>背景音乐</span><a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
			<div class="scroll_content edit_alert_content active">
				<div class="search_wrap clearfix">
					<form action="">
						<strong class="clearfix">
							<label for="">素材名称:</label>
							<input type="text" value="夏日冷饮.jpg">
						</strong>
					</form>
				</div><!--search_wrap end-->
				<div class="mode_content clearfix">
					<h4>转场模式</h4>
					<div class="mode_left">
						<ul class="clearfix">
							<li class="mode_li mode_li1">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="blue"></span>
										<span class="white"></span>
										<img class="right_img" src="<%=ctx%>/images/rightImg.png"> 
									</div>
								</div>
								<em>向右推出</em>
							</li>
							<li class="mode_li mode_li2">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="white"></span>
										<span class="blue"></span>
										<img class="left_img" src="<%=ctx%>/images/leftImg.png"> 
									</div>
								</div>
								<em>向左推出</em>
							</li>
							<li class="mode_li mode_li3">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="blue"></span>
										<span class="white"></span>
										<img class="up_img" src="<%=ctx%>/images/upImg.png"> 
									</div>
								</div>
								<em>向下推出</em>
							</li>
							<li class="mode_li mode_li4">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="white"></span>
										<span class="blue"></span>
										<img class="down_img" src="<%=ctx%>/images/downImg.png"> 
									</div>
								</div>
								<em>向上推出</em>
							</li>
							<li class="mode_li mode_li5">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="blue"></span>
										<span class="white"></span>
										<span class="blue"></span>										
										<img class="right_img" src="<%=ctx%>/images/rightImg.png"> 
										<img class="left_img" src="<%=ctx%>/images/leftImg.png"> 
									</div>
								</div>
								<em>左右向中央收缩</em>
							</li>
							<li class="mode_li mode_li6">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="white"></span>
										<span class="blue"></span>
										<span class="white"></span>
										<img class="left_img" src="<%=ctx%>/images/leftImg.png">
										<img class="right_img" src="<%=ctx%>/images/rightImg.png">  
									</div>
								</div>
								<em>中央向左右收缩</em>
							</li>
							<li class="mode_li mode_li7">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="blue"></span>
										<span class="white"></span>
										<span class="blue"></span>
										<img class="down_img" src="<%=ctx%>/images/downImg.png"> 
										<img class="up_img" src="<%=ctx%>/images/upImg.png"> 
									</div>
								</div>
								<em>上下向中央收缩</em>
							</li>
							<li class="mode_li mode_li8">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="white"></span>
										<span class="blue"></span>
										<span class="white"></span>
										<img class="up_img" src="<%=ctx%>/images/upImg.png"> 
										<img class="down_img" src="<%=ctx%>/images/downImg.png"> 
									</div>
								</div>
								<em>中央向上下收缩</em>
							</li>
						</ul>
					</div>
					<div class="mode_right">
						<div class="mode_right_con clearfix">
							<span class="blue"></span>
						    <span class="white"></span>
						</div>
					</div>					
				</div><!--mode_content end-->
				<div class="search_wrap select_wrap clearfix">
					<form action="">
						<strong class="clearfix">
							<label for="">复制到其他素材:</label>
							<select name="" id="">
								<option value="无">无</option>
							</select>
						</strong>
					</form>
				</div><!--search_wrap end-->
			</div><!--scroll_content end-->
			<div class="scroll_content edit_alert_content add_music_alert">
				<div class="search_wrap clearfix">
					<ul class="clearfix">
						<li>
							<img src="images/musicImg1.png">
							<span>天空之城.mp4</span>   
						</li>
					</ul>
					<form action="">
						<strong class="add_music_btn">添加背景音乐</strong>
						<strong class="clearfix">
							<label for="">复制到其他素材:</label>
							<select name="" id="">
								<option value="无">无</option>
								<option value="01.夏日饮料.jpg">01.夏日饮料.jpg</option>
								<option value="02.夏日饮料.jpg">02.夏日饮料.jpg</option>
								<option value="03.夏日饮料.jpg">03.夏日饮料.jpg</option>
								<option value="04.夏日饮料.jpg">04.夏日饮料.jpg</option>
								<option value="05.夏日饮料.jpg">05.夏日饮料.jpg</option>
								<option value="06.夏日饮料.jpg">06.夏日饮料.jpg</option>
							</select>
						</strong>
					</form>
				</div><!--search_wrap end-->
			</div><!--scroll_content end-->
			<div class="scroll_bottom clearfix">
				<span class="blue_btn">确定</span>
				<span class="grey_btn">取消</span>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	
	<!--添加素材--音乐素材和本地上传的tab切换弹出层-->
	<div class="alert_wrap alert_profile_wrap scroll_alert sc_alert music_alert">
		<div class="alert_content">
			<h3 class="sc_title music_title"><span class="active">音乐素材</span><span>本地上传</span><a href="#" class="close_btn"><img src="<%=ctx%>/images/close.png"></a></h3>
			<div class="scroll_content sucai_content">
				<div class="sckj_con music_con active">
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix">
								<input type="search" placeholder="搜索素材">
								<img src="<%=ctx%>/images/search.png" width="20" height="20"> 
							</strong>
							<strong class="clearfix">
								<label for="">上传时间:</label>
								<input id="datepicker-start2" type="text"><em>-</em><input id="datepicker-end2" type="text">
							</strong>
						</form>
					</div><!--search_wrap end-->
					<div class="scroll_lis sucai_lis music_lis">
						<ul class="clearfix">
							<!--<li>
								<img src="images/musicImg1.png">
								<input type="checkbox">
								<span>天空之城.mp4</span>
							</li>-->
						</ul>
					</div>
				</div>
				<div class="sckj_con music_con local">
					<div class="btns clearfix">
						<span class="add_files">
							<img src="images/addFiles.png">
							<input type="file">添加文件</span>  
						<span class="up_files"><img src="images/greyUpFiles.png">全部开始上传</span>
						<span class="pause_files"><img src="images/greyPaused.png">全部暂停上传</span>
						<span class="del_files"><img src="images/greyDelete.png">全部删除</span>
					</div>
					<div class="table_box">
						<table cellpadding="0" cellspacing="0" border="0" width="100%" class="table">
							<!--<tr>
								<th>1</th>
								<td>夏日冷饮.jpg</td>
								<td><em></em><em>1.2M</em></td>
								<td>等待上传</td>
								<td><span><img src="images/upBtn.png"></span><span><img src="images/deleBtn.png"></span></td>
							</tr>-->
						</table>
					</div>
				</div>
			</div><!--scroll_content end-->
			<div class="scroll_bottom clearfix">
				<span class="blue_btn">确定</span>
				<span class="grey_btn">取消</span>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	<script type="text/javascript" src="<%=ctx %>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=ctx %>/js/zebra_datepicker.js"></script>
	<script type="text/javascript" src="<%=ctx %>/js/jquery.imgscroll.min.js"></script>
	<script type="text/javascript" src="<%=ctx %>/js/jquery-ui.min.js"></script>
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
			
			var tabs_li_height = $(".tabs_content").height() - $(".search_wrap").outerHeight() - 108 + "px";
			$(".tabs_lis").css("height",tabs_li_height);
			
			var zjxx_wrapper_height = $(".making_wrap").height() - 302 + "px";
			$(".zjxx_wrapper").css("height",zjxx_wrapper_height);
			
			var edit_content_height = $(".making_wrap").outerHeight() - $(".edit_wrap h3").outerHeight() - 12 + "px";
			$(".edit_content").css("height",edit_content_height);

			var zzbj_edit_width = $(".tabs_wrap").innerWidth() - $(".zzbj_left").width() - $(".added_compon").outerWidth() - 20 + "px";
			$(".zzbj_edit").css("width",zzbj_edit_width);
			//竖版广告库li
			/*var vertLi = [{
				"path":"images/sbImg1.png"
			},{
				"path":"images/sbImg2.png"
			}];*/
			var vertLi = "";
			$.ajax({
				url: "<%=ctx %>/ad/adPackage!getTemplateList.do",
				cache : false,
				type: "POST",
				async: false,
				data: "templateType=2",
				success: function(data){
					vertLi = eval(data);
				}
			});
			var vert_li_html = "";
			for(var i = 0; i < vertLi.length; i ++){
				vert_li_html += "<li>" +
									 "<img src='" + vertLi[i].path + "'>" +
									 "<div class='shade_content clearfix'>" +
									 	"<img class='ok_icon' src='<%=ctx %>/images/ok.png'>" +
									 "</div>" +
						   "</li>";
			}
			$(".vert_li ul").html(vert_li_html);
			
			//广告列表鼠标移上移除的效果
			$(".tabs_lis li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});
			
			//添加素材li
			var scLi = [{
				"time":"00:00:50",
				"number":"01"
			},{
				"time":"00:11:50",
				"number":"02"
			}];
			var sc_li_html = "";
			for(var i = 0; i < scLi.length; i ++){
				sc_li_html += "<li>" +
									 "<div class='sc_con'>" +
										 "<div class='set_con clearfix'><span>" + scLi[i].number + "</span><img src='<%=ctx%>/images/set.png'></div>" +
										 "</div>" +
									 	"<div class='time_wrap'>" +
											"<b>" + scLi[i].time + "</b>" +
											"<em class='time_h'></em><em class='time_m'></em><em class='time_s'></em>" +
											"<div class='h_time_con'>" + "<span></span>" + "</div>" +
											"<div class='m_time_con'>" + "<span></span>" + "</div>" +
											"<div class='s_time_con'>" + "<span></span>" + "</div>" +
									     "</div>" +
						   		"</li>";
			}
			$(".sc_content ul").html(sc_li_html);

			//时
			var h_num_html = "";
			for(var h = 0; h <=23; h++){
				if(h<10){
					h_num_html += "<span>" + "0" + h + "</span>";
				}else{
					h_num_html += "<span>" + h + "</span>";
				}
			}
			$(".h_time_con").html(h_num_html);	
			
			//分
			var m_num_html = "";
			for(var m = 0; m <=59; m++){
				if(m<10){
					m_num_html += "<span>" + "0" + m + "</span>";
				}else{
					m_num_html += "<span>" + m + "</span>";
				}
			}
			$(".m_time_con").html(m_num_html);	
			
			//秒
			var s_num_html = "";
			for(var s = 0; s <=59; s++){
				if(s<10){
					s_num_html += "<span>" + "0" + s + "</span>";
				}else{
					s_num_html += "<span>" + s + "</span>";
				}
			}
			$(".s_time_con").html(s_num_html);	
									
			//弹出层广告库详情
			var scrollLi = [{
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
			var scroll_li_html = "";
			for(var i = 0; i < scrollLi.length; i ++){
				scroll_li_html += "<li class='second_li'>" +
										"<img src='" + scrollLi[i].path + "'>" +
										 "<div class='shade_content'>" +
											"<div class='shade_li clearfix'>" +
												"<span>广告名称:</span>" +
												"<span>" + scrollLi[i].name + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>分辨率:</span>" +
												"<span>" + scrollLi[i].RP + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>上次编辑时间:</span>" +
												"<span>" + scrollLi[i].time + "</span>" +
											"</div>" +
											"<div class='shade_li clearfix'>" +
												"<span>编辑人:</span>" +
												"<span>" + scrollLi[i].user + "</span>" +
											"</div>" +
											"<a class='more_white' href='" + scrollLi[i].url + "'><img src='<%=ctx%>/images/moreWhite.png'></a>" +
										 "</div>" +
						   		  "</li>";
			}
			$(".first_con ul").html(scroll_li_html);
			
			//素材空间-图片li
			var addLi = [{
				"path":"<%=ctx%>/images/sucaiImg1.png",
				"title":"天空之城.jpg"
			},{
				"path":"<%=ctx%>/images/sucaiImg1.png",
				"title":"天空之城.jpg"
			}];
			var add_li_html = "";
			for(var i = 0; i < addLi.length; i ++){
				add_li_html += "<li>" +
									 "<img src='" + addLi[i].path + "'>" +
									 "<input type='checkbox'>" +
									 "<span>" + addLi[i].title + "</span>" +
						   		"</li>";
			}
			$(".sucai_lis ul").html(add_li_html);
			
			//素材空间-视频li
			var videoLi = [{
				"path":"<%=ctx%>/images/sucaiImg1.png",
				"title":"天空之城.mp4"
			},{
				"path":"<%=ctx%>/images/sucaiImg1.png",
				"title":"天空之城.mp4"
			}];
			var video_li_html = "";
			for(var i = 0; i < videoLi.length; i ++){
				video_li_html += "<li>" +
									 "<img src='" + videoLi[i].path + "'>" +
									 "<input type='checkbox'>" +
									 "<span>" + videoLi[i].title + "</span>" +
						   		"</li>";
			}
			$(".video_lis ul").html(video_li_html);
			
			//本地上传
			var localTr = [{
				"number":"1",
				"title":"夏日冷饮.jpg",
				"percent":"",
				"speed":"1.6M",
				"state":"等待上传",
				"path":"<%=ctx%>/images/upBtn.png"
			},{
				"number":"2",
				"title":"夏日冷饮.jpg",
				"percent":"46%",
				"speed":"2.01M/s",
				"state":"等待上传",
				"path":"<%=ctx%>/images/startBtn.png"
			},{
				"number":"3",
				"title":"夏日冷饮.jpg",
				"percent":"24%",
				"speed":"2.01M/s",
				"state":"等待上传",
				"path":"<%=ctx%>/images/pauseBtn.png"
			}];
			var local_tr_html = "";
			for(var i = 0; i < localTr.length; i ++){
				local_tr_html += "<tr>" +
								"<th>" + localTr[i].number + "</th>" +
								"<td>" + localTr[i].title + "</td>" +
								"<td><em>" + localTr[i].percent + "</em><em>" + localTr[i].speed + "</em></td>" +
								"<td>" + localTr[i].state + "</td>" +
								"<td><span><img src='" + localTr[i].path + "'></span><span class='delete_td'><img src='<%=ctx%>/images/deleBtn.png'></span></td>" +
							"</tr>";
			}
			$(".table_box .table").html(local_tr_html);
			
			//音乐素材
			var musicLi = [{
				"path":"<%=ctx%>/images/musicImg1.png",
				"title":"天空之城.mp4"
			},{
				"path":"<%=ctx%>/images/musicImg1.png",
				"title":"天空之城.mp4"
			}];
			var music_li_html = "";
			for(var i = 0; i < musicLi.length; i ++){
				music_li_html += "<li>" +
									 "<img src='" + musicLi[i].path + "'>" +
									 "<input type='checkbox'>" +
									 "<span>" + musicLi[i].title + "</span>" +
						   		"</li>";
			}
			$(".music_lis ul").html(music_li_html);
			
			//添加素材--设置--背景音乐
			var muLi = [{
				"path":"images/musicImg1.png",
				"title":"天空之城.mp4"
			},{
				"path":"images/musicImg1.png",
				"title":"天空之城.mp4"
			}];
			var mu_li_html = "";
			for(var i = 0; i < muLi.length; i ++){
				mu_li_html += "<li>" +
									 "<img src='" + muLi[i].path + "'>" +
									 "<span>" + muLi[i].title + "</span>" +
						   		"</li>";
			}
			$(".add_music_alert ul").html(mu_li_html);
			
			//点击广告库显示竖版广告弹出层
			$(".have_link").click(function(e) {
				$(".sb_alert").fadeIn(500);
			});
			
			//选中时图片加边框，下一步按钮变蓝色
			$(".second_li .shade_content").click(function(e) {
				$(this).parent(".second_li").addClass("active");
				$(".next_btn").removeClass("next_btn").addClass("blue_btn");
			});
			
			//点击白色OK变成绿色
			$(".shade_content").click(function(e) {
				$(this).children("img").attr("src","images/greenOk.png");
			});
			
			//点击关闭按钮隐藏竖版广告弹出层
			$(".scroll_alert .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
			
			//广告列表鼠标移上移除的效果
			$(".second_li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});
			
			//点击预览
			$(".preview_icon img").click(function(e) {
				$(".pre_wrap").fadeIn(500);
			});
			
			//添加素材
			$(".add_icon img").click(function(e) {
				$(".space_alert").fadeIn(500);
			});
			
			//图片空间 本地上传的tab切换
			$(".sc_title span").click(function(e) {
				$(this).addClass("active").siblings().removeClass("active");
				$(".sckj_con").eq($(this).index()).addClass("active").siblings().removeClass("active");
			});
			
			//图片空间--图片和视频的tab切换
			$(".pic_tabs span").click(function(e) {
				$(this).addClass("active").siblings().removeClass("active");
				$(".sckj_con .scroll_lis").eq($(this).index()).addClass("active").siblings().removeClass("active");
			});
			
			$(".set_con img").click(function(e) {
				$(".edit_alert").fadeIn(500);
			});
			
			//添加素材--转场动画和背景音乐tab切换
			$(".edit_title span").click(function(e) {
				$(this).addClass("active").siblings().removeClass("active");
				$(".edit_alert_content").eq($(this).index()).addClass("active").siblings().removeClass("active");
			});
			
			//添加音乐
			$(".add_music_btn").click(function(e) {
				$(".music_alert").fadeIn(500);
			});
			
			//音乐素材和本地上传的tab切换
			$(".music_title span").click(function(e) {
				$(this).addClass("active").siblings().removeClass("active");
				$(".music_con").eq($(this).index()).addClass("active").siblings().removeClass("active");
			});
			
			//删除table中的tr
			$(".delete_td").click(function(e) {
				$(this).parent().parent("tr").hide(500);
			});
		
			//制作布局--组件拖动
			$(".zj_li1").click(function(){
				$(".zzbj_block_content").append(
					$("<li class='zj_li zj_li11' style='top:10px;'><img class='blue_close_btn' src='images/blueClose.png'></li>")
					.click(function(e){
						$(this).draggable({ containment: ".zzbj_block_content", scroll: false });
						$(this).resizable({
							containment: ".zzbj_block_content",
							minWidth:70,
							minHeight:50,
							resize:callback11
						});	
						$(this).addClass("active").siblings().removeClass("active");
						$(".txt_name").val("多媒体区块");
						var x11 = $(this).offset().left.toFixed(2);
						$(".txt_x").val(x11);
						var y11 = $(this).offset().top.toFixed(2);
						$(".txt_y").val(y11);
					})
				)
			})
		
			$(".zj_li2").click(function(){
				$(".zzbj_block_content").append(
					$("<li class='zj_li zj_li22' style='top:10px;'><img class='blue_close_btn' src='images/blueClose.png'></li>")
					.click(function(e){
						$( ".zj_li22" ).draggable({ containment: ".zzbj_block_content", scroll: false });
						$(this).resizable({
							containment: ".zzbj_block_content",
							minWidth:70,
							minHeight:50,
							resize:callback11
						});	
						$(this).addClass("active").siblings().removeClass("active");
						$(".txt_name").val("日期");
						var x22 = $(this).offset().left.toFixed(2);
						$(".txt_x").val(x22);
						var y22 = $(this).offset().top.toFixed(2);
						$(".txt_y").val(y22);
					})
				)
			})
			
			$(".zj_li3").click(function(){
				$(".zzbj_block_content").append(
					$("<li class='zj_li zj_li33' style='top:10px;'><img class='blue_close_btn' src='images/blueClose.png'></li>")
					.click(function(e){
						$( ".zj_li33" ).draggable({ containment: ".zzbj_block_content", scroll: false });
						$(this).resizable({
							containment: ".zzbj_block_content",
							minWidth:70,
							minHeight:50,
							resize:callback11
						});	
						$(this).addClass("active").siblings().removeClass("active");
						$(".txt_name").val("时间");
						var x33 = $(this).offset().left.toFixed(2);
						$(".txt_x").val(x33);
						var y33 = $(this).offset().top.toFixed(2);
						$(".txt_y").val(y33);
					})
				)
			})
			
			$(".zj_li4").click(function(){
				$(".zzbj_block_content").append(
					$("<li class='zj_li zj_li44' style='top:10px;'><img class='blue_close_btn' src='images/blueClose.png'></li>")
					.click(function(e){
						$( ".zj_li44" ).draggable({ containment: ".zzbj_block_content", scroll: false});
						$( ".zj_li44" ).draggable({ containment: ".zzbj_block_content", scroll: false});
						$(this).resizable({
							containment: ".zzbj_block_content",
							minWidth:70,
							minHeight:50,
							resize:callback11
						});	
						$(this).addClass("active").siblings().removeClass("active");
						$(".txt_name").val("天气");
						var x44 = $(this).offset().left.toFixed(2);
						$(".txt_x").val(x44);
						var y44 = $(this).offset().top.toFixed(2);
						$(".txt_y").val(y44);
					})
				)
			})
			
			$(".zj_li5").click(function(){
				$(".zzbj_block_content").append(
					$("<li class='zj_li zj_li55' style='top:10px;'><img class='blue_close_btn' src='images/blueClose.png'></li>")
					.click(function(e){
						$( ".zj_li55" ).draggable({ containment: ".zzbj_block_content", scroll: false});
						$(this).resizable({
							containment: ".zzbj_block_content",
							minWidth:70,
							minHeight:50,
							resize:callback11
						});	
						$(this).addClass("active").siblings().removeClass("active");
						$(".txt_name").val("音乐");
						var x55 = $(this).offset().left.toFixed(2);
						$(".txt_x").val(x55);
						var y55 = $(this).offset().top.toFixed(2);
						$(".txt_y").val(y55);
					})
				)
			})
			
			$(".mode_li_con").click(function(e) {
				$(this).addClass("active").parent().siblings().children().removeClass("active");
			});

			//时
			$(".time_h").click(function(e) {
				$(".m_time_con").slideUp(500);
				$(".s_time_con").slideUp(500);
				$(".h_time_con").slideDown(500);
			});

			$(".h_time_con span").click(function(e) {
				$(this).addClass("active");
				$(this).parent().slideUp(500);
			});
			
			//分
			$(".time_m").click(function(e) {
				$(".h_time_con").slideUp(500);
				$(".s_time_con").slideUp(500);
				$(".m_time_con").slideDown(500);
			});
			
			$(".m_time_con span").click(function(e) {
				$(this).addClass("active");
				$(this).parent().slideUp(500);
			});	
			
			//秒
			$(".time_s").click(function(e) {
				$(".h_time_con").slideUp(500);
				$(".m_time_con").slideUp(500);
				$(".s_time_con").slideDown(500);
			});
			
			$(".s_time_con span").click(function(e) {
				$(this).addClass("active");
				$(this).parent().slideUp(500);
			});	
			
			//选中
			$(".sc_con").click(function(e) {
				$(this).addClass("active").parent().siblings().children(".sc_con").removeClass("active");
			});

			//删除
			$(".del_btn").click(function(e) {
				if($(".sc_con").hasClass("active")){
					$(".sc_con.active").parent().hide(500);
				}
			});
			
			//前移
			$(".down_btn").click(function(e) {
				if($(".sc_con").hasClass("active")){
					$(".sc_con.active").parent().prev().before($(".sc_con.active").parent());
				}
			});
			
			//后移
			$(".up_btn").click(function(e) {
				if($(".sc_con").hasClass("active")){
					$(".sc_con.active").parent().next().after($(".sc_con.active").parent());
				}
			});
			
			//添加素材--预览中图片滚动
			var prewLi = [{
				"path":"images/bigimg1.png"
			},{
				"path":"images/bigimg1.png"
			},{
				"path":"images/bigimg1.png"
			}];
			var prew_li_html = "";
			for(var i = 0; i < prewLi.length; i ++){
				prew_li_html += "<li>" +
									 "<img src='" + prewLi[i].path + "'>" +
						   		"</li>";
			}
			$(".sb_block_content ul").html(prew_li_html);
			
			imgScroll.rolling({
				name:'sb_block_content',
				width:'498px',
				height:'305px',
				direction:'top',
				speed:20,
				addcss:true
			});
			
			//制作布局--div大小的调节
			$(".ui-widget-top").click(function(e) {
				$(this).css("border","1px solid #3f9ce9");
				var x1 = $(this).offset().left.toFixed(2);
				$(".txt_x").val(x1);
				var y1 = $(this).offset().top.toFixed(2);
				$(".txt_y").val(y1);
				$(".txt_name").val("区块01");
			});
			$(".resizable1").resizable({
				maxWidth:378,
				maxHeight:580,
				resize:callback1
			});
			
			$(".ui-widget-middle").click(function(e) {
				$(this).css("border","1px solid #3f9ce9");
				var x2 = $(this).offset().left.toFixed(2);
				$(".txt_x").val(x2);
				var y2 = $(this).offset().top.toFixed(2);
				$(".txt_y").val(y2);
				$(".txt_name").val("区块02");
			});
			$(".resizable2").resizable({
				maxWidth:378,
				maxHeight:580,
				resize:callback2
			});
			
			$(".ui-widget-bottom").click(function(e) {
				$(this).css("border","1px solid #3f9ce9");
				var x3 = $(this).offset().left.toFixed(2);
				$(".txt_x").val(x3);
				var y3 = $(this).offset().top.toFixed(2);
				$(".txt_y").val(y3);
				$(".txt_name").val("区块02");
			});
			$(".resizable3").resizable({
				maxWidth:338,
				maxHeight:580,
				resize:callback3
			});
			
			$(".add_music_alert li").click(function(e) {
				$(this).css("border","1px solid #f39801").siblings().css("border","1px solid #d3d3d3");
			});
			

			//弹出层按钮由灰色变成彩色 
			$(".add_files").click(function(e) {
				$(".up_files img").attr("src","images/upFiles.png");
				$(".pause_files img").attr("src","images/pausedFiles.png");
				$(".del_files img").attr("src","images/deleteFiles.png");
			});
			
			//制作布局--编辑上排按钮
			//置顶
			$(".direc_btn1").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css("z-index","999").siblings().css("z-index","0");
				}
			});
			
			//置底
			$(".direc_btn2").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css("z-index","0").siblings(".zj_li").css("z-index","1");
				}
			});
			
			//上移一层
			$(".direc_btn3").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css("z-index","99").next(".zj_li").css("z-index","90");
				}
			});
			
			//下移一层
			$(".direc_btn4").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css("z-index","90").prev(".zj_li").css("z-index","99");
				}
			});
			
			//左对齐
			$(".direc_btn5").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css({"left":"10px","right":"auto"});
				}
			});
			
			//水平对齐
			$(".direc_btn6").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css({"left":"150px","right":"auto"});
				}
			});
			
			//右对齐
			$(".direc_btn7").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css({"right":"10px","left":"auto"});
				}
			});
			
			//顶对齐
			$(".direc_btn8").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css({"top":"10px","bottom":"auto"});
				}
			});
			
			//垂直对齐
			$(".direc_btn9").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css({"top":"240px","bottom":"auto"});
				}
			});
			
			//底对齐
			$(".direc_btn10").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css({"bottom":"10px","top":"auto"});
				}
			});
			
			//放大
			$(".direc_btn11").click(function(e) {
				var block_right_width = $(".block_right").width();
				var block_right_height = $(".block_right").height();
				var time_con_width = $(".time_con").outerWidth();
				var time_con_height = $(".time_con").outerHeight();
				var time_con_top = $(".time_con").position().top + 10;
				var weather_con_width = $(".weather_con").outerWidth();
				var weather_con_height = $(".weather_con").outerHeight();
				var weather_con_top = $(".weather_con").position().top;
				var zj_li11_top = $(".zj_li11").position().top;
				if( zj_li11_top > weather_con_top ){
					$(".zj_li11").animate(
					{
						"width":weather_con_width,
						"height":weather_con_height,
						"left":"10px",
						"top":weather_con_top
					},500);
				}else if( 10 < zj_li11_top < time_con_top){
					$(".zj_li11").animate(
					{
						"width":block_right_width,
						"height":block_right_height,
						"left":"10px",
						"top":"10px"
					},500);
				}else{
					$(".zj_li11").animate(
					{
						"width":time_con_width,
						"height":time_con_height,
						"left":"10px",
						"top":time_con_top
					},500);
				}
			});
			
			//删除
			$(".direc_btn12").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").hide();
				}
			});
		});
		//关闭
			function closed(){
				$(".pre_wrap").fadeOut(500);
			}
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
				$(".title").html("添加素材");
			}
			function stepThree(){
				$('#step_three').hide();
				$('#step_four').show();
				$(".title").html("编辑完成");
			}
			function stepNext(){
				$("#step_one").hide();
				$("#step_two").show();
				$(".scroll_alert").hide();
				$(".title").html("制作布局");
			}
			function restepOne(){
				$('#step_one') .show();
				$('#step_two').hide();
				$(".title").html("选择类型");
			}
			function restepTwo(){
				$('#step_two') .show();
				$('#step_three').hide();
				$(".title").html("制作布局");
			}

			function callback1(e,ui){
				var width1 = ui.size.width;
				var height1 = ui.size.height;
				$(".txt_w").val(width1);
				$(".txt_h").val(height1);
			}

			function callback2(e,ui){
				var width2 = ui.size.width;
				var height2 = ui.size.height;
				$(".txt_w").val(width2);
				$(".txt_h").val(height2);
			}
			
			function callback3(e,ui){
				var width3 = ui.size.width;
				var height3 = ui.size.height;
				$(".txt_w").val(width3);
				$(".txt_h").val(height3);
			}
			function callback11(e,ui){
				var width11 = ui.size.width;
				var height11 = ui.size.height;
				$(".txt_w").val(width11);
				$(".txt_h").val(height11);
			}
			
			function callback22(e,ui){
				var width22 = ui.size.width;
				var height22 = ui.size.height;
				$(".txt_w").val(width22);
				$(".txt_h").val(height22);
			}
			
			function callback33(e,ui){
				var width33 = ui.size.width;
				var height33 = ui.size.height;
				$(".txt_w").val(width33);
				$(".txt_h").val(height33);
			}
			
			function callback44(e,ui){
				var width44 = ui.size.width;
				var height44 = ui.size.height;
				$(".txt_w").val(width44);
				$(".txt_h").val(height44);
			}
			
			function callback55(e,ui){
				var width55 = ui.size.width;
				var height55 = ui.size.height;
				$(".txt_w").val(width55);
				$(".txt_h").val(height55);
			}
			
	</script>
</body>
</html>
