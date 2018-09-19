package com.harmony.devops.context.aop;

import com.alibaba.fastjson.JSONObject;
import com.harmony.devops.common.component.redis.MyRedisComponent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 拦截器：记录用户操作日志，检查用户是否登录……
 */
@Aspect
@Component(value = "访问日志服务")
public class ControllerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Autowired
    MyRedisComponent myRedisComponent;

    /**
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.harmony.devops.domain.*.controller..*(..))")
    public void controllerMethodPointcut(){}

    /**
     * 拦截器具体实现
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名

        logger.info("请求开始，方法：{}", methodName);
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }

        long costMs = System.currentTimeMillis() - beginTime;
        logger.info("{}请求结束，耗时：{}ms", methodName, costMs);
        logger.info("obj::" + JSONObject.toJSONString(result));
        return result;
    }
}