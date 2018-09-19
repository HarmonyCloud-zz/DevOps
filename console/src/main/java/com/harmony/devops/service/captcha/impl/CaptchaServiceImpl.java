package com.harmony.devops.service.captcha.impl;

import com.harmony.devops.common.enums.RtnResultEnum;
import com.harmony.devops.common.exception.BaseException;
import com.harmony.devops.service.captcha.CaptchaService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    private static final int LENGTH = 4;
    public static final String NAME = "captcha";

    @Override
    public void check(String captcha) throws BaseException {
        Object c = SecurityUtils.getSubject().getSession().getAttribute(NAME); // 读取
        SecurityUtils.getSubject().getSession().removeAttribute(NAME); // 移除
        if (null == c || !c.equals(captcha)) { // 较验
            throw new BaseException(RtnResultEnum.E020002);
        }
    }

    @Override
    public String generate() { // 生成
        String captcha = RandomStringUtils.randomAlphabetic(LENGTH);
        SecurityUtils.getSubject().getSession().setAttribute(NAME, captcha);
        return captcha;
    }
}
