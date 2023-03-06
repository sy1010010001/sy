package com.sy.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sy.admin.mapper.SyShopUserMapper;
import com.sy.admin.service.SyShopUserService;
import com.sy.common.constants.AdminConstants;
import com.sy.common.dto.admin.SyShopUserLoginDTO;
import com.sy.common.entity.admin.AccessToken;
import com.sy.common.entity.admin.SyShopUser;
import com.sy.common.execeptions.MyRuntimeException;
import com.sy.common.rest.R;
import com.sy.common.rest.UserContext;
import com.sy.common.utils.JwtUtil;
import com.sy.common.utils.SnowflakeIdUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class SyShopUserServiceImpl extends ServiceImpl<SyShopUserMapper, SyShopUser> implements SyShopUserService {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 用户登录
     * @param syShopUserLoginDTO
     * @return
     */
    @Override
    public R login(SyShopUserLoginDTO syShopUserLoginDTO) {
        QueryWrapper<SyShopUser> syShopUserQueryWrapper = new QueryWrapper<>();
        syShopUserQueryWrapper.eq("username",syShopUserLoginDTO.getUsername());
        SyShopUser user = this.getOne(syShopUserQueryWrapper);
        if (Objects.isNull(user)){
            throw new MyRuntimeException("用户名输入错误");
        }
        if (!user.getPassword().equals(syShopUserLoginDTO.getPassword())){
            throw new MyRuntimeException("密码输入错误");
        }
        //用户名密码输入正确生成token
        String jwtToken = JwtUtil.getJwtToken(user.getId(), user.getNickname());
        redisTemplate.opsForValue().set(AdminConstants.LOGIN_USER+user.getId(),jwtToken,30,TimeUnit.MINUTES);
        AccessToken accessToken = new AccessToken();
        accessToken.setToken(jwtToken);
        return R.ok(accessToken);
    }

    /**
     * 用户注册
     * @param syShopUserLoginDTO
     * @return
     */
    @Override
    public R register(SyShopUserLoginDTO syShopUserLoginDTO) {
        //根据用户名获取分布式锁
        RLock lock = redissonClient.getLock(AdminConstants.REGISTER_LOCK+syShopUserLoginDTO.getUsername());
        lock.lock();
        try {
            QueryWrapper<SyShopUser> syShopUserQueryWrapper = new QueryWrapper<>();
            syShopUserQueryWrapper.eq("username", syShopUserLoginDTO.getUsername());
            SyShopUser syShopUser = this.getOne(syShopUserQueryWrapper);
            if (Objects.nonNull(syShopUser)) {
                return R.error("用户名存在");
            }
            SyShopUser shopUser = new SyShopUser();
            BeanUtils.copyProperties(syShopUserLoginDTO,shopUser);
            shopUser.setId(SnowflakeIdUtils.nextId());
            shopUser.setStatus(1);
            shopUser.setCreateTime(new Date());
            shopUser.setUpdateTime(new Date());
            this.save(shopUser);
        }catch (Exception e){
            return R.error("请求异常,请稍后重试");
        }finally {
            lock.unlock();
        }
         return R.ok("注册成功");
    }

    /**
     * 用户注销
     * @return
     */
    @Override
    public R logout() {
        SyShopUser user = UserContext.getUser();
        redisTemplate.delete(AdminConstants.LOGIN_USER+user.getId());
        return R.ok("注销成功");
    }
}
