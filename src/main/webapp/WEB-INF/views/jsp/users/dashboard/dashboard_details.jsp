<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Dashboard</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="dashboard"/>
	<tiles:putAttribute name="content">
		<spring:url value="/stock" var="stock" />
		<spring:url value="/customer" var="customer" />
		<spring:url value="/resources/images/undraw_Dashboard_re_3b76.svg" var="dashboardImage" />
		<spring:url value="/billing/fullBillDetails" var="fullbilldetails" />
		<spring:url value="/customer/checkDetails" var="checkCustomerDetails" />
		<spring:url value="/billing" var="billing" />
		
<div class="row">
	<div class="col-md-4">
			<h3>Dashboard</h3>
			<br> <img src="${dashboardImage}" height="200" width="400" />
	</div>
	<div class="col-md-8">
		<div class="col-md-12 col-md-6 mb-4">
			<!-- <div class="card-header">Recent Transactions</div> -->
			<c:choose>
				<c:when test="${!empty recentbillList}">
				<h4>Today's Transactions</h4>
				<br>
				<div class="card">
					<div class="card-body">
						<table
							class="table table-sm table-bordered table-hover table-striped"
							id="todaysbillstable">
							<thead>
								<tr>
									<th>Time</th>
									<th>Bill No</th>
									<th>Bill Type</th>
									<th>Customer Name</th>
									<th>Bill Amount</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${recentbillList}" var="bills">
									<tr>
										<td style="color:red;margin-left:40px;">${bills.timestamp}</td>
										<td><a
											href="${fullbilldetails}?billno=${bills.bill_barcode_no}">${bills.bill_barcode_no}</a></td>
										<td>${bills.billrange_type}</td>
										<td><a
											href="${checkCustomerDetails}?customerid=${bills.customer.customerPKID}">${bills.customer.fname}&nbsp;${bills.customer.mname}&nbsp;${bills.customer.lname}</a></td>
										<td>&#8377; ${bills.bill_price}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-success" role="alert">
						<strong>No Recent Transaction</strong>
					</div>
				</c:otherwise>
			</c:choose>


		</div>
		<div class="col-md-12 col-md-6 mb-4">
		</div>
	</div>
</div>

<div class="container">
	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>
</div>
<br>
<hr>
<h5>Quick Links</h5>
<br>
<div class="row">
	<div class="col-xl-3 col-md-6 mb-4">
		<div class="card border-left-primary shadow h-100 py-2">
			<div class="card-body">
				<div class="row no-gutters align-items-center">
					<div class="col mr-2">
						<div
							class="text-xs font-weight-bold text-primary text-uppercase mb-1">
							<a href="${customer}"> Total Customers</a>
						</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">${customerCount}</div>
					</div>
					<div class="col-auto">
						<img
							src="https://img.icons8.com/cute-clipart/48/000000/gender-neutral-user.png" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Sales This month -->
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<div class="col-xl-3 col-md-6 mb-4">
			<div class="card border-left-success shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-success text-uppercase mb-1">
								<a href="${stock}" class="text-success">Active Sales stock
									Amount</a>
							</div>

							<c:set var="salestotal" value="${0}" />
							<c:forEach var="article" items="${stockList}">
								<c:if
									test="${article.stockType == 'SALES' and article.stockExitDate == 'null'}">
									<c:set var="salestotal"
										value="${salestotal + article.stockPrice}" />
								</c:if>
							</c:forEach>
							<div class="h5 mb-0 font-weight-bold text-gray-800">&#8377;
								${salestotal}</div>
						</div>
						<div class="col-auto">
							<img
								src="https://img.icons8.com/color/48/000000/total-sales-1.png" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</sec:authorize>

	<!-- Mortgages This Month -->
	<div class="col-xl-3 col-md-6 mb-4">
		<div class="card border-left-success shadow h-100 py-2">
			<div class="card-body">
				<div class="row no-gutters align-items-center">
					<div class="col mr-2">
						<div
							class="text-xs font-weight-bold text-success text-uppercase mb-1">
							<a href="${stock}" class="text-info">Active Mortgaged Amount</a>
						</div>
						<c:set var="mortgagetotal" value="${0}" />
						<c:forEach var="article" items="${stockList}">
							<c:if
								test="${article.stockType == 'MORTGAGE' and article.stockExitDate == 'null'}">
								<c:set var="mortgagetotal"
									value="${mortgagetotal + article.stockPrice}" />
							</c:if>
						</c:forEach>
						<div class="h5 mb-0 font-weight-bold text-gray-800">&#8377;
							${mortgagetotal}</div>
					</div>
					<div class="col-auto">
						<img src="https://img.icons8.com/dusk/48/000000/sell-property.png" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Pending Requests Card Example -->
	<div class="col-xl-3 col-md-6 mb-4">
		<div class="card border-left-warning shadow h-100 py-2">
			<div class="card-body">
				<div class="row no-gutters align-items-center">
					<div class="col mr-2">
						<div
							class="text-xs font-weight-bold text-warning text-uppercase mb-1">
							Saved Sales Orders</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
					</div>
					<div class="col-auto">
						<img
							src="https://img.icons8.com/flat-round/48/000000/check-file.png" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-xl-6 col-md-6 mb-4">
		<div class="card border-left-primary shadow h-100 py-2">
			<div class="card-body">
				<div class="row no-gutters align-items-center">
					<div class="col mr-2">
						<div class="text-xs font-weight-bold text-uppercase mb-1">
							<a href="${billing}" class="text-danger"> Bills</a>
						</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">${billList.size()}</div>
					</div>
					<div class="col mr-2">
						<div
							class="text-xs font-weight-bold text-success text-uppercase mb-1">
							Mortgage Bills</div>
						<c:set var="mortgagebills" value="${0}" />
						<c:forEach var="bill" items="${billList}">
							<c:if
								test="${bill.billrange_type != 'GHOSH_BROTHERS_JEWELLERS_SALES'}">
								<c:set var="mortgagebills" value="${mortgagebills + 1}" />
							</c:if>
						</c:forEach>
						<div class="h5 mb-0 font-weight-bold text-gray-800">${mortgagebills}</div>
					</div>
					<div class="col mr-2">
						<div
							class="text-xs font-weight-bold text-success text-uppercase mb-1">
							Sales Bills</div>
						<div class="h5 mb-0 font-weight-bold text-gray-800">${billList.size()-mortgagebills}</div>
					</div>
					<div class="col-auto">
						<img
							src="https://img.icons8.com/color/48/000000/purchase-order.png" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xl-6 col-md-6 mb-4">
		<div class="card border-left-primary shadow h-100 py-2">
			<div class="card-body">
				<div class="row no-gutters align-items-center">
					<div class="col mr-2">
						<div
							class="text-xs text-danger font-weight-bold text-uppercase mb-1">
							Generate Bill</div>
						<div class="input-group mb-3">
							<input type="text" class="form-control form-control-sm"
								placeholder="Enter Bill No" aria-label="Recipient's username"
								aria-describedby="basic-addon2">
							<div class="input-group-append">
								<button class="btn btn-success btn-sm" type="button">Generate</button>
							</div>
						</div>
					</div>
				
					<div class="col-auto">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>	
	</tiles:putAttribute>
</tiles:insertDefinition>
</body>
</html>