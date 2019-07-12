package com.card.service.impl;

import com.card.inteface.dao.CommentDao;
import com.card.inteface.dao.CommentReplyDao;
import com.card.inteface.entity.Comment;
import com.card.inteface.service.CommentService;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.CommentReplyVo;
import com.card.inteface.vo.CommentVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @notes:评论Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private CommentReplyDao commentReplyDao;

	/**
	 * @notes 通过信用卡查询评论信息
	 * @param pageBean
	 * @return
	 */
	public PageBean<CommentVo> findCommentVoByCreditCardId(PageBean<CommentVo> pageBean) throws Exception {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<CommentVo> list = commentDao.findCommentVoByCreditCardId(pageBean.getBean().getObjectId());
		if(null != list && list.size()>0){
			for(CommentVo commentVo : list){
				Integer commentId = commentVo.getId();//评论id
				List<CommentReplyVo> commentReplyVoList = commentReplyDao.findCommentReplyVoByCommnetId(commentId);
				commentVo.setCommentReplyVo(commentReplyVoList);
			}
		}
		return new PageBean<CommentVo>(list);
	}

	/**
	 * @notes保存评论信息
	 * @param comment
	 */
	public void addComment(Comment comment) throws Exception {
		commentDao.addComment(comment);
	}

	/**
	 * @ntoes 查询用户未读消息数量
	 * @param userId
	 * @return
	 */
	public Integer findUserNoReadCommentCount(Integer userId) throws Exception {
		return commentDao.findUserNoReadCommentCount(userId);
	}
}
