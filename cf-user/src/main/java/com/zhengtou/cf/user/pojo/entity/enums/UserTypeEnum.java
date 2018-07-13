package com.zhengtou.cf.user.pojo.entity.enums;

public enum UserTypeEnum {
        后台管理员,运营人员,风控人员,客服人员,法人,店员,店长,机构管理员,消费用户,静默注册消费用户;
        public String toPrintString() {
            return this.name();
    }
}
