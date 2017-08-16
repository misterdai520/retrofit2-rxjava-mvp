package com.petecc.pro.peteccenforcesystem.utils;

import android.widget.EditText;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private final static Pattern emailer = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	public static final String DATE_PATTERN = "HH:mm:ss";

	/**
	 * @param currentTime
	 * @param datePattern
	 * @return 日期格式转换
	 */
	public static final String translateTimeStampToDate(String currentTime,
			String datePattern) {
		try {
			if (datePattern == null || currentTime.equals(""))
				return "";
			String timeStamp = String.valueOf(currentTime).substring(0, 10);
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern);

			String dateTime = sdf.format(Long.parseLong(String
					.valueOf(timeStamp)) * 1000);
			return dateTime;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 非空判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null || str.length() == 0 || "".equals(str.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断表中是否有未填项
	 * @param strs
	 * @return
	 */
	public static boolean hasNull(String strs) {
		String[] str = strs.split("@");
		for (int i = 0; i < str.length; i++) {
			if (str[i] == null || str[i].equals("")) {
				return true;
			}
		}
		return false;
	}
	

	/**
	 * 获取当前的时间字符串
	 * 
	 * @author wangw
	 * @return
	 */
	public static String currentTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date(System.currentTimeMillis());
		return sdf.format(date);
	}

	/**
	 * 获取当前的时间字符串
	 *
	 * @author wangw
	 * @return
	 */
	public static String currentTimeString(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date(System.currentTimeMillis());
		return sdf.format(date);
	}

	/**
	 * 將日曆得信息轉換成指定格式得日期
	 * @param calendar
	 * @param style
	 * @return
	 */
	public static String clanderTodatetime(Calendar calendar, String style) {
		SimpleDateFormat formatter = new SimpleDateFormat(style);
		return formatter.format(calendar.getTime());
	}
	/**
	  * 将长时间格式字符串转换为时间 yyyy-MM-dd
	  * 
	  * @param strDate
	  * @return
	  */
	public static Date strToDate(String strDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  ParsePosition pos = new ParsePosition(0);
	  Date strtodate = formatter.parse(strDate, pos);
	  return strtodate;
	}
	 
	/** 
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss  
	 *    
	 * @param dateDate  
	 * @return  
	 */
	public static String dateToStrLong(Date dateDate) {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	  String dateString = formatter.format(dateDate);
	  return dateString;
	}



	/**
	 * 把毫秒转换成：1:20:30这里形式
	 * @param timeMs
	 * @return
	 */
	public static String stringForTime(int timeMs) {
		StringBuilder mFormatBuilder = new StringBuilder();
		Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

		int totalSeconds = timeMs / 1000;
		int seconds = totalSeconds % 60;

		int minutes = (totalSeconds / 60) % 60;

		int hours = totalSeconds / 3600;

		mFormatBuilder.setLength(0);
		if (hours > 0) {
			return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds)
					.toString();
		} else {
			return mFormatter.format("%02d:%02d", minutes, seconds).toString();
		}
	}

	public static String getEditTextString(EditText edittext) {
		if (edittext.getText() == null) {
			return "";
		} else {
			return edittext.getText().toString();
		}
	}

	/**
	 * 分割拼接字符串
	 * @param str 拼接字符串
	 * @param sep 分隔符
	 * @param position 位置
	 * @return
	 */
	public static String getPingString(String str, String sep, int position) {
		String[] strs = str.split(sep);
		if (position < strs.length) {
			return strs[position];
		}
		return "";

	}


	/**
	 * 验证是否有效车牌号
	 * @param plateno
	 * @return
     */
	public static boolean isValidPlate(String plateno) {
		if (StringUtils.isNull(plateno)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5|WJ]{1}[A-Z0-9]{6}$");
		Matcher matcher = pattern.matcher(plateno);
		if (!matcher.matches()) {
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 车牌的大小写转换
	 * @param plateNum
	 * @return
     */
	public static String checkPlate(String plateNum){
		return plateNum.toUpperCase();
	}

	/**
	 * 手机号验证
	 * @param mobiles
	 * @return
     */
	public static boolean isMobileNumber(String mobiles) {
		return Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}")
				.matcher(mobiles).matches();
	}






}
