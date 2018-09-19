package com.harmony.devops.domain.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@Data
@MappedSuperclass
public abstract class UserInfo extends LoginUser {
    @ManyToOne
    protected User creater; // 创建人

    protected String imageHeadPic; // 头像路径

    protected String email;//邮箱


}
