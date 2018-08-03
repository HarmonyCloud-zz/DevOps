package com.harmony.devops.user.aop;

import com.alibaba.fastjson.JSONObject;
import com.harmony.devops.common.component.redis.MyRedisComponent;
import com.harmony.devops.common.enums.RtnResultEnum;
import com.harmony.devops.common.pojo.vo.ErrorFessionVO;
import com.harmony.devops.user.annotation.Permission;
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

/**
 * 拦截器：记录用户操作日志，检查用户是否登录……
 */
@Aspect
@Component
public class ControllerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Autowired
    MyRedisComponent myRedisComponent;
    /**
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.harmony.*.controller..*(..))")
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

        if(!methodName.contains("Png")){
            Object[] objects = pjp.getArgs();
            for(Object object:objects) {
                logger.info("obj::" + JSONObject.toJSONString(object));
                if(object!=null && object.getClass().isInstance(HttpServletRequest.class)) {
                    HttpServletRequest request = (HttpServletRequest)object;
                    String token = request.getParameter("token");
                    if (!myRedisComponent.hasKey(token)) {
                        return new ErrorFessionVO(RtnResultEnum.E000003);
                    }
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
     * 判断一个方法是否需要登录
     * @param method
     * @return
     */
    private boolean hasPermission(Method method){
        boolean result = true;
        if(method.isAnnotationPresent(Permission.class)){
            String permission = method.getAnnotation(Permission.class).value();
        }

        return result;
    }
}