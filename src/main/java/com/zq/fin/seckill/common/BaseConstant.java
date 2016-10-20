/*******************************************************************************
 * Copyright (c) 2005, 2015 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.zq.fin.seckill.common;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 公用衡量
 * 
 */
public class BaseConstant {
	
	public static final String START = "[start]";

	public static final String END = "[end]";
	
	//激活状态
	public static String activate_state = "0";
	
	//正常状态
	public static String normal_state="1";
	
	//失效状态
	public static String failure_state="-1";
	
	/** 用于获取静态配置值 */
	private static Properties props = null;
	
	/** 验证码js */
	public static String LOGINUTIL_URL;
	/** 验证码对比图片 */
	public static String CODERECOGNITIONUTIL_URL;
	
	static {
		try {
			props = PropertiesLoaderUtils.loadAllProperties("config.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/** 验证码js */
		LOGINUTIL_URL = props.getProperty("loginutil.url");
		/** 验证码对比图片 */
		CODERECOGNITIONUTIL_URL = props.getProperty("coderecognitionutil.url");
	}
	
}