package com.card.api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SiteConfig {

	/** 微信调用URL */
	public @Value("${wx_sessionKey_url}") String wxSessionKeyUrl;

	/** appid */
	public @Value("${appid}") String appid;

	/** secret */
	public @Value("${secret}") String secret;

	/** grant_type */
	public @Value("${grant_type}") String grantType;

	/** grant_type */
	public @Value("${grant_type_authorization}") String grantTypeAuthorization;

	/** send_service_msg_url */
	public @Value("${send_service_msg_url}") String sendServiceMsgUrl;

	/** send_service_msg_url */
	public @Value("${wx_card_token_url}") String wxCardTokenUrl;

	/** file.download.url */
	public @Value("${file_download_url}") String fileDownLoadUrl;

	/** is_pass */
	public @Value("${is_pass}") String isPass;

	/** is_pass */
	public @Value("${is_can_click_detail}") Integer isCanClickDetail;

	/** is_pass */
	public @Value("${ailingka_code_url}") String ailingkaCodeUrl;


}
