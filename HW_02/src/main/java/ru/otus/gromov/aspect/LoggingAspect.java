package ru.otus.gromov.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* ru.otus.gromov.service.TestingServiceImpl.getBatchTestQuestions(..))")
    public Object logBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("Call method:  " + joinPoint.getSignature().getName());
        try {
            Object result = joinPoint.proceed();
            LOGGER.info("After Call method:  " + joinPoint.getSignature().getName() + " :: " + result);
            return result;
        } catch (Exception e) {
            LOGGER.debug("Call method:  " + joinPoint.getSignature().getName());
            LOGGER.error(" trow exception: " + e.getMessage());
            throw e;
        }
    }
}
