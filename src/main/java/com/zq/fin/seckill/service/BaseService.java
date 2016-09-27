/*******************************************************************************
 * Copyright (c) 2015, 2016 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/

package com.zq.fin.seckill.service;

import org.slf4j.Logger;
import org.springside.modules.utils.Clock;

import com.zq.fin.seckill.common.BaseConstant;

public class BaseService extends BaseConstant{
	
	/** 时钟 */
	protected Clock clock = Clock.DEFAULT;
	
	/**
	 * log记录start（Service）
	 */
	protected static void logInfoStart(Logger logger, String methodname){
		
		logger.info(methodname + START);
	}
	
	/**
	 * log记录end（Service）
	 */
	protected static void logInfoEnd(Logger logger, String methodname){
		
		logger.info(methodname + END);
	}
	
}
