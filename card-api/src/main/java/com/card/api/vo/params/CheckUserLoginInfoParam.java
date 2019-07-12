package com.card.api.vo.params;

import io.swagger.annotations.ApiModelProperty;

/**
 * @notes 检查用户是否登录
 */
public class CheckUserLoginInfoParam {

	@ApiModelProperty(value = "业务key")
	private String businessKey;

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
}
