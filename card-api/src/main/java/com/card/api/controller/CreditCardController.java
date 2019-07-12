package com.card.api.controller;

import com.card.api.service.CreditCardService;
import com.card.api.utils.OutVo;
import com.card.api.vo.params.*;
import com.card.api.vo.responses.*;
import io.swagger.annotations.*;
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
 * @信用卡信息控制层
 */
@RestController
@RequestMapping("/creditCard")
@Api(value = "性用卡controller", description = "信用卡信息")
public class CreditCardController {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardController.class);

    @Autowired
    private CreditCardService creditCardService;

    @RequestMapping(value = "findCreditCardList", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "信用卡列表", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = CreditCardListResponse.class)
    public OutVo<Response> findCreditCardList(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "信用卡列表信息入参", required = true) @RequestBody CreditCardListParam params){
        logger.info("==================信用卡列表params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = creditCardService.findCreditCardList(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("信用卡列表失败", e);
            out.setCode(1);
            out.setShowMsg("信用卡列表失败");
        }
        return out;
    }


    @RequestMapping(value = "findBankCreditCardList", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "银行信用卡列表", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = BankCreditCardListResponse.class)
    public OutVo<Response> findBankCreditCardList(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "信用卡列表信息入参", required = true) @RequestBody BankCreditCardListParam params){
        logger.info("==================银行信用卡列表params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = creditCardService.findBankCreditCardList(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("银行信用卡列表失败", e);
            out.setCode(1);
            out.setShowMsg("银行信用卡列表失败");
        }
        return out;
    }

    @RequestMapping(value = "findCreditCardDetail", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "信用卡详情", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = CreditCardDetailResponse.class)
    public OutVo<Response> findCreditCardDetail(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "信用卡详情信息入参", required = true) @RequestBody CreditCardDetailParam params){
        logger.info("==================信用卡详情params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = creditCardService.findCreditCardDetail(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("信用卡详情失败", e);
            out.setCode(1);
            out.setShowMsg("信用卡详情失败");
        }
        return out;
    }

    @RequestMapping(value = "findCreditCardComment", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "信用卡评论", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = CreditCardCommentResponse.class)
    public OutVo<Response> findCreditCardComment(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "信用卡评论查询信息入参", required = true) @RequestBody CreditCardCommentParam params){
        logger.info("==================信用卡评论params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = creditCardService.findCreditCardComment(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("信用卡评论失败", e);
            out.setCode(1);
            out.setShowMsg("信用卡评论失败");
        }
        return out;
    }

    @RequestMapping(value = "findCreditCardConcern", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "信用卡关注列表", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = CreditCardConcernResponse.class)
    public OutVo<Response> findCreditCardConcern(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "信用卡关注信息入参", required = true) @RequestBody CreditCardConcernParam params){
        logger.info("==================信用卡关注params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = creditCardService.findCreditCardConcernPage(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("信用卡关注列表失败", e);
            out.setCode(1);
            out.setShowMsg("信用卡关注列表失败");
        }
        return out;
    }

    @RequestMapping(value = "findBankList", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "查询银行信息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = BankResponse.class)
    public OutVo<Response> findBankList(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "查询银行信息入参", required = true) @RequestBody BankParam params){
        logger.info("==================信用卡关注params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = creditCardService.findBankList(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("查询银行信息失败", e);
            out.setCode(1);
            out.setShowMsg("查询银行信息失败");
        }
        return out;
    }

    @RequestMapping(value = "queryCreditCardList", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "查询银行信息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = BankCreditCardQueryResponse.class)
    public OutVo<Response> queryCreditCardList(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "查询银行信息入参", required = true) @RequestBody BankCreditCardListQueryParam params){
        logger.info("==================信用卡关注params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = creditCardService.queryCreditCardList(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("查询银行信息失败", e);
            out.setCode(1);
            out.setShowMsg("查询银行信息失败");
        }
        return out;
    }

    @RequestMapping(value = "addCreditCardApplyData", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "保存信用卡申请信息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = AddCreditCardApplyDataResponse.class)
    public OutVo<Response> addCreditCardApplyData(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "保存信用卡申请信息入参", required = true) @RequestBody AddCreditCardApplyDataParam params){
        logger.info("==================保存信用卡申请信息params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = creditCardService.addCreditCardApplyData(params);
            // 设置响应值
            out.setData(resp);
        }catch (Exception e) {
            logger.error("查询银行信息失败", e);
            out.setCode(1);
            out.setShowMsg("查询银行信息失败");
        }
        return out;
    }
}
