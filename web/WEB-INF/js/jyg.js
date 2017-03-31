

function login() {
	var login = document.getElementById("yq_login")
	login.style.display = "inline"
}

window.onload=function() {
	window.setInterval("realSysTime()" , 1000);
}


function realSysTime() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth();
	var date = now.getDate();
	var day = now.getDay();
	var hour = now.getHours();
	var minu = now.getMinutes();
	var sec = now.getSeconds();
	
	month += 1;
	var arr_week = new Array("星期日", "星期一","星期二" ,"星期三" ,"星期四" ,"星期五" ,"星期六");
	var week = arr_week[day];
	var time1 = hour+" : "+minu+" : "+sec;
	var time2 = year +"年" + month + "月" + date +"日" + week;

	var clock1 = document.getElementById("jyg_time1");
	clock1.innerHTML =  time1;
	var clock2 = document.getElementById("jyg_time2");
	clock2.innerHTML =  time2;
}

function check() {
	var login = document.getElementById('yq_login');
	var name = yq_form.username.value
	var pw = yq_form.password.value
	var pw2 = yq_form.password2.value
	var e_mail = yq_form.email.value
	var sex = yq_form.sex.value

	var reg_pw = new RegExp("^[A-Za-z0-9]+$")
	var reg_email = new RegExp("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+")
	
	if (name=="") {
    	alert("用户名不能为空");
    	yq_form.username.focus();
    	yq_form.username.select();
    	return false;
	}else if(pw==""){
		alert("密码不能为空");
		yq_form.password.focus();
		yq_form.password.select();
		return false;
	}else if(pw.length<6){
		alert("密码长度少于6位");
		yq_form.password.focus();
		yq_form.password.select();
		return false;
	}else if(!reg_pw.test(pw)){
		alert("密码错误字符");
		yq_form.password.focus();
		yq_form.password.select();
		return false;
	}else if(pw!=pw2){
		alert("密码不一致");
		yq_form.password2.focus();
		yq_form.password2.select();
		return false;
	}else if (!reg_email.test(e_mail)) {
		alert("邮箱格式不对");
		yq_form.Email.focus();
		yq_form.Email.select();
		return false;
	}


	login.style.display = "none";

	alert("登录成功");
    return true;
}