<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!-- 日程安排 -->
    <!-- 基本信息 -->
    <section class="type_content sch_content" style="height: 510px; padding: 20px 20px 16px 20px"> <header class="sch_header"> <label>广告日期</label>
    <span class="sort_tips">顺序播放</span> </header>
    <div class="table_box">
        <table class="ad_tabel">
            <tr class="t_header">
                <td class="index_1">发布单号</td>
                <td class='index_2'>开始播放时间</td>
                <td class='index_3'>失效时间</td>
                <td></td>
            </tr>
            <tr class="t_item">
                <td class="index_1">JDDJ554</td>
                <td class="index_2">2016-1-1 12:00:00</td>
                <td class="index_3">2016-2-1 12:00:00</td>
                <td class="index_4"><a href="#" class="show_date"> <img src="../../images/date.png"></a> <a href="#" class="show_more">
                        <img src="../../images/down_arrow.png">
                </a></td>
            </tr>
            <tr class="t_item">
                <td class="index_1">JDDJ554</td>
                <td class="index_2">2016-1-1 12:00:00</td>
                <td class="index_3">2016-2-1 12:00:00</td>
                <td class="index_4"><a href="#" class="show_date"> <img src="../../images/date.png"></a> <a href="#" class="show_more">
                        <img src="../../images/down_arrow.png">
                </a></td>
            </tr>
        </table>
    </div>
    </section>
    <script type="text/javascript">

        $(".show_more").click(function(){
            if($(this).hasClass("active")){
                /*动效处理  收起*/
                $('img',this).attr("src", '../../images/down_arrow.png')
                $(this).removeClass("active");
                $(this).parents('tr').removeClass('active');
                $("table.ad_tabel tr.tr_show").remove();

            }else{
                /*动效处理 添加*/
                $("table.ad_tabel tr.tr_show").remove();
                $("table.ad_tabel tr.active a.show_more img").attr("src", '../../images/down_arrow.png');
                $("table.ad_tabel tr.active").removeClass("active");
                $(this).addClass("active");

                $('img' , this).attr("src", '../../images/up_arrow.png');
                $(this).parents('tr').addClass('active');
                var tr = $(this).parents('tr');

                $.get("/template/equ/pic_info.html",function(result){
                    tr.after(result);
                })

            }

        });

        var calendar
        $(".show_date").click(function(){
            calendar = layer.open({
                type: 2,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                area: ['1222px', '700px'],
                content: ['template/equ/calendar.html?_dt=' + new Date(),'no']
            });
        })

        function closeCal(){
            layer.close(calendar);
        }
    </script>
</body>
</html>