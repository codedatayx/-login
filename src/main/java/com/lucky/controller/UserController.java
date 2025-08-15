package com.lucky.controller;

import cn.hutool.core.bean.BeanUtil;
import com.lucky.domain.dto.LoginFormDTO;
import com.lucky.domain.dto.Result;
import com.lucky.domain.dto.UserDTO;
import com.lucky.domain.dto.UserLoginDTO;
import com.lucky.domain.po.User;
import com.lucky.domain.po.UserInfo;
import com.lucky.domain.vo.UserInfoVO;
import com.lucky.service.IUserInfoService;
import com.lucky.service.IUserService;
import com.lucky.utils.UserHolder;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理接口")
@EnableKnife4j
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private IUserInfoService userInfoService;

    @Operation(summary = "密码登录")
    @PostMapping("/login")
    public Result login(
            @RequestBody UserLoginDTO userlogin){
        return userService.login(userlogin);
    }
    @Operation(summary = "发送验证码")
    @PostMapping("code")
    public Result sendCode(@RequestParam String phone, HttpSession session){
        return userService.sendCode(phone,session);
    }
    @Operation(summary = "验证码登录")
    @PostMapping("/loginByCode")
    public Result login(
            @RequestBody LoginFormDTO loginForm, HttpSession session){
        return userService.loginByCode(loginForm,session);
    }

    @Operation(summary = "主页")
    @GetMapping("/info/{id}")
    public Result info(@Parameter(name = "id",in = ParameterIn.PATH)
            @PathVariable("id") Long id){
        return userInfoService.userInfo(id);

    }

    @Operation(summary = "修改个人信息byUserInfo")
    @PostMapping("info")
    public Result save(
            @RequestBody UserInfo userInfo
    ){
        return userInfoService.saveMassage(userInfo);
    }

}
