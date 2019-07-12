package com.card.dao.impl;

import com.card.dao.DaoSupport;
import com.card.inteface.vo.CommentReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.card.inteface.entity.CommentReply;  
import com.card.inteface.dao.CommentReplyDao;

import java.util.List;

/**
 * @notes:评论回复Dao实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:07:44		Email:azhangzhihengi@163.com
 */
@Repository
public class CommentReplyDaoImpl  implements CommentReplyDao {

    @Autowired
    private DaoSupport daoSupport;

    /**
     * @notes 通过评论id 查询回复信息
     * @param commentId
     * @return
     */
    public List<CommentReplyVo> findCommentReplyVoByCommnetId(Integer commentId) throws Exception {
        return (List<CommentReplyVo>) daoSupport.findForList("CommentReplyMapper.findCommentReplyVoByCommnetId",commentId);
    }

    /**
     * @notes 保存回复信息
     * @param commentReply
     */
    public void addCommentReply(CommentReply commentReply) throws Exception {
        daoSupport.save("CommentReplyMapper.insertSelective",commentReply);
    }

    /**
     * @notes 根据用户id查询用户信息
     * @param toUid
     * @return
     */
    public List<CommentReplyVo> findMyCommentReplyPage(Integer toUid) throws Exception {
        return (List<CommentReplyVo>) daoSupport.findForList("CommentReplyMapper.findMyCommentReplyVoPage",toUid);
    }

    /**
     * @notes 更新未读消息
     * @param commentReply
     */
    public void updateCommentReply(CommentReply commentReply) throws Exception {
        daoSupport.update("CommentReplyMapper.updateCommentReply",commentReply);
    }
}