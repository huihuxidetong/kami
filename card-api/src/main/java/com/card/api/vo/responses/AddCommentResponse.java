package com.card.api.vo.responses;

import com.card.api.vo.vo.CreditCardInfoHotVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "信用卡评论信息返回", discriminator = "AddCommentResponse", subTypes = {AddCommentResponse.class})
public class AddCommentResponse extends Response{

    private static final long serialVersionUID = 1L;

}
