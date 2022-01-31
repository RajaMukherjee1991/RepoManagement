<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Mortgage</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="billingSubMenu2"/>
	<tiles:putAttribute name="content">
<spring:url value="/billing" var="billing" />
<spring:url value="/billing/saveMortgage" var="saveMortgage" />
<spring:url value="/image/barcode" var="barcode" />
<br>
<div class="col-md-12">
	<h4>Mortgage Billing</h4>
	<hr>
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="${billing}">All bills</a></li>
			<li class="breadcrumb-item active" aria-current="page"><a
				href="${toMortgageBilling}">Add Mortgage bill</a></li>
		</ol>
	</nav>
</div>
<form:form id="mortagage-form" enctype="multipart/form-data"
	method="post" action="${saveMortgage}" modelAttribute="mortgagebillVO">

	<spring:bind path="customerDetails.customerPKID">
		<form:input type="hidden" path="customerDetails.customerPKID" />
	</spring:bind>

	<div>
		<div class="col-md-12">
			<div class="card"
				style="background-color: <spring:message code="mortgage.bill.type.css.${mortgagebillVO.billRangeType.name}"></spring:message>;">
				<div class="card-body text-dark shadow-sm">
					<div class="row">
						<div class="col-md-8 ">
							<h5>
								<spring:message
									code="mortgage.bill.type.${mortgagebillVO.billRangeType.name}"></spring:message>
							</h5>
						</div>
						<div class="col-md-4">
							<h5>
							<!-- <svg id="barcode"></svg> -->
								<img src="${barcode}/${mortgagebillVO.mortgageBillBarcode}"
									width="400" height="40">
							</h5>
							<span>&nbsp;${mortgagebillVO.mortgageBillBarcode}</span>
						</div>
					</div>

				</div>
			</div>
		</div>
		<br>
	</div>
	<div class="col-md-12">
		<div class="alert alert-info shadow-sm">
			<img src="https://img.icons8.com/ios/30/000000/info--v1.png"/>&nbsp; This will create the
			<strong>Mortgage</strong> bill. You can download all the necessary
			invoice details in Bill Summary section.
		</div>
	</div>
	<div class="col-md-12">
		<div id="mortgage-alerts"></div>
	</div>
	<div class="col-md-12">
		<table class="table table-sm table-bordered">
			<tr>
				<th class=" font-weight-bold">Net Weight</th>
				<td class=" font-weight-bold"><spring:bind path="stock.stockWeight">
						<form:input class="form-control form-control-sm" type="text"
							path="stock.stockWeight" id="netweight1" />
						<form:errors path="stock.stockWeight" cssStyle="color: #ff0000;"
										class="control-label" />	
					</spring:bind></td>
				<th class=" font-weight-bold">Loan Amount</th>
				<td class=" font-weight-bold"><spring:bind path="stock.stockPrice">
						<form:input class="form-control form-control-sm" type="text"
							path="stock.stockPrice" id="loanamount1" />
						<form:errors path="stock.stockPrice" cssStyle="color: #ff0000;"
										class="control-label" />		
					</spring:bind></td>
			</tr>
		</table>
	</div>
	<div class="col-md-12">
		<div class="card">
			<div class=" font-weight-bold card-body text-dark shadow-sm">
				Statement showing details of conditions of the loan etc. Prescribed
				under Rule 19(2) of section 24(2)(a) of the <strong>Bengal
					Money Lenders Act, 1940</strong>.
			</div>
		</div>
	</div>
	<br>
	<div class="col-md-12">
		<table class="table table-sm table-bordered">
			<tr>
				<th class=" font-weight-bold">Date of Loan</th>
				<td class=" font-weight-bold"><spring:bind path="stock.stockEntryDate">
						<form:input class="form-control form-control-sm" type="date"
							path="stock.stockEntryDate" />
						<form:errors path="stock.stockEntryDate" cssStyle="color: #ff0000;"
										class="control-label" />		
					</spring:bind></td>
				<th class=" font-weight-bold">Phone</th>
				<td class=" font-weight-bold"><spring:bind path="customerDetails.primarymobilenumber">
						<form:input class="form-control form-control-sm" type="text"
							path="customerDetails.primarymobilenumber" disabled="true" />
					</spring:bind></td>
			</tr>
			<tr>
				<th class=" font-weight-bold">Name of Debtor</th>
				<td class=" font-weight-bold"><spring:bind path="customerDetails.fname">
						<form:input class="form-control form-control-sm" type="text" disabled="true"
							path="customerDetails.fname" />
					</spring:bind></td>
				<td class=" font-weight-bold"><spring:bind path="customerDetails.mname">
						<form:input class="form-control form-control-sm" type="text" disabled="true"
							path="customerDetails.mname" />
					</spring:bind></td>
				<td class=" font-weight-bold"><spring:bind path="customerDetails.lname">
						<form:input class="form-control form-control-sm" type="text" disabled="true"
							path="customerDetails.lname" />
					</spring:bind></td>
			</tr>
			<tr>
				<th class=" font-weight-bold">Address</th>
				<td class=" font-weight-bold" colspan="2"><spring:bind path="customerDetails.address1">
						<form:input class="form-control form-control-sm" type="text" disabled="true"
							path="customerDetails.address1" />
					</spring:bind></td>
				<td class=" font-weight-bold" colspan="1"><spring:bind path="customerDetails.address2">
						<form:input class="form-control form-control-sm" type="text" disabled="true"
							path="customerDetails.address2" />
					</spring:bind></td>
			</tr>
			<tr>
				<th class=" font-weight-bold">Landlord</th>
				<td class=" font-weight-bold" colspan="3"><spring:bind path="customerDetails.landlord">
						<form:input class="form-control form-control-sm" type="text"
							path="customerDetails.landlord" disabled="true" />
					</spring:bind></td>
			</tr>
			<tr>
				<th class=" font-weight-bold">Details of Gold Ornaments offered as collateral Security</th>
				<td class=" font-weight-bold" colspan="3"><spring:bind path="stock.stock_desc">
						<form:input class="form-control form-control-sm" type="text"
							path="stock.stock_desc" />
					</spring:bind></td>
			</tr>
			<tr>
				<th class=" font-weight-bold">Gross Weight</th>
				<td class=" font-weight-bold"><spring:bind path="stock.stockGrossWeight">
						<form:input class="form-control form-control-sm" type="text"
							path="stock.stockGrossWeight" id="stock.stockGrossWeight" />
					</spring:bind>
					<form:errors path="stock.stockGrossWeight" cssStyle="color: #ff0000;"
										class="control-label" /></td>
				<th class=" font-weight-bold">Deduction (Stone/Dirt) in gms</th>
				
				<td class=" font-weight-bold"><spring:bind path="stock.stockDeductions">
						<form:input class="form-control form-control-sm" type="text"
							path="stock.stockDeductions" id="stock.stockDeductions" />
						<form:errors path="stock.stockDeductions" cssStyle="color: #ff0000;"
										class="control-label" />	
					</spring:bind></td>
			</tr>
			<tr>
				<th class=" font-weight-bold">Net Weight</th>
				<td class=" font-weight-bold" colspan="3"><spring:bind path="stock.stockWeight">
						<form:input class="form-control form-control-sm" type="text"
							path="stock.stockWeight" id="netweight2" disabled="true" />
					</spring:bind></td>
			</tr>
			<tr>
				<th class=" font-weight-bold">Amount of loan Rs</th>
				<td class=" font-weight-bold amount" colspan="3"><spring:bind path="stock.stockPrice">
						<form:input class="form-control form-control-sm" type="text"
							path="stock.stockPrice" disabled="true" id="loanamount2" />
					</spring:bind></td>
			</tr>
		</table>
		<table class="table table-sm table-bordered">
			<tr>
				<th class=" font-weight-bold" colspan="2">Add Item image</th>
				<td class=" font-weight-bold" colspan="2">
					<div class="custom-file">
						<spring:bind path="mortgageStockImage"><form:input type="file" class="custom-file-input"
							path="mortgageStockImage" /></spring:bind> <label class="custom-file-label"
							for="customFile">Choose file</label>
					</div>
				</td>
			</tr>
			<%-- <tr>
				<th class=" font-weight-bold" colspan="2">Add Customer Image</th>
				<td class=" font-weight-bold" colspan="2">
					<div class="custom-file">
						<spring:bind path="mortgageCustomerImage"><form:input type="file" class="custom-file-input"
							path="mortgageStockImage" /></spring:bind> <label class="custom-file-label"
							for="customFile">Choose file</label>
					</div>
				</td>
			</tr> --%>
		</table>
	</div>
	
	<div class="col-md-12">
		<table>
			<tr>
				<td><p>
						<button id="submitMortgage" class="btn btn-sm gbj-btn">Submit Mortgage Bill</button>
					</p></td>
				<td id="next"></td>
			</tr>
		</table>
	</div>
</form:form>
<div class="col-md-1">
<button class="btn btn-sm btn-danger" onclick="cancelTransaction('Mortgage')">Cancel</button>
</div>
</tiles:putAttribute>
</tiles:insertDefinition>