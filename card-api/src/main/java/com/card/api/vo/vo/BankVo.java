package com.card.api.vo.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:银行
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class BankVo implements Serializable {

	private static final long serialVersionUID = 201809261303391L;

    @ApiModelProperty(value = "银行id")
    private Integer id;
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    @ApiModelProperty(value = "银行logo")
    private String bankLogo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }
}