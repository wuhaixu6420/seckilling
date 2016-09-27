package com.zq.fin.seckill.exception;

/**
 * UserException : 用户自定义异常
 *
 * @author StarZou
 * @since 2014-09-27 18:12
 */
public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 异常发生时间
	 */
	private long date = System.currentTimeMillis();

	public long getDate() {
		return date;
	}
	
	public UserException() {
		super();
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(Throwable cause) {
		super(cause);
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}
}
