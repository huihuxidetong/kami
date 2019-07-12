package com.card.api.vo.responses;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "回复评论信息返回", discriminator = "AddCommentReplyResponse", subTypes = {AddCommentReplyResponse.class})
public class AddCommentReplyResponse extends Response{

    private static final long serialVersionUID = 1L;

}
