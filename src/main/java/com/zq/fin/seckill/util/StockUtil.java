package com.zq.fin.seckill.util;

import com.zq.fin.seckill.common.BaseConstant;

public class StockUtil {
	
	/**
	 * 传入股票代码，判断是上证还是深证
	 */
	public static String judgeStock(String stock){
		
		String header = stock.substring(0,2);
		
		if("30".equals(header)
				||"20".equals(header)
				||"00".equals(header)){
			//sz
			return BaseConstant.SZ;
		} else if("60".equals(header)
				||"51".equals(header)
				||"90".equals(header)
				||"50".equals(header)){
			//sh
			
			return BaseConstant.SH;
		} else {
			//报错
			return null;
		}
	}
	
}
