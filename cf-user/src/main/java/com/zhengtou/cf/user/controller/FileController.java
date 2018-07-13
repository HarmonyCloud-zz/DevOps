package com.zhengtou.cf.user.controller;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.enums.AnnexTypeEnum;
import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ErrorFessionVO;
import com.zhengtou.cf.common.pojo.vo.ListResponseVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.zhengtou.cf.user.pojo.vo.AnnexVO;
import com.zhengtou.cf.user.pojo.vo.ConsumerUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("file")
public class FileController {
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    BaseDao dao;

    @RequestMapping(value = "getAllAnnex/{token}", method = RequestMethod.POST)
    public NetVO getAllAnnex(@PathVariable String token, AnnexTypeEnum annexTypeEnum) {
        ConsumerUserVO userItemVO = (ConsumerUserVO) myRedisComponent.get(token);
        if (userItemVO == null) {
            return new ErrorFessionVO(RtnResultEnum.E000003);
        }
        StringBuffer hql = new StringBuffer("select new com.zhengtou.cf.user.pojo.vo.AnnexVO(a.annexCode,a.annexTypeEnum,a.url) from AnnexEntity a " +
                "where a.isDeleted=false and a.consumer.id=?0 ");
        Object[] temp=new Object[]{userItemVO.getId()};
        if(annexTypeEnum!=null){
            hql.append("and a.annexTypeEnum=?1");
            temp[1]=annexTypeEnum;
        }
        List<AnnexVO> annexVOS = dao.find(hql.toString(),temp);
        return new ListResponseVO(annexVOS);
    }
}
