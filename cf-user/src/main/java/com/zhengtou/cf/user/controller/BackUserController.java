package com.zhengtou.cf.user.controller;

import com.zhengtou.cf.common.annotation.validator.PassWord;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.*;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.enums.RedisKeyPrefixEnum;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.operator.pojo.entity.StoreEntity;
import com.zhengtou.cf.user.controller.reciveVO.BackUserReciveVO;
import com.zhengtou.cf.user.controller.reciveVO.QueryConsumerUserReciveVO;
import com.zhengtou.cf.user.pojo.entity.BackUserEntity;
import com.zhengtou.cf.user.pojo.entity.UserEntity;
import com.zhengtou.cf.user.pojo.entity.enums.UserTypeEnum;
import com.zhengtou.cf.user.pojo.vo.BackUserVO;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import com.zhengtou.cf.user.service.BackUserService;
import com.zhengtou.cf.user.service.ConsumerUserService;
import com.zhengtou.cf.user.service.UserService;
import com.zhengtou.cf.util.BeanNullUtil;
import com.zhengtou.cf.util.FileUtil;
import com.zhengtou.cf.util.PinyinUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/back")
@Api(description = "后台用户服务")
@Validated
public class BackUserController {
    @Autowired
    UserService userService;
    @Autowired
    BackUserService backUserService;
    @Autowired
    ConsumerUserService consumerUserService;
    @Autowired
    BaseDao dao;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Value("${dashboard.menu.resources.path}")
    private String menuJson;

    /**
     * 用户密码登录
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ApiOperation("后台用户密码登录")
    public NetVO login(@RequestParam String loginName, @RequestParam @PassWord String password) {
        BackUserVO user = backUserService.getBackUserByLoginNameAndPwd(loginName, DBUtil.generatePassword(password));
        if (user == null) {
            return new SuccFessionVO(RtnResultEnum.E000000);
        }
        UserEntity userEntity = dao.get(UserEntity.class, user.getId());
        userEntity.setLastLoginTime(System.currentTimeMillis());
        dao.modify(userEntity);
        //登录成功时，将登录信息存入缓存redis
        String token = DBUtil.generateID();
        user.setToken(token);
        user.setLastLoginTime(TimeUtil.timeToString(userEntity.getLastLoginTime()));
        myRedisComponent.setDay(RedisKeyPrefixEnum.LOGIN_TOKEN.getPrefix() + loginName, token, 1L);
        myRedisComponent.setDay(token, user, 1L);
        return new ResponseVO(user);
    }

    /**
     * 用户旧密码改密
     */
    @RequestMapping(value = "updataPwd/{token}/{oldPwd}/{newPwd}", method = RequestMethod.PUT)
    @ApiOperation("用户旧密码改密")
    public NetVO updataPwd(@PathVariable String token, @PathVariable @PassWord String oldPwd, @PathVariable @PassWord String newPwd) {
        BackUserVO backUserVO = (BackUserVO) myRedisComponent.get(token);
        if (backUserVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        UserEntity user = dao.get(UserEntity.class, backUserVO.getId());
        if (user == null) {
            return new SuccFessionVO(RtnResultEnum.E000001);
        }
        if (!user.getPassword().equals(DBUtil.generatePassword(oldPwd))) {
            return new SuccFessionVO(RtnResultEnum.E000001);
        }
        user.setPassword(DBUtil.generatePassword(newPwd));
        dao.modify(user);
        return new SuccFessionVO();
    }

    /**
     * 用户可查看栏目查询
     */
    @RequestMapping(value = "queryMenu/{token}", method = RequestMethod.GET)
    @ApiOperation("用户可查看栏目查询")
    public NetVO queryMenu(@PathVariable String token) {
        BackUserVO backUserVO = (BackUserVO) myRedisComponent.get(token);
        if (backUserVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        BackUserEntity user = dao.get(BackUserEntity.class, backUserVO.getId());
        if (user == null) {
            return new SuccFessionVO(RtnResultEnum.E000001);
        }
        if (StringUtils.isBlank(user.getMenu())) {
            return new SuccFessionVO(RtnResultEnum.E070012);
        }
        return new StringResponseVO(user.getMenu());
    }

    /**
     * 用户可查看栏目查询
     */
    @RequestMapping(value = "queryBackUserMenu/{userId}", method = RequestMethod.GET)
    @ApiOperation("用户可查看栏目查询")
    public NetVO queryBackUserMenu(@PathVariable long userId) {
        BackUserEntity user = dao.get(BackUserEntity.class, userId);
        if (user == null) {
            return new SuccFessionVO(RtnResultEnum.E000001);
        }
        if (user.getUserType().equals(UserTypeEnum.消费用户) || user.getUserType().equals(UserTypeEnum.静默注册消费用户)) {
            return new SuccFessionVO(RtnResultEnum.E070014);
        }
        return new StringResponseVO(user.getMenu());
    }

    /**
     * 用户可查看栏目编辑
     */
    @RequestMapping(value = "editMenu/{userId}", method = RequestMethod.PUT)
    @ApiOperation("用户可查看栏目编辑")
    public NetVO editMenu(@PathVariable long userId, @RequestParam String menu) {
        BackUserEntity user = dao.get(BackUserEntity.class, userId);
        if (user == null) {
            return new SuccFessionVO(RtnResultEnum.E000001);
        }
        if (user.getUserType().equals(UserTypeEnum.消费用户) || user.getUserType().equals(UserTypeEnum.静默注册消费用户)) {
            return new SuccFessionVO(RtnResultEnum.E070014);
        }
        user.setMenu(menu);
        dao.modify(user);
        return new SuccFessionVO();
    }

    /**
     * 获取用户基础信息
     */
    @RequestMapping(value = "getUserInfo/{token}/{id}", method = RequestMethod.GET)
    @ApiOperation("获取用户基础信息")
    public NetVO getUserInfo(@PathVariable long id) {
        BackUserVO backUserVO = backUserService.getBackUserByUserId(id);
        if (backUserVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        return new ResponseVO(backUserVO);
    }

    /**
     * 设置用户所在机构
     */
    @RequestMapping(value = "editUserOrg/{token}/{userId}/{orgId}", method = RequestMethod.PUT)
    @ApiOperation("设置用户所在机构")
    public NetVO editUserOrg(@PathVariable long userId, @PathVariable long orgId, Long storeId) {
        BackUserEntity backUserEntity = dao.get(BackUserEntity.class, userId);
        if (backUserEntity == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        backUserEntity.setOrganiza(dao.get(OrganizationEntity.class, orgId));
        if (storeId != null) {
            backUserEntity.setStore(dao.get(StoreEntity.class, storeId));
        }
        dao.modify(backUserEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "editBackUser/{token}/{userId}", method = RequestMethod.PUT)
    @ApiOperation("编辑用户信息")
    public NetVO editBackUser(@PathVariable String token,@PathVariable long userId, @Validated BackUserReciveVO backUserReciveVO) throws BaseException {
        BackUserVO user = (BackUserVO) myRedisComponent.get(token);
        if (user == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        OrganizationEntity ztOrg = (OrganizationEntity) myRedisComponent.get("ztOrganiza");
        if (user.getOrgId() != ztOrg.getId()) {
            throw new BaseException(RtnResultEnum.E060000);
        }
        BackUserEntity backUserEntity = dao.get(BackUserEntity.class, userId);
        BeanUtils.copyProperties(backUserReciveVO, backUserEntity, BeanNullUtil.getNullPropertyNames(backUserReciveVO));
        if (backUserReciveVO.getOrgId() != null) {
            OrganizationEntity organizationEntity = dao.get(OrganizationEntity.class, backUserReciveVO.getOrgId());
            if (!organizationEntity.getStatus().equals(OrganizationEntity.OrgStatus.启用) || organizationEntity.getIsDeleted()) {
                throw new BaseException(RtnResultEnum.E040006);
            }
            backUserEntity.setOrganiza(organizationEntity);
        }
        if (backUserReciveVO.getStoreId() != null) {
            StoreEntity storeEntity = dao.get(StoreEntity.class, backUserReciveVO.getStoreId());
            if (!storeEntity.getStatus().equals(OrganizationEntity.OrgStatus.启用) || storeEntity.getIsDeleted()) {
                throw new BaseException(RtnResultEnum.E040007);
            }
            backUserEntity.setStore(storeEntity);
        }
        dao.modify(backUserEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "deletedBackUser/{token}/{userId}", method = RequestMethod.DELETE)
    @ApiOperation("删除用户")
    public NetVO deletedBackUser(@PathVariable String token, @PathVariable long userId) throws BaseException {
        BackUserVO user = (BackUserVO) myRedisComponent.get(token);
        if (user == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        OrganizationEntity organizationEntity = (OrganizationEntity) myRedisComponent.get("ztOrganiza");
        if (user.getOrgId() != organizationEntity.getId()) {
            throw new BaseException(RtnResultEnum.E060000);
        }
        BackUserEntity backUserEntity = dao.get(BackUserEntity.class, userId);
        dao.delete(backUserEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "register/{token}", method = RequestMethod.POST)
    @ApiOperation("用户注册")
    public NetVO register(@PathVariable String token, @Validated BackUserReciveVO backUserReciveVO) throws BaseException {
        BackUserVO user = (BackUserVO) myRedisComponent.get(token);
        if (user == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        if (backUserReciveVO.getOrgId() == null) {
            return new SuccFessionVO(RtnResultEnum.E070003);
        }
        BackUserEntity backUserEntity = new BackUserEntity();
        backUserEntity.setPhone(backUserReciveVO.getPhone());
        backUserEntity.setNickName(backUserReciveVO.getNickName());
        backUserEntity.setPassword(DBUtil.generatePassword());
        backUserEntity.setLoginName(PinyinUtil.getPingYin(backUserReciveVO.getNickName()));
        backUserEntity.setUserType(backUserReciveVO.getUserType());
        backUserEntity.setOrganiza(backUserReciveVO.getOrgId() != null ? dao.get(OrganizationEntity.class, backUserReciveVO.getOrgId()) :
                (OrganizationEntity) myRedisComponent.get("nullOrganiza"));
        backUserEntity.setStore(backUserReciveVO.getStoreId() != null ? dao.get(StoreEntity.class, backUserReciveVO.getStoreId()) : (StoreEntity)
                myRedisComponent.get("nullStore"));
        backUserEntity.setMenu(FileUtil.readResource(menuJson).replaceAll("\\\\s*|\\t|\\r|\\n", ""));
        dao.save(backUserEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "getConsumerUser/{token}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("注册用户列表")
    public NetVO getConsumerUser(@PathVariable String token, QueryConsumerUserReciveVO consumerUserReciveVO, @PathVariable Integer page,
                                 @PathVariable Integer size) {
        BackUserVO user = (BackUserVO) myRedisComponent.get(token);
        if (user == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        OrganizationEntity organizationEntity = (OrganizationEntity) myRedisComponent.get("ztOrganiza");
        if (user.getOrgId() != organizationEntity.getId()) {
            return new SuccFessionVO(RtnResultEnum.E060000);
        }
        List<ConsumerUserVO> userItemVOS = consumerUserService.getConsumerUserList(consumerUserReciveVO, page, size);
        Long count = consumerUserService.countConsumerUserList(consumerUserReciveVO);
        return new ListResponseVO(userItemVOS, count);
    }

    @RequestMapping(value = "getBackUser/{token}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("后台用户列表")
    public NetVO getBackUser(@PathVariable String token, BackUserReciveVO backUserReciveVO, @PathVariable Integer page, @PathVariable
            Integer size) {
        BackUserVO user = (BackUserVO) myRedisComponent.get(token);
        if (user == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        OrganizationEntity ztOrg = (OrganizationEntity) myRedisComponent.get("ztOrganiza");
        StoreEntity ztStore = (StoreEntity) myRedisComponent.get("ztStore");
        if (user.getOrgId() != null && user.getOrgId() != ztOrg.getId()) {
            backUserReciveVO.setOrgId(user.getOrgId());
        }
        if (user.getStoreId() != null && user.getStoreId() != ztStore.getId()) {
            backUserReciveVO.setStoreId(user.getStoreId());
        }
        List<BackUserVO> backUserList = backUserService.getBackUserList(backUserReciveVO, page, size);
        Long count = backUserService.countBackUser(backUserReciveVO);
        return new ListResponseVO(backUserList, count);
    }
}
