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
<title>设备维护人员登录</title>
<link rel="stylesheet" href="<%=path%>/css/common1.css">
<link rel="stylesheet" href="<%=path%>/css/barstyle.css">
<link rel="stylesheet" href="<%=path%>/js/skin/default/layer.css">
<link rel="stylesheet" href="<%=path%>/js/skin/mylayer.css">		
<link rel="stylesheet" href="<%=path%>/css/slider.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/css/jquery.range.css">
<link rel="stylesheet" href="<%=path%>/css/laypage.css">

</head>
<body class="grey">
    <div class="container">
        <%@include file="/top.jsp"%>
        <div class="login_left">
            <ul>
                <!--<li class="li1">制作广告</li>
                <li class="li2">发布广告</li>
                li class="li3">发布审核</li>
                <!--<li class="li4">发布记录</li>
                <li class="li5">广告库</li>-->
                <li class="li6 active">设备管理</li>
                <!--<li class="li7">权限管理</li>-->
            </ul>
        </div>
        <!--login_left end-->
        <div class="login_middle">
            <h3>设备管理</h3>
            <div class="search_wrap clearfix">
                <form action="">
                    <strong class="clearfix"> <label for="">筛选:</label> <select id="select_province">
                            <option value="0">请选择</option>
                            <s:iterator value="provinces" id="province">
                                <option value="<s:property value='#province.areaCode'/>"><s:property value='#province.areaName' /></option>
                            </s:iterator>
                    </select> <select id="select_city">
                            <option value="0">请选择</option>
                    </select> <select id="select_country" name="etbClientDevice.areaId">
                            <option value="0">请选择</option>
                    </select>
                    </strong> <strong class="clearfix"> <label for="">分辨率:</label> <select name="resolution" id="resolution">
                            <option value="">请选择</option>
                            <option value="600*800">600*800</option>
                            <option value="800*1000">800*1000</option>
                            <option value="1000*1200">1000*1200</option>
                    </select>
                    </strong> <strong class="clearfix"> <label for="">设备状态:</label> <select name="" id="select_status">
                            <option value="0">所有状态</option>
                            <option value="1">正常开启</option>
                            <option value="2">当天未登录</option>
                            <option value="3">多天未登录</option>
                    </select>
                    </strong> <a onclick="demo()"><strong class="search_btn"><img src="<%=path%>/images/search.png"></strong></a>
                </form>
            </div>
            <!--search_wrap end-->
            <div class="btn_wrapper clearfix">
                <a href="#" class="batch_btn" id="batch_btn">批量远程操作</a> <span class="clearfix" id="batch_content"> <em class="clearfix"><a
                        href="#">电源</a><a href="#" class="off"><img src="<%=path%>/images/off.png"></a><a href="#" class="restart"><img
                            src="<%=path%>/images/restart.png"></a></em> <em class="clearfix"><a href="#">播放</a><a href="#" class="play"><img
                            src="<%=path%>/images/play.png"></a><a href="#" class="pause"><img src="<%=path%>/images/pause.png"></a></em> <em
                    class="clearfix"><a href="#">声音</a>
                        <div class="progress">
                            <div class="range_box">
                                <input id='top_range' type="range" value="0">
                            </div>
                        </div></em>
                </span>
            </div>
            <div class="equipment_lists">
                <ul id="tbodyByPage">
                </ul>
            </div>
            <div class="pagenation clearfix" id="byPage"></div>
            <!--pagenation end-->
        </div>
        <!--login_middle end-->
    </div>
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.range.js"></script>
    <script type="text/javascript" src="<%=path%>/js/RangeSlider.js"></script>
    <script type="text/javascript" src="<%=path%>/js/layer.js"></script>
    <script type="text/javascript" src="<%=path%>/js/laypage.js"></script>
    <script type="text/javascript">

        function demo(curr) {
            $.ajax({
                type : "post",
                url : "etbClientDeviceAction!getDeviceList.do",
                data : {
                    page : curr || 1,
                    "etbClientDevice.areaId" : $("#select_country").val(),
                    "resolution" : $("#resolution").val(),
                    "deviceProperty.status" : $("#select_status").val()
                },
                datatype : "json",
                success : function(res) {
                    document.getElementById("tbodyByPage").innerHTML = "";
                    var list1 = res.list;//得到用户信息
                    for (var i = 0; i < list1.length; i++) {
                        document.getElementById("tbodyByPage").innerHTML += "<li>"
                            + "<input class='equipment_checkbox' style='display:none;' type='checkbox' name=''>"
                            + "<h2 class='clearfix'><strong>"
                            + list1[i].imei
                            + "</strong><span><em class='green'></em></span></h2>"
                            + "<div>" + list1[i].name + "</div>"
                            + "<div>" + list1[i].ip + "</div>"
                            + "<div>最近推送:<em>" + list1[i].time + "</em></div>"
                            + "<div><a class='remote' id=" + list1[i].imei + "><img src='<%=path%>/images/remote.png'></a><a  class='more' id="
                            + list1[i].imei
                            + "><img src='<%=path%>/images/more.png'></a><a  class='setting' id="
                            + list1[i].imei
                            + "><img src='<%=path%>/images/setting.png'></a></div>"
                            + "<section class='mask' style='display: none;'><div class='mask_content'><div class='mask_item_0'><span class='label'>"
                            + "电源</span><span class='op'><a href='#'><img src='<%=path%>/images/off.png'></a><a href='#'><img src='<%=path%>/images/restart.png'></a></span></div><div class='mask_item_1'>"
                            + "<span class='label'>播放</span><span class='op'><a href='#'><img src='<%=path%>/images/play.png'></a><a href='#'><img src='<%=path%>/images/pause.png'></a></span></div><div class='mask_item_2'>"
                            + "<span class='label'>声音</span><span class='op'><div class='mask_item_raido' style=''><div class='range_box'><input type='range' value='0'></div></div></span></div><div><button class='op_btn'>"
                            + "确定</button><button class='op_btn'>取消</button></div></div></section>"+"</li>";
                    }
    
                    laypage({
                        cont: 'byPage', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                        pages: res.totalPage, //通过后台拿到的总页数
                        curr: curr || 1, //当前页
                        jump: function(obj, first){ //触发分页后的回调
                            if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                demo(obj.curr);
                                }
                            }
                    });
                    }
                })
        };
        demo();

        var addUser; 
        function closeLayer(){
            layer.close(addUser)
        }

        $(document).ready(function(){
            //初始化批量操作音量插件
            $('#top_range').RangeSlider(
                {
                    min: 0,
                    max: 100,
                    step: 0.1,
                    callback: function(){}
            });
            //声音调节进度条
            // var action=true;
            // $.playBar.addBar($('.progress'),1000*60);//第一个参数是需要显示播放器的容器，第二个参数为时间，单位毫秒
            // $.playBar.changeBarColor("#fff");//设置进度条颜色
            //右侧高度
            var height = $(".login_middle").height($(window).height() - $(".login_top").height()) + "px";
            $(".login_middle").css("height",height);
        });
    
        /*批量操作*/
        $("#batch_btn").click(function(){
            if($(this).hasClass('active')){
                $("div.equipment_lists .equipment_checkbox").hide();
                $("#batch_content").removeClass('active');
                $(this).removeClass('active');
            }else{
                $("div.equipment_lists .equipment_checkbox").show();
                $("#batch_content").addClass('active');
                $(this).addClass('active');
            }
        });

        //设置
        $(document).on("click",'.setting',function(){
            $.get('etbClientDeviceAction!gotoSetting.do?imei='+$(this).attr("id"),function(result){
                addUser = layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    shadeClose: true,
                    area: ['900px', '700px'],
                    content: result
                });
            })
        });

        //右侧高度
        var height = $(".login_middle").height($(window).height() - $(".login_top").height()) + "px";
        $(".login_middle").css("height",height);
        /*远程操作*/
        $(document).on('click', '.remote', function(){
            $(this).parents('li').find('section').show();
            $(this).parents('li').find('input[type=range]').RangeSlider({
                        min: 0,
                        max: 100,
                        step: 0.1,
                        callback: function(){}
            });
        });

        $(document).on("mouseleave", '.mask', function(){
            $(this).hide();
        });

        $(document).on('click', '.more', function(){
            $.get('etbClientDeviceAction!gotoMore.do?imei='+$(this).attr("id"), function(result){
                addUser = layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    shadeClose: true,
                    area: ['900px', '670px'],
                    content: result
                    });
                });
        });

        $("#select_province").change(function(){
            $("#select_city option").remove();
            if($(this).val()==0){
                $("#select_country option").remove()
                $("#select_city").append("<option value='0'>请选择</option>");
                $("#select_country").append("<option value='0'>请选择</option>");
                return ;
            }
            $.ajax({
                url:"etbClientDeviceAction!getCityByProvinceCode.do",
                type:"post",
                data:{"areaCode":$(this).val()},
                datatype:"json",
                success:function(data){
                    var list=data.cityList;
                    for(var i=0;i<list.length;i++){
                        $("#select_city").append("<option value="+list[i].areaCode+">"+list[i].areaName+"</option>");
                    }
                    $("#select_city").change();
                }
            })
        })

        $("#select_city").change(function(){
            $("#select_country option").remove();
            $.ajax({
                url:"etbClientDeviceAction!getCountryByCityCode.do",
                type:"post",
                data:{"areaCode":$(this).val()},
                datatype:"json",
                success:function(data){
                    var list=data.cityList;
                    for(var i=0;i<list.length;i++){
                        $("#select_country").append("<option value="+list[i].areaId+">"+list[i].areaName+"</option>");
                    }
                }
            })
        })
    </script>
</body>
</html>
