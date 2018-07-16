package com.harmony.devops.user.user.controller;

import com.zhengtou.cf.common.component.redis.MyRedisComponent;
import com.zhengtou.cf.common.pojo.dao.BaseDao;
import com.zhengtou.cf.common.pojo.vo.ListResponseVO;
import com.zhengtou.cf.common.pojo.vo.NetVO;
import com.harmony.devops.user.user.pojo.vo.role.MenuPermissionVO;
import com.harmony.devops.user.user.pojo.vo.role.MenuVO;
import com.harmony.devops.user.user.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("file")
public class MenuController {
    @Autowired
    MyRedisComponent myRedisComponent;
    @Autowired
    BaseDao dao;
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "queryAllMenu", method = RequestMethod.POST)
    @ApiOperation("查询目录结构2级")
    public NetVO queryAllMenu() {
        List<MenuVO> menuVOS=menuService.queryAllMenu();
        return new ListResponseVO(menuVOS);
    }

    @RequestMapping(value = "queryChildMenuPermission/{menuId}", method = RequestMethod.POST)
    @ApiOperation("根据栏目下权限")
    public NetVO queryChildMenuPermissionByMenuId(@PathVariable long menuId) {
        List<MenuPermissionVO> menuPermissionVOS=menuService.queryChildMenuPermission(menuId);
        return new ListResponseVO(menuPermissionVOS);
    }

    @RequestMapping(value = "queryAllChildMenuPermission", method = RequestMethod.POST)
    @ApiOperation("查看各栏目及其下权限")
    public NetVO queryAllChildMenuPermission() {
        List<MenuPermissionVO> menuPermissionVOS=menuService.queryAllChildMenuPermission();
        return new ListResponseVO(menuPermissionVOS);
    }


}
