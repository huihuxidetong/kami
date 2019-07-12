package com.card.api.common.exception;

/**
 * 请求参数处理异常
 * 
 * @author hujp
 * @date 2017/11/1
 *
 */
public class RequestException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RequestException() {
		super();
	}
	
	public RequestException(String msg) {
		super(msg);
	}

	public RequestException(String msg, Throwable e) {
		super(msg, e);
	}
}
