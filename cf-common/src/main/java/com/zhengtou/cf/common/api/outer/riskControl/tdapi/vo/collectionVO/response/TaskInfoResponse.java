package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.response;

import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone.TaskVO;

/**
 * 任务返回
 * @author 葛文镇
 */
public class TaskInfoResponse {
    private String code;
    private String message;
    private TaskVO data;
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

    public TaskVO getData() {
        return data;
    }

    public void setData(TaskVO data) {
        this.data = data;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }
}
