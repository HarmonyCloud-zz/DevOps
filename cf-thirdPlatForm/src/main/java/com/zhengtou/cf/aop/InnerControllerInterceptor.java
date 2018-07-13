package com.zhengtou.cf.aop;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 拦截器：记录用户操作日志，检查用户是否登录……
 */
@Aspect
@Component
public class InnerControllerInterceptor {
    private static final Log logger =  LogFactory.getLog(InnerControllerInterceptor.class);

    /**
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.zhengtou.cf.inner..*..controller..*(..))")
    public void controllerMethodPointcut() {
    }

    /**
     * 拦截器具体实现
     *
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名

        Object[] objects = pjp.getArgs();
        for (Object object : objects) {
            logger.info("obj::" + JSONObject.toJSONString(object));
        }
        logger.info("请求开始，方法："+ methodName);
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }

        long costMs = System.currentTimeMillis() - beginTime;
        logger.info(methodName+"请求结束，耗时："+costMs+"ms");
        logger.info("obj::" + JSONObject.toJSONString(result));
        return result;
    }

}