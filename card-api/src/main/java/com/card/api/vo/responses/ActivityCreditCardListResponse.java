package com.card.api.vo.responses;

import com.card.api.vo.vo.ActivityCreditCardVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "活动相关信用返回", discriminator = "ActivityDetailResponse", subTypes = {ActivityCreditCardListResponse.class})
public class ActivityCreditCardListResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "相关信用卡")
    private List<ActivityCreditCardVo> activityCreditCardVoList;
    @ApiModelProperty(value = "当前页数")
    private Integer page;
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;
    @ApiModelProperty(value = "总条数")
    private Long count;


    public List<ActivityCreditCardVo> getActivityCreditCardVoList() {
        return activityCreditCardVoList;
    }

    public void setActivityCreditCardVoList(List<ActivityCreditCardVo> activityCreditCardVoList) {
        this.activityCreditCardVoList = activityCreditCardVoList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
