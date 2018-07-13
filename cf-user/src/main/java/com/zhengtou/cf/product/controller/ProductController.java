package com.zhengtou.cf.product.controller;

import com.zhengtou.cf.api.cl.ProductApi;
import com.zhengtou.cf.common.api.outer.cl.cls.infoVO.ProductInfo;
import com.zhengtou.cf.common.api.outer.cl.cls.infoVO.ProductSub;
import com.zhengtou.cf.common.api.outer.cl.cls.response.TNQProductGroupResponse;
import com.zhengtou.cf.common.api.outer.cl.cls.response.TNQProductResponse;
import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.*;
import com.zhengtou.cf.enums.ZtProductEnum;
import com.zhengtou.cf.product.pojo.entity.*;
import com.zhengtou.cf.product.pojo.entity.enums.ChargeFeeMethodEnum;
import com.zhengtou.cf.product.pojo.entity.enums.ChargeFeeRadixEnum;
import com.zhengtou.cf.product.pojo.entity.enums.FeeTypeEnum;
import com.zhengtou.cf.product.pojo.entity.enums.RepayMethodEnum;
import com.zhengtou.cf.product.pojo.vo.ProductVO;
import com.zhengtou.cf.product.pojo.vo.SubProductVO;
import com.zhengtou.cf.product.service.ProductService;
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

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 贷款产品服务
 */
@RestController
@RequestMapping("product")
@Api(description = "贷款产品服务")
@Validated
public class ProductController {
    @Autowired
    BaseDao dao;
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    ProductApi productApi;
    @Autowired
    ProductService subProductService;

    @RequestMapping(value = "synProduce", method = RequestMethod.POST)
    @ApiOperation("贷款产品同步")
    public NetVO synProduce() {
        //所有产品组代码
        for (ZtProductEnum ztProductEnum : ZtProductEnum.values()) {
            try {
                //入库产品组
                TNQProductGroupResponse tnqProductGroupResponse = productApi.queryProductGroup(ztProductEnum);
                ProductGrpupEntity productGrpupEntity = dao.get("from ProductGrpupEntity p where p.isDeleted=false and p.productGroupCd=?0", new
                        Object[]{tnqProductGroupResponse.getProductGroupCd()});
                if (productGrpupEntity == null) {
                    productGrpupEntity = new ProductGrpupEntity();
                }
                BeanUtils.copyProperties(tnqProductGroupResponse, productGrpupEntity);
                dao.saveOrModify(productGrpupEntity);
                //所有产品代码
                for (ProductInfo productInfo : tnqProductGroupResponse.getProductList()) {
                    TNQProductResponse tnqProductResponse = productApi.getProductList(productInfo.getProductCd());
                    //产品入库
                    ProductEntity productEntity = dao.get("from ProductEntity p where p.isDeleted=false and p.productCd=?0 ", new
                            Object[]{tnqProductResponse.getProductCd()});
                    if (productEntity == null) {
                        productEntity = new ProductEntity();
                    }
                    BeanUtils.copyProperties(tnqProductResponse, productEntity);
                    productEntity.setProductGroupCd(tnqProductGroupResponse.getProductGroupCd());
                    dao.saveOrModify(productEntity);
                    //子产品入库
                    for (ProductSub productSub : tnqProductResponse.getProductSubList()) {
                        SubProductEntity subProductEntity = dao.get("from SubProductEntity s where s.isDeleted=false and s.productSubCd=?0", new
                                Object[]{productSub.getProductSubCd()});
                        if (subProductEntity == null) {
                            subProductEntity = new SubProductEntity();
                        }
                        BeanUtils.copyProperties(productSub, subProductEntity);
                        subProductEntity.setCycleCnt(Integer.parseInt(productSub.getCycleCnt()));
                        subProductEntity.setProductGroupCd(tnqProductGroupResponse.getProductGroupCd());
                        subProductEntity.setProductCd(tnqProductResponse.getProductCd());
                        subProductEntity.setZtProductEnum(ztProductEnum);
                        subProductEntity.setRepayMethod(RepayMethodEnum.getEnum(productSub.getRepayMethod().name()));
                        dao.saveOrModify(subProductEntity);
                        //费率入库
                        if (tnqProductResponse.getMulctFeeDef() != null) {
                            MulctFeeEntity mulctFeeEntity = dao.get("from MulctFeeEntity m where m.isDeleted=false and m.product.productCd=?0 ", new
                                    Object[]{tnqProductResponse.getProductCd()});
                            if (mulctFeeEntity == null) {
                                mulctFeeEntity = new MulctFeeEntity();
                            }
                            BeanUtils.copyProperties(tnqProductResponse.getMulctFeeDef(), mulctFeeEntity);
                            mulctFeeEntity.setProductSubCd(productSub.getProductSubCd());
                            dao.saveOrModify(mulctFeeEntity);
                        }
                        if (tnqProductResponse.getLateFeeDef() != null) {
                            LateFeeEntity lateFeeEntity = dao.get("from LateFeeEntity m where m.isDeleted=false and m.product.productCd=?0 ", new
                                    Object[]{tnqProductResponse.getProductCd()});
                            if (lateFeeEntity == null) {
                                lateFeeEntity = new LateFeeEntity();
                            }
                            BeanUtils.copyProperties(tnqProductResponse.getLateFeeDef(), lateFeeEntity);
                            lateFeeEntity.setProductSubCd(productSub.getProductSubCd());
                            dao.saveOrModify(lateFeeEntity);
                        }
                        //TODO 具体其他费项接口未返回
                        FeeEntity feeEntity = dao.get("from FeeEntity f where f.isDeleted=false and f.productCd=?0 and f.productSubCd=?1 and f" +
                                ".feeType=?2", new Object[]{tnqProductResponse.getProductCd(), productSub.getProductSubCd(), FeeTypeEnum.提前结清手续费});
                        if (feeEntity == null) {
                            feeEntity = new FeeEntity();
                            feeEntity.setFeeType(FeeTypeEnum.提前结清手续费);
                            feeEntity.setChargeFeeMethod(ChargeFeeMethodEnum.及时生效);
                            feeEntity.setChargeFeeRadix(ChargeFeeRadixEnum.全部本金);
                            feeEntity.setChargeFeeRate(0.0001);
                            feeEntity.setChargeFeeVal(200l);
                            feeEntity.setProductCd(tnqProductResponse.getProductCd());
                            feeEntity.setProductSubCd(productSub.getProductSubCd());
                            dao.saveOrModify(feeEntity);
                        }
                    }
                }
            } catch (BaseException e) {
                return new ErrorFessionVO(e.getErrorCode(), e.getErrorMsg());
            }
        }
        return new SuccFessionVO();
    }

    @RequestMapping(value = "getAllSubProduce/{productId}", method = RequestMethod.POST)
    @ApiOperation("获取贷款子产品")
    public NetVO getAllSubProduce(@PathVariable long productId) {
        List<SubProductVO> subProductVOS = subProductService.getSubProductByProductId(productId);
        return new ListResponseVO(subProductVOS, subProductVOS.size());
    }

    @RequestMapping(value = "getAllSubProduce", method = RequestMethod.POST)
    @ApiOperation("获取所有贷款子产品")
    public NetVO getAllSubProduce() {
        List<SubProductVO> subProductVOS = subProductService.getSubProduct();
        return new ListResponseVO(subProductVOS, subProductVOS.size());
    }

    @RequestMapping(value = "getSubProduceInfo/{subProductId}", method = RequestMethod.POST)
    @ApiOperation("贷款子产品详情")
    public NetVO getSubProduceInfo(@PathVariable long subProductId) {
        SubProductEntity subProductEntity = dao.get("from SubProductEntity s where s.isDeleted=false and s.id=?0", new
                Object[]{subProductId});
        return new ResponseVO(subProductEntity);
    }

    @RequestMapping(value = "getAllProduce/{page}/{size}", method = RequestMethod.POST)
    @ApiOperation("获取贷款产品")
    public NetVO getAllProduce(Integer page, Integer size) {
        List<ProductVO> productVOS = subProductService.getAllProduct(page, size);
        return new ListResponseVO(productVOS, productVOS.size());
    }
}
