package com.card.file.vo;

/**
 * createUser：lizq
 * createDate：2016年3月17日
 * version：1.0
 * note：
 *
 **/

public class OutVo<T> {
	
	private Object data; 
	private String msg;
	private int code;
	private String showMsg;
	
	
	private final static int successCode = 0;
	
	/**
	 * 成功直接返回状态码
	 */
	public OutVo(){
		this.code = this.successCode;
	}
	
	/**
	 * 成功直接返回状态码
	 * @param code
	 */
	public OutVo(int code){
		this.code = code;
	}
	
	/**
	 * 失败 返回失败状态码和失败信息
	 * @param code
	 * @param msg
	 */
	public OutVo(int code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	/**
	 * 成功返回成功状态码成功数据
	 * @return 
	 */
	public void OutVO(int code, Object obj){
		this.code = this.successCode;
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
