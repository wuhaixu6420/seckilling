package com.zq.fin.seckill.exception;

/**
 * 更新失败异常
 * @author admin
 *
 * create Y-Y 2016年8月18日16:32:42
 */
public class UpdateErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UpdateErrorException(String message) {
		super(message);
	}

	public UpdateErrorException(String message, Throwable cause) {
		super(message, cause);
	}

}
