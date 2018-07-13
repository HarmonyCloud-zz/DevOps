package com.zhengtou.cf.product.service.impl;

import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.product.pojo.entity.FeeEntity;
import com.zhengtou.cf.product.pojo.entity.SubProductEntity;
import com.zhengtou.cf.product.pojo.entity.enums.FeeTypeEnum;
import com.zhengtou.cf.product.pojo.vo.ProductVO;
import com.zhengtou.cf.product.pojo.vo.SubProductVO;
import com.zhengtou.cf.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    BaseDao dao;

    @Override
    public SubProductVO getSubProductById(long subProductId) {
        return dao.get("select new com.zhengtou.cf.product.pojo.vo.SubProductVO(sp.id,sp.productCd,sp.repayMethod,sp.cycleCnt) from " +
                "SubProductEntity sp where sp.isDeleted=false" +
                " and sp.id=?0 ", new Object[]{subProductId});
    }

    @Override
    public List<SubProductVO> getSubProductByProductCd(String productCd) {
        List<SubProductVO> subProductVOS = dao.find("select new com.zhengtou.cf.product.pojo.vo.SubProductVO(s.id,s.productGroupCd,s.productCd,s" +
                ".productSubCd,s.ztProductEnum,s.description,s.repayMethod,s.cycleCnt,s.maxAmt,s.minAmt,s.tag) from SubProductEntity s where s" +
                ".isDeleted=false and s.productCd=?0 order by s.cycleCnt", new Object[]{productCd});
        return subProductVOS;
    }

    @Override
    public List<SubProductVO> getSubProductByProductId(long productId) {
        List<SubProductVO> subProductVOS = dao.find("select new com.zhengtou.cf.product.pojo.vo.SubProductVO(s.id,s.productGroupCd,s.productCd,s" +
                ".productSubCd,s.ztProductEnum,s.description,s.repayMethod,s.cycleCnt,s.maxAmt,s.minAmt,s.tag) from SubProductEntity s," +
                "ProductEntity p where s.isDeleted=false and p.id=?0 and s.productCd=p.productCd", new
                Object[]{productId});
        return subProductVOS;
    }

    @Override
    public List<SubProductVO> getSubProduct() {
        List<SubProductVO> subProductVOS = dao.find("select new com.zhengtou.cf.product.pojo.vo.SubProductVO(s.id,s.productGroupCd,s.productCd,s" +
                ".productSubCd,s.ztProductEnum,s.description,s.repayMethod,s.cycleCnt,s.maxAmt,s.minAmt,s.tag) from SubProductEntity s where s" +
                ".isDeleted=false", new
                Object[]{});
        return subProductVOS;
    }

    @Override
    public List<ProductVO> getAllProduct(Integer page, Integer size) {
        List<ProductVO> productVOS = dao.find("select new com.zhengtou.cf.product.pojo.vo.ProductVO(p.id,p.productGroupCd,p.productCd,p" +
                ".productName,p.description,p.productStatus) from ProductEntity p where p.isDeleted=false", new Object[]{}, page, size);
        return productVOS;
    }

    @Override
    public SubProductEntity getSubProductEntityById(long subProductId) {
        return dao.get(SubProductEntity.class, subProductId);
    }

    @Override
    public FeeEntity getFeeByProduceCdAndProductSubCdAndFeeType(String productCd, String productSubCd, FeeTypeEnum feeTypeEnum) {
        return dao.get("select f from FeeEntity f where f.isDeleted=false and f.productCd=?0 and f.productSubCd=?1 and f.feeType=?2 ", new
                Object[]{productCd, productSubCd, feeTypeEnum});
    }
}
