package com.harmony.devops.user.user.service.impl;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.harmony.devops.user.operator.pojo.entity.OrganizationEntity;
import com.harmony.devops.user.user.controller.reciveVO.QueryConsumerUserReciveVO;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;
import com.harmony.devops.user.user.pojo.entity.UserEntity;
import com.harmony.devops.user.user.pojo.entity.enums.AnnexStatusEnum;
import com.harmony.devops.user.user.pojo.entity.enums.UserSourceTypeEnum;
import com.harmony.devops.user.user.pojo.entity.enums.UserTypeEnum;
import com.harmony.devops.user.user.pojo.entity.person.PersonalEntity;
import com.harmony.devops.user.user.pojo.vo.AnnexVO;
import com.harmony.devops.user.user.pojo.vo.ConsumerUserVO;
import com.harmony.devops.user.user.pojo.vo.person.*;
import com.harmony.devops.user.user.service.ConsumerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class ConsumerUserServiceImpl implements ConsumerUserService {
    @Autowired
    BaseDao dao;
    @Autowired
    MyRedisComponent myRedisComponent;

    @Override
    public void saveConsumerUser(String phone, String password,UserSourceTypeEnum userSourceTypeEnum,OrganizationEntity org) {
        ConsumerUserEntity consumerUserEntity = new ConsumerUserEntity();
        consumerUserEntity.setPassword(DBUtil.generatePassword(password));
        consumerUserEntity.setLoginName(phone);
        consumerUserEntity.setPhone(phone);
        consumerUserEntity.setUserType(UserTypeEnum.消费用户);
        consumerUserEntity.setPersonal((PersonalEntity) myRedisComponent.get("nullPerson"));
        consumerUserEntity.setUserSourceTypeEnum(userSourceTypeEnum);
        consumerUserEntity.setOrganiza(org==null?(OrganizationEntity) myRedisComponent.get("ztOrganiza"):org);
        dao.save(consumerUserEntity);
    }

    @Override
    public ConsumerUserVO getConsumerUserByLoginNameAndPwd(String loginName, String passwd) {
        return dao.get("select new com.harmony.devops.user.user.pojo.vo.ConsumerUserVO(u.id,u.userNo,u.userType,u.lastLoginTime,u.personal.name,u.phone,u" +
                ".isUserInfoComplete,u.socialTypeEnum,u.canUseAmt,u.creditAmt,u.amtCreateTime) from ConsumerUserEntity u where u.loginName=?0 and  " +
                "u.password=?1", new Object[]{loginName, DBUtil.generatePassword(passwd)});
    }

    @Override
    public List<DeviceVO> getDeviceByUserId(long userId) {
        return dao.find("select new com.harmony.devops.user.user.pojo.vo.person.DeviceVO(p.id,p.carrierType,p.deviceType,p.deviceIp,p.location,p" +
                ".iphoeBreakout) from DeviceEntity p where p.isDeleted=false and p.consumer.id=?0", new Object[]{userId});
    }

    @Override
    public List<HouseVO> getHouseByUserId(long userId) {
        return dao.find("select new com.harmony.devops.user.user.pojo.vo.person.HouseVO(c.id,c.propertyNo,c.address,c.propertyType,c.plannedUse,c.area,c" +
                ".accessedValue,c.availLoanAmount) from HouseEntity c where c.isDeleted=false and c.consumer.id=?0", new Object[]{userId});
    }

    @Override
    public List<CarVO> getCarByUserId(long userId) {
        return dao.find("select new com.harmony.devops.user.user.pojo.vo.person.CarVO(c.id,c.vehicleCompany,c.vehicleBrand,c.vehicleModel,c.vehiclePrice,c" +
                ".firstRegister) from CarEntity c where c.isDeleted=false and c.consumer.id=?0", new Object[]{userId});
    }

    @Override
    public PersonalVO getPersonalById(long id) {
        return dao.get("select new com.harmony.devops.user.user.pojo.vo.person.PersonalVO(p.id,p.name,p.gender,p.age,p.idType,p.idNo,p.cellphone,p" +
                ".houseCondition) from PersonalEntity p where p.isDeleted=false and p.id=?0 ", new Object[]{id});
    }

    @Override
    public PersonalVO getPersonalByUserId(long userId) {
        return dao.get("select new com.harmony.devops.user.user.pojo.vo.person.PersonalVO(p.id,p.name,p.gender,p.age,p.idType,p.idNo,p.cellphone,p" +
                ".houseCondition) from PersonalEntity p,ConsumerUserEntity c where p.isDeleted=false and c.id=?0 and c.personal=p", new
                Object[]{userId});
    }

    @Override
    public List<ContactVO> getContactByPersonalId(long personalId) {
        return dao.find("select new com.harmony.devops.user.user.pojo.vo.person.ContactVO(c.id,c.contactName,c.contactRelation,c.contactMobile) from " +
                "ContactEntity c where c.isDeleted=false and c.personal.id=?0", new Object[]{personalId});
    }

    @Override
    public List<EducationalVO> getEducationalByPersonalId(long personalId) {
        return dao.find("select new com.harmony.devops.user.user.pojo.vo.person.EducationalVO(p.id,p.qualification,p.schoolLength,p.schoolEducationType,p" +
                        ".schoolGraduated,p.schoolStartDate,p.schoolGraduationTime) from EducationalEntity p where p.isDeleted=false and p.personal" +
                        ".id=?0",
                new Object[]{personalId});
    }

    @Override
    public List<EmpVO> getEmpByPersonalId(long personalId) {
        return dao.find("select new com.harmony.devops.user.user.pojo.vo.person.EmpVO(p.id,p.empStandFrom,p.empYears,p.empUnitName,p.empWorkStandFrom,p" +
                ".empDepartment,p.empPost,p.empType,p.empStructure) from EmpEntity p where p.isDeleted=false and p.personal.id=?0", new
                Object[]{personalId});
    }

    @Override
    public IncomeVO getIncomeByPersonalId(long personalId) {
        return dao.get("select new com.harmony.devops.user.user.pojo.vo.person.IncomeVO(p.id,p.monthIncome,p.otherIncome,p.yearIncome,p.otherLoan) from " +
                "IncomeEntity p where p.isDeleted=false and p.personal.id=?0", new Object[]{personalId});
    }

    @Override
    public List<OtherVO> getOtherByPersonalId(long personalId) {
        return dao.find("select new com.harmony.devops.user.user.pojo.vo.person.OtherVO(p.id,p.telcheckFlag,p.zhimaScore,p.applyAdressState,p" +
                ".applyAdressCity) " +
                "from OtherEntity p where p.isDeleted=false and p.personal.id=?0", new Object[]{personalId});
    }

    @Override
    public UserEntity getUserByPhone(String phone) {
        return dao.get("select u from UserEntity u where u.isDeleted=false and u.phone=?0", new Object[]{phone});
    }

    @Override
    public List<ConsumerUserVO> getConsumerUserList(QueryConsumerUserReciveVO consumerUserReciveVO, Integer page, Integer size) {
        StringBuffer hql = new StringBuffer("select new com.harmony.devops.user.user.pojo.vo.ConsumerUserVO(u.id,u.userNo,u.lastLoginTime,u.personal.name,u" +
                ".phone,u.isUserInfoComplete,u.socialTypeEnum,u.canUseAmt,u.creditAmt) from ConsumerUserEntity u where u.isDeleted=false ");
        HashMap<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(consumerUserReciveVO.getPhone())) {
            hql.append(" and u.phone=:phone ");
            where.put("phone", consumerUserReciveVO.getPhone());
        }
        if (consumerUserReciveVO.getOrgId() != null) {
            hql.append(" and u.organiza.id=:orgId ");
            where.put("orgId", consumerUserReciveVO.getOrgId());
        }
        if (consumerUserReciveVO.getIsUserInfoComplete() != null) {
            hql.append(" and u.isUserInfoComplete=:isUserInfoComplete ");
            where.put("isUserInfoComplete", consumerUserReciveVO.getIsUserInfoComplete());
        }
        if (StringUtils.isNotBlank(consumerUserReciveVO.getRealname())) {
            hql.append(" and u.personal.name like :realname ");
            where.put("realname", DBUtil.generateLikeSql(consumerUserReciveVO.getRealname()));
        }
        if (StringUtils.isNotBlank(consumerUserReciveVO.getIdnum())) {
            hql.append(" and u.personal.idNo=:idnum ");
            where.put("idnum", consumerUserReciveVO.getIdnum());
        }
        return dao.find(hql.toString(), where, page, size);
    }

    @Override
    public Long countConsumerUserList(QueryConsumerUserReciveVO consumerUserReciveVO) {
        StringBuffer hql = new StringBuffer("select count(*) from ConsumerUserEntity u  where u.isDeleted=false ");
        HashMap<String, Object> where = new HashMap<>();
        if (StringUtils.isNotBlank(consumerUserReciveVO.getPhone())) {
            hql.append(" and u.phone=:phone ");
            where.put("phone", consumerUserReciveVO.getPhone());
        }
        if (consumerUserReciveVO.getOrgId() != null) {
            hql.append(" and u.organiza.id=:orgId ");
            where.put("orgId", consumerUserReciveVO.getOrgId());
        }
        if (consumerUserReciveVO.getIsUserInfoComplete() != null) {
            hql.append(" and u.isUserInfoComplete=:isUserInfoComplete ");
            where.put("isUserInfoComplete", consumerUserReciveVO.getIsUserInfoComplete());
        }
        if (StringUtils.isNotBlank(consumerUserReciveVO.getRealname())) {
            hql.append(" and u.personal.name like :realname ");
            where.put("realname", DBUtil.generateLikeSql(consumerUserReciveVO.getRealname()));
        }
        if (StringUtils.isNotBlank(consumerUserReciveVO.getIdnum())) {
            hql.append(" and u.personal.idNo=:idnum ");
            where.put("idnum", consumerUserReciveVO.getIdnum());
        }
        return dao.count(hql.toString(), where);
    }

    @Override
    public ConsumerUserVO getConsumerUserVOByPhone(String phone) {
        return dao.get("select new com.harmony.devops.user.user.pojo.vo.ConsumerUserVO(u.id,u.userNo,u.userType,u.lastLoginTime,u.personal.name,u.phone,u" +
                ".isUserInfoComplete,u.socialTypeEnum,u.canUseAmt,u.creditAmt,u.amtCreateTime)" +
                " from ConsumerUserEntity u where u.phone=?0 ", new Object[]{phone});
    }

    @Override
    public void changeConsumerCanUseAmt(long userId, long amt) {
        ConsumerUserEntity consumerUserEntity = dao.get(ConsumerUserEntity.class, userId);
        consumerUserEntity.setCanUseAmt(consumerUserEntity.getCanUseAmt() - amt);
        dao.modify(consumerUserEntity);
    }


    @Override
    public ConsumerUserEntity getConsumerUserByUserNo(String userNo) {
        return dao.get("select c from ConsumerUserEntity c where c.isDeleted=false and c.userNo=?0",new Object[]{userNo});
    }

}
