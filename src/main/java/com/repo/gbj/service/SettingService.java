package com.repo.gbj.service;

import com.repo.gbj.model.UserSettings;

public interface SettingService {

	 boolean saveSettings(UserSettings userSettings);
	 UserSettings fetchCurrentSetting();
	 boolean updateSettings(UserSettings userSettings);
}
