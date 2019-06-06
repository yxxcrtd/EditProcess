(function($) {
	var
	tools = {
		valueCache: {
			putValue: function(d, obj) {
				d.propValueCache.put(obj.id, obj.value);
			},
			cacheToElements: function(d) {
				var keys = d.propValueCache.keys();
				var values = d.propValueCache.values();
				for(var i in keys) {
					$("#" + keys[i]).val(values[i]);
				}
			}
		},
		bindingOnClick: function(d, buttonObj) {
			$("#" + buttonObj.id).click(function() {
				eval(buttonObj.click);
			});
		},
		dataTable: {
			create: function(d, dataTableObj) {
				var tempDataTable = $("#" + dataTableObj.id).dataTable({
					"bFilter" : false, // 开关，是否启用客户端过滤器
					"bProcessing" : true, // 当datatable获取数据时候是否显示正在处理提示信息。
					"bAutoWidth" : false, // 自适应宽度
					"sPaginationType" : "full_numbers", // 分页样式
					"bServerSide" : true, // 从服务器端取数据
					"sAjaxSource" : eval(eval(dataTableObj.ajaxSource)),
					"fnServerParams" : eval(dataTableObj.serverParams),
					"fnServerData" : tools.dataTable.retrieveData,
					"fnDrawCallback" : function(oSettings) {
						for (var i = 0, iLen = oSettings.aiDisplay.length; i < iLen; i++) {
							$('td:eq(0)', oSettings.aoData[oSettings.aiDisplay[i]].nTr).html(i + oSettings._iDisplayStart + 1);
						}
					},
					"aoColumns" : eval(dataTableObj.columns),

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
				});
				
				d.dataTable.put(dataTableObj.id, tempDataTable);
				$('[data-rel=tooltip]').tooltip();
				
			},
			retrieveData: function(sSource, aoData, fnCallback) {
				$.ajax({
					"dataType" : "json",
					"type" : "POST",
					"url" : sSource,
					"cache" : false,
					"data" : aoData,
					"success" : function(response) {
						fnCallback(response);
					},
					"error": function(response) {
						// alert(response); alert("error%%%");
					}
				});
			}
	
		},
		content: {
			init_detail: function(d) {
				var
				setting = d.setting,
				only = d.only,
				warp_content = d.warp_content;
				var current_theme = d.theme.value();
				
				var query_param = {};
				query_param[setting.main_data_param.name] = setting.main_data_param.value;
				query_param[setting.type_data_param.name] = setting.type_data_param.value;
				query_param[setting.classify_data_param.name] = "root";
				
				$.ajax({
					type: "POST",
					async: false,
					"dataType": "json",
					url: setting.add_tab_url,
					data: query_param,
					success: function(response) {
						if (response.isSuccess == "true") {
							var first_level_classify = [];
							
							var html = "";
							var classify_list = response[setting.classify_list.name];
							if(classify_list != null && classify_list.length > 0) {
								var def_id = null;
								var def_code = null;
								var def_name = null;
								
								if(current_theme == "1") { // Tab
									html = html + '<div class="tabbable span12">';
									html = html + '<ul class="nav nav-tabs active">';
									for (var i in classify_list) {
										var classifyId = classify_list[i][d.setting.classify_list.field.id];
										var classifyCode = classify_list[i][d.setting.classify_list.field.code];
										var classifyName = classify_list[i][d.setting.classify_list.field.name];
										
										var first_level_classify_id = only + classifyCode + "_first_level_classify";
										first_level_classify.push({
											id: first_level_classify_id,
											data: {
												id: classifyId,
												code: classifyCode,
												name: classifyName
											}
										});
										
										if (i == 0) {
											def_id = classifyId;
											def_code = classifyCode;
											def_name = classifyName;
											
											html = html + '<li id="'+ first_level_classify_id +'" class="active" classifyId="' + classifyId + '" classifyCode="' + classifyCode + '"><a href="#' + d.only + classifyCode + '" data-toggle="tab">' + classifyName + '</a></li>';
										} else {
											html = html + '<li id="'+ first_level_classify_id +'" classifyId="' + classifyId + '" classifyCode="' + classifyCode + '"><a href="#' + d.only + classifyCode + '"  data-toggle="tab">' + classifyName + '</a></li>';
										}
									}
									html = html + '</ul>';
									html = html + '<div class="tab-content">';
									
									for (var i in classify_list) {
										var classifyId = classify_list[i][d.setting.classify_list.field.id];
										var classifyCode = classify_list[i][d.setting.classify_list.field.code];
										var classifyName = classify_list[i][d.setting.classify_list.field.name];
										
										if (i == 0) {
											html = html + '<div class="tab-pane active" id="' + d.only + classifyCode + '"></div>';
										} else {
											html = html + '<div class="tab-pane" id="' + d.only + classifyCode + '"></div>';
										}
									}
									html = html + '</div>';
									
									warp_content.html(html);
									
									for(var i in first_level_classify) {
										$("#" + first_level_classify[i].id).bind("click", first_level_classify[i].data, function(event) {
											tools.content.add_tab(d, event.data.id, event.data.code, event.data.name);
										});
									}
									
									tools.content.add_tab(d, def_id, def_code, def_name);
								}
								
								if(current_theme == "2") {
									var bind_accordion_click = [];
									
									for (var i in classify_list) {
										var classifyId = classify_list[i][d.setting.classify_list.field.id];
										var classifyCode = classify_list[i][d.setting.classify_list.field.code];
										var classifyName = classify_list[i][d.setting.classify_list.field.name];
										
										if(i == 0) {
											def_id = classifyId;
											def_code = classifyCode;
											def_name = classifyName;
										}
										
										var accordion_id = d.only + "accordion" + classifyCode;
										bind_accordion_click.push({
											id: accordion_id,
											data: {
												id: classifyId,
												code: classifyCode,
												name: classifyName
											}
										});
										
										html = html + '<div class="table-header on" id="'+ accordion_id +'">';
										html = html + '<a href="###"><i class="icon-folder-open"></i>&nbsp;&nbsp;';
										html = html + classifyName;
										html = html + '</a>';
										html = html + '</div>';
										
										html = html + '<div class="on-down tab-list">';
										html = html + '<div class="tabbable" id="'+ d.only + classifyCode +'">';
										html = html + '</div>';
										html = html + '</div>';
										
										tools.content.add_tab(d, classifyId, classifyCode, classifyName);
									}
									
									warp_content.html(html);
									
									for(var i in bind_accordion_click) {
										$("#" + bind_accordion_click[i].id).bind("click", bind_accordion_click[i].data, function(event) {
											tools.event.accordion.click(this, d, event.data.id, event.data.code, event.data.name);
										});
									}
									
									
								}
								
								if(current_theme == "3") {
									
									var step_ul_li_html = '';
									var step_div_html = '';

									for (var i in classify_list) {
										var classifyId = classify_list[i][d.setting.classify_list.field.id];
										var classifyCode = classify_list[i][d.setting.classify_list.field.code];
										var classifyName = classify_list[i][d.setting.classify_list.field.name];
										
										if(i == 0) {
											def_id = classifyId;
											def_code = classifyCode;
											def_name = classifyName;
										}
										
										var tabNum = parseInt(i) + 1; // TAB页计数器
										
										step_ul_li_html += '<li data-target="#step'+ tabNum +'\"';
										if (i == 0) {
											step_ul_li_html += ' class="active"';
										}
										step_ul_li_html += ' classifyId="'+ classifyId +'" classifyCode="'+ classifyCode +'" classifyName="'+classifyName+'">';
										step_ul_li_html += '	<span class="step">' + tabNum+ '</span>';
										step_ul_li_html += '	<span class="title">'+ classifyName +'</span>';
										step_ul_li_html += '</li>';
										
										step_div_html += '<div class="step-pane';
										if (i == 0) {
											step_div_html += ' active';
										}
										step_div_html += '" id="'+ d.only + classifyCode +'">';
										step_div_html += '</div>';

									}
									
									html += '		<div class="span12">';
									html += '			<div class="widget-box">';
									html += '				<div class="widget-header widget-header-blue widget-header-flat wi1dget-header-large">';
									html += '					<h4 class="lighter">操作</h4>';
									
									html += '					<span id="'+ d.only +'prevBtn" class="btn btn-prev">';
									html += '						<i class="icon-arrow-left"></i>';
									html += '						Prev';
									html += '					</span>';
									
									html += '					<span id="'+ d.only +'nextBtn" class="btn btn-success btn-next">';
									html += '						Next';
									html += '						<i class="icon-arrow-right icon-on-right"></i>';
									html += '					</span>';
									
									html += '				</div>';
									html += '				<div class="widget-body">';
									html += '					<div class="widget-main">';
									html += '						<div class="row-fluid">';
									html += '							<div id="'+ d.only +'fuelux-wizard" class="row-fluid hidden">';
									html += '								<ul class="wizard-steps">';
									
									html += step_ul_li_html;
									
									html += '								</ul>';
									html += '							</div>';
									html += '							<hr />';
									html += '							<div class="step-content row-fluid position-relative">';
									
									html += step_div_html;
									
									html += '							</div>';
									html += '						</div>';
									html += '					</div>';
									html += '				</div>';
									html += '			</div>';
									html += '		</div>';
									
									warp_content.html(html);
									
									// 加载序列滚动元素
									$("#" + d.only + "fuelux-wizard").ace_digital_wizard(d.only + "prevBtn", d.only + "nextBtn")
									.on('change', function(e, info) {
										var currentLi = $(this).find("li[class=active]"); // 当前li
										if(info.direction == "previous") {
											currentLi = currentLi.prev();
										} else if(info.direction == "next") {
											currentLi = currentLi.next();
										}
										var classifyId = currentLi.attr("classifyId");
										var classifyCode = currentLi.attr("classifyCode");
										var classifyName = currentLi.attr("classifyName");
										
										tools.content.add_tab(d, classifyId, classifyCode, classifyName, function() {
											$("#" + d.only + classifyCode).addClass("active");
										});
									})
									.on('finished', function(e) {
										return false;
									})
									.on('stepclick', function(e){
										return false;
									});
									
									tools.content.add_tab(d, def_id, def_code, def_name);
								}
								
							}

						} else {
							alert("FAILURE");
						}
					},
					error : function(response) {
						alert("error");
					}
				});
				
				
			},
			add_tab: function(d, id, code, name, callback) {
				var setting = d.setting;
				var only = d.only;
				var current_theme = d.theme.value();
				
				var query_param = {};
				query_param[setting.main_data_param.name] = setting.main_data_param.value;
				query_param[setting.type_data_param.name] = setting.type_data_param.value;
				query_param[setting.classify_data_param.name] = id;
				
				var nextId = "";
				var nextCode = "";
				var nextName = "";
				
				var url = setting.add_tab_url;
				
				$.ajax({
					"dataType" : 'json',
					"type" : "POST",
					"url" : url,
					data: query_param,
					"cache" : false,
					"success" : function(response) {
						
						var tabbable = '<div class="tabbable span12" id="' + d.only + id + '">';
						var divEnd = '</div>';
						var ul = '<ul class="nav nav-tabs active">';
						var ulEnd = '</ul>';
						var divTab = '<div class="tab-content">';
						var a = "";

						var inDivTab = '';
						
						var classList = response[setting.classify_list.name];
						if (classList != null && classList.length > 0) {
							var child_level_classify = [];
							
							for (var i in classList) {
								var classify_id = classList[i][d.setting.classify_list.field.id];
								var classify_code = classList[i][d.setting.classify_list.field.code];
								var classify_name = classList[i][d.setting.classify_list.field.name]; 
								
								
								var child_level_classify_id = only + classify_code + "_child_level_classify";
								child_level_classify.push({
									id: child_level_classify_id,
									data: {
										id: classify_id,
										code: classify_code,
										name: classify_name
									}
								});
								if (i == 0) {
									a += '<li id="'+child_level_classify_id+'" class="active"><a href="#' + d.only + classify_code + '" data-toggle="tab">' + classify_name + '</a></li>';
									divTab += '<div class="tab-pane active" id="' + d.only + classify_code + '"></div>';
									
									nextId = classify_id;
									nextCode = classify_code;
									nextName = classify_name;
									
								} else {
									a += '<li id="'+child_level_classify_id+'"><a href="#' + d.only + classify_code + '" data-toggle="tab">' + classify_name + '</a></li>';
									divTab += '<div class="tab-pane " id="' + d.only + classify_code + '"></div>';
								}
							}
							$("div#" + d.only + id + ".tabbable").remove();
							$("#" + d.only + code).append(tabbable + ul + a + ulEnd + divTab + divEnd);
							
							for(var i in child_level_classify) {
								$("#" + child_level_classify[i].id).bind("click", child_level_classify[i].data, function(event) {
									tools.content.add_tab(d, event.data.id, event.data.code, event.data.name);
								});
							}
							
						} else {
							var dataTableArray = [];
							var buttonArray = [];
							
							var prop_value_bind = [];
							
							var prop_list = response[d.setting.prop_list.name];
							if (prop_list != null && prop_list.length > 0) {
								
								var prop_id_name = d.setting.prop_data_param.id_name;
								var prop_value_name = d.setting.prop_data_param.value_name;
								
								for (var i in prop_list) {
									var prop_id = prop_list[i][d.setting.prop_list.field.id];
									var prop_code = prop_list[i][d.setting.prop_list.field.code];
									var prop_name = prop_list[i][d.setting.prop_list.field.name];
									var prop_value = prop_list[i][d.setting.prop_list.field.value];
									var prop_display = prop_list[i][d.setting.prop_list.field.display];
									var prop_must = prop_list[i][d.setting.prop_list.field.must];
									var prop_source = prop_list[i][d.setting.prop_list.field.source];
									
									if(prop_value == null) {
										prop_value = "";
									}
									
									var prop_value_html_id = d.only + code + prop_code;

									if (prop_display == 'select') {
										inDivTab += '<input type="hidden" name="'+ prop_id_name +'" value="' + prop_id + '" />';
										inDivTab += '<div>';
										inDivTab += '<div class="control-group span3">';
										inDivTab += '<label class="control-label" for="form-field-1">';
										if (prop_must == "YES") {
											inDivTab += '<span class="red">*</span>';
										}

										inDivTab += prop_name + ":";
										inDivTab += '</label>';

										inDivTab += '<div class="controls">';
										inDivTab += '<select id="' + prop_value_html_id + '" name="'+ prop_value_name +'" style="height:25px;" value="' + prop_value + '" class="span10">';
										inDivTab += '<option value="">----选择----</option>';
										
										if(response.dicMap != null) {
											var selectData = response.dicMap[prop_source];
											if(selectData != null) {
												for(var selectKey in selectData) {
													inDivTab += '<option value="' + selectKey + '"';
													if (selectKey == prop_value) {
														inDivTab += ' selected="selected" ';
													}
													inDivTab += '>' + selectData[selectKey] + '</option>';
												}
											}
											
										}
										
										inDivTab += '</select>';
										inDivTab += '</div>';
										
										prop_value_bind.push(prop_value_html_id);
									}
									if (prop_display == 'text') {
										inDivTab += '<input type="hidden" name="'+ prop_id_name +'" value="' + prop_id + '" />';
										inDivTab += '<div>';
										inDivTab += '<div class="control-group span3">';
										inDivTab += '<label class="control-label" for="form-field-1">';
										if (prop_must == "YES") {
											inDivTab += '<span class="red">*</span>';
										}

										inDivTab += prop_name + ":";
										inDivTab += '</label>';

										inDivTab += '<div class="controls">';
										inDivTab += '<input type="text" name="'+ prop_value_name +'" id="' + prop_value_html_id + '" value="' + prop_value + '" class="span10" />';
										inDivTab += '</div>';
										
										prop_value_bind.push(prop_value_html_id);
									}
									if (prop_display == 'textarea') {

										inDivTab += '<input type="hidden" name="'+ prop_id_name +'" value="' + prop_id + '" />';
										inDivTab += '<div>';
										inDivTab += '<div class="control-group  span3">';
										inDivTab += '<label class="control-label" for="form-field-1">';
										if (prop_must == "YES") {
											inDivTab += '<span class="red">*</span>';
										}

										inDivTab += prop_name + ":";
										inDivTab += '</label>';

										inDivTab += '<div class="controls">';
										inDivTab += '<textarea name="'+ prop_value_name +'" id="' + prop_value_html_id + '"  class="span10 h50">' + prop_value + '</textarea>';
										inDivTab += '</div>';
										
										prop_value_bind.push(prop_value_html_id);
									}
									if (prop_display == 'button') {
										var onclick = "";
										if (prop_source != "") {
											var sourceObj = JSON.parse(prop_source);
											onclick = sourceObj.onclick;
										}

										inDivTab += '<input type="hidden" name="'+ prop_id_name +'" value="' + prop_id + '" />';
										inDivTab += '<input type="hidden" name="'+ prop_value_name +'" id="button_' + prop_code + '" value="' + prop_id + '" />';
										inDivTab += '<div class="row-fluid"><div class="ace-thumbnails">';
										inDivTab += '<button type="button" class="btn btn-small btn-primary" id="' + d.only + prop_code + '">';
										inDivTab += '<i class="icon-plus-sign" bigger-125=""></i>' + prop_name;
										inDivTab += '</button>';
										inDivTab += '</div></div>';

										var buttonObj = {
											"id" : d.only + prop_list[i].code,
											"click" : onclick
										};
										buttonArray.push(buttonObj);
									}
									if (prop_list[i].display == 'datatable') {

										inDivTab += '<input type="hidden" name="'+ prop_id_name +'" value="' + prop_id + '" />';
										inDivTab += '<input type="hidden" name="'+ prop_value_name +'" id="datatable_' + prop_code + '" value="' + prop_id + '" />';

										inDivTab += '<div class="table-header"><i class="icon-flag"></i>&nbsp;&nbsp;' + prop_name + '</div>';
										inDivTab += '<table id="' + d.only + prop_code + '" class="table table-striped table-bordered table-hover">';
										inDivTab += '<thead>' + '<tr>';
										var sourceObj = JSON.parse(prop_source);
										var columnCount = sourceObj.columnCount;
										for (var x = 0; x < columnCount; x++) {
											inDivTab += '<th width="3%" align="center"></th>';
										}
										inDivTab += '</tr>' + '</thead>' + '<tbody align="center" style="line-height: 18px; border: 1px solid #97bbdc;">' + '</tbody>' + '<tfoot>' + '<tr>';
										for (var x = 0; x < prop_list[i].source; x++) {
											inDivTab += '<th width="3%" align="center"></th>';
										}
										inDivTab += '</tr>' + '</tfoot>';
										inDivTab += '</table>';

										var dataTableObj = {
											"id" : d.only + prop_list[i].code,
											"ajaxSource" : sourceObj.ajaxSource,
											"columnCount" : sourceObj.columnCount,
											"serverParams" : sourceObj.serverParams,
											"columns" : sourceObj.columns
										};
										dataTableArray.push(dataTableObj);
									}

									inDivTab += '</div>';
									inDivTab += '</div>';
								}
							}
							
							$("#" + d.only + code).empty();
							$("#" + d.only + code).append(inDivTab);
							
							for (var index = 0; index < dataTableArray.length; index++) {
								tools.dataTable.create(d, dataTableArray[index]);
							}
							for (var index = 0; index < buttonArray.length; index++) {
								tools.bindingOnClick(d, buttonArray[index]);
							}
							
							for(var i in prop_value_bind) {
								$("#" + prop_value_bind[i]).bind("blur", {dat: d}, tools.event.valueProp.blur);
							}
							
						}
						
						if (nextId != "" && nextCode != "" && nextName != "") {
							tools.content.add_tab(d, nextId, nextCode, nextName, function() {
								if(current_theme == "3") {
									$("#" + d.only + code).addClass("active");
								}
							});
							return;
						}
						// 将页面缓存的数据重新赋值给属性信息
						tools.valueCache.cacheToElements(d);
						
						if($.isFunction(callback)) {
							callback(id, code, name);
						}
						
					},
					"error" : function(response) {
						alert("error");
					}
				});
				
			},
			init_warp_content: function(d) {
				d.warp_theme = $("<div>", {
					id: d.only + "warp_theme",
					"class": "row-fluid"
				});
				
				d.warp_content = $("<div>", {
					id: d.only + "warp_content",
					"class": "row-fluid"
				});
				
				d.warp.append(d.warp_theme);
				d.warp.append(d.warp_content);
				
				var warp_theme_html = new StringBuffer();
				warp_theme_html.add('<div class="control-group span6">');
				warp_theme_html.add('<label class="control-label" for="form-field-1">布局方式：</label>');
				warp_theme_html.add('<div class="controls">');
				warp_theme_html.add('<select id="{id}" name="{name}" style="width: 150px;">', {id: d.theme.id, name: d.theme.name});
				warp_theme_html.add('<option value="1">tab方式</option>');
				warp_theme_html.add('<option value="2">accordion方式</option>');
				warp_theme_html.add('<option value="3">sequence方式</option>');
				warp_theme_html.add('</select>');
				warp_theme_html.add('<span class="help-inline"></span>');
				warp_theme_html.add('</div>');
				warp_theme_html.add('</div>');
				
				d.warp_theme.append(warp_theme_html.toString());
				
				$("#" + d.theme.id).bind("change", {dat: d}, tools.event.theme.change);
				
			}
		},
		event: {
			theme: {
				change: function(event) {
					tools.content.init_detail(event.data.dat);
				}
			},
			valueProp: {
				blur: function(event) {
					tools.valueCache.putValue(event.data.dat, this);
				}
			},
			accordion: {
				click: function(obj, dd, id, code, name) {
					var openClassName = "icon-folder-open";
					var closeClassName = "icon-folder-close";
					var imark = $(obj).find("i");
					var disp = $("#" + dd.only + code).css("display"); // 当前accordion显示状态
					if(disp == "none") {
						tools.content.add_tab(dd, id, code, name);
						imark.removeClass(closeClassName).addClass(openClassName);
					} else {
						imark.removeClass(openClassName).addClass(closeClassName);
					}
					$("#" + dd.only + code).slideToggle("fast");
				}
			}
		}
	},
	dataHold = new Map(),
	Data = function(_setting) {
		var t = this;
		this.valueCounter = -1;
		this.setting = _setting;
		this.propValueCache = new Map();
		this.warp = $("#" + this.setting.renderTo);
		this.warp_theme = null,
		this.warp_content = null;
		this.only = this.warp.attr("id") + "_";
		this.dataTable = new Map();
		
		this.theme = {};
		this.theme.id = this.only + "theme";
		this.theme.name = this.theme.id;
		this.theme.value = function() {
			return $("#" + this.id).val();
		};
		
		this.next = function() {
			this.valueCounter++;
			return this.valueCounter;
		};
		
		this.warp.empty();
		tools.content.init_warp_content(this);
		tools.content.init_detail(this);
		
		dataHold.put(this.setting.renderTo, this);
		
	};
	
	$.customProperty = {
		create: function(_setting) {
			return new Data(_setting);
		},
		destroy: function(renderTo) {
			var hd = dataHold.get(renderTo);
			if(hd != null) {
				hd.propValueCache.clear();
				hd.dataTable.clear();
				hd.warp.empty();
				hd = null;
				dataHold.remove(renderTo);
			}
		},
		refreshDataTable: function(renderTo, dataTableId) {
			var hd = dataHold.get(renderTo);
			if(hd != null) {
				var dt = hd.dataTable.get(renderTo + "_" + dataTableId);
				if(dt != null) {
					dt.fnStandingRedraw();
				}
			}
		}
		
	};
	
	
})(jQuery);