package com.zq.fin.seckill.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author admin create Y-Y 2016年8月19日16:44:39
 */
@Controller
// @Service @Component 放入spring容器中
@RequestMapping("/index")
// url:/模块/资源/{id}/细分/seckill/list
public class IndexController {
	
	/**
	 * 打开登陆页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "index";
	}
}
