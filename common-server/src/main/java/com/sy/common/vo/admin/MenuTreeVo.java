package com.sy.common.vo.admin;

import com.sy.common.entity.admin.SyShopMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuTreeVo {
    private Long id;
    private String name;
    private Long pid;
    private List<MenuTreeVo> children;
}
