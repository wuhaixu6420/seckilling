package com.zq.fin.seckill.entity.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.zq.fin.seckill.enums.StockStatEnum;

public class StockBusinessModel {
	
//	/**
//	 * 股东代码	（0，1）
//	 */
//	private String gdtype;
//	/**
//	 * 股东代码	（沪A-A466777393，深A-0200381345）
//	 */
//	private String gdmc;
	
	private StockStatEnum stockStatEnum;
	
	/**
	 * 证券代码
	 */
	private String stockCode;
	
	/**
	 * 卖出/买入 价格
	 */
	private String price;
	
	/**
	 * 卖出/买入 数量
	 */
	private String stockNum;
	
	/**
	 * 报价方式	（0 默认，4，6）
	 */
	private String wtfs = "0";
	
	/**
	 * 默认（0）
	 */
	private String wtsltype = "0";
	
	public StockBusinessModel() {}

	public StockBusinessModel(StockStatEnum stockStatEnum, String stockCode, String price, String stockNum) {
		this.stockStatEnum = stockStatEnum;
		this.stockCode = stockCode;
		this.price = price;
		this.stockNum = stockNum;
	}

	public StockStatEnum getStockStatEnum() {
		return stockStatEnum;
	}

	public void setStockStatEnum(StockStatEnum stockStatEnum) {
		this.stockStatEnum = stockStatEnum;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStockNum() {
		return stockNum;
	}

	public void setStockNum(String stockNum) {
		this.stockNum = stockNum;
	}

	public String getWtfs() {
		return wtfs;
	}
	
	public void setWtfs(String wtfs) {
		this.wtfs = wtfs;
	}
	
	public String getWtsltype() {
		return wtsltype;
	}
	
	public void setWtsltype(String wtsltype) {
		this.wtsltype = wtsltype;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
