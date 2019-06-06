
showResponse = function(response, statusText) {
	disableAllButton();
	if (response.isSuccess == "true") {
		ajaxAlertSuccessMsg(response.msg, true);
		refreshFrameDataTableInLayer('oTable1');
		autoCloseCommonModal(5);
	} else {
		ajaxAlertErrorMsg(response.msg, true);
		enableAllButton();
	}
};
disableAllButton = function() {
	$("#save").attr('disabled', "true");
	$("#reset").attr('disabled', "true");
};

//Add保存
$(function() {
	var options = {	
		success : showResponse,
		url : $('#ctx').val() + "/pages/notation/form/addftl",
		type : 'post',
		clearForm : false,
		timeout : 30000
	};
	$('#notationForm').ajaxForm(options);
});


//上传
uploadword = function(id) {
	$(".hiddentr").hide();
	var url = $('#ctx').val() + "/pages/notation/form/editor?id="+ id;
	var commonModalCss = {
		"width": "600px",
		"margin": "100px 0 0 -450px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};

retrieveData = function(sSource, aoData, fnCallback) {
	$.ajax({
		"dataType" : 'json',
		"type" : "POST",
		"url" : sSource,
		"cache" : false,
		"data" : aoData,
		"success" : function(response) {
			fnCallback(response);
		},
		"error" : function(response) {
			alert("error");
		}
	});
};

query = function() {
	initPagingParamsInFrame('oTable1');
	// 重新刷新页面Table
	refreshFrameDataTableInFrame('oTable1');
};


loadword = function(id){
	var url = $('#ctx').val()+"/pages/notation/form/download?id="+id;
	window.location=url;
};
