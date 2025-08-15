package com.lucky.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
@Data
public class User implements Serializable {
        private static final long serialVersionUID = 1L;
        /*
         * 主键
         */
        @TableId(value = "id",type = IdType.AUTO)
        private Long id;
        /*
         * 手机号码
         */

        private String password;

        /**
         * 昵称，默认是随机字符
         */
        private String phone;
        /**
         * 密码，加密存储
        */
        private String userName;

        /**
         * 用户头像
         */
        private String icon = "";

        /*
         * 创建时间
         */
        private LocalDateTime createTime;

        /**
         * 更新时间
         */
        private LocalDateTime updateTime;

        /*
         * 余额
         *
         * */
        private Long money;

        /*
         * 状态
         *
         * */
        private int status;

    }

