package com.harmony.devops.user.user.service;

import com.harmony.devops.user.operator.pojo.entity.OrganizationEntity;
import com.harmony.devops.user.user.controller.reciveVO.QueryConsumerUserReciveVO;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;
import com.harmony.devops.user.user.pojo.entity.UserEntity;
import com.harmony.devops.user.user.pojo.entity.enums.UserSourceTypeEnum;
import com.harmony.devops.user.user.pojo.vo.AnnexVO;
import com.harmony.devops.user.user.pojo.vo.ConsumerUserVO;
import com.harmony.devops.user.user.pojo.vo.person.*;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 消费用户服务
 */
public interface ConsumerUserService {
    /**
     * 客户用户注册
     */
    void saveConsumerUser(String phone, String password, UserSourceTypeEnum userSourceTypeEnum, OrganizationEntity org);
    /**
     * 客户用户登陆
     */
    ConsumerUserVO getConsumerUserByLoginNameAndPwd(String loginName, String passwd);

    /**
     *设备信息
     */
    List<DeviceVO> getDeviceByUserId(long userId);
    /**
     * 房产信息
     */
    List<HouseVO> getHouseByUserId(long userId);
    /**
     * 车辆信息
     */
    List<CarVO> getCarByUserId(long userId);
    /*****************************************************用户资料***************************************************/
    /**
     * 个人基础信息
     */
    PersonalVO getPersonalById(long id);
    PersonalVO getPersonalByUserId(long userId);
    /**
     * 联系人信息
     */
    List<ContactVO> getContactByPersonalId(long personalId);
    /**
     *教育信息
     */
    List<EducationalVO> getEducationalByPersonalId(long personalId);
    /**
     * 工作信息
     */
    List<EmpVO> getEmpByPersonalId(long personalId);
    /**
     * 收入信息
     */
    IncomeVO getIncomeByPersonalId(long personalId);
    /**
     * 其他信息
     */
    List<OtherVO> getOtherByPersonalId(long personalId);

    /**
     * 根据手机号获取用户
     */
    UserEntity getUserByPhone(String phone);
    /**
     * 用户列表
     */
    List<ConsumerUserVO> getConsumerUserList(QueryConsumerUserReciveVO consumerUserReciveVO, Integer page, Integer size);
    /**
     * 用户列表
     */
    Long countConsumerUserList(QueryConsumerUserReciveVO consumerUserReciveVO);

    /**
     * 客户用户信息
     */
    ConsumerUserVO getConsumerUserVOByPhone(String phone);

    /**
     * 更改用户可用额度
     */
    void changeConsumerCanUseAmt(long userId,long amt);

    /**
     * 根据用户no拿用户
     */
    ConsumerUserEntity getConsumerUserByUserNo(String userNo);

}
