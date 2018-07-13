package com.zhengtou.cf.api.pay.yibaoapi.controller;

import com.zhengtou.cf.api.pay.yibaoapi.YiBaoApi;
import com.zhengtou.cf.common.api.outer.pay.yibao.enums.AdviceSmsTypeEnum;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.NoSmsPayRequestVO;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.SmsBindCardRequestVO;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.SmsPayRequestVO;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.WithDrawRequestVO;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.request.*;
import com.zhengtou.cf.common.api.outer.pay.yibao.vo.tzt.response.*;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.ResponseVO;
import com.zhengtou.cf.util.StringUtils;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pay/yibao")
@Api(description = "易宝支付服务")
public class YiBaoController {
    @Autowired
    YiBaoApi yiBaoApi;

    //有短验绑卡申请
    @RequestMapping(value = "smsBindCard", method = RequestMethod.POST)
    @ApiOperation("绑卡发短验")
    @ApiResponses({@ApiResponse(code = 200, response = SmsBindCardResponse.class, message = "有短验绑卡申请返回")})
    public NetVO smsBindCard(@RequestBody SmsBindCardRequestVO smsBindCardRequestVO) throws Exception {
        SmsBindCardRequest smsBindCardRequest = new SmsBindCardRequest();
        BeanUtils.copyProperties(smsBindCardRequestVO, smsBindCardRequest);
        SmsBindCardResponse smsBindCardResponse = yiBaoApi.smsBindCard(smsBindCardRequest);
        if (StringUtils.isNotBlank(smsBindCardResponse.getErrorcode())) {
            return new ErrorFessionVO(smsBindCardResponse.getErrorcode(), smsBindCardResponse.getErrormsg());
        }
        return new ResponseVO(smsBindCardResponse);
    }

    //有短验确认
    @RequestMapping(value = "smsBindCardCheck", method = RequestMethod.POST)
    @ApiOperation("绑卡短验确认")
    @ApiResponses({@ApiResponse(code = 200, response = SmsBindCardCheckResponse.class, message = "绑卡短验确认返回")})
    public NetVO smsBindCardCheck(@RequestParam @ApiParam(value = "交易流水号", name = "requestno") String requestno, @RequestParam @ApiParam(value =
            "验证码", name = "validatecode") String validatecode) throws Exception {
        SmsBindCardCheckRequest smsBindCardCheckRequest = new SmsBindCardCheckRequest(requestno, validatecode);
        SmsBindCardCheckResponse smsBindCardCheckResponse = yiBaoApi.smsBindCardCheck(smsBindCardCheckRequest);
        if (StringUtils.isNotBlank(smsBindCardCheckResponse.getErrorcode())) {
            return new ErrorFessionVO(smsBindCardCheckResponse.getErrorcode(), smsBindCardCheckResponse.getErrormsg());
        }
        return new ResponseVO(smsBindCardCheckResponse);
    }

    //有短验绑卡申请 重发
    @RequestMapping(value = "reSmsBindCard", method = RequestMethod.POST)
    @ApiOperation("绑卡短验重发")
    @ApiResponses({@ApiResponse(code = 200, response = ReSmsBindCardResponse.class, message = "绑卡短验重发返回")})
    public NetVO reSmsBindCard(@RequestParam @ApiParam(value = "交易流水号", name = "requestno") String requestno, @RequestParam @ApiParam(value =
            "短验发送方式", name = "adviceSmsTypeEnum") AdviceSmsTypeEnum adviceSmsTypeEnum) throws Exception {
        ReSmsBindCardRequest reSmsBindCardRequest = new ReSmsBindCardRequest(requestno, adviceSmsTypeEnum);
        ReSmsBindCardResponse reSmsBindCardResponse = yiBaoApi.reSmsBindCard(reSmsBindCardRequest);
        if (StringUtils.isNotBlank(reSmsBindCardResponse.getErrorcode())) {
            return new ErrorFessionVO(reSmsBindCardResponse.getErrorcode(), reSmsBindCardResponse.getErrormsg());
        }
        return new ResponseVO(reSmsBindCardResponse);
    }

    //无短验充值
    @RequestMapping(value = "noSmsPay", method = RequestMethod.POST)
    @ApiOperation("无短验充值")
    @ApiResponses({@ApiResponse(code = 200, response = NoSmsPayResponse.class, message = "无短验充值返回")})
    public NetVO noSmsPay(@RequestBody NoSmsPayRequestVO noSmsPayRequestVO) throws IllegalAccessException {
        NoSmsPayRequest noSmsPayRequest = new NoSmsPayRequest();
        BeanUtils.copyProperties(noSmsPayRequestVO, noSmsPayRequest);
        NoSmsPayResponse smsPayResponse = yiBaoApi.noSmsPay(noSmsPayRequest);
        if (StringUtils.isNotBlank(smsPayResponse.getErrorcode())) {
            return new ErrorFessionVO(smsPayResponse.getErrorcode(), smsPayResponse.getErrormsg());
        }
        return new ResponseVO(smsPayResponse);
    }

    //有短验充值 请求
    @RequestMapping(value = "smsPay", method = RequestMethod.POST)
    @ApiOperation("有短验充值")
    @ApiResponses({@ApiResponse(code = 200, response = SmsPayResponse.class, message = "有短验充值返回")})
    public NetVO smsPay(@RequestBody SmsPayRequestVO smsPayRequestVO) throws Exception {
        SmsPayRequest smsPayRequest = new SmsPayRequest();
        BeanUtils.copyProperties(smsPayRequestVO, smsPayRequest);
        SmsPayResponse smsPayResponse = yiBaoApi.smsPay(smsPayRequest);
        if (StringUtils.isNotBlank(smsPayResponse.getErrorcode())) {
            return new ErrorFessionVO(smsPayResponse.getErrorcode(), smsPayResponse.getErrormsg());
        }
        return new ResponseVO(smsPayResponse);
    }

    //有短验充值确认
    @RequestMapping(value = "smsPayCheck", method = RequestMethod.POST)
    @ApiOperation("有短验充值确认")
    @ApiResponses({@ApiResponse(code = 200, response = SmsPayCheckResponse.class, message = "有短验充值确认返回")})
    public NetVO smsPayCheck(@RequestParam @ApiParam(value = "交易流水号", name = "requestno") String requestno, @RequestParam @ApiParam(value =
            "短信验证码", name = "validatecode") String validatecode) throws Exception {
        SmsPayCheckRequest smsPayCheckRequest = new SmsPayCheckRequest(requestno, validatecode);
        SmsPayCheckResponse smsPayCheckResponse = yiBaoApi.smsPayCheck(smsPayCheckRequest);
        if (StringUtils.isNotBlank(smsPayCheckResponse.getErrorcode())) {
            return new ErrorFessionVO(smsPayCheckResponse.getErrorcode(), smsPayCheckResponse.getErrormsg());
        }
        return new ResponseVO(smsPayCheckResponse);
    }

    //有短验充值确认 重发
    @RequestMapping(value = "reSmsPayCheck", method = RequestMethod.POST)
    @ApiOperation("有短验充值重发短验")
    @ApiResponses({@ApiResponse(code = 200, response = SmsPayCheckResponse.class, message = "有短验充值重发短验返回")})
    public NetVO reSmsPayCheck(@RequestParam @ApiParam(value = "交易流水号", name = "requestno") String requestno, @RequestParam @ApiParam(value =
            "短验发送方式", name = "adviceSmsType") AdviceSmsTypeEnum adviceSmsType) throws Exception {
        ReSmsBindCardRequest reSmsBindCardRequest = new ReSmsBindCardRequest(requestno, adviceSmsType);
        SmsPayCheckResponse smsPayCheckResponse = yiBaoApi.reSmsPay(reSmsBindCardRequest);
        if (StringUtils.isNotBlank(smsPayCheckResponse.getErrorcode())) {
            return new ErrorFessionVO(smsPayCheckResponse.getErrorcode(), smsPayCheckResponse.getErrormsg());
        }
        return new ResponseVO(smsPayCheckResponse);
    }

    //体现请求
    @RequestMapping(value = "withDraw", method = RequestMethod.POST)
    @ApiOperation("提现")
    @ApiResponses({@ApiResponse(code = 200, response = WithDrawResponse.class, message = "提现返回")})
    public NetVO withDraw(@RequestBody WithDrawRequestVO withDrawRequestVO) throws IllegalAccessException {
        WithDrawRequest withDrawRequest = new WithDrawRequest();
        BeanUtils.copyProperties(withDrawRequestVO, withDrawRequest);
        WithDrawResponse withDrawResponse = yiBaoApi.withDraw(withDrawRequest);
        if (StringUtils.isNotBlank(withDrawResponse.getErrorcode())) {
            return new ErrorFessionVO(withDrawResponse.getErrorcode(), withDrawResponse.getErrormsg());
        }
        return new ResponseVO(withDrawResponse);
    }

    /*******************************查询类接口*************************************/
    //绑卡记录查询
    @RequestMapping(value = "queryBindCardStatus", method = RequestMethod.POST)
    @ApiOperation("查询绑卡结果")
    @ApiResponses({@ApiResponse(code = 200, response = QueryBindCardResponse.class, message = "查询绑卡结果返回")})
    public NetVO queryBindCardStatus(@RequestParam(required = false) @ApiParam(value = "交易流水号", name = "requestno") String requestno, @RequestParam
            (required = false) @ApiParam(value = "易宝流水号", name = "yborderid") String yborderid) {
        QueryBindCardResponse queryBindCardResponse = yiBaoApi.queryBindCardStatus(requestno, yborderid);
        if (StringUtils.isNotBlank(queryBindCardResponse.getErrorcode())) {
            return new ErrorFessionVO(queryBindCardResponse.getErrorcode(), queryBindCardResponse.getErrormsg());
        }
        return new ResponseVO(queryBindCardResponse);
    }

    //绑卡充值记录查询
    @RequestMapping(value = "queryBindPayRecord", method = RequestMethod.POST)
    @ApiOperation("查询充值结果")
    @ApiResponses({@ApiResponse(code = 200, response = QueryPayResponse.class, message = "查询充值结果返回")})
    public NetVO queryBindPayRecord(@RequestParam(required = false) @ApiParam(value = "交易流水号", name = "requestno") String requestno, @RequestParam
            (required = false) @ApiParam(value = "易宝流水号", name = "yborderid") String yborderid) {
        QueryPayResponse queryPayResponse = yiBaoApi.queryBindPayRecord(requestno, yborderid);
        if (StringUtils.isNotBlank(queryPayResponse.getErrorcode())) {
            return new ErrorFessionVO(queryPayResponse.getErrorcode(), queryPayResponse.getErrormsg());
        }
        return new ResponseVO(queryPayResponse);
    }

    //体现记录查询
    @RequestMapping(value = "queryDrawRecord", method = RequestMethod.POST)
    @ApiOperation("查询提现结果")
    @ApiResponses({@ApiResponse(code = 200, response = QueryWithDrawResponse.class, message = "查询提现结果返回")})
    public NetVO queryDrawRecord(@RequestParam(required = false) @ApiParam(value = "交易流水号", name = "requestno") String requestno, @RequestParam
            (required = false) @ApiParam(value = "易宝流水号", name = "yborderid") String yborderid) {
        QueryWithDrawResponse queryWithDrawResponse = yiBaoApi.queryDrawRecord(requestno, yborderid);
        if (StringUtils.isNotBlank(queryWithDrawResponse.getErrorcode())) {
            return new ErrorFessionVO(queryWithDrawResponse.getErrorcode(), queryWithDrawResponse.getErrormsg());
        }
        return new ResponseVO(queryWithDrawResponse);
    }

    //可提现余额查询
    @RequestMapping(value = "drawValidAmount", method = RequestMethod.POST)
    @ApiOperation("查询账户余额")
    @ApiResponses({@ApiResponse(code = 200, response = QueryDrawValidAmountResponse.class, message = "查询账户余额返回")})
    public NetVO drawValidAmount() {
        QueryDrawValidAmountResponse queryDrawValidAmountResponse = yiBaoApi.drawValidAmount();
        if (StringUtils.isNotBlank(queryDrawValidAmountResponse.getErrorcode())) {
            return new ErrorFessionVO(queryDrawValidAmountResponse.getErrorcode(), queryDrawValidAmountResponse.getErrormsg());
        }
        return new ResponseVO(queryDrawValidAmountResponse);
    }
}
