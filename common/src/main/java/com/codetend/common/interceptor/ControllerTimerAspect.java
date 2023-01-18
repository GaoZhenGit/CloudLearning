package com.codetend.common.interceptor;

import com.codetend.common.util.RequestUtil;
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
@Order(7)
public class ControllerTimerAspect {
    @Autowired
    private ThreadLocal<Long> startTime;

    @Pointcut("execution(public * com.codetend..*.controllers..*.*(..))")
    public void printTime() {

    }

    @Before("printTime()")
    private void requestPrint(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(returning = "ret", pointcut = "printTime()")
    private void responsePrint(Object ret) {
        long cost = System.currentTimeMillis() - startTime.get();
        log.debug("------API:{} cost:{}------", RequestUtil.getCurrentUrl(), cost);
    }
}
