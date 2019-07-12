package com.card.api.vo.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class CreditCardConcernVo implements Serializable {

	private static final long serialVersionUID = 201809261303390L;

    @ApiModelProperty(value = "id")
    private Integer creditCardid;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "用户名称")
    private String nickName;
    @ApiModelProperty(value = "用户头像")
    private String userPortrait;

    public Integer getCreditCardid() {
        return creditCardid;
    }

    public void setCreditCardid(Integer creditCardid) {
        this.creditCardid = creditCardid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }
}
