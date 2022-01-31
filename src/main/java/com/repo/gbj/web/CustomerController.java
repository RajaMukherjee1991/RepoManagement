package com.repo.gbj.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.repo.gbj.model.Billing;
import com.repo.gbj.model.Customer;
import com.repo.gbj.model.Stock;
import com.repo.gbj.service.BillingService;
import com.repo.gbj.service.CustomerService;
import com.repo.gbj.service.StockService;
import com.repo.gbj.validator.CustomerValidator;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerValidator customerValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(customerValidator);
	}
	
	private CustomerService customerService;
	private BillingService billingService;
	private StockService stockService;
	
	@Autowired
	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Autowired
	public void setBillingService(BillingService billingService) {
		this.billingService = billingService;
	}
	
	@RequestMapping(value="/customerpage/{customerid}", method=RequestMethod.GET)
	public String saveCustomerPage(@PathVariable("customerid") String customerid , Model model){
		
		logger.info("saveCustomerPage : Integer.parseInt(customerid) "+Integer.parseInt(customerid));
		Customer customer = customerService.findCustomerByID(Integer.parseInt(customerid));
		model.addAttribute("customerData",customer);
		return "users/customer/customer";
	}
	
	@RequestMapping(value="/checkDetails", method=RequestMethod.GET)
	public String checkCustomerDetails(@RequestParam("customerid") String customerid , Model model){
		logger.info("customerid "+ customerid);
		logger.info("checkCustomerDetails : Integer.parseInt(customerid) "+Integer.parseInt(customerid));
		Customer customer = customerService.findCustomerByID(Integer.parseInt(customerid));
		ArrayList<Billing> customerBills = billingService.findCustomerBill(Integer.parseInt(customerid));
		Map<Billing,ArrayList<Stock>> customersBilledstockMap = new HashMap<Billing,ArrayList<Stock>>();
		
		for(Billing bill : customerBills){
			logger.info(stockService.fetchStockByBillBarcode(bill.getBill_barcode_no()).toString());
			customersBilledstockMap.put(bill,stockService.fetchStockByBillBarcode(bill.getBill_barcode_no()));
		}
		
		//customersBilledstockMap = customerBills.parallelStream().map(bills -> bills).collect(Collectors.toMap(bills,stockService.fetchStockByBillBarcode(bills.getBill_barcode_no())));
		
		model.addAttribute("customerData",customer);
		model.addAttribute("customersBilledstockMap", customersBilledstockMap);
		
		return "users/customer/customerdetails";
	}
	
	@RequestMapping(value="/saveorupdate", method = RequestMethod.POST)
	public String saveorUpdateCustomer(@ModelAttribute("customerData") @Validated Customer customer,
			BindingResult result, Model model,final RedirectAttributes redirectAttributes){
		logger.info("PKID "+ customer.getCustomerPKID());
		if(result.hasErrors()){
			logger.info("customer first name "+ customer.getFname());
			model.addAttribute("customerData", customer);
			return "users/customer/customer";
		}else if(customer.getCustomerPKID() == null){
			customerService.saveCustomer(customer);
			redirectAttributes.addFlashAttribute("msg", "New customer added successfully !!");
			redirectAttributes.addFlashAttribute("css","success");
			return "redirect:/customer";
		}else{
			logger.info("Updating a customer");
			customerService.updateCustomer(customer);
			redirectAttributes.addFlashAttribute("msg", "Details updated for "+customer.getFname()+" "+customer.getLname());
			redirectAttributes.addFlashAttribute("css","success");
			return "redirect:/customer";
		}
	}
	
	@RequestMapping
	public String displayCustomerPage(Model model){
		ArrayList<Customer> customerList = (ArrayList<Customer>) customerService.findAllCustomer();
		model.addAttribute("customerList", customerList);
		return "users/customer/customerslist";
	}
	
	@RequestMapping(value="/newCustomer", method=RequestMethod.GET)
	public String saveNewCustomer(Model model){
		model.addAttribute("customerData",new Customer());
		return "users/customer/customer";
	}

	
}