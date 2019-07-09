/**

 * 
 */
var mydata = [];
var SchoolName;
var schoolid;
var areaid;


var obj_edit;
var obj_del;

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
				$.post(
						"QueryBySchoolId.action",
						{
							school_id:data.schoolid,
						}, 
						function(datas) {
							var datas = JSON.parse(datas);
							//console.log(datas);
							SchoolName = datas.schoolName;
							areaid = datas.areaId;
					}
				);
		}
	);
	
});

$(function(){
	
	$.post(
			"QueryBuildingByAreaId.action",
			{
				area_id:areaid,
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(1111111);
			    console.log(data)
				for(var i = 0 ;i < data.length ; i++){
					$("#studentbuildId").append("<option value="+ data[i].buildingName +">"+ data[i].buildingName+"</option>");
				}
			}
			);
	
	
/*	$.post(
			"QueryHouseByBuildingId.action",
			{
				area_id:areaid,
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(1111111);
			    console.log(data)
				for(var i = 0 ;i < data.length ; i++){
					$("#studentbuildNum").append("<option value="+ data[i].houseName +">"+ data[i].houseName+"</option>");
				}
			}
			);*/
	
});

function StudentInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryTempStudentBySchoolValid.action",
			{
				School_Name:SchoolName,
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
	StudentInfo();
    $.jgrid.defaults.styleUI = "Bootstrap";
    $("#table_list_2").jqGrid({
        data: mydata,
        datatype: "local",
        height: 450,
        autowidth: true,
        shrinkToFit: true,
        rowNum: 20,
        multiselect: true,
        celledit: true,
        editurl:"",
        colNames: ["序号", "学生姓名", "学生身份证号", "父亲姓名", 
        	"母亲姓名", "业主姓名", "业主身份证号", "与业主关系", 
        	"房产证号", "房屋预告登记号", "所属楼盘", "地址","所属学校", "学生联系电话", "毕业小学", "使用日期"],
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
            search:false,
            width: 30,
            align: "left"
        },
        {
            name: "studentHouseNumber",
            index: "studentHouseNumber",
            editable: true,
            searchoptions: {sopt:['cn']},
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
            name: "studentPreSchool",
            index: "studentPreSchool",
            editable: true,
            search:false,
            width: 30,
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
    
    $("#bt_toexcel").click(function(){
    	window.open("add_validExcel.html");
    })
    
    obj_edit = $("#table_list_2").jqGrid("getRowData");//获取修改时多选的id，并放入到数组obj_edit中
    //var hw_del = $("#table_list_2").jqGrid("getRowData",obj_del[0]);
    $("#bt_add").click(function() {
        $("#myEdit").css("display","none");
        $("#mySave").css("display","inline");
        $("#bt_add").attr("data-target","#add-edit");
    	$("#studentName").val("");
    	$("#identityId").val("");
    	$("#farther").val("");
    	$("#mother").val("");
    	$("#houseOwner").val("");
    	$("#houseOwnerId").val("");
    	$("#relation").val("");
    	$("#houseCard").val("");
    	$("#housepreCard").val("");
    	$("#studentbuildId").val("");
    	$("#studentbuildNum").val("");
    	$("#studentSchool").val("");
    	$("#studentTel").val("");
    	$("#privateSchool").val("");
    	$("#useTime").val("");
    });
    
    $("#mySave").click(function(){
    	//alert(1111);
    	var op1 = $("#studentbuildId option:selected");
    	var studentbuildNum = $("#studentbuildNum").val();
    	var studentName = $("#studentName").val();
    	var identityId = $("#identityId").val();
    	var farther = $("#farther").val();
    	var mother = $("#mother").val();
    	var houseOwner = $("#houseOwner").val();
    	var houseOwnerId = $("#houseOwnerId").val();
    	var relation = $("#relation").val();
    	var houseCard = $("#houseCard").val();
    	var housepreCard = $("#housepreCard").val();
    	var studentbuildId = op1.val();
    	var studentTel = $("#studentTel").val();
    	var privateSchool = $("#privateSchool").val();
    	var useTime = $("#useTime").val();
    	
    	var IDOwnerFlag = IsCard(houseOwnerId);
    	var DateFlag = IsDate(useTime);
    	var IDFlag = IsCard(identityId);
    	
    	if(studentName == "" || studentName == null || identityId == null || identityId =="" || houseOwner == "" 
    		|| houseOwner == null || 	houseOwnerId == null || houseOwnerId =="" || relation == null || 
    		relation =="" || studentbuildNum == "" || studentbuildNum == null||  
    		useTime == null || useTime == ""
    	){
    		alert("所有必填选项非空！");
    	}
    	else if(DateFlag == 0 || useTime.length != 10){
    		alert("日期格式错误!!");
    	}
    	else if(IDFlag == 0 || IDOwnerFlag == 0){
    		alert("身份证格式错误!!");
    	}
    	else{
    		$.post(
    				"AddValidTempStudent.action",
    				{
    					student_name:studentName,
    					student_idcard:identityId,
    					student_father:farther,
    					student_mother:mother,
    					student_houseowner:houseOwner,
    					student_ownerid:houseOwnerId,
    					student_relation:relation,
    					student_housecard:houseCard,
    					student_houseprecard:housepreCard,
    					student_budingid:studentbuildId,
    					student_housenum:studentbuildNum,
    					student_school:SchoolName,
    					student_tele:studentTel,
    					student_preschool:privateSchool,
    					usetime:useTime,
    				}, 
    				function(data) {
    					data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Success"){
							alert("增加成功！");
							window.location.replace("hw_table_student_valid.html");
						}
						else{
							alert("增加失败！");
							window.location.replace("hw_table_student_valid.html");
						}
    		});
    	}
    })
    
    $("#bt_edit").click(function() {
    	obj_edit = $("#table_list_2").jqGrid("getRowData");//获取修改时多选的id，并放入到数组obj_edit中
        obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
        $("#mySave").css("display","none");
        $("#myEdit").css("display","inline");
        if(obj_del.length==1){
            //alert(obj_edit[obj_del[0]-1].id);
            $("#bt_edit").attr("data-target","#add-edit");
            
        	$("#studentName").val(obj_edit[(obj_del[0]-1)%20].studentName);
        	$("#identityId").val(obj_edit[(obj_del[0]-1)%20].studentIdCard);
        	$("#farther").val(obj_edit[(obj_del[0]-1)%20].studentFather);
        	$("#mother").val(obj_edit[(obj_del[0]-1)%20].studentMother);
        	$("#houseOwner").val(obj_edit[(obj_del[0]-1)%20].studentHouseOwner);
        	$("#houseOwnerId").val(obj_edit[(obj_del[0]-1)%20].studentOwnerId);
        	$("#relation").val(obj_edit[(obj_del[0]-1)%20].studentRelation);
        	$("#houseCard").val(obj_edit[(obj_del[0]-1)%20].studentHouseCard);
        	$("#housepreCard").val(obj_edit[(obj_del[0]-1)%20].studentHousePreCard);
        	$("#studentbuildId").val(obj_edit[(obj_del[0]-1)%20].studentBuildingId);
        	$("#studentbuildNum").val(obj_edit[(obj_del[0]-1)%20].studentHouseNumber);
        	
        	$("#studentSchool").val(obj_edit[(obj_del[0]-1)%20].studentSchool);
        	$("#studentTel").val(obj_edit[(obj_del[0]-1)%20].studentPhone);
        	$("#privateSchool").val(obj_edit[(obj_del[0]-1)%20].studentPreSchool);
        	var str = obj_edit[(obj_del[0]-1)%20].studentUseTime;
        	var time = str.slice(0,4);
        	time = time + "-";
        	time = time + str.slice(4,6);
        	time = time + "-";
        	time = time + str.slice(6,8);
        	$("#useTime").val(time);
        }else if(obj_del.length < 1){
            $("#bt_edit").attr("data-target","");
            $("#myless").modal("show");
        }else{
            $("#bt_edit").attr("data-target","");
            $("#mymore").modal("show");
        }
    });
    
	//编辑
	$("#myEdit").click(function(){
    	var op1 = $("#studentbuildId option:selected");
    	var studentbuildNum = $("#studentbuildNum").val();
		var sid = obj_edit[(obj_del[0]-1)%20].studentId;
		var studentName = $("#studentName").val();
    	var identityId = $("#identityId").val();
    	var farther = $("#farther").val();
    	var mother = $("#mother").val();
    	var houseOwner = $("#houseOwner").val();
    	var houseOwnerId = $("#houseOwnerId").val();
    	var relation = $("#relation").val();
    	var houseCard = $("#houseCard").val();
    	var housepreCard = $("#housepreCard").val();
    	var studentbuildId = op1.val();
    	var studentTel = $("#studentTel").val();
    	var privateSchool = $("#privateSchool").val();
    	var useTime = $("#useTime").val();
    	
    	var DateFlag = IsDate(useTime);
    	var IDFlag = IsCard(identityId);
    	var IDOwnerFlag = IsCard(houseOwnerId);
    	
    	if(studentName == "" || studentName == null || identityId == null || identityId =="" 
        		||	houseOwner == "" || houseOwner == null || 	houseOwnerId == null || houseOwnerId =="" 
        			|| relation == null || relation =="" ||  studentbuildNum == "" || studentbuildNum == null || useTime == null || useTime == ""
        	){
        		alert("所有选项非空！");
        	}
    	else if(DateFlag == 0 || useTime.length != 10){
    		alert("日期格式错误!!");
    	}
    	else if(IDFlag == 0 || IDOwnerFlag == 0){
    		alert("身份证格式错误!!");
    	}
    	else{
    		//alert(SchoolName);
    		$.post(
    				"UpdateTempStudent.action",
    				{
    					student_id:sid,
    					student_name:studentName,
    					student_idcard:identityId,
    					student_father:farther,
    					student_mother:mother,
    					student_houseowner:houseOwner,
    					student_ownerid:houseOwnerId,
    					student_relation:relation,
    					student_housecard:houseCard,
    					student_houseprecard:housepreCard,
    					student_budingid:studentbuildId,
    					student_housenum:studentbuildNum,
    					student_school:SchoolName,
    					student_tele:studentTel,
    					student_preschool:privateSchool,
    					usetime:useTime,
    				}, 
    				function(data) {
    					data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Success"){
							alert("更新成功！");
							window.location.replace("hw_table_student_valid.html");
						}
						else{
							alert("增加失败！");
							window.location.replace("hw_table_student_valid.html");
						}
    		});
    	}
	})
    
    $("#bt_del").click(function() {
    	obj_edit = $("#table_list_2").jqGrid("getRowData");//获取修改时多选的id，并放入到数组obj_edit中
        obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
        if(obj_del.length<1){
            $("#myless").modal("show");
            $("#bt_del").attr("data-target","");
        }else{
            
            //alert(obj_edit[obj_del[0]-1].id);//obj_edit[obj_del[0]-1].idobj_edit[obj_del[0]-1].id即为所在行的id
            
            $("#bt_del").attr("data-target","#mydel");
            var success = 0;
            var fail = 0;
            $("#myDelete").click(function(){
            	for(var i = 0 ; i< obj_del.length ; i++){
		    		$(function(){
		    			$.post(
		    					"DeleteTempStudent.action",
		    					{
		    						student_id:obj_edit[(obj_del[i]-1)%20].studentId,
		    					},
		    					function(data){
		    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
		    						if(data == "Success"){
/*				    							alert("删除成功 ！！");
		    							window.location.replace("hw_table_school.html");*/
		    							success++;
		    						}
		    						else{
		    							fail++;
/*				    							alert("删除失败 ！！");
		    							window.location.replace("hw_table_school.html");*/
		    						}
		    					}
		    					);
		    			
		    		});

            	}
	    		alert("删除成功：" +success + "项，删除失败：" + fail + "项" );
		        window.location.replace("hw_table_student_valid.html");
            })
        }
        
    }); 
	
    $("#bt_save").click(function() {
    	obj_edit = $("#table_list_2").jqGrid("getRowData");//获取修改时多选的id，并放入到数组obj_edit中
        obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
        if(obj_del.length<1){
            $("#mysavestu").modal("show");
            $("#bt_save").attr("data-target","");
        }else{
            
            //alert(obj_edit[obj_del[0]-1].id);//obj_edit[obj_del[0]-1].idobj_edit[obj_del[0]-1].id即为所在行的id
            
            $("#bt_save").attr("data-target","#SaveStudent");
            var success = 0;
            var fail = 0;
            $("#mySaveStu").click(function(){
            	for(var i = 0 ; i< obj_del.length ; i++){
		    		$(function(){
		    			$.post(
		    					"SaveStudent.action",
		    					{
		    						student_id:obj_edit[(obj_del[i]-1)%20].studentId,
		    					},
		    					function(data){
		    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
		    						if(data == "Success"){
/*				    							alert("删除成功 ！！");
		    							window.location.replace("hw_table_school.html");*/
		    							success++;
		    						}
		    						else{
		    							fail++;
/*				    							alert("删除失败 ！！");
		    							window.location.replace("hw_table_school.html");*/
		    						}
		    					}
		    					);
		    			
		    		});

            	}
	    		alert("保存成功：" +success + "项，保存失败：" + fail + "项" );
		        window.location.replace("hw_table_student_valid.html");
            })
        }
        
    }); 
    
    function IsCard(str){
		// 正则表达式：
		var idcardReg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		if(idcardReg.test(str)) {
		    return 1;
		}
		else return 0;
	}

	
	function IsDate(date){
		var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
		var r = date.match(reg);
		if(r == null) return 0;
		else return 1;
	}
/*    $("#bt_valid").click(function() {
        obj_del = $("#table_list_2").jqGrid("getGridParam","selarrrow");
        $("#mySave").css("display","none");
        $("#myEdit").css("display","none");
        $("#myValid").css("display","inline");
        if(obj_del.length==1){
            //alert(obj_edit[obj_del[0]-1].id);
            $("#bt_valid").attr("data-target","#add-edit");
        }else if(obj_del.length < 1){
            $("#bt_valid").attr("data-target","");
            $("#myless").modal("show");
        }else{
            $("#bt_valid").attr("data-target","");
            $("#mymore").modal("show");
        }
    });*/
});