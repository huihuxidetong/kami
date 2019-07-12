package com.card.api.vo.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:首页banner实体类
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class BannerIndexVo implements Serializable {

	private static final long serialVersionUID = 201809261303390L;
    @ApiModelProperty(value = "bannerid")
    private Integer id;
    @ApiModelProperty(value = "banner名称")
    private String bannerName;
    @ApiModelProperty(value = "banner图片链接")
    private String bannerImage;
    @ApiModelProperty(value = "banner排序")
    private Integer sort;
    @ApiModelProperty(value = "banner图片跳转地址")
    private String bannerUrl;
    @ApiModelProperty(value = "类型 1=路径 2=链接")
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