package com.harmony.devops.common.annotation.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 真是姓名认证
 * @author 葛文镇
 */
@Target({ElementType.FIELD,ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=NameValidator.class)
public @interface Name {
    String message() default"姓名格式错误";

    boolean canNull() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
