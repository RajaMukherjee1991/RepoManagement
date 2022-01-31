var basicOverviewObject = new Object();
var selectionOverviewObject = new Object();

$(document).ready(function() {
	//initializeCustomerName();
	initializeBillTypeSelection();
	//fetchbilltype();
	createSummaryCard();
	$('#billrange').hide();
	//submitBasicOverview();
	/*create();*/
});

function initializeBillTypeSelection() {
	$("input[name='billtype']").click(function() {
		var value = $('input[name=billtype]:checked').val();
		selectionOverviewObject.billtype =value;
		if (value == 'M') {
			$('#billrange').show();
		} else {
			$('#billrange').hide();
			var billtype = 'S';
			var summarycard = createSummaryCard(billtype);
			$('#basicSummary').html(summarycard);
		}
	});
}

function fetchbilltype() {
	var selectedbillvalue = $("#billstype option:selected").val();
	bootbox.alert("Selected value "+selectedbillvalue);
	selectionOverviewObject.billrangetype = selectedbillvalue;
	var billstatement = '';
	var isValidMortgageBill = false;
	if (selectedbillvalue == 'TJ') {
		billstatement = "Amount Ranging from <span>&#8377;</span> 10001 to <span>&#8377;</span> 50000";
		$('#billbasicsummary').show();
		isValidMortgageBill = true;
	} else if (selectedbillvalue == 'GBJ') {
		billstatement = "Amount Ranging from <span>&#8377;</span> 1 to <span>&#8377;</span> 10000";
		$('#billbasicsummary').show();
		isValidMortgageBill = true;
	} else if (selectedbillvalue == 'BJ') {
		billstatement = "Amount Ranging from <span>&#8377;</span> 50000 to no limit";
		$('#billbasicsummary').show();
		isValidMortgageBill = true;
	} else {
		billstatement = '';
		$('#billbasicsummary').hide();
	}

	if (isValidMortgageBill) {
		$("#selectedbilldetail").html(
				"<p class=\"card-text\"><small class=\"text-muted\">"
						+ billstatement + "</small></p>");
		var billtype = 'M';
		var summarycard = createSummaryCard(billtype);
		$('#basicSummary').html(summarycard);
	}
}
function createSummaryCard(billtype) {
	var cardheading = "<strong style=\"font-size:12px;\">Selection Overview</strong>"

	var customerCard = "<div class=\"card shadow-sm\">"
		+ "<div style=\"font-size:12px;\" class=\"card-header\">"
		+ "<strong style=\"font-size:12px;\">Customer Card</strong>"
		+ "</div>"
		+ "<div class=\"card-body\">"
		+ "<p class=\"card-text\"><strong style=\"font-size:12px;\">Name - </strong>"
		+ basicOverviewObject.customerName
		+ "</p>"
		+ "<p class=\"card-text\"><strong style=\"font-size:12px;\">Address - </strong>"
		+ basicOverviewObject.customerAddress
		+ "</p>"
		+ "<p class=\"card-text\"><strong style=\"font-size:12px;\">Phone - </strong>"
		+ basicOverviewObject.customerPhone
		+ "</p>"
		+ "</div></div>"
		+ "<br>"
		
	if (billtype == 'M') {
		var summaryCard = "<div class=\"card shadow-sm\">"
				+ "<div style=\"font-size:12px;\" class=\"card-header\">"
				+ cardheading
				+ "</div>"
				+ "<div class=\"card-body\">"
				+ "<p class=\"card-text\"><strong style=\"font-size:12px;\">Bill Date Selected - </strong>"
				+ $('#bill-date').val()
				+ "</p>"
				+ "<p class=\"card-text\"><strong style=\"font-size:12px;\">Current Date - </strong>"
				+ new Date($.now())
				+ "</p>"
				+ "<p class=\"card-text\"><strong style=\"font-size:12px;\">Bill Type - </strong>"
				+ $("#billstype option:selected").text()
				+ "</p>"
				+ "<p class=\"card-text\"><strong style=\"font-size:12px;\">Bill - </strong>"
				+ $('input[name=billtype]:checked').val()
				+ "</p>"
				+ "</div></div>"
				+ "<br>"
				+ "<table>"
				+ "<button class=\"btn btn-success btn-sm\" onclick=\"create()\">Create</button></table>"
				+ "<br>"

	} else {
		var summaryCard = "<div class=\"card shadow-sm\">"
				+ "<div style=\"font-size:12px;\" class=\"card-header\">"
				+ cardheading
				+ "</div>"
				+ "<div class=\"card-body\">"
				+ "<p class=\"card-text\"><strong style=\"font-size:12px;\">Bill Date Selected - </strong>"
				+ $('#bill-date').val()
				+ "</p>"
				+ "<p class=\"card-text\"><strong style=\"font-size:12px;\">Current Date - </strong>"
				+ new Date($.now())
				+ "</p>"
				+ "<p class=\"card-text\"><strong style=\"font-size:12px;\">Bill - </strong>"
				+ $('input[name=billtype]:checked').val()
				+ "</p>"
				+ "</div></div>"
				+ "<br>"
				+ "<table>"
				+ "<button class=\"btn btn-success btn-sm\" onclick=\"create()\">Create</button></table>"
				+ "<br>"
	}

	return customerCard+summaryCard;
}

/*function submitBasicOverview(){
	$('#basicOverviewButton').on('click',function(event){
		event.preventDefault();
		var ctx = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
		var value = $('input[name=billType]:checked').val();
		if(value=='M'){
			document.basicOverviewFormName.action = ctx+"/billing/toMortgageBilling"
			document.getElementById('basicOverviewForm').submit();
		}else if(value=='S'){
			document.basicOverviewFormName.action = ctx+"/billing/toSalesBilling"
			document.getElementById('basicOverviewForm').submit();
		}else{
			bootbox.alert("Please select bill type");
		}
	});
}*/

