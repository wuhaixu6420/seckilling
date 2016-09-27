package com.zq.fin.seckill.exception;

/**
 * 更新或者是插入超出指定数量异常
 * @author admin
 *
 * create Y-Y 2016年8月18日16:32:42
 */
public class ExceedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExceedException(String message) {
		super(message);
	}

	public ExceedException(String message, Throwable cause) {
		super(message, cause);
	}

}
