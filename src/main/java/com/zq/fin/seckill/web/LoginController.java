package com.zq.fin.seckill.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zq.fin.seckill.entity.User;
import com.zq.fin.seckill.service.UserService;
import com.zq.fin.seckill.util.MD5Util;
import com.zq.fin.seckill.util.ObjectUtil;

/**
 * 
 * @author admin create Y-Y 2016年8月19日16:44:39
 */
@Controller
// @Service @Component 放入spring容器中
@RequestMapping("/login")
// url:/模块/资源/{id}/细分/seckill/list
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 打开登陆页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "login";
	}

	/**
	 * shiro的验证无法直接使用，所以用
	 * SecurityUtils.getSubject().login(xxx,yyy)
	 * 作为登录条件
	 * @param username
	 * @param password
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, 
			@RequestParam(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM) String password, 
			Model model, HttpServletRequest request) {
		//验证登录用户名密码
		User user = userService.authentication(new User(username, MD5Util.MD5(password)));
		if(ObjectUtil.isEmpty(user)){
			model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
			model.addAttribute("message", "用户名或密码错误");
			return "login";
		} else {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("user", user);
			try {
				SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
				model.addAttribute("message", "用户名或密码错误");
				return "login";
			}
			return "redirect:index";
		}
	}
	
	/**
	 * 因为使用sercurityRealm失败，考虑到login需要弹出框
	 */
//	/**
//	 * 登录失败的场合，才执行该方法
//	 * @param userName
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(method = RequestMethod.POST)
//	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username, Model model,HttpServletRequest request) {
//		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
//		model.addAttribute("message", "用户名或密码错误");
//		return "login";
//	}
}
