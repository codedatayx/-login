package com.lucky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.domain.dto.LoginFormDTO;
import com.lucky.domain.dto.Result;
import com.lucky.domain.dto.UserLoginDTO;
import com.lucky.domain.po.User;
import jakarta.servlet.http.HttpSession;

/*
* 服务类
*
*
* */
public interface IUserService extends IService<User> {


    void deductmoney(Long id, Long money);

    Result login(UserLoginDTO userlogin);

    Result sendCode(String phone, HttpSession session);

    Result loginByCode(LoginFormDTO loginForm, HttpSession session);
}
