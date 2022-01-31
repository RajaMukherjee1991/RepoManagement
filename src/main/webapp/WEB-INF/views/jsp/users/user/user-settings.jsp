<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">User Settings</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="userSetting"/>
	<tiles:putAttribute name="content">
	
<spring:url value="/setting/saveUpdate" var="saveSetting" />
<spring:url value="/setting/edit" var="edit" />
<spring:url value="/resources/images/undraw_settings_tab_mgiw.svg"
	var="usersettingsImage" />
<c:if test="${not empty msg}">
	<div class="alert alert-${css} alert-dismissible shadow-sm"
		role="alert">
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong>${msg}</strong>
	</div>
</c:if>
<form:form action="${saveSetting}" method="post"
			modelAttribute="userSettings">
<div class="row">
	<div class="col-md-4">
		<h3>Admin</h3>
		<img src="${usersettingsImage}" height="200" width="700" />
	</div>
	
	<div class="col-md-8">
		
			<spring:bind path="settingID">
				<form:input type="hidden" class="form-control" path="settingID" />
			</spring:bind>

			<div class="col-md-12">
				<table class="table table-sm table-bordered">
					<tr>
						<td colspan="3" align="right"><a href="${edit}" type="submit"
							class="btn btn-success btn-sm" style="border-radius: 2px;">Edit</a></td>
					</tr>
					<tr>
						<th >License No</th>
						<td colspan="2"><spring:bind path="licenseNo">
								<form:input type="text" class="form-control form-control-sm"
									path="licenseNo" disabled="${!isEditable}" required="true" />
								<form:errors path="licenseNo" cssStyle="color: #ff0000;"
									class="control-label" />
							</spring:bind></td>
					</tr>
					<tr>
						<th >Shop Address</th>
						<td colspan="2"><spring:bind path="shopAddress">
								<form:input type="text" class="form-control form-control-sm"
									path="shopAddress" disabled="${!isEditable}" required="true" />
								<form:errors path="shopAddress" cssStyle="color: #ff0000;"
									class="control-label" />
							</spring:bind></td>
					</tr>
					<tr>
						<th >GST Number</th>
						<td colspan="2"><spring:bind path="gstNumber">
								<form:input type="text" class="form-control form-control-sm"
									path="gstNumber" disabled="${!isEditable}" />
								<form:errors path="gstNumber" cssStyle="color: #ff0000;"
									class="control-label" />
							</spring:bind></td>
					</tr>
					<tr>
						<th >Shop Phone Numbers</th>
						<td><spring:bind path="shopPhone1">
								<form:input type="text" class="form-control form-control-sm"
									path="shopPhone1" disabled="${!isEditable}" required="true" />
								<form:errors path="shopPhone1" cssStyle="color: #ff0000;"
									class="control-label" />
							</spring:bind></td>

						<td><spring:bind path="shopPhone2">
								<form:input type="text" class="form-control form-control-sm"
									path="shopPhone2" disabled="${!isEditable}" required="true" />
								<form:errors path="shopPhone2" cssStyle="color: #ff0000;"
									class="control-label" />
							</spring:bind></td>
					</tr>
					<tr>
						<th >Gold Rate</th>
						<td colspan="2"><spring:bind path="goldRate">
								<form:input type="text" class="form-control form-control-sm"
									path="goldRate" disabled="${!isEditable}" required="true" />
								<form:errors path="goldRate" cssStyle="color: #ff0000;"
									class="control-label" />
							</spring:bind></td>
						
					</tr>
				</table>
			</div>
			<br>
	</div>
				<div class="col-md-12">
				<div id="accordion">
					<div class="card ">
						<div class="card-header" id="headingOne">
							<h5 class="mb-0">
								<a class="btn btn-link" data-toggle="collapse"
									href="#collapseOne" data-target="#collapseOne" role="button" aria-expanded="false"
									aria-controls="collapseOne">Terms And Conditions</a>
							</h5>
						</div>
						<div id="collapseOne" class="collapse show"
							aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body">
								<form:textarea class="form-control" style="width: 1555px; height: 300px;" path="termsAndConditions" id="termsAndConditions" disabled="${!isEditable}" required="true"/>
							</div>
						</div>
					</div>
				</div>
				<br>
				<c:choose>
				<c:when test="${isEditable && isUpdatable}">
					<tr>
						<td align="right" colspan="3"><button
								class="btn btn-sm btn-success" style="border-radius: 2px;">Update</button></td>
					</tr>
				</c:when>
				<c:when test="${isEditable && !isUpdatable}">
					<tr>
						<td align="right" colspan="3"><button
								class="btn btn-sm btn-success" style="border-radius: 2px;">Save</button></td>
					</tr>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
				</div>
</div>
<br>
</form:form>	
</tiles:putAttribute>
</tiles:insertDefinition>
