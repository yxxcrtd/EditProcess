validate = function () {
    var flag = true;
    if (!WordInfo()) {
		flag = false;
	}
    if (!photoInfo()) {
		flag = false;
	}
    if (!wordText()) {
		flag = false;
	}
    return flag;
};

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

WordInfo = function() {
	//判断是不是修改进来的
	if($("#id").val()==""){
		if($("#upLoadWord").val() == ""){
			$("#wordDiv").addClass("error");
			$("#wordSpan").html("请选择文件");
			return false;
		}
	}
	if($("#upLoadWord").val() != ""){
		var val = $("#upLoadWord").val().substring($("#upLoadWord").val().lastIndexOf(".") + 1);
		if(!(val=="doc"||val=="DOC")){
			$("#wordDiv").addClass("error");
			$("#wordSpan").html("请上传doc格式的文件！");
			return false;
		}
	}
	$("#wordDiv").removeClass("error").addClass("success");
	$("#wordSpan").html("通过验证");
	return true;
};

photoInfo = function() {
	if($("#upLoadPhoto").val() != ""){
		var val = $("#upLoadPhoto").val().substring($("#upLoadPhoto").val().lastIndexOf(".") + 1);
		if(!(val=="jpg"||val=="JPG")){
			$("#photoDiv").addClass("error");
			$("#photoSpan").html("请上传jpg格式的文件！");
			return false;
		}else{
			$("#photoDiv").removeClass("error").addClass("success");
			$("#photoSpan").html("通过验证");
			return true;
		}
	}
	return true;
};

wordText = function() {
	if ($("#text").val() == "") {
		$("#textDiv").addClass("error");
		$("#textSpan").html("标题不能为空！");
		return false;
	}else{
		$("#textDiv").removeClass("error").addClass("success");
		$("#textSpan").html("通过验证");
		return true;
	}
};


workSubmit = function() {
	var formObj = $('#WordForm');
	var options = {	
			beforeSubmit : validate,
			success : showResponse,
			url : $('#ctx').val() + "/pages/gao/form/addftl",
			type : 'post',
			clearForm : false,
			timeout : 30000
		};
	formObj.ajaxForm(options);
	formObj.submit();
	
} ;


//增加
addObj = function(id) {
	$(".hiddentr").hide();
	var url = $('#ctx').val() + "/pages/gao/form/add";
	var commonModalCss = {
		"width": "600px",
		"margin": "100px 0 0 -450px"
	};
	var commonModalBodyCss = {
		"max-height": "800px"
	};
	openCommonModalInFrame(url, commonModalCss, commonModalBodyCss);
};


//修改
modObj = function(id) {
	var url = $('#ctx').val() + "/pages/gao/form/add?id=" + id;
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

//删除信息
delObj = function(id) {
	openConfirmModalInFrame("您确定删除信息吗？",function(){
		var url = $('#ctx').val()+"/pages/gao/form/delete?id="+id;
		$.ajax( {
			"dataType": 'json',
			"type": "POST",
			"url": url,
			"cache": false,
			"success": function(response) {
				if (response.isSuccess == "true") {
					window.parent.alertMsg('successModal', 'successMsg', response.msg);
					refreshFrameDataTableInFrame('oTable1');
					autoCloseCommonModal(50);
				} else {
					window.parent.alertMsg('errorModal', 'errorMsg', response.msg);
				}
			},
			"error": function(response) {
				alert("error");
			}
		} );
	},null,null);
};



loadword = function(id){
	var url = $('#ctx').val()+"/pages/gao/form/download?id="+id;
	window.location=url;
};

loadphoto = function(id){
	var url = $('#ctx').val()+"/pages/gao/form/loadphoto?id="+id;
	window.location=url;
};

ueditorObj = function(id) {
	var url = $('#ctx').val() + "/pages/gao/form/edit?id=" + id;
	window.location=url;
};

querySubmit=function(){
	var formObj = $('#ContentForm');
	var url = $('#ctx').val() + "/pages/gao/save/word";
	formObj.attr("action",url);
	formObj.submit();
};

htmlObj=function(id){
	var url = $('#ctx').val()+"/pages/gao/save/html?id="+id;
	window.location=url;
};

