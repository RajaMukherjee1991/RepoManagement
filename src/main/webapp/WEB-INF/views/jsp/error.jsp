<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">



<body>

	<div class="container">
	<spring:url value="/"  var="loginpage"/>
		<h1>You are logged out</h1>

		<%-- <p>${exception.message}</p> --%>
		<p>Please <a href="${loginpage}">Login</a></p>
		<!-- Exception: ${exception.message}.
		  	<c:forEach items="${exception.stackTrace}" var="stackTrace"> 
				${stackTrace} 
			</c:forEach>
	  	-->

	</div>

	

</body>
</html>