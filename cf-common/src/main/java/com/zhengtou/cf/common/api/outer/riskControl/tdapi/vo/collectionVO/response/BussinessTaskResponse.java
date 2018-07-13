package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.response;


import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.bussiness.BussinessTaskDataVO;

/**
 * 获取任务结果(电商)
 * @author 葛文镇
 */
public class BussinessTaskResponse {
    private String code;
    private String message;
    private BussinessTaskDataVO data;
    private String task_id;

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

    public BussinessTaskDataVO getData() {
        return data;
    }

    public void setData(BussinessTaskDataVO data) {
        this.data = data;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }
}
