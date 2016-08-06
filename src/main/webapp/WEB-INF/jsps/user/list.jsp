<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Hello</title>  
</head>  
<body>  
	
	<div align="center">
	    <c:forEach items="${userList}" var="item">
	  		${item.name },
	  		${item.description }
	  		<a href="editUI?id=${item.id }">修改</a>
	  		<a href="delete?id=${item.id }" onclick="return confirm('确定要删除吗')">删除</a>
	  		<br>
	  </c:forEach>
	  	<a href="addUI">添加</a>
	 </div>
	 
	 
</body>  
</html>