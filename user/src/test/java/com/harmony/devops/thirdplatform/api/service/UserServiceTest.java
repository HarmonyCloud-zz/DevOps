package com.harmony.devops.thirdplatform.api.service;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.harmony.devops.user.operator.pojo.entity.OrganizationEntity;
import com.harmony.devops.user.operator.pojo.entity.enums.OrganizaTypeEnum;
import com.harmony.devops.thirdplatform.api.test.BaseTestWithTransaction;
import com.harmony.devops.user.user.pojo.entity.ConsumerUserEntity;
import com.harmony.devops.user.user.pojo.entity.UserEntity;
import com.harmony.devops.user.user.pojo.entity.enums.UserInfoCompleteEnum;
import com.harmony.devops.user.user.pojo.entity.enums.UserSourceTypeEnum;
import com.harmony.devops.user.user.pojo.entity.person.enums.SocialIdTypeEnum;
import com.harmony.devops.user.user.pojo.vo.ConsumerUserVO;
import com.harmony.devops.user.user.service.BackUserService;
import com.harmony.devops.user.user.service.ConsumerUserService;
import com.harmony.devops.user.user.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServiceTest extends BaseTestWithTransaction {

    @Autowired
    UserService userService;
    @Autowired
    ConsumerUserService consumerUserService;
    @Autowired
    BackUserService backUserService;
    @Autowired
    BaseDao dao;

    @Before
    public void init() {

        OrganizationEntity org = new OrganizationEntity();
        org.setSocialCreditCode("shxym123455666");
        org.setOrgNo(DBUtil.getOrgNo());
        org.setStatus(OrganizationEntity.OrgStatus.启用);
        org.setDescript("测试");
        org.setType(OrganizaTypeEnum.合作结构);
        org.setName("test");
        dao.save(org);
        setData("orgTest1", org);

        ConsumerUserEntity consumerUserTest1 = new ConsumerUserEntity();
        consumerUserTest1.setPhone("18937118056");
        consumerUserTest1.setPassword("123456");
        consumerUserTest1.setUserSourceTypeEnum(UserSourceTypeEnum.APP);
        consumerUserTest1.setOrganiza(org);
        setData("registConsumerUser", consumerUserTest1);
        consumerUserService.saveConsumerUser(consumerUserTest1.getPhone(), consumerUserTest1.getPassword(), consumerUserTest1.getUserSourceTypeEnum
                (), org);
    }

    /***********************************user*************************************/
    @Test
    public void getUserByPhone() {
        ConsumerUserEntity consumerUserTest1 = getData("registConsumerUser", ConsumerUserEntity.class);
        UserEntity userEntity = userService.getUserByPhone(consumerUserTest1.getPhone());
        Assert.assertEquals("18937118056", userEntity.getPhone());
        Assert.assertEquals("18937118056", userEntity.getLoginName());
        Assert.assertEquals(DBUtil.generatePassword("123456"), userEntity.getPassword());
    }

    /***********************************consumerUser*************************************/

    @Test
    public void getUserByLoginNameAndPwd() {
        ConsumerUserEntity consumerUserTest1 = getData("registConsumerUser", ConsumerUserEntity.class);

        ConsumerUserVO consumerUserVO = consumerUserService.getConsumerUserByLoginNameAndPwd(consumerUserTest1.getPhone(),
               consumerUserTest1.getPassword());

        Assert.assertNotNull(consumerUserVO);
        Assert.assertEquals("18937118056", consumerUserVO.getPhone());
        Assert.assertNotNull(consumerUserVO.getId());
        Assert.assertNotNull(consumerUserVO.getUserNo());
        Assert.assertNull(consumerUserVO.getPersonId());
        Assert.assertEquals("",consumerUserVO.getAmtCreateTime());
        Assert.assertEquals("",consumerUserVO.getCanUseAmt());
        Assert.assertEquals(false,consumerUserVO.getHasTradePwd());
        Assert.assertEquals(UserInfoCompleteEnum.未完善,consumerUserVO.getUserInfoCompleteEnum());
        Assert.assertEquals(SocialIdTypeEnum.在职人员,consumerUserVO.getSocialTypeEnum());
        Assert.assertEquals("",consumerUserVO.getCreditAmt());
    }


}
