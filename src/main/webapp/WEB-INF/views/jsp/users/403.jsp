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
<spring:url value="/resources/images/undraw_access_denied.svg" var="access_denied" />
<!-- Bootstrap core CSS -->
<link href="${bootstrap}" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${style}" rel="stylesheet">
<title>Access Denied</title>
</head>
<body>
<br>
	<div class="row">
		<div class="col-md-6">
			
			<img src="${access_denied}" height="500" width="800" />
		</div>
		<div class="col-md-6">
			<h3>Access Denied</h3>
			<h2>${msg}</h2>
			<c:url value="/j_spring_security_logout" var="logoutUrl" />
			<a href="${logoutUrl}" class="btn btn-danger btn-sm">Log Out</a>
		</div>
	</div>		
</body>






</html>