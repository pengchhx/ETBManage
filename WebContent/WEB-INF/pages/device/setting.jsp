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
    <div class="alert_wrap alert_add">
        <div class="alert_content alert_setting_content">
            <h3>
                <ul class="alert_sub_type">
                    <li class="active" type="1">基本信息</li>
                    <li type="2">开关机时间</li>
                    <li type="3">显示设置</li>
                    <li type="4">音量设置</li>
                    <li type="5">系统设置</li>
                </ul>
                <a href="#" onclick="closeLayer()"><img src="<%=path%>/images/close.png"></a>
            </h3>
            <form action="etbClientDeviceAction!editEtbClientDevice.do" method="post" id="editForm">
                <input type="hidden" name="deviceProperty.id" value="<s:property value="etbClientDevice.deviceProperty.id"/>" id="devicePropertyId" />
                <input type="hidden" name="city1" value="etbClientDevice.cityCode" /> <input type="hidden" name="country1"
                    value="etbClientDevice.areaId" /> <input type="hidden" id="property_volume" value="etbClientDevice.deviceProperty.volume" />
                <section id="type_body"> <!-- 基本信息 --> <section class="type_content" style="height: 540px;" type="1">
                <div class="base_info clearfix">
                    <div class="item">
                        <div class="left_label">终端名称:</div>
                        <div class="right_box">
                            <input type="text" name="etbClientDevice.name" value="<s:property value="etbClientDevice.name"/>" />
                        </div>
                    </div>
                    <div class="item">
                        <div class="left_label">终端编号:</div>
                        <div class="right_box">
                            <input class="disabel" type="text" name="etbClientDevice.imei" value="<s:property value="etbClientDevice.imei"/>" />
                        </div>
                    </div>
                    <div class="item">
                        <div class="left_label">
                            地<span class="hidden">端编</span>址:
                        </div>
                        <div class="right_box">
                            <div class="clearfix">
                                <select id="select_province1">
                                    <option>请选择</option>
                                    <s:iterator value="provinces" id="province">
                                        <option value="<s:property value='#province.areaCode'/>"
                                            <s:if test="#province.areaName==etbClientDevice.province">
                                                selected='selected'
                                                </s:if>><s:property
                                                value='#province.areaName' />
                                        </option>
                                    </s:iterator>
                                </select> <select id="select_city1">
                                    <option>请选择</option>
                                </select> <select id="select_country1" name="etbClientDevice.areaId">
                                    <option>请选择</option>
                                </select>
                            </div>
                            <br> <input type="text" name="etbClientDevice.Address" value="<s:property value='etbClientDevice.Address'/>" />
                        </div>
                    </div>
                    <div class="item">
                        <div class="left_label">联系电话:</div>
                        <div class="right_box">
                            <input type="text" name="etbClientDevice.tel" value="<s:property value="etbClientDevice.tel"/>" />
                        </div>
                    </div>
                    <div class="item">
                        <div class="left_label">
                            备<span class="hidden">端编</span>注:
                        </div>
                        <div class="right_box">
                            <textarea class="note" name="etbClientDevice.tips"><s:property value="etbClientDevice.tips" /></textarea>
                        </div>
                    </div>
                </div>
                </section> <!-- 开关机设置 --> <!-- 开关机 --> <section class="type_content" style="height: 540px;" type="2">
                <div class="time_setting clearfix">
                    <div class="item time_header">
                        <label><input type="radio" name="every" checked value="day">每日重复</label> <label><input type="radio"
                            name="every" value="week">每周重复</label>
                        <!-- <select>
                            <option>212</option>
                            <option>212</option>
                            <option>212</option>
                        </select> -->
                    </div>
                </div>
                <div class="item setting_content" id="setting_time">
                    <section class="show_box" type='1'>
                    <table class="setting_table">
                        <tr>
                            <td class="index_1">序号</td>
                            <td class="index_2">开机时间</td>
                            <td class="index_3">关机时间</td>
                            <td class="index_4"></td>
                        </tr>
                        <s:if test="etbClientDevice.deviceTimeList.size()!=0">
                            <s:iterator value="etbClientDevice.deviceTimeList" id="time" status="index">
                                <tr class="edit_tr" index='<s:property value="#index.index+1"/>'>
                                    <td><s:property value="#index.index+1" /></td>
                                    <td><span class="input_box"> <input class="first" readonly="true" type="text"
                                            name="deviceDayTimeList[<s:property value="#index.index+1"/>].openHour"
                                            value="<s:property value="#time.openHour"/>" /> <img class="input_arrow"
                                            src="<%=path%>/images/blue_arrow.png">
                                            <div type='h' class="select_time_box" style="display: none">
                                                <header>时</header>
                                                <section>
                                                <ul>
                                                </ul>
                                                </section>
                                            </div>
                                    </span> <span class="input_box"> <input class="first" type="text"
                                            name="deviceDayTimeList[<s:property value="#index.index+1"/>].openMin"
                                            value="<s:property value="#time.openMin"/>" /> <img class="input_arrow"
                                            src="<%=path%>/images/blue_arrow.png">
                                            <div type='m' class="select_time_box select_time_box_2" style="display: none">
                                                <header>分</header>
                                                <section>
                                                <ul>
                                                </ul>
                                                </section></span>
                                    <td><span class="input_box"> <input class="second" readonly="true" type="text"
                                            name="deviceDayTimeList[<s:property value="#index.index+1"/>].closeHour"
                                            value="<s:property value="#time.closeHour"/>" /> <img class="input_arrow"
                                            src="<%=path%>/images/blue_arrow.png">
                                            <div type='h' class="select_time_box" style="display: none">
                                                <header>时</header>
                                                <section>
                                                <ul>
                                                </ul>
                                                </section>
                                            </div>
                                    </span> <span class="input_box"> <input class="second" type="text"
                                            name="deviceDayTimeList[<s:property value="#index.index+1"/>].closeMin"
                                            value="<s:property value="#time.closeMin"/>" /> <img class="input_arrow"
                                            src="<%=path%>/images/blue_arrow.png">
                                            <div type='m' class="select_time_box select_time_box_2" style="display: none">
                                                <header>分</header>
                                                <section>
                                                <ul>
                                                </ul>
                                                </section></span></td>
                                    <td class="index_4"><a href="#" class="op_delete"><img src="<%=path%>/images/y_delete.png"></a></td>
                                </tr>
                            </s:iterator>
                        </s:if>
                        <s:else>
                            <tr class="edit_tr" index='1'>
                                <td>1</td>
                                <td><span class="input_box"> <input class="first" readonly="true" type="text"
                                        name="deviceDayTimeList[0].openHour" value="00" /> <img class="input_arrow"
                                        src="<%=path%>/images/blue_arrow.png">
                                        <div type='h' class="select_time_box" style="display: none">
                                            <header>时</header>
                                            <section>
                                            <ul>
                                            </ul>
                                            </section>
                                        </div>
                                </span> <span class="input_box"> <input class="first" type="text" name="deviceDayTimeList[0].openMin" value="00" /> <img
                                        class="input_arrow" src="<%=path%>/images/blue_arrow.png">
                                        <div type='m' class="select_time_box select_time_box_2" style="display: none">
                                            <header>分</header>
                                            <section>
                                            <ul>
                                            </ul>
                                            </section></span>
                                <td><span class="input_box"> <input class="second" readonly="true" type="text"
                                        name="deviceDayTimeList[0].closeHour" value="00" /> <img class="input_arrow"
                                        src="<%=path%>/images/blue_arrow.png">
                                        <div type='h' class="select_time_box" style="display: none">
                                            <header>时</header>
                                            <section>
                                            <ul>
                                            </ul>
                                            </section>
                                        </div>
                                </span> <span class="input_box"> <input class="second" type="text" name="deviceDayTimeList[0].closeMin" value="00" />
                                        <img class="input_arrow" src="<%=path%>/images/blue_arrow.png">
                                        <div type='m' class="select_time_box select_time_box_2" style="display: none">
                                            <header>分</header>
                                            <section>
                                            <ul>
                                            </ul>
                                            </section></span></td>
                                <td class="index_4"><a href="#" class="op_delete"><img src="<%=path%>/images/y_delete.png"></a></td>
                            </tr>
                        </s:else>
                        <tr id="op_tr1">
                            <td colspan="3"><a href="#" id='add_setting_time' class="op_add"><img src="<%=path%>/images/add_time.png">新增</a>
                            </td>
                        </tr>
                    </table>
                    </section>
                    <section class="show_box" type="2" style="display:none">
                    <table id='week_table'>
                        <tr>
                            <th class="index_00">序号</th>
                            <th class="index_01">星期一</th>
                            <th class="index_02">星期二</th>
                            <th class="index_03">星期三</th>
                            <th class="index_04">星期四</th>
                            <th class="index_05">星期五</th>
                            <th class="index_06">星期六</th>
                            <th class="index_07">星期天</th>
                        </tr>
                        <tbody>
                            <tr class="week_edit_tr" index='1'>
                                <td>
                                    <div class="num">1</div>
                                    <div class="type">
                                        <ol>
                                            <li>开机</li>
                                            <li>关机</li>
                                        </ol>
                                    </div>
                                </td>
                                <td type='1'>
                                    <div class="time">
                                        <div class="start">
                                            <input type="hidden" name="deviceWeekTimeList[0].weekTime" value="1" /><input type="text" value="00"
                                                name="deviceWeekTimeList[0].openHour" maxlength="2">：<input type="text" value="00"
                                                name="deviceWeekTimeList[0].openMin" maxlength="2">
                                        </div>
                                        <div class="end">
                                            <input type="text" value="00" name="deviceWeekTimeList[0].closeHour" maxlength="2">：<input
                                                type="text" value="00" name="deviceWeekTimeList[0].closeMin" maxlength="2">
                                        </div>
                                    </div>
                                    <div class="op">
                                        <img src="<%=path%>/images/y_delete.png">
                                    </div>
                                </td>
                                <td type='2'>
                                    <div class="time">
                                        <div class="start">
                                            <input type="hidden" name="deviceWeekTimeList[1].weekTime" value="2" /><input type="text" value="00"
                                                name=deviceWeekTimeList[1].openHour maxlength="2">：<input type="text" value="00"
                                                name="deviceWeekTimeList[1].openMin" maxlength="2">
                                        </div>
                                        <div class="end">
                                            <input type="text" value="00" name="deviceWeekTimeList[1].closeHour" maxlength="2">：<input
                                                type="text" value="00" name="deviceWeekTimeList[1].closeMin" maxlength="2">
                                        </div>
                                    </div>
                                    <div class="op">
                                        <img src="<%=path%>/images/y_delete.png">
                                    </div>
                                </td>
                                <td type='3'>
                                    <div class="time">
                                        <div class="start">
                                            <input type="hidden" name="deviceWeekTimeList[2].weekTime" value="3" /><input type="text" value="00"
                                                name="deviceWeekTimeList[2].openHour" maxlength="2">：<input type="text" value="00"
                                                name="deviceWeekTimeList[2].openMin" maxlength="2">
                                        </div>
                                        <div class="end">
                                            <input type="text" value="00" name="deviceWeekTimeList[2].closeHour" maxlength="2">：<input
                                                type="text" value="00" name="deviceWeekTimeList[2].closeMin" maxlength="2">
                                        </div>
                                    </div>
                                    <div class="op">
                                        <img src="<%=path%>/images/y_delete.png">
                                    </div>
                                </td>
                                <td type='4'>
                                    <div class="time">
                                        <div class="start">
                                            <input type="hidden" name="deviceWeekTimeList[3].weekTime" value="4" /><input type="text" value="00"
                                                name="deviceWeekTimeList[3].openHour" maxlength="2">：<input type="text" value="00"
                                                name="deviceWeekTimeList[3].openMin" maxlength="2">
                                        </div>
                                        <div class="end">
                                            <input type="text" value="00" name="deviceWeekTimeList[3].closeHour" maxlength="2">：<input
                                                type="text" value="00" name="deviceWeekTimeList[3].closeMin" maxlength="2">
                                        </div>
                                    </div>
                                    <div class="op">
                                        <img src="<%=path%>/images/y_delete.png">
                                    </div>
                                </td>
                                <td type='5'>
                                    <div class="time">
                                        <div class="start">
                                            <input type="hidden" name="deviceWeekTimeList[4].weekTime" value="5" /><input type="text" value="00"
                                                name="deviceWeekTimeList[4].openHour" maxlength="2">：<input type="text" value="00"
                                                name="deviceWeekTimeList[4].openMin" maxlength="2">
                                        </div>
                                        <div class="end">
                                            <input type="text" value="00" name="deviceWeekTimeList[4].closeHour" maxlength="2">：<input
                                                type="text" value="00" name="deviceWeekTimeList[4].closeMin" maxlength="2">
                                        </div>
                                    </div>
                                    <div class="op">
                                        <img src="<%=path%>/images/y_delete.png">
                                    </div>
                                </td>
                                <td type='6'>
                                    <div class="time">
                                        <div class="start">
                                            <input type="hidden" name="deviceWeekTimeList[5].weekTime" value="6" /><input type="text" value="00"
                                                name="deviceWeekTimeList[5].openHour" maxlength="2">：<input type="text" value="00"
                                                name="deviceWeekTimeList[5].openMin" maxlength="2">
                                        </div>
                                        <div class="end">
                                            <input type="text" value="00" name="deviceWeekTimeList[5].closeHour" maxlength="2">：<input
                                                type="text" value="00" name="deviceWeekTimeList[5].closeMin" maxlength="2">
                                        </div>
                                    </div>
                                    <div class="op">
                                        <img src="<%=path%>/images/y_delete.png">
                                    </div>
                                </td>
                                <td type='7'>
                                    <div class="time">
                                        <div class="start">
                                            <input type="hidden" name="deviceWeekTimeList[6].weekTime" value="7" /><input type="text" value="00"
                                                name="deviceWeekTimeList[6].openHour" maxlength="2">：<input type="text" value="00"
                                                name="deviceWeekTimeList[6].openMin" maxlength="2">
                                        </div>
                                        <div class="end">
                                            <input type="text" value="00" name="deviceWeekTimeList[6].closeHour" maxlength="2">：<input
                                                type="text" value="00" name="deviceWeekTimeList[6].closeMin" maxlength="2">
                                        </div>
                                    </div>
                                    <div class="op">
                                        <img src="<%=path%>/images/y_delete.png">
                                    </div>
                                </td>
                            </tr>
                            <tr class="op_tr">
                                <td></td>
                                <td type='1' class=""><a href="#" class="add_time_2"><img src="<%=path%>/images/add_time.png">新增</a></td>
                                <td type='2'><a href="#" class="add_time_2"><img src="<%=path%>/images/add_time.png">新增</a></td>
                                <td type='3'><a href="#" class="add_time_2"><img src="<%=path%>/images/add_time.png">新增</a></td>
                                <td type='4'><a href="#" class="add_time_2"><img src="<%=path%>/images/add_time.png">新增</a></td>
                                <td type='5'><a href="#" class="add_time_2"><img src="<%=path%>/images/add_time.png">新增</a></td>
                                <td type='6'><a href="#" class="add_time_2"><img src="<%=path%>/images/add_time.png">新增</a></td>
                                <td type='7'><a href="#" class="add_time_2"><img src="<%=path%>/images/add_time.png">新增</a></td>
                            </tr>
                        </tbody>
                    </table>
                    </section>
                </div>
                </section> <!-- 显示设置 --> <section class="type_content" style="height: 540px;" type="3">
                <div class="display_setting clearfix">
                    <div class="tips">
                        <span class="star">*</span>数值为0-100的整数
                    </div>
                    <div class="item">
                        <div class="left_label">
                            色<span class="hidden">端编</span>彩:
                        </div>
                        <div class="right_box">
                            <input type="text" name="deviceProperty.color" id='color' readonly="true"
                                value="<s:property value="etbClientDevice.deviceProperty.color"/>" />
                            <div class="slider_box">
                                <input type="hidden" class="slider-input" id='colorSlider'
                                    value="<s:property value="etbClientDevice.deviceProperty.color"/>" />
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="left_label">
                            亮<span class="hidden">端编</span>度:
                        </div>
                        <div class="right_box">
                            <input value="<s:property value="etbClientDevice.deviceProperty.brightness"/>" id='light' type="text" readonly="true"
                                name="deviceProperty.brightness" />
                            <div class="slider_box">
                                <input type="hidden" class="slider-input" id='lightSlider'
                                    value="<s:property value="etbClientDevice.deviceProperty.brightness"/>" />
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="left_label">
                            对<span class="hidden">*</span>比<span class="hidden">*</span>度:
                        </div>
                        <div class="right_box">
                            <input value="<s:property value="etbClientDevice.deviceProperty.contrast"/>" id='compare' type="text" readonly="true"
                                name="deviceProperty.contrast" />
                            <div class="slider_box">
                                <input type="hidden" class="slider-input" id='compareSlider'
                                    value="<s:property value="etbClientDevice.deviceProperty.contrast"/>" />
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="left_label">
                            色<span class="hidden">端编</span>调:
                        </div>
                        <div class="right_box">
                            <input value="<s:property value="etbClientDevice.deviceProperty.tone"/>" id='tinge' type="text" readonly="true"
                                name="deviceProperty.tone" />
                            <div class="slider_box">
                                <input type="hidden" class="slider-input" id='tingeSlider'
                                    value="<s:property value="etbClientDevice.deviceProperty.tone"/>" />
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="left_label">
                            锐<span class="hidden">端编</span>度:
                        </div>
                        <div class="right_box">
                            <input value="<s:property value="etbClientDevice.deviceProperty.sharpness"/>" id='acuity' type="text" readonly="true"
                                name="deviceProperty.sharpness" />
                            <div class="slider_box">
                                <input type="hidden" class="slider-input" id='acuitySlider'
                                    value="<s:property value="etbClientDevice.deviceProperty.sharpness"/>" />
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="left_label">
                            色<span class="hidden">端编</span>温:
                        </div>
                        <div class="right_box">
                            <input type="hidden" id="property_colorTemperature"
                                value="<s:property value="etbClientDevice.deviceProperty.colorTemperature"/>" /> <select
                                name="deviceProperty.colorTemperature" id="select_colorTemperature">
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                            </select>
                        </div>
                    </div>
                    <div class="item">
                        <a class="reset_btn" id="setDefault1">恢复默认</a>
                    </div>
                </div>
                </section> <!-- 音量--> <section class="type_content" style="height: 540px;" type="4">
                <div class="voice_setting clearfix">
                    <div class="item time_header">
                        <label>默认音量</label> <select name="select_volume" id="">
                            <option>10</option>
                            <option>9</option>
                            <option>8</option>
                            <option>7</option>
                            <option>6</option>
                            <option>5</option>
                            <option>4</option>
                            <option>3</option>
                            <option>2</option>
                            <option>1</option>
                            <option>0</option>
                        </select>
                    </div>
                </div>
                <div class="item setting_content" id="setting_voice">
                    <table class="setting_table">
                        <tr>
                            <td class="voice_1">序号</td>
                            <td class="voice_2">开机时间</td>
                            <td class="voice_3">关机时间</td>
                            <td class="voice_4">音量</td>
                            <td class="voice_5"></td>
                        </tr>
                        <s:if test="etbClientDevice.deviceVolumeList.size()!=0">
                            <s:iterator value="etbClientDevice.deviceVolumeList" id="volume" status="index">
                                <tr class="edit_tr" index="<s:property value='#index.index+1'/>">
                                    <td><s:property value='#index.index+1' /></td>
                                    <td><span class="input_box"> <input class="first" readonly="true" type="text"
                                            value="<s:property value='#volume.openHour'/>"
                                            name="deviceVolumeList[<s:property value='#index.index+1'/>].openHour" /> <img class="input_arrow"
                                            src="<%=path%>/images/blue_arrow.png">
                                            <div type='h' class="select_time_box" style="display: none">
                                                <header>时</header>
                                                <section>
                                                <ul>
                                                </ul>
                                                </section>
                                            </div>
                                    </span> <span class="input_box"> <input class="first" type="text" value="<s:property value='#volume.openMin'/>"
                                            name="deviceVolumeList[<s:property value='#index.index+1'/>].openMin" /> <img class="input_arrow"
                                            src="<%=path%>/images/blue_arrow.png">
                                            <div type='m' class="select_time_box select_time_box_2" style="display: none">
                                                <header>分</header>
                                                <section>
                                                <ul>
                                                </ul>
                                                </section></span></td>
                                    <td><span class="input_box"> <input class="second" readonly="true" type="text"
                                            value="<s:property value='#volume.closeHour'/>"
                                            name="deviceVolumeList[<s:property value='#index.index+1'/>].closeHour" /> <img class="input_arrow"
                                            src="<%=path%>/images/blue_arrow.png">
                                            <div type='h' class="select_time_box" style="display: none">
                                                <header>时</header>
                                                <section>
                                                <ul>
                                                </ul>
                                                </section>
                                            </div>
                                    </span> <span class="input_box"> <input class="second" type="text" value="<s:property value='#volume.closeMin'/>"
                                            name="deviceVolumeList[<s:property value='#index.index+1'/>].closeMin" /> <img class="input_arrow"
                                            src="<%=path%>/images/blue_arrow.png">
                                            <div type='m' class="select_time_box select_time_box_2" style="display: none">
                                                <header>分</header>
                                                <section>
                                                <ul>
                                                </ul>
                                                </section></span></td>
                                    <td class="slider_td"><span class="input_box_2"> <input class="thred" type="text" name="voice"
                                            value="<s:property value='#volume.value'/>"
                                            name="deviceVolumeList[<s:property value='#index.index+1'/>].value"> <img class="input_arrow"
                                            src="<%=path%>/images/down_arrow.png">
                                            <div class="slider_box" style="display: none">
                                                <input type="hidden" class="slider-input voiceSlider" value="<s:property value='#volume.value'/>"
                                                    name="deviceVolumeList[<s:property value='#index.index+1'/>].value" />
                                            </div>
                                    </span></td>
                                    <td class="voice_5"><a href="#" class="op_delete"><img src="<%=path%>/images/y_delete.png"></a></td>
                                </tr>
                            </s:iterator>
                        </s:if>
                        <s:else>
                            <tr class="edit_tr" index='1'>
                                <td>1</td>
                                <td><span class="input_box"> <input class="first" readonly="true" type="text"
                                        name="deviceVolumeList[0].openHour" value="00" /> <img class="input_arrow"
                                        src="<%=path%>/images/blue_arrow.png">
                                        <div type='h' class="select_time_box" style="display: none">
                                            <header>时</header>
                                            <section>
                                            <ul>
                                            </ul>
                                            </section>
                                        </div>
                                </span> <span class="input_box"> <input class="first" type="text" name="deviceVolumeList[0].openMin" value="00" /> <img
                                        class="input_arrow" src="<%=path%>/images/blue_arrow.png">
                                        <div type='m' class="select_time_box select_time_box_2" style="display: none">
                                            <header>分</header>
                                            <section>
                                            <ul>
                                            </ul>
                                            </section></span></td>
                                <td><span class="input_box"> <input class="second" readonly="true" type="text"
                                        name="deviceVolumeList[0].closeHour" value="00" /> <img class="input_arrow"
                                        src="<%=path%>/images/blue_arrow.png">
                                        <div type='h' class="select_time_box" style="display: none">
                                            <header>时</header>
                                            <section>
                                            <ul>
                                            </ul>
                                            </section>
                                        </div>
                                </span> <span class="input_box"> <input class="second" type="text" name="deviceVolumeList[0].closeMin" value="00" /> <img
                                        class="input_arrow" src="<%=path%>/images/blue_arrow.png">
                                        <div type='m' class="select_time_box select_time_box_2" style="display: none">
                                            <header>分</header>
                                            <section>
                                            <ul>
                                            </ul>
                                            </section></span></td>
                                <td class="slider_td"><span class="input_box_2"> <input class="thred" type="text" name="voice" value="50"
                                        name="deviceVolumeList[0].value"> <img class="input_arrow" src="<%=path%>/images/down_arrow.png">
                                        <div class="slider_box" style="display: none">
                                            <input type="hidden" class="slider-input voiceSlider" value="50" name="deviceVolumeList[0].value" />
                                        </div>
                                </span></td>
                                <td class="voice_5"><a href="#" class="op_delete"><img src="<%=path%>/images/y_delete.png"></a></td>
                            </tr>
                        </s:else>
                        <tr id="op_tr">
                            <td colspan="4"><a href="#" id='add_tr' class="op_add"><img src="<%=path%>/images/add_time.png">新增</a></td>
                        </tr>
                    </table>
                </div>
                </section> <!-- 系统--> <section class="type_content" style="height: 540px;" type="5">
                <div class="sys_setting clearfix">
                    <div class="item time_header">
                        <label>语<span class="hidden">言言</span>言
                        </label> <input type="hidden" id="property_language" value="<s:property value="etbClientDevice.deviceProperty.language"/>" /> <select
                            name="deviceProperty.language" id="select_language">
                            <option value="cn">中文</option>
                            <option value="en">英文</option>
                        </select>
                    </div>
                    <div class="item time_header clock_display " style="">
                        <label>时钟显示</label> <input type="hidden" id="property_timeShow"
                            value="<s:property value="etbClientDevice.deviceProperty.timeShow"/>" /> <select name="deviceProperty.timeShow"
                            id="select_timeShow">
                            <option value="top">上</option>
                            <option value="down">下</option>
                            <option value="left">左</option>
                            <option value="right">右</option>
                            <option value="close">常关</option>
                        </select>
                    </div>
                </div>
                </section>
                <div class="btns clearfix">
                    <a onclick='document.getElementById("editForm").submit()' class="save_btn">保存</a><a href="#" onclick="closeLayer()">取消</a>
                </div>
                </section>
            </form>
        </div>
        <script type="text/javascript">
$(function(){
    $(".type_content").hide();
    $(".type_content[type=1]").show();
    
    
    $("#select_language option").each(function(){
        if($(this).val()==$("#property_language").val()){
            $(this).attr("selected","selected");
        }
    })
    
    $("#select_timeShow option").each(function(){
        if($(this).val()==$("#property_timeShow").val()){
            $(this).attr("selected","selected");
        }
    })
    
    $("#select_colorTemperature option").each(function(){
        if($(this).val()==$("#property_colorTemperature").val()){
            $(this).attr("selected","selected");
        }
    })
    
    $("#select_volume option").each(function(){
        if($(this).val()==$("#property_volume").val()){
            $(this).attr("selected","selected");
        }
    })
    
})

//TAB切换
    $(".alert_sub_type li").click(function(event) {
        $(".alert_sub_type li.active").removeClass('active');
        $(this).addClass('active');
        
        var type=$(this).attr("type");
        $(".type_content").hide();
        $(".type_content[type="+type+"]").show();


    });
    

//地区下拉框
$("#select_province1").change(function(){
    $("#select_city1 option").remove();
    $.ajax({
        url:"etbClientDeviceAction!getCityByProvinceCode.do",
        type:"post",
        data:{"areaCode":$(this).val()},
        datatype:"json",
        success:function(data){            
            var list=data.cityList;        
            for(var i=0;i<list.length;i++){
                $("#select_city1").append("<option value="+list[i].areaCode+">"+list[i].areaName+"</option>");
            }                                
            $("#select_city1 option").each(function(){
                if($(this).val()==$("#city1").val()){
                    $(this).attr("selected","selected");
                }
            })
            
            $("#select_city1").change();
        }                
    })
})



$("#select_city1").change(function(){
    $("#select_country1 option").remove();
    $.ajax({
        url:"etbClientDeviceAction!getCountryByCityCode.do",
        type:"post",
        data:{"areaCode":$(this).val()},
        datatype:"json",
        success:function(data){            
            var list=data.cityList;        
            for(var i=0;i<list.length;i++){
                $("#select_country1").append("<option value="+list[i].areaId+">"+list[i].areaName+"</option>");
            }                                
            $("#select_country1 option").each(function(){
                if($(this).val()==$("#country1").val()){
                    $(this).attr("selected","selected");
                }
            })
            
        }                
    })
})

 $("#select_province1").change();
 
// $(document).on('click', '#add_setting_time', function(){
//                 var index='';
//                 if($('.edit_tr').size() < 1){
//                     index = 1;
//                 }else{
//                     index =  1 + parseInt($('.edit_tr').last().attr('index'));
//                 } 
<%--                   var _html = '<tr class="edit_tr" index="'+index+'"><td>'+index+'</td><td><input class="first" type="text" name="deviceDayTimeList['+index+'].openTime"></td><td><input class="second" type="text" name="deviceDayTimeList['+index+'].closeTime"></td><td class="index_4"><a href="#" class="op_delete"><img src="<%=path%>/images/y_delete.png"></a></td></tr>'; --%>
//                 $("#op_tr1").before(_html);

//     });

//     $(document).on('click', '.op_delete', function(){
//                 $(this).parents('tr').remove();

//     });

//-------------------------------------------------------------------------------------------------------------



            /*每周增加*/

            $("#week_table").on("click", '.add_time_2',function () {
                var $this = $(this);
                var type=$this.parent('td').attr('type');
                var i=0
                $("#week_table tr.week_edit_tr td[type="+type+"]").each(function(){
                  if($('img',$(this)).size()>0){
                      i++;

                      return;
                  }else{
                      var _html = '<div class="time"><div class="start"><input type="text" name="" maxlength="2">：<input type="text" name="" maxlength="2"></div><div class="end"><input type="text" name=""  maxlength="2">：<input type="text" name=""  maxlength="2"></div></div><div class="op"><img src="<%=path%>/images/y_delete.png"></div>';
                      $(this).html(_html);
                      return false;
                  }
                });
                if(i== $("#week_table tr.week_edit_tr td[type="+type+"]").size()){
                    var index = $("#week_table tr.week_edit_tr").last().attr('index');

                    var _tr_html = '<tr  class="week_edit_tr" index="'+(parseInt(index)+1)+'"><td><div class="num">'+(parseInt(index)+1)+'</div><div class="type"><ol><li>开机</li><li>关机</li></ol></div></td>';
                    var _td_html
                    for(var i = 1; i <=7; i ++){
                        if(type == i){
                                var _html = '<td type="'+i+'"> <div class="time"><div class="start"><input type="text" name="" maxlength="2">：<input type="text" name="" maxlength="2"></div><div class="end"><input type="text" name=""  maxlength="2">：<input type="text" name=""  maxlength="2"></div></div><div class="op"><img src="<%=path%>/images/y_delete.png"></div></td>';
                                _td_html += _html;
                        }else{

                            _td_html += '<td type="'+i+'"> </td>';

                        }
                        

                    }
                     _tr_html = _tr_html + _td_html + "</tr>";
                    $("#week_table .op_tr").before(_tr_html);
                }


            });

            /*每周删除*/
            $("#week_table").on("click",'.op img', function(){

                $(this).parents('td').empty();

            })
            /*展示时间设置*/
             $("#setting_time").on('hover', '.input_box', function(){ 
                $(".input_arrow",this).toggleClass("active")
                var select_box =  $(this).find("div.select_time_box") ;
                if(select_box.attr("type") == "h"){
                    var val  = $("input[type=text]" , this).val();
                    var h_html = ""
                    for(var i = 0 ; i < 3; i++){
                        for(var j = 0; j < 10 ; j ++){
                                var li 
                               
                                if(val == (i +""+ j )){
                                     li = "<li class='active' id=''>"+ i + j +" </li>";
                                }else{
                                    li = "<li class='' id=''>"+ i + j +" </li>"
                                }
                                
                                if(i == 2 && j > 3){
                                    continue;
                                }
                                h_html += li;

                        }
                    }
                    $("section ul" , select_box).html(h_html);


                }else if(select_box.attr("type") == "m"){
                    var m_html = ""
                    var val  = $("input[type=text]" , this).val();
                    for(var i = 0 ; i < 6; i++){
                        for(var j = 0; j < 10 ; j ++){
                                var li 
                               
                                if(val == (i +""+ j )){
                                     li = "<li class='active' id=''>"+ i + j +" </li>";
                                }else{
                                    li = "<li class='' id=''>"+ i + j +" </li>"
                                }
                                 
                                m_html += li;

                        }
                    }
                    $("section ul" , select_box).html(m_html);


                }
                select_box.toggle();

        });

 
     /*添加行*/
     $('#setting_time').on('click', '#add_setting_time', function(){
         
         var index = ''
             if($('.edit_tr').size() < 1){
                 index = 1;
             }else{
                    index =  1 + parseInt($('.edit_tr').last().attr('index'));
             } 
                var _html1 =  '<span class="input_box">' + 
                                '<input class="first" readonly="true" type="text" value="00" name="deviceDayTimeList['+index+'].openHour"/>'+
                                '<img  class="input_arrow" src="<%=path%>/images/blue_arrow.png">' +
                                '<div type="h" class="select_time_box" ' + 'style="display:none">'+
                                        '<header>时</header>'+'<section><ul></ul></section></div></span>'+'<span class="input_box">'+ '<input class="first" type="text" name="deviceDayTimeList['+index+'].openMin" value="00" />'+
                                '<img  class="input_arrow" src="<%=path%>/images/blue_arrow.png">' +  
                                '<div type="m" class="select_time_box select_time_box_2"  style="display:none">' +
                                        '<header>分</header><section><ul></ul></section></span>';
                var _html2 =  '<span class="input_box">' + 
                                '<input class="second" readonly="true" type="text" value="00" name="deviceDayTimeList['+index+'].closeHour"/>'+
                                '<img  class="input_arrow" src="<%=path%>/images/blue_arrow.png">' +
                                '<div type="h" class="select_time_box" ' + 'style="display:none">'+
                                        '<header>时</header>'+'<section><ul></ul></section></div></span>'+'<span class="input_box">'+ '<input class="second" type="text" name="deviceDayTimeList['+index+'].closeMin" value="00" />'+
                                '<img  class="input_arrow" src="<%=path%>/images/blue_arrow.png">' +  
                                '<div type="m" class="select_time_box select_time_box_2"  style="display:none">' +
                                        '<header>分</header><section><ul></ul></section></span>';
                                     


                  var _html = '<tr class="edit_tr" index="'+index+'"><td>'+index+'</td><td> '+_html1+'</td><td>'+_html2+'</td>'+'<td class="voice_5"><a href="#" class="op_delete"><img src="<%=path%>/images/y_delete.png"></a></td></tr>';
                $("#op_tr1").before(_html);

    });

     

       /*时间选中事件*/
      $("#setting_time").on('click', "li",function(){
          
          var val = $(this).text().trim();
          $(".input_box li.active").removeClass("active");
          $(this).addClass("active");
          $(this).parents(".input_box").find('input[type=text]').val(val);

      })


      /**/

      $("input[type=radio][name=every]").click(function(){
          var $this = $(this);
          $("section.show_box[type=1]").toggle()
          $("section.show_box[type=2]").toggle()
              if($this.val() == 'week'){


              }else if($this.val() == 'day'){


              }

      })


    $(document).on('click', '.op_delete', function(){
              $(this).parents('tr').remove();

    });
//----------------------------------------------------------------------------------------------------------------------------------------------


     $(function(){
            /*色彩*/                     
             $('#colorSlider').jRange({
                from: 0,
                to: 100,
                step: 1,
                scale: [0,25,50,75,100],
                format: '%s',
                width: 240,
                showLabels: false,
                showScale: false,
                theme : 'theme-1'
            , onstatechange: function(value){

                $("#color").val(value);
            }
            });



             /*亮度*/
            
            $('#lightSlider').jRange({
                from: 0,
                to: 100,
                step: 1,
                scale: [0,25,50,75,100],
                format: '%s',
                width: 240,
                showLabels: false,
                showScale: false,
                theme : 'theme-2'
            , onstatechange: function(value){

                $("#light").val(value);
            }
            });
          



           /*对比度*/
            
            $('#compareSlider').jRange({
                from: 0,
                to: 100,
                step: 1,
                scale: [0,25,50,75,100],
                format: '%s',
                width: 240,
                showLabels: false,
                showScale: false,
                theme : 'theme-3'
            , onstatechange: function(value){

                $("#compare").val(value);
            }
            });



           /*色调*/
            
            $('#tingeSlider').jRange({
                from: 0,
                to: 100,
                step: 1,
                scale: [0,25,50,75,100],
                format: '%s',
                width: 240,
                showLabels: false,
                showScale: false,
                theme : 'theme-4'
            , onstatechange: function(value){

                $("#tinge").val(value);
            }
            });





           /*锐度*/
            
            $('#acuitySlider').jRange({
                from: 0,
                to: 100,
                step: 1,
                scale: [0,25,50,75,100],
                format: '%s',
                width: 240,
                showLabels: false,
                showScale: false,
                theme : 'theme-5'
            , onstatechange: function(value){

                $("#acuity").val(value);
            }
            });

            /*恢复默认*/
            $("#setDefault1").click(function(){
                
                $('#colorSlider').jRange('setValue', '50'); 
                $('#lightSlider').jRange('setValue', '50'); 
                $('#tingeSlider').jRange('setValue', '50'); 
                $('#compareSlider').jRange('setValue', '50'); 
                $('#acuitySlider').jRange('setValue', '50'); 
            });     
            
            //未设置属性 则给默认值
            if($("#devicePropertyId").val()==0){
                $("#setDefault1").click();
            }
            
           });


//----------------------------------------
  /*调出音量设置*/
      $("#setting_voice").on('hover', '.slider_td', function(){ 
 
        $('.slider_box',this).toggle();
        var _this = $(this);
        $(".input_arrow",this).toggleClass("active");

        $('.voiceSlider' ,this).jRange({
                from: 0,
                to: 100,
                step: 1,
                scale: [0,25,50,75,100],
                format: '%s',
                width: 200,
                showLabels: false,
                showScale: false,
                theme : 'theme-green'
            , onstatechange: function(value){
                $('input[name=voice]', _this).val(value);
            }
        });


      })


          //时间设置
          $("#setting_voice").on('hover', '.input_box', function(){ 
                $(".input_arrow",this).toggleClass("active")
                var select_box =  $(this).find("div.select_time_box") ;
                if(select_box.attr("type") == "h"){
                    var val  = $("input[type=text]" , this).val();
                    var h_html = ""
                    for(var i = 0 ; i < 3; i++){
                        for(var j = 0; j < 10 ; j ++){
                                var li 

                                if(val == (i +""+ j )){
                                     li = "<li class='active' id=''>"+ i + j +" </li>";
                                }else{
                                    li = "<li class='' id=''>"+ i + j +" </li>"
                                }

                                if(i == 2 && j > 3){
                                    continue;
                                }
                                h_html += li;

                        }
                    }
                    $("section ul" , select_box).html(h_html);


                }else if(select_box.attr("type") == "m"){
                    var m_html = ""
                    var val  = $("input[type=text]" , this).val();
                    for(var i = 0 ; i < 6; i++){
                        for(var j = 0; j < 10 ; j ++){
                                var li 
                               
                                if(val == (i +""+ j )){
                                     li = "<li class='active' id=''>"+ i + j +" </li>";
                                }else{
                                    li = "<li class='' id=''>"+ i + j +" </li>"
                                }
                                 
                                m_html += li;

                        }
                    }
                    $("section ul" , select_box).html(m_html);


                }
                select_box.toggle();

        });
        /*时间选中事件*/
        $("#setting_voice").on('click', "li",function(){
            
            var val = $(this).text().trim();
            $(".input_box li.active").removeClass("active");
            $(this).addClass("active");
            $(this).parents(".input_box").find('input[type=text]').val(val);

        })
        /*添加设置行*/
        $('#setting_voice').on('click', '#add_tr', function(){
            
            var index = ''
            if($('.edit_tr').size() < 1){
                index = 1;
            }else{
                index =  1 + parseInt($('.edit_tr').last().attr('index'));
            } 


                var _html1 =  '<span class="input_box">' + 
                                '<input class="first" readonly="true" type="text"  value="00" name="deviceVolumeList['+index+'].openHour"/>'+
                                '<img  class="input_arrow" src="<%=path%>/images/blue_arrow.png">' +
                                '<div type="h" class="select_time_box" ' + 'style="display:none">'+
                                        '<header>时</header>'+'<section><ul></ul></section></div></span>'+'<span class="input_box">'+ '<input class="first" type="text" value="00" name="deviceVolumeList['+index+'].openMin" />'+
                                '<img  class="input_arrow" src="<%=path%>/images/blue_arrow.png">' +  
                                '<div type="m" class="select_time_box select_time_box_2"  style="display:none">' +
                                        '<header>分</header><section><ul></ul></section></span>';
                var _html2 =  '<span class="input_box">' + 
                                '<input class="second" readonly="true" type="text" value="00" name="deviceVolumeList['+index+'].closeHour"/>'+
                                '<img  class="input_arrow" src="<%=path%>/images/blue_arrow.png">' +
                                '<div type="h" class="select_time_box" ' + 'style="display:none">'+
                                        '<header>时</header>'+'<section><ul></ul></section></div></span>'+'<span class="input_box">'+ '<input class="second" type="text" value="00" name="deviceVolumeList['+index+'].closeMin"  />'+
                                '<img  class="input_arrow" src="<%=path%>/images/blue_arrow.png">' +  
                                '<div type="m" class="select_time_box select_time_box_2"  style="display:none">' +
                                        '<header>分</header><section><ul></ul></section></span>';
                var _html3 = '<td class="slider_td"><span class="input_box_2"><input  class="thred" type="text" name="voice" value="50"><img   class="input_arrow" src="<%=path%>/images/down_arrow.png"><div class="slider_box" style="display:none" ><input type="hidden" class="slider-input voiceSlider" name="deviceVolumeList['+index+'].value" value="50"  /></div></span></td>'                        

                

                  var _html = '<tr class="edit_tr" index="'+index+'"><td>'+index+'</td><td> '+_html1+'</td><td>'+_html2+'</td>'+_html3+'<td class="voice_5"><a href="#" class="op_delete"><img src="<%=path%>/images/y_delete.png"></a></td></tr>';
                $("#setting_voice #op_tr").before(_html);

    });

    $(document).on('click', '.op_delete', function(){
                $(this).parents('tr').remove();

    });
</script>
</body>
</html>