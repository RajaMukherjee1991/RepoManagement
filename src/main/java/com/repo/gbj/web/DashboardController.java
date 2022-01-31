package com.repo.gbj.web;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.repo.gbj.model.Billing;
import com.repo.gbj.model.Employee;
import com.repo.gbj.model.Stock;
import com.repo.gbj.service.BillingService;
import com.repo.gbj.service.CustomerService;
import com.repo.gbj.service.EmployeeService;
import com.repo.gbj.service.StockService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private EmployeeService employeeService;
	private CustomerService customerService;
	private StockService stockService;
	private BillingService billingService;
	
	@RequestMapping
	public String displayDashboard(Principal principal,Model map){
	
		String loggedInUserName=principal.getName();
		
		Employee employee= employeeService.findByUsername(loggedInUserName);
		int customerCount = customerService.findAllCustomer().size();
		ArrayList<Stock> stockList = (ArrayList<Stock>)stockService.findAllStocks();
		ArrayList<Billing> billList = billingService.findAllbills();
		ArrayList<Billing> recentbillList = billingService.findAllTodaysBills();
		String name = employee.getFirstname().concat(" ").concat(employee.getLastname());
		
		map.addAttribute("name", name);
		map.addAttribute("number", employee.getMobilenumber());
		map.addAttribute("address",employee.getAddress());
		map.addAttribute("id", employee.getId());
		map.addAttribute("customerCount", customerCount);
		map.addAttribute("stockList", stockList);
		map.addAttribute("billList", billList);
		map.addAttribute("recentbillList", recentbillList);
		return "users/dashboard/dashboard_details";
	}
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Autowired
	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

	@Autowired
	public void setBillingService(BillingService billingService) {
		this.billingService = billingService;
	}
}
