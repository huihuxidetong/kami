package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:评论实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = 201809261303392L;
	
    private Integer id;
    
    private String content;
    
    private Integer fromUid;
    
    private String nickname;
    
    private String thumbImg;
    
    private Integer isReply;
    
    private Integer status;
    
    private Date createTime;

    private Integer objectId;

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

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}