package com.card.inteface.vo;

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

	private Integer id;
    private String content;
    private Integer fromUid;
    private String nickname;
    private String userPortrait;
    private Date createTime;
    private Integer objectId;
    private String thumbImg;
    private List<CommentReplyVo> commentReplyVo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return thumbImg;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public List<CommentReplyVo> getCommentReplyVo() {
        return commentReplyVo;
    }

    public void setCommentReplyVo(List<CommentReplyVo> commentReplyVo) {
        this.commentReplyVo = commentReplyVo;
    }
}