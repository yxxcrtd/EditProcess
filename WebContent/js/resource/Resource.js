validate = function () {
    var flag = true;
    if (!XmlInfo()) {
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

$(function() {
	var options = {
		beforeSubmit : validate,
		success : showResponse,
		url : $('#ctx').val() + "/pages/dynamic/form/addftl",
		type : 'post',
		clearForm : false,
		timeout : 30000
	};
	$('#ResourceForm').ajaxForm(options);
});


XmlInfo = function() {
	if($("#upLoadFileXML").val() != ""){
		var val = $("#upLoadFileXML").val().substring($("#upLoadFileXML").val().lastIndexOf(".") + 1);
		if(!(val=="xml"||val=="XML")){
			$("#txtFileDiv").addClass("error");
			$("#txtFileSpan").html("请上传xml格式的文件！");
			return false;
		}else{
			$("#txtFileDiv").removeClass("error").addClass("success");
			$("#txtFileSpan").html("通过验证");
			return true;
		}
	}else{
		$("#txtFileDiv").addClass("error");
		$("#txtFileSpan").html("请选择文件");
		return false;
	}
	
};


resourceInfo = function() {
	if($("#formatFlag").val() != ""){
			$("#fileNameDiv").addClass("success");
			$("#activeSpan").html("通过验证");
			return true;
	}else{
		$("#fileNameDiv").addClass("error");
		$("#activeSpan").html("请选择");
		return false;
	}
	
};



$(function() {
	$('#saveButton').click(function(){
		return resourceInfo();
	});
});



//增加
addObj = function(id) {
	$(".hiddentr").hide();
	var url = $('#ctx').val() + "/pages/dynamic/form/add";
	var commonModalCss = {
		"width": "450px",
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

//转换格式选择层
transformObj = function(id) {
	var url = $('#ctx').val() + "/pages/dynamic/form/transform?id=" + id;
	var top=document.body.offsetHeigth/2;
	var left=document.body.offsetWIdth/2;
	var width = "800px";
	var height = "300px";
	window.showModalDialog(url,window,"minimize:yes;maximize:yes;dialogTop:"+top+";dialogLeft:"+left+";dialogWidth="+width+";dialogHeight="+height);
	
};

//删除信息
delObj = function(id) {
	openConfirmModalInFrame("您确定删除该信息吗？",function(){
		var url = $('#ctx').val()+"/pages/dynamic/form/delete?id="+id;
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
