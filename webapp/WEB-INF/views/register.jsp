<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Registration</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

<script type="text/javascript" 
		src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
</head>
<body>


	<h4>Create account</h4>
	
	

	<form:form commandName="member" cssClass="registrationForm">
		<c:if test="${success eq true}">
			<div>Registration successfull!</div>
		</c:if>
		
		<label>Username:</label>
		<form:input path="username" />
		<form:errors path="username" />
		<br />

		<label>Password:</label>
		<form:password path="password"/>
		<form:errors path="password"/>
		<br />

		<label>Confirm password:</label>
		<input type="password" name="password_again" id="password_again">
		<br />
		<input type="submit" value="Create account">
	</form:form>

<script type="text/javascript">
$(document).ready(function() {
	
	$(".registrationForm").validate(
		{
			rules: {
				username: {
					required : true,
					minlength : 3,
					remote : {
						url: "<spring:url value='/register/available' />",
						type: "get",
						data: {
							username: function() {
								return $("#username").val();
							}
						}
					}
				},
				password: {
					required : true,
					minlength : 5
				},
				password_again: {
					required : true,
					minlength : 5,
					equalTo: "#password"
				}
			},			
			messages: {
				username: {
					remote: "Such username already exists!"
				}
			}
		}
	);
});
</script>
</body>
</html>