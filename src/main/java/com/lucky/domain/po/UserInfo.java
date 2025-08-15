package com.lucky.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Data
@TableName("tb_user_info")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id",type = IdType.AUTO)
    private Long id;
//    昵称
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
