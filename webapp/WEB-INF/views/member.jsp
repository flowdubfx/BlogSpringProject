<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Member page</title>
</head>
<body>

	<p>Welcome, ${member.username}.</p>
	<ul>
		<c:forEach var="blog" items="${member.blogs}">
			<li><c:out value="${blog.name}" /></li>
		</c:forEach>
	</ul>

	<h4>Your posts</h4>

	<c:forEach var="blog" items="${member.blogs}">
		<c:forEach var="post" items="${blog.posts}">
			<h3>
				<c:out value="${post.name}" />
			</h3>
			<p>
				<fmt:formatDate type="date" value="${post.created}" />
			</p>
		</c:forEach>
	</c:forEach>


	<h3>Add blog</h3>

	<form:form commandName="blog">
		<label>Blog name</label>
		<form:input path="name" />
		<form:errors path="name" />
		<input type="submit" value="Add blog">
	</form:form>

	<h3>Add post</h3>

	<form:form action="/blog/addPost" commandName="post">
		<label>Post name</label>
		<form:input path="name" />
		<form:errors path="name" />
		<form:textarea path="text" />
		<form:errors path="text" />
		
		<form:select path="blog.id" items="${blogs}" itemLabel="name"
			itemValue="id" />
		<form:errors path="blog" />
		<input type="submit" value="Add post">
	</form:form>

</body>
</html>