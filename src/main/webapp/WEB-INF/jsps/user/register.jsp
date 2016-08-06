<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0037)http://xq.baihe.com/register/register -->
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">
<title>百合相亲</title>
<link href="<%=request.getContextPath()%>/resources/register_files/lovecss.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/register_files/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/resources/register_files/jquery.min.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath() %>/resources/register_files/effect.js"></script>
<!--[if lte IE 8]>
	<link rel="stylesheet" type="text/css"  href="http://static1.baihe.com/css/loveie8.css?ver=1404.4"/>
<![endif]-->
<script type="text/javascript">
function checkCodeSend() {
	var phone=$("#phone").val();
	$.ajax({
		url : "/FriendsPlatform/user/phoneNumCheck",
		type : "post",
		data : {
			phone:phone
 		},
		success : function(response) {
		if(response=="该手机号已被注册")
			{
			alert(response);
			}
		else{
			document.getElementById("captcha_tips").style.display = "block";
		}
		},
	}); 
}
function register() {
	var phone=$("#phone").val();
	var code=$("#captcha").val();
	var password=$("#password").val();
	$.ajax({
		url : "/FriendsPlatform/user/register",
		type : "post",
		data : {
			phone:phone,
			code:code,
			password:password
 		},
		success : function(response) {
		if(response=="你输入的验证码不正确")
			{
			alert(response);
			}
		else
			{
		window.location.href="/FriendsPlatform/user/shouye";
			}
		},
	}); 
}
function login() {
    window.location.href="/FriendsPlatform/user/loginJsp";
}
</script>
</head>
<body class="BJLand" style="background:url(<%=request.getContextPath() %>/resources/baihe_files/body_afternoon.jpg);">
<div class="layerLogin"><a href="javascript:void(0);" class="yellow" onclick="login()">百合网账号登录</a></div>
<div id="Landing">
	<form id="register_form" action="" autocomplete="off" method="post">
	<div class="mobile">
		<a href="http://xq.baihe.com/register"><h1>百合网旗下，严肃的婚恋交友平台</h1></a>
		<div class="black">
			<p>在推荐会员之前，让我们先帮您创建相亲账号：）</p>
			<dl>
				<dt>账号设置</dt>
				<dd>
					<input id="phone" name="phone" type="text" placeholder="请输入你的手机号"><br>
					<!-- <span id="phone_msg" style="display:none;">请正确输入手机号码</span>
					<div id="phone_icon" class="ok" style="display:none;"></div> -->
				</dd>
			</dl>
			<dl>
				<dt>填写验证码</dt>
				<dd>
					<input id="captcha" name="captcha" type="text" placeholder="填写验证码" class="inp" style="width:147px;">
					<a href="javascript:void(0);" data-url="/register/captcha" class="red" id="captcha_btn" onclick="checkCodeSend()">获取验证码</a>
					<!-- <div class="time" style="display:none;" id="captcha_tips" data-time="60" data-unit="秒"></div>
					<span id="captcha_msg" style="display:none;">验证码不正确~~~~</span>
					<div id="captcha_icon" style="display:none;" class="error"></div> -->
				</dd>
			</dl>
			<dl>
				<dt>账号密码设置</dt>
				<dd>
					<input id="password" name="password" type="text" placeholder="密码由6-18位英文字母或数字组成"><br>
					<!-- <span id="password_msg" style="display:none;">密码不能为空或大于6位~~~~</span>
					<div id="password_icon" class="error" style="display:none;"></div> -->
				</dd>
			</dl>
			<div class="left"><a href="http://xq.baihe.com/register/agreement" target="_blank">百合相亲服务协议</a></div>
			<a href="javascript:void(0);" class="red01" id="login_btn" onclick="register()">同意协议并创建账号</a>
		</div>
	</div>
	</form>
</div>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/register_files/register.register.js"></script>
<div class="syTxtLB"><strong id="marriedcount">773,315</strong>人</div>
<div class="syTxtLT">我们一生遇到无数人，遇到对的人来之不易，所以如果爱，认真爱！</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/register_files/countUp.min.js"></script>
<script type="text/javascript">
(function() {
	var count = '773315';
	var marriedcount = new countUp("marriedcount", 0, count, 0, 2.5);
	marriedcount.start();
})();
</script>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F887bcaca634b40591b6e9953c168af21' type='text/javascript'%3E%3C/script%3E"));
</script><script src="./register_files/h.js" type="text/javascript"></script>


</body></html>