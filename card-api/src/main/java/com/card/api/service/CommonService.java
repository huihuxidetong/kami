/**
 * 
 */
package com.card.api.service;

import com.card.api.common.exception.BusinessException;
import com.card.api.vo.responses.WxSessionResponse;

/**
 * @author zzh
 *
 */
public interface CommonService {
	/**
	 * 根据客户端传过来的code从微信服务器获取appid和session_key，然后生成3rdkey返回给客户端，后续请求客户端传3rdkey来维护客户端登录态
	 * @param wxCode	小程序登录时获取的code
	 * @return ResponseVo
	 */
	public WxSessionResponse createSssion(String wxCode) throws BusinessException;
	
}
