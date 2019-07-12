package com.card.api.vo.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @notes:用户自定义标签
 *
 * @author zzh
 *
 * 2018-09-26 13:03:39		Email:azhangzhihengi@163.com
 */
public class UserCustomLableVo implements Serializable {

	private static final long serialVersionUID = 201809261303391L;

    @ApiModelProperty(value = "该标签是否已经被选中 1=是 0=否")
    private Integer isChooseLable;

	@ApiModelProperty(value = "标签名字")
    private String userCustomLableName;

	@ApiModelProperty(value = "索引值")
    private Integer index;

    public Integer getIsChooseLable() {
        return isChooseLable;
    }

    public void setIsChooseLable(Integer isChooseLable) {
        this.isChooseLable = isChooseLable;
    }

    public String getUserCustomLableName() {
        return userCustomLableName;
    }

    public void setUserCustomLableName(String userCustomLableName) {
        this.userCustomLableName = userCustomLableName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}