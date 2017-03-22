<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent">
	<s:form id="chunkInfoForm" theme="simple" method="post"
		action="chunk/chunkAction!reserveChunkInfo.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<s:hidden name="vo.chunkId"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>备份URL地址：</label> <input class="required" id="inputReserveUrl"
						name="vo.reserveUrl"
						dwzname="chunkInfo.chunkUrl" type="text" suggestFields="chunkUrl"
						suggestUrl="chunk/chunkAction!findFreeChunk.do" lookupGroup="chunkInfo"
						value="<s:property value='vo.reserveUrl'/>"
						 /> <a class="add"
						href="chunk/chunkAction!showFreeChunk.do" target="dialog"
						rel="chunkSeleter" title="chunkServer信息" width="690" height="406"
						mask="true" resizable="false"> <span><img alt="搜索"
							src="themes/azure/images/preview.png"></span></a>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">&nbsp;保存&nbsp;</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">&nbsp;取消&nbsp;</button>
						</div>
					</div></li>
			</ul>
		</div>
	</s:form>
</div>