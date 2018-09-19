package com.harmony.devops.controller.api.organization;

import com.harmony.devops.common.enums.RtnResultEnum;
import com.harmony.devops.common.exception.BaseException;
import com.harmony.devops.common.utils.BeanNullUtil;
import com.harmony.devops.common.vo.NetVO;
import com.harmony.devops.common.vo.SuccFessionVO;
import com.harmony.devops.controller.recive.organization.OrganizationVO;
import com.harmony.devops.domain.organization.Organization;
import com.harmony.devops.service.AllServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/3
 * @描述
 */
@RestController
@RequestMapping("org")
@Api(description = "机构服务")
public class OrganizationController extends AllServices{

    @ApiOperation("新建机构")
    @RequestMapping(value = "save", method = {RequestMethod.POST,RequestMethod.GET})
    public NetVO saveOrg(@RequestBody @ApiParam(value = "机构vo", name = "organizationVO") OrganizationVO organizationVO) {
        Organization organizationEntity=new Organization();
        BeanUtils.copyProperties(organizationVO,organizationEntity, BeanNullUtil.getNullPropertyNames(organizationVO));
        organizationRepository.save(organizationEntity);
        return new SuccFessionVO();
    }

    @ApiOperation("编辑机构")
    @RequestMapping(value = "modify/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public NetVO modifyOrg(@PathVariable @ApiParam(value = "id",name = "id") long id,
                           @RequestBody @ApiParam(value = "机构vo", name = "organizationVO") OrganizationVO organizationVO)
            throws BaseException {
        Organization organization=organizationRepository.findOne(id);
        if(organization==null){
            throw new BaseException(RtnResultEnum.U000000);
        }
        BeanUtils.copyProperties(organizationVO,organization, BeanNullUtil.getNullPropertyNames(organizationVO));
        organizationRepository.save(organization);
        return new SuccFessionVO();
    }


}
