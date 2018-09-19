package com.harmony.devops.repository.organization;

import com.harmony.devops.domain.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/13
 * @描述
 */
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Organization findByOrgNo(String orgNo);
}
