package com.harmony.devops.domain.resources;

import com.harmony.devops.common.domain.BaseEntity;
import com.harmony.devops.common.vo.VO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述 权限表
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_resources")
public class Resources extends BaseEntity implements VO.ToVO {
    /**
     * 资源名
     */
    protected String resourcesName;
    /**
     * 资源码
     */
    protected String resourcesCode;
    /**
     * 资源地址
     */
    @NonNull
    protected String resourcesUrl;

    @Override
    public Vo toVO(boolean isRecursion) {
        Vo vo=new Vo();
        vo.resourcesCode=this.resourcesCode;
        vo.resourcesName=this.resourcesName;
        vo.resourcesUrl=this.resourcesUrl;
        return vo;
    }

    @Data
    public static class Vo implements VO {
        private String resourcesName;
        private String resourcesCode;
        private String resourcesUrl;
        private Integer sort;
    }
}
