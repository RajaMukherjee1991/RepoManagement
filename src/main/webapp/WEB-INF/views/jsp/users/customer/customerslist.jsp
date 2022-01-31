<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Customer Page</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="customer"/>
	<tiles:putAttribute name="content">
<spring:url value="/customer/customerpage" var="savecustomer" />
<spring:url value="/customer/newCustomer" var="newcustomer" />
<spring:url value="/customer/checkDetails" var="checkDetails" />


<div class="row">
	<div class="col-md-4">
		<h3>Customer Details</h3>
	</div>
	<div class="col-md-8">
		<div class="col-xl-12 col-md-6 mb-4">
                            <div class="card border-left-primary h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                Total Customers</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${customerList.size()}</div>
                                        </div>
                                        <div class="col-auto">
                                            <a
											href="${newcustomer}"
											class="btn btn-sm gbj-btn"><i class='bx bxs-user-plus bx-xs' style='color:#d5e627'></i> Add New</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
		
	</div>
</div>
<hr>
<c:if test="${not empty msg}">
	<div class="alert alert-${css} alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong>${msg}</strong>
	</div>
</c:if>
<br>
<div class="row">
	<div class="col-md-12">
		<div class="card">
			<div class="card-header">
				<div class="row">
					<div class="col-md-11">
						<h6>Customer List</h6>
					</div>
					<div class="col-md-1 float-right">
						<div class="dropdown">
							<button type="button" class="btn btn-light btn-sm"
								data-toggle="dropdown">...</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="#">Download PDF</a> 
								<a class="dropdown-item" href="#">Download Excel</a>
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="card-body">
	<table class="table table-sm table-bordered table-hover shadow-md" id="customerdatatable">
		<thead>
			<tr style="background-color: #D6EAF8;">
				<th>Customer ID</th>
				<th>Name</th>
				<th>Mobile Number</th>
				<th>Verification</th>
			</tr>
		</thead>
			<tbody>
			<c:forEach items="${customerList}" var="customerList">
				<tr>
					<td>${customerList.customerPKID}</td>
					<td><a href="${checkDetails}?customerid=${customerList.customerPKID}">${customerList.fullname}</a></td>
					<td>${customerList.primarymobilenumber}</td>
					<td><c:if test="${customerList.isVerified == 1}">
							<h8 class="text-success">Verified Customer</h8>
						</c:if> <c:if test="${customerList.isVerified == 0}">
							<h8 class="text-danger">Customer Not Verified</h8>
						</c:if></td>

					<%-- <td><button class="btn btn-link" type="button"
							data-toggle="collapse"
							data-target="#collapseExample${customerList.customerPKID}"
							aria-expanded="false" aria-controls="collapseExample">See
							All</button></td> --%>
				</tr>
				</c:forEach>
			</tbody>
	</table>
</div>
</div>
</div>	
</div>
</tiles:putAttribute>
</tiles:insertDefinition>
