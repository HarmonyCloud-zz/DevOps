package com.zhengtou.cf.trade.pojo.push;

public class TaskNotify {

    private String SERVICE_ID;
    private String SEND_TIME;
    private String APP_NO;
    private String CONTRA_NBR;
    private String UUID;
    private String SERVICE_SN;
    private String MSG_TYPE;
    private String STATUS;
    private Object MSG_CONTENT;
    private Object EXTRA;

    public String getSERVICE_ID() {
        return SERVICE_ID;
    }

    public void setSERVICE_ID(String SERVICE_ID) {
        this.SERVICE_ID = SERVICE_ID;
    }

    public String getSEND_TIME() {
        return SEND_TIME;
    }

    public void setSEND_TIME(String SEND_TIME) {
        this.SEND_TIME = SEND_TIME;
    }

    public String getAPP_NO() {
        return APP_NO;
    }

    public void setAPP_NO(String APP_NO) {
        this.APP_NO = APP_NO;
    }

    public String getCONTRA_NBR() {
        return CONTRA_NBR;
    }

    public void setCONTRA_NBR(String CONTRA_NBR) {
        this.CONTRA_NBR = CONTRA_NBR;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getSERVICE_SN() {
        return SERVICE_SN;
    }

    public void setSERVICE_SN(String SERVICE_SN) {
        this.SERVICE_SN = SERVICE_SN;
    }

    public String getMSG_TYPE() {
        return MSG_TYPE;
    }

    public void setMSG_TYPE(String MSG_TYPE) {
        this.MSG_TYPE = MSG_TYPE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public Object getMSG_CONTENT() {
        return MSG_CONTENT;
    }

    public void setMSG_CONTENT(Object MSG_CONTENT) {
        this.MSG_CONTENT = MSG_CONTENT;
    }

    public Object getEXTRA() {
        return EXTRA;
    }

    public void setEXTRA(Object EXTRA) {
        this.EXTRA = EXTRA;
    }
}
