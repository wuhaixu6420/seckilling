package com.zq.fin.seckill.entity.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ClinchdealGLscModel {

	/**
	 * 证券名称
	 */
	private String stockName;
	
	/**
	 * 股票代码
	 */
	private String stockCode;
	
	/**
	 * 操作
	 */
	private String operation;
	
	/**
	 * 成交数量
	 */
	private String clinchdealNum;
	
	/**
	 * 成交均价
	 */
	private String averageprice;
	
	/**
	 * 成交时间
	 */
	private String clinchdealtm;
	
	public ClinchdealGLscModel() {
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getClinchdealNum() {
		return clinchdealNum;
	}

	public void setClinchdealNum(String clinchdealNum) {
		this.clinchdealNum = clinchdealNum;
	}

	public String getAverageprice() {
		return averageprice;
	}

	public void setAverageprice(String averageprice) {
		this.averageprice = averageprice;
	}

	public String getClinchdealtm() {
		return clinchdealtm;
	}

	public void setClinchdealtm(String clinchdealtm) {
		this.clinchdealtm = clinchdealtm;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
