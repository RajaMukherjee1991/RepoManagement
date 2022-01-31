var salesStockJSONData = '';
var transactionType ='';
let timer,timer1, currSeconds = 0;


$(document).ready(function() {
	$('#addSalesData').prop('disabled', false);
	initializeAadharFormat();
	initializeFingerprintCapture();
	initializeDataTables();
	fileSelect();
	tablerowClick();
	submitBill();
	navigationFeature();
	generateReport();
});

function navigationFeature(){
	if($('#sidebar-wrapper a.activated').length!=0){
		$('#sidebar-wrapper a.activated').parent().removeClass("collapse").addClass("collapsed");
	}
}

function resetTimer() {

    /* Hide the timer text */
  //  document.querySelector(".timertext").style.display = 'none';
    
    /* Clear the previous interval */
    clearInterval(timer);

    /* Reset the seconds of the timer */
    currSeconds = 0;

    /* Set a new interval */
    timer =  setTimeout(showMessage, 60000);
}
// Define the events that
// would reset the timer
window.onload = resetTimer;
window.onmousemove = resetTimer;
window.onmousedown = resetTimer;
window.ontouchstart = resetTimer;
window.onclick = resetTimer;
window.onkeypress = resetTimer;

function showMessage() {
   
    	var ctx = window.location.pathname.substring(0,window.location.pathname.indexOf("/",2));
    	var dialog = bootbox
		.dialog({
			message : "<h6><strong>You were inactive for a while.</strong></h6>"
					+ "<h6><p>Do you want to contnue ?</p></h6>",
			size : 'large',
			buttons : {
				cancel : {
					label : "Re-login",
					className : 'btn-danger btn-sm',
					callback : function() {
						console.log('Custom cancel clicked');
						window.location.href = ctx;
					}
					
				},
				confirm : {
					label : "Yes",
					className : 'btn-warning btn-sm',
					callback : function() {
						console
								.log('Custom button clicked');
						window.location.href = ctx+"/billing";
					}
				}
			}
		});
}


function initializeFingerprintCapture() {
	$("#btnCapture")
			.click(
					function() {
						console.log("Button capture clicked")
						try {
							document.getElementById('imgFinger').src = "data:image/bmp;base64,";
							document.getElementById('txtIsoTemplate').value = "";
							var res = CaptureFinger(quality, timeout);
							if (res.httpStaus) {
								if (res.data.ErrorCode == "0") {
									document.getElementById('imgFinger').src = "data:image/bmp;base64,"
											+ res.data.BitmapData;
									var imageinfo = "Quality: "
											+ res.data.Quality + " Nfiq: "
											+ res.data.Nfiq + " W(in): "
											+ res.data.InWidth + " H(in): "
											+ res.data.InHeight + " area(in): "
											+ res.data.InArea + " Resolution: "
											+ res.data.Resolution
											+ " GrayScale: "
											+ res.data.GrayScale + " Bpp: "
											+ res.data.Bpp
											+ " WSQCompressRatio: "
											+ res.data.WSQCompressRatio
											+ " WSQInfo: " + res.data.WSQInfo;
									document.getElementById('txtIsoTemplate').value = res.data.IsoTemplate;
									var successAlert = "<div class=\"alert alert-success\" role=\"alert\">Success</div>";
									$('#toastmessage').html(successAlert);
								}
							} else {
								alert(res.err);
							}
						} catch (e) {
							alert(e);
						}
					});
}

function initializeAadharFormat() {
	$("#aadharNo").keyup(function() {
		var foo = $(this).val().split(" ").join("");
		if (foo.length > 0) {
			foo = foo.match(new RegExp('.{1,4}', 'g')).join(" ");
		}
		$(this).val(foo);
	});
}

function initializeDataTables() {

	$('#billstable').DataTable({
		"searching" : true,
		"pageLength" : 10
	});

	$('#salesStockdatatable').DataTable({
		"searching" : true,
		"pageLength" : 10
	});
	$('#mortgageStockdatatable').DataTable({
		"searching" : true,
		"pageLength" : 10
	});
	$('#customerdatatable').DataTable({
		"searching" : true,
		"pageLength" : 10
	});

	$('#availableStock').DataTable({
		"searching" : true,
		"pageLength" : 10
	});

	$('#availableStockTable').DataTable({
		"searching" : true,
		"pageLength" : 10
	});

	$('#todaysbillstable').DataTable({
		"searching" : true,
		"pageLength" : 5
	});

	$('#linkedstock').DataTable({
		"searching" : true,
		"pageLength" : 3
	});

}

 var currentDateFunction = function getCurrentDate() {
 var date = new Date();
 var month = String(date.getMonth() + 1);
 var day = String(date.getDate());
 if (month.length < 2) {
 month = '0' + month;
 console.log(month);
 }
 if (day.length < 2) {
 day = '0' + day;
 console.log(day);
 }
 var ddmmyyFormat =String(day +'-'+month+'-'+date.getFullYear());
 console.log(date.getFullYear() + '-' + month + '-' + day);
 return ddmmyyFormat;
 }
 
function submitBill() {
	$('#submitBillSummary')
			.on(
					'click',
					function(event) {
						event.preventDefault();

						var dialog = bootbox
								.dialog({
									message : "<h6><strong>Confirm</strong></h6>"
											+ "<h6><p class=\"font-italic font-weight-bold text-primary\">Please confirm submission ?</p></h6>",
									size : 'large',
									buttons : {
										cancel : {
											label : "No",
											className : 'btn-danger',
											callback : function() {
												console
														.log('Custom cancel clicked');
											}
										},
										confirm : {
											label : "Yes",
											className : 'btn-warning',
											callback : function() {
												console
														.log('Custom button clicked');
												var ctx = window.location.pathname
														.substring(
																0,
																window.location.pathname
																		.indexOf(
																				"/",
																				2));
												document.bill_summary_form_name.action = ctx
														+ "/billing/submitBill";
												document.getElementById(
														'bill_summary_form')
														.submit();
											}
										}
									}
								});
					});
}

function fileSelect() {
	$(".custom-file-input").on(
			"change",
			function() {
				var fileName = $(this).val().split("\\").pop();
				$(this).siblings(".custom-file-label").addClass("selected")
						.html(fileName);
			});
}

/*function addTableRow() {
 var tablerow = ''
 $('#addSalesItem')
 .on(
 'click',
 function() {
 $
 .each(
 jQuery.parseJSON(salesStockJSONData),
 function() {
 if (this['stockID'] == $(
 "#stockList").val()) {
 var valueAfterDiscount = this['stockPrice']
 - $('#discountedAmount')
 .val();
 console
 .log('valueAfterDiscount'
 + valueAfterDiscount);
 tablerow += "<tr>"
 + "<td>"
 + this['stockID']
 + "</td>"
 + "<td>"
 + getCurrentDate()
 + "</td>"
 + "<td>ST-S-"
 + this['barcode']
 + "</td>"
 + "<td>"
 + valueAfterDiscount
 + "</td>"
 + "<td>"
 + this['stock_desc']
 + "</td>"
 + "<td><a href=\"#\">"
 + "<span class=\"material-icons\">delete</span>"
 + "</a></td>" + "</tr>"
 }
 })
 $('#salesItemName').text('');
 $('#salesAmount').text('');
 $('#weightInGrams').text('');
 $('#salesItemList').show();
 console.log(tablerow);
 $('#saleItems').html(tablerow);
 })
 }

 function storeStock() {
 $('#saveStock').on('click', function() {
 var stockPrice = $('#stockprice').val();
 var stockweight = $('#stockweight').val();
 var entryDate = $('#stock-entry-date').val();
 var stockDesc = $('#stockDesc').val();
 var imageSource = $('#stockImageFile').val()

 console.log('entryDate ' + entryDate)
 console.log('imageSource ' + imageSource)
 });

 }
 */

function generateReport(){
	$('#generatereport').on('click', function() {
		var startDate = $('#startdate').val();
		var endDate = $('#enddate').val();
		var reportType = $('#reporttype').val()
		console.log('startdate '+reportType);
		
		if(startDate == '')
			bootbox.alert('Please select start date');
		else if(endDate=='')
			bootbox.alert('Please select end date');
		else if(reportType=='')
			bootbox.alert('Please select report type');
		else if(Date.parse(endDate) < Date.parse(startDate))
			bootbox.alert('Start date should be less than End Date ');
		else{
			var reportdetails = {};
			reportdetails['startDate']=startDate;
			reportdetails['endDate']=endDate;
			reportdetails['reportType']=reportType;
			
			var url = window.location+'/excelreport';
			alert('calling ajax on URL '+url);
			
			$.ajax({
				url: url,
				type: 'POST',
				contentType : 'application/json',
				dataType : 'json',
				data : JSON.stringify(reportdetails),
				beforeSend: function () { 
		               $('#loader').attr('hidden', false)
		        }
			}).done(function(data){
				 $('#loader').attr('hidden', true)
				var returnedObject = jQuery.parseJSON(JSON.stringify(data));
				console.log(returnedObject);
				$('#reportinfo').html('<div class=\"alert alert-success\" role=\"alert\"><strong>'+returnedObject['reportType']+'</strong> report generated from <strong>'+returnedObject['startDate']+ '</strong> to <strong>'+returnedObject['endDate']+'</strong></div>');
			});
		}
	});
}

function tablerowClick() {
	$('#salesStockdatatable tr').on('click', function() {
		bootbox.alert('You triggered the table row ');
	});
}

function cancelTransaction(transactionType){
	var dialog = bootbox.dialog({
	    message:"<h6><strong>Notice</strong></h6>" +
		"<p>Are you sure you want to cancel "+transactionType+" Billing ?</p><br>",
	    size: 'large',
	    buttons: {
	        cancel: {
	            label: "No",
	            className: 'btn btn-success btn-sm',
	            callback: function(){
	                console.log('Custom cancel clicked');
	            }
	        },
	        confirm: {
	            label: "Yes",
	            className: 'btn btn-danger btn-sm',
	            callback: function(){
	                console.log('Custom button clicked');
	                var ctx = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	                console.log("ctx "+ctx);
	                window.location.replace(ctx+"/billing/toBillingOverview");
	            }
	        }
	    }
	});
}