package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.response;

import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.vo.phone.TaskDataVO;
import com.zhengtou.cf.common.pojo.PeakBaseVo;

/**
 * 获取任务结果
 * @author 葛文镇
 */
public class PhoneTaskResponse extends PeakBaseVo{
    private String code;
    private String message;
    private TaskDataVO data;
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

    public TaskDataVO getData() {
        return data;
    }

    public void setData(TaskDataVO data) {
        this.data = data;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }
}
