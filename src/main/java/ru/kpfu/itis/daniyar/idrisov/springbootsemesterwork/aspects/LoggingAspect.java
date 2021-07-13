package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(PublicLog)")
    public void publicMethode() {
    }

    @Pointcut("@annotation(ErrorLog)")
    public void error() {
    }

    @Before("publicMethode()")
    public void publicLogging(JoinPoint joinPoint) {
        Date date = new Date();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String method = joinPoint.getSignature().getName() + "()";
        LOG.info("{Username = '" + username + "', Method: '" + method + "', Date: '" + date + "'}");
    }

    @After("error()")
    public void errorLogging(JoinPoint joinPoint) {
        Date date = new Date();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String method = joinPoint.getSignature().getName() + "()";
        LOG.error("{Username = '" + username + "', Method: '" + method + "', Date: '" + date + "'}");
    }

}
