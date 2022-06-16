package com.example.hwthree.jwt.dto;

import lombok.Data;

@Data
public class JwtLoginRequestDto {

    private String username;
    private String password;
}
