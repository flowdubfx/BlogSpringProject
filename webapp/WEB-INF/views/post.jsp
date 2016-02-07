<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<title>${post.name}</title>
</head>
<body>


	<h3>
		<c:out value="${post.name}" />
	</h3>
	<p>
		<c:out value="${post.text}" />
	</p>
	<p>
		<fmt:formatDate type="date" value="${post.created}" />
	</p>

	<br />
	<br />

	<c:forEach var="comment" items="${comments}">
		<c:out value="${comment.text}" />
		<p>
			Created by: ${comment.member.username},
			<fmt:formatDate type="date" value="${comment.created}" />
		</p>
	</c:forEach>

	<security:authorize access="isAuthenticated()">
		<h5>Add a comment</h5>
		<form:form commandName="comment">
			<form:textarea path="text"/>
			<form:errors path="text" />
			<input type="submit" value="Add comment">
		</form:form>
	</security:authorize>
	
	<security:authorize access="!isAuthenticated()">
		<p>You must be logged in to add a comment.</p>
	</security:authorize>
</body>
</html>