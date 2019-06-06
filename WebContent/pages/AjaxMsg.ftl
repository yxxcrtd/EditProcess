<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/x html">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>

	<body>
		<div id="infoMessage" class="alert alert-info hide">
			<span id="infoMessageContent"></span>
			<a class="close" onclick="$(this).parent().hide();">&times;</a>
		</div>
		<div id="successMessage" class="alert alert-success hide">
			<span id="successMessageContent"></span>
			<a class="close" onclick="$(this).parent().hide();">&times;</a>
		</div>
		<div id="errorMessage" class="alert alert-error hide">
			<span id="errorMessageContent"></span>
			<a class="close" onclick="$(this).parent().hide();">&times;</a>
		</div>

		<script type="text/javascript">
			function ajaxAlertInfoMsg(msg, hide) {
				if (hide == true) {
					$(".alert").hide();
				}
				$('[id=infoMessageContent]:last').html(msg);
				$('[id=infoMessage]:last').show();
			}
			function ajaxAlertSuccessMsg(msg, hide) {
				if (hide == true) {
					$(".alert").hide();
				}
				$('[id=successMessageContent]:last').html(msg);
				$('[id=successMessage]:last').show();
			}
			function ajaxAlertErrorMsg(msg, hide) {
				if (hide == true) {
					$(".alert").hide();
				}
				$('[id=errorMessageContent]:last').html(msg);
				$('[id=errorMessage]:last').show();
			}
		</script>
	</body>
</html>
