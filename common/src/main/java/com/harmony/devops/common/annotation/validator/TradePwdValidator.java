package com.harmony.devops.common.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 交易密码验证实现类
 * @author 葛文镇
 */
public class TradePwdValidator implements ConstraintValidator<TradePwd, String> {

    private String phoneReg = "^\\d{6}$";
    private Pattern phonePattern = Pattern.compile(phoneReg);

    @Override
    public void initialize(TradePwd phone) {

    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return phonePattern.matcher(phone.toString()).matches();
    }
}
