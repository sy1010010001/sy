package com.sy.common.dto.admin;

import com.sy.common.groups.admin.AddGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyShopMenuDto {
    @NotNull(message = "父id为空",groups = {AddGroup.class})
    private Long pid;
    @NotNull(message = "菜单名称为空",groups = {AddGroup.class})
    private String name;
}
