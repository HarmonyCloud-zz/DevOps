package com.zhengtou.cf.api.app.v01.user;

import com.zhengtou.cf.common.annotation.validator.PassWord;
import com.zhengtou.cf.common.annotation.validator.Phone;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.AnnexTypeEnum;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.*;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.enums.RedisKeyPrefixEnum;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.operator.service.OrgService;
import com.zhengtou.cf.user.controller.reciveVO.person.*;
import com.zhengtou.cf.user.pojo.entity.AnnexEntity;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;
import com.zhengtou.cf.user.pojo.entity.UserEntity;
import com.zhengtou.cf.user.pojo.entity.enums.UserInfoCompleteEnum;
import com.zhengtou.cf.user.pojo.entity.enums.UserSourceTypeEnum;
import com.zhengtou.cf.user.pojo.entity.enums.UserTypeEnum;
import com.zhengtou.cf.user.pojo.entity.person.*;
import com.zhengtou.cf.user.pojo.vo.AnnexVO;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserInfo;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import com.zhengtou.cf.user.pojo.vo.person.*;
import com.zhengtou.cf.user.service.ConsumerUserService;
import com.zhengtou.cf.util.BeanNullUtil;
import com.zhengtou.cf.util.CommonUtil;
import com.zhengtou.cf.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v0.1/consumer")
@Api(description = "客户用户api")
@Validated
public class ConsumerUserApi {
    @Autowired
    ConsumerUserService consumerUserService;
    @Autowired
    BaseDao dao;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    OrgService orgService;
    @Value("${upload.contractFile.path}")
    private String path;
    @Value("${show.contractFile.path}")
    private String getPath;

    /**
     * 用户基本信息
     */
    @RequestMapping(value = "getUserInfo/{phone}", method = RequestMethod.POST)
    @ApiOperation("用户基本信息")
    public NetVO getUserInfo(@PathVariable @Phone String phone) {
        ConsumerUserVO consumerUserVO = consumerUserService.getConsumerUserVOByPhone(phone);
        if (consumerUserVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000000);
        }
        PersonalVO personalVO = consumerUserService.getPersonalByUserId(consumerUserVO.getId());
        String token = DBUtil.generateID();
        consumerUserVO.setToken(token);
        consumerUserVO.setPersonId(personalVO == null ? null : personalVO.getPersonalId());
        myRedisComponent.setDay(RedisKeyPrefixEnum.LOGIN_TOKEN.getPrefix() + phone, token, 1L);
        myRedisComponent.setDay(token, consumerUserVO, 1L);
        return new ResponseVO(consumerUserVO);
    }

    /**
     * 用户旧密码改密
     */
    @RequestMapping(value = "updatePwd/{token}/{oldPwd}/{newPwd}", method = RequestMethod.PUT)
    @ApiOperation("用户旧密码改密")
    public NetVO updatePwd(@PathVariable String token, @PathVariable @PassWord String oldPwd, @PathVariable @PassWord String newPwd) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new SuccFessionVO(RtnResultEnum.E000003);
        }
        UserEntity user = dao.get(UserEntity.class, userItemVO.getId());
        if (user == null) {
            return new SuccFessionVO(RtnResultEnum.E000001);
        }
        user.setPassword(DBUtil.generatePassword(newPwd));
        dao.modify(user);
        return new SuccFessionVO();
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "register/{phone}/{passWord}/{code}/{orgNo}", method = RequestMethod.POST)
    @ApiOperation("用户注册")
    public NetVO register(@PathVariable @Phone String phone, @PathVariable @PassWord String passWord, @PathVariable String code, @PathVariable
            String orgNo) throws BaseException {
        String hasCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.REG_SMS_CODE.getPrefix() + phone);
        CommonUtil.checkCode(hasCode, code);
        OrganizationEntity org = StringUtils.isNotBlank(orgNo) ? orgService.getOrganizaByOrgNo(orgNo) : null;
        consumerUserService.saveConsumerUser(phone, passWord, UserSourceTypeEnum.WEB, org);
        return new SuccFessionVO();
    }

    /**
     * 用户静默注册
     */
    @RequestMapping(value = "registerSilent/{phone}/{orgNo}", method = RequestMethod.POST)
    @ApiOperation("用户静默注册")
    public NetVO registerSilent(@PathVariable @Phone String phone, @PathVariable String orgNo) throws BaseException {
        if (StringUtils.isBlank(orgNo)) {
            throw new BaseException(RtnResultEnum.E030000);
        }
        OrganizationEntity organizationEntity = (OrganizationEntity) dao.get("from OrganizationEntity o where o.isDeleted=false and o" +
                ".orgNo=?0", new Object[]{orgNo});
        if (organizationEntity == null) {
            throw new BaseException(RtnResultEnum.E040000);
        }
        ConsumerUserEntity consumerUserEntity = new ConsumerUserEntity();
        consumerUserEntity.setPassword(DBUtil.generatePassword());
        consumerUserEntity.setLoginName(phone);
        consumerUserEntity.setPhone(phone);
        consumerUserEntity.setUserType(UserTypeEnum.静默注册消费用户);
        consumerUserEntity.setOrganiza(organizationEntity);
        consumerUserEntity.setUserSourceTypeEnum(UserSourceTypeEnum.WEB);
        consumerUserEntity.setPersonal((PersonalEntity) myRedisComponent.get("nullPerson"));
        dao.save(consumerUserEntity);
        ConsumerUserVO userVO = new ConsumerUserVO();
        userVO.setPhone(phone);
        userVO.setUserType(UserTypeEnum.静默注册消费用户);
        String token = DBUtil.generateID();
        userVO.setToken(token);
        myRedisComponent.setDay(RedisKeyPrefixEnum.LOGIN_TOKEN.getPrefix() + phone, token, 1L);
        myRedisComponent.setDay(token, userVO, 1L);
        return new ResponseVO(userVO);
    }

    /**
     * 用户密码登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ApiOperation("用户密码登录")
    public NetVO login(@RequestParam(required = true) @Phone String phone, @RequestParam(required = true) @PassWord String password) {
        ConsumerUserVO user = consumerUserService.getConsumerUserByLoginNameAndPwd(phone, password);
        if (user == null) {
            return new SuccFessionVO(RtnResultEnum.E000000);
        }
        ConsumerUserEntity consumerUserEntity = dao.get(ConsumerUserEntity.class, user.getId());
        consumerUserEntity.setLastLoginTime(System.currentTimeMillis());
        dao.modify(consumerUserEntity);
        PersonalVO personalVO = consumerUserService.getPersonalByUserId(user.getId());
        String token = DBUtil.generateID();
        user.setToken(token);
        user.setLastLoginTime(TimeUtil.timeToString(consumerUserEntity.getLastLoginTime()));
        user.setPersonId(personalVO == null ? null : personalVO.getPersonalId());
        myRedisComponent.setDay(RedisKeyPrefixEnum.LOGIN_TOKEN.getPrefix() + phone, token, 1L);
        myRedisComponent.setDay(token, user, 1L);
        return new ResponseVO(user);
    }

    /**
     * 获取用户登陆信息
     */
    @RequestMapping(value = "getConsumerUserInfoByToken/{token}", method = RequestMethod.GET)
    @ApiOperation("获取用户登陆信息")
    public NetVO queryConsumerUserByToken(@PathVariable String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        ConsumerUserEntity consumerUserEntity = dao.get(ConsumerUserEntity.class, userItemVO.getId());
        PersonalEntity personalEntity = consumerUserEntity.getPersonal();
        List<EducationalVO> educationalVOS = consumerUserService.getEducationalByPersonalId(personalEntity.getId());
        ConsumerUserInfo consumerUserInfo = new ConsumerUserInfo(consumerUserEntity.getId(), consumerUserEntity.getUserType(), consumerUserEntity
                .getLastLoginTime(), personalEntity.getName(), personalEntity.getIdNo(), consumerUserEntity.getIsUserInfoComplete(),
                consumerUserEntity.isHasTradePwd(), consumerUserEntity.getSocialTypeEnum(), educationalVOS.isEmpty() ? null : educationalVOS.get(0)
                .getQualification(), personalEntity
                .getResidenceAddress(), personalEntity.getMaritalStatus(), personalEntity.getAge(), personalEntity.getHouseCondition());
        return new ResponseVO(consumerUserInfo);
    }

    /**
     * 编辑用户基础信息
     */
    @RequestMapping(value = "modifyPersonInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("编辑用户基础信息")
    public NetVO modifyPersonInfo(@PathVariable String token, @Validated PersonalReciveVO personalVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        PersonalEntity personalEntity = dao.get(PersonalEntity.class, userItemVO.getPersonId());
        if (personalEntity.equals((PersonalEntity) myRedisComponent.get("nullPerson"))) {
            personalEntity = new PersonalEntity();
            BeanUtils.copyProperties(personalVO, personalEntity, BeanNullUtil.getNullPropertyNames(personalVO));
            personalEntity.setCellphone(userItemVO.getPhone());
            dao.save(personalEntity);

            ConsumerUserEntity consumerUserEntity = dao.get(ConsumerUserEntity.class, userItemVO.getId());
            consumerUserEntity.setIsUserInfoComplete(UserInfoCompleteEnum.已完善);
            consumerUserEntity.setPersonal(personalEntity);
            dao.modify(consumerUserEntity);
            userItemVO.setPersonId(personalEntity.getId());
            userItemVO.setUserInfoCompleteEnum(UserInfoCompleteEnum.已完善);
            myRedisComponent.setDay(token, userItemVO, 1l);
        } else {
            BeanUtils.copyProperties(personalVO, personalEntity);
            dao.modify(personalEntity);
        }
        return new SuccFessionVO();
    }

    /**
     * 获取用户基础信息
     */
    @RequestMapping(value = "getPersonInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("获取用户基础信息")
    public NetVO getPersonInfo(@PathVariable String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        PersonalVO personalVO = consumerUserService.getPersonalById(userItemVO.getPersonId());
        return new ResponseVO(personalVO);
    }

    /**
     * 新增教育信息
     */
    @RequestMapping(value = "addEducationInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("新增用户教育信息")
    public NetVO modifyEducationInfo(@PathVariable String token, @Validated EducationalReciveVO educationalVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        EducationalEntity educationalInfoEntity = new EducationalEntity();
        BeanUtils.copyProperties(educationalVO, educationalInfoEntity);
        educationalInfoEntity.setPersonal(dao.get(PersonalEntity.class, userItemVO.getPersonId()));
        dao.save(educationalInfoEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑教育信息
     */
    @RequestMapping(value = "editEducationInfo/{token}/{id}", method = RequestMethod.POST)
    @ApiOperation("编辑用户教育信息")
    public NetVO editEducationInfo(@PathVariable String token, @PathVariable long id, @Validated EducationalReciveVO educationalVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        EducationalEntity educationalInfoEntity = dao.get(EducationalEntity.class, id);
        if (educationalInfoEntity == null) {
            return new ErrorFessionVO(RtnResultEnum.E050001);
        }
        BeanUtils.copyProperties(educationalVO, educationalInfoEntity);
        dao.modify(educationalInfoEntity);
        return new SuccFessionVO();
    }

    /**
     * 获取教育信息
     */
    @RequestMapping(value = "getEducationInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("获取用户教育信息")
    public NetVO getEducationInfo(@PathVariable String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        List<EducationalVO> educationalVOS = consumerUserService.getEducationalByPersonalId(userItemVO.getPersonId());
        return new ListResponseVO(educationalVOS);
    }

    /**
     * 新增工作信息
     */
    @RequestMapping(value = "addEmpInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("新增用户工作信息")
    public NetVO addEmpInfo(@PathVariable String token, @Validated EmpReciveVO empVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        EmpEntity educationalInfoEntity = new EmpEntity();
        BeanUtils.copyProperties(empVO, educationalInfoEntity);
        educationalInfoEntity.setPersonal(dao.get(PersonalEntity.class, userItemVO.getPersonId()));
        dao.save(educationalInfoEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑工作信息
     */
    @RequestMapping(value = "editEmpInfo/{token}/{id}", method = RequestMethod.POST)
    @ApiOperation("编辑用户工作信息")
    public NetVO editEmpInfo(@PathVariable String token, @PathVariable long id, @Validated EmpReciveVO empVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        EmpEntity educationalInfoEntity = dao.get(EmpEntity.class, id);
        if (educationalInfoEntity == null) {
            return new ErrorFessionVO(RtnResultEnum.E0000xx);
        }
        BeanUtils.copyProperties(empVO, educationalInfoEntity);
        dao.modify(educationalInfoEntity);
        return new SuccFessionVO();
    }

    /**
     * 获取工作信息
     */
    @RequestMapping(value = "getEmpInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("获取用户工作信息")
    public NetVO getEmpInfo(@PathVariable String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        List<EmpVO> empVOS = consumerUserService.getEmpByPersonalId(userItemVO.getPersonId());
        return new ListResponseVO(empVOS);
    }

    /**
     * 修改用户收入信息
     */
    @RequestMapping(value = "modifyIncomeInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("编辑用户收入信息")
    public NetVO modifyIncomeInfo(@PathVariable String token, @Validated IncomeReciveVO incomeVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        IncomeEntity incomeEntity = dao.get("select p from IncomeEntity p,PersonalEntity c where p.isDeleted=false and c.id=?0 and p.personal=c" +
                " ", new
                Object[]{userItemVO.getPersonId()});
        if (incomeEntity == null) {
            IncomeEntity incomeInfoEntity = new IncomeEntity();
            BeanUtils.copyProperties(incomeVO, incomeInfoEntity);
            incomeInfoEntity.setPersonal(dao.get(PersonalEntity.class, userItemVO.getPersonId()));
            dao.save(incomeInfoEntity);
        } else {
            BeanUtils.copyProperties(incomeVO, incomeEntity);
            dao.modify(incomeEntity);
        }
        return new SuccFessionVO();
    }

    /**
     * 获取用户收入信息
     */
    @RequestMapping(value = "getIncomeInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("获取用户收入信息")
    public NetVO getIncomeInfo(@PathVariable String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        IncomeVO incomeVO = consumerUserService.getIncomeByPersonalId(userItemVO.getPersonId());
        return new ResponseVO(incomeVO);
    }

    /**
     * 添加联系人信息
     */
    @RequestMapping(value = "addContactPersonInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("添加联系人信息")
    public NetVO addContactPersonInfo(@PathVariable String token, @Validated ContactReciveVO contactVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        PersonalEntity personalEntity = dao.get(PersonalEntity.class, userItemVO.getPersonId());
        ContactEntity contactEntity = new ContactEntity();
        BeanUtils.copyProperties(contactVO, contactEntity);
        contactEntity.setPersonal(personalEntity);
        dao.save(contactEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑联系人信息
     */
    @RequestMapping(value = "editContactPersonInfo/{token}/{id}", method = RequestMethod.POST)
    @ApiOperation("编辑联系人信息")
    public NetVO editContactPersonInfo(@PathVariable String token, @PathVariable long id, @Validated ContactReciveVO contactVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        ContactEntity contactEntity = dao.get(ContactEntity.class, id);
        if (contactEntity == null) {
            return new ErrorFessionVO(RtnResultEnum.E030001);
        }
        BeanUtils.copyProperties(contactVO, contactEntity);
        dao.modify(contactEntity);
        return new SuccFessionVO();
    }

    /**
     * 获取联系人信息
     */
    @RequestMapping(value = "getContactPersonInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("获取联系人信息")
    public NetVO getContactPersonInfo(@PathVariable String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        List<ContactVO> contactVOS = consumerUserService.getContactByPersonalId(userItemVO.getPersonId());
        return new ListResponseVO(contactVOS);
    }

    /**
     * 修改用户其他信息
     */
    @RequestMapping(value = "modifyOtherInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("编辑用户其他信息")
    public NetVO modifyOtherInfo(@PathVariable String token, @Validated OtherReciveVO otherVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        OtherEntity personalInfo = dao.get("select p from OtherEntity p,PersonalEntity c where p.isDeleted=false and c.id=?0 and p" +
                ".personal=c", new
                Object[]{userItemVO.getPersonId()});
        if (personalInfo == null) {
            OtherEntity otherEntity = new OtherEntity();
            BeanUtils.copyProperties(otherVO, otherEntity);
            otherEntity.setPersonal(dao.get(PersonalEntity.class, userItemVO.getPersonId()));
            dao.save(otherEntity);
        } else {
            BeanUtils.copyProperties(otherVO, personalInfo);
            dao.modify(personalInfo);
        }
        return new SuccFessionVO();
    }

    /**
     * 获取用户其他信息
     */
    @RequestMapping(value = "getOtherInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("获取用户其他信息")
    public NetVO getOtherInfo(@PathVariable String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            return new ErrorFessionVO(RtnResultEnum.E000005);
        }
        List<OtherVO> otherVOS = consumerUserService.getOtherByPersonalId(userItemVO.getPersonId());
        return new ListResponseVO(otherVOS);
    }

    /**
     * 添加设备信息
     */
    @RequestMapping(value = "addDeviceInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("添加设备信息")
    public NetVO addDeviceInfo(@PathVariable String token, @Validated DeviceVO deviceVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setConsumer(dao.get(ConsumerUserEntity.class, userItemVO.getId()));
        BeanUtils.copyProperties(deviceVO, deviceEntity);
        dao.save(deviceEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑设备信息
     */
    @RequestMapping(value = "editDeviceInfo/{token}/{id}", method = RequestMethod.POST)
    @ApiOperation("编辑设备信息")
    public NetVO editDeviceInfo(@PathVariable String token, @PathVariable long id, @Validated DeviceVO deviceVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        DeviceEntity contactPersonInfo = dao.get(DeviceEntity.class, id);
        if (contactPersonInfo == null) {
            return new ErrorFessionVO(RtnResultEnum.E030001);
        }
        BeanUtils.copyProperties(deviceVO, contactPersonInfo);
        dao.modify(contactPersonInfo);
        return new SuccFessionVO();
    }

    /**
     * 获取设备信息
     */
    @RequestMapping(value = "getDeviceInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("获取设备信息")
    public NetVO getDeviceInfo(@PathVariable String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        List<DeviceVO> deviceVOS = consumerUserService.getDeviceByUserId(userItemVO.getId());
        return new ListResponseVO(deviceVOS);
    }

    /**
     * 添加车辆信息
     */
    @RequestMapping(value = "addCarInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("添加车辆信息")
    public NetVO addCarInfo(@PathVariable String token, @Validated CarVO carVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        CarEntity carEntity = new CarEntity();
        carEntity.setConsumer(dao.get(ConsumerUserEntity.class, userItemVO.getId()));
        BeanUtils.copyProperties(carVO, carEntity);
        dao.save(carEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑车辆信息
     */
    @RequestMapping(value = "editCarInfo/{token}/{id}", method = RequestMethod.POST)
    @ApiOperation("编辑车辆信息")
    public NetVO editCarInfo(@PathVariable String token, @PathVariable long id, @Validated CarVO carVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        CarEntity contactPersonInfo = dao.get(CarEntity.class, id);
        if (contactPersonInfo == null) {
            return new ErrorFessionVO(RtnResultEnum.E030001);
        }
        BeanUtils.copyProperties(carVO, contactPersonInfo);
        dao.modify(contactPersonInfo);
        return new SuccFessionVO();
    }

    /**
     * 获取车辆信息
     */
    @RequestMapping(value = "getCarInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("获取车辆信息")
    public NetVO getCarInfo(@PathVariable String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        List<CarVO> carVOS = consumerUserService.getCarByUserId(userItemVO.getId());
        return new ListResponseVO(carVOS);
    }

    /**
     * 添加房产信息
     */
    @RequestMapping(value = "addHouseInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("添加房产信息")
    public NetVO addHouseInfo(@PathVariable String token, @Validated HouseVO houseVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        HouseEntity contactPersonInfoEntity = new HouseEntity();
        contactPersonInfoEntity.setConsumer(dao.get(ConsumerUserEntity.class, userItemVO.getId()));
        BeanUtils.copyProperties(houseVO, contactPersonInfoEntity);
        dao.save(contactPersonInfoEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑房产信息
     */
    @RequestMapping(value = "editHouseInfo/{token}/{id}", method = RequestMethod.POST)
    @ApiOperation("编辑房产信息")
    public NetVO editHouseInfo(@PathVariable String token, @PathVariable long id, @Validated HouseVO houseVO) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        HouseEntity contactPersonInfo = dao.get(HouseEntity.class, id);
        if (contactPersonInfo == null) {
            return new ErrorFessionVO(RtnResultEnum.E030001);
        }
        BeanUtils.copyProperties(houseVO, contactPersonInfo);
        dao.modify(contactPersonInfo);
        return new SuccFessionVO();
    }

    /**
     * 获取房产信息
     */
    @RequestMapping(value = "getHouseInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("获取房产信息")
    public NetVO getHouseInfo(@PathVariable String token) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        List<HouseVO> houseVOS = consumerUserService.getHouseByUserId(userItemVO.getId());
        return new ListResponseVO(houseVOS);
    }

    @RequestMapping(value = "uploadPersonAnnex/{token}", method = RequestMethod.POST)
    @ApiOperation("上传附件信息")
    public NetVO uploadPersonAnnex(@RequestParam MultipartFile file, @PathVariable String token, @RequestParam AnnexTypeEnum annexTypeEnum) throws
            BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        if (file.isEmpty()) {
            return new ErrorFessionVO();
        }
        String filePath = path + userItemVO.getPersonId() + "/";
        String fileName = FileUtil.saveFile(file, filePath, annexTypeEnum);
        AnnexEntity annexEntity = new AnnexEntity();
        annexEntity.setUser(dao.get(ConsumerUserEntity.class, userItemVO.getId()));
        annexEntity.setAnnexTypeEnum(annexTypeEnum);
        annexEntity.setUrl(getPath + userItemVO.getPersonId() + "/" + fileName);
        annexEntity.setAnnexCode(DBUtil.getAnnexCode(userItemVO.getPersonId() + "", annexTypeEnum));
        dao.save(annexEntity);
        return new ResponseVO(new AnnexVO(annexTypeEnum, filePath + fileName));
    }
}
