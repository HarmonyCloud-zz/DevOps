package com.harmony.devops.repository.role;

import com.harmony.devops.domain.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/14
 * @描述
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Page<Role> findByRoleName(String roleName, Pageable pageable);
}
