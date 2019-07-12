package com.card.api.vo.responses;

import com.card.api.vo.vo.MyCreditCardInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "我的信用卡列表返回", discriminator = "MyCreditCardListResponse", subTypes = {MyCreditCardListResponse.class})
public class MyCreditCardListResponse extends Response{

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "信用卡列表")
    private List<MyCreditCardInfoVo> creditCardInfoHotVoPageList;

    public List<MyCreditCardInfoVo> getCreditCardInfoHotVoPageList() {
        return creditCardInfoHotVoPageList;
    }

    public void setCreditCardInfoHotVoPageList(List<MyCreditCardInfoVo> creditCardInfoHotVoPageList) {
        this.creditCardInfoHotVoPageList = creditCardInfoHotVoPageList;
    }

}
