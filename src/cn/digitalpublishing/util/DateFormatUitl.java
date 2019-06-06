package cn.digitalpublishing.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 日期格式转换工具类
 * String2Date或者Date2String
 * 针对不同的日期格式进行相互转换
 * @author ggh
 *
 */
public class DateFormatUitl {
	
	private static final String PATTERN_DATE = "yyyy-MM-dd";
	private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	public static String GMTDateToSimpleDateString(String GMTDateString){
		java.text.SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.US);
		Date d;
		try {
			d = sdf.parse(GMTDateString);
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		} else {
			try {
				return DateFormatUtils.format(date, pattern);
			} catch (Exception e) {
				return null;
			}
		}
	}
	
	public static String formatDate(Date date) {
		return format(date, PATTERN_DATE);
	}
	
	public static String formatDateTime(Date date) {
		return format(date, PATTERN_DATE_TIME);
	}
	public static Date stringToDate(String date) {
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");	
		Date time=null;		
		try {
			if(date!=null&&!"".equals(date)){
				time = format.parse(date);		
			}else{
				time = null;
			}
		} catch (ParseException e) {			
			e.printStackTrace();		
		}		
		return time;
	}
	public static Date stringToDatetime(String date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		Date time = null;		
		try {
			if(date!=null&&!"".equals(date)){
				time = format.parse(date);		
			}else{
				time = null;
			}
		} catch (ParseException e) {			
			e.printStackTrace();		
		}		
		return time;
	}
	/**
	 * 获取系统当前时间
	 * @return 返回规定格式的数据 例如：20140710170735665
	 */
	public static String formatString(){
		return String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%1$tL", System.currentTimeMillis());
	}
	/**
	 * 获取当前年
	 * @return yyyy
	 */
	public static String getYear() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return format.format(date).toString();
	}
	
	/**
	 * 获取当前月
	 * @return MM
	 */
	public static String getMonth() {
		SimpleDateFormat format = new SimpleDateFormat("MM");
		Date date = new Date();
		return format.format(date).toString();
	}
	
	/**
	 * 获取当前日
	 * @return dd
	 */
	public static String getDay(){
		SimpleDateFormat format = new SimpleDateFormat("dd");
		Date date = new Date();
		return format.format(date).toString();
	}
}