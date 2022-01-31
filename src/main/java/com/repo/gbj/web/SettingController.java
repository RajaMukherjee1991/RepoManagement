package com.repo.gbj.web;

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

import com.repo.gbj.model.UserSettings;
import com.repo.gbj.service.SettingService;
import com.repo.gbj.validator.SettingValidator;

@Controller
@RequestMapping("/setting")
public class SettingController {
	
	private final static Logger logger = LoggerFactory.getLogger(SettingController.class);

	@Autowired
	SettingValidator settingValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(settingValidator);
	}
	
	SettingService settingService;
	
	@RequestMapping
	public String displaySettingsPage(Model model){
		UserSettings currentUserSetting = settingService.fetchCurrentSetting();
		if(currentUserSetting!=null){
			model.addAttribute("userSettings",currentUserSetting);
		}else{
			model.addAttribute("userSettings",new UserSettings());
		}
		return "users/user/user-settings";
	}
	
	@RequestMapping(value="/saveUpdate", method = RequestMethod.POST)
	public String saveSettings(Model model,@ModelAttribute("userSettings") @Validated UserSettings userSettings,
			BindingResult result,final RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			model.addAttribute("isEditable", Boolean.TRUE);
			model.addAttribute("userSettings", userSettings);
			return "users/user/user-settings";
		}else if(userSettings.getSettingID()==0){
			boolean saveStatus = settingService.saveSettings(userSettings);
			if(saveStatus){
				redirectAttributes.addFlashAttribute("msg", "Settings saved Successfully !!");
				redirectAttributes.addFlashAttribute("css","success");
				model.addAttribute("isEditable", Boolean.FALSE);
				return "redirect:/setting";
			}else{
				redirectAttributes.addFlashAttribute("msg", "Error Saving settings !! Try again. ");
				redirectAttributes.addFlashAttribute("css","danger");
				return "redirect:/setting";
			}
		}else{
			boolean updateStatus = settingService.updateSettings(userSettings);
			if(updateStatus){
				logger.info("userSettings ::: Update done");
				redirectAttributes.addFlashAttribute("msg", "Settings updated Successfully !!");
				redirectAttributes.addFlashAttribute("css","success");
				model.addAttribute("isEditable", Boolean.FALSE);
				return "redirect:/setting";
			}else{
				logger.info("userSettings ::: Update not done");
				redirectAttributes.addFlashAttribute("msg", "Error updating settings !! Try again. ");
				redirectAttributes.addFlashAttribute("css","danger");
				return "redirect:/setting";
			}
		}
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public String editSettings(Model model){
		
		UserSettings currentUserSetting = settingService.fetchCurrentSetting();
		if(currentUserSetting !=null){
			model.addAttribute("isEditable", Boolean.TRUE);
			model.addAttribute("isUpdatable", Boolean.TRUE);
			model.addAttribute("userSettings",currentUserSetting);
		}else{
			model.addAttribute("isEditable", Boolean.TRUE);
			model.addAttribute("isUpdatable", Boolean.FALSE);
			model.addAttribute("userSettings",new UserSettings());
		}
		return "users/user/user-settings";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updateSettings(Model model,@ModelAttribute("userSettings") @Validated UserSettings userSettings,
			BindingResult result,final RedirectAttributes redirectAttributes){
		
		logger.info("userSettings :::: "+ userSettings.toString());
		
		if(result.hasErrors()){
			logger.info("userSettings ::: Has errors");
			model.addAttribute("isEditable", Boolean.TRUE);
			model.addAttribute("userSettings", userSettings);
			return "users/user/user-settings";
		}else{
			logger.info("userSettings :::NO errors");
			boolean updateStatus = settingService.updateSettings(userSettings);
			if(updateStatus){
				logger.info("userSettings ::: Update done");
				redirectAttributes.addFlashAttribute("msg", "Settings updated Successfully !!");
				redirectAttributes.addFlashAttribute("css","success");
				model.addAttribute("isEditable", Boolean.FALSE);
			}else{
				logger.info("userSettings ::: Update not done");
				redirectAttributes.addFlashAttribute("msg", "Error updating settings !! Try again. ");
				redirectAttributes.addFlashAttribute("css","danger");
			}
			logger.info("userSettings ::: sending to /settings");
			return "redirect:/setting";
		}
	}

	@Autowired
	public void setSettingService(SettingService settingService) {
		this.settingService = settingService;
	}
	
	
}
