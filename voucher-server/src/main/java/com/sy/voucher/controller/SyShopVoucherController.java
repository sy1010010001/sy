package com.sy.voucher.controller;

import com.sy.common.dto.voucher.SyShopVoucherDto;
import com.sy.common.rest.R;
import com.sy.voucher.service.SyShopVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("syShopVoucher")
public class SyShopVoucherController {

    @Autowired
    private SyShopVoucherService syShopVoucherService;

    /**
     * 优惠券添加
     * @param syShopVoucherDto
     * @return
     */
    @RequestMapping("add")
    public R add(@RequestBody SyShopVoucherDto syShopVoucherDto){
       return syShopVoucherService.add(syShopVoucherDto);
    }
}
