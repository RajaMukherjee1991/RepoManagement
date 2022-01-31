package com.repo.gbj.dao.manager.implementor;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.repo.gbj.dao.Queries;
import com.repo.gbj.dao.manager.SettingManager;
import com.repo.gbj.dao.mapper.SettingMapper;
import com.repo.gbj.model.UserSettings;

@Repository
public class SettingManagerImpl implements SettingManager{

	private final Logger logger = LoggerFactory.getLogger(SettingManagerImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	
	public boolean saveSetting(UserSettings userSettings) {
		boolean saveSettingStatus = false;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("licenseno",userSettings.getLicenseNo());
		paramSource.addValue("shopaddress",userSettings.getShopAddress());
		paramSource.addValue("shopphoneno1",userSettings.getShopPhone1());
		paramSource.addValue("shopphoneno2",userSettings.getShopPhone2());
		paramSource.addValue("gstno",userSettings.getGstNumber());
		paramSource.addValue("goldRate",userSettings.getGoldRate());
		paramSource.addValue("termsAndConditions",userSettings.getTermsAndConditions());
		paramSource.addValue("BARCODE",userSettings.getBarcodeSeriesNo());
		
		int noOfrows = namedParameterJdbcTemplate.update(Queries.SAVE_SETTINGS, paramSource);
		if(noOfrows>0){
			saveSettingStatus = true; 
		}
		return saveSettingStatus;
	}

	public UserSettings fetchCurrentSetting() {
		logger.info("findEmployeeforThisCredential() : ");

		ArrayList<UserSettings> userSettings =null;
		try{
			userSettings = (ArrayList<UserSettings>)namedParameterJdbcTemplate.query(Queries.FETCH_CURRENT_SETTINGS, new SettingMapper());
		}catch(Exception e){
			e.printStackTrace();
		}
		if(userSettings.isEmpty()){
			return null;
		}else{
			return userSettings.get(0);
		}
	}
	public boolean updateSetting(UserSettings userSettings) {
		boolean updteSettingStatus = false;
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("usersetting_id",userSettings.getSettingID());
		paramSource.addValue("licenseno",userSettings.getLicenseNo());
		paramSource.addValue("shopaddress",userSettings.getShopAddress());
		paramSource.addValue("shopphoneno1",userSettings.getShopPhone1());
		paramSource.addValue("shopphoneno2",userSettings.getShopPhone2());
		paramSource.addValue("gstno",userSettings.getGstNumber());
		paramSource.addValue("goldRate",userSettings.getGoldRate());
		paramSource.addValue("termsAndConditions",userSettings.getTermsAndConditions());
		paramSource.addValue("BARCODE",userSettings.getBarcodeSeriesNo());
		
		int noOfrows = namedParameterJdbcTemplate.update(Queries.UPDATE_CURRENT_SETTINGS, paramSource);
		if(noOfrows>0){
			updteSettingStatus = true; 
		}
		
		return updteSettingStatus;
	}

}
