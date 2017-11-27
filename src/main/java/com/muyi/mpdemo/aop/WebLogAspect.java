package com.muyi.mpdemo.aop;

import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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

    @Pointcut("execution(public * com.muyi.mpdemo.controller.*.*(..))")
    public void webLog(){ }

    //@Before("webLog()")
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

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();

    }


    //@AfterReturning(value = "webLog()",returning = "result")
    public void doAfterReturning(JoinPoint joinPoint,Object result){
        //处理完请求
        //log.info("【AfterReturning】...");
        log.info("【AfterReturning】result:{}",JsonUtil.toJson(result));

    }

    @Around("webLog()")
    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
        //log.info("【aroundLog】:");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录请求参数
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            stringBuilder.append(paraName + " :" + request.getParameter(paraName) + ", ");
        }

        //记录请求内容
        log.info("【WebRequest   】{}   {}",request.getMethod(),request.getRequestURL().toString());
        log.info("【RequestParams】{}",stringBuilder.toString());

        //获取参数
        Object[] args = pjp.getArgs();
        StringBuilder argsBuilder = new StringBuilder();
//        for (Object o: args) {
//            argsBuilder.append(o.toString());
//        }

        log.info("【ControllerLog】method:{}",pjp.getSignature().getDeclaringTypeName() + "..." + pjp.getSignature().getName());
        log.info("【ControllerLog】args  :{}",JsonUtil.toJson(args));
        Object result = pjp.proceed();
        log.info("【ControllerLog】result:{}",JsonUtil.toJson(result));
        return result;
    }

}
