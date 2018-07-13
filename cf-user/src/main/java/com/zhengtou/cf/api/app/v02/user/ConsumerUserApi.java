package com.zhengtou.cf.api.app.v02.user;

import com.zhengtou.cf.api.third.SmsApi;
import com.zhengtou.cf.common.annotation.validator.MsgCode;
import com.zhengtou.cf.common.annotation.validator.PassWord;
import com.zhengtou.cf.common.annotation.validator.Phone;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.AnnexTypeEnum;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ListResponseVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.ResponseVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.common.utils.TimeUtil;
import com.zhengtou.cf.enums.RedisKeyPrefixEnum;
import com.zhengtou.cf.jpush.pojo.vo.MsgVO;
import com.zhengtou.cf.jpush.service.MsgService;
import com.zhengtou.cf.trade.service.AnnexService;
import com.zhengtou.cf.user.controller.reciveVO.person.*;
import com.zhengtou.cf.user.pojo.entity.AnnexEntity;
import com.zhengtou.cf.user.pojo.entity.ConsumerUserEntity;
import com.zhengtou.cf.user.pojo.entity.UserEntity;
import com.zhengtou.cf.user.pojo.entity.enums.UserInfoCompleteEnum;
import com.zhengtou.cf.user.pojo.entity.enums.UserSourceTypeEnum;
import com.zhengtou.cf.user.pojo.entity.person.*;
import com.zhengtou.cf.user.pojo.vo.AnnexVO;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserInfo;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import com.zhengtou.cf.user.pojo.vo.person.*;
import com.zhengtou.cf.user.service.ConsumerUserService;
import com.zhengtou.cf.util.BeanNullUtil;
import com.zhengtou.cf.util.CommonUtil;
import com.zhengtou.cf.util.FileUtil;
import com.zhengtou.cf.util.VerifyCodeUtil;
import io.swagger.annotations.*;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@RestController("consumerUser_v0.2")
@RequestMapping("api/v0.2/consumer")
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
    SmsApi smsApi;
    @Value("${person.annex.path}")
    private String path;
    @Value("${show.person.annex.path}")
    private String getPath;
    @Autowired
    MsgService msgService;
    @Autowired
    AnnexService annexService;

    /**
     * 用户注册
     */
    @RequestMapping(value = "register/{phone}/{passWord}/{msgCode}", method = RequestMethod.POST)
    @ApiOperation("用户注册")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "用户注册")})
    public NetVO register(@PathVariable @Phone @ApiParam(value = "手机号", name = "phone") String phone, @PathVariable @PassWord @ApiParam(value =
            "登陆密码", name = "passWord") String passWord, @PathVariable @MsgCode @ApiParam(value = "短信验证码", name = "msgCode") String msgCode) throws
            BaseException {
        String hasCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.REG_SMS_CODE.getPrefix() + phone);
        CommonUtil.checkCode(hasCode, msgCode);
        consumerUserService.saveConsumerUser(phone, passWord, UserSourceTypeEnum.APP, null);
        return new SuccFessionVO();
    }

    /**
     * 用户密码登录
     */
    @RequestMapping(value = "login/{phone}/{password}", method = RequestMethod.GET)
    @ApiOperation("用户密码登录")
    @ApiResponses({@ApiResponse(code = 200, response = ConsumerUserVO.class, message = "用户密码登录")})
    public NetVO login(@PathVariable @Phone @ApiParam(value = "手机号", name = "phone") String phone, @PathVariable @PassWord @ApiParam(value =
            "登陆密码", name = "passWord") String password) throws BaseException {
        ConsumerUserVO user = consumerUserService.getConsumerUserByLoginNameAndPwd(phone, password);
        if (user == null) {
            throw new BaseException(RtnResultEnum.E000000);
        }
        AnnexVO annexVO = annexService.getConsumerUserHeadAnnex(user.getId());
        user.setHeadPath(annexVO == null ? "" : annexVO.getUrl());
        ConsumerUserEntity consumerUserEntity = dao.get(ConsumerUserEntity.class, user.getId());
        consumerUserEntity.setLastLoginTime(System.currentTimeMillis());
        dao.modify(consumerUserEntity);
        String token = DBUtil.generateID();
        user.setToken(token);
        user.setLastLoginTime(TimeUtil.timeToString(consumerUserEntity.getLastLoginTime()));
        user.setPersonId(consumerUserEntity.getPersonal().getId());
        myRedisComponent.setDay(RedisKeyPrefixEnum.LOGIN_TOKEN.getPrefix() + phone, token, 1L);
        myRedisComponent.setDay(token, user, 1L);
        return new ResponseVO(user);
    }

    /**
     * 获取用户登陆信息
     */
    @RequestMapping(value = "queryConsumerUserByToken/{token}", method = RequestMethod.GET)
    @ApiOperation("获取用户登陆信息")
    @ApiResponses({@ApiResponse(code = 200, response = ConsumerUserVO.class, message = "获取用户登陆信息")})
    public NetVO queryConsumerUserByToken(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        ConsumerUserEntity consumerUserEntity = dao.get(ConsumerUserEntity.class, userItemVO.getId());
        PersonalEntity personalEntity = consumerUserEntity.getPersonal();
        List<EducationalVO> educationalVOS = consumerUserService.getEducationalByPersonalId(personalEntity.getId());
        ConsumerUserInfo consumerUserInfo = new ConsumerUserInfo(consumerUserEntity.getId(), consumerUserEntity.getUserNo(), consumerUserEntity
                .getUserType(), consumerUserEntity.getLastLoginTime(), consumerUserEntity.getNickName(), personalEntity.getName(),
                consumerUserEntity.getPhone(), consumerUserEntity.getIsUserInfoComplete(), consumerUserEntity.isHasTradePwd(), consumerUserEntity
                .getSocialTypeEnum(), consumerUserEntity.getCanUseAmt(), consumerUserEntity.getCreditAmt(), consumerUserEntity.getAmtCreateTime(),
                educationalVOS.isEmpty() ? null : educationalVOS.get(0).getQualification(), personalEntity.getResidenceAddress(), personalEntity
                .getMaritalStatus(), personalEntity.getAge());
        AnnexVO annexVO = annexService.getConsumerUserHeadAnnex(userItemVO.getId());
        consumerUserInfo.setHeadPath(annexVO == null ? "" : annexVO.getUrl());
        return new ResponseVO(consumerUserInfo);
    }

    @RequestMapping(value = "updatePwdSms/{phone}/{msgCode}/{password}", method = RequestMethod.PUT)
    @ApiOperation("忘记密码使用验证码修改密码")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "忘记密码使用验证码修改密码")})
    public NetVO updatePwdSms(@PathVariable @Phone @ApiParam(value = "登陆手机号", name = "phone") String phone, @PathVariable @PassWord @ApiParam(value
            = "登陆密码", name = "password") String password, @PathVariable @MsgCode @ApiParam(value = "短信验证码", name = "msgCode") String msgCode)
            throws BaseException {
        //验证验证码
        String hasCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.PWD_SMS_CODE.getPrefix() + phone);
        CommonUtil.checkCode(hasCode, msgCode);
        UserEntity user = consumerUserService.getUserByPhone(phone);
        if (user == null) {
            throw new BaseException(RtnResultEnum.E000000);
        } else {
            user.setPassword(DBUtil.generatePassword(password));
            dao.saveOrModify(user);
            return new SuccFessionVO();
        }
    }

    /**
     * 新增用户基础信息
     */
    @RequestMapping(value = "addPersonInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("新增用户基础信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "新增用户基础信息")})
    public NetVO addPersonInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @Validated PersonalReciveVO personalVO)
            throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        PersonalEntity personalEntity = new PersonalEntity();
        BeanUtils.copyProperties(personalVO, personalEntity);
        personalEntity.setCellphone(userItemVO.getPhone());
        dao.save(personalEntity);

        ConsumerUserEntity consumerUserEntity = dao.get(ConsumerUserEntity.class, userItemVO.getId());
        consumerUserEntity.setIsUserInfoComplete(UserInfoCompleteEnum.已完善);
        consumerUserEntity.setPersonal(personalEntity);
        dao.modify(consumerUserEntity);
        userItemVO.setPersonId(personalEntity.getId());
        userItemVO.setUserInfoCompleteEnum(UserInfoCompleteEnum.已完善);
        myRedisComponent.setDay(token, userItemVO, 1l);
        return new SuccFessionVO();
    }

    /**
     * 编辑用户基础信息
     */
    @RequestMapping(value = "editPersonInfo/{token}/{id}", method = RequestMethod.PUT)
    @ApiOperation("编辑用户基础信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "新增用户基础信息")})
    public NetVO editPersonInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "用户信息id",
            name = "id") long id, @Validated PersonalReciveVO personalVO) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        PersonalEntity nullPersonal = (PersonalEntity) myRedisComponent.get("nullPerson");
        if (userItemVO.getPersonId() != id || userItemVO.getPersonId() == nullPersonal.getId()) {
            throw new BaseException(RtnResultEnum.U000000);
        }
        PersonalEntity personalEntity = dao.get(PersonalEntity.class, id);
        BeanUtils.copyProperties(personalVO, personalEntity, BeanNullUtil.getNullPropertyNames(personalVO));
        if (StringUtils.isNotBlank(personalVO.getName())) {
            userItemVO.setRealname(personalVO.getName());
            myRedisComponent.setDay(token, userItemVO, 1l);
        }
        dao.modify(personalEntity);
        return new SuccFessionVO();
    }

    /**
     * 获取用户基础信息
     */
    @RequestMapping(value = "getPersonInfo/{token}", method = RequestMethod.GET)
    @ApiOperation("获取用户基础信息")
    @ApiResponses({@ApiResponse(code = 200, response = PersonalVO.class, message = "获取用户基础信息")})
    public NetVO getPersonInfo(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        PersonalVO personalVO = consumerUserService.getPersonalById(userItemVO.getPersonId());
        return new ResponseVO(personalVO);
    }

    /**
     * 新增教育信息
     */
    @RequestMapping(value = "addEducationInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("新增用户教育信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "新增用户教育信息")})
    public NetVO modifyEducationInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @Validated EducationalReciveVO
            educationalVO) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
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
    @RequestMapping(value = "editEducationInfo/{token}/{id}", method = RequestMethod.PUT)
    @ApiOperation("编辑用户教育信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "新增用户教育信息")})
    public NetVO editEducationInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "教育信息id",
            name = "id") long id, @Validated EducationalReciveVO educationalVO) throws
            BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        EducationalEntity educationalInfoEntity = dao.get(EducationalEntity.class, id);
        if (educationalInfoEntity == null) {
            throw new BaseException(RtnResultEnum.E050001);
        }
        BeanUtils.copyProperties(educationalVO, educationalInfoEntity, BeanNullUtil.getNullPropertyNames(educationalVO));
        dao.modify(educationalInfoEntity);
        return new SuccFessionVO();
    }

    /**
     * 获取教育信息
     */
    @RequestMapping(value = "getEducationInfo/{token}", method = RequestMethod.GET)
    @ApiOperation("获取用户教育信息")
    @ApiResponses({@ApiResponse(code = 200, response = EducationalVO.class, message = "获取用户教育信息")})
    public NetVO getEducationInfo(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        List<EducationalVO> educationalVOS = consumerUserService.getEducationalByPersonalId(userItemVO.getPersonId());
        return new ListResponseVO(educationalVOS);
    }

    /**
     * 新增工作信息
     */
    @RequestMapping(value = "addEmpInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("新增用户工作信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "新增工作信息")})
    public NetVO addEmpInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @Validated EmpReciveVO empVO) throws
            BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
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
    @RequestMapping(value = "editEmpInfo/{token}/{id}", method = RequestMethod.PUT)
    @ApiOperation("编辑用户工作信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "编辑用户工作信息")})
    public NetVO editEmpInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "工作信息id", name =
            "id") long id, @Validated EmpReciveVO empVO) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        EmpEntity educationalInfoEntity = dao.get(EmpEntity.class, id);
        if (educationalInfoEntity == null) {
            throw new BaseException(RtnResultEnum.E0000xx);
        }
        BeanUtils.copyProperties(empVO, educationalInfoEntity, BeanNullUtil.getNullPropertyNames(empVO));
        dao.modify(educationalInfoEntity);
        return new SuccFessionVO();
    }

    /**
     * 获取工作信息
     */
    @RequestMapping(value = "getEmpInfo/{token}", method = RequestMethod.GET)
    @ApiOperation("获取用户工作信息")
    @ApiResponses({@ApiResponse(code = 200, response = EmpVO.class, message = "获取用户工作信息")})
    public NetVO getEmpInfo(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        List<EmpVO> empVOS = consumerUserService.getEmpByPersonalId(userItemVO.getPersonId());
        return new ListResponseVO(empVOS);
    }

    /**
     * 新增用户收入信息
     */
    @RequestMapping(value = "addIncomeInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("新增用户收入信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "新增用户收入信息")})
    public NetVO addIncomeInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @Validated IncomeReciveVO incomeVO) throws
            BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        IncomeEntity incomeInfoEntity = new IncomeEntity();
        BeanUtils.copyProperties(incomeVO, incomeInfoEntity);
        incomeInfoEntity.setPersonal(dao.get(PersonalEntity.class, userItemVO.getPersonId()));
        dao.save(incomeInfoEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑用户收入信息
     */
    @RequestMapping(value = "editIncomeInfo/{token}/{id}", method = RequestMethod.PUT)
    @ApiOperation("编辑用户收入信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "编辑用户收入信息")})
    public NetVO editIncomeInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "收入信息id",
            name = "id") long id, @Validated IncomeReciveVO incomeVO) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        IncomeEntity incomeEntity = dao.get(IncomeEntity.class, id);
        BeanUtils.copyProperties(incomeVO, incomeEntity);
        dao.modify(incomeEntity);
        return new SuccFessionVO();
    }

    /**
     * 获取用户收入信息
     */
    @RequestMapping(value = "getIncomeInfo/{token}", method = RequestMethod.GET)
    @ApiOperation("获取用户收入信息")
    @ApiResponses({@ApiResponse(code = 200, response = IncomeVO.class, message = "获取用户收入信息")})
    public NetVO getIncomeInfo(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        IncomeVO incomeVO = consumerUserService.getIncomeByPersonalId(userItemVO.getPersonId());
        return new ResponseVO(incomeVO);
    }

    /**
     * 添加联系人信息
     */
    @RequestMapping(value = "addContactPersonInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("添加联系人信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "添加联系人信息")})
    public NetVO addContactPersonInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @Validated ContactReciveVO contactVO)
            throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        PersonalEntity personalEntity = dao.get(PersonalEntity.class, userItemVO.getPersonId());
        ContactEntity contactEntity = new ContactEntity();
        BeanUtils.copyProperties(contactVO, contactEntity, BeanNullUtil.getNullPropertyNames(contactVO));
        contactEntity.setPersonal(personalEntity);
        dao.save(contactEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑联系人信息
     */
    @RequestMapping(value = "editContactPersonInfo/{token}/{id}", method = RequestMethod.PUT)
    @ApiOperation("编辑联系人信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "添加联系人信息")})
    public NetVO editContactPersonInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value =
            "联系人id", name = "id") long id, @Validated ContactReciveVO contactVO) throws
            BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        ContactEntity contactEntity = dao.get(ContactEntity.class, id);
        if (contactEntity == null) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        BeanUtils.copyProperties(contactVO, contactEntity, BeanNullUtil.getNullPropertyNames(contactVO));
        dao.modify(contactEntity);
        return new SuccFessionVO();
    }

    /**
     * 获取联系人信息
     */
    @RequestMapping(value = "getContactPersonInfo/{token}", method = RequestMethod.GET)
    @ApiOperation("获取联系人信息")
    @ApiResponses({@ApiResponse(code = 200, response = ContactVO.class, message = "获取联系人信息")})
    public NetVO getContactPersonInfo(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        List<ContactVO> contactVOS = consumerUserService.getContactByPersonalId(userItemVO.getPersonId());
        return new ListResponseVO(contactVOS);
    }

    /**
     * 新增用户其他信息
     */
    @RequestMapping(value = "addOtherInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("新增用户其他信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "新增用户其他信息")})
    public NetVO addOtherInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @Validated OtherReciveVO otherVO) throws
            BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        OtherEntity otherEntity = new OtherEntity();
        BeanUtils.copyProperties(otherVO, otherEntity);
        otherEntity.setPersonal(dao.get(PersonalEntity.class, userItemVO.getPersonId()));
        dao.save(otherEntity);
        return new SuccFessionVO();
    }

    /**
     * 修改用户其他信息
     */
    @RequestMapping(value = "editOtherInfo/{token}/{id}", method = RequestMethod.PUT)
    @ApiOperation("编辑用户其他信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "编辑用户其他信息")})
    public NetVO editOtherInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "其他信息id", name
            = "id") long id, @Validated OtherReciveVO otherVO) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        OtherEntity personalInfo = dao.get(OtherEntity.class, id);
        BeanUtils.copyProperties(otherVO, personalInfo, BeanNullUtil.getNullPropertyNames(otherVO));
        dao.modify(personalInfo);
        return new SuccFessionVO();
    }

    /**
     * 获取用户其他信息
     */
    @RequestMapping(value = "getOtherInfo/{token}", method = RequestMethod.GET)
    @ApiOperation("获取用户其他信息")
    @ApiResponses({@ApiResponse(code = 200, response = OtherVO.class, message = "获取用户其他信息")})
    public NetVO getOtherInfo(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (userItemVO.getPersonId() == null) {
            throw new BaseException(RtnResultEnum.E000005);
        }
        List<OtherVO> otherVOS = consumerUserService.getOtherByPersonalId(userItemVO.getPersonId());
        return new ListResponseVO(otherVOS);
    }

    /**
     * 获取用户消息
     */
    @RequestMapping(value = "getUserMsg/{token}/{page}/{size}", method = RequestMethod.GET)
    @ApiOperation("获取用户消息")
    @ApiResponses({@ApiResponse(code = 200, response = MsgVO.class, message = "获取用户消息")})
    public NetVO getUserMsg(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "页数", name =
            "page") Integer page, @PathVariable @ApiParam(value = "每页条数", name = "size") Integer size) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<MsgVO> msgVOS = msgService.findPersonMsgByUserNo(userItemVO.getUserNo(), page, size);
        Long count = msgService.countPersonMsgByUserNo(userItemVO.getUserNo());
        return new ListResponseVO(msgVOS, count);
    }

    /**
     * 获取用户消息
     */
    @RequestMapping(value = "getMsgByThirdMsgId/{thirdId}", method = RequestMethod.GET)
    @ApiOperation("获取用户消息")
    @ApiResponses({@ApiResponse(code = 200, response = MsgVO.class, message = "获取用户消息")})
    public NetVO getUserMsg(@PathVariable @ApiParam(value = "推送平台消息id", name = "thirdId") String thirdId) throws BaseException {
        MsgVO msgVOS = msgService.getMsgByThirdMsgId(thirdId);
        return new ResponseVO(msgVOS);
    }

    /**
     * 添加设备信息
     */
    @RequestMapping(value = "addDeviceInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("添加设备信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "添加设备信息")})
    public NetVO addDeviceInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @Validated DeviceReciveVO deviceReciveVO)
            throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setConsumer(dao.get(ConsumerUserEntity.class, userItemVO.getId()));
        BeanUtils.copyProperties(deviceReciveVO, deviceEntity);
        dao.save(deviceEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑设备信息
     */
    @RequestMapping(value = "editDeviceInfo/{token}/{id}", method = RequestMethod.PUT)
    @ApiOperation("编辑设备信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "编辑设备信息")})
    public NetVO editDeviceInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "设备id",
            name = "id") long id, @Validated DeviceReciveVO deviceReciveVO) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        DeviceEntity contactPersonInfo = dao.get(DeviceEntity.class, id);
        if (contactPersonInfo == null) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        BeanUtils.copyProperties(deviceReciveVO, contactPersonInfo, BeanNullUtil.getNullPropertyNames(deviceReciveVO));
        dao.modify(contactPersonInfo);
        return new SuccFessionVO();
    }

    /**
     * 获取设备信息
     */
    @RequestMapping(value = "getDeviceInfo/{token}", method = RequestMethod.GET)
    @ApiOperation("获取设备信息")
    @ApiResponses({@ApiResponse(code = 200, response = DeviceVO.class, message = "获取设备信息")})
    public NetVO getDeviceInfo(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<DeviceVO> deviceVOS = consumerUserService.getDeviceByUserId(userItemVO.getId());
        return new ListResponseVO(deviceVOS);
    }

    /**
     * 添加车辆信息
     */
    @RequestMapping(value = "addCarInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("添加车辆信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "添加车辆信息")})
    public NetVO addCarInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @Validated CarReciveVO carReciveVO) throws
            BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        CarEntity carEntity = new CarEntity();
        carEntity.setConsumer(dao.get(ConsumerUserEntity.class, userItemVO.getId()));
        BeanUtils.copyProperties(carReciveVO, carEntity);
        dao.save(carEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑车辆信息
     */
    @RequestMapping(value = "editCarInfo/{token}/{id}", method = RequestMethod.PUT)
    @ApiOperation("编辑车辆信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "编辑车辆信息")})
    public NetVO editCarInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "车辆id", name =
            "id") long id, @Validated CarReciveVO carReciveVO) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        CarEntity contactPersonInfo = dao.get(CarEntity.class, id);
        if (contactPersonInfo == null) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        BeanUtils.copyProperties(carReciveVO, contactPersonInfo, BeanNullUtil.getNullPropertyNames(carReciveVO));
        dao.modify(contactPersonInfo);
        return new SuccFessionVO();
    }

    /**
     * 获取车辆信息
     */
    @RequestMapping(value = "getCarInfo/{token}", method = RequestMethod.GET)
    @ApiOperation("获取车辆信息")
    @ApiResponses({@ApiResponse(code = 200, response = CarVO.class, message = "获取车辆信息")})
    public NetVO getCarInfo(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<CarVO> carVOS = consumerUserService.getCarByUserId(userItemVO.getId());
        return new ListResponseVO(carVOS);
    }

    /**
     * 添加房产信息
     */
    @RequestMapping(value = "addHouseInfo/{token}", method = RequestMethod.POST)
    @ApiOperation("添加房产信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "添加房产信息")})
    public NetVO addHouseInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @Validated HouseReceiveVO houseReceiveVO)
            throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        HouseEntity contactPersonInfoEntity = new HouseEntity();
        contactPersonInfoEntity.setConsumer(dao.get(ConsumerUserEntity.class, userItemVO.getId()));
        BeanUtils.copyProperties(houseReceiveVO, contactPersonInfoEntity);
        dao.save(contactPersonInfoEntity);
        return new SuccFessionVO();
    }

    /**
     * 编辑房产信息
     */
    @RequestMapping(value = "editHouseInfo/{token}/{id}", method = RequestMethod.PUT)
    @ApiOperation("编辑房产信息")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "编辑房产信息")})
    public NetVO editHouseInfo(@PathVariable @ApiParam(value = "token", name = "token") String token, @PathVariable @ApiParam(value = "房产id", name
            = "id") long id, @Validated HouseReceiveVO houseReceiveVO) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        HouseEntity contactPersonInfo = dao.get(HouseEntity.class, id);
        if (contactPersonInfo == null) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        BeanUtils.copyProperties(houseReceiveVO, contactPersonInfo, BeanNullUtil.getNullPropertyNames(houseReceiveVO));
        dao.modify(contactPersonInfo);
        return new SuccFessionVO();
    }

    /**
     * 获取房产信息
     */
    @RequestMapping(value = "getHouseInfo/{token}", method = RequestMethod.GET)
    @ApiOperation("获取房产信息")
    @ApiResponses({@ApiResponse(code = 200, response = HouseVO.class, message = "获取房产信息")})
    public NetVO getHouseInfo(@PathVariable @ApiParam(value = "token", name = "token") String token) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<HouseVO> houseVOS = consumerUserService.getHouseByUserId(userItemVO.getId());
        return new ListResponseVO(houseVOS);
    }

    @RequestMapping(value = "uploadPersonAnnex/{token}", method = RequestMethod.POST)
    @ApiOperation("上传附件信息")
    @ApiResponses({@ApiResponse(code = 200, response = AnnexVO.class, message = "上传附件信息")})
    public NetVO uploadPersonAnnex(@RequestParam @ApiParam(value = "文件", name = "file") MultipartFile file, @PathVariable @ApiParam(value =
            "token", name = "token") String token, @RequestParam @ApiParam(value = "附件类型", name = "annexTypeEnum") AnnexTypeEnum annexTypeEnum)
            throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (file.isEmpty()) {
            throw new BaseException(RtnResultEnum.U000000);
        }
        String filePath = path + userItemVO.getUserNo() + "/";
        String fileName = FileUtil.saveFile(file, filePath, annexTypeEnum);
        AnnexEntity annexEntity = new AnnexEntity();
        annexEntity.setUser(dao.get(ConsumerUserEntity.class, userItemVO.getId()));
        annexEntity.setAnnexTypeEnum(annexTypeEnum);
        annexEntity.setUrl(getPath + userItemVO.getUserNo() + "/" + fileName);
        annexEntity.setAnnexCode(DBUtil.getAnnexCode(userItemVO.getUserNo() + "", annexTypeEnum));
        dao.save(annexEntity);
        return new ResponseVO(new AnnexVO(annexTypeEnum, filePath + fileName));
    }

    @RequestMapping(value = "getPersonAnnex/{token}", method = RequestMethod.GET)
    @ApiOperation("查看附件信息")
    @ApiResponses({@ApiResponse(code = 200, response = AnnexVO.class, message = "查看附件信息")})
    public NetVO getPersonAnnex(@PathVariable @ApiParam(value = "token", name = "token") String token, @ApiParam(value = "按类型检索", name =
            "annexTypeEnum") AnnexTypeEnum annexTypeEnum) throws BaseException {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        List<AnnexVO> annexVOS = consumerUserService.findAnnexVOList(userItemVO.getId(), annexTypeEnum);
        return new ListResponseVO(annexVOS);
    }

    /**
     * 获取注册短信
     */
    @RequestMapping(value = "getRegSmsCode/{phone}/{pngToken}/{pngCode}", method = RequestMethod.GET)
    @ApiOperation("获取注册短信")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "获取注册短信")})
    public NetVO getRegSmsCode(@PathVariable @Phone @ApiParam(value = "手机号", name = "phone") String phone, @PathVariable @ApiParam(value = "图片验证码",
            name = "pngCode") String pngCode, @PathVariable @ApiParam(value = "获取图片验证码时得pngToken", name = "pngToken") String pngToken) throws BaseException {
        String hasCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.PNG_TOKEN.getPrefix() + pngToken);
        CommonUtil.checkCode(hasCode, pngCode);
        String regCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.REG_SMS_CODE.getPrefix() + phone);
        if (regCode != null) {
            throw new BaseException(RtnResultEnum.E010001);
        }
        String smsCode = RandomStringUtils.randomNumeric(6);
        try {
            smsApi.sendSms(phone, smsCode);
        } catch (BaseException e) {
            return new SuccFessionVO(RtnResultEnum.E010005);
        }
        myRedisComponent.setSeconds(RedisKeyPrefixEnum.REG_SMS_CODE.getPrefix() + phone, smsCode, 60L);
        return new SuccFessionVO();
    }

    /**
     * 获取改密短信
     */
    @RequestMapping(value = "getPwdSmsCode/{phone}/{pngToken}/{pngCode}", method = RequestMethod.GET)
    @ApiOperation("获取改密短信")
    @ApiResponses({@ApiResponse(code = 200, response = SuccFessionVO.class, message = "获取改密短信")})
    public NetVO getPwdSmsCode(@PathVariable @Phone @ApiParam(value = "手机号", name = "phone") String phone, @PathVariable @ApiParam(value = "图片验证码",
            name = "pngCode") String pngCode, @PathVariable @ApiParam(value = "获取图片验证码时得pngToken", name = "pngToken") String pngToken) throws BaseException {
        String hasCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.PNG_TOKEN.getPrefix() + pngToken);
        CommonUtil.checkCode(hasCode, pngCode);
        String regCode = (String) myRedisComponent.get(RedisKeyPrefixEnum.PWD_SMS_CODE.getPrefix() + phone);
        if (regCode != null) {
            throw new BaseException(RtnResultEnum.E010001);
        }
        String smsCode = RandomStringUtils.randomNumeric(6);
        try {
            smsApi.sendSms(phone, smsCode);
        } catch (BaseException e) {
            return new SuccFessionVO(RtnResultEnum.E010005);
        }
        myRedisComponent.setSeconds(RedisKeyPrefixEnum.PWD_SMS_CODE.getPrefix() + phone, smsCode, 60L);
        return new SuccFessionVO();
    }

    /**
     * 获取图片验证码
     */
    @RequestMapping(value = "getPngCode/{pngToken}", method = RequestMethod.GET)
    @ApiOperation("获取图片验证码")
    @ApiResponses({@ApiResponse(code = 200, response = OutputStream.class, message = "图片流")})
    public void getPngCode(@PathVariable @ApiParam(value = "pngToken", name = "pngToken") String pngToken, HttpServletResponse response) {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        myRedisComponent.setSeconds(RedisKeyPrefixEnum.PNG_TOKEN.getPrefix() + pngToken, verifyCode, 60L);
        int w = 200, h = 80;
        try {
            VerifyCodeUtil.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
