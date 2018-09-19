package com.harmony.devops.controller.api.user;

import com.harmony.devops.common.component.redis.MyRedisComponent;
import com.harmony.devops.common.enums.RtnResultEnum;
import com.harmony.devops.common.exception.BaseException;
import com.harmony.devops.common.utils.BeanNullUtil;
import com.harmony.devops.common.vo.NetVO;
import com.harmony.devops.common.vo.SuccFessionVO;
import com.harmony.devops.controller.recive.user.UserVO;
import com.harmony.devops.domain.organization.Department;
import com.harmony.devops.domain.organization.Organization;
import com.harmony.devops.domain.user.User;
import com.harmony.devops.service.AllServices;
import com.harmony.devops.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/3
 * @描述
 */
@RestController
@Api(description = "用户服务")
@RequestMapping("user")
public class UserController extends AllServices{
    @Autowired
    MyRedisComponent myRedisComponent;

    @ApiOperation("新建用户")
    @RequestMapping(value = "save", method = {RequestMethod.POST,RequestMethod.GET})
    public NetVO saveUser(@RequestBody @ApiParam(name = "用户vo",value = "userVO") UserVO userVO) throws BaseException {
        User user1 = (User) SecurityUtils.getSubject().getPrincipal();
        User user=new User();
        BeanUtils.copyProperties(userVO,user, BeanNullUtil.getNullPropertyNames(userVO));
        if(userVO.orgId!=null){
            Organization organization= organizationRepository.getOne(userVO.orgId);
            if(organization==null){
                throw new BaseException(RtnResultEnum.E020000);
            }
            user.setOrg(organization);
        }
        if(userVO.departId!=null){
            Department department=departmentRespository.getOne(userVO.departId);
            if(department==null){
                throw new BaseException(RtnResultEnum.E020001);
            }
            user.setDepartment(department);
        }
        user.setCreater(user1);
        userRepository.save(user);
        return new SuccFessionVO();
    }

}
