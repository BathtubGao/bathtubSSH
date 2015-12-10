package com.bathtub.core.utils;

public class StringUtil
{
	public static boolean isNull(String str) {
		return str == null;
	}
	
	public static boolean isEmpty(String str) {
		return "".equals(str);
	}
	
	public static boolean isNullOrEmpty(String str) {
		return isNull(str) || isEmpty(str);
	}
	
	public static boolean isNotNullAndNotEmpty(String str) {
		return !isNullOrEmpty(str);
	}
}
