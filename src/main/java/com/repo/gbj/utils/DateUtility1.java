package com.repo.gbj.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtility1 {

	private final static Logger logger = LoggerFactory.getLogger(DateUtility1.class);
	
	private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{
        put("^\\d{8}$", "yyyyMMdd");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
        put("^\\d{12}$", "yyyyMMddHHmm");
        put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
        put("^\\d{14}$", "yyyyMMddHHmmss");
        put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
        put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
        put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
        put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
        put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
        put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
        put("^\\d{1,3}\\s[a-z]{3,}\\s\\d{2}\\s\\d{1,2}:\\d{2}:\\d{2}\\s\\d{3}\\s\\d{4}$", "EEE MMM dd HH:mm:ss zzz yyyy");
    }};
	
	public static Date StringToSQLDateConverter(String dateValue){
		java.sql.Date sqlDate = null;
		try{
			if(!StringUtils.isEmpty(dateValue)){
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date = sdf1.parse(dateValue);
				sqlDate = new java.sql.Date(date.getTime());
				logger.info("SQL Date "+ sqlDate);
			}
		}catch(ParseException pe){
			logger.error("Error while converting Date to SQL Date format");
		}
		return sqlDate;
	}
	
	public static java.util.Date StringToDateConverter(String dateValue){
		java.util.Date utilDateType = null;
		try{
			if(!StringUtils.isEmpty(dateValue)){
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				utilDateType = sdf1.parse(dateValue);
			}
		}catch(ParseException pe){
			logger.error("Error while converting Date to SQL Date format");
		}
		return utilDateType;
	}
	
	public static String todaysDateinDDMMYYYYHHMMSS(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		java.util.Date currentTimestamp = new java.util.Date();
		String timeNow = sdf.format(currentTimestamp);
		logger.debug(timeNow);
		return timeNow;
		
	}
	
	public static String givenDateToStringinDD_MM_YYYY(java.util.Date givenDate){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String givenDateinString = sdf.format(givenDate);
		logger.debug(givenDateinString);
		return givenDateinString;
		
	}
	
	public static String todaysDateinStringDD_MM_YYYY(){
		java.util.Date date = new java.util.Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		    String strDate= formatter.format(date);  
		    logger.debug(strDate); 
		    return strDate;
	}
	
	public static Timestamp convertStringDatetoTimestamp(String givendate){
		Date date = StringToSQLDateConverter(givendate);
		Timestamp t = new Timestamp(date.getTime());
		return t;
	}
	
	/**
     * Determine SimpleDateFormat pattern matching with the given date string. Returns null if
     * format is unknown. You can simply extend DateUtil with more formats if needed.
     * @param dateString The date string to determine the SimpleDateFormat pattern for.
     * @return The matching SimpleDateFormat pattern, or null if format is unknown.
     * @see SimpleDateFormat
     */
    public static String determineDateFormat(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.toLowerCase().matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        return null; // Unknown format.
    }
	
	/*public static void main(String[] args) {
		logger.info("" +DateUtility.StringToSQLDateConverter(DateUtility.todaysDateinStringDD_MM_YYYY()));
	}*/
}
