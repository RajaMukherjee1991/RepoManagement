<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Billing Summary</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="billingSubMenu2" />
	<tiles:putAttribute name="content">

		<spring:url value="/billing" var="billing" />
		<spring:url value="/billing/saveMortgage" var="saveMortgage" />
		<spring:url value="/image/barcode" var="barcode" />
		<br>
		<div>
			<h4>Bill Summary</h4>
			<hr>
		</div>
		<br>
		<div>
			<div class="alert alert-info">
				<i class='bx bx-info-circle bx-sm'></i> &nbsp; You can download all
				the necessary <strong>invoices</strong> for <strong>Sales/Mortgage</strong>
				from this section.
			</div>
		</div>
		<form method="post" 
			id="bill_summary_form" name="bill_summary_form_name">
			<input type="hidden" id="image" name="image"/>
								
			<div class="receipt-content">
				<div class="bootstrap snippets bootdey" id='print'>
					<div class="row">
						<div class="col-md-12">
							<div class="invoice-wrapper shadow border border-dark">

								<div class="row">
									<div class="col-md-10">
										<div
											style="background-color: #F8EAE7; padding: 20px; border-radius: 3px; width: 1200px" class="border border-dark">
											<div class="intro">
												<br>
												<h4 class="text-danger">
													<spring:message
														code="mortgage.bill.type.${billSummaryVO.billDetails.billrange_type.name}"></spring:message>
												</h4>
											</div>

											<div class="payment-info">
												<div class="row">
													<div class="col-sm-6">
														<span>Payment No.</span> <strong>${billSummaryVO.billDetails.bill_barcode_no}</strong>
													</div>
													<div class="col-sm-6 text-right">
														<span>Payment Date</span> <strong>${billSummaryVO.billDetails.bill_date}</strong>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="contentarea">
											<div class="output">
											
											<!-- <input  id="photo" class="card-img-top border border-dark" style="padding: 10px; border-radius: 3px;" alt="..." height="178" width="220"> -->
												<img id="photo" name="photo"
													class="card-img-top border border-dark" style="padding: 10px; border-radius: 3px;" alt="..." height="178" width="220">
											</div>
										</div>
									</div>
								</div>
								<div class="payment-details">
									<div class="row">
										<div class="col-sm-6">
											<span>Seller Details</span> <strong> Ghosh Brother's
												Jewellers </strong>
											<p>
												8A, Ekbalpul Lane <br> Kolkata -700023 <br> <a
													href="#"> 9831392410 | 9804347895 </a>
											</p>
										</div>
										<div class="col-sm-6 text-right">
											<span>Payment To</span> <strong>
												${billSummaryVO.billDetails.customer.fname}
												${billSummaryVO.billDetails.customer.mname}
												${billSummaryVO.billDetails.customer.lname} </strong>
											<p>
												${billSummaryVO.billDetails.customer.address1} &nbsp;
												${billSummaryVO.billDetails.customer.address2}<br>
												${billSummaryVO.billDetails.customer.pincode}<br> <a
													href="#">
													${billSummaryVO.billDetails.customer.primarymobilenumber} </a>
											</p>
										</div>
									</div>
								</div>

								<div class="line-items">
									<div class="headers clearfix">
										<div class="row">
											<div class="col-md-4">Description</div>
											<div class="col-md-3">Quantity</div>
											<div class="col-md-5 text-right">Final Amount</div>
										</div>
									</div>
									<div class="items">
										<c:set var="totalPrice" value="${0}" />
										<c:set var="totalQuantity" value="${0}" />
										<c:forEach items="${billSummaryVO.itemList}" var="itemList">
											<c:choose>
												<c:when
													test="${billSummaryVO.billDetails.billrange_type.name != 'GBJS' }">
													<c:set var="totalPrice"
														value="${totalPrice + itemList.stockPrice}" />
												</c:when>
												<c:otherwise>
													<c:set var="totalPrice"
														value="${totalPrice + itemList.sellingPrice}" />
												</c:otherwise>
											</c:choose>

											<c:set var="totalQuantity"
												value="${totalQuantity + itemList.quantity}" />

											<div class="row item">
												<div class="col-md-4 desc">${itemList.barcode}</div>
												<div class="col-md-3 qty">${itemList.quantity}</div>
												<div class="col-md-5 amount text-right">

													<c:choose>
														<c:when
															test="${billSummaryVO.billDetails.billrange_type.name != 'GBJS' }">
															<span>&#8377;</span>${itemList.stockPrice}
											</c:when>
														<c:otherwise>
															<span>&#8377;</span>${itemList.sellingPrice}
											</c:otherwise>
													</c:choose>

												</div>
											</div>
										</c:forEach>
									</div>
									<div class="total text-right">
										<p class="extra-notes">
											<strong>Extra Notes</strong> Please send all items at the
											same time to shipping address by next week. Thanks a lot.
										</p>
										<div class="field">
											Total Quantity <span>${totalQuantity}</span>
										</div>
										<div class="field">
											Subtotal <span>&#8377;${totalPrice}</span>
										</div>
										<div class="field grand-total">
											Total <span>&#8377;${totalPrice}</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br>
			<button class="btn btn-sm btn-success" id="submitBillSummary"
				data-toggle="modal" data-target="#exampleModal">Save</button>

			<a href="#" class="btn btn-sm btn-danger"
				onclick="cancelTransaction('this')">Cancel</a>
			<a href="javascript:;" onclick="printDiv('print')"> <i
				class="btn btn-sm btn-primary">Print this receipt</i></a>

			<a href="#" class="btn btn-sm gbj-btn" data-toggle="modal"
				data-target="#addcustomerimage">Add Customer Image</a>

			<div class="modal fade" id="addcustomerimage" tabindex="-1"
				aria-labelledby="addcustomerimage" aria-hidden="true">
				<div class="modal-dialog modal modal-dialog-centered"
					role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="addcustomerimage">Image</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body" id="image">
							<table class="table table-sm table-borderless">
								<tbody>
									<tr>
										<th colspan="2">Add Customer image</th>
									</tr>
									<tr>
										<td colspan="5">
											<div class="camera">
												<video id="video"></video>
												<canvas id="canvas"></canvas>
											</div>
											<div>
												<button id="startbutton" class="btn btn-sm gbj-btn">Take Photo</button>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							<div class="modal-footer"></div>
						</div>
					</div>
				</div>
			</div>

		</form>

		<div class="footer">
			<jsp:include page="../../fragments/footer.jsp" />
		</div>
		<script>
			function printDiv(divName) {
				var printContents = document.getElementById(divName).innerHTML;
				var originalContents = document.body.innerHTML;
				document.body.innerHTML = printContents;
				window.print();
				document.body.innerHTML = originalContents;
			}

			/* JS comes here */
			(function() {

				var width = 220; // We will scale the photo width to this
				var height = 0; // This will be computed based on the input stream

				var streaming = false;

				var video = null;
				var canvas = null;
				var photo = null;
				var startbutton = null;
				var image = null;

				function startup() {
					video = document.getElementById('video');
					canvas = document.getElementById('canvas');
					photo = document.getElementById('photo');
					startbutton = document.getElementById('startbutton');
					image = document.getElementById('image');

					navigator.mediaDevices.getUserMedia({
						video : true,
						audio : false
					}).then(function(stream) {
						video.srcObject = stream;
						video.play();
					})
					/* .catch(function(err) {
					    console.log("An error occurred: " + err);
					}); */

					video.addEventListener('canplay', function(ev) {
						if (!streaming) {
							height = video.videoHeight
									/ (video.videoWidth / width);

							if (isNaN(height)) {
								height = width / (4 / 3);
							}

							video.setAttribute('width', width);
							video.setAttribute('height', height);
							canvas.setAttribute('width', width);
							canvas.setAttribute('height', height);
							streaming = true;
						}
					}, false);

					startbutton.addEventListener('click', function(ev) {
						takepicture();
						ev.preventDefault();
					}, false);

					clearphoto();
				}

				function clearphoto() {
					var context = canvas.getContext('2d');
					context.fillStyle = "white";
					context.fillRect(0, 0, canvas.width, canvas.height);

					var data = canvas.toDataURL('image/png');
					photo.setAttribute('src', data);
				}

				function takepicture() {
					var context = canvas.getContext('2d');
					if (width && height) {
						canvas.width = width;
						canvas.height = height;
						context.drawImage(video, 0, 0, width, height);
						alert(canvas);
						var data = canvas.toDataURL('image/png');
						alert(data);
						photo.setAttribute('src', data);
						image.setAttribute('value', data);
						
					} else {
						clearphoto();
					}
				}
				window.addEventListener('load', startup, false);
			})();
		</script>
	</tiles:putAttribute>
</tiles:insertDefinition>