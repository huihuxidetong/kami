package com.card.api.common.exception;

/**
 * 自定义业务异常类
 * 
 * @author chenz
 * @version 2017/11/06
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * 错误编码
	 */
	private Integer errorCode;
	/**
	 * 业务数据
	 */
	private String data;

	public BusinessException() {
		super();
	}

	/**
	 * 构造一个基本异常.
	 *
	 * @param message
	 *            信息描述
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * 构造一个基本异常.
	 *
	 * @param errorCode
	 *            错误编码
	 * @param message
	 *            信息描述
	 */
	public BusinessException(Integer errorCode, String message) {
		super(message);
		this.setErrorCode(errorCode);
	}
	
	/**
	 * 构造一个基本异常.
	 *
	 * @param errorCode
	 *            错误编码
	 * @param data
	 *            业务数据
	 * @param message
	 *            信息描述
	 */
	public BusinessException(Integer errorCode, String data, String message) {
		super(message);
		this.setErrorCode(errorCode);
		this.setData(data);
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
