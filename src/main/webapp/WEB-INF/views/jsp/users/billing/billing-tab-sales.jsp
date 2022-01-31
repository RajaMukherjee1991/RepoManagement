<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Sales</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="billingSubMenu2"/>
	<tiles:putAttribute name="content">

<spring:url value="/billing" var="billing" />
<spring:url value="/billing/saveSalesDetails" var="saveSalesDetails" />
<spring:url value="/image/barcode" var="barcode" />
<spring:url value="/image/fetchStockPhoto" var="stockPhoto" />

	<br>
	<div class="col-md-12">
		<h4>
			<img src="https://img.icons8.com/color/40/000000/sell-stock.png" />
			Sales billing
		</h4>
		<hr>
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${billing}">All bills</a></li>
				<li class="breadcrumb-item active" aria-current="page"><a
					href="${toSalesBilling}">Add Sales bill</a></li>
			</ol>
		</nav>
	</div>
	<form:form name="salesbill_form_name" id="salesbill-form"
		enctype="multipart/form-data" method="post"
		modelAttribute="salesBillVO">


		<div>
			<div id="alert_msg"></div>
			<div class="col-md-12">
				<div class="alert alert-info">
					<img src="https://img.icons8.com/ios/30/000000/info--v1.png" />
					This will create the <strong>Sales</strong> bill.
				</div>
			</div>
			<div class="col-md-12">
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

			<div class="col-md-12">
				<div class="row">
					<div class="col-md-6">
						<h6>
							<strong>Customer Details</strong>
						</h6>
						<hr>
						<table class="table table-sm table-bordered">
							<tr>
								<th class=" font-weight-bold">Date of Purchase</th>
								<td class=" font-weight-bold" colspan=1><spring:bind
										path="salesBillDate">
										<form:input class="form-control form-control-sm" type="date"
											path="salesBillDate" />
										<form:errors path="salesBillDate" cssStyle="color: #ff0000;"
											class="control-label" />
									</spring:bind></td>
							</tr>
							<tr>
								<th class=" font-weight-bold">Name</th>
								<td class=" font-weight-bold"><spring:bind
										path="customerDetails.fullname">
										<form:input class="form-control form-control-sm" type="text"
											disabled="true" path="customerDetails.fullname" />
									</spring:bind></td>
							</tr>
							<tr>
								<th class=" font-weight-bold">Address 1</th>
								<td class=" font-weight-bold" colspan="1"><spring:bind
										path="customerDetails.address1">
										<form:input class="form-control form-control-sm" type="text"
											disabled="true" path="customerDetails.address1" />
									</spring:bind></td>
							</tr>
							<tr>
								<th class=" font-weight-bold">Address 2</th>
								<td class=" font-weight-bold" colspan="1"><spring:bind
										path="customerDetails.address2">
										<form:input class="form-control form-control-sm" type="text"
											disabled="true" path="customerDetails.address2" />
									</spring:bind></td>
							</tr>
							<tr>
								<th class=" font-weight-bold">Pincode</th>
								<td class=" font-weight-bold" colspan="1"><spring:bind
										path="customerDetails.pincode">
										<form:input class="form-control form-control-sm" type="text"
											disabled="true" path="customerDetails.pincode" />
									</spring:bind></td>
							</tr>
							<tr>
								<th class=" font-weight-bold">Mobile Number</th>
								<td class=" font-weight-bold" colspan="1"><spring:bind
										path="customerDetails.primarymobilenumber">
										<form:input class="form-control form-control-sm" type="text"
											disabled="true" path="customerDetails.primarymobilenumber" />
									</spring:bind></td>
							</tr>
						</table>
					</div>

					<div class="col-md-6">
						<h6>
							<strong>Selling Employee Details</strong>
						</h6>
						<hr>
						<table class="table table-sm table-bordered">
							<tr>
								<td colspan="2">Name</td>
								<td>Mobile</td>
							</tr>
							<tr style="background-color: #F9CD03;">
								<td>${salesBillVO.sellingEmployee.firstname}</td>
								<td>${salesBillVO.sellingEmployee.lastname}</td>
								<td>${salesBillVO.sellingEmployee.mobilenumber}</td>
							</tr>
						</table>
						<table class="table table-sm table-bordered">
							<tr>
								<td>Barcode</td>
								<td colspan="2" align="right"><img
									src="${barcode}/${salesBillVO.salesBillBarcode}" width="400"
									height="40">${salesBillVO.salesBillBarcode}</td>

							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<button type="button" class="btn btn-sm gbj-btn" data-toggle="modal"
					data-target="#checkAvailableStockModal">Check available
					Stock</button>
			</div>
			<br>
			<div class="col-md-12">
				<h6>
					<strong>Items</strong>
				</h6>
				<hr>
				<table class="table table-sm table-bordered" id="sellingStockTable">
				
						<tr class="align-middle">
							<th>Sr. No</th>
							<th>Date of Purchase</th>
							<th>Item Name</th>
							<th>Quantity</th>
							<th>Original Price</th>
							<th>Final Amount</th>
							<th>Delete</th>
						</tr>
				
					<tbody id="sellingtbody">
						<c:forEach items="${salesBillVO.salesStockList}"
							var="salesStockList" varStatus="index">

							<jsp:include page="billing-tab-sales-selling-list.jsp">
								<jsp:param name="index" value="${index.index}" />
								<jsp:param name="stockEntryDate"
									value="${salesStockList.stockEntryDate}" />
								<jsp:param name="barcode" value="${salesStockList.barcode}" />
								<jsp:param name="quantity" value="${salesStockList.quantity}" />
								<jsp:param name="stockPrice"
									value="${salesStockList.stockPrice}" />
								<jsp:param name="sellingPrice"
									value="${salesStockList.sellingPrice}" />
							</jsp:include>
						</c:forEach>
					</tbody>
				</table>

				<p>
					<button class="btn btn-sm gbj-btn" name="submitSalesOrder"
						id="generateSalesBillSummary">Check and pay</button>
					<button class="btn btn-sm btn-warning" name="saveDraftOrder"
						id="saveAsDraft">Save as draft</button>
				</p>
			</div>
		</div>
	</form:form>

	<!--CheckAvailableStockModal Modal -->
	<div class="modal" id="checkAvailableStockModal" tabindex="-1"
		aria-labelledby="availableStockLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl modal-dialog-centered"
			role="document">
			<div class="modal-content">
				<div class="modal-header shadow-sm">
					<h5 class="modal-title" id="availableStockLabel">Available
						Stock</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<table class="table table-sm table-bordered"
						id="availableStockTable">
						<thead>
							<tr class="bg-warning">
								<th class="align-middle">Name</th>
								<th class="align-middle">Entry Date</th>
								<th class="align-middle">Original Price</th>
								<th class="align-middle">Quantity</th>
								<th class="align-middle">Barcode</th>
								<th class="align-middle">Select</th>
							</tr>
						</thead>
						<tbody id="availableStockList">
							<c:forEach items="${allAvailableSalesStock}" var="stockList"
								varStatus="index">

								<tr>
									<td class="text-break align-middle" align="center"><c:out
											value="${stockList.stock_desc}" /></td>
									<td class="text-break align-middle" align="center"><c:out
											value="${stockList.stockEntryDate}" /></td>
									<td class="text-break align-middle" align="center"><c:out
											value="${stockList.stockPrice}" /></td>
									<td class="text-break align-middle" align="center"><c:out
											value="${stockList.quantity}" /></td>
									<td class="text-break align-middle" align="center"><div class="dropright">
											<a class="btn btn-link dropdown-toggle text-left text-small"
												data-toggle="dropdown" aria-haspopup="true"
												aria-expanded="false"> <small><c:out
														value="${stockList.barcode}" /></small>
											</a>
											<div class="dropdown-menu">
												<a class="dropdown-item"><img
													src="data:image/jpg;base64,${stockList.base64stockimage}"
													width="400" height="400" /></a>
											</div>
										</div></td>
									<td class="text-break align-middle" align="center"><button
											class="btn btn-sm btn-info"
											onclick="finalizePricingDetails('${stockList.barcode}',this);">
											Add</button></td>
								</tr>

							</c:forEach>
						</tbody>

					</table>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm gbj-btn"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
<div class="col-md-1">
<button class="btn btn-sm btn-danger" onclick="cancelTransaction('Sales')">Cancel</button>
</div>
	
</tiles:putAttribute>
</tiles:insertDefinition>