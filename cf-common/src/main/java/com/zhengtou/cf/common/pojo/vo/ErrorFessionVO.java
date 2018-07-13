package com.zhengtou.cf.common.pojo.vo;

import com.zhengtou.cf.common.enums.ReturnMsgEnum;
import com.zhengtou.cf.common.enums.RtnResultEnum;

/**
 * 业务层VO,无实体
 * @author 葛文镇
 */
public class ErrorFessionVO extends NetVO {
    private String status;
    private String desc;

    public ErrorFessionVO() {
        super(ReturnMsgEnum.ERROR);
    }

    public ErrorFessionVO(String status, String desc) {
        super(ReturnMsgEnum.ERROR);
        this.status = status;
        this.desc = desc;
    }

    public ErrorFessionVO(RtnResultEnum rtnResultEnum) {
        super(ReturnMsgEnum.ERROR);
        this.status = rtnResultEnum.name();
        this.desc = rtnResultEnum.getMsg();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
