package com.it211_ss12_04.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.it211_ss12_04.controller.StudentController.*(..))")
    public void logBeforeController(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("[AOP @Before] Controller method: {} | Args: {}", methodName, Arrays.toString(args));
    }

    @AfterThrowing(pointcut = "execution(* com.it211_ss12_04.service.StudentService.*(..))", throwing = "ex")
    public void logAfterThrowingService(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        log.warn("[AOP @AfterThrowing] Exception in Service method {}: {}", methodName, ex.getMessage());
    }

    @Around("execution(* com.it211_ss12_04.controller.StudentController.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("[AOP @Around] Controller method {} thuc thi mat: {} ms", methodName, timeTaken);

        return proceed;
    }
}