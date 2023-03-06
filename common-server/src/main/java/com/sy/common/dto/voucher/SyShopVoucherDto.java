package com.sy.common.dto.voucher;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sy.common.groups.admin.AddGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SyShopVoucherDto {
    @NotBlank(message = "优惠券名称为空",groups = {AddGroup.class})
    private String voucherName;
    @NotNull(message = "优惠券名称为空",groups = {AddGroup.class})
   private BigDecimal voucherAmount;
    @NotNull(message = "优惠券名称为空",groups = {AddGroup.class})
   private Integer count;
    @NotNull(message = "优惠券名称为空",groups = {AddGroup.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Date endTime;


}
