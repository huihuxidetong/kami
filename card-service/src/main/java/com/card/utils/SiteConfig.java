package com.card.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SiteConfig {

	/** 获取tokenurl */
	public @Value("${wx_card_token_url}") String wxCardTokenUrl;

	/** appid */
	public @Value("${appid}") String appid;

	/** 秘钥 */
	public @Value("${secret}") String secret;

	/** 请求类型 */
	public @Value("${grant_type}") String grantType;

	/** 跳转页面url*/
	public @Value("${page_index}") String pageIndex;

	/** 二维码url */
	public @Value("${wx_qr_cdoe_url}")String wxQrCodeUrl;

	/** 文件上传url */
	public @Value("${file.upload.url}")String fileUploadUrl;//文件上传路径

	/** 文件下载url */
	public @Value("${file.download.url}")String fileDownloadUrl;//文件下载路径

	/** 文件临时url */
	public @Value("${file.path}")String filePath;//文件存放路径

}
