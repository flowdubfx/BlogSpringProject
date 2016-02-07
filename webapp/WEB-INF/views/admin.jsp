<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin</title>
</head>
<body>


	Welcome ${username}.

	<security:authorize access="isAuthenticated()">
		<a href="<spring:url value="/logout" />">Logout</a>
	</security:authorize>


	<h3>Blogs</h3>

	<ul>
		<c:forEach var="blog" items="${blogs}">
			<li>
			<a href="<spring:url value="/blog/${blog.id}"/>">${blog.name}</a>
			| <a href="<spring:url value="/admin/edit/blog/${blog.id}"/>">Edit</a>
			| <a href="<spring:url value="/admin/delete/blog/${blog.id}"/>">Delete</a>
			</li>

		</c:forEach>
	</ul>

	<br />

	<h3>Posts</h3>

	<ul>
		<c:forEach var="post" items="${posts}">
			<li>
			<a href="<spring:url value="/post/${post.id}"/>">${post.name}</a>
			| <a href="<spring:url value="/admin/edit/post/${post.id}"/>">Edit</a>
			| <a href="<spring:url value="/admin/delete/post/${post.id}"/>">Delete</a>
		</c:forEach>
	</ul>


</body>
</html>