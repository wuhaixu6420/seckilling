package com.zq.fin.seckill.service.stock.impl;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zq.fin.seckill.dto.DataRseult;
import com.zq.fin.seckill.dto.reg.RegModelResult;
import com.zq.fin.seckill.entity.model.AssetGlscMode;
import com.zq.fin.seckill.entity.model.ClinchdealGLscModel;
import com.zq.fin.seckill.entity.model.PositionGlscModel;
import com.zq.fin.seckill.entity.model.StockBusinessModel;
import com.zq.fin.seckill.service.BaseService;
import com.zq.fin.seckill.service.stock.GlscService;
import com.zq.fin.seckill.util.DateUtils;
import com.zq.fin.seckill.util.HtmlUtil;
import com.zq.fin.seckill.util.HttpsRequest;
import com.zq.fin.seckill.util.LoginUtil;
import com.zq.fin.seckill.util.ObjectUtil;
import com.zq.fin.seckill.util.StockUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
			LoginUtil.getCookie();
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
	public RegModelResult glscBuy(AssetGlscMode assetGlscMode, ClinchdealGLscModel clinchdealGLscModel) {
		/**
		 * version 1.0
		 * 使用静态类，map
		 */
		//模式   操作,股票代码,数量
		String key = clinchdealGLscModel.getOperation() + "," + clinchdealGLscModel.getStockCode() + "," + clinchdealGLscModel.getClinchdealNum();
		//存在该账户
		if(ObjectUtil.isNotEmpty(clichdealMap.get(key))){
			//存在返回空
			return null;
		}
		//可用余额
		BigDecimal usableasset = new BigDecimal(assetGlscMode.getUsableasset());
		//总资产
		BigDecimal totalasset = new BigDecimal(assetGlscMode.getTotalassets());
		//获取股票代码的是SH/SZ
		String codeType = StockUtil.judgeStock(clinchdealGLscModel.getStockCode());
		
		String[] clichdealNum = clinchdealGLscModel.getClinchdealNum().split(",");
		//需要数量的比值
		BigDecimal bigDecimal = new BigDecimal(clichdealNum[1]).subtract(new BigDecimal(clichdealNum[0]));
		//获取当前买入的总价格
		bigDecimal = totalasset.multiply(bigDecimal).divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);
		//买入价格大于余额
		if(bigDecimal.compareTo(usableasset) > 0){
			//使用余额
			//计算 使用成交价格时，需要买入的数量
			bigDecimal = usableasset.divide(new BigDecimal(clinchdealGLscModel.getAverageprice()), 4, BigDecimal.ROUND_HALF_UP);
		} else {
			//使用买入价格
			bigDecimal = bigDecimal.divide(new BigDecimal(clinchdealGLscModel.getAverageprice()), 4, BigDecimal.ROUND_HALF_UP);
		}
		//考虑因为不是100的整数倍，所以整理，计算买入数量
		int gigNum = bigDecimal.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP).intValue();
		bigDecimal = new BigDecimal(gigNum*100);
		try {
			//买入
			String buyResult = LoginUtil.buyStockToGlsc(glscLoginServiceModel, new StockBusinessModel(stockMap.get(codeType), 
					clinchdealGLscModel.getStockCode(), clinchdealGLscModel.getAverageprice(), bigDecimal.toString()));
			
			//'以股东代码 沪A-A466777393进行交易不成功。\r\n证券系统返回的原因：(-59)[120147][当前时间不允许委托][curr_time=171446]！'
			if(!buyResult.contains("交易不成功")){
				//交易成功
				clichdealMap.put(key, clinchdealGLscModel);
			} else {
				//交易失败
			}
			return new RegModelResult(true, buyResult);
		} catch (ConnectException e) {
			//买入失败
			e.printStackTrace();
			return new RegModelResult(false, "买入异常");
		}
	}
	
	@Override
	public RegModelResult glscSale(AssetGlscMode assetGlscMode, ClinchdealGLscModel clinchdealGLscModel) {
		/**
		 * version 1.0
		 * 使用静态类，map
		 */
		//模式   操作,股票代码,数量
		String key = clinchdealGLscModel.getOperation() + "," + clinchdealGLscModel.getStockCode() + "," + clinchdealGLscModel.getClinchdealNum();
		//存在该账户
		if(ObjectUtil.isNotEmpty(clichdealMap.get(key))){
			//存在返回空
			return null;
		}
		//总资产
		BigDecimal totalasset = new BigDecimal(assetGlscMode.getTotalassets());
		//获取股票代码的是SH/SZ
		String codeType = StockUtil.judgeStock(clinchdealGLscModel.getStockCode());
		
		String[] clichdealNum = clinchdealGLscModel.getClinchdealNum().split(",");
		//需要数量
		BigDecimal bigDecimal = new BigDecimal(0.00);
		//如果是清仓
		if(new BigDecimal(0.00).compareTo(new BigDecimal(clichdealNum[1])) == 0){
			DataRseult<?> dataRseult_position = glscGetPosition();
			if(ObjectUtil.isNotEmpty(dataRseult_position.getData()) && dataRseult_position.getData() instanceof List){
				@SuppressWarnings("unchecked")
				List<PositionGlscModel> positionGlscModels = (List<PositionGlscModel>)dataRseult_position.getData();
				if(!positionGlscModels.isEmpty()){
					for(PositionGlscModel positionGlscModel : positionGlscModels){
						if(clinchdealGLscModel.getStockCode().equals(positionGlscModel.getStockCode())){
							//用股票的可用余额,不至于出现卖出当日买的
							//股数
							bigDecimal = new BigDecimal(positionGlscModel.getUsablestock());
							break;
						}
					}
				}
			}
		} else {
			//获取当前需要卖的比例
			BigDecimal decimal = new BigDecimal(clichdealNum[0]).subtract(new BigDecimal(clichdealNum[1]));
			//获取当前卖的总价格
			BigDecimal pricedecimal = totalasset.multiply(decimal).divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);
			//计算需要卖出的数量
			bigDecimal = pricedecimal.divide(new BigDecimal(clinchdealGLscModel.getAverageprice()), 4, BigDecimal.ROUND_HALF_UP);
			//当前数量可能出现小数，或者说是不是100的整数
			int gigNum = bigDecimal.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP).intValue();
			bigDecimal = new BigDecimal(gigNum*100);
		}
		try {
			if(new BigDecimal(0.0).compareTo(bigDecimal) >= 0){
				return new RegModelResult(false, "卖出数量为0");
			}
			//卖出
			String buyResult = LoginUtil.saleStockToGlsc(glscLoginServiceModel, new StockBusinessModel(stockMap.get(codeType), 
					clinchdealGLscModel.getStockCode(), clinchdealGLscModel.getAverageprice(), bigDecimal.toString()));
			
			//'以股东代码 沪A-A466777393进行交易不成功。\r\n证券系统返回的原因：(-59)[120147][当前时间不允许委托][curr_time=171446]！'
			if(!buyResult.contains("交易不成功")){
				//交易成功
				clichdealMap.put(key, clinchdealGLscModel);
			} else {
				//交易失败
			}
			return new RegModelResult(true, buyResult);
		} catch (ConnectException e) {
			//买入失败
			e.printStackTrace();
			return new RegModelResult(false, "卖出异常");
		}
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
	public DataRseult<?> glscGetAsset() {
		String request = HttpsRequest.XMLhttpRequest(chicang_url + "?" + System.currentTimeMillis(), "GET", glscLoginServiceModel.getCookie());
		
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
			Element masthead = doc.select("table.mtb").first();
			String content = HtmlUtil.delHTMLTag_Asset(masthead.toString());
			
			String [] c = content.split("abc");
			String [] d = c[2].split("stock");
			
			AssetGlscMode assetGlscMode = new AssetGlscMode();
//			添加剩余金额
			assetGlscMode.setUsableasset(d[3].replace("\r|\n", "").replace("\\s*", ""));
//			添加股票市值
			assetGlscMode.setStockmarketvalue(d[5].replace("\r|\n", "").replace("\\s*", ""));
//			添加总资产
			assetGlscMode.setTotalassets(d[7].replace("\r|\n", "").replace("\\s*", ""));
//			添加总盈亏
			assetGlscMode.setTotaProfitloss(d[9].replace("\r|\n", "").replace("\\s*", ""));
			
			return new DataRseult<AssetGlscMode>(true, assetGlscMode);
		}
	}
	
	@Test
	public void wewe(){
		getStockInfoForXueQiu();
	}
	
	/**
	 * 待定
	 * TODO
	 */
	@Override
	public DataRseult<?> getStockInfoForXueQiu(){
		
		try {
			String requestUrl = "https://xueqiu.com/stock/forchart/stocklist.json";
			
			String params = "symbol=SH000001&period=1d&one_min=1&_=" + new Date().getTime();
			
			String str = HttpsRequest.sendGet(requestUrl, params);
			
			System.out.println(str);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public DataRseult<?> nowDayClinchdeal(String pin) {
		logInfoStart(logger, Thread.currentThread().getStackTrace()[1].getMethodName());
		
		List<ClinchdealGLscModel> clinchdealGLscModels = new ArrayList<ClinchdealGLscModel>();
		try {
			String params = "pin=" + URLEncoder.encode(pin, "UTF-8") + "&pageNum=1&_=" + String.valueOf(System.currentTimeMillis());
			
			String loginapi = "https://gupiao.jd.com/package/historyConverts";
			
			String str = HttpsRequest.sendGet(loginapi, params);
			
			str = str.substring(1);
			str = str.substring(0, str.length()-1);
			
			JSONObject jsonObject = JSONObject.fromObject(str);
			
			JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("data"));
			
			for(int a = 0; a < jsonArray.size(); a++){
				JSONObject jsonObj = jsonArray.getJSONObject(a);
				
				String date = jsonObj.getString("date");
				String time = jsonObj.getString("time");
				JSONArray array = JSONArray.fromObject(jsonObj.get("list"));
				
				if(DateUtils.isSameDate(date, DateUtils.date2String(new Date()))){
					
					for(int c = 0; c < array.size(); c++){
						JSONObject json = array.getJSONObject(c);
						System.out.println(json);
						
						ClinchdealGLscModel clinchdealGLscModel = new ClinchdealGLscModel();
						//证券名称
						clinchdealGLscModel.setStockName(json.getString("stockName"));
						//证券代码
						clinchdealGLscModel.setStockCode(json.getString("stockCode").substring(2));
						//操作类型
						clinchdealGLscModel.setOperation(json.getString("type"));
						//成本价
						clinchdealGLscModel.setAverageprice(json.getString("deal"));
						//成交股票余额比
						String posAfter = json.getString("posAfter").substring(0, json.getString("posAfter").lastIndexOf("%"));
						//初始股票余额比
						String posBefore = json.getString("posBefore").substring(0, json.getString("posBefore").lastIndexOf("%"));
						//成交数量
						clinchdealGLscModel.setClinchdealNum(posBefore + "," + posAfter);
						//成交时间
						clinchdealGLscModel.setClinchdealtm(DateUtils.date2String(DateUtils.string2Date(date + " " + time, "yyyy-MM-dd HH:mm:ss")));
						
						clinchdealGLscModels.add(clinchdealGLscModel);
						System.out.println("当日成交list--->" + clinchdealGLscModel);
						
					}
				}
			}
			logInfoEnd(logger, Thread.currentThread().getStackTrace()[1].getMethodName());
			return new DataRseult<List<ClinchdealGLscModel>>(true, clinchdealGLscModels);
		} catch (Exception e) {
			e.printStackTrace();
			logInfoEnd(logger, Thread.currentThread().getStackTrace()[1].getMethodName());
			return new DataRseult<>(false, "异常输出");
		}
	}
	
	/**
	 * 定时器，周一至周五，每一分钟获取数据
	 */
	@Override
	public DataRseult<?> automaticDocumentary(){
		//先登录状态
		getGLscStockAccountByConfig();
		//获取当前总资产
		DataRseult<?> dataRseultAsset = glscGetAsset();
		//不在登录状态
		if(!dataRseultAsset.isSuccess()){
			//登录
			glscLogin();
			//获取当前总资产
			dataRseultAsset = glscGetAsset();
		}
		//当前 data中属性  String 或者是 AssetGlscMode
		if(dataRseultAsset.getData() instanceof AssetGlscMode){
			//强转类型
			AssetGlscMode assetGlscMode = (AssetGlscMode)dataRseultAsset.getData();
			//可以考虑数据库换一个跟单用户
			DataRseult<?> dataRseult_Clinchdeal = nowDayClinchdeal(JDPIN);
			
			if(dataRseult_Clinchdeal.getData() instanceof List){
				@SuppressWarnings("unchecked")
				List<ClinchdealGLscModel> clinchdealGLscModels = (List<ClinchdealGLscModel>) dataRseult_Clinchdeal.getData();
				
				if(!clinchdealGLscModels.isEmpty()){
					
					List<RegModelResult> regModelResults = new ArrayList<RegModelResult>();
					
					for(ClinchdealGLscModel clinchdealGLscModel : clinchdealGLscModels){
						
						RegModelResult regModelResult = null;
						
						if(BUY.equals(clinchdealGLscModel.getOperation())){
							regModelResult = glscBuy(assetGlscMode, clinchdealGLscModel);
						} else if (SALE.equals(clinchdealGLscModel.getOperation())){
							regModelResult = glscSale(assetGlscMode, clinchdealGLscModel);
						} else {
							//异常
						}
						regModelResults.add(regModelResult);
					}
					return new DataRseult<>(true, regModelResults);
				} else {
					//当前没有成交记录
				}
				return new DataRseult<>(true, "当前没有成交记录");
			} else {
				//获取成交记录失败
			}
		} else {
			//获取当前总资产失败
			//重新登录
			glscLogin();
		}
		return new DataRseult<>(false, "异常状态");
	}
	
}
