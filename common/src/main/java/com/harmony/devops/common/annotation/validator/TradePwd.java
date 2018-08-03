package com.harmony.devops.common.annotation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 交易密码认证
 * @author 葛文镇
 */
@Target({ElementType.FIELD,ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=TradePwdValidator.class)
public @interface TradePwd {
    String message() default"交易密码格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
