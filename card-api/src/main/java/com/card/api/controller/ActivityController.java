package com.card.api.controller;

import com.card.api.utils.OutVo;
import com.card.api.vo.params.ActivityCreditCardListParam;
import com.card.api.vo.params.ActivityDetailParam;
import com.card.api.vo.params.ActivityListParam;
import com.card.api.vo.params.JoinActivityParam;
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
@RequestMapping(value = "/activity")
@Api(value = "活动controller", description = "活动")
public class ActivityController {

	private static Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@Autowired
	private com.card.api.service.ActivityService activityService;

	@RequestMapping(value = "findActivityList", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查询活动列表", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = ActivityListResponse.class)
	public OutVo<Response> findActivityList(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "查询活动列表入参", required = true) @RequestBody ActivityListParam params){
		logger.info("==================查询活动列表params"+JSONObject.fromObject(params));
		OutVo<Response> out = new OutVo<Response>();
		// 返回参数
		Response resp = null;
		try {
			resp = activityService.findActivityList(params);
			// 设置响应值
			out.setData(resp);
		}catch (Exception e) {
			logger.error("保存用户信息失败", e);
			out.setCode(1);
			out.setShowMsg("保存用户信息失败");
		}
		return out;
	}

	@RequestMapping(value = "findActivityDetail", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查询活动详情", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = ActivityDetailResponse.class)
	public OutVo<Response> findActivityDetail(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "查询活动列表入参", required = true) @RequestBody ActivityDetailParam params){
		logger.info("==================查询活动详情params"+JSONObject.fromObject(params));
		OutVo<Response> out = new OutVo<Response>();
		// 返回参数
		Response resp = null;
		try {
			resp = activityService.findActivityDetail(params);
			// 设置响应值
			out.setData(resp);
		}catch (Exception e) {
			logger.error("保存用户信息失败", e);
			out.setCode(1);
			out.setShowMsg("保存用户信息失败");
		}
		return out;
	}

	@RequestMapping(value = "findActivityCreditCardPage", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
	@ApiOperation(value = "查询活动相关信用卡分页", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = ActivityCreditCardListResponse.class)
	public OutVo<Response> findActivityCreditCardPage(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "查询活动相关信用卡分页列表入参", required = true) @RequestBody ActivityCreditCardListParam params){
		logger.info("==================查询活动相关信用卡分页params"+JSONObject.fromObject(params));
		OutVo<Response> out = new OutVo<Response>();
		// 返回参数
		Response resp = null;
		try {
			resp = activityService.findActivityCreditCardPage(params);
			// 设置响应值
			out.setData(resp);
		}catch (Exception e) {
			logger.error("保存用户信息失败", e);
			out.setCode(1);
			out.setShowMsg("保存用户信息失败");
		}
		return out;
	}

	@RequestMapping(value = "joinActivity", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
	@ApiOperation(value = "参与活动", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = JoinActivityResponse.class)
	public OutVo<Response> joinActivity(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "参与活动入参", required = true) @RequestBody JoinActivityParam params){
		logger.info("==================参与活动params"+JSONObject.fromObject(params));
		OutVo<Response> out = new OutVo<Response>();
		// 返回参数
		Response resp = null;
		try {
			resp = activityService.joinActivity(params);
			// 设置响应值
			out.setData(resp);
		}catch (Exception e) {
			logger.error("参与活动", e);
			out.setCode(1);
			out.setShowMsg("参与活动");
		}
		return out;
	}
}
