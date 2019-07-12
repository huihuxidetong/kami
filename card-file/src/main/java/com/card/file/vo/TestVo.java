package com.card.file.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * createUser：lizq
 * createDate：2016年4月8日
 * version：1.0
 * note：用户通话记录
 *
 **/

public class TestVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * conversationMobile	通话号码
		conversationPeople	通话人
		callTime	呼叫时间
		status	呼出、接听、已拒绝、未接通的来电
		duration	通话时间
	 */
	
	private String mobile;
	private String tel;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public TestVo(String mobile, String tel) {
		super();
		this.mobile = mobile;
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "TestVo [mobile=" + mobile + ", tel=" + tel + "]";
	}
	
}
