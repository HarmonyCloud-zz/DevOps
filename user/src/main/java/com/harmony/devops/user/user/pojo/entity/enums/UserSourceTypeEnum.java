package com.harmony.devops.user.user.pojo.entity.enums;

public enum UserSourceTypeEnum {
        APP,WEB,其他;
        public String toPrintString() {
            return this.name();
    }
}
