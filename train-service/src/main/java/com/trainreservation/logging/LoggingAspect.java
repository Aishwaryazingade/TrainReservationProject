package com.trainreservation.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component

public class LoggingAspect {
	    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	    
	 @Pointcut("execution(*  com.trainreservation.service.service.TrainServiceImpl.*(..))")
	    public void serviceMethods() {
	    }
	    @Before("serviceMethods()")
	    public void logBefore(JoinPoint joinPoint) {
	        logger.info("A method in TrainServiceImpl is about to be executed. Method: " + joinPoint.getSignature().getName() + ", Arguments: " + Arrays.toString(joinPoint.getArgs()));
	    }
	    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
	    public void logAfterReturning(JoinPoint joinPoint, Object result) {
	        logger.info("A method in TrainServiceImpl has executed successfully. Method: " + joinPoint.getSignature().getName() + ", Result: " + result);
	    }
	    @AfterThrowing(pointcut = "serviceMethods()", throwing = "error")
	    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
	        logger.error("An error occurred in TrainServiceImpl. Method: " + joinPoint.getSignature().getName() + ", Error: " + error.getMessage());
	    }
	}
	 


