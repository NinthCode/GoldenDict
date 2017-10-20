package com.paouke.gdp.common.utils;

public final class StringUtils extends org.apache.commons.lang.StringUtils {
	private static final String TIME_FORMAT_SHORT = "yyyyMMddHHmmss";
	private static final String TIME_FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
	private static final String TIME_FORMAT_ENGLISH = "MM/dd/yyyy HH:mm:ss";
	private static final String TIME_FORMAT_CHINA = "yyyy年MM月dd日 HH时mm分ss秒";

	private static final String DATE_FORMAT_SHORT = "yyyyMMdd";
	private static final String DATE_FORMAT_NORMAL = "yyyy-MM-dd";
	private static final String DATE_FORMAT_ENGLISH = "MM/dd/yyyy";
	private static final String DATE_FORMAT_CHINA = "yyyy年MM月dd日";
	private static final String DATE_FORMAT_NORMAL2 = "yyyy.MM.dd";
	
	
	/***
	 * HTTP发送协议
	 */
	public static final String HTTP_TYPES="application/json";  
	public static final String HTTP_TYPES_TEXT="text/xml";  
	public static final String HTTP_CODES="UTF-8";

	public static String tropeSlashAnddollar(String src){
		return src.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\$","\\\\\\$");
	}

	public static String splitWord(String input) {
		if (isEmptyOrBlankString(input))
			return input;

		return input.replaceAll("[_*\\s]+", " ").replaceAll("([A-Z][a-z]+)|([0-9\\W]+)", " $0 ")
				.replaceAll("[A-Z]{2,}", " $0").replaceAll("\\s{2,}", " ").trim();
	}

	public static boolean isEmptyOrBlankString(String str) {
		return null == str || str.trim().isEmpty();
	}
}
