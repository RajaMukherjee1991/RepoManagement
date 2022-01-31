<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<tiles:insertDefinition name="defaultlayout">
	<tiles:putAttribute name="title">Generate Reports</tiles:putAttribute>
	<tiles:putAttribute name="selectedMenu" value="reports" />
	<tiles:putAttribute name="content">

		<h3>Generate Reports</h3>
		<br>

		<div class="accordion" id="accordionExample">
			<div class="card">
				<div class="card-header" id="sales">
					<h2 class="mb-0">
						<button class="btn btn-link" type="button" data-toggle="collapse"
							data-target="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne">Stock Report</button>
					</h2>
				</div>
				<div id="collapseOne" class="collapse show"
					aria-labelledby="headingOne" data-parent="#accordionExample">
					<div class="card-body">
						<div id="loader" class="spinner-border m-2 " role="status"
							hidden="true">
							<span class="sr-only">Loading...</span>
						</div>
						<div id="reportinfo"></div>
						<div class="row">
							<div id="sales" class="col-md-3">

								<label>Start Date</label><input type="date"
									class="form-control form-control-sm" id="startdate" /> <br>
								<label>End Date</label><input type="date"
									class="form-control form-control-sm" id="enddate" /> <br>
								<label>Select report type</label> <select
									class="custom-select custom-select-sm" id="reporttype">
									<option value=""></option>
									<option value="Sales">Sales Stock</option>
									<option value="Mortgage">Mortgage Stock</option>
								</select> <br> <br>
								<button class="btn btn-sm gbj-btn" type="button"
									id="generatereport">
									<i class='bx bxs-report'></i> Generate Report
								</button>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<br>
		<button class="btn btn-sm gbj-btn" onclick="history.back()">
			Go Back</button>

	</tiles:putAttribute>
</tiles:insertDefinition>
