<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>客户基本信息</h3>
				<div class="table_wrapper">
					<form action="userInfo!editCompany.do" id="editForm">
					<input type="hidden"  id="city" value="<s:property value='companyInfo.companyInfoAreaId'/>"/>
					<input type="hidden" name="id" id="id" value="0"/>
					<input type="hidden" name="companyInfo.companyInfoId" value="<s:property value='companyInfo.companyInfoId'/>"/>
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th><label for=""><span>*</span><em>公司名称</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoName" value="<s:property value='companyInfo.companyInfoName'/>"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>密码</em></label></th>
								<td><input type="password" class="txt" name="companyInfo.companyInfoPassword" value="<s:property value='companyInfo.companyInfoPassword'/>"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>确认密码</em></label></th>
								<td><input type="password" class="txt"  value="<s:property value='companyInfo.companyInfoPassword'/>"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>管理员姓名</em></label></th>
								<td><input type="text" class="txt"  name="companyInfo.companyInfoAdminName" value="<s:property value='companyInfo.companyInfoAdminName'/>"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>电话</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoTel" value="<s:property value='companyInfo.companyInfoTel'/>"></td>
							</tr>
							<tr>
								<th><label for=""><span>*</span><em>E-mail</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoEmail" value="<s:property value='companyInfo.companyInfoEmail'/>"></td>
							</tr>
							<tr>
								<th><label for=""><em>所属地区</em></label></th>
								<td class="clearfix">
									<select  id="select_province">
										<s:iterator value="provinces" id="province" >
											<s:if test="#province.areaName==companyInfo.province">
												<option selected="selected" value="<s:property value='#province.areaCode'/>"><s:property value='#province.areaName'/></option>
											</s:if>
											<s:else>
												<option value="<s:property value='#province.areaCode'/>"><s:property value='#province.areaName'/></option>
											</s:else>					
										</s:iterator>
									</select>
									
									<select name="companyInfo.companyInfoAreaId" id="select_city">
				
									</select>
								</td>
							</tr>
							<tr>
								<th><label for=""><em>详细地址</em></label></th>
								<td><input type="text" class="txt" name="companyInfo.companyInfoAddress" value="<s:property value='companyInfo.companyInfoAddress'/>"></td>
							</tr>
						</table>
					</form>
				</div>
<div class="btns clearfix"><a onclick="document.getElementById('editForm').submit()" class="save_btn">保存</a><a href="userInfo!gotoSuperProfile.do?companyInfo.companyInfoId=<s:property value='companyInfo.companyInfoId'/>">取消</a></div>

<script type="text/javascript">

//保存
$("#save_btn").click(function(){
	$("#id").val(1);
	$("#editForm").submit();				
})

		$("#select_province").change(function(){
			$("#select_city option").remove();
			$.ajax({
				url:"userInfo!getCityByProvince.do",
				type:"post",
				data:{"areaCode":$(this).val()},
				datatype:"json",
				success:function(data){			
					var list=data.cityList;		
					for(var i=0;i<list.length;i++){
						$("#select_city").append("<option value="+list[i].areaId+">"+list[i].areaName+"</option>");
					}			
				
					$("#select_city option").each(function(){
						if($(this).val()==$("#city").val()){
							$(this).prop("selected","true");
						}
					})
				}				
			})
		
		})
		$("#select_province").change();
</script>

</body>
</html>