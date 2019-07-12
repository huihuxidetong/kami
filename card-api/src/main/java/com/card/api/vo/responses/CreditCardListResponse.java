package com.card.api.vo.responses;

import com.card.api.vo.vo.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "信用卡列表信息返回", discriminator = "CreditCardListResponse", subTypes = {CreditCardListResponse.class})
public class CreditCardListResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "银行列表")
    private List<BankVo> bankVoLiist;
    @ApiModelProperty(value = "热门信用卡")
    private List<CreditCardInfoHotVo> creditCardInfoHotVoList;
    @ApiModelProperty(value = "信用卡分页列表")
    private List<CreditCardInfoHotVo> creditCardInfoHotVoPageList;
    @ApiModelProperty(value = "当前页数")
    private Integer page;
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;
    @ApiModelProperty(value = "总条数")
    private Long count;
    @ApiModelProperty(value = "是否要进去详情页 1=是 0=否")
    private Integer isCanClickDetail;

    public List<BankVo> getBankVoLiist() {
        return bankVoLiist;
    }

    public void setBankVoLiist(List<BankVo> bankVoLiist) {
        this.bankVoLiist = bankVoLiist;
    }

    public List<CreditCardInfoHotVo> getCreditCardInfoHotVoList() {
        return creditCardInfoHotVoList;
    }

    public void setCreditCardInfoHotVoList(List<CreditCardInfoHotVo> creditCardInfoHotVoList) {
        this.creditCardInfoHotVoList = creditCardInfoHotVoList;
    }

    public List<CreditCardInfoHotVo> getCreditCardInfoHotVoPageList() {
        return creditCardInfoHotVoPageList;
    }

    public void setCreditCardInfoHotVoPageList(List<CreditCardInfoHotVo> creditCardInfoHotVoPageList) {
        this.creditCardInfoHotVoPageList = creditCardInfoHotVoPageList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getIsCanClickDetail() {
        return isCanClickDetail;
    }

    public void setIsCanClickDetail(Integer isCanClickDetail) {
        this.isCanClickDetail = isCanClickDetail;
    }
}
