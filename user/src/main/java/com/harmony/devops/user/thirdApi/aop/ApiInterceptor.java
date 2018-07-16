package com.harmony.devops.user.thirdApi.aop;

import com.alibaba.fastjson.JSONObject;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.harmony.devops.user.thirdApi.util.CommonUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * api访问日志
 */
@Aspect
@Component
public class ApiInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);

    @Autowired
    MyRedisComponent myRedisComponent;
    /**
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.harmony.devops.thirdplatform.api.app..*(..))")
    public void apiMethodPointcut(){}

    /**
     * 拦截器具体实现
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("apiMethodPointcut()")
    public Object Interceptor(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名

        if(!methodName.contains("Png")){
            Object[] objects = pjp.getArgs();
            for(Object object:objects) {
                logger.info("obj::" + JSONObject.toJSONString(object));
                if(object!=null && object.getClass().isInstance(HttpServletRequest.class)) {
                    HttpServletRequest request = (HttpServletRequest)object;
                }
            }
        }
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

    /**
     * 判断加密方式是否正确
     */
    private boolean isSignError(String sign, Map<String, String[]> params, String signKey) {
        Map<String, Object> args = new HashMap<String, Object>();
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            if (value != null && value.length > 0) {
                args.put(key, value[0]);
            }
        }
        return StringUtils.equalsIgnoreCase(sign, CommonUtil.generateSign(signKey, args));
    }
}