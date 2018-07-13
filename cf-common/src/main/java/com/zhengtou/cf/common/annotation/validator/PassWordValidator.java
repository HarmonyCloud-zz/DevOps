package com.zhengtou.cf.common.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 密码验证实现类
 * @author 葛文镇
 */
public class PassWordValidator implements ConstraintValidator<PassWord, String> {

    private String phoneReg = "^(?!\\d+$)(?![A-Za-z]+$)[a-zA-Z0-9]{6,12}$";
    private Pattern phonePattern = Pattern.compile(phoneReg);

    @Override
    public void initialize(PassWord phone) {

    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return phonePattern.matcher(phone.toString()).matches();
    }
}
