package com.zhengtou.cf.risk.riskApi.tdapi;

import com.alibaba.fastjson.JSON;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiRequest;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiResponse;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.request.TaskInfoRequest;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.response.BussinessTaskResponse;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.response.PhoneTaskResponse;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.response.TaskInfoResponse;
import com.zhengtou.cf.config.Conf;
import com.zhengtou.cf.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 同盾接口实现类
 *
 * @author 葛文镇
 */
@Service
public class TdApi {
    @Autowired
    Conf conf;

    //贷前审核
    public TDApiResponse tdApi(TdTypeEnum tdTypeEnum,TDApiRequest bodyGuardApiRequest) {
        String url=conf.getTdConf().getUrl();
        if(tdTypeEnum.equals(TdTypeEnum.ios)){
            url=conf.getTdIosConf().getUrl();
        }
        if(tdTypeEnum.equals(TdTypeEnum.android)){
            url=conf.getTdAndConf().getUrl();
        }
        TDApiResponse bodyGuardApiResponse = JSON.parseObject(HttpUtils.tdPost(url, bodyGuardApiRequest.toString()),
                TDApiResponse.class);
        return bodyGuardApiResponse;
    }

    /***************************************授权爬取****************************************/
    //创建任务
    public TaskInfoResponse createTask(TaskInfoRequest taskInfoRequest) {
        TaskInfoResponse taskInfoResponse = JSON.parseObject(HttpUtils.tdPost(conf.getTdConf().getCrawUrl(), JSON.toJSONString(taskInfoRequest)),
                TaskInfoResponse.class);
        return taskInfoResponse;
    }

    //登陆验证


    /******************************************获取任务结果*****************运营商***************************/
    //获取任务状态


    //获取运营商任务结果
    public PhoneTaskResponse collectionPhoneTask(String taskId) {
        PhoneTaskResponse phoneTaskResponse = JSON.parseObject(HttpUtils.tdPost(conf.getTdConf().getCrawUrl(), "{\"task_id\":\"" + taskId + "\"}"),
                PhoneTaskResponse.class);
        return phoneTaskResponse;
    }

    /******************************************获取任务结果*****************电商***************************/
    //获取任务状态


    //获取电商任务结果
    public BussinessTaskResponse collectionBussinessTask(String taskId) {
        BussinessTaskResponse bussinessTaskResponse = JSON.parseObject(HttpUtils.tdPost(conf.getTdConf().getCrawUrl(), "{\"task_id\":\"" + taskId +
                "\"}"), BussinessTaskResponse.class);
        return bussinessTaskResponse;
    }
}