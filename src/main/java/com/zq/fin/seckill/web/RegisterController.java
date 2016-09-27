package com.zq.fin.seckill.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zq.fin.seckill.common.BaseGroup.GroupVerify;
import com.zq.fin.seckill.dto.reg.RegModelResult;
import com.zq.fin.seckill.dto.reg.RegModelVerify;
import com.zq.fin.seckill.exception.ExceedException;
import com.zq.fin.seckill.exception.UpdateErrorException;
import com.zq.fin.seckill.service.RegisterService;
import com.zq.fin.seckill.util.ObjectUtil;

/**
 * 
 * @author admin create Y-Y 2016年8月19日16:44:39
 */
@Controller
// @Service @Component 放入spring容器中
@RequestMapping("reg")
// url:/模块/资源/{id}/细分/seckill/list
public class RegisterController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RegisterService registerService;
	/**
	 * 打开注册页
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String reg_get(HttpServletRequest request) {
		return "reg";
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String reg_post(@Validated(GroupVerify.class) RegModelVerify regModelVerify, BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			// 将出错信息放到model
			validate(result, model);
			// 将regModel 放到model
			model.addAttribute("regModel", regModelVerify);
			// 返回 页面
			return "reg";
		}
		
		try {
			RegModelResult regModelResult = registerService.selectByUsername(regModelVerify);
			if(regModelResult.isSuccess()){
				//true  创建成功
				//创建成功之后，用户登录，跳转之前的页面（java没有办法直接使用跳转前一页面，可以使用加跳转参数，进行跳转页面）
				if(ObjectUtil.isNotEmpty(regModelVerify.getRedirectUrl())){
					//目测这一条好像不会用到T-T
					return "redirect:" + regModelVerify.getRedirectUrl();
				} else {
					//如果没有跳转参数，可以直接用首页
					return "redirect:index";
				}
			} else {
				//false  创建失败
				//将创建的参数重新返回页面
				model.addAttribute("regModel", regModelVerify);
				return "reg";
			}
		} catch (UpdateErrorException e) {
			logger.info(e.getMessage());
			throw e;
		} catch (ExceedException e) {
			logger.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}
	}
}
