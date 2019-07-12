package com.card.api.vo.responses;

import com.card.api.vo.vo.ActivityIndexVo;
import com.card.api.vo.vo.BannerIndexVo;
import com.card.api.vo.vo.CreditCardInfoIndexVo;
import com.card.inteface.entity.Banner;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "首页信息返回", discriminator = "IndexResponse", subTypes = {IndexResponse.class})
public class IndexResponse extends Response{

    private static final long serialVersionUID = 1L;

    private List<BannerIndexVo> bannerList;

    private List<CreditCardInfoIndexVo> creditCardInfoVoList;

    private List<ActivityIndexVo> activityVoList;

    @ApiModelProperty(value = "是否要进去详情页 1=是 0=否")
    private Integer isCanClickDetail;

    public List<BannerIndexVo> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerIndexVo> bannerList) {
        this.bannerList = bannerList;
    }

    public List<CreditCardInfoIndexVo> getCreditCardInfoVoList() {
        return creditCardInfoVoList;
    }

    public void setCreditCardInfoVoList(List<CreditCardInfoIndexVo> creditCardInfoVoList) {
        this.creditCardInfoVoList = creditCardInfoVoList;
    }

    public List<ActivityIndexVo> getActivityVoList() {
        return activityVoList;
    }

    public void setActivityVoList(List<ActivityIndexVo> activityVoList) {
        this.activityVoList = activityVoList;
    }

    public Integer getIsCanClickDetail() {
        return isCanClickDetail;
    }

    public void setIsCanClickDetail(Integer isCanClickDetail) {
        this.isCanClickDetail = isCanClickDetail;
    }
}
