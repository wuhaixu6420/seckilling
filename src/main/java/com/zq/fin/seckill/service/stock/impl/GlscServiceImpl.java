package com.zq.fin.seckill.service.stock.impl;

import java.net.ConnectException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zq.fin.seckill.dto.DataRseult;
import com.zq.fin.seckill.dto.reg.RegModelResult;
import com.zq.fin.seckill.entity.model.PositionGlscModel;
import com.zq.fin.seckill.service.BaseService;
import com.zq.fin.seckill.service.stock.GlscService;
import com.zq.fin.seckill.util.HtmlUtil;
import com.zq.fin.seckill.util.HttpsRequest;
import com.zq.fin.seckill.util.LoginUtil;
import com.zq.fin.seckill.util.StockUtil;

/**
 * 用于国联证券账户的相关业务
 * 逻辑层
 * 
 * @author Y-Y
 * @since 2016年10月20日16:47:19
 **/
@Service
public class GlscServiceImpl extends BaseService implements GlscService {
	private static Logger logger = LoggerFactory.getLogger(GlscServiceImpl.class);
	
	@Override
	public RegModelResult glscLogin() {
		try {
			//进行登录，获取cookie
			LoginUtil.getCookie(glscLoginServiceModel);
		} catch (NullPointerException e) {
			//没有获取到cookie
			return new RegModelResult(false, "登录失败，没有获取cookie，请重新登录");
		} catch (Exception e) {
			//获取cookie异常
			return new RegModelResult(false, "登录失败，获取cookie异常，请重新登录");
		}
		return new RegModelResult(true, "登录成功");
	}
	
	@Override
	public void getGlscLoginServiceModelforConfig(){
		
		glscLoginServiceModel.setStckaccount(stckaccount);
		glscLoginServiceModel.setPw(stckaccountpw);
		
	}

	@Override
	public RegModelResult glscBuy(String stockCode, String price, String num) {
		StockUtil.judgeStock(stockCode);
		return null;
	}

	@Override
	public RegModelResult glscBuy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegModelResult glscSell(String stockCode, String price, String num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegModelResult glscSell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataRseult<List<PositionGlscModel>> glscGetPosition() {
		//获取持仓信息
		String request = HttpsRequest.XMLhttpRequest(chicang_url + "?" + System.currentTimeMillis(), "GET", glscLoginServiceModel.getCookie());
		
		List<PositionGlscModel> positionGlscModels = new ArrayList<PositionGlscModel>();
		
		String sub="交易链接出现异常断开，请重新登录交易或者关闭浏览器后重新登录";
		int errors=request.indexOf(sub);
		if(errors>0){
			logger.info("cookie错误----->"+sub);
			//没有获取到持仓信息，应该是cookie出现问题
			//重新获取cookie
			glscLogin();
			
			return new DataRseult<>(false, "cookie获取失败");
		} else {
			//正则，去掉多余的html标签
			Document doc = Jsoup.parse(request);
			Element masthead = doc.select("table.mtb").last();
			// 正则去标签
			String content = HtmlUtil.delHTMLTag_Position(masthead.toString());
			
			String[] a = content.split("abc");
			for (int i = 1; i < a.length; i++) {
				String[] b=a[i].split("stock");
				PositionGlscModel positionGlscModel  = new PositionGlscModel();
				positionGlscModel.setStockCode(b[0].trim());
				positionGlscModel.setStockName(b[1].trim());
				//证券数量放到股票余额
				positionGlscModel.setStockexcess(b[2].trim());
				//可卖数量放到可用余额
				positionGlscModel.setUsablestock(b[3].trim());
				//成本价
				positionGlscModel.setCostprice(b[4].trim());
				//最新市值
				positionGlscModel.setNewmarket_value(b[6].trim());
				//盈亏比
				positionGlscModel.setCysratio(b[8].trim());
				
				positionGlscModels.add(positionGlscModel);
			}
			
			return new DataRseult<List<PositionGlscModel>>(true, positionGlscModels);
		}
	}

	@Override
	public RegModelResult glscGetAsset() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DataRseult<?> getStockInfoForXueQiu(){
		
		try {
			String requestUrl = "https://xueqiu.com/stock/forchart/stocklist.json";
			
			Map<String, String> params = new HashMap<String, String>();
			
			params.put("symbol", "SH000001");
			params.put("period", "1d");
			params.put("one_min", "1");
			params.put("_", String.valueOf(new Date().getTime()));
			
			String str = HttpsRequest.net(requestUrl, params, "GET");
			
			System.out.println(str);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Test
	public void wee(){
		getStockInfoForXueQiu();
	}
	
}
