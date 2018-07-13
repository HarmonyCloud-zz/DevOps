package com.zhengtou.cf.common.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 密码验证实现类
 * @author 葛文镇
 */
public class MsgCodeValidator implements ConstraintValidator<MsgCode, String> {

    private String phoneReg = "^\\d{6}$";
    private Pattern phonePattern = Pattern.compile(phoneReg);

    @Override
    public void initialize(MsgCode phone) {

    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return phonePattern.matcher(phone.toString()).matches();
    }
}
