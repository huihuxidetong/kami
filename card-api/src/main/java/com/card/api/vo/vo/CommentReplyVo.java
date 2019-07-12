package com.card.api.vo.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:评论回复实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class CommentReplyVo implements Serializable {

	private static final long serialVersionUID = 201809261303390L;
    @ApiModelProperty(value = "回复的id")
    private Integer id;
	@ApiModelProperty(value = "评论的id")
    private Integer commentId;
    @ApiModelProperty(value = "1为回复评论，2为回复别人的回复")
    private Integer replyType;
    @ApiModelProperty(value = "回复目标id，reply_type为1时，是comment_id，reply_type为2时为回复表的id")
    private Integer replyId;
    @ApiModelProperty(value = "回复内容")
    private String content;
    @ApiModelProperty(value = "被回复人id")
    private Integer toUid;
    @ApiModelProperty(value = "被回复人姓名")
    private String toNickname;
    @ApiModelProperty(value = "回复id")
    private Integer fromUid;
    @ApiModelProperty(value = "回复头像")
    private String fromThumbImg;
    @ApiModelProperty(value = "回复姓名")
    private String fromNickname;
    @ApiModelProperty(value = "是否已读 1=是 0=否")
    private Integer isRead;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "信用卡id")
    private String creditCardId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public String getToNickname() {
        return toNickname;
    }

    public void setToNickname(String toNickname) {
        this.toNickname = toNickname;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public String getFromThumbImg() {
        return fromThumbImg;
    }

    public void setFromThumbImg(String fromThumbImg) {
        this.fromThumbImg = fromThumbImg;
    }

    public String getFromNickname() {
        return fromNickname;
    }

    public void setFromNickname(String fromNickname) {
        this.fromNickname = fromNickname;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }
}