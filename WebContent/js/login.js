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
				"Login.action",
				{
					user_account:account,
					user_password:password,
					uflag:0,
					code:code,
				},
				function(data){
					data = data.replace(/^\s*/, "").replace(/\s*$/, "");
					console.log(data);
					if(data == "JYJ"){
						//alert(111);
						//Cookies.set("user_phone", account, {expires: 7});
						window.location.replace("test.html")
					}
					else if(data == "XX"){
						window.location.replace("test.html")
					}
					else if(data == "WrongAnswer"){
						alert("密码错误");
						resetCode();
					}
					else if(data=="NoUser"){
						alert("账号不存在");
						resetCode();
					}
					else{
						alert("验证码错误");
						resetCode();
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
					uflag:0,
					code:code,
				},
				function(data){
					data = data.replace(/^\s*/, "").replace(/\s*$/, "");
					console.log(data);
					if(data == "JYJ"){
						//alert(111);
						//Cookies.set("user_phone", account, {expires: 7});
						window.location.replace("test.html")
					}
					else if(data == "XX"){
						window.location.replace("test.html")
					}
					else if(data == "WrongAnswer"){
						alert("密码错误");
						resetCode();
					}
					else if(data=="NoUser"){
						alert("账号不存在");
						resetCode();
					}
					else{
						alert("验证码错误");
						resetCode();
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