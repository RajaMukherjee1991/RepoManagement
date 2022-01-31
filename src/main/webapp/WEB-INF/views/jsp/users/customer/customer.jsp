<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Customer</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="customer"/>
	<tiles:putAttribute name="content">
		<spring:url value="/customer" var="customer" />
		<h3>Customer</h3>
		<hr>
		<spring:url value="/customer/saveorupdate" var="saveCustomer" />

		<form:form action="${saveCustomer}" method="post"
			modelAttribute="customerData">
			<!-- <div class="container"> -->
			<div id="accordion" class="accordion">
				<div class="card">
					<div class="card-header" id="headingOne">
						<h5 class="mb-0">
							<a class="btn btn-link" data-toggle="collapse"
								href="#customer_personal_details" role="button" aria-expanded="true"
								aria-controls="customer_personal_details"><strong>Customer
									Details</strong></a>
						</h5>
					</div>
					<div class="collapse multi-collapse" id="customer_personal_details">
						<div class="card-body text-uppercase">
							<div class="form-row">
								<spring:bind path="customerPKID">
									<form:input type="hidden" class="form-control"
										path="customerPKID" />
								</spring:bind>

								<spring:bind path="fname">
									<div class="form-group col-md-4">
										<label>First Name</label>
										<form:input type="text" class="form-control form-control-sm" path="fname"
											placeholder="Firstname" />
										<form:errors path="fname" cssStyle="color: #ff0000;"
											class="control-label" />
									</div>
								</spring:bind>
								<spring:bind path="mname">
									<div class="form-group col-md-4">
										<label>Middle Name</label>
										<form:input type="text" class="form-control  form-control-sm" path="mname"
											placeholder="Middlename" />
										<form:errors path="mname" cssStyle="color: #ff0000;"
											class="control-label" />
									</div>
								</spring:bind>
								<spring:bind path="lname">
									<div class="form-group col-md-4">
										<label>Last Name</label>
										<form:input type="text" class="form-control  form-control-sm" path="lname"
											placeholder="Lastname" />
										<form:errors path="lname" cssStyle="color: #ff0000;"
											class="control-label" />
									</div>
								</spring:bind>
							</div>
							<div class="row">
								<spring:bind path="address1">
									<div class="col-md-7">
										<label>Address 1</label>
										<form:input type="text" class="form-control  form-control-sm" path="address1"
											placeholder="Enter Address 1" />
										<form:errors path="address1" cssStyle="color: #ff0000;"
											class="control-label" />
									</div>
								</spring:bind>
								<spring:bind path="pincode">
									<div class="col-md-5">
										<label>Pincode</label>
										<form:input type="text" class="form-control  form-control-sm" path="pincode"
											placeholder="Pincode" />
										<form:errors path="pincode" cssStyle="color: #ff0000;"
											class="control-label" />
									</div>
								</spring:bind>
							</div>
							<br>
							<div class="row">
								<spring:bind path="address2">
									<div class="col-md-12">
										<label>Address 2</label>
										<form:input type="text" class="form-control  form-control-sm" path="address2"
											placeholder="Enter Address 2" />
										<form:errors path="address2" cssStyle="color: #ff0000;"
											class="control-label" />
									</div>
								</spring:bind>
							</div>
							<br>
							<div class="row">
								<spring:bind path="landlord">
									<div class="col-md-7">
										<label>Landlord</label>
										<form:input type="text" class="form-control  form-control-sm" path="landlord"
											placeholder="Landlord" />
										<form:errors path="landlord" cssStyle="color: #ff0000;"
											class="control-label" />
									</div>
								</spring:bind>
								<spring:bind path="email">
									<div class="col-md-5">
										<label>Email</label>
										<form:input type="email" class="form-control  form-control-sm" path="email"
											placeholder="Customer Email ID" />
										<form:errors path="email" cssStyle="color: #ff0000;"
											class="control-label" />
									</div>
								</spring:bind>
							</div>
							<br>
							<div class="row">
								<spring:bind path="aadharNo">
									<div class="col-md-7">
										<label>Aadhar Card Number</label>
										<form:input type="text" class="form-control  form-control-sm" id="aadharNo"
											path="aadharNo" maxlength="14" name="aadharNo"
											placeholder="Aadhar Card Number" />
										<form:errors path="aadharNo" cssStyle="color: #ff0000;"
											class="control-label" />
									</div>
								</spring:bind>
								<spring:bind path="pancard">
									<div class="col-md-5">
										<label>Pan Card</label>
										<form:input type="text" class="form-control  form-control-sm" path="pancard"
											placeholder="Pancard Number" />
										<form:errors path="pancard" cssStyle="color: #ff0000;"
											class="control-label" />
									</div>
								</spring:bind>
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header" id="headingTwo">
						<h5 class="mb-0">
							<a class="btn btn-link" data-toggle="collapse"
								href="#customer_biometric_details" role="button"
								aria-expanded="false" aria-controls="customer_biometric_details"><strong>Biometrics</strong></a>
						</h5>
					</div>
					<div class="collapse multi-collapse" id="customer_biometric_details">
						<div class="card-body text-uppercase">
							<div class="row">
								<div class="col-md-2">
									<img id="imgFinger" class="card-img-top" alt="">
								</div>
								<div class="col-md-10">
									<div class="card-body">
										<h5 class="card-title">Fingerprint</h5>
										<p class="card-text">
											<font color="red">Please connect the fingerprint
												device if not connected at this point, and ask the customer
												to place the right thumb over the device. Once the right
												thumb is at position press capture.</font>
										</p>
										<p>
											<br>
									</div>
								</div>
							</div>
							<br>
							<div class="form-group col-md-4">
								<h5>&nbsp; Finger print data</h5>
							</div>
							<spring:bind path="fingerprintISOtemplate">
								<div class="form-group col-md-4">
									<p>
										<form:textarea id="txtIsoTemplate"
											path="fingerprintISOtemplate"
											style="width: 315%; height: 100px;" class="form-control" />
										<form:errors path="fingerprintISOtemplate"
											cssStyle="color: #ff0000;" class="control-label" />
									</p>
								</div>
							</spring:bind>
							<div class="form-group col-md-4">
								<p>
									<input type="button" id="btnCapture" value="Capture"
										class="btn btn-warning btn-sm" /> &nbsp; <input
										type="submit" id="reset" value="Reset"
										class="btn btn-info btn-sm" onclick="" /> <br>
								</p>
							</div>
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-header" id="headingThree">
						<h5 class="mb-0">
							<a class="btn btn-link" data-toggle="collapse"
								href="#customer_verification_details" role="button"
								aria-expanded="false" aria-controls="customer_verification_details"><strong>Verification</strong></a>
						</h5>
					</div>
					<div class="collapse multi-collapse" id="customer_verification_details">
						<div class="card-body text-uppercase">
							<div class="row">
								<spring:bind path="primarymobilenumber">
									<div class="col-md-3">
										<form:input type="text" class="form-control  form-control-sm"
											path="primarymobilenumber"
											placeholder="Primary Mobile Number" />
										<form:errors path="primarymobilenumber"
											cssStyle="color: #ff0000;" class="control-label" />
									</div>
								</spring:bind>
								<spring:bind path="alternatemobilenumber">
									<div class="col-md-3">
										<form:input type="text" class="form-control  form-control-sm"
											path="alternatemobilenumber"
											placeholder="Alternate Mobile Number" />
										<form:errors path="alternatemobilenumber"
											cssStyle="color: #ff0000;" class="control-label" />
									</div>
								</spring:bind>
							</div>

						</div>
					</div>
				</div>
			</div>
			<br>
			<div>
				<button type="submit" id=save class="btn btn-success btn-sm">Save</button>
				<button type="button" class="btn btn-success btn-sm" onclick="history.back()">Cancel</button>
			</div>
			<div id="statusmyModal" class="modal fade bd-example-modal-sm"
				tabindex="-1">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Finger Print Status</h5>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div id="toastmessage"></div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>