package com.sy.voucher.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sy.common.dto.voucher.SyShopVoucherDto;
import com.sy.common.entity.voucher.SyShopVoucher;
import com.sy.common.rest.R;
import com.sy.common.utils.SnowflakeIdUtils;
import com.sy.voucher.mapper.SyShopVoucherMapper;
import com.sy.voucher.service.SyShopVoucherService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SyShopVoucherServiceImpl extends ServiceImpl<SyShopVoucherMapper, SyShopVoucher> implements SyShopVoucherService {
    /**
     * 优惠券添加
     * @param syShopVoucherDto
     * @return
     */
    @Override
    public R add(SyShopVoucherDto syShopVoucherDto) {
        SyShopVoucher syShopVoucher = new SyShopVoucher();
        BeanUtils.copyProperties(syShopVoucherDto,syShopVoucher);
        syShopVoucher.setId(SnowflakeIdUtils.nextId());
        syShopVoucher.setCreateTime(new Date());
        syShopVoucher.setUpdateTime(new Date());
        save(syShopVoucher);
        return R.ok("添加成功!");
    }
}
