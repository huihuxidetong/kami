package com.card.inteface.utils;


import java.io.Serializable;

/**
 * createUser：CHENZ 
 * createDate：2018年1月25日 
 * version：1.0 
 * note：
 *
 **/

public class OutVo<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3997593953375508639L;
	
	private Object data;
	private String msg;
	private int code;
	private String showMsg;

	private final static int SUCCESSCODE = 0;

	/**
	 * 成功直接返回状态码
	 */
	public OutVo() {
		this.code = this.SUCCESSCODE;
	}

	/**
	 * 成功直接返回状态码
	 * 
	 * @param code
	 */
	public OutVo(int code) {
		this.code = code;
	}

	/**
	 * 失败 返回失败状态码和失败信息
	 * 
	 * @param code
	 * @param msg
	 */
	public OutVo(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 成功返回成功状态码成功数据
	 * 
	 * @return
	 */
	public void OutVO(int code, Object obj) {
		this.code = this.SUCCESSCODE;
		this.data = obj;
	}

	public String getShowMsg() {
		return showMsg;
	}

	public void setShowMsg(String showMsg) {
		this.showMsg = showMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
