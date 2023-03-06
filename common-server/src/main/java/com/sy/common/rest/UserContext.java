package com.sy.common.rest;

import com.sy.common.entity.admin.SyShopUser;

import java.util.Optional;

public class UserContext {
    private static final ThreadLocal<SyShopUser> thread=new ThreadLocal<>();

    public static void setUser(SyShopUser syShopUser){
        SyShopUser shopUser = thread.get();
        if (shopUser!=null){
            return;
        }
        thread.set(syShopUser);
    }

    public static SyShopUser getUser(){
        SyShopUser shopUser = thread.get();
        if (shopUser!=null){
            return shopUser;
        }
        return new SyShopUser();
    }

    public static void remove(){
        thread.remove();
    }
}
