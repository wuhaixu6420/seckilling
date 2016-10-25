/*******************************************************************************
 * Copyright (c) 2015, 2016 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/

package com.zq.fin.seckill.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springside.modules.utils.Clock;

import com.zq.fin.seckill.common.BaseConstant;
import com.zq.fin.seckill.entity.model.ClinchdealGLscModel;

public class BaseService extends BaseConstant{
	
	/** 时钟 */
	protected Clock clock = Clock.DEFAULT;
	
	protected static Map<String, ClinchdealGLscModel> clichdealMap = new HashMap<>();
	
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
