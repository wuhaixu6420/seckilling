package com.zq.fin.seckill.exception;

/**
 * 秒杀相关业务异常
 * @author admin
 *
 * create Y-Y 2016年8月18日16:32:42
 */
public class SeckillException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}

}
