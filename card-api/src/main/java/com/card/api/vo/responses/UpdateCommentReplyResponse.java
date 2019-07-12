package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "阅读消息返回", discriminator = "UpdateCommentReplyResponse", subTypes = {UpdateCommentReplyResponse.class})
public class UpdateCommentReplyResponse extends Response{

    private static final long serialVersionUID = 1L;


}
