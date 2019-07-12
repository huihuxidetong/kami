package com.card.service.impl;

import com.alibaba.dubbo.container.page.PageHandler;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.CommentReplyVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.inteface.dao.CommentReplyDao;
import com.card.inteface.entity.CommentReply;  
import com.card.inteface.service.CommentReplyService;

import java.util.List;

/**
 * @notes:评论回复Service实现类
 *
 * @author zzh
 *
 * 2018-09-26 13:05:40		Email:azhangzhihengi@163.com
 */
@Service
public class CommentReplyServiceImpl implements CommentReplyService {

	@Autowired
	private CommentReplyDao commentReplyDao;

	/**
	 * @notes 保存回复信息
	 * @param commentReply
	 */
	public void addCommentReply(CommentReply commentReply) throws Exception {
		commentReplyDao.addCommentReply(commentReply);
	}

	/**
	 * @notes 查询我的评论
	 * @param pageBean
	 * @return
	 */
	public PageBean<CommentReplyVo> findMyCommentReplyPage(PageBean<CommentReplyVo> pageBean) throws Exception {
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<CommentReplyVo> list = commentReplyDao.findMyCommentReplyPage(pageBean.getBean().getToUid());
		return new PageBean<CommentReplyVo>(list);
	}
	/**
	 * @notes 更新未读消息
	 * @param commentReply
	 */
	public void updateCommentReply(CommentReply commentReply) throws Exception {
		commentReplyDao.updateCommentReply(commentReply);
	}

}
