package com.harmony.devops.domain.server;

import com.harmony.devops.common.domain.BaseEntity;
import com.harmony.devops.common.vo.VO;
import com.harmony.devops.domain.organization.Department;
import com.harmony.devops.domain.organization.Organization;
import lombok.Data;

import javax.persistence.*;

import static com.harmony.devops.common.vo.VO.inCaseNull;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/17
 * @描述
 */
@Entity
@Table(name = "t_server")
public class Server extends BaseEntity implements VO.ToVO{
    @ManyToOne
    protected Organization org;
    @ManyToOne
    protected Department department;

    @Enumerated(EnumType.ORDINAL)
    protected ServerType serverType;

    protected String ip;//地址

    protected String name;//名称

    protected String des;//描述

    @Override
    public VO toVO(boolean isRecursion) {
        Vo vo=new Vo();
        vo.ip=this.ip;
        vo.name=this.name;
        vo.des=this.des;
        vo.serverType=this.serverType;
        if(isRecursion){
            vo.org=(Organization.Vo)inCaseNull(this.org,false);
            vo.department=(Department.Vo)inCaseNull(this.department,false);
        }
        return null;
    }

    @Data
    public static class Vo implements VO{
        private String ip;
        private String name;
        private String des;
        private ServerType serverType;
        private Organization.Vo org;
        private Department.Vo department;
    }

    public enum ServerType{
        All,git,jenkins;
    }
}
