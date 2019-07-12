package com.card.api.service;

import com.card.api.common.exception.BusinessException;
import com.card.api.vo.params.*;
import com.card.api.vo.responses.*;

/**
 * @notes:评论信息Service接口
 *
 * @author zzh
 *
 * 2018-08-21 14:56:12		Email:azhangzhihengi@163.com
 */
public interface CommentService {

    /**
     * @notes 用户评论
     * @param addCommentParam
     * @return
     */
    public AddCommentResponse addComment(AddCommentParam addCommentParam) throws Exception;

    /**
     * @notes 保存回复信息
     * @param addCommentReplyParam
     * @return
     */
    public AddCommentReplyResponse addCommentReply(AddCommentReplyParam addCommentReplyParam) throws Exception;

    /**
     * @notes 查询我的消息
     * @param myCommentReplyParam
     * @return
     */
    public MyCommentReplyResponse myCommentReply(MyCommentReplyParam myCommentReplyParam) throws Exception;

    /**
     * @notes 更新未读消息
     * @param updateCommentReplyParam
     * @return
     */
    public UpdateCommentReplyResponse updateCommentReply(UpdateCommentReplyParam updateCommentReplyParam) throws Exception;
}
