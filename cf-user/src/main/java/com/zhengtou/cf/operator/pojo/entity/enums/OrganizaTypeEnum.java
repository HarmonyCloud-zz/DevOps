package com.zhengtou.cf.operator.pojo.entity.enums;

/**
 * @author 葛文镇
 * 机构类型
 */
public enum OrganizaTypeEnum {
    资金方,合作结构,郑投;
    public String toPrintString() {
        return this.name();
    }
}
