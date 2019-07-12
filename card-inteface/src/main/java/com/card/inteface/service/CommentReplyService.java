package com.card.inteface.service;

import com.card.inteface.entity.CommentReply;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.CommentReplyVo;

/**
 * @notes:评论回复Service接口
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
public interface CommentReplyService  {

    /**
     * @notes 保存回复信息
     * @param commentReply
     */
    public void addCommentReply(CommentReply commentReply) throws Exception;

    /**
     * @notes 查询我的评论
     * @param pageBean
     * @return
     */
    public PageBean<CommentReplyVo> findMyCommentReplyPage(PageBean<CommentReplyVo> pageBean) throws Exception;

    /**
     * @notes 更新未读消息
     * @param commentReply
     */
    public void updateCommentReply(CommentReply commentReply) throws Exception;
}
