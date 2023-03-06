package com.sy.common.dto.admin;

import com.sy.common.groups.admin.LoginGroup;
import com.sy.common.groups.admin.RegisterGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyShopUserLoginDTO {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名为空",groups = {RegisterGroup.class, LoginGroup.class})
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码为空",groups = {RegisterGroup.class, LoginGroup.class})
    private String password;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称为空",groups = {RegisterGroup.class})
    private String nickname;
    /**
     * 手机号
     */
    @NotBlank(message = "手机号为空",groups = {RegisterGroup.class})
    private String phone;
}
