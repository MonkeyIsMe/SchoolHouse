/**
 * 
 */

var mydata = [];

function GetAreaInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryAllArea.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				for (var i = 0; i < data.length; i++) {
					//alert(mydata.length);
					mydata.push(data[i]);
		}
	});
}

$(document).ready(function() {
	GetAreaInfo()
	$.jgrid.defaults.styleUI = "Bootstrap";
	var obj_i=[];
	$("#table_list_2").jqGrid({
		data : mydata,
		datatype : "local",
		height : 450,
		autowidth : true,
		shrinkToFit : true,
		rowNum : 20,
		rowList : [ 10, 20, 30 ],
		multiselect : true,
		 onSelectRow: function(id, stats) {
	            if (stats) {
	                obj_i.push(id);
	            } else {
	                obj_i.shift();
	            }
	        },
		colNames : [ "序号", "学区名称", "创建时间" ],
		colModel : [ {
			name : "areaId",
			index : "areaId",
			editable : true,
			width : 60,
			sorttype : "int",
			search : true
		}, {
			name : "areaName",
			index : "areaName",
			editable : true,
			width : 90
		}, {
			name : "areaCreatTime",
			index : "areaCreatTime",
			editable : true,
			width : 100
		} ],
		pager : "#pager_list_2",
		viewrecords : true,
		add : true,
		edit : true,
		addtext : "Add",
		edittext : "Edit",
		hidegrid : false
	});
	$("#table_list_2").setSelection(0, true);
	$("#table_list_2").jqGrid("navGrid", "#pager_list_2", {
		edit : true,
		add : true,
		del : true,
		search : false
	}, {
		height : 400,
		reloadAfterSubmit : true
	});
	$(window).bind("resize", function() {
		var width = $(".jqGrid_wrapper").width();
		$("#table_list_2").setGridWidth(width)
	})
	
    var obj_edit = $("#table_list_2").jqGrid("getRowData");//获取修改时多选的id，并放入到数组obj_edit中
    var obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");//获取到删除时多选的id，并放入到数组obj_del中
    
    $("#bt_add").click(function() {
        $("#mySave").css("display","inline");
        $("#myEdit").css("display","none");
        $("#bt_add").attr("data-target","#add-edit");
        $("#mySave").click(function(){
            var AreaName = $("#SDName").val();
        	if(AreaName == null || AreaName == ""){
        		alert("所填均为非空!");
        	}
        	else{
        		//alert(AreaName);
				$(function(){
					$.post(
							"AddArea.action",
							{
								area_name:AreaName,
							},
							function(data){
								data = data.replace(/^\s*/, "").replace(/\s*$/, "");
								if(data == "Success"){
									alert("添加成功！！");
									window.location.replace("hw_table_school_district.html");
								}
								else{
									alert("添加成功！！");
									window.location.replace("hw_table_school_district.html");
								}
							}
							);
					
				});
        	}
        })
    });
    
    $("#bt_edit").click(function() {
        $("#mySave").css("display","none");
        $("#myEdit").css("display","inline");
        if(obj_i.length==1){
            // alert(obj_edit[obj_i[obj_i.length-1]-1].schoolName);
            $("#bt_edit").attr("data-target","#add-edit");
            $("#SDName").val(obj_edit[obj_i[obj_i.length-1]-1].areaName)
            $("#myEdit").click(function(){
            	var aid = obj_edit[obj_i[obj_i.length-1]-1].areaId;
                var AreaName = $("#SDName").val();
            	if(AreaName == null || AreaName == ""){
            		alert("所填均为非空!");
            	}
            	else{
            		//alert(AreaName);
					$(function(){
						$.post(
								"UpdateArea.action",
								{
									area_name:AreaName,
									area_id:aid,
								},
								function(data){
									data = data.replace(/^\s*/, "").replace(/\s*$/, "");
									if(data == "Success"){
										alert("更新成功！！");
										window.location.replace("hw_table_school_district.html");
									}
									else{
										alert("更新成功！！");
										window.location.replace("hw_table_school_district.html");
									}
								}
								);
						
					});
            	}
            })
            
        }else if(obj_i.length < 1){
            $("#bt_edit").attr("data-target","");
            alert("请至少选择1项！");
        }else{
            $("#bt_edit").attr("data-target","");
            alert("请只选择1项！");
        }
    });
    
    $("#bt_del").click(function() {
        if(obj_del.length<1){
            alert("请至少选择1项！");
        }else{
            // alert(obj_del);
            $("#bt_del").attr("data-target","#mydel");
            $("#myDelete").click(function(){
            	for(var i = 0 ; i<obj_del.length ; i++){
		    		$(function(){
		    			$.post(
		    					"DeleteArea.action",
		    					{
		    						area_id:obj_edit[obj_del[i]-1].schoolId
		    					},
		    					function(data){
		    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
		    						if(data == "Success"){
		    							alert("删除成功 ！！");
		    							window.location.replace("hw_table_school.html");
		    						}
		    						else{
		    							alert("删除失败 ！！");
		    							window.location.replace("hw_table_school.html");
		    						}
		    					}
		    					);
		    			
		    		});
            	}
            })
        }
        
    }); 
});
