/**
 * This contains all the mortgage functionalities
 */

$(document).ready(function() {
	bindobjectsforNetweights();
	bindobjectsforloanAmounts();
	saveMortgage();
});

function bindobjectsforNetweights() {
	$('#netweight1').keyup(function(e) {
		var txtVal = $(this).val();
		console.log(txtVal)
		$('#netweight2').val(txtVal);
	});
}

function bindobjectsforloanAmounts() {
	$('#loanamount1').keyup(function(e) {
		var txtVal = $(this).val();
		console.log(txtVal)
		$('#loanamount2').val(txtVal);
	});
}

function saveMortgage() {
	$('#submitMortgage')
			.on(
					'click',
					function(event) {
						event.preventDefault();

						bootbox
								.confirm({
									message : "<h6><strong>Notice</strong></h6>"
											+ "<p>Once confirmed the page will be redirected to the billing section.</p>"
											+ "<p>Do you want to continue by confirming the above details ?  </p>",
									size : 'large',
									buttons : {
										confirm : {
											label : 'Confirm',
											className : 'btn btn-success btn-sm'
										},
										cancel : {
											label : 'Cancel',
											className : 'btn btn-danger btn-sm'
										}
									},
									callback : function(result) {
										if (result) {
											document.getElementById(
													'mortagage-form').submit();
										}
									}
								});
					});
}

function calculateAmountLeft() {
	var leftAmount = $('#amountLeft').val();
	var capitalAmount = $("#capitalAmount").val();
	if ($.isNumeric(capitalAmount)) {
		var currentLeftAmount = leftAmount - capitalAmount;
		$("#amountLeft").val(currentLeftAmount);
	} else {
		$("#capitalAmount").addClass("is-invalid");
	}
}

function showInterestPaymentModalWindow(barcode) {

	console.log("executing addNewInterestPaid " + currentDateFunction());
	var url = 'addnewInterestPayment?barcodeno=' + barcode;
	alert('Adding new payment interest \nLink - ' + url)
	$.ajax({
		type : 'GET',
		url : url,
		beforeSend : function() {
			$("#loading").show();
		},
		complete : function() {
			$("#loading").hide();
		},
		success : function(data) {
			$('#interestpayment-block').html(data)

		}
	})
	$('#addnewInterestPaidModal').modal('show');

}

function addnewinterestPayment() {
	var interestpaymentdate = $('#payementdate').val();
	var paidinterest = $('#interestAmount').val();
	var paidcapitalamount = $('#capitalAmount').val();
	var amountLeft = $('#amountLeft').val();
	var barcode = '';

	alert('interestpaymentdate =' + interestpaymentdate + '\npaidinterest ='
			+ paidinterest + '\npaidcapitalamount = ' + paidcapitalamount);

	if (interestpaymentdate != null && paidinterest != null
			&& paidcapitalamount != null) {
		$('#generalpaymentdetails li').each(function() {
			barcode = $(this).find('#bill_barcode').text();
		});
		alert('barcode ' + barcode)
		var paymentObject = {};
		paymentObject["billno"] = barcode;
		paymentObject["payementdate"] = interestpaymentdate;
		paymentObject["interestAmount"] = paidinterest.trim();
		paymentObject["capitalAmount"] = paidcapitalamount.trim();
		paymentObject["amountLeft"] = amountLeft.trim();

		$.ajax({
			url : 'addInterestPayment',
			type : "POST",
			contentType : 'application/json',
			dataType : 'json',
			data : JSON.stringify(paymentObject),
			success : function(data){
				/*$("#addnewInterestPaidModal .close").click();*/
				$("#addnewInterestPaidModal").modal('hide');
				/*bootbox.alert(data);*/
				location.reload(true);
			}
		});
	} else {
		$('#interestpaymentmsg')
				.html(
						'<div class="alert alert-danger" role="alert">Please enter valid values</div>')
	}
}

function addToInterestTable(interest) {
	if (interest.paidinterest != "" && interest.paidcapitalamount != "") {
		createRow(interest);
	}
}

/*var createInteresttable = function interestPaymentTable() {

 if(!$('#interestPaymentTable').length){
 var table = "<table class=\"table table-sm table-bordered\" id=\"interestPaymentTable\">"
 + "<tbody id=\"interestPaymenttbody\"><tr><th>Sr No</th><th>Date</th><th>Interest Paid</th><th>Capital Amount</th><th>Amount Left</th></tr></tbody>"
 + "</table>";
 return table;
 }
 }*/

var createInterestPaymentRow = function interestPaymentrow(interest) {
	var tableLastRow = $('#interestPaymentTable tr').length;

	// var tr = '<tr><td>'+(tableLastRow +1)
	// +'</td><td>'+interest.paymentdate+'</td><td>'+interest.paidinterest+'</td><td></td><td></td></tr>';
	var tr = '<tr>' + '<td><c:out value="${param.index}"></c:out></td>'
	'<td><input id="salesStockList[${param.index}]stockEntryDate" name="salesStockList[${param.index}].stockEntryDate" value="${param.stockEntryDate}" class="form-control form-control-sm " type="date" readonly/></td>'
			+ '<td><input id="salesStockList[${param.index}]barcode" name="salesStockList[${param.index}].barcode" value="${param.barcode}" class="form-control form-control-sm" type="text" readonly/></td>'
			+ '<td><input id="salesStockList[${param.index}]quantity" name="salesStockList[${param.index}].quantity" value="${param.quantity}" class="form-control form-control-sm" type="number"/></td>'
			+ '<td><input id="salesStockList[${param.index}]stockPrice" name="salesStockList[${param.index}].stockPrice" value="${param.stockPrice}" class="form-control form-control-sm" type="number" readonly/></td>'
			+ '</tr>';

}
