package com.harmony.devops.common.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 手机号验证实现类
 * @author 葛文镇
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private String phoneReg = "^(13|14|15|17|18)[0-9]{9}$";
    private Pattern phonePattern = Pattern.compile(phoneReg);

    @Override
    public void initialize(Phone phone) {

    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return phonePattern.matcher(phone.toString()).matches();
    }
}
