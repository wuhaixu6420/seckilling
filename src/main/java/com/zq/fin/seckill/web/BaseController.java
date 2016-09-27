/*******************************************************************************
 * Copyright (c) 2015, 2016 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/

package com.zq.fin.seckill.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springside.modules.utils.Clock;

import com.zq.fin.seckill.common.BaseConstant;
import com.zq.fin.seckill.util.ObjectUtil;

/**
 * BaseController
 * 
 * @author Y_Y
 */


public class BaseController extends BaseConstant {
	
	/** 时钟 */
	protected Clock clock = Clock.DEFAULT;
	
	/** 跳转名称 */
	protected final static String REDIRECT_URL = "redirectUrl";
	
	/**
	 * log记录start（Ctrl）
	 */
	protected void logInfoStart(Logger logger, HttpServletRequest request, String methodname){
		
		logger.info("remote ip:" + getIpAddr(request) + " " + methodname + START);
	}
	
	/**
	 * log记录end（Ctrl）
	 */
	protected void logInfoEnd(Logger logger, HttpServletRequest request, String methodname){
		
		logger.info("remote ip:" + getIpAddr(request)  + " " + methodname + END);
		
	}
	
	/**
	 * 获取当前网络ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request){
		
		String ipAddress = request.getHeader("x-forwarded-for");
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
				//根据网卡取本机配置的IP
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress= inet.getHostAddress();
			}
		}
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
			if(ipAddress.indexOf(",")>0){
				ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
			}
		}
		
		return ipAddress; 
	}
	
	/* 后台认证，将错误信息传递到前台页面中 */
	/* 后台认证，将错误信息传递到前台页面中 */
	protected void validate(BindingResult result, Model model){
		ObjectError gloerror = result.getGlobalError();
		List<FieldError> errors= result.getFieldErrors();
		
		if(ObjectUtil.isNotEmpty(gloerror)){
			model.addAttribute("glo", gloerror.getDefaultMessage());
		}
		for(FieldError fieldError:errors){
			model.addAttribute(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}
	
	/**
	 * 将当前链接加入跳转链接中
	 * 只是在出现登录或者是注册页面的时候，
	 * 跳转到之前的页面
	 */
	protected void setRedirectUrlToHttpServletRequest(String redirectUrl, HttpServletRequest request){
		HttpSession httpSession = request.getSession();
		
		httpSession.setAttribute(REDIRECT_URL, redirectUrl);
	}
	
	/**
	 * 获取跳转链接
	 * HttpServletRequest
	 */
	protected String getRedirectUrlToHttpServletRequest(String redirectUrl, HttpServletRequest request){
		HttpSession httpSession = request.getSession();
		
		return String.valueOf(httpSession.getAttribute(REDIRECT_URL));
	}
}
