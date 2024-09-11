package com.dedalus.amphi_integration.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.dedalus.amphi_integration.controller.AmphiController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Call to: " + joinPoint.getSignature().toShortString());
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            System.out.println("Arguments: ");
            for (Object arg : args) {
                System.out.println(" - " + arg);
            }
        }
    }
}