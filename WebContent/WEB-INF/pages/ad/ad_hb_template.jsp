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
<link rel="stylesheet" href="<%=ctx %>/css/common.css">
<link rel="stylesheet" href="<%=ctx %>/css/calendar.css">
<link rel="stylesheet" href="<%=ctx %>/css/jquery-ui.min.css">
<style type="text/css">
.making_wrap .edit_wrap2 .edit_content .zzbj_content .zzbj_con .block_content{
	overflow:hidden;
	}
.making_wrap .edit_wrap2 .edit_content .zzbj_content .zzbj_con .block_content .block_left{
	width:332px;
	}
.making_wrap .edit_wrap2 .edit_content .zzbj_content .zzbj_con .block_content .block_right{
	width:234px;
	}
.ui-widget-content{
	background-color:rgba(255,255,255,0);
	border:0;
	}
</style>
</head>

<body class="light">
	<div class="container">
		<%-- <div class="login_top">
			<div class="logo"><img src="<%=ctx %>/images/blueLogo.png"></div>
			<div class="message_box clearfix">
				<span>admin</span>
				<span>(0)</span>
				<span>退出</span>
			</div>
		</div><!--login_top end--> --%>
		<%@include file="/top.jsp" %>
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
						<a href="<%=ctx%>/ad/adPackage!toHbTemplateChoose.do" class="active">横板广告</a>
						<a href="<%=ctx%>/ad/adPackage!toSbTemplateChoose.do">竖版广告</a>
						<a href="<%=ctx%>/ad/adPackage!toScrollTemplateChoose.do">滚动字幕</a>
					</div><!--tabs_btn end-->
					<div class="tabs_content active">
						<div class="search_wrap clearfix">
							<form action="">
								<strong class="clearfix">
									<label for="">分辨率:</label>
									<select name="" id="fbl" onchange="getTemplitByFbl()">
										<option value="1200*900">1200*900</option>
										<option value="1200*1000">1200*1000</option>
									</select>
								</strong>
							</form>
							<a href="#" class="have_link">从已有广告库选取</a>
						</div><!--search_wrap end-->
						<div class="tabs_lis hori_li">
							<ul class="clearfix">
								<!--<li>
									<img src="images/hbImg1.png">
									<div class="shade_content">
										<img class="ok_icon" src="images/ok.png">
									</div>
								</li>-->
							</ul>
						</div>
					</div><!-- 横版广告 tabs_content end-->
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
				<div class="tabs_wrap making_wrap hb_end_wrap no_border clearfix">
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
					<div class="edit_wrap edit_wrap2 zzbj_right zzbj_edit">
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
									<div class="block_content">
										<%-- <div class="block_left">
											<div class="time_con clearfix">
												<span>2017-01-17</span>
												<em>14:20:45</em>
											</div>
											<div class="weather_con">
												<ul class="clearfix">
													<li>
														<span>今天</span>
														<span>8&deg;C/15&deg;C</span>
														<img src="<%=ctx %>/images/weather1.png" width="38" height="38"> 
													</li>
													<li>
														<span>明天</span>
														<span>4&deg;C/10&deg;C</span>
														<img src="<%=ctx %>/images/weather2.png" width="38" height="38"> 
													</li>
													<li>
														<span>后天</span>
														<span>6&deg;C/10&deg;C</span>
														<img src="<%=ctx %>/images/weather3.png" width="38" height="38"> 
													</li>
												</ul>
											</div>
										</div>
										<div class="block_right ui-widget-right ui-widget-content resizable2">区块01</div> --%>
									</div>
								</div>
								<!-- <div class="zzbj_btns clearfix">
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
				<div class="tabs_wrap making_wrap no_border clearfix">
					<div class="edit_wrap2 templet_wrap zzbj_left">
						<h3>模板信息</h3>
						<div class="edit_content">
							<div class="preview_icon"><img src="<%=ctx %>/images/previewIcon.png" width="26" height="16"></div>
							<div class="zzbj_content clearfix">
								<div class="zzbj_con clearfix">
									<%-- <div class="music_btn"><img src="<%=ctx %>/images/musicIcon.png" width="36" height="36"></div> --%>
									<div class="block_content">
										<!-- <div class="block_left"> -->
											<!--<div class="time_con clearfix">
												<span>2017-01-17</span>
												<em>14:20:45</em>
											</div>
											<div class="weather_con">
												<ul>
													<li>
														<span>今天</span>
														<span>8&deg;C/15&deg;C</span>
														<img src="images/weather1.png" width="38" height="38"> 
													</li>
													<li>
														<span>明天</span>
														<span>4&deg;C/10&deg;C</span>
														<img src="images/weather2.png" width="38" height="38"> 
													</li>
													<li>
														<span>后天</span>
														<span>6&deg;C/10&deg;C</span>
														<img src="images/weather3.png" width="38" height="38"> 
													</li>
												</ul>
											</div>-->
										<%-- </div>
										<div class="block_right"><img src="<%=ctx %>/images/img1.png" width="237" height="380"></div> --%>
									</div>
								</div>
								<div class="zzbj_btns clearfix">
									<h4>选择组件</h4>
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
						<div class="edit_content add_content">
							<div class="dirc_btns">
								<div class="dirc_btns_con clearfix">
									<span class="down_btn clearfix"><img src="<%=ctx %>/images/down.png" width="20" height="20"><em>前移</em></span>
									<span class="up_btn clearfix"><img src="<%=ctx %>/images/up.png" width="20" height="20"><em>后移</em></span>
									<span class="del_btn clearfix"><img src="<%=ctx %>/images/del.png" width="18" height="20"></span>
								</div>
							</div>
							<div class="add_icon"><img src="<%=ctx %>/images/addIcon.png" width="30" height="30"></div>
							<div class="sc_content">
								<ul class="clearfix">
									<%-- <li>
										<div class="sc_con">
											<div class="set_con clearfix"><span>01</span><img src="<%=ctx %>/images/set.png" width="16" height="16"></div>
										</div>
										<div class="time_wrap">
											<b>00:11:50</b>
											<em class="time_h"></em><em class="time_m"></em><em class="time_s"></em>
										</div>
									</li> --%>
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
				<div class="tabs_wrap making_wrap hb_end_wrap no_border clearfix">
					<div class="edit_left editt_left">
						<div class="preview_con editt_con">
							<div class="block_content">
								<div class="block_left"></div>
								<div class="block_right"><img src="<%=ctx %>/images/img1.png" width="237" height="380"></div>
							</div>
						</div>
						<div class="form_group">
							<span>广告名称:</span>
							<input type="text" value="未命名-01" id="setAdName">
						</div>
						<div class="form_group">
							<span>广告批次:</span>
							<input value="Bsssss" id="setAdBatchNo" type="text">
						</div>
					</div>
					<div class="edit_right editt_right">
						<ul>
							<li class="clearfix"><a href="javascript:void(0)" onclick="submitAllAd('1')"><span></span><em>发布这个广告</em></a></li>
							<li class="clearfix"><a href="javascript:void(0)" onclick="submitAllAd('0')"><span></span><em>保存，但不发布</em></a></li>
							<li class="clearfix"><a href="javascript:void(0)" onclick="window.location.reload()"><span></span><em>放弃这个广告</em></a></li>
						</ul>
					</div>
				</div><!--tabs_wrap end-->
			</div><!--编辑完成 end-->
		</div><!--login_middle end-->
	</div><!--container end-->
	
	<!--横版广告广告库弹出层-->
	<div class="alert_wrap alert_profile_wrap scroll_alert hb_alert">
		<div class="alert_content">
			<h3>横版广告<a href="#" class="close_btn"><img src="<%=ctx %>/images/close.png"></a></h3>
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
									<img src="<%=ctx %>/images/sbImg1.png">
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
										<a href="#" class="more_white"><img src="<%=ctx %>/images/moreWhite.png"></a>
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
	<div class="alert_wrap alert_profile_wrap pre_wrap">
		<div class="alert_content">
			<h3>预览<a href="#" class="close_btn" onClick="closed()"><img src="<%=ctx %>/images/close.png"></a></h3>
			<div class="block_content hb_block_content">
				<ul class="clearfix">
					<!--<li><img src="images/bigimg1.png"></li>
					<li><img src="images/bigimg1.png"></li>
					<li><img src="images/bigimg1.png"></li>-->
				</ul>
				<!--<div class="block_left"></div>
				<div class="block_right"><img src="images/bigimg1.png" width="305" height="498"></div>-->
			</div><!--block_content end-->
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	
	<!--添加素材--素材空间和本地上传的tab切换弹出层-->
	<div class="alert_wrap alert_profile_wrap scroll_alert sc_alert space_alert">
		<div class="alert_content">
			<h3 class="sc_title"><span class="active">素材空间</span><span>本地上传</span><a href="#" class="close_btn"><img src="<%=ctx %>/images/close.png"></a></h3>
			<div class="scroll_content sucai_content">
				<div class="sckj_con active">
					<div class="pic_tabs active"><span class="active">图片</span><span>视频</span></div>
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix">
								<input type="search" placeholder="搜索素材">
								<img src="<%=ctx %>/images/search.png" width="20" height="20"> 
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
						<form action="" enctype="multipart/form-data" id="resourceFileForm">
							<a href="javascript:void(0)" onclick="$('#fileupload').click();">选择素材</a>
							<input type="file" id="fileupload" name="fileupload" onchange="selectResource('fileupload',this)">
							<input id="fileuploadFileName" name="fileuploadFileName" value="" type="hidden">
							<input id="resourceType" name="resourceType" value="" type="hidden">
							<input id="setAdTemplateId" name="setAdTemplateId" value="1" type="hidden">
							<input id="adType" name="adType" value="H" type="hidden">
						</form>
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
				<span class="blue_btn" onclick="addResourceToList()">确定</span>
				<span class="grey_btn" onclick="$('.scroll_alert .close_btn').click();">取消</span>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	
	<!--添加素材--转场动画和背景音乐的tab切换弹出层-->
	<div class="alert_wrap alert_profile_wrap scroll_alert edit_alert">
		<div class="alert_content">
			<h3 class="edit_title"><span class="active">转场动画</span><span>背景音乐</span><a href="#" class="close_btn"><img src="<%=ctx %>/images/close.png"></a></h3>
			<div class="scroll_content edit_alert_content active">
				<div class="search_wrap clearfix">
					<form action="">
						<strong class="clearfix">
							<label for="">素材名称:</label>
							<input type="text" value="夏日冷饮.jpg" id="resourceName">
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
										<img class="right_img" src="<%=ctx %>/images/rightImg.png"> 
									</div>
								</div>
								<em>向右推出</em>
							</li>
							<li class="mode_li mode_li2">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="white"></span>
										<span class="blue"></span>
										<img class="left_img" src="<%=ctx %>/images/leftImg.png"> 
									</div>
								</div>
								<em>向左推出</em>
							</li>
							<li class="mode_li mode_li3">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="blue"></span>
										<span class="white"></span>
										<img class="up_img" src="<%=ctx %>/images/upImg.png"> 
									</div>
								</div>
								<em>向下推出</em>
							</li>
							<li class="mode_li mode_li4">
								<div class="mode_li_con">
									<div class="mode_li_middle clearfix">
										<span class="white"></span>
										<span class="blue"></span>
										<img class="down_img" src="<%=ctx %>/images/downImg.png"> 
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
										<img class="right_img" src="<%=ctx %>/images/rightImg.png"> 
										<img class="left_img" src="<%=ctx %>/images/leftImg.png"> 
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
										<img class="left_img" src="<%=ctx %>/images/leftImg.png">
										<img class="right_img" src="<%=ctx %>/images/rightImg.png">  
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
										<img class="down_img" src="<%=ctx %>/images/downImg.png"> 
										<img class="up_img" src="<%=ctx %>/images/upImg.png"> 
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
										<img class="up_img" src="<%=ctx %>/images/upImg.png"> 
										<img class="down_img" src="<%=ctx %>/images/downImg.png"> 
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
							<select name="" id="copyGoToType">
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
							<select name="" id="copyBackgroundMusic">
								<option value="无">无</option>
							</select>
						</strong>
					</form>
				</div><!--search_wrap end-->
			</div><!--scroll_content end-->
			<div class="scroll_bottom clearfix">
				<span class="blue_btn" onclick="selectProfileWrap()">确定</span>
				<span class="grey_btn" onclick="$('.edit_title .close_btn').click();">取消</span>
			</div>
		</div><!--alert_content end-->
	</div><!--alert_wrap end-->
	
	<!--添加素材--音乐素材和本地上传的tab切换弹出层-->
	<div class="alert_wrap alert_profile_wrap scroll_alert sc_alert music_alert">
		<div class="alert_content">
			<h3 class="sc_title music_title"><span class="active">音乐素材</span><span>本地上传</span><a href="#" class="close_btn"><img src="<%=ctx %>/images/close.png"></a></h3>
			<div class="scroll_content sucai_content">
				<div class="sckj_con music_con active">
					<div class="search_wrap clearfix">
						<form action="">
							<strong class="clearfix">
								<input type="search" placeholder="搜索素材">
								<img src="<%=ctx %>/images/search.png" width="20" height="20"> 
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
					<div class="table_box">
						<form action="" enctype="multipart/form-data" id="resourceMusicForm">
							<a href="javascript:void(0)" onclick="$('#fileupload').click();">选择素材</a>
							<input type="file" id="fileupload" name="fileupload" onchange="selectResource('fileupload',this)">
							<input id="fileuploadFileName" name="fileuploadFileName" value="" type="hidden">
							<input id="resourceType" name="resourceType" value="" type="hidden">
							<input id="setAdTemplateId" name="setAdTemplateId" value="1" type="hidden">
							<input id="adType" name="adType" value="B" type="hidden">
						</form>
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
		var areaCount = 0;
		var setResource;
		var adTemplateId;
		function getRandom(){//生成随机串  
			var random = ""; 
			for (var i = 1; i <= 16; i++) {  
		        var n = Math.floor(Math.random() * 16.0).toString(16);  
		        random += n;  
		        if ((i == 8) || (i == 12) || (i == 16) || (i == 20)) random += "";  
		    }

		    return random; 
		}
		$(document).ready(function(){
			//生成随机批次号
			$("#setAdBatchNo").val(getRandom());

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
			
			/*var zjxx_wrapper_height = $(".making_wrap").height() - 302 + "px";
			$(".zjxx_wrapper").css("height",zjxx_wrapper_height);*/
			
			var edit_content_height = $(".making_wrap").outerHeight() - $(".edit_wrap h3").outerHeight() - 12 + "px";
			$(".edit_content").css("height",edit_content_height);
			
			var add_content_height = $(".edit_content").height() - 10 + "px";
			$(".add_content").css("height",add_content_height);
			
			var zzbj_edit_width = $(".tabs_wrap").innerWidth() - $(".zzbj_left").width() - $(".added_compon").outerWidth() - 20 + "px";
			$(".zzbj_edit").css("width",zzbj_edit_width);
			
			//横版广告库li
			/*var horiLi = [{
				"path":"<%=ctx %>/images/hbImg1.png"
			},{
				"path":"<%=ctx %>/images/hbImg2.png"
			}];*/
			var horiLi = "";
			$.ajax({
				url: "<%=ctx %>/ad/adPackage!getTemplateList.do",
				cache : false,
				type: "POST",
				async: false,
				data: "templateType=1",
				success: function(data){
					horiLi = eval(data);
				}
			});
			var hori_li_html = "";
			for(var i = 0; i < horiLi.length; i ++){
				hori_li_html += "<li>" +
									 "<img src='" + horiLi[i].path + "'>" +
									 "<div class='shade_content clearfix'>" +
									 	"<input type='hidden' id='areaCount' value='" + horiLi[i].areaCount + "' />" +
									 	"<input type='hidden' id='adTemplateId' value='" + horiLi[i].tempId + "' />" +
									 	"<img class='ok_icon' src='<%=ctx %>/images/ok.png'>" +
									 "</div>" +
						   		"</li>";
			}
			$(".hori_li ul").html(hori_li_html);
			
			//广告列表鼠标移上移除的效果
			/*$(".tabs_lis li").hover(function(e){
				//if($(this).children(".shade_content").children("img").attr("src").indexOf("green") < 0){
				$(this).children(".shade_content").stop().toggle(500);
				//}
			});*/
			
			//选中时图片加边框，下一步按钮变蓝色
			$(".second_li .shade_content").click(function(e) {
				$(this).parent(".second_li").addClass("active");
				$(".next_btn").removeClass("next_btn").addClass("blue_btn");
			});
			
			//点击白色OK变成绿色
			$(".tabs_lis li").click(function(e) {
				if($(this).children(".shade_content").children("img").attr("src").indexOf("green") < 0){
					for(var i = 0;i < $(".tabs_lis .shade_content").length;i ++){
						if($($(".tabs_lis .shade_content")[i]).children("img").attr("src").indexOf("green") > 0){
							$($(".tabs_lis .shade_content")[i]).children("img").attr("src","<%=ctx %>/images/ok.png");
							$($(".tabs_lis .shade_content")[i]).hide();
						}
					}

					$(this).children(".shade_content").children("img").attr("src","<%=ctx %>/images/greenOk.png");
					$(this).children(".shade_content").show();
				}else{
					$(this).children(".shade_content").children("img").attr("src","<%=ctx %>/images/ok.png");
					$(this).children(".shade_content").hide();
				}
			});
			
			//添加素材li
			/*var scLi = [{
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
										 "<div class='set_con clearfix'><span>" + scLi[i].number + "</span><img src='<%=ctx %>/images/set.png'></div>" +
										 "</div>" +
									 	"<div class='time_wrap'>" +
											"<b>" + scLi[i].time + "</b>" +
											"<em class='time_h'></em><em class='time_m'></em><em class='time_s'></em>" +
									     "</div>" +
						   		"</li>";
			}
			$(".sc_content ul").html(sc_li_html);*/
						
			//弹出层广告库详情
			var scrollLi = [{
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
											"<a class='more_white' href='" + scrollLi[i].url + "'><img src='<%=ctx %>/images/moreWhite.png'></a>" +
										 "</div>" +
						   		  "</li>";
			}
			$(".first_con ul").html(scroll_li_html);
			
			//素材空间-图片li
			/*var addLi = [{
				"path":"<%=ctx %>/images/sucaiImg1.png",
				"title":"天空之城.jpg"
			},{
				"path":"<%=ctx %>/images/sucaiImg1.png",
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
			$(".sucai_lis ul").html(add_li_html);*/
			
			//素材空间-视频li
			/*var videoLi = [{
				"path":"<%=ctx %>/images/sucaiImg1.png",
				"title":"天空之城.mp4"
			},{
				"path":"<%=ctx %>/images/sucaiImg1.png",
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
			$(".video_lis ul").html(video_li_html);*/
			
			//本地上传
			/*var localTr = [{
				"number":"1",
				"title":"夏日冷饮.jpg",
				"percent":"",
				"speed":"1.6M",
				"state":"等待上传",
				"path":"<%=ctx %>/images/upBtn.png"
			},{
				"number":"2",
				"title":"夏日冷饮.jpg",
				"percent":"46%",
				"speed":"2.01M/s",
				"state":"等待上传",
				"path":"<%=ctx %>/images/startBtn.png"
			},{
				"number":"3",
				"title":"夏日冷饮.jpg",
				"percent":"24%",
				"speed":"2.01M/s",
				"state":"等待上传",
				"path":"<%=ctx %>/images/pauseBtn.png"
			}];
			var local_tr_html = "";
			for(var i = 0; i < localTr.length; i ++){
				local_tr_html += "<tr>" +
								"<th>" + localTr[i].number + "</th>" +
								"<td>" + localTr[i].title + "</td>" +
								"<td><em>" + localTr[i].percent + "</em><em>" + localTr[i].speed + "</em></td>" +
								"<td>" + localTr[i].state + "</td>" +
								"<td><span><img src='" + localTr[i].path + "'></span><span class='delete_td'><img src='<%=ctx %>/images/deleBtn.png'></span></td>" +
							"</tr>";
			}
			$(".table_box .table").html(local_tr_html);*/
			
			//音乐素材
			/*var musicLi = [{
				"path":"<%=ctx %>/images/musicImg1.png",
				"title":"天空之城.mp4"
			},{
				"path":"<%=ctx %>/images/musicImg1.png",
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
			$(".music_lis ul").html(music_li_html);*/
			
			//添加素材--设置--背景音乐
			/*var muLi = [{
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
			$(".add_music_alert ul").html(mu_li_html);*/
			
			
			//点击广告库显示横版广告弹出层
			$(".have_link").click(function(e) {
				$(".hb_alert").fadeIn(500);
			});
			
			//点击关闭按钮隐藏横版广告弹出层
			$(".scroll_alert .close_btn").click(function(e) {
				$(this).parent().parent().parent().fadeOut(500);
			});
			
			//广告列表鼠标移上移除的效果
			$(".second_li").hover(function(e){
				$(this).children(".shade_content").stop().toggle(500);
			});

			//添加素材--预览中图片滚动
			/*var prewLi = [{
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
			$(".hb_block_content ul").html(prew_li_html);
			
			imgScroll.rolling({
				name:'hb_block_content',
				width:'305px',
				height:'498px',
				direction:'left',
				speed:20,
				addcss:true
			});*/
			
			//点击预览
			$(".preview_icon img").click(function(e) {
				var prewLi = [];
				if(areaCount > 1){

				}else{
					for(var i = 0;i < $(".sc_content li").length;i ++){
						var image_path = {};
						image_path.path = $($(".sc_content li")[i]).find("#img_path").val();

						prewLi[i] = image_path;
					}
				}

				var prew_li_html = "";
				for(var i = 0; i < prewLi.length; i ++){
					prew_li_html += "<li>" +
										 "<img src='" + prewLi[i].path + "'>" +
							   		"</li>";
				}
				$(".hb_block_content ul").html(prew_li_html);
				imgScroll.rolling({
					name:'hb_block_content',
					width:'754px',
					height:'498px',
					direction:'left',
					speed:1,
					addcss:true
				});

				$(".pre_wrap").fadeIn(500);
			});
			
			//添加素材
			$(".add_icon img").click(function(e) {
				$(".space_alert").fadeIn(500);
				
				var imageLi = "";
				var videoLi = "";
				$.ajax({
					url: "<%=ctx %>/ad/adPackage!getLocalResource.do",
					cache : false,
					type: "POST",
					async: false,
					success: function(data){
						var dataList = eval(data);
						imageLi = dataList.image_list;
						videoLi = dataList.video_list;
						
						var add_li_html = "";
						for(var i = 0; i < imageLi.length; i ++){
							add_li_html += "<li>" +
												 "<img src='" + imageLi[i].path + "'>" +
												 "<input type='checkbox'>" +
												 "<span>" + imageLi[i].title + "</span>" +
									   		"</li>";
						}
						$(".sucai_lis ul").html(add_li_html);
						
						var video_li_html = "";
						for(var i = 0; i < videoLi.length; i ++){
							video_li_html += "<li>" +
												 "<video width='120' height='120' controls='controls'>" + 
												 " <source src='" + videoLi[i].path + "' type='video/mp4' />" +
												 "</video>" +
												 "<input type='checkbox'>" +
												 "<span>" + videoLi[i].title + "</span>" +
									   		"</li>";
						}
						$(".video_lis ul").html(video_li_html);
					}
				});
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

				var musicLi = "";
				$.ajax({
					url: "<%=ctx %>/ad/adPackage!getLocalMusicResource.do",
					cache : false,
					type: "POST",
					async: false,
					success: function(data){
						var dataList = eval(data);
						musicLi = dataList.music_list;
						
						var music_li_html = "";
						for(var i = 0; i < musicLi.length; i ++){
							music_li_html += "<li>" +
												 "<input type='hidden' id='music_path' value='" + musicLi[i].path + "' />" + 
												 "<img src='<%=ctx %>/images/musicImg1.png'>" +
												 "<input type='checkbox'>" +
												 "<span>" + musicLi[i].title + "</span>" +
									   		"</li>";
						}
						$(".music_lis ul").html(music_li_html);
					}
				});
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
					$("<li class='zj_li zj_li11 ui-widget-content ui-resizable'><img class='blue_close_btn' src='images/blueClose.png'></li>")
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
					$("<li class='zj_li zj_li22 ui-widget-content ui-resizable'><img class='blue_close_btn' src='images/blueClose.png'></li>")
					.click(function(e){
						$( ".zj_li22" ).draggable({ containment: ".zzbj_block_content", scroll: false });
						$(this).resizable({
							containment: ".zzbj_block_content",
							minWidth:70,
							minHeight:50,
							resize:callback22
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
					$("<li class='zj_li zj_li33 ui-widget-content ui-resizable'><img class='blue_close_btn' src='images/blueClose.png'></li>")
					.click(function(e){
						$( ".zj_li33" ).draggable({ containment: ".zzbj_block_content", scroll: false });
						$(this).resizable({
							containment: ".zzbj_block_content",
							minWidth:70,
							minHeight:50,
							resize:callback33
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
					$("<li class='zj_li zj_li44 ui-widget-content ui-resizable'><img class='blue_close_btn' src='images/blueClose.png'></li>")
					.click(function(e){
						$( ".zj_li44" ).draggable({ containment: ".zzbj_block_content", scroll: false});
						$(this).resizable({
							containment: ".zzbj_block_content",
							minWidth:70,
							minHeight:50,
							resize:callback44
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
					$("<li class='zj_li zj_li55 ui-widget-content ui-resizable'><img class='blue_close_btn' src='images/blueClose.png'></li>")
					.click(function(e){
						$( ".zj_li55" ).draggable({ containment: ".zzbj_block_content", scroll: false});
						$(this).resizable({
							containment: ".zzbj_block_content",
							minWidth:70,
							minHeight:50,
							resize:callback55
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
			
			//删除动态加载的组件
			$(document).on('click','.blue_close_btn',function(){
				$(this).parent().hide();
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
					$(".zj_li.active").css({"left":"240px","right":"auto"});
				}
			});
			
			//右对齐
			$(".direc_btn7").click(function(e) {
				if($(".zj_li").hasClass("active")){
					$(".zj_li.active").css({"right":"14px","left":"auto"});
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
					$(".zj_li.active").css({"top":"150px","bottom":"auto"});
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
				var block_left_width = $(".block_left").width();
				var block_left_height = $(".block_left").height();
				var block_right_width = $(".block_right").width();
				var block_right_height = $(".block_right").height();
				var block_right_position_left = $(".block_right").position().left;
				var zj_li11_position_left = $(".zj_li11").position().left;
				alert($(".zj_li11").position().left);
				if( zj_li11_position_left > block_right_position_left ){
					$(".zj_li11").animate(
					{
						"width":block_right_width,
						"height":block_right_height,
						"left":block_right_position_left,
						"top":"10px"
					},500);
				}else{
					$(".zj_li11").animate(
					{
						"width":block_left_width,
						"height":block_left_height,
						"left":"10px",
						"top":"10px"
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
						
						areaCount = parseInt($($(".shade_content .ok_icon")[i]).parent().children("#areaCount").val());
						adTemplateId = parseInt($($(".shade_content .ok_icon")[i]).parent().children("#adTemplateId").val());
					}
				}
				if(isSelected){
					$("#step_one").hide();
					$("#step_two").show();
					$(".title").html("制作布局");
					
					if(areaCount == 1){
						if(!$($(".block_content")[0]).is(":hidden")){
							$($(".block_content")[0]).html("<div class='block_left zzbj_block_content ui-widget-left ui-widget-content resizable1' style='width: 580px;'>区块1</div>");
							$($(".zzbj_btns_con")[0]).html("<span class='active'>区块1</span>");
						}
					}else{
						$($(".block_content")[0]).html("<div id='qk1' class='block_left ui-widget-left ui-widget-content resizable1' style='width: 270px;'>区块1</div>" +
							"<div id='qk2'  class='block_left ui-widget-right ui-widget-content resizable2' style='width: 270px;'>区块2</div>");
						$($(".zzbj_btns_con")[0]).html("<span class='active'>区块1</span><span>区块2</span>");
					}

					//制作布局--div大小的调节
					$(".ui-widget-left").click(function(e) {
						$(this).css("border","1px solid #3f9ce9");
						var x1 = $(this).offset().left.toFixed(2);
						$(".txt_x").val(x1);
						var y1 = $(this).offset().top.toFixed(2);
						$(".txt_y").val(y1);
						$(".txt_name").val("区块02");
					});
					$(".resizable1").resizable({
						maxWidth:580,
						maxHeight:380,
						resize:callback1
					});
					
					$(".ui-widget-right").click(function(e) {
						$(this).css("border","1px solid #3f9ce9");
						var x2 = $(this).offset().left.toFixed(2);
						$(".txt_x").val(x2);
						var y2 = $(this).offset().top.toFixed(2);
						$(".txt_y").val(y2);
						$(".txt_name").val("区块01");
					});
					$(".resizable2").resizable({
						maxWidth:580,
						maxHeight:380,
						resize:callback2
					});
				}else{
					alert("请选择一个类型");
				}
			}
			function stepTwo(){
				$('#step_two').hide();
				$('#step_three').show();
				$(".title").html("添加素材");
				
				$($(".zzbj_btns_con")[1]).html("");
				if(areaCount == 1){
					$($(".zzbj_btns_con")[1]).append("<span class='active'>区块1</span>");
				}else{
					$($(".zzbj_btns_con")[1]).append("<span class='active'>区块1</span><span>区块2</span>");

					$(".sc_content").html("<div id='area_place1'><ul class='clearfix'> </ul></div><div id='area_place2' style='display:none'><ul class='clearfix'> </ul></div>");
				}
				if($("li.zj_li22").length > 1){
					$($(".zzbj_btns_con")[1]).append("<span>日期</span>");
				}
				if($("li.zj_li33").length > 1){
					$($(".zzbj_btns_con")[1]).append("<span>时间</span>");
				}
				if($("li.zj_li44").length > 1){
					$($(".zzbj_btns_con")[1]).append("<span>天气</span>");
				}
				if($("li.zj_li55").length > 1){
					$($(".zzbj_btns_con")[1]).append("<span>音乐</span>");
				}

				$($(".zzbj_btns_con")[1]).children("span").click(function(){
					$(this).addClass("active").siblings().removeClass("active");
				});
				$($($(".zzbj_btns_con")[1]).children("span")[0]).click(function(){
					$("#area_place1").show();
					$("#area_place2").hide();
				});
				$($($(".zzbj_btns_con")[1]).children("span")[1]).click(function(){
					$("#area_place1").hide();
					$("#area_place2").show();
				});
			}
			function stepThree(){
				$('#step_three').hide();
				$('#step_four').show();
				$(".title").html("编辑完成");


				var prewLi = [];
				var prew_li_html = "";
				if(areaCount > 1){

					$(".preview_con .block_content").html("<div class='block_left' style='width:270px;'></div><div class='block_right' style='width:270px;'></div>");
				}else{
					for(var i = 0;i < $(".sc_content li").length;i ++){
						var image_path = {};
						image_path.path = $($(".sc_content li")[i]).find("#img_path").val();

						prewLi[i] = image_path;
					}

					$(".preview_con .block_content").html("<div class='show_select_list' style='width:580px;'><ul></ul></div>");
				}

				
				for(var i = 0; i < prewLi.length; i ++){
					prew_li_html += "<li>" +
										 "<img src='" + prewLi[i].path + "' height='380px'>" +
							   		"</li>";
				}
				$(".show_select_list ul").html(prew_li_html);
				imgScroll.rolling({
					name:'show_select_list',
					width:'580px',
					height:'380px',
					direction:'left',
					speed:1,
					addcss:true
				});
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

			function addResourceToList(){
				var number = $(".sc_content ul li").length;
				var sc_li_html = $(".sc_content ul").html();

				if(areaCount > 1){
					if(!$("#area_place1").is(":hidden")){
						number = $(".sc_content #area_place1 ul li").length;
						sc_li_html = $(".sc_content #area_place1 ul").html();
					}else{
						number = $(".sc_content #area_place2 ul li").length;
						sc_li_html = $(".sc_content #area_place2 ul").html();
					}
				}
				//获取选择的图片
				for(var i = 0;i < $($(".scroll_lis.sucai_lis")[0]).find("input[type='checkbox']").length;i ++){
					if($($($(".scroll_lis.sucai_lis")[0]).find("input[type='checkbox']")[i]).is(":checked")){
						number = number + 1;
						var path = $($($(".scroll_lis.sucai_lis")[0]).find("input[type='checkbox']")[i]).parent().children("img").attr("src");

						var title = $($($(".scroll_lis.sucai_lis")[0]).find("input[type='checkbox']")[i]).parent().children("span").text();

						sc_li_html += "<li>" +
									 "<div class='sc_con' style=\"background-image: url('" + path + "' );background-size: 158px 138px;\">" +
										 "<div class='set_con clearfix'><span>" + number + "</span><img src='<%=ctx %>/images/set.png'>" +
										 "<input type='hidden' id='img_name' name='img_name' value='" + title + "' />" +
										 "<input type='hidden' id='img_thumName' name='img_name' value='' />" +
										 "<input type='hidden' id='img_path' value='" + path + "' />" +
										 "<input type='hidden' id='img_time_h' value='00' />" +
										 "<input type='hidden' id='img_time_m' value='00' />" +
										 "<input type='hidden' id='img_time_s' value='15' />" +
										 "<input type='hidden' id='img_transitions' value='' />" +
										 "<input type='hidden' id='img_music' value='' />" +
										 "<input type='hidden' id='img_number' value='" + number + "' />" +
										 "<input type='hidden' id='img_type' value='1' />" +
										 "<input type='hidden' id='img_area_place' value='1' />" +
										 "</div></div>" +
									 	"<div class='time_wrap'>" +
											"<b>00:00:15</b>" +
											"<em class='time_h'></em><em class='time_m'></em><em class='time_s'></em>" +
											"<div class='h_time_con'>" + "<span></span>" + "</div>" +
											"<div class='m_time_con'>" + "<span></span>" + "</div>" +
											"<div class='s_time_con'>" + "<span></span>" + "</div>" +
									     "</div>" +
						   		"</li>";
					}
				}
				//获取选择的视频
				for(var i = 0;i < $(".scroll_lis.video_lis").find("input[type='checkbox']").length;i ++){
					if($($(".scroll_lis.video_lis").find("input[type='checkbox']")[i]).is(":checked")){
						number = number + 1;
						var path = $($(".scroll_lis.video_lis").find("input[type='checkbox']")[i]).parent().find("source").attr("src");

						var title = $($(".scroll_lis.video_lis").find("input[type='checkbox']")[0]).parent().find("span").text();

						sc_li_html += "<li>" +
										 "<div class='sc_con' style=\"background-image: url('<%=ctx %>/images/video.png' );background-size: 160px auto;\">" +
											 "<div class='set_con clearfix'><span>" + number + "</span><img src='<%=ctx %>/images/set.png'>" +
											 "<input type='hidden' id='img_name' name='img_name' value='" + title + "' />" +
											 "<input type='hidden' id='img_thumName' name='img_name' value='' />" +
											 "<input type='hidden' id='img_path' value='" + path + "' />" +
											 "<input type='hidden' id='img_time_h' value='00' />" +
										 	 "<input type='hidden' id='img_time_m' value='00' />" +
											 "<input type='hidden' id='img_time_s' value='50' />" +
											 "<input type='hidden' id='img_transitions' value='' />" +
											 "<input type='hidden' id='img_music' value='' />" +
											 "<input type='hidden' id='img_number' value='" + number + "' />" +
										 	 "<input type='hidden' id='img_type' value='2' />" +
										 	 "<input type='hidden' id='img_area_place' value='1' />" +
											 "</div></div>" +
										 	"<div class='time_wrap'>" +
												"<b>00:00:50</b>" +
												"<em class='time_h'></em><em class='time_m'></em><em class='time_s'></em>" +
												"<div class='h_time_con'>" + "<span></span>" + "</div>" +
												"<div class='m_time_con'>" + "<span></span>" + "</div>" +
												"<div class='s_time_con'>" + "<span></span>" + "</div>" +
										     "</div>" +
							   		"</li>";
				   	}
				}
				if(areaCount > 1){
					if(!$("#area_place1").is(":hidden")){
						$(".sc_content #area_place1 ul").html(sc_li_html);
					}else{
						$(".sc_content #area_place2 ul").html(sc_li_html);
					}
				}else{
					$(".sc_content ul").html(sc_li_html);
				}
				
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
				
				$(".set_con img").click(function(e) {
					$("#resourceName").val($(this).parent().children("#img_name").val());

					//设置复制到其他
					var option_html = $("#copyGoToType").html().trim();
					for(var i = 0;i < $("input[id='img_name']").length;i ++){
						option_html += "<option value='" + i + "'>" + $($("input[id='img_name']")[i]).val() + "</option>"
					}
					$("#copyGoToType").html(option_html);
					$("#copyBackgroundMusic").html(option_html);
					setResource = $(this);

					//添加本地音乐
					var muLi = "";
					$.ajax({
						url: "<%=ctx %>/ad/adPackage!getLocalMusicResource.do",
						cache : false,
						type: "POST",
						async: false,
						success: function(data){
							var dataList = eval(data);
							muLi = dataList.music_list;
							
							var mu_li_html = "";
							for(var i = 0; i < muLi.length; i ++){
								mu_li_html += "<li>" +
												 "<input type='hidden' id='music_path' value='" + muLi[i].path + "' />" + 
												 "<img src='<%=ctx %>/images/musicImg1.png'>" +
												 "<span>" + muLi[i].title + "</span>" +
									   		"</li>";
							}
							$(".add_music_alert ul").html(mu_li_html);

							$(".add_music_alert li").click(function(e) {
								$(this).addClass("active").siblings().removeClass("active");
							});
						}
					});
					

					$(".edit_alert").fadeIn(500);
				});

				//时
				$(".time_h").click(function(e) {
					$(".m_time_con").slideUp(500);
					$(".s_time_con").slideUp(500);
					$(".h_time_con").slideDown(500);
				});

				$(".h_time_con span").click(function(e) {
					var time = $(".time_wrap b").text();
					var one = time.indexOf(":");
					//var two = time.lastIndexOf(":");
					var setTime = time.substring(one);
					$(".time_wrap b").text($(this).text() + setTime);
					$(this).parent().parent().parent().find("#img_time_h").val($(this).text());

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
					var time = $(".time_wrap b").text();
					var one = time.indexOf(":");
					var two = time.lastIndexOf(":");
					var setTime1 = time.substring(0, one + 1);
					var setTime2 = time.substring(two);
					$(".time_wrap b").text(setTime1 + $(this).text() + setTime2);
					$(this).parent().parent().parent().find("#img_time_m").val($(this).text());

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
					var time = $(".time_wrap b").text();
					//var one = time.indexOf(":");
					var two = time.lastIndexOf(":");
					//var setTime1 = time.substring(0,one + 1);
					var setTime = time.substring(0, two + 1);
					$(".time_wrap b").text(setTime + $(this).text());
					$(this).parent().parent().parent().find("#img_time_s").val($(this).text());

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
						//$(".sc_con.active").parent().hide(500);
						$(".sc_con.active").parent().remove();
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
				//前移
				$(".down_btn1").click(function(e) {
					if($(".sc_con").hasClass("active")){
						var oldNumber = $(".sc_con.active").find("span").text();
						var newNumbet = $(".sc_con.active").parent().prev().find(".set_con").children("span").text();

						if(oldNumber > 1){
							$(".sc_con.active").find("span").text(newNumbet);
							$(".sc_con.active").find("#img_number").val(newNumbet);
							$(".sc_con.active").parent().prev().find(".set_con").children("span").text(oldNumber);
							$(".sc_con.active").parent().prev().find(".set_con").children("#img_number").val(oldNumber);
						}
						
						$(".sc_con.active").parent().prev().before($(".sc_con.active").parent());
					}
				});
				
				//后移
				$(".up_btn1").click(function(e) {
					if($(".sc_con").hasClass("active")){
						var oldNumber = $(".sc_con.active").find("span").text();
						var newNumbet = $(".sc_con.active").parent().next().find(".set_con").children("span").text();

						if(areaCount > 1){
							if(!$("#area_place1").is(":hidden")){
								if(oldNumber < $(".sc_content #area_place1 li").length){
									$(".sc_con.active").find("span").text(newNumbet);
									$(".sc_con.active").find("#img_number").val(newNumbet);
									$(".sc_con.active").parent().next().find(".set_con").children("span").text(oldNumber);
									$(".sc_con.active").parent().next().find(".set_con").children("#img_number").val(oldNumber);
								}
							}else{
								if(oldNumber < $(".sc_content #area_place2 li").length){
								$(".sc_con.active").find("span").text(newNumbet);
								$(".sc_con.active").find("#img_number").val(newNumbet);
								$(".sc_con.active").parent().next().find(".set_con").children("span").text(oldNumber);
								$(".sc_con.active").parent().next().find(".set_con").children("#img_number").val(oldNumber);
								}
							}
						}else{
							if(oldNumber < $(".sc_content li").length){
								$(".sc_con.active").find("span").text(newNumbet);
								$(".sc_con.active").find("#img_number").val(newNumbet);
								$(".sc_con.active").parent().next().find(".set_con").children("span").text(oldNumber);
								$(".sc_con.active").parent().next().find(".set_con").children("#img_number").val(oldNumber);
							}
						}
						$(".sc_con.active").parent().next().after($(".sc_con.active").parent());
					}
				});

				$('.scroll_alert .close_btn').click();
			}
			function selectProfileWrap(){
				for(var i = 0;i < $(".mode_li_con").length;i ++){
					if($(".mode_li_con")[i].className.indexOf("active") > 0){
						setResource.parent().children("#img_transitions").val(i + 1);
						break;
					}
				}

				for(var i = 0;i < $(".add_music_alert .search_wrap li").length;i ++){
					if($(".add_music_alert .search_wrap li")[i].className.indexOf("active") > -1){
						setResource.parent().children("#img_music").val($($(".add_music_alert .search_wrap li")[i]).children("input").val());
						break;
					}
				}
				$('.scroll_alert .close_btn').click();
			}

			function selectResource(fileIds,which){
				var imgPath = $("#fileupload").val();  
			    if (imgPath == "") {  
			        sAlert("请选择上传图片！");  
			        return;  
			    }

			    $("#fileuploadFileName").val(imgPath);
			    var resource_type = 0;
			    if(/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(imgPath)){ 
			    	resource_type = 1;
			    }else if(/\.(mp4)$/.test(imgPath)){
			    	resource_type = 2;
			    }else if(/\.(mp3)$/.test(imgPath)){
			    	resource_type = 3;
			    }
			    if(resource_type < 3){
			    	$("#resourceFileForm > #resourceType").val(resource_type);
				}else{
					$("#resourceMusicForm > #resourceType").val(resource_type);
				}
				$("#setAdTemplateId").val(adTemplateId);
				var localTr = new Array();
				//var number = $(".table_box .table tr").length;
				for(var i  = 0;i < which.files.length;i ++){
					var obj = {};
					//number ++;
					obj.number = "1";
					obj.title = which.files[i].name;
					obj.percent = "";
					obj.speed = "";
					obj.state = "等待上传";
					obj.path = "<%=ctx %>/images/upBtn.png";

					localTr[i] = obj;	
				}

				var local_tr_html = "";
				for(var i = 0; i < localTr.length; i ++){
					local_tr_html += "<tr>" +
									"<th>" + localTr[i].number + "</th>" +
									"<td>" + localTr[i].title + "</td>" +
									"<td><em>" + localTr[i].percent + "</em><em>" + localTr[i].speed + "</em></td>" +
									"<td>" + localTr[i].state + "</td>" +
									"<td><span id='uploadResource'><img src='" + localTr[i].path + "'></span><span class='delete_td'><img src='<%=ctx %>/images/deleBtn.png'></span></td>" +
								"</tr>";
				}
				if(resource_type < 3){
					$(".sckj_con .table_box .table").html(local_tr_html);
				}else{
					$(".sckj_con .music_con .table_box .table").html(local_tr_html);
				}

				$(".delete_td").click(function(e) {
					$(this).parent().parent("tr").hide(500);
				});
				$("#uploadResource").click(function(e) {
					var formData;
					if(resource_type < 3){
						formData = new FormData($("#resourceFileForm")[0]);
					}else{
						formData = new FormData($("#resourceMusicForm")[0]);
					}
					$.ajax({
						url: "<%=ctx %>/ad/adPackage!uploadResource.do",
				        type: "POST",
				        async:false,
						data: formData,
				        contentType: false,  
				        processData: false,
						success: function (data, status) {
							if (typeof (data.status) != 'undefined') {
								if (data.status == "true") {
									if(resource_type < 3){
										var number = $(".sc_content ul li").length + 1;
										var sc_li_html = $(".sc_content ul").html();

										if(areaCount > 1){
											if(!$("#area_place1").is(":hidden")){
												number = $(".sc_content #area_place1 ul li").length + 1;
												sc_li_html = $(".sc_content #area_place1 ul").html();
											}else{
												number = $(".sc_content #area_place2 ul li").length + 1;
												sc_li_html = $(".sc_content #area_place2 ul").html();
											}
										}

										sc_li_html += "<li>";
										if(resource_type == 1){
											sc_li_html += "<div class='sc_con' style=\"background-image: url('" + data.path + data.name + "' );background-size: 158px 138px;\">";
										}
										if(resource_type == 2){
											sc_li_html += "<div class='sc_con' style=\"background-image: url('<%=ctx %>/images/video.png' );background-size: 160px auto;\">";
										}
										sc_li_html += "<div class='set_con clearfix'><span>" + number + "</span><img src='<%=ctx %>/images/set.png'>" +
														 "<input type='hidden' id='img_name' name='img_name' value='" + data.name + "' />" +
														 "<input type='hidden' id='img_thumName' name='img_name' value='" + data.thumName + "' />" +
														 "<input type='hidden' id='img_path' value='" + data.path + "' />" +
														 "<input type='hidden' id='img_time_h' value='00' />" +
														 "<input type='hidden' id='img_time_m' value='00' />" +
														 "<input type='hidden' id='img_time_s' value='15' />" +
														 "<input type='hidden' id='img_transitions' value='' />" +
														 "<input type='hidden' id='img_music' value='' />" +
														 "<input type='hidden' id='img_number' value='" + number + "' />";
										if(resource_type == 1){
											sc_li_html += "<input type='hidden' id='img_type' value='1' />"
										}
										if(resource_type == 2){
											sc_li_html += "<input type='hidden' id='img_type' value='2' />"
										}
										sc_li_html += "<input type='hidden' id='img_area_place' value='1' />" +
														 "</div></div>" +
													 	"<div class='time_wrap'>" +
															"<b>00:00:15</b>" +
															"<em class='time_h'></em><em class='time_m'></em><em class='time_s'></em>" +
															"<div class='h_time_con'>" + "<span></span>" + "</div>" +
															"<div class='m_time_con'>" + "<span></span>" + "</div>" +
															"<div class='s_time_con'>" + "<span></span>" + "</div>" +
													     "</div>" +
										   		"</li>";
											
										//$(".sc_content ul").html(sc_li_html);
										if(areaCount > 1){
											if(!$("#area_place1").is(":hidden")){
												$(".sc_content #area_place1 ul").html(sc_li_html);
											}else{
												$(".sc_content #area_place2 ul").html(sc_li_html);
											}
										}else{
											$(".sc_content ul").html(sc_li_html);
										}

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
										
										$(".set_con img").click(function(e) {
											$("#resourceName").val($(this).parent().children("#img_name").val());

											//设置复制到其他
											var option_html = $("#copyGoToType").html().trim();
											for(var i = 0;i < $("input[id='img_name']").length;i ++){
												option_html += "<option value='" + i + "'>" + $($("input[id='img_name']")[i]).val() + "</option>"
											}
											$("#copyGoToType").html(option_html);
											$("#copyBackgroundMusic").html(option_html);
											setResource = $(this);

											//添加本地音乐
											var muLi = "";
											$.ajax({
												url: "<%=ctx %>/ad/adPackage!getLocalMusicResource.do",
												cache : false,
												type: "POST",
												async: false,
												success: function(data){
													var dataList = eval(data);
													muLi = dataList.music_list;
													
													var mu_li_html = "";
													for(var i = 0; i < muLi.length; i ++){
														mu_li_html += "<li>" +
																		 "<input type='hidden' id='music_path' value='" + muLi[i].path + "' />" + 
																		 "<img src='<%=ctx %>/images/musicImg1.png'>" +
																		 "<span>" + muLi[i].title + "</span>" +
															   		"</li>";
													}
													$(".add_music_alert ul").html(mu_li_html);

													$(".add_music_alert li").click(function(e) {
														$(this).addClass("active").siblings().removeClass("active");
													});
												}
											});
											$(".edit_alert").fadeIn(500);
										});

										//时
										$(".time_h").click(function(e) {
											$(".m_time_con").slideUp(500);
											$(".s_time_con").slideUp(500);
											$(".h_time_con").slideDown(500);
										});

										$(".h_time_con span").click(function(e) {
											var time = $(".time_wrap b").text();
											var one = time.indexOf(":");
											//var two = time.lastIndexOf(":");
											var setTime = time.substring(one);
											$(".time_wrap b").text($(this).text() + setTime);
											$(this).parent().parent().parent().find("#img_time_h").val($(this).text());

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
											var time = $(".time_wrap b").text();
											var one = time.indexOf(":");
											var two = time.lastIndexOf(":");
											var setTime1 = time.substring(0, one + 1);
											var setTime2 = time.substring(two);
											$(".time_wrap b").text(setTime1 + $(this).text() + setTime2);
											$(this).parent().parent().parent().find("#img_time_m").val($(this).text());

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
											var time = $(".time_wrap b").text();
											//var one = time.indexOf(":");
											var two = time.lastIndexOf(":");
											//var setTime1 = time.substring(0,one + 1);
											var setTime = time.substring(0, two + 1);
											$(".time_wrap b").text(setTime + $(this).text());
											$(this).parent().parent().parent().find("#img_time_s").val($(this).text());

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
												//$(".sc_con.active").parent().hide(500);
												$(".sc_con.active").parent().remove();
											}
										});
										
										//前移
										$(".down_btn").click(function(e) {
											if($(".sc_con").hasClass("active")){
												var oldNumber = $(".sc_con.active").find("span").text();
												var newNumbet = $(".sc_con.active").parent().prev().find(".set_con").children("span").text();

												if(oldNumber > 1){
													$(".sc_con.active").find("span").text(newNumbet);
													$(".sc_con.active").find("#img_number").val(newNumbet);
													$(".sc_con.active").parent().prev().find(".set_con").children("span").text(oldNumber);
													$(".sc_con.active").parent().prev().find(".set_con").children("#img_number").val(oldNumber);
												}
												
												$(".sc_con.active").parent().prev().before($(".sc_con.active").parent());
											}
										});
										
										//后移
										$(".up_btn").click(function(e) {
											if($(".sc_con").hasClass("active")){
												var oldNumber = $(".sc_con.active").find("span").text();
												var newNumbet = $(".sc_con.active").parent().next().find(".set_con").children("span").text();

												if(areaCount > 1){
													if(!$("#area_place1").is(":hidden")){
														if(oldNumber < $(".sc_content #area_place1 li").length){
															$(".sc_con.active").find("span").text(newNumbet);
															$(".sc_con.active").find("#img_number").val(newNumbet);
															$(".sc_con.active").parent().next().find(".set_con").children("span").text(oldNumber);
															$(".sc_con.active").parent().next().find(".set_con").children("#img_number").val(oldNumber);
														}
													}else{
														if(oldNumber < $(".sc_content #area_place2 li").length){
														$(".sc_con.active").find("span").text(newNumbet);
														$(".sc_con.active").find("#img_number").val(newNumbet);
														$(".sc_con.active").parent().next().find(".set_con").children("span").text(oldNumber);
														$(".sc_con.active").parent().next().find(".set_con").children("#img_number").val(oldNumber);
														}
													}
												}else{
													if(oldNumber < $(".sc_content li").length){
														$(".sc_con.active").find("span").text(newNumbet);
														$(".sc_con.active").find("#img_number").val(newNumbet);
														$(".sc_con.active").parent().next().find(".set_con").children("span").text(oldNumber);
														$(".sc_con.active").parent().next().find(".set_con").children("#img_number").val(oldNumber);
													}
												}
												$(".sc_con.active").parent().next().after($(".sc_con.active").parent());
											}
										});

										$(".delete_td").click();
										$('.scroll_alert .close_btn').click();
									}else{
										var mu_li_html = $(".add_music_alert ul").html();
										mu_li_html += "<li>" +
														 "<input type='hidden' id='music_path' value='" + data.path + "' />" + 
														 "<img src='<%=ctx %>/images/musicImg1.png'>" +
														 "<span>" + data.name + "</span>" +
											   		"</li>";
										
										$(".add_music_alert ul").html(mu_li_html);

										$(".add_music_alert li").click(function(e) {
											$(this).addClass("active").siblings().removeClass("active");
										});
									}
								}else{
									return alert('Failed to upload logo!');
								}
							}else{
								return alert('Failed to upload logo!');
							}
						},
						error: function (data, status, e) {
							return alert('Failed to upload logo!');
						}
					});
				});
			}

			function submitAllAd(status){
				var ad_list = [];
				if(areaCount > 1){

				}else{
					for(var i = 0;i < $(".sc_content li").length;i ++){
						var ad_info = {};
						ad_info.img_name = $($(".sc_content li")[i]).find("#img_name").val();
						ad_info.adResourcePath = $($(".sc_content li")[i]).find("#img_path").val();
						ad_info.adResourceTimeH = $($(".sc_content li")[i]).find("#img_time_h").val();
						ad_info.adResourceTimeM = $($(".sc_content li")[i]).find("#img_time_m").val();
						ad_info.adResourceTimeS = $($(".sc_content li")[i]).find("#img_time_s").val();
						ad_info.adResourceTransitions = $($(".sc_content li")[i]).find("#img_transitions").val();
						ad_info.adResourceMusic = $($(".sc_content li")[i]).find("#img_music").val();
						ad_info.adResourceTypes = $($(".sc_content li")[i]).find("#img_type").val();
						ad_info.adResourceNumber = $($(".sc_content li")[i]).find("#img_number").val();
						ad_info.adResourceAreaPlace = $($(".sc_content li")[i]).find("#img_area_place").val();
						ad_info.adResourceThumbnailPath = $($(".sc_content li")[i]).find("#img_thumName").val();

						ad_list[i] = JSON.stringify(ad_info);
					}
				}

				var adPackageName = $("#setAdName").val();
				var adPackageBatchNo = $("#setAdBatchNo").val();
				$.ajax({
					url: "<%=ctx%>/ad/adPackage!putAllAdInfo.do",
					type: "POST",
					data: "parm=" + ad_list + "&adPackageName=" + adPackageName + "&adPackageBatchNo=" + adPackageBatchNo + "&adTemplateId=" + adTemplateId + "&adType=H&adStatus=" + status,
					async: false,
					success: function(data){
						if(data.status == "true"){
							if(status == "0"){
								window.location.reload();
							}else{
								window.location.href = "<%=ctx%>/ad/adPackage!toReleaseHbAd.do?adPackageId=" + data.adPackageId;
							}
						}
					}
				})
			}
			function  getTemplitByFbl(){
				
				var fbl=$('#fbl option:selected') .val();
				alert(fbl);
				var horiLi = "";
				$.ajax({
					url: "<%=ctx %>/ad/adPackage!getTemplateList.do?fbl="+fbl,
					cache : false,
					type: "POST",
					async: false,
					data: "templateType=1",
					success: function(data){
						horiLi = eval(data);
					}
				});
				var hori_li_html = "";
				for(var i = 0; i < horiLi.length; i ++){
					hori_li_html += "<li>" +
										 "<img src='" + horiLi[i].path + "'>" +
										 "<div class='shade_content clearfix'>" +
										 	"<input type='hidden' id='areaCount' value='" + horiLi[i].areaCount + "' />" +
										 	"<input type='hidden' id='adTemplateId' value='" + horiLi[i].tempId + "' />" +
										 	"<img class='ok_icon' src='<%=ctx %>/images/ok.png'>" +
										 "</div>" +
							   		"</li>";
				}
				$(".hori_li ul").html(hori_li_html);
			}
	</script>
</body>
</html>
