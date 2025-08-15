package com.lucky.domain.vo;

import lombok.Data;

import java.time.LocalDate;


@Data
public class UserInfoVO {

    private String userName;
    //    城市
    private String city;
    //    介绍
    private String introduce;
    //    性别
    private String gender;
    //    年龄
    private Integer age;
    //    邮箱
    private String email;
    //    生日
    private LocalDate birthday;
    //    创建时间
    private LocalDate createTime;
    //    修改时间
    private LocalDate updateTime;
}
