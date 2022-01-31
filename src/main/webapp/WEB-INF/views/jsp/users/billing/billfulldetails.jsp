<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Bill Details</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="billingSubMenu1"/>
	<tiles:putAttribute name="content">
<spring:url value="/billing" var="bills" />
<jsp:include page="billsubdetail.jsp">
		<jsp:param value="${customersBilledstockMap}" name="customersBilledstockMap"/>
</jsp:include>
<br>
<button class="btn btn-sm btn-success" onclick="history.back()">Go Back</button>
<jsp:include page="../../fragments/footer.jsp" />
</tiles:putAttribute>
</tiles:insertDefinition>
