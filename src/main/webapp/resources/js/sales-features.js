var stockBarcode = ''
var stockJSONData = '';
var itembarcode='';
var ctl='';
$(document).ready(function() {
	//finalizePricingDetails(stockBarcode,ctl);
	// resetStockSelectModal();
	submitSales();
	saveAsDraft();
	showStockDetails(itembarcode);
	amountLeft();
});

function finalizePricingDetails(stockBarcode, ctl) {
	console.log("Inside finalize pricing");
	$('#checkAvailableStockModal').modal('toggle');
	var url = 'fetchStocksByBarcode?barcode=' + stockBarcode;

	$.ajax({
		type : 'POST',
		url : url,
		success : function(data) {
			productAddToSellingTable(data);
		}
	});
	/*var data = fetchJSONStockDetail(stockBarcode);
	console("After fetching JSON data "+data)
	productAddToTable(data);*/
	productDelete(ctl);
}

function productAddToSellingTable(data) {
	$.each(jQuery.parseJSON(data), function() {
		var tableLastRow = $('#sellingStockTable tr').length;
		var url = window.location + '/addSellingStockRow?index='
				+ (tableLastRow - 1) + '&stockEntryDate='
				+ this['stockEntryDate'] + '&barcode=' + this['barcode']
				+ '&quantity=' + this['quantity'] + '&stockPrice='
				+ this['stockPrice'];
		console.log("Url is : " + url);
		$.ajax({
			url : url,
			type : "GET",
			dataType : 'html'
		}).done(function(recieveddata) {
			console.log("recieveddata " + recieveddata);
			$('#sellingtbody').append(recieveddata);
		});
	});
}

function productDelete(ctl) {
	$(ctl).parents("tr").remove();
}

function fetchJSONStockDetail(stockBarcode) {

	var url = 'fetchStocksByBarcode?barcode='+stockBarcode;
	$.ajax({
		type : 'POST',
		url : url,
		success : function(data) {
			stockJSONData = data;
			alert(data);
		}
	});
	console.log("Json Barcode data after AJAX call "+stockJSONData);
	return stockJSONData;
}

/*function showStockDetails(itembarcode){
	console.log("Here I am This is me "+itembarcode);
	var url1 = 'fetchStocksByBarcode?barcode='+itembarcode;
	console.log("Here I am This is me "+url1);
	$.ajax({
		type : 'POST',
		url : url1,
		success : function(data) {
			stockJSONData = data;
			
			$.each(jQuery.parseJSON(data), function() {
				
				var bootboxmessage = "<h4>Stock Details</h4><br>" +
						"<table class=\"table table-sm table-bordered text-uppercase\">" +
						"<tr>" +
						"<td class=\"text-muted\">Net Weight</td></tr>" +
						"<tr>" +
						"<td class=\"border-bottom border-warning\" style=\"background-color: #F7F6E0;\">"+this['stockWeight']+"</td></tr>" +
						"<tr>" +
						"<td class=\"text-muted\">Stock Description</td></tr>" +
						"<tr>" +
						"<td class=\"border-bottom border-warning\" style=\"background-color: #F7F6E0;\">"+this['stock_desc']+"</td></tr>" +
						"<tr>" +
						"<td class=\"text-muted\">Stock Original Price</td></tr>" +
						"<tr>" +
						"<td class=\"border-bottom border-warning \" style=\"background-color: #F7F6E0;\">"+this['stockPrice']+"</td></tr>" +
						"<tr>" +
						"<td class=\"text-muted\">Stock Image</td></tr>" +
						"<tr>" +
						"<td class=\"text-center\">"+"<img src=\"data:image/jpg;base64,"+this['base64stockimage']+"\" width=\"300\" height=\"300\" />" +
						"</td></tr>"+
						"</table>"
						
				bootbox.alert(bootboxmessage);
			});
		}
	});
}
*/
function amountLeft(){
	$('#capitalAmount').on('input', function(){
		alert( ' amount left field '+ $('#amountLeft').val() + '\n'+ 'capital amount field '+ $('#capitalAmount').val());
		$('#amountLeft').val($('#amountLeft').val() - $('#capitalAmount').val());
	})
}


function deleteDatafromSellingList(ctl,barcode){
	console.log("barcode "+ barcode)
	var currentRow=$(ctl).closest("tr");
	var url = 'fetchStocksByBarcode?barcode='+barcode;

	$.ajax({
		type : 'POST',
		url : url,
		success : function(data) {
			productAddToAvailableTable(data);
		},
		error : function(errordata){
			alert(errordata);
		}
	});
	
	productDelete(ctl);
}


function productAddToAvailableTable(data){
	console.log("productAddToAvailableTable ");
	$.each(jQuery.parseJSON(data), function() {
		var tableLastRow = $('#availableStockList tr').length;
		var availableListRow ="<tr>"+
		"<td>"+this['stock_desc']+"</td>"+
		"<td>"+this['stockEntryDate']+"</td>"+
		"<td>"+this['stockPrice']+"</td>"+
		"<td>"+this['quantity']+"</td>"+
		"<td><div class=\"dropright\">"+
		"<a class=\"btn btn-link dropdown-toggle text-left text-small\"" + 
		"data-toggle=\"dropdown\" aria-haspopup=\"true\"" +
			"aria-expanded=\"false\"> <small>"+this['barcode']+"</small></a>"+
		"<div class=\"dropdown-menu\">" +
			"<a class=\"dropdown-item\"><img src=\"data:image/jpg;base64,"+this['base64stockimage']+
				"width=\"400\" height=\"400\" /></a>" +
		"</div>"+
	"</div></td>"+
"<td class=\"font-weight-bold\" align=\"center\">" +
"<button class=\"btn btn-sm btn-info\" onclick=\"finalizePricingDetails('"+this['barcode']+"',this);\">Add</button></td></tr>"
console.log(availableListRow);
$('#availableStockList').append(availableListRow);
	});
}


function submitSales(){
	$('#generateSalesBillSummary').on('click',function(event){
		event.preventDefault();
        var flag= true;
		$("#sellingtbody tr").each(function(){
        	var currentRow = $(this);
        	console.log('Submit sales' + currentRow);
        	var sellingPrice = currentRow.find("td:eq(5) input[type='number']").val();
        	if(sellingPrice==''){
        		flag = false;
        		currentRow.find("td:eq(5) input[type='number']").addClass('is-invalid');
        		return false;
        	}else{
        		currentRow.find("td:eq(5) input[type='number']").addClass('is-valid');
        	}
        });
		
		if(flag){
		var dialog = bootbox.dialog({
		    message:"<h6><strong>Notice</strong></h6>" +
    		"<p>Once confirmed the page will be redirected to the billing section.</p>" +
    		"<p>Do you want to continue by confirming the above details ?</p>" ,
		    size: 'large',
		    buttons: {
		        cancel: {
		            label: "Cancel",
		            className: 'btn btn-danger btn-sm',
		            callback: function(){
		                console.log('Custom cancel clicked');
		            }
		        },
		        confirm: {
		            label: "Proceed to billing",
		            className: 'btn btn-warning btn-sm',
		            callback: function(){
		                console.log('Custom button clicked');
		                var ctx = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
			                console.log("ctx "+ctx);
			                document.salesbill_form_name.action = ctx+"/billing/saveSalesDetails"
			                document.getElementById('salesbill-form').submit();
		            }
		        }
		    }
		});
		}
	});
}

function saveAsDraft(){
	$('#saveAsDraft').on('click',function(event){
		event.preventDefault();
		
		var dialog = bootbox.dialog({
		    message:"<h6><strong>Save order as draft</strong></h6>" +
    		"<p>Do you want to save this as a Estimate draft order ?</p>",
		    size: 'large',
		    buttons: {
		        cancel: {
		            label: "No",
		            className: 'btn btn-danger btn-sm',
		            callback: function(){
		                console.log('Custom cancel clicked');
		            }
		        },
		        confirm: {
		            label: "Yes",
		            className: 'btn btn-warning btn-sm',
		            callback: function(){
		                console.log('Custom button clicked');
		                var ctx = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
		                document.salesbill_form_name.action = ctx+"/billing/saveOrderDraft";
		                document.getElementById('salesbill-form').submit();
		            }
		        }
		    }
		});
	});
}


