<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Add Stock</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="stockSubMenu1" />
	<tiles:putAttribute name="content">

		<spring:url value="/image/barcode" var="barcode" />
		<spring:url value="/stock/saveStock" var="saveStock" />
		<spring:url value="/resources/images/undraw_Invest_re_8jl5.svg"
			var="stockadd" />
		<spring:url value="/resources/images/undraw_Images_re_0kll.svg"
			var="addimage" />

		<h3>Add Stock</h3>
		<br>
		<form:form id="stock-form" enctype="multipart/form-data" method="post"
			action="${saveStock}" modelAttribute="stockVO">
			<div id="accordion">
				<div class="card">
					<div class="card-header" id="stockAdderrheading">
						<a class="btn btn-link" data-toggle="collapse"
							href="#stock_details" role="button" aria-expanded="true"
							aria-controls="stock_details">Stock Details</a> <label><spring:bind
								path="stock.barcode">
								<form:input class="form-control form-control-sm" type="text"
									path="stock.barcode" />
							</spring:bind></label>
					</div>
					<div class="collapse show multi-collapse" id="stock_details">
						<div class="card-body">
							<c:if test="${not empty msg}">
								<div class="alert alert-success" role="alert">${msg}</div>
								<br>
							</c:if>
							<div class="form-group row">
								<label for="colFormLabelSm"
									class="col-sm-2 col-form-label col-form-label-sm">Stock
									Type</label>
								<div class="col-sm-3">
									<spring:bind path="stock.stockType">
										<form:select class="custom-select custom-select-sm"
											path="stock.stockType">
											<form:options />
										</form:select>
									</spring:bind>
								</div>
								<label for="colFormLabelSm"
									class="col-sm-2 col-form-label col-form-label-sm text-right">Per
									Piece price</label>
								<div class="col-sm-3">
									<input type="text" id="perpiece"
										class="form-control form-control-sm" />
								</div>
							</div>
							<div class="form-group row">
								<label for="colFormLabelSm"
									class="col-sm-2 col-form-label col-form-label-sm">Stock
									Barcode</label>
								<div class="col-sm-3">
									<img src="${barcode}/${stockVO.stock.barcode}" width="300"
										height="35">
								</div>

								<label for="colFormLabelSm"
									class="col-sm-2 col-form-label col-form-label-sm text-right">Quantity</label>
								<div class="col-sm-3">
									<spring:bind path="stock.quantity">
										<form:input type="text" path="stock.quantity"
											class="form-control form-control-sm" value="1" min="1"
											max="10" />
										<form:errors path="stock.quantity" cssStyle="color: #ff0000;"
											class="control-label" />
									</spring:bind>
								</div>
							</div>
							<div class="form-group row">
								<label for="colFormLabelSm"
									class="col-sm-2 col-form-label col-form-label-sm">Entry
									Date</label>
								<div class="col-sm-3">
									<spring:bind path="stock.stockEntryDate">
										<form:input class="form-control form-control-sm" type="date"
											path="stock.stockEntryDate" />
										<form:errors path="stock.stockEntryDate"
											cssStyle="color: #ff0000;" class="control-label" />
									</spring:bind>
								</div>
								<label for="colFormLabelSm"
									class="col-sm-2 col-form-label col-form-label-sm text-right">Total
									Price ( in <span>&#8377;</span>)
								</label>
								<div class="col-sm-3">
									<spring:bind path="stock.stockPrice">
										<form:input type="text" path="stock.stockPrice"
											class="form-control form-control-sm" />
										<form:errors path="stock.stockPrice"
											cssStyle="color: #ff0000;" class="control-label" />
									</spring:bind>
								</div>
							</div>
							<div class="form-group row">
								<label for="colFormLabelSm"
									class="col-sm-2 col-form-label col-form-label-sm">Stock
									Description</label>
								<div class="col-sm-3">
									<spring:bind path="stock.stock_desc">
										<form:input class="form-control form-control-sm" type="text"
											path="stock.stock_desc" />
										<form:errors path="stock.stock_desc"
											cssStyle="color: #ff0000;" class="control-label" />
									</spring:bind>
								</div>
								<label for="colFormLabelSm"
									class="col-sm-2 col-form-label col-form-label-sm text-right">Weight
									Details in gms</label>
								<div class="col-sm-1">
									<spring:bind path="stock.stockGrossWeight">
										<form:input type="text" path="stock.stockGrossWeight"
											class="form-control form-control-sm"
											placeholder="Gross Weight" />
										<form:errors path="stock.stockGrossWeight"
											cssStyle="color: #ff0000;" class="control-label" />
									</spring:bind>
								</div>
								<div class="col-sm-1">
									<spring:bind path="stock.stockDeductions">
										<form:input type="text" path="stock.stockDeductions"
											class="form-control form-control-sm" placeholder="Deductions" />
										<form:errors path="stock.stockDeductions"
											cssStyle="color: #ff0000;" class="control-label" />
									</spring:bind>
								</div>
								<div class="col-sm-1">
									<spring:bind path="stock.stockWeight">
										<form:input type="text" path="stock.stockWeight"
											class="form-control form-control-sm" placeholder="Net Weight" />
										<form:errors path="stock.stockWeight"
											cssStyle="color: #ff0000;" class="control-label" />
									</spring:bind>
								</div>
							</div>
							<div class="form-group row">
								<!-- <label for="colFormLabelSm"
					class="col-sm-2 col-form-label col-form-label-sm">Image</label> -->
								<div class="col-sm-2">
									<button type="button" class="btn btn-sm gbj-btn"
										data-toggle="modal" data-target="#addStockImage">Add
										Image</button>
								</div>
								<div class="col-sm-2 col-form-label col-form-label-sm"
									id="imagedata"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br>
			<div>
				<button type="submit" class="btn btn-sm gbj-btn" id="saveStockBtn">Save</button>
				<button type="button" class="btn btn-sm gbj-btn" id="goback"
					onclick="history.back()">Back</button>
			</div>

			<div class="modal fade" id="addStockImage" tabindex="-1"
				aria-labelledby="addStockImage" aria-hidden="true">
				<div class="modal-dialog modal modal-dialog-centered"
					role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="addStockImage">Image</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body" id="image">
							<table class="table table-sm table-bordered">
								<tbody>
									<tr>
										<th colspan="2">Add Item image</th>
										<td colspan="5"><div class="col-md-13">
												<div class="custom-file">
													<spring:bind path="stockImage">
														<form:input type="file" class="custom-file-input"
															onchange="readURL(this);" path="stockImage" />
													</spring:bind>
													<label class="custom-file-label" for="customFile">Choose
														file</label>
												</div>
											</div></td>
									</tr>
								</tbody>
							</table>
							<table class="table table-sm table-bordered">
								<tbody>
									<tr>
										<div class="alert alert-danger" role="alert">
											<strong>Important</strong> Please do not add images which
											exceeds 200KB !!!
										</div>
									</tr>
									<tr>
										<td style="text-align: center"><img id="blah"
											src="${addimage}" height="200" width="100" alt="your image" /></td>
									</tr>
								</tbody>
							</table>
							<div class="modal-footer">
								<button type="button" class="btn gbj-btn btn-sm "
									onclick="addImageToForm();">Save changes</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>