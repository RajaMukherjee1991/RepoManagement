<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:url value="/print/bill" var="printBill" />
<spring:url value="/image/barcode" var="barcode" />

<h3>
	Billed Transactions
</h3>
<hr>
<div style="width: 1500px; overflow-y: auto; overflow-x: hidden;">
	<div class="row">
		<div class="col-md-11">
			<c:forEach items="${customersBilledstockMap}" var="customerBills"
				varStatus="index">
				<nav>
					<div class="nav nav-tabs card-header-tabs" id="nav-tab"
						role="tablist">
						<a class="nav-item nav-link active" id="nav-home-tab"
							data-toggle="tab"
							href="#nav-home_${customerBills.key.bill_barcode_no}" role="tab"
							aria-controls="nav-home" aria-selected="true"><strong>Bill</strong></a>
					</div>
				</nav>
				<br>
				<div class="tab-content" id="nav-tabContent">
					<br>
					<div class="tab-pane fade show active"
						id="nav-home_${customerBills.key.bill_barcode_no}" role="tabpanel"
						aria-labelledby="nav-home-tab">
						<div class="card shadow-sm">
							<div class="card-body">
								<div class="row">
									<div class="col-md-6">
										<ul class="list-group list-group-flush" >
											<li class="list-group-item d-flex justify-content-between align-items-center p-1"><h6 style="color: #3C2A8A;">${customerBills.key.billrange_type}</h6></li>
											<li class="list-group-item d-flex justify-content-between align-items-center p-1"><h6 class="text-secondary text-small">Bill Number </h6><span>${customerBills.key.bill_barcode_no}</span></li>
											<li class="list-group-item d-flex justify-content-between align-items-center p-1"><h6 class="text-secondary text-small">Bill Date </h6><span>${customerBills.key.bill_date}</span></li>
											<li class="list-group-item d-flex justify-content-between align-items-center p-1"><h6 class="text-secondary text-small">Bill Price </h6><span>${customerBills.key.bill_price}</span></li>
										</ul>
									</div>
									<div class="col-md-1">
										<div class="vertical_bold_line"></div>
									</div>
									<div class="col-md-3">
										<ul class="list-group list-group-flush" >
											<li class="list-group-item d-flex justify-content-between align-items-center p-1"><h6 class="text-danger">Customer / Buyer Details</h6></li>
											<li class="list-group-item d-flex justify-content-between align-items-center p-1"><h6 class="text-secondary text-small">Name </h6><span>${customerBills.key.customer.fullname}</span></li>
											<li class="list-group-item d-flex justify-content-between align-items-center p-1"><h6 class="text-secondary text-small">Address </h6><span>${customerBills.key.customer.address1}<br>${customerBills.key.customer.address2}</span></li>
											<li class="list-group-item d-flex justify-content-between align-items-center p-1"><h6 class="text-secondary text-small">Phone </h6><span>${customerBills.key.customer.primarymobilenumber}</span></li>
										</ul>
									</div>
									<div class="col-md-2">
									<img id="photo" name="photo" class="card-img-top border border-dark" style="padding: 10px; border-radius: 3px;" src="data:image/jpg;base64,${customerBills.key.customerimage}" height="178" width="220">
									</div>
								</div>
								
								
								<hr>
								<ul class="list-group list-group-flush">
									<li class="list-group-item list-group-item-warning"><div
											class="row">
											<div class="col-sm text-black">Stock No</div>
											<div class="col-sm text-black">Original Price</div>
											<div class="col-sm text-black">Entry Date</div>
											<div class="col-sm text-black">Stock Weight</div>
											<div class="col-sm text-black">Stock Type</div>
										</div></li>
								</ul>
									<!-- <ul class="list-group list-group-flush"> -->
									<c:forEach items="${customerBills.value}" var="billStock"
									varStatus="index">
										<li class="list-group-item py-2"><div class="row">
												<div class="col-sm">${billStock.barcode}</div>
												<div class="col-sm">${billStock.stockPrice}</div>
												<div class="col-sm">${billStock.stockEntryDate}</div>
												<div class="col-sm">${billStock.stockWeight}</div>
												<div class="col-sm">${billStock.stockType}</div>
											</div></li>
									</c:forEach>		
									<!-- </ul> -->
								
								<br>
								<table class="table table-sm table-borderless">
									<tr>
										<td><a
											href="${printBill}/${customerBills.key.bill_barcode_no}"
											class="btn btn-sm btn-success"><i class='bx bx-printer' ></i> Print Bill</a> <a href="#"
											class="btn btn-sm btn-outline-danger"><i class='bx bx-trash'></i> Delete Bill</a></td>

										<c:if
											test="${customerBills.key.billrange_type != 'GHOSH_BROTHERS_JEWELLERS_SALES'}">
											<td align="right"><button class="btn btn-sm gbj-btn"
													onclick='showInterestPaymentModalWindow("${customerBills.key.bill_barcode_no}");'>Add
													New Interest Payment</button> &nbsp; <a href="#"
												class="btn btn-sm btn-danger"><i class='bx bx-trash'></i> Release Mortgage Bill</a></td>
										</c:if>
									</tr>
								</table>
							</div>
						</div>
						<br>
					</div>
				</div>
				<br>
				<div class="card">
					<div class="card-header">
						<h6>
							<strong>Monthly Interest and Capital payment Receipt</strong>
						</h6>
					</div>
					<div class="card-body">
						<c:choose>
							<c:when
								test="${customerBills.key.billrange_type != 'GHOSH_BROTHERS_JEWELLERS_SALES'}">

								<ul class="list-group list-group-flush ">
										<li class="list-group-item list-group-item-info">
										<div class="row">
											<div class="col-sm text-black">Sr No</div>
											<div class="col-sm text-black">Date</div>
											<div class="col-sm text-black">Interest Paid</div>
											<div class="col-sm text-black">Capital Amount</div>
											<div class="col-sm text-black">Amount Left</div>
											<div class="col-sm text-black">Delete</div>
										</div>
										</li>
									</ul>
								<c:forEach items="${interestpaid}" var="interestpaid"
									varStatus="index">
										<li class="list-group-item py-1"><div class="row">
												<div class="col-sm">${index.index+1}</div>
												<div class="col-sm">${interestpaid.payementdate}</div>
												<div class="col-sm">${interestpaid.interestAmount}</div>
												<div class="col-sm">${interestpaid.capitalAmount}</div>
												<div class="col-sm "><span class="badge badge-success">${interestpaid.amountLeft}</span></div>
												<div class="col-sm"><h6><i class='bx bx-trash'></i></h6></div>
											</div></li>
									</c:forEach>
							</c:when>
							<c:otherwise>
								<div class="alert alert-success" role="alert">No interest
									to be calculated</div>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<div class="modal fade" id="addnewInterestPaidModal" tabindex="-1"
	aria-labelledby="addnewInterestPaidLabel" aria-hidden="true">
	<div class="modal-dialog modal modal-dialog-centered"
		role="document">
		<div class="modal-content">
			<div class="modal-header shadow-sm">
				<h5 class="modal-title" id="interestPayments"><i class="fa fa-credit-card" style="font-size:24px"></i> Payment</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="interestpayment-block"></div>
		</div>
	</div>
</div>
