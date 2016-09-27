/*******************************************************************************
 * Copyright (c) 2005, 2015 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.zq.fin.seckill.common;


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
	
//	/** 用于获取静态配置值 */
//	private static Properties props = null;
//	
//	/** 股觅定时器接口 */
//	public static String GUMI_PHP_UPDATETIMING_API;
//	
//	static {
//		try {
//			props = PropertiesLoaderUtils.loadAllProperties("config.properties");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		/** 股觅  定时器接口*/
//		GUMI_PHP_UPDATETIMING_API = props.getProperty("gumi.php.updatetiming.api");
//	}
	
}