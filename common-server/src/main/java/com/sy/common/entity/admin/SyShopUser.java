package com.sy.common.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SyShopUser {
    //主键
    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    //手机号
    private String phone;
    //状态
    private Integer status;
    //创建日期
    private Date createTime;
    //更新日期
    private Date updateTime;

}
