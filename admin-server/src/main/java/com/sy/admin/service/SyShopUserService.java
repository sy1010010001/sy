package com.sy.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sy.common.dto.admin.SyShopMenuDto;
import com.sy.common.dto.admin.SyShopUserLoginDTO;
import com.sy.common.entity.admin.SyShopUser;
import com.sy.common.rest.R;

public interface SyShopUserService extends IService<SyShopUser> {
    /**
     * 用户登录
     * @param syShopUserLoginDTO
     * @return
     */
    R login(SyShopUserLoginDTO syShopUserLoginDTO);

    /**
     * 用户注册
     * @param syShopUserLoginDTO
     * @return
     */
    R register(SyShopUserLoginDTO syShopUserLoginDTO);

    /**
     * 用户注销
     * @return
     */
    R logout();

}
