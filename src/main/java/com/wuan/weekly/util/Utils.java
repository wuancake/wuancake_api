package com.wuan.weekly.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	/** 
	 * 第一周第一天的日期
	 */
	public static final Date FIRSTDAY;

	static { 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = null;
		try {
			d  = format.parse("2015-10-26 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		FIRSTDAY = d;
	}
	
}
