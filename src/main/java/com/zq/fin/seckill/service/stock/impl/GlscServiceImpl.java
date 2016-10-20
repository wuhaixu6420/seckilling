package com.zq.fin.seckill.service.stock.impl;

import com.zq.fin.module_simustk.entity.ServiceAccountModel;
import com.zq.fin.module_simustk.util.LoginUtil;
import com.zq.fin.module_simustk.util.ObjectUtil;
import com.zq.fin.module_simustk.util.LoginUtil.ResponsObj;
import com.zq.fin.seckill.service.stock.GlscService;

/**
 * 用于国联证券账户的相关业务
 * 逻辑层
 * 
 * @author Y-Y
 * @since 2016年10月20日16:47:19
 **/
public class GlscServiceImpl implements GlscService {
	
	
	private String getCookie(ServiceAccountModel serviceAccountModel) {
		
		String cookie = null;
		
		try {
			
			ResponsObj responsObj = LoginUtil.pageinit(String.valueOf(serviceAccountModel.getUserid()));
			if(ObjectUtil.isEmpty(responsObj)){
				System.out.println("cookie is null");
				return cookie;
			}
			System.out.println("1. cookie=" + responsObj.getCookie());
			
			LoginUtil.imgCookie(responsObj, String.valueOf(serviceAccountModel.getUserid()));
			System.out.println("2. cookie=" + responsObj.getCookie());
			
			String code = LoginUtil.identifyImg();
			System.out.println("3. code=" + code);
			
			cookie = LoginUtil.loginCookie(code, serviceAccountModel.getStckaccount(), serviceAccountModel.getPw(), responsObj, String.valueOf(serviceAccountModel.getUserid()));
			System.out.println("4. cookie=" + cookie);
		
			//更新cookie，或者是存入cookie
			serviceAccountModel.setCookie(cookie);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cookie;
	}
}
