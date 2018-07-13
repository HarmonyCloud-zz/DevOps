package com.zhengtou.cf.api.app.v01.trade;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ListResponseVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.common.pojo.vo.SuccFessionVO;
import com.zhengtou.cf.operator.pojo.entity.OrganizationEntity;
import com.zhengtou.cf.operator.service.OrgService;
import com.zhengtou.cf.product.pojo.vo.SubProductVO;
import com.zhengtou.cf.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 贷款产品服务
 */
@RestController
@RequestMapping("api/v0.1/product")
@Api(description = "贷款产品api")
@Validated
public class ProductOutApi {
    @Autowired
    BaseDao dao;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    ProductService subProductService;
    @Autowired
    OrgService orgService;

    @RequestMapping(value = "getAllSubProduce/{produceCd}", method = RequestMethod.POST)
    @ApiOperation("获取贷款子产品")
    public NetVO getAllSubProduce(@PathVariable String produceCd) {
        List<SubProductVO> subProductVOS = subProductService.getSubProductByProductCd(produceCd);
        if(subProductVOS==null || subProductVOS.isEmpty()){
            OrganizationEntity organizationEntity=orgService.getOrganizaByOrgNo(produceCd);
            if(organizationEntity==null){
                return new SuccFessionVO(RtnResultEnum.E040000);
            }
            subProductVOS=subProductService.getSubProductByProductCd("ZTS12");
        }
        return new ListResponseVO(subProductVOS,subProductVOS.size());
    }
}
