<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="pageHeader"></div>
<div class="grid">
	<div class="gridHeader">
		<div class="gridThead">
			<table class="table" width="100%" layoutH="120">
				<thead>
					<tr style="text-align: center">
						<th>异常类型</th>
						<th>当天异常数量(占比)</th>
						<th>当月异常数量(占比)</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator var="exception" value="exceptionReport">
						<tr>
							<td><s:if test="#exception.type == 1">
				                 <a href="information/exception!findExceptionInfoListTotal.do? exceptionInfo.exceptionType=<s:property value='#exception.type'/>"
								target="dialog" rel="reportss" width="900"
								style="text-decoration: underline" width="700" height="600"
								fresh="false" mask="true" title="广告异常信息">   广告异常</a>
				                 </s:if> <s:elseif test="#exception.type == 2">
				                 <a href="information/exception!findExceptionInfoListTotal.do? exceptionInfo.exceptionType=<s:property value='#exception.type'/>"
								target="dialog" rel="reportss" width="900"
								style="text-decoration: underline" width="700" height="600"
								fresh="false" mask="true" title="应用异常信息">   应用异常</a>
				                 </s:elseif> <s:elseif test="#exception.type == 3">
				                   <a href="information/exception!findExceptionInfoListTotal.do? exceptionInfo.exceptionType=<s:property value='#exception.type'/>"
								target="dialog" rel="reportss" width="900"
								style="text-decoration: underline" width="700" height="600"
								fresh="false" mask="true" title="OTA异常信息">   OTA异常</a>
				                 </s:elseif> <s:elseif test="#exception.type == 4">
				                 <a href="information/exception!findExceptionInfoListTotal.do? exceptionInfo.exceptionType=<s:property value='#exception.type'/>"
								target="dialog" rel="reportss" width="900"
								style="text-decoration: underline" width="700" height="600"
								fresh="false" mask="true" title="缴费异常信息">   缴费异常</a>
				                 </s:elseif> <s:elseif test="#exception.type == 5">
				                 <a href="information/exception!findExceptionInfoListTotal.do? exceptionInfo.exceptionType=<s:property value='#exception.type'/>"
								target="dialog" rel="reportss" width="900"
								style="text-decoration: underline" width="700" height="600"
								fresh="false" mask="true" title="彩票异常信息">  彩票异常</a>
				                 </s:elseif> <s:elseif test="#exception.type == 6">
				                <a href="information/exception!findExceptionInfoListTotal.do? exceptionInfo.exceptionType=<s:property value='#exception.type'/>"
								target="dialog" rel="reportss" width="900"
								style="text-decoration: underline" width="700" height="600"
								fresh="false" mask="true" title=" 应用分类异常信息">  应用分类异常</a>
				                 </s:elseif>
							</td>
							<td><a
								href="information/exception!findExceptionInfoList.do?exceptionInfo.time =<s:property value='#exception.time'/>&& exceptionInfo.exceptionType=<s:property value='#exception.type'/>"
								target="dialog" rel="reportss" width="900"
								style="text-decoration: underline" width="700" height="600"
								fresh="false" mask="true" title="当天异常信息"> <s:property
										value='#exception.exceptionTypeCount' /></a>&nbsp;&nbsp;(<s:property
									value='#exception.exceptionTypeProportion' />)</td>
							<td><a
								href="information/exception!findExceptionInfoListByYear.do?exceptionInfo.year=<s:property value='#exception.year'/>&& exceptionInfo.exceptionType=<s:property value='#exception.type'/>"
								target="dialog" rel="reportssss" width="900"
								style="text-decoration: underline" width="700" height="600"
								fresh="false" mask="true" title="当月异常信息"> <s:property
										value='#exception.Data' /></a>&nbsp;&nbsp;(<s:property
									value='#exception.proportion' />)</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
</div>