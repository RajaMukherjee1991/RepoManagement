<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Billing</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="billingSubMenu1"/>
	<tiles:putAttribute name="content">
<spring:url value="/billing/fullBillDetails" var="getfullbilldetails" />
<spring:url value="/customer/checkDetails" var="checkCustomerDetails" />
<spring:url value="/resources/images/undraw_Receipt_re_fre3.svg"
	var="billingImage" />
<c:set var="mortgagebillAmount" value="${0}" />
<c:set var="salesbillAmount" value="${0}" />
<c:forEach var="bill" items="${billingList}">
	<c:if test="${bill.billrange_type != 'GHOSH_BROTHERS_JEWELLERS_SALES'}">
		<c:set var="mortgagebillAmount"
			value="${mortgagebillAmount + bill.bill_price }" />
	</c:if>
	<c:if test="${bill.billrange_type == 'GHOSH_BROTHERS_JEWELLERS_SALES'}">
		<c:set var="salesbillAmount"
			value="${salesbillAmount + bill.bill_price }" />
	</c:if>
</c:forEach>
<div class="row">
	<div class="col-md-4">
		<h3>Billing</h3>
		<img src="${billingImage}" height="200" width="700" />
	</div>
	<div class="col-md-8">
		<div class="col-xl-12 col-md-6 mb-4">
			<div class="card border-left-primary shadow h-20 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-primary text-uppercase mb-1">
								Sales Billed Amount</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">&#8377;
								${salesbillAmount}</div>
						</div>
						<div class="col-auto">
							<a href="${printBill}/${customerBills.key.bill_barcode_no}"
								class="btn btn-sm gbj-btn"><i class='bx bx-printer' ></i> Print Report</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Earnings (Monthly) Card Example -->
		<div class="col-xl-12 col-md-6 mb-4">
			<div class="card border-left-success shadow h-20 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-success text-uppercase mb-1">
								Mortgage Billed Amount</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">&#8377;
								${mortgagebillAmount}</div>
						</div>
						<div class="col mr-2">
							<div
								class="text-xs font-weight-bold text-info text-uppercase mb-1">
								Mortgage Released Amount</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">&#8377;
								0</div>
						</div>
						<div class="col-auto">
							<a href="${printBill}/${customerBills.key.bill_barcode_no}"
								class="btn btn-sm gbj-btn"><i class='bx bx-printer' ></i> Print Report</a>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>
<hr>
<br>
<div class="row">
	<div class="col-md-12">
		<div class="card w-auto">
			<div class="card-body w-auto">
				<table
					class="table table-sm table-bordered table-hover table-striped"
					id="billstable">
					<thead>
						<tr>
							<th>Bill No</th>
							<th>Bill Date</th>
							<th>Customer Name</th>
							<th>Customer Phone</th>
							<th>Bill Amount</th>
							<th>Sold By</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${billingList}" var="bills">
							<tr>
								<td><a
									href="<c:out value="${getfullbilldetails}?billno=${bills.bill_barcode_no}"/>">${bills.bill_barcode_no}</a></td>
								<td>${bills.bill_date}</td>
								<td><a
									href="<c:out value="${checkCustomerDetails}?customerid=${bills.customer.customerPKID}"/>">${bills.customer.fname}&nbsp;${bills.customer.mname}&nbsp;${bills.customer.lname}</a></td>
								<td>${bills.customer.primarymobilenumber}</td>
								<td>&#8377; ${bills.bill_price}</td>
								<td>${bills.employee.firstname}&nbsp;${bills.employee.lastname}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<%-- <ul class="list-group list-group-flush " >
										<li class="list-group-item list-group-item-info">
										<div class="row">
											<div class="col-sm text-black">Bill No</div>
											<div class="col-sm text-black">Bill Date</div>
											<div class="col-sm text-black">Customer Name</div>
											<div class="col-sm text-black">Capital Amount</div>
											<div class="col-sm text-black">Customer Phone</div>
											<div class="col-sm text-black">Bill Amount</div>
											<div class="col-sm text-black">Sold By</div>
										</div>
										</li>
									</ul>
				<c:forEach items="${billingList}" var="bills">
										<li class="list-group-item py-1"><div class="row">
												<div class="col-sm"><a
									href="<c:out value="${getfullbilldetails}?billno=${bills.bill_barcode_no}"/>">${bills.bill_barcode_no}</a></div>
												<div class="col-sm">${bills.bill_date}</div>
												<div class="col-sm"><a
									href="<c:out value="${checkCustomerDetails}?customerid=${bills.customer.customerPKID}"/>">${bills.customer.fname}&nbsp;${bills.customer.mname}&nbsp;${bills.customer.lname}</a></div>
												<div class="col-sm">${bills.customer.primarymobilenumber}</div>
												<div class="col-sm">&#8377; ${bills.bill_price}</div>
												<div class="col-sm">${bills.employee.firstname}&nbsp;${bills.employee.lastname}</div>
											</div></li>
									</c:forEach> --%>					
			</div>
		</div>
	</div>
</div>
<br>
<button class="btn btn-sm gbj-btn" onclick="history.back()"> Go Back</button>
</tiles:putAttribute>
</tiles:insertDefinition>