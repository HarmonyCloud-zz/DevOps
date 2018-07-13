package com.zhengtou.cf.handler;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.operator.pojo.entity.NullOrganizationEntity;
import com.zhengtou.cf.operator.pojo.entity.NullStoreEntity;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.operator.pojo.entity.StoreEntity;
import com.zhengtou.cf.operator.pojo.entity.enums.OrganizaTypeEnum;
import com.zhengtou.cf.risk.pojo.entity.rule.enums.ApprovalRuleTypeEnum;
import com.zhengtou.cf.risk.pojo.vo.rule.RuleVO;
import com.zhengtou.cf.risk.service.RuleService;
import com.zhengtou.cf.user.pojo.entity.BackUserEntity;
import com.zhengtou.cf.user.pojo.entity.enums.UserTypeEnum;
import com.zhengtou.cf.user.pojo.entity.person.NullPersonalEntity;
import com.zhengtou.cf.util.FileUtil;
import com.zhengtou.cf.util.PinyinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目初始化数据准备
 *
 * @author 葛文镇
 */
@Component
public class InitData implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    BaseDao dao;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    RuleService ruleService;
    @Value("${dashboard.menu.resources.path}")
    private String menuJson;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            //初始化空机构信息
            NullOrganizationEntity nullOrganizationEntity = dao.get("from OrganizationEntity o where o.isDeleted=false and o" +
                    ".class='NullOrganizationEntity'", new Object[]{});
            if (nullOrganizationEntity ==null){
                nullOrganizationEntity=new NullOrganizationEntity();
                dao.save(nullOrganizationEntity);
            }
            myRedisComponent.setDay("nullOrganiza", nullOrganizationEntity, 30l);
            //初始化空门店数据
            NullStoreEntity nullStoreEntity = dao.get("from StoreEntity s where s.isDeleted=false and s.class='NullStoreEntity'", new Object[]{});
            if (nullStoreEntity== null) {
                nullStoreEntity=new NullStoreEntity();
                dao.save(nullStoreEntity);
            }
            myRedisComponent.setDay("nullStore", nullStoreEntity, 30l);
            //初始化空个人信息数据
            NullPersonalEntity nullPersonalEntity = dao.get("from PersonalEntity s where s.isDeleted=false and s.class='NullPersonalEntity'", new Object[]{});
            if (nullPersonalEntity== null) {
                nullPersonalEntity=new NullPersonalEntity();
                dao.save(nullPersonalEntity);
            }
            myRedisComponent.setDay("nullPerson", nullPersonalEntity, 30l);
            /*************************************郑投数据**************************************/
            //初始化郑投机构信息
            OrganizationEntity organizationEntity = dao.get("from OrganizationEntity o where o.isDeleted=false" +
                    " and o.class='OrganizationEntity' and o.type=?0", new Object[]{OrganizaTypeEnum.郑投});
            if (organizationEntity == null) {
                organizationEntity = new OrganizationEntity();
                organizationEntity.setName("河南郑投网络信息技术有限责任公司");
                organizationEntity.setAddress("郑州市郑东新区东风南路互联网金融大厦16楼");
                organizationEntity.setType(OrganizaTypeEnum.郑投);
                organizationEntity.setOrgNo(DBUtil.getOrgNo());
                organizationEntity.setSocialCreditCode("91410100345006117Q");
                organizationEntity.setStatus(OrganizationEntity.OrgStatus.启用);
                organizationEntity.setDescript("郑投网以“安全、规范、透明”为核心理念，是郑州投资控股有限公司“互联网金融”战略的主要载体，致力于为投融资双方提供一个安全、便捷、高效的线上交易平台。");
                dao.save(organizationEntity);
            }
            myRedisComponent.setDay("ztOrganiza", organizationEntity, 30l);
            //初始化郑投门店信息
            StoreEntity store = dao.get("from StoreEntity o where o.isDeleted=false and o.class='StoreEntity' and o.organiza=?0", new
                    Object[]{organizationEntity});
            if (store == null) {
                store = new StoreEntity();
                store.setStoreName("河南郑投门店");
                store.setStoreNo(DBUtil.getStoreNo());
                store.setAddress("郑州市郑东新区东风南路互联网金融大厦16楼");
                store.setOrganiza(organizationEntity);
                store.setStatus(StoreEntity.StoreStatus.启用);
                dao.save(store);
            }
            myRedisComponent.setDay("ztStore", store, 30l);
            //初始化用户
            if (dao.<BackUserEntity>get("from BackUserEntity b where b.isDeleted=false and b.organiza=?0", new Object[]{organizationEntity}) ==
                    null) {
                BackUserEntity admin = new BackUserEntity();
                admin.setLoginName(PinyinUtil.convertHanzi2Pinyin(organizationEntity.getName(), false));
                admin.setPassword(DBUtil.generatePassword());
                admin.setUserType(UserTypeEnum.后台管理员);
                admin.setNickName("超级管理员");
                admin.setOrganiza(organizationEntity);
                admin.setStore(store);
                try {
                    admin.setMenu(FileUtil.readResource(menuJson).replaceAll("\\\\s*|\\t|\\r|\\n", ""));
                } catch (BaseException e) {
                    e.printStackTrace();
                }
                dao.save(admin);
            }
            //预加载风控拒绝项规则
            List<RuleVO> ruleVOS = ruleService.getRuleList();
            if (ruleVOS != null) {
                for (RuleVO ruleVO : ruleVOS) {
                    if (ruleVO.getRuleType().equals(ApprovalRuleTypeEnum.拒绝项)) {
                        myRedisComponent.setDay("RejectRule" + ruleVO.getRuleId(), ruleVO.getRuleType(), 100l);
                    } else {
                        myRedisComponent.setDay("WarnRule" + ruleVO.getRuleId(), ruleVO.getRuleType(), 100l);
                    }
                }
            }
        }
    }
}