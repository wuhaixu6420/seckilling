package com.zq.fin.seckill.util;


public class StringUtil {

	/**
	 * 判空
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;

		} else if (str.length() == 0) {
			return true;

		}
		return false;
	}

	/**
	 * 非空
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
}
