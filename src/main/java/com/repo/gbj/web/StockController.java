package com.repo.gbj.web;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.repo.gbj.model.Stock;
import com.repo.gbj.model.datatype.BarcodeDatatype;
import com.repo.gbj.model.datatype.BarcodeDatatype.BarcodeType;
import com.repo.gbj.model.viewobject.StockVO;
import com.repo.gbj.service.StockService;
import com.repo.gbj.utils.BarcodeGenerator;
import com.repo.gbj.utils.ZXingHelper;
import com.repo.gbj.validator.StockValidator;

@Controller
@RequestMapping("/stock")
public class StockController {

	private final static Logger logger = LoggerFactory.getLogger(StockController.class);
	private StockService stockService;
	
	@Autowired
	StockValidator stockValidator;


	@RequestMapping
	public String displayStockPage(Model model,Principal principal) {
		ArrayList<Stock> salesStockList = (ArrayList<Stock>) stockService.getAllStockByTypeIncludingExitDate(Stock.StockType.SALES);
		ArrayList<Stock> mortgageStockList = (ArrayList<Stock>) stockService.getAllStockByTypeIncludingExitDate(Stock.StockType.MORTGAGE);
		model.addAttribute("salesStockList", salesStockList);
		model.addAttribute("mortgageStockList", mortgageStockList);

		return "users/stock/stock";
	}

	@Autowired
	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}
	
	@InitBinder("stockVO")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(stockValidator);
	}

	@RequestMapping(value = "/stockAdder", method = RequestMethod.GET)
	public String goToStockAdder(Model model){
		ArrayList<Stock> salesStockList = (ArrayList<Stock>) stockService.getAllStockByTypeIncludingExitDate(Stock.StockType.SALES);
		Stock stock = new Stock();
		stock.setBarcode(new BarcodeGenerator(new BarcodeDatatype(BarcodeType.SAS,null,null)).getBarcodeGenerated());
		StockVO stockVO = new StockVO();
		stockVO.setStock(stock);
		model.addAttribute("salesStockList" ,salesStockList);
		model.addAttribute("stockVO", stockVO);
		return "users/stock/stock-adder";
	}
	
	@RequestMapping(value = "/mortgageStock", method = RequestMethod.GET)
	public String goToMortgageStock(Model model){
		ArrayList<Stock> mortgageStockList = (ArrayList<Stock>) stockService.getAllStockByTypeIncludingExitDate(Stock.StockType.MORTGAGE);
		model.addAttribute("salesStockList" ,mortgageStockList);
		return "users/stock/stock-adder";
	}
	
	@RequestMapping(value = "/saveStock", method = RequestMethod.POST)
	public String saveStock(@ModelAttribute("stockVO") @Validated StockVO stockVO,
										 BindingResult result , Model model,final RedirectAttributes redirectAttributes ) {
		
		logger.info("Saving Stock");
		logger.info(stockVO.getStock().toString());
		logger.info(stockVO.getStockImage().getOriginalFilename());
		if(result.hasErrors()){
			logger.info("Saving Stock : in haserrors");
			model.addAttribute("stockVO",stockVO);
			return "users/stock/stock-adder";
		}else{
			logger.info("Saving Stock : in else");
			Stock stock = stockVO.getStock();
			try {
				logger.info("Saving Stock : imagesetter");
				stock.setBase64stockimage(ZXingHelper.getBase64String(stockVO.getStockImage().getBytes()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			boolean saveStatus = stockService.saveStock(stock);
			if(saveStatus){
				logger.info("Saving Stock : in else "+ saveStatus + "barcode "+stockVO.getStock().getBarcode());
				Stock addedstock = (Stock)stockService.fetchStockByBarcode(stockVO.getStock().getBarcode());
				redirectAttributes.addFlashAttribute("msg" , "Stock saved successfully with barcode "+addedstock.getBarcode());
				redirectAttributes.addFlashAttribute("css","success");
				return "redirect:/stock/stockAdder";
			}else{
				logger.info("Saving Stock : in else "+ saveStatus);
				model.addAttribute("msg" , "Error occurred while saving stock with barcode "+stockVO.getStock().getBarcode());
				model.addAttribute("stockVO",stockVO);
				return "users/stock/stock-adder";
			}
		}
	}
	
	/*@PostMapping(value = "/saveStock", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public String saveStock(@ModelAttribute("stockVO") @Validated StockVO stockVO,
			 BindingResult result , Model model,final RedirectAttributes redirectAttributes ) {
		
	}*/
}
