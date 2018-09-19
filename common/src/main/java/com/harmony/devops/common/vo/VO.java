package com.harmony.devops.common.vo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @创建人 gewenzhen
 * @创建时间 2018/8/6
 * @描述 自定义session获取
 */
public interface VO {
    interface ToVO {
        default VO toVO() {
            return toVO(false);
        }

        VO toVO(boolean isRecursion);
    }

    static Collection<? extends VO> toVOCollection(Iterable<? extends ToVO> vos) {
        return toVOCollection(vos, false);
    }

    static Collection<? extends VO> toVOCollection(Iterable<? extends ToVO> vos, boolean isRecursion) {
        Set<VO> r = new HashSet<>();
        if (null != vos) {
            vos.forEach(vo -> r.add(vo.toVO(isRecursion)));
        }
        return r;
    }

    static VO inCaseNull(VO.ToVO vo, boolean isRecursion) {
        return null == vo ? null : vo.toVO(isRecursion);
    }
}
