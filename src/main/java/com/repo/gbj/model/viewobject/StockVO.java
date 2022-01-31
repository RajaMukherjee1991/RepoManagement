package com.repo.gbj.model.viewobject;

import org.springframework.web.multipart.MultipartFile;

import com.repo.gbj.model.Stock;

public class StockVO {

	private Stock stock;
	private MultipartFile stockImage;

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public MultipartFile getStockImage() {
		return stockImage;
	}

	public void setStockImage(MultipartFile stockImage) {
		this.stockImage = stockImage;
	}
}
