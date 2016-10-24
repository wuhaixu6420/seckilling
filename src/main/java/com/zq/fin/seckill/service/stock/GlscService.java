package com.zq.fin.seckill.service.stock;

import com.zq.fin.seckill.dto.DataRseult;
import com.zq.fin.seckill.dto.reg.RegModelResult;

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
	 * 通过config文件获取证券信息
	 * @return
	 */
	void getGlscLoginServiceModelforConfig();
	
	/**
	 * 手动买入
	 * @param glscLoginServiceModel
	 * @return
	 */
	RegModelResult glscBuy(String stockCode, String price, String num);
	
	/**
	 * 自动买入
	 * @return
	 */
	RegModelResult glscBuy();
	
	/**
	 * 手动卖出
	 * @param glscLoginServiceModel
	 * @return
	 */
	RegModelResult glscSell(String stockCode, String price, String num);
	
	/**
	 * 自动卖出
	 * @return
	 */
	RegModelResult glscSell();
	
	/**
	 * 获取持仓信息
	 * @return
	 */
	DataRseult<?> glscGetPosition();
	
	/**
	 * 获取当前资金信息
	 * @return
	 */
	RegModelResult glscGetAsset();
	
	/**
	 * 
	 * @return
	 */
	DataRseult<?> getStockInfoForXueQiu();
}
