package com.card.api.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * 项目名称：apidoc
 *
 * @description:
 * @author chenz
 * @create_time：2018-1-25
 * @version V1.0.0
 *
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
// Loads the spring beans required by the framework
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        //自定义异常信息
        ArrayList<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder().code(200).message("成功").build());
            add(new ResponseMessageBuilder().code(400).message("请求参数错误").responseModel(new ModelRef("Error")).build());
//               add(new ResponseMessageBuilder().code(401).message("权限认证失败").responseModel(new ModelRef("Error")).build());
//               add(new ResponseMessageBuilder().code(403).message("请求资源不可用").responseModel(new ModelRef("Error")).build());
            add(new ResponseMessageBuilder().code(404).message("请求资源不存在").responseModel(new ModelRef("Error")).build());
//               add(new ResponseMessageBuilder().code(409).message("请求资源冲突").responseModel(new ModelRef("Error")).build());
//               add(new ResponseMessageBuilder().code(415).message("请求格式错误").responseModel(new ModelRef("Error")).build());
//               add(new ResponseMessageBuilder().code(423).message("请求资源被锁定").responseModel(new ModelRef("Error")).build());
            add(new ResponseMessageBuilder().code(500).message("服务器内部错误").responseModel(new ModelRef("Error")).build());
//               add(new ResponseMessageBuilder().code(501).message("请求方法不存在").responseModel(new ModelRef("Error")).build());
//               add(new ResponseMessageBuilder().code(503).message("服务暂时不可用").responseModel(new ModelRef("Error")).build());
            add(new ResponseMessageBuilder().code(-1).message("未知异常").responseModel(new ModelRef("Error")).build());
        }};
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInf()).select()
                .apis(RequestHandlerSelectors.basePackage("com.card.api.controller"))// controller路径
                .paths(PathSelectors.any()).build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages);
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder().title("信用卡").termsOfServiceUrl(null)
                .description("API接口信息")
                .contact(new Contact("zzh", "azhangzhihengi@163.com", null)).version("1.0").build();
    }
}