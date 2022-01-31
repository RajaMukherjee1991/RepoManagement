<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Sales</tiles:putAttribute>
	<tiles:putAttribute name="content">
<spring:url value="/sales/billing/searchCustomer" var="salesmain" />

<h3>Sales</h3>
<hr>
<br>

<form:form action="${salesmain}" method="post" modelAttribute="customer">
	<div class="row">
		<div class="col-6">
			<div class="card shadow-sm mb-5 bg-white rounded">
				<div class="card-header">
					<h5>Search by Mobile number</h5>
				</div>
				<div class="card-body">
					<div class="input-group mb-3">
						<form:input type="text" path="searchType" class="form-control"
							placeholder="Customer's Lastname or Mobile number" />
					</div>
					<p class="card-text">Search a customer / Add a customer using
						Mobile Number, Last-Name</p>
					<button type="submit" class="btn btn-success">Search</button>
				</div>
			</div>
		</div>
		<div class="col-6">
			<%-- <div class="card shadow-sm mb-5 bg-white rounded">
				<div class="card-header">Search Sales customer by Fingerprint</div>
				<div class="card-body">
					<div class="input-group mb-3">
						<form:input type="text" path="searchType" class="form-control"
							placeholder="Customer's Lastname or Mobile number" />
					</div>
					<p class="card-text">Search a customer / Add a customer using
						Mobile Number, Last-Name</p>
					<button type="submit" class="btn btn-primary">Search</button>
				</div>
			</div> --%>
		</div>
	</div>
</form:form>
<br>
<br>

<c:choose>
	<c:when test="${show}">
		<h3>Sales customer table results</h3>
		<hr>
		<br>
		<c:forEach items="${savedcustomerList}" var="savedcustomerList">
			<ul class="list-group shadow-sm">
				<li
					class="list-group-item d-flex justify-content-between align-items-center">
					<c:out value="${savedcustomerList.fname}" />&nbsp;<c:out
						value="${savedcustomerList.lname}" /> &nbsp;
					<button class="btn btn-success" type="button"
						data-toggle="collapse" data-target="#collapseExample"
						aria-expanded="false" aria-controls="collapseExample">
						See more</button>
				</li>
			</ul>
			<br>
			<div class="collapse" id="collapseExample">
				<table class="table table-sm table-bordered shadow-sm">
					<tbody>
						<tr>
							<th scope="row">Name</th>
							<td><c:out value="${savedcustomerList.fname}" />&nbsp;<c:out
									value="${savedcustomerList.lname}" /> <c:if
									test="${savedcustomerList.isVerified == 1}">
									<h5 class="badge badge-success">Verified Customer</h5>
								</c:if> <c:if test="${savedcustomerList.isVerified == 0}">
									<h5 class="badge badge-danger">Customer Not Verified</h5>
								</c:if></td>

							<td><a href="#">Print PDF</a></td>
						</tr>
						<tr>
							<th scope="row">Id</th>
							<td><c:out value="${savedcustomerList.customerPKID}" /></td>
							<td><a href="#">See transactions</a></td>
						</tr>
						<tr>
							<th scope="row">Mobile Number</th>
							<td><c:out value="${savedcustomerList.primarymobilenumber}" /></td>
							<td><a href="#">Send OTP</a></td>
						</tr>
						<tr>
							<th scope="row">Email</th>
							<td><c:out value="${savedcustomerList.email}" /></td>
							<td><a href="#">Send Email</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:forEach>
		<br>
	</c:when>
	<c:when test="${empty show}">
	</c:when>
	<c:otherwise>
		<h3>Sales customer table results</h3>
		<hr>
		<br>
		<div class="card w-100 shadow-sm">
			<div class="card-body">
				<h5 class="card-title">Add new customer</h5>
				<a href="#" class="btn btn-success">Add New</a>
			</div>
		</div>
	</c:otherwise>
</c:choose>
</tiles:putAttribute>
</tiles:insertDefinition>
