package com.lucky.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.constant.JwtClaimsConstant;
import com.lucky.constant.MassageConstant;
import com.lucky.domain.dto.LoginFormDTO;
import com.lucky.domain.dto.Result;
import com.lucky.domain.dto.UserDTO;
import com.lucky.domain.dto.UserLoginDTO;
import com.lucky.domain.po.User;

import com.lucky.domain.vo.UserVO;
import com.lucky.mapper.UserMapper;
import com.lucky.properties.JwtProperties;
import com.lucky.service.IUserService;
import com.lucky.utils.JwtUtils;
import com.lucky.constant.TimeConstant;
import com.lucky.utils.UserHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.lucky.constant.RedisConstant.LOGIN_USER_TOKEN;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private JwtProperties jwtProperties;
    @Qualifier("redisTemplate")

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void deductmoney(Long id,  Long money) {
        //1.查询用户
        User user = getById(id);
        //2.校验用户状态
        if (user == null||user.getStatus()==2) {
            throw new RuntimeException("用户状态异常");
        }
        //3，检测余额是否充足
        if(user.getMoney()<money){
            throw new RuntimeException("用户余额不足");
        }
        //4.扣减余额
        Long remianMoney = user.getMoney()-money;
        lambdaUpdate()
                .set(User::getMoney,remianMoney)
                .eq(User::getId,id)
                .update();
    }

    @Override
    public Result login(UserLoginDTO userlogin) {
        String phone = userlogin.getPhone();
        if(phone== null){
            return Result.fail(MassageConstant.PHONE_ERROR);
        }
        User user = query().eq("phone",phone).one();
        if(user==null){
            return Result.fail(MassageConstant.USER_ERROR);
        }
        HashMap<String,Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtils.generateToken(jwtProperties.getUserSecretKey(), TimeConstant.EXPIRATION,claims);

        if (!userlogin.getPassword().equals(user.getPassword())){
            return Result.fail(MassageConstant.PSSWORD_ERROR);
        }
        UserVO userVO = UserVO.builder().id(user.getId()).username(user.getUserName()).token(token).build();
        UserHolder.saveUser(BeanUtil.copyProperties(userVO,UserDTO.class));
        System.out.println(UserHolder.getUser());
        return Result.ok(userVO);
    }

    @Override
    public Result sendCode(String phone, HttpSession session) {
        if(phone==null){
            return Result.fail(MassageConstant.PHONE_ERROR);
        }
        User user = query().eq("phone",phone).one();
        String code = RandomUtil.randomNumbers(6);
        stringRedisTemplate.opsForValue().set(LOGIN_USER_TOKEN+phone,code,30, TimeUnit.MINUTES);
        System.out.println("验证码: "+code);
        return Result.ok();
    }

    @Override
    public Result loginByCode(LoginFormDTO loginForm, HttpSession session) {
        String phone = loginForm.getPhone();
        if(phone==null){
            return Result.fail(MassageConstant.PHONE_ERROR);
        }
        String code = loginForm.getCode();
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_USER_TOKEN+phone);
        if(code==null || !code.equals(cacheCode)){
            return Result.fail(MassageConstant.CODE_ERROR);
        }
        User user = query().eq("phone",phone).one();
        if(user==null){
            user = creatUserWithPhone(phone);
        }
        return Result.ok(user);
    }
    private User creatUserWithPhone(String phone) {
        User user = new User();
        user.setPhone(phone);
        user.setUserName("yangxuan_"+RandomUtil.randomString(10));
        //保存用户
        save(user);
        return user;
    }


}
