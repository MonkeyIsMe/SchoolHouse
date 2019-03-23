/**
 * 
 */

var mydata = [];

function ApplyInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryByFlag.action",
			{
				user_flag:"-1",
			}, 
			function(data) {
				var data = JSON.parse(data);
				console.log(data);
				for (var i = 0; i < data.length; i++) {
					//alert(mydata.length);
					mydata.push(data[i]);
		}
	});
}

$(document).ready(function() {
	ApplyInfo();
					$.jgrid.defaults.styleUI = "Bootstrap";
					$("#table_list_2").jqGrid(
							{
								data : mydata,
								datatype : "local",
								height : 450,
								autowidth : true,
								shrinkToFit : true,
								rowNum : 20,
								rowList : [ 10, 20, 30 ],
								colNames : [ "序号", "账号", "用户昵称", "电话号码", "密码",
										"登录时间", "修改时间", "创建时间", "是否通过审核" ],
								colModel : [ {
									name : "userId",
									index : "userId",
									editable : true,
									width : 60,
									sorttype : "int",
									search : true
								}, {
									name : "userAccount",
									index : "userAccount",
									editable : true,
									width : 90
								}, {
									name : "userName",
									index : "userName",
									editable : true,
									width : 100
								}, {
									name : "userPhone",
									index : "userPhone",
									editable : true,
									width : 80,
									align : "left"
								}, {
									name : "userPassword",
									index : "userPassword",
									editable : true,
									width : 80,
									align : "left",
									sorttype : "float"
								}, {
									name : "userLoginTime",
									index : "userLoginTime",
									editable : true,
									width : 80,
									align : "left"
								}, {
									name : "userUpdateTime",
									index : "userUpdateTime",
									editable : true,
									width : 100,
									sortable : false
								}, {
									name : "userCreatTime",
									index : "userCreatTime",
									editable : true,
									width : 120,
									align : "left"
								}, {
									name : "check",
									index : "check",
									editable : true,
									width : 120,
									align : "center",
									formatter: function (value, grid, rows, state) { return "<button type='button' class=btn btn-primary' onclick='approve("+rows.userId+")'>通过</button>&nbsp;&nbsp;<button type='button' class=btn btn-primary' onclick='disapprove("+ rows.userId +")'>不通过</button>" } 
								} ],
								pager : "#pager_list_2",
								viewrecords : true,
								add : true,
								edit : true,
								addtext : "Add",
								edittext : "Edit",
								hidegrid : false
							});
					$("#table_list_2").setSelection(0, false);
					$("#table_list_2").jqGrid("navGrid", "#pager_list_2", {
						edit : false,
						add : false,
						del : false,
						search : false
					}, {
						height : 400,
						reloadAfterSubmit : true
					});
					$(window).bind("resize", function() {
						var width = $(".jqGrid_wrapper").width();
						$("#table_list_2").setGridWidth(width)
					})
				});

function approve(uid){
	//alert(uid);
	$.post(
			"UserToSchool.action",
			{
				user_id:uid,
			}, 
			function(data){
				data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				if(data == "Success"){
					alert("该用户通过注册 ！！");
					window.location.replace("hw_table_school_administrator_check.html");
				}
				else{
					alert("注册失败 ！！");
					window.location.replace("hw_table_school_administrator_check.html");
				}
			}
			);
}

function disapprove(uid){
	//alert(uid);
	$.post(
			"DeleteUser.action",
			{
				user_id:uid,
			}, 
			function(data){
				data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				if(data == "Success"){
					alert("删除成功 ！！");
					window.location.replace("hw_table_school_administrator_check.html");
				}
				else{
					alert("删除失败 ！！");
					window.location.replace("hw_table_school_administrator_check.html");
				}
			}
			);
}
