package com.zq.fin.seckill.exception;

/**
 * 重复秒杀异常（运行期异常）
 * @author admin
 *
 * create Y-Y 2016年8月18日16:32:42
 */
public class RepeatKillException extends SeckillException {

	private static final long serialVersionUID = 1L;

	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}

}
