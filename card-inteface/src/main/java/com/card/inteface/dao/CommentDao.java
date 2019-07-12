package com.card.inteface.dao;

import com.card.inteface.vo.CommentVo;
import org.springframework.stereotype.Component;

import com.card.inteface.entity.Comment;

import java.util.List;

/**
 * @notes:评论Dao接口
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Component
public interface CommentDao {

    /**
     * @ntoes 通过信用卡id 查询评论信息
     * @param creditCardId
     * @return
     */
    public List<CommentVo> findCommentVoByCreditCardId(Integer creditCardId) throws Exception;

    /**
     * @notes保存评论信息
     * @param comment
     */
    public Object addComment(Comment comment) throws Exception;

    /**
     * @ntoes 查询用户未读消息数量
     * @param userId
     * @return
     */
    public Integer findUserNoReadCommentCount(Integer userId) throws Exception;
}
