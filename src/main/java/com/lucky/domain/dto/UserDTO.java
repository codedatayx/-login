package com.lucky.domain.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String phone;
    private String password;
    private Long money;
    private String username;
    private String token;
}
