package com.card.inteface.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:用户关注基本信息实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class UserConcernBasic implements Serializable {

	private static final long serialVersionUID = 201809261303393L;
	
    private Integer id;
    
    private Integer userId;
    
    private Integer concernType;
    
    private Integer concernId;

    private Date createTime;

    private Integer status;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public Integer getConcernType() {
        return concernType;
    }

    public void setConcernType(Integer concernType) {
        this.concernType = concernType;
    }
    
    public Integer getConcernId() {
        return concernId;
    }

    public void setConcernId(Integer concernId) {
        this.concernId = concernId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}