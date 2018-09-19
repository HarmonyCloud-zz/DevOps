package com.harmony.devops.controller.login;

import com.harmony.devops.common.component.redis.MyRedisComponent;
import com.harmony.devops.common.enums.RtnResultEnum;
import com.harmony.devops.common.exception.BaseException;
import com.harmony.devops.common.utils.BeanNullUtil;
import com.harmony.devops.common.utils.PageableUtil;
import com.harmony.devops.common.vo.ListResponseVO;
import com.harmony.devops.common.vo.NetVO;
import com.harmony.devops.common.vo.SuccFessionVO;
import com.harmony.devops.common.vo.VO;
import com.harmony.devops.controller.recive.resources.ResourcesVO;
import com.harmony.devops.domain.resources.Resources;
import com.harmony.devops.service.AllServices;
import com.harmony.devops.service.resources.ResourcesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/3
 * @描述
 */
@RestController
@RequestMapping("resources")
@Api(description = "资源服务")
public class LoginPermissionController extends AllServices{
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    ResourcesService resourcesService;

    @ApiOperation("新建资源")
    @RequestMapping(value = "save", method = {RequestMethod.POST,RequestMethod.GET})
    public NetVO saveResources(@RequestBody @ApiParam(value = "资源vo", name = "resourcesVO") ResourcesVO resourcesVO) throws BaseException {
        Resources resources=new Resources();
        resources.setResourcesName(resourcesVO.getResourcesName());
        resources.setResourcesUrl(resourcesVO.getResourcesUrl());
        resourcesRepository.save(resources);
        return new SuccFessionVO();
    }

    @ApiOperation("编辑资源")
    @RequestMapping(value = "modify/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public NetVO modifyResources(@PathVariable @ApiParam(value = "id",name = "id") long id,
                           @RequestBody @ApiParam(value = "资源vo", name = "resourcesVO") ResourcesVO resourcesVO)
            throws BaseException {
        Resources resources=resourcesRepository.getOne(id);
        if(resources==null){
            throw new BaseException(RtnResultEnum.E000000);
        }
        BeanUtils.copyProperties(resourcesVO,resources, BeanNullUtil.getNullPropertyNames(resourcesVO));
        resourcesRepository.save(resources);
        return new SuccFessionVO();
    }

    @ApiOperation("删除资源")
    @RequestMapping(value = "del/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public NetVO delResources(@PathVariable @ApiParam(value = "id",name = "id") long id)
            throws BaseException {
        Resources resources=resourcesRepository.getOne(id);
        if(resources==null){
            throw new BaseException(RtnResultEnum.E000000);
        }
        resourcesRepository.delete(resources);
        return new SuccFessionVO();
    }

    @ApiOperation("查询资源")
    @RequestMapping(value = "queryBy/{page}/{size}", method = {RequestMethod.POST,RequestMethod.GET})
    public Collection<VO> queryResources(@PathVariable @ApiParam(value = "页数",name ="page" ) Integer page,
                                         @PathVariable @ApiParam(value = "单页结果数",name = "size") Integer size,
                                         @ApiParam(value = "资源vo",name = "resourcesVO") ResourcesVO resourcesVO) {
        Page<Resources> resources= resourcesService.findResourcesBy(resourcesVO,PageableUtil.get(page,size));
        return (Collection<VO>)VO.toVOCollection(resources);
    }
}
