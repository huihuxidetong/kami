package com.card.inteface.service;

import com.card.inteface.entity.Comment;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.CommentVo;

/**
 * @notes:评论Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface CommentService  {

    /**
     * @notes 通过信用卡查询评论信息
     * @param pageBean
     * @return
     */
    public PageBean<CommentVo> findCommentVoByCreditCardId(PageBean<CommentVo> pageBean) throws Exception;

    /**
     * @notes保存评论信息
     * @param comment
     */
    public void addComment(Comment comment) throws Exception;

    /**
     * @ntoes 查询用户未读消息数量
     * @param userId
     * @return
     */
    public Integer findUserNoReadCommentCount(Integer userId) throws Exception;
}
