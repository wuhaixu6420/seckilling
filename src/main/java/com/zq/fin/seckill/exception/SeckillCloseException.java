package com.zq.fin.seckill.exception;

/**
 * 秒杀关闭异常（运行期异常）
 * @author admin
 *
 * create Y-Y 2016年8月18日16:32:42
 */
public class SeckillCloseException extends SeckillException {

	private static final long serialVersionUID = 1L;

	public SeckillCloseException(String message) {
		super(message);
	}

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

}
