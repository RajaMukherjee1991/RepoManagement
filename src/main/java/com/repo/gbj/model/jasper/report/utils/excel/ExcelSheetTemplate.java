package com.repo.gbj.model.jasper.report.utils.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.repo.gbj.model.Stock;
import com.repo.gbj.model.Stock.StockType;
import com.repo.gbj.utils.ConfigurationHolder.ExcelConstants;
import com.repo.gbj.utils.DateUtil.DateFormat;
import com.repo.gbj.utils.DateUtil;

public class ExcelSheetTemplate {

	private XSSFSheet sheet;
	private final static Logger logger = LoggerFactory.getLogger(ExcelSheetTemplate.class);
	private AtomicInteger rowCount = new AtomicInteger(9);

	public ExcelSheetTemplate(XSSFSheet sheet) {
		this.sheet = sheet;
	}
	
	public void mergeCells(String range1, String range2) {
		sheet.addMergedRegion(CellRangeAddress.valueOf(range1 + ":" + range2));
	}
	
	public  void addRow(Stock stock) {
		XSSFRow row = sheet.createRow(rowCount.getAndIncrement());
		AtomicInteger colCount = new AtomicInteger(0);
		
		stock.getDataInColumnOrder().forEach(stockdata -> {
			XSSFCell cell = row.createCell(colCount.getAndIncrement());
			cell.setCellStyle(addDataCellBorder());
			cell.getCellStyle().setAlignment(HorizontalAlignment.LEFT);
			if (stockdata instanceof String) {
				cell.setCellValue((String) stockdata);
			} else if (stockdata instanceof Date) {
				cell.setCellValue((Date) stockdata);
			} else if (stockdata instanceof Integer) {
				cell.setCellValue((Integer) stockdata);
			} else if(stockdata instanceof BigDecimal){
				BigDecimal decimalValue = (BigDecimal) stockdata;
				cell.setCellValue(decimalValue.floatValue());
			}
			sheet.autoSizeColumn(colCount.get());
		});
	}

	
	public XSSFCellStyle addDataCellBorder() {
		XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
		style.setBorderBottom(BorderStyle.DASHED);
		style.setBorderRight(BorderStyle.DASHED);
		style.setBorderTop(BorderStyle.DASHED);
		style.setBorderLeft(BorderStyle.DASHED);
		style.setFont(getFontDetails(false));
		style.setFillForegroundColor(new XSSFColor(new java.awt.Color(246, 245, 236)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
	}

	private XSSFFont getFontDetails(boolean isHeader){
		XSSFFont dataFont = sheet.getWorkbook().createFont();
	    dataFont.setFontName("Courier New");
	    dataFont.setFontHeightInPoints((short) 12);
	    IndexedColors color = (isHeader) ? IndexedColors.WHITE : IndexedColors.BLACK;
	    dataFont.setColor(color.index);
	    return dataFont;
	}
	
	public XSSFCellStyle addHeaderCellBorder() {
		XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setFont(getFontDetails(true));
		style.setFillForegroundColor(new XSSFColor(new java.awt.Color(248, 148, 36)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
	}
	
	public XSSFCellStyle reportHeaderCellBorder(boolean isReportHeader) {
		XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setFont(reportHeaderFontDetails(isReportHeader));
		style.setFillForegroundColor(new XSSFColor(new java.awt.Color(246, 245, 236)));
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return style;
	}
	
	private XSSFFont reportHeaderFontDetails(boolean isReportHeader){
		XSSFFont dataFont = sheet.getWorkbook().createFont();
	    dataFont.setFontName("Courier New");
	    dataFont.setFontHeightInPoints((short) 12);
	    dataFont.setBold(isReportHeader);
	    IndexedColors color = (isReportHeader) ? IndexedColors.ORANGE :IndexedColors.BLACK;
	    dataFont.setColor(color.index);
	    return dataFont;
	}
	
	public void writeToFile(XSSFWorkbook workbook,StockType type){
		try(FileOutputStream fos = new FileOutputStream(getFilePath(type))){
			workbook.write(fos);
		}catch(IOException ioe){
			try{
				workbook.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			ioe.printStackTrace();
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getFilePath(StockType type){
		
		String filename = type.equals(StockType.SALES) ? ExcelConstants.SALES.toString() : 
			   ExcelConstants.MORTGAGE.toString();
		return MessageFormat.format
				(ExcelConstants.EXCELFILEPATH.toString(),System.getProperty("user.name"))
				.concat(filename).concat(DateUtil.date_now(DateFormat.YYYYMMDDHHMMSS.toString())).concat(".")
				.concat(ExcelConstants.EXCELFILETYPE.toString());
	}
	
	public void addHeaderRow() {
		XSSFRow row = sheet.createRow(rowCount.getAndIncrement());
		List<String> headers = null;
		headers = ExcelUtility.getHeaders();
		logger.info("Headers "+ headers);

		AtomicInteger colCount = new AtomicInteger(0);
		headers.forEach(header -> {
			XSSFCell cell = row.createCell(colCount.getAndIncrement());
			cell.getCellStyle().setWrapText(true);
			cell.setCellStyle(addHeaderCellBorder());
			cell.setCellValue(header);
			sheet.autoSizeColumn(colCount.get());
		});
	}
	
	public void createReportData(BigDecimal totalstockPrice, BigDecimal totalsellingPrice , StockType type){
		
		ExcelUtility excelutility = new ExcelUtility();
		ExcelUtility.ExcelReport report = excelutility.new ExcelReport(type);
		
		report.setTotalSum(totalstockPrice.toString());
		report.setTotalSellingPrice(totalsellingPrice.toString());
		List<String> data = null;
		data =report.getDataHeader();
		
		CellReference cr = new CellReference(0,0);
		
		int n = cr.getRow();
		int m = cr.getCol();
		XSSFRow row = sheet.getRow(n);
		if (row == null)
		    row = sheet.createRow(n);
		XSSFCell cell = row.getCell(m);
		if(cell == null)
			cell = row.createCell(m);
		cell.getCellStyle().setWrapText(true);
		cell.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
		cell.setCellStyle(reportHeaderCellBorder(true));
		cell.setCellValue(report.ghoshBrotherHeader);
		
		mergeCells("A1", "B2");
		sheet.autoSizeColumn(m);
		
		int j=0;
		for(int r=2;r<=4;r++){
				for(int c=0;c<=1;c++){
					setValueToCell(r,c,data.get(j++));
				}
			}
	}	
	
	public void setValueToCell(int i, int j,String value){
		CellReference cr = new CellReference(i,j);
		int r = cr.getRow();
		int c = cr.getCol();
		
		XSSFRow row = sheet.getRow(r);
		if (row == null)
		    row = sheet.createRow(r);
		XSSFCell cell = row.getCell(c);
		if(cell == null)
			cell = row.createCell(c);
		cell.getCellStyle().setWrapText(true);
		cell.setCellStyle(reportHeaderCellBorder(false));
		cell.setCellValue(value);
		sheet.autoSizeColumn(c);
	}
}
