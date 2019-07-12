package com.card.api.controller;

import com.card.api.common.exception.BusinessException;
import com.card.api.service.UserService;
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

/**
 * @用户信息控制层
 */
@RestController
@RequestMapping("/users")
@Api(value = "用户信息controller", description = "用户信息")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService usersService;

    @RequestMapping(value = "saveUsers", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "保存用户信息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = SaveUserResponse.class)
    public OutVo<Response> saveUsers(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "保存用户信息入参", required = true) @RequestBody SaveUserParam params) {
        logger.info("==================保存用户信息params" + JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.saveUsers(params);
            // 设置响应值
            out.setData(resp);
        } catch (BusinessException e) {
            out.setCode(e.getErrorCode());
            out.setShowMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("保存用户信息失败", e);
            out.setCode(1);
            out.setShowMsg("保存用户信息失败");
        }
        return out;
    }

    @RequestMapping(value = "myInfo", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "我的信息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = MyInfoResponse.class)
    public OutVo<Response> myInfo(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "我的信息入参", required = true) @RequestBody MyInfoParam params) {
        logger.info("==================保存用户信息params" + JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.findMyInfo(params);
            // 设置响应值
            out.setData(resp);
        } catch (BusinessException e) {
            out.setCode(e.getErrorCode());
            out.setShowMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("我的信息", e);
            out.setCode(1);
            out.setShowMsg("我的信息");
        }
        return out;
    }


    @RequestMapping(value = "findMyCreditCardList", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "我的信用卡列表", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = MyCreditCardListResponse.class)
    public OutVo<Response> findMyCreditCardList(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "信用卡列表信息入参", required = true) @RequestBody MyCreditCardListParam params){
        logger.info("==================信用卡列表params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.findMyCreditCardList(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("我的信用卡列表", e);
            out.setCode(1);
            out.setShowMsg("我的信用卡列表");
        }
        return out;
    }

    @RequestMapping(value = "addMyCreditCard", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "绑定信用卡", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = AddMyCreditCardResponse.class)
    public OutVo<Response> addMyCreditCard(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "信用卡列表信息入参", required = true) @RequestBody AddMyCreditCardParam params){
        logger.info("==================绑定信用卡params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.addMyCreditCard(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("绑定信用卡", e);
            out.setCode(1);
            out.setShowMsg("绑定信用卡");
        }
        return out;
    }

    @RequestMapping(value = "findMyLableList", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "查询我的标签", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = MyLableListResponse.class)
    public OutVo<Response> findMyLableList(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "信用卡列表信息入参", required = true) @RequestBody MyLableListParam params){
        logger.info("==================查询我的标签params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.findMyLableList(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("查询我的标签失败", e);
            out.setCode(1);
            out.setShowMsg("查询我的标签失败");
        }
        return out;
    }

    @RequestMapping(value = "addCustomLable", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "添加自定义标签", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = AddCustomLableResponse.class)
    public OutVo<Response> addCustomLable(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "添加自定义标签入参", required = true) @RequestBody AddCustomLableParam params){
        logger.info("==================添加自定义标签params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.addCustomLable(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("添加自定义标签失败", e);
            out.setCode(1);
            out.setShowMsg("添加自定义标签失败");
        }
        return out;
    }

    @RequestMapping(value = "delUserChooseLable", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "删除用户标签", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = DelUserChooseLableResponse.class)
    public OutVo<Response> delUserChooseLable(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "删除用户标签入参", required = true) @RequestBody DelUserChonnseLableParam params){
        logger.info("==================删除用户标签params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.delUserChooseLable(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("删除用户标签失败", e);
            out.setCode(1);
            out.setShowMsg("删除用户标签失败");
        }
        return out;
    }

    @RequestMapping(value = "findUserConcernBasic", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "查询用户关注信息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = UserConcernBasicResponse.class)
    public OutVo<Response> findUserConcernBasic(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "查询用户关注信息入参", required = true) @RequestBody UserConcernBasicParam params){
        logger.info("==================查询用户关注信息params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.findUserConcernBasic(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("查询用户关注信息失败", e);
            out.setCode(1);
            out.setShowMsg("查询用户关注信息失败");
        }
        return out;
    }

    @RequestMapping(value = "saveChannelData", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "保存渠道点击信息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = SaveChannelDataResponse.class)
    public OutVo<Response> saveChannelData(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "保存渠道点击入参", required = true) @RequestBody SaveChannelDataParam params){
        logger.info("==================保存渠道点击信息params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.saveChannelData(params);
            // 设置响应值
            out.setData(resp);
        }catch (BusinessException e) {
            out.setCode(e.getErrorCode());
            out.setShowMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("保存广告主点击异常", e);
            out.setCode(1);
            out.setShowMsg("保存广告主点击异常");
        }
        return out;
    }

    @RequestMapping(value = "addUserConcernBasic", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "用户关注信息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = AddUserConcernBasicResponse.class)
    public OutVo<Response> addUserConcernBasic(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "用户关注信息入参", required = true) @RequestBody AddUserConcernBasicParam params){
        logger.info("==================用户关注信息params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.addUserConcernBasic(params);
            // 设置响应值
            out.setData(resp);
        }catch (BusinessException e) {
            out.setCode(e.getErrorCode());
            out.setShowMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("保存广告主点击异常", e);
            out.setCode(1);
            out.setShowMsg("保存广告主点击异常");
        }
        return out;
    }

    @RequestMapping(value = "updateUserConcernBasic", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "用户取消关注信息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = UpdateUserConcernBasicResponse.class)
    public OutVo<Response> updateUserConcernBasic(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "用户关注信息入参", required = true) @RequestBody UpdateUserConcernBasicParam params){
        logger.info("==================用户取消关注信息params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.updateUserConcernBasic(params);
            // 设置响应值
            out.setData(resp);
        }catch (BusinessException e) {
            out.setCode(e.getErrorCode());
            out.setShowMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("用户取消关注异常", e);
            out.setCode(1);
            out.setShowMsg("用户取消关注异常");
        }
        return out;
    }

    @RequestMapping(value = "getOpenId", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取openId", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = GetOpenIdResponse.class)
    public OutVo<Response> getOpenId(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "获取openId入参", required = true) @RequestBody GetOpenIdParam params){
        logger.info("==================获取openIdparams"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.getOpenId(params);
            // 设置响应值
            out.setData(resp);
        }catch (BusinessException e) {
            out.setCode(e.getErrorCode());
            out.setShowMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("获取openId异常", e);
            out.setCode(1);
            out.setShowMsg("获取openId异常");
        }
        return out;
    }

    @RequestMapping(value = "sendServiceMsg", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "发送客服消息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = SendServiceMsgResponse.class)
    public OutVo<Response> sendServiceMsg(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "发送客服消息入参", required = true) @RequestBody SendServiceMsgParam params){
        logger.info("==================获取openIdparams"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = usersService.sendServiceMsg(params);
            // 设置响应值
            out.setData(resp);
        }catch (BusinessException e) {
            out.setCode(e.getErrorCode());
            out.setShowMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("获取openId异常", e);
            out.setCode(1);
            out.setShowMsg("获取openId异常");
        }
        return out;
    }

}
