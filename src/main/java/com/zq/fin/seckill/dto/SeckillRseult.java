package com.zq.fin.seckill.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @author admin
 *
 * create Y-Y 2016年8月19日16:57:38
 * @param <T>
 */
//所有的ajax请求返回类型，封装json结果
public class SeckillRseult<T> {

	private boolean success;
	
	private T data;
	
	private String error;

	public SeckillRseult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public SeckillRseult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
