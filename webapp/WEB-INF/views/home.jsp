<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Home page</title>
</head>
<body>


	<h3>Welcome to our blog.</h3>

	<security:authorize access="!isAuthenticated()">
		<a href="<spring:url value="/login" />">Login</a>
		<a href="<spring:url value="/register" />">Register</a>
	</security:authorize>

	<security:authorize access="hasRole('ROLE_ADMIN')">
		<a href="<spring:url value="/admin" />">Admin</a>
	</security:authorize>



	<security:authorize access="isAuthenticated()">
		<a href="<spring:url value="/logout" />">Logout</a>
		<a href="<spring:url value="/member" />">Your account</a>
	</security:authorize>

	<ul>
		<c:forEach var="blog" items="${blogs}">
			<li><a href="<c:url value="/blog/" />${blog.id}"><c:out
						value="${blog.name}" /></a></li>
		</c:forEach>
	</ul>



	<br />
	<br />

	<c:forEach var="post" items="${posts}">
		<h3>
			<a href="<c:url value="/post/" />${post.id}"><c:out
					value="${post.name}" /></a>
		</h3>
		<p>
			<c:out value="${post.text}" />
		</p>
		<p>
			<fmt:formatDate type="date" value="${post.created}" />
		</p>


	</c:forEach>

</body>
</html>