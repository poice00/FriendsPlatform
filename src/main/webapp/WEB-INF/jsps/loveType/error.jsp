<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>404页面</title>
<script type="text/javascript">setTimeout(function(){top.location='';},6000)</script>
<style type="text/css">
body{text-align:center}
h1{font-family:"微软雅黑"}
</style>
</head>

<body>
<p><img src="<%=request.getContextPath()%>/resources/css/baihe/img/14025841314042.gif" width="520" height="320" /></p>
<h1>抱歉，这个页面已经被外星人绑架了</h1>
<p><span id="showtimes"></span>秒钟后将带您返回地球</p>
<script type="text/javascript">  
	//设定倒数秒数  
	var t = 5;  
	//显示倒数秒数  
	function showTime(){  
	    t -= 1;  
	    document.getElementById('showtimes').innerHTML= t;  
	    if(t==0){  
	        location.href='/FriendsPlatform/match';  
	    }  
	    //每秒执行一次,showTime()  
	    setTimeout("showTime()",1000);  
	}  
	//执行showTime()  
	showTime();  
</script>
</body>
</html>