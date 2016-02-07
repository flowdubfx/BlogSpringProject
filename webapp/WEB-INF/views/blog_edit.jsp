<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"  prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit ${blog.name}</title>
</head>
<body>
		
	<h3>Edit blog</h3>
	
	<form:form modelAttribute="blog" >
		<form:input path="name"/>
		<input type="submit" value="Update blog">		
	</form:form>	
	
</body>
</html>