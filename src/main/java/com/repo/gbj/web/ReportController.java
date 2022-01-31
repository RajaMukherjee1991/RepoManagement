package com.repo.gbj.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repo.gbj.model.Billing;
import com.repo.gbj.model.Customer;
import com.repo.gbj.model.Stock;
import com.repo.gbj.model.Stock.StockType;
import com.repo.gbj.model.jasper.report.utils.ReportGenerationUtility;
import com.repo.gbj.model.jasper.report.utils.excel.WriteWorkSheetThread;
import com.repo.gbj.model.jasper.report.view.CustomerMortgageInvoice;
import com.repo.gbj.model.jasper.report.view.CustomerSalesInvoice;
import com.repo.gbj.model.jasper.report.view.ParentJasperViewObject;
import com.repo.gbj.model.jasper.report.view.StockImages;
import com.repo.gbj.model.viewobject.ReportGeneratorVO;
import com.repo.gbj.service.BillingService;
import com.repo.gbj.service.StockService;
import com.repo.gbj.utils.ConfigurationHolder.ExcelConstants;
import com.repo.gbj.utils.DateUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/print")
public class ReportController {

	public static final String MORTGAGE_BILL_JRXML_CUSTOMER_COPY = "customer_mortgage_invoice_copy.jrxml";
	public static final String MORTGAGE_BILL_JRXML_SELLER_COPY = "seller_mortgage_invoice_copy.jrxml";

	public static final String STOCK_PRINT_JRXML = "stock_print.jrxml";

	public static final String SALES_BILL_JRXML_PART_1 = "customer_sales_billing items_p1.jrxml";
	public static final String SALES_BILL_JRXML_PART_2 = "customer_sales_item_detail_bill_p2.jrxml";

	public static final String MORTGAGE_SALES_STOCK_LIST_JRXML = "total_mortgage.jrxml";
	public static final String MORTGAGE_BILL_IDENTIFIER = "MTB";

	public String[] mortgageBillPages = { MORTGAGE_BILL_JRXML_CUSTOMER_COPY, STOCK_PRINT_JRXML };

	private BillingService billingService;
	private StockService stockService;

	public ArrayList<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
	public ParentJasperViewObject pjvo = new ParentJasperViewObject();
	public ReportGenerationUtility reportGenerationUtility = new ReportGenerationUtility();

	private final static Logger logger = LoggerFactory.getLogger(ReportController.class);

	@RequestMapping
	public String reports(Model model) {
		return "users/report/generate-reports";
	}

	@RequestMapping(value = "bill/{bill_no}", method = RequestMethod.GET)
	public void generateMortgageBill(@PathVariable("bill_no") String billno, HttpServletResponse response) {

		Billing bill = billingService.findBillByBillNo(billno);
		ArrayList<Stock> stockList = stockService.fetchStockByBillBarcode(billno);
		Customer billCustomer = bill.getCustomer();

		StockImages images = new StockImages();

		if (billno.substring(0, 3).equals(MORTGAGE_BILL_IDENTIFIER)) {

			CustomerMortgageInvoice cusMortgageInv = new CustomerMortgageInvoice();
			cusMortgageInv.setCustomer(billCustomer);

			Stock mortgageStock = stockList.get(0);
			cusMortgageInv.setMortgageStock(mortgageStock);
			pjvo.setCustomerMortgageInvoice(cusMortgageInv);

			images.getBase64ImageList().add(mortgageStock.getBase64stockimage());
			pjvo.setStockImages(images);

			JasperPrint jasperPrint1 = reportGenerationUtility.jasperToPDF(pjvo, MORTGAGE_BILL_JRXML_CUSTOMER_COPY);
			JasperPrint jasperPrint2 = reportGenerationUtility.jasperToPDF(pjvo, STOCK_PRINT_JRXML);
			jasperPrint1.addPage(jasperPrint2.getPages().get(0));
			response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename="
					+ cusMortgageInv.getCustomer().getFullname().trim().toUpperCase() + "_" + billno + ".pdf");
			try {
				JasperExportManager.exportReportToPdfStream(jasperPrint1, response.getOutputStream());

			} catch (JRException e) {
				logger.error("Cause of error " + e.getCause());
				logger.error(e.toString());
			} catch (IOException e) {
				logger.error(e.toString());
			}
		} else {
			CustomerSalesInvoice customerSalesInvoice = new CustomerSalesInvoice();
			customerSalesInvoice.getSalesStock().addAll(stockList);
			customerSalesInvoice.setCustomer(billCustomer);

			for (Stock salesStock : stockList) {
				images.getBase64ImageList().add(salesStock.getBase64stockimage());
			}
			pjvo.setCustomerSalesInvoice(customerSalesInvoice);
			pjvo.setStockImages(images);

			JasperPrint jasperPrint1 = reportGenerationUtility.jasperToPDF(pjvo, SALES_BILL_JRXML_PART_1);
			JasperPrint jasperPrint2 = reportGenerationUtility.jasperToPDF(pjvo, STOCK_PRINT_JRXML);

			jasperPrint1.addPage(jasperPrint2.getPages().get(0));
			response.setContentType("application/x-download");
			response.addHeader("Content-disposition", "attachment; filename="
					+ customerSalesInvoice.getCustomer().getFullname().trim().toUpperCase() + "_" + billno + ".pdf");
			try {
				JasperExportManager.exportReportToPdfStream(jasperPrint1, response.getOutputStream());
			} catch (JRException e) {
				logger.error("Cause of error " + e.getCause());
			} catch (IOException e) {
				logger.error(e.toString());
			}
		}
	}

	/*
	 * @RequestMapping(value="customer/{customer_PKID}", method =
	 * RequestMethod.GET) public void
	 * generateCustomerDetail(@PathVariable("customer_pkid") String
	 * pkid,HttpServletResponse response){
	 * 
	 * }
	 */

	/*
	 * @RequestMapping(value="excel/sales/report", method = RequestMethod.GET)
	 * public String generateSalesExcelReport(HttpServletResponse response){
	 * List<Stock> salesStock = (ArrayList<Stock>)
	 * stockService.findAllStocks(StockType.SALES); logger.info(
	 * "Excel Constants "); WriteWorkSheetThread excelThread = new
	 * WriteWorkSheetThread(salesStock); Thread thread = new
	 * Thread(excelThread); thread.start(); return "redirect:/stock"; }
	 */

	@RequestMapping(value = "/excelreport", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ReportGeneratorVO generateExcelReport(Model model, @RequestBody ReportGeneratorVO report) {

		logger.info("Report Start Date " + report.getStartDate());
		logger.info("Report End Date " + report.getEndDate());

		StockType type = report.getReportType().toUpperCase().equals(ExcelConstants.SALES.toString()) ? StockType.SALES
				: StockType.MORTGAGE;
		List<Stock> salesStock = (ArrayList<Stock>) stockService.getAllStockByTypeIncludingExitDate(type);
		List<Stock> stockBetweenDates = new ArrayList<Stock>();

		stockBetweenDates = salesStock.stream()
				.filter(stock -> (stock.getTimestamp().after(DateUtil.toTimestamp(report.getStartDate())) 
						&& 
						stock.getTimestamp().before(DateUtil.toTimestamp(report.getEndDate()))))
				.collect(Collectors.toList());

		WriteWorkSheetThread excelThread = new WriteWorkSheetThread(stockBetweenDates, type);
		Thread thread = new Thread(excelThread);
		thread.start();

		logger.info("Generating excel report");
		return report;
	}

	/*
	 * @RequestMapping(value="/stock/list/{stock_type}",method=
	 * RequestMethod.GET) public void
	 * generateMortgageStockList(@PathVariable("stock_type") String stock_type
	 * ,Model model, HttpServletResponse response){ System.out.println(
	 * "Stock Type "+ stock_type); ArrayList<Stock> stockList =
	 * stock_type.equalsIgnoreCase("S")?(ArrayList<Stock>)
	 * stockService.findAllStocks(Stock.StockType.SALES) : (ArrayList<Stock>)
	 * stockService.findAllStocks(Stock.StockType.MORTGAGE);
	 * 
	 * ReportGenerationUtility reportGenerationUtility = new
	 * ReportGenerationUtility(); JasperPrint jasperPrint =
	 * reportGenerationUtility.jasperToPDF(stockList,
	 * MORTGAGE_SALES_STOCK_LIST_JRXML);
	 * response.setContentType("application/x-download");
	 * response.addHeader("Content-disposition",
	 * "attachment; filename=StockReport.pdf"); try {
	 * JasperExportManager.exportReportToPdfStream(jasperPrint,response.
	 * getOutputStream()); } catch (JRException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } catch (IOException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */

	@Autowired
	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

	@Autowired
	public void setBillingService(BillingService billingService) {
		this.billingService = billingService;
	}

	/*
	 * @RequestMapping(value="sales/stock/list",method= RequestMethod.GET)
	 * public void generateSalesStockList(Model model, HttpServletResponse
	 * response){ ArrayList<Stock> salesStockList = (ArrayList<Stock>)
	 * stockService.findAllStocks(Stock.StockType.SALES.toString());
	 * 
	 * }
	 */
}
