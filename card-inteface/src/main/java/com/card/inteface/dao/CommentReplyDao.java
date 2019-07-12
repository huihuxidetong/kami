package com.card.inteface.dao;

import com.card.inteface.vo.CommentReplyVo;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.CommentReply;

import java.util.List;

/**
 * @notes:评论回复Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface CommentReplyDao {

    /**
     * @notes 通过评论id 查询回复信息
     * @param CommentId
     * @return
     */
    public List<CommentReplyVo> findCommentReplyVoByCommnetId(Integer CommentId) throws Exception;


    /**
     * @notes 保存回复信息
     * @param commentReply
     */
    public void addCommentReply(CommentReply commentReply) throws Exception;

    /**
     * @notes 根据用户id查询用户信息
     * @param toUid
     * @return
     */
    public List<CommentReplyVo> findMyCommentReplyPage(Integer toUid) throws Exception;

    /**
     * @notes 更新未读消息
     * @param commentReply
     */
    public void updateCommentReply(CommentReply commentReply) throws Exception;
}
