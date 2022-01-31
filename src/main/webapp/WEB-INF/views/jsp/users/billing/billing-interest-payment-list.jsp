<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<ul class="list-group list-group-flush">
	<li class="list-group-item p-2">
		<div class="row">
			<div class="col-sm">
				<c:out value="${param.index}"></c:out>
			</div>
			<div class="col-sm">
				<input id="interestPaymentList[${param.index}]payementdate"
					name="interestPaymentList[${param.index}].payementdate"
					value="${param.payementdate}" class="form-control form-control-sm "
					type="date" readonly />
			</div>
			<div class="col-sm">
				<input id="interestPaymentList[${param.index}]interestAmount"
					name="interestPaymentList[${param.index}].interestAmount"
					value="${param.interestAmount}"
					class="form-control form-control-sm" type="number" readonly />
			</div>
			<div class="col-sm">
				<input id="interestPaymentList[${param.index}]capitalAmount"
					name="interestPaymentList[${param.index}].capitalAmount"
					value="${param.capitalAmount}" class="form-control form-control-sm"
					type="number" readonly />
			</div>
			<div class="col-sm">
				<input id="interestPaymentList[${param.index}]amountLeft"
					name="interestPaymentList[${param.index}].amountLeft"
					value="${param.amountLeft}" class="form-control form-control-sm"
					type="number" readonly />
			</div>
			<div class="col-sm">
				<button type='button'
					onclick='deleteDatafromSellingList(this,"${param.barcode}");'
					class='btn btn-default'>
					<img
						src="https://img.icons8.com/ios-glyphs/20/000000/trash--v3.png" />
				</button>
			</div>
		</div>
	</li>
</ul>