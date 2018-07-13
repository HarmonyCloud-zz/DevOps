package com.zhengtou.cf.user.service;

import com.zhengtou.cf.user.controller.reciveVO.BackUserReciveVO;
import com.zhengtou.cf.user.pojo.vo.BackUserVO;

import java.util.List;

/**
 * 后台用户服务
 */
public interface BackUserService {
    /**
     * 获取后台用户
     */
    BackUserVO getBackUserByUserId(long userId);

    /**
     * 用户登陆
     */
    BackUserVO getBackUserByLoginNameAndPwd(String loginName, String passwd);

    /**
     * 取用户列表(展示)
     */
    List<BackUserVO> getBackUserList(BackUserReciveVO backUserReciveVO, Integer page, Integer size);
    Long countBackUser(BackUserReciveVO backUserReciveVO);

    /**
     * 取用户完整列表
     */
    List<BackUserVO> getAllBackUser(Long orgId,Long storeId);
}
