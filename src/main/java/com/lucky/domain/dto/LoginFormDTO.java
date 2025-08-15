package com.lucky.domain.dto;

import lombok.Data;

@Data
public class LoginFormDTO {
    private String phone;
    private String password;
    private String code;
}
