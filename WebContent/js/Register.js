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
	var schoolid = $("#schoolid").val();
	var code = $("#sch_login_code").val();
	//alert(name);
	//alert(account + " " + password + " " + code);
	schoolid = schoolid.replace(/\s+/g,"");
	var EmailFlag = checkEmail(email);
	var PhoneFlag = IsPhone(phone);
	var PasswordFlag = ValidPassword(password);
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
	else if(EmailFlag == 0){
		alert("邮箱格式不正确!");
	}
	else if( PhoneFlag == 0){
		alert("手机不正确!");
	}
	else if( PasswordFlag == 0){
		alert("密码长度9-16位，必须是大写字母、小写字母、数字、特殊字符四种类型中三种以上的组合!");
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
								"SchoolAlready.action",
								{
									school_name:schoolid,
								},
								function(data){
									data = data.replace(/^\s*/, "").replace(/\s*$/, "");
									//alert(data);
									if(data == "Fail"){
										$.post(
												"RegisterSchoolUser.action",
												{
													user_account:account,
													user_password:password,
													user_name:name,
													user_tele:phone,
													user_email:email,
													code:code,
													schoolid:schoolid,
												},
												function(data){
													data = data.replace(/^\s*/, "").replace(/\s*$/, "");
													if(data == "Success"){
														alert("注册成功！请耐心等待教育局管理员批准！")
														window.location.replace("welcome.html")
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
									else{
										alert("学校不存在！！");
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