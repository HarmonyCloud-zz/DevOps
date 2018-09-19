package com.harmony.devops.service;

import com.harmony.devops.repository.AllRepositories;
import com.harmony.devops.service.captcha.CaptchaService;
import com.harmony.devops.service.resources.ResourcesService;
import com.harmony.devops.service.role.RoleService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AllServices extends AllRepositories {
    @Autowired
    protected CaptchaService captchaService;
    @Autowired
    protected ResourcesService resourcesService;
    @Autowired
    protected RoleService roleService;
}
