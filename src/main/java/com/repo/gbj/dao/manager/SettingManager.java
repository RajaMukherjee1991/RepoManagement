package com.repo.gbj.dao.manager;

import com.repo.gbj.model.UserSettings;

public interface SettingManager {
	
	 boolean saveSetting(UserSettings userSettings);
	 UserSettings fetchCurrentSetting();
	 boolean updateSetting(UserSettings userSettings);

}
