package com.sy.admin.controller;

import com.sy.admin.service.SyShopUserService;
import com.sy.common.dto.admin.SyShopUserLoginDTO;
import com.sy.common.entity.admin.SyShopUser;
import com.sy.common.groups.admin.LoginGroup;
import com.sy.common.groups.admin.RegisterGroup;
import com.sy.common.rest.R;
import com.sy.common.rest.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("syShopUser")
public class SyShopUserController {

    @Autowired
    private SyShopUserService syShopUserService;

    /**
     * 用户登录
     * @param syShopUserLoginDTO
     * @return
     */
    @RequestMapping("login")
    public R login(@RequestBody @Validated(value = {LoginGroup.class}) SyShopUserLoginDTO syShopUserLoginDTO){
       return syShopUserService.login(syShopUserLoginDTO);
    }

    /**
     * 用户注册
     * @param syShopUserLoginDTO
     * @return
     */
    @RequestMapping("register")
    public R register(@RequestBody @Validated(value = {RegisterGroup.class}) SyShopUserLoginDTO syShopUserLoginDTO){
        return syShopUserService.register(syShopUserLoginDTO);
    }

    /**
     * 用户注销
     * @return
     */
    @RequestMapping("logout")
    public R logout(){
        return syShopUserService.logout();
    }
}
