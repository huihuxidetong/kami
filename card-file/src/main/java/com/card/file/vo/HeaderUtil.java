package com.card.file.vo;

import javax.servlet.http.HttpServletRequest;


/**
 * createUser：lizq
 * createDate：2016年3月24日
 * version：1.0
 * note：访问IP地址帮助类
 *
 **/

public class HeaderUtil {
	
	//获得请求头的IP地址
//	public static String getIp(HttpServletRequest request) {
//		
//	    if(request.getHeader("Cdn-Src-Ip") != null)
//	    	return getRealIp(request.getHeader("Cdn-Src-Ip"));
//	    if (request.getHeader("x-forwarded-for") == null)
//	    	return getRealIp(request.getRemoteAddr());
//	    if (request.getHeader("Proxy-Client-IP") == null)
//	    	return getRealIp(request.getRemoteAddr());
//	    if (request.getHeader("WL-Proxy-Client-IP") == null)
//	    	return getRealIp(request.getRemoteAddr());
//	    else
//			return getRealIp(request.getHeader("x-forwarded-for"));
//	}
	
	public static String getIp(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");      
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  

	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  

	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  

	    return ip;  
	}
	
	public static String getRealIp(String ips) {
		return ips.split(",")[0];
	}

	//从请求头里面读取token 令牌信息
	public static String getToken(HttpServletRequest request){
		String token = request.getHeader("token");
		return token;
	}
	
	//从请求头里面读取token 令牌信息
	public static String getVersion(HttpServletRequest request){
		String token = request.getHeader("version");
		return token;
	}
}
