<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#include "Common.ftl" />
<#include "Context.ftl" />
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>
	<body>
		<div class="clearfix">
			<#include "AjaxMsg.ftl" />
			<div id="page-content" class="clearfix">
				<div class="row-fluid">
					<div class="page-header position-relative">
						<h1>xml文件<small> <i class="icon-double-angle-right"></i>&nbsp;&nbsp;转换</small></h1>
					</div>
					<div class="row-fluid">
						<div class="table-header on">基本信息</div>
						<form id="ocrFileTransformForm" commandName="form" class="form-horizontal" action="transformSubmit">
							<div class="on-down">
								<div class="control-group" id="fileNameDiv">
									<label class="control-label">转换格式：</label>
									<div class="controls">
										<select id="formatFlag" name="formatFlag" class="span6" value="">
									        <option value="">--选择--</option>
									        <option value="1">pdf</option>
									        <option value="2">xml</option>
									        <option value="3">doc</option>
									     </select>
										<span id="activeSpan" class="help-inline"></span>
									</div>
								</div>
								<input type="hidden" name="obj.id" value="${id!}" />
							</div>
							<div class="form-actions" style="text-align: center; padding-left:0px;">
								<button class="btn btn-success" type="submit" id="saveButton"><i class="icon-save bigger-110"></i> 确定</button>&nbsp; &nbsp; &nbsp;
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/resource/Resource.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/resource/ResourceInit.js"></script>
	</body>
</html>