package com.muyi.mpdemo.aop;

import com.muyi.mpdemo.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Author: muyi
 * @Date: Created in 10:26 2017/11/22
 * @Description:
 */

@Slf4j
@Aspect
@Component
public class ServiceLogAspect {


    @Pointcut("execution(public * com.muyi.mpdemo.service.*.*(..))")
    public void serviceLog(){ }

    @Around("serviceLog()")
    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
        //获取参数
        Object[] args = pjp.getArgs();
        StringBuilder argsBuilder = new StringBuilder();
        for (Object o: args) {
            argsBuilder.append(o.toString());
        }

        log.info("【ServiceLog】method:{}",pjp.getSignature().getDeclaringTypeName() + "..." + pjp.getSignature().getName());
        log.info("【ServiceLog】args:{}",argsBuilder.toString());
        Object result = pjp.proceed();
        log.info("【ServiceLog】result:{}", JsonUtil.toJson(result));
        return result;
    }




}
