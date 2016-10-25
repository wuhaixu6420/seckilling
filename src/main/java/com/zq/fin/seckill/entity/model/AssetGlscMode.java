package com.zq.fin.seckill.entity.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AssetGlscMode {

	/**
	 * 可用余额
	 */
	private String usableasset;
	
	/**
	 * 股票总市值
	 */
	private String stockmarketvalue;
	
	/**
	 * 总资产
	 */
	private String totalassets;
	
	/**
	 * 总盈亏
	 */
	private String TotaProfitloss;
	
	public AssetGlscMode() {
	}

	public String getUsableasset() {
		return usableasset;
	}

	public void setUsableasset(String usableasset) {
		this.usableasset = usableasset;
	}

	public String getStockmarketvalue() {
		return stockmarketvalue;
	}

	public void setStockmarketvalue(String stockmarketvalue) {
		this.stockmarketvalue = stockmarketvalue;
	}
	
	public String getTotalassets() {
		return totalassets;
	}

	public void setTotalassets(String totalassets) {
		this.totalassets = totalassets;
	}

	public String getTotaProfitloss() {
		return TotaProfitloss;
	}

	public void setTotaProfitloss(String totaProfitloss) {
		TotaProfitloss = totaProfitloss;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
