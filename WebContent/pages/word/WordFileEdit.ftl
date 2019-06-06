<#include "Common.ftl" />
<#include "Context.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>水印信息列表</title>
	</head>
	
	<body>
		<div class="clearfix">
			<div id="page-content" class="clearfix">
				<div class="row-fluid">
					<div class="row-fluid">
					  
						<div class="on-down">
							<form id="ContentForm" commandName="form" class="form-horizontal"></br>
								<div class="row-fluid">
									<div class="control-group span3">
										<label class="control-label" for="form-field-2">内容主体：</label>
										<div class="controls">
										<textarea name="obj.content" id="word" "class='input', style='width: 800px; height: 580px;'">${form.obj.content}</textarea>
										</div>
									</div>
								</div></br>  
								<input type="hidden" name="wordId" id="wordId" value="${form.wordId}"/>
								<input type="hidden" name="obj.id" value="${form.obj.id}"/>
								<div style="text-align: center;">
									<button class="btn btn-small btn-success" type="button" onclick="querySubmit();"><i class="icon-zoom-in bigger-110"></i> 保存</button>
									&nbsp;&nbsp;
									<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${request.contextPath}/js/common.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/word/Word.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/word/WordInit.js"></script>
		<script type="text/javascript" src="${request.contextPath}/js/kindeditor.js"></script>
		 <script>
            var editor;
            KindEditor.options.filterMode = false;
            KindEditor.ready(function(K) {
                editor = K.create('textarea[name="obj.content"]', {
                    allowFileManager : true ,
   					afterBlur:function(){this.sync();}
                });
            });
        </script>
	</body>
</html>
