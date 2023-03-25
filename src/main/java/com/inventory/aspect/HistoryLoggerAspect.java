package com.inventory.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class HistoryLoggerAspect {
    @After("execution(* com.inventory.service.HistoryService.saveHistory(..))")
    public void afterHistorySaveCalled(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();

        log.info("||||||  HistoryService Called. " + args.length);
    }
}
