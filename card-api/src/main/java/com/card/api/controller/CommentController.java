package com.card.api.controller;

import com.card.api.service.CommentService;
import com.card.api.utils.OutVo;
import com.card.api.vo.params.*;
import com.card.api.vo.responses.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "comment")
@Api(value = "留言controller", description = "用户留言")
public class CommentController  {

	private static Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "addComment", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
	@ApiOperation(value = "用户评论", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = AddCommentResponse.class)
	public OutVo<Response> addComment(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "用户评论信息入参", required = true) @RequestBody AddCommentParam params){
		logger.info("==================用户评论params"+JSONObject.fromObject(params));
		OutVo<Response> out = new OutVo<Response>();
		// 返回参数
		Response resp = null;
		try {
			resp = commentService.addComment(params);
			// 设置响应值
			out.setData(resp);
		}catch (Exception e) {
			logger.error("用户评论失败", e);
			out.setCode(1);
			out.setShowMsg("用户评论失败");
		}
		return out;
	}

	@RequestMapping(value = "addCommentReply", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
	@ApiOperation(value = "用户回复", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = AddCommentReplyResponse.class)
	public OutVo<Response> addCommentReply(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "用户回复信息入参", required = true) @RequestBody AddCommentReplyParam params){
		logger.info("==================用户回复params"+JSONObject.fromObject(params));
		OutVo<Response> out = new OutVo<Response>();
		// 返回参数
		Response resp = null;
		try {
			resp = commentService.addCommentReply(params);
			// 设置响应值
			out.setData(resp);
		}catch (Exception e) {
			logger.error("用户回复失败", e);
			out.setCode(1);
			out.setShowMsg("用户回复失败");
		}
		return out;
	}

	@RequestMapping(value = "myCommentReply", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
	@ApiOperation(value = "我的消息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = MyCommentReplyResponse.class)
	public OutVo<Response> myCommentReply(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "我的消息入参", required = true) @RequestBody MyCommentReplyParam params){
		logger.info("==================我的消息params"+JSONObject.fromObject(params));
		OutVo<Response> out = new OutVo<Response>();
		// 返回参数
		Response resp = null;
		try {
			resp = commentService.myCommentReply(params);
			// 设置响应值
			out.setData(resp);
		}catch (Exception e) {
			logger.error("我的消息失败", e);
			out.setCode(1);
			out.setShowMsg("我的消息失败");
		}
		return out;
	}

	@RequestMapping(value = "updateCommentReply", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
	@ApiOperation(value = "阅读消息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = UserConcernBasicResponse.class)
	public OutVo<Response> updateCommentReplyById(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "阅读消息入参", required = true) @RequestBody UpdateCommentReplyParam params){
		logger.info("==================阅读单条消息params"+JSONObject.fromObject(params));
		OutVo<Response> out = new OutVo<Response>();
		// 返回参数
		Response resp = null;
		try {
			resp = commentService.updateCommentReply(params);
			// 设置响应值
			out.setData(resp);
		}catch (Exception e) {
			logger.error("阅读消息失败", e);
			out.setCode(1);
			out.setShowMsg("阅读消息失败");
		}
		return out;
	}
}
