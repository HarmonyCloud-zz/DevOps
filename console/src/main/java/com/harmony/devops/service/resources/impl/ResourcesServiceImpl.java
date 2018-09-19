package com.harmony.devops.service.resources.impl;

import com.harmony.devops.controller.recive.resources.ResourcesVO;
import com.harmony.devops.domain.resources.Resources;
import com.harmony.devops.repository.AllRepositories;
import com.harmony.devops.service.resources.ResourcesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/16
 * @描述
 */
@Service
public class ResourcesServiceImpl extends AllRepositories implements ResourcesService{
    @Override
    public Page<Resources> findResourcesBy(ResourcesVO resourcesVO, Pageable pageable) {
        if(StringUtils.isNotBlank(resourcesVO.getResourcesName())){
            return resourcesRepository.findByResourcesName(resourcesVO.getResourcesName(),pageable);
        }
        if(StringUtils.isNotBlank(resourcesVO.getResourcesUrl())){
            return resourcesRepository.findByResourcesUrl(resourcesVO.getResourcesUrl(),pageable);
        }
        return resourcesRepository.findByResourcesNameAndResourcesUrl(resourcesVO.getResourcesName(),resourcesVO.getResourcesUrl(),pageable);

    }
}
