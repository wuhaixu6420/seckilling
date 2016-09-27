/**
 * 
 */
package com.zq.fin.seckill.util;

/**
 * @author admin
 *
 */
public class ObjectUtil {
	
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;

		} else if (obj instanceof String) {
			String str = String.valueOf(obj);
			if (str.length() == 0) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
}
