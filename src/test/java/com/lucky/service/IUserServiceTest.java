package com.lucky.service;

import com.lucky.domain.po.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class IUserServiceTest {
    @Autowired
    private IUserService userService;
    @Test
    void addUser(){
        User user = new User();
        user.setUserName("xixixixixixixixix");
        user.setPhone("15196788024");
        user.setPassword("123456");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userService.save(user);
    }
}