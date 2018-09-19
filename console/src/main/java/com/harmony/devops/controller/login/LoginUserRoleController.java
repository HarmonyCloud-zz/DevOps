package com.harmony.devops.controller.login;

import com.harmony.devops.common.vo.VO;
import com.harmony.devops.domain.role.Role;
import com.harmony.devops.domain.user.User;
import com.harmony.devops.service.AllServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/3
 * @描述
 */
@RestController
@Api(description = "用户角色服务")
@RequestMapping("userRole")
public class LoginUserRoleController extends AllServices{
    @ApiOperation("新建用户角色关系")
    @RequestMapping(value = "save/{userId}/{roleId}", method = {RequestMethod.POST,RequestMethod.GET})
    public VO saveUserRole(@PathVariable @ApiParam(name = "用户id", value = "userId") long userId,
                           @PathVariable @ApiParam(name = "角色id", value = "roleId") long roleId) {
        User user=userRepository.getOne(userId);
        Role role=roleRepository.getOne(roleId);
        user.getRoles().add(role);
        userRepository.save(user);
        return user.toVO();
    }

    @ApiOperation("删除用户角色关系")
    @RequestMapping(value = "del/{userId}/{roleId}", method = {RequestMethod.POST,RequestMethod.GET})
    public VO delUserRole(@PathVariable @ApiParam(name = "用户id", value = "userId") long userId,
                             @PathVariable @ApiParam(name = "角色id", value = "roleId") long roleId) {
        User user=userRepository.getOne(userId);
        Role role=roleRepository.getOne(roleId);
        user.getRoles().remove(role);
        userRepository.save(user);
        return user.toVO();
    }
}
