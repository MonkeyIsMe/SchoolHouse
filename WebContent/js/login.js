/**
 * 
 */
function EduAgree(){
	edu_login();
}

function SchAgree(){
	sch_login();
}

function sch_login(){
	var account = $("#schacount").val();
	var password = $("#schpwd").val();
	var code = $("#sch_login_code").val();
	
	//alert(account + " " + password + " " + code);
	
	if(account == "" || account == null){
		alert("账号不能为空!");
	}
	else if(password == null || password ==""){
		alert("密码不能为空!");
	}
	else if(code == "" || code == null){
		alert("验证码不能为空!");
	}
	else{
		//alert(1);
		$("#sch_agree").attr("data-target","#add-edit");
		$("#SchAgree").click(function(){
			//alert(1111);
			$.post(
					"SchLogin.action",
					{
						user_account:account,
						user_password:password,
						code:code,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						console.log(data);
						if(data == "Success"){
							window.location.replace("hw_index_school.html");
						}
						else if(data == "WrongAnswer"){
							$("#edu_agree").attr("data-target","");
							resetCode();
							alert("密码错误");
							window.location.replace("login_sch.html");
							
						}
						else if(data=="NoUser"){
							$("#edu_agree").attr("data-target","");
							resetCode();
							alert("账号不存在");
							window.location.replace("login_sch.html");
						}
						else{
							$("#edu_agree").attr("data-target","");
							resetCode();
							alert("验证码错误");
							window.location.replace("login_sch.html");
						}
					}
					);
		})
		
		$("#SchDismiss").click(function(){
			resetCode();
		})

	}
}


function edu_login(){
	var account = $("#eduacount").val();
	var password = $("#edupwd").val();
	var code = $("#edu_login_code").val();
	
	//alert(account + " " + password + " " + code);
	
	if(account == "" || account == null){
		alert("账号不能为空!");
	}
	else if(password == null || password ==""){
		alert("密码不能为空!");
	}
	else if(code == "" || code == null){
		alert("验证码不能为空!");
	}
	else{
		
		$("#edu_agree").attr("data-target","#add-edit");
		
		$("#EduAgree").click(function(){
			$.post(
					"Login.action",
					{
						user_account:account,
						user_password:password,
						code:code,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						console.log(data);
						if(data == "Success"){
							window.location.replace("hw_index _education_bureau.html")
						}
						else if(data == "WrongAnswer"){
							resetCode();
							$("#edu_agree").attr("data-target","");
							alert("密码错误");
							window.location.replace("login_edu.html");
						}
						else if(data=="NoUser"){
							resetCode();
							$("#edu_agree").attr("data-target","");
							alert("账号不存在");
							window.location.replace("login_edu.html");
						}
						else{
							resetCode();
							$("#edu_agree").attr("data-target","");
							alert("验证码错误");
							window.location.replace("login_edu.html");
						}
					}
					);
		})
		
		$("#EduDismiss").click(function(){
			resetCode();
		})

	}
}

function resetCode() {
    var date = new Date();
    console.log(11);
    $("#code_img").attr("src", "SendCode.action?" + date.toString());
}