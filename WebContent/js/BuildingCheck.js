/**
 * 
 */

var mydata = [];

function GetBuildingInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryBuildingInvaild.action",
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
        colNames: ["序号", "所属学校","楼盘名称", "楼盘地址", "楼盘所属街道", "创建时间","审核状态","操作"],
        colModel: [{
            name: "buildingId",
            index: "id",
            editable: true,
            width: 60,
            sorttype: "int",
            search: true
        },
        {
            name: "schoolName",
            index: "schoolName",
            editable: true,
            width: 100
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
            width: 80,
            sortable: false
        },
        {
            name: "check",
            index: "check",
            editable: true,
            width: 150,
            align: "center",
            formatter: function (value, grid, rows, state) { return "<button type='button' class=btn btn-primary' title='通过' style='background-color:#B8B8B8'  onclick='approve("+rows.buildingId+")'>通过" +
            		"</button>&nbsp;&nbsp;<button type='button' class=btn btn-primary' style='background-color:#B8B8B8' onclick='disapprove("+ rows.buildingId +")'>不通过</button>" +
            		"</button>&nbsp;&nbsp;<button type='button' class=btn btn-primary' style='background-color:#B8B8B8' onclick='DeleteBuilding("+ rows.buildingId +")'>删除</button>"	
            } 	
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
    $("#bt_edit").click(function() {
        obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
    	$("#mySave").css("display","none");
        $("#myEdit").css("display","inline");
        if(obj_del.length==1){
            // alert(obj_edit[obj_del[0]-1].schoolName);
            $("#bt_edit").attr("data-target","#add-edit");
        }else if(obj_del.length < 1){
            $("#bt_edit").attr("data-target","");
            $("#myless").modal("show");
            // alert("请至少选择1项！");
        }else{
            $("#bt_edit").attr("data-target","");
            // alert("请只选择1项！");
            $("#mymore").modal("show");
        }
    });
    $("#bt_del").click(function() {
        obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
        if(obj_del.length<1){
            //alert("请至少选择1项！");
            $("#bt_del").attr("data-target","");
            $("#myless").modal("show");
        }else{
            //alert(obj_edit[obj_del[0]-1].schoolName);
            $("#bt_del").attr("data-target","#mydel");
        }
        
    }); 
});


function approve(uid){
	//alert(uid);
	$.post(
			"SetBuildingValid.action",
			{
				building_id:uid,
			}, 
			function(data){
				data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				if(data == "Success"){
					alert("该楼栋通过审核 ！！");
					window.location.replace("hw_table_building_check.html");
				}
				else{
					alert("操作失败 ！！");
					window.location.replace("hw_table_building_check.html");
				}
			}
			);
}

function disapprove(uid){
	//alert(uid);
	$.post(
			"UnSubmitBuilding.action",
			{
				building_id:uid,
			}, 
			function(data){
				data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				if(data == "Success"){
					alert("审核不通过");
					window.location.replace("hw_table_building_check.html");
				}
				else{
					alert("操作失败 ！！");
					window.location.replace("hw_table_building_check.html");
				}
			}
			);
}


function DeleteBuilding(uid){
	$.post(
			"DeleteBuilding.action",
			{
				building_id:uid,
			}, 
			function(data){
				data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				if(data == "Success"){
					alert("删除成功");
					window.location.replace("hw_table_building_check.html");
				}
				else{
					alert("操作失败 ！！");
					window.location.replace("hw_table_building_check.html");
				}
			}
			);
}