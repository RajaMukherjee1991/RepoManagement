package com.repo.gbj.web;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.repo.gbj.service.LoginService;
import com.repo.gbj.validator.LoginValidator;

@Controller
@SessionAttributes("credentials")
public class EmployeeLoginController {

	private final Logger logger = LoggerFactory.getLogger(EmployeeLoginController.class);
	
	@Autowired
	LoginValidator loginValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(loginValidator);
	}
	
	private LoginService loginService;

	@Autowired
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
		
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		//model.addAttribute("credentials", new EmployeeCredentials()); 
		return "users/login";
	}

	@RequestMapping(value="/loginError", method = RequestMethod.GET)
	public String loginError(ModelMap model) {
		model.addAttribute("error", "true");
		return "users/login";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName()
			+ ", You can not access this page!");
		} else {
			model.addObject("msg",
			"You can not access this page!");
		}

		model.setViewName("users/403");
		return model;
	}
}
