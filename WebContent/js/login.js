/**
 * 
 */

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
						console.log(data);
						
						$.post(
								"DeleteAllTempStudent.action",
								{
									
								}, 
								function(data) {
							}
						);
						
						//Cookies.set("user_phone", account, {expires: 7});
						window.location.replace("hw_index_school.html")
					}
					else if(data == "WrongAnswer"){
						resetCode();
						alert("密码错误");
						
					}
					else if(data=="NoUser"){
						resetCode();
						alert("账号不存在");
					}
					else{
						resetCode();
						alert("验证码错误");
					}
				}
				);
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
						//alert(111);
						//Cookies.set("user_phone", account, {expires: 7});
						window.location.replace("hw_index _education_bureau.html")
					}
					else if(data == "WrongAnswer"){
						resetCode();
						alert("密码错误");
					}
					else if(data=="NoUser"){
						resetCode();
						alert("账号不存在");
					}
					else{
						resetCode();
						alert("验证码错误");
					}
				}
				);
	}
}

function resetCode() {
    var date = new Date();
    console.log(11);
    $("#code_img").attr("src", "SendCode.action?" + date.toString());
}