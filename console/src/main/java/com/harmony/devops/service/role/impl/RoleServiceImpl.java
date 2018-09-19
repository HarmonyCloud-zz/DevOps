package com.harmony.devops.service.role.impl;

import com.harmony.devops.domain.role.Role;
import com.harmony.devops.repository.AllRepositories;
import com.harmony.devops.service.role.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/17
 * @描述
 */
@Service
public class RoleServiceImpl extends AllRepositories implements RoleService{

    @Override
    public Page<Role> findBy(String roleName, Pageable pageable) {
        if(StringUtils.isNotBlank(roleName)){
            return roleRepository.findByRoleName(roleName,pageable);
        }
        return roleRepository.findAll(pageable);
    }
}
