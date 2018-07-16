
package com.harmony.devops.common.exception;


import com.harmony.devops.common.enums.RtnStatusEnum;

public class BaseRunTimeException extends RuntimeException {

    public BaseRunTimeException() {
        super();
    }

    public BaseRunTimeException(RtnStatusEnum enumRtnStatus, String errorCode, String errorMsg) {
        super("处理状态:" + enumRtnStatus + ",错误码:" + errorCode + "错误信息:" + errorMsg);
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
}