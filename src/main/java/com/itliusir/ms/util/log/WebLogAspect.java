package com.itliusir.ms.util.log;

import com.itliusir.ms.util.ClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * 使用AOP统一处理Web请求日志类
 *
 * @author liugang
 * @since 2017-11-15
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.itliusir.ms.rest.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 记录下请求内容
        log.info("url : " + request.getRequestURL().toString());
        log.info("http_method : " + request.getMethod());
        log.info("ip : " + ClientUtil.getClientIp(request));
        log.info("class_method : " + signature.getDeclaringTypeName() + "." + signature.getName());
        log.info("args : " + Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("response : " + ret);
        log.info("spend time : " + (System.currentTimeMillis() - startTime.get()));
        startTime.remove();
    }
}