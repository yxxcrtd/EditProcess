<#include "Common.ftl" />
<#include "Context.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>WebService接口</title>
	</head>
	
	<body>
		<div class="clearfix">
			<div id="page-content" class="clearfix">
				<div class="row-fluid">
					<div class="row-fluid">
					    <div class="ace-thumbnails">
							<span class="fr"></span>
							<button class="btn btn-small btn-primary">
								<i class="icon-plus-sign bigger-125"></i>接口调用演示
							</button>
						</div>
						<div class="table-header">
							<i class="icon-flag"></i>&nbsp;&nbsp;接口列表
						</div>
						<table id="table_report" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<td width="10%">调用方式</td>
									<td width="30%" class="left">接口地址</td>
									<td width="60%" class="left">返回示例</td>
								</tr>
							</thead>
							<tbody align="center" style="line-height: 18px; border: 1px solid #97bbdc;"></tbody>
							<tfoot>
								<tr>
									<td>GET</td>
									<td class="left"><a href="${scheme}://${serverName}:${port}${request.contextPath}/pages/digital/invoke" target="_blank">${scheme}://${serverName}:${port}${request.contextPath}/pages/digital/invoke</td>
									<td class="left" id="json"></td>
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
			</div>
		</div>
		<script language="javascript">
		<!--
		$(".btn-primary").on("click", function() {
			$.get("${request.contextPath}/pages/digital/invoke", "", function(data) {
				$("#json").html(data);
			}, "text");
		});
		//-->
		</script>
	</body>
</html>
