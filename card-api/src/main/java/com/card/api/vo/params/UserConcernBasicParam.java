package com.card.api.vo.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户关注信息", discriminator = "UserConcernBasicParam", subTypes = {UserConcernBasicParam.class})
public class UserConcernBasicParam {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "业务key")
    private String businessKey;
    @ApiModelProperty(value = "关注类型 1=信用卡 2=活动")
    private Integer concernType;
    @ApiModelProperty(value = "当前页数")
    private Integer pageNum;
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Integer getConcernType() {
        return concernType;
    }

    public void setConcernType(Integer concernType) {
        this.concernType = concernType;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
