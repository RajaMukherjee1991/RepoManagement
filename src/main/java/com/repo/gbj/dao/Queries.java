package com.repo.gbj.dao;

public final class Queries {

	/**
	 * ---------
	 * Employee 
	 * ---------
	 */
	public static final String FIND_EMPLOYEE_BY_CREDENTIALS ="SELECT * FROM REPO.EMPLOYEE WHERE"
															  + " EMP_USERNAME=:EMP_USERNAME"
															  + " AND "
															  + " EMP_PHNE=:EMP_PHNE "
															  + " AND"
															  + " EMP_PASS=:EMP_PASS";
	
	public static final String FIND_EMPLOYEE_BY_MOBILENUMBER ="SELECT * FROM REPO.EMPLOYEE WHERE"
															  + " EMP_PHNE=:EMP_PHNE";
	public static final String FIND_EMPLOYEE_BY_USERNAME ="SELECT * FROM REPO.EMPLOYEE WHERE"
			  + " EMP_USERNAME=:EMP_USERNAME";
	
	
	/**
	 * ----------
	 * Customer |
	 * ----------
	 */
	public static final String ALL_CUSTOMER ="SELECT * FROM REPO.CUSTOMER";
	
	public static final String ALL_CUSTOMERS_BY_SEARCH_KEY = ALL_CUSTOMER+" WHERE CUS_FNAME LIKE :key";
	
	public static final String FIND_CUSTOMER_BY_MOBILENUMBER ="SELECT * FROM REPO.CUSTOMER WHERE"
															  + " CUS_PHNE_1=:CUS_PHNE_1";
	
	public static final String FIND_CUSTOMER_BY_LASTNAME ="SELECT * FROM REPO.CUSTOMER WHERE"
			  + " CUS_LNAME=:CUS_LNAME";

	public static final String FIND_CUSTOMER_BY_ID ="SELECT * FROM REPO.CUSTOMER WHERE"
			  + " CUS_PKID=:CUS_PKID";

	
	public static final String SAVE_CUSTOMER="INSERT INTO REPO.CUSTOMER "
			+  "(CUS_FNAME, CUS_MNAME, CUS_LNAME, CUS_ADD_1, CUS_ADD_2, CUS_PHNE_1, CUS_PHNE_2, CUS_REL_ID, ISVERIFIED,CUS_FINGERPRINT,CUS_AADHAR,CUS_EMAIL,CUS_PANCARD,CUS_PINCODE,CUS_LANDLORD) VALUES"
			+ " (:CUS_FNAME,:CUS_MNAME,:CUS_LNAME,:CUS_ADD_1,:CUS_ADD_2,:CUS_PHNE_1,:CUS_PHNE_2,:CUS_REL_ID,:ISVERIFIED,:CUS_FINGERPRINT,:CUS_AADHAR,:CUS_EMAIL,:CUS_PANCARD,:CUS_PINCODE,:CUS_LANDLORD)";

	public static final String FIND_MAX_CUSTOMERID ="SELECT MAX(CUS_ID) FROM REPO.CUSTOMER";
	
	public static final String UPDATE_CUSTOMER="UPDATE REPO.CUSTOMER SET "
			+  "CUS_FNAME=:CUS_FNAME, CUS_MNAME=:CUS_MNAME, CUS_LNAME=:CUS_LNAME, CUS_ADD_1=:CUS_ADD_1, CUS_ADD_2=:CUS_ADD_2, CUS_PHNE_1=:CUS_PHNE_1, CUS_PHNE_2=:CUS_PHNE_2, CUS_REL_ID=:CUS_REL_ID, ISVERIFIED=:ISVERIFIED,CUS_FINGERPRINT=:CUS_FINGERPRINT,CUS_AADHAR=:CUS_AADHAR,CUS_EMAIL=:CUS_EMAIL,CUS_PANCARD=:CUS_PANCARD,CUS_PINCODE=:CUS_PINCODE,CUS_LANDLORD=:CUS_LANDLORD WHERE CUS_PKID=:CUS_PKID";
	
	/**
	 * ----------------------
	 * Stock                |
	 * ----------------------
	 */
	public static final String ALL_STOCK ="SELECT * FROM REPO.STOCK";
	public static final String SAVE_STOCK="INSERT INTO REPO.STOCK (ST_PRICE,ST_WEIGHT,ST_ENTRY_DATE,ST_TYPE_DESC,ST_BARCODE,ST_TYPE,ST_IMAGE,ST_QTY,ST_GROSS_WEIGHT,ST_DEDUCTION,TRSN_BILL_NO,ST_SELL_PRICE) VALUES"
			+ " (:ST_PRICE,:ST_WEIGHT,:ST_ENTRY_DATE,:ST_TYPE_DESC,:ST_BARCODE,:ST_TYPE,:ST_IMAGE,:ST_QTY,:ST_GROSS_WEIGHT,:ST_DEDUCTION,:TRSN_BILL_NO,:ST_SELL_PRICE)";
	
	public static final String UPDATE_STOCK ="UPDATE REPO.STOCK SET ST_EXIT_DATE=:ST_EXIT_DATE,TRSN_BILL_NO=:TRSN_BILL_NO,ST_QTY=:ST_QTY,ORDER_ID=:ORDER_ID,ST_SELL_PRICE=:ST_SELL_PRICE WHERE ST_BARCODE=:ST_BARCODE";
	
	public static final String FETCH_STOCK_BY_BARCODE ="SELECT * FROM REPO.STOCK WHERE"
			  + " ST_BARCODE=:ST_BARCODE and ST_EXIT_DATE is NULL";
	
	public static final String FETCH_STOCK_BY_BILL_BARCODE ="SELECT * FROM REPO.STOCK WHERE"
			  + " TRSN_BILL_NO=:TRSN_BILL_NO";
	
	public static final String FETCH_STOCK_IMAGE_BY_ID ="select ST_IMAGE from repo.stock where ST_BARCODE=:ST_BARCODE";
	
	
	
	/**
	 * ----------------------
	 * Sales Stock          |
	 * ----------------------
	 */
	
	public static final String ALL_ACTIVE_SALESSTOCK = ALL_STOCK +" WHERE ST_TYPE=:ST_TYPE and ST_EXIT_DATE is NULL";
	public static final String ALL_SOLD_SALESSTOCK = ALL_STOCK +" WHERE ST_TYPE=:ST_TYPE and ST_EXIT_DATE is not NULL";
	
	public static final String ALL_STOCK_BY_TYPE = ALL_STOCK +" WHERE ST_TYPE=:ST_TYPE";
	
	public static final String FETCH_ALL_BILL ="SELECT * FROM REPO.TRANSACTION" 
			  +" LEFT OUTER JOIN REPO.CUSTOMER ON TRANSACTION.CUS_ID=CUSTOMER.CUS_PKID"
			  +" LEFT OUTER JOIN REPO.EMPLOYEE ON TRANSACTION.EMP_ID=EMPLOYEE.EMP_ID";
	
	
	/**
	 * Mortgage Bill
	 */
	public static final String FETCH_MORTGAGE_BILL ="SELECT * FROM REPO.TRANSACTION" 
											  +" LEFT OUTER JOIN REPO.CUSTOMER ON TRANSACTION.CUS_ID=CUSTOMER.CUS_PKID"
											  +" LEFT OUTER JOIN REPO.EMPLOYEE ON TRANSACTION.EMP_ID=EMPLOYEE.EMP_PKID WHERE TRANSACTION.TRSN_TYPE='M'";
	
	/**
	 * Sales Bill
	 */
	public static final String FETCH_SALES_BILL ="SELECT TRANSACTION.TRSN_BILL_NO,TRANSACTION.TRSN_DATE,CUSTOMER.CUS_FNAME, CUSTOMER.CUS_MNAME,CUSTOMER.CUS_LNAME,CUSTOMER.CUS_PHNE_1,"
			  							  +" TRANSACTION.TRSN_PRICE,TRANSACTION.TRSN_CUS_IMAGE,EMPLOYEE.EMP_FNAME,EMPLOYEE.EMP_LNAME,EMPLOYEE.EMP_ID FROM REPO.TRANSACTION" 
			  							  +" LEFT OUTER JOIN REPO.CUSTOMER ON TRANSACTION.CUS_ID=:CUSTOMER.CUS_PKID"
			  							  +" LEFT OUTER JOIN REPO.EMPLOYEE ON TRANSACTION.EMP_ID=:EMPLOYEE.EMP_PKID WHERE TRANSACTION.TRSN_TYPE='S'";
	
	
	/**
	 * Bill = TRANSACTION
	 */
	public static final String SAVE_BILL="INSERT INTO REPO.TRANSACTION"
			+ " (TRSN_TYPE,TRSN_DATE,CUS_ID,EMP_ID,TRSN_PRICE,TRSN_BILL_NO,TRSN_CUS_IMAGE) VALUES"
			+ "	(:TRSN_TYPE,:TRSN_DATE,:CUS_ID,:EMP_ID,:TRSN_PRICE,:TRSN_BILL_NO,:TRSN_CUS_IMAGE)";
	
	public static final String FETCH_CUSTOMER_BILLS = "SELECT * FROM REPO.TRANSACTION" 
			  +	" LEFT OUTER JOIN REPO.CUSTOMER ON TRANSACTION.CUS_ID=CUSTOMER.CUS_PKID "
			  + " LEFT OUTER JOIN REPO.EMPLOYEE ON TRANSACTION.EMP_ID=EMPLOYEE.EMP_ID"
			  + " WHERE TRANSACTION.CUS_ID=:CUS_ID";
	
	public static final String FIND_BILL_BY_BARCODE ="SELECT * FROM REPO.TRANSACTION" 
			  +	" LEFT OUTER JOIN REPO.CUSTOMER ON TRANSACTION.CUS_ID=CUSTOMER.CUS_PKID "
			  + " LEFT OUTER JOIN REPO.EMPLOYEE ON TRANSACTION.EMP_ID=EMPLOYEE.EMP_ID"
			  + " WHERE TRANSACTION.TRSN_BILL_NO=:TRSN_BILL_NO";
	
		
	/**
	 * Interest Payment
	 */
	public static final String SAVE_INTEREST_PAYMENT ="INSERT INTO REPO.INTEREST_PAID"
			+ " (int_paid_bill_no , int_paid_date, int_paid_amnt, int_paid_leftpayment , int_paid_cap_amnt) VALUES"
			+ " (:int_paid_bill_no , :int_paid_date, :int_paid_amnt, :int_paid_leftpayment , :int_paid_cap_amnt)";

	public static final String FIND_PAYMENTS_BY_BARCODE="SELECT * FROM REPO.INTEREST_PAID where int_paid_bill_no=:int_paid_bill_no";
	
	/**
	 * Settings
	 */
	public static final String SAVE_SETTINGS ="INSERT INTO REPO.USERSETTING"
			+ " (licenseno,shopaddress,shopphoneno1,shopphoneno2,gstno,goldRate,termsAndConditions,BARCODE) VALUES"
			+ " (:licenseno,:shopaddress,:shopphoneno1,:shopphoneno2,:gstno,:goldRate,:termsAndConditions,:BARCODE)";
	
	public static final String FETCH_CURRENT_SETTINGS ="SELECT * FROM REPO.USERSETTING";
	
	public static final String UPDATE_CURRENT_SETTINGS="UPDATE REPO.USERSETTING SET "
			+ "licenseno =:licenseno, shopaddress=:shopaddress,shopphoneno1=:shopphoneno1,shopphoneno2=:shopphoneno2,gstno=:gstno,goldRate=:goldRate,termsAndConditions=:termsAndConditions,"
			+ "BARCODE=:BARCODE where usersetting_id=:usersetting_id";
			
	
	/**Check the next auto-increment number**/
	/*SELECT Table_schema,Table_name,Auto_increment from information_schema.TABLES where table_schema="repo"and TABLE_NAME="stock";*/
}
