package com.harmony.devops.domain.organization;

import com.harmony.devops.common.domain.BaseEntity;
import com.harmony.devops.common.vo.VO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/2
 * @描述 部门
 */
@Entity
@Table(name = "t_depart")
public class Department extends BaseEntity implements VO.ToVO{
    /**
     * 关联机构
     */
    @ManyToOne
    protected Organization org;

    /**
     * 部门名称
     */
    protected String name;
    /**
     * 联系电话
     */
    @Column(length = 32)
    protected String telephone;

    /**
     * 联系人
     */
    @Column(length = 32)
    protected String contactName;
    /**
     * 描述
     */
    protected String description;
    /**
     * 状态
     */
    @Enumerated(EnumType.ORDINAL)
    protected DepartmentStatus status;

    /**
     * 关联Partner，可以为空
     */
    @ManyToMany(fetch = FetchType.LAZY)
    protected Set<Partner> partners;

    @Override
    public VO toVO(boolean isRecursion) {
        Vo vo=new Vo();
        vo.orgVo=this.org.toVO(false);
        vo.contactName=this.contactName;
        vo.description=this.description;
        vo.name=this.name;
        vo.status=this.status;
        vo.telephone=this.telephone;
        return vo;
    }

    public enum DepartmentStatus {
        enable, discard;
    };

    public static class Vo implements VO{
        private VO orgVo;
        private String name;
        private String telephone;
        private String contactName;
        private String description;
        private DepartmentStatus status;
    }

    public Set<Partner.Vo> getPartners() {
        Set<Partner.Vo> partnervos = new HashSet<>();
        this.partners.forEach(partner -> partnervos.add(partner.toVO(false)));
        return partnervos;
    }

}
