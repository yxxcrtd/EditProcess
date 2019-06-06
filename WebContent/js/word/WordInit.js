$(function() {
	oTable1 = $('#table_report').dataTable( {
        "bFilter": false, // 开关，是否启用客户端过滤器
        "bProcessing": true, // 当datatable获取数据时候是否显示正在处理提示信息。
        "bAutoWidth": false, // 自适应宽度
        "sPaginationType": "full_numbers", //分页样式
        "bServerSide": true, // 从服务器端取数据
       	"sAjaxSource": $('#ctx').val() + "/pages/gao/form/manager?now=" + new Date().getTime(), //mvc后台ajax调用接口。
       	"fnServerParams": function(aoData) { // 以下是For查询
       		aoData.push({"name" : "title", "value" : $("#title").val()}); 
       		aoData.push({"name" : "name", "value" : $("#name").val()});
       		aoData.push({"name" : "photo", "value" : $("#photo").val()});
       	},
        "fnServerData": retrieveData,
        "fnDrawCallback": function(oSettings) {
			for ( var i=0, iLen=oSettings.aiDisplay.length ; i<iLen ; i++ )	{
				$('td:eq(0)', oSettings.aoData[ oSettings.aiDisplay[i] ].nTr ).html( i+oSettings._iDisplayStart+1 );
			}
        },
        "aoColumns": [ { 
    			"sTitle": "编号",
        		"mDataProp": "id"
            },{
    			"sTitle": "标题",
                "mDataProp": "title",
			},{
    			"sTitle": "稿件名称",
                "mDataProp": "name",
            },{
    			"sTitle": "图片名称",
				"mDataProp": "photo",
	               "fnRender": function (oObj) {
	              	 return '<img src="'+"/upload/image/" +oObj.aData.photoLink + '" width="60"/>';
	          	}
			},{
    			"sTitle": "操作",
    			"mData": null,
    	        "aTargets": [ -1 ],
              	// 自定义列的样式
                "fnRender": function (oObj) {
                    var start = '<div class="hidden-phone visible-desktop btn-group">';
                    var edit = '<button class="btn btn-mini btn-info" title="修改" onclick="modObj(\'' + oObj.aData.id + '\')"><i class="icon-edit bigger-120"></i></button>';
                    var uedit = '<button class="btn btn-mini btn-warning" title="编辑" onclick="ueditorObj(\'' + oObj.aData.id + '\')"><i class="icon-wrench bigger-120"></i></button>';
                    var	htmledit = '<button class="btn btn-mini btn-info" title="下载html编排" onclick="htmlObj(\'' + oObj.aData.id + '\')"><i class=" icon-arrow-down" bigger-120"></i></button>';
                    var trash = '<button class="btn btn-mini btn-danger" title="删除" onclick="delObj(\'' + oObj.aData.id + '\')"><i class="icon-trash bigger-120"></i></button>';
                    var downword = '<button class="btn btn-mini btn-warning" title="下载word" onclick="loadword(\'' + oObj.aData.id + '\')"><i class=" icon-arrow-down"></i></button>';
                    var downphoto = '<button class="btn btn-mini btn-primary" title="下载图片" onclick="loadphoto(\'' + oObj.aData.id + '\')"><i class=" icon-arrow-down"></i></button>';
                    var end = '</div>';
                    if(oObj.aData.contenNum>0){
                    	return start + edit + uedit + htmledit + trash + downword + downphoto + end;
                    }else{
                    	return start + edit + uedit  + trash + downword + downphoto + end;
                    	
                    }
            	}
			}
        ],
        
        // 多语言配置
		"oLanguage" : {
			"sProcessing" : "正在加载中......",
			"sLengthMenu" : "每页显示 _MENU_ 条记录",
			"sZeroRecords" : "对不起，查询不到相关数据！",
			"sEmptyTable" : "表中无数据存在！",
			"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
			"sSearch" : "搜索",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "末页"
			}
		}

    } );

	$('[data-rel=tooltip]').tooltip();
});



