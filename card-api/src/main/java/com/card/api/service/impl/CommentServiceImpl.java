package com.card.api.service.impl;

import com.card.api.common.exception.BusinessException;
import com.card.api.service.CommentService;
import com.card.api.utils.JedisUtil;
import com.card.api.vo.params.AddCommentParam;
import com.card.api.vo.params.AddCommentReplyParam;
import com.card.api.vo.params.MyCommentReplyParam;
import com.card.api.vo.params.UpdateCommentReplyParam;
import com.card.api.vo.responses.AddCommentReplyResponse;
import com.card.api.vo.responses.AddCommentResponse;
import com.card.api.vo.responses.MyCommentReplyResponse;
import com.card.api.vo.responses.UpdateCommentReplyResponse;
import com.card.inteface.entity.Comment;
import com.card.inteface.entity.CommentReply;
import com.card.inteface.entity.User;
import com.card.inteface.service.CommentReplyService;
import com.card.inteface.utils.EmojiFilter;
import com.card.inteface.utils.PageBean;
import com.card.inteface.vo.CommentReplyVo;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @notes:评论信息Service接口
 *
 * @author zzh
 *
 * 2018-08-21 14:56:12		Email:azhangzhihengi@163.com
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private com.card.inteface.service.CommentService commentService;

	@Autowired
	private com.card.inteface.service.UserService userService;

	@Autowired
	private CommentReplyService commentReplyService;

	/**
	 * @notes 用户评论
	 * @param addCommentParam
	 * @return
	 */
	public AddCommentResponse addComment(AddCommentParam addCommentParam) throws Exception {
		AddCommentResponse addCommentResponse = new AddCommentResponse();
		if (addCommentParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(addCommentParam.getBusinessKey())) {
			throw new BusinessException(1, "业务key不能为空");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(addCommentParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取用户信息失败");
		}
		User user = userService.findUserByOpenId(keyMap.get("openId").toString());
		if(null == user){
			throw new BusinessException(1, "未查询到用户信息");
		}

		Comment comment = new Comment();
		comment.setObjectId(addCommentParam.getObjectId());
		comment.setCreateTime(new Date());
		comment.setFromUid(user.getId());
		comment.setIsReply(0);
		comment.setStatus(1);
		comment.setNickname(user.getNickName());
		comment.setThumbImg(user.getUserPortrait());
		comment.setContent(EmojiFilter.filterEmoji(addCommentParam.getContent()));
		commentService.addComment(comment);
		return addCommentResponse;
	}

	/**
	 * @notes 保存回复信息
	 * @param addCommentReplyParam
	 * @return
	 */
	public AddCommentReplyResponse addCommentReply(AddCommentReplyParam addCommentReplyParam) throws Exception {
		AddCommentReplyResponse addCommentReplyResponse = new AddCommentReplyResponse();
		if (addCommentReplyParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(addCommentReplyParam.getBusinessKey())) {
			throw new BusinessException(1, "业务key不能为空");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(addCommentReplyParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取用户信息失败");
		}
		User user = userService.findUserByOpenId(keyMap.get("openId").toString());
		if(null == user){
			throw new BusinessException(1, "未查询到用户信息");
		}
		CommentReply commentReply = new CommentReply();
		Integer replyType = addCommentReplyParam.getReplyType();
		User user1 = userService.findUserByUserId(addCommentReplyParam.getToUid());
		if(null == user1){
			throw new BusinessException(1, "活肤目标不存在");
		}
		commentReply.setCommentId(addCommentReplyParam.getCommentId());
		commentReply.setReplyType(replyType);
		commentReply.setReplyId(addCommentReplyParam.getReplyId());
		commentReply.setContent(EmojiFilter.filterEmoji(addCommentReplyParam.getContent()));
		commentReply.setToUid(user1.getId());
		commentReply.setToNickname(user1.getNickName());
		commentReply.setFromUid(user.getId());
		commentReply.setFromNickname(user.getNickName());
		commentReply.setFromThumbImg(user.getUserPortrait());
		commentReply.setCreateTime(new Date());
		commentReplyService.addCommentReply(commentReply);

		return addCommentReplyResponse;
	}

	/**
	 * @notes 查询我的消息
	 * @param myCommentReplyParam
	 * @return
	 */
	public MyCommentReplyResponse myCommentReply(MyCommentReplyParam myCommentReplyParam) throws Exception {
		MyCommentReplyResponse myCommentReplyResponse = new MyCommentReplyResponse();
		if (myCommentReplyParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(myCommentReplyParam.getBusinessKey())) {
			throw new BusinessException(1, "业务key不能为空");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(myCommentReplyParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取用户信息失败");
		}
		User user = userService.findUserByOpenId(keyMap.get("openId").toString());
		if(null == user){
			throw new BusinessException(1, "未查询到用户信息");
		}
		CommentReplyVo commentReplyvo = new CommentReplyVo();
		commentReplyvo.setToUid(user.getId());
		PageBean<CommentReplyVo> pageBean = new PageBean<CommentReplyVo>();
		pageBean.setBean(commentReplyvo);
		pageBean.setPageNum(myCommentReplyParam.getPageNum());
		pageBean.setPageSize(myCommentReplyParam.getPageSize());
		PageBean<CommentReplyVo> list = commentReplyService.findMyCommentReplyPage(pageBean);
		myCommentReplyResponse.setCount(list.getTotal());
		myCommentReplyResponse.setPage(list.getPageNum());
		myCommentReplyResponse.setPageSize(list.getPageSize());
		myCommentReplyResponse.setTotalPage(list.getPages());

		List<CommentReplyVo> commentReplyVoList = list.getDatas();
		List<com.card.api.vo.vo.CommentReplyVo> commentReplyVoList1 = new ArrayList<>();
		if(null != commentReplyVoList && commentReplyVoList.size()>0){
			for(CommentReplyVo commentReplyVo1 : commentReplyVoList){
				com.card.api.vo.vo.CommentReplyVo commentReplyVo2 = new com.card.api.vo.vo.CommentReplyVo();
				BeanUtils.copyProperties(commentReplyVo1,commentReplyVo2);
				commentReplyVoList1.add(commentReplyVo2);
			}
			myCommentReplyResponse.setCommentReplyVoList(commentReplyVoList1);
		}
		return myCommentReplyResponse;
	}

	/**
	 * @notes 更新未读消息
	 * @param updateCommentReplyParam
	 * @return
	 */
	public UpdateCommentReplyResponse updateCommentReply(UpdateCommentReplyParam updateCommentReplyParam) throws Exception {
		UpdateCommentReplyResponse updateCommentReplyResponse = new UpdateCommentReplyResponse();
		if (updateCommentReplyParam == null) {
			throw new BusinessException(1, "获取信息失败");
		}
		if (StringUtils.isBlank(updateCommentReplyParam.getBusinessKey())) {
			throw new BusinessException(1, "业务key不能为空");
		}
		Map<String, Object> keyMap = JedisUtil.getKeyMap(updateCommentReplyParam.getBusinessKey());
		if (keyMap == null || keyMap.isEmpty()) {
			throw new BusinessException(1, "获取用户信息失败");
		}
		User user = userService.findUserByOpenId(keyMap.get("openId").toString());
		if(null == user){
			throw new BusinessException(1, "未查询到用户信息");
		}

		CommentReply commentReply = new CommentReply();
		commentReply.setToUid(user.getId());
		Integer id = updateCommentReplyParam.getId();
		if(null != id){
			commentReply.setId(id);
		}

		commentReplyService.updateCommentReply(commentReply);
		return updateCommentReplyResponse;
	}
}
