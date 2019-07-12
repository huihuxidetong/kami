package com.card.api.vo.responses;

import com.card.api.vo.vo.CommentReplyVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "我的消息返回返回", discriminator = "MyCommentReplyResponse", subTypes = {MyCommentReplyResponse.class})
public class MyCommentReplyResponse extends Response{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "我的消息")
    private List<CommentReplyVo> commentReplyVoList;
    @ApiModelProperty(value = "当前页数")
    private Integer page;
    @ApiModelProperty(value = "总页数")
    private Integer totalPage;
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;
    @ApiModelProperty(value = "总条数")
    private Long count;

    public List<CommentReplyVo> getCommentReplyVoList() {
        return commentReplyVoList;
    }

    public void setCommentReplyVoList(List<CommentReplyVo> commentReplyVoList) {
        this.commentReplyVoList = commentReplyVoList;
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
