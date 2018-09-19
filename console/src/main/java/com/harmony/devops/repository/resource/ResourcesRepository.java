package com.harmony.devops.repository.resource;

import com.harmony.devops.domain.resources.Resources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/13
 * @描述
 */
public interface ResourcesRepository extends JpaRepository<Resources, Long> {

    Page<Resources> findByResourcesName(String resourcesName,Pageable pageable);

    Page<Resources> findByResourcesUrl(String resourcesUrl,Pageable pageable);

    Page<Resources> findByResourcesNameAndResourcesUrl(String resourcesName,String resourcesUrl,Pageable pageable);
}
