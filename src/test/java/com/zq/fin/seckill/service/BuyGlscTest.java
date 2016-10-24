package com.zq.fin.seckill.service;

import java.net.ConnectException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zq.fin.seckill.common.BaseConstant;
import com.zq.fin.seckill.service.stock.GlscService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:applicationContext.xml",
	"classpath:spring-mvc.xml",
	"classpath:mybatis-config.xml",
	"classpath:applicationContext-shiro.xml"
	})
public class BuyGlscTest extends BaseConstant{
	
	@Autowired
	GlscService glscService;
	
	@Test
	public void buyTest() throws ConnectException{
		//登录
		glscService.getGlscLoginServiceModelforConfig();
		glscService.glscLogin();
		
	}

}
