package com.card.common.intercept;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 控制器里的参数拦截
 * @author Administrator
 *
 */
public class ControlLog {

	private static Logger log = LoggerFactory.getLogger(ControlLog.class);
	
	//在类里面写方法，方法名诗可以任意的。此处我用标准的before和after来表示  
    //此处的JoinPoint类可以获取，action所有的相关配置信息和request等内置对象。  
	//进入前记录日志
	@Before("controllerAspect()")
	public void before(JoinPoint joinpoint){
		Object[] obj = joinpoint.getArgs();//此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象  
		String params = "";
		
		for(Object o:obj){
			params = params + o;
		}
		log.info("control before之前:" + (joinpoint.getTarget().getClass().getName() + "." + joinpoint.getSignature().getName() + "()" 
		+ " params:" + params ));  
	} 
	
	//结束访问时记录日志
	@After("controllerAspect()")
	public void after(JoinPoint joinpoint ){
//		Object[] obj = joinpoint.getArgs();//此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象  
//		String params = "";
//		
//		for(Object o:obj){
//			params = params + o;
//		}
//		log.info("control after结束:" + (joinpoint.getTarget().getClass().getName() + "." + joinpoint.getSignature().getName() + "()" 
//		+ " return params:" + result ));  
//		log.info("------" + JSONObject.fromObject(result).toString());
//		log.info("22222222" );
	}  
	
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
       HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
       //获取请求ip    
       String ip = request.getRemoteAddr();    
       //获取用户请求方法的参数并序列化为JSON格式字符串    
       String params = "";    
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {    
               params += JSONObject.parseObject((joinPoint.getArgs()[i]).toString()) + ";";
           }    
       }    
        try {    
             /*========控制台输出=========*/    
        	log.info("=====异常通知开始=====" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()") + " params:" + params);
              /*==========数据库日志=========*/    
       }  catch (Exception ex) {    
           log.error("==异常通知异常==");    
           log.error("异常信息:{}", ex.getMessage());    
       }    
       log.error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);    
   }    
	
}
