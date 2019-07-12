package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.Comment;  
import com.card.inteface.dao.CommentDao;

import java.util.List;

/**
 * @notes:评论Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class CommentDaoImpl  implements CommentDao {

    @Autowired
    private DaoSupport daoSupport;
    /**
     * @ntoes 通过信用卡id 查询评论信息
     * @param creditCardId
     * @return
     */
    public List<CommentVo> findCommentVoByCreditCardId(Integer creditCardId) throws Exception {
        return (List<CommentVo>) daoSupport.findForList("CommentMapper.findCommentVoByCreditCardId",creditCardId);
    }

    /**
     * @notes保存评论信息
     * @param comment
     */
    public Object addComment(Comment comment) throws Exception {
        return daoSupport.save("CommentMapper.insertSelective",comment);
    }

    /**
     * @ntoes 查询用户未读消息数量
     * @param userId
     * @return
     */
    public Integer findUserNoReadCommentCount(Integer userId) throws Exception {
        return (Integer) daoSupport.findForObject("CommentMapper.findUserNoReadCommentCount",userId);
    }
}