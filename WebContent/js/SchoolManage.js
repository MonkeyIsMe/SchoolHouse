/**
 * 
 */

var mydata = [];

function GetSchoolInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryAllSchool.action",
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

var hw_x = [];
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
						rowList : [10],
						multiselect : true,
						celledit:true,
						onSelectRow:function(id,stats){
				        	if(stats){
				                hw_x.push(id);
				            }else{
				            	hw_x.shift();
				            }
				        },
						colNames : [ "序号", "学校名称", "学校地址", "所属街道", "所属学区",
								"法人代表", "联系电话", "创建时间" ],
						colModel : [ {
							name : "schoolId",
							index : "schoolId",
							editable : false,
							width : 60,
							sorttype : "int",
							search : true
						}, {
							name : "schoolName",
							index : "schoolName",
							editable : true,
							width : 90
						}, {
							name : "schoolAddress",
							index : "schoolAddress",
							editable : true,
							width : 100
						}, {
							name : "schoolStreet",
							index : "schoolStreet",
							editable : true,
							width : 100
						}, {
							name : "areaId",
							index : "areaId",
							editable : true,
							width : 80,
							align : "left"
						}, {
							name : "schoolIegalPerson",
							index : "schoolIegalPerson",
							editable : true,
							width : 80,
							align : "left",
							sorttype : "float"
						}, {
							name : "schoolPhone",
							index : "schoolPhone",
							editable : true,
							width : 80,
							align : "left"
						}, {
							name : "schoolCreatTime",
							index : "schoolCreatTime",
							editable : true,
							width : 100,
							sortable : false
						} ],
						pager : "#pager_list_2",
						viewrecords : true,
						add : true,
						edit : true,
						
						addtext : "Add",
						edittext : "Edit",
						hidegrid : false
					});
/*			$("#table_list_2").setSelection(0, true);*/
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
			
			var hw_obj = $("#table_list_2").jqGrid("getRowData");
			var hw_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
			$("#bt_edit").click(function() {
				$("#mySave").css("display","none");
			    $("#myEdit").css("display","inline");
			    if(hw_x.length==1){
			        $("#update_legalPerson").val(hw_obj[hw_x[hw_x.length-1]-1].schoolIegalPerson);
			        $("#update_schoolTel").val(hw_obj[hw_x[hw_x.length-1]-1].schoolPhone);
			        $("#update_Sdid").val(hw_obj[hw_x[hw_x.length-1]-1].areaId);
			        $("#update_schoolStreet").val(hw_obj[hw_x[hw_x.length-1]-1].schoolStreet);
			        $("#update_schooladdress").val(hw_obj[hw_x[hw_x.length-1]-1].schoolAddress);
			        $("#update_schoolName").val(hw_obj[hw_x[hw_x.length-1]-1].schoolName);
			    	$("#UpdateSchool").click(function(){
				        var sid = hw_obj[hw_x[hw_x.length-1]-1].schoolId;
				        var update_legalPerson = $("#update_legalPerson").val();
				        var update_schoolTel =$("#update_schoolTel").val();
				        var update_Sdid = $("#update_Sdid").val();
				        var update_schoolStreet = $("#update_schoolStreet").val();
				        var update_schooladdress = $("#update_schooladdress").val();
				        var update_schoolName = $("#update_schoolName").val();
				        //alert(update_schooladdress + " + " +hw_obj[hw_x[hw_x.length-1]-1].schoolStreet);
				    	if(update_schoolName == null || update_schoolName == "" || update_schooladdress == null || update_schooladdress == "" || update_schoolStreet == null ||
				    			update_schoolStreet =="" || update_Sdid == null || update_Sdid == "" || update_legalPerson == null || 
				    			update_legalPerson == "" || update_schoolTel ==null || update_schoolTel ==""){
				    		alert("所有输入均为非空！！");
				    	}
				    	else{
				    		$(function(){
				    			$.post(
				    					"AreaIsExit.action",
				    					{
				    						area_id:update_Sdid,
				    					},
				    					function(data){
				    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				    						if(data == "Success"){
				    							$(function(){
				    								$.post(
				    										"UpdateSchool.action",
				    										{
				    											school_name:update_schoolName,
				    											school_address:update_schooladdress,
				    											school_street:update_schoolStreet,
				    											school_legal:update_legalPerson,
				    											school_tele:update_schoolTel,
				    											area_id:update_Sdid,
				    											school_id:sid,
				    										},
				    										function(data){
				    											data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				    											if(data == "Success"){
				    												alert("更新成功！！");
				    												window.location.replace("hw_table_school.html");
				    											}
				    										}
				    										);
				    								
				    							});
				    						}
				    						else{
				    							alert("学区不存在！！");
				    							window.location.replace("hw_table_school.html");
				    						}
				    					}
				    					);
				    			
				    		});
				    	}
			    	})  
			    }else if(hw_x.length < 1){
			        alert("请至少选择1项！");
			        $("#bt_edit").attr("data-target","");
			    }else{
			        alert("请只选择1项！");
			    }
			});
			
		    $("#bt_del").click(function() {
		        if(hw_del.length<1){
		            alert("请至少选择1项！");
		        }else{
		            // alert(obj_del);
		            $("#bt_del").attr("data-target","#mydel");
		            $("#myDelete").click(function(){
				        for(var i = 0 ; i< hw_del.length ; i++){
				        	//alert(hw_obj[hw_del[i]-1].schoolId);
				    		$(function(){
				    			$.post(
				    					"DeleteSchool.action",
				    					{
				    						school_id:hw_obj[hw_del[i]-1].schoolId,
				    					},
				    					function(data){
				    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				    						if(data == "Success"){
				    							alert("删除成功 ！！");
				    							window.location.replace("hw_table_school_district.html");
				    						}
				    						else{
				    							alert("删除失败 ！！");
				    							window.location.replace("hw_table_school_district.html");
				    						}
				    					}
				    					);
				    			
				    		});
				        }
		            });
		        }

		    }); 
			
		});



function SaveSchool(){
	var schoolName = $("#schoolName").val();
	var schooladdress = $("#schooladdress").val();
	var schoolStreet = $("#schoolStreet").val();
	var Sdid = $("#Sdid").val();
	var legalPerson = $("#legalPerson").val();
	var schoolTel = $("#schoolTel").val();
	if(schoolName == null || schoolName == "" || schooladdress == null || schooladdress == "" || schoolStreet == null ||
			schoolStreet =="" || Sdid == null || Sdid == "" || legalPerson == null || 
			legalPerson == "" || schoolTel ==null || schoolTel ==""){
		alert("所有输入均为非空！！");
	}
	else{
		
		$(function(){
			$.post(
					"AreaIsExit.action",
					{
						area_id:Sdid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Success"){
							$(function(){
								$.post(
										"AddSchool.action",
										{
											school_name:schoolName,
											school_address:schooladdress,
											school_street:schoolStreet,
											school_legal:legalPerson,
											school_tele:schoolTel,
											area_id:Sdid,
										},
										function(data){
											data = data.replace(/^\s*/, "").replace(/\s*$/, "");
											if(data == "Success"){
												alert("添加成功！！");
												window.location.replace("hw_table_school.html");
											}
										}
										);
								
							});
						}
						else{
							alert("学区不存在！！");
							window.location.replace("hw_table_school.html");
						}
					}
					);
			
		});
		

	}

}



