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
<!-- 信息 -->
    <div class="alert_wrap alert_add">
        <input type="hidden" id="imei"
            value="<s:property value="imei"/>">
        <div class="alert_content alert_more_content">
            <h3>
                <ul class="alert_sub_type">
                    <li class="active" type="1">基本信息</li>
                    <li type="2">日程安排</li>
                </ul>
                <a href="#" onclick="closeLayer()"><img
                    src="<%=path%>/images/close.png"></a>
            </h3>
            <section id="type_body"> </section>
    </div>

    <script type="text/javascript">

    $(function(){
        $("#type_body").load("etbClientDeviceAction!gotoMore_Base.do?imei="+$("#imei").val());
    })
    $(".alert_sub_type li").click(function(event) {
        $(".alert_sub_type li.active").removeClass('active');
        $(this).addClass('active');
        var type = $(this).attr('type');
        if(type=='1'){
            $("#type_body").load("etbClientDeviceAction!gotoMore_Base.do?imei="+$("#imei").val());
         }
        if(type=='2'){
            $("#type_body").load('template/equ/schedule.html?_dt=' + new Date().getTime());
        }
    });

    </script>
</body>
</html>