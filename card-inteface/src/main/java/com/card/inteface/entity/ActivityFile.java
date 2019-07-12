package com.card.inteface.entity;

import java.io.Serializable;

/**
 * @notes:活动文件实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class ActivityFile implements Serializable {

	private static final long serialVersionUID = 201809261303393L;
	
    private Integer id;
    
    private String imageUrl;
    
    private Integer status;
    
    private Integer activityId;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
    
}