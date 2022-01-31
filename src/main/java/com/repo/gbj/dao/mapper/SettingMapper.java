package com.repo.gbj.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.repo.gbj.model.UserSettings;

public class SettingMapper implements RowMapper<UserSettings>{

	public UserSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserSettings userSettings = new UserSettings();
		userSettings.setSettingID(rs.getInt("usersetting_id"));
		userSettings.setLicenseNo(rs.getString("licenseno"));
		userSettings.setGstNumber(rs.getString("gstno"));
		userSettings.setShopAddress(rs.getString("shopaddress"));
		userSettings.setShopPhone1(rs.getString("shopphoneno1"));
		userSettings.setShopPhone2(rs.getString("shopphoneno2"));
		userSettings.setBarcodeSeriesNo(String.valueOf(rs.getInt("BARCODE")));
		userSettings.setGoldRate(rs.getBigDecimal("goldRate"));
		userSettings.setTermsAndConditions(rs.getString("termsAndConditions"));
		
		return userSettings;
	}
}
