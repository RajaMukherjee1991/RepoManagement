<span class="badge badge-info">Please Note</span>
<div class="alert alert-warning" role="alert">Interest and Capital Payment details for</div>
<div>
	<ul class="list-group list-group-flush border border-dark" id= "generalpaymentdetails">
		<li class="list-group-item list-group-item-info">
			<div class="row">
				<div class="col-sm text-black">Bill Number</div>
				<div class="col-sm text-black">Amount Left</div>
			</div>
		</li>
		
		<li class="list-group-item py-2 border border-light">
			<div class="row">
				<div class="col-sm" id='bill_barcode'>${interestPaymentBill.bill_barcode_no}</div>
				<div class="col-sm">&#8377; ${leftcapitalamount}</div>
			</div>
		</li>
	</ul>	
</div>
<div id="interestpaymentmsg"></div>
<br>
<div class="card border border-info">
	<div class="card-body">
		<form>
			<div class="form-group row">
				<label for="inputEmail3" class="col-sm-7">Date of Interest
					Payment</label>
				<div class="col-md-5">
					<input type="date" id="payementdate"
						class="form-control form-control-sm">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputEmail3" class="col-sm-7 col-form-label">Paid
					Interest</label>
				<div class="col-md-5">
					<input type="text" id="interestAmount"
						class="form-control form-control-sm">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputEmail3" class="col-sm-7 col-form-label">Paid
					Capital Amount</label>
				<div class="col-md-5">
					<input type="text" id="capitalAmount" onchange="calculateAmountLeft()"
						class="form-control form-control-sm">
				</div>
			</div>
			<div class="form-group row">
				<label for="inputEmail3" class="col-sm-7 col-form-label">Amount
					Left</label>
				<div class="col-md-5">
					<input type="text" id="amountLeft" readonly
						value="${leftcapitalamount}"
						class="form-control form-control-sm">
				</div>
			</div>
			<button type="button" class="btn gbj-btn btn-sm"
				onclick="addnewinterestPayment();">Save</button>
		</form>
	</div>
</div>

