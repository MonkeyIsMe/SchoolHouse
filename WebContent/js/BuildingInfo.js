/**
 * 
 */
var mydata = [];
var areaid;
var SchoolName;
var schoolid;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"GetSchoolUserInfo.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				schoolid = data.schoolid;
				//console.log(data)
		}
	);
	
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryBySchoolId.action",
			{
				school_id:schoolid,
			}, 
			function(data) {
				var data = JSON.parse(data);
				areaid = data.areaId;
				//console.log(data)
		}
	);
	
});

function GetBuildingInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryBuildingByAreaId.action",
			{
				area_id:areaid
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
	//console.log("area = " + areaid);
	GetBuildingInfo();
    $.jgrid.defaults.styleUI = "Bootstrap";
    $("#table_list_2").jqGrid({
        data: mydata,
        datatype: "local",
        height: 450,
        autowidth: true,
        shrinkToFit: true,
        rowNum: 20,
        rowList: [10, 20, 30],
        multiselect: true,
        celledit:true,
        editurl: "hw_index_1.html",
        colNames: ["序号","楼盘名称", "楼盘地址", "楼盘所属街道","创建时间","审批状态"],
        colModel: [{
            name: "buildingId",
            index: "buildingId",
            editable: true,
            width: 60,
            sorttype: "int",
            search: true
        },
        {
            name: "buildingName",
            index: "buildingName",
            editable: true,
            width: 90
        },
        {
            name: "buildingAddress",
            index: "buildingAddress",
            editable: true,
            width: 100
        },
        {
            name: "buildingStreet",
            index: "buildingStreet",
            editable: true,
            width: 80,
            align: "left"
        },
        {
            name: "buildingCreatTime",
            index: "buildingCreatTime",
            editable: true,
            width: 100,
            sortable: false
        },
        {
            name: "buildingHint",
            index: "buildingHint",
            editable: true,
            width: 100,
            sortable: false
        }
        ],
        pager: "#pager_list_2",
        viewrecords: true,
        add: true,
        edit: true,
        addtext: "Add",
        edittext: "Edit",
        hidegrid: false
    });

    $("#table_list_2").jqGrid("navGrid", "#pager_list_2", {
        edit: false,
        add: false,
        del: false,
        search: true
    },
    {
        height: 400,
        reloadAfterSubmit: true
    });
    $(window).bind("resize",
    function() {
        var width = $(".jqGrid_wrapper").width();
        $("#table_list_2").setGridWidth(width)
    });
  
    var obj_edit = $("#table_list_2").jqGrid("getRowData");//获取修改时多选的id，并放入到数组obj_edit中
    var obj_del;//获取到删除时多选的id，并放入到数组obj_del中
    $("#bt_add").click(function() {
        $("#mySave").css("display","inline");
        $("#myEdit").css("display","none");
        $("#bt_add").attr("data-target","#add-edit");
        
    });
    
	$("#mySave").click(function(){
		var buildStreet = $("#buildStreet").val();
        var buildAddress = $("#buildAddress").val();
        var buildName = $("#buildName").val();
        if(buildStreet == null || buildStreet == "" || buildAddress == null || buildAddress == "" || buildName == null || buildName ==""){
        	alert("所有项均为非空!");
        }
        else{
			$(function(){
				
				$.post(
						"BuildingIsAlready.action",
						{
							area_id:areaid,
							building_name:buildName,
						},
						function(data){
							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
							if(data == "Success"){
								$.post(
										"AddBuilding.action",
										{
											building_street:buildStreet,
											building_address:buildAddress,
											area_id:areaid,
											building_name:buildName,
											school_id:schoolid,
										},
										function(data){
											data = data.replace(/^\s*/, "").replace(/\s*$/, "");
											if(data == "Success"){
												alert("添加成功！！");
												window.location.replace("hw_table_building.html");
											}
											else{
												alert("添加失败！！");
												window.location.replace("hw_table_building.html");
											}
										}
										);
							}
							else{
								alert("楼盘已存在！！");
								window.location.replace("hw_table_building.html");
							}
						}
						);
				

				
			});
        }
	})
    
    
    $("#myCancel").click(function(){
    	$("#bt_edit").attr("data-target","");
    	$("#bt_del").attr("data-target","");
    	$("#bt_add").attr("data-target","");
    	$("#add-edit").hide();
    })
    
             $("#myEdit").click(function(){
 
            	var buildStreet = $("#buildStreet").val();
    	        var buildAddress = $("#buildAddress").val();
    	        var buildName = $("#buildName").val();
    	        
                var bid = obj_edit[obj_del[0]-1].buildingId;
                //	alert(obj_edit[obj_del[0]-1].areaId)
                if(buildStreet == null || buildStreet == "" || buildAddress == null || buildAddress == "" || buildName == null || buildName ==""){
                	alert("所有项均为非空!");
                }
                else{
        			$(function(){
        				$.post(
        						"AreaIsExit.action",
        						{
    								area_id:areaid,
        						},
        						function(data){
        							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
        							if(data == "Success"){
        		        				$.post(
        		        						"UpdateBuilding.action",
        		        						{
        		        							building_street:buildStreet,
        		    								building_address:buildAddress,
        		    								area_id:areaid,
        		    								building_name:buildName,
        		    								building_id:bid,
        		        						},
        		        						function(data){
        		        							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
        		        							if(data == "Success"){
        		        								alert("更新成功！！");
        		        								window.location.replace("hw_table_building.html");
        		        							}
        		        							else{
        		        								alert("更新失败！！");
        		        								window.location.replace("hw_table_building.html");
        		        							}
        		        						}
        		        						);
        							}
        							else{
        								alert("添加失败！！");
        								window.location.replace("hw_table_building.html");
        							}
        						}
        						);
        				
        				
        			});
                }
                
            })
    
    $("#bt_edit").click(function() {
        obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
    	$("#mySave").css("display","none");
        $("#myEdit").css("display","inline");
        if(obj_del.length==1){
            // alert(obj_edit[obj_del[0]-1].schoolName);
            $("#bt_edit").attr("data-target","#add-edit");
            
            $("#buildStreet").val(obj_edit[obj_del[0]-1].buildingStreet);
            $("#buildAddress").val(obj_edit[obj_del[0]-1].buildingAddress);
            $("#buildName").val(obj_edit[obj_del[0]-1].buildingName);
            $("#SdId").val(obj_edit[obj_del[0]-1].areaId);
            
            
        }else if(obj_del.length < 1){
            $("#bt_edit").attr("data-target","");
            $("#myless").modal("show");
        }else{
            $("#bt_edit").attr("data-target","");
            $("#mymore").modal("show");
        }
    });
    
    
    $("#bt_del").click(function() {
        obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
        if(obj_del.length<1){
            $("#bt_del").attr("data-target","");
            $("#myless").modal("show");
        }else{
            $("#bt_del").attr("data-target","#mydel");
			var success = 0; 
        	var fail = 0;
			$("#bt_del").attr("data-target", "#mydel");
			//alert(obj_edit[obj_del[0]-1].buildingId);
			$("#myDelete").click(function(){
				for(var i = 0 ; i< obj_del.length ; i++){
    		$(function(){
    			$.post(
    					"DeleteBuilding.action",
    					{
    						building_id:obj_edit[obj_del[i]-1].buildingId,
    					},
    					function(data){
    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
    						if(data == "Success"){
    							success++;
    							//alert("删除成功 ！！");
    							//window.location.replace("hw_table_school_administrator.html");
    						}
    						else{
    							fail++;
    							//("删除失败 ！！");
    							//window.location.replace("hw_table_school_administrator.html");
    						}
    					}
    					);
    			
    		});
        }
				alert("删除成功：" +success + "项，删除失败：" + fail + "项" );
				window.location.replace("hw_table_building.html");
			})
        }
        
    });
    
    
    $("#bt_sub").click(function() {
        obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
        if(obj_del.length<1){
            $("#bt_sub").attr("data-target","");
            $("#myless").modal("show");
        }else{
            $("#bt_sub").attr("data-target","#mysub");
			var success = 0; 
        	var fail = 0;
			$("#bt_sub").attr("data-target", "#mysub");
			//alert(obj_del.length);
			$("#mySub").click(function(){
				for(var i = 0 ; i< obj_del.length ; i++){
					if(obj_edit[obj_del[0]-1].buildingHint != "通过审核"){
		    			$.post(
		    					"SubmitBuilding.action",
		    					{
		    						building_id:obj_edit[obj_del[i]-1].buildingId,
		    					},
		    					function(data){
		    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
		    						if(data == "Success"){
		    							success++;
		    						}
		    						else{
		    							fail++;
		    						}
		    					}
		    					);
					}
				}
				alert("提交审核成功：" +success + "项，提交审核失败：" + fail + "项" );
				window.location.replace("hw_table_building.html");
			})
        }
        
    }); 
    
});
