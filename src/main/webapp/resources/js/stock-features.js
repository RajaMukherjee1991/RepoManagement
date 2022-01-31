/**
 * This contains all the mortgage functionalities
 */
var input='';
$(document).ready(function() {
	saveStock();
	/*resetStockSelection();*/
	readURL(input);
});

/**
 * AJAX call to save a stock
 */
function saveStock(){
	
		var stockBarcode = $('#stockbarcodenumber').val();
		$('#stock-form').submit(function (e){
			e.preventDefault(); // don't submit multiple times
		    this.submit();
		    
		   /* setTimeout(function(){ // Delay for Chrome
		        bootbox.alert('Stock '+stockBarcode+ 'added successfully !'); // blank the input
		    }, 100);
*/
		});
		
		/*var form = $('#stock-form')[0];
		var data = new FormData(form);
		$('#saveStock').prop('disabled',true);
		var stockSavingURL = window.location + '/saveStock';
		
		//Calling AJAX service to invoke java controller to get data from page
		$.ajax({
			type:"POST",
			enctype: 'multipart/form-data',
            url: stockSavingURL,
            data: data,
            processData: false,
            contentType: false,
            cache: false, 
            timeout: 800000,
            success: function (data){
            	addInfoAlert("Stock saved Successfully !! Stock Id = "+data,'stock-alerts');
            	$('#next').html('<button class=\"btn btn-success\">Next</button>')
            },
            error: function(e){
            	addErrorAlert("Error "+e, 'stock-alerts');
            }
		})
		$('#storeStock').modal('close');*/
	/*});*/
}


function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah')
                .attr('src', e.target.result)
                .width(400)
                .height(329);
        };
        reader.readAsDataURL(input.files[0]);
    }
}

function addImageToForm(){
	$('#imagedata').html('<h6>Image Added</h6>');
	$('#addStockImage').modal('hide')
}