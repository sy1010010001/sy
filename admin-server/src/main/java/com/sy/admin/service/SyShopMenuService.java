package com.sy.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sy.common.dto.admin.SyShopMenuDto;
import com.sy.common.entity.admin.SyShopMenu;
import com.sy.common.rest.R;

public interface SyShopMenuService extends IService<SyShopMenu> {
    /**
     * 菜单添加
     * @param syShopMenuDto
     * @return
     */
    R add(SyShopMenuDto syShopMenuDto);

    /**
     * 菜单删除
     * @param id
     * @return
     */
    R delete(Long id);

    /**
     * 菜单列表
     * @return
     */
    R menuList();

    /**
     * 给用户添加菜单权限
     * @param menuId
     * @param uid
     * @return
     */
    R addMenuForUser(Long menuId, Long uid);

    /**
     * 查询某个用户下菜单权限
     * @param uid
     * @return
     */
    R userMenuList(Long uid);

    R testMybatis(Integer flag);
}
