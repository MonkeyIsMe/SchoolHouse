/**
 * 
 */


var mydata = [];
var obj_edit;

var obj_del;

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


$(function(){
	$.post(
			"QueryAllSchool.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				console.log(data);
				for(var i = 0 ;i < data.length ; i++){
					$("#update_school").append("<option value="+ data[i].schoolId +">"+ data[i].schoolName+"</option>");
					$("#add_school").append("<option value="+ data[i].schoolId +">"+ data[i].schoolName+"</option>");
				}
		}
	);
	
});


$(document).ready(function() {
			GetSchoolInfo()
			$.jgrid.defaults.styleUI = "Bootstrap";
			$("#table_list_2").jqGrid(
					{
						data : mydata,
						datatype : "local",
						height : 450,
						autowidth : true,
						shrinkToFit : true,
						rowNum : 20,
						multiselect : true,
						colNames : [ "序号", "账号", "用户昵称","用户密码", "电话号码", "所属学校","用户邮箱", "登录时间",
								"修改时间", "创建时间" ],
						colModel : [ {
							name : "userId",
							index : "userId",
							editable : true,
							width : 60,
							sorttype : "int",
							searchoptions: {sopt:['cn']},
							search : false,
						}, {
							name : "userAccount",
							index : "userAccount",
							searchoptions: {sopt:['cn']},
							editable : false,
							width : 90
						}, {
							name : "userName",
							index : "userName",
							searchoptions: {sopt:['cn']},
							editable : true,
							width : 100
						}, 
						{
							name : "userPasswords",
							index : "userPasswords",
							searchoptions: {sopt:['cn']},
							edittype:"password",
							editable : true,
							width : 100
						},
						{
							name : "userPhone",
							searchoptions: {sopt:['cn']},
							index : "userPhone",
							editable : true,
							width : 80,
							align : "left"
						},
						{
							name : "schoolName",
							index : "schoolName",
							searchoptions: {sopt:['cn']},
							editable : true,
							width : 80,
							align : "left"
						}, 
						{
							name : "userEmail",
							index : "userEmail",
							searchoptions: {sopt:['cn']},
							editable : true,
							width : 80,
							align : "left"
						}, 
						{
							name : "userLoginTime",
							index : "userLoginTime",
							searchoptions: {sopt:['cn']},
							editable : false,
							width : 80,
							search : false,
							align : "left"
						}, {
							name : "userUpdateTime",
							index : "userUpdateTime",
							searchoptions: {sopt:['cn']},
							editable : false,
							search : false,
							width : 100,
							sortable : false
						}, {
							name : "userCreatTime",
							index : "userCreatTime",
							searchoptions: {sopt:['cn']},
							editable : false,
							width : 120,
							search : false,
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

		    obj_edit = $("#table_list_2").jqGrid("getRowData");//获取修改时多选的id，并放入到数组obj_edit中
		    
		    $("#bt_add").click(function() {
		        $("#mySave").css("display","inline");
		        $("#myEdit").css("display","none");
		        $("#bt_add").attr("data-target","#add-edit");
		        	});
		    
	        $("#mySave").click(function(){
	        	var op1 = $("#add_school option:selected");
	        	var schacount = $("#schacount").val();
	        	var suuserName = $("#suuserName").val();
	        	var schtel = $("#schtel").val();
	        	var schwpd =$("#schwpd").val();
	        	var school_id =op1.val();
	        	var email =$("#email").val();
	        	
	        	var EmailFlag = checkEmail(email);
	        	var PhoneFlag = IsPhone(schtel);
	        	var PasswordFlag = ValidPassword(schwpd);
	        	var SchoolFlag = checkNumber(school_id);
	        	
	        	if(schacount == null || schacount == "" || suuserName == null || suuserName =="" || schtel == null || schtel ==""
	        		|| schwpd == null || schwpd =="" || school_id == null || school_id == "" || email == "" || email == null){
	        		alert("所填均非空!");
	        	}
	        	else if(EmailFlag == 0){
	        		alert("邮箱格式不正确!");
	        	}
	        	else if( PhoneFlag == 0){
	        		alert("手机不正确!");
	        	}
	        	else if( PasswordFlag == 0){
	        		alert("密码长度8-16位，必须是大写字母、小写字母、数字、特殊字符四种类型中三种以上的组合!");
	        	}
	        	else if( SchoolFlag == 0 ){
	        		alert("学校编号为数字!");
	        	}
	        	else{
	        		$.post(
		        			"SchoolAccountIsExit.action",
		        			{
		        				user_account:schacount,
		        			}, 
		        			function(data) {
								data = data.replace(/^\s*/, "").replace(/\s*$/, "");
								if(data == "Success"){
									
									$.post(
						        			"AddSchoolUser.action",
						        			{
						        				user_account:schacount,
						        				school_id:school_id,
						        				user_name:suuserName,
						        				user_tele:schtel,
						        				user_pwd:schwpd,
						        				user_flag:"0",
						        				user_email:email,
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
	        	}
	        	
	        	})
		    
		    $("#addedu").click(function() {
		    	//alert(1111111);
		        $("#amySave").css("display","inline");
		        $("#amyEdit").css("display","none");
		        $("#addedu").attr("data-target","#addedus");
		        $("#amySave").click(function(){
		        	var schacount = $("#aschacount").val();
		        	var suuserName = $("#asuuserName").val();
		        	var schtel = $("#aschtel").val();
		        	var schwpd =$("#aschwpd").val();
		        	var email =$("#aemail").val();
		        	
		        	var SchoolFlag = checkNumber(school_id);
		        	var EmailFlag = checkEmail(email);
		        	var PhoneFlag = IsPhone(schtel);
		        	var PasswordFlag = ValidPassword(schwpd);
		        	
		        	if(schacount == null || schacount == "" || suuserName == null || suuserName =="" || schtel == null || schtel ==""
		        		|| schwpd == null || schwpd =="" || school_id == null || school_id == "" || email == "" || email == null){
		        		alert("所填均非空!");
		        	}else if(EmailFlag == 0){
		        		alert("邮箱格式不正确!");
		        	}
		        	else if( PhoneFlag == 0){
		        		alert("手机不正确!");
		        	}
		        	else if( PasswordFlag == 0){
		        		alert("密码长度9-16位，必须是大写字母、小写字母、数字、特殊字符四种类型中三种以上的组合!");
		        	}
		        	else if( SchoolFlag == 0 ){
		        		alert("学校编号为数字!");
		        	}
		        	else{
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
							        				user_email:email,
							        				user_flag:"1",
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
		        	}
		        	
		        	})
		        	});

					$("#bt_edit").click(function() {
						obj_edit = $("#table_list_2").jqGrid("getRowData");
						obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
						var idd = (obj_del[0]-1)%20;
						$("#up_mySave").css("display", "none");
						$("#up_myEdit").css("display", "inline");

						if (obj_del.length == 1) {
							
							$("#bt_edit").attr("data-target","#edit");
							$("#up_schacount").val(obj_edit[idd].userAccount);
							$("#up_suuserName").val(obj_edit[idd].userName);
							$("#up_schtel").val(obj_edit[idd].userPhone);
							$("#up_schwpd").val(obj_edit[idd].userPasswords);
							$("#up_school_id").val(obj_edit[idd].schoolId);
							$("#up_email").val(obj_edit[idd].userEmail);
							//alert(obj_edit[obj_del[0] - 1].userEmail);
						} else if(obj_del.length < 1){
					        alert("请至少选择1项！");
					        $("#bt_edit").attr("data-target","");
				        }else{
				            alert("请至多选择1项！");
			        		$("#bt_edit").attr("data-target","");
				        }
					});
					
					$("#up_myEdit").click(function(){
						var op1 = $("#update_school option:selected");
						var schwpd = $("#up_schwpd").val();
						var schtel = $("#up_schtel").val();
						var suuserName = $("#up_suuserName").val();
						var email = $("#up_email").val();
						var sid = obj_edit[(obj_del[0] - 1)%20].userId;
						var school_id =op1.val();
						
			        	var PhoneFlag = IsPhone(schtel);
			        	var PasswordFlag = ValidPassword(schwpd);
						
						if(schwpd == null || schwpd =="" || email == "" || email == null ||
							schtel == null || schtel == "" || suuserName == null || suuserName =="" || school_id == null || school_id == ""){
							alert("所有项必须非空！！！");
						}
						else if( PhoneFlag == 0){
			        		alert("手机不正确!");
			        	}
			        	else if( PasswordFlag == 0){
			        		alert("密码长度8-16位，必须是大写字母、小写字母、数字、特殊字符四种类型中三种以上的组合!");
			        	}
						else{
							
							
							$.post(
				        			"UpdateSchoolUser.action",
				        			{
				        				school_id:school_id,
				        				userId:sid,
				        				userName:suuserName,
				        				userPhone:schtel,
				        				user_pwd:schwpd,
				        				user_email:email
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
					
					$("#bt_del").click(function() {
						obj_edit = $("#table_list_2").jqGrid("getRowData");
						obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
						
						if (obj_del.length < 1) {
							alert("请至少选择1项！");
					        $("#bt_edit").attr("data-target","");
						} else {
							var success = 0; 
				        	var fail = 0;
							// alert(obj_del);
							$("#bt_del").attr("data-target", "#mydel");
							//alert(obj_edit[obj_del[0]-1].userId);
							$("#myDelete").click(function(){
								for(var i = 0 ; i< obj_del.length ; i++){
				        	//alert(hw_obj[hw_del[i]-1].schoolId);
				    		$(function(){
				    			$.post(
				    					"DeleteSchoolUser.action",
				    					{
				    						user_id:obj_edit[obj_del[i]-1].userId,
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
								window.location.replace("hw_table_school_administrator.html");
							})
						}

					});
				});


function checkEmail(str){
	   var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
	   if(re.test(str)){
	       return 1;
	   }else{
	       return 0;
	   }
	}

	function IsPhone(phone){
		var re = /^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|17[678])[0-9]{8}$/;
		if(re.test(phone)){
			return 1;
		}
		else return 0;
	}

	function ValidPassword(password){
		
		var flag1 = 0;
		var flag2 = 0;
		var flag3 = 0;
		var flag4 = 0;
		var flag5 = 0;
		if(password.length > 8 ){
			flag1 = 1;
		}
		for(var i = 0;i<password.length ; i++){
			if(password.charAt(i) >='0' && password.charAt(i) <= '9'){
				flag2 = 1;
			}
			if(password.charAt(i) >='a' && password.charAt(i) <= 'z'){
				flag3 = 1;
			}
			if(password.charAt(i) >='A' && password.charAt(i) <= 'Z'){
				flag4 = 1;
			}
			if(password.charAt(i) =='!' || password.charAt(i) == '~' || password.charAt(i) =='@' || password.charAt(i) == '#' ||  password.charAt(i) =='$' || password.charAt(i) == '%'
				||  password.charAt(i) =='^' || password.charAt(i) == '&'|| password.charAt(i) == '*'|| password.charAt(i) == '_'|| password.charAt(i) == '-'|| password.charAt(i) == '+'|| password.charAt(i) == '='){
				flag5 = 1;
			}
		}
		var sum = flag1 + flag2 + flag3 + flag4 +flag5;
		if(sum >= 4){
			return 1;
		}
		else return 0;
	}
	
	function checkNumber(theObj) {
		  var reg = /^[0-9]+.?[0-9]*$/;
		  if (reg.test(theObj)) {
		    return 1;
		  }
		  return 0;
	}