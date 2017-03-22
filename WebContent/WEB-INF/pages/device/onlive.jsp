<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!-- onlive.html -->
    <!--查看用户-->
    <div class="alert_wrap alert_add alert_show" id="alert_show">
        <div class="alert_content alert_user_manager_content">
            <h3>
                查看用户<a href="#"><img src="images/close.png" onclick="closeLayer()"></a>
            </h3>
            <div class="table_wrapper">
                <span class="edit_btn"><img src="images/edit.png"></span>
                <table cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <th><label for="">用户名</label></th>
                        <td><input type="text" class="txt" value="admin01" readOnly="true"></td>
                    </tr>
                    <tr>
                        <th><label for="">姓名</label></th>
                        <td><input type="text" class="txt" value="田亮" readOnly="true"></td>
                    </tr>
                    <tr>
                        <th><label for="">密码</label></th>
                        <td class="passwordView"><input type="password" class="txt" value="123456" readOnly="true"><em><img
                                src="images/passwordView.png"></em></td>
                    </tr>
                    <tr>
                        <th><label for="">电话</label></th>
                        <td><input type="text" class="txt" value="13954122323" readOnly="true"></td>
                    </tr>
                    <tr>
                        <th><label for="">访问权限</label></th>
                        <td><span>编辑权限</span> <span>审核权限</span> <span>维护权限</span></td>
                    </tr>
                    <tr>
                        <th><label for="">E-mail</label></th>
                        <td><input type="text" class="txt" value="153544@163.com" readOnly="true"></td>
                    </tr>
                    <tr>
                        <th><label for="">备注</label></th>
                        <td><textarea name="" id="" cols="30" rows="10" readOnly="true">权限编辑加审核</textarea></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</body>
</html>