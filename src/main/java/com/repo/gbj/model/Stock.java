package com.repo.gbj.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class Stock {

	private int stockID;
	private Timestamp timestamp;
	private BigDecimal stockPrice;
	private BigDecimal stockWeight;
	private String stockEntryDate;
	private String stockExitDate;
	private String stock_desc;
	private String barcode;
	private StockType stockType;
	private BigDecimal stockGrossWeight;
	private BigDecimal stockDeductions;
	private String bill_no;
	private String base64stockimage;
	private BigDecimal quantity;
	private int orderId;
	private BigDecimal sellingPrice;

	public Stock() {
	}

	public Stock(Stock duplicatedStock) {
		super();
		this.stockID = duplicatedStock.getStockID();
		this.timestamp = duplicatedStock.getTimestamp();
		this.stockPrice = duplicatedStock.getStockPrice();
		this.stockWeight = duplicatedStock.getStockWeight();
		this.stockEntryDate = duplicatedStock.getStockEntryDate();
		this.stockExitDate = duplicatedStock.getStockExitDate();
		this.stock_desc = duplicatedStock.getStock_desc();
		this.barcode = duplicatedStock.getBarcode();
		this.stockType = duplicatedStock.getStockType();
		this.stockGrossWeight = duplicatedStock.getStockGrossWeight();
		this.stockDeductions = duplicatedStock.getStockDeductions();
		this.bill_no = duplicatedStock.getBill_no();
		this.base64stockimage = duplicatedStock.getBase64stockimage();
		this.quantity = duplicatedStock.getQuantity();
		this.orderId = duplicatedStock.getOrderId();
	}

	public List<Object> getDataInColumnOrder() {
		return Arrays.asList(stock_desc, stockPrice, stockWeight, stockEntryDate, stockExitDate, quantity,
				sellingPrice);
	}

	public int getStockID() {
		return stockID;
	}

	public void setStockID(int stockID) {
		this.stockID = stockID;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public BigDecimal getStockWeight() {
		return stockWeight;
	}

	public void setStockWeight(BigDecimal stockWeight) {
		this.stockWeight = stockWeight;
	}

	public String getStockEntryDate() {
		return stockEntryDate;
	}

	public void setStockEntryDate(String stockEntryDate) {
		this.stockEntryDate = stockEntryDate;
	}

	public String getStockExitDate() {
		return stockExitDate;
	}

	public void setStockExitDate(String stockExitDate) {
		this.stockExitDate = stockExitDate;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getStock_desc() {
		return stock_desc;
	}

	public void setStock_desc(String stock_desc) {
		this.stock_desc = stock_desc;
	}

	public enum StockType {
		SALES("S"), MORTGAGE("M");
		private String name;

		StockType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	public BigDecimal getStockGrossWeight() {
		return stockGrossWeight;
	}

	public void setStockGrossWeight(BigDecimal stockGrossWeight) {
		this.stockGrossWeight = stockGrossWeight;
	}

	public BigDecimal getStockDeductions() {
		return stockDeductions;
	}

	public void setStockDeductions(BigDecimal stockDeductions) {
		this.stockDeductions = stockDeductions;
	}

	public String getBill_no() {
		return bill_no;
	}

	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}

	public String getBase64stockimage() {
		return base64stockimage;
	}

	public void setBase64stockimage(String base64stockimage) {
		this.base64stockimage = base64stockimage;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Stock [stockID=" + stockID + ", stockPrice=" + stockPrice + ", stockWeight=" + stockWeight
				+ ", stockEntryDate=" + stockEntryDate + ", stockExitDate=" + stockExitDate + ", stock_desc="
				+ stock_desc + ", barcode=" + barcode + ", stockType=" + stockType + ", stockGrossWeight="
				+ stockGrossWeight + ", stockDeductions=" + stockDeductions + ", bill_no=" + bill_no
				+ ", base64stockimage=" + base64stockimage + ", quantity=" + quantity + "]";
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
