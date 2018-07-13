package com.zhengtou.cf.common.api.outer.riskControl.tdapi.vo;

import com.zhengtou.cf.common.pojo.PeakBaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 同盾返回参数封装
 * @author 葛文镇
 */
@ApiModel(discriminator = "同盾返回参数封装")
public class TDApiResponse extends PeakBaseVo implements Serializable {
    private static final long serialVersionUID = 4152462611121573434L;
    /**
     * 是否调用成功
     */
    @ApiModelProperty(value="是否调用成功",name="success")
    private Boolean success = false;
    /**
     * 进件ID
     */
    @ApiModelProperty(value="进件ID",name="id")
    private String id;
    /**
     * 结果详情
     */
    @ApiModelProperty(value="结果详情",name="result_desc")
    private ResultDescVO result_desc;
    /**
     * 错误详情描述
     */
    @ApiModelProperty(value="错误详情描述",name="reason_desc")
    private String reason_desc;
    /**
     * 调用失败时的错误码
     */
    @ApiModelProperty(value="调用失败时的错误码",name="reason_code")
    private String reason_code;
    /**
     * 下一步
     * SUPPLEMENT:数据补充；REVIEW:决策复议；RETRY:重试；GO_ON:暂停；
     */
    @ApiModelProperty(value="下一步操作：SUPPLEMENT:数据补充；REVIEW:决策复议；RETRY:重试；GO_ON:暂停；",name="nextService")
    private String nextService;
    /**
     * 补充详情
     * 当nextService为SUPPLEMENT时返回
     */
    @ApiModelProperty(value="补充详情：当nextService为SUPPLEMENT时返回",name="supplementInfo")
    private String supplementInfo;

    public String getReason_code() {
        return reason_code;
    }

    public void setReason_code(String reason_code) {
        this.reason_code = reason_code;
    }

    public ResultDescVO getResult_desc() {
        return result_desc;
    }

    public void setResult_desc(ResultDescVO result_desc) {
        this.result_desc = result_desc;
    }

    public String getReason_desc() {
        return reason_desc;
    }

    public void setReason_desc(String reason_desc) {
        this.reason_desc = reason_desc;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNextService() {
        return nextService;
    }

    public void setNextService(String nextService) {
        this.nextService = nextService;
    }

    public String getSupplementInfo() {
        return supplementInfo;
    }

    public void setSupplementInfo(String supplementInfo) {
        this.supplementInfo = supplementInfo;
    }

    @Override
    public String toString() {
        if (success) {
            return "TDApiResponse [success=" + success + ", id=" + id
                    + ", result_desc=" + result_desc + "]";
        } else {
            return "TDApiResponse [success=" + success
                    + ", reason_code=" + reason_code + ", reason_desc=" + reason_desc + "]";
        }
    }

}
            