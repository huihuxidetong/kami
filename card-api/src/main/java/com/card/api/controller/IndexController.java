package com.card.api.controller;

import com.card.api.common.exception.BusinessException;
import com.card.api.service.IndexService;
import com.card.api.service.UserService;
import com.card.api.utils.OutVo;
import com.card.api.vo.params.IndexParam;
import com.card.api.vo.responses.IndexResponse;
import com.card.api.vo.responses.Response;
import com.card.api.vo.responses.SaveUserResponse;
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
 * @首页控制层
 */
@RestController
@RequestMapping("/index")
@Api(value = "首页controller", description = "首页")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "findIndexInfo", method = RequestMethod.POST,  produces = "application/json; charset=utf-8")
    @ApiOperation(value = "首页信息", httpMethod = "POST", notes = "结果信息通过json格式返回" ,response = IndexResponse.class)
    public OutVo<Response> saveUsers(HttpServletRequest request, HttpServletResponse response, @ApiParam(name = "params", value = "首页信息入参", required = true) @RequestBody IndexParam params){
        logger.info("==================首页信息params"+params);
        OutVo<Response> out = new OutVo<Response>();
        // 返回参数
        Response resp = null;
        try {
            resp = indexService.findIndexInfo(params);
            // 设置响应值
            out.setData(resp);
        } catch (Exception e) {
            logger.error("获取首页信息失败", e);
            out.setCode(1);
            out.setShowMsg("获取首页信息失败");
        }
        return out;
    }
}
