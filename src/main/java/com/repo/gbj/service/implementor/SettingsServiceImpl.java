package com.repo.gbj.service.implementor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repo.gbj.dao.manager.SettingManager;
import com.repo.gbj.model.UserSettings;
import com.repo.gbj.service.SettingService;

@Service("settingService")
public class SettingsServiceImpl implements SettingService{

	private final Logger logger = LoggerFactory.getLogger(SettingsServiceImpl.class);
	
	SettingManager settingManager;
	
	public boolean saveSettings(UserSettings userSettings) {
		return settingManager.saveSetting(userSettings);
	}

	public UserSettings fetchCurrentSetting() {
		return settingManager.fetchCurrentSetting();
	}

	@Autowired
	public void setSettingManager(SettingManager settingManager) {
		this.settingManager = settingManager;
	}

	public boolean updateSettings(UserSettings userSettings) {
		return settingManager.updateSetting(userSettings);
	}

}
