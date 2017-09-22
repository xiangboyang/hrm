package org.fkjava.hrm.exception;
/**
 * 基础的异常类
 * @author LEE.SIU.WAH
 * @email lixiaohua7@163.com
 * @date 2014-2-27 下午3:52:27
 * @version 1.0
 */
public class HrmException extends RuntimeException {

	private static final long serialVersionUID = 8075902403954978625L;

	public HrmException() {
	}
	public HrmException(String message) {
		super(message);
	}

	public HrmException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public HrmException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public HrmException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
