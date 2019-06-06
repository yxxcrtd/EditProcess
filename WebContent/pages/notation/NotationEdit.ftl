<#include "Context.ftl" />
<#assign ingentatag=JspTaglibs["/WEB-INF/taglib/ingenta-taglib.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
						<h1>批注信息 </h1>
					</div>
					<div class="row-fluid">
						<div class="table-header on">基本信息</div>
						<form id="notationForm" commandName="form" class="form-horizontal" enctype="multipart/form-data">
							<div class="on-down">
								<div class="control-group" id="txtFileDiv">
									<label class="control-label">上传文件：</label>
									<div class="controls">
											<#if ("" != form.obj.name)>
											${form.obj.name!}
                                            <input type="file" id="upLoadword" class="span6" name="upLoadWord"/>
                                            <#else>
                                          <input type="file" id="upLoadword" class="span6" name="upLoadWord"/>
                                            </#if>
									</div>
								</div>
							</div>
							
							<div>
								<input type="hidden" name="obj.id" value="${form.obj.id!}" />
							</div>
							<div class="form-actions" style="text-align: center; padding-left:0px;">
								<button class="btn btn-success" type="submit" id="save"><i class="icon-save bigger-110"></i> 保存</button>&nbsp; &nbsp; &nbsp;
								<button class="btn btn-inverse" type="reset" id="reset"><i class="icon-undo bigger-110"></i> 清空</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/notation/Notation.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/notation/NotationInit.js"></script>
	</body>
</html>
