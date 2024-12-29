package com.sushavi.stats.cc;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class StatsAppAspect {


    @Around("@annotation(com.sushavi.stats.cc.LogExecutionTime)")
    public Object beforeMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Method : {} with Execution time {}", joinPoint.getSignature().getName(), (endTime-startTime));
        return result;
    }

}
