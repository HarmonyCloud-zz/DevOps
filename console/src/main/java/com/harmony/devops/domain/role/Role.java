package com.harmony.devops.domain.role;

import com.harmony.devops.common.domain.BaseEntity;
import com.harmony.devops.common.vo.VO;
import com.harmony.devops.domain.resources.Resources;
import com.harmony.devops.utils.ResourcesUtil;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述 角色表
 */
@Data
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity implements VO.ToVO{
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色编码
     */
    private String roleCode= ResourcesUtil.getRoleCode();

    @ManyToMany(fetch=FetchType.LAZY)
    private Set<Resources> resources;


    @Override
    public VO toVO(boolean isRecursion) {
        Vo vo=new Vo();
        vo.roleCode=this.roleCode;
        vo.roleName=this.roleName;
        this.resources.forEach(permission->vo.permissionVos.add(permission.toVO(false)));
        return null;
    }

    @Data
    public static class Vo{
        private String roleName;
        private String roleCode;
        private Set<Resources.Vo> permissionVos;
    }
}
