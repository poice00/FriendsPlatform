<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0031)http://xq.baihe.com/baihe/login -->
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width">
<title>百合相亲</title>
<link href="<%=request.getContextPath()%>/resources/baihe_files/lovecss.css" rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/resources/login_files/public.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/resources/login_files/jquery.min.js"></script>
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/resources/login_files/effect.js"></script>
<!--[if lte IE 8]>
	<link rel="stylesheet" type="text/css"  href="http://static1.baihe.com/css/loveie8.css?ver=1404.4"/>
<![endif]-->
<script type="text/javascript">
function login() {
	var name=$("#phone").val();
	var password=$("#password").val();
	$.ajax({
		url : "/FriendsPlatform/user/login",
		type : "post",
		data : {
 			name:name,
 			password:password
 		},
		success : function(response) {
			if(response=="用户名或密码不能为空"){
				alert(response);
			}
			else if(response=="无效的用户名" || response=="密码错误"){
				alert(response);
			}
			else{
				window.location.href="/FriendsPlatform/user/shouye";
			}
		},
	}); 
}
</script>
</head>
<body class="BJLand" style="background:url(<%=request.getContextPath() %>/resources/baihe_files/body_afternoon.jpg);">
	<div id="Landing" >
		<form id="login_form" action=""
			autocomplete="off" method="post">
			<div class="user">
				<h1>百合网旗下，严肃的婚恋交友平台</h1>
				<div class="left black">
					<p>百合网用户登录</p>
					<!--<div class="pic"><p>扫一扫下载<br>百合相亲APP</p><img src="./login_files/icon_02a.gif" alt="二维码"></div>-->
					<dl>
						<dt>账号</dt>
						<dd>
							<input id="phone" name="phone" type="text" placeholder="请输入百合网账号"><br>
							<!-- <span id="phone_msg" style="display: none;">对不起，登录帐户格式不正确！</span>
							<div id="phone_icon" class="ok" style="display: none;"></div> -->
						</dd>
					</dl>
					<dl>
						<dt>密码</dt>
						<dd>
							<input id="password" name="password" type="text" placeholder="请输入百合网密码"><br>
							<!-- <span id="password_msg" style="display: none;">密码格式不正确</span>
							<div id="password_icon" class="error" style="display: none;"></div> -->
						</dd>
					</dl>
					<dl>
						<dt>&nbsp;</dt>
						<dd>
							<input name="remember" type="checkbox" value="1" class="inp01">保存密码
							<a href="http://xq.baihe.com/register/forget/">忘记密码？</a>
						</dd>
					</dl>
				</div>
				<div class="right black">
					注册<br> <br> 还没有<br>百合相亲账号？ <a
						href="/FriendsPlatform/user/registerJsp" class="red">注册&gt;</a>
		
				</div>
				<div class="clear"></div>
				<a href="javascript:void(0);" class="red" id="login_btn" onclick="login()">进入</a>
				<!--<div class="txt"><a href="http://xq.baihe.com/register/agreement" target="_blank">百合相亲服务协议</a></div>-->
			</div>
		</form>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/login_files/baihe.login.js"></script>
	<div class="syTxtLB">
		<strong id="marriedcount">773,315</strong>人
	</div>
	<div class="syTxtLT">我们一生遇到无数人，遇到对的人来之不易，所以如果爱，认真爱！</div>

	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/login_files/countUp.min.js"></script>
	<script type="text/javascript">
		(function() {
			var count = '773315';
			var marriedcount = new countUp("marriedcount", 0, count, 0, 2.5);
			marriedcount.start();
		})();
	</script>


</body>
</html>