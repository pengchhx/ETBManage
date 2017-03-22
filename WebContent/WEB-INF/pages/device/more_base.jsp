<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <section class="type_content" style="height: 510px;">
        <div class="more_info clearfix">
            <button class="online_broadcast" id="onlive">实时画面</button>
            <div class="item">
                <div class="left_label">终端名称</div>
                <div class="right_box">
                    <i class="line"></i> <span><s:property value="etbClientDevice.name" /></span>
                </div>
        </div>
        <div class="item">
            <div class="left_label">终端编号</div>
                <div class="right_box">
                    <i class="line"></i> <span><s:property value="etbClientDevice.imei" /></span>
                </div>
        </div>
        <div class="item">
            <div class="left_label">
                    地<span class="hidden">地址</span>址
            </div>
            <div class="right_box">
                <i class="line"></i>
                <span>
                    <s:property value="etbClientDevice.province" />
                    <s:property value="etbClientDevice.city" />
                    <s:property value="etbClientDevice.country" />
                </span>
            </div>
        </div>
        <div class="item">
            <div class="left_label">终端状态</div>
                <div class="right_box">
                    <i class="line"></i> <span></span>
                </div>
            </div>
            <div class="item">
                <div class="left_label">
                    分<span class="hidden">*</span>辨<span class="hidden">*</span>率
                </div>
                <div class="right_box">
                    <i class="line"></i> <span><s:property value="etbClientDevice.deviceProperty.resolution" /></span>
                </div>
            </div>
            <!-- <div class="item"> -->
            <%-- <div class="left_label">创<span class="hidden">-</span>建<span class="hidden">*</span>人</div> --%>
            <!-- <div class="right_box"> -->
            <!-- <i class="line"></i> -->
            <%-- <span><s:property value="etbClientDevice"/></span> --%>
            <!-- </div> -->
            <!-- </div>	 -->
            <!-- <div class="item"> -->
            <!-- <div class="left_label">所属机构</div> -->
            <!-- <div class="right_box"> -->
            <!-- <i class="line"></i> -->
            <%-- <span></span> --%>
            <!-- </div> -->
            <!-- </div>	 -->
            <div class="item">
                <div class="left_label">更新时间</div>
                <div class="right_box">
                    <i class="line"></i> <span><s:date name="etbClientDevice.ut" format="yyyy-MM-dd" /></span>
                </div>
            </div>
            <div class="item">
                <div class="left_label">
                    备<span class="hidden">备注</span>注
                </div>
                <div class="right_box">
                    <i class="line"></i> <span><s:property value="etbClientDevice.tips" /></span>
                </div>
            </div>
        </div>
    </section>

    <script type="text/javascript">
        $("#onlive").click(function(){})
    </script>

</body>
</html>