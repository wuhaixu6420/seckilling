package com.zq.fin.seckill.exception;

/**
 * DaoException : 封装Dao(数据库访问)层发生的异常
 *
 * @author StarZou
 * @since 2014-09-27 18:17
 */
public class DaoException extends UserException {

	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
