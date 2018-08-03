package com.harmony.devops.common.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 真是姓名验证实现类
 * @author 葛文镇
 */
public class NameValidator implements ConstraintValidator<Name, String> {

    private String phoneReg = "^[\\u4E00-\\u9FA5A-Za-z]+$";
    private Pattern phonePattern = Pattern.compile(phoneReg);

    @Override
    public void initialize(Name phone) {
        if(phone.canNull()){
            phoneReg = "^[\\u4E00-\\u9FA5A-Za-z].$";
            phonePattern=Pattern.compile(phoneReg);
        }
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return phonePattern.matcher(phone.toString()).matches();
    }
}
