package com.harmony.devops.controller.recive.resources;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/14
 * @描述
 */
@Data
@ApiModel(discriminator = "权限vo")
public class ResourcesVO implements Serializable {

    /**
     * 资源名
     */
    @ApiModelProperty(name = "resourcesName",value = "资源名")
    private String resourcesName;
    /**
     * 资源地址
     */
    @ApiModelProperty(name = "resourcesUrl",value = "资源地址")
    private String resourcesUrl;
}
