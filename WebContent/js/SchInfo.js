/**
 * 
 */

var SchoolId;
var userid;
var username;
var usertele;
var useremail;
var pwd;

var school_area;
var school_address;
var school_street;
var school_name;
var school_time;
var school_person;
var school_phone;

function SchInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"GetSchoolUserInfo.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				console.log(data);
				SchoolId = data.schoolid;
				userid = data.userid;
				username = data.username;
				usertele = data.usertele;
				useremail = data.useremail;
				pwd = data.password;
				$("#name").append(data.username);
		}
	);
}

function SchoolInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryBySchoolId.action",
			{
				school_id:SchoolId,
			}, 
			function(data) {
				var data = JSON.parse(data);
				console.log(data);
				
				$("#school_area").val(data.areaName);
				$("#school_address").val(data.schoolAddress);
				$("#school_time").val(data.schoolCreatTime);
				$("#school_person").val(data.schoolIegalPerson);
				$("#school_name").val(data.schoolName);
				$("#school_phone").val(data.schoolPhone);
				$("#school_street").val(data.schoolStreet);
		}
	);
}



SchInfo();
SchoolInfo();


function logout(){
	$.post(
			"Logout.action",
			{
				
			}, 
			function(data) {
				window.location.replace("login_sch.html");
		}
	);
	
	
}

$(document).ready(function() {
	

	
    $("#person-info-edit").click(function() {
        $("#person-info-modal").modal("show");
    }); 
    
    $("#school_info").click(function() {
        $("#school-info-modal").modal("show");
    }); 
    //alert(pwd);
	$("#edit_schuserName").val(username);
	$("#edit_schtel").val(usertele);
	$("#edit_email").val(useremail);
	$("#edit_schpwd").val(pwd);
	
    $("#personEdit").click(function(){
    	var name = $("#edit_schuserName").val();
    	var password = $("#edit_schpwd").val();
    	var phone = $("#edit_schtel").val();
    	var email = $("#edit_email").val();
    	$.post(
    			"UpdateSchoolUserInfo.action",
    			{
    				user_id:userid,
    				user_name:name,
    				user_password:password,
    				user_email:email,
    				user_phone:phone
    			}, 
    			function(data) {
    				data = data.replace(/^\s*/, "").replace(/\s*$/, "");
        			if(data == "Success"){
        				alert("更新成功！！");
        				window.location.replace("hw_index_school.html");
        			}
        			else {
        				alert("更新失败！！");
        				window.location.replace("hw_index_school.html");
        			}
    		}
    	);
    })
});