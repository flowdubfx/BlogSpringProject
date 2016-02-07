<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>${blog.name}</title>
</head>
<body>

	<p>
		Posts in
		<c:out value="${blog.name}" />
	</p>

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