/**
 * 
 */

var mydata = [];
var obj_edit;
var obj_del;
var len;

function GetAreaInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryAllArea.action",
			{
				
			}, 
			function(data) {
				
				var data = JSON.parse(data);
				len = data.length;
				//alert(len);
				for (var i = 0; i < data.length; i++) {
					//alert(mydata.length);
					mydata.push(data[i]);
		}
	});
}
var obj_edit
$(document).ready(function() {
	GetAreaInfo()
	$.jgrid.defaults.styleUI = "Bootstrap";
	$("#table_list_2").jqGrid({
		data : mydata,
		datatype : "local",
		height : 450,
		autowidth : true,
		shrinkToFit : true,
		rowNum : 20,
		rowList : [ 10, 20, 30 ],
		multiselect : true,
		colNames : [ "学区编号", "学区名称", "创建时间" ],
		colModel : [ {
			name : "areaId",
			index : "areaId",
			editable : true,
			width : 60,
			sorttype : "int",
			search : false,
			searchoptions: {sopt:['cn']},
		}, {
			name : "areaName",
			index : "areaName",
			editable : true,
			width : 90,
			searchoptions: {sopt:['cn']},
		}, {
			name : "areaCreatTime",
			index : "areaCreatTime",
			editable : true,
			width : 100,
			search : false,
			searchoptions: {sopt:['cn']},
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
		edit : false,
		add : false,
		del : false,
		search : true
	}, {
		height : 400,
		reloadAfterSubmit : true
	});
	$(window).bind("resize", function() {
		var width = $(".jqGrid_wrapper").width();
		$("#table_list_2").setGridWidth(width)
	})
	
    obj_edit = $("#table_list_2").jqGrid("getRowData");//获取修改时多选的id，并放入到数组obj_edit中
    
    $("#bt_add").click(function() {
        $("#mySave").css("display","inline");
        $("#myEdit").css("display","none");
        $("#bt_add").attr("data-target","#add-edit");
    });
    
    $("#mySave").click(function(){
        var AreaName = $("#SDName").val();
    	if(AreaName == null || AreaName == ""){
    		alert("所填均为非空!");
    	}
    	else{
    		//alert(AreaName);
			$(function(){
				
				$.post(
						"AreaByName.action",
						{
							area_name:AreaName,
						},
						function(data){
							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
							if(data == "Success"){
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
    
    $("#bt_edit").click(function() {
    	obj_edit = $("#table_list_2").jqGrid("getRowData");
    	obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
        $("#mySave").css("display","none");
        $("#myEdit").css("display","inline");
        if(obj_del.length==1){
            // alert(obj_edit[obj_del[0]-1].schoolName);
            $("#bt_edit").attr("data-target","#add-edit");
            $("#SDName").val(obj_edit[(obj_del[0]-1)%20].areaName);
            
        }else if(obj_del.length < 1){
            alert("请至少选择1项！");
			$("#bt_edit").attr("data-target","");
        }else{
            alert("请至多选择1项！");
			$("#bt_edit").attr("data-target","");
        }
    });
    
    $("#myEdit").click(function(){
    	var aid = obj_edit[(obj_del[0]-1)%20].areaId;
        var AreaName = $("#SDName").val();
    	if(AreaName == null || AreaName == ""){
    		alert("所填均为非空!");
    	}
    	else{
			$(function(){
				//alert(AreaName);
				$.post(
						"UpdateArea.action",
						{
							area_name:AreaName,
							area_id:aid,
						},
						function(data){
							//alert(AreaName);
							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
							if(data == "Success"){
								alert("更新成功！！");
								window.location.replace("hw_table_school_district.html");
							}
							else{
								alert("更新失败！！");
								window.location.replace("hw_table_school_district.html");
							}
						}
						);
				

				
			});
    	}
    })
    
    $("#bt_excel").click(function(){
    	//alert(1111);
    	window.open("add_excel_xuequ.html");
    })
    
    $("#bt_del").click(function() {
    	var ob = $("#table_list_2").jqGrid("getRowData");
    	obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
        if(obj_del.length<1){
			alert("请至少选择1项！");
			$("#bt_edit").attr("data-target","");
        }else{
        	//alert(obj_del.length);
            // alert(obj_del);
        	var success = 0; 
        	var fail = 0;
            $("#bt_del").attr("data-target","#mydel");
            $("#myDelete").click(function(){
            	for(var i = 0 ; i<obj_del.length ; i++){
		    		$(function(){
		    			$.post(
		    					"DeleteArea.action",
		    					{
		    						area_id:ob[(obj_del[i]-1)%20].areaId
		    					},
		    					function(data){
		    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
		    						if(data == "Success"){
		    							success++ ;
		    							//window.location.replace("hw_table_school_district.html");
		    						}
		    						else{
		    							fail++;
		    							//window.location.replace("hw_table_school_district.html");
		    						}
		    					}
		    					);
		    			
		    		});
            	}
            	alert("删除成功：" +success + "项，删除失败：" + fail + "项" );
            	window.location.replace("hw_table_school_district.html");
            })
        }
        
    }); 
});

function myrefresh(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryAllArea.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				//alert(len + "    " + data.length +" " + (len == data.length))
				if(data.length != len){
					window.location.replace("hw_table_school_district.html");
				}
	});
}

setInterval(myrefresh,2000); //指定1秒刷新一次