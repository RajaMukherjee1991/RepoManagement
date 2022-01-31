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
<spring:url value="/billing/fullBillDetails" var="getfullbilldetails" />
<spring:url value="/customer" var="gobacktocustomer" />
<spring:url value="/print/bill" var="printBill" />


	<h3>
		Customer Details
	</h3>
	<hr>
	<table class="table table-sm table-bordered table-hover" style="width: 1500px; overflow-y: auto;background-color:white; overflow-x: hidden;">
		<tbody>
			<tr>

				<th colspan="7" class="h6" style="color: #09580F;"><strong><c:out
							value="${customerData.fname}" /> <c:out
							value="${customerData.mname}" /> <c:out
							value="${customerData.lname}" /></strong></th>
				<c:set var="customer_pkid" value="${customerData.customerPKID}" />
				<td colspan="1"><c:if test="${customerData.isVerified == 1}">
						<h5 class="badge badge-success">Verified Customer</h5>
					</c:if> <c:if test="${customerData.isVerified == 0}">
						<h5 class="badge badge-danger">Customer Not Verified</h5>
					</c:if></td>
			</tr>
			<tr>
				<th scope="row" class="text-muted">Mobile Number</th>
				<td colspan="7"><c:out
						value="${customerData.primarymobilenumber}" /></td>
			</tr>
			<tr>
				<th scope="row" class="text-muted">Email</th>
				<td colspan="7"><c:out value="${customerData.email}" /></td>
			</tr>
			<tr>
				<th scope="row" class="text-muted">Pancard</th>
				<td colspan="3"><c:out value="${customerData.pancard}" /></td>
				<th scope="row" class="text-muted">Aadhar Card</th>
				<td colspan="3"><c:out value="${customerData.aadharNo}" /></td>
			</tr>
			<tr>
				<th scope="row" class="text-muted">Address</th>
				<td colspan="7"><c:out value="${customerData.address1}" /> <c:out
						value="${customerData.address2}" /></td>
			</tr>
		</tbody>
	</table>
	<br>
	<table class="table table-sm table-borderless" style="width: 1500px; overflow-y: auto; overflow-x: hidden;">
	
	<tr>
		<td>
			<a href="#" class="btn btn-sm gbj-btn"><i class='bx bx-printer'></i> Print PDF </a>
			<a href="${savecustomer}/${customerData.customerPKID}" class="btn btn-sm gbj-btn"><i class='bx bx-edit-alt' ></i> Update</a>
		</td>
		<td align="right">
			<a href="${savecustomer}/delete/${customerData.customerPKID}" class="btn btn-sm btn-danger"><i class='bx bx-trash'></i> Delete</a>
			<a href="#" class="btn btn-sm btn-success"><i class='bx bx-add-to-queue' ></i> Add Bill</a>
		</td>
	</tr>
	
	</table>	
	<c:choose>
	<c:when test="${!empty customersBilledstockMap}">
	<br>
	<h3>Transactions</h3>
	<hr>
	
	<!-- <div class="shadow-sm" style="width: 1500px; overflow-y: auto; overflow-x: hidden;"> -->
		<ul class="list-group list-group-flush">
									<li class="list-group-item list-group-item-warning"><div
											class="row">
											<div class="col-sm text-black">Bill No</div>
											<div class="col-sm text-black">Bill Date</div>
											<div class="col-sm text-black">Bill Amount</div>
											<div class="col-sm text-black">Sold By</div>
											<div class="col-sm text-black">Print</div>
										</div></li>
								</ul>
	
			<!-- <ul class="list-group list-group-flush"> -->
			<c:forEach items="${customersBilledstockMap}" var="bill">
										<li class="list-group-item py-2"><div class="row">
												<div class="col-sm"><a href="${getfullbilldetails}?billno=${bill.key.bill_barcode_no}"><c:out value="${bill.key.bill_barcode_no}"/></a></div>
												<div class="col-sm">${bill.key.bill_date}</div>
												<div class="col-sm">&#8377; ${bill.key.bill_price}</div>
												<div class="col-sm">${bill.key.employee.firstname}&nbsp;${bill.key.employee.lastname}</div>
												<div class="col-sm"><a href="${printBill}/${bill.key.bill_barcode_no}"
											class="btn btn-sm btn-link"><i class='bx bx-printer'></i> Print Bill</a></div>
											</div></li>
			</c:forEach>								
									<!-- </ul> -->	
		
	<!-- </div> -->	
	</c:when>
	<c:otherwise>
		<div class="alert alert-success" role="alert">No Transaction</div>
	</c:otherwise>
	</c:choose>
	<br>
	<button class="btn btn-sm btn-success" onclick="history.back()">Go Back</button>
</tiles:putAttribute>
</tiles:insertDefinition>