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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--查看用户-->
	 <div class="alert_wrap alert_add alert_show" id="alert_show">
		<div class="alert_content alert_user_manager_content">
			<h3>查看用户<a href="#"><img src="<%=path%>/images/close.png"  onclick="closeLayer()"></a></h3>
			<div class="table_wrapper">
				<span class="edit_btn"><a href="#"><img src="<%=path%>/images/edit.png"></a></span>
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th><label for="">用户名</label></th>
						<td><input type="text" class="txt" value='<s:property value="userInfo.userInfoUsername"/>' readOnly="true"></td>
					</tr>
					<tr>
						<th><label for="">姓名</label></th>
						<td><input type="text" class="txt" value='<s:property value="userInfo.userInfoName"/>' readOnly="true"></td>
					</tr>
					<tr>
						<th><label for="">密码</label></th>
						<td class="passwordView"><input type="password" class="txt" value='<s:property value="userInfo.userInfoPwd"/>' readOnly="true"><em><img src="<%=path%>/images/passwordView.png"></em></td>
					</tr>
					<tr>
						<th><label for="">电话</label></th>
						<td><input type="text" class="txt" value='<s:property value="userInfo.userInfoTel"/>' readOnly="true"></td>
					</tr>
					<tr>
						<th><label for="">访问权限</label></th>
						<td>
						<s:iterator value="userInfo.powerInfoList" id="powerInfo">
							<s:property value="#powerInfo.powerInfoName"/>
						</s:iterator>
						</td>
					</tr>
					<tr>
						<th><label for="">性别</label></th>
						<td><input type="text" class="txt" value='<s:if test="userInfo.userInfoSex==1">男</s:if><s:if test="userInfo.userInfoSex==0">女</s:if>' readOnly="true" ></td>
					</tr>
					<tr>
						<th><label for="">备注</label></th>
						<td><textarea name="" id="" cols="30" rows="10" readOnly="true"><s:property value="userInfo.userInfoTips"/></textarea></td>
					</tr>
				</table>
			</div>
		</div> 
	 </div> 
</body>
</html>