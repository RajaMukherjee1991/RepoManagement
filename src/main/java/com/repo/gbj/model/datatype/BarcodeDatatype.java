package com.repo.gbj.model.datatype;


public class BarcodeDatatype{
		
	private BarcodeType barcodetype;
	private String employeeId;
	private String customerId;
	
		
	public BarcodeDatatype(BarcodeType barcodetype, String employeeId, String customerId) {
		super();
		this.barcodetype = barcodetype;
		this.employeeId = employeeId;
		this.customerId = customerId;
	}
	
	/*public BarcodeDatatype parse(String barcodeno){
		
	}*/

	public enum BarcodeType{
		MTB,
		MTS,
		SAB,
		SAS;
		
		@Override
		public String toString(){
			switch(this) {
			case MTB:
				return "MTB";
			case MTS:
				return "MTS";
			case SAB:
				return "SAB";
			case SAS:
				return "SAS";
			default:
				throw new IllegalStateException();
			}
		}
	}

	public BarcodeType getBarcodetype() {
		return barcodetype;
	}

	public void setBarcodetype(BarcodeType barcodetype) {
		this.barcodetype = barcodetype;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
	
	
