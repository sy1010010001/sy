package com.sy.common.utils;

import com.sy.common.entity.admin.SyShopMenu;
import com.sy.common.vo.admin.MenuTreeVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuTreeUtil {
    // 流程：数据库查出的菜单记录装载在承载菜单的列表中---
    //         构建树（获取根节点，遍历根节点，对每一个根节点构建子树）---得到最终树形菜单

    // 承载菜单的列表
    private List<MenuTreeVo> menuList = new ArrayList<>();
    // 带参构造器，将数据库中的菜单数据记录，装载在我们承载菜单的列表中
    public MenuTreeUtil(List<SyShopMenu> syShopMenus){
        for (SyShopMenu syShopMenu : syShopMenus) {
            MenuTreeVo menuTreeVo = new MenuTreeVo();
            BeanUtils.copyProperties(syShopMenu,menuTreeVo);
            menuList.add(menuTreeVo);
        }
    }

    // 获取根节点
    public List<MenuTreeVo> getRootNode(){
        List<MenuTreeVo> rootNode = new ArrayList<>();
        for (MenuTreeVo menu : menuList) {
            if (menu.getPid().intValue()==0){
                rootNode.add(menu);
            }
        }
        return rootNode;
    }

    // 构建子树
    public MenuTreeVo buildChildren(MenuTreeVo rootNode){
        List<MenuTreeVo> childrenTree = new ArrayList<>();
        for (MenuTreeVo menu : menuList) {
            if (menu.getPid().equals(rootNode.getId())){
                childrenTree.add(buildChildren(menu));
            }
        }
        rootNode.setChildren(childrenTree);
        return rootNode;
    }

    // 构建树
    public List<MenuTreeVo> buildTree(){
        List<MenuTreeVo> menus = getRootNode();
        for (MenuTreeVo menu : menus) {
            buildChildren(menu);
        }
        return menus;
    }

}
