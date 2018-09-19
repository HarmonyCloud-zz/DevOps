package com.harmony.devops.repository;

import com.harmony.devops.repository.organization.DepartmentRespository;
import com.harmony.devops.repository.organization.OrganizationRepository;
import com.harmony.devops.repository.resource.ResourcesRepository;
import com.harmony.devops.repository.role.RoleRepository;
import com.harmony.devops.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AllRepositories {
    @Autowired
    protected ResourcesRepository resourcesRepository;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected OrganizationRepository organizationRepository;
    @Autowired
    protected RoleRepository roleRepository;
    @Autowired
    protected DepartmentRespository departmentRespository;
}
