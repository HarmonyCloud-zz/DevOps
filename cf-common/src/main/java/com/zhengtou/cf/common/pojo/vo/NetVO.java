package com.zhengtou.cf.common.pojo.vo;

import com.zhengtou.cf.common.enums.ReturnMsgEnum;
import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * 网络通信层vo
 * @author 葛文镇
 */
public abstract class NetVO extends PeakBaseVo{
    private String code;
    private String message;

    public NetVO(ReturnMsgEnum returnMsgEnum) {
        this.code = returnMsgEnum.getCode();
        this.message = returnMsgEnum.getMsg();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
