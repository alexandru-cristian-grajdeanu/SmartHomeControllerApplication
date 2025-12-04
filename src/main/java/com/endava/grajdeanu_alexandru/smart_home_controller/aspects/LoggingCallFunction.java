package com.endava.grajdeanu_alexandru.smart_home_controller.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingCallFunction {
    private final Logger logger = LoggerFactory.getLogger(LoggingCallFunction.class);

    @Before("execution(* com.endava.grajdeanu_alexandru.smart_home_controller.services..*(..))")
    public void logBeforeMethodCall(JoinPoint joinPoint) {
        logger.info(
                "Calling method: {}",
                joinPoint.getSignature().getName()
        );
    }

    @Around("execution(* com.endava.grajdeanu_alexandru.smart_home_controller.services..*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info(
                "Method {} executed in {} ms",
                joinPoint.getSignature().getName(),
                (endTime - startTime)
        );
        return result;
    }


    @AfterThrowing(
            pointcut = "execution(* com.endava.grajdeanu_alexandru.smart_home_controller.services..*(..))",
            throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in method {} ->  {}",
                joinPoint.getSignature().toShortString(),
                ex.getMessage());
    }
}
