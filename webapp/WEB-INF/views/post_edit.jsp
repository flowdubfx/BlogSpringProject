<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit ${post.name}</title>
</head>
<body>

	<h3>Edit blog</h3>

	<form:form modelAttribute="post">
		<form:input path="name" />
		<form:textarea path="text"/>
		<input type="submit" value="Update post">
	</form:form>

</body>
</html>