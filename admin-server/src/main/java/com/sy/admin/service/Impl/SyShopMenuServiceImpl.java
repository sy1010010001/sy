package com.sy.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sy.admin.mapper.SyShopMenuMapper;
import com.sy.admin.service.SyShopMenuService;
import com.sy.admin.service.SyShopMenuUserService;
import com.sy.common.constants.AdminConstants;
import com.sy.common.dto.admin.SyShopMenuDto;
import com.sy.common.entity.admin.SyShopMenu;
import com.sy.common.entity.admin.SyShopMenuUser;
import com.sy.common.execeptions.MyRuntimeException;
import com.sy.common.rest.R;
import com.sy.common.utils.MenuTreeUtil;
import com.sy.common.utils.SnowflakeIdUtils;
import com.sy.common.vo.admin.MenuTreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SyShopMenuServiceImpl extends ServiceImpl<SyShopMenuMapper, SyShopMenu> implements SyShopMenuService {

    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private SyShopMenuUserService syShopMenuUserService;

    @Autowired
    private SyShopMenuMapper syShopMenuMapper;
    /**
     * 菜单添加
     * @param syShopMenuDto
     * @return
     */
    @Override
    public R add(SyShopMenuDto syShopMenuDto) {
        SyShopMenu syShopMenu = new SyShopMenu();
        BeanUtils.copyProperties(syShopMenuDto,syShopMenu);
        syShopMenu.setId(SnowflakeIdUtils.nextId());
        syShopMenu.setStatus(1);
        syShopMenu.setCreateTime(new Date());
        syShopMenu.setUpdateTime(new Date());
        save(syShopMenu);
        return R.ok("菜单添加成功!");
    }

    /**
     * 菜单删除
     * @param id
     * @return
     */
    @Override
    public R delete(Long id) {
        if (Objects.isNull(id)){
            throw new MyRuntimeException("菜单id为空!");
        }
        SyShopMenu syShopMenu = getById(id);
        if (Objects.isNull(syShopMenu)){
            throw new MyRuntimeException("菜单不存在!");
        }
        syShopMenu.setStatus(0);
        syShopMenu.setUpdateTime(new Date());
        updateById(syShopMenu);
        return R.ok("菜单删除成功!");
    }

    /**
     * 菜单列表
     * @return
     */
    @Override
    public R menuList() {
        List<MenuTreeVo> menuTreeVoList = redisTemplate.opsForList().range(AdminConstants.MENU_LIST, 0, -1);
        if (!CollectionUtils.isEmpty(menuTreeVoList)){
            return R.ok(menuTreeVoList);
        }
        QueryWrapper<SyShopMenu> syShopMenuQueryWrapper = new QueryWrapper<>();
        syShopMenuQueryWrapper.eq("status",1);
        List<SyShopMenu> syShopMenus = list(syShopMenuQueryWrapper);
        List<MenuTreeVo> menuTreeVos = new MenuTreeUtil(syShopMenus).buildTree();
        redisTemplate.opsForList().leftPushAll(AdminConstants.MENU_LIST,menuTreeVos);
        return R.ok(menuTreeVos);
    }

    /**
     * 给用户添加菜单权限
     * @param menuId
     * @param uid
     * @return
     */
    @Override
    public R addMenuForUser(Long menuId, Long uid) {
        if (Objects.isNull(menuId)){
            throw new MyRuntimeException("菜单id为空!");
        }
        if (Objects.isNull(uid)){
            throw new MyRuntimeException("用户id为空!");
        }
        QueryWrapper<SyShopMenuUser> syShopMenuUserQueryWrapper = new QueryWrapper<>();
        syShopMenuUserQueryWrapper.eq("mid",menuId).eq("uid",uid);
        SyShopMenuUser shopMenuUser = syShopMenuUserService.getOne(syShopMenuUserQueryWrapper);
        if (Objects.isNull(shopMenuUser)){
            SyShopMenuUser syShopMenuUser = new SyShopMenuUser();
            syShopMenuUser.setId(SnowflakeIdUtils.nextId());
            syShopMenuUser.setMid(menuId);
            syShopMenuUser.setUid(uid);
            syShopMenuUser.setStatus(1);
            syShopMenuUser.setCreateTime(new Date());
            syShopMenuUser.setUpdateTime(new Date());
            syShopMenuUserService.save(syShopMenuUser);
            return R.ok("添加成功!");
        }
        if (shopMenuUser.getStatus()==1){
            throw new MyRuntimeException("菜单存在!");
        }
        shopMenuUser.setStatus(1);
        shopMenuUser.setUpdateTime(new Date());
        syShopMenuUserService.updateById(shopMenuUser);
        return R.ok("添加成功!");
    }

    /**
     * 查询某个用户下菜单权限
     * @param uid
     * @return
     */
    @Override
    public R userMenuList(Long uid) {
        if (Objects.isNull(uid)){
            throw new MyRuntimeException("用户id为空!");
        }
        QueryWrapper<SyShopMenuUser> syShopMenuUserQueryWrapper = new QueryWrapper<>();
        syShopMenuUserQueryWrapper.eq("uid",uid).eq("status",1);
        List<SyShopMenuUser> syShopMenuUserList = syShopMenuUserService.list(syShopMenuUserQueryWrapper);
        Set<Long> mids = syShopMenuUserList.stream().map(SyShopMenuUser::getMid).collect(Collectors.toSet());
        QueryWrapper<SyShopMenu> syShopMenuQueryWrapper = new QueryWrapper<>();
        syShopMenuQueryWrapper.in("id",mids);
        List<SyShopMenu> syShopMenuList = list(syShopMenuQueryWrapper);
        List<MenuTreeVo> menuTreeVoList = new MenuTreeUtil(syShopMenuList).buildTree();
        return R.ok(menuTreeVoList);
    }

    @Override
    public R testMybatis(Integer flag) {
       List<SyShopMenu> syShopMenus= syShopMenuMapper.testMybatis(flag);
        return R.ok(syShopMenus);
    }
}
