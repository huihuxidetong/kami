package com.card.api.common.intercept;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ServiceLog{  

	private static Logger log = LoggerFactory.getLogger(ServiceLog.class);
	
    //在类里面写方法，方法名诗可以任意的。此处我用标准的before和after来表示  
   public void before(JoinPoint joinPoint){
	   try{
		   Object[] obj = joinPoint.getArgs();
		   String params = "";
			for(Object o:obj){
				if (o instanceof List) {
					 o=((List)o).size();   
				 }
				params = params +"|"+ o;
			}
		   log.info("service before:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()" 
		+ " params:" + params )); 
	   }catch (Exception e) {  
           //记录本地异常日志  
		   log.error("==前置通知异常==");  
		   log.error("异常信息:{}", e.getMessage());  
       }  
   }  
   public void after(JoinPoint joinPoint){
	   Object[] obj = joinPoint.getArgs();
	   String params = "";
		for(Object o:obj){
			 if (o instanceof List) {
				 o=((List)o).size();   
			 }
			params = params +"|"+ o;
		}
	   log.info(" service after:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()" 
	+ " params:" + params )); 
	   
   }  
   
}  
