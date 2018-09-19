package com.harmony.devops.service.role;

import com.harmony.devops.domain.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/17
 * @描述
 */
public interface RoleService {
    Page<Role> findBy(String roleName, Pageable pageable);
}
