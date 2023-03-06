package com.sy.admin.controller;

import com.sy.admin.service.SyShopMenuService;
import com.sy.admin.service.SyShopUserService;
import com.sy.common.execeptions.MyRuntimeException;
import com.sy.common.rest.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private SyShopMenuService syShopMenuService;


    @RequestMapping("test01")
    public void test01(){
        Set<String> strings = new HashSet<>();
        strings.add("220436151");
        strings.add("123456");
        strings.add("147258369");
        String join = StringUtils.join(strings, ",");
        System.out.println(join);
    }

    @RequestMapping("testMybatis")
    public R testMybatis(Integer flag){
       return syShopMenuService.testMybatis(flag);
    }
}
