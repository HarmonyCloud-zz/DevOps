package com.zhengtou.cf.risk.controller;

import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiRequest;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.TDApiResponse;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.response.BussinessTaskResponse;
import com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo.collectionVO.response.PhoneTaskResponse;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.ResponseVO;
import com.zhengtou.cf.risk.riskApi.tdapi.TdApi;
import com.zhengtou.cf.risk.riskApi.tdapi.TdTypeEnum;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("risk")
@Api(description = "风控服务")
public class RiskController {
    @Autowired
    TdApi tdApi;

    @RequestMapping(value = "getTdApproval", method = RequestMethod.POST)
    @ApiOperation("获取同盾审批结果")
    @ApiResponses({@ApiResponse(code = 200,response = TDApiResponse.class,message = "获取同盾审批结果返回")})
    public NetVO getTdApproval(@RequestParam @ApiParam(value = "身份证号", name = "id_number") String id_number, @RequestParam @ApiParam(value =
            "手机号", name = "account_mobile") String account_mobile, @RequestParam @ApiParam(value = "真实姓名", name = "account_name") String
            account_name, @RequestParam @ApiParam(value = "风控类型：web,ios,android;", name = "tdTypeEnum") TdTypeEnum tdTypeEnum) {
        TDApiRequest apiRequest = new TDApiRequest(id_number, account_mobile, account_name);
        TDApiResponse tdApiResponse = tdApi.tdApi(tdTypeEnum, apiRequest);
        if (tdApiResponse.getSuccess()) {
            return new ResponseVO(tdApiResponse);
        } else {
            return new ErrorFessionVO(tdApiResponse.getReason_code(), tdApiResponse.getReason_desc());
        }
    }

    @RequestMapping(value = "getTdPhone", method = RequestMethod.POST)
    @ApiOperation("获取同盾运营商结果")
    public NetVO getTdPhone(@RequestParam String taskId) {
        PhoneTaskResponse phoneTaskResponse = tdApi.collectionPhoneTask(taskId);
        return new ResponseVO(phoneTaskResponse.getCode(), phoneTaskResponse.getMessage(), phoneTaskResponse.getData());
    }

    @RequestMapping(value = "getTdBussiness", method = RequestMethod.POST)
    @ApiOperation("获取同盾电商结果")
    public NetVO getTdBussiness(@RequestParam String taskId) {
        BussinessTaskResponse bussinessTaskResponse = tdApi.collectionBussinessTask(taskId);
        return new ResponseVO(bussinessTaskResponse.getCode(), bussinessTaskResponse.getMessage(), bussinessTaskResponse.getData());
    }
}
