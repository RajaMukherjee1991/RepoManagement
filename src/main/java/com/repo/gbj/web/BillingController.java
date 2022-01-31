package com.repo.gbj.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.repo.gbj.model.Billing;
import com.repo.gbj.model.Billing.BillRangeType;
import com.repo.gbj.model.Customer;
import com.repo.gbj.model.Employee;
import com.repo.gbj.model.Payment;
import com.repo.gbj.model.Stock;
import com.repo.gbj.model.Stock.StockType;
import com.repo.gbj.model.datatype.BarcodeDatatype;
import com.repo.gbj.model.datatype.BarcodeDatatype.BarcodeType;
import com.repo.gbj.model.viewobject.BasicOverviewVO;
import com.repo.gbj.model.viewobject.BillSummaryVO;
import com.repo.gbj.model.viewobject.MortgageBillVO;
import com.repo.gbj.model.viewobject.SalesBillVO;
import com.repo.gbj.service.BillingService;
import com.repo.gbj.service.CustomerService;
import com.repo.gbj.service.EmployeeService;
import com.repo.gbj.service.StockService;
import com.repo.gbj.utils.BarcodeGenerator;
import com.repo.gbj.utils.ZXingHelper;
import com.repo.gbj.validator.MortgageBillValidator;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/billing")
@SessionAttributes({ "basicOverviewVO", "billSummaryVO" })
public class BillingController {

	private final static Logger logger = LoggerFactory.getLogger(BillingController.class);

	private final static String BASE64STRING = "data:image/png;base64,";
	private CustomerService customerService;
	private BillingService billingService;
	private StockService stockService;
	private EmployeeService employeeService;

	@Autowired
	MortgageBillValidator mortgageBillValidator;

	@RequestMapping
	public String displayBillingPage(Model model) {
		ArrayList<Billing> billingList = (ArrayList<Billing>) billingService.findAllbills();
		model.addAttribute("billingList", billingList);
		return "users/billing/billing";
	}

	@InitBinder("mortgagebillVO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(mortgageBillValidator);
	}

	@Autowired
	public void setBillingService(BillingService billingService) {
		this.billingService = billingService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Autowired
	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

	/**
	 * This method is used in the Billing Overview page to fetch all the
	 * customers using AJAX call.
	 * 
	 * @param key
	 * @return
	 */

	@RequestMapping(value = "/toSalesBilling/fetchStocksByType", method = RequestMethod.POST)
	public @ResponseBody String fetchStocksByType(@RequestParam("key") String key) {
		logger.info("BillingController : Fetch all stock by type");
		ArrayList<Stock> stockList = (ArrayList<Stock>) stockService.findAllStocks(StockType.SALES);
		logger.info("BillingController : " + stockList.size());
		JSONArray jsonObj = JSONArray.fromObject(stockList);
		logger.info("BillingController : JsonObj - Stock Details -" + jsonObj);
		return jsonObj.toString();
	}

	@RequestMapping(value = "fetchStocksByBarcode", method = RequestMethod.POST)
	public @ResponseBody String checkStock(@RequestParam("barcode") String barcode) {
		logger.info("BillingController : Fetch all stock by type");
		Stock barcodeStock = stockService.fetchStockByBarcode(barcode);
		logger.info("BillingController : " + barcodeStock);
		JSONArray jsonObj = JSONArray.fromObject(barcodeStock);
		logger.info("BillingController : JsonObj - Stock Details -" + jsonObj);
		return jsonObj.toString();
	}

	@RequestMapping(value = "/toSalesBilling", method = RequestMethod.GET)
	public String toSalesBilling(@ModelAttribute("basicOverviewVO") BasicOverviewVO basicOverviewVO, Model model) {

		SalesBillVO salesBillVO = new SalesBillVO();
		salesBillVO.setCustomerDetails(basicOverviewVO.getCustomer());
		salesBillVO.setSellingEmployee(basicOverviewVO.getEmployee());
		salesBillVO.setSalesBillBarcode(basicOverviewVO.getBill_no());
		salesBillVO.setSalesBillDate(basicOverviewVO.getBillDate());

		logger.info("Sales Bill VO " + salesBillVO.getSalesBillBarcode());
		ArrayList<Stock> availableSalesStockList = (ArrayList<Stock>) stockService.findAllStocks(StockType.SALES);
		model.addAttribute("allAvailableSalesStock", availableSalesStockList);
		model.addAttribute("salesBillVO", salesBillVO);

		return "users/billing/billing-tab-sales";
	}

	@RequestMapping(value = "/saveSalesDetails", method = RequestMethod.POST)
	public String saveSales(@SessionAttribute("basicOverviewVO") BasicOverviewVO basicOverviewVO,
			@ModelAttribute("salesBillVO") SalesBillVO salesbillVO, BindingResult result, Model model) {

		logger.info("salesbillVO stock List size " + salesbillVO.getSalesStockList().size());

		for (Stock s : salesbillVO.getSalesStockList()) {
			System.out.println(" ********** Stock barcode ***********" + s.getBarcode());
		}

		if (result.hasErrors()) {
			model.addAttribute("salesBillVO", salesbillVO);
			return "users/billing/billing-tab-sales";
		} else {
			BillSummaryVO billSummaryVO = new BillSummaryVO();

			Billing bill = new Billing();
			bill.setBill_barcode_no(basicOverviewVO.getBill_no());
			bill.setBill_date(basicOverviewVO.getBillDate());
			bill.setBillrange_type(basicOverviewVO.getBillRangeType());
			bill.setCustomer(basicOverviewVO.getCustomer());
			bill.setEmployee(basicOverviewVO.getEmployee());

			BigDecimal finalPrice = salesbillVO.getSalesStockList().stream().map(item -> item.getSellingPrice())
					.reduce((a, b) -> a.add(b)).orElse(BigDecimal.ZERO);

			logger.info("--------------salesbillVO.getSalesStockList() size-----------------"
					+ salesbillVO.getSalesStockList().size());
			bill.setBill_price(finalPrice);
			billSummaryVO.getItemList().addAll(salesbillVO.getSalesStockList());
			billSummaryVO.setBillDetails(bill);

			model.addAttribute("billSummaryVO", billSummaryVO);
			return "users/billing/billing-tab-summary";
		}
	}

	@RequestMapping(value = "/saveMortgage", method = RequestMethod.POST)
	public String saveMortgageBill(@SessionAttribute("basicOverviewVO") BasicOverviewVO basicOverviewVO,
			@ModelAttribute("mortgagebillVO") @Validated MortgageBillVO mortgagebillVO, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("mortgagebillVO", mortgagebillVO);
			return "users/billing/billing-tab-mortgage";
		} else {
			BillSummaryVO billSummaryVO = new BillSummaryVO();
			try {
				mortgagebillVO.getStock().setBase64stockimage(
						ZXingHelper.getBase64String(mortgagebillVO.getMortgageStockImage().getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}

			Billing bill = new Billing();
			bill.setBill_barcode_no(basicOverviewVO.getBill_no());
			bill.setBill_date(mortgagebillVO.getStock().getStockEntryDate());
			bill.setBill_price(mortgagebillVO.getStock().getStockPrice());
			bill.setBillrange_type(basicOverviewVO.getBillRangeType());
			bill.setCustomer(basicOverviewVO.getCustomer());
			bill.setEmployee(basicOverviewVO.getEmployee());

			mortgagebillVO.getStock().setQuantity(BigDecimal.ONE);
			mortgagebillVO.getStock().setStockType(StockType.MORTGAGE);
			mortgagebillVO.getStock().setBarcode(
					new BarcodeGenerator(new BarcodeDatatype(BarcodeType.MTS, null, null)).getBarcodeGenerated());

			logger.info("Mortgage stock barcode " + mortgagebillVO.getStock().getBarcode());
			billSummaryVO.getItemList().add(mortgagebillVO.getStock());
			billSummaryVO.setBillDetails(bill);

			model.addAttribute("billSummaryVO", billSummaryVO);
			return "users/billing/billing-tab-summary";
		}
	}

	@RequestMapping(value = "/saveOrderDraft", method = RequestMethod.POST)
	public String saveSalesAsDraft(@SessionAttribute("basicOverviewVO") BasicOverviewVO basicOverviewVO,
			@ModelAttribute("salesBillVO") SalesBillVO salesbillVO, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		logger.info("Save as draft");
		redirectAttributes.addFlashAttribute("msg",
				"Order Estimate Saved Successfully !! Bill No " + salesbillVO.getSalesBillBarcode());
		redirectAttributes.addFlashAttribute("css", "success");
		model.addAttribute("salesBillVO", salesbillVO);
		return "redirect:/billing/billing/toSalesBilling";
	}

	@RequestMapping(value = "/toBillingOverview", method = RequestMethod.GET)
	public String toBillingOverview(Model model) {

		BasicOverviewVO basicOverviewVO = new BasicOverviewVO();
		model.addAttribute("basicOverviewVO", basicOverviewVO);
		return "users/billing/billing-tab-basicoverview";
	}

	@RequestMapping(value = "/toSalesBilling/addSellingStockRow", method = RequestMethod.GET)
	public String addSellingStockRow(Model model) {
		return "users/billing/billing-tab-sales-selling-list";
	}

	@RequestMapping(value = "/toMortgageBilling", method = RequestMethod.GET)
	public String toMortgageBilling(@ModelAttribute("basicOverviewVO") BasicOverviewVO basicOverviewVO, Model model) {

		logger.info(basicOverviewVO.getBillRangeType().getName());

		MortgageBillVO vo = new MortgageBillVO();
		vo.setBillRangeType(basicOverviewVO.getBillRangeType());
		vo.setSellingEmployee(basicOverviewVO.getEmployee());
		vo.setCustomerDetails(basicOverviewVO.getCustomer());

		Stock mortgageStock = new Stock();
		mortgageStock.setStockEntryDate(basicOverviewVO.getBillDate());

		vo.setMortgageBillBarcode(basicOverviewVO.getBill_no());
		vo.setStock(mortgageStock);

		model.addAttribute("mortgagebillVO", vo);

		return "users/billing/billing-tab-mortgage";
	}

	@RequestMapping(value = "/submitBill", method = RequestMethod.POST)
	public String submitBill(@ModelAttribute("billSummaryVO") BillSummaryVO billSummaryVO, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		for (Stock stock : billSummaryVO.getItemList()) {
			stock.setBill_no(billSummaryVO.getBillDetails().getBill_barcode_no());
			if (billSummaryVO.getBillDetails().getBillrange_type()
					.equals(BillRangeType.GHOSH_BROTHERS_JEWELLERS_SALES)) {
				// Saving
				stock.setStockType(StockType.SALES);
				stockService.updateStockStatus(stock);
			} else {
				// Saving Mortgage stock by the same method
				stockService.saveStock(stock);
			}
		}
		String customerimage = request.getParameter("image");
		String base64customerimagestring[] = customerimage.split(BASE64STRING, customerimage.length()); 
		
		billSummaryVO.getBillDetails().setCustomerimage(base64customerimagestring[1]);		

		boolean billSaveStatus = billingService.saveBill(billSummaryVO.getBillDetails());
		if (billSaveStatus) {
			redirectAttributes.addFlashAttribute("msg",
					"Bill Saved Successfully !! Bill No " + billSummaryVO.getBillDetails().getBill_barcode_no());
			redirectAttributes.addFlashAttribute("css", "success");

		}
		return "redirect:/billing/toBillingOverview";
	}

	@RequestMapping(value = "/createBill", method = RequestMethod.POST)
	public String createBill(@ModelAttribute("basicOverviewVO") BasicOverviewVO basicOverviewVO, Principal principal,
			BindingResult result, Model model) {

			if (principal != null) {
				Customer customer = customerService
						.findCustomerByID(Integer.parseInt(basicOverviewVO.getCustomersPKID()));
				Employee employee = employeeService.findByUsername(principal.getName());

				basicOverviewVO.setCustomer(customer);
				basicOverviewVO.setEmployee(employee);

				if (result.hasErrors()) {
					model.addAttribute("basicOverviewVO", basicOverviewVO);
					return "users/billing/billing-tab-basicoverview";
				}

				if (basicOverviewVO.getBillType().equals(Stock.StockType.MORTGAGE.getName())) {
					String mortgageBillBarcode = new BarcodeGenerator(new BarcodeDatatype(BarcodeType.MTB,
							customer.getCustomerPKID().toString(), employee.getId().toString())).getBarcodeGenerated();
					basicOverviewVO.setBill_no(mortgageBillBarcode);
					return "redirect:/billing/toMortgageBilling";
				} else {
					String salesBillBarcode = new BarcodeGenerator(new BarcodeDatatype(BarcodeType.SAB,
							customer.getCustomerPKID().toString(), employee.getId().toString())).getBarcodeGenerated();
					basicOverviewVO.setBill_no(salesBillBarcode);
					return "redirect:/billing/toSalesBilling";
				}
			} else {
				return "redirect:/";
			}
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@ModelAttribute("allcustomers")
	public List<Customer> populateCustomers() {
		ArrayList<Customer> customers = (ArrayList<Customer>) customerService.findAllCustomer();
		return customers;
	}

	@RequestMapping(value = "/fullBillDetails", method = RequestMethod.GET)
	public String getFullBillDetails(@RequestParam("billno") String billno, Model model) {
		Billing bill = billingService.findBillByBillNo(billno);
		ArrayList<Stock> mappedstocklist = (ArrayList<Stock>) stockService.fetchStockByBillBarcode(billno);
		Map<Billing, ArrayList<Stock>> customersBilledstockMap = new HashMap<Billing, ArrayList<Stock>>();

		customersBilledstockMap.put(bill, mappedstocklist);
		model.addAttribute("customersBilledstockMap", customersBilledstockMap);
		model.addAttribute("interestpaid", billingService.fetchInterestPayments(billno));

		return "users/billing/billfulldetails";
	}

	@RequestMapping(value = "/addnewInterestPayment", method = RequestMethod.GET)
	public String addNewInterestPayment(@RequestParam("barcodeno") String barcode, Model model) {
		logger.info("addNewInterestPayment : Interest Payments for bill no : " + barcode);
		Billing interestPaymentBill = billingService.findBillByBillNo(barcode);
		ArrayList<Payment> paymentList = billingService.fetchInterestPayments(barcode);

		BigDecimal totalInterestPaidAmount = paymentList.parallelStream()
				.map(singlepayement -> singlepayement.getCapitalAmount()).reduce((current, next) -> current.add(next))
				.orElse(BigDecimal.ZERO);
		BigDecimal amountLeft = interestPaymentBill.getBill_price().subtract(totalInterestPaidAmount);

		model.addAttribute("leftcapitalamount", amountLeft);
		model.addAttribute("interestPaymentBill", interestPaymentBill);
		return "users/billing/billing-mortgage-interest";

	}

	@RequestMapping(value = "/addInterestPayment", method = RequestMethod.POST)
	public @ResponseBody String addInterestPayment(@RequestBody Payment payment, HttpServletRequest request) {
		logger.info("AddInterest Payment" + payment.toString());
		boolean interestPaymentStatus = billingService.saveInterestPayment(payment);
		if (interestPaymentStatus) {
			return "Saved";
		}
		return "Saving Error";
	}

	/*
	 * @RequestMapping(value="/releaseMortgageBill" , method=RequestMethod.POST)
	 * public @ResponseBody String releaseMortgage(@RequestParam("barcodeno")
	 * String barcode , HttpServletRequest request){
	 * 
	 * ArrayList<Payment> interestPaid =
	 * billingService.fetchInterestPayments(barcode);
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/delete/{billbarcode}",method=RequestMethod.POST)
	public @ResponseBody String deleteBill(@PathVariable("billbarcode") String billbarcode, Model model){
		return null;
	}
}
