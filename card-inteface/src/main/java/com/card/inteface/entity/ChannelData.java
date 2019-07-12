package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:渠道数据实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class ChannelData implements Serializable {

	private static final long serialVersionUID = 201809261303392L;
	
    private Integer id;
    
    private String channelCode;
    
    private Integer userId;
    
    private Date createTime;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}