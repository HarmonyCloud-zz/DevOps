package com.harmony.devops.repository.organization;

import com.harmony.devops.domain.organization.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/14
 * @描述
 */
public interface DepartmentRespository extends JpaRepository<Department, Long> {
}
