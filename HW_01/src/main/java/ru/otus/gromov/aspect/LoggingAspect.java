package ru.otus.gromov.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* ru.otus.gromov.service.TestingServiceImpl.*(..))")
    public Object logBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Call method:  " + joinPoint.getSignature().getName());
        try {
            Object result = joinPoint.proceed();
            System.out.println("Call after method: " + result.toString());
            return result;
        } catch (Exception e) {
            System.out.println("Call method:  " + joinPoint.getSignature().getName());
            System.out.println(" trow exception: " + e.getMessage());
            throw e;
        }
    }
}
