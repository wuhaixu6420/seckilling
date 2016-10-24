package com.zq.fin.seckill.web.stock;

import java.net.ConnectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zq.fin.seckill.common.BaseConstant;
import com.zq.fin.seckill.common.BaseGroup.GroupGlscLogin;
import com.zq.fin.seckill.dto.DataRseult;
import com.zq.fin.seckill.dto.GlscLoginServiceModel;
import com.zq.fin.seckill.dto.reg.RegModelResult;
import com.zq.fin.seckill.entity.model.StockBusinessModel;
import com.zq.fin.seckill.enums.StockStatEnum;
import com.zq.fin.seckill.service.stock.GlscService;
import com.zq.fin.seckill.util.LoginUtil;
import com.zq.fin.seckill.web.BaseController;

/**
 * 该class只是用于国联证券账户的相关操作
 * 
 * 例如：
 * 登录、买入、卖出、自动跟盘
 * 
 * @author admin create Y-Y 2016年8月19日16:44:39
 */
@Controller
@RequestMapping("/glsc")
public class GlscController extends BaseController {
	
	@Autowired
	GlscService glscService;
	
	/**
	 * 用于跳转证券登录页面
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String LoginGlsc(){
		return "stock/login";
	}
	
	/**
	 * ajax提交证券信息
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public RegModelResult LoginGlsc(@Validated(GroupGlscLogin.class) GlscLoginServiceModel glscLoginServiceModel){
		//将证券信息存放在类中，用于证券登录使用
		BaseConstant.glscLoginServiceModel = glscLoginServiceModel;
		//证券登录
		RegModelResult regModelResult = glscService.glscLogin();
		//返回，成功  跳转，失败  继续登录
		return regModelResult;
	}
	
	/**
	 * 自动登录中
	 * 考虑中...如何实现自动登录页面效果
	 * @return
	 */
	@RequestMapping(value = "/automatic", method = RequestMethod.GET)
	@ResponseBody
	public RegModelResult automaticLoginGlsc(){
		//调取config中文件信息
		//自动登录
		glscService.getGlscLoginServiceModelforConfig();
		//证券登录
		RegModelResult regModelResult = glscService.glscLogin();
		//返回，成功  跳转，失败  继续登录
		return regModelResult;
	}
	
	/**
	 * 获取持仓信息
	 * @return
	 */
	@RequestMapping(value = "/position", method = RequestMethod.GET)
	@ResponseBody
	public DataRseult<?> glscGetPosition(){
		DataRseult<?> dataRseult = glscService.glscGetPosition();
		return dataRseult;
	}
	
	/**
	 * 证券买入
	 * @return
	 */
	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	@ResponseBody
	public RegModelResult buyGlsc(String stockCode, String price, String num){
		try {
//			LoginUtil.buyStockToGlsc(glscLoginServiceModel, new StockBusinessModel(StockStatEnum.GDTYPE_SH, "502005", "1.160", "100"));
			LoginUtil.buyStockToGlsc(glscLoginServiceModel, new StockBusinessModel(StockStatEnum.GDTYPE_SH, "502005", "1.160", "100"));
		} catch (ConnectException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 证券卖出
	 * @return
	 */
	@RequestMapping(value = "/sell", method = RequestMethod.GET)
	@ResponseBody
	public RegModelResult sellGlsc(String stockCode, String price, String num){
		try {
//			LoginUtil.sellStockToGlsc(glscLoginServiceModel, new StockBusinessModel(StockStatEnum.GDTYPE_SH, "502005", "1.160", "100"));
			LoginUtil.sellStockToGlsc(glscLoginServiceModel, new StockBusinessModel(StockStatEnum.GDTYPE_SH, "502005", "1.160", "100"));
		} catch (ConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(){
		return "stock/detail";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public String getStockInfoForXueQiu(){
		return null;
	}
	
}
