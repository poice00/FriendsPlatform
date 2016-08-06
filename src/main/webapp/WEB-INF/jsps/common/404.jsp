<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>  
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>页面不存在</title>
		
		<jsp:include page="/WEB-INF/jsps/common/link.jsp"></jsp:include>
		
	</head>
	<body style="background:#fff">
		<div id="error">
			<dl>
				<dt><img src="/FriendsPlatform/resources/img/static/404.gif" alt=""></dt>
				<dd>
					<strong>对不起，请检查您输入的网址是否正确。</strong><br>
					或者点击这里：<a href="/FriendsPlatform">返回相亲</a>
				</dd>
			</dl>
		</div>
	</body>
</html>