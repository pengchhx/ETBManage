<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div id="linechartttt" class="lineChart" 
	valueData='<s:property value="lineDataValue"/>' xLength="560"
	yLength="200" contrast="2" axisystep="9"  arr="['广告','  应用','  OTA','  缴费','  彩票','应用分类']"
	style="height: 230px; width: 720px; float: left;"></div>
<div style="float: right; width: 130px; margin-top: 50px; height: 90px;">
	<span style="background-image: url('./images/adException.bmp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>：广告异常<br>
	<span style="background-image: url('./images/AppException.bmp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>：应用异常<br>
	<span style="background-image: url('./images/OTAException.bmp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>：OTA异常
	<br> <span
		style="background-image: url('./images/payException.bmp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>：缴费异常<br>
	<span style="background-image: url('./images/lotteryException.bmp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>：彩票异常<br>
	<span style="background-image: url('./images/AppItemException.bmp');">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>：应用分类异常<br>
</div>

<script type="text/javascript">
	$(function() {
		$("div.lineChart", $(this)).chart();
	});

	
</script>