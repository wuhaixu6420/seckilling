package com.zq.fin.seckill.dto.reg;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RegModelResult {

	private boolean success;
	
	private String message;

	public RegModelResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
