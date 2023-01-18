package com.codetend.common.interceptor;

import com.codetend.common.util.RequestUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@Order(5)
public class ControllerLogAspect {
    @Autowired
    private Gson gson;

    @Pointcut("execution(public * com.codetend..*.controllers..*.*(..))")
    public void printLog() {

    }

    @Before("printLog()")
    private void requestPrint(JoinPoint joinPoint) {
        log.info("======API start:" + RequestUtil.getCurrentUrl() + "=====");
        log.info("req:" + gson.toJson(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "printLog()")
    private void responsePrint(Object ret) {
        log.info("rsp:" + gson.toJson(ret));
        log.info("======API end:" + RequestUtil.getCurrentUrl() + "=====");
    }
}
