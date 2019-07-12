package com.card.api.utils;

public abstract class Constants {

	/**
	 * MD5 签名加后缀参数
	 */
	public static final String MD5SALT = "fjsandyf";

	/**
	 * 小程序唯一标识
	 */
	public static final String APPID = "wx9561918173e4a2cf";

	/**
	 * 小程序的 app secret
	 */
	public static final String SECRET = "4a8c9ee87d353bd305a02f8889cd08e6";

	/**
	 * 登录时获取的 code
	 */
	public static final String JSCODE = "js_code=";

	/**
	 * grant_type
	 */
	public static final String GRANTTYPE = "authorization_code";
	
	/**
	 * token有效时间
	 */
	public static final Integer TOKENTIME = 60 * 60 * 24 * 7;

}
