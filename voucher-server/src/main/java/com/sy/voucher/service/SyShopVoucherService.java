package com.sy.voucher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sy.common.dto.voucher.SyShopVoucherDto;
import com.sy.common.entity.voucher.SyShopVoucher;
import com.sy.common.rest.R;

public interface SyShopVoucherService extends IService<SyShopVoucher> {

    /**
     * 优惠券添加
     * @param syShopVoucherDto
     * @return
     */
    R add(SyShopVoucherDto syShopVoucherDto);
}
