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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>东慧E通宝平台管理系统</title>

<link href="themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="style/common.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="js/speedup.js" type="text/javascript"></script>
<![endif]-->

<script src="js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.bgiframe.js" type="text/javascript"></script>
<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.v2.1.0.min.js"></script>

<script src="xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="chart/raphael.js"></script>
<script type="text/javascript" src="chart/g.raphael.js"></script>
<script type="text/javascript" src="chart/g.bar.js"></script>
<script type="text/javascript" src="chart/g.line.js"></script>
<script type="text/javascript" src="chart/g.pie.js"></script>
<script type="text/javascript" src="chart/g.dot.js"></script>

<script src="js/dwz.core.js" type="text/javascript"></script>
<script src="js/dwz.util.date.js" type="text/javascript"></script>
<script src="js/dwz.validate.method.js" type="text/javascript"></script>
<script src="js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="js/dwz.barDrag.js" type="text/javascript"></script>
<script src="js/dwz.drag.js" type="text/javascript"></script>
<script src="js/dwz.tree.js" type="text/javascript"></script>
<script src="js/dwz.accordion.js" type="text/javascript"></script>
<script src="js/dwz.ui.js" type="text/javascript"></script>
<script src="js/dwz.theme.js" type="text/javascript"></script>
<script src="js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="js/dwz.navTab.js" type="text/javascript"></script>
<script src="js/dwz.tab.js" type="text/javascript"></script>
<script src="js/dwz.resize.js" type="text/javascript"></script>
<script src="js/dwz.dialog.js" type="text/javascript"></script>
<script src="js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="js/dwz.cssTable.js" type="text/javascript"></script>
<script src="js/dwz.stable.js" type="text/javascript"></script>
<script src="js/dwz.taskBar.js" type="text/javascript"></script>
<script src="js/dwz.ajax.js" type="text/javascript"></script>
<script src="js/dwz.pagination.js" type="text/javascript"></script>
<script src="js/dwz.database.js" type="text/javascript"></script>
<script src="js/dwz.datepicker.js" type="text/javascript"></script>
<script src="js/dwz.effects.js" type="text/javascript"></script>
<script src="js/dwz.panel.js" type="text/javascript"></script>
<script src="js/dwz.checkbox.js" type="text/javascript"></script>
<script src="js/dwz.history.js" type="text/javascript"></script>
<script src="js/dwz.combox.js" type="text/javascript"></script>
<script src="js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="js/menu.tree.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("dwz.frag.xml", {
		loginUrl:"login_dialog.html", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>
</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="http://j-ui.com">标志</a>
				<ul class="nav">	
					<li><a href="#" target="dialog" width="600">设置</a></li>				
					<li><a href="#">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!--<li theme="red"><div>红色</div></li>-->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->
			
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>E通宝主菜单</h2><div>收缩</div></div>

				<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>业务管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="#">终端应用管理</a>
								<ul> 
									<li><a href="<%=path%>/app/appTypesAction!listAppTypesInfo.do" rel="listAppTypes" target="navTab"">应用分类管理</a></li>
									<li><a href="<%=path%>/app/appAction!listAppInfo.do" rel="listApp" target="navTab"">应用基本信息管理</a></li>
									<li><a href="<%=path%>/app/appAction!checkAppInfoList.do" rel="listAppCheck" target="navTab"">应用的审核</a></li>
									<li><a href="<%=path%>/app/appAction!checkedAppInfoList.do" rel="listAppUp" target="navTab"">应用的发布</a></li>									
								</ul>
							</li>
							<li><a href="#">广告业务管理</a>
								<ul>
									<li><a href="<%=path%>/ad/adPackage!listAd.do" rel="listAd" target="navTab">广告包管理</a></li>
									<li><a href="<%=path%>/ad/adPackage!listAdCheck.do" rel="listAdCheck" target="navTab">广告的审核</a></li>
									<li><a href="<%=path%>/ad/adPackage!listAdPublish.do" rel="listAdPublish" target="navTab">广告的发布</a></li>									
								</ul>
							</li>							
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>设备管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">							
							<li><a href="#">终端应用管理</a>
								<ul>

									<li><a href="<%=path%>/device/etbClientDeviceAction!listDeviceInfo.do" target="navTab" rel="deviceid">终端设备信息管理</a></li>
									<li><a href="#">终端设备监控管理</a></li>															
								</ul>
							</li>
							<li><a href="#">平台设备管理</a>
								<ul>
									<li><a href="<%=path%>/device/etbServerDeviceAction!listDeviceInfo.do" target="navTab" rel="serverid">平台设备信息管理</a></li>								
								</ul>
							</li>
							<li><a href="#">OTA管理</a>
								<ul>
									<li><a href="<%=path%>/ota/versioninfo!getOtaVersionInfoList.do" target="navTab" rel="otamanger">OTA信息管理</a></li>
									<li><a href="<%=path%>/ota/versioninfo!getOtaAuditList.do" target="navTab" rel="otamanger">OTA的审核</a></li>	
									<li><a href="<%=path%>/ota/versioninfo!getOtaReleaseList.do" target="navTab" rel="otamanger">OTA的发布</a></li>															
								</ul>
							</li>							
						</ul>						
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>信息采集管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="#">广告信息采集</a></li>
							<li><a href="#">彩票信息采集</a></li>
							<li><a href="#">缴费信息采集</a></li>
						</ul>
					</div>
					<div class="accordionHeader">
						<h2><span>Folder</span>用户管理</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree">
							<li><a href="<%=path%>/user/clientUserInfo!listClientUserInfo.do" rel="listClientUserInfo" target="navTab"">终端用户管理</a></li>
							<li><a href="<%=path%>/user/userInfo!listUserInfo.do" rel="listUserInfo" target="navTab">平台用户管理</a></li>
							<li><a href="<%=path%>/user/menuInfo!showMenuTree.do" rel="showMenuTree" target="navTab">系统菜单管理</a></li>
							<li><a href="<%=path%>/user/roleInfo!listRoleInfo.do" rel="listRoleInfo" target="navTab">角色管理</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<h2><a href="doc/dwz-user-guide.pdf" target="_blank">DWZ框架使用手册(PDF)</a></h2>
								<a href="doc/dwz-user-guide.swf" target="_blank">DWZ框架演示视频</a>
							</div>
							<div class="right">
								<p><a href="doc/dwz-user-guide.zip" target="_blank" style="line-height:19px">DWZ框架使用手册(CHM)</a></p>
								<p><a href="doc/dwz-ajax-develop.swf" target="_blank" style="line-height:19px">DWZ框架Ajax开发视频教材</a></p>
							</div>
							<p><span>DWZ富客户端框架</span></p>
							<p>DWZ官方微博:<a href="http://weibo.com/dwzui" target="_blank">http://weibo.com/dwzui</a></p>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:230px">
							
							<p style="color:red">DWZ官方微博 <a href="http://weibo.com/dwzui" target="_blank">http://weibo.com/dwzui</a></p>
							<p style="color:red">DWZ官方微群 <a href="http://q.weibo.com/587328/invitation=11TGXSt-148c2" target="_blank">http://q.weibo.com/587328/invitation=11TGXSt-148c2</a></p>

<div class="divider"></div>
<h2>dwz v1.2视频教程:</h2>
<p><a href="http://www.u-training.com/thread-57-1-1.html" target="_blank">http://www.u-training.com/thread-57-1-1.html</a></p>

<div class="divider"></div>
<h2>DWZ系列开源项目:</h2>
<div class="unit"><a href="http://code.google.com/p/dwz/" target="_blank">dwz富客户端框架 - jUI</a></div>
<div class="unit"><a href="http://code.google.com/p/dwz4j" target="_blank">dwz4j(Java Web)快速开发框架 + jUI整合应用</a></div>
<div class="unit"><a href="http://code.google.com/p/dwz4php" target="_blank">ThinkPHP + jUI整合应用</a></div>
<div class="unit"><a href="http://code.google.com/p/dwz4php" target="_blank">Zend Framework + jUI整合应用</a></div>
<div class="unit"><a href="http://www.yiiframework.com/extension/dwzinterface/" target="_blank">YII + jUI整合应用</a></div>

<div class="divider"></div>
<h2>常见问题及解决:</h2>
<pre style="margin:5px;line-height:1.4em">
Error loading XML document: dwz.frag.xml
直接用IE打开index.html弹出一个对话框：Error loading XML document: dwz.frag.xml
原因：没有加载成功dwz.frag.xml。IE ajax laod本地文件有限制, 是ie安全级别的问题, 不是框架的问题。
解决方法：部署到apache 等 Web容器下。
</pre>

<div class="divider"></div>
<h2>有偿服务请联系:</h2>
<pre style="margin:5px;line-height:1.4em;">
定制化开发，公司培训，技术支持
合作电话：010-52897073
邮箱：support@dwzjs.com
</pre>
						</div>
						
						<div style="width:230px;position: absolute;top:60px;right:0" layoutH="80">
							<iframe width="100%" height="430" class="share_self"  frameborder="0" scrolling="no" src="http://widget.weibo.com/weiboshow/index.php?width=0&height=430&fansRow=2&ptype=1&skin=1&isTitle=0&noborder=1&isWeibo=1&isFans=0&uid=1739071261&verifier=c683dfe7"></iframe>
						</div>
					</div>
					
				</div>
			</div>
		</div>

	</div>

	<div id="footer">Copyright &copy; 2013 <a href="demo_page2.html" target="dialog">Archermind团队</a> Tel：027-52897073</div>



</body>
</html>