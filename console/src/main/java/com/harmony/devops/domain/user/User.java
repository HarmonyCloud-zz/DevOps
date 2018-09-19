package com.harmony.devops.domain.user;

import com.harmony.devops.common.utils.DBUtil;
import com.harmony.devops.common.vo.VO;
import com.harmony.devops.domain.organization.Department;
import com.harmony.devops.domain.organization.Organization;
import com.harmony.devops.utils.UserUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static com.harmony.devops.common.vo.VO.inCaseNull;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/3
 * @描述
 */
@Data
@Entity
@Table(name = "t_user")
public class User extends UserInfo{
    /**
     * 用户编号
     */
    @Column(unique = true)
    protected String userNo= DBUtil.getUserNo();
    /**
     * 用户姓名
     */
    @Column(length = 64)
    protected String name;
    /**
     * 联系电话
     */
    @Column(length = 32)
    protected String telePhone;
    /**
     * 关联机构，外键<br>
     */
    @ManyToOne
    protected Organization org;

    /**
     * 关联部门，外键<br>
     */
    @ManyToOne
    protected Department department;



    @Override
    public VO toVO(boolean isRecursion) {
        Vo vo=new Vo();
        vo.id=this.id;
        vo.name=this.name;
        vo.telePhone=this.telePhone;
        vo.userNo=this.userNo;
        if(isRecursion){
            vo.orgVo=(Organization.Vo)inCaseNull(this.org,false);
            vo.departVo=(Department.Vo)inCaseNull(this.department,false);
            vo.creater=(Vo) inCaseNull(this.creater,false);
        }

        return null;
    }

    @Data
    public static class Vo implements VO{
        private Long id;
        private String userNo;
        private String name;
        private String telePhone;
        private Organization.Vo orgVo;
        private Department.Vo departVo;
        private Vo creater;
    }
}
