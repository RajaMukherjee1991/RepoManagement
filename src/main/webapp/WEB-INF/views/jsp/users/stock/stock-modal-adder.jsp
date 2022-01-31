<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal fade" id="stock_adder_modal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLongTitle">Add Sales
					Stock Adder</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<form:form id="stock-form" enctype="multipart/form-data"
					method="post" action="${saveStock}" modelAttribute="stockVO">

					<div class="row">
						<div class="col-md-5">
							<h3>Add Stock</h3>
							<img src="${stockadd}" height="200" width="700" />
						</div>
						<div class="col-md-7">
							<table class="table table-sm table-bordered">
								<tbody>
									<tr>
										<th colspan="2">Stock type</th>
									</tr>
									<tr>
										<td colspan="2"><spring:bind path="stock.stockType">
												<form:select class="custom-select custom-select-sm"
													path="stock.stockType">
													<form:options />
												</form:select>
											</spring:bind></td>
									</tr>
							</table>
							<table class="table table-sm table-bordered ">
								<tbody>
									<tr>
										<th colspan="2">Stock Description</th>
										<td colspan="6"><spring:bind path="stock.stock_desc">
												<form:input class="form-control form-control-sm" type="text"
													path="stock.stock_desc" />
												<form:errors path="stock.stock_desc"
													cssStyle="color: #ff0000;" class="control-label" />
											</spring:bind></td>

									</tr>
									<tr>
										<th colspan="2">Stock Barcode</th>
										<td colspan="2"><spring:bind path="stock.barcode">
												<form:input class="form-control form-control-sm" type="text"
													path="stock.barcode" />
											</spring:bind></td>
										<td colspan="4"><img
											src="${barcode}/${stockVO.stock.barcode}" width="400"
											height="35"></td>
									</tr>
									<tr>
										<th colspan="2">Jewellery Type</th>
										<td colspan="3"><form:select
												class="custom-select custom-select-sm"
												path="stock.stockType">
												<form:options />
											</form:select></td>
										<td colspan="3" align="right"><a href="#"
											class="btn btn-link btn-sm">Add new</a></td>
									</tr>
								</tbody>
							</table>
							<table class="table table-sm table-bordered">
								<tr>
									<th colspan="1">Entry Date</th>
								</tr>
								<tr>
									<td colspan="1"><spring:bind path="stock.stockEntryDate">
											<form:input class="form-control form-control-sm" type="date"
												path="stock.stockEntryDate" />
											<form:errors path="stock.stockEntryDate"
												cssStyle="color: #ff0000;" class="control-label" />
										</spring:bind></td>
								</tr>

								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-md-5"></div>
						<div class="col-md-7">
							<table class="table table-sm table-borderless">
								<tr>
									<td colspan="1"><button type="button"
											class="btn btn-sm gbj-btn" data-toggle="modal"
											data-target="#addStockImage">Add Image</button></td>
									<th colspan="4" id="imagedata" align="left"></th>
								</tr>
							</table>
							<hr>
							<table class="table table-sm table-bordered">
								<tbody>
									<tr>
										<th colspan="2">Price</th>
									</tr>
									<tr>
										<th colspan="1">Per Piece Price ( in <span>&#8377;</span>)
										</th>
										<td colspan="1"><input type="text" id="perpiece"
											class="form-control form-control-sm" /></td>
									</tr>
									<tr>
										<th colspan="1">Quantity</th>
										<td colspan="1"><spring:bind path="stock.quantity">
												<form:input type="text" path="stock.quantity"
													class="form-control form-control-sm" value="1" min="1"
													max="10" />
												<form:errors path="stock.quantity"
													cssStyle="color: #ff0000;" class="control-label" />
											</spring:bind></td>
									</tr>
									<tr>
										<th colspan="1">Total Price ( in <span>&#8377;</span>)
										</th>
										<td colspan="1"><spring:bind path="stock.stockPrice">
												<form:input type="text" path="stock.stockPrice"
													class="form-control form-control-sm" />
												<form:errors path="stock.stockPrice"
													cssStyle="color: #ff0000;" class="control-label" />
											</spring:bind></td>
									</tr>
								</tbody>
							</table>
							<table class="table table-sm table-bordered">
								<tbody>
									<tr>
										<th colspan="2">Weight</th>
									</tr>
									<tr>
										<th colspan="1">Gross Weight</th>
										<td colspan="1"><spring:bind
												path="stock.stockGrossWeight">
												<form:input type="text" path="stock.stockGrossWeight"
													class="form-control form-control-sm" />
												<form:errors path="stock.stockGrossWeight"
													cssStyle="color: #ff0000;" class="control-label" />
											</spring:bind></td>
									</tr>
									<tr>
										<th colspan="1">Deduction</th>
										<td colspan="1"><spring:bind path="stock.stockDeductions">
												<form:input type="text" path="stock.stockDeductions"
													class="form-control form-control-sm" />
												<form:errors path="stock.stockDeductions"
													cssStyle="color: #ff0000;" class="control-label" />
											</spring:bind></td>
									</tr>
									<tr>
										<th colspan="1">Net Weight</th>
										<td colspan="1"><spring:bind path="stock.stockWeight">
												<form:input type="text" path="stock.stockWeight"
													class="form-control form-control-sm" />
												<form:errors path="stock.stockWeight"
													cssStyle="color: #ff0000;" class="control-label" />
											</spring:bind></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<button type="submit" class="btn gbj-btn" data-toggle="modal"
						data-target="#stockNotificationModal">Save</button>

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
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>