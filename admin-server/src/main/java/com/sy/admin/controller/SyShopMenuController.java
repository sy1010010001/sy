package com.sy.admin.controller;

import com.sy.admin.service.SyShopMenuService;
import com.sy.admin.service.SyShopUserService;
import com.sy.common.dto.admin.SyShopMenuDto;
import com.sy.common.groups.admin.AddGroup;
import com.sy.common.rest.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("syShopMenu")
public class SyShopMenuController {

    @Autowired
    private SyShopMenuService syShopMenuService;

    /**
     * 菜单添加
     * @param syShopMenuDto
     * @return
     */
    @RequestMapping("add")
    public R add(@RequestBody @Validated(value = {AddGroup.class}) SyShopMenuDto syShopMenuDto){
       return syShopMenuService.add(syShopMenuDto);
    }

    /**
     * 菜单删除
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable(value = "id")Long id){
       return syShopMenuService.delete(id);
    }

    /**
     * 查询菜单列表
     * @return
     */
    @RequestMapping("menuList")
    public R menuList(){
        return syShopMenuService.menuList();
    }

    /**
     * 给用户添加菜单权限
     * @param menuId
     * @param uid
     * @return
     */
    @RequestMapping("addMenuForUser")
    public R addMenuForUser(Long menuId,Long uid){
        return syShopMenuService.addMenuForUser(menuId,uid);
    }

    /**
     * 查询某个用户下菜单权限
     * @param uid
     * @return
     */
    @RequestMapping("/userMenuList/{uid}")
    public R userMenuList(@PathVariable("uid")Long uid){
        return syShopMenuService.userMenuList(uid);
    }


}
