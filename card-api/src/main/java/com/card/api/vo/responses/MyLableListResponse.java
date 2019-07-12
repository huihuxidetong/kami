package com.card.api.vo.responses;

import com.card.api.vo.vo.UserCustomLableVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "我的标签返回", discriminator = "MyLableListResponse", subTypes = {MyLableListResponse.class})
public class MyLableListResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户已经选择标签")
    private List<UserCustomLableVo> userChooseLable;

    @ApiModelProperty(value = "用户自定义标签")
    private List<UserCustomLableVo> userCustomLableVoList;

    public List<UserCustomLableVo> getUserChooseLable() {
        return userChooseLable;
    }

    public void setUserChooseLable(List<UserCustomLableVo> userChooseLable) {
        this.userChooseLable = userChooseLable;
    }

    public List<UserCustomLableVo> getUserCustomLableVoList() {
        return userCustomLableVoList;
    }

    public void setUserCustomLableVoList(List<UserCustomLableVo> userCustomLableVoList) {
        this.userCustomLableVoList = userCustomLableVoList;
    }
}
