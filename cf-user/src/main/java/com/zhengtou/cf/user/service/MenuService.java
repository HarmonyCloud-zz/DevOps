package com.zhengtou.cf.user.service;

import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.user.pojo.vo.role.MenuPermissionVO;
import com.zhengtou.cf.user.pojo.vo.role.MenuVO;

import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 * 栏目服务
 */
public interface MenuService {
    /**
     * 查看目录结构
     */
    List<MenuVO> queryAllMenu();

    /**
     * 查看子目录对应得权限
     */
    List<MenuPermissionVO> queryAllChildMenuPermission();

    /**
     * 根据目录id查下面权限
     */
    List<MenuPermissionVO> queryChildMenuPermission(long menuId);

    /**
     * 查看所有父级栏目
     */
    List<MenuVO> queryParentMenu();

    /**
     * 新建栏目
     */
    void saveMenu(String name,String path,String title,Long parentId,Long childrenId) throws BaseException;
}
