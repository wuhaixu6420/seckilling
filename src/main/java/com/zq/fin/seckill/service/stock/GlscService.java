package com.zq.fin.seckill.service.stock;

import com.zq.fin.seckill.dto.DataResult;
import com.zq.fin.seckill.dto.reg.RegModelResult;
import com.zq.fin.seckill.entity.model.AssetGlscMode;
import com.zq.fin.seckill.entity.model.ClinchdealGLscModel;

/**
 * 用于国联证券账户的相关业务
 * 逻辑层
 * 
 * @author Y-Y
 * @since 2016年10月20日16:47:19
 **/
public interface GlscService {
	
	// 个人持仓信息
	static String chicang_url = "https://trade.glsc.com.cn/content/Zjgf.aspx";
	
	/**
	 * 登录账户
	 * @param glscLoginServiceModel
	 * @return
	 */
	RegModelResult glscLogin();
	
	/**
	 * 自动买入
	 * @return
	 */
	RegModelResult glscBuy(AssetGlscMode assetGlscMode, ClinchdealGLscModel clinchdealGLscModel);
	
	/**
	 * 自动卖出
	 * @return
	 */
	RegModelResult glscSale(AssetGlscMode assetGlscMode, ClinchdealGLscModel clinchdealGLscModel);
	
	/**
	 * 获取持仓信息
	 * @return
	 */
	DataResult<?> glscGetPosition();
	
	/**
	 * 获取当前资金信息
	 * @return
	 */
	DataResult<?> glscGetAsset();
	
	/**
	 * 根据雪球网API，获取股票信息
	 * @return
	 */
	DataResult<?> getStockInfoForXueQiu();
	
	/**
	 * 获取当日成交记录
	 * @param pin
	 * @return
	 */
	DataResult<?> nowDayClinchdeal(String pin);
	
	/**
	 * 自动跟单
	 * @return
	 */
	DataResult<?> automaticDocumentary();
}
