package com.zhengtou.cf.product.service;

import com.zhengtou.cf.product.pojo.entity.FeeEntity;
import com.zhengtou.cf.product.pojo.entity.SubProductEntity;
import com.zhengtou.cf.product.pojo.entity.enums.FeeTypeEnum;
import com.zhengtou.cf.product.pojo.vo.ProductVO;
import com.zhengtou.cf.product.pojo.vo.SubProductVO;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
public interface ProductService {
    /**
     * 根据子产品id获取子产品
     */
    SubProductVO getSubProductById(long subProductId);

    /**
     * 根据产品cd获取所有子产品
     */
    List<SubProductVO> getSubProductByProductCd(String productCd);

    /**
     * 根据产品id获取所有子产品
     */
    List<SubProductVO> getSubProductByProductId(long productId);

    /**
     * 根据产品cd获取所有子产品
     */
    List<SubProductVO> getSubProduct();

    /**
     * 获得所有产品
     */
    List<ProductVO> getAllProduct(Integer page,Integer size);

    /**
     * 根据子产品id获取子产品
     */
    SubProductEntity getSubProductEntityById(long subProductId);

    /**
     * 根据产品cd，子产品cd，产品费项查找费项
     */
    FeeEntity getFeeByProduceCdAndProductSubCdAndFeeType(String productCd, String productSubCd, FeeTypeEnum feeTypeEnum);

}
