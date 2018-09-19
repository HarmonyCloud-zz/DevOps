package com.harmony.devops.domain.user;

import com.harmony.devops.common.domain.BaseEntity;
import com.harmony.devops.common.vo.VO;
import com.harmony.devops.domain.resources.Resources;
import com.harmony.devops.domain.role.Role;
import com.harmony.devops.utils.UserUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import java.util.HashSet;
import java.util.Set;

@Data
@MappedSuperclass
public abstract class LoginUser extends BaseEntity implements VO.ToVO {
    @ManyToMany(fetch = FetchType.LAZY)
    protected Set<Role> roles;
    @ManyToMany(fetch = FetchType.LAZY)
    protected Set<Resources> resources;
    /**
     * 登录名
     */
    @Column(length = 65, unique = true)
    protected String userName;
    /**
     * 密码
     */
    @Column(length = 32)
    protected String passWord= UserUtil.genertePassword();
    /**
     * 上次登录时间
     */
    protected Long lastLoginTime;

    public Set<String> getStringRoles() {
        Set<String> roles = new HashSet<>();
        this.roles.forEach(role -> roles.add(role.getRoleName()));
        return roles;
    }

    public Set<String> getStringResourceCode() {
        Set<String> resources = new HashSet<>();
        this.roles.forEach(role -> role.getResources().forEach(permission -> resources.add(permission.getResourcesCode())));
        this.resources.forEach(permission -> resources.add(permission.getResourcesCode()));
        return resources;
    }
}
