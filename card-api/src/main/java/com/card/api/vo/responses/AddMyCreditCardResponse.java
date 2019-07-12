package com.card.api.vo.responses;

import com.card.api.vo.vo.MyCreditCardInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "添加我的信用卡返回", discriminator = "AddMyCreditCardResponse", subTypes = {AddMyCreditCardResponse.class})
public class AddMyCreditCardResponse extends Response{

    private static final long serialVersionUID = 1L;


}
