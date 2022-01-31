<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Sales Stock</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="stockSubMenu1"/>
	<tiles:putAttribute name="content">
<spring:url value="/print/stock/list/M" var="printMortgagePDF" />
<spring:url value="/print/stock/list/S" var="printSalesPDF" />
<spring:url value="/stock/stockAdder" var="stockAdder" />
<spring:url value="/print/excel/sales/report" var="stockExcelReport"/>
<div class="row">
	<div class="col-md-8">
		<h3>Sales Stock</h3>
	</div>
	<div class="col-md-4">
		<%-- <div class="col-xl-12 col-md-6 mb-4">
			<div class="card border-left-success shadow-sm h-50 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col">
							<div
								class="text-xs font-weight-bold text-success text-uppercase mb-1">
								Active Sales Amount</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">&#8377;
								${mortgagebillAmount}</div>
						</div>
						<div class="col">
							<div
								class="text-xs font-weight-bold text-info mb-1">
								<a href="${stockAdder}" class="btn btn-info btn-sm gbj-btn">Add Stock</a></div>
						</div>
						<div class="col-auto">
							<a href="${printBill}/${customerBills.key.bill_barcode_no}"
								class="btn btn-sm gbj-btn"><svg
									xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-printer" viewBox="0 0 16 16"> <path
										d="M2.5 8a.5.5 0 1 0 0-1 .5.5 0 0 0 0 1z" /> <path
										d="M5 1a2 2 0 0 0-2 2v2H2a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h1v1a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2v-1h1a2 2 0 0 0 2-2V7a2 2 0 0 0-2-2h-1V3a2 2 0 0 0-2-2H5zM4 3a1 1 0 0 1 1-1h6a1 1 0 0 1 1 1v2H4V3zm1 5a2 2 0 0 0-2 2v1H2a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-1v-1a2 2 0 0 0-2-2H5zm7 2v3a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1v-3a1 1 0 0 1 1-1h6a1 1 0 0 1 1 1z" />
													</svg> Print Report</a>
						</div>
					</div>
				</div>
				
			</div>
		</div> --%>
		<div class="card" style="width: 30rem;">
			<ul class="list-group list-group-flush">
				<li class="list-group-item d-flex justify-content-between align-items-center p-2"><h6>Active Sales Amount</h6></li>
				<li class="list-group-item d-flex justify-content-between align-items-center p-2"><h6>Total Active Sales Item</h6></li>
				<li class="list-group-item d-flex justify-content-between align-items-center p-2"><h6>Print Excel Report</h6><span><a href="${stockExcelReport}" class="btn btn-info btn-sm gbj-btn">Sales Report</a></span></li>
				<li class="list-group-item d-flex justify-content-between align-items-center p-2"><h6>Add New Stock </h6><span><a href="${stockAdder}" class="btn btn-info btn-sm gbj-btn">Add Stock</a></span></li>
			</ul>
		</div>

	</div>
</div>
<hr>
<br>

<!-- <div class="row"> -->
<!-- 	<div class="col-md-6"> -->
<div class="card">
	<div class="card-header">
		<div class="row">
			<div class="col-md-11">
				<h6>Sales Stock</h6>
			</div>
			<div class="col-md-1 float-right">
				<div class="dropdown">
					<button type="button" class="btn btn-light btn-sm"
						data-toggle="dropdown">...</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" data-toggle="modal"
							onclick="resetStockSelection()" data-target="#storeStock">+
							Add Sales stock</a> <a class="dropdown-item" href="${printSalesPDF}"
							target="_blank"><img
							src="https://img.icons8.com/color/20/000000/pdf.png" /> Download
							PDF</a> <a class="dropdown-item" href="#"><img
							src="https://img.icons8.com/color/20/000000/ms-excel.png" />
							Download Excel</a>
					</div>
				</div>
			</div>
		</div>

	</div>
	<div class="card-body">
		<table class="table table-sm table-bordered table-hover table-striped"
			id="salesStockdatatable">
			<thead>
				<tr>
					<th>Stock ID</th>
					<th>Name</th>
					<th>Entry Date</th>
					<th>Price</th>
					<th>Barcode</th>
					<th>Quantity</th>
					<th>Status</th>
					<th>Edit</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${salesStockList}" var="stockList">
					<tr>

						<td><c:out value="${stockList.stockID}" /></td>
						<td><c:out value="${stockList.stock_desc}" /></td>
						<td><c:out value="${stockList.stockEntryDate}" /></td>
						<td>&#8377; <c:out value="${stockList.stockPrice}" /></td>
						<td><c:out value="${stockList.barcode}" /></td>
						<td><c:out value="${stockList.quantity}" /></td>
						<c:choose>
							<c:when test="${stockList.stockExitDate == 'null' }">
								<td class="text-success">Available</td>
							</c:when>
							<c:otherwise>
								<td class="text-danger">Not Available</td>
							</c:otherwise>
						</c:choose>

						<td><a href="#">See Details</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!-- </div> -->
<br>

<!-- 	<div class="col-md-6"> -->
<%-- <div class="card">
			<div class="card-header">
				<div class="row">
					<div class="col-md-11">
						<h6>Mortgage Stock</h6>
					</div>
					<div class="col-md-1 float-right">
						<div class="dropdown">
							<button type="button" class="btn btn-light btn-sm"
								data-toggle="dropdown">...</button>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="${printMortgagePDF}" target="_blank">Download PDF</a> <a
									class="dropdown-item" href="#">Download Excel</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body">
				<table class="table table-sm table-bordered table-hover table-striped"
					id="mortgageStockdatatable">
					<thead>
						<tr>
							<th>Stock ID</th>
							<th>Name</th>
							<th>Entry Date</th>
							<th>Price</th>
							<th>Barcode</th>
							<th>Quantity</th>
							<th>Status</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${mortgageStockList}" var="stockList">
							<tr>
								<td><c:out value="${stockList.stockID}" /></td>
								<td><c:out value="${stockList.stock_desc}" /></td>
								<td><c:out value="${stockList.stockEntryDate}" /></td>
								<td>&#8377; <c:out value="${stockList.stockPrice}" /></td>
								<td><c:out value="${stockList.barcode}" /></td>
								<td><c:out value="${stockList.quantity}" /></td>								<c:choose>
									<c:when test="${stockList.stockExitDate == 'null' }">
										<td  class="text-success">Available</td>
									</c:when>
									<c:otherwise>
										<td class="text-danger">Not Available</td>
									</c:otherwise>
								</c:choose>
								<td><a href="#">See Details</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
 --%>
<button class="btn btn-sm gbj-btn" onclick="history.back()"> Go Back</button>
</tiles:putAttribute>
</tiles:insertDefinition>