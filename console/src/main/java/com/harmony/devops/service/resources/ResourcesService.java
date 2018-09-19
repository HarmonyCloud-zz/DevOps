package com.harmony.devops.service.resources;

import com.harmony.devops.controller.recive.resources.ResourcesVO;
import com.harmony.devops.domain.resources.Resources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/16
 * @描述
 */
public interface ResourcesService {
    Page<Resources> findResourcesBy(ResourcesVO resourcesVO, Pageable pageable);
}
