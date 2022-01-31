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
<spring:url value="/login" var="login" />
<spring:url value="/dashboard" var="dashboard" />

<title>Welcome</title>
<body>
	<br>
	<div class="backloginScreen">
		<div class="div-center shadow">
			<div class="content">
				<h3>Welcome</h3>
				<hr>
				<form>
					<h5><label>Login to Ghosh Brothers Jewellers</label></h5> 
					<a href="${login}" class="btn gbj-btn btn-sm">Login</a>
					<hr>
				</form>
			</div>
		</div>
	</div>
</body>
</head>
</html>
