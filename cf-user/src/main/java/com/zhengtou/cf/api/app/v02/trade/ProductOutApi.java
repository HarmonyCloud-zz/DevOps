package com.zhengtou.cf.api.app.v02.trade;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ListResponseVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.operator.service.OrgService;
import com.zhengtou.cf.product.pojo.vo.SubProductVO;
import com.zhengtou.cf.product.service.ProductService;
import io.swagger.annotations.*;
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
@RestController("product_v0.2")
@RequestMapping("api/v0.2/product")
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

    @RequestMapping(value = "getAllSubProduce/{produceCd}", method = RequestMethod.GET)
    @ApiOperation("获取贷款子产品")
    @ApiResponses({@ApiResponse(code = 200,response = SubProductVO.class,message = "获取贷款子产品返回")})
    public NetVO getAllSubProduce(@PathVariable @ApiParam(value = "产品代码", name = "produceCd") String produceCd) throws BaseException {
        List<SubProductVO> subProductVOS = subProductService.getSubProductByProductCd(produceCd);
        if (subProductVOS == null || subProductVOS.isEmpty()) {
            throw new BaseException(RtnResultEnum.E070005);
        }
        return new ListResponseVO(subProductVOS, subProductVOS.size());
    }
}
