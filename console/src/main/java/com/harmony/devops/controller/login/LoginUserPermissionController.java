package com.harmony.devops.controller.login;

import com.harmony.devops.common.vo.VO;
import com.harmony.devops.domain.resources.Resources;
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

import javax.annotation.Resource;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/3
 * @描述
 */
@RestController
@Api(description = "用户权限服务")
@RequestMapping("userPerm")
public class LoginUserPermissionController extends AllServices{
    @ApiOperation("新建用户权限关系")
    @RequestMapping(value = "save/{userId}/{permId}", method = {RequestMethod.POST,RequestMethod.GET})
    public VO saveUserPerm(@PathVariable @ApiParam(name = "用户id", value = "userId") long userId,
                           @PathVariable @ApiParam(name = "权限id", value = "permId") long permId) {
        User user=userRepository.getOne(userId);
        Resources resources=resourcesRepository.getOne(permId);
        user.getResources().add(resources);
        userRepository.save(user);
        return user.toVO();
    }

    @ApiOperation("删除用户权限关系")
    @RequestMapping(value = "del/{userId}/{roleId}", method = {RequestMethod.POST,RequestMethod.GET})
    public VO delUserPerm(@PathVariable @ApiParam(name = "用户id", value = "userId") long userId,
                             @PathVariable @ApiParam(name = "权限id", value = "permId") long permId) {
        User user=userRepository.getOne(userId);
        Resources resources=resourcesRepository.getOne(permId);
        user.getResources().remove(resources);
        userRepository.save(user);
        return user.toVO();
    }
}
