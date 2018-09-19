package com.harmony.devops.domain.organization;

import com.harmony.devops.common.domain.BaseEntity;
import com.harmony.devops.common.vo.VO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/2
 * @描述
 */
@Entity
@Table(name = "t_depart_partner")
public class Partner extends BaseEntity implements VO.ToVO{
    /**
     * 接口调用方名称
     */
    private String partnerName;
    /**
     * partnerId(接口调用需要使用)
     */
    @Column(length = 32)
    private String partnerId;

    /**
     * partnerKey(接口调用需要使用)
     */
    @Column(length = 32)
    private String partnerKey;

    @Override
    public Vo toVO(boolean isRecursion) {
        Vo vo=new Vo();
        vo.partnerId=this.partnerId;
        vo.partnerName=this.partnerName;
        return vo;
    }

    @Data
    public static class Vo implements VO{
        private String partnerName;
        private String partnerId;
    }
}
