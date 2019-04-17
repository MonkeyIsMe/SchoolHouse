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
			"QueryBuildingBySchoolIdFlag.action",
			{
				school_id:schoolid
			}, 
			function(data) {
				var data = JSON.parse(data);
				for (var i = 0; i < data.length; i++) {
					//alert(mydata.length);
					$("#Choosebuilding").append("<option value="+ data[i].buildingId +">"+ data[i].buildingName+"</option>");
		}
	});
}


function GetHouseInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryHouseByBuildingId.action",
			{
				area_id:areaid,
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
	GetHouseInfo();
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
        colNames: ["序号", "所属楼盘名称", "栋数", "房号", "创建时间"],
        colModel: [{
            name: "houseId",
            index: "houseId",
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
            name: "houseName",
            index: "houseName",
            editable: true,
            width: 100
        },
        {
            name: "houseRoom",
            index: "houseRoom",
            editable: true,
            width: 80,
            align: "left"
        },
        {
            name: "houseCreateTime",
            index: "houseCreateTime",
            editable: true,
            width: 100,
            sortable: false
        }],
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
        search: false
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
		var op1 = $("#Choosebuilding option:selected");
		var bId = op1.val();
        var buildNum = $("#buildNum").val();
        var houseNum = $("#houseNum").val();
        if(houseNum == null || houseNum == "" ||  buildNum == null || buildNum =="" || bId == null || bId ==""){
        	alert("所有项均为非空!");
        }
        else{
			$(function(){
				
				$.post(
						"HouseIsExit.action",
						{
							building_id:bId,
							house_name:buildNum,
							house_room:houseNum,
						},
						function(data){
							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
							if(data == "Success"){
								$.post(
										"AddHouse.action",
										{
											buding_id:bId,
											house_name:buildNum,
											house_room:houseNum,
										},
										function(data){
											data = data.replace(/^\s*/, "").replace(/\s*$/, "");
											if(data == "Success"){
												alert("添加成功！！");
												window.location.replace("hw_table_house.html");
											}
											else{
												alert("添加失败！！");
												window.location.replace("hw_table_house.html");
											}
										}
										);
							}
							else{
								alert("房屋已经存在！！");
								window.location.replace("hw_table_house.html");
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
            
            $("#bId").val(obj_edit[obj_del[0]-1].buildingId);
            $("#buildNum").val(obj_edit[obj_del[0]-1].houseName);
            $("#houseNum").val(obj_edit[obj_del[0]-1].houseRoom);
            
            
        }else if(obj_del.length < 1){
            $("#bt_edit").attr("data-target","");
            $("#myless").modal("show");
        }else{
            $("#bt_edit").attr("data-target","");
            $("#mymore").modal("show");
        }
    });
    
	
    $("#myEdit").click(function(){
    	var op1 = $("#Choosebuilding option:selected");
    	var bId = op1.val();
        var buildNum = $("#buildNum").val();
        var houseNum = $("#houseNum").val();
        var hid = obj_edit[obj_del[0]-1].houseId;
        if(houseNum == null || houseNum == "" ||  buildNum == null || buildNum =="" || bId == null || bId ==""){
        	alert("所有项均为非空!");
        }
        else{
			$(function(){
				$.post(
						"UpdateHouse.action",
						{
							buding_id:bId,
							house_name:buildNum,
							house_room:houseNum,
							house_id:hid,
						},
						function(data){
							data = data.replace(/^\s*/, "").replace(/\s*$/, "");
							if(data == "Success"){
								alert("更新成功！！");
								window.location.replace("hw_table_house.html");
							}
							else{
								alert("更新失败！！");
								window.location.replace("hw_table_house.html");
							}
						}
						);
				
			});
        }
    })
    
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
			$("#myDelete").click(function(){
				for(var i = 0 ; i< obj_del.length ; i++){
    		$(function(){
    			$.post(
    					"DeteleHouse.action",
    					{
    						house_id:obj_edit[obj_del[i]-1].houseId,
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
    			
    		});
        }
				alert("删除成功：" +success + "项，删除失败：" + fail + "项" );
				window.location.replace("hw_table_house.html");
			})
        }
        
    }); 
});