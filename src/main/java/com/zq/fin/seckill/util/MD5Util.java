package com.zq.fin.seckill.util;

import org.springframework.util.DigestUtils;

public class MD5Util {

	private static final String salt ="q2746024irs[df09^&*()g8234!@#$%^&*3654sdafgkxcvasliecbl}{:<>?sdf";
	
	public static String MD5(Object object){
		String base = String.valueOf(object) + salt;
		return DigestUtils.md5DigestAsHex(base.getBytes());
	}
}
