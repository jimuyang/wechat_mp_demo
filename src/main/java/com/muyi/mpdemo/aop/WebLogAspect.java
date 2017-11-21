package com.muyi.mpdemo.aop;

import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @Author: muyi
 * @Date: Created in 18:55 2017/11/20
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(public * com.muyi.mpdemo.controller..*(..))")
    public void webLog(){
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //接收到请求，log请求内容
        //log.info("WebLogAspect.doBefore()");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录请求内容
        log.info("【收到web请求】{}:{}",request.getMethod(),request.getRequestURL().toString());
        //获取所有参数方法一：
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            //log.info( "{}:{}",paraName,request.getParameter(paraName));
            stringBuilder.append(paraName + ":" + request.getParameter(paraName) + ",");
        }
        log.info("             PARAMS:{}",stringBuilder.toString());
        //log.info("IP:{}",request.getRemoteAddr());
        log.info("CLASS_METHOD:{} ",joinPoint.getSignature().getDeclaringTypeName() + "..." + joinPoint.getSignature().getName());
        //log.info("ARGS:{}",Arrays.toString(joinPoint.getArgs()));

    }


    @AfterReturning(value = "webLog()",returning = "result")
    public void doAfterReturning(JoinPoint joinPoint,Object result){
        //处理完请求
        //log.info("【AfterReturning】...");
        log.info("【AfterReturning】result:{}",JsonUtil.toJson(result));
//        log.info("【AfterReturning】toLongString:{}",joinPoint.toLongString());
//        log.info("【AfterReturning】getSignature:{}",joinPoint.getSignature());
//        log.info("【AfterReturning】getArgs:{}",JsonUtil.toPrettyJson(joinPoint.getArgs()));
//        log.info("【AfterReturning】getKind:{}",JsonUtil.toPrettyJson(joinPoint.getKind()));
//        log.info("【AfterReturning】getSourceLocation:{}",JsonUtil.toPrettyJson(joinPoint.getSourceLocation()));
//        log.info("【AfterReturning】getTarget:{}",JsonUtil.toPrettyJson(joinPoint.getTarget()));
        //log.info("【AfterReturning】getThis:{}",JsonUtil.toPrettyJson(joinPoint.getThis()));

    }

    //@Around("webLog()")
    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
        //log.info("【aroundLog】:");
        //获取参数
        Object[] args = pjp.getArgs();
        for (Object o: args) {
            log.info("【aroundLog】arg:{}",o.toString());
        }
        Object result = pjp.proceed();
        log.info("【aroundLog】result:{}",result.toString());
        return result;
    }

}
