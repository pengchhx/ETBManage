<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <tr class="tr_show">
        <td colspan="4">
            <div class="ad_pic_box">
                <span class="index">01</span> <img class="ad_pic" src="../../images/img_1.png" />
                <div class="ad_pic_mask" style="display: none">
                    <section>
                    <div>广告名称：XXXX</div>
                    <div>分辨率：1000X600px</div>
                    <div>上次编辑时间：XXXX</div>
                    <div>编辑人：XXXX</div>
                    <a href="#" class="pic_more"><img src="../../images/more.png" /></a> </section>
                </div>
            </div>
            <div class="ad_pic_box">
                <span class="index">01</span> <img class="ad_pic" src="../../images/img_1.png" />
                <div class="ad_pic_mask" style="display: none">
                    <section>
                    <div>广告名称：XXXX</div>
                    <div>分辨率：1000X600px</div>
                    <div>上次编辑时间：XXXX</div>
                    <div>编辑人：XXXX</div>
                    <a href="#" class="pic_more"><img src="../../images/more.png" /></a> </section>
                </div>
            </div>
        </td>
    </tr>
    <script type="text/javascript">

        $(".ad_pic_box").hover(function() {
            $(".ad_pic_mask", this).show();
            $(".ad_pic_mask", this).hide();
        })
    
        $(".pic_more").click(function() {
    
        })
    </script>
</body>
</html>