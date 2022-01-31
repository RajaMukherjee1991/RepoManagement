<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<!-- Required meta tags -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="icon" href="Favicon.png">

<spring:url value="/resources/css/bootstrap.css" var="bootstrap" />
<spring:url value="/resources/css/style.css" var="style" />

<!-- Bootstrap core CSS -->
<link href="${bootstrap}" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${style}" rel="stylesheet">
<title>Login Page</title>
</head>
<body onload='document.loginForm.j_username.focus();'>
	<!-- <h3>GBJ Login Page</h3> -->

	<div class="backloginScreen">


		<div class="div-center shadow">
			<div class="content">
				<%
					String errorString = (String) request.getAttribute("error");
					if (errorString != null && errorString.trim().equals("true")) {
				%>

				<div class="alert alert-danger">
					Incorrect login name or password. Please try again
				</div>
				<%
					}
				%>
				<h3>Login</h3>
				<hr>
				<form name='loginForm' action="<c:url value='login' />"
					method='POST'>
					<div class="form-group">
						<label>Username</label> <input type="text" class="form-control"
							name="username" />
					</div>
					<div class="form-group">
						<label>Password</label> <input type="password"
							class="form-control" name="password" />
					</div>
					<input type="submit" class="btn gbj-btn btn-sm" value="Login"
						name="submit"> <br>
					<hr>

					<input type="reset" class="btn btn-link" value="Reset" name="reset" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>
