package com.sy.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sy.common.entity.admin.SyShopMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyShopMenuMapper extends BaseMapper<SyShopMenu> {
    List<SyShopMenu> testMybatis(Integer flag);
}
