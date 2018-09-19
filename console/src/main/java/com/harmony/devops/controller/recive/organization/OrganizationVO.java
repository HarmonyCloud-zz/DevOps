package com.harmony.devops.controller.recive.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/3
 * @描述
 */
@Data
@ApiModel(description = "机构vo")
public class OrganizationVO implements Serializable{
    /**
     * 机构名称
     */
    @ApiModelProperty(name = "orgName",value = "机构名称")
    private String orgName;
    /**
     * 联系电话
     */
    @ApiModelProperty(name = "telephone",value = "联系电话")
    private String telephone;

    /**
     * 联系人
     */
    @ApiModelProperty(name = "contactName",value = "联系人")
    private String contactName;
}
