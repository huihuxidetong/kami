package com.card.api.vo.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @notes:评论实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class CommentVo implements Serializable {

	private static final long serialVersionUID = 201809261303392L;

    @ApiModelProperty(value = "评论的id")
    private Integer id;
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "评论者id")
    private Integer fromUid;
    @ApiModelProperty(value = "评论者姓名")
    private String nickname;
    @ApiModelProperty(value = "评论者头像")
    private String userPortrait;
    @ApiModelProperty(value = "评论时间")
    private Date createTime;
    @ApiModelProperty(value = "信用卡id")
    private Integer creditCardId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(value = "回复者信息")


    private List<CommentReplyVo> commentReplyVo;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public Integer getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

    public List<CommentReplyVo> getCommentReplyVo() {
        return commentReplyVo;
    }

    public void setCommentReplyVo(List<CommentReplyVo> commentReplyVo) {
        this.commentReplyVo = commentReplyVo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}