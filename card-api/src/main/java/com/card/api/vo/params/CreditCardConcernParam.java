package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "信用卡关注", discriminator = "CreditCardConcernParam", subTypes = {CreditCardConcernParam.class})
public class CreditCardConcernParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "信用卡Id")
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
