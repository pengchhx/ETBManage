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
<!--编辑用户-->
	 <div class="alert_wrap alert_add" id="alert_edit">
		<div class="alert_content alert_user_manager_content">
		<form action="userInfo!updateUser.do" method="post" id="updateForm">
			<input type="hidden" name="userInfo.userInfoId" value='<s:property value="userInfo.userInfoId"/>' />
			<h3>编辑用户<a href="#" onclick="closeLayer()"><img src="<%=path%>/images/close.png" ></a></h3>	
			<div class="table_wrapper">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th><label for=""><span>*</span>用户名</label></th>
						<td><input type="text" name="userInfo.userInfoUsername" class="txt" value='<s:property value="userInfo.userInfoUsername"/>'></td>
					</tr>
					<tr>
						<th><label for=""><span>*</span>姓名</label></th>
						<td><input type="text" name="userInfo.userInfoName" class="txt" value='<s:property value="userInfo.userInfoName"/>'></td>
					</tr>
					<tr>
						<th><label for=""><span>*</span>密码</label></th>
						<td><input type="password" name="userInfo.userInfoPwd" class="txt" value='<s:property value="userInfo.userInfoPwd"/>'></td>
					</tr>
					<tr>
						<th><label for=""><span>*</span>确认密码</label></th>
						<td><input type="password" class="txt"></td>
					</tr>
					<tr>
						<th><label for=""><span>*</span>电话</label></th>
						<td><input type="text" name="userInfo.userInfoTel" class="txt" value='<s:property value="userInfo.userInfoTel"/>' ></td>
					</tr>
					<tr>
						<th><label for=""><span>*</span>访问权限</label></th>
						<td>
									
								<span><input type="checkbox" name="tempList" value="3"  <s:iterator value="userInfo.powerInfoList" id="powerInfo">	  <s:if test="#powerInfo.powerInfoId==3"> checked="checked" </s:if> </s:iterator>>编辑权限</span>		
								<span><input type="checkbox" name="tempList" value="4" <s:iterator value="userInfo.powerInfoList" id="powerInfo">	  <s:if test="#powerInfo.powerInfoId==4"> checked="checked" </s:if> </s:iterator>>审核权限</span>
								<span><input type="checkbox" name="tempList" value="5" <s:iterator value="userInfo.powerInfoList" id="powerInfo">	  <s:if test="#powerInfo.powerInfoId==5"> checked="checked" </s:if> </s:iterator>>维护权限</span>
							
						</td>
					</tr>
					<tr>
						<th><label for="">性别</label></th>
						<td><input type="radio" name="userInfo.userInfoSex" value="1"<s:if test="userInfo.userInfoSex==1"> checked="checked" </s:if>/>男 <input type="radio" name="userInfo.userInfoSex" value="0" <s:if test="userInfo.userInfoSex==0"> checked="checked" </s:if>/>女</td>
					</tr>
					<tr>
						<th><label for="">备注</label></th>
						<td><textarea name="userInfo.userInfoTips"  cols="30" rows="10"><s:property value="userInfo.userInfoTips"/></textarea></td>
					</tr>
				</table>
			</div>
			<div class="btns clearfix"><a href="#" class="save_btn" onclick="document.getElementById('updateForm').submit();">保存</a><a href="#" onclick="closeLayer()">取消</a></div>
			</form>
		</div> 
 </div> 

	<!--alert_wrap end-->
	
</body>
</html>