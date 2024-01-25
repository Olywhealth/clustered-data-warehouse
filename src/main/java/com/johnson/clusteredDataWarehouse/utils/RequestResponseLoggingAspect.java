package com.johnson.clusteredDataWarehouse.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;

/**
 * @author Johnson on 24/12/2023
 */

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RequestResponseLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


//    @Pointcut("@annotation(com.johnson.clusteredDataWarehouse.contracts.ExtractRequestResponse)") //This is used as annotation based to target specific methods
    @Pointcut("execution(* com.johnson..*(..))") // Use this to target any method within the specified package
    public void provideValuesForLogging() {
    }


    @AfterReturning(pointcut = "provideValuesForLogging()", returning = "returnValue")
    public void logMethods(JoinPoint joinPoint, Object returnValue) {

        try {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String host = request.getHeader("Host");
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            String requestBody = null;
            String responseBody = returnValue.toString();

            if (joinPoint.getArgs().length > 0) {
                Object requestObj = joinPoint.getArgs()[0];
                ObjectMapper objectMapper = new ObjectMapper();
                requestBody = objectMapper.writeValueAsString(requestObj);
            }

            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }

            logger.info("Request URL: {}", request.getRequestURL());
            logger.info("Request Method: {}", request.getMethod());
            logger.info("Request Headers: {}", Collections.list(request.getHeaderNames()));
            logger.info("Request Body: {}", requestBody);
            logger.info("IPAddress for request {}", ipAddress);
            logger.info("Response Body: {}", responseBody);
            logger.info("Host is : {}", host);
            logger.info("Method name {}", joinPoint.getSignature().getName());


        } catch (Exception e) {
            logger.info("AN EXCEPTION OCCURRED DUE TO :::: {0} ", e);
        }
    }

    @AfterThrowing(pointcut = "provideValuesForLogging()", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Throwable ex) {

        try {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

            String host = request.getHeader("Host");
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            String requestBody = null;
            String responseBody = ex.getLocalizedMessage() + "||" + ex.getMessage() + "||" + ex.getCause();

            if (joinPoint.getArgs().length > 0) {
                Object requestObj = joinPoint.getArgs()[0];
                ObjectMapper objectMapper = new ObjectMapper();
                requestBody = objectMapper.writeValueAsString(requestObj);
            }
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }

            logger.info("Request URL: {}", request.getRequestURL());
            logger.info("Request Method: {}", request.getMethod());
            logger.info("Request Headers: {}", Collections.list(request.getHeaderNames()));
            logger.info("Request Body: {}", requestBody);
            logger.info("IPAddress for request {}", ipAddress);
            logger.info("Response Body: {}", responseBody);
            logger.info("Host is : {}", host);
            logger.info("Method name {}", joinPoint.getSignature().getName());

        } catch (Exception e) {
            int lineNumber = e.getStackTrace()[0].getLineNumber();
            logger.info("Line number {}", lineNumber);
            logger.info("AN EXCEPTION OCCURRED DUE TO :::: {0}", e);
        }

    }

}
