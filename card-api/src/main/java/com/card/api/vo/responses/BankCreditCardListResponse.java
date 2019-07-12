package com.card.api.vo.responses;

import com.card.api.vo.vo.BankVo;
import com.card.api.vo.vo.CreditCardInfoHotVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "银行信用卡列表信息返回", discriminator = "BankCreditCardListResponse", subTypes = {BankCreditCardListResponse.class})
public class BankCreditCardListResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "银行id")
    private Integer id;
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    @ApiModelProperty(value = "银行logo")
    private String bankLogo;
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
}
