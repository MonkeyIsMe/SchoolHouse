/**
 * 
 */

function Register(){
	var account = $("#schacount").val();
	var name = $("#schuserName").val();
	var password = $("#schpwd").val();
	var pwd = $("#schpwd2").val();
	var email = $("#schemail").val();
	var phone = $("#schphone").val();
	var code = $("#sch_login_code").val();
	//alert(name);
	//alert(account + " " + password + " " + code);
	
	if(account == "" || account == null){
		alert("账号不能为空!");
	}
	else if(password == null || password =="" || pwd == null || pwd == ""){
		alert("密码不能为空!");
	}
	else if(code == "" || code == null){
		alert("验证码不能为空!");
	}
	else if(email == null || email == ""){
		alert("邮箱不能为空!");
	}
	else if(name == null || name == ""){
		alert("昵称不能为空!");
	}
	else if(pwd != password ){
		alert("两次密码不一致！！！");
	}
	else{
		
		$.post(
				"AccountIsExit.action",
				{
					user_account:account,
				},
				function(data){
					data = data.replace(/^\s*/, "").replace(/\s*$/, "");
					//alert(data);
					if(data == "Fail"){
						alert("该用户已存在");
					}
					else{
						$.post(
								"RegisterUser.action",
								{
									user_account:account,
									user_password:password,
									user_name:name,
									user_tele:phone,
									user_email:email,
									code:code,
								},
								function(data){
									data = data.replace(/^\s*/, "").replace(/\s*$/, "");
									if(data == "Success"){
										alert("注册成功！，请耐心等待教育区管理员批准！")
										window.location.replace("test.html")
									}
									else if(data == "Fail"){
										alert("注册失败!");
									}
									else{
										alert("验证码错误!");
										resetCode();
									}
								}
								);
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