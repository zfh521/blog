// JavaScript Document	
//check参数
function isName(){
	//用户名
	var $userName = $("input[name=name]");
	var regexp =  /^[a-zA-Z][a-zA-Z0-9_]{2,16}$/;
	var con = document.getElementById("username");
	if(regexp.test($userName.val())){
		$userName.parent().addClass("success");
		con.innerHTML="";
		return true;
	}else{
		$userName.parent().removeClass("success");
		$userName.parent().addClass("error");
		con.style.color="red";
		con.style.fontSize="9px";
		con.innerHTML="以字母开头，至少3位以上（包括3位）";	
		return false;	
	}	
	
}
function isCompany(){
	//真实姓名
	var $companyName = $("input[name=companyName]");
	var chinese =  /^[\u0391-\uFFE5]*$/;
	var con = document.getElementById("companyName");
	if(chinese.test($companyName.val()) && $companyName.val()!=""){
		$companyName.parent().addClass("success");
		con.innerHTML="";
		return true;
	}else{
		$companyName.parent().removeClass("success");
		$companyName.parent().addClass("error");
		con.style.color="red";
		con.style.fontSize="9px";
		con.innerHTML="请输入中文";	
		return false;	
	}
}
function isPassWord(){
	//密码
	var $passWord = $("input[name=password]");
	var txtCode =  /^[0-9a-zA-Z_]{6,15}$/;
	var con = document.getElementById("password");
	if(txtCode.test($passWord.val())){
		$passWord.parent().addClass("success");
		con.innerHTML="";
		return true;
	}else{
		$passWord.parent().removeClass("success");
		$passWord.parent().addClass("error");
		con.style.color="red";
		con.style.fontSize="9px";
		con.innerHTML="至少6位以上（包括6位）";
		return false;		
	}	
}

function isPassWord1(){
	//密码1
	var $passWord = $("input[name=password]");
	var $passWord1 = $("input[name=password1]");
	var con = document.getElementById("password1");

	if($passWord1.val()==$passWord.val() && $passWord1.val()!=""){
		$passWord1.parent().addClass("success");
		con.innerHTML="";
		return true;
	}else{
		$passWord1.parent().removeClass("success");
		$passWord1.parent().addClass("error");
		con.style.color="red";
		con.style.fontSize="9px";
		con.innerHTML="密码不一致";	
		return false;	
	}	
}

function isRealName(){
	//真实姓名
	var $realName = $("input[name=contactPerson]");
	var chinese =  /^[\u0391-\uFFE5]*$/;
	var con = document.getElementById("realname");
	if(chinese.test($realName.val()) && $realName.val()!=""){
		$realName.parent().addClass("success");
		con.innerHTML="";
		return true;
	}else{
		$realName.parent().removeClass("success");
		$realName.parent().addClass("error");
		con.style.color="red";
		con.style.fontSize="9px";
		con.innerHTML="请输入中文";	
		return false;	
	}
	
}

function isTelPhone(){
	
	//手机号码
	var $tellPhone = $("input[name=contactWay]");
	var phoneNum =  /^1[3458]\d{9}$/;
	var con = document.getElementById("tel");
	if(phoneNum.test($tellPhone.val())){
		$tellPhone.parent().addClass("success");
		con.innerHTML="";
		return true;
	}else{
		$tellPhone.parent().removeClass("success");
		$tellPhone.parent().addClass("error");
		con.style.color="red";
		con.style.fontSize="9px";
		con.innerHTML="请输入正确的手机号码";
		return false;
	}
	
}

function isEmail(){
	
	//邮件
	var con = document.getElementById("email");
	var $mail = $("input[name=email]");
	var mailURL =  /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	if(mailURL.test($mail.val())){
		$mail.parent().addClass("success");
		con.innerHTML="";
		return true;
	}else{
		$mail.parent().removeClass("success");
		$mail.parent().addClass("error");
		con.style.color="red";
		con.style.fontSize="9px";
		con.innerHTML="请输入正确的邮件联系方式";
		return false;
	}
	
}


//注册
$(document).ready(function() {
	$("#submitBtn").on("click",function(){
	  if(isName()&&isPassWord()&&isPassWord1()&&isRealName()&&isTelPhone()&&isEmail()){
		  if($("input[type=checkbox]")[0].checked==false){
			  alert("您需要查看并同意注册协议!");
			  return;
		  }
	  	$.post("/account/reg?_t="+new Date().getTime(),$("form.formContent").serializeArray(),function(result){
	  		//alert(result.retCode)
	  		if(result.status==0){
	  			alert(result.message)
	  		}else{
	  			window.location.href="/checkEmail.html?email="+result.contents.email+"&code="+result.contents.salt;
	  		}
	  	})
	  }
   });

});
