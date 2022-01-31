<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Billing</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="billingSubMenu2"/>
	<tiles:putAttribute name="content">
<spring:url value="/billing/createBill" var="createBill" />
<spring:url value="/dashboard" var="dashboard" />
<spring:url value="/resources/images/undraw_fill_form_re_cwyf.svg"
	var="fillform" />
	<br>
	<div class="col-md-12">
		<h3>Add Bill</h3>
		<hr>
		<div>
				<br>
			<div class="col-md-12">
				<div class="alert alert-info">
					<img src="https://img.icons8.com/ios/30/000000/info--v1.png" />
					&nbsp; Please select the basic requirements for the <strong>Sales/Mortgage</strong>
					bill.
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

			<br>
			<form:form action="${createBill}" method="post"
				modelAttribute="basicOverviewVO">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-6">
							<img src="${fillform}" height="250px" width="800" />
						</div>

						<div class="col-md-6">
							<table class="table table-lg table-bordered">
								<tr>
									<th class="font-weight-bold">Select Customer
										<p class="card-text">
											<small class="text-muted">Select customer from the
												customer list</small>
										</p>
									</th>
									<td class=" font-weight-bold" colspan="2"><form:select
											path="customersPKID" id="customers"
											class="custom-select custom-select-sm fstdropdown-select"
											required="true">
											<c:forEach items="${allcustomers}" var="allcustomers">
												<form:option value="${allcustomers.customerPKID}">
													<c:out value="" />
													<c:out value="${allcustomers.fullname}" /> | <c:out
														value="${allcustomers.primarymobilenumber}" />
												</form:option>
											</c:forEach>

										</form:select></td>
								</tr>
								<tr>
									<th class="font-weight-bold">Bill Date</th>
									<td class="font-weight-bold" colspan="2"><spring:bind
											path="billDate">
											<form:input type="date" class="form-control form-control-sm"
												id="bill-date" path="billDate" />
										</spring:bind></td>
								</tr>
								<tr>
									<th class="font-weight-bold">Select Bill</th>
									<spring:bind path="billType">
										<td class="font-weight-bold" colspan="1">

											<div class="radio">
												<label><form:radiobutton path="billType"
														id="billType" class="form-check-input" value="M" />Mortgage</label>
											</div>
										</td>
										<td class="font-weight-bold" colspan="1">
											<div class="radio ">
												<label><form:radiobutton path="billType"
														id="billType" class="form-check-input" value="S" />Sales</label>
											</div>
										</td>
									</spring:bind>
								</tr>

								<tr>
									<th class="font-weight-bold" colspan="1">Select Bill Range</th>
									<td class="font-weight-bold" colspan="2"><spring:bind
											path="billRangeType">
											<form:select path="billRangeType" id="billstype"
												class="custom-select custom-select-sm">
												<form:option value="" disabled="true" selected="true">Select Bill range type</form:option>
												<form:options />
											</form:select>
										</spring:bind></td>
								</tr>
							</table>
							<button class="btn btn-sm gbj-btn" type="submit">Create
								Bill Card</button>
							<a href="${dashboard}" class="btn btn-primary btn-sm">Go to
								Dashboard</a>
						</div>
					</div>
				</div>
			</form:form>
			<hr>
		</div>
	</div>
</tiles:putAttribute>
</tiles:insertDefinition>
