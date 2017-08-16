package com.zq.fin.seckill.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DataResult<T> {
	
	private boolean success;
	
	private T data;
	
	private String error;
	
	public DataResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public DataResult(boolean success, String error) {
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
