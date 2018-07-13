package com.zhengtou.cf.user.service.impl;

import com.zhengtou.cf.common.enums.RtnResultEnum;
import com.zhengtou.cf.common.exception.BaseException;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.utils.StringUtils;
import com.zhengtou.cf.user.pojo.vo.role.MenuPermissionVO;
import com.zhengtou.cf.user.pojo.vo.role.MenuVO;
import com.zhengtou.cf.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 葛文镇
 * email:15258397904@163.com
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    BaseDao dao;

    @Override
    public List<MenuVO> queryAllMenu() {
        List<MenuVO> menuVOS = dao.find("select new com.zhengtou.cf.user.pojo.vo.role.MenuVO(m.id,m.name,m.path,m.title,cm.id,cm.name,cm.path,cm" +
                ".title) from MenuEntity m inner join MenuEntity cm on cm.parent=m order by m.id,m.createTime ");
        HashMap<Long, MenuVO> tempMap = new HashMap<>();
        for (MenuVO menuVO : menuVOS) {
            if (tempMap.containsKey(menuVO.getId())) {
                tempMap.get(menuVO.getId()).setChildren(new MenuVO(menuVO.getChildrenId(), menuVO.getChildrenName(), menuVO.getChildrenpath(),
                        menuVO.getChildrentitle()));
            } else {
                tempMap.put(menuVO.getId(), new MenuVO(menuVO.getId(), menuVO.getName(), menuVO.getPath(), menuVO.getTitle()));
            }
        }
        return (List<MenuVO>) tempMap.values();
    }

    @Override
    public List<MenuPermissionVO> queryAllChildMenuPermission() {
        return dao.find("select new com.zhengtou.cf.user.pojo.vo.role.MenuPermissionVO(mp.menu.id ,mp.menu.name,mp.permission.id,mp.permission" +
                ".name,mp.permission.path,mp.permission.type) from MenuPermissionEntity mp where mp.isDeleted=false ");
    }

    @Override
    public List<MenuPermissionVO> queryChildMenuPermission(long menuId) {
        return dao.find("select new com.zhengtou.cf.user.pojo.vo.role.MenuPermissionVO(mp.permission.id,mp.permission" +
                ".name,mp.permission.path,mp.permission.type) from MenuPermissionEntity mp where mp.isDeleted=false and mp.menu.id=?0", new
                Object[]{menuId});
    }

    @Override
    public List<MenuVO> queryParentMenu() {
        return dao.find("select new com.zhengtou.cf.user.pojo.vo.role.MenuVO(m.id,m.name,m.path,m.title) from MenuEntity m " +
                "where m.isDeleted=false and m.path is null");
    }

    @Override
    public void saveMenu(String name,String path,String title,Long parentId,Long childrenId) throws BaseException {
        if(childrenId!=null && StringUtils.isNotBlank(path)){
            throw new BaseException(RtnResultEnum.E050005);
        }
        
    }


}
