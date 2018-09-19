package com.harmony.devops.domain.resources;

import com.harmony.devops.common.vo.VO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static com.harmony.devops.common.vo.VO.inCaseNull;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/9/13
 * @描述
 */
@Data
@Entity
public class Menu extends Resources{
    @ManyToOne
    private Menu parenter;//父级
    private Integer sort;//序列

    public Vo toMenuVo(boolean isRecursion) {
        Vo vo=new Vo();
        vo.resourcesName=this.resourcesName;
        vo.resourcesUrl=this.resourcesUrl;
        vo.sort=this.sort;
        if (isRecursion) {
            vo.partnerVo = (Vo) inCaseNull(this.parenter, isRecursion);
        } else {
            // 去除多重关联
        }
        return vo;
    }

    @Data
    public static class Vo implements VO {
        private String resourcesName;
        private String resourcesUrl;
        private Integer sort;
        private Vo partnerVo;
    }
}
