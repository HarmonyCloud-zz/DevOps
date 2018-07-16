package com.harmony.devops.user.operator.controller;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.*;
import com.zhengtou.cf.common.utils.DBUtil;
import com.zhengtou.cf.common.utils.StringUtils;
import com.harmony.devops.user.operator.controller.vo.FetchOrganizaVO;
import com.harmony.devops.user.operator.controller.vo.FetchStoreVO;
import com.harmony.devops.user.operator.pojo.entity.OrganizationEntity;
import com.harmony.devops.user.operator.pojo.entity.StoreEntity;
import com.harmony.devops.user.operator.pojo.vo.OrganizaVO;
import com.harmony.devops.user.operator.pojo.vo.StoreVO;
import com.harmony.devops.user.operator.service.OrgService;
import com.harmony.devops.user.operator.service.StoreService;
import com.harmony.devops.user.user.pojo.entity.BackUserEntity;
import com.harmony.devops.user.user.pojo.entity.enums.UserTypeEnum;
import com.harmony.devops.user.user.pojo.vo.BackUserVO;
import com.harmony.devops.user.user.service.BackUserService;
import com.harmony.devops.user.thirdApi.util.PinyinUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("org")
@Api(description = "组织机构服务")
public class OrganizaController {
    @Autowired
    BaseDao dao;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    OrgService orgService;
    @Autowired
    StoreService storeService;
    @Autowired
    BackUserService backUserService;

    @RequestMapping(value = "getOrganizaList/{token}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("获取组织列表")
    /**
     * 默认返回10个结果
     */
    public NetVO getOrganizaList(FetchOrganizaVO fetchOrganizaVO, @PathVariable Integer page, @PathVariable Integer size) {
        List<OrganizaVO> organizaVOList = orgService.getOrganizaList(fetchOrganizaVO, page, size);
        long count = orgService.countOrganiza(fetchOrganizaVO);
        return new ListResponseVO(organizaVOList, count);
    }

    @RequestMapping(value = "getOrganiza/{id}", method = RequestMethod.POST)
    @ApiOperation("获取组织")
    public NetVO getOrganiza(@PathVariable long id) throws BaseException {
        OrganizaVO organizaVO = orgService.getOrganizaById(id);
        if (organizaVO == null) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        return new ResponseVO(organizaVO);
    }

    @RequestMapping(value = "addOrganiza/{token}", method = RequestMethod.POST)
    @ApiOperation("新增组织")
    public NetVO addOrganiza(@PathVariable String token, @Validated FetchOrganizaVO fetchOrganizaVO) throws BaseException {
        BackUserVO backUserVO = (BackUserVO) myRedisComponent.get(token);
        if (backUserVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        OrganizationEntity organizationEntity = new OrganizationEntity();
        BeanUtils.copyProperties(fetchOrganizaVO, organizationEntity);
        organizationEntity.setOrgNo(StringUtils.isNotBlank(fetchOrganizaVO.getOrgNo()) ? fetchOrganizaVO.getOrgNo() : DBUtil.getOrgNo());
        dao.save(organizationEntity);
        BackUserEntity backUserEntity = new BackUserEntity();
        backUserEntity.setOrganiza(organizationEntity);
        backUserEntity.setNickName(fetchOrganizaVO.getName() + "管理员");
        backUserEntity.setPassword(DBUtil.generatePassword());
        backUserEntity.setLoginName(PinyinUtil.convertHanzi2Pinyin(fetchOrganizaVO.getName(), false));
        backUserEntity.setUserType(UserTypeEnum.后台管理员);
        dao.save(backUserEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "getSelectOrganizaList/", method = RequestMethod.POST)
    @ApiOperation("获取组织列表")
    public NetVO getSelectOrganizaList() {
        List<OrganizaVO> organizaVOList = orgService.getOrganizaSelectList();
        return new ListResponseVO(organizaVOList);
    }

    @RequestMapping(value = "editOrganiza/{id}", method = RequestMethod.POST)
    @ApiOperation("编辑组织")
    public NetVO editOrganiza(@PathVariable long id, @Validated FetchOrganizaVO fetchOrganizaVO) throws BaseException {
        OrganizationEntity organizationEntity = dao.get(OrganizationEntity.class, id);
        if (organizationEntity == null) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        if (organizationEntity.getIsDeleted()) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        String orgNo = organizationEntity.getOrgNo();
        BeanUtils.copyProperties(fetchOrganizaVO, organizationEntity);
        if (StringUtils.isBlank(fetchOrganizaVO.getOrgNo())) {
            organizationEntity.setOrgNo(orgNo);
        }
        dao.modify(organizationEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "delOrganiza/{id}", method = RequestMethod.POST)
    @ApiOperation("删除组织")
    public NetVO delOrganiza(@PathVariable long id) throws BaseException {
        OrganizationEntity organizationEntity = dao.get(OrganizationEntity.class, id);
        if (organizationEntity == null) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        List<StoreVO> storeVOS = storeService.getStoreByOrgId(organizationEntity.getId(), null, null);
        if (!storeVOS.isEmpty()) {
            throw new BaseException(RtnResultEnum.E030005);
        }
        List<BackUserVO> backUserVOS = backUserService.getAllBackUser(id, null);
        if (!backUserVOS.isEmpty()) {
            throw new BaseException(RtnResultEnum.E040005);
        }
        dao.delete(organizationEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "getStoreList/{token}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("获取门店列表")
    public NetVO getStoreList(@PathVariable String token, FetchStoreVO fetchStoreVO, @PathVariable Integer page, @PathVariable Integer size) throws
            BaseException {
        BackUserVO user = (BackUserVO) myRedisComponent.get(token);
        if (user == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        OrganizationEntity organizationEntity = (OrganizationEntity) myRedisComponent.get("ztOrganiza");
        if (user.getOrgId() != organizationEntity.getId()) {
            fetchStoreVO.setOrgId(user.getOrgId());
        }
        List<StoreVO> organizaVOList = storeService.getStoreList(fetchStoreVO, page, size);
        long count = storeService.countStore(fetchStoreVO);
        return new ListResponseVO(organizaVOList, count);
    }

    @RequestMapping(value = "getSelectStoreList/{orgId}", method = RequestMethod.POST)
    @ApiOperation("获取门店列表")
    public NetVO getSelectStoreList(@PathVariable long orgId) {
        List<StoreVO> storeVOS = storeService.getStoreSelectList(orgId);
        return new ListResponseVO(storeVOS);
    }

    @RequestMapping(value = "getStore/{id}", method = RequestMethod.POST)
    @ApiOperation("获取门店")
    public NetVO getStore(@PathVariable long id) throws BaseException {
        StoreVO storeVO = storeService.getStoreById(id);
        if (storeVO == null) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        return new ResponseVO(storeVO);
    }

    @RequestMapping(value = "getStoreByOrg/{orgId}/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("获取组织下门店")
    public NetVO getStoreByOrg(@PathVariable long orgId, @PathVariable Integer page, @PathVariable Integer size) {
        List<StoreVO> storeVOs = storeService.getStoreByOrgId(orgId, page, size);
        return new ListResponseVO(storeVOs);
    }

    @RequestMapping(value = "addStore/{token}", method = RequestMethod.POST)
    @ApiOperation("新增门店")
    public NetVO addStore(@PathVariable String token, @Validated FetchStoreVO fetchStoreVO) throws BaseException {
        BackUserVO backUserVO = (BackUserVO) myRedisComponent.get(token);
        if (backUserVO == null) {
            throw new BaseException(RtnResultEnum.E000003);
        }
        if (backUserVO.getUserType().equals(UserTypeEnum.后台管理员)) {
            OrganizationEntity organizationEntity = dao.get(OrganizationEntity.class, backUserVO.getOrgId());
            if (organizationEntity == null) {
                throw new BaseException(RtnResultEnum.E030001);
            }
            StoreEntity storeEntity = new StoreEntity();
            BeanUtils.copyProperties(fetchStoreVO, storeEntity);
            storeEntity.setStoreNo(StringUtils.isNotBlank(fetchStoreVO.getStoreNo()) ? fetchStoreVO.getStoreNo() : DBUtil.getStoreNo());
            storeEntity.setOrganiza(organizationEntity);
            dao.save(storeEntity);
            BackUserEntity backUserEntity = new BackUserEntity();
            backUserEntity.setOrganiza(organizationEntity);
            backUserEntity.setStore(storeEntity);
            backUserEntity.setNickName(fetchStoreVO.getStoreName() + "管理员");
            backUserEntity.setPassword(DBUtil.generatePassword());
            backUserEntity.setLoginName(PinyinUtil.convertHanzi2Pinyin(fetchStoreVO.getStoreName(), false));
            backUserEntity.setUserType(UserTypeEnum.后台管理员);
            dao.save(backUserEntity);
            return new SuccFessionVO();
        } else {
            return new SuccFessionVO(RtnResultEnum.E060000);
        }
    }

    @RequestMapping(value = "editStore/{id}", method = RequestMethod.POST)
    @ApiOperation("编辑门店")
    public NetVO editStore(@PathVariable long id, @Validated FetchStoreVO fetchStoreVO) throws BaseException {
        StoreEntity storeEntity = dao.get(StoreEntity.class, id);
        if (storeEntity == null) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        if (storeEntity.getIsDeleted()) {
            throw new BaseException(RtnResultEnum.E070002);
        }
        String storeNo = storeEntity.getStoreNo();
        BeanUtils.copyProperties(fetchStoreVO, storeEntity);
        if (StringUtils.isBlank(fetchStoreVO.getStoreNo())) {
            storeEntity.setStoreNo(storeNo);
        }
        dao.modify(storeEntity);
        return new SuccFessionVO();
    }

    @RequestMapping(value = "delStore/{id}", method = RequestMethod.POST)
    @ApiOperation("删除门店")
    public NetVO delStore(@PathVariable long id) throws BaseException {
        StoreEntity organizationEntity = dao.get(StoreEntity.class, id);
        if (organizationEntity == null) {
            throw new BaseException(RtnResultEnum.E030001);
        }
        dao.delete(organizationEntity);
        return new SuccFessionVO();
    }

}
