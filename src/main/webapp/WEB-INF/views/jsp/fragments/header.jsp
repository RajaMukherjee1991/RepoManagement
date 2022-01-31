<html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=yes">
<meta name="description" content="">
<meta name="author" content="Raja Mukherjee">
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
<spring:url value="/resources/css/bootstrap.css" var="bootstrap" />
<spring:url value="/resources/css/style.css" var="style" />
<spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="datatablebootstrapcss" />
<%-- <spring:url value="/resources/css/nav.css" var="navcss" /> --%>
	
<spring:url value="/resources/css/dataTables.bootstrap4.min.css" var="datatablebootstrapcss" />
<spring:url value="/resources/css/sb-admin-2.min.css" var="sb-admin-2" />
<spring:url value="/resources/css/all.min.css" var="allmin" />
<spring:url value="/resources/css/fstdropdown.min.css" var="fstdropdowncss" />

<spring:url value="/resources/js/bootstrap.js" var="bootstrapjs" />
<spring:url value="/resources/js/jquery.js" var="jqueryjs" />
<spring:url value="/resources/js/dataTables.bootstrap4.min.js" var="datatablesbootstrapjs" />
<spring:url value="/resources/js/jquery.dataTables.min.js" var="datatablesjs" />
<spring:url value="/resources/js/gbj.js" var="gbjjs" />
<spring:url value="/resources/js/popper.min.js" var="popperjs" />
<spring:url value="/resources/js/mortgage-features.js" var="mortgagefeatures" />
<spring:url value="/resources/js/stock-features.js" var="stockfeatures" />
<spring:url value="/resources/js/sales-features.js" var="salesfeatures" />
<spring:url value="/resources/js/basicoverview-features.js" var="basicoverviewfeatures" />
<spring:url value="/resources/js/mfs100-9.0.2.6.js" var="mfsjs" />
<spring:url value="/resources/js/fingerprint.js" var="fingerprint" />
<spring:url value="/resources/js/bootbox.js" var="bootbox" />
<spring:url value="/resources/js/fstdropdown.min.js" var="fstdropdownjs" />
<spring:url value="/resources/js/script.js" var="scriptjs" />


<!-- jQuery -->
<script type="text/javascript" src="${jqueryjs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${popperjs}"></script>
<script type="text/javascript" src="${bootstrapjs}"></script>
<script type="text/javascript" src="${bootbox}"></script>
<script type="text/javascript" src="${datatablesjs}"></script>
<script type="text/javascript" src="${datatablesbootstrapjs}"></script>
<script type="text/javascript" src="${gbjjs}"></script>
<script type="text/javascript" src="${mortgagefeatures}"></script>
<script type="text/javascript" src="${stockfeatures}"></script>
<script type="text/javascript" src="${salesfeatures}"></script>
<script type="text/javascript" src="${basicoverviewfeatures}"></script>
<script type="text/javascript" src="${mfsjs}"></script>
<script type="text/javascript" src="${fingerprint}"></script>
<script type="text/javascript" src="${fstdropdownjs}"></script>
<script type="text/javascript" src="${scriptjs}"></script>

<!-- CSS -->
<link href="${bootstrap}" rel="stylesheet">
<link href="${style}" rel="stylesheet">
<link href="${datatablebootstrapcss}" rel="stylesheet">
<link href="${datatablecss}" rel="stylesheet">
<link href="${sb-admin-2}" rel="stylesheet">
<link href="${allmin}" rel="stylesheet" type="text/css">
<link href="${fstdropdowncss}" rel="stylesheet" type="text/css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
</head>

<tiles:importAttribute name="selectedMenu" ignore="true"/>
<body>
	<spring:url value="/dashboard" var="dashboard" />
	<spring:url value="/billing" var="billing" />
	<spring:url value="/customer" var="customer" />
	<spring:url value="/billing/toSalesBilling" var="toSalesBilling" />
	<spring:url value="/billing/toMortgageBilling" var="toMortgageBilling" />
	<spring:url value="/admin" var="admin" />
	<spring:url value="/search" var="search" />
	<spring:url value="/stock" var="stock" />
	<spring:url value="/setting" var="setting" />
	<spring:url value="/stock/mortgageStock" var="mortgageStock" />
	<spring:url value="/billing/toBillingOverview" var="toBillingOverview" />
	<spring:url value="/print" var="reports" />
	<spring:url value="/resources/images/Ghosh Brothers Jewellers-logos.jpeg" var="ghoshbrothersjewellersImage" />

	<c:url value="/j_spring_security_logout" var="logoutUrl" />

	<div class="d-flex" id="wrapper">
		
		<!-- Sidebar -->
		<div class="border-right shadow-sm" id="sidebar-wrapper">
			<div>
				<img src="${ghoshbrothersjewellersImage}" height="250" width="250"/>
			</div>
			<div class="list-group"> 
				<div class="card">
					<div class="card-header btn-link text-left py-2" data-toggle="collapse" data-target="#dashboard" aria-expanded="true" aria-controls="dashboard">
								<i class='bx bx-grid-alt bx-sm'></i> Dashboard
					</div>
					<div class="collapse multi-collapse" id="dashboard">
 						<a href="${dashboard}" class=" list-group-item py-2 text-justify <c:if test="${selectedMenu =='dashboard'}">activated</c:if>">
   								 Home Page
  						</a>
  						<a href="${customer}" class=" list-group-item py-2 text-justify <c:if test="${selectedMenu =='customer'}">activated</c:if>">
   								 Customer Details
  						</a>
					</div>
				</div>
				
				<!-- Billing -->
				<div class="card">
					<div class="card-header btn-link text-left py-2" data-toggle="collapse" data-target="#billing" aria-expanded="true" aria-controls="billing" >
							<i class='bx bx-receipt bx-sm' ></i>  Billing
					</div>
					<div class="collapse multi-collapse" id="billing">
  								<a href="${billing}" class="list-group-item list-group-item-action py-2 text-justify <c:if test="${selectedMenu =='billingSubMenu1'}">activated</c:if>">Check all bills</a>
  								<a href="${toBillingOverview}" class="list-group-item list-group-item-action py-2 text-justify <c:if test="${selectedMenu =='billingSubMenu2'}">activated</c:if>">Add Bill</a>
					</div>
				</div>
				
				
				<!-- Admin -->
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<div class="card">
					<div class="card-header btn-link text-left py-2 " data-toggle="collapse" data-target="#admin" aria-expanded="true" aria-controls="admin" >
							<i class='bx bx-user-pin bx-sm' ></i>  Admin
					</div>
					<div class="collapse multi-collapse" id="admin">
  								<a href="${setting}" class="list-group-item list-group-item-action py-2 text-justify <c:if test="${selectedMenu =='userSetting'}">activated</c:if>">User Setting</a>
					</div>
				</div>
				</sec:authorize>	
			
				<!-- Stock -->
				<div class="card">
					<div class="card-header btn-link text-left py-2" data-toggle="collapse" data-target="#stock" aria-expanded="true" aria-controls="stock">
							<i class='bx bx-cart bx-sm'></i>  Stocks
					</div>
					<div class="collapse multi-collapse" id="stock">
  								<a href="${stock}" class="list-group-item list-group-item-action py-2 text-justify <c:if test="${selectedMenu =='stockSubMenu1'}">activated</c:if>">Sales Stock</a>
  								<a href="${mortgageStock}" class="list-group-item list-group-item-action py-2 text-justify <c:if test="${selectedMenu =='stockSubMenu2'}">activated</c:if>">Mortgage Stock</a>
					</div>
				</div>
				
				<!-- Generate Reports -->
				<div class="card">
					<div class="card-header btn-link text-left py-2" data-toggle="collapse" data-target="#reports" aria-expanded="true" aria-controls="stock">
							<i class='bx bx-spreadsheet bx-sm' ></i>  Generate Reports
					</div>
					<div class="collapse multi-collapse" id="reports">
  								<a href="${reports}" class="list-group-item list-group-item-action py-2 text-justify <c:if test="${selectedMenu =='reports'}">activated</c:if>">Reports</a>
					</div>
				</div>
				
				<!-- Logout -->
				<div class="card">
					<div class="card-header">
							<a href="${logoutUrl}" class="btn btn-link text-center">
   								<i class='bx bx-log-out bx-sm' ></i>  Logout
  							</a>
					</div>
				</div>
				<%-- <a href="${search}" class="btn btn-md text-left">Test</a> <a
					href="${search}" class="btn btn-md text-left">Generate Reports</a> --%>
			</div>
		</div>
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<%-- <tiles:addAttribute name="error"></tiles:addAttribute> --%>
			<tiles:insertAttribute name="content"></tiles:insertAttribute>
		</div>
		</div>
   </body> 	
</html>	