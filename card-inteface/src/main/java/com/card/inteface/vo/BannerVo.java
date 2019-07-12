package com.card.inteface.vo;

import java.io.Serializable;

/**
 * @notes:首页banner实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class BannerVo implements Serializable {

	private static final long serialVersionUID = 201809261303390L;

    private Integer id;
    private String bannerName;
    private String bannerImage;
    private String bannerUrl;
    private Integer sort;
    private Integer bannerType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Integer getBannerType() {
        return bannerType;
    }

    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }
}