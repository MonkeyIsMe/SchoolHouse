/**
 * 
 */

mydata = [];
var length;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryTempStudentCheck.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				length = data.length;
		}
	);
	
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryTempStudentCheck.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				console.log(data);
		}
	);
	
});


$(document).ready(function() {
    $.jgrid.defaults.styleUI = "Bootstrap";
    $("#table_list_2").jqGrid({
    	url:"QueryTempStudentCheck.action",
    	datatype:"json",
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
        	"房产证号", "房屋预告登记号", "所属楼盘", "地址", "所属学校", "学生联系电话", "使用日期","操作"],
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
            width: 30,
            search:false,
            align: "left"
        },
        {
            name: "studentHouseCard",
            index: "studentHouseCard",
            editable: true,
            width: 30,
            search:false,
            align: "left"
        },
        {
            name: "studentHousePreCard",
            index: "studentHousePreCard",
            editable: true,
            width: 40,
            search:false,
            align: "left"
        },
        {
            name: "studentBuildingId",
            index: "studentBuildingId",
            editable: true,
            width: 30,
            search:false,
            align: "left"
        },
        {
            name: "studentHouseNumber",
            index: "studentHouseNumber",
            searchoptions: {sopt:['cn']},
            editable: true,
            width: 20,
            align: "left"
        },
        {
            name: "studentSchool",
            index: "studentSchool",
            editable: true,
            search:false,
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
        },
        {
            name: "check",
            index: "check",
            editable: true,
            search:false,
            width: 150,
            align: "center",
            formatter: function (value, grid, rows, state) { return "<button type='button' class=btn btn-primary' title='通过' style='background-color:#B8B8B8'  onclick='approve("+rows.studentId+")'>通过" +
            		"</button>&nbsp;&nbsp;<button type='button' class=btn btn-primary' style='background-color:#B8B8B8' onclick='disapprove("+ rows.studentId +")'>不通过</button>"
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
    $("#table_list_2").setSelection(0, true);
    $("#table_list_2").jqGrid("navGrid", "#pager_list_2", {
        edit: false,
        add: false,
        del: false,
        search: true
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
    

    
});
function approve(uid){
	//alert(id);
	$.post(
			"SaveStudent.action",
			{
				student_id:uid,
			}, 
			function(data){
				data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				if(data == "Success"){
					alert("审核通过");
					window.location.replace("See_in_student_all.html");
				}
				else{
					alert("操作失败 ！！");
					window.location.replace("See_in_student_all.html");
				}
			}
			);
}

function myrefresh(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryTempStudentCheck.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				if(data.length != length){
					window.location.replace("See_in_student_all.html");
				}
	});
}
setInterval(myrefresh,1000); //指定1秒刷新一次