package com.card.api.vo.responses;

import com.card.api.vo.vo.CommentVo;
import com.card.api.vo.vo.CreditDetailActivityVo;
import com.card.inteface.entity.CreditCardCost;
import com.card.inteface.entity.CreditCardQuestion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "信用卡评论返回", discriminator = "CreditCardCommentResponse", subTypes = {CreditCardCommentResponse.class})
public class CreditCardCommentResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "信用卡id")
    private Integer id;
    @ApiModelProperty(value = "评论信息")
    private List<CommentVo> commentVos;
    @ApiModelProperty(value = "当前页数")
    private Integer page;
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;
    @ApiModelProperty(value = "总条数")
    private Long count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CommentVo> getCommentVos() {
        return commentVos;
    }

    public void setCommentVos(List<CommentVo> commentVos) {
        this.commentVos = commentVos;
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
