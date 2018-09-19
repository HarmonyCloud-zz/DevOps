package com.harmony.devops.domain.organization;

import com.harmony.devops.common.domain.BaseEntity;
import com.harmony.devops.common.utils.DBUtil;
import com.harmony.devops.common.vo.VO;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/2
 * @描述 机构实体类
 */
@Entity
@Table(name = "t_org")
public class Organization extends BaseEntity implements VO.ToVO{
    /**
     * 机构编号
     */
    protected String orgNo= DBUtil.getOrgNo();
    /**
     * 机构名称
     */
    protected String orgName;
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
     * 简称
     */
    @Column(unique = true)
    protected String abbreviation;

    @ManyToMany(fetch = FetchType.LAZY)
    protected Set<Partner> partners;

    @Override
    public VO toVO(boolean isRecursion) {
        Vo vo=new Vo();
        vo.abbreviation=this.abbreviation;
        vo.orgName=this.orgName;
        vo.orgNo=this.orgNo;
        vo.telephone=this.telephone;
        vo.contactName=this.contactName;
        return vo;
    }

    @Data
    public static class Vo implements VO{
        private String orgNo;
        private String orgName;
        private String telephone;
        private String contactName;
        private String abbreviation;
    }

    public Set<Partner.Vo> getPartners() {
        Set<Partner.Vo> partnervos = new HashSet<>();
        this.partners.forEach(partner -> partnervos.add(partner.toVO(false)));
        return partnervos;
    }
}
