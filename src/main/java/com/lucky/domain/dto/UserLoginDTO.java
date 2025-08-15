package com.lucky.domain.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String phone;
    private String password;
    private String userName;
    private String token;
}
