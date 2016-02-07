<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Login page</title>
</head>
<body>
	
	
	<form action="<spring:url value="/j_spring_security_check"/>" method="post">
		<label>Enter username</label>
		<input type="text" name="j_username" required autofocus>
		<br/>
		<label>Enter password</label>
		<input type="password" name="j_password" required>
		<input type="submit" value="Login" >	
	</form>
	
</body>
</html>