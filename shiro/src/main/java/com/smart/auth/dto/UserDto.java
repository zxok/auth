package com.smart.auth.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String  password;
    private int locked;
}
