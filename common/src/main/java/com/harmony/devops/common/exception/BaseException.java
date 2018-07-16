
package com.harmony.devops.common.exception;


import com.harmony.devops.common.enums.RtnResultEnum;
import com.harmony.devops.common.enums.RtnStatusEnum;

public class BaseException extends Exception {

    public BaseException(RtnStatusEnum enumRtnStatus, String errorCode, String errorMsg) {
        this.rtnStatus=enumRtnStatus;
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }
    //成功得exception
    public BaseException(RtnResultEnum rtnResultEnum) {
        this.rtnStatus=rtnResultEnum.getStatus();
        this.errorCode=rtnResultEnum.name();
        this.errorMsg=rtnResultEnum.getMsg();
    }

    //三方异常exception
    public BaseException(String errorCode,String errorMsg) {
        this.rtnStatus=RtnStatusEnum.SUCCESS;
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }

    /**
     * 错误码
     */
    private String  errorCode;

    /**
     * 错误消息
     */
    private String  errorMsg;

    /**
     * 交易状态
     */
    private RtnStatusEnum rtnStatus;

    /**
     * Getter method for property errorCode.
     *
     * @return property value of errorCode
     **/
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter method for property errorCode.
     *
     * @param errorCode value to be assigned to property errorCode
     **/
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter method for property errorMsg.
     *
     * @return property value of errorMsg
     **/
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Setter method for property errorMsg.
     *
     * @param errorMsg value to be assigned to property errorMsg
     **/
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Getter method for property rtnStatus.
     *
     * @return property value of rtnStatus
     **/
    public RtnStatusEnum getRtnStatus() {
        return rtnStatus;
    }

    /**
     * Setter method for property rtnStatus.
     *
     * @param rtnStatus value to be assigned to property rtnStatus
     **/
    public void setRtnStatus(RtnStatusEnum rtnStatus) {
        this.rtnStatus = rtnStatus;
    }

    // 重写此方法，加快Exception的生产速度
    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}