/**
 * 
 */

mydata = [];

var rows = 1;

var PageNumber;
var len;

function StudentInfolen(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryAllStudent.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				len = data.length;
				PageNumber = Math.ceil(len/20);
	});
}

$(document).ready(function() {
	StudentInfolen();
    $.jgrid.defaults.styleUI = "Bootstrap";
    $("#table_list_2").jqGrid({
    	url:"QueryStudentByPageSize.action",
    	datatype:"json",
    	postData : {
    		rowss:rows,
    		pagesize:20,
    		},
        //data: mydata,
        //datatype: "local",
        height: 450,
        autowidth: true,
        shrinkToFit: true,
        rowNum: 20,
        multiselect: true,
        celledit: true,
        editurl:"",
        colNames: ["序号", "学生姓名", "学生身份证号", "父亲姓名", 
        	"母亲姓名", "业主姓名", "业主身份证号", "与业主关系", 
        	"房产证号", "房屋预告登记号", "所属楼盘", "地址",  "所属学校", "学生联系电话", "使用日期"],
        colModel: [{
            name: "studentId",
            index: "studentId",
            editable: true,
            width: 15,
            sorttype: "int",
            search:false,
        },
        {
            name: "studentName",
            index: "studentName",
            editable: true,
            search:false,
            width: 25
        },
        {
            name: "studentIdCard",
            index: "studentIdCard",
            editable: true,
            search:false,
            width: 50
        },
        {
            name: "studentFather",
            index: "studentFather",
            editable: true,
            width: 25,
            search:false,
            align: "left"
        },
        {
            name: "studentMother",
            index: "studentMother",
            editable: true,
            width: 25,
            search:false,
            align: "left"
        },
        {
            name: "studentHouseOwner",
            index: "studentHouseOwner",
            editable: true,
            width: 30,
            search:false,
            align: "left"
        },
        {
            name: "studentOwnerId",
            index: "studentOwnerId",
            editable: true,
            width: 50,
            search:false,
            sortable: false
        },
        {
            name: "studentRelation",
            index: "studentRelation",
            editable: true,
            search:false,
            width: 30,
            align: "left"
        },
        {
            name: "studentHouseCard",
            index: "studentHouseCard",
            editable: true,
            search:false,
            width: 30,
            align: "left"
        },
        {
            name: "studentHousePreCard",
            index: "studentHousePreCard",
            editable: true,
            search:false,
            width: 40,
            align: "left"
        },
        {
            name: "studentBuildingId",
            index: "studentBuildingId",
            editable: true,
            search:false,
            width: 30,
            align: "left"
        },
        {
            name: "studentHouseNumber",
            index: "studentHouseNumber",
            editable: true,
            search:false,
            width: 20,
            align: "left"
        },
        {
            name: "studentSchool",
            index: "studentSchool",
            search:false,
            editable: true,
            width: 30,
            align: "left"
        },
        {
            name: "studentPhone",
            index: "studentPhone",
            editable: true,
            search:false,
            width: 40,
            align: "left"
        },
        {
            name: "studentUseTime",
            index: "studentUseTime",
            editable: true,
            search:false,
            width: 30,
            align: "left"
        }],
        pager: "#pager_list_2",
        viewrecords: true,
        add: true,
        edit: true,
        addtext: "Add",
        edittext: "Edit",
        hidegrid: false
    });
    $("#table_list_2").setSelection(0, true);
    $("#table_list_2").jqGrid("navGrid", "#pager_list_2", {
        edit: false,
        add: false,
        del: false,
        search: false
    },
    {
        height: 300,
        reloadAfterSubmit: true
    });
    $(window).bind("resize",
    function() {
        var width = $(".jqGrid_wrapper").width();
        $("#table_list_2").setGridWidth(width)
    });
    $("#sp_1_pager_list_2").text(PageNumber);
    
	$("#next_pager_list_2").click(function(){
		//$("#table_list_2").jqGrid('reloadGrid'); 
		if(rows < PageNumber) rows = rows + 1;
		//console.log(rows);
		$("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
			postData: {
	    		rowss:rows,
	    		pagesize:20,
			}
		}).trigger("reloadGrid");
		$("#sp_1_pager_list_2").text(PageNumber);
		$("#input_pager_list_2").children().first().val(rows);
	})
	
		$("#prev_pager_list_2").click(function(){
		//$("#table_list_2").jqGrid('reloadGrid'); 
		if(rows!= 1)
		rows = rows - 1;
		console.log(rows);
		$("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
			postData: {
	    		rowss:rows,
	    		pagesize:20,
			}
		}).trigger("reloadGrid");
		$("#sp_1_pager_list_2").text(PageNumber);
		$("#input_pager_list_2").children().first().val(rows);
	})
    
	$("#last_pager_list_2").click(function(){
		rows = PageNumber;
	$("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
			postData: {
	    		rowss:PageNumber,
	    		pagesize:20,
			}
		}).trigger("reloadGrid");
		$("#sp_1_pager_list_2").text(PageNumber);
		$("#input_pager_list_2").children().first().val(rows);
	})
	
	$("#first_pager_list_2").click(function(){
		rows = 1;
	$("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
			postData: {
	    		rowss:1,
	    		pagesize:20,
			}
		}).trigger("reloadGrid");
		$("#sp_1_pager_list_2").text(PageNumber);
		$("#input_pager_list_2").children().first().val(rows);
	})
	
	document.onkeydown = function(e){
	    if(e.keyCode == 13){
	    	//alert("回车键事件触发~");
	    	var spage = $("#input_pager_list_2").children().first().val();
			rows = spage;
			//alert(spage)
			$("#table_list_2").jqGrid('setGridParam',{  // 重新加载数据
					postData: {
			    		rowss:spage,
			    		pagesize:20,
					}
				}).trigger("reloadGrid");
				$("#sp_1_pager_list_2").text(PageNumber);
				$("#input_pager_list_2").children().first().val(rows);
	    }
	}
	
});


$("#search_info").click(function(){
	var PlaceInfo = $("#search_input").val();

	window.open("EduSearch.html?place=" + PlaceInfo);
})


function myrefresh(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryAllStudent.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				//alert(len +" " + data.length);
				if(data.length != len){
					window.location.replace("student_all.html");
				}
	});
}
setInterval(myrefresh,2000); //指定1秒刷新一次
