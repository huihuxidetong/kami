/**
 *
 */
package com.card.api.controller.common;
import com.card.api.common.exception.BusinessException;
import com.card.api.service.CommonService;
import com.card.api.utils.JedisUtil;
import com.card.api.utils.OutVo;
import com.card.api.vo.params.CheckUserLoginInfoParam;
import com.card.api.vo.responses.Response;
import com.card.api.vo.responses.WxSessionResponse;
import io.swagger.annotations.*;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @notes 通用
 */
@RestController
@RequestMapping(value = "/common")
@Api(value = "通用controller", description = "通用操作")
public class CommonController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/createSssion", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "微信openId获取", httpMethod = "GET", notes = "结果信息通过json格式返回", response = WxSessionResponse.class)
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", dataType = "String", name = "wxCode", value = "微信code", required = true)})
    public OutVo<Response> createSssion(HttpServletRequest request, HttpServletResponse response, String wxCode) {
        logger.info("==================首页信息入参wxCode"+wxCode);
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = commonService.createSssion(wxCode);
            // 设置响应值
            out.setData(resp);
        }catch (BusinessException e) {
            out.setCode(e.getErrorCode());
            out.setShowMsg(e.getMessage());
        } catch (Exception e) {
            logger.error("查询进行中的游戏失败", e);
            out.setCode(1);
            out.setShowMsg("获取游戏信息失败");
        }
        return out;
    }

    @RequestMapping(value = "/checkBusinessKey", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "检查用户KEY", httpMethod = "POST", notes = "结果通过json格式返回", response = Response.class)
    public OutVo<Response> checkBusinessKey(HttpServletRequest request, HttpServletResponse response,  @ApiParam(name = "params", value = "检查用户KEY入参", required = true) @RequestBody CheckUserLoginInfoParam params) {
        logger.info("==================首页信息入参params"+JSONObject.fromObject(params));
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            if (params != null) {
                Map<String, Object> keyMap = JedisUtil.getKeyMap(params.getBusinessKey());
                if (keyMap == null || keyMap.isEmpty()) {
                    out.setShowMsg("key失效");
                    out.setCode(1);
                }
            } else {
                out.setShowMsg("获取信息失败，请重新授权");
                out.setCode(1);
            }
            // 设置响应值
            out.setData(resp);
        } catch (Exception e) {
            logger.error("获取信息失败，请重新授权", e);
            out.setCode(1);
            out.setShowMsg("获取信息失败，请重新授权");
        }
        return out;
    }
}
