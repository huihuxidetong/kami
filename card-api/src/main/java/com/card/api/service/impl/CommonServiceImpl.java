/**
 * 
 */
package com.card.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.card.api.common.exception.BusinessException;
import com.card.api.service.CommonService;
import com.card.api.utils.JedisUtil;
import com.card.api.utils.OutVo;
import com.card.api.utils.SiteConfig;
import com.card.api.vo.responses.WxSessionResponse;
import net.sf.json.JSON;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenz
 *
 */
@Service
public class CommonServiceImpl implements CommonService {

	private static final Logger logger = Logger.getLogger(CommonServiceImpl.class);

	@Autowired
	private SiteConfig siteConfig;

	@Override
	public WxSessionResponse createSssion(String wxCode) throws BusinessException {
        WxSessionResponse rsp = new WxSessionResponse();
        if (StringUtils.isBlank(wxCode)) {
            throw new BusinessException(1, "获取微信code失败");
        }
        String appid = siteConfig.appid;
        String secret = siteConfig.secret;
        logger.info("============appid:"+appid);
        logger.info("============secret:"+appid);
        OutVo<Object> result = this.getSessionKey(appid, secret,wxCode, siteConfig.grantTypeAuthorization);
        if (result.getCode() != 0) {
            throw new BusinessException(1, "获取微信code失败");
        }
        JSONObject json = JSONObject.parseObject(result.getData().toString());
        logger.info("============获取session_key_json:"+json);
        if (StringUtils.isBlank(json.getString("session_key"))) {
            throw new BusinessException(1, "获取微信sessionKey失败");
        }
        if (StringUtils.isBlank(json.getString("openid"))) {
            throw new BusinessException(1, "获取微信openid失败");
        }
        String businessKey = this.create3rdSession(json.getString("session_key"), json.getString("openid"), 60 * 60 * 24 * 30);
        rsp.setBusinessKey(businessKey);
        logger.info(net.sf.json.JSONObject.fromObject(rsp));
        return rsp;
	}


    // 获取sessionKey
    private OutVo<Object> getSessionKey(String appid, String secret, String wxCode, String grantType) {
        OutVo<Object> out = new OutVo<Object>();
        RestTemplate rt = new RestTemplate();
        StringBuffer sb = new StringBuffer(siteConfig.wxSessionKeyUrl);
        sb.append("?appid=").append(appid);
        sb.append("&secret=").append(secret);
        sb.append("&js_code=").append(wxCode);
        sb.append("&grant_type=").append(grantType);
        ResponseEntity<String> forEntity = rt.getForEntity(sb.toString(), String.class);
        logger.info("获取sessionKey，结果" + forEntity.getBody());
        if (HttpStatus.OK.equals(forEntity.getStatusCode())) {
            JSONObject json = JSONObject.parseObject(forEntity.getBody());
            if (json != null) {
                out.setCode(0);
                out.setData(json);
            } else {
                out.setShowMsg("获取信息失败");
            }
        } else {
            out.setShowMsg("获取信息失败");
        }
        return out;
    }

    /**
     * 缓存微信openId和session_key
     *
     * @param openId     微信用户唯一标识
     * @param sessionKey 微信服务器会话密钥
     * @param expires    会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
     * @return
     */
    public String create3rdSession(String sessionKey, String openId, int expires) {
        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sessionKey", sessionKey);
        map.put("openId", openId);
        JedisUtil.setKeyMap(thirdSessionKey, map, expires);
        return thirdSessionKey;
    }
}
