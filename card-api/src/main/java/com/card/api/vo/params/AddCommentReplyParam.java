package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "信用卡评论回复", discriminator = "AddCommentReplyParam", subTypes = {AddCommentReplyParam.class})
public class AddCommentReplyParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业务key")
    private String businessKey;
    @ApiModelProperty(value = "信用卡id")
    private Integer objectId;
    @ApiModelProperty(value = "回复内容")
    private String content;
    @ApiModelProperty(value = "评论id")
    private Integer commentId;
    @ApiModelProperty(value = "1为回复评论，2为回复别人的回复")
    private Integer replyType;
    @ApiModelProperty(value = "回复目标id，reply_type为1时，是comment_id，reply_type为2时为回复表的id")
    private Integer replyId;
    @ApiModelProperty(value = "目标用户id")
    private Integer toUid;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }
}
