package com.card.api.vo.responses;

import com.card.api.vo.vo.ActivityCreditCardVo;
import com.card.api.vo.vo.ActivityIndexVo;
import com.card.api.vo.vo.CreditCardInfoHotVo;
import com.card.api.vo.vo.CreditCardInfoQueryVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "用户关注信息返回", discriminator = "UserConcernBasicResponse", subTypes = {UserConcernBasicResponse.class})
public class UserConcernBasicResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关注信用卡列表")
    private List<CreditCardInfoHotVo> creditCardInfoHotVoList;

    @ApiModelProperty(value = "用户关注活动列表")
    private List<ActivityIndexVo> activityIndexVoList;

    @ApiModelProperty(value = "当前页数")
    private Integer page;
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;
    @ApiModelProperty(value = "总条数")
    private Long count;

    public List<CreditCardInfoHotVo> getCreditCardInfoHotVoList() {
        return creditCardInfoHotVoList;
    }

    public void setCreditCardInfoHotVoList(List<CreditCardInfoHotVo> creditCardInfoHotVoList) {
        this.creditCardInfoHotVoList = creditCardInfoHotVoList;
    }

    public List<ActivityIndexVo> getActivityIndexVoList() {
        return activityIndexVoList;
    }

    public void setActivityIndexVoList(List<ActivityIndexVo> activityIndexVoList) {
        this.activityIndexVoList = activityIndexVoList;
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
