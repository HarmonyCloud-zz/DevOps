package com.harmony.devops.controller.login;

import com.harmony.devops.common.enums.RtnResultEnum;
import com.harmony.devops.common.exception.BaseException;
import com.harmony.devops.common.utils.BeanNullUtil;
import com.harmony.devops.common.utils.PageableUtil;
import com.harmony.devops.common.vo.NetVO;
import com.harmony.devops.common.vo.SuccFessionVO;
import com.harmony.devops.common.vo.VO;
import com.harmony.devops.domain.resources.Resources;
import com.harmony.devops.domain.role.Role;
import com.harmony.devops.service.AllServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/3
 * @描述
 */
@RestController
@RequestMapping("role")
@Api(description = "角色服务")
public class LoginRoleController extends AllServices {

    @ApiOperation("新建角色")
    @RequestMapping(value = "save", method = {RequestMethod.POST, RequestMethod.GET})
    public VO saveResources(@RequestParam @ApiParam(value = "角色名称", name = "roleName") String roleName,
                            @RequestBody @ApiParam(value = "资源列表 ", name = "resourcesIds") Set<Long> resourcesIds) {
        Role role = new Role();
        role.setRoleName(roleName);
        Set<Resources> resources = new HashSet<>();
        resourcesIds.forEach(resourcesId -> resources.add(resourcesRepository.findOne(resourcesId)));
        role.setResources(resources);
        return roleRepository.save(role).toVO(false);
    }

    @ApiOperation("编辑角色")
    @RequestMapping(value = "modify/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public VO modifyResources(@PathVariable @ApiParam(value = "id", name = "id") long id,
                              @RequestParam(required = false) @ApiParam(value = "角色名称", name = "roleName") String roleName,
                              @RequestBody @ApiParam(value = "资源列表 ", name = "resourcesIds") Set<Long> resourcesIds)
            throws BaseException {
        Role role = roleRepository.getOne(id);
        if (role == null) {
            throw new BaseException(RtnResultEnum.E000000);
        }
        if (StringUtils.isNotBlank(roleName)) {
            role.setRoleName(roleName);
        }
        Set<Resources> resources = new HashSet<>();
        resourcesIds.forEach(resourcesId -> resources.add(resourcesRepository.findOne(resourcesId)));
        role.setResources(resources);
        return roleRepository.save(role).toVO(false);
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "del/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public NetVO delResources(@PathVariable @ApiParam(value = "id", name = "id") long id)
            throws BaseException {
        Role role = roleRepository.getOne(id);
        if (role == null) {
            throw new BaseException(RtnResultEnum.E000000);
        }
        resourcesRepository.delete(id);
        return new SuccFessionVO();
    }

    @ApiOperation("查询角色")
    @RequestMapping(value = "queryBy/{page}/{size}", method = {RequestMethod.POST, RequestMethod.GET})
    public Collection<VO> queryResources(@RequestParam(required = false)
                                             @ApiParam(name = "roleName", value = "权限名称") String roleName,
                                         @PathVariable Integer page,@PathVariable Integer size) {
        return (Collection<VO>) VO.toVOCollection(roleService.findBy(roleName, PageableUtil.get(page,size)));
    }

}
