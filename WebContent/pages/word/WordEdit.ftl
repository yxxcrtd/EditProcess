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
						<h1>稿件图像<small> <i class="icon-double-angle-right"></i>&nbsp;&nbsp;<#if ("" == form.id || 0 == form.id)>新建<#else>修改</#if></small></h1>
					</div>
					<div class="row-fluid">
						<div class="table-header on">基本信息</div>
						<form id="WordForm" commandName="form" class="form-horizontal" enctype="multipart/form-data">
							<div class="on-down">
								<div class="control-group" id="wordDiv">
									<label class="control-label">上传文件：</label>
									<div class="controls">
											<#if ("" != form.obj.name)>
											${form.obj.name}
                                            <input type="file" id="upLoadWord" class="span6" name="upLoadWord" onblur="WordInfo();"/>
                                            </#if>
                                            <#if ("" == form.obj.name)>
                                            <input type="file" id="upLoadWord" class="span6" name="upLoadWord" onblur="WordInfo();"/>
                                            </#if>
                                            
									</div>
										<span id="wordSpan" class="help-inline"></span>
								</div>
							</div>
							
							<div class="on-down">
								<div class="control-group" id="photoDiv">
									<label class="control-label">上传图片：</label>
									<div class="controls">
											<#if ("" != form.obj.photoLink)>
											<image src="/upload/image/${form.obj.photoLink}" width="40"/>
	                                        <input type="file" class="span6" name="upLoadPhoto" id="upLoadPhoto" onblur="photoInfo();"/>
	                                        <#else>
	                                        <input type="file" class="span6" name="upLoadPhoto" id="upLoadPhoto" onblur="photoInfo();"/>
	                                        </#if>
									</div>
										<span id="photoSpan" class="help-inline"></span>
								</div>
							</div>
							
							<div class="on-down">
								<div class="control-group" id="textDiv">
									<label class="control-label">标题：</label>
									<div class="controls" id="titleDiv"> 
	                                 	<input class="span6" id="text" name="obj.title" value="${form.obj.title}" onblur="wordText();"/>
										<span id="textSpan" class="help-inline"></span>
						        	</div>
								</div>
							</div>
							
							<div>
							  <input type="hidden" class="span6" id="id" name="obj.id" value="${form.obj.id}"/>
                            </div>
                           
							<div class="form-actions" style="text-align: center; padding-left:0px;">
								<button class="btn btn-success" type="button" onclick="workSubmit();" id="save"><i class="icon-save bigger-110"></i> 保存</button>&nbsp; &nbsp; &nbsp;
								<button class="btn btn-inverse" type="reset" id="reset"><i class="icon-undo bigger-110"></i> 清空</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${request.contextPath}/js/word/Word.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/word/WordInit.js"></script>
	</body>
</html>
