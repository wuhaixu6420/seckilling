package com.zq.fin.seckill.entity.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PositionGlscModel implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 股票代码
	 */
	private String stockCode;
	
	/**
	 * 股票名称
	 */
	private String stockName;
	
	/**
	 * 股票余额
	 */
	private String stockexcess;
	
	/**
	 * 股票可用余额
	 */
	private String usablestock;
	
	/**
	 * 成本价
	 */
	private String costprice;
	
	/**
	 * 最新市值
	 */
	private String newmarket_value;
	
	/**
	 * 盈亏比
	 */
	private String cysratio;
	
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockexcess() {
		return stockexcess;
	}

	public void setStockexcess(String stockexcess) {
		this.stockexcess = stockexcess;
	}

	public String getUsablestock() {
		return usablestock;
	}

	public void setUsablestock(String usablestock) {
		this.usablestock = usablestock;
	}

	public String getCostprice() {
		return costprice;
	}

	public void setCostprice(String costprice) {
		this.costprice = costprice;
	}

	public String getNewmarket_value() {
		return newmarket_value;
	}

	public void setNewmarket_value(String newmarket_value) {
		this.newmarket_value = newmarket_value;
	}

	public String getCysratio() {
		return cysratio;
	}

	public void setCysratio(String cysratio) {
		this.cysratio = cysratio;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
