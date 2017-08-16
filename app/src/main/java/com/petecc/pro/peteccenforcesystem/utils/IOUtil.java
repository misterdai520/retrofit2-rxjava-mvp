package com.petecc.pro.peteccenforcesystem.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 * @author liyanying
 *
 */
public class IOUtil {
	static SharedPreferences pref;
	
	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * @author wangw
	 * @param input
	 * @return boolean
	 */
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

	
	/**
	 * 获取当前的时间字符串
	 * @author wangw
	 * @return
	 */
	public static String currentTimeString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		return sdf.format(date);		
	}
	
	public static String readString(String key) {
		if(pref == null) {
			pref = UIHelper.getApplication().getSharedPreferences(Constant.PREFERENCES_NAME, Context.MODE_APPEND);
		}
		return pref.getString(key, "");
	}
	
	public static void writeString(String key, String value) {
		if(pref == null) {
			pref = UIHelper.getApplication().getSharedPreferences(Constant.PREFERENCES_NAME, Context.MODE_APPEND);
		}
		Editor editor = pref.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static void removeString(String key) {
		if(pref == null) {
			pref = UIHelper.getApplication().getSharedPreferences(Constant.PREFERENCES_NAME, Context.MODE_APPEND);
		}
		Editor editor = pref.edit();
		editor.remove(key);
		editor.commit();
	}
	
	public static Boolean readBoolean(String key) {
		if(pref == null) {
			pref = UIHelper.getApplication().getSharedPreferences(Constant.PREFERENCES_NAME, Context.MODE_APPEND);
		}
		return pref.getBoolean(key, false);
	}
	
	public static void writeBoolean(String key, Boolean value) {
		if(pref == null) {
			pref = UIHelper.getApplication().getSharedPreferences(Constant.PREFERENCES_NAME, Context.MODE_APPEND);
		}
		Editor editor = pref.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public static void writeInt(String key, int value) {
		if(pref == null) {
			pref = UIHelper.getApplication().getSharedPreferences(Constant.PREFERENCES_NAME, Context.MODE_APPEND);
		}
		Editor editor = pref.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	public static int readInt(String key) {
		if(pref == null) {
			pref = UIHelper.getApplication().getSharedPreferences(Constant.PREFERENCES_NAME, Context.MODE_APPEND);
		}
		return pref.getInt(key, -1);
	}

}
