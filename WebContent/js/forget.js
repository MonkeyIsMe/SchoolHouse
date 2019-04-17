/**
 * 
 */

function Forget(){
	
	var account = $("#schacount").val();
	var code = $("#forget_code").val();
	
	$.post(
			"ForgetPassword.action",
			{
				user_account:account,
				code:code
			},
			function(data){
				data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				console.log(data);
				if(data == "NoAccount"){
					//alert(111);
					alert("没有这个账户");
				}
				else if(data == "Success"){
					alert("发送成功请注意查收邮件！");
					window.location.replace("forget_pwd.html");
				}
				else{
					alert("验证码错误！");
				}
			}
			);
}

function UserForget(){
	
	var account = $("#schacount").val();
	var code = $("#forget_code").val();
	
	$.post(
			"UserForgetPassword.action",
			{
				user_account:account,
				code:code
			},
			function(data){
				data = data.replace(/^\s*/, "").replace(/\s*$/, "");
				console.log(data);
				if(data == "NoAccount"){
					//alert(111);
					alert("没有这个账户");
				}
				else if(data == "Success"){
					alert("发送成功请注意查收邮件！");
					window.location.replace("forget_pwd.html");
				}
				else{
					alert("验证码错误！");
				}
			}
			);
}

function resetCode() {
    var date = new Date();
    console.log(11);
    $("#code_img").attr("src", "SendCode.action?" + date.toString());
}