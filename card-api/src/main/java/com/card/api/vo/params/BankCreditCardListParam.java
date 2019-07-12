package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 银行信用卡列表
 */
@ApiModel(value = "银行信用卡列表", discriminator = "BankCreditCardListParam", subTypes = {BankCreditCardListParam.class})
public class BankCreditCardListParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "银行Id")
    private Integer id;
    @ApiModelProperty(value = "当前页数")
    private Integer pageNum;
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
