<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="pageContent">
	<s:form id="chunkInfoForm" theme="simple" method="post"
		action="chunk/chunkAction!updateChunkInfo.do"
		cssClass="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);">
		<s:hidden name="vo.chunkId"></s:hidden>
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>设备ID：</label>
				<s:label name="vo.deviceId" value="7"></s:label>
			</p>
			<p>
				<label>URL地址：</label>
				<s:textfield id="chunkUrl" name="vo.chunkUrl"
					cssClass="required"  maxlength="40" ></s:textfield>
			</p>
			<p>
				<label>状态：</label>
				<s:select list="#{1:'运行',2:'空闲',3:'暂停'}" listKey="key"
							listValue="value" name="vo.status"
							id="status" cssClass="required"></s:select>
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