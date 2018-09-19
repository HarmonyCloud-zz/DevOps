package com.harmony.devops.service.captcha;

import com.harmony.devops.common.exception.BaseException;

public interface CaptchaService {
    void check(String captcha) throws BaseException;

    String generate();
}
