/**
 * 
 */

var userid; 
var username;
var usertele;
var useremail;
var pwd;
function EduInfo(){
	$.ajaxSettings.async = false;
	$.post(
			"GetUserInfo.action",
			{
				
			}, 
			function(data) {
				var data = JSON.parse(data);
				console.log(data);
				userid = data.userid;
				$("#name").append(data.username);
				username = data.username;
				usertele = data.usertele;
				useremail = data.useremail;
				pwd = data.password;
		}
	);
}

EduInfo();


function logout(){
	$.post(
			"Logout.action",
			{
				
			}, 
			function(data) {
				window.location.replace("login_edu.html");
		}
	);
}

$("#person-info-edit").click(function() {
	
	
    $("#person-info-modal").modal("show");
	$("#eidt_eduuserName").val(username);
	$("#eidt_edutele").val(usertele);
	$("#eduemail").val(useremail);
	$("#eidt_edupwd").val(pwd);
	
    $("#personEdit").click(function(){
    	//alert(userid);
    	var name = $("#eidt_eduuserName").val();
    	var password = $("#eidt_edupwd").val();
    	var phone = $("#eidt_edutele").val();
    	var email = $("#eduemail").val();
    	$.post(
    			"UpdateUserInfo.action",
    			{
    				user_id:userid,
    				user_name:name,
    				user_password:password,
    				user_phone:phone,
    				user_email:email,
    			}, 
    			function(data) {
    				data = data.replace(/^\s*/, "").replace(/\s*$/, "");
        			if(data == "Success"){
        				alert("更新成功！！");
        				window.location.replace("hw_index _education_bureau.html");
        			}
        			else {
        				alert("更新失败！！");
        				window.location.replace("hw_index _education_bureau.html");
        			}
    		}
    	);
    })
    
});


$("#add_eduUser").click(function(){
	
})