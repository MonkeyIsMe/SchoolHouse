/**
 * 
 */

var mydata = [];

function GetSchoolInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"QuerySchoolManager.action",
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
			GetSchoolInfo()
			$.jgrid.defaults.styleUI = "Bootstrap";
			var obj_i=[];
			$("#table_list_2").jqGrid(
					{
						data : mydata,
						datatype : "local",
						height : 450,
						autowidth : true,
						shrinkToFit : true,
						rowNum : 20,
						rowList : [ 10, 20, 30 ],
						multiselect : true,
						onSelectRow:function(id,stats){
				            if(stats){
				                obj_i.push(id);
				            }else{
				                obj_i.shift();
				            }
				        },
						colNames : [ "序号", "账号", "用户昵称","用户密码", "电话号码",  "登录时间",
								"修改时间", "创建时间" ],
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
							editable : false,
							width : 90
						}, {
							name : "userName",
							index : "userName",
							editable : true,
							width : 100
						}, 
						{
							name : "userPassword",
							index : "userPassword",
							edittype:"password",
							editable : true,
							width : 100
						},
						{
							name : "userPhone",
							index : "userPhone",
							editable : true,
							width : 80,
							align : "left"
						}, {
							name : "userLoginTime",
							index : "userLoginTime",
							editable : false,
							width : 80,
							align : "left"
						}, {
							name : "userUpdateTime",
							index : "userUpdateTime",
							editable : false,
							width : 100,
							sortable : false
						}, {
							name : "userCreatTime",
							index : "userCreatTime",
							editable : false,
							width : 120,
							align : "left"
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
				search : true,
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
		        	var schacount = $("#schacount").val();
		        	var suuserName = $("#suuserName").val();
		        	var schtel = $("#schtel").val();
		        	var schwpd =$("#schwpd").val();
		        	
		        	$.post(
		        			"AccountIsExit.action",
		        			{
		        				user_account:schacount,
		        			}, 
		        			function(data) {
								data = data.replace(/^\s*/, "").replace(/\s*$/, "");
								if(data == "Success"){
						        	$.post(
						        			"AddUser.action",
						        			{
						        				user_account:schacount,
						        				user_name:suuserName,
						        				user_tele:schtel,
						        				user_pwd:schwpd,
						        				user_flag:"0",
						        			}, 
						        			function(data) {
												data = data.replace(/^\s*/, "").replace(/\s*$/,"");
												if (data == "Success") {													
													alert("添加成功！！");										
													window.location.replace("hw_table_school_administrator.html");
												} else {									
													alert("添加失败！！");				
													window.location.replace("hw_table_school_administrator.html");
													}
												});
						        	} else {
						        		alert("该账号已经注册！！");
						        		window.location.replace("hw_table_school_administrator.html");
						        		}
								});
		        	})
		        	});

					$("#bt_edit").click(function() {
						$("#up_mySave").css("display", "none");
						$("#up_myEdit").css("display", "inline");
						//alert(1111);
						if (obj_i.length == 1) {
							// alert(obj_edit[obj_i[obj_i.length-1]-1].schoolName);
							$("#bt_edit").attr("data-target","#edit");
							$("#up_schacount").val(obj_edit[obj_i[obj_i.length-1]-1].userAccount);
							$("#up_suuserName").val(obj_edit[obj_i[obj_i.length - 1] - 1].userName);
							$("#up_schtel").val(obj_edit[obj_i[obj_i.length - 1] - 1].userPhone);
							$("#up_schwpd").val(obj_edit[obj_i[obj_i.length - 1] - 1].userPassword);
							//alert(obj_edit[obj_i[obj_i.length - 1] - 1].userPassword);
							$("#up_myEdit").click(function(){
								var schwpd = $("#up_schwpd").val();
								var schtel = $("#up_schtel").val();
								var suuserName = $("#up_suuserName").val();
								var sid = obj_edit[obj_i[obj_i.length - 1] - 1].userId;
								
								if(schwpd == null || schwpd =="" ||
									schtel == null || schtel == "" || suuserName == null || suuserName ==""){
									alert("所有项必须非空！！！");
								}else{
									$.post(
						        			"UpdateUser.action",
						        			{
						        				userId:sid,
						        				userName:suuserName,
						        				userPhone:schtel,
						        				user_pwd:schwpd,
						        			}, 
						        			function(data) {
												data = data.replace(/^\s*/, "").replace(/\s*$/,"");
												if (data == "Success") {													
													alert("更新成功！！");										
													window.location.replace("hw_table_school_administrator.html");
												} else {									
													alert("更新成功！！");				
													window.location.replace("hw_table_school_administrator.html");
													}
												});
								}
							})
							
						} else if (obj_i.length < 1) {
							$("#bt_edit").attr("data-target","");
							alert("请至少选择1项！");
							} else {
								$("#bt_edit").attr("data-target","");
								alert("请只选择1项！");
								}
						});
					$("#bt_del").click(function() {
						if (obj_del.length < 1) {
							alert("请至少选择1项！");
						} else {
							// alert(obj_del);
							$("#bt_del").attr("data-target", "#mydel");
							//alert(obj_edit[obj_del[0]-1].userId);
							$("#myDelete").click(function(){
								for(var i = 0 ; i< obj_del.length ; i++){
				        	//alert(hw_obj[hw_del[i]-1].schoolId);
				    		$(function(){
				    			$.post(
				    					"DeleteUser.action",
				    					{
				    						user_id:obj_edit[obj_del[i]-1].userId,
				    					},
				    					function(data){
				    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				    						if(data == "Success"){
				    							alert("删除成功 ！！");
				    							window.location.replace("hw_table_school_administrator.html");
				    						}
				    						else{
				    							alert("删除失败 ！！");
				    							window.location.replace("hw_table_school_administrator.html");
				    						}
				    					}
				    					);
				    			
				    		});
				        }
							})
						}

					});
				});
